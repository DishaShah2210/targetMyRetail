# targetMyRetail

#Ddl Statements
create table myretail.Brand(brand_id int not null primary key AUTO_INCREMENT , brand_name varchar(20) not null ,facet_id  int) ;

create table myretail.price( price_id int not null primary key AUTO_INCREMENT, value decimal(20,2) not null , currency varchar(20) not null);

create table myretail.Product(id  int not null primary key AUTO_INCREMENT,name varchar(50) not null ,  price_id int , product_id BIGINT(20),foreign key (price_id) references price(price_id),brand_id int ,
foreign key (brand_id) references brand(brand_id));

#DML statements

insert into myretail.brand (brand_name,facet_id)values("nike",1);

insert into myretail.price (value,currency)values(1400,"rupee");

insert into myretail.product (name,price_id,brand_id,product_id)values("The Big Lebowski (Blu-ray)",1,1,13860428);



# java 8 
# spring boot
# mysql need to be installed
# maven

# import as spring boot maven project

#Run as spring boot app

##three apis :-

#To get product details for a product:-
METHOD TYPE :-GET

product Id is 13860428 that we need send in URL 

http://localhost:8080/products/13860428

Response :-
{
    "id": 13860428,
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": 1400,
        "currency_code": "rupee"
    }
}

Response:-

if successful(http status code :200):-

Response will be Success

if not successfull:-

(http status code :500)

then Internal Server Error with the message saying "product not found" if product is not found in the repository


#To get price details for a product:-

METHOD TYPE :-GET

http://localhost:8080/product/13860428/price

Response:-
 "current_price": {
        "value": 1400,
        "currency_code": "rupee"
    }

Response:-

if successful(http status code :200):-

Response will be Success

if not successfull:-

(http status code :500)

then Internal Server Error with the message saying "product not found" if product is not found in the repository


#To update product price for a product:-

METHOD TYPE :-POST

http://localhost:8080/products/13860428

POST BODY:-
{
    "id": 13860428,
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": 14500,
        "currency_code": "rupee"
    }
}

Response:-

if successful (http status code :200):-

Response will be Success

if not successfull (http status code :500):-

then Internal Server Error with the message saying "price is empty" if price in response body is null



