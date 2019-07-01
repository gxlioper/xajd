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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/zjcmJxjDAO.js'></script>
	<script type="text/javascript">
	function xnjxjSh(shzt){
		var url = "/xgxt/zjcm_xnjxj.do?method=xnJxjSh&doType=save&shzt="+shzt;
		showTips('���������У���ȴ�......');
		$("buttonTg").disabled=true;
		$("buttonBtg").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	function xnjxjCjSh(shzt){
		var url = "/xgxt/zjcm_xnjxj.do?method=xnJxjSh&doType=cjsave&shzt="+shzt;
		showTips('���������У���ȴ�......');
		$("buttonTg").disabled=true;
		$("buttonBtg").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	function jxjPrint(){
	    var url = "/xgxt/zjcm_xnjxj.do?method=jxjDjb";
	    var xh = $("xh").value;
	    var jxjmc = $("jxjmc").value; 
		url+="&xh="+xh;
		url+="&jxjmc="+jxjmc;
	  
        document.forms[0].action = url;
	    document.forms[0].target = "_blank";
	    document.forms[0].submit();
	    document.forms[0].target = "_self";
	}
	
	function savedata() {
		var pk = document.getElementById('pkValue').value;
		var userType = document.getElementById('userType').value;
		var isFdy = document.getElementById('isFdy').value;
		var sh = document.getElementById('sh').value;
		zjcmJxjDAO.checkJxjshjg(pk,userType,isFdy, sh, function (data){
			if (data == 'false') {
				alert("�ý�ѧ�����ͨ�������ѴﵽѧУ���õĻ�����,���������ͨ��!");
				return false;
			} else {
				refreshForm('pjpy_zjcm_jxjshDetails.do?go=save');
			}
		});
		
	}
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/zjcm_xnjxj" method="post">
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
			<input type="hidden" name="msg" id="msg" value="${msg}"/>
			<input type="hidden" name="userType" id="userType" value="${userType }"/>
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx }"/>
			<div class="title">
				<div class="title_img">
					��ǰ����λ�ã��������� - ��� - У�ڽ�ѧ�����
				</div>
			</div>
			<fieldset>
				<legend>
					
				</legend>
				<table width="100%" class="tbstyle" align="center">
					<thead>
						<tr align="center">
							<td colspan="4">
								У�ڽ�ѧ�����
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right" width="20%">
							ѧ�ţ�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xh"/>
							<input type="hidden" id="xh" value="<bean:write name="rs" property="xh"/>"/>
						</td>
						<td align="right" width="20%">
							����ѧ�꣺
						</td>
						<td align="left" width="30%">
							${rs.xn }
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							������
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xm"/>
						</td>
						<td align="right" width="20%">
							����ѧ�ڣ�
						</td>
						<td align="left" width="30%">
							${rs.xqmc }
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							�Ա�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xb"/>
						</td>
						<td align="right" width="20%">
							��ѧ��
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="jxjmc"/>
							<input type="hidden" id="jxjmc" value="<bean:write name="rs" property="jxjmc"/>"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							<bean:message key="lable.xsgzyxpzxy" />���ƣ�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="xymc"/>
						</td>
						<td align="right" width="20%">
							�����֣�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="dyzhf"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							רҵ���ƣ�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="zymc"/>
						</td>
						<td align="right" width="20%">
							�����֣�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="zyzhf"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							�༶���ƣ�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="bjmc"/>
						</td>
						<td align="right" width="20%">
							�����֣�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="tyzhf"/>
						</td>
					</tr>
					<tr>
						
						<td align="right" width="20%">
							�����֣�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="nlzhf"/>
						</td>
						<td align="right" width="20%">
							�ۺϷ֣�
						</td>
						<td align="left" width="30%">
							<bean:write name="rs" property="zhf"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							������������
						</td>
						<td align="left" width="30%">
							${rs.dypm }
						</td>
						<td align="right" width="20%">
							������������
						</td>
						<td align="left" width="30%">
							${rs.zypm }
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							������������
						</td>
						<td align="left" width="30%">
							${rs.typm }
						</td>
						<td align="right" width="20%">
							������������
						</td>
						<td align="left" width="30%">
							${rs.nlpm }
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							�۲��������
						</td>
						<td align="left" width="30%">
							${rs.zhpm }
						</td>
						<td align="right" width="20%">
							&nbsp;
						</td>
						<td align="left" width="30%">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							��˽����
						</td>
						<td align="left" width="30%">
							<html:select property="sh" styleId="sh" >
								<html:options collection="shList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
						<td align="right" width="20%">
							&nbsp;
						</td>
						<td align="left" width="30%">
							&nbsp;
						</td>
					</tr>
					
					<logic:notEmpty name="cfList">
					<tr>
						<td align="center" colspan="4">
							<b>��УΥ�����</b>
						</td>
					</tr>
					<tr><td colspan="4" >
						<table class="tbstyle" align="center" style="width:100%">
						<tr>
							<td align="center">ѧ��</td>
							<td align="center">ѧ��</td>
							<td align="center">�������</td>
							<td align="center">����ԭ��</td>
							<td align="center">����ʱ��</td>
							<td align="center">�����ĺ�</td>
						</tr>
						<logic:iterate name="cfList" id="s" indexId="index">
						<tr>
								<logic:iterate id="v" name="s" >
								<td align="center">
								<bean:write name="v" />
								</td>
								</logic:iterate>
						</tr>
					</logic:iterate>
					</table>
					</td></tr>
					</logic:notEmpty>
					
					<logic:equal name="userType" value="xy">
					<logic:equal name="isFdy" value="true">
					<tr>
						<td align="right" width="20%">
							����Ա�����
						</td>
						<td align="left" colspan="3">
							<html:textarea property="yj" style="width:100%" rows="5" onblur="chLeng(this,250)"/>
						</td>
					</tr>
					</logic:equal>
					<logic:notEqual value="true" name="isFdy">
						<tr>
						<td align="right" width="20%">
							����Ա�����
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="fdyyj" style="width:100%" rows="3" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							<bean:message key="lable.xsgzyxpzxy" />�����
						</td>
						<td align="left" colspan="3">
							<html:textarea property="yj" style="width:100%" rows="3" onblur="chLeng(this,250)"/>
						</td>
					</tr>
					</logic:notEqual>
					
					</logic:equal>
					<logic:notEqual name="userType" value="xy">

					<tr>
						<td align="right" width="20%">
							����Ա�����
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="fdyyj" style="width:100%" rows="3" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							<bean:message key="lable.xsgzyxpzxy" />�����
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="xyyj" style="width:100%" rows="3" readonly="true"/>
						</td>
					</tr>
					<tr>
						<td align="right" width="20%">
							ѧУ�����
						</td>
						<td align="left" colspan="3">
							<html:textarea property="yj" style="width:100%" rows="3" onblur="chLeng(this,250)"/>
						</td>
					</tr>
					</logic:notEqual>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<logic:notEqual value="view" name="writa">
							<button class="button2" id="btn_save" onclick="savedata()" style="width:80px" ${dis }>����</button>
							&nbsp;
							</logic:notEqual>
							<button class="button2" id="btn_save" onclick="window.close();return false;" style="width:80px">�ر�</button>
						</td>
					</tr>
					<logic:notEmpty name="dis">
						<div align="center">
							<font color="red">��ز��������,�����ٽ������!</font>
						</div>
					</logic:notEmpty>
				</table>
			</fieldset>
		
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>		
		</html:form>
	</body>
</html>
