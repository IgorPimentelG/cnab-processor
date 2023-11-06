import axios from 'axios';

export const processorAPI = () => {
  const api = axios.create({
    baseURL: import.meta.env.VITE_PROCESSOR_API_URL,
  });

  function loadTransactions() {
    return api.get('/transactions');
  }

  function uploadCNAB(file: File) {
    const formData = new FormData();
    formData.append('file', file);
    
    return api.post('/cnab/upload', formData, { 
      headers: {
      'Content-Type': 'multipart/form-data',
    }});
  }

  return { 
    loadTransactions,
    uploadCNAB,
  };
}