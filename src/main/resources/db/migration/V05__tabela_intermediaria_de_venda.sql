
CREATE TABLE produtos_venda(
	Id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	Id_venda BIGINT(20),
	FOREIGN KEY (Id_venda) REFERENCES venda(Id),
	Id_produto BIGINT(20),
	FOREIGN KEY (Id_produto) REFERENCES produto(Id)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;




