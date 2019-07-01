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
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script language="javascript">
	
	</script>
	<script type="text/javascript">
		function saveSh(){
			var xysh=""
			if($("write").value!="yes"){
				alert("学校用户已审核，您不能再进行修改！");
				return false;
			}
			if($("xysh")){
				xysh=$("xysh").value
			}
			if(xysh=="不通过"){
				$("xxsh").value=$("xysh").value;
				$("shzt").value=$("xysh").value;
			}else if(xysh=="通过"){
				$("xxsh").value="未审核";
				$("shzt").value="未审核";
			}
			if($("sh")){
			$("shzt").value=$("sh").value;
			}
			showTips("保存中，请等待...");
			refreshForm('rcswKqglXskq.do?method=rcswKqglXskqShOne&doType=modi');
		}
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
		  <html:form action="rcswKqglXskq" method="post">
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：日常事物 - 考勤管理 - 请假审核 
		</div>
	</div>
		<input type="hidden" id="url" name="url" value="/rcswKqglXskq.do?method=rcswKqglXskqShOne=view" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
		<logic:notEqual name="isFdy" value="true">
		<logic:equal name="userType" value="xy">
		  <html:hidden property="save_xyshsj" value="${nowTime}" />
		</logic:equal>
		<logic:equal name="userType" value="xx">
		   <html:hidden property="save_xxshsj" value="${nowTime}" />
		</logic:equal>
		</logic:notEqual>
		<input type="hidden" name="save_sqsj" id="save_sqsj" value="${rs.sqsj}"/>
		<html:hidden  property="save_shzt" styleId="shzt" value="${rs.shzt}" />
		<logic:equal name="userType" value="xy">
			<html:hidden property="save_xxsh" styleId="xxsh" value=""/>
		</logic:equal>
		<html:hidden property="write" value="${write}" styleId="write"/>
		<fieldset>
			<table width="99%" id="rsT" class="tbstyle">
		<thead>
			<tr style="height:22px">
				<td colspan="4" align="center">
					<b>学生请假申请</b>
				</td>
			</tr>
		</thead>
		<tr>
		<td align="right" style="width: 10%">
				<font color="red">*</font>学号：
			</td>
			<td align="left">
				<bean:write name="rs" property="xh"/>
				<html:hidden property="save_xh" value="${rs.xh}"/>
			</td>

			<td align="right" style="width: 10%">
				学年：
			</td>
			<td align="left" style="width: 40%">
				<bean:write name="rs" property="xn" />	
				<html:hidden property="save_xn"  value="${rs.xn}"/>
			</td>
		</tr>
		<tr style="height:22px">
			<td align="right">
				姓名：
			</td>
			<td align="left">
			<logic:notEmpty name="rs">
				<bean:write name='rs' property="xm" />
				</logic:notEmpty>
			</td>
			<td align="right">
				学期：
			</td>
			<td align="left">
				<bean:write name="rs" property="xqmc"/>
				<html:hidden property="save_xq" value="${rs.xq}"/>
			</td>
		</tr>
		<tr style="height:22px">
			<td align="right">
				性别：
			</td>
			<td align="left">
			<logic:notEmpty name="rs">
				<bean:write name='rs' property="xb" />
			</logic:notEmpty>
			</td>
			<td align="right">
				<font color="red"></font>年度：
			</td>
			<td align="left">
				<bean:write name="rs" property="nd"/>
			</td>		
		</tr>
		<tr style="height:22px">
			<td align="right">
				<bean:message key="lable.xsgzyxpzxy" />：
			</td>
			<td align="left">
			<logic:notEmpty name="rs">
				<bean:write name='rs' property="xymc" />
			</logic:notEmpty>
			</td>
			<td align="right">
				<font color="red">*</font>请假类型：
			</td>
			<td align="left">
				<bean:write name="rs" property="qjlxmc"/>
			</td>
		</tr>
		<tr style="height:22px">
			<td align="right">
				专业：
			</td>
			<td align="left">
			<logic:notEmpty name="rs">
				<bean:write name='rs' property="zymc" />
			</logic:notEmpty>
			</td>
			<td align="right">
				<font color="red">*</font>请假时间：
			</td>
			<td align="left">
				<bean:write name="rs" property="qjsj"/>
				<html:hidden property="save_qjsj" value="${rs.qjsj}" />
			</td>
		</tr>
		<tr style="height:22px">
			<td align="right">
				班级：
			</td>
			<td align="left">
			<logic:notEmpty name="rs">
				<bean:write name='rs' property="bjmc" />
			</logic:notEmpty>
			</td>
			<td align="right">
				
			</td>
			<td align="left">
			
			</td>
		</tr>
		<tr style="height:22px">
			<td align="right">
				年级：
			</td>
			<td align="left">
			<logic:notEmpty name="rs">
				<bean:write name='rs' property="nj" />
			</logic:notEmpty>	
			</td>
			<td>
				
			</td>
			<td>
			  
			</td>
			</tr>
		<tr>
		<td align="right">
		审核：
		</td>
		<td>
		
			<logic:equal name="isGyfdy" value="true">
					<html:select property="save_fdysh" styleId="sh">
						<html:option value="未审核">未审核</html:option>
						<html:option value="通过">通过</html:option>
						<html:option value="不通过">不通过</html:option>
					</html:select>
			</logic:equal>
			<logic:notEqual name="isGyfdy" value="true">
				<!-- <bean:message key="lable.xsgzyxpzxy" />用户 -->
				<logic:equal name="userType" value="xy">
					<!-- 请假3天内学校用户单审 -->		
					<logic:lessThan name="rs" property="qjsj" value="3">
						<html:select property="save_xysh"  styleId="sh">
							<html:option value="未审核">未审核</html:option>
							<html:option value="通过">通过</html:option>
							<html:option value="不通过">不通过</html:option>
						</html:select>
					</logic:lessThan>	
					<logic:greaterEqual name="rs" property="qjsj" value="3">
						<html:select property="save_xysh" styleId="xysh">
							<html:option value="未审核">未审核</html:option>
							<html:option value="通过">通过</html:option>
							<html:option value="不通过">不通过</html:option>
						</html:select>
					</logic:greaterEqual>
				</logic:equal>
				<!-- 学校 -->
				<logic:equal name="userType" value="xx">
					<html:select   property="save_xxsh" styleId="sh">
						<html:option value="未审核">未审核</html:option>
						<html:option value="通过">通过</html:option>
						<html:option value="不通过">不通过</html:option>
					</html:select>
				</logic:equal>
				<!-- 管理员 -->
				<logic:equal name="userType" value="admin">
					<html:select property="save_xxsh" styleId="sh">
						<html:option value="未审核">未审核</html:option>
						<html:option value="通过">通过</html:option>
						<html:option value="不通过">不通过</html:option>
					</html:select>
				</logic:equal>
			</logic:notEqual>
			
		</td>
		<td>
		</td>
		<td>
		</td>
		</tr>
		<tr style="height:22px">
			<td align="right">
				申请理由：
				<br />
				<span class="style1">(限400字)&nbsp;</span>
			</td>
			<td colspan="3" align="left">
				<html:textarea rows="8" styleId="qjly" style="width:98%" name="rs" property="qjly" onblur="chLeng(this,400)"/>
			</td>
		</tr>
		<tr style="height:22px">
			<td align="right">
				备注：
				<br />
				<span class="style1">(限400字)&nbsp;</span>
			</td>
			<td colspan="3" align="left">
				<html:textarea rows="8" style="width:98%" styleId="bz" name="rs" property="bz" onblur="chLeng(this,400)"/>
			</td>
		</tr>
	</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="saveSh()"
						style="width:80px" id="btn">
						保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</fieldset>
	</html:form>
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
	</body>


</html>

