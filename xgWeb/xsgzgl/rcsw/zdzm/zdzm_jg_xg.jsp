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
		<script type="text/javascript" src="xsgzgl/rcsw/zdzm/js/zdzm_jg.js"></script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/rcsw_zdzm_jggl" method="post" styleId="rcswZdzmJgForm">
			<html:hidden property="zdzmjgid" />
			<html:hidden property="sqsj" />
			<html:hidden property="xh" />
			<html:hidden property="zdzmsqid" />
			<html:hidden property="jrsj" />
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>在读证明申请</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>申请理由
								<br/>
								<font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="sqly" styleId="sqly" style="width:95%;" rows="5" onkeypress="checkLen(this,500);"></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button id="save_button" type="button"  onclick="updateZdzmJgAction();">
										保存
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

