

## Framework and language used :

Framework and  technologies used : 
   
    SpringBoot (spring-boot-starter-web)
    Swagger
    Lombok
    Spring Validations
    Spring Data JPA
    MySQL JDBC driver.
    Sun.Email


language used :      Java

## Data flow
Controller : 

UserController:

    User Sign Up:
    Method Name: userSignUp
    HTTP Method: POST
    Endpoint: /user/signup


    User Sign In:
    Method Name: userSignIn
    HTTP Method: POST
    Endpoint: /user/signIn/{email}/{password}


    User Sign Out:
    Method Name: userSignOut
    HTTP Method: DELETE
    Endpoint: /user/signOut


    Update User:
    Method Name: updateUser
    HTTP Method: PUT
    Endpoint: /user/changes


PostController:

    Create Insta Post:
    Method Name: createInstaPost
    HTTP Method: POST
    Endpoint: /InstaPost


    Get Post By Id:
    Method Name: getPostById
    HTTP Method: GET
    Endpoint: /post/id/{postId}/


Services : 

AuthenticationService:

    createToken:
    Method Name: createToken
    Return Type: void

    authenticate:
    Method Name: authenticate
    Return Type: boolean

    deleteToken:
    Method Name: deleteToken
    Return Type: void


PostService:

    createInstaPost:
    Method Name: createInstaPost
    Return Type: String

    getPostById:
    Method Name: getPostById
    Return Type: Post


UserService:

    userSignUp:
    Method Name: userSignUp
    Return Type: String

    userSignIn:
    Method Name: userSignIn
    Return Type: String

    userSignOut:
    Method Name: userSignOut
    Return Type: String

    updateUser:
    Method Name: updateUser
    Return Type: String

EmailHandler,PasswordEncryptor.    

           
Repository : 

    interface IAuthenticationRepo extends JpaRepository<AuthenticationToken,Long>
    interface IPostRepo extends JpaRepository<Post,Integer>
    interface IUserRepo extends JpaRepository<User,Integer>
   
    


DataBase Design :

    AuthenticationToken:
    (tokenId (Type: Long)
    tokenValue (Type: String)
    tokenCreationDateTime (Type: LocalDateTime)
    user (Type: User)
    )

 
    Post:
    (
    postId (Type: Integer)
    postData (Type: String)
    postCaption (Type: String)
    postLocation (Type: String)
    postType (Type: PostType enum)
    postCreatedTimeStamp (Type: LocalDateTime)
    postOwner (Type: User)
    )
    

    User:
    (
    userId (Type: Integer)
    userName (Type: String)
    userHandle (Type: String)
    userBio (Type: String)
    userEmail (Type: String)
    userPassword (Type: String)
    gender (Type: Gender enum)
    accountType (Type: AccountType enum)
    blueTick (Type: boolean)
    )
   


## Data Structure
Data Structure used : List    

    List;
    
   

# Project summary

1. Entity Classes:
2. 
 User Class:

       Represents a user in the system with various attributes like name, handle, bio, email, password, gender, account type, and a boolean indicating whether the user has a blue tick (verified status).
   
 Post Class:

    Represents a post made by a user, containing data such as the post content, caption, location, post type, timestamp, and a reference to the user who owns the post.
    
 AuthenticationToken Class:

    Represents an authentication token associated with a user. It includes a unique token ID, token value, creation timestamp, and a reference to the associated user.

Controllers:

UserController:

    Handles user-related operations such as sign-up, sign-in, sign-out, and user profile updates.

PostController:

    Manages post-related operations, including creating a new post and retrieving a post by its ID.

3. Services:

UserService:

    Provides functionality for user-related operations, such as user sign-up, sign-in, sign-out, and profile updates.

PostService:

    Manages operations related to posts, including creating a new post and retrieving posts.
AuthenticationService:

    Handles the creation, authentication, and deletion of authentication tokens.


4. Other Components:

Enums:

    Gender: Represents the gender of a user.
    AccountType: Represents the type of user account.
    PostType: Represents the type or category of a post.

Utilities:

    Contains utility classes for handling email and password encryption.

5. Persistence:

       Uses JPA annotations for entity mapping.
       Repositories (IUserRepo, IPostRepo, IAuthenticationRepo) are expected to interact with the database.

6. Functionality:

       Users can sign up, sign in (with email verification through tokens), and sign out.
       Users can create posts with different types/categories.
       User profiles can be updated.
       Token-based authentication is used for user sessions.

7. Considerations:

       Passwords are encrypted using a utility class (PasswordEncryptor).
       Some fields have validation constraints, such as the uniqueness of email addresses.


