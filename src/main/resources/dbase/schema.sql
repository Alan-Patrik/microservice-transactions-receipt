create table comprovante (
    id bigint not null auto_increment,
    data_criacao datetime,
    conta_destinatario MEDIUMTEXT not null,
    conta_remetente MEDIUMTEXT not null,
    tipo_transacao varchar(255) not null,
    valor double precision not null,
    primary key (id)
);
