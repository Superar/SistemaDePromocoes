DROP TABLE Promocao;
DROP TABLE Site;
DROP TABLE Hotel;

CREATE TABLE Site
(
    url VARCHAR(256) NOT NULL,
    nome VARCHAR(256),
    senha VARCHAR(20),
    telefone VARCHAR(24),
    
    CONSTRAINT primary_key_site PRIMARY KEY (url)
);

CREATE TABLE Hotel
(
    CNPJ VARCHAR(14) NOT NULL,
    nome VARCHAR(256),
    senha VARCHAR(20),
    cidade VARCHAR(256),

    CONSTRAINT primary_key_hotel PRIMARY KEY (cnpj)
);

CREATE TABLE Promocao
(
    urlSite VARCHAR(256) NOT NULL REFERENCES Site (url),
    CNPJHotel VARCHAR(14) NOT NULL REFERENCES Hotel (CNPJ),
    preco REAL,
    dataInicial DATE,
    dataFinal DATE,

    CONSTRAINT primary_key_promocao PRIMARY KEY (urlSite, CNPJHotel)
);
