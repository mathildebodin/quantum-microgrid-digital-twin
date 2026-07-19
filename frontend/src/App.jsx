import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import MicrogridDetail from './pages/MicrogridDetail';
import './App.css';

export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Dashboard />} />
        <Route path="/microgrids/:id" element={<MicrogridDetail />} />
      </Routes>
    </BrowserRouter>
  );
}