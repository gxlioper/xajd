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
	
	<script language="javascript" src="/xgxt/js/function.js"></script>
	<script language="javascript" src="/xgxt/js/stuinfoFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript">
	function expDate_lx(){
		document.forms[0].action = "/xgxt/leaveExpDate.do";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}	
	</script>
	<body onload="check_user();">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/listingQuery.do" method="post">
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="session"/>" />	
				<input type="hidden" name="xyV" value=""/>
				<input type="hidden" name="zyV" value=""/>
				<input type="hidden" name="bjV" value=""/>		
				<input type="hidden" name="tableName" value="view_xslxxx"/>		
				<input type="hidden" name="realTable" value="xslxxxb"/>
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置: 学生离校-离校清单查询						
					</div>
				</div>
				<logic:notEqual value="student" name="user">
					<fieldset>
						<legend>
							查 询
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">										
										年级：
										<logic:notPresent name="njList">
											<html:select property="nj" style="width:90px"
											onchange="initZyList();initBjList();" disabled="disabled">
											<html:option value=""></html:option>
											</html:select>
										</logic:notPresent>
										
										<logic:present name="njList">
										<html:select property="nj" style="width:90px"
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>		
										</logic:present>								
										&nbsp;&nbsp;学号：
										<html:text property="xh" />
										&nbsp;&nbsp;姓名：
										<html:text property="xm" />
										
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/listingQuery.do')">
											查询
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>										
										<bean:message key="lable.xsgzyxpzxy" />：
										<logic:notPresent name="xyList">
											<html:select property="xydm" style="width:180px"
											styleId="xy" disabled="disabled">
											<html:option value=""></html:option>
										</html:select>
										</logic:notPresent>
										
										<logic:present name="xyList">
										<html:select property="xydm" style="width:180px"
											onchange="refreshForm('/xgxt/listingQuery.do')" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										</logic:present>
										
										&nbsp;&nbsp;专业：
										<logic:notPresent name="zyList">
											<html:select property="zydm" style="width:180px" styleId="zy" disabled="disabled">
											<html:option value=""></html:option>
										</html:select>
										</logic:notPresent>
										<logic:present name="zyList">
											<html:select property="zydm" style="width:180px" styleId="zy"
											onchange="refreshForm('/xgxt/listingQuery.do')">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>
										</logic:present>									
										
										&nbsp;&nbsp;班级：
										<logic:notPresent name="bjList">
											<html:select property="bjdm" style="width:120px" styleId="bj" disabled="disabled">
												<html:option value=""></html:option>												
											</html:select>
										</logic:notPresent>
										<logic:present name="bjList">
											<html:select property="bjdm" style="width:120px" styleId="bj">
												<html:option value=""></html:option>
												<html:options collection="bjList" property="bjdm"
													labelProperty="bjmc" />
											</html:select>
										</logic:present>
										</td>
								</tr>
								<tr>
									<td>
										审核单位：
										<html:select property="shdw">
											<html:option value="">--所有单位--</html:option>
											<html:option value="xdzsyj">系党总支书记</html:option>
											<html:option value="bzryj">班主任</html:option>
											<html:option value="tsgyj">图书馆</html:option>
											<html:option value="ywsyj">医务室</html:option>
											<html:option value="cwcyj">财务处</html:option>
											<html:option value="jwcyj">教务处</html:option>
											<html:option value="xscyj">学生工作部</html:option>
											<html:option value="sqglbyj">滨江后勤公司</html:option>
										</html:select>
										&nbsp;&nbsp;
										审核结果：
										<html:select property="shjg">
											<html:option value=""></html:option>
											<html:option value="通过">通过</html:option>
											<html:option value="不通过">不通过</html:option>
											<html:option value="未审核">未审核</html:option>
										</html:select>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
				</logic:notEqual>
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
										<logic:iterate id="tit" name="topTr">
											<td id="<bean:write name="tit" property="cn"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     ">
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>	
				
				<logic:present name="writeAble">
				<logic:equal value="yes" name="writeAble">
				<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								导入数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="button2"
								onclick="expDate_lx()"
								style="width:80px">
								导出数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="expTab('rsTable','学生离校审核信息','')">
								打印列表
							</button>
				</div>
				</logic:equal>
				</logic:present>
				<script type="text/javascript" src="js/bottomButton.js"></script>				
			</html:form>
		</center>
	</body>
</html>
