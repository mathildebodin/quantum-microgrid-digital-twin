import { useEffect, useState } from 'react';
import { microgridApi } from '../api/microgridApi';
import MicrogridCard from './MicrogridCard';
import MicrogridForm from './MicrogridForm';

export default function MicrogridList() {
  const [microgrids, setMicrogrids] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    loadMicrogrids();
  }, []);

  async function loadMicrogrids() {
    setLoading(true);
    setError(null);
    try {
      const data = await microgridApi.getAll();
      setMicrogrids(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }

  function handleCreated(newMicrogrid) {
    setMicrogrids((prev) => [...prev, newMicrogrid]);
  }

  async function handleDelete(id) {
    const previous = microgrids;
    // Optimistic update: remove immediately, roll back on failure
    setMicrogrids((prev) => prev.filter((m) => m.id !== id));
    try {
      await microgridApi.delete(id);
    } catch (err) {
      setError(err.message);
      setMicrogrids(previous);
    }
  }

  return (
    <div className="microgrid-list-page">
      <MicrogridForm onCreated={handleCreated} />

      <h2>Microgrids</h2>
      {loading && <p>Chargement…</p>}
      {error && <p className="form-error">{error}</p>}
      {!loading && !error && microgrids.length === 0 && (
        <p>Aucun microgrid pour le moment. Crée le premier ci-dessus.</p>
      )}

      <div className="microgrid-grid">
        {microgrids.map((microgrid) => (
          <MicrogridCard key={microgrid.id} microgrid={microgrid} onDelete={handleDelete} />
        ))}
      </div>
    </div>
  );
}