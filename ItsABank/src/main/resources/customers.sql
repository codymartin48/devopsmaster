-- Drop table

-- DROP TABLE public.customers;

CREATE TABLE public.customers (
	firstname varchar NOT NULL,
	lastname varchar NOT NULL,
	firstname2 varchar NULL,
	lastname2 varchar NULL,
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	balance float8 NULL,
	CONSTRAINT customers_pk PRIMARY KEY (username)
);

-- Permissions

ALTER TABLE public.customers OWNER TO codyrmartin;
GRANT ALL ON TABLE public.customers TO codyrmartin;
