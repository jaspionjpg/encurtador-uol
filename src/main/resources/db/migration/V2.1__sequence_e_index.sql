CREATE SEQUENCE encurtador_uol.link_sequence INCREMENT BY 1 MINVALUE 1 MAXVALUE 9223372036854775807;

CREATE INDEX idx_link__referencia_url_gerada ON encurtador_uol.link (referencia_url_gerada);