INSERT INTO categorias(nome) VALUES
                                 ('Eventos de empresas e eventos corporativos'),
                                 ('Eventos com causa e de angariação de fundos'),
                                 ('Eventos desportivos'),
                                 ('Eventos públicos (da administração pública)'),
                                 ('Eventos sociais'),
                                 ('Reuniões ou convenções'),
                                 ('Espetáculos e eventos de lazer');

INSERT INTO tipobilhetes(nome) VALUES
                                   ('VIP'),
                                   ('Normal'),
                                   ('Pessoas com Necessidades Especiais'),
                                   ('Convidados');

INSERT INTO users(name, username, password, phone_number, user_role, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('Sofia Amorim', 'sofiaam@ipvc.pt', '$2a$10$W28BVhcE6tZlWco5/Wtq6um/X4kkya.CVrx/FG.bL25rzIGMsPR/q', '962855707', 'MANAGER', true, true, true, true),
        ('João Maria', 'joaomaria@ipvc.pt', '$2a$10$W28BVhcE6tZlWco5/Wtq6um/X4kkya.CVrx/FG.bL25rzIGMsPR/q', '962117434', 'MANAGER', true, true, true, true);


INSERT INTO users(name, username, password, phone_number, user_role, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('Jack Junior', 'jackjunior@ipvc.pt', '$2a$10$W28BVhcE6tZlWco5/Wtq6um/X4kkya.CVrx/FG.bL25rzIGMsPR/q', '952117432', 'ORGANIZER', true, true, true, true),
        ('Filipa Ferraz', 'ff@gmail.com', '$2a$10$W28BVhcE6tZlWco5/Wtq6um/X4kkya.CVrx/FG.bL25rzIGMsPR/q', '957731001', 'ORGANIZER', true, true, true, true);

INSERT INTO users(name, username, password, phone_number, user_role, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('Marta da Conceição', 'martazao@ipvc.pt', '$2a$10$W28BVhcE6tZlWco5/Wtq6um/X4kkya.CVrx/FG.bL25rzIGMsPR/q', '955287142', 'PARTICIPANT', true, true, true, true),
        ('Amália Cerqueira', 'cerqueira@gmail.com', '$2a$10$W28BVhcE6tZlWco5/Wtq6um/X4kkya.CVrx/FG.bL25rzIGMsPR/q', '986115409', 'PARTICIPANT', true, true, true, true);

INSERT INTO eventos (nome, data, local, descricao, capacidade_max, id_categoria, id)
VALUES ('Corrida de Karts', '2023-07-29', 'Viana do Castelo', 'Corrida de Karts', 55, 3, 3),
       ('Festa do Pijama', '2023-07-25', 'Algarve', 'Pijama Party', 44, 7, 3),
       ('Festa da Sardinha', '2023-08-09', 'Barcelos', 'Sardinha para todos', 74, 2, 3);

