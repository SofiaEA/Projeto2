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
VALUES ('Sofia Amorim', 'sofia@ipvc.pt', '$2a$10$W28BVhcE6tZlWco5/Wtq6um/X4kkya.CVrx/FG.bL25rzIGMsPR/q', '1234567890', 'MANAGER', true, true, true, true);

INSERT INTO users(name, username, password, phone_number, user_role, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('Jackson Junior', 'jacksonjunior@ipvc.pt', '$2a$10$W28BVhcE6tZlWco5/Wtq6um/X4kkya.CVrx/FG.bL25rzIGMsPR/q', '1234567891', 'ORGANIZER', true, true, true, true);

INSERT INTO users(name, username, password, phone_number, user_role, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('Participant User', 'participant@ipvc.pt', '$2a$10$W28BVhcE6tZlWco5/Wtq6um/X4kkya.CVrx/FG.bL25rzIGMsPR/q', '1234567892', 'PARTICIPANT', true, true, true, true);


