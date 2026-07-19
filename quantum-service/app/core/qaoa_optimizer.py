import time
from qiskit_algorithms import QAOA
from qiskit_algorithms.optimizers import COBYLA
from qiskit.primitives import Sampler
from qiskit_optimization.algorithms import MinimumEigenOptimizer
from app.schemas.optimization import OptimizationRequest, OptimizationResult, SourceAllocation
from app.core.problem_builder import build_quadratic_program

def solve_with_qaoa(request: OptimizationRequest) -> OptimizationResult:
    start = time.perf_counter()
    qp = build_quadratic_program(request)

    qaoa = QAOA(sampler=Sampler(), optimizer=COBYLA(maxiter=100), reps=2)
    solver = MinimumEigenOptimizer(qaoa)

    try:
        result = solver.solve(qp)
        solver_used = "QAOA"
    except Exception:
        # Repli classique si le solveur quantique échoue ou est trop lent
        from qiskit_optimization.algorithms import CplexOptimizer
        result = CplexOptimizer().solve(qp)
        solver_used = "classical_fallback"

    allocations = [
        SourceAllocation(source_id=s.id, allocated_kw=s.capacity_kw * result.x[i])
        for i, s in enumerate(request.sources)
    ]

    return OptimizationResult(
        microgrid_id=request.microgrid_id,
        total_cost=result.fval,
        allocations=allocations,
        execution_time_ms=(time.perf_counter() - start) * 1000,
        solver_used=solver_used
    )