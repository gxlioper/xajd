<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"  src="js/sztzFunction.js"></script>
		<script language="javascript"  src="js/sztzFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script language="javascript"  src="js/gygl/gyglTyFunction.js"></script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/zjcmGygl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
		
			<!-- 房源库信息 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>房源库信息</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="height: 23px">
						<th align="right" width="20%">
							校区：
						</th>
						<td align="left" width="30%">
							<bean:write name="rs" property="xqmc"/>
						</td>
						<th align="right" width="20%">
							分配标记：
						</th>
						<td align="left">
							<bean:write name="rs" property="fpbj"/>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							楼栋：
						</th>
						<td align="left">
							<bean:write name="rs" property="ldmc"/>
						</td>
						<th align="right">
							床位数：
						</th>
						<td align="left">
							<bean:write name="rs" property="cws"/>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							所属层数：
						</th>
						<td align="left">
							第<bean:write name="rs" property="cs"/>层
						<th align="right">
							寝室电话：
						</th>
						<td align="left">
							<bean:write name="rs" property="qsdh"/>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							寝室号：
						</th>
						<td align="left">
							<bean:write name="rs" property="qsh"/>
						</td>
						<th align="right">
							收费标准：
						</th>
						<td align="left">
							<bean:write name="rs" property="sfbz"/>
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
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									关	闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
