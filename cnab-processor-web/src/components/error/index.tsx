import { FC } from 'react';
import styles from './styles.module.css';

type Props = {
  message: string;
}

export const Error: FC<Props> = ({ message }) => {
  return (
    <div className={styles.wrapper}>
      <span>{message}</span>
    </div>
  );
}
