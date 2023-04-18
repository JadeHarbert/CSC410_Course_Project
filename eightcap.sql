--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

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
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    address_id integer NOT NULL,
    address_line_1 character varying(50),
    address_line_2 character varying(50),
    city character varying(50),
    state character(2),
    zip character(5),
    address_type_id integer
);


ALTER TABLE public.address OWNER TO postgres;

--
-- Name: address_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address_type (
    address_type_id integer NOT NULL,
    address_type character varying(50)
);


ALTER TABLE public.address_type OWNER TO postgres;

--
-- Name: address_types_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.address_types_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.address_types_id_seq OWNER TO postgres;

--
-- Name: address_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.address_types_id_seq OWNED BY public.address_type.address_type_id;


--
-- Name: addresses_address_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.addresses_address_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.addresses_address_id_seq OWNER TO postgres;

--
-- Name: addresses_address_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.addresses_address_id_seq OWNED BY public.address.address_id;


--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.client (
    client_id integer NOT NULL,
    first_name character varying(25),
    last_name character varying(25),
    date_of_birth date,
    datetime_of_entry timestamp without time zone NOT NULL,
    prescreen_date date,
    dba_id integer,
    hmis integer
);


ALTER TABLE public.client OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.client_id_seq OWNER TO postgres;

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.client_id;


--
-- Name: county; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.county (
    county_id integer NOT NULL,
    county_name character varying(25)
);


ALTER TABLE public.county OWNER TO postgres;

--
-- Name: county_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.county_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.county_id_seq OWNER TO postgres;

--
-- Name: county_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.county_id_seq OWNED BY public.county.county_id;


--
-- Name: followups; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.followups (
    followup_id integer NOT NULL,
    ticket_id integer,
    followup_date timestamp without time zone NOT NULL,
    followup_outcome character varying(1500)
);


ALTER TABLE public.followups OWNER TO postgres;

--
-- Name: followups_followup_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.followups_followup_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.followups_followup_id_seq OWNER TO postgres;

--
-- Name: followups_followup_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.followups_followup_id_seq OWNED BY public.followups.followup_id;


--
-- Name: phone_number; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.phone_number (
    phone_number_id integer NOT NULL,
    phone_number character(10),
    number_type_id integer
);


ALTER TABLE public.phone_number OWNER TO postgres;

--
-- Name: phone_number_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.phone_number_type (
    number_type_id integer NOT NULL,
    number_type character varying(10)
);


ALTER TABLE public.phone_number_type OWNER TO postgres;

--
-- Name: phone_number_types_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.phone_number_types_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.phone_number_types_id_seq OWNER TO postgres;

--
-- Name: phone_number_types_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.phone_number_types_id_seq OWNED BY public.phone_number_type.number_type_id;


--
-- Name: phone_numbers_phone_number_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.phone_numbers_phone_number_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.phone_numbers_phone_number_id_seq OWNER TO postgres;

--
-- Name: phone_numbers_phone_number_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.phone_numbers_phone_number_id_seq OWNED BY public.phone_number.phone_number_id;


--
-- Name: preferred_contact; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.preferred_contact (
    preferred_contact_id integer NOT NULL,
    contact_method character varying(25)
);


ALTER TABLE public.preferred_contact OWNER TO postgres;

--
-- Name: preferred_contact_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.preferred_contact_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.preferred_contact_id_seq OWNER TO postgres;

--
-- Name: preferred_contact_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.preferred_contact_id_seq OWNED BY public.preferred_contact.preferred_contact_id;


--
-- Name: service; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.service (
    service_id integer NOT NULL,
    service_name character varying(25) NOT NULL
);


ALTER TABLE public.service OWNER TO postgres;

--
-- Name: services_service_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.services_service_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.services_service_id_seq OWNER TO postgres;

--
-- Name: services_service_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.services_service_id_seq OWNED BY public.service.service_id;


--
-- Name: staff; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.staff (
    staff_id integer NOT NULL,
    first_name character varying(25) NOT NULL,
    last_name character varying(25) NOT NULL,
    work_phone_number character(10),
    service_id integer
);


ALTER TABLE public.staff OWNER TO postgres;

--
-- Name: staff_staff_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.staff_staff_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.staff_staff_id_seq OWNER TO postgres;

--
-- Name: staff_staff_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.staff_staff_id_seq OWNED BY public.staff.staff_id;


