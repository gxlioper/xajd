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
<html:html>
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript">
			function yz(){
				var temp = checkAllInput('xh!!pddd');
				if(temp){				
					document.forms[0].action = "/xgxt/nbty_qhtsjxj.do?method=qhtsjxjsq&doType=add";
					document.forms[0].submit();
					return true;
				}
				return false;
			}
	</script>
</head>
<body>
	
	<html:form action="/nbty_qhtsjxj.do?method=qhtsjxjsq" method="post">
	<div class="title">
		<div class="title_img" id="title_m">当前位置：评奖评优 - 奖学金申请 - 清寒天使奖学金申请
		</div>
	</div>
		<input type="hidden" id="url" name="url"
			value="/nbty_qhtsjxj.do?method=qhtsjxjsq" />
		<input type="hidden" name="save_sqsj" value="${sqsj }" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xh-xm" />
		<input type="hidden" name="save_jxjdm" value="${jxjxx.jxjdm }"/>
		<table class="tbstyle" width="93%">
			<thead>
				<tr style="height:22px">
					<td colspan="12" align="center">
						<b>填写申请表</b>
					</td>
				</tr>
			</thead>
			<tr>
				<logic:notEqual name="userOnLine" value="student" scope="session">
					<td align="center" width="20%">
						<font color="red">*</font>学号
					</td>
					<td align="left" width="30%">
						<html:text property="save_xh" styleId="xh" readonly="readonly"
							onchange="checkXhExists($('getStuInfo').value);"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</td>
				</logic:notEqual>
				<logic:equal name="userOnLine" value="student" scope="session">
					<td align="center" width="20%">
						<font color="red">*</font>学号
					</td>
					<td align="left" width="30%">
						<html:text styleId="xh" property="save_xh"
							style="width:100%;heigh:100%" value="${rs.xh}" readonly="true" />
					</td>
				</logic:equal>
				<td width="20%">
					<div align="center">
						学年
					</div>
				</td>
				<td width="30%">
					<input type="hidden" name="save_xn" value="${xn }"/>
					${xn }
				</td>

			</tr>
			<tr>
				<td width="16%">
					<div align="center">
						姓名
					</div>
				</td>
				<td width="34%">
					${rs.xm }
				</td>
				<td>
					<div align="center">
						性别
					</div>
				</td>
				<td>
					${rs.xb }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						出生日期
					</div>
				</td>
				<td>
					${rs.csrq}
				</td>
				<td>
					<div align="center">
						民族
					</div>
				</td>
				<td>
					${rs.mzmc}
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						年级
					</div>
				</td>
				<td>
					${rs.nj }
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					${rs.xymc }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						专业名称
					</div>
				</td>
				<td>
					${rs.zymc }
				</td>
				<td>
					<div align="center">
						班级
					</div>
				</td>
				<td>
					${rs.bjmc }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						是否参加勤工助学
					</div>
				</td>
				<td>
					<html:select property="save_sfcjqgzx">
						<html:option value="是">是</html:option>
						<html:option value="否">否</html:option>
					</html:select>
				</td>
				<td>
					<div align="center">
						是否申请助学贷款
					</div>
				</td>
				<td>
					<html:select property="save_sfsqzxdk">
						<html:option value="是">是</html:option>
						<html:option value="否">否</html:option>
					</html:select>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<font color="red">*</font>品德等第
					</div>
				</td>
				<td>
					<html:text styleId="pddd" property="save_pddd" style="width:90%;heigh:100%"/>
				</td>
				<td>
					<div align="center">
						奖学金
					</div>
				</td>
				<td>
					${jxjxx.jxjmc }
				</td>

			</tr>
			<tr>
				<td>
					<div align="center">
						奖学金金额
					</div>
				</td>
				<td>
					${jxjxx.jlje }
				</td>

			</tr>
			<tr align="left" style="height:22px">
							<td align="right">
								奖惩情况:
								<br />
								<font color="red">(限制在200字内)</font>
							</td>
							<td colspan="7">
								<html:textarea property='save_jcqk' style="width:99%"
									rows='3' />
							</td>
			</tr>
			<tr align="left" style="height:22px">
							<td align="right">
								何学年获何种资助（写明受助金额）:
								<br />
							<font color="red">(限制在200字内)</font>
							</td>
							<td colspan="7">
								<html:textarea property='save_hxnhzzz' style="width:99%"
									rows='3'/>
							</td>
			</tr>
			<tr align="left" style="height:22px">
							<td align="right">
								申请理由：
								<br />
								<font color="red">(限制在800字内)</font>
							</td>
							<td colspan="7">
								<html:textarea property='save_sqly' style="width:99%"
									rows='5' />
							</td>
						</tr>
		</table>
		<logic:equal name="isApply" value="true">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="yz();">
					提交申请
				</button>
			</div>
		</logic:equal>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			alert(document.getElementById('message').value);
		</script>
	</logic:present>
</body>
</html:html>
