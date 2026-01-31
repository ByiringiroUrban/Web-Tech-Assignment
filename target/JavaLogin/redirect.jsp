<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Assignment 2 - Send Redirect</title>
    <style>
        body { font-family: sans-serif; padding: 2rem; max-width: 500px; margin: auto; }
        form { display: flex; flex-direction: column; gap: 1rem; }
        label { font-weight: bold; }
        input[type="text"] { padding: 0.5rem; font-size: 1rem; }
        button { padding: 0.6rem 1rem; background: #28a745; color: white; border: none; border-radius: 4px; cursor: pointer; }
        button:hover { background: #218838; }
        h1 { color: #333; }
    </style>
</head>
<body>
    <h1> Send Redirect</h1>
    <form action="redirect" method="post">
        <label for="query">Search query:</label>
        <input type="text" id="query" name="query" placeholder="Enter search term" required>

        <button type="submit">Fetch</button>
    </form>
    <p><a href="index.html">Back to Home</a></p>
</body>
</html>
