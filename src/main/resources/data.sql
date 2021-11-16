alter table projeto 
       add constraint teste 
       foreign key (categoria_id) 
       references categoria ON DELETE SET NULL;

INSERT INTO membro(nome,email,foto_src,status, cargo, curso, lattes) 
VALUES ('Lucas Mateus', 'lucas_jdev1@gmail.com', 'lalala', 'ATIVO', 'VOLUNTARIO', 'informática', '231231');

INSERT INTO membro(nome,email,foto_src,status, cargo, curso, lattes) 
VALUES ('Carol Jesuíta', 'kkkkkkk@gmail.com', 'lalala', 'ATIVO', 'BOLSISTA', 'informática', '5674531');

INSERT INTO membro(nome,email,foto_src,status, cargo, curso, lattes) 
VALUES ('Junior Akarleson', 'lucas_jdev2@gmail.com', 'lalala', 'INATIVO', 'VOLUNTARIO', 'eletrotécnica', '3312312');

INSERT INTO categoria(nome) VALUES ('Indefinido');
INSERT INTO categoria(nome) VALUES ('Gamificação');
INSERT INTO categoria(nome) VALUES ('Astronomia');
INSERT INTO categoria(nome) VALUES ('Social');

INSERT INTO projeto(nome, descricao, foto_src, jogo_src, status, categoria_id)
VALUES ('Duelo das ciências', 'projeto feito para o ensino de ciências na região do Mato-Grande',
'dasdasdsad', 'sdasdasd', 'ANDAMENTO', 1);
INSERT INTO projeto(nome, descricao, foto_src, jogo_src, status, categoria_id)
VALUES ('Banco de Jaule', 'projeto feito para o ensino de física na região do Mato-Grande',
'dasdasdsad', 'sdasdasd', 'CONCLUIDO', 1);
INSERT INTO projeto(nome, descricao, foto_src, jogo_src, status, categoria_id)
VALUES ('Mulheres na Ciência', 'projeto feito para o incentivo de mulheres cientistas',
'dasdasdsad', 'sdasdasd', 'ANDAMENTO', 3);

INSERT INTO membro_projeto VALUES (1,1);
INSERT INTO membro_projeto VALUES (2,1);
INSERT INTO membro_projeto VALUES (3,2);

