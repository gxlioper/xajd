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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript">
	function dataSave(){
		 var url = "/xgxt/jycxzmgl.do?method=jycczmUpdate&act=update";
		 var yzdz = "";
		 var yzcd = "";
		 var mustFill = "xh-zqsj-yzyh-yzzh-sfzh";
		 checkdataSave(url,yzdz,yzcd,mustFill);
		 }
	 
	 function checkdataSave(url,yzdz,yzcd,mustFill){
		 var eles = mustFill.split("-");
	    	for (var i = 0; i < eles.length; i++) {
	    		if (document.getElementById(eles[i]) && document.getElementById(eles[i]).value == "") {
	    			alert("�뽫��\"*\"�ŵ���Ŀ����������");
	    			return false;
	    		}
	    	}
	    	var splitDz = "";
	    	var splitCd = "";
	    	if(yzdz != ""){
	    		splitDz = yzdz.split("-");
	    	}
	    	if(yzcd != ""){
	    		splitCd = yzcd.split("-");
	    	}
	    	for (i = 0; i < splitDz.length; i++) {
	    		var dzsjcd = document.getElementById(splitDz[i]).cells[1].getElementsByTagName('textarea')[0].value;
	    		var dzsjmc = document.getElementById(splitDz[i]).cells[0].innerHTML;
	    		if (dzsjcd.length>splitCd[i]) {
	    			alert(dzsjmc.replace("<BR>", "")+"���ܳ���"+splitCd[i]+"���֣�");
	    			return false;
	    		}
	    	}
	    	document.forms[0].action = url;
	    	document.forms[0].submit();
	    	document.getElementById("buttonSave").disabled=true;
	 }
	</script>
	<body>
		<html:form action="/jycxzmgl" method="post">
			<input type="hidden" name="xxdm" id="xxdm"
				value="<bean:write name='xxdm'/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��ճ����� - ��������֤������- ��������֤������
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    				alert("�������ѧ����Ч!");
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-kh" />
				<input type="hidden" id="tableName" name="tableName" value="czxx_jycyzmb" />
				<input type="hidden" id="url" name="url" value="/jycxzmgl.do?method=addJycczm" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<logic:equal name="do" value="no">
									<b>��д�����</b>
								</logic:equal>
								<logic:equal name="do" value="yes">
									<b>�޸������</b>
								</logic:equal>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
							<input id="pkVal" name="pkVal" value="<bean:write name="rs" property="pk"/>" type="hidden">
						</td>
							<td align="right">
								<font color="red">*</font>����ʱ�䣺						
							</td>
							<td align="left">
								<html:text name='rs' property="zqsj" readonly="true" ></html:text>						
							</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							������
						</td>
						<td align="left">
							<bean:write name="rs" property="xm" />
						</td>
						<td align="right">
							��ȣ�
						</td>
						<td align="left">
<%--							<html:text name="rs" property="nd" readonly="true" styleId="nd"></html:text>--%>
							<html:select name="rs" property="nd">
								<html:options collection="ndList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right">
							ѧ�꣺
						</td>
						<td align="left">
<%--							<html:text name="rs" property="xn" readonly="true" styleId="xn"></html:text>--%>
							<html:select name="rs" property="xn" styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<bean:write name="rs" property="nj" />
						</td>
						<td align="right">
							ѧ�ڣ�
						</td>
						<td align="left">
<%--							<html:text name="rs" property="xq" readonly="true" styleId="xq"></html:text>--%>
							<html:select name="rs" property="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc" />
						</td>
						<td align="right">
							<font color="red">*</font>�������У�
						</td>
						<td align="left">
							<html:select name="rs" property="yzyh">
								<html:option value=""></html:option>
								<html:options collection="yhList" property="yhdmid"
									labelProperty="yhdmmc"/>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							רҵ��
						</td>
						<td align="left">
						<bean:write name="rs" property="zymc" />
						</td>
						<td align="right">
							<font color="red">*</font>����֧�У�
						</td>
						<td>
							<html:text name='rs' property="yzzh" maxlength="40"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<bean:write name="rs" property="bjmc" />
						</td>
						<td align="right">
							<font color="red">*</font>���֤��
						</td>
						<td>
							<html:text name='rs' property="sfzh" maxlength="30"></html:text>
						</td>
					</tr>
					<tr align="left" style="height:22px;display: none">
						<td align="right">
							����������
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='gzjl' styleId="gzjl"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px;display: none">
						<td align="right">
							<font color="red">*</font>�������ɣ�
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='xssq' styleId="sqly"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px;display: none">
						<td align="right">
							��ע��
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz"
								style="width:99%" rows='5' />
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button type="button" id="buttonSave" class="button2" onclick="dataSave();">
<%--						onclick="if(checkFiledSuccess()){chkisDataExist('xh-xmdm-lxdh-kcjqgzxsj-sqly');}">--%>
						�� �� �� ��
					</button>


<%--					<logic:notPresent name="zdy">--%>
<%--						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--						<button type="button" class="button2" onclick="print();">--%>
<%--								�� ӡ Ԥ ��--%>
<%--							</button>--%>
<%--					</logic:notPresent>--%>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="done">
			<script language="javascript">
					alert("�����ɹ���");
					//Close();
					//window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="yes" name="isexists">
			<script language="javascript">
					alert("��¥���Ѿ��ڱ�ѧ�������ˣ������ظ����룡");
					//Close();
					//window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="done">
				<script language="javascript">
						alert("����ʧ�ܣ�");
						//Close();
						//window.dialogArguments.document.getElementById('search_go').click();
				</script>
		</logic:equal>
	</body>
</html>
