DROP TABLE IF EXISTS BOUTEILLE;

CREATE TABLE BOUTEILLE
(
  ID decimal(22) auto_increment PRIMARY KEY NOT NULL,
  NOM varchar2(250) NOT NULL,
  NOM_CHATEAU varchar2(250),
  MILLESIME decimal(10) NOT NULL,
  CONTENANCE decimal(10) NOT NULL
);
