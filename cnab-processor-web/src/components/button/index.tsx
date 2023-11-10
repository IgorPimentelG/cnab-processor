import { FC, ReactNode, DetailedHTMLProps, ButtonHTMLAttributes } from 'react';
import styles from './styles.module.css';

type Props = DetailedHTMLProps<ButtonHTMLAttributes<HTMLButtonElement>, HTMLButtonElement> & {
  label: string;
  children?: ReactNode;
}

export const Button: FC<Props> = ({ label, children, ...rest }) => {
  return (
    <button className={styles.wrapper} {...rest}>
      {children}
      <span>{label}</span>
    </button>
  );
}
