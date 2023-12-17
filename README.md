# Book Exchange Club
Online platform to support buy books and exchange books among friends and strangers. Has provision of Book shop as well. 

## Build Status

[![Build Status](https://travis-ci.com/shashank136/BookExchangeClub.svg?branch=master)](https://travis-ci.com/shashank136/BookExchangeClub)
[![docker build](https://img.shields.io/docker/cloud/build/shashank136/book-exchange-club)](https://cloud.docker.com/u/shashank136/repository/docker/shashank136/book-exchange-club)
![GitHub commit activity](https://img.shields.io/github/commit-activity/m/shashank136/BookExchangeClub?style=flat-square)
![License](https://img.shields.io/apm/l/vim-mode)

# Motivation

* My motivation for starting this project was to explore and learn Spring framework by doing an interesting project. 
* So, I decided to create a online platform where people can buy book for themselves and share/exchange the books among each other.

## Tech/framework used

* Java
* Spring Framework 5
* Spring boot 2.3.3
* RESTful web service
* Spring MVC
* JUnit 5
* Messaging Queue (Apache Active MQ, Apache Kafka)
* GitHub (source control)
* Mailtrap (for mail sending service)
* Travis CI
* Docker
* Postgre SQL

## Features

Following are the features of this project:
* User(customer/sellers) can register
* online cart
* customer can buy products
* view past orders and cart
* MVC Architecture
* exchange book among friends
* participate in a book exchange cycle to send and receive book from anonymous users on the platform.

### How is this project different?

This project was start in order to explore and learn Spring Framework 5 in a fun way (creative side project).

I've implemented following features of Spring Framework 5:

* Spring Core
* Spring java configuration
* Spring MVC
* [Spring Email](https://github.com/shashank136/BookExchangeClub-EmailApplication)
* Spring Data JPA
* Command Object
* Spring Security
* Aspect Oriented Programming
* JMS support 
* Project Lombok
* Processing JSON
* Building Docker Image

Messaging service implemented between services in a same application and between different service in different applications.

## How to get started?



## API Reference
The REST API details:
###### for Users
* Get the list of Users:
`GET /users`
* Get a specific user: 
`GET /users/{id}`
* Add a new user: 
`POST /users/new`
    > Request Body
    ```yml
    {
          "username": "dummyUdser",
          "password": "Password",
          "role": "USER",
          "customer": {
                 "firstName": "DummyFirstName",
                 "lastName": "DummyLastName",
                 "email": "dummyEmailid@gmail.com",
                 "phoneNumber": "9999999999",
                 "address": {
                     "line1": "dummy first line",
                     "line2": "dummy second line",
                     "city": "dummy city",
                     "state": "dummy state",
                     "zipcode": "000000"
                    }       
          } 
    }
    ```

* Edit user details:
`PUT /users/edit/{id}`
    > Request body same as 'add new user'
* Delete a user: 
`DELETE /users/delete/{id}`
* Get list of items specific user's cart:
`GET /users/{id}/cart`
* Add a item to user's cart:
`POST /users/{id}/addToCart?product={product_id}`
* User buy a product:
`POST /users/{id}/buy?product={productId}&quantity={quantity}`

###### for Customers
* Get list of Customer: 
`GET /customers/`
* Get a Customer:
`GET /customers/{id}`
* Add a new customer:
`POST /customers/new`
    > Request Body
    ```yml
    {
        "firstName": "demofirstName",
        "lastName": "demoLastName",
        "email": "demoEmail@gmail.com",
        "phoneNumber": "999999999"
    }
    ```
* Edit a Customer:
`PUT /customers/edit/{id}`
    > Request Body same as "Add new customer"
* Delete a Customer:
`DELETE /customers/delete/{id}`
* Get Order of a customer:
`GET /customers/{id}/orders`
###### for Products
* Get list of products: 
`GET /products/`
* Get a product details: 
`GET /products/{id}`
* Add a new product: 
`POST /products/new`
    > Request Body
    ```yml
    {
        name: "demoProductName"
        description: "demoDescription"
        imgUrl: "demoURL"
        quantity: "quantity"
    }
    ```
* Edit a product details: 
`PUT /products/edit/{id}`
    > Request Body same as "Add new product"
* Delete a product: 
`DELETE /products/delete/{id}`
## How to use?

## UML diagrams

Following are the entities in the project:
* User
* Customer
* Product
* Cart
* Cart Details
* Order
* Order Details

![UML Diagram](https://github.com/shashank136/BookExchangeClub/blob/master/resources/Database%20ER%20diagram.png)


