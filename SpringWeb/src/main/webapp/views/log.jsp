
<%@page language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="style.css" />
    <title>Login Form</title>
</head>
<body>
<div class="login-container">
    <h2>Login</h2>
    <form action="home">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="user" placeholder="Enter your username" required />
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="pass" placeholder="Enter your password" required />
        </div>
        <button type="submit" class="login-btn">Sign In</button>
    </form>
    <div class="footer-text">
        Don't have an account? <a href="#">Register</a>
    </div>
</div>
</body>
</html>