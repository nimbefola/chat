
--CREATE SEQUENCE hibernate_sequence START 1 INCREMENT 1;
DROP TABLE IF EXISTS  chat_message;
create TABLE chat_message (
    "id" VARCHAR(255) NOT NULL,
    "created" TIMESTAMP NOT NULL,
    "updated" TIMESTAMP NOT NULL,
    "version" INT8 DEFAULT 0,
    "sender_id" VARCHAR(50) NOT NULL,
    "recipient_id" VARCHAR(50) NOT NULL,
    "content" VARCHAR(50) NOT NULL,
    "status" VARCHAR(50) NOT NULL,
    "sender_username" VARCHAR(50) NOT NULL,
    "recipient_username" VARCHAR(50) NOT NULL,
    primary key (id)
);
