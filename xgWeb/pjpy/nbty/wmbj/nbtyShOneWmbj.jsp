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
	<script language="javascript" src="js/function.js"></script>	
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript">
	
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body onload="">	
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		   <script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		   <html:form action="nbtyWmbj" method="post">
		   	<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}"/>
			 <html:hidden property="write" value="${write}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：评奖评优 - 审核 - 先进班级审核 
				</div>
			</div>
			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>文明班级审核</b>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 10%">
						<font color="red">*</font>学年：
					</td>
					<td>
					<bean:write name="rs" property="xn" />
						<html:hidden property="save_xn" value="${rs.xn}"/> 
					</td>
					
					<td align="right" style="width: 10%">
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left" style="width: 40%">
						<bean:write name="rs" property="xymc"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>专业：
					</td>
					<td align="left" style="width: 40%">
					<bean:write name="rs" property="zymc"/>
					</td>
					<td align="right">
						<font color="red">*</font>班级：
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
						<html:hidden  property="save_bjdm" value="${rs.bjdm}"/>
					</td>
				</tr>
					<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>审核：
					</td>
					<td align="left" style="width: 40%">
						
						<!-- <bean:message key="lable.xsgzyxpzxy" />用户 -->
						<logic:equal name="userType" value="xy">
								<html:select property="save_xysh">
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
						</logic:equal>
						<!-- 学校用户 -->
						<logic:equal name="userType" value="xx">
							<html:select property="save_xxsh">
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
						</logic:equal>
						
						<!-- 管理员 -->
						<logic:equal name="userType" value="admin">
							<html:select property="save_xxsh">
								<html:option value="未审核">未审核</html:option>
								<html:option value="通过">通过</html:option>
								<html:option value="不通过">不通过</html:option>
							</html:select>
						</logic:equal>
					</td>
					<td align="right">
						
					</td>
					<td align="left">
						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						主要事迹：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="zysj"  name="rs" property="save_zysj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						奖励记录：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="zysj"  name="rs" property="jljl" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						备注：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="zysj" name="rs" property="bz" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<logic:equal name="userType" value="xy">
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />意见：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="xyyj" name="rs" property="save_xyyj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual name="userType" value="xy">
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />意见：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="xyyj" name="rs" property="xyyj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				</logic:notEqual>
				<logic:equal name="userType" value="xx">
				<tr style="height:22px">
					<td align="right">
						学校意见：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="xxyj" name="rs" property="save_xxyj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						学生会意见：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="xscyj" name="rs" property="save_xscyj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				</logic:equal>
				<logic:equal name="userType" value="admin">
				<tr style="height:22px">
					<td align="right">
						学校意见：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="zysj" name="rs" property="save_xxyj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						学生会意见：
						<br />
						<span class="style1">(限400字)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" styleId="xscyj" name="rs" property="save_xscyj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				</logic:equal>
			</table>
			 	<div class="buttontool" align="center">
					    <logic:notEqual name="doType" value="view">
							<button type="button" class="button2" onclick="saveData('nbtyWmbj.do?method=nbtyShOneWmbj&doType=modi','shjg')"
								style="width:80px" id="buttonSave">
								保 存
							</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEqual>
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							关 闭
						</button>
					</div>
			<logic:equal name="write" value="no">
				     <script>
			          alert("对不起，学校用户已审核，您不能再进行修改操作！");
			        </script>
			       
			</logic:equal>
		<logic:present name="result">
			<input type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
		</html:form>
	
	</body>


</html>

