import { useState } from 'react';
import { RiUploadCloudLine } from 'react-icons/ri';
import Logo from '../../assets/logo.png';
import { Button, Error, Input, Table } from '../../components';
import styles from './styles.module.css';

const Home = () => {

  const [error, setError] = useState("");
  const [transactions, setTransactions] = useState([]);

  return (
    <main className={styles.wrapper}>
      <div>
        <img src={Logo} alt='logo' />
      </div>

      <form className={styles.formWrap}>
        <h3>CNAB</h3>

        <div>
          <Input name='cnab-file' />
          <Button label='Upload File'>
            <RiUploadCloudLine />
          </Button>
        </div>

        {error && <Error message={error} />}
      </form>

      <hr/>

      <h3>Transactions</h3>
      <div>
        {transactions.length === 0 ? (
          <Error message={'No transactions have been processed.'} />
        ) : (
          <Table
            storeName='Nome da Loja'
            amount={0.00}
          />
        )}
      </div>
    </main>
  );
}

export default Home;