from qiskit_optimization import QuadraticProgram
from app.schemas.optimization import OptimizationRequest

# Build a QUBO (Quadratic Unconstrained Binary Optimization) Problem
# from the optimization request
# - Variables are binary (0 or 1).
# - The objective is to minimize a cost function.
# - Constraints, if any, are incorporated into the objective function.
def build_quadratic_program(request: OptimizationRequest) -> QuadraticProgram:
    qp = QuadraticProgram(name=f"microgrid_{request.microgrid_id}")

    # UOne binary variable for each discretized power level, for each energy source.
    for source in request.sources:
        qp.binary_var(name=f"src_{source.id}")

    # Goal: Minimize the total cost of energy production
    linear = {f"src_{s.id}": s.cost_per_kwh * s.capacity_kw for s in request.sources}
    qp.minimize(linear=linear)

    # Constraint: The sum of sources must cover the demand
    qp.linear_constraint(
        linear={f"src_{s.id}": s.capacity_kw for s in request.sources},
        sense=">=",
        rhs=request.demand_kw,
        name="demand_coverage"
    )
    return qp