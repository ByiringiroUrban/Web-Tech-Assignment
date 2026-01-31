<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        body { font-family: sans-serif; padding: 2rem; max-width: 400px; margin: auto; }
        form { display: flex; flex-direction: column; gap: 1rem; }
        label { font-weight: bold; }
        input[type="text"], input[type="password"] { padding: 0.5rem; font-size: 1rem; }
        button { padding: 0.6rem 1rem; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
        button:hover { background: #0056b3; }
        h1 { color: #333; }
    </style>
</head>
<body>
    <h1>Login </h1>
    <form action="login" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required placeholder="Enter username">

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required placeholder="Enter password (8+ chars)">

        <button type="submit">Login</button>
    </form>
    <p><a href="index.html">Back to Home</a></p>
</body>
</html>
