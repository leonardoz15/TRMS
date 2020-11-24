create type auth_level as enum ('EMPLOYEE', 'SUPERVISOR', 'DEPT_HEAD', 'BENCO', 'BENCO_SUP', 'ADMIN');
create type event_type as enum ('UNI_COURSE', 'SEMINAR', 'CERT_PREP_CLASS',	'CERTIFICATION','TECHNICAL_TRAINING', 'OTHER');
create type approval_status as enum ('DS_PENDING', 'DH_PENDING', 'BC_PENDING', 'INFO_NEEDED', 'DENIED',	'APPROVED');


CREATE TABLE "employee" (
  "employee_id" serial,
  "first_name" text,
  "last_name" text,
  "phone_number" numeric(10,0),
  "address" text,
  "reports_to" int,
  PRIMARY KEY ("employee_id")
);

CREATE TABLE "users" (
  "user_id" serial,
  "employee_id" int,
  "username" varchar(25),
  "password" varchar(25),
  "auth_level" auth_level,
  PRIMARY KEY ("user_id")
);

CREATE TABLE "request" (
  "request_id" serial,
  "user_id" int,
  "cost" numeric(5,2),
  "date" date,
  "time" time,
  "location" text,
  "description" text,
  "grading_format" varchar(100),
  "event_type" event_type,
  "isUrgent" boolean,
  "projected_amount" numeric(4,2),
  "approval_status" approval_status,
  PRIMARY KEY ("request_id")
);

CREATE TABLE "info_req" (
  "info_id" serial,
  "request_id" int,
  "employee_id" int,
  "description" text,
  PRIMARY KEY ("info_id")
);

CREATE TABLE "waitlist" (
  "priority" int,
  "user_id" int,
  "request_id" int,
  PRIMARY KEY ("priority", "user_id")
);

CREATE TABLE "attachment" (
  "attach_id" serial,
  "request_id" int,
  "file_type" varchar(30),
  "file" bytea,
  PRIMARY KEY ("attach_id")
);

alter table "users" add constraint FK_employee_id
foreign key (employee_id) references employee (employee_id) on delete cascade on update cascade;

alter table "request" add constraint FK_user_id
foreign key (user_id) references "users" (user_id) on delete cascade on update cascade;

alter table "waitlist" add constraint FK_request_id
foreign key (request_id) references "request" (request_id) on delete cascade on update cascade;

alter table "attachment" add constraint FK_request_id
foreign key (request_id) references "request" (request_id) on delete cascade on update cascade;

alter table "info_req" add constraint FK_request_id
foreign key (request_id) references "request" (request_id) on delete cascade on update cascade;

alter table "info_req" add constraint FK_employee_id
foreign key (employee_id) references "employee" (employee_id) on delete cascade on update cascade;


--------------------------------------------------------------------------------------------------

select * from employee;