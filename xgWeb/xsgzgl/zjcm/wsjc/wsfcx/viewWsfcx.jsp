<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjcm/wsjc/wsfcx/js/wsfcx.js"></script>
		
	</head>
	<body>
		
		<body>
		
		<html:form action="/cjWsfcx" method="post" styleId="WsfcxForm">
			<div style='tab;width:100%;height:425px;overflow-x:hidden;overflow-y:auto;'>
				<html:hidden property="wsfid" styleId="wsfid"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th style="width:20%">¥��</th>
							<td style="width:30%">
								${rs.ldmc}
							</td>
							<th style="width:20%">���</th>
							<td style="width:30%">
								${rs.ch}
							</td>
					    </tr>
					    <tr>
					    	<th>���Һ�</th>
					    	<td>
								${rs.qsh}
							</td>
							<th>�����꼶</th>
					    	<td>
								${rs.nj}
							</td>
					    </tr>
					    <tr>
							<th>
								����ѧԺ
							</th>
							<td>
								${rs.xymc}
							</td>
							<th>
								��λ��
							</th>
							<td>
								${rs.cws}
							</td>
			      		</tr>
			      		<tr>
			      			<th>
								��ס����
							</th>
							<td>
								${rs.rzrs}
							</td>
			      		</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>�����</th>
							<td>
								${rs.pfzmc}
							</td>
							<th><span class="red"></span>�������</th>
							<td>
								${rs.ccny}
							</td>
					    </tr>
					    <tr>
					    	<th>�����</th>
					    	<td>
								${rs.ccr}
							</td>
							<th>�������</th>
					    	<td>
								${rs.ccrq}
							</td>
					    </tr>
					    <tr>
					    	<th>�����IP</th>
					    	<td>
								${rs.ccrip}
							</td>
							<th>��ֵ</th>
					    	<td>
								${rs.fz}
							</td>
						</tr>
						<tr>
							<th>��ֵ��ע</th>
							<td colspan="3">${rs.fzbz}</td>
			      		</tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

