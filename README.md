# poo-aplicacao-controle-de-estoque
Sistema simples de controle de estoque Java aplicando os 4 pilares da POO

# 📦 Sistema de Controle de Estoque

Sistema completo de controle de estoque desenvolvido em **Java SE** utilizando **SQLite** como banco de dados, com foco em boas práticas de desenvolvimento, testes unitários, segurança e automatização.

---

## 🛠 Arquitetura do Projeto

O projeto está organizado da seguinte forma:


---

## 🧩 Funcionalidades Implementadas

- Cadastro de **Usuários**, **Categorias**, **Unidades de Medida**, **Fornecedores**, **Produtos** e **Movimentações**
- Controle de **Login Seguro** com sistema de **tentativas limitadas**
- **Permissões de Acesso** baseadas no tipo de usuário (`admin`, `operador`, `visualizador`)
- Sistema de **Auditoria de Tentativas de Login** (console + arquivo TXT + banco de dados)
- **Backup automático** dos logs de login antes de qualquer limpeza
- Tela de **Logout** e **relogin** sem fechar o sistema
- **Reset seguro** de banco de dados com `DatabaseResetter`
- **Seeder automático** de dados essenciais com `MockDataSeeder`
- **Relatório de Testes** (JUnit) com contagem de sucesso, falhas e tempo total
- **Tela Inicial Estilizada** para uma apresentação profissional

---

## 🛡 Segurança

- **Login protegido**: após 3 tentativas incorretas, bloqueio por 60 segundos com contagem regressiva.
- **Auditoria**: cada tentativa de login é registrada no Console, no Arquivo e no Banco (`auditoria_login`).
- **Permissões de acesso**:
  - `admin`: Acesso completo
  - `operador`: Acesso a Produtos e Movimentações
  - `visualizador`: Apenas visualização (listagem)

---

## 📄 Banco de Dados

Utiliza **SQLite**, com as seguintes tabelas:

| Tabela | Descrição |
|:------|:----------|
| `usuario` | Controle de acesso e permissões |
| `categoria` | Categorias dos produtos |
| `unidade_medida` | Unidades como "Kg", "Un", "L" |
| `fornecedor` | Dados dos fornecedores |
| `produto` | Produtos cadastrados |
| `movimentacao` | Entradas e saídas de estoque |
| `auditoria_login` | Registro de tentativas de login |

---

## 🧪 Testes

- **Testes Unitários**:
  - CRUD completo para todas as entidades
  - Validação de inserção, consulta, atualização e exclusão
- **Testes de Integração**:
  - Produto + Movimentação associadas
- **Relatório de Testes**:
  - Mostra quantidade de testes, sucessos, falhas, tempo de execução

Rodar a classe `TestReportGenerator` para gerar o relatório completo.

---

## ⚙️ Inicialização do Sistema

- A classe `SystemInitializer`:
  - Verifica o banco de dados.
  - Se vazio, popula automaticamente com `MockDataSeeder`.
  - Oferece opção de resetar com `DatabaseResetter`.
- Depois disso, o sistema inicia o fluxo normal (Tela Inicial ➔ Login).

---

## 🖥 Requisitos para Rodar

- **Java SE 11+** instalado
- **IntelliJ Community Edition** configurado
- **JUnit 5** adicionado manualmente (arquivo `.jar` dentro da pasta `lib/`)
- Banco de dados **SQLite** (incluso no projeto via JDBC)

---

## 🚀 Como Rodar o Sistema

1. Clone ou copie o projeto para sua máquina.
2. Certifique-se que o JUnit5 está adicionado ao projeto (`lib/`).
3. Rode o `RunSeeder` (opcional) se quiser popular manualmente.
4. Execute o `SistemaDeEstoque.main()`:
   - Inicializa o banco
   - Exibe a tela inicial
   - Redireciona para tela de login
5. Usuário Padrão Inicial:
   - Email: `admin@estoque.com`
   - Senha: `admin123`
   - Tipo: `admin`

---

## 📝 Observações Finais

- Sistema 100% orientado a objetos (POO).
- Separação de responsabilidades utilizando DAO e Model.
- Log das auditorias tanto no console quanto em arquivos de texto.
- Backup automático antes de qualquer limpeza de auditorias.
- Sistema preparado para expansão futura: novas entidades, novos relatórios, novas permissões.
- Código comentado para facilitar o entendimento e a manutenção.

---

## ✨ Projeto criado com objetivo de estudo e práticas profissionais de desenvolvimento Java + SQLite + JUnit.

---






