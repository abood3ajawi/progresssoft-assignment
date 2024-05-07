# FX Deals Data Warehouse Documentation

## Overview

This document provides an overview of the FX Deals Data Warehouse system developed by the Scrum team for Bloomberg. The system is designed to accept FX deals details and persist them into a database for analysis.

## Table of Contents

1. [Introduction](#introduction)
2. [System Architecture](#system-architecture)
3. [Database Schema](#database-schema)
4. [Request Logic](#request-logic)
5. [Deployment](#deployment)
6. [Error Handling](#error-handling)
7. [Logging](#logging)
8. [Unit Testing](#unit-testing)
9. [Documentation Repository](#documentation-repository)
10. [Makefile](#makefile)

## Introduction

The FX Deals Data Warehouse system is developed to cater to the specific requirements of Bloomberg for analyzing FX deals. It accepts deal details through a standardized request format, validates the data, ensures no duplicate entries, and persists the data into a database.

## appllication strucure 
my-spring-project/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── myproject/
│   │   │               ├── controller/
│   │   │               │   └── FXDealsController.java
│   │   │               ├── model/
│   │   │               │   └── FXDeals.java
│   │   │               ├── repository/
│   │   │               │   └── FXDealsRepository.java
│   │   │               └── service/
│   │   │               │   └── FXDealsService.java
│   │   │               └── validator/
│   │   │                   └── CheckingFXDealsFields.java
                      
                        


## Database Schema

The database schema consists of the following tables:

- **Deals**: Stores details of FX deals, including Deal Unique Id, From Currency ISO Code, To Currency ISO Code, Deal Timestamp, and Deal Amount.
as following : 
CREATE TABLE fx_deals (
                          deal_id VARCHAR(50) PRIMARY KEY,
                          from_currency_iso CHAR(3) NOT NULL,
                          to_currency_iso CHAR(3) NOT NULL,
                          deal_timestamp TIMESTAMP NOT NULL,
                          deal_amount DECIMAL(20, 5) NOT NULL,

                          CONSTRAINT chk_from_currency_iso CHECK (LENGTH(from_currency_iso) = 3),
                          CONSTRAINT chk_to_currency_iso CHECK (LENGTH(to_currency_iso) = 3),
                          CONSTRAINT chk_deal_amount CHECK (deal_amount > 0)
);

##Validation:
CheckingFXDealsFields:
1- Deal Amount: Ensures that the deal amount is provided and is a non-negative number.
2- Source Currency ISO: Verifies that the source currency ISO code is a three-letter alphabetic code.
3- Target Currency ISO: Validates that the target currency ISO code is also a three-letter alphabetic code 
4 - checkIfCurrencyCodeExist
"SELECT COUNT(*) AS ExistenceCount\n" +
                "FROM (\n" +
                "    SELECT CurrencyCode\n" +
                "    FROM iso_currency\n" +
                "    WHERE CurrencyCode = ?1\n" +
                "    UNION ALL\n" +
                "    SELECT CurrencyCode\n" +
                "    FROM iso_currency\n" +
                "    WHERE CurrencyCode = ?2\n" +
                ") AS temp;\n", nativeQuery = true)
also to check if the input currany code exist in stander iso currany list(almost all currany i have add with iso_currency in DB ) 

## Deployment

1- BUild the hole project with maven, then create 2 docker file, 1 for APP and the athoer one for mysql, and runnnig it with docker-compose.

## Unit Testing
traid to achive most casese.
