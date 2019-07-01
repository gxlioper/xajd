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
		<script type="text/javascript" src="xsgzgl/gygl/gyyjx/js/gyyjx.js"></script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/gygl_gyyjxstu" method="post" styleId="gyyjxForm">
			<logic:notEmpty name="actionType" >
				<logic:equal value="update" name="actionType">
					<html:hidden property="gyyjid"/>
				</logic:equal>
			</logic:notEmpty>
			
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>ס�޴�λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>¥������</th>
							<td>${cwxx.ldmc}</td>
							<th>���Һ�</th>
							<td>${cwxx.qsh}</td>
						</tr>
						<tr>
							<th>��λ��</th>
							<td>${cwxx.cwh}</td>
							<th>���ҵ绰</th>
							<td>${cwxx.qsdh}</td>
						</tr>
						<tr>
							<th>�շѱ�׼</th>
							<td>${cwxx.sfbz}</td>
							<th>�����꼶</th>
							<td>${cwxx.qsnj}</td>
						</tr>
						<tr>
							<th>����<bean:message key="lable.xb" /></th>
							<td>${cwxx.qsxymc}</td>
							<th>�����༶</th>
							<td>${cwxx.bjmc}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								�������
							</th>
							<td colspan="3">
								${gyyjxx.yjflmc }
							</td>
						</tr>
						<tr>
							
							<th>
								�������
							</th>
							<td colspan="3" style="word-break:break-all;">
								${gyyjxx.yjms }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tr>
							<th>
								�������
							</th>
							<td colspan="3">
								${gyyjxx.fkqkmc }
							</td>
						</tr>
						<logic:notEmpty name="gyyjxx" >
							<logic:equal value="1" name="gyyjxx" property="fkqk" >
								<tr>
									<th>
										��������
									</th>
									<td colspan="3" style="word-break:break-all;">
										${gyyjxx.fknr }
									</td>
								</tr>
							</logic:equal>
						</logic:notEmpty>

					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="�� ��" onclick="iFClose();">
										�� ��
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

