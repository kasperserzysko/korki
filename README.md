
<h1 align="center">Private Lessons Service</h1>

<h4 align="center">A Backend REST application made for students and teachers, which allows private tutoring.</h4>


## Technologies
* SpringBoot 
* MySQL
* Spring security
* JWT
* Hibernate
* Spring validation
* Spring email
* Lombok

## Key Features (Some haven't been implemented yet)

  - User authentication and authorization via JWT,
  - account email verification,
  - email notifications,
  - CRUD operations on adverts,
  - messaging between users,
  - searching adverts by up to 7 parameters.
  - files uploading 

## How To Use

Clone this reposity, then change in:
  - web_app/backend/src/main/resources/application.properties for Your MySQL database,
  - web_app/email_service/src/main/resources/application.properties for Your email credentials.
  - web_app/backend/src/main/java/com/korki/backend/utills/FileService.java FOLDER_PATH for desired destination

