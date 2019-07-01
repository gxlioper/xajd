<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
	<script language="javascript"  src="js/pjpy/nbcs/pjpy_wjdc.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyNbcsDAO.js'></script>	
	<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>	
	<script language="javascript"  src="js/pjpy/nbcs/pjpy_wjdc.js"></script>
	<script type="text/javascript">	

	</script>
	<body onload="">
		<html:form action="/nbcsPxpj">
			<!-- 隐藏域 -->
			<%@ include file="/pjpy/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
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
							${xn }学年
						</td>
					</tr>
				</thead>
				<tr style="height: 23px">
					<td align="right" width="10%">
						班级：
					</td>
					<td align="left" width="30%" colspan="">
						${bjmc }
					</td>
					<td align="right" width="10%">
						问卷：
					</td>
					<td align="left" width="30%" colspan="">
						${wjmc }
						<input type="hidden" name="wjbh" value="${wjbh }" />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%">
							<!-- 评价列表为空 -->
							<logic:empty name="rsList">
								<tr>
									<td align="center">
										无被评价人
									</td>
								</tr>
							</logic:empty>
							<!-- 评价列表非空 -->
							<logic:notEmpty name="rsList">
								<tr>
									<td>
										学号
									</td>
									<td>
										姓名
									</td>
									<td>
										本学年处分信息
									</td>
									<td>
										评价等级
									</td>
									<td>
										评价分数
									</td>
								</tr>
								<logic:iterate name="rsList" id="rs">
									<tr>
										<td>
											${rs.xh }
											<input type="hidden" name="fpxh" value="${rs.xh }" />
										</td>
										<td>
											${rs.xm }
										</td>
										<td>
											<!-- 处分信息 -->
											<logic:empty name="cfList">无处分信息</logic:empty>
											<logic:notEmpty name="cfList">
												<%boolean flag = true;%>
												<logic:iterate name="cfList" id="cf">
													<logic:equal name="cf" property="xh" value="${rs.xh }">
														${cf.cflbmc }<br>
														<% flag = false;%>
													</logic:equal>
												</logic:iterate>
												<%if(flag){%>
													无处分信息
												<%}%> 												
											</logic:notEmpty>
											<!-- 处分信息 end -->
										</td>
										<td>
											&nbsp;${rs.pj }
										</td>
										<td>
											<input type="text" name="pjf" value="${rs.pjf }" 
											onkeypress="return onlyNum(this,5)" 
											style="width:30%;ime-mode:disabled"/>
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</table>
					</td>
				</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
					<!-- 非查看 -->
					<logic:notEqual name="doType" value="view">
						<button type="button" class="button2" 
							id="buttonSave" 
							onclick="saveUpdate('/xgxt/nbcsPxpj.do?method=pjjgUpdate&doType=save','');""
							style="width: 80px">
								保&nbsp;&nbsp;存
						</button> 
					</logic:notEqual>
					<button type="button" class="button2" id="buttonClose" onclick="Close();return false;"
						style="width: 80px">
						关&nbsp;&nbsp;闭
					</button>
				</tr>
			</table>
			<logic:notEmpty name="message">
				<script>
					alert($("message").value);
					$("message").value = "";
					$("doType").value = "";
					dialogArgumentsQueryChick();
					window.close();
				</script>
			</logic:notEmpty>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
