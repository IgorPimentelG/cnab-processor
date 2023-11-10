import { AxiosError } from 'axios';
import { FormEvent, useEffect, useState } from 'react';
import { BiRefresh } from 'react-icons/bi';
import { RiUploadCloudLine } from 'react-icons/ri';
import Logo from '../../assets/logo.png';
import { Button, Error, Input, Spinner, Table } from '../../components';
import { TransactionReport } from '../../services/@types/transaction';
import { processorAPI } from '../../services/processor-api';
import styles from './styles.module.css';

const Home = () => {

  const { loadTransactions, uploadCNAB } = processorAPI();

  const [error, setError] = useState("");
  const [file, setFile] = useState<File>();
  const [isLoading, setIsLoading] = useState(false);
  const [transactions, setTransactions] = useState<TransactionReport[]>([]);

  useEffect(() => {
    handleLoadTransactions();
  }, []);

  async function handleSubmit(event: FormEvent) {
    event.preventDefault();

    if (file) {
      setIsLoading(true);
      try {
        await uploadCNAB(file);
      } catch (error: unknown) {
        const httpError  = error as AxiosError;
        setError(String(httpError.response?.data) || 'Internal Server Error');
      }
      setIsLoading(false);
    } else {
      setError("Select CNAB file.");
    }
  }

  async function handleLoadTransactions() {
    setIsLoading(true);
    const response = await loadTransactions();

    if (response.status === 200) {
      console.log("DEU CERTO!!");
      console.log(response.data);
      setTransactions(response.data);
    }

    setIsLoading(false);
  }

  return (
    <main className={styles.wrapper}>
      <form className={styles.formWrap} onSubmit={handleSubmit}>
        <div>
          <img src={Logo} alt='logo' />
        </div>

        <div>
          <h3>CNAB</h3>

          <div>
            <Input name='cnab-file' file={file} setFile={setFile} />
            <Button label='Upload File' type='submit'>
              <RiUploadCloudLine />
            </Button>
            <Button label='Refresh' type='button' onClick={handleLoadTransactions}>
              <BiRefresh />
            </Button>
          </div>

          {error && <Error message={error} />}
        </div>
      </form>

      <hr/>

      <h3>Transactions</h3>
      {isLoading ? (
        <Spinner />
      ) : (
        <div>
          {transactions.length === 0 ? (
            <div className={styles.errorWrap}>
              <Error message={'No transactions have been processed.'} />
            </div>
          ) : (
          <>
            {transactions.map((item, key) => (
              <Table report={item} key={key} />
            ))}
          </>
          )}
        </div>
      )}
    </main>
  );
}

export default Home;