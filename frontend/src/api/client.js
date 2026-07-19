import axios from 'axios';

// Base client for the Java (Spring Boot) backend
export const apiClient = axios.create({
  baseURL: import.meta.env.VITE_JAVA_API_URL,
  headers: { 'Content-Type': 'application/json' },
  timeout: 15000
});

// Longer timeout for optimization calls, since QAOA can take a few seconds
export const optimizationClient = axios.create({
  baseURL: import.meta.env.VITE_JAVA_API_URL,
  headers: { 'Content-Type': 'application/json' },
  timeout: 35000
});

// Central error handler: normalizes backend error shape ({message, status})
// so components don't need to know about axios internals.
function handleApiError(error) {
  if (error.response) {
    const message = error.response.data?.message || 'Une erreur est survenue côté serveur.';
    return { message, status: error.response.status };
  }
  if (error.request) {
    return { message: 'Impossible de contacter le serveur. Vérifiez que le backend est démarré.', status: 0 };
  }
  return { message: error.message, status: -1 };
}

apiClient.interceptors.response.use(
  (response) => response,
  (error) => Promise.reject(handleApiError(error))
);

optimizationClient.interceptors.response.use(
  (response) => response,
  (error) => Promise.reject(handleApiError(error))
);