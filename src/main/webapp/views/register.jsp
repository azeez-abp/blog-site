<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/static/css/register.css">

</head>
<body>
    
    <div class="register-wrapper"> 
        <div class="header">Create an Account</div>
        <div class="body">
        <form action="register" method="post">
            <input type="text" name="username" placeholder="Username" required /><br />
            <input type="email" name="email" placeholder="Email" required /><br />
            <input type="password" name="password" placeholder="Password" required /><br />
            <button type="submit">Register</button>
        <p style="color:red">${error}</p>
    </form>
        </div>
        <div class="footer"></div>

    </div>
    

    
</body>
</html>