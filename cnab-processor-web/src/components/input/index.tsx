import { ChangeEvent, Dispatch, FC, SetStateAction } from 'react';
import styles from './styles.module.css';

type Props = {
  name: string;
  file: File | undefined,
  setFile: Dispatch<SetStateAction<File | undefined>>;
}

export const Input: FC<Props> = ({ name, file, setFile }) => {

  function handleFileChange(event: ChangeEvent<HTMLInputElement>) {
    const fileList = event.target.files;

    if (fileList) {
      const file = fileList[0];
      setFile(file);
    }
  }

  return (
    <label htmlFor={name} className={styles.wrapper}>
      {file ? file.name : 'Choose File'}
      <input 
        id={name} 
        type='file' 
        accept='.txt' 
        onChange={handleFileChange} 
      />
    </label>
  );
}
