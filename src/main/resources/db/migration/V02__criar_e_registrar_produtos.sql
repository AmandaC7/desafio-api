CREATE TABLE produto (
	Id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	codigo_produto VARCHAR(20) NOT NULL UNIQUE,
	valor DECIMAL(10,2) NOT NULL,
	promocao BOOLEAN NOT NULL,
	valor_promocao DECIMAL(10,2),
	categoria VARCHAR(20),
	imagem BLOB,
	quantidade BIGINT NOT NULL,
	Id_fornecedor BIGINT(20) NOT NULL,
	FOREIGN KEY (Id_fornecedor) REFERENCES fornecedor(Id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria,imagem, quantidade, Id_fornecedor)
values("Playstation 4", "#PLAY4",2500, true, 2000, "Games", "play4.jpg", 15,1);
INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("DVD", "#DVD",300, false, 0, "Tecnologia","DvdSony.jpg", 12, 1);
INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("Playstation 5", "#PLAY5",5300, true, 5200, "Games","Play5.jpg", 170, 1);
INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("Câmera", "#CAMERA",2600, false, 0, "Fotografia","camera-sony.jpg", 52, 1);
INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("Xperia", "#XPERIA",2100, true, 1900, "Smartphone","XperiaSony.jpg", 112, 1);

INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("XBOX ONE", "#XBOX1",2300, true, 1900, "Games","XboxOne.jpg", 159, 2);
INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("XBOX X", "#XBOXX",5000, true, 4900, "Games","XboxX.jpg", 123, 2);
INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("Notebook Surface", "#SURFACE",4300, true, 4000, "Notebook","NoteSurfaceMicrosoft.jpg", 12,2);

INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("Iphone 12", "#IPHONE12",9300, true, 9200, "Smartphone","iphone-12.jpg", 230, 3);
INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("Iphone 8 plus", "#IPHONE8",5300, true, 5000, "Smartphone","iphone8plus.jpg", 122, 3);
INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("Macbook air", "#MACAIR",12300, true, 12000, "Notebook","MacbookAir.jpg", 102, 3);
INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("Macbook Pro", "#MACPRO",18300, false,0, "Smartphone","MacbookPro.jpg", 127, 3);

INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("Redmi note 8 pro", "#REDMI8PRO",1600, true, 1300, "Smartphone","Redmin8pro.jpg", 122, 4);
INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("Redmi 9s", "#REDMI9",1800, false, 0, "Smartphone","Redmin9s.jpg", 162, 4);
INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("Torneira Inteligente", "#TORNERIASMRT",350, false, 0, "Tecnologia","TorneiraInteligente.jpg", 122, 4);
INSERT INTO produto (nome, codigo_produto, valor, promocao, valor_promocao, categoria, imagem,quantidade, Id_fornecedor)
values("Yeelight 1s", "#YEELIGHT",180, false, 0, "Smart home","Yeelight.jpg", 122, 4);








