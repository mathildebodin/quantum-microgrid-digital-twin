import { apiClient } from './client';

export const simulationApi = {
  simulate: (simulationRequestDto) =>
    apiClient.post('/simulation', simulationRequestDto).then((res) => res.data)
};