<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
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
			<div class="title_img" id="title_m">
				当前位置：
				<logic:equal value="view" name="operation">团员推优单个查看</logic:equal>
				<logic:equal value="modi" name="operation">团员推优单个修改</logic:equal>
			</div>
		</div>

		<input type="hidden" name="pkValue" value="${pkValue }" />
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:22px">
					<td colspan="12" align="center">
						<b>推优信息</b>
					</td>
				</tr>
			</thead>
			<tr>
				<td align="center" width="20%">
					学号
				</td>
				<td align="left" width="30%">
					<input type="hidden" name="save_xh" value="${rs.xh }" />
					${rs.xh }
				</td>
				<td width="20%">
					<div align="center">
						学年
					</div>
				</td>
				<td width="30%">
					<input type="hidden" name="save_xn" value="${rs.xn }" />
					${rs.xn }
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
					<input type="hidden" name="xq" value="${rs.xq }">
					${rs.xq }
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
						辅导员审核
					</div>
				</td>
				<td>
					${rs.fdysh }
				</td>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />审核
					</div>
				</td>
				<td>
					${rs.xysh }
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						学校审核
					</div>
				</td>
				<td>
					${rs.xxsh }
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
						onblur="checkLen(this,800)" rows='8' value="${rs.sqly }" />
				</td>
			</tr>
			<tr align="left" style="height:22px">
				<td align="right">
					备注：
					<br />
					<font color="red">(限制在400字内)</font>
				</td>
				<td colspan="7">
					<html:textarea property='save_bz' style="width:99%" rows='8'
						onblur="checkLen(this,800)" value="${rs.bz }" />
				</td>
			</tr>
			
			<tr align="left">
				<td align="right">
					辅导员审核意见:<br />
					<font color="red">(限制在400字内)</font>
				</td>
				<td colspan="3">
					<logic:equal value="fdy" name="userType">
						<html:textarea name='rs' property='save_bjpy' style="width:99%"
							rows='5' value="${rs.bjpy}" onblur="chLeng(this,800)" />
					</logic:equal>
					<logic:notEqual value="fdy" name="userType">
						<html:textarea name='rs' property='bjpy' style="width:99%"
							rows='5' value="${rs.bjpy}" readonly="true"
							onblur="chLeng(this,300)" />
					</logic:notEqual>
				</td>
			</tr>
			<tr align="left">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />审核意见：<br />
					<font color="red">(限制在200字内)</font>
				</td>
				<td colspan="3">
					<logic:equal value="xy" name="userType">
						<html:textarea name='rs' property='save_xyshyj' style="width:99%"
							rows='5' value="${rs.xyshyj}" onblur="chLeng(this,400)" />
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<html:textarea name='rs' property='xyshyj' style="width:99%"
							rows='5' readonly="true" value="${rs.xyshyj}"
							onblur="chLeng(this,200)" />
					</logic:notEqual>
				</td>
			</tr>
			<tr align="left">
				<td align="right">
					学校审核意见：<br />
					<font color="red">(限制在200字内)</font>
				</td>
				<td colspan="3">
					<logic:equal value="xx" name="userType">
						<html:textarea name='rs' property='save_xxshyj' style="width:99%"
							rows='5' value="${rs.xxshyj}" onblur="chLeng(this,400)" />
					</logic:equal>
					<logic:notEqual value="xx" name="userType">
						<html:textarea name='rs' property='xxshyj' style="width:99%"
							rows='5' readonly="true" value="${rs.xxshyj}"
							onblur="chLeng(this,200)" />
					</logic:notEqual>
				</td>
			</tr>
		</table>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<logic:equal value="modi" name="operation">
					<button type="button" class="button2" style="width:80px"
						onClick="BatAlert.showTips('正在保存数据，请稍候');saveData('gdby_tygl.do?method=tyViewAndModi&doType=update','');">
						保 存
					</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			</logic:equal>
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
	</html:form>
		<logic:present name="result">
			<input type="hidden" id="message" value="${message }" />
			<script type="text/javascript">
				alert(document.getElementById('message').value);
				Close();
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
			</script>
		</logic:present>
</body>
</html:html>
