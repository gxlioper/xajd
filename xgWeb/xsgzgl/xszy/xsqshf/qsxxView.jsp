<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type='text/javascript'>
		function xsxxView(xh) {
			showDialog("新生信息查看", 700, 450, "xszyXsxxgl.do?method=viewXszyXsxx&xh=" + xh);
		}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xszyXsqshf" method="post" styleId="XszyQshfForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>寝室基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								寝室号
							</th>
							<td width="30%">
								${qsxxMap.qsh}
							</td>
							<th width="20%">
								楼栋
							</th>
							<td width="30%">
								${qsxxMap.ldmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								性别
							</th>
							<td width="30%">
								${qsxxMap.qsxb}
							</td>
							<th width="20%">
								园区
							</th>
							<td width="30%">
								${qsxxMap.xymc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								是否混合寝室
							</th>
							<td width="30%">
								${qsxxMap.sfhhqs}
							</td>
							<th width="20%">
								联系电话
							</th>
							<td width="30%">
								${qsxxMap.qsdh}
							</td>
						</tr>
						<tr>
						<th>入住学生</th>
							<td colspan="3">
								<table width="100%">
									<thead>
										<tr>
											<td>学号</td>
											<td>姓名</td>
											<td>大类</td>
											<td>班级</td>
											<td>联系电话</td>
										</tr>
									</thead>
									<tbody id="xsxx">
									<logic:present name="rzxsList">
										<logic:iterate id="xsxx" name="rzxsList" indexId="i">
											<tr>
												<td>
												<a href="javascript:void(0);" class="name" onclick="xsxxView('${xsxx.xh }');return false;">${xsxx.xh }</a>
												</td>
												<td>
												${xsxx.xm }
												</td>
												<td>
												${xsxx.dl }
												</td>
												<td>
												${xsxx.bjmc }
												</td>
												<td>
												${xsxx.lxdh }
												</td>
											</tr>
										</logic:iterate>
									</logic:present>
									<logic:empty name="rzxsList">
											<tr>
												<td colspan="5" align="center">无住宿学生！</td>
											</tr>
										</logic:empty>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>新生之友信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszy/comm/xszyView.jsp" %>
				</table>
				</div>
			     <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
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

