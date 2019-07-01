<%@ page language="java" contentType="text/html; charset=GBK"%>
<html>
<head>
    <title>${fileInfo.originalname}</title>
<style type="text/css">
    body{
        margin:0;
        padding:0;
        text-align: center;
    }
    img{
        max-width: 90%;
    }

</style>
</head>
<body>
    <img src="common_upload.do?method=asyncDownload&fid=${fileInfo.fid}">
</body>
</html>
