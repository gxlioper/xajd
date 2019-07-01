<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

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
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
</head>
<body>
	
	<html:form action="gdby_tygl.do?method=tysq" method="post">
	<div class="title">
		<div class="title_img" id="title_m">当前位置：${title }
		</div>
	</div>
		<input type="hidden" id="url" name="url"
			value="/gdby_tygl.do?method=tysq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xh-xm" />
		<input type="hidden" name="save_sqsj" value="${sqsj }" />
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
						<button type="button" onclick="showTopWin('/xgxt/gdby_tygl.do?method=getStuInfo',750,550);"
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
						学期
					</div>
				</td>
				<td>
					<input type="hidden" name="save_xq" value="${xq }" >
					${xq }
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
						民族
					</div>
				</td>
				<td>
					${rs.mzmc}
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
						入党申请书递交时间
					</div>
				</td>
				<td colspan="3">
					${rs.sqsj }
				</td>
			</tr>
		

			<tr align="left" style="height:22px">
							<td align="right">
								申请理由：
								<br />
							<font color="red">(限制在400字内)</font>
							</td>
							<td colspan="7">
								<html:textarea property='save_sqly' style="width:99%"
									onblur="checkLen(this,800)" rows='8'/>
							</td>
			</tr>
			<tr align="left" style="height:22px">
							<td align="right">
								备注：
								<br />
								<font color="red">(限制在400字内)</font>
							</td>
							<td colspan="7">
								<html:textarea property='save_bz' style="width:99%"
									rows='8' onblur="checkLen(this,800)"/>
							</td>
						</tr>
		</table>
		<logic:equal name="isApply" value="true">
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button type="button" class="button2" onClick="saveData('gdby_tygl.do?method=tysq&doType=add','xh');">
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
	<logic:present name="msg">
		<input type="hidden" id="msg" value="${msg }" />
		<script type="text/javascript">
			alert(document.getElementById('msg').value);
		</script>
	</logic:present>
</body>
</html:html>
