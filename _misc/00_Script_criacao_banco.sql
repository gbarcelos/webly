CREATE DATABASE webly
  WITH ENCODING='UTF8'
       OWNER=postgres
       CONNECTION LIMIT=-1;

CREATE SCHEMA dbwebly
       AUTHORIZATION postgres;
      
create sequence dbwebly.SQ_USUARIO_COSEQPESSOA
increment 1
minvalue 1
maxvalue 999999999
start 1;

create sequence dbwebly.SQ_TIPOUSUARIO_COSEQTIPOUSUARIO
increment 1
minvalue 1
maxvalue 999999999
start 1;

create sequence dbwebly.SQ_USUARIO_COSEQUSUARIO
increment 1
minvalue 1
maxvalue 999999999
start 1;

create sequence dbwebly.SQ_COSEQSITUACAOUSUARIO
increment 1
minvalue 1
maxvalue 999999999
start 1;

create sequence dbwebly.SQ_POST
increment 1
minvalue 1
maxvalue 999999999
start 1;

create table dbwebly.TB_PESSOA (
   CO_SEQ_PESSOA        NUMERIC(9)           not null,
   NO_PESSOA            VARCHAR(100)         not null,
   EMAIL                VARCHAR(60)          not null,
   ST_REGISTRO_ATIVO    CHAR(1)              not null,
   constraint PK_TB_PESSOA primary key (CO_SEQ_PESSOA)
);
create table dbwebly.TB_TIPO_USUARIO (
   CO_SEQ_TIPO_USUARIO  NUMERIC(9)           not null,
   DS_TIPO_USUARIO      VARCHAR(20)          not null,
   ROLE_TIPO_USUARIO    VARCHAR(15)          not null,
   ST_REGISTRO_ATIVO    CHAR(1)              not null,
   constraint PK_TB_TIPO_USUARIO primary key (CO_SEQ_TIPO_USUARIO)
);
create table dbwebly.TB_SITUACAO_USUARIO (
   CO_SEQ_SITUACAO_USUARIO  NUMERIC(9)       not null,
   DS_SITUACAO_USUARIO      VARCHAR(50)      not null,
   ST_REGISTRO_ATIVO    CHAR(1)              not null,
   constraint PK_TB_SITUACAO_USUARIO primary key (CO_SEQ_SITUACAO_USUARIO)
);
create table dbwebly.TB_USUARIO (
   CO_SEQ_USUARIO       NUMERIC(9)           not null,
   CO_TIPO_USUARIO      NUMERIC(9)           null,
   CO_PESSOA            NUMERIC(9)           null,
   CO_SITUACAO_USUARIO  NUMERIC(9)           null,
   NOME                 VARCHAR(30)          not null,
   NOME_PUBLICO         VARCHAR(30)          not null,
   PASS                 VARCHAR(50)          not null,
   CODIGO_VERIFICACAO   VARCHAR(10)          not null,
   DT_INCLUSAO TIMESTAMP WITH TIME ZONE NOT NULL,
   DT_ALTERACAO TIMESTAMP WITH TIME ZONE,
   DT_CONFIRMACAO TIMESTAMP WITH TIME ZONE,
   ST_REGISTRO_ATIVO    VARCHAR(1)           not null,
   constraint PK_TB_USUARIO primary key (CO_SEQ_USUARIO)
);

create table dbwebly.TB_POST (
   CO_SEQ_POST         NUMERIC(9)            not null,
   CO_USUARIO          NUMERIC(9)            not null,
   DS_TITULO           VARCHAR(45)          not null,
   DS_SUBTITULO        VARCHAR(100)         null,
   PARTE_URL           VARCHAR(45)          not null,
   DS_POST             TEXT                  not null,
   DS_RESUMO_POST      TEXT                  not null,
   DT_CRIACAO           TIMESTAMP            not null,
   DT_PUBLICACAO        TIMESTAMP            null,
   ST_REGISTRO_ATIVO   CHAR(1)               not null,
   constraint PK_TB_POST primary key (CO_SEQ_POST)
);

alter table dbwebly.TB_USUARIO
   add constraint FK_TB_USUAR_FK_USUARI_TB_PESSO foreign key (CO_PESSOA)
      references dbwebly.TB_PESSOA (CO_SEQ_PESSOA)
      on delete restrict on update restrict;
     
alter table dbwebly.TB_USUARIO
   add constraint FK_TB_USUAR_FK_USUARI_TB_TIPO_ foreign key (CO_TIPO_USUARIO)
      references dbwebly.TB_TIPO_USUARIO (CO_SEQ_TIPO_USUARIO)
      on delete restrict on update restrict;

alter table dbwebly.TB_USUARIO
   add constraint FK_SITUACAO_USUARIO foreign key (CO_SITUACAO_USUARIO)
      references dbwebly.TB_SITUACAO_USUARIO (CO_SEQ_SITUACAO_USUARIO)
      on delete restrict on update restrict;

alter table dbwebly.TB_POST
   add constraint FK_TB_USUARIO foreign key (CO_USUARIO)
      references dbwebly.TB_USUARIO (CO_SEQ_USUARIO)
      on delete restrict on update restrict;