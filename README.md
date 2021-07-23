# Gestao de Tarefas

PedidoWs é um Sistema Web Java - Ativividade de Estudo

O Banco de dados será Postgresql 9.6 

Tecnologias utilizadas:

Java 8

Netbeans 8.2

Tomcat 8.5

Bibliotecas Java:
Driver JDBC do Postgresql

BootsFaces 1.4.2

PrimeFaces 7.0

JPA 2.1

Hibernate 4.3.x

Html

Css

JavaScrip

JSF 2.2

Como usar este projeto em ambiente local.

1 - Configurar o arquivo persistence.xml com os dados do banco de dados e schema criados no host desejado.

2 - Criar as seguintes tabelas com o sql abaixo:

<sql>
CREATE SEQUENCE gestao_tarefas."responsavel_idResponsavel_seq"
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
</sql>




<code>
  CREATE SEQUENCE gestao_tarefas.tarefa_idtarefa_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;
</code>	





	
<code>    
CREATE TABLE IF NOT EXISTS gestao_tarefas.responsavel
(
    idresponsavel integer NOT NULL DEFAULT nextval('gestao_tarefas."responsavel_idResponsavel_seq"'::regclass),
    nome character varying(100) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT responsavel_pkey PRIMARY KEY (idresponsavel)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
</code>
	
  
  
  
	
<code>

CREATE TABLE IF NOT EXISTS gestao_tarefas.tarefa
(
    idtarefa integer NOT NULL DEFAULT nextval('gestao_tarefas.tarefa_idtarefa_seq'::regclass),
    descricao text COLLATE pg_catalog."default" NOT NULL,
    prioridade "char" NOT NULL,
    idresponsavel integer,
    titulo character varying(100) COLLATE pg_catalog."default" NOT NULL,
    deadline timestamp with time zone,
    concluido boolean DEFAULT false,
    CONSTRAINT tarefa_pkey PRIMARY KEY (idtarefa),
    CONSTRAINT fk_tarefa_idresponsavel FOREIGN KEY (idresponsavel)
        REFERENCES gestao_tarefas.responsavel (idresponsavel) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
</code>    
    
