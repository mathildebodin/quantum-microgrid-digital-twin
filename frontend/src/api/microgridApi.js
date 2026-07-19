import { apiClient } from './client';

export const microgridApi = {
  getAll: () => apiClient.get('/microgrids').then((res) => res.data),

  getById: (id) => apiClient.get(`/microgrids/${id}`).then((res) => res.data),

  create: (microgridRequestDto) =>
    apiClient.post('/microgrids', microgridRequestDto).then((res) => res.data),

  delete: (id) => apiClient.delete(`/microgrids/${id}`)
};