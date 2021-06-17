<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 17.06.2021
  Time: 19:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8"%>
<html>
<head>

    <title>Title</title>
</head>
<body>
<form method="POST" action="uploadFile" enctype="multipart/form-data">
    File to upload: <input type="file" name="file"><br />

    <input type="submit" value="Upload">
    Press here to upload the file!
</form>
</body>
</html>
