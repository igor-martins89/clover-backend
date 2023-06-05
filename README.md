# backend-clover

# PARA RODAR NO AMBIENTE DE DEV, UTILIZE O SEGUINTE SCRIPT NO MYSQL

USE clover;

SELECT * FROM tb_colecao;

INSERT INTO tb_colecao (nome) VALUES
('Blusas'),
('Camisetas'),
('Funkos'),
('Acessórios');


SELECT * FROM tb_categoria;

INSERT INTO tb_categoria (nome) VALUES
('Animes'),
('Desenhos'),
('Filmes'),
('Séries'),
('Jogos');


SELECT * FROM tb_subcategoria;


INSERT INTO tb_subcategoria (nome) VALUES

-- ANIMES
('One Piece'),
('Naruto'),
('One Punch Man'),
('Bleach'),
('Attack On Titan'),

-- DESENHOS 
('A Turma da Mônica'),
('Bojack Horseman'),
('Rick And Morty'),
('KND - A Turma do Bairro'),
-- FILMES
('Star Wars'),
('O Senhor dos Aneis'),
('De Volta Para o Futuro'),
('O Poderoso Chefão'),    

-- SÉRIES
('Dark'),
('The Witcher'),
('Brooklin 99'),
('The Office'),
('The Big Bang Theory'),     

-- JOGOS
('Mario Bros'),
('Red Dead Redemption'),
('God Of War');

SELECT * FROM tb_produto;

INSERT INTO tb_produto (nome, descricao, valor) VALUES
('Funko Luffy Gear 4th', 'Veja o Luffy na sua versão Gear 4th', 257),
('Funko Zoro', 'Veja o Zoro', 117),
('Funko Nami', 'Veja o Nami', 127),
('Funko Chopper', 'Veja o Chopper', 287),
('Funko Naruto', 'Veja o Naruto', 147),
('Funko Sanji', 'Veja o Sanji', 157),
('Camiseta Luffy Gear 5th', 'Veja o Luffy na sua versão Gear 5th', 67),
('Camiseta Naruto', 'Veja o Naruto', 47),
('Camiseta Sasuke', 'Veja o Sasuke', 37),
('Camiseta Sakura', 'Veja o Sakura', 67);



SELECT * FROM tb_colecao;

 /*
 COLECAO
 '1', 'Blusas'
'2', 'Camisetas'
'3', 'Funkos'
'4', 'Acessórios' 
 */

SELECT * FROM tb_categoria;

/*
CATEGORIA 

'1', 'Animes'
'2', 'Desenhos'
'3', 'Filmes'
'4', 'Séries'
'5', 'Jogos'
*/

SELECT * FROM tb_colecao_categoria;

INSERT INTO tb_colecao_categoria (colecao_id, categoria_id) VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(2,1),
(2,2),
(2,3),
(2,4),
(2,5),
(3,1),
(3,2),
(3,3),
(3,4),
(3,5),
(4,1),
(4,2),
(4,3),
(4,4),
(4,5);

SELECT * FROM tb_categoria_subcategoria;

INSERT INTO tb_categoria_subcategoria (colecao_id, categoria_id, subcategoria_id) VALUES

(1,1,1),
(2,1,1),
(3,1,1),
(4,1,1),

(1,1,2),
(2,1,2),
(3,1,2),
(4,1,2),

(1,1,3),
(2,1,3),
(3,1,3),
(4,1,3),

(1,1,4),
(2,1,4),
(3,1,4),
(4,1,4),

(1,1,5),
(2,1,5),
(3,1,5),
(4,1,5),

(1, 2, 6),
(2, 2, 6),
(3, 2, 6),
(4, 2, 6),

(1, 2, 7),
(2, 2, 7),
(3, 2, 7),
(4, 2, 7),

(1, 2, 8),
(2, 2, 8),
(3, 2, 8),
(4, 2, 8),

(1, 2, 9),
(2, 2, 9),
(3, 2, 9),
(4, 2, 9),

(1, 3, 10),
(2, 3, 10),
(3, 3, 10),
(4, 3, 10),

(1, 3, 11),
(2, 3, 11),
(3, 3, 11),
(4, 3, 11),

(1, 3, 12),
(2, 3, 12),
(3, 3, 12),
(4, 3, 12),

(1, 3, 13),
(2, 3, 13),
(3, 3, 13),
(4, 3, 13),

(1, 4, 14),
(2, 4, 14),
(3, 4, 14),
(4, 4, 14),

(1, 4, 15),
(2, 4, 15),
(3, 4, 15),
(4, 4, 15),

(1, 4, 16),
(2, 4, 16),
(3, 4, 16),
(4, 4, 16),

(1, 4, 17),
(2, 4, 17),
(3, 4, 17),
(4, 4, 17),

(1, 4, 18),
(2, 4, 18),
(3, 4, 18),
(4, 4, 18),

(1, 5, 19),
(2, 5, 19),
(3, 5, 19),
(4, 5, 19),

(1, 5, 20),
(2, 5, 20),
(3, 5, 20),
(4, 5, 20),

