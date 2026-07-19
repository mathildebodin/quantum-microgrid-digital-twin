from fastapi import APIRouter, HTTPException
from app.schemas.optimization import OptimizationRequest, OptimizationResult, ComparisonResult
from app.core.qaoa_optimizer import solve_with_qaoa
from app.core.comparator import compare_all_methods


router = APIRouter()

@router.post("/optimize", response_model=OptimizationResult)
async def optimize(request: OptimizationRequest):
    if not request.sources:
        raise HTTPException(status_code=400, detail="Aucune source d'énergie fournie")
    try:
        return solve_with_qaoa(request)
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Échec de l'optimisation : {str(e)}")
    
@router.post("/optimize/compare", response_model=ComparisonResult)
async def optimize_compare(request: OptimizationRequest):
    """Run and compare QAOA, exact classical, and greedy solvers side by side."""
    if not request.sources:
        raise HTTPException(status_code=400, detail="No energy source provided")
    try:
        return compare_all_methods(request)
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Comparison failed: {str(e)}")