INSERT INTO PROMOCOES.SITE (URL, NOME, SENHA, TELEFONE) VALUES ('grupo.com', 'grupo', '123', '12345'),
                                                               ('abc.xyz', 'abc', 'hihi', '18456'),
                                                               ('habbo.com.br', 'habbo', 'lalala', '486453123');

INSERT INTO PROMOCOES.HOTEL (CNPJ, NOME, SENHA, CIDADE) VALUES ('12345674912345', 'Hotelzinho', 'senhasegura', 'Campo Grande'),
                                                               ('12345678945678', 'Habbo Hotel', 'senhazinha', 'Campo Grande'),
                                                               ('12345678912345', 'Habbo Hotel', 'lalala', 'São Carlos');

INSERT INTO PROMOCOES.PROMOCAO (URLSITE, CNPJHOTEL, PRECO, DATAINICIAL, DATAFINAL) VALUES ('habbo.com.br', '12345678912345', 125, '2018-02-06', '2018-02-26'),
                                                                                          ('habbo.com.br', '12345678912345', 5678, '2018-03-06', '2018-08-12'),
                                                                                          ('grupo.com', '12345678945678', 125, '2018-02-06', '2018-02-26');