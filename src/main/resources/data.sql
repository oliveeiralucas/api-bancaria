-- LIMPA OS DADOS QUANDO REINICIA --
DELETE FROM TRANSACTIONS;

DELETE FROM WALLETS;

-- Insert wallets --
INSERT INTO
    WALLETS (
        ID, FULL_NAME, CPF, EMAIL, "PASSWORD", "TYPE", BALANCE, "VERSION"
    )
VALUES (
        1, 'Lucas - User', 12347778900, 'lucas.oliveira@edu.unifil.com', '123456', 1, 1000.00, 1
    ),
    (
        2, 'Petri - User', 12345678900, 'ricardo.petri@unifil.com', '123456', 1, 1000.00, 1
    );
    ;

INSERT INTO
    WALLETS (
        ID, FULL_NAME, CPF, EMAIL, "PASSWORD", "TYPE", BALANCE, "VERSION"
    )
VALUES (
        3, 'Lucas Pinheiros - Lojista', 12345678901, 'lucas.fernandes@edu.unifil.com', '123456', 2, 1000.00, 1
    );