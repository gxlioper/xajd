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
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript">

function chkView(){
    var url = "/xgxt/jhzy_gygl.do?method=xjqsChek&pkValue=";
    var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	url += pk+"&view=yes";
    showTopWin(url,"600","400");              		                  
 }
 function xjqsupdate(str){
 	var check = document.getElementsByName('pkV');
 	var pkVStr = document.getElementById('pkVStr');
 	var num = 0;
 	var pkval = "@";
 	var pkone = '';
 	for(var i=0;i<check.length;i++){
 		if(check[i].checked==true){        		                  
 			num++;
 			pkval += check[i].value+'@';
 			pkone = check[i].value;
 		}
 	}
 	if(str=='modi'){
 		if(num==0){
 			alert('请勾选要修改的记录！');
 			return false;
 		}else if(num > 1){
 			alert('一次只能修改一条记录！');
 			return false;
 		}else{
 			showTopWin('jhzy_gygl.do?method=xjwmqsSq&xmdm=1&doType=xjqswh&act=modi&pkone='+pkone,800,470)
 		}
 	}
 	if(str=='del'){
 		if(num==0){
 			alert('请勾选要删除的记录！');
 			return false;
 		}else{
 			pkVStr.value = pkval;
 			refreshForm('/xgxt/jhzy_gygl.do?method=xjqsManange&doType=del');
 		}
 	}	
 }
</script>
	<body>
		<center>
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
			<script type="text/javascript"
				src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="js/AjaxFunction.js"></script>
			<script language="javascript" src="/xgxt/js/pjpy/pjpy_zjsyzy.js"></script>
			
			<html:form action="/jhzy_gygl" method="post">
				<input type="hidden" name="pkVStr" id="pkVStr" value=""/>
				<input type="hidden" name="realTable" id="realTable" value=""/>
				<input type="hidden" name="tableName" id="tableName" value="<bean:write name="tableName"/>"/>
				<input type="hidden" id="userType" name="userType"
					value="${userType}" />
				<input type="hidden" id="userName" name=" userName "
					value="${ userName }" />
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：公寓管理 - 星级文明寝室管理 - 审核结果查询 - 星级寝室查询
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
									&nbsp;&nbsp; 月份：
									<html:select property="yf" styleId="yf" style="width:60px">
										<html:option value=""></html:option>
										<html:option value="01">01</html:option>
										<html:option value="02">02</html:option>
										<html:option value="03">03</html:option>
										<html:option value="04">04</html:option>
										<html:option value="05">05</html:option>
										<html:option value="06">06</html:option>
										<html:option value="07">07</html:option>
										<html:option value="08">08</html:option>
										<html:option value="09">09</html:option>
										<html:option value="10">10</html:option>
										<html:option value="11">11</html:option>
										<html:option value="12">12</html:option>
									</html:select>
									&nbsp;&nbsp; <bean:message key="lable.xsgzyxpzxy" />：
									<logic:equal value="xy" name="userType">
										<input type="hidden" name="xydm" value="<bean:write name="xydm"/>">
										<html:select property="xydm" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:equal>
									<logic:notEqual value="xy" name="userType">
										<html:select property="xydm" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/jhzy_gygl.do?method=xjqsManange&doType=query');">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									楼栋
									<html:select property="lddm" styleId="lddm"
										onchange="getLcList()">
										<html:options collection="ldList" property="dm"
											labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;楼层
									<html:select property="lc" styleId="lc"
										onchange="getQshList2()">
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;寝室
									<html:select property="qsh" styleId="qsh">
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;学校审核：
									<html:select property="yesNo" styleId="yesNo">
										<html:option value="">--请选择--</html:option>
										<html:options collection="chkList" property="en"
											labelProperty="cn" />
									</html:select>
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
							记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：双击一行可以查看详细信息并进行审核；单击表头可以排序</font>
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
										<bean:write name="s" property="qsh" />
									</td>
									<td align="center">
										<bean:write name="s" property="ldmc" />
									</td>
									<td align="center">
										<bean:write name="s" property="cs" />
									</td>
									<td align="center">
										<bean:write name="s" property="lxdh" />
									</td>
									<td align="center">
										<bean:write name="s" property="dj" />
									</td>
									<td align="center">
										<bean:write name="s" property="fdysh" />
									</td>
									<td align="center">
										<bean:write name="s" property="xysh" />
									</td>
									<td align="center">
										<bean:write name="s" property="xxsh" />
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
						<logic:equal value="yes" name="writeAble">													
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="showTopWin('jhzy_gygl.do?method=xjwmqsSq&xmdm=1&doType=xjqswh',800,470)"
								style="width:80px">
								增 加
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="xjqsupdate('modi')"
								style="width:80px">
								修 改
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="xjqsupdate('del')"
								style="width:80px">
								删 除
							</button>																			
						</logic:equal>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="dataExport()" style="width:80px">
							导出数据
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
			alert('删除成功！');
			document.getElementById('search_go').click();
		</script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script language="javascript">
			alert('删除失败！');
		</script>
	</logic:equal>
</html>
