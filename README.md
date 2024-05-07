# FX Deals Data Warehouse Documentation

## Overview

This document provides an overview of the FX Deals Data Warehouse system developed by the Scrum team for Bloomberg. The system is designed to accept FX deals details and persist them into a database for analysis.

## Table of Contents

1. [Introduction](#introduction)
2. [System Architecture](#system-architecture)
3. [Database Schema](#database-schema)
4. [Validation](#Validation)
5. [Deployment](#deployment)



## Introduction

The FX Deals Data Warehouse system is developed to cater to the specific requirements of Bloomberg for analyzing FX deals. It accepts deal details through a standardized request format, validates the data, ensures no duplicate entries, and persists the data into a database.



## Database Schema

The database schema consists of the following tables:

- **Deals**: Stores details of FX deals, including Deal Unique Id, From Currency ISO Code, To Currency ISO Code, Deal Timestamp, and Deal Amount.

```sql
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
```


## Validation

**CheckingFXDealsFields:**

- **Deal Amount:** Ensures that the deal amount is provided and is a non-negative number.
- **Source Currency ISO:** Verifies that the source currency ISO code is a three-letter alphabetic code.
- **Target Currency ISO:** Validates that the target currency ISO code is also a three-letter alphabetic code.
- **checkIfCurrencyCodeExist:** SQL query to check if the input currency code exists in the standard ISO currency list(not all currancy i have add in the iso_currency table).

```sql
SELECT COUNT(*) AS ExistenceCount
FROM (
    SELECT CurrencyCode
    FROM iso_currency
    WHERE CurrencyCode = ?1
    UNION ALL
    SELECT CurrencyCode
    FROM iso_currency
    WHERE CurrencyCode = ?2
) AS temp;
```

## Deployment

1. **Build the whole project with Maven**: Build the entire project using Maven.
2. **Create Dockerfiles**: Create two Dockerfiles, one for the application and the other for MySQL.
3. **Docker Compose**: Use Docker Compose to run both containers simultaneously.


## Note

Please put the jar file under dockers directory.
