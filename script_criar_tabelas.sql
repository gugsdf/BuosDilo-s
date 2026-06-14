-- ====================================
-- SCRIPT DE CRIACAO DAS TABELAS
-- ====================================

-- Tabela: cliente
CREATE TABLE IF NOT EXISTS cliente (
    cliente_id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela: telefone_cliente
CREATE TABLE IF NOT EXISTS telefone_cliente (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    numero_telefone VARCHAR(20) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id) ON DELETE CASCADE
);

-- Tabela: endereco_cliente
CREATE TABLE IF NOT EXISTS endereco_cliente (
    endereco_id SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    cep VARCHAR(10) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    complemento VARCHAR(255),
    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id) ON DELETE CASCADE
);

-- Tabela: produto
CREATE TABLE IF NOT EXISTS produto (
    produto_id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preco NUMERIC(10, 2) NOT NULL,
    sku VARCHAR(50) NOT NULL UNIQUE,
    ativo BOOLEAN DEFAULT TRUE,
    imagem TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela: log_valor_produto
CREATE TABLE IF NOT EXISTS log_valor_produto (
    log_id SERIAL PRIMARY KEY,
    produto_id INTEGER NOT NULL,
    acao VARCHAR(10) NOT NULL,
    valor_antigo NUMERIC(10, 2),
    valor_novo NUMERIC(10, 2) NOT NULL,
    data_evento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (produto_id) REFERENCES produto(produto_id) ON DELETE CASCADE
);

-- Tabela: endereco_estoque
CREATE TABLE IF NOT EXISTS endereco_estoque (
    endereco_estoque_id SERIAL PRIMARY KEY,
    cep VARCHAR(10) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    complemento VARCHAR(255)
);

-- Tabela: estoque
CREATE TABLE IF NOT EXISTS estoque (
    estoque_id SERIAL PRIMARY KEY,
    produto_id INTEGER NOT NULL,
    endereco_estoque_id INTEGER NOT NULL,
    quantidade INTEGER NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produto(produto_id) ON DELETE CASCADE,
    FOREIGN KEY (endereco_estoque_id) REFERENCES endereco_estoque(endereco_estoque_id) ON DELETE CASCADE
);

-- Tabela: pedido
CREATE TABLE IF NOT EXISTS pedido (
    pedido_id SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'PENDENTE',
    valor_total NUMERIC(10, 2) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(cliente_id) ON DELETE CASCADE
);

-- Tabela: item_pedido
CREATE TABLE IF NOT EXISTS item_pedido (
    item_id SERIAL PRIMARY KEY,
    pedido_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    preco_unitario NUMERIC(10, 2) NOT NULL,
    subtotal NUMERIC(10, 2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedido(pedido_id) ON DELETE CASCADE,
    FOREIGN KEY (produto_id) REFERENCES produto(produto_id)
);

-- Tabela: tipo_pagamento
CREATE TABLE IF NOT EXISTS tipo_pagamento (
    tipo_id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

-- Tabela: pagamento
CREATE TABLE IF NOT EXISTS pagamento (
    pagamento_id SERIAL PRIMARY KEY,
    pedido_id INTEGER NOT NULL,
    tipo_id INTEGER NOT NULL,
    valor NUMERIC(10, 2) NOT NULL,
    status VARCHAR(20) DEFAULT 'PENDENTE',
    data_pagamento TIMESTAMP,
    FOREIGN KEY (pedido_id) REFERENCES pedido(pedido_id) ON DELETE CASCADE,
    FOREIGN KEY (tipo_id) REFERENCES tipo_pagamento(tipo_id)
);

-- Tabela: entrega
CREATE TABLE IF NOT EXISTS entrega (
    entrega_id SERIAL PRIMARY KEY,
    pedido_id INTEGER NOT NULL,
    cep VARCHAR(10) NOT NULL,
    numero VARCHAR(10) NOT NULL,
    cidade VARCHAR(50) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    complemento VARCHAR(255),
    FOREIGN KEY (pedido_id) REFERENCES pedido(pedido_id) ON DELETE CASCADE
);

-- ====================================
-- INSERIR TIPOS DE PAGAMENTO PADRAO
-- ====================================
INSERT INTO tipo_pagamento (nome) VALUES ('CREDITO') ON CONFLICT DO NOTHING;
INSERT INTO tipo_pagamento (nome) VALUES ('DEBITO') ON CONFLICT DO NOTHING;
INSERT INTO tipo_pagamento (nome) VALUES ('PIX') ON CONFLICT DO NOTHING;
INSERT INTO tipo_pagamento (nome) VALUES ('BOLETO') ON CONFLICT DO NOTHING;

-- ====================================
-- SCRIPT COMPLETADO COM SUCESSO
-- ====================================

