<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src='js/calendar/calendar-setup.js'></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xbzj/js/xbzj.js"></script>
	</head>
	<body>
		<html:form action="/rcsw_xbzj.do" styleId="xbzjForm" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="id"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%"><font color="red">*</font>ѧ��</th>
							<td style="width:35%">
								<html:hidden property="xh" styleId="xh"/>
								<bean:write name="jbxx" property="xh"/>
							</td>
							<th style="width:15%">����</th>
							<td style="width:35%">
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xm"/>
								</logic:present>
							</td>
						</tr>
						
						<tr>
							<th>�꼶</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="nj"/>
								</logic:present>
							</td>
							<th><bean:message key="lable.xb" /></th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xymc"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>רҵ</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="zymc"/>
								</logic:present>
							</td>
							<th>�༶</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="bjmc"/>
								</logic:present>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>֧����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<bean:write name="xbzjForm" property="xn"/>
							</td>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							
							<td width="34%">
								<bean:write name="xbzjForm" property="xqmc"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>��ʼʱ��
							</th>
							<td width="34%">
								<bean:write name="xbzjForm" property="zjsj"/>
							</td>
							<th width="16%"></th>
							<td width="34%"></td>
						</tr>
						<tr>
							<th width="16%" rowspan="4">
								��ע
								<br/>
								<font color="red">����������200��</font>
							</th>
							<td width="34%" colspan="3" rowspan="4">
								<html:textarea property="bz" readonly="true" styleId="bz" rows="4" style="width:90%" ></html:textarea>
							</td>
						</tr>
					</tbody>
					
				</table>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									
									<button type="button" onclick="refreshParent2();">
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