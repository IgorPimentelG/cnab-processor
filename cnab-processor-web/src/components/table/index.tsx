import { FC } from 'react';
import styles from './styles.module.css';

type Props = {
  storeName: string;
  amount: number;
}

export const Table: FC<Props> = ({ storeName, amount }) => {
  return (
    <div className={styles.wrapper}>
      <div>
        <span>{storeName}</span>
        <span>Total: R$ {amount}</span>
      </div>

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
      </table>
    </div>
  );
}
