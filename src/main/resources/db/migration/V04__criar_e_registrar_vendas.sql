CREATE TABLE venda(
	Id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	data_compra DATE,
	total_compra DECIMAL(10,2),
	Id_fornecedor BIGINT(20) NOT NULL,
	FOREIGN KEY (Id_fornecedor) REFERENCES fornecedor(Id),
	Id_cliente BIGINT(20) NOT NULL,
	FOREIGN KEY (Id_cliente) REFERENCES cliente(Id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO venda(data_compra, total_compra, id_fornecedor, id_cliente)
values("2020-10-12", total_compra, 1, 1);
INSERT INTO venda(data_compra, total_compra, id_fornecedor, id_cliente)
values("2020-10-12", total_compra, 2, 2);


