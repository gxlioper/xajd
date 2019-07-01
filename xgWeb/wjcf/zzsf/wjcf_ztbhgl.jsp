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
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>				

		<html:form action="/wjcf_ZtBhGl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<logic:equal value="11049" name="xxdm">
						当前所在位置：
					违纪处理 - 数据维护 - 主题班会
					</logic:equal>
					<logic:notEqual value="11049" name="xxdm">
						当前所在位置：
					违纪处分 - 数据维护 - 处分后表现
					</logic:notEqual>
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						查 询
					</legend>
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="session"/>" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
						<input type="hidden" id="xyV" name="xyV" />
						<input type="hidden" id="zyV" name="zyV" />
						<input type="hidden" id="bjV" name="bjV" />
					<table width="100%" class="tbstyle" >
						<thead>
							<tr>
								<td>
								年级：
									<html:select property="nj" style="width:90px;padding-left:80px" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
									&nbsp;&nbsp;&nbsp;学号：
									<html:text property="xh" styleId="xh" style="width:85px"></html:text>
									&nbsp;&nbsp;&nbsp;姓名：
									<html:text property="xm" styleId="xm" maxlength="15"></html:text>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="go" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/wjcf_ZtBhGl.do')">
										查询
									</button>
								</td>
							</tr>
						 <tr>
								  <td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:220px" styleId="xy" onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;专业：
								<html:select property="zydm" style="width:200px" styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" 
									labelProperty="zymc"/>
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" styleId="bj" style="width:170px">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc"/>
								</html:select>
								</td>  
							</tr> 
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
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
							<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
									<tr align="center" onclick="rowOnClick(this);" ondblclick="chkPriseOne2('wjcf_ZtBhGl_load.do?doType=load&xh=',510,478)" style="cursor:hand">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="2">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<logic:equal value="yes" name="writeAble" scope="request">
							<button type="button" class="button2"
								onclick="myView_oper('save','wjcf_ZtBhGl_load.do?doType=')"
								style="width:120px">
								<logic:equal value="11049" name="xxdm">
									班会记录
								</logic:equal>
								<logic:notEqual value="11049" name="xxdm">
									添加会议记录
								</logic:notEqual>
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="myView_oper('modi','wjcf_ZtBhGl_load.do?doType=')"
							style="width:80px">
							修 改
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="myView_oper('del','wjcf_ZtBhGl_oper.do?doType=')"
							style="width:80px">
							删 除
						</button>
							</logic:equal>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="myView_oper('print','wjcf_ZtBhGl_print.do?doType=')"
								style="width:80px">
								打印/预览
							</button>
						</div>
					</center>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
					function myView_oper(doType,url)
					{
						url += doType;
   						var xh="";
   						url += "&xh=";
   						if(doType=="save"){
      						url +=xh;
      						showTopWin(url,510,485);
  						 }else if(doType=='print'){
  						 	if (curr_row==null){
  						 		window.open(url);
  						 	}else{
  						 		url+=curr_row.getElementsByTagName("input")[0].value;
  						 		url+="&rq=";
  						 		url+=curr_row.cells[8].innerText;
  						 		window.open(url);
  						 	}
  						 }else{
  						 	if (doType=='modi'){
								if(curr_row == null){
								alert("请选择要修改的数据！\n（单击相应的行）");
								return false;
							}else{
								url+=curr_row.getElementsByTagName("input")[0].value;
								url+="&rq=";
  						 		url+=curr_row.cells[8].innerText;
							}
							showTopWin(url,510,478);
						}if (doType=='del'){
							if(curr_row == null){
								alert("请选择要删除的数据！\n（单击相应的行）");
								return false;
							}else{
								url+=curr_row.getElementsByTagName("input")[0].value;
								url+="&rq=";
  						 		url+=curr_row.cells[8].innerText;
								if (confirm("您确定要删除该行数据吗？"))
								{
									refreshForm(url);	
								}else
									return;
								}
							}
  						 }
					}
					function chkPriseOne2(url, w, h) {
						if (w == null) {
							w = 700;
						}
						if (h == null) {
							h = 500;
						}	
						if (curr_row == null) {
							alert("请选择要操作的行！");
							return false;
						} else {		
							var val = curr_row.cells[0].getElementsByTagName("input")[0].value;
							url+=val;
							url+="&rq=";
							url+=curr_row.cells[8].innerText;
							showTopWin(url, w, h);
						}
					}
		</script>
		<logic:equal value="ok" name="done">
	<script type="text/javascript">
		document.getElementById("xh").value="";
		document.getElementById("xm").value="";
		document.getElementById('search_go').click();   
	</script>
</logic:equal>
<logic:equal value="no" name="done">
		<script type="text/javascript">.
		alert("操作失败！");
		document.getElementById('search_go').click();   
	</script>
</logic:equal>
	</body>
</html>


