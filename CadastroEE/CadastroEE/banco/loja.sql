-- ============================================================
-- Banco de dados: loja
-- SQL Server — usado nas práticas anteriores ao roteiro JEE
-- ============================================================

-- Criar banco e usuário (executar como sa ou admin)
CREATE DATABASE loja;
GO

USE loja;
GO

CREATE LOGIN loja WITH PASSWORD = 'loja';
GO

CREATE USER loja FOR LOGIN loja;
GO

ALTER ROLE db_owner ADD MEMBER loja;
GO

-- ============================================================
-- Tabela Produto
-- ============================================================
CREATE TABLE Produto (
    id          INT IDENTITY(1,1) PRIMARY KEY,
    nome        NVARCHAR(100)  NOT NULL,
    quantidade  INT            NOT NULL DEFAULT 0,
    precoVenda  FLOAT          NOT NULL DEFAULT 0.0
);
GO

-- Dados de exemplo para teste do Servlet (Passo 7b)
INSERT INTO Produto (nome, quantidade, precoVenda) VALUES
    ('Caderno Universitário',  50,  18.90),
    ('Caneta Esferográfica',  200,   2.50),
    ('Mochila Escolar',        30, 119.99),
    ('Régua 30cm',             80,   4.75),
    ('Calculadora Científica', 15,  89.00);
GO
