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
			var bjgmc = document.getElementById('bjgmc').value;
			var xfjd = document.getElementById('xfjd').value;
			var zhszpf = document.getElementById('zhszpf').value;
			var zhszpm = document.getElementById('zhszpm').value;
			if((bjgmc == null) || (bjgmc == "")){
				alert("����д�������Ŵ���!");
				return false;
			}
			if((xfjd == null) || (xfjd == "")){
				alert("����дѧ�ּ���!");
				return false;
			}
			if((zhszpf == null) || (zhszpf == "")){
				alert("����д�ۺϲ�������!");
				return false;
			}
			if((zhszpm == null) || (zhszpm == "")){
				alert("����д�ۺϲ�������!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmqtPlEdit&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>����ѧ�� - ��������ά�� - ��Ŀ������������</a>
				</p>
			</div>
	<html:form action="zgdzdx_xszz.do?method=jzxj_xmqtPlEdit" method="post">

		<input type="hidden" id="pkDel" name="pkDel"
			value="<bean:write name='rs' property='pkDel'/>" />
		<logic:present name="end">
			<logic:match value="end" name="end">
				<script language="javascript">
	         	alert("������ɣ�");
	         	</script>
			</logic:match>
		</logic:present>
		
		<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="2"><span>��Ŀ������������</span></th>
			        </tr>
			    </thead>
		 <tfoot>
			      <tr>
			        <td colspan="2"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			        <button type="button" class="" onClick="yz();">
						����
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
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
				<th width="50%">
					<div align="">
						�������Ŵ�
					</div>
				</th>
				<td width="50%">
					<input type="text" id="bjgmc" name="bjgmc" maxlength="2"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="bjgmc"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
			</tr>
			<tr>
				<th>
					<div align="">
						ѧ�ּ���
					</div>
				</th>
				<td>
					<input type="text" id="xfjd" name="xfjd" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="xfjd"/>">
				</td>
			</tr>
			<tr>
				<th>
					<div align="">
						�ۺϲ�������
					</div>
				</th>
				<td>
					<input type="text" id="zhszpf" name="zhszpf" maxlength="6"
						style="width:100%;heigh:100%"
						value="<bean:write name="rs" property="zhszpf"/>">
				</td>
			</tr>
			<tr>
				<th>
					<div align="">
						�ۺϲ�������<br />
						(�༶�����ٷֱ�)
					</div>
				</th>
				<td>
					<input type="text" id="zhszpm" name="zhszpm" maxlength="3"
						style="width:50px;heigh:100%"
						value="<bean:write name="rs" property="zhszpm"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') "
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">%
				</td>
			</tr>
			</tbody>
		</table>
		</div>
	</html:form>
</body>
</html:html>
