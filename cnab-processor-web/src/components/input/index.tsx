import { FC, ChangeEvent, Dispatch, SetStateAction } from 'react';
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
    <div className={styles.wrapper}>
      <label htmlFor={name}>
        {file ? file.name : 'Choose File'}
      </label>
      <input 
        id={name} 
        type='file' 
        accept='.txt' 
        onChange={handleFileChange} 
      />
    </div>
  );
}
