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
		<script type="text/javascript" defer="defer">

		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
	
		<logic:notEmpty name="shjg" >
			<logic:notEqual value="0" name="shjg">
				<div class="prompt">
		          <h3><span>��ʾ��</span></h3>
		          <p style="color: red;">�������¼�Ѿ�����ˣ������޸ģ�</p>
		          <a class="close" title="����" onclick="jQuery('.prompt').toggle();"></a>
		      	</div>
			</logic:notEqual>
		</logic:notEmpty>
	
		<html:form action="/xsxx_xnxj_xjtxgl" method="post" styleId="xnxjForm">
			<html:hidden property="id"/>
			<html:hidden property="shjg" styleId="shjg"/>
			<html:hidden property="txsj" styleId="txsj"/>
			<html:hidden property="splid" styleId="splid"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xsxx/xnxj/10511/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��С����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td colspan="3"><bean:write name="hsdxnxjsqForm" property="xn"/></td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>����С��
								<br/>
								<font color="red">(��2000��)</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="xjnr" styleId="xjnr" style="width:95%;" rows="5" onblur="checkLen(this,2000);"></html:textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button id="save_button" type="button" onclick="updateXnxj('save');">
										����
									</button>
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

