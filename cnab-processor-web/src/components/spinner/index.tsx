import { FC } from 'react';
import styles from './styles.module.css';

export const Spinner: FC = () => {
  return (
    <div className={styles.wrapper}>
      <span className={styles.loader} />
    </div>
  );
}
