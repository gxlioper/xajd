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
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/xbemyStuStatus.do" method="post">		
		<input type="hidden" name="realTable" id="realTable" value="stu_archives_auditing"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生信息 - 历届学生档案　-　转学审核
				</div>
			</div>			
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							转入单个审核
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" >
						学号：
					</td>
					<td align="left">
						<bean:write name="rs" property="xh" />
						<input type="hidden" value="<bean:write name="rs" property="xh" />" name="xh" id="xh"/>
						<input type="hidden" value="<bean:write name="rs" property="sqrq" />" name="sqrq" id="sqrq"/>
					</td>
					<td align="right">
						转出学校名称：
					</td>
					<td align="left">
						<bean:write name="rs" property="zcxxmc" />						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td align="right">
						转出专业：
					</td>
					<td align="left">
						<bean:write name="rs" property="zczymc" />						
					</td>
				</tr>				
				<tr style="height:22px">
					<td align="right">
						性别：
					</td>
					<td align="left">&nbsp;
						<bean:write name="rs" property="xb"/>
					</td>
					<td align="right">
						转出年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="zcnj" />						
					</td>	
					
				</tr>
				<tr style="height:22px">
					<td align="right">
						准考证号：
					</td>
					<td align="left">
						<bean:write name="rs" property="zkzh" />
					</td>
					<td align="right">
						转出学历层次：</td>
					<td align="left">
						<bean:write name="rs" property="zcxlcc" />
					</td>				
				</tr>
				<tr style="height:22px">
					<td align="right">
						民族：
					</td>
					<td align="left">
						<bean:write name="rs" property="mzmc"/>						
					</td>
					<td align="right">
						转出学制：
					</td>
					<td align="left">
						<bean:write name="rs" property="zcxz"/>
					</td>				
				</tr>
				<tr style="height:22px">
					<td align="right">
						联系电话：
					</td>
					<td align="left">
						<bean:write name="rs" property="lxdh"/>
					</td>
					<td align="right">
						转入学校名称：
					</td>
					<td align="left">
						<bean:write name="rs" property="zrxxmc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						入学时间：
					</td>
					<td align="left">
						<bean:write name="rs" property="rxsj"/>
					</td>
					<td align="right">
						转入<bean:message key="lable.xsgzyxpzxy" />名称：
					</td>
					<td align="left">
						<bean:write name="rs" property="zrzymc"/>
					</td>				
				</tr>
				<tr  style="height:22px">
					<td align="right">
						转入专业名称：
					</td>
					<td align="left">
						<bean:write name="rs" property="zrzymc"/>
					</td>	
				    <td align="right">转入学制：</td>
				    <td align="left">
						<bean:write name="rs" property="zrxz"/>
					</td>
				</tr>	
				<tr>
					<td align="right">转入班级：
					</td>
					<td align="left">
						<bean:write name="rs" property="zrbjmc"/>
					</td>
					<td align="right">转入学历层次：
					</td>
					<td align="left">
						<bean:write name="rs" property="zrxlcc"/>
					</td>					
				</tr>
				<tr>
					<td align="right">转入年级：
					</td>
					<td align="left">
						<bean:write name="rs" property="zrnj"/>
					</td>
					<td align="right">学籍状态：
					</td>
					<td align="left">
						<bean:write name="rs" property="xjztm"/>
					</td>					
				</tr>
				<tr>
					<td align="right">曾用名：
					</td>
					<td align="left">
						<bean:write name="rs" property="cym"/>
					</td>
					<td align="right">辅修专业：
					</td>
					<td align="left">
						<bean:write name="rs" property="fxzy"/>
					</td>					
				</tr>
				<tr>
					<td align="right">专业方向：
					</td>
					<td align="left">
						<bean:write name="rs" property="zyfx"/>
					</td>
					<td align="right">辅修专业方向：
					</td>
					<td align="left">
						<bean:write name="rs" property="fxzyfx"/>
					</td>					
				</tr>
				<tr>
					<td align="right">培养方向：
					</td>
					<td align="left">
						<bean:write name="rs" property="pyfx"/>
					</td>
					<td align="right">学校所在地：
					</td>
					<td align="left">
						<bean:write name="rs" property="xxszd"/>
					</td>					
				</tr>
				<tr>
					<td align="right">专业类别：
					</td>
					<td align="left">
						<bean:write name="rs" property="zylb"/>
					</td>	
					<td align="right">当前所在级：
					</td>
					<td align="left">
						<bean:write name="rs" property="dqszj"/>
					</td>									
				</tr>
				<tr>
					<td align="right">办学类型：
					</td>
					<td align="left">
						<bean:write name="rs" property="bxlx"/>
					</td>	
					<td align="right">办学形式：
					</td>
					<td align="left">
						<bean:write name="rs" property="bxxs"/>
					</td>									
				</tr>
				<tr>
					<td align="right">学习形式：
					</td>
					<td align="left">
						<bean:write name="rs" property="xxxs"/>
					</td>	
					<td align="right">招生季节：
					</td>
					<td align="left">
						<bean:write name="rs" property="zsjj"/>
					</td>									
				</tr>
				<tr>
					<td align="right">考生号：
					</td>
					<td align="left">
						<bean:write name="rs" property="ksh"/>
					</td>	
					<td align="right">生源地：
					</td>
					<td align="left">
						<bean:write name="rs" property="syd"/>
					</td>									
				</tr>
				<tr>
					<td align="right">政治面貌：
					</td>
					<td align="left">
						<bean:write name="rs" property="zzmmmc"/>
					</td>	
					<td align="right">入学方式：
					</td>
					<td align="left">
						<bean:write name="rs" property="rxfs"/>
					</td>									
				</tr>
				<tr>
					<td align="right">身份证号：
					</td>
					<td align="left">
						<bean:write name="rs" property="sfzh"/>
					</td>	
					<td align="right">籍贯：
					</td>
					<td align="left">
						<bean:write name="rs" property="jg"/>
					</td>									
				</tr>
				<tr>
					<td align="right">出生地：
					</td>
					<td align="left">
						<bean:write name="rs" property="csd"/>
					</td>	
					<td align="right">出生日期：
					</td>
					<td align="left">
						<bean:write name="rs" property="csrq"/>
					</td>									
				</tr>
				<tr>
					<td align="right">校长姓名：
					</td>
					<td align="left">
						<bean:write name="rs" property="xzxm"/>
					</td>	
					<td align="right">毕业日期：
					</td>
					<td align="left">
						<bean:write name="rs" property="byrq"/>
					</td>									
				</tr>
				<tr>
					<td align="right">毕结业结论：
					</td>
					<td align="left">
						<bean:write name="rs" property="bjyjl"/>
					</td>	
					<td align="right">证书编号：
					</td>
					<td align="left">
						<bean:write name="rs" property="zsbh"/>
					</td>									
				</tr>
				<tr>
					<td align="right">证书编号：
					</td>
					<td align="left">
						<bean:write name="rs" property="zsbh"/>
					</td>	
					<td align="right">证书序列号：
					</td>
					<td align="left">
						<bean:write name="rs" property="zsxlh"/>
					</td>						
				</tr>
				<tr>
					<td align="right">学位：
					</td>
					<td align="left">
						<bean:write name="rs" property="xw"/>
					</td>	
					<td align="right">学位证书编号：
					</td>
					<td align="left">
						<bean:write name="rs" property="xwzsbh"/>
					</td>						
				</tr>
				<tr>
					<td align="right">替换标识：
					</td>
					<td align="left">
						<bean:write name="rs" property="thbs"/>
					</td>	
					<td align="right">学位证书序列号：
					</td>
					<td align="left">
						<bean:write name="rs" property="xwzsxlh"/>
					</td>						
				</tr>
				<tr>
					<td align="right">审核标记：
					</td>
					<td align="left">
						<bean:write name="rs" property="shbj"/>
					</td>	
					<td align="right">打印标记：
					</td>
					<td align="left">
						<bean:write name="rs" property="dybj"/>
					</td>						
				</tr>
				
				<tr>
					<td align="right">备注：
					</td>
					<td align="left" colspan="3">
						<bean:write name="rs" property="bz"/>
					</td>						
				</tr>
				<tr style="height:22px">
				  <td align="right">申请理由：</td>
				  <td colspan="3" align="left">
				  <bean:write name="rs" property="sqly"/>
				  </td>
			  </tr>
				<tr style="height:22px">
				  <td align="right"> 审核：</td>
				  <td align="left">
				  	<html:select property="xxsh" name="rs"> 
				  		<html:option value="未审核">未审核</html:option> 
				  		<html:option value="通过">通过</html:option> 
				  		<html:option value="不通过">不通过</html:option> 
				  </html:select> 
				  </td>				 
				  <td align="right">
				  	自动转入学生基本信息库：
				  </td>
				  <td align="left">
				  <html:radio property="zdzrbj" value="1">是</html:radio>
				  <html:radio property="zdzrbj" value="0">否</html:radio>
				  </td>
			  </tr>
				<tr  style="height:22px">
				  <td align="right">转出学校意见： </td>
				  <td align="left" colspan="3">
				  	<html:textarea property="zcxxyj" name="rs" style="width:100%;height:45px"/>
				  </td>
			  </tr>
				<tr  style="height:22px">
				  <td align="right">转入学校意见：</td>
				  <td align="left" colspan="3">
					<html:textarea property="zrxxyj" name="rs" style="width:100%;height:45px"/>
				  </td>
			  </tr>
				<tr  style="height:22px">
				  <td align="right">转出省教育厅意见：</td>
				  <td align="left" colspan="3">
					<html:textarea property="zcsjytyj" name="rs" style="width:100%;height:45px"/>
				  </td>
			  </tr>
				<tr  style="height:22px">					
					<td align="right">转入省教育厅意见：</td>
					<td align="left" colspan="3">
					<html:textarea property="zrsjytyj" name="rs" style="width:100%;height:45px"/>
					</td>
				</tr>
				<html:hidden property="zxlx" name="rs"/>
			</table>
			<div class="buttontool" align="center">			
			<logic:equal value="yes" name="writeAble">
				<logic:notEqual value="xy" name="userType" scope="session">
				<button class="button2"
					onclick="refreshForm('xbemyStuStatus.do?method=stuTransferAuditing&doType=save')"
					style="width:80px" id="buttonSave">
					保 存
				</button>
				</logic:notEqual>				
				<logic:equal value="xy" name="userType" scope="session">
					<button class="button2"
					style="width:80px" id="buttonSave" disabled="disabled">
					保 存
				</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			  </logic:equal>
			</div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
