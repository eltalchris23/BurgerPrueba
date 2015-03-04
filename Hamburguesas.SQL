/*
Created: 2/15/2015
Modified: 3/2/2015
Model: Hamburguesas
Database: PostgreSQL 9.2
*/


-- Create schemas section -------------------------------------------------

CREATE SCHEMA "fotos"
;

-- Create sequences section -------------------------------------------------

CREATE SEQUENCE "fotos"."seq_usuarios"
 INCREMENT BY 1
 NO MAXVALUE
 NO MINVALUE
 CACHE 1
;

-- Create tables section -------------------------------------------------

-- Table fotos.datos

CREATE TABLE "fotos"."datos"(
 "id" Bigint NOT NULL,
 "reporte" Varchar DEFAULT 50 NOT NULL,
 "ruta" Varchar DEFAULT 50 NOT NULL,
 "fecha_generacion" Timestamp with time zone NOT NULL,
 "usuario_modifico" Varchar DEFAULT 20 NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table fotos.datos

ALTER TABLE "fotos"."datos" ADD CONSTRAINT "pk_id" PRIMARY KEY ("id")
;

-- Table fotos.usuarios

CREATE TABLE "fotos"."usuarios"(
 "id" Bigint NOT NULL,
 "usuario" Varchar DEFAULT 25 NOT NULL,
 "clave" Varchar DEFAULT 30 NOT NULL
)
WITH (OIDS=FALSE)
;

-- Add keys for table fotos.usuarios

ALTER TABLE "fotos"."usuarios" ADD CONSTRAINT "PK_id" PRIMARY KEY ("id")
;





