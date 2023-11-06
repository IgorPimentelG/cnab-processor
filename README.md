# CNAB Processor ️👨‍💻

<p align="center">
  <img src="./docs/logo.png" alt="logo" width="400" />
</p>


![Version](https://img.shields.io/badge/version-1.0.0-blue)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED.svg?style=for-the-badge&logo=Docker&logoColor=white)
![Render](https://img.shields.io/badge/Render-46E3B7.svg?style=for-the-badge&logo=Render&logoColor=white)
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)

É uma aplicação de gerenciamento de transações financeiras através da leitura e processaomento de arquivos CNAB.

**OBS:**
O intuito deste projeto é aplicar os conhecimentos o spring framework e suas tecnologias, visando criar um sistema 
escalável e resiliente que facilite o desenvolvimento, implantação e manutenção.

## Funcionalidades
- Upload de arquivos CNAB
- Processamento de arquivos CNAB
- Relatório de processamento

## Infraestrutura

É uma arquitetura monolítica que se baseia nas ferramentas e bibliotecas do Spring Framework e ReactJS. Além disso, faz uso de 
container Docker para o ambiente de produção na web.

## Principais tecnologias
- [Spring Boot](https://spring.io/projects/spring-boot): O Spring Boot é um framework de desenvolvimento em Java que
	simplifica a criação de aplicativos web e microserviços, fornecendo configuração e estrutura pré-definidas.
- [Spring Batch](https://spring.io/projects/spring-batch): é um framework leve e abrangente projetado para facilitar o desenvolvimento 
  e a execução de processos em lote robustos e escalonáveis ​​no ambiente Java. Oferece recursos para processamento de grande volume de dados, 
  como transações, particionamento e gerenciamento de erros, tornando-o uma escolha popular para a automação de tarefas em lote em aplicativos empresariais.
- [ReactJS](https://react.dev/): é uma biblioteca JavaScript de código aberto que simplifica a criação de interfaces de usuário interativas e dinâmicas. Com sua a
  bordagem baseada em componentes, ReactJS permite o desenvolvimento eficiente de aplicativos da web de página única, oferecendo um estado de gerenciamento 
  simplificado e uma renderização eficiente. 
- [Typescript](https://www.typescriptlang.org/): é uma linguagem de programação de código aberto desenvolvida pela Microsoft. Ela estende a sintaxe do JavaScript, 
  adicionando tipos estáticos opcionais.
- [Maven](https://maven.apache.org/): É uma ferramenta de automação de construção e gerenciamento de dependências
	usada principalmente em projetos Java. Ele simplifica o processo de compilação, empacotamento e distribuição de aplicativos.
- [H2](https://www.h2database.com/html/main.html): O H2 é um sistema de gerenciamento de banco de dados relacional escrito em Java. 
  É conhecido por sua natureza leve, o que o torna uma opção popular para testes e desenvolvimento. 
- [Docker](https://www.docker.com/): É uma plataforma de virtualização de contêineres que permite empacotar
	aplicativos e suas dependências em contêineres isolados.

## Requisitos
- [Arquivo CNAB](./docs/CNAB.txt)

## Produção

A API está hospedada na infraestrutura da plataforma [Render](https://render.com/), e o front-end na [Vercel](https://vercel.com).

👉 [Aqui para ver o projeto em produção](https://cnab-processor.vercel.app/) 👈

## Créditos
Este material foi elaborado a partir dos estudos sobre Spring Batch apresentadas no canal da Giuliana Bezerra.

👉 Disponível na [playlist](https://www.youtube.com/@giulianabezerra).

## Feedback

Se você tiver algum feedback, por favor nos deixe saber por meio de dev.igorpimentel@gmail.com

## Autores

- [@IgorPimentelG](https://www.github.com/IgorPimentelG)
