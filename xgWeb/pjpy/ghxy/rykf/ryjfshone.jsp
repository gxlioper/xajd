<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type='text/javascript' src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript" src="js/pjpy/ghxy/grjfsq.js"></script>

	<body>
		<html:form action="/ghxy_rykf.do">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：个人荣誉减分审核
				</div>
			</div>
			<input type="hidden" name="pkValue" value="${param.pkValue }" />
			<input type="hidden" name="save_id" value="${param.pkValue }" />
			<input type="hidden" id="operation" value="${operation }" />
			<fieldset>
				<legend>
					学生基本信息
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<thead>
						<tr style="height: 22px">
							<td colspan="4" align="center">
								<b>个人信息</b>
							</td>
						</tr>
					</thead>
					<tr style="height: 23px">
						<td align="right" width="15%">
							<font style="color: red">*</font>学号：
						</td>
						<td align="left" width="35%">
							<html:text styleId="xh" property="save_xh"
								style="width:100%;heigh:100%" value="${rs.xh}" readonly="true" />
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">
							<input type="hidden" id="xn" name="save_xn" value="${rs.xn }" />
							${rs.xn }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							学期：
						</td>
						<td align="left">
							<input type="hidden" id="xq" name="save_xq" value="${rs.xq}" />
							${rs.xqmc }
						</td>
						<td align="right">
							年级：
						</td>
						<td align="left">
							${rs.nj }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							${rs.xm }
						</td>

						<td align="right" width="15%">
							院系：
						</td>
						<td align="left">
							${rs.xymc }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							专业：
						</td>
						<td align="left">
							${rs.zymc }
						</td>
						<td align="right">
							班级：
						</td>
						<td align="left">
							${rs.bjmc }
						</td>
					</tr>
					<tr align="left" style="height: 23px">
						<td>
							<div align="right">
								年级部主任审核
							</div>
						</td>
						<td>
							<logic:equal value="njbzr" name="userType">
								<html:select property="save_njbzrsh" value="${rs.njbzrsh}">
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</logic:equal>
							<logic:notEqual value="njbzr" name="userType">
								${rs.njbzrsh }
							</logic:notEqual>
						</td>

						<td align="right">
							学校审核：
						</td>
						<td>
							<logic:equal value="xx" name="userType">
								<html:select property="save_xxsh" value="${rs.xxsh}">
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</logic:equal>
							<logic:notEqual value="xx" name="userType">							
								${rs.xxsh }
							</logic:notEqual>
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							项目名称：
						</td>
						<td align="left">
							<input type="hidden" name="save_xmdm" value="${rs.xmdm }" />
							${rs.xmmc }
						</td>

						<td align="right" width="15%">
							减分
						</td>
						<td align="left">
							${rs.jf }
						</td>
					</tr>
					<tr align="left" style="height: 22px">
						<td align="right">
							减分事由：
							<br />
						</td>
						<td colspan="3">
							<html:textarea property='jfsy' style="width:99%" rows='5' readonly="true" value="${rs.jfsy }" />
						</td>
					</tr>
					<tr align="left" style="height: 22px">
						<td align="right">
							备注：
							<br />
						</td>
						<td colspan="3">
							<html:textarea property='bz' style="width:99%" rows='5'
								readonly="true" value="${rs.bz }" />
						</td>
					</tr>

				</table>
			</fieldset>

			<div class="buttontool" align="center">
				<span class="openbutton">
					<button type="button" class="button2" id="buttonSave"
					onclick="BatAlert.showTips('正在处理数据，请稍候');saveData('ghxy_rykf.do?method=ryjfshone&doType=save','xh');" 
						style="width: 80px">
						保 存
					</button>

					<button type="button" class="button2" onclick=Close();; style="width: 80px">
						关 闭
					</button> </span>
			</div>
			<logic:present name="result">
				<hidden type="hidden" id="msg" value="${message}" />
			<script>
				alert($("msg").value);
				Close();
				if (window.dialogArguments
						&& window.dialogArguments.document.all("search_go")) {
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
