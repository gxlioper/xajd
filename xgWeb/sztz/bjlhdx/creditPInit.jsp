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

	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript"  src="js/AjaxFunction.js"></script>		

		<center>
			<html:form action="/bjlhdx_sztz" method="post">
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />			
				<div class="title">
					<div class="title_img" id="title_m">
						当前所在位置：素质拓展 - 参数设置 - 信用分初始化
					</div>
				</div>
				<fieldset>
					<legend>
						信用分初始化
					</legend>
					<table width="80%" align="center" class="tbstyle">
						<thead>
							<tr align="center">
								<td height="25" colspan="2">
									信用分初始化设定
								</td>
							</tr>
						</thead>
						<tr>
							<td width="45%" height="25" align="right">
								年级：
							</td>
							<td height="25" align="left">
								<html:select property="nj" style="width:60px"
									onchange="initZyList();initBjList();refreshForm('/xgxt/bjlhdx_sztz.do?method=creditPointInit');">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td width="45%" height="25" align="right">
								<bean:message key="lable.xsgzyxpzxy" />：
							</td>
							<td height="25" align="left">

								<html:select property="xydm" style="width:180px" styleId="xy"
									onchange="initZyList();initBjList();refreshForm('/xgxt/bjlhdx_sztz.do?method=creditPointInit');">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								专业：
							</td>
							<td align="left">
								<html:select property="zydm" style="width:180px" styleId="zy"
									onchange="initBjList();refreshForm('/xgxt/bjlhdx_sztz.do?method=creditPointInit');">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								班级：
							</td>
							<td align="left">
								<html:select property="bjdm" style="width:120px" styleId="bj"
									onchange="refreshForm('/xgxt/bjlhdx_sztz.do?method=creditPointInit');">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								初始分值：
							</td>
							<td align="left">
								<html:text property="csf" styleId="csf"
									onkeypress='return sztzNumInputValue(this,6,event)'></html:text>
							</td>
						</tr>
						<thead>
							<tr>
								<td height="25" colspan="3" align="center">
									<button class="button2" id="buttonSave" onclick="saveCsf()">
										保存
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
							结果查询
						</legend>
						<table width="80%" align="center" class="tbstyle">
							<thead>
								<tr align="center">
									<td>
										年级
									</td>
									<td>
										<bean:message key="lable.xsgzyxpzxy" />
									</td>
									<td>
										专业
									</td>
									<td>
										班级
									</td>
									<td>
										初始分值
									</td>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="xfsbManageView()">

									<logic:iterate id="v" name="s">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<logic:notEmpty name="done">
					<logic:equal name="done" value="true">
						<script>alert("初始化成功!")</script>
					</logic:equal>
					<logic:equal name="done" value="false">
						<script>alert("初始化失败!")</script>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
	<script type="text/javascript">
	 function saveCsf(){
	   	var nj = $("nj").value;
	   	var xydm = $("xydm").value;
	   	var zydm = $("zydm").value;
	   	var bjdm = $("bjdm").value;    
	    var csf  = $("csf").value;
	    var nj   = document.forms[0].nj.options[document.forms[0].nj.selectedIndex].text;
	    var xymc = document.forms[0].xydm.options[document.forms[0].xydm.selectedIndex].text;
	    var zymc = document.forms[0].zydm.options[document.forms[0].zydm.selectedIndex].text;
	    var bjmc = document.forms[0].bjdm.options[document.forms[0].bjdm.selectedIndex].text;
	    if(csf==""){
	       alert("初始分值不能为空！");
	       return false;
	    }
	    if(csf.match(/^\d+\.{0,1}\d{0,3}$/)==null){
		  alert("初始分值需为数字！");
		  return false;
	    }
	    var context = "此操作将对";
	    if(nj==""&&xydm==""&&zydm==""&&bjdm==""){	       
	       context+="全校全体";
	    }else{
	       if(nj!=""){
	           context+=nj+"年级 ";
	       }
	       if(xydm!=""){
	           context+=xymc+" ";
	       }
	       if(zydm!=""){
	           context+=zymc+" ";
	       }
	       if(bjdm!=""){
	           context+=bjmc;
	       }	       
	    }
	    context += "学生\n进行信用分初试化！\n初始分值为"+csf+"。\n\n确定要执行此操作吗？";
	    if(confirm(context)){
	       refreshForm("/xgxt/bjlhdx_sztz.do?method=creditPointInit&doType=save");
	       $("buttonSave").disabled=true;
	    }
	 }
	</script>
</html>
