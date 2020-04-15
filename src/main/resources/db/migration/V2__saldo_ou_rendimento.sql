create table saldo_ou_rendimento
(
    id                bigint auto_increment
        primary key,
    categoria_rendto  varchar(255)   null,
    dt_apuracao       datetime       null,
    legado_origem     varchar(255)   null,
    nm_conta          varchar(255)   null,
    numero_da_conta   int            null,
    produto           varchar(255)   null,
    vr_rendto         decimal(18, 2) null,
    vr_saldo_anterior bigint         null,
    vr_saldo_atual    bigint         null,
    titular_id        bigint         null,
    constraint FKjuvftngtmi5986tmsgbgej74g
        foreign key (titular_id) references cliente (id)
);