<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/jfgl/js/jfhbzjdx.js"></script>
		<style>.datelist td {word-break:break-all}</style>
	</head>
	<body>
		<html:form action="/qgzx_jfgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="hbsjs" id="hbsjs" value="" />
			<input type="hidden" name="hbjes" id="hbjes" value="" />
			<input type="hidden" name="bzs" id="bzs" value="" />
		<div style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">
			<!-- 提示信息 -->
			<div class="prompt" id="promptTs">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					划拨时间不可修改，金额和备注可修改
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none'"></a>
			</div>
			<!-- 提示信息 end-->	
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>勤工经费修改</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								年度
							</th>
							<td width="34%">
								<html:hidden name="rs" property="nd" styleId="nd"/>
								${rs.nd }
							</td>
							<th width="16%">
								用人单位
							</th>
							<td width="34%">
								<html:hidden name="rs" property="bm" styleId="bm"/>
								<bean:write name="rs" property="bmmc"/>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<button type="button" onclick="delXgTr();return false;" class="btn_common">删除经费项</button>
							</td>
						</tr>
					</thead>
				</table>
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td width="5%">
								<input type="checkbox" name="th" onclick="selectAll(this);" />
							</td>
							<td width="15%">
								划拨时间
							</td>
							<td width="15%">
								<font class="red">*</font>划拨金额<元>
							</td>
							<td width="65%">
								备注
							</td>
						</tr>
					</thead>
					<tbody id="tbody_jfxx">
						${rs.rsList}
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
							<div class="btn">
								<button type="button" onclick="saveXgJfhb();return false;">
									保 存
								</button>
								<button type="button" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>