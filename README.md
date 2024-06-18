# Summative Project

## This is a stock management system for a game store

### Description

This stock management system is intended for use by employees, and access is limited by a login feature.
The system displays data visualisations and provides easy access to stock levels.
Stock levels can be updated using csvs and txt reports can be generated. 

### Usage

To clone the repository, the following code can be used.

```bash
 git clone https://github.com/kropner22/GameStore.git
```
Upon loading the code, build and then run in Gradle.
Although this system was developed in IntelliJ, any IDE may be used. 

### Data Structure

The tables included in this project have the following structure...

Employee:
employee_id (PK Int)
usernamte (String)
password (String)

Customer:
customer_id (PK Int)
name (String)
postcode (String)

Console:
console_id (PK Int)
name (String)
stock (Int)
price (Float)
sale (Boolean)
sale_price (Float)
developer (String)
storage_gigabytes (Int)
hybrid (Boolean)

Game:
game_id (PK Int)
console_id (FK Int)
name (String)
stock (Int)
price (Float)
sale (Boolean)
sale_price (Float)
developer (String)
genre (String)

Order:
order_id (PK Int)
customer_id (FK Int)
employee_id (FK Int)
date (Date)
total (Float)

Order_Items:
order_item_id (PK Int)
game_id (FK Int)
console_id (FK Int)
order_id (FK Int)
quantity (Int)

### Inputs

CSVs to update stocks need three columns: name (String), stock (Int) and sale (Boolean).