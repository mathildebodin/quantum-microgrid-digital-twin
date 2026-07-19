import { useState } from 'react';
import {
  BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer
} from 'recharts';
import { optimizationApi } from '../api/optimizationApi';

// Runs /optimize/compare and shows QAOA vs exact classical vs greedy
// fallback side by side: cost bar chart + execution time bar chart.
export default function SolverComparison({ microgridId }) {
  const [demandKw, setDemandKw] = useState('');
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  async function handleCompare(event) {
    event.preventDefault();
    setError(null);
    setResult(null);

    if (!demandKw) {
      setError('Indique la demande en kW à couvrir.');
      return;
    }

    setLoading(true);
    try {
      const comparison = await optimizationApi.compare({
        microgridId,
        demandKw: parseFloat(demandKw),
        sources: [], // populated server-side from the microgrid's registered sources
        horizonHours: 1
      });
      setResult(comparison);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  const costData = result
    ? [
        { method: 'QAOA', cost: result.qaoaResult.totalCost },
        { method: 'Exact classique', cost: result.exactResult.totalCost },
        { method: 'Glouton', cost: result.greedyResult.totalCost }
      ]
    : [];

  const timeData = result
    ? [
        { method: 'QAOA', time: result.qaoaResult.executionTimeMs },
        { method: 'Exact classique', time: result.exactResult.executionTimeMs },
        { method: 'Glouton', time: result.greedyResult.executionTimeMs }
      ]
    : [];

  return (
    <div className="solver-comparison">
      <h2>Comparaison des solveurs</h2>

      <form onSubmit={handleCompare} className="compare-form">
        <label htmlFor="demand-kw">Demande à couvrir (kW)</label>
        <input
          id="demand-kw"
          type="number"
          min="0"
          step="0.1"
          value={demandKw}
          onChange={(e) => setDemandKw(e.target.value)}
          placeholder="150"
        />
        <button type="submit" disabled={loading}>
          {loading ? 'Calcul en cours…' : 'Comparer les solveurs'}
        </button>
      </form>

      {error && <p className="form-error">{error}</p>}

      {result && (
        <>
          <p className="cheapest-method">
            Méthode la moins coûteuse : <strong>{result.cheapestMethod}</strong>
          </p>

          <h3>Coût total (€)</h3>
          <ResponsiveContainer width="100%" height={200}>
            <BarChart data={costData}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="method" />
              <YAxis />
              <Tooltip formatter={(value) => `€${value.toFixed(2)}`} />
              <Bar dataKey="cost" fill="#2a78d6" radius={[4, 4, 0, 0]} />
            </BarChart>
          </ResponsiveContainer>

          <h3>Temps d'exécution (ms)</h3>
          <ResponsiveContainer width="100%" height={200}>
            <BarChart data={timeData}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="method" />
              <YAxis scale="log" domain={['auto', 'auto']} allowDataOverflow />
              <Tooltip formatter={(value) => `${value} ms`} />
              <Bar dataKey="time" fill="#eb6834" radius={[4, 4, 0, 0]} />
            </BarChart>
          </ResponsiveContainer>
        </>
      )}
    </div>
  );
}