CREATE TABLE tb_product (
    id VARCHAR(36),
    name VARCHAR(100) NOT NULL UNIQUE CHECK(LENGTH(name)>=3),
    description VARCHAR(300) NOT NULL CHECK(LENGTH(description)>=3),
    price NUMERIC(7,2) NOT NULL,
    date DATETIME NOT NULL,
    img_url varchar(200) NOT NULL CHECK(LENGTH(img_url)>=12),
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE tb_category (
    id VARCHAR(36),
    name VARCHAR(50) NOT NULL UNIQUE CHECK(LENGTH(name)>=3),
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE tb_product_category (
    product_id VARCHAR(36),
    category_id VARCHAR(36),
    CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES tb_product(id),
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES tb_category(id),
    CONSTRAINT pk_product_category PRIMARY KEY (product_id,category_id)
);

CREATE TABLE tb_user (
    id VARCHAR(36),
    first_name VARCHAR(20) NOT NULL CHECK(LENGTH(first_name)>=2),
    last_name VARCHAR(25) NOT NULL CHECK(LENGTH(last_name)>=2),
    email VARCHAR(70) NOT NULL UNIQUE CHECK(LENGTH(email)>=8),
    password VARCHAR(50) NOT NULL CHECK(LENGTH(password)>=20),
    CONSTRAINT pk_user PRIMARY KEY (id)
);

CREATE TABLE tb_role (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL UNIQUE CHECK(LENGTH(name)>=6),
    CONSTRAINT pk_role PRIMARY KEY (id)
);

CREATE TABLE tb_user_role (
    user_id VARCHAR(36),
    role_id INT,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES tb_user(id),
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES tb_role(id),
    CONSTRAINT pk_user_role PRIMARY KEY (user_id,role_id)
);