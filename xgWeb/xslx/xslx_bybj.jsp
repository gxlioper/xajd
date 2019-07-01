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
	
	function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("xsxx")[0].checked;
      }
    }
	
	function batch(url){
		var RowsStr="!!";
		
		for (i=0; i<document.getElementsByName("pk").length; i++){
	    	if(document.getElementsByName("pk")[i].checked){
	    		RowsStr+=document.getElementsByName("pk")[i].value+"!!";
	    	}
		}
		//document.forms[0].delPk.value = RowsStr;
		
		if (RowsStr=="!!"){
			alert("请选择要批量操作的记录！");
			return false;
		}
		
		if (!confirm("确定要操作所选记录？")){
			return false;
		}
		url += "&pk=";
		url += RowsStr;
		refreshForm(url);		
}


	</script>
	<body >
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<center>
			<html:form action="/xdzsj.do" method="post">
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="session"/>" />
				<input type="hidden" name="tableName" value="view_xdzsjxxb"/>		
				<input type="hidden" name="realTable" value="xdzsjxxb"/>
				<input type="hidden" name="xyV" value=""/>
			    <input type="hidden" name="zyV" value=""/>
			    <input type="hidden" name="bjV" value=""/>	
				<div class="title">
					<div class="title_img" id="title_m">
						当前位置: 学生离校 - 毕业班级信息					
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
										<html:select property="nj" style="width:180px"
											styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>	
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" style="width:180px"
											styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>		
									&nbsp;&nbsp;专业：
										<html:select property="zydm" style="width:180px"
											styleId="zy">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select>	
										<br/><br/>
										班级：
										<html:select property="bjdm" style="width:180px"
											styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>							
									</td>
									<td width="10" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/bybjfp.do')">
											查询
										</button>
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
							<font color="blue">提示：单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">		
										<td>
											<input type="checkbox" name="xsxx" value="all"
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
								<tr onclick="rowOnClick(this)">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="checkbox" name="pk"
													value="<bean:write name="v"/>">
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
				</logic:notEmpty>	
				
				<logic:present name="writeAble">
				<logic:equal value="yes" name="writeAble">
				<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button class="button2"
								onclick="batch('/xgxt/bybjfp.do?doType=save')"
								style="width:120px">
								设置为毕业班
							</button>							
				</div>
				</logic:equal>
				</logic:present>
				<logic:present name="result">
				<logic:equal value="true" name="result">
				<script>
					alert("操作成功！");
					document.getElementById('search_go').click();
				</script>
				</logic:equal>
				<logic:equal value="false" name="result">
				<script>
					alert("操作失败！");
				</script>
				</logic:equal>
				</logic:present>
				<script type="text/javascript" src="js/bottomButton.js"></script>				
			</html:form>
		</center>
	</body>
</html>
