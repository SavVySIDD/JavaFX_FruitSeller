# JavaFX Fruit Selling Application

This JavaFX Fruit Selling Application is a user-friendly interface for purchasing fruits. Users can log in, browse a variety of fruits, add selected items to their cart, and proceed to checkout. The app manages user details, retains cart contents, and displays a total checkout amount.

## Features

1. **User Authentication**  
   - Users must either log in or create an account to access the application.
   - Account details are saved, allowing users to log in without re-creating an account each time.

2. **Fruit Selection and Cart Management**  
   - Users can view a list of available fruits, with names, prices, and images displayed.
   - Selected fruits can be added to the cart, which retains items until checkout or logout.

3. **Persistent Cart**  
   - Cart contents are saved for each user, so users can return to their cart details across sessions.

4. **Dashboard Navigation**  
   - A persistent dashboard visible across pages provides options for navigating between the home and cart pages.
   - A back button on the cart page returns users to the home page.

5. **Checkout Summary**  
   - The checkout page displays a summary of items and the total cost of the selected fruits.

## Technology Used

- **JavaFX** - For creating the graphical user interface.
- **Java** - Core language for managing business logic and UI handling.
- **Exception Handling** - Ensures valid user input, such as non-empty fields for account creation.

## Key Components

### 1. User Authentication

- Users provide a username and password to log in or register.
- If fields are empty, an error dialog notifies users, ensuring required fields are completed.
- The `UserManager` class manages user accounts and persists login details for future sessions.

### 2. Cart and Checkout

- Users can browse a list of fruits and add items to their cart.
- The cart can be accessed from any page using the dashboard, where users can review items before checkout.
- The checkout page summarizes items in the cart and calculates the total cost.

### 3. Dashboard Navigation

- The dashboard is visible across pages, allowing seamless navigation.
- Users can quickly switch between the home and cart pages and view their cart items or continue shopping.

## Screens and Workflow

1. **Login/Registration Screen**:  
   Users enter their credentials to log in or create a new account.

2. **Home Page**:  
   Displays available fruits with names, prices, and images. Users can select items to add to the cart.

3. **Cart Page**:  
   Accessible from the dashboard, it displays all added fruits. A back button allows users to return to the home page.

4. **Checkout Page**:  
   Displays the final order summary, showing all selected fruits and the total amount.

---

This application combines intuitive UI and smooth navigation to create an engaging shopping experience for fruit lovers.
