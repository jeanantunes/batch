create table cotitulares
(
    idsaldoourendimento bigint not null,
    idcotitular         bigint not null,
    constraint FK6o7htevi9a7xe75hxs2ssupar
        foreign key (idcotitular) references cliente (id),
    constraint FKngmldb6oyuiui31iwh58pns51
        foreign key (idsaldoourendimento) references saldo_ou_rendimento (id)
);