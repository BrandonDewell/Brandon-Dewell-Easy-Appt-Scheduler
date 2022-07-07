The Title of the application is Easy Appointment Scheduler.
  
The application's purpose is to provide record keeping of customers, their information, and their appointments.  Appointments are scheduled with these customers by the users.  Users make login attempts and those are audited.
The login screen information is capable of being displayed in both the French and English languages.  Customers and appointments can be added, updated, and deleted.  Appointments are able to be filtered to show the appointments for the current week,
current month, or all appointments.  Reports are generated with specific information in a graphical user interface.  Pop up alert windows provide feedback if the user has not entered all the required information correctly.

The author of the application is Brandon Dewell.  His contact information is bdewel2@wgu.edu.  The application version is 1.0, created on July 7th, 2022.

The IDE version number is IntelliJ IDEA 2021.3.2 (Community Edition), the JDK used is Java 17.0.2, the JavaFX version used is JavaFX-SDK-17.0.2.

Launch the application and login with the username test and password test, or username admin and password admin.  The login screen window and pop-up alerts are capable of being displayed in both the French and English languages,
depending on the setting of the user's computer.  All login attempts are saved to the file login_activity.txt in the root folder of the application.  Upon a valid username and password entry, a pop-up window loads letting the user know if
there are or are not any upcoming appointments scheduled in the next 15 minutes.  Next, the Main Menu loads and you can click the Add, Update, or Delete button associated with either the Customer Record table on top or the Appointment table on the
bottom.  The top Add button loads another window that allows a user to input customer information and the top Update button loads the same window, but with the saved customer information pre-loaded.  The bottom Add button loads another window that
allows a user to input appointment information and the bottom Update button loads the same window, but with the saved appointment information pre-loaded.  The top Delete button will delete a customer record and any appointments for that customer.
The bottom Delete button only deletes the appointment that was selected.  Pop-up messages throughout the program do a variety of things such as: confirm the choices you are about to make, ensure a selection is made before doing an update and delete,
ensure correct values are being entered in text fields, remind you to make a selection in drop-down boxes and the date picker calendar, and prevent saving customers or appointments with information that has not been filled out.  The Appointments
table can be filtered to show the current Week's appointments (with the first day of the week being Sunday), the current Month's appointments, and All appointments using the radio buttons.  The Reports button loads a different window with three sections
for reporting of frequently queried data.  To close the application, a user can click the X in the top right corner of any window, or click the Exit button in the bottom right corner of the Main Menu.

The Reports window has three sections.  The bottom section is used to list all the Contacts and their associated email addresses for quick lookup in the case that emergency contact is needed for a customer or for the case of potential clients for the
business to advertise to.

The MySQL Connector driver version number is mysql-connector-java-8.0.25.