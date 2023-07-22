create table Users(
                      "id_user" serial constraint users_pk primary key,
                      nome          varchar(255) ,
                      username         varchar(100) not null UNIQUE,
                      password      varchar(100) not null CHECK ( length(password) > 6),
                      telemovel   integer,
                      "id_tipo_user" integer constraint TipoUser_idTipoUser_fk references TipoUser,
                      enabled boolean
);

create table Categorias(
                           "id_categoria" serial constraint categorias_pk primary key,
                           nome          varchar(255) not null
);


create table Eventos(
                        "id_evento" serial constraint evento_pk primary key,
                        nome          varchar(255) not null,
                        local         varchar(100) not null,
                        descricao     varchar(100),
                        capacidade_max integer,
                        data          date,
                        "id_categoria" integer constraint categorias_idCategoria_fk references Categorias,
                        "id_user" integer constraint user_idUser_fk references Users
);

create table Bilhetes(
                         "id_bilhete" serial constraint Bilhetes_pk primary key,
                         precoTotal    float,
                         "id_tipo_bilhetes" integer constraint TipoBilhetes_idTipoBilhetes_fk references TipoBilhetes,
                         "id_evento" integer constraint Eventos_idEvento_fk references Eventos,
                         num_bilhetes integer,
                         bilhetes_disp integer,
                         bilhetes_comprados integer
);

create table Compras(
                        "id_compra" serial constraint Compras_pk primary key,
                        "id_user" integer constraint user_idUser_fk references Users,
                        "id_bilhete" integer constraint Bilhetes_idBilhete_fk references Bilhetes,
                        num_bilhetes integer,
                        data_compra timestamp DEFAULT current_timestamp,
                        total_preco float
);


INSERT INTO Categorias(nome) VALUES
                                 ('Eventos de empresas e eventos corporativos'),
                                 ('Eventos com causa e de angariação de fundos'),
                                 ('Eventos desportivos'),
                                 ('Eventos públicos (da administração pública)'),
                                 ('Eventos sociais'),
                                 ('Reuniões ou convenções'),
                                 ('Espetáculos e eventos de lazer');

INSERT INTO Users VALUES (1,'admin','admin','admin',123456789,1,true);
