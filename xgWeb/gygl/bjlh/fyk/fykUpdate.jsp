<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/gygl/bjlh/bjlhFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
	<script type="text/javascript">	

	</script>
	<body onload="">
		<html:form action="/bjlh_sjwh">
			<input type="hidden" id="doType" name="doType" value="${ doType}"/>
			<input type="hidden" id="realTable" name="realTable" value="${ realTable}"/>
			<input type="hidden" id="url" name="url" value="${ url}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="4" align="center">
							房源库信息
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="20%">
						校区：
					</td>
					<td align="left" width="30%">
						<bean:write name="rs" property="xqmc"/>
					</td>
					<td align="right" width="20%">
						分配标记：
					</td>
					<td align="left">
						<bean:write name="rs" property="fbbj"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						楼栋：
					</td>
					<td align="left">
						<bean:write name="rs" property="ldmc"/>
					</td>
					<td align="right">
						床位数：
					</td>
					<td align="left">
						<bean:write name="rs" property="cws"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						所属层数：
					</td>
					<td align="left">
						第<bean:write name="rs" property="cs"/>层
					<td align="right">
						寝室电话：
					</td>
					<td align="left">
						<bean:write name="rs" property="qsdh"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						寝室号：
					</td>
					<td align="left">
						<bean:write name="rs" property="qsh"/>
					</td>
					<td align="right">
						收费标准：
					</td>
					<td align="left">
						<bean:write name="rs" property="sfbz"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						备注：<br><font color="red">(限250字)</font>
					</td>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 100%" readonly="true"/>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center">
						<logic:notPresent name="rsList">
							本寝室无人居住
						</logic:notPresent>
						<logic:present name="rsList">
						<fieldset>
							<legend>
								本寝室居住人数： <bean:write name="rsNum" />人
							</legend>
							<table width="100%" id="rsTable" class="tbstyle">
								<thead>	
									<tr align="center" style="cursor:hand">
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rsList" id="s" indexId="index">
									<tr>
										<logic:iterate id="v" name="s">
											<td align="left">
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
						</logic:present>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<button class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						关	闭
					</button>
					</td>
				</tr>
			</table>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
