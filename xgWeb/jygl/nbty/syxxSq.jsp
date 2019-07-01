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
		 var url = "/xgxt/nbtybysy.do?method=addnbtybysy&act=add";
		 var yzdz = "";
		 var yzcd = "";
		 var mustFill = "xh-xm";
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
		<html:form action="/nbtybysy" method="post">
			<input type="hidden" name="xxdm" id="xxdm"
				value="<bean:write name='xxdm'/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：就业管理 - 生源信息- 生源信息申请
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
				<input type="hidden" id="tableName" name="tableName" value="nbtysysbb" />
				<input type="hidden" id="url" name="url" value="/nbtybysy.do?method=addnbtybysy" />
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr style="height:22px">
							<td colspan="4" align="center">
								<b>生源信息</b>
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
							<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
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
								<font color="red">*</font>姓名:					
							</td>
							<td align="left">
								<html:text name="rs" property="xm" readonly="true"></html:text>						
							</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							性别：
						</td>
						<td align="left">
							<html:text name="rs" property="xb" maxlength="15" readonly="true"></html:text>
						</td>
						<td align="right">
							身份证号：
						</td>
						<td align="left">
							<html:text name="rs" property="sfzh" readonly="" styleId="sfzh"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							出生年月：
						</td>
						<td align="left">
							<html:text name="rs" property="csny" readonly="true" onclick="return showCalendar('csny','y-mm-dd');" styleId="csny"></html:text>
						</td>
						<td align="right">
							民族：
						</td>
						<td align="left">
<%--							<html:text name="rs" property="xn" readonly="true" styleId="xn"></html:text>--%>
							<html:select name="rs" property="mz" styleId="mz">
								<html:options collection="mzList" property="mzdm"
									labelProperty="mzmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							政治面貌：
						</td>
						<td align="left">
							<html:select name="rs" property="zzmm" styleId="mz">
								<html:options collection="zzmmList" property="zzmmdm"
									labelProperty="zzmmmc" />
							</html:select>
						</td>
						<td align="right">
							联系地址：
						</td>
						<td align="left">
							<html:text name="rs" property="lxdz" maxlength="150" styleId="lxdz"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							邮政编码：
						</td>
						<td align="left">
							<html:text name="rs" property="yzbm" maxlength="8"></html:text>
						</td>
						<td align="right">
							联系电话：
						</td>
						<td align="left">
							<html:text property="lxdh" ></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							手机号码：
						</td>
						<td align="left">
						<html:text property="sjhm" maxlength="15"></html:text>
						</td>
						<td align="right">
							email邮箱：
						</td>
						<td>
							<html:text name="rs" property="email" maxlength="40"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							修业年限：
						</td>
						<td align="left">
							<html:text name="rs" property="xynx" maxlength="15"></html:text>
						</td>
						<td align="right">
							所属学校：
						</td>
						<td>
							<html:text property="xxdm" maxlength="30" readonly="true"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							所属班级：
						</td>
						<td align="left">
							<input type="hidden" name="xydm" value="<bean:write name="rs" property="xydm"/>">
							<input type="hidden" name="zydm" value="<bean:write name="rs" property="zydm"/>">
							<input type="hidden" name="bjdm" value="<bean:write name="rs" property="bjdm"/>">
							<html:text name="rs" property="bjmc" maxlength="15" readonly="true"></html:text>
						</td>
						<td align="right">
							入学年份：
						</td>
						<td>
							<html:text property="rxnf" maxlength="30"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							毕业年份：
						</td>
						<td align="left">
							<html:text name="rs" property="bynf" maxlength="15"></html:text>
						</td>
						<td align="right">
							学历层次：
						</td>
						<td>
							<html:text name="rs" property="xlcc" maxlength="30"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							培养方式：
						</td>
						<td align="left">
							<html:text name="rs" property="pyfs" maxlength="15"></html:text>
						</td>
						<td align="right">
							招生类别：
						</td>
						<td>
							<html:text property="zslb" maxlength="30"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							定向或委培单位：
						</td>
						<td align="left">
							<html:text name="rs" property="dxhwpdw" maxlength="15"></html:text>
						</td>
						<td align="right">
							是否在职：
						</td>
						<td>
							<html:select name="rs" property="sfzz" styleId="sfzz">
								<html:option value=""></html:option>
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							是否师范：
						</td>
						<td align="left">
							<html:select name="rs" property="sfsf" styleId="sfsf">
								<html:option value=""></html:option>
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>
						</td>
						<td align="right">
							是否独立<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td>
							<html:select name="rs" property="sfdlxy" styleId="sfdlxy">
								<html:option value=""></html:option>
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							生源地：
						</td>
						<td colspan="2">
							<html:select name="rs" property="syds" styleId="syds">
								<html:options collection="sydList" property="syddm"
									labelProperty="sydmc" />
							</html:select>
						</td>
						<td>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button id="buttonSave" class="button2" onclick="dataSave();">
<%--						onclick="if(checkFiledSuccess()){chkisDataExist('xh-xmdm-lxdh-kcjqgzxsj-sqly');}">--%>
						提 交 申 请
					</button>

<%--					<logic:notPresent name="zdy">--%>
<%--						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--						<button class="button2" onclick="print();">--%>
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
