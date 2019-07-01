<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">			
		</script>
	</head>
	<body>
		<html:form action="/xpj_sqsh" method="post" styleId="sqshForm">
			<input type="hidden" value="${jbxx.xh }" id="xh"/>
			
			
			<div style='tab;width:100%; height: 450px; overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
					<thead>
							<tr>
								<th colspan="4">
									<span>综测成绩排名汇总</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th style="text-align: center;" colspan="2">学年学期</th>
								<th style="text-align: center;" colspan="2">班级排名</th>
							</tr>
							<logic:present name="zcpmAll">
								<logic:iterate id="z" name="zcpmAll">
									<tr>
										<td colspan="2" style="text-align: center;">${z.xn }${z.xq }</td>
										<td colspan="2" style="text-align: center;">${z.pm}</td>
									</tr>
								</logic:iterate>
								<logic:empty name="zcpmAll">
									<tr>
										<td colspan="5">未找到任何记录！</td>
									</tr>
								</logic:empty>
							</logic:present>
						</tbody>
				</table>
			</div>
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
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

