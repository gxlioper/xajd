<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<base target="_self" />
<head>

<%@ include file="/syscommon/pagehead_V4.ini"%>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(){
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmrsPlsz&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	

	<html:form action="shgc_kns.do" method="post">

		<input type="hidden" id="pkDel" name="pkDel"
			value="<bean:write name='rs' property='pkDel'/>" />
		<logic:present name="end">
			<logic:match value="end" name="end">
				<script language="javascript">
	         	alert("������ɣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>����ѧ�� - ��������ά�� - ��Ŀ������������</a>
				</p>
			</div>
		<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="2"><span>��Ŀ����ʱ��ά��</span></th>
			        </tr>
			    </thead>
		 <tfoot>
			      <tr>
			        <td colspan="2"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			        
			       <button type="button" class="" onClick="yz();">
				����
			</button>
			<button type="button" class=""
				onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
				�ر�
			</button>
			</div>
				</td>
			      </tr>
			    </tfoot>
			<tbody>
			      <tr>
			        <th width="25%">��Ŀ������</th>
			        <td width="25%">
			        	<input type="text" id="zrs" name="zrs" maxlength="5"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="zrs"/>"
							onkeyup="value=value.replace(/[^\d]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
			        </td>
			       
			      </tr>
			       <tr>
			        <th width="25%">�Ƿ���Ч</th>
			        <td width="25%">
			        	<logic:present name="rs" property="sfyx">
						<logic:match value="��" name="rs" property="sfyx">
							<input type="radio" name="sfyx" value="��" checked>&nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="sfyx" value="��">&nbsp;&nbsp;��
						</logic:match>
						<logic:match value="��" name="rs" property="sfyx">
							<input type="radio" name="sfyx" value="��">&nbsp;&nbsp;��
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="radio" name="sfyx" value="��" checked>&nbsp;&nbsp;��
						</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfyx">
						<input type="radio" name="sfyx" value="��">&nbsp;&nbsp;��
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="sfyx" value="��" checked>&nbsp;&nbsp;��
					</logic:notPresent>
			        </td>
			      </tr>
		</tbody>
		</table>
	
	</html:form>
</body>
</html:html>
