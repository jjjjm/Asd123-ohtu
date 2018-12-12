/* Define database schema here */
CREATE TABLE BOOK (
    ID SERIAL,
    TITLE VARCHAR(200) NOT NULL,
    WRITER VARCHAR(200) NOT NULL,
    ISBN VARCHAR(50),
    DESCRIPTION VARCHAR(1000),
    IS_READ BOOLEAN,
    DATE_OF_READ TIMESTAMP,
    PRIMARY KEY (ID)
);

CREATE TABLE BLOG (
    ID SERIAL,
    TITLE VARCHAR(200) NOT NULL,
    WRITER VARCHAR(200) NOT NULL,
    URL VARCHAR(200) NOT NULL,
    DESCRIPTION VARCHAR(1000),
    IS_READ BOOLEAN,
    DATE_OF_READ TIMESTAMP,
    PRIMARY KEY (ID)
);

CREATE TABLE TAG (
    ID SERIAL,
    NAME VARCHAR(100) NOT NULL,
    DATE_CREATED TIMESTAMP
);

/*CREATE TABLE BOOK_TAG (
    FOREIGN KEY REFERENCES BOOK (ID),
    FOREIGN KEY REFERENCES TAG (ID)
);

CREATE TABLE BLOG_TAG (
    FOREIGN KEY REFERENCES BLOG (ID),
    FOREIGN KEY REFERENCES TAG (ID)
);*/