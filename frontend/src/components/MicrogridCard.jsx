import { Link } from 'react-router-dom';

// Displays a single microgrid summary. Pure presentational component:
// all data comes from props, no API calls here.
export default function MicrogridCard({ microgrid, onDelete }) {
  const loadPercent = microgrid.maxCapacityKw
    ? Math.round((microgrid.currentLoadKw / microgrid.maxCapacityKw) * 100)
    : 0;

  return (
    <div className="microgrid-card">
      <div className="microgrid-card-header">
        <h3>{microgrid.name}</h3>
        <button
          className="delete-button"
          onClick={() => onDelete(microgrid.id)}
          aria-label={`Supprimer ${microgrid.name}`}
        >
          Supprimer
        </button>
      </div>

      <p>Capacité max : {microgrid.maxCapacityKw} kW</p>
      <p>Charge actuelle : {microgrid.currentLoadKw ?? 0} kW ({loadPercent}%)</p>
      <p>
        {microgrid.solarPanels?.length ?? 0} panneaux solaires ·{' '}
        {microgrid.windTurbines?.length ?? 0} éoliennes ·{' '}
        {microgrid.batteries?.length ?? 0} batteries
      </p>

      <Link to={`/microgrids/${microgrid.id}`}>Voir le détail →</Link>
    </div>
  );
}