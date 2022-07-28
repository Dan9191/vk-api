create table hibernate_sequence (
    next_val bigint
) engine=MyISAM;

insert into hibernate_sequence values ( 1 );
insert into hibernate_sequence values ( 1 );

create table if not exists search_result (
    id bigint primary key,
    param varchar not null,
    result varchar not null,
    tech_date_response timestamp default now()
) engine=MyISAM;;

comment on table search_result is 'Журнал запросов';
comment on column search_result.id is 'Идентификатор';
comment on column search_result.param is 'Параметры запроса';
comment on column search_result.result is 'Результат';
comment on column search_result.tech_date_response is 'Время запроса';
