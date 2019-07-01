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
	<body onload="xyDisabled('xy');">
		<center>
			<script language="javascript" src="/xgxt/js/function.js"></script>
			<script language="javascript" src="/xgxt/js/jsFunction.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="/xgxt/js/jxglFunction.js"></script>
			<script language="javascript" src="js/AjaxFunction.js"></script>
			
			<html:form action="/jxgl" method="post">
				<input type="hidden" id="userType" name="userType"  value="<bean:write name="userType"/>" />
				<input type="hidden" id="realTalbe" name="realTable"  value="<bean:write name="realTable"/>" />
				<input type="hidden" id="tableName" name="tableName"  value="<bean:write name="tableName"/>" />
				<input type="hidden" id="xyV" name="xyV"  value="" />
				<input type="hidden" id="zyV" name="zyV"  value="" />
				<input type="hidden" id="bjV" name="bjV"  value="" />
				<input type="hidden" id="delPk" name="delPk"  value="" />				
				
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：军训管理 - 信息维护 - 学生参训干部
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
									<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" styleId="xy" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" labelProperty="xymc" property="xydm"/>
									</html:select>
									专业：
									<html:select property="zydm" styleId="zy" onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" labelProperty="zymc" property="zydm"/>
									</html:select>																	
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="document.forms[0].go.value='go';refreshForm('/xgxt/jxgl.do?method=jxxscxgbSearch');">
										查询
									</button>
								</td>
							</tr>	
							<tr>
							<td>
								班级：
									<html:select property="bjdm">
										<html:option value=""></html:option>
										<html:options collection="bjList" labelProperty="bjmc" property="bjdm"/>
									</html:select>	
								军训年度：
									<html:select property="jxnd">
										<html:option value=""></html:option>
										<html:options collection="xnList" labelProperty="nd" property="nd"/>
									</html:select>
								负责连队：
									<html:select property="fzlddm">
										<html:option value=""></html:option>
										<html:options collection="ldList" labelProperty="mc" property="dm"/>
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
							<font color="blue">提示：双击一行可以选定；单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">		
									<td>
										<input type="checkbox" name="checkAll" value="all" onclick="check()">
									</td>							
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" style="cursor:hand"
										ondblclick="sendJsghInfo()">
										<td>
										
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="checkOne" value="<bean:write name="v"/>">
											<input type="hidden" value="<bean:write name="v" />"/>
										</logic:iterate>										
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
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
							<button type="button" class="button2" onclick="showTopWin('jxgl.do?method=showJxcxgbAdd',600,400)"
								style="width:80px">
								增 加
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="modiInfo('jxgl.do?method=showJxcxgbModi',600,400)"
								style="width:80px">
								修 改
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="batch('jxgl.do?method=delJxcxgb')"
								style="width:80px">
								删 除
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="showTopWin('/xgxt/data_import.do',600,480)"
								style="width:80px">
								导入数据
							</button>						
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport()" style="width:80px">
								导出数据
							</button>
						</logic:equal>
					</div>
				</center>
				<logic:present name="result">
					<logic:equal value="true" name="result">
					<script>
						alert('操作成功!');
						Close();						
					</script>
					</logic:equal>
					<logic:equal value="false" name="result">
						<logic:notEmpty name="mes">
						<input name="mes" id="mes" value="${msg}"/>
						<script>
							alert(document.getElementById("msg").value);
						</script>
						</logic:notEmpty>
						<logic:empty name="mes">
						<script>
							alert('操作失败！');
						</script>
						</logic:empty>
					</logic:equal>
				</logic:present>
				
			</html:form>
			<div id="tmpdiv"></div>
		</center>
	</body>
	<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
	</script>
</html>
