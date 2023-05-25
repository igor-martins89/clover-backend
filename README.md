# backend-clover

<!-- PARA RODAR NO AMBIENTE DE DEV, UTILIZE O SEGUINTE SCRIPT NO MYSQL -->
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