(1, 5, 21),
(2, 5, 21),
(3, 5, 21),
(4, 5, 21);


/*
COLECAO:

Camiseta = 2
Funko = 3

CATEGORIA:

Anime = 1

SUBCATEGORIA: 

One Piece = 1
Naruto = 2
*/

SELECT * FROM tb_produto_subcategoria;

INSERT INTO tb_produto_subcategoria (colecao_id, categoria_id, subcategoria_id, produto_id) VALUES
(2, 1, 1, 7),
(2, 1, 2, 8),
(2, 1, 2, 9),
(2, 1, 2, 10),

(3, 1, 1, 1),
(3, 1, 1, 2),
(3, 1, 1, 3),
(3, 1, 1, 4),
(3, 1, 1, 6),
(3, 1, 2, 5);

select * from tb_produto;

-- 1 ao 6 : FUNKO
-- 7 ao 10: Camiseta

INSERT INTO tb_tamanho VALUES
(1, 'DEFAULT'),
(2, 'DEFAULT'),
(3, 'DEFAULT'),
(4, 'DEFAULT'),
(5, 'DEFAULT'),
(6, 'DEFAULT'),

(7, 'PP'),
(7, 'P'),
(7, 'M'),
(7, 'G'),
(7, 'GG'),

(8, 'PP'),
(8, 'P'),
(8, 'M'),
(8, 'G'),
(8, 'GG'),

(9, 'PP'),
(9, 'P'),
(9, 'M'),
(9, 'G'),
(9, 'GG'),

(10, 'PP'),
(10, 'P'),
(10, 'M'),
(10, 'G'),
(10, 'GG');

SELECT * FROM tb_tamanho;


SELECT 
tb_produto.* 
FROM tb_produto 
INNER JOIN tb_produto_subcategoria 
ON tb_produto.id = tb_produto_subcategoria.produto_id 
WHERE tb_produto_subcategoria.colecao_id = 2;




INSERT INTO tb_cor VALUES
('#2F4F4F', 'DarkSlateGray'),
('#006400', 'DarkGreen'),
('#000000', 'Black'),
('#C0C0C0', 'Silver'),
('#808080', 'Gray'),
('#00008B', 'DarkBlue'),
('#0000FF', 'Blue');

SELECT * FROM tb_cor;

SELECT TABLE_NAME
FROM INFORMATION_SCHEMA.TABLES
WHERE TABLE_SCHEMA = 'CLOVER'
AND TABLE_ROWS = 0;


SELECT * FROM tb_cor_produto;

INSERT INTO tb_cor_produto VALUES
('#2F4F4F', 1),
('#006400', 1),
('#000000', 1),
('#C0C0C0', 1),
('#808080', 1),
('#00008B', 1),
('#0000FF', 1),

('#2F4F4F', 2),
('#006400', 2),
('#000000', 2),
('#C0C0C0', 2),
('#808080', 2),
('#00008B', 2),
('#0000FF', 2),

('#2F4F4F', 3),
('#006400', 3),
('#000000', 3),
('#C0C0C0', 3),
('#808080', 3),
('#00008B', 3),
('#0000FF', 3),

('#2F4F4F', 4),
('#006400', 4),
('#000000', 4),
('#C0C0C0', 4),
('#808080', 4),
('#00008B', 4),
('#0000FF', 4),

('#2F4F4F', 5),
('#006400', 5),
('#000000', 5),
('#C0C0C0', 5),
('#808080', 5),
('#00008B', 5),
('#0000FF', 5),

('#2F4F4F', 6),
('#006400', 6),
('#000000', 6),
('#C0C0C0', 6),
('#808080', 6),
('#00008B', 6),
('#0000FF', 6),

('#2F4F4F', 7),
('#006400', 7),
('#000000', 7),
('#C0C0C0', 7),
('#808080', 7),
('#00008B', 7),
('#0000FF', 7),

('#2F4F4F', 8),
('#006400', 8),
('#000000', 8),
('#C0C0C0', 8),
('#808080', 8),
('#00008B', 8),
('#0000FF', 8),

('#2F4F4F', 9),
('#006400', 9),
('#000000', 9),
('#C0C0C0', 9),
('#808080', 9),
('#00008B', 9),
('#0000FF', 9),

('#2F4F4F', 10),
('#006400', 10),
('#000000', 10),
('#C0C0C0', 10),
('#808080', 10),
('#00008B', 10),
('#0000FF', 10);


INSERT INTO tb_imagem VALUES
('#2F4F4F', 10, 'https://clovers3-image.s3.amazonaws.com/1685989495209_luffy3.jpeg'),
('#2F4F4F', 10, 'https://clovers3-image.s3.amazonaws.com/1685989494754_luffy2.jpeg'),
('#2F4F4F', 10, 'https://clovers3-image.s3.amazonaws.com/1685989493436_luffy1.jpeg');

SELECT cor_id as 'Hexadecimal', produto_id as 'ID do Produto', imagem as 'URL' FROM tb_imagem;


