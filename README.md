# PlaneCatalogServlet

## Создай БД plane_catalog_list в PostgreSQL (pgAdmin4).

## Добавь таблицу profile.
```SQL
CREATE TABLE IF NOT EXISTS public.profile
(
    id bigint NOT NULL DEFAULT 'nextval('profile_id_seq'::regclass)',
    login character varying(50) COLLATE pg_catalog."default" NOT NULL,
    password character varying(50) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT profile_pkey PRIMARY KEY (id)
)
```

## Добавь таблицу plane.
```SQL
CREATE TABLE IF NOT EXISTS public.plane
(
    id bigint NOT NULL DEFAULT 'nextval('plane_id_seq'::regclass)',
    mark character varying(50) COLLATE pg_catalog."default" NOT NULL,
    model character varying(50) COLLATE pg_catalog."default" NOT NULL,
    type character varying(50) COLLATE pg_catalog."default" NOT NULL,
    mileage integer NOT NULL,
    price integer NOT NULL,
    id_profile bigint NOT NULL,
    CONSTRAINT plane_fkey PRIMARY KEY (id),
    CONSTRAINT fk_plane_to_profile FOREIGN KEY (id_profile)
        REFERENCES public.profile (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
```

## Перейди в директорию, где расположен apache-tomcat, в папке conf найди файл server.xml и добавь внутрь GlobalNamingResources. Замени на своё имя пользователя и пароль, который у тебя есть в PostgreSQL. 
```xml
<Resource
            name="jdbc/postgresPlanes"
            auth="Container"
            type="javax.sql.DataSource"
            driverClassName="org.postgresql.Driver"
            url="jdbc:postgresql://localhost:5432/plane_catalog_list"
            username="YOUR_USERNAME"
            password="YOUR_PASSWORD"
            maxTotal="20"
            maxIdle="10"
            maxWaitMillis="10000"/>
```
## Затем в папке conf найди файл context.xml и добавь внутрь Context.
```xml
<ResourceLink name="jdbc/postgresPlanes"
                  global="jdbc/postgresPlanes"
                  auth="Container"
                  type="javax.sql.DataSource" />
```
