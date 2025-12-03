# Stefanini Challenge

## Sobre o Projeto

Este projeto demonstra a integração de uma aplicação Spring Boot com a API REST do Jira e uma LLM para automatizar a criação de issues no Jira a partir da análise de arquivos. A aplicação planeja expor um endpoint REST para o upload de arquivos. O conteúdo do arquivo é processado por um LLM para extrair informações relevantes, como título, descrição e tipo de issue. Em seguida, uma nova issue é criada no Jira com os dados extraídos e o arquivo original é anexado.

Este projeto serve como um acelerador para equipes que lidam com um grande volume de documentação (como relatórios de bugs, especificações de requisitos ou feedback de clientes) e desejam otimizar o processo de criação e triagem de tarefas no Jira.

## Tecnologias Utilizadas

### Backend
* Java 17+
* Spring Boot 3.x
* Gradle
* Spring AI
* Jira REST API Client

### Frontend
* Angular 19+
* TypeScript
* Node.js
* npm

## Fluxo de Trabalho

1.  **Upload do Arquivo**: O usuário envia um arquivo (ex: PDF, TXT, DOCX) através de um endpoint REST na aplicação Spring Boot.
2.  **Processamento e Extração de Conteúdo**: A aplicação utiliza bibliotecas para extrair o texto bruto do arquivo.
3.  **Análise com LLM**: O texto extraído é enviado para um Modelo de Linguagem Grande. Um prompt é engenheirado para solicitar ao LLM que gere uma sugestão de título, uma descrição sumarizada e que classifique o tipo da tarefa (ex: Bug, Story, Task).
4.  **Criação da Issue no Jira**: A aplicação utiliza a API REST do Jira para criar uma nova issue no projeto especificado, utilizando as informações geradas pelo LLM.
5.  **Anexo do Arquivo**: Após a criação da issue, o arquivo original enviado pelo usuário é anexado à issue recém-criada no Jira para referência e contexto completo.
6.  **Resposta**: A aplicação retorna o ID e a chave da nova issue criada no Jira.

## Funcionalidades

* Endpoint REST para upload de arquivos via requisições multipart.
* Extração de texto de diversos formatos de arquivo.
* Integração flexível com modelos de linguagem grandes (LLMs) para análise de conteúdo.
* Geração automática de títulos e descrições para issues do Jira.
* Classificação automática do tipo de issue.
* Criação de issues em projetos Jira via API REST.
* Adição de anexos às issues criadas no Jira.

## Como Executar o Sistema

### Pré-requisitos

* Java 17 ou superior
* Node.js 18 ou superior
* npm 9 ou superior
* Conta e projeto configurado no Jira
* Credenciais de API do Jira

### Configuração

1. Clone o repositório:
```bash
git clone https://github.com/mxs2/stefanini-challenge.git
cd stefanini-challenge
```

2. Configure as variáveis de ambiente para o backend:

Crie um arquivo `.env` na raiz do diretório `backend/` com o seguinte conteúdo:
```properties
# Configurações do Jira
JIRA_URL=https://seu-dominio.atlassian.net
JIRA_USERNAME=seu-email@example.com
JIRA_API_KEY=seu-token-api
JIRA_PROJECT_KEY=CHAVE-DO-PROJETO

# Configurações da LLM
SPRING_AI_OPENAI_API_KEY=sua-chave-api
```

**Nota:** Certifique-se de adicionar o arquivo `.env` ao `.gitignore` para não expor suas credenciais.

### Executando o Backend

```bash
cd backend
./gradlew bootRun
```

O backend estará disponível em `http://localhost:8080`

### Executando o Frontend

1. Instale o Angular CLI globalmente (caso ainda não tenha):
```bash
npm install -g @angular/cli
```

2. Instale as dependências e inicie o servidor de desenvolvimento:
```bash
cd frontend
npm install
ng serve
```

O frontend estará disponível em `http://localhost:4200`

### Executando Ambos os Serviços

Em terminais separados, execute:

**Terminal 1 - Backend:**
```bash
cd backend
./gradlew bootRun
```

**Terminal 2 - Frontend:**
```bash
cd frontend
ng serve
```

## Contribuidores 

* [Alessandra Barbosa](https://github.com/alebarbosas/)
* [Alvaro Silva](https://github.com/alvaro5801/)
* [Arthur Estevão](https://github.com/ArthurEstevaum/)
* [Mateus Xavier](https://github.com/mxs2/)
* [Raphael Miranda](https://github.com/rapheto/)
* [Samuel Araújo](https://github.com/iwannaendme/)
* [Thays Barbosa](https://github.com/idthy/)
  
## Licença

Distribuído sob a Licença MIT. Veja `LICENSE` para mais informações.
