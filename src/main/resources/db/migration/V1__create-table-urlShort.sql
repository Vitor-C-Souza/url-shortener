CREATE TABLE url_short(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    url varchar(200) NOT NULL,
    urlShortened varchar(10) NOT NULL UNIQUE,
    countUse integer NOT NULL,
    creationDate timestamp(6) NOT NULL,
    expirationDate timestamp(6) NOT NULL,
    PRIMARY KEY (id)
);