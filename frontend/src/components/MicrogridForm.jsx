import { useState } from 'react';
import { microgridApi } from '../api/microgridApi';

// Controlled form to create a new microgrid.
// Calls onCreated(newMicrogrid) after a successful save so the parent
// (MicrogridList) can refresh its list without a full page reload.
export default function MicrogridForm({ onCreated }) {
  const [name, setName] = useState('');
  const [maxCapacityKw, setMaxCapacityKw] = useState('');
  const [error, setError] = useState(null);
  const [submitting, setSubmitting] = useState(false);

  async function handleSubmit(event) {
    event.preventDefault();
    setError(null);

    if (!name.trim() || !maxCapacityKw) {
      setError('Le nom et la capacité maximale sont requis.');
      return;
    }

    setSubmitting(true);
    try {
      const created = await microgridApi.create({
        name: name.trim(),
        maxCapacityKw: parseFloat(maxCapacityKw)
      });
      setName('');
      setMaxCapacityKw('');
      onCreated?.(created);
    } catch (err) {
      setError(err.message);
    } finally {
      setSubmitting(false);
    }
  }

  return (
    <form onSubmit={handleSubmit} className="microgrid-form">
      <h2>Nouveau microgrid</h2>

      <label htmlFor="microgrid-name">Nom</label>
      <input
        id="microgrid-name"
        type="text"
        value={name}
        onChange={(e) => setName(e.target.value)}
        placeholder="Site-A"
      />

      <label htmlFor="microgrid-capacity">Capacité maximale (kW)</label>
      <input
        id="microgrid-capacity"
        type="number"
        min="0"
        step="0.1"
        value={maxCapacityKw}
        onChange={(e) => setMaxCapacityKw(e.target.value)}
        placeholder="500"
      />

      {error && <p className="form-error">{error}</p>}

      <button type="submit" disabled={submitting}>
        {submitting ? 'Création…' : 'Créer le microgrid'}
      </button>
    </form>
  );
}