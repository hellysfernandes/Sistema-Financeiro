CREATE TABLE transacoes (
id INTEGER PRIMARY KEY,
-- id_carteira INTEGER NOT NULL,
descricao TEXT NOT NULL,
valor REAL NOT NULL,
data TEXT NOT NULL,
tipo TEXT NOT NULL
);

-- Provavel tabela de carteira ou conta de pagamento
CREATE TABLE carteira ou conta (
id INTEGER PRIMARY KEY,
nome TEXT NOT NULL.
tipo TEXT NOT NULL,
valor DOUBLE -- ver questao de permanecia de daods ?
);

-- talvez tabela que guarda a informação da semana ou mes