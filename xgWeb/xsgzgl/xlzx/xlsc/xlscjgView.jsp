<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	</head>
	<body>
		<html:form action="/xlzx_xlscjg" method="post" styleId="xlscjgForm">
			<html:hidden property="id" styleId="id"/>
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
								<span>ɸ����ϸ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
				    	<tr>
				    		<th style="text-align:center;">ɸ������</th>
				    		<td>
				    			${model.scrq}
				    		</td>
				    		<th style="text-align:center;">SCL90���</th>
				    		<td>
				    			${model.scl}
				    		</td>
				    	</tr>
				    	<tr>
				    		<th style="text-align:center;">SDS���</th>
				    		<td>
				    			${model.sds}
				    		</td>
				    		<th style="text-align:center;">SAS���</th>
				    		<td>
				    			${model.sas}
				    		</td>
				    	</tr>
				    	<tr>
				    		<th style="text-align:center;">�����������</th>
				    		<td>
				    			${model.bkyy}
				    		</td>
				    		<th style="text-align:center;">���˽��ǽ��</th>
				    		<td>
				    			${model.bkjl}
				    		</td>
				    	</tr>
				    	<tr>
				    		<th style="text-align:center;">�Ƿ���Ҫ�μ�Լ̸</th>
				    		<td>
				    			${sfxyyt}
				    		</td>
				    		<th style="text-align:center;">�Ƿ��Ѳμ�Լ̸</th>
				    		<td>
				    			${sfyyt}
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