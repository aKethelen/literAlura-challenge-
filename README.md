<h1 align="center">
  LiterAlura - Desafio de Catálogo de Livros
</h1>

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.3-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blue)

<img src="./resources/images/docs/badge-literalura.png" height="200" alt="Badge: LiterAlura">

</div>

## Sobre o Projeto

O **LiterAlura** é uma aplicação de console desenvolvida para o desafio da formação Java da **Alura** em parceria com o **Oracle Next Education (ONE)**. O sistema consome a API [Gutendex](https://gutendex.com/) para buscar informações de livros de domínio público e os armazena em um banco de dados **PostgreSQL**.

## Desenvolvedora
- **Kethelen De Azevedo**
- Estudante de Ciência da Computação

## Licença
Este projeto está sob a **Licença MIT**.

## Funcionalidades

O sistema permite realizar as seguintes operações:
1. **Buscar livros pelo título**: Consulta a API externa e salva no banco de dados local.
2. **Listar livros registrados**: Exibe todos os livros que já foram salvos no seu banco.
3. **Listar autores registrados**: Lista todos os autores salvos.
4. **Listar autores vivos em determinado ano**: Filtra autores com base em datas históricas.
5. **Listar livros por idioma**: Filtro por siglas (ex: PT, EN, FR).

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4.3**
- **Spring Data JPA** (Persistência de dados)
- **PostgreSQL** (Banco de dados relacional)
- **Jackson** (Tratamento de JSON)
- **Dotenv** (Segurança de variáveis de ambiente)

## Como Executar

### 1. Pré-requisitos
- Ter o JDK 17 instalado.
- Ter o PostgreSQL configurado.

### 2. Instalação e Execução
Clonar Repositório
Bash
git clone https://github.com/aKethelen/literAlura-challenge-.git
cd literAlura-challenge-
Compilar e Rodar (Via Terminal)
Bash
./mvnw clean install
./mvnw spring-boot:run

### 3. Instalação e Execução
No terminal, executa os seguintes comandos:

Bash
# Clonar o repositório
git clone [https://github.com/aKethelen/literAlura-challenge-.git](https://github.com/aKethelen/literAlura-challenge-.git)

# Aceder à pasta do projeto
cd literAlura-challenge-

# Compilar e correr a aplicação
./mvnw clean install
./mvnw spring-boot:run

### 4. Uso da Interface CLI
Ao iniciar a aplicação, utiliza os inputs numéricos no terminal para navegar:

Plaintext
1 - Procurar livro pelo título
2 - Listar livros registados
3 - Listar autores registados
4 - Listar autores vivos num determinado ano
5 - Listar livros num determinado idioma
0 - Sair

### 5. Configuração de Segurança
Este projeto utiliza variáveis de ambiente para proteger dados sensíveis. 
- Crie um arquivo chamado `.env` na raiz do projeto.
- Use o arquivo `.env.example` como base e preencha com as suas credenciais locais:

```properties
DB_URL=jdbc:postgresql://localhost:5432/literalura_db
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha


Desenvolvedora
Kethelen De Azevedo

Estudante de Ciência da Computação

Licença
Este projeto está sob a Licença MIT.
