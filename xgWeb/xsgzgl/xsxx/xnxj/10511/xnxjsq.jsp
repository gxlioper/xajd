<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true" ></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xnxj/10511/js/xnxj.js"></script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/xsxx_xnxj_xjtxgl" method="post" styleId="xnxjForm">
			<html:hidden property="id" styleId = "id"/>
			<html:hidden property="shjg" styleId="shjg"/>
			<html:hidden property="txsj" styleId="txsj"/>
			<html:hidden property="splid" styleId="splid"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xsxx/xnxj/10511/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>学年小结信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td colspan="3"><bean:write name="hsdxnxjsqForm" property="xn"/></td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>自我小结
								<br/>
								<font color="red">&lt;限2000字&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="xjnr" styleId="xjnr" style="width:95%;" rows="5" ></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button id="save_button" type="button"  onclick="saveXnxj('save');">
										保存草稿
									</button>
									<button  id="submit_button" type="button" onclick="saveXnxj('submit');">
										提交申请
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

