PGDMP     -    /                w        	   url_short    11.2    11.2 	                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                       false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                       false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                       false                       1262    16393 	   url_short    DATABASE     �   CREATE DATABASE url_short WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'English_United States.1252' LC_CTYPE = 'English_United States.1252';
    DROP DATABASE url_short;
             zdravko    false            �            1259    16460 	   short_url    TABLE     a   CREATE TABLE public.short_url (
    short_url bigint NOT NULL,
    original_url text NOT NULL
);
    DROP TABLE public.short_url;
       public         zdravko    false            �            1259    16458    short_url_short_url_seq    SEQUENCE     �   CREATE SEQUENCE public.short_url_short_url_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.short_url_short_url_seq;
       public       zdravko    false    199                       0    0    short_url_short_url_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.short_url_short_url_seq OWNED BY public.short_url.short_url;
            public       zdravko    false    198            �
           2604    16463    short_url short_url    DEFAULT     z   ALTER TABLE ONLY public.short_url ALTER COLUMN short_url SET DEFAULT nextval('public.short_url_short_url_seq'::regclass);
 B   ALTER TABLE public.short_url ALTER COLUMN short_url DROP DEFAULT;
       public       zdravko    false    199    198    199            �
           2606    16468    short_url short_url_pkey 
   CONSTRAINT     ]   ALTER TABLE ONLY public.short_url
    ADD CONSTRAINT short_url_pkey PRIMARY KEY (short_url);
 B   ALTER TABLE ONLY public.short_url DROP CONSTRAINT short_url_pkey;
       public         zdravko    false    199           