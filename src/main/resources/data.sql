INSERT INTO FILIAL (id, nome, endereco, capacidade_patio) VALUES (1, 'Filial Centro', 'Rua A, 100', 20);

INSERT INTO PATIO (id, descricao, dimensao, filial_id) VALUES (1, 'Pátio 1', 'Grande', 1);

INSERT INTO MOTO (id, placa, modelo, ano, status, patio_id) VALUES (1, 'ABC1234', 'Honda CG 160', 2023, 'ATIVA', 1);

INSERT INTO SENSOR (id, tipo, status, moto_id) VALUES (1, 'GPS', 'ATIVO', 1);

INSERT INTO OPERADOR (id, nome, login, senha, filial_id) VALUES (1, 'Carlos Silva', 'carlos', '123456', 1);

INSERT INTO MOVIMENTACAO (id, data_hora, posicao_anterior, nova_posicao, moto_id)
VALUES (1, CURRENT_TIMESTAMP, 'Entrada', 'Pátio', 1);
