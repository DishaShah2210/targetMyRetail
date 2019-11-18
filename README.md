# targetMyRetail

#Ddl Statements
create table myretail.Brand(brand_id int not null primary key AUTO_INCREMENT , brand_name varchar(20) not null ,facet_id  int) ;

create table myretail.price( price_id int not null primary key AUTO_INCREMENT, value decimal(20,2) not null , currency varchar(20) not null);

create table myretail.Product(id  int not null primary key AUTO_INCREMENT,name varchar(50) not null ,  price_id int , product_id BIGINT(20),foreign key (price_id) references price(price_id),brand_id int ,
foreign key (brand_id) references brand(brand_id));
