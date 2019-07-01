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
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="js/pjpy/hndx/dypyadd.js"></script>
</head>
<body onload="onShow()">
	<html:form action="hndx_dypy.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：德育评议维护 - 德育评议成绩录入
			</div>
		</div>
		<input type="hidden" id="url" name="url"
			value="/hndx_dypy.do?method=dypyadd" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm" />
		<input type="hidden" id="doType" value="" />
		
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:22px">
					<td colspan="12" align="center">
						<b>学生基本信息</b>
					</td>
				</tr>
			</thead>
			<tr>

				<td align="center" width="20%">
					<font color="red">*</font>学号
				</td>
				<td align="left" width="30%">
					<html:text property="xh" styleId="xh" readonly="readonly"
						onchange="checkXhExists($('getStuInfo').value);"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						选择
					</button>
				</td>

				<td width="20%">
					<div align="center">
						学年
					</div>
				</td>
				<td width="30%">
					<input type="hidden" name="xn" value="${xn }" />
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
						学制
					</div>
				</td>
				<td>
					${rs.xz }
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
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					${rs.xymc }
				</td>
				<td>
					<div align="center">
						专业名称
					</div>
				</td>
				<td>
					${rs.zymc }
				</td>
			</tr>
			<tr>

				<td>
					<div align="center">
						班级
					</div>
				</td>
				<td>
					${rs.bjmc }
				</td>
			</tr>
		</table>
		
		
		<fieldset>
				<legend>
					各加分项目
				</legend>

				<p>
<%--					<input  value="+"--%>
<%--						onclick="trAdd('flag1','add','numAdd1','rzqk');"--%>
<%--						style="width: 20px" />--%>
<%--					<input  value="-" onclick="trDel('flag1','delRow1');"--%>
<%--						style="width: 20px" />--%>
<%--					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 生成&nbsp;&nbsp;--%>
					<input type="hidden" name="numAdd" id="numAdd1" style="width: 20px"
						onblur="trAdd('flag1','madd','numAdd1','rzqk')">
<%--					&nbsp;行 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 清除&nbsp;&nbsp;--%>
					<input type="hidden" name="numDel" id="numDel1" style="width: 20px"
						onblur="trDelAll('flag1','numDel1')">
<%--					&nbsp;行&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
				</p>
				<table class="tbstyle" align="center" width="100%" id="tTb">
					<tr>
						<td>
							<div class="mid_box">
								<table align="center" style="width: 100%" id="rsT"
									class="tbstyle">
									<!-- 打印时第一行不显示- -->
									<thead style="height: 23px">
										<tr>
<%--											<td align="center" nowrap="nowrap" style='width:6%'>--%>
<%--												选定删除行--%>
<%--											</td>--%>
											<td align="center" nowrap="nowrap" style='width:17%'>
												项目名称
											</td>
											<td align="center" nowrap="nowrap" style='width:17%'>
												项目比率<font color="red">(%)</font>
											</td>
											<td align="center" nowrap="nowrap" style='width:17%'>
												项目加分
											</td>
										</tr>
									</thead>
									<tbody width="100%" align="center" class="tbstyle" id="flag1">
									</tbody>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool" id="btn" style="position: absolute;width:100%">
				<button type="button" class="button2"
					onClick="saveData('hndx_dypy.do?method=dypyadd&doType=add','xh');">
					保 存
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2"
					onClick="Close();">
					关 闭
				</button>
			</div>
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
			Close();
			if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				window.dialogArguments.document.getElementById('search_go').click();	
			}
		</script>
	</logic:present>
</body>
</html:html>
