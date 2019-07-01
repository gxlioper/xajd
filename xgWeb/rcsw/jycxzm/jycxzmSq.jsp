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

		<meta name="Copyright" content="正方软件 zfsoft" />
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
		 var url = "/xgxt/jycxzmgl.do?method=addJycczm&act=add";
		 var yzdz = "";
		 var yzcd = "";
		 var mustFill = "xh-zqsj-yzyh-yzzh-sfzh";
		 checkdataSave(url,yzdz,yzcd,mustFill);
		 }
	 
	 function checkdataSave(url,yzdz,yzcd,mustFill){
		 var eles = mustFill.split("-");
	    	for (var i = 0; i < eles.length; i++) {
	    		if (document.getElementById(eles[i]) && document.getElementById(eles[i]).value == "") {
	    			alert("请将带\"*\"号的项目输入完整！");
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
	    			alert(dzsjmc.replace("<BR>", "")+"不能超过"+splitCd[i]+"个字！");
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
					当前位置：日常事务 - 教育储蓄证明管理- 教育储蓄证明申请
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    				alert("您输入的学号无效!");
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
									<b>填写申请表</b>
								</logic:equal>
								<logic:equal name="do" value="yes">
									<b>修改申请表</b>
								</logic:equal>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td align="left">
							<html:text name='rs' property="xh" styleId="xh"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								选择
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="right">
							<font color="red">*</font>学号：
						</td>
						<td align="left">
							<bean:write name='rs' property="xh" />
							<input type="hidden" name="xh" id="xh"
								value="<bean:write name='rs' property="xh" />">
						</td>
					</logic:equal>
							<td align="right">
								<font color="red">*</font>申请时间：						
							</td>
							<td align="left">
								<html:text property="zqsj" readonly="true" onclick="return showCalendar('zqsj','y-mm-dd');" onblur="dateFormatChg(this)"></html:text>						
							</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							<bean:write name="rs" property="xm" />
						</td>
						<td align="right">
							年度：
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
							性别：
						</td>
						<td align="left">
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right">
							学年：
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
							年级：
						</td>
						<td align="left">
							<bean:write name="rs" property="nj" />
						</td>
						<td align="right">
							学期：
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
							<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc" />
						</td>
						<td align="right">
							<font color="red">*</font>储蓄银行：
						</td>
						<td align="left">
							<html:select property="yzyh">
								<html:option value=""></html:option>
								<html:options collection="yhList" property="yhdmid"
									labelProperty="yhdmmc"/>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							专业：
						</td>
						<td align="left">
						<bean:write name="rs" property="zymc" />
						</td>
						<td align="right">
							<font color="red">*</font>储蓄支行：
						</td>
						<td>
							<html:text property="yzzh" maxlength="40"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							班级：
						</td>
						<td align="left">
							<bean:write name="rs" property="bjmc" />
						</td>
						<td align="right">
							<font color="red">*</font>身份证：
						</td>
						<td>
							<html:text property="sfzh" maxlength="30"></html:text>
						</td>
					</tr>
					<tr align="left" style="height:22px;display: none">
						<td align="right">
							工作经历：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='gzjl' styleId="gzjl"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px;display: none">
						<td align="right">
							<font color="red">*</font>申请理由：
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='xssq' styleId="sqly"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px;display: none">
						<td align="right">
							备注：
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
						提 交 申 请
					</button>


<%--					<logic:notPresent name="zdy">--%>
<%--						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--						<button type="button" class="button2" onclick="print();">--%>
<%--								打 印 预 览--%>
<%--							</button>--%>
<%--					</logic:notPresent>--%>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="done">
			<script language="javascript">
					alert("操作成功！");
					//Close();
					//window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="yes" name="isexists">
			<script language="javascript">
					alert("该学生已经申请了，不必重复申请！");
					//Close();
					//window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="done">
				<script language="javascript">
						alert("操作失败！");
						//Close();
						//window.dialogArguments.document.getElementById('search_go').click();
				</script>
		</logic:equal>
	</body>
</html>
