-- AUTORES
INSERT INTO autor (nome) VALUES ('Fulano da Silva');
INSERT INTO autor (nome) VALUES ('Ciclano de Souza');
INSERT INTO autor (nome) VALUES ('Beltrano Ferreira');

-- PUBLICAÇÕES
INSERT INTO publicacao (titulo, texto, data_publicacao, autor_id)
VALUES (
    'Como viver bem',
    'Veja neste texto dicas de como viver bem.',
    '2025-10-31',
    1
);

INSERT INTO publicacao (titulo, texto, data_publicacao, autor_id)
VALUES (
    '20 Dicas para falar bem',
    'Aqui estão 20 dicas para você falar melhor em público.',
    '2025-12-02',
    2
);

INSERT INTO publicacao (titulo, texto, data_publicacao, autor_id)
VALUES (
    'Guia de produtividade',
    'Aprenda como organizar sua rotina e aumentar sua produtividade.',
    CURRENT_DATE,
    3
);
