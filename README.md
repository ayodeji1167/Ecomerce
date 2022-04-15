# ShoppingCart - An amazing website 


I developed an  **Ecommerce REST API Application using Spring Boot** with multiple
modules


### Functionalities:

1. User Registeration.

2. **CRUD Operations for Category, Company, and Product**

* Users can add, delete Items to their cart
* Users can reduce the quantity of a particular item in the cart 


3. **Spring Security**

* Method Level Security
* Any request can hit the registration endpoint
* Different endpoint for USER and ADMIN registration
* Only ADMIN can edit COMPANY, PRODUCT, CATEGORY, AND USER.

4. **Add To Cart**

* User can add any quantity of a particular product to a cart (CART ITEM)
* User can delete or reduce the quantity of the product in the cart


5 **Check Out**
* After adding products to the cart the user can checkout.
* After Checking Out, all checked-out products will be saved to DB as (ORDERED PRODUCTS)
* Confirming Shipping and Billing Address.



6. Configure your database configuration in **application.properties**.

   * Database properties:

 
                      spring.jpa.database-platform=YOUR DB DIALECT
                      spring.datasource.driver-class-name=YOUR DB DRIVER CLASS NAME


                      spring.datasource.username= YOUR DB USERNAME
                      spring.datasource.password= YOUR DB PASSWORD
                      spring.jpa.hibernate.ddl-auto = create

### Tools and Technologies:

* **Technology** :  Java, Spring - Boot.
* **Application Servicer**: Apache Tomcat Server.
* **Database** : My Sql
* Contributors are most welcome.

     

