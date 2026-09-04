# Drone Dispatch System

A console-based Java application for managing drone delivery orders. Users can create customer profiles, build parcels (boxes and envelopes), and process orders through a FIFO delivery queue.

## Features

- **Customer Management** – Create and update customer details with input validation (name, contact number, address)
- **Parcel Creation** – Build boxes with up to 5 items or envelopes with 4 feature types (tamper-evident, waterproof, confidential, standard)
- **Flight Classification** – Automatic priority-based categorization (Hazardous → Fragile → Standard) based on parcel contents
- **Order Management** – Add orders, deliver oldest order (FIFO), search by ID, and update existing orders
- **Clean Architecture** – Separation of concerns between UI logic (ConsoleIO, ParcelMenuHelper) and business logic (OrderManager)

## Tech Stack

Java, OOP (Inheritance, Encapsulation, Polymorphism)

## Project Structure

- Parcel (abstract) → Box, Envelope
- OrderManager handles order collection and FIFO delivery
- ConsoleIO handles all user input/output with validation

## How to Run

javac *.java
java DroneDispatchDriver

## Author

Yun Pei En
