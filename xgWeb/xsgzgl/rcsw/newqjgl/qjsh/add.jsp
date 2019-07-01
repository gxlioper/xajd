<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsh/js/operation.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function() {
			});
		</script>
	</head>
	<body>
		<html:form method="post"
		 styleId="form" action="/qjsq">
		<div style='tab;width:100%;height:400px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/selectStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>请假信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							学年
						</th>
						<td align="left">
							${dqxn}
						</td>
						<th align="right">
							学期
						</th>
						<td align="left">
							${dqxq}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>请假天数
						</th>
						<td align="left">
							<html:text property="qjts" styleId="qjts" style="width:40%"></html:text>&nbsp;&nbsp;天&nbsp;&nbsp;
						</td>
						<th align="right">
							<span class="red">*</span>请假类型
						</th>
						<td align="left">
								<html:select property="qjlxid" styleId="qjlxid" disabled="false" 
									style="width:94%">
									<html:options collection="qjlxList" property="qjlxid"
										labelProperty="qjlxmc" />
								</html:select>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>请假开始时间
						</th>
						<td align="left">
							<html:text property="kssj" styleId="kssj" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:92%"/>
						</td>
						<th align="right">
							<span class="red">*</span>请假结束时间
						</th>
						<td align="left">
							<html:text property="jssj" styleId="jssj" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:92%"/>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>请假事由
						</th>
						<td colspan="3">
							<html:textarea property="qjsy" styleId="qjsy" style="width:92%"></html:textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"  onclick="save('qjsq.do?method=add&type=save','xh-qjts-qjlxid-kssj-jssj-qjsy');return false;" id="buttonSave">
									保 存
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</html:form>
	</body>
</html>
