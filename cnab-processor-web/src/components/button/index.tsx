import { FC, ReactNode } from 'react';
import styles from './styles.module.css';

type Props = {
  label: string;
  children?: ReactNode;
}

export const Button: FC<Props> = ({ label, children }) => {
  return (
    <button className={styles.wrapper}>
      {children}
      <span>{label}</span>
    </button>
  );
}
