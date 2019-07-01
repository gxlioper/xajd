<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwgl.js"></script>
	</head>
	<body>
		<html:form action="/xsgwsqnew_sq" method="post" styleId="qgzxGwglNewForm" onsubmit="return false;">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="xhs" id="xhs" value="${xhs}" />
			<input type="hidden" name="xhTal" id="xhTal" value="${xhs}" />
			<input type="hidden" name="xqrs" id="xqrs" value="${rs.xqrs-rs.zgrs}" />
			<input type="hidden" name="fknsrs" id="fknsrs" value="${rs.xqrs-rs.zgrs-rs.knsrs+yykns}" />
			<input type="hidden" name="xn" id="xn" value="${rs.xn}" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		
			<div style="overflow-x:hidden;overflow-y:auto;">

				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
								
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>岗位信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr>
							<th width="16%">
								学年
							</th>
							<td width="34%">
								${rs.xn}
							</td>
							<th width="16%">
								用人部门
							</th>
							
							<td width="34%">
								${rs.yrdwmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								岗位名称
							</th>
							<td width="34%">
								${rs.gwmc}
							</td>
							<th width="16%">
								岗位性质
							</th>
							<td width="34%">
								${rs.gwxzmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								需求人数
							</th>
							<td width="34%">
								${rs.xqrs}
							</td>
							<th width="16%">
								困难生数
							</th>
							<td width="34%">
								${rs.knss}
							</td>
						</tr>
						<tr>
							<th>是否受岗位申请数限制</th>
							<td colspan="3">
								${rs.sfsgwsqsxzmc}
								<input type="hidden"  id="sfsgwsqsxz" value="${rs.sfsgwsqsxz}"/>
							</td>
						</tr>
						<tr>
							<th align="right" >
								当前在岗人数
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.zgrs}
							</td>
						</tr>
						<tr>
							<th align="right" >
								岗位描述
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwms}
							</td>
						</tr>
						<tr>
							<th align="right" >
								岗位人员要求
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwryyq}
							</td>
						</tr>
						<tr>
							<th align="right" >
								岗位预期人员效果
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwyqryxg}
							</td>
						</tr>
						<tr>
							<th align="right" >
								备注
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.bz}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:35px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="refreshParentTg();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="xszgxxDiv" style="display: none;">
				<%@ include file="/xsgzgl/qgzx/gwglnew/ryxxCk.jsp"%>
			</div>
		</html:form>
	</body>
</html>

