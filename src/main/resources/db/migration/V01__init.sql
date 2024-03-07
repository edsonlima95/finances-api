CREATE TABLE "users" (
  "id" bigserial PRIMARY KEY,
  "name" varchar(100) NOT NULL,
  "email" varchar(100) NOT NULL,
  "password" varchar(255) NOT NULL,
  "status" bool DEFAULT true
);

CREATE TABLE "wallets" (
  "id" bigserial PRIMARY KEY,
  "user_id" int8 NOT NULL,
  "title" varchar(100) NOT NULL,
  "description" varchar(200)
);

CREATE TABLE "categories" (
  "id" bigserial PRIMARY KEY NOT NULL,
  "user_id" int8 NOT NULL,
  "title" varchar(100) NOT NULL,
  "description" varchar(200)
);

CREATE TABLE "invoices" (
  "id" bigserial PRIMARY KEY,
  "user_id" int8 NOT NULL,
  "category_id" int8 NOT NULL,
  "wallet_id" int8 NOT NULL,
  "type" varchar(50) NOT NULL,
  "description" varchar(200),
  "start_date" date NOT NULL,
  "installment" varchar(50) NOT NULL
);

CREATE TABLE "installments" (
  "id" bigserial PRIMARY KEY NOT NULL,
  "invoice_id" int8 NOT NULL,
  "value" decimal(10,2) NOT NULL,
  "quantity" int NOT NULL,
  "status" bool NOT NULL,
  "date" date NOT NULL
);

ALTER TABLE "categories" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "wallets" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "installments" ADD FOREIGN KEY ("invoice_id") REFERENCES "invoices" ("id");

ALTER TABLE "invoices" ADD FOREIGN KEY ("wallet_id") REFERENCES "wallets" ("id");

ALTER TABLE "invoices" ADD FOREIGN KEY ("category_id") REFERENCES "categories" ("id");

ALTER TABLE "invoices" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");