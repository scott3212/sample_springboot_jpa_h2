# sample_springboot_jpa_h2

Hi! This is a simple description of how to use the endpoints.

## Sample URL
I have deployed this sample application to my server. Please check the swagger doc here:
http://huizhenwu.ca/bitbuytest/swagger-ui.html
**Note: "http" only, "https" is not yet configured for this sample**

## Sample Test Steps
1. Register User - POST /api/register
This endpoint will create a new user. It will verify if the username already exists. If not exist, create a new user; otherwise, return a 400 to indicate the user already exists. The return values include a JWT token (usually when users successfully registered we want to log them in automatically) and it can be used for secure endpoints.
2. Login User - POST /api/login
This endpoint will log a user in. It will check if the username & password pair are correct. The response is the same as the /api/register endpoint.
3. Get User Information - GET /api/users/{uuid}
This is a secure endpoint. A valid JWT token must be generated from one of the two endpoints above and passed into the header:
![][img/addBearerToken.jpg]
and only authorized users with a valid JWT token can access this endpoint.
4. Update User Information - POST /api/users/{uuid}
This is also a secure endpoint. It's used for updating a user's information.
