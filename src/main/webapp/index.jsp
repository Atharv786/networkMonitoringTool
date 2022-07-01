<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="loginPage.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>

</head>
<body>

<div class="nav">
    <h2 style="font-family:'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;color: white;"></h2><script>

        </script>
</div>


<form>
    <div class="container1";>
        <h1 style="text-shadow: #f1f1f1;padding-top: 0%;margin-top: -5px;">Sign In</h1>
    </div>


    <div class="container">
        <i class="fa fa-user icon" style="margin-left: 4px;"></i>
        <label for="username"><b>Username</b></label>

        <input id="username" type="text" placeholder="Enter Username" name="username" required>

        <i class="fa fa-key icon" style="margin-left: 4px;"></i>
        <label for="password"><b>Password</b></label>

        <input type="password" placeholder="Enter Password" name="password"  id="password" required>
        <input type="checkbox" class="showPassword">Show Password

        <input type="button" value="Login" class="loginButton" id="submit" style="margin-top: 20px;">
    </div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="homePageJS.js"></script>
    <script src="loginJS.js"></script>

    <script>
        $(document).ready(function () {
            functions.buttonBind();
        });
    </script>

</form>

</body>
</html>
