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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
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
	<base target="_self">
	<script language="javascript">

</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getPlan.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>	
		<html:form action="/stgl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：社会活动 - 社团管理 - 社团活动经费申请表
				</div>
			</div>
			 <logic:notPresent name="inserted">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！<bean:write name="errMessage" />
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/stgl.do?method=zhsq" />
				<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name='pkValue' />" />
				<input type="hidden" id="doType" name="doType" value="<bean:write name='doType' />" />
				<input type="hidden" id="xn" name="xn" value="<bean:write name='rs' property="xn" />" />
				<input type="hidden" id="nd" name="nd" value="<bean:write name='rs' property="nd" />" />
				<input type="hidden" id="xqdm" name="xqdm" value="<bean:write name='rs' property="xqdm" />" />
				<table width="100%" id="rsT" class="tbstyle">
					
					<thead>
						<tr style="height:22px">
							<td colspan="12" align="center">
								<b>填写申请表</b>
							</td>
						</tr>
					</thead>
					<tr style="height:22px">
						<td align="right">
							学年：
						</td>
						<td align="left" colspan="2">
							<bean:write name = "rs" property="xn"/>
						</td>
						<td align="right" >
							年度：
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="nd" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							学期：
						</td>
						<td align="left" colspan="2">
							<html:select name='rs' property="xqdm" style="width:90px" disabled="true"
											styleId="xqdm">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
							</html:select>
						</td>
						<td align="right" >
						</td>
						<td align="left" colspan="4">
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							<font color="red">*</font>社团名称：
						</td>
						<logic:present name= "sfstzh">
						<td align="left" colspan="2">
							<input type="hidden" id="stdm" name="stdm" value="<bean:write name='rs' property="stdm" />" />
							<html:select name='rs' property="stdm" style="width:90px" disabled="true"
											styleId="stdm">
											<html:option value=""></html:option>
											<html:options collection="stList" property="stdm"
												labelProperty="stmc" />
							</html:select>
						</td>
						</logic:present>
						<logic:notPresent name= "sfstzh">
						<td align="left" colspan="2">
							<html:select name='rs' property="stdm" style="width:90px" onchange="refreshForm('/xgxt/stgl.do?method=hdjfsq&refresh=on')"
											styleId="stdm">
											<html:option value=""></html:option>
											<html:options collection="stList" property="stdm"
												labelProperty="stmc" />
							</html:select>
						</td>
						</logic:notPresent>
						<td align="right">
							<font color="red">*</font>活动项目名称：
						</td>
						<td align="left" colspan="4">
							<html:select name='rs' property="pk" style="width:90px" onchange="refreshForm('/xgxt/stgl.do?method=hdjfsq&refresh=on')"
											styleId="stdm">
											<html:option value=""></html:option>
											<html:options collection="sthdList" property="pk"
												labelProperty="hdmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" >
							负责人姓名：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="xm" />
						</td>
						<td align="right">
							负责人<bean:message key="lable.xsgzyxpzxy" />：
						</td>
						<td align="left" colspan="4">
							<bean:write name = "rs" property="xymc"/>
						</td>
						
					</tr>
					<tr style="height:22px">
						<td align="right" >
							联系方式：
						</td>
						<td align="left" colspan="2">
							<bean:write name='rs' property="lxfs" />
						</td>
						<td align="right">
							指导老师姓名
						</td>
						<td align="left" colspan="4">
							<bean:write name='rs' property="lsxm" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" >
							经费用途(1)：
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="jfyt1" />
						</td>
						<td align="right" >
							金额(1)：
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="je1" onkeypress="return numInputValue(this,4,event)"/>
						</td>
					</tr>
						<tr style="height:22px">
						<td align="right" >
							经费用途(2)：
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="jfyt2" />
						</td>
						<td align="right" >
							金额(2)：
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="je2" onkeypress="return numInputValue(this,4,event)"/>
						</td>
					</tr>
						<tr style="height:22px">
						<td align="right" >
							经费用途(3)：
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="jfyt3" />
						</td>
						<td align="right" >
							金额(3)：
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="je3" onkeypress="return numInputValue(this,4,event)"/>
						</td>
					</tr>
						<tr style="height:22px">
						<td align="right" >
							经费用途(4)：
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="jfyt4" />
						</td>
						<td align="right" >
							金额(4)：
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="je4" onkeypress="return numInputValue(this,4,event)"/>
						</td>
					</tr>
						<tr style="height:22px">
						<td align="right" >
							经费用途(5)：
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="jfyt5" />
						</td>
						<td align="right" >
							金额(5)：
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="je5" onkeypress="return numInputValue(this,4,event)"/>
						</td>
					</tr>
						<tr style="height:22px">
						<td align="right" >
							经费用途(6)：
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="jfyt6" />
						</td>
						<td align="right" >
							金额(6)：
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="je6" onkeypress="return numInputValue(this,4,event)"/>
						</td>
					</tr>
					<logic:notEmpty name = "doType">
					<tr style="height:22px">
						<td align="right" >
							以付金额：
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="yfje" onkeypress="return numInputValue(this,4,event)"/>
						</td>
						<td align="right" >
							经手人：
						</td>
						<td align="left" colspan="4">
							<html:text name='rs' property="jsr" />
						</td>
					</tr>
					<tr align="left" style="height:22px">
							<td align="right">
								院团委意见：
							</td>
							<td colspan="7">
								<html:textarea name='rs' property='ytwyj' style="width:99%"
									rows='6' />
							</td>
					</tr>
					<tr style="height:22px">
						<td align="right" >
							报销情况：
						</td>
						<td align="left" colspan="2">
							<html:text name='rs' property="bxqk" onkeypress="return numInputValue(this,4,event)"/>
						</td>
						<td align="right">
							学校审核：
						</td>
						<td align="left" colspan="4">
							<html:select name='rs' property="shzt" style="width:90px"
											styleId="shzt">
											<html:options collection="shztList" property="en"
												labelProperty="cn" />
							</html:select>
						</td>
						
					</tr>
					</logic:notEmpty>
				</table>
				<div class="buttontool" align="center">
					<button class="button2"
						onclick="szsxDataDoSave('/xgxt/stgl.do?method=saveHdJfSq','pk')">
						提 交 申 请
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:notPresent name="showhzy">
					<button class="button2" onclick="window.open('sxjy_report.do?type=sthdjfsh&pk='+document.getElementById('pkValue').value)">
						打 印 报 表
					</button>
					</logic:notPresent>
				</div>
			</logic:notEmpty>
			
			</logic:notPresent>
			<logic:present name="inserted">
				<logic:present name="act">
				<logic:equal name="inserted" value="ok">
    			<script>
    				alert("提交成功！");
	    				if(opener){
	    					opener.document.getElementById("search_go").click();
							Close();
						}
	    			</script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
		    			alert("提交失败！");
		    			if(opener){
		    				alert(opener);
		    				opener.document.getElementById("search_go").click();
							Close();
						}
    			</script>
				</logic:equal>
				</logic:present>
				<logic:notPresent name="act">
				<logic:equal name="inserted" value="ok">
    			<script>
    				alert("提交成功！");
    				refreshForm('stgl.do?method=hdjfsq');
    			</script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("提交失败！");
    			alert(<bean:write name='errMessage'/>);
    			refreshForm('stgl.do?method=hdjfsq');
    </script>
				</logic:equal>
				</logic:notPresent>
			</logic:present>
		</html:form>
	</body>
</html>
