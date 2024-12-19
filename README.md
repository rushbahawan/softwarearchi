# softwarearchitecture
Depot Management System
Overview
The Depot Management System is a Java-based application designed to manage a parcel depot. It allows depot workers to process customers in a queue and manage their parcels efficiently. The application demonstrates:

Queue management for customers.
Parcel processing with details such as weight and parcel ID.
Dynamic updates in the GUI for customer and parcel lists.
Logging of processed data using the Singleton design pattern.
Features
Load Customers and Parcels:
Load data from Custs.csv and Parcels.csv.
Process Customers:
Remove a customer from the queue and mark their parcel as processed.
Log details of the processed parcel in ProcessedData.txt.
Add New Customers and Parcels:
Dynamically add customers and parcels via the GUI.
Sort Parcels:
Sort parcels by weight for efficient processing.
Save Data:
Save updated customer and parcel lists back to their respective CSV files.
GUI:
Interactive GUI displays:
Customer queue.
Parcel list.
Current parcel being processed.

Project Structure
bash
Copy code
src/
├── model/
│   ├── log.java           # Singleton class for logging events.
│   ├── queueofcustomer.java # Manages customer queue using a Queue data structure.
│   ├── parcelmapp.java    # Manages parcels using a Map data structure.
├── view/
│   ├── gui.java           # Handles the graphical user interface (MVC view).
├── controller/
│   ├── manager.java       # Launches the application (MVC controller).
data/
├── Custs.csv              # Sample customer data.
├── Parcels.csv            # Sample parcel data.
├── ProcessedData.txt      # Log file for processed parcels.
