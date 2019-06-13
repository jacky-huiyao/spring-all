create table user(
  id IDENTITY not null ,
  name varchar(50) not null,
  salary DECIMAL(20, 2),
  status varchar(20) not null,
  birth_date DATE,
  last_login_time TIMESTAMP not null
);

