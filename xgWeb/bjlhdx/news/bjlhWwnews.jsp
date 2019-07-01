<%@ page language="java"  pageEncoding="GB18030"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
  </head>
  
  <body>
  	<input type="hidden" path="bjlhWwnews.do">
  	<logic:present name="news">
	    <ul>
		    <logic:iterate id="n" name="news">
		    	<li>
		    		<a href="#" onclick="window.open('newsContent.do?newsId=${n.newsid }')">${n.newstitle }</a>
		    	</li>
		    </logic:iterate>
	    </ul>
  	</logic:present>
  </body>
</html>
