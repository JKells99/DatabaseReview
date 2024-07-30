CREATE TABLE animals2(
  id SERIAL PRIMARY KEY,
  animalName VARCHAR(100) NOT NULL,
	animalBreed VARCHAR(100) NOT NULL, 
  animalType VARCHAR(25) NOT NULL
)

CREATE TABLE users(
  userId SERIAL PRIMARY KEY,
  userName VARCHAR(100) NOT NULL,
	password VARCHAR(100) NOT NULL
 
)

-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    user_id integer NOT NULL DEFAULT nextval('users_user_id_seq'::regclass),
    username character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role character varying(50) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default",
    created_at timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT users_username_key UNIQUE (username)
        ON DELETE CASCADE
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;



-- Table: public.products

-- DROP TABLE IF EXISTS public.products;

CREATE TABLE IF NOT EXISTS public.products
(
    product_id integer NOT NULL DEFAULT nextval('products_product_id_seq'::regclass),
    product_name character varying(100) COLLATE pg_catalog."default" NOT NULL,
    product_price numeric(10,2) NOT NULL,
    product_quantity integer NOT NULL,
    seller_id integer,
    CONSTRAINT products_pkey PRIMARY KEY (product_id),
    CONSTRAINT seller_id FOREIGN KEY (seller_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.products
    OWNER to postgres;