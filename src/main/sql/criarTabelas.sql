DROP TABLE Promocao;
DROP TABLE Site;
DROP TABLE Hotel;
DROP TABLE Administrador;

CREATE TABLE Site
(
	    url VARCHAR(256) NOT NULL,
	    nome VARCHAR(256) NOT NULL,
	    senha VARCHAR(20) NOT NULL,
	    telefone VARCHAR(24) NOT NULL,
	    
	    CONSTRAINT primary_key_site PRIMARY KEY (url)
);

CREATE TABLE Hotel
(
	    CNPJ VARCHAR(14) NOT NULL,
	    nome VARCHAR(256) NOT NULL,
	    senha VARCHAR(20) NOT NULL,
	    cidade VARCHAR(256) NOT NULL,

	    CONSTRAINT primary_key_hotel PRIMARY KEY (cnpj)
);

CREATE TABLE Promocao
(
	    urlSite VARCHAR(256) NOT NULL REFERENCES Site (url),
	    CNPJHotel VARCHAR(14) NOT NULL REFERENCES Hotel (CNPJ),
	    preco REAL NOT NULL,
	    dataInicial DATE NOT NULL,
	    dataFinal DATE NOT NULL,

	    CONSTRAINT primary_key_promocao PRIMARY KEY (urlSite, CNPJHotel)
);

CREATE TABLE Administrador
(
            login VARCHAR(30) NOT NULL,
            senha VARCHAR(20) NOT NULL,

            CONSTRAINT primary_key_administrador PRIMARY KEY (login)
);