
<%@page language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="style.css" />
    <title>Home Page</title>
</head>
<body>
<header>
    <h1>Welcome,${user.getUser()}</h1>
    <a href="/">
        <button class="logout-btn">Logout</button>
    </a>

</header>

<main class="grid-container">
    <div class="card">
        <h3>Profile</h3>
        <p>View and edit your personal information.
            <br>
            username : ${user.getUser()}
            <br>
            password : ${user.getPass()}
        </p>
    </div>
    <div class="card">
        <h3>Messages</h3>
        <p>Check your latest messages and notifications.</p>
    </div>
    <div class="card">
        <h3>Settings</h3>
        <p>Customize your preferences and account settings.</p>
    </div>
    <div class="card">
        <h3>Analytics</h3>
        <p>Track your activity and performance metrics.</p>
    </div>
</main>

<footer>
    &copy; 2025 YourApp. All rights reserved.
</footer>
</body>
</html>
