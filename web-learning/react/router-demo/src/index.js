import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import App from './App';
import Expenses from './routes/expenses'
import Invoices from './routes/invoices'
import Invoice from './routes/invoice';
import reportWebVitals from './reportWebVitals';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Routes>
      <Route path='/' element={<App />}>
        <Route path='expenses' element={<Expenses />}></Route>
        <Route path='invoices' element={<Invoices />}>
          <Route
            index
            element={<main style={{ padding: '1rem' }}>
              <p>Select an invoice</p>
            </main>}
          >
          </Route>
          <Route path=':invoiceId' element={<Invoice />}></Route>
        </Route>

        {/* The "*" has special meaning here. 
        It will match only when no other routes do. */}
        <Route
          path='*'
          element={<main style={{ padding: '1rem' }}>
            <p>there's nothing here</p>
          </main>}
        />
      </Route>
    </Routes>
  </BrowserRouter>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
