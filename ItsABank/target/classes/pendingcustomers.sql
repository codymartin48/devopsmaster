-- Drop table

-- DROP TABLE public.pendingcustomers;

CREATE TABLE public.pendingcustomers (
	firstname varchar NOT NULL,
	lastname varchar NOT NULL,
	firstname2 varchar NULL,
	lastname2 varchar NULL,
	username varchar NOT NULL,
	"password" varchar NOT NULL
);

-- Permissions

ALTER TABLE public.pendingcustomers OWNER TO codyrmartin;
GRANT ALL ON TABLE public.pendingcustomers TO codyrmartin;
