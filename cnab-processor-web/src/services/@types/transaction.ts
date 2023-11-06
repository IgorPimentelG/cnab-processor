export type TransactionReport = {
  storeName: string;
  amount: number;
  transactions: Transaction[];
}

export type Transaction = {
  id: string;
  type: number;
  registeredAt: string;
  amount: number;
  cpf: string;
  cardNumber: string;
  storeHolder: string;
  storeName: string;
}