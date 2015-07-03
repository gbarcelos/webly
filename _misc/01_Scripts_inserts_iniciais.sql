
INSERT INTO dbwebly.tb_tipo_usuario(co_seq_tipo_usuario, ds_tipo_usuario, role_tipo_usuario, st_registro_ativo) VALUES (1, 'Super Administrativo', 'ROLE_SP_ADMIN', 'S');
INSERT INTO dbwebly.tb_tipo_usuario(co_seq_tipo_usuario, ds_tipo_usuario, role_tipo_usuario, st_registro_ativo) VALUES (2, 'Administrativo', 'ROLE_ADMIN', 'S');
INSERT INTO dbwebly.tb_tipo_usuario(co_seq_tipo_usuario, ds_tipo_usuario, role_tipo_usuario, st_registro_ativo) VALUES (3, 'Visitante', 'ROLE_VISITANTE', 'S');
INSERT INTO dbwebly.tb_tipo_usuario(co_seq_tipo_usuario, ds_tipo_usuario, role_tipo_usuario, st_registro_ativo) VALUES (4, 'Colaborador', 'ROLE_COLAB', 'S');
SELECT setval('dbwebly.sq_tipousuario_coseqtipousuario', 4, true);


INSERT INTO dbwebly.tb_situacao_usuario(co_seq_situacao_usuario, ds_situacao_usuario, st_registro_ativo) VALUES (1, 'Confirmado', 'S');
INSERT INTO dbwebly.tb_situacao_usuario(co_seq_situacao_usuario, ds_situacao_usuario, st_registro_ativo) VALUES (2, 'Aguardando confirmação após inclusão', 'S');
INSERT INTO dbwebly.tb_situacao_usuario(co_seq_situacao_usuario, ds_situacao_usuario, st_registro_ativo) VALUES (3, 'Aguardando confirmação após alteração', 'S');
SELECT setval('dbwebly.sq_coseqsituacaousuario', 3, true);

INSERT INTO dbwebly.tb_pessoa VALUES (1, 'Usuario ADM', 'adm@adm.com', 'S');
SELECT setval('dbwebly.sq_usuario_coseqpessoa', 1, true);

INSERT INTO dbwebly.tb_usuario(co_seq_usuario, co_tipo_usuario, co_situacao_usuario, co_pessoa, nome, nome_publico, pass, st_registro_ativo, codigo_verificacao, dt_inclusao)
VALUES (1, 1, 1, 1, 'adm', 'Adm', '0141efc1dbe9cd04ecc4de47b88c8bca', 'S', '13472902', current_timestamp);
SELECT setval('dbwebly.sq_usuario_cosequsuario', 1, true);