--
-- Name: status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status (
    status_id integer NOT NULL,
    status_name character varying(50)
);


ALTER TABLE public.status OWNER TO postgres;

--
-- Name: statuses_status_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.statuses_status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.statuses_status_id_seq OWNER TO postgres;

--
-- Name: statuses_status_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.statuses_status_id_seq OWNED BY public.status.status_id;


--
-- Name: ticket; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ticket (
    ticket_id integer NOT NULL,
    subject character varying(100),
    details character varying(1000),
    client_id integer,
    creator_of_ticket integer,
    entry_date timestamp without time zone NOT NULL,
    call_date timestamp without time zone NOT NULL,
    status_id integer,
    service_type_id integer
);


ALTER TABLE public.ticket OWNER TO postgres;

--
-- Name: ticket_ticket_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ticket_ticket_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ticket_ticket_id_seq OWNER TO postgres;

--
-- Name: ticket_ticket_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ticket_ticket_id_seq OWNED BY public.ticket.ticket_id;


--
-- Name: address address_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address ALTER COLUMN address_id SET DEFAULT nextval('public.addresses_address_id_seq'::regclass);


--
-- Name: address_type address_type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address_type ALTER COLUMN address_type_id SET DEFAULT nextval('public.address_types_id_seq'::regclass);


--
-- Name: client client_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client ALTER COLUMN client_id SET DEFAULT nextval('public.client_id_seq'::regclass);


--
-- Name: county county_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.county ALTER COLUMN county_id SET DEFAULT nextval('public.county_id_seq'::regclass);


--
-- Name: followups followup_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.followups ALTER COLUMN followup_id SET DEFAULT nextval('public.followups_followup_id_seq'::regclass);


--
-- Name: phone_number phone_number_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phone_number ALTER COLUMN phone_number_id SET DEFAULT nextval('public.phone_numbers_phone_number_id_seq'::regclass);


--
-- Name: phone_number_type number_type_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phone_number_type ALTER COLUMN number_type_id SET DEFAULT nextval('public.phone_number_types_id_seq'::regclass);


--
-- Name: preferred_contact preferred_contact_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.preferred_contact ALTER COLUMN preferred_contact_id SET DEFAULT nextval('public.preferred_contact_id_seq'::regclass);


--
-- Name: service service_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.service ALTER COLUMN service_id SET DEFAULT nextval('public.services_service_id_seq'::regclass);


--
-- Name: staff staff_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staff ALTER COLUMN staff_id SET DEFAULT nextval('public.staff_staff_id_seq'::regclass);


--
-- Name: status status_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status ALTER COLUMN status_id SET DEFAULT nextval('public.statuses_status_id_seq'::regclass);


--
-- Name: ticket ticket_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket ALTER COLUMN ticket_id SET DEFAULT nextval('public.ticket_ticket_id_seq'::regclass);


--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.address (address_id, address_line_1, address_line_2, city, state, zip, address_type_id) FROM stdin;
\.


--
-- Data for Name: address_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.address_type (address_type_id, address_type) FROM stdin;
1	Home
2	Work
3	Mailing
\.


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.client (client_id, first_name, last_name, date_of_birth, datetime_of_entry, prescreen_date, dba_id, hmis) FROM stdin;
\.


--
-- Data for Name: county; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.county (county_id, county_name) FROM stdin;
1	Montcalm
2	Gratiot
3	Ionia
4	Isabella
5	Other
\.


--
-- Data for Name: followups; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.followups (followup_id, ticket_id, followup_date, followup_outcome) FROM stdin;
\.


--
-- Data for Name: phone_number; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.phone_number (phone_number_id, phone_number, number_type_id) FROM stdin;
\.


--
-- Data for Name: phone_number_type; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.phone_number_type (number_type_id, number_type) FROM stdin;
1	Wi-Fi
2	Home
3	Work
4	Personal
\.


--
-- Data for Name: preferred_contact; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.preferred_contact (preferred_contact_id, contact_method) FROM stdin;
1	phone
2	WI-FI Phone
3	email
\.


--
-- Data for Name: service; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.service (service_id, service_name) FROM stdin;
1	Community Service
2	Eviction Diversion
\.


--
-- Data for Name: staff; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.staff (staff_id, first_name, last_name, work_phone_number, service_id) FROM stdin;
\.


