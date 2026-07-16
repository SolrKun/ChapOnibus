-- =====================================================
-- ChapOnibus Database Schema
-- PostgreSQL + PostGIS
-- =====================================================


-- =====================================================
-- Extensions
-- =====================================================

CREATE EXTENSION IF NOT EXISTS postgis;


-- =====================================================
-- Users
-- =====================================================

CREATE TABLE usuarios (
    usuario VARCHAR(100) PRIMARY KEY,
    senha VARCHAR(100) NOT NULL,
    perfil VARCHAR(50) NOT NULL,
    primeiro_acesso BOOLEAN DEFAULT TRUE,

    CONSTRAINT chk_perfil_usuario
    CHECK (
        perfil IN (
            'Usuário',
            'Administrador',
            'Super Administrador'
        )
    )
);


-- =====================================================
-- Bus Stops
-- =====================================================

CREATE TABLE pontos_onibus (
    id SERIAL PRIMARY KEY,
    referencia TEXT,
    localizacao GEOGRAPHY(POINT, 4326)
);


-- =====================================================
-- Routes
-- =====================================================

CREATE TABLE rotas (
    id SERIAL PRIMARY KEY,
    nome TEXT,
    trajeto_geom GEOMETRY(LINESTRING, 4326)
);


-- =====================================================
-- Route Points
-- =====================================================

CREATE TABLE pontos_rota (
    id SERIAL PRIMARY KEY,

    rota_id INTEGER NOT NULL
        REFERENCES rotas(id)
        ON DELETE CASCADE,

    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    ordem_sequencia INTEGER NOT NULL
);


-- =====================================================
-- Route / Bus Stop Relationship
-- =====================================================

CREATE TABLE rotas_pontos (
    rota_id INTEGER NOT NULL,
    ponto_id INTEGER NOT NULL,
    ordem_sequencia INTEGER NOT NULL,

    CONSTRAINT fk_rota_ponto_rota
    FOREIGN KEY (rota_id)
    REFERENCES rotas(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_rota_ponto_ponto
    FOREIGN KEY (ponto_id)
    REFERENCES pontos_onibus(id)
    ON DELETE CASCADE
);


-- =====================================================
-- Buses
-- =====================================================

CREATE TABLE onibus (
    id_onibus SERIAL PRIMARY KEY,
    placa VARCHAR(10) UNIQUE NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    capacidade INT NOT NULL,

    id_rota INT
        REFERENCES rotas(id)
        ON DELETE SET NULL
);


-- =====================================================
-- Functions
-- =====================================================

CREATE OR REPLACE FUNCTION atualizar_trajeto_rota()
RETURNS TRIGGER AS $$

BEGIN

    UPDATE rotas
    SET trajeto_geom = (

        SELECT ST_MakeLine(
            ST_SetSRID(
                ST_MakePoint(longitude, latitude),
                4326
            )::geometry
            ORDER BY ordem_sequencia
        )

        FROM pontos_rota

        WHERE rota_id =
            CASE
                WHEN TG_OP = 'DELETE'
                THEN OLD.rota_id
                ELSE NEW.rota_id
            END
    )

    WHERE id =
        CASE
            WHEN TG_OP = 'DELETE'
            THEN OLD.rota_id
            ELSE NEW.rota_id
        END;


    RETURN NULL;

END;

$$ LANGUAGE plpgsql;


-- =====================================================
-- Triggers
-- =====================================================

CREATE TRIGGER trg_atualizar_trajeto

AFTER INSERT OR UPDATE OR DELETE
ON pontos_rota

FOR EACH ROW
EXECUTE FUNCTION atualizar_trajeto_rota();