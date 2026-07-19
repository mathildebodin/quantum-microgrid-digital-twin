from pydantic import BaseModel
from typing import List, Optional

class EnergySourceInput(BaseModel):
    id: int
    type: str  # "solar", "wind", "battery", "grid"
    capacity_kw: float
    cost_per_kwh: float
    current_output_kw: Optional[float] = 0.0

class OptimizationRequest(BaseModel):
    microgrid_id: int
    demand_kw: float
    sources: List[EnergySourceInput]
    horizon_hours: int = 1

class SourceAllocation(BaseModel):
    source_id: int
    allocated_kw: float

class OptimizationResult(BaseModel):
    microgrid_id: int
    total_cost: float
    allocations: List[SourceAllocation]
    execution_time_ms: float
    solver_used: str  # "QAOA" ou "classical_fallback"