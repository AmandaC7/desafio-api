CREATE TABLE cliente (
	Id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL,
	senha VARCHAR(30) NOT NULL,
	email VARCHAR(30) NOT NULL UNIQUE,
	documento VARCHAR(30) NOT NULL UNIQUE,
	data_cadastro DATE


)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO cliente (nome,senha, email, documento, data_cadastro)
VALUES ("Amanda", "awesomeP@ss246", "amanda.sousa@gft.com", "377656710", "2020-03-12");
INSERT INTO cliente (nome,senha, email, documento, data_cadastro)
VALUES ("Vanessa", "senha123", "vanessa.sousa@gft.com", "377654510", "2020-08-12");
INSERT INTO cliente (nome,senha, email, documento, data_cadastro)
VALUES ("Gabriel","thisMyPass", "gabriel.sousa@gft.com", "371236710", "2020-10-12");
INSERT INTO cliente (nome,senha, email, documento, data_cadastro)
VALUES ("Henrique","henrique" ,"henrique.sousa@gft.com", "377657480", "2019-03-04");
INSERT INTO cliente (nome,senha, email, documento, data_cadastro)
VALUES ("Clecio","TonyStark", "clecio.sousa@gft.com", "427657480", "2019-03-05");