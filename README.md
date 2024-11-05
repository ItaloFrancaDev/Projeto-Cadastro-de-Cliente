# Gerenciamento de Clientes

Este sistema é uma aplicação web para o gerenciamento de clientes. Ele permite que os usuários cadastrem, busquem, editem e deletem informações de clientes de maneira simples e eficiente.

## Funcionalidades

- **Cadastro de Clientes**: Permite que os usuários cadastrem novos clientes, inserindo informações como nome, email, CEP, endereço, bairro, cidade e estado.
- **Busca de Clientes**: Os usuários podem buscar clientes por nome ou ID, facilitando a localização de registros específicos.
- **Edição de Clientes**: Os usuários podem editar as informações de clientes já cadastrados.
- **Deleção de Clientes**: Os usuários podem remover clientes do sistema, com uma confirmação para evitar deleções acidentais.
- **Integração com API de CEP**: Ao inserir o CEP, o sistema preenche automaticamente os campos de endereço, bairro, cidade e estado utilizando a API do ViaCEP.

## Tecnologias Utilizadas

- **Frontend**:
  - HTML
  - CSS (Bootstrap)
  - JavaScript
- **Backend**:
  - Java (Spring Boot)
  - Thymeleaf (para renderização de páginas)
- **Banco de Dados**:
  - H2 (ou outro banco de dados relacional)
  
## Pré-requisitos

Antes de executar o projeto, certifique-se de que você possui as seguintes ferramentas instaladas:

- [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (versão 11 ou superior)
- [Maven](https://maven.apache.org/download.cgi) (para gerenciamento de dependências)
- [Git](https://git-scm.com/downloads) (para controle de versão)

## Como Rodar o Sistema

1. **Clone o Repositório**:

   ```bash
   git clone (https://github.com/ItaloFrancaDev/Projeto-Cadastro-de-Cliente.git)
   cd nome-do-repositorio
