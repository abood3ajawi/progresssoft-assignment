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

## System Architecture

The system architecture follows a microservices-based approach, comprising the following components:

- **API Service**: Accepts incoming deal requests and handles validation.
- **Database Service**: Persists validated deals into the database.
- **Validation Service**: Responsible for validating the structure and content of incoming deal requests.

## Database Schema

The database schema consists of the following tables:

- **Deals**: Stores details of FX deals, including Deal Unique Id, From Currency ISO Code, To Currency ISO Code, Deal Timestamp, and Deal Amount.

## Request Logic

The system follows the following request logic:

1. Incoming requests must contain the following fields: Deal Unique Id, From Currency ISO Code, To Currency ISO Code, Deal Timestamp, and Deal Amount.
2. The system validates the structure and format of incoming requests, checking for missing fields and correct data types.
3. Duplicate requests are not imported into the system.
4. No rollback is allowed, ensuring that every imported row is saved in the database.

## Deployment

The system is deployed using Docker Compose, which includes containers for the API Service, Database Service, and Validation Service. A sample file is provided for testing purposes.

## Error Handling

Error handling is implemented throughout the system to ensure graceful degradation and informative error messages for users. Common error scenarios are documented and handled appropriately.

## Logging

Logging is implemented using a logging framework such as Log4j. Logs capture important system events, errors, and debugging information for troubleshooting purposes.

## Unit Testing

Unit testing is performed using JUnit and Mockito to ensure the reliability and correctness of each component. Test coverage is measured to maintain code quality.

## Documentation Repository

The documentation, along with the source code, is hosted on GitHub for easy access and collaboration among team members.

## Makefile

A Makefile is provided to streamline the process of building, testing, and running the application. It includes targets for compiling the code, running unit tests, and deploying the system using Docker Compose.

This documentation provides a comprehensive overview of the FX Deals Data Warehouse system, covering its architecture, request logic, deployment, error handling, logging, unit testing, and repository structure.
