<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	</head>
		
	<body>
		<html:form action="/xpj_zcfs" method="post" >
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>原提交信息</span>
						</th>
					</tr>
					</thead>
					<tbody>
							<tr>
								<th align="right" width="20%">
									学年
								</th>
								<td align="left" width="30%">
									${qxjlInfor.xn }
								</td>
								<th align="right" width="20%">
									学期
								</th>
								<td align="left" id="xm">
									${qxjlInfor.xqmc}
								</td>
							</tr>
							<tr>
								<th align="right">
									年级
								</th>
									<td align="left" id="nj">
									${qxjlInfor.nj}
								</td>
								<th align="right">
									<bean:message key="lable.xb" />
								</th>
									<td align="left" id="xymc">
									${qxjlInfor.xymc}
								</td>
							</tr>
							<tr>
								<th align="right">
									专业
								</th>
									<td align="left"  id="zymc">
									${qxjlInfor.zymc}
								</td>
								<th align="right">
									班级
								</th>
								<td align="left" id="bjmc">
									${qxjlInfor.bjmc}
								</td>
							</tr>
							<tr>
								<th>原提交人</th>
								<td>
									${qxjlInfor.ytjrxm }
								</td>
								<th>原提交时间</th>
								<td>
									${qxjlInfor.ytjsj }
								</td>
						    </tr>
					</tbody>
						<thead>
							<tr>
								<th colspan="4">
								<span>取消信息</span>
								</th>
							</tr>
						</thead>
					</thead>
					<tbody>
						<tr>
							<th>取消人</th>
							<td>
								${qxjlInfor.qxrxm }
							</td>
							<th>取消时间</th>
							<td>
								${qxjlInfor.qxsj }
							</td>
						</tr>
					    <tr>
							<th>
								取消理由
							</th>
							<td colspan="3">
								${qxjlInfor.qxyy }
							</td>
			      		</tr>
					</tbody>
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

