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
		<script type="text/javascript" src="xsgzgl/xpjpy/cpfwh/js/cpfwh.js"></script>
		
	</head>
	<body>
		
		<body>
		
		<html:form action="/cpfwh_sq" method="post" styleId="cpfwhForm">
			<div style='tab;width:100%;height:425px;overflow-x:hidden;overflow-y:auto;'>
				<html:hidden property="id" styleId="id"/>
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
								<span>���ܷ���</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red"></span>ѧ��</th>
							<td>
								${xnsr}
							</td>
							<th><span class="red"></span>ѧ��</th>
							<td>
								${xqsr}
							</td>
					    </tr>
					    <tr>
					    	<th><span class="red">*</span>����</th>
					    	<td colspan="3">
								<html:text property="fs" styleId="fs" onkeyup="value=value.replace(/[^\d]/g,'');"></html:text>
							</td>
					    </tr>
					    <tr>
							<th>
								��������
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveSqjg('update');">
										����
									</button>
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

