create table fontepagadora_saldorendimento
(
    id_saldoourendimento bigint not null,
    id_fontepagadora     bigint not null,
    primary key (id_saldoourendimento, id_fontepagadora),
    constraint FK6moea1nv56077f9q4dx7746pj
        foreign key (id_saldoourendimento) references saldo_ou_rendimento (id),
    constraint FK9qo1ai6sl34c1o8s79pswxedv
        foreign key (id_fontepagadora) references fonte_pagadora (id)
);