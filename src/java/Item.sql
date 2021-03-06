/**
 * Author:  Bruna Alves
 * Created: 21/05/2017
 */

CREATE TABLE Item(
    id INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    pedido INTEGER NOT NULL,
    dono VARCHAR(50) NOT NULL,
    valor REAL NOT NULL,
    nome VARCHAR(50) NOT NULL,
    atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);