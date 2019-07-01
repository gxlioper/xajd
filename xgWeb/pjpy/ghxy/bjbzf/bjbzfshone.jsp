<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript' src="js/check.js"></script>
	<script language="javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>

	<body>
		<html:form action="/ghxy_bjbz.do">
			<input type="hidden" name="pkValue" value="${pkValue }"/>
			
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：班级表彰分单个审核
				</div>
			</div>
			<fieldset>
				<legend>
					学生基本信息
				</legend>
				<table class="tbstyle" border="0" id="rsTable" align="center"
					style="width: 100%">
					<thead>
						<tr style="height:22px">
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
							<html:text styleId="xh" property="xh"
								style="width:100%;heigh:100%" value="${rs.xh}" readonly="true" />
						</td>
						<td align="right">
							学年：
						</td>
						<td align="left">
							<input type="hidden" id="xn"  name="xn" value="${rs.xn }" />
							${rs.xn }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							学期：
						</td>
						<td align="left">
							<input type="hidden" id="xq" name="xq" value="${rs.xq }" />
							${rs.xq }
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
							月度：
						</td>
						<td align="left">
							<input type="hidden" id="yd" name="yd" value="${rs.yd }" />
							${rs.yd}
						</td>
						<td align="right">
							表彰等级：
						</td>
						<td align="left">
							${rs.bzdj }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							姓名：
						</td>
						<td align="left">
							${rs.xm }
						</td>

						<td align="right">
							性别：
						</td>
						<td align="left">
							${rs.xb }
						</td>
					</tr>
					<tr style="height: 23px">
						<td align="right">
							班级：
						</td>
						<td align="left">
							${rs.bjmc }
						</td>
						<td align="right">
							表彰分：
						</td>
						<td align="left">
							${rs.bzf }
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
								<html:select property="njbzrsh" value="${rs.njbzrsh}">
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</logic:equal>
							<logic:notEqual value="njbzr" name="userType">
								${rs.njbzrsh }
								<input type="hidden" name="njbzrsh" value="${rs.njbzrsh }"/>
							</logic:notEqual>
						</td>
						<td align="right">
							学校审核：
						</td>
						<td>
							<logic:equal value="xx" name="userType">
								<html:select property="xxsh" value="${rs.xxsh}">
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</logic:equal>
							<logic:notEqual value="xx" name="userType">							
								${rs.xxsh }
								<input type="hidden" name="xxsh" value="${rs.xxsh }"/>
							</logic:notEqual>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton">
						<button type="button" class="button2" id="buttonSave"
							onclick="saveData('/xgxt/ghxy_bjbz.do?method=bjbzfshone&doType=save','');"
							style="width: 80px">
							保 存
						</button>
			</span>
			</div>
			<logic:present name="msg">
				<hidden type="hidden" id="msg" value="${msg}" />
				<script>
					alert($("msg").value);
					Close();
					if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
						window.dialogArguments.document.getElementById('search_go').click();	
					}
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
