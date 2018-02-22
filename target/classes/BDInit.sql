create table manufactures(
  id binary(16) not null unique,
  manufacture_name varchar(40) not null,
  primary key(id)
);

create table products(
  id binary(16) not null unique,
  manufacturer_id binary(16) not null,
  foreign key(manufacturer_id) references manufactures(id),
  product_name varchar(40) not null,
  price decimal(19,4) not null,
  primary key(id)
);