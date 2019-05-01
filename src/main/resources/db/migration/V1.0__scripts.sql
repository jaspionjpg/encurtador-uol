create schema encurtador_uol;
create table encurtador_uol.link (
	id bigint,
	url varchar(255),
	referencia_url_gerada varchar(100) UNIQUE,
	data_criacao timestamp default NOW(),
	CONSTRAINT link_pk primary key (id)
);