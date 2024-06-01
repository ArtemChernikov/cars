create table if not exists history_owner
(
    id      serial primary key,
    startAt timestamp with time zone not null,
    endAt   timestamp with time zone not null
);