--
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.status (status_id, status_name) FROM stdin;
1	Open
2	Closed
3	Waiting On Client
4	Pending Prescreening
\.


--
-- Data for Name: ticket; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ticket (ticket_id, subject, details, client_id, creator_of_ticket, entry_date, call_date, status_id, service_type_id) FROM stdin;
\.


--
-- Name: address_types_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.address_types_id_seq', 3, true);


--
-- Name: addresses_address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.addresses_address_id_seq', 1, false);


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.client_id_seq', 1, false);


--
-- Name: county_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.county_id_seq', 5, true);


--
-- Name: followups_followup_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.followups_followup_id_seq', 1, false);


--
-- Name: phone_number_types_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.phone_number_types_id_seq', 4, true);


--
-- Name: phone_numbers_phone_number_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.phone_numbers_phone_number_id_seq', 1, false);


--
-- Name: preferred_contact_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.preferred_contact_id_seq', 3, true);


--
-- Name: services_service_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.services_service_id_seq', 2, true);


--
-- Name: staff_staff_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.staff_staff_id_seq', 1, false);


--
-- Name: statuses_status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.statuses_status_id_seq', 4, true);


--
-- Name: ticket_ticket_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ticket_ticket_id_seq', 1, false);


--
-- Name: address_type address_types_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address_type
    ADD CONSTRAINT address_types_id_key UNIQUE (address_type_id);


--
-- Name: address addresses_address_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT addresses_address_id_key UNIQUE (address_id);


--
-- Name: client client_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_id_key UNIQUE (client_id);


--
-- Name: county county_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.county
    ADD CONSTRAINT county_id_key UNIQUE (county_id);


--
-- Name: followups followups_followup_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.followups
    ADD CONSTRAINT followups_followup_id_key UNIQUE (followup_id);


--
-- Name: phone_number_type phone_number_types_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phone_number_type
    ADD CONSTRAINT phone_number_types_id_key UNIQUE (number_type_id);


--
-- Name: phone_number phone_numbers_phone_number_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phone_number
    ADD CONSTRAINT phone_numbers_phone_number_id_key UNIQUE (phone_number_id);


--
-- Name: preferred_contact preferred_contact_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.preferred_contact
    ADD CONSTRAINT preferred_contact_id_key UNIQUE (preferred_contact_id);


--
-- Name: service services_service_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.service
    ADD CONSTRAINT services_service_id_key UNIQUE (service_id);


--
-- Name: service services_service_name_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.service
    ADD CONSTRAINT services_service_name_key UNIQUE (service_name);


--
-- Name: staff staff_staff_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staff
    ADD CONSTRAINT staff_staff_id_key UNIQUE (staff_id);


--
-- Name: status statuses_status_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT statuses_status_id_key UNIQUE (status_id);


--
-- Name: ticket ticket_ticket_id_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_ticket_id_key UNIQUE (ticket_id);


--
-- Name: address addresses_address_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT addresses_address_type_id_fkey FOREIGN KEY (address_type_id) REFERENCES public.address_type(address_type_id);


--
-- Name: followups followups_ticket_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.followups
    ADD CONSTRAINT followups_ticket_id_fkey FOREIGN KEY (ticket_id) REFERENCES public.ticket(ticket_id);


--
-- Name: phone_number phone_numbers_number_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.phone_number
    ADD CONSTRAINT phone_numbers_number_type_id_fkey FOREIGN KEY (number_type_id) REFERENCES public.phone_number_type(number_type_id);


--
-- Name: staff staff_service_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.staff
    ADD CONSTRAINT staff_service_id_fkey FOREIGN KEY (service_id) REFERENCES public.service(service_id);


--
-- Name: ticket ticket_client_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_client_id_fkey FOREIGN KEY (client_id) REFERENCES public.client(client_id);


--
-- Name: ticket ticket_creator_of_ticket_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_creator_of_ticket_fkey FOREIGN KEY (creator_of_ticket) REFERENCES public.staff(staff_id);


--
-- Name: ticket ticket_service_type_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_service_type_id_fkey FOREIGN KEY (service_type_id) REFERENCES public.service(service_id);


--
-- Name: ticket ticket_status_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ticket
    ADD CONSTRAINT ticket_status_id_fkey FOREIGN KEY (status_id) REFERENCES public.status(status_id);


--
-- PostgreSQL database dump complete
--

