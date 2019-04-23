create table encurtador_uol.link (
	id bigint,
	url varchar(255),
	slug varchar(100),
	data_criacao timestamp default NOW()
);