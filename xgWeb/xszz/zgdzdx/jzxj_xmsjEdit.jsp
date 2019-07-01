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
			var xmdm = document.getElementById('xmdm').value;
			var xydm = document.getElementById('xydm').value;
			var kssj = document.getElementById('kssj').value;
			var jssj = document.getElementById('jssj').value;
			if((xmdm == null) || (xmdm == "")){
				alert("��ѡ����ѧ����Ŀ!");
				return false;
			}
			if((xydm == null) || (xydm == "")){
				alert("��ѡ��<bean:message key="lable.xsgzyxpzxy" />!");
				return false;
			}
			if((kssj == null) || (kssj == "")){
				alert("���뿪ʼʱ�䲻��Ϊ��!");
				return false;
			}
			if((jssj == null) || (jssj == "")){
				alert("�������ʱ�䲻��Ϊ��!");
				return false;
			}
			if (kssj > jssj){
				alert("���뿪ʼʱ�䲻�ܴ����������ʱ�䣡");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsjEdit&act=save";
			document.forms[0].submit();
		}
	</script>
</head>

<body>
	<html:form action="shgc_kns.do" method="post">
		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
			</logic:match>
		</logic:present>
		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>����ѧ�� - ��������ά�� - ��Ŀ����ʱ��ά��</a>
				</p>
			</div>
		<div class="tab">
  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>��Ŀ����ʱ��ά��</span></th>
			        </tr>
			    </thead>
		 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          <logic:equal name="writeAble" value="yes">
			         <button type="button" class="" onClick="yz();">
						����
					</button>
					<button type="button" class=""
						onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						�ر�
					</button>
					</logic:equal>
				</td>
			      </tr>
			    </tfoot>
			    
			    <tbody>
			      <tr>
			        <th width="25%">��Ŀ</th>
			        <td width="25%">
			        	<input type="hidden" id="xmdm" name="xmdm"
							value="<bean:write name="rs" property="xmdm"/>" />
						<input type="text" id="xmmc" name="xmmc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xmmc"/>">
			        </td>
			        <th width="25%"><bean:message key="lable.xsgzyxpzxy" /></th>
			        <td width="25%">
			        	<input type="hidden" id="xydm" name="xydm"
							value="<bean:write name="rs" property="xydm"/>" />
						<input type="text" id="xymc" name="xymc" readonly="readonly"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xymc"/>">
			        </td>
			      </tr>
					
					 <tr>
			        <th width="25%">�Ƿ������������</th>
			        <td width="25%">
			        	<logic:present name="rs" property="sfkns">
						<logic:match value="��" name="rs" property="sfkns">
							<input type="radio" name="sfkns" value="��" checked>&nbsp;&nbsp;��
							&nbsp;
							<input type="radio" name="sfkns" value="��">&nbsp;&nbsp;��
						</logic:match>
						<logic:match value="��" name="rs" property="sfkns">
							<input type="radio" name="sfkns" value="��">&nbsp;&nbsp;��
							&nbsp;
							<input type="radio" name="sfkns" value="��" checked>&nbsp;&nbsp;��
						</logic:match>
					</logic:present>
					<logic:notPresent name="rs" property="sfkns">
						<input type="radio" name="sfkns" value="��">&nbsp;&nbsp;��
						&nbsp;
						<input type="radio" name="sfkns" value="��" checked>&nbsp;&nbsp;��
					</logic:notPresent>
			        </td>
			        <th width="25%">���뿪ʼʱ��</th>
			        <td width="25%">
			        	<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('kssj','y-mm-dd');"
								value="<bean:write name='rs' property="kssj" />"
								name="kssj" id="kssj" />
			        </td>
			      </tr>
			<tr>
			<th></th><td></td>
				<th>
					�������ʱ��
				</th>
				<td align="center">
					<input type="text" readonly style="cursor:hand;width:120px"
								onclick="return showCalendar('jssj','y-mm-dd');"
								value="<bean:write name='rs' property="jssj" />"
								name="jssj" id="jssj" />
				</td>
				
			</tr>
			</tbody>
		</table>
		</div>
	</html:form>
</body>
</html:html>
