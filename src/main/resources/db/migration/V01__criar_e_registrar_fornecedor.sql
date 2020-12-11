CREATE TABLE fornecedor(
	Id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(30) NOT NULL,
	cnpj VARCHAR(15) NOT NULL UNIQUE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO fornecedor (nome, cnpj)
values ("Microsoft", "45.576.539/989");
INSERT INTO fornecedor (nome, cnpj)
values ("Sony", "45.576.539/998");
INSERT INTO fornecedor (nome, cnpj)
values ("Apple", "45.516.539/998");
INSERT INTO fornecedor (nome, cnpj)
values ("Xiaomi", "45.476.539/998");
INSERT INTO fornecedor (nome, cnpj)
values ("Dell", "41.576.539/998");