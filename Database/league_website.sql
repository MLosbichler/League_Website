--
-- PostgreSQL database cluster dump
--

-- Started on 2024-04-04 15:05:25

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS;

--
-- User Configurations
--








--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

-- Started on 2024-04-04 15:05:25

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

-- Completed on 2024-04-04 15:05:26

--
-- PostgreSQL database dump complete
--

--
-- Database "league_website" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

-- Started on 2024-04-04 15:05:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 4854 (class 1262 OID 16753)
-- Name: league_website; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE league_website WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'German_Germany.1252';


ALTER DATABASE league_website OWNER TO postgres;

\connect league_website

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 217 (class 1259 OID 16774)
-- Name: league_entry; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.league_entry (
    league_id character varying,
    summoner_id character varying,
    summoner_name character varying,
    queue_type character varying,
    tier character varying,
    rank character varying,
    league_points integer,
    wins integer,
    losses integer,
    hot_streak boolean,
    veteran boolean,
    fresh_blood boolean,
    inactive boolean,
    id integer NOT NULL
);


ALTER TABLE public.league_entry OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16789)
-- Name: league_entry_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.league_entry ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.league_entry_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 215 (class 1259 OID 16754)
-- Name: riot_account; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.riot_account (
    puuid character varying NOT NULL,
    game_name character varying,
    tag_line character varying
);


ALTER TABLE public.riot_account OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16766)
-- Name: summoner; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.summoner (
    account_id character varying,
    profile_icon_id integer,
    revision_date numeric,
    name character varying,
    id character varying,
    puuid character varying NOT NULL,
    summoner_level integer
);


ALTER TABLE public.summoner OWNER TO postgres;

--
-- TOC entry 4847 (class 0 OID 16774)
-- Dependencies: 217
-- Data for Name: league_entry; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.league_entry (league_id, summoner_id, summoner_name, queue_type, tier, rank, league_points, wins, losses, hot_streak, veteran, fresh_blood, inactive, id) FROM stdin;
\.


--
-- TOC entry 4845 (class 0 OID 16754)
-- Dependencies: 215
-- Data for Name: riot_account; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.riot_account (puuid, game_name, tag_line) FROM stdin;
\.


--
-- TOC entry 4846 (class 0 OID 16766)
-- Dependencies: 216
-- Data for Name: summoner; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.summoner (account_id, profile_icon_id, revision_date, name, id, puuid, summoner_level) FROM stdin;
\.


--
-- TOC entry 4855 (class 0 OID 0)
-- Dependencies: 218
-- Name: league_entry_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.league_entry_id_seq', 1, true);


--
-- TOC entry 4701 (class 2606 OID 16796)
-- Name: league_entry league_entry_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.league_entry
    ADD CONSTRAINT league_entry_pk PRIMARY KEY (id);


--
-- TOC entry 4697 (class 2606 OID 16782)
-- Name: riot_account riot_account_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.riot_account
    ADD CONSTRAINT riot_account_pk PRIMARY KEY (puuid);


--
-- TOC entry 4699 (class 2606 OID 16798)
-- Name: summoner summoner_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.summoner
    ADD CONSTRAINT summoner_pk PRIMARY KEY (puuid);


-- Completed on 2024-04-04 15:05:26

--
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 16.2
-- Dumped by pg_dump version 16.2

-- Started on 2024-04-04 15:05:26

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2 (class 3079 OID 16384)
-- Name: adminpack; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;


--
-- TOC entry 4837 (class 0 OID 0)
-- Dependencies: 2
-- Name: EXTENSION adminpack; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 216 (class 1259 OID 16523)
-- Name: test; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.test (
);


ALTER TABLE public.test OWNER TO postgres;

--
-- TOC entry 4831 (class 0 OID 16523)
-- Dependencies: 216
-- Data for Name: test; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.test  FROM stdin;
\.


-- Completed on 2024-04-04 15:05:26

--
-- PostgreSQL database dump complete
--

-- Completed on 2024-04-04 15:05:26

--
-- PostgreSQL database cluster dump complete
--

