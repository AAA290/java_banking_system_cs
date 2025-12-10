# FeiMaBank

#### Project Name
FeiMa Bank Management System

#### Architecture Pattern
Client-Server (C/S) Architecture

#### Technologies Used
* **Object-Oriented Programming (OOP)**
* **Network Programming** (Socket/TCP)
* **I/O Streams**
* **Database Technology** (MySQL)
* **Multithreading**
* **GUI Technology** (Swing/JavaFX)

#### Detailed Description
The project consists primarily of two parts: the Server and the Client.

**1. Server-Side**
The FeiMa Bank Server is deployed on a **Huawei Cloud Elastic Cloud Server (ECS)**.
* **Database Connection**: Upon startup, the server automatically connects to the **MySQL database** (hosted on the same cloud server) to store and retrieve banking data.
* **Concurrency Handling**: It remains in a listening state to accept client connections. Utilizing **Multithreading**, the server spawns a dedicated thread for each client request, allowing multiple users to log in and operate simultaneously.
* **Data Safety**: **Synchronization Locks** are implemented to ensure thread safety during database operations, guaranteeing the security of customer information and assets.

**2. Client-Side**
The FeiMa Bank Client runs on the user's local computer.
* **Indirect Access**: The client cannot access the MySQL database directly; all data operations are processed indirectly through the Server.
* **User Interface**: Built with **GUI technology**, the client provides a clear and user-friendly graphical interface, offering various services for both customers and administrators.
