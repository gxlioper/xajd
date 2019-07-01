<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/rysq/js/rysq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/xpjpy_rysq" method="post" styleId="rysqForm">
			<input type="hidden" id="xmdm" name="xmdm" value="${xmwhModel.xmdm}"/>
			<html:hidden styleId="xh" property="xh" />
			<html:hidden property="id" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<!-- 这个页面是有病的，等这段时间忙完回过头来要优化这个东西2018-07-10，Meng.Wei -->
					<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请奖项</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								评奖周期
							</th>
							<td colspan="3">
								<bean:write property="xn" name="rysqForm"/>
							</td>
						</tr>
						<tr>
							<th>
								项目名称
							</th>
							<td>
								${xmwhModel.xmmc}
							</td>
							<th>
								项目金额
							</th>
							<td>
								${xmwhModel.xmje}
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>申请理由
								<br /><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="sqly" styleId="sqly" style="width:95%;" rows="5" onblur="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
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
									<button type="button" onclick="saveRysqXg('save');return false;">
										保存草稿
									</button>
									<button type="button" onclick="saveRysqXg('submit');return false;">
										提交申请
									</button>
									<button type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>