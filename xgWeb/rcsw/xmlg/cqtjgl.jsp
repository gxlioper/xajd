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
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
	<script type="text/javascript">
		function addcqtj(){
			showTopWin('/xgxt/cqtjgl.do?method=cqtjAdd',600,350);
		}
		function updatecqtj(){
			var num = 0;
			var pkVStr = '';
			var pks = document.getElementsByName('pkV');
			for(var i=0; i<pks.length; i++){
				if(pks[i].checked == true){
					num++; 
					pkVStr +=pks[i].value;
				}
			}
			if(num == 0){
				alert("请选择你要修改的记录！");
				return  false;
			}else if(num > 1){
				alert("一次只能修改一条记录！");
				return  false;
			}
			showTopWin('/xgxt/cqtjgl.do?method=cqtjUpdate&pk='+pkVStr,600,350);
		}
		function deletecqtj(){
			var num = 0;
			if(confirm("您确认要删除吗？")){
				var pkVStr = '!@!';	 
				var pks = document.getElementsByName('pkV');
				for(var i=0; i<pks.length; i++){
					if(pks[i].checked == true){
						num++;
						pkVStr +=pks[i].value+'!@!'; 
					}
				}
				if(num == 0){
					alert("请选择你要删除的记录！");
					return  false;
				}else{
					$('pkVStr').value=pkVStr;
				}
			
				$('pkVStr').value=pkVStr;	
				document.forms[0].action = "/xgxt/cqtjgl.do?method=cqtjDelete";
		   		document.forms[0].submit();
			}
			
		}
		function queryData(){
			refreshForm('/xgxt/cqtjgl.do?method=cqtjgl&doType=query');
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
		function cqtjb(){
			var jcsj=document.getElementById('jcsj').value;
			if(jcsj == ''){
				alert('请在查询条件中填写检查时间！');
				return false;
			}
			document.forms[0].action = "/xgxt/cqtjgl.do?method=cqtjPrint";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
	</script>
	<body>
		<center>
			<html:form action="/cqtjgl" method="post">
				<input type="hidden" name="realTable" id="realTable"
					value="<bean:write name="realTable"/>" />
				<input type="hidden" name="tableName" id="tableName"
					value="<bean:write name="tableName"/>" />
				<input type="hidden" name="pkVStr" id="pkVStr"
					value="" />
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：日常事务 - 考勤管理 - 出勤统计管理
					</div>
				</div>
				<fieldset>
					<legend>
						查 询
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									学年：
									<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp; 学期：
									<html:select property="xq" styleId="xn" style="width:40px">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									&nbsp;&nbsp; <bean:message key="lable.xsgzyxpzxy" />：
									<logic:equal value="xy" name="userType">
										<html:select property="xydm" styleId="xy" disabled="true">
											<html:option value="">--请选择--</html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual value="xy" name="userType">
										<html:select property="xydm" styleId="xy">
											<html:option value="">--请选择--</html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>

									&nbsp;&nbsp; 检查时间：
									<html:text property="jcsj" styleId="jcsj" readonly="true"
										onclick="this.value='';return showCalendar('jcsj','y-mm-dd');"
										onblur="getRqVal('jcsj')"></html:text>
								</td>
								<td width="10" align="center" valign="middle">
									<button type="button" class="button2" id="search_go" onclick="queryData();">
										查询
									</button>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
					<br />
					<br />
					<p align="center">
						未找到任何记录！
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							<font color="blue">提示：单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td align="center">
										<input type="checkbox" name="all" value="all" onclick="chec()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="chkView()">
									<td align="center">
										<input type="checkbox" name="pkV"
											value="<bean:write name="s" property="pk"/>">
									</td>
									<td align="center">
										<bean:write name="s" property="xn" />
									</td>
									<td align="center">
										<bean:write name="s" property="xqmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="xymc" />
									</td>
									<td align="center">
										<bean:write name="s" property="jcsj" />
									</td>
									<td align="center">
										<bean:write name="s" property="ydrs" />
									</td>
									<td align="center">
										<bean:write name="s" property="sdrs" />
									</td>
									<td align="center">
										<bean:write name="s" property="qjrs" />
									</td>
									<td align="center">
										<bean:write name="s" property="kkrs" />
									</td>
									<td align="center">
										<bean:write name="s" property="cql" />
									</td>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
					<br />
					<br />
				</logic:notEmpty>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<button type="button" class="button2" onclick="addcqtj()" style="width:60px">
							增 加
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="updatecqtj()" style="width:60px">
							修 改
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="deletecqtj()" style="width:60px">
							删 除
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="impAndChkData()"
							style="width:60px">
							导 入
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="dataExport()" style="width:60px">
							导 出
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="cqtjb()" style="width:80px">
							出勤统计表
						</button>
					</div>
				</center>
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
	<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "98%";
					window.setInterval("initBTNTool('btn')",1);
	</script>
	<logic:equal value="true" name="result">
		<script language="javascript">
			alert('操作成功！');
			document.getElementById('search_go').click();
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script language="javascript">
			alert('操作失败！');
		</script>
	</logic:equal>
</html>
