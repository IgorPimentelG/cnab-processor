import { FC } from 'react';
import { TransactionReport } from '../../services/@types/transaction';
import styles from './styles.module.css';

type Props = {
  report: TransactionReport;
}

export const Table: FC<Props> = ({ report }) => {

  function formatCurrency(value: number) {
    return value.toLocaleString('pt-br', { style: 'currency', currency: 'BRL' });
  }

  return (
    <div className={styles.wrapper}>
      <div>
        <span>{report.storeName}</span>
        <span>Total: R$ {formatCurrency(report.amount)}</span>
      </div>

      <div>
        <table className={styles.table}>
          <thead>
            <tr>
              <th>Card Number</th>
              <th>CPF</th>
              <th>Date</th>
              <th>Store Holder</th>
              <th>Type</th>
              <th>Amount</th>
            </tr>
          </thead>

          <tbody>
            {report.transactions.map((item) => (
              <tr>
                <td>{item.cardNumber}</td>
                <td>{item.cpf}</td>
                <td>{new Date(item.registeredAt).toLocaleDateString()}</td>
                <td>{item.storeHolder}</td>
                <td>{item.type}</td>
                <td>{formatCurrency(item.amount)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
