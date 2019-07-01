<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="js/jsFunction.js"></script>
<script language="javascript" src="js/xgutil.js"></script>
	 <logic:present name="autoChk">
	 	<logic:equal value="ok" name="autoChk">
	 		<script>
	 			alert('操作成功！');
	 			window.close();
	 		</script>
	 	</logic:equal>
	 	<logic:equal value="no" name="autoChk">
	 		<script>
	 			alert('操作失败！');
	 			window.close();
	 		</script>
	 	</logic:equal>
	 	</logic:present>
  </head>
  <body>
  </body>
</html>
