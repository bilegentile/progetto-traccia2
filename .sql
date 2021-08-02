PGDMP                         y           DBProgettoTracciaDue    13.1    13.2 *    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    49356    DBProgettoTracciaDue    DATABASE     r   CREATE DATABASE "DBProgettoTracciaDue" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Italian_Italy.1252';
 &   DROP DATABASE "DBProgettoTracciaDue";
                postgres    false            �            1255    99026    meeting_inserimento_funzione()    FUNCTION     �   CREATE FUNCTION public.meeting_inserimento_funzione() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
New.codicemeeting:=nextval("sequenzacodicemeeting");
Return NEW;
END;
$$;
 5   DROP FUNCTION public.meeting_inserimento_funzione();
       public          postgres    false            �            1255    99024    progetto_inserimento_funzione()    FUNCTION     �   CREATE FUNCTION public.progetto_inserimento_funzione() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
New.codprogetto:=nextval("sequenzacodiceprogetti");
Return NEW;
END;
$$;
 6   DROP FUNCTION public.progetto_inserimento_funzione();
       public          postgres    false            �            1259    99075    ambito    TABLE     ?  CREATE TABLE public.ambito (
    nome_ambito character varying(100) NOT NULL,
    codice_progetto character varying(100) NOT NULL,
    CONSTRAINT ambito_nome_ambito_check CHECK ((((nome_ambito)::text ~~ 'Economia'::text) OR ((nome_ambito)::text ~~ 'Medicina'::text) OR ((nome_ambito)::text ~~ 'Informatica'::text)))
);
    DROP TABLE public.ambito;
       public         heap    postgres    false            �            1259    99047    archiviopartecipantimeeting    TABLE     �   CREATE TABLE public.archiviopartecipantimeeting (
    codfiscale character varying(100) NOT NULL,
    codicemeeting character varying(100) NOT NULL
);
 /   DROP TABLE public.archiviopartecipantimeeting;
       public         heap    postgres    false            �            1259    99028    archiviopartecipantiprogetto    TABLE     L  CREATE TABLE public.archiviopartecipantiprogetto (
    codfiscale character varying(100) NOT NULL,
    nomeprogetto character varying(100) NOT NULL,
    ruolo character varying,
    CONSTRAINT archiviopartecipantiprogetto_ruolo_check CHECK ((((ruolo)::text ~~ 'ProjectManager'::text) OR ((ruolo)::text ~~ 'Sviluppatore'::text)))
);
 0   DROP TABLE public.archiviopartecipantiprogetto;
       public         heap    postgres    false            �            1259    99013    meeting    TABLE     �  CREATE TABLE public.meeting (
    codicemeeting character varying(255) NOT NULL,
    data character varying(200) NOT NULL,
    orainizio character varying NOT NULL,
    piattaforma character varying,
    tipologia character varying,
    nomesala character varying,
    durata integer NOT NULL,
    CONSTRAINT meeting_data_check CHECK (((data)::text ~* '^[0-9][0-9]:[0-9][0-9]:[0-9][0-9][0-9][0-9]'::text)),
    CONSTRAINT meeting_orainizio_check CHECK (((orainizio)::text ~* '^[0-9][0-9]:[0-9][0-9]'::text)),
    CONSTRAINT meeting_tipologia_check CHECK ((((tipologia)::text ~~ 'Fisico'::text) OR ((tipologia)::text ~~ 'Telematico'::text)))
);
    DROP TABLE public.meeting;
       public         heap    postgres    false            �            1259    90821    membro    TABLE       CREATE TABLE public.membro (
    nome character varying(100) NOT NULL,
    cognome character varying(100) NOT NULL,
    codfiscale character varying(16) NOT NULL,
    ruolo character varying,
    salariomedio integer NOT NULL,
    valutazione character varying,
    CONSTRAINT membro_codfiscale_check CHECK (((codfiscale)::text ~* '^[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][0-9][0-9][A-Z][0-9][0-9][A-Z][0-9][0-9][0-9][A-Z]'::text)),
    CONSTRAINT membro_ruolo_check CHECK ((((ruolo)::text ~~ 'ProjectManager'::text) OR ((ruolo)::text ~~ 'Sviluppatore'::text))),
    CONSTRAINT membro_valutazione_check CHECK ((((valutazione)::text ~~ 'Buona'::text) OR ((valutazione)::text ~~ 'Mediocre'::text) OR ((valutazione)::text ~~ 'Male'::text) OR ((valutazione)::text ~~ 'NULL'::text)))
);
    DROP TABLE public.membro;
       public         heap    postgres    false            �            1259    90846    progetto    TABLE     ;  CREATE TABLE public.progetto (
    nome character varying(255) NOT NULL,
    tipo character varying(255) NOT NULL,
    codprogetto character varying(255) NOT NULL,
    stato character varying(255) NOT NULL,
    CONSTRAINT progetto_stato_check CHECK ((((stato)::text ~~ 'Completo'::text) OR ((stato)::text ~~ 'Incompleto'::text))),
    CONSTRAINT progetto_tipo_check CHECK ((((tipo)::text ~~ 'Ricerca di base'::text) OR ((tipo)::text ~~ 'Ricerca industruale'::text) OR ((tipo)::text ~~ 'Ricerca sperimentale'::text) OR ((tipo)::text ~~ 'Sviluppo sperimentale'::text)))
);
    DROP TABLE public.progetto;
       public         heap    postgres    false            �            1259    90788    sequenzacodicemeeting    SEQUENCE     �   CREATE SEQUENCE public.sequenzacodicemeeting
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    MAXVALUE 9999
    CACHE 1;
 ,   DROP SEQUENCE public.sequenzacodicemeeting;
       public          postgres    false            �            1259    74325    sequenzacodicemembri    SEQUENCE     �   CREATE SEQUENCE public.sequenzacodicemembri
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    MAXVALUE 99999
    CACHE 1;
 +   DROP SEQUENCE public.sequenzacodicemembri;
       public          postgres    false            �            1259    74323    sequenzacodiceprogetti    SEQUENCE     �   CREATE SEQUENCE public.sequenzacodiceprogetti
    START WITH 1000
    INCREMENT BY 1
    MINVALUE 1000
    MAXVALUE 99999
    CACHE 1;
 -   DROP SEQUENCE public.sequenzacodiceprogetti;
       public          postgres    false            �            1259    90832    skills    TABLE     v   CREATE TABLE public.skills (
    skill character varying(100) NOT NULL,
    membro character varying(100) NOT NULL
);
    DROP TABLE public.skills;
       public         heap    postgres    false            �          0    99075    ambito 
   TABLE DATA           >   COPY public.ambito (nome_ambito, codice_progetto) FROM stdin;
    public          postgres    false    209   �:       �          0    99047    archiviopartecipantimeeting 
   TABLE DATA           P   COPY public.archiviopartecipantimeeting (codfiscale, codicemeeting) FROM stdin;
    public          postgres    false    208   ;       �          0    99028    archiviopartecipantiprogetto 
   TABLE DATA           W   COPY public.archiviopartecipantiprogetto (codfiscale, nomeprogetto, ruolo) FROM stdin;
    public          postgres    false    207   3;       �          0    99013    meeting 
   TABLE DATA           k   COPY public.meeting (codicemeeting, data, orainizio, piattaforma, tipologia, nomesala, durata) FROM stdin;
    public          postgres    false    206   P;       �          0    90821    membro 
   TABLE DATA           ]   COPY public.membro (nome, cognome, codfiscale, ruolo, salariomedio, valutazione) FROM stdin;
    public          postgres    false    203   m;       �          0    90846    progetto 
   TABLE DATA           B   COPY public.progetto (nome, tipo, codprogetto, stato) FROM stdin;
    public          postgres    false    205   �;       �          0    90832    skills 
   TABLE DATA           /   COPY public.skills (skill, membro) FROM stdin;
    public          postgres    false    204   9<       �           0    0    sequenzacodicemeeting    SEQUENCE SET     F   SELECT pg_catalog.setval('public.sequenzacodicemeeting', 1013, true);
          public          postgres    false    202            �           0    0    sequenzacodicemembri    SEQUENCE SET     F   SELECT pg_catalog.setval('public.sequenzacodicemembri', 1000, false);
          public          postgres    false    201            �           0    0    sequenzacodiceprogetti    SEQUENCE SET     G   SELECT pg_catalog.setval('public.sequenzacodiceprogetti', 1017, true);
          public          postgres    false    200            ]           2606    99080    ambito ambito_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.ambito
    ADD CONSTRAINT ambito_pkey PRIMARY KEY (nome_ambito, codice_progetto);
 <   ALTER TABLE ONLY public.ambito DROP CONSTRAINT ambito_pkey;
       public            postgres    false    209    209            [           2606    99051 <   archiviopartecipantimeeting archiviopartecipantimeeting_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.archiviopartecipantimeeting
    ADD CONSTRAINT archiviopartecipantimeeting_pkey PRIMARY KEY (codfiscale, codicemeeting);
 f   ALTER TABLE ONLY public.archiviopartecipantimeeting DROP CONSTRAINT archiviopartecipantimeeting_pkey;
       public            postgres    false    208    208            Y           2606    99036 >   archiviopartecipantiprogetto archiviopartecipantiprogetto_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.archiviopartecipantiprogetto
    ADD CONSTRAINT archiviopartecipantiprogetto_pkey PRIMARY KEY (codfiscale, nomeprogetto);
 h   ALTER TABLE ONLY public.archiviopartecipantiprogetto DROP CONSTRAINT archiviopartecipantiprogetto_pkey;
       public            postgres    false    207    207            W           2606    99023    meeting meeting_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.meeting
    ADD CONSTRAINT meeting_pkey PRIMARY KEY (codicemeeting);
 >   ALTER TABLE ONLY public.meeting DROP CONSTRAINT meeting_pkey;
       public            postgres    false    206            O           2606    90831    membro membro_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.membro
    ADD CONSTRAINT membro_pkey PRIMARY KEY (codfiscale);
 <   ALTER TABLE ONLY public.membro DROP CONSTRAINT membro_pkey;
       public            postgres    false    203            S           2606    90858    progetto progetto_nome_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.progetto
    ADD CONSTRAINT progetto_nome_key UNIQUE (nome);
 D   ALTER TABLE ONLY public.progetto DROP CONSTRAINT progetto_nome_key;
       public            postgres    false    205            U           2606    90856    progetto progetto_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.progetto
    ADD CONSTRAINT progetto_pkey PRIMARY KEY (codprogetto);
 @   ALTER TABLE ONLY public.progetto DROP CONSTRAINT progetto_pkey;
       public            postgres    false    205            Q           2606    90836    skills skills_pkey 
   CONSTRAINT     [   ALTER TABLE ONLY public.skills
    ADD CONSTRAINT skills_pkey PRIMARY KEY (membro, skill);
 <   ALTER TABLE ONLY public.skills DROP CONSTRAINT skills_pkey;
       public            postgres    false    204    204            e           2620    99027 #   meeting meeting_inserimento_trigger    TRIGGER     �   CREATE TRIGGER meeting_inserimento_trigger BEFORE INSERT ON public.meeting FOR EACH ROW EXECUTE FUNCTION public.meeting_inserimento_funzione();
 <   DROP TRIGGER meeting_inserimento_trigger ON public.meeting;
       public          postgres    false    211    206            d           2620    99025 %   progetto progetto_inserimento_trigger    TRIGGER     �   CREATE TRIGGER progetto_inserimento_trigger BEFORE INSERT ON public.progetto FOR EACH ROW EXECUTE FUNCTION public.progetto_inserimento_funzione();
 >   DROP TRIGGER progetto_inserimento_trigger ON public.progetto;
       public          postgres    false    205    210            _           2606    99037 3   archiviopartecipantiprogetto fk_codfiscale_archivio    FK CONSTRAINT     �   ALTER TABLE ONLY public.archiviopartecipantiprogetto
    ADD CONSTRAINT fk_codfiscale_archivio FOREIGN KEY (codfiscale) REFERENCES public.membro(codfiscale);
 ]   ALTER TABLE ONLY public.archiviopartecipantiprogetto DROP CONSTRAINT fk_codfiscale_archivio;
       public          postgres    false    2895    203    207            a           2606    99052 1   archiviopartecipantimeeting fk_codfiscale_meeting    FK CONSTRAINT     �   ALTER TABLE ONLY public.archiviopartecipantimeeting
    ADD CONSTRAINT fk_codfiscale_meeting FOREIGN KEY (codfiscale) REFERENCES public.membro(codfiscale);
 [   ALTER TABLE ONLY public.archiviopartecipantimeeting DROP CONSTRAINT fk_codfiscale_meeting;
       public          postgres    false    203    2895    208            c           2606    99081    ambito fk_codiceprogetto_ambito    FK CONSTRAINT     �   ALTER TABLE ONLY public.ambito
    ADD CONSTRAINT fk_codiceprogetto_ambito FOREIGN KEY (codice_progetto) REFERENCES public.progetto(codprogetto);
 I   ALTER TABLE ONLY public.ambito DROP CONSTRAINT fk_codiceprogetto_ambito;
       public          postgres    false    205    2901    209            `           2606    99042 5   archiviopartecipantiprogetto fk_nomeprogetto_archivio    FK CONSTRAINT     �   ALTER TABLE ONLY public.archiviopartecipantiprogetto
    ADD CONSTRAINT fk_nomeprogetto_archivio FOREIGN KEY (nomeprogetto) REFERENCES public.progetto(nome);
 _   ALTER TABLE ONLY public.archiviopartecipantiprogetto DROP CONSTRAINT fk_nomeprogetto_archivio;
       public          postgres    false    207    205    2899            b           2606    99057 3   archiviopartecipantimeeting fk_nomeprogetto_meeting    FK CONSTRAINT     �   ALTER TABLE ONLY public.archiviopartecipantimeeting
    ADD CONSTRAINT fk_nomeprogetto_meeting FOREIGN KEY (codicemeeting) REFERENCES public.meeting(codicemeeting);
 ]   ALTER TABLE ONLY public.archiviopartecipantimeeting DROP CONSTRAINT fk_nomeprogetto_meeting;
       public          postgres    false    2903    206    208            ^           2606    99096    skills pk_membro_skill    FK CONSTRAINT     �   ALTER TABLE ONLY public.skills
    ADD CONSTRAINT pk_membro_skill FOREIGN KEY (membro) REFERENCES public.membro(codfiscale) NOT VALID;
 @   ALTER TABLE ONLY public.skills DROP CONSTRAINT pk_membro_skill;
       public          postgres    false    204    203    2895            �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   c   x�M˱@@���0�x��e�4\�"z�����'�g�R�\ʶ�C�X ��������-�߄vjGl����@=�P�=r�1rR?5gι�� �      �   I   x��ww	�W���q��LN-JNT(.H-��M�+I�I�4404���K��-�I-��
 �!��YK� k�"F      �   [   x�(�+)M��,9������7������ � ������2���2��R1���rRs��s�2��1��$�d&�䈤 F��� :P.s     