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
		function commomOperation(type,url,chkName,sendVal){
			var num = 0;
			var pkVals = "!@!";
			if("query" == type){
				refreshForm(url);
			}else if("add" == type ){
				showTopWin(url,"700","450");
			}else if("update" == type || "view" == type || "delete" == type){
				var pks = document.getElementsByName(chkName);
				for(var i=0; i<pks.length; i++){
					if(pks[i].checked == true){
						num++;
						pkVals +=pks[i].value+"!@!"; 
					}
				}
				if(num == 0){
					alert("请选择你要操作的记录！");
					return  false;
				}
				if("update" == type || "view" == type){
					if(num > 1){
						alert("一次只能操作一条记录！");
						return  false;
					}else{
						pkVals = replaceChar(pkVals,"!@!","");
						url += "&"+sendVal+"="+pkVals;
					}
					showTopWin(url,"700","450");
				}else{
					if($('pkVStr')){
						$('pkVStr').value = pkVals;
					}else{
						url += "&"+pkVStr+"="+pkVals;
					}
					refreshForm(url);
				}			
			}
		}
	</script>
	<body>
		<center>
			<html:form action="/zbrygl" method="post">
					<input type="hidden" name="pkVStr" id="pkVStr" value="" />
					<input type="hidden" name="realTable" id="realTable" value="<bean:write name="realTable"/>"/>
					<input type="hidden" name="tableName" id="tableName" value="<bean:write name="tableName"/>"/>
					<input type="hidden" name="pk" id="pk" value="${pk }"/>
					<input type="hidden" name="doType2" id="doType2" value="${doType2 }"/>
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />
					<div class="title">
						<div class="title_img" id="title_m">
							当前所在位置：日常事务 - 学生管理员管理 - 学生管理员
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
										<bean:message key="lable.xsgzyxpzxy" />名称：
										<html:select property="xydm" style="width:220px" styleId="xy" 
											onchange="initZyList();initBjList();">
											<html:option value="">--请选择--</html:option>
											<html:options property="xydm" labelProperty="xymc"
													collection="xyList" />
										</html:select>								
										&nbsp;&nbsp;&nbsp; 专业名称：
										<html:select property="zydm" style="width:220px" styleId="zy" 
											onchange="initBjList();">
											<html:option value="">--请选择--</html:option>
											<html:options property="zydm" labelProperty="zymc"
												collection="zyList" />
										</html:select>
									</td>
									<td width="10" rowspan="2"  align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2"  id="search_go" style="height: 40px"
											onclick="commomOperation('query','/xgxt/xsgly.do?method=xsgly&doType=query');">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left">
										班级名称：
										<html:select property="bjdm" style="width:250px" styleId="bj">
											<html:option value="">--请选择--</html:option>
											<html:options property="bjdm" labelProperty="bjmc"
													collection="bjList" />
										</html:select>
										&nbsp;&nbsp;&nbsp; 学号：
										<html:text property="xh"></html:text>
										&nbsp;&nbsp;&nbsp; 姓名：
										<html:text property="xm"></html:text>
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
								共<bean:write name="rsNum"/>记录&nbsp;&nbsp;
								<font color="blue">提示：单击表头可以排序</font>
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td align="center">
											<input type="checkbox" name="all" value="all"
												onclick="chec()">
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
												value="<bean:write name="s" property="xh"/>">
										</td>
										<td align="center">
											<bean:write name="s" property="xh" />
										</td>
										<td align="center">
											<bean:write name="s" property="xm" />
										</td>
										<td align="center">
											<bean:write name="s" property="xb" />
										</td>
										<td align="center">
											<bean:write name="s" property="xymc" />
										</td>
										<td align="center">
											<bean:write name="s" property="zymc" />
										</td>
										<td align="center">
											<bean:write name="s" property="bjmc" />
										</td>
										<td align="center">
											<bean:write name="s" property="sfqy" />
										</td>
										<td align="center">
											<bean:write name="s" property="bz" />
										</td>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
						<br />
						<br />
					</logic:notEmpty>
					<div id="tmpdiv"></div>
			        <div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2" onclick="commomOperation('add','/xgxt/xsgly.do?method=operateXsglyxx&act=add')" style="width:60px">
								增 加
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="commomOperation('update','/xgxt/xsgly.do?method=operateXsglyxx&act=update','pkV','pkVStr')" style="width:60px">
								修 改
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="commomOperation('delete','/xgxt/xsgly.do?method=xsgly&doType=delete','pkV','pkVStr')" style="width:60px">
								删 除
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="impAndChkData()" style="width:60px">
								导 入
							</button>	
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:60px">
								导 出
							</button>
						</div>
					</center>
			</html:form>
			
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
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script language="javascript">
			alert('操作失败！');
		</script>
	</logic:equal>
</html>
