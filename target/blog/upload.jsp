<%--
  Created by IntelliJ IDEA.
  User: 22968
  Date: 2019/11/21
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>

        .box{
            margin-top: 100px;
            width: 100px;
            height: 50px;
            border: none;
            border-radius: 5px;
            background-color: aquamarine;
            cursor: pointer;
            font-size: 20px;
            font-weight: bold;
        }
        p{
            font-size: 20px;
            font-weight: 800;
        }
    </style>
</head>
<body>
<form action="/upload" method="post" enctype="multipart/form-data">
    <div>
        <input type="file" name="filename" multiple="multiple">
    </div>
    <div>
        <input type="submit" value="上传" class="box">
    </div>

</form>
<p>上传文件最大为5MB</p>
<p>${msg}</p>
<p>${url}</p>

<div></div>
</body>
</html>
