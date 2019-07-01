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
		<script type="text/javascript" src="/xgxt/dwr/interface/yxglFun.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>	
		<script type="text/javascript">
			function checkRowData(){
				if(curr_row != null){
					yxglXyybdModi();
				}else{
					alert("请选择要报到的数据!");
				}			
			}
		</script>	
	</head>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
		
	<body onload="document.getElementById('ksh').focus()">
		<logic:notEmpty name="tag">
			<logic:equal value="no" name="tag">
				<script type="text/javascript">
					alert("对不起,不在迎新时间段内!");
					location.href="about:blank";
				</script>
			</logic:equal>
		</logic:notEmpty>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/yxglFunction.js"></script>
		<script language="javascript" src="js/sharedFunction.js"></script>
		<input type="hidden" id="flag" name="flag" value="<bean:write name="onload" />">
		<html:form action="yxgl_xyybd.do">
		<input type="hidden" id="realTable" name="realTable" value="view_newstuyyreportinfo" >
		<input type="hidden" id="tableName" name="tableName" value="view_newstuyyreportinfo" >
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:message bundle="yxgl" key="yxgl_xyybd" />
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
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<logic:equal name="userType" value="xy" scope="session">
									<html:select property="xydm" style="width:160px" styleId="xy"
										onchange="refreshForm('yxgl_xyybd.do');getfocus('search_go')" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="xy" scope="session">
									<html:select property="xydm" style="width:160px" styleId="xy"
										onchange="refreshForm('yxgl_xyybd.do');getfocus('search_go')">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:notEqual>
									&nbsp;&nbsp;专业：
									<html:select property="zydm" style="width:160px" styleId="zy"
										onchange="refreshForm('yxgl_xyybd.do');getfocus('search_go')">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;班级：
									<html:select property="bjdm" style="width:160px" styleId="bj"
										onchange="refreshForm('yxgl_xyybd.do');getfocus('search_go')">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>									
								</td>
								<td width="50" rowspan="3" align="center" valign="middle">
									<button class="button2" id="report_go"
										onclick=" yxglXsbdModi('yy')"
										style="height:40px;">
										报到
									</button>
								</td>
								<td width="50" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" id="search_go"
										onclick="search('yxgl_xyybd.do')"
										onkeypress="keyPressDo(this)" style="height:40px;">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;考生号：
									<html:text property="ksh" style="width:120px" onkeydown="if(event.keyCode==13)report_go.click();"></html:text>
									&nbsp;&nbsp;学号：
									<html:text property="xh" style="width:120px"></html:text>
									&nbsp;&nbsp;姓名：
									<html:text property="xm" style="width:120px"></html:text>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;是否体检：
									<html:select property="sftj" style="width:180px" styleId="sftj">
										<html:option value=""></html:option>
										<html:options collection="sftjList" property="en"
											labelProperty="cn" />
									</html:select>
									&nbsp;&nbsp;是否注射疫苗：
									<html:select property="sfzsym" style="width:180px" styleId="sfzsym">
										<html:option value=""></html:option>
										<html:options collection="sfzsymList" property="en"
											labelProperty="cn" />
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
								<logic:iterate id="tit" name="topTr" offset="0" length='1'>
										<td  style="display:none" id="<bean:write name="tit" property="en"/>"
											nowrap>
											<input type="hidden" value="<bean:write name="tit" property="cn" />" />
										</td>
									</logic:iterate>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="yxgltableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>						
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">	
									<tr onclick="rowOnClick(this);" style="cursor:hand"
										ondblclick="yxglXyybdModi()">
										<td style="display:none">
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>											
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">							
							<button class="button2" onclick="checkRowData();"
									style="width:80px">
								报 到 
							</button>	
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="cancleBd('xyy');"
									style="width:80px">
								撤销报到 
							</button>						
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="showTopWin('yxgl_xyybd_sdjyqy.do',500,300);"
								style="width:90px">
								设定检疫区域
							</button>							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="dataExport()" style="width:80px">
								导出数据
							</button>				
						</div>
					</center>
				</logic:equal>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>

