# CadastroEE — Sistema Cadastral Jakarta EE

Projeto corporativo para a disciplina de **Desenvolvimento Web Java EE**, cobrindo
os três procedimentos do roteiro de prática.

---

## Stack utilizada

| Camada       | Tecnologia                          |
|--------------|-------------------------------------|
| Persistência | JPA 2.2 + SQL Server                |
| Negócio      | EJB 3.2 Stateless Session Beans     |
| Web          | Servlet 4.0 + JSP 2.3               |
| Design       | Bootstrap 5.3                       |
| Servidor     | GlassFish 6.2.1                     |
| Plataforma   | Jakarta EE 8                        |

---

## Estrutura do projeto

```
CadastroEE/                        ← EAR (projeto principal)
├── banco/
│   ├── loja.sql                   ← Script SQL Server (tabela + dados)
│   └── asadmin-config.bat         ← Comandos asadmin para o pool JDBC
│
├── CadastroEE-ejb/                ← Camada EJB + JPA
│   └── src/main/java/cadastroee/
│       ├── model/
│       │   └── Produto.java       ← Entidade JPA
│       └── controller/
│           ├── AbstractFacade.java
│           ├── ProdutoFacade.java      ← @Stateless EJB
│           └── ProdutoFacadeLocal.java ← Interface @Local
│   └── src/main/resources/META-INF/
│       └── persistence.xml        ← Unidade de persistência JTA (jdbc/loja)
│
└── CadastroEE-war/                ← Camada Web
    └── src/main/java/cadastroee/servlets/
    │   ├── ServletProduto.java    ← Servlet de teste (Procedimento 1)
    │   └── ServletProdutoFC.java  ← Front Controller (Procedimento 2)
    └── src/main/webapp/
        ├── WEB-INF/web.xml        ← Deployment descriptor
        ├── ProdutoLista.jsp       ← Listagem com Bootstrap
        └── ProdutoDados.jsp       ← Formulário inclusão/alteração com Bootstrap
```

---

## Passo a passo de configuração

### 1. Banco de dados (SQL Server)

```sql
-- Execute loja.sql no SQL Server Management Studio
```

### 2. Driver JDBC no GlassFish

Copie `mssql-jdbc-12.2.0.jre8.jar` para:
```
<glassfish>/lib/
```

### 3. Pool JDBC via asadmin

Execute os comandos em `banco/asadmin-config.bat` no prompt do asadmin.

### 4. NetBeans — importar o projeto

- **File → Open Project** nos três subprojetos (EAR, EJB, WAR)
- Adicionar biblioteca **Jakarta EE 8 API** ao módulo `CadastroEE-ejb`
- Adicionar biblioteca **Jakarta EE Web 8 API** ao módulo `CadastroEE-war`

### 5. Deploy e teste

```
Run no projeto CadastroEE (EAR)
```

| URL | Descrição |
|-----|-----------|
| `http://localhost:8080/CadastroEE-war/ServletProduto`            | Servlet de teste simples |
| `http://localhost:8080/CadastroEE-war/ServletProdutoFC?acao=listar` | CRUD completo com Bootstrap |

---

## URLs do CRUD

| Ação         | URL                                                              |
|--------------|------------------------------------------------------------------|
| Listar       | `?acao=listar`                                                   |
| Form inclusão| `?acao=formIncluir`                                              |
| Form alteração| `?acao=formAlterar&id={id}`                                     |
| Incluir      | POST `?acao=incluir` (via formulário)                            |
| Alterar      | POST `?acao=alterar&id={id}` (via formulário)                   |
| Excluir      | `?acao=excluir&id={id}`                                          |
