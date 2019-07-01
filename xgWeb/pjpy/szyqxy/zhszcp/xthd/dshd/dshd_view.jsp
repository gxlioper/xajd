<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<body>
		<html:form action="/pjpyszyqwh">
			<input id="url" name="url" type="hidden" value="/pjpy/szyqxy/zhszcp/zznl_Add.jsp"/>
			<input id="getStuInfo" name="getStuInfo" type="hidden" value="xh"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：综合素质-学生综合素质养成课-读书活动查看
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
<%--				<thead>--%>
<%--					<tr style="height: 23px">--%>
<%--						<td colspan="4" align="center">--%>
<%--							思想政治与道德素质分增加--%>
<%--						</td>--%>
<%--					</tr>--%>
<%--				</thead>--%>
				<tr style="height: 23px">
					<td align="right">
						学号：
					</td>
					<td align="left">
						<bean:write name="rs" property="xh"/>
					</td>
					<td align="right">
						姓名：
					</td>
					<td align="left">
						<bean:write name="rs" property="xm"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						学年：
					</td>
					<td align="left">
						<bean:write name="rs" property="xn"/>
					</td>
					<td align="right">
						学期：
					</td>
					<td align="left">
							<bean:write name="rs" property="xq"/>
					</td>
				</tr>
				<tr style="height: 23px">
				<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />：
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
					<td align="right">
						专业：
					</td>
					<td align="left">
							<bean:write name="rs" property="zymc"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<td align="right">
						班级：
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<td align="right">
					</td>
					<td align="left">
					</td>
				</tr>
			</table>
			<fieldset>
				<table class="tbstyle" align="center" width="93%" id="tTb">
					<tr>
						<td>
					<div class="mid_box">
							<table align="center" style="width: 100%" id="rsT" class="tbstyle">
								<!-- 打印时第一行不显示- -->
								<thead style="height: 23px">
									<tr>
										<td nowrap="nowrap">
											序号
										</td>
										<td nowrap="nowrap">
											书名
										</td>
										<td nowrap="nowrap">
											读书日期
										</td>
										<td nowrap="nowrap">
											读书心得
										</td>
										<td nowrap="nowrap">
											是否获奖
										</td>
										<td nowrap="nowrap">
											加减分
										</td>
										<td nowrap="nowrap">
											评分
										</td>
									</tr>
								</thead>
								<tbody class="tbstyle" id="flag">
									<logic:notEmpty name="rs1">
										<logic:iterate name="rs1" id="s">
											<tr>
											<td>
													<bean:write name="s" property="rnum" />
												</td>
												<td>
													<bean:write name="s" property="dsmc"/>
												</td>
												<td>
													<bean:write name="s" property="dsrq"/>
												</td>
												<td>
													<bean:write name="s" property="dsxd"/>
												</td>
												<td>
													<bean:write name="s" property="sfhj"/>
												</td>
												<td>
													<bean:write name="s" property="jjf"/>
												</td>
												<td>
													<bean:write name="s" property="pf"/>
												</td>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
									</tbody>				
							</table>
							</div>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool" align="center">
				<span class="openbutton">
					<button type="button" class="button2" id="buttonClose" onclick="window.close();return false;" style="width: 80px" id="buttonClose">
						关 闭
					</button> 
				</span>
			</div>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
