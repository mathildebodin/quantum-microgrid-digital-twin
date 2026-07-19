import { useState } from 'react';
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer
} from 'recharts';
import { simulationApi } from '../api/simulationApi';

// Runs a simulation over N hours and plots solar/wind output, battery
// charge, and demand on the same time axis.
export default function SimulationChart({ microgridId }) {
  const [horizonHours, setHorizonHours] = useState(24);
  const [demandForecastKw, setDemandForecastKw] = useState('');
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  async function handleSimulate(event) {
    event.preventDefault();
    setError(null);
    setResult(null);
    setLoading(true);

    try {
      const simulation = await simulationApi.simulate({
        microgridId,
        horizonHours: parseInt(horizonHours, 10),
        demandForecastKw: demandForecastKw ? parseFloat(demandForecastKw) : null
      });
      setResult(simulation);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="simulation-chart">
      <h2>Simulation temporelle</h2>

      <form onSubmit={handleSimulate} className="simulation-form">
        <label htmlFor="horizon-hours">Horizon (heures)</label>
        <input
          id="horizon-hours"
          type="number"
          min="1"
          max="168"
          value={horizonHours}
          onChange={(e) => setHorizonHours(e.target.value)}
        />

        <label htmlFor="demand-forecast">Prévision de demande (kW, optionnel)</label>
        <input
          id="demand-forecast"
          type="number"
          min="0"
          step="0.1"
          value={demandForecastKw}
          onChange={(e) => setDemandForecastKw(e.target.value)}
          placeholder="100"
        />

        <button type="submit" disabled={loading}>
          {loading ? 'Simulation en cours…' : 'Lancer la simulation'}
        </button>
      </form>

      {error && <p className="form-error">{error}</p>}

      {result && (
        <>
          <p>
            Coût total estimé : <strong>€{result.totalCost.toFixed(2)}</strong> · Part renouvelable
            moyenne : <strong>{Math.round(result.totalRenewableShare * 100)}%</strong>
          </p>

          <ResponsiveContainer width="100%" height={320}>
            <LineChart data={result.timeline}>
              <CartesianGrid strokeDasharray="3 3" />
              <XAxis dataKey="hour" label={{ value: 'Heure', position: 'insideBottom', offset: -5 }} />
              <YAxis label={{ value: 'kW', angle: -90, position: 'insideLeft' }} />
              <Tooltip />
              <Legend />
              <Line type="monotone" dataKey="solarOutputKw" name="Solaire" stroke="#eda100" dot={false} />
              <Line type="monotone" dataKey="windOutputKw" name="Éolien" stroke="#1baf7a" dot={false} />
              <Line type="monotone" dataKey="demandKw" name="Demande" stroke="#e34948" dot={false} />
              <Line type="monotone" dataKey="gridImportKw" name="Import réseau" stroke="#4a3aa7" dot={false} />
            </LineChart>
          </ResponsiveContainer>
        </>
      )}
    </div>
  );
}