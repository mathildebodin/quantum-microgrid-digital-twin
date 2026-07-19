import { optimizationClient } from './client';

export const optimizationApi = {
  // Runs the main solver (QAOA with classical fallback)
  optimize: (optimizationRequestDto) =>
    optimizationClient.post('/optimization', optimizationRequestDto).then((res) => res.data),

  // Runs QAOA, exact classical, and greedy solvers side by side
  compare: (optimizationRequestDto) =>
    optimizationClient.post('/optimization/compare', optimizationRequestDto).then((res) => res.data)
};