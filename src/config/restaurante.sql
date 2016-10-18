/* Criando banco de dados restaurante caso não exista */
create database if not exists restaurante;

/* excluindo tabela usuarios, caso exista */
-- drop table if exists restaurante.usuarios; --

/* criando tabela de usuários */
create table if not exists restaurante.usuarios ( 
codigo integer not null primary key auto_increment,
nome varchar(50) not null,
login varchar(20) ,
senha varchar(255),
perfil varchar(30),
ativo bit(1) default 0,
created_at datetime,
updated_at timestamp
);
/* incluindo um usuário padrão */
insert into restaurante.usuarios (nome,login,senha,perfil,ativo,created_at,updated_at)
                        values ( "Administrador do Sistema","admin","123","admin",1,now(),now());

/* tabela clientes */
create table if not exists restaurante.clientes (
   codigo integer not null primary key auto_increment,
   nome varchar(100) not null,
   sexo varchar(20),
   estado_civil varchar(20),
   cpf char(11) not null,
   cep char(8) ,
   endereco varchar(100),
   bairro varchar(100),
   cidade varchar(100),
   uf char(2),
   receber_promocoes bit(1) default 0,
   created_at datetime,
   updated_at timestamp 
);
                
