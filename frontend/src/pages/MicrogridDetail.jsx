import { useEffect, useState } from 'react';
import { useParams, Link } from 'react-router-dom';
import { microgridApi } from '../api/microgridApi';
import SolverComparison from '../components/SolverComparison';
import SimulationChart from '../components/SimulationChart';

export default function MicrogridDetail() {
  const { id } = useParams();
  const [microgrid, setMicrogrid] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    let cancelled = false;

    async function loadMicrogrid() {
      setLoading(true);
      setError(null);
      try {
        const data = await microgridApi.getById(id);
        if (!cancelled) setMicrogrid(data);
      } catch (err) {
        if (!cancelled) setError(err.message);
      } finally {
        if (!cancelled) setLoading(false);
      }
    }

    loadMicrogrid();
    return () => {
      cancelled = true;
    };
  }, [id]);

  if (loading) return <p>Chargement…</p>;
  if (error) return <p className="form-error">{error}</p>;
  if (!microgrid) return null;

  return (
    <main className="microgrid-detail-page">
      <Link to="/">← Retour au tableau de bord</Link>
      <h1>{microgrid.name}</h1>
      <p>Capacité maximale : {microgrid.maxCapacityKw} kW</p>
      <p>Charge actuelle : {microgrid.currentLoadKw ?? 0} kW</p>

      <SolverComparison microgridId={microgrid.id} />
      <SimulationChart microgridId={microgrid.id} />
    </main>
  );
}