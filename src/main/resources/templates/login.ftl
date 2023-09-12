<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <script>
        // JavaScript函数，用于刷新验证码图片
        function refreshCaptchaImage() {
            var captchaImage = document.getElementById('captchaImage');
            captchaImage.src = '/api/user/captcha?' + new Date().getTime(); // 添加时间戳以确保获取新的图片
        }
    </script>
</head>
<body>
<h1>Login</h1>
<form action="/login" method="post">
    <div>
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>
    </div>
    <div>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
    </div>
    <div>
        <label for="captchaCode">Captcha:</label>
        <input type="text" id="captchaCode" name="captchaCode" required>
        <img id="captchaImage" src="/api/user/captcha" alt="Captcha Image">
        <a href="javascript:void(0);" onclick="refreshCaptchaImage();">Refresh Captcha</a> <!-- 刷新链接 -->

    </div>
    <div>
        <input type="submit" value="Login">
    </div>
</form>
</body>
</html>