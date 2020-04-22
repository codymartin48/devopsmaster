-- Drop table

-- DROP TABLE public.admins;

CREATE TABLE public.admins (
	firstname varchar NOT NULL,
	lastname varchar NOT NULL,
	username varchar NOT NULL,
	"password" varchar NOT NULL
);

-- Permissions

ALTER TABLE public.admins OWNER TO codyrmartin;
GRANT ALL ON TABLE public.admins TO codyrmartin;
