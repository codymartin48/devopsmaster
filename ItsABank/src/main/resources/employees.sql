-- Drop table

-- DROP TABLE public.employees;

CREATE TABLE public.employees (
	firstname varchar NOT NULL,
	lastname varchar NOT NULL,
	username varchar NOT NULL,
	"password" varchar NOT NULL,
	CONSTRAINT employees_pk PRIMARY KEY (username)
);

-- Permissions

ALTER TABLE public.employees OWNER TO codyrmartin;
GRANT ALL ON TABLE public.employees TO codyrmartin;
