<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${fjmc}</title>
    <style type="text/css">
        body{
            margin:0;
            padding:0;
            overflow: hidden;
        }

        #displayPdfIframe{
            width:100%;
            height:100%;
            boder:0;
        }
    </style>
</head>
<body>
<iframe id="displayPdfIframe"></iframe>
<script type="text/javascript">
    var pdfUrl = "${pageContext.request.contextPath}/rcsw_rcxwwhnew_wjyl.do?method=getPdfFile&fjlj=${fjlj}&fjmc=${fjmc}";
    document.getElementById("displayPdfIframe").src = "js/pdfjs/web/viewer.html?file="+encodeURIComponent(pdfUrl);
</script>
</body>
</html>
