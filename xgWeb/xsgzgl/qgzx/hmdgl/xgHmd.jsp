<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/hmdgl/js/hmdgl.js"></script>
	</head>
	<body>
		<html:form method="post" styleId="hmdglForm" action="/qgzxhmdgl"
			enctype="multipart/form-data">
			<html:hidden property="id"  styleId="id"/>
			<html:hidden property="xh" styleId="xh" />
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>岗位信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>岗位名称
							</th>
							<td align="left">
								<html:text styleId="gwmc" property="gwmc" maxlength="50" />
							</td>

							<th align="right">
								<span class="red">*</span>用人单位
							</th>
							<td align="left">
								<html:text styleId="yrdw" property="yrdw" maxlength="50" />
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<span class="red">*</span>上岗时间
							</th>
							<td align="left">
								<html:text property="sgsj" styleId="sgsj" style="width:120px;"
									onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'lgsj','','','');" />
							</td>

							<th align="right">
								<span class="red">*</span>离岗时间
							</th>
							<td align="left">
								<html:text property="lgsj" styleId="lgsj" style="width:120px;"
									onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'sgsj','','','');" />
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								备注&nbsp;
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="3">
								<html:textarea rows="4" property="bz" styleId="bz"
									style="width:97%" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button"
										onclick="saveXgHmd();return false;"
										id="buttonSave">
										保存
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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
