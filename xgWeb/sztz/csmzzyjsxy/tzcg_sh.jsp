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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/csmz_sztz.do?method=tzcg_sh" method="post">
	    <input type="hidden" name="pkValue" id="pkValue" value="<bean:write name="pkValue" scope="request"/>">
		<input type="hidden" name="xmid" id="xmid" value="<bean:write name='rs' property="xmid" />">
		<input type="hidden" name="xmjb" id="xmjb" value="<bean:write name='rs' property="xmjb" />">	
		<input type="hidden" name="jxlb" id="jxlb" value="<bean:write name='rs' property="jxlb" />">
		
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name="tips" scope="request"/>
				</div>
			</div>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							单个审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td width="15%" align="right" nowrap >
						学号：
				    </td>
					<td align="left">
						<html:text name='rs' property="xh" styleId="xh" readonly="true" />
					</td>
					<td align="right">
						项目名称：
					</td>
					<td align="left">
					<html:text name='rs' property="xmmc" styleId="xmmc" readonly="true" style="width:250px"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name='rs' property="xm" />
					</td>
					<td align="right">
						学年：
					</td>
					<td align="left">
						<bean:write name='rs' property="xn" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">
						<bean:write name='rs' property="xb" />
					</td>
					<td align="right">
						学期：
					</td>
					<td align="left">
						<bean:write name='rs' property="xq" />
					</td>
				</tr>	
				 <tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name='rs' property="xymc" />
					</td>
					<td align="right">
						所属科目：
					</td>
					<td>
						<bean:write name='rs' property="kmmc" />
					</td>				
				<tr style="height:22px">
					<td align="right">
						专业：
					</td>
					<td align="left">
						<bean:write name='rs' property="zymc" />
					</td>
					<td align="right">
						活动(项目)级别：
					</td>
					<td align="left">
						<bean:write name='rs' property="xmjb" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name='rs' property="bjmc" />
					</td>
					<td align="right">
						组织单位：
					</td>
					<td align="left">
						<bean:write name='rs' property="zzdw" />
					</td>
				</tr>
				<tr align="left">
					<td align="right">
						参与角色：
					</td>
					<td align="left">
						<bean:write name='rs' property="cyjs" />
					</td>
					<td align="right">
						组办时间：
					</td>
					<td align="left">
						<bean:write name='rs' property="zbsj" />
					</td>
				</tr>	
				<tr style="height:22px">
					<td align="right">
					申报时间：
					</td>
					<td align="left">
						<bean:write name='rs' property="sbsj" />
					</td>
					<td align="right">
						审核：
					</td>
					<td align="left">
					<logic:notPresent name="isBJXM">
					    <html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>	
					</logic:notPresent>											
					</td>
				</tr>
				<tr align="left">
					<td align="right">
                     获奖奖项：
					</td>
					<td align="left">
                    <bean:write name='rs' property="jxm" />
					</td>
					<td align="right">
				    项目所属部门：
					</td>
					<td align="left">
					<bean:write name='rs' property="sqbmmc" />	
					</td>
				</tr>
				<tr align="left">
					<td align="right">
                    
					</td>
					<td align="left">
                  
					</td>
					<td align="right">
				   项目负责人：
					</td>
					<td align="left">
					<bean:write name='rs' property="xmsbrmc" />	
					</td>
				</tr>				
				<tr align="left">
					<td align="right">
						项目描述：
					</td>
					<td colspan="3">
						<bean:write name='rs' property="xmms" />
					</td>
				</tr>
				<tr align="left">
					<td align="right">
						成果描述：
					</td>
					<td colspan="3">
						<html:textarea name='rs' property='cgms' styleId="cgms"
							style="width:99%" rows='5' readonly="true" />
					</td>
				</tr>				
     		</table>
			<div class="buttontool" align="center">
               <logic:notPresent name="isBJXM">			
 				<button class="button2"
					onclick="refreshForm('/xgxt/csmz_sztz.do?method=tzcg_sh&doType=save');this.disabled=true"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notPresent>
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</html:form>
		<logic:equal value="yes" name="done">
			<script language="javascript">
	         	alert("操作成功！");
	         	Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
	         </script>
		</logic:equal>
	    <logic:equal value="no" name="done">
         	<script language="javascript">
	         	alert("操作失败！");
	         	Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
	        </script>
	   </logic:equal>
	</body>
</html>
