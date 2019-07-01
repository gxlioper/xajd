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
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function SaveAdd(){
		   document.forms[0].action = "/xgxt/xshkgl.do?method=addhkxx&doType=save";
		   document.forms[0].submit();
		}		
		function getRqVal(name){
			var rq=document.getElementById(name).value;
			if (rq!=""){
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++){
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
		}
		function changeView(obj){
			var hkzt = document.getElementById('hkzt');
			if('��Ǩ��' == hkzt.value){
				$('sfxs').style.display = 'block';
			}else{
				$('sfxs').style.display = 'none';
			}
		}
	</script>
</head>
<body>

	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã��ճ����� - ѧ�����ڹ��� - ���ӻ�����Ϣ
		</div>
	</div>
	<html:form action="/xshkgl.do?method=addhkxx" method="post">
		<table class="tbstyle" width="100%">
			<tr>
				<td align="right" width="15%">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left" width="35%">
					<html:text name='rs' property="xh" readonly="readonly" styleId="xh"/>
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
				</td>
				<td align="right" width="15%">
						������
				</td>
				<td align="left" width="35%">
					<bean:write name="rs" property="xm" />
				</td>
			</tr>
			<tr>
				<td align="right">
						�Ա�
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
						�꼶��
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>

			</tr>
			<tr>
				<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />���ƣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
						רҵ���ƣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
			</tr>
			<tr>
				<td align="right">
					�༶���ƣ�

				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td>
				</td>
				<td>
				</td>
			</tr>
			<tr>
				<td align="right">
					����״̬��
				</td>
				<td align="left">
					<html:select property="hkzt" styleId="hkzt" style="width:150px" onchange="changeView(this);">
						<html:option value="">--��ѡ��--</html:option>
						<html:option value="��ס�˿�">��ס�˿�</html:option>
						<html:option value="��ס�˿�">��ס�˿�</html:option>
						<html:option value="��ס�˿�">��ס�˿�</html:option>
						<html:option value="��Ǩ��">��Ǩ��</html:option>
					</html:select>
				</td>
				<td align="right">
					���ڴ��룺
				</td>
				<td align="left">
					<html:text name="rs" property="hkdm" maxlength="25"></html:text>
				</td>
			</tr>
			
		</table>
		<div id="sfxs" style="display: none">
		<table class="tbstyle" width="100%">
			<tr>
				<td align="right" width="15%">
						Ǩ��ʱ�䣺
				</td>
				<td align="left" width="35%">
					<html:text name="rs" property="qcsj" styleId="qcsj" readonly="true"
						style="width:150px" onclick="this.value='';return showCalendar('qcsj','y-mm-dd');"
						onblur="getRqVal('qcsj')"></html:text>
				</td>
				<td align="right" width="15%">
						Ǩ����ַ��
				</td>
				<td align="left" width="35%">
					<html:text name="rs" property="qcdz" maxlength="100"></html:text>
				</td>
			</tr>
			<tr>
				<td align="right">
						Ǩ��֤���룺
				</td>
				<td align="left">
					<html:text name="rs" property="qyzhm" maxlength="40"></html:text>
				</td>
				<td align="right">
						�Ƿ���ȡ��
				</td>
				<td align="left">
					<html:select name="rs" property="sflq" styleId="sflq">
						<html:option value="">--��ѡ��--</html:option>
						<html:option value="��">��</html:option>
						<html:option value="��">��</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<td align="right">
						��ȡ��������
				</td>
				<td align="left">
					<html:text name="rs" property="lqrxm" maxlength="40" style="width:150px"></html:text>
				</td>
				<td align="right">
						��ȡ���ڣ�
				</td>
				<td align="left">
					<html:text name="rs" property="lqrq" styleId="lqrq" readonly="true" style="width:150px"
						onclick="this.value='';return showCalendar('lqrq','y-mm-dd');"
						onblur="getRqVal('lqrq')"></html:text>
				</td>
			</tr>
			
		</table>
		</div>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2" onClick="SaveAdd()" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2" onClick="Close()" id="buttonSave">
					�� ��
				</button>
		</div>

	</html:form>
	<logic:equal value="true" name="result">
		<script type="text/javascript">
			 alert('�����ɹ���');
			 window.dialogArguments.document.getElementById('search_go').onclick();
			 window.close();
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script type="text/javascript">
			 alert('����ʧ�ܣ�');
		</script>
	</logic:equal>
</body>
</html:html>
