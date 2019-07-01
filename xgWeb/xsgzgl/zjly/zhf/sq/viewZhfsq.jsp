<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/js/zhfsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
						
		</script>
	</head>
	<body>
		<html:form action="/zhf_sq" method="post" styleId="form">
			<html:hidden property="id"/>
			<html:hidden property="xh"/>		
			<div style='overflow-x:hidden;overflow-y:auto;height:410px;margin-bottom: 0px;'>
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
								<span>�Ʒ���Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								����ģ��
							</th>
							<td>
								${model.xmmkmc}
							</td>
							<th>
								�Ʒ���Ŀ
							</th>
							<td>
								${model.jfxmmc}		
							</td>
						</tr>
						<tr>
							<th>
								����Ҫ��
							</th>
							<td>
								${model.khyd}
							</td>
							<th>
								����
							</th>
							<td>
								${model.fs}
							</td>
						</tr>
						<tr>
							<th>
								�����μ�ʱ��
							</th>
							<td>
								${model.cysj}
							</td>
							<th>
								����˵��
							</th>
							<td>
								${model.sxsm}
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td>
								${model.lrr}
							</td>
							<th>
								����ʱ��
							</th>
							<td>
								${model.lrsj}
							</td>
						</tr>
					<logic:notEmpty name="model" property="shr">
						<tr>
							<th>
								�����
							</th>
							<td>
								${model.shr}
							</td>
							<th>
								���ʱ��
							</th>
							<td>
								${model.shsj}
							</td>
						</tr>
						</logic:notEmpty>
						<logic:notEmpty name="model" property="thyj">
							<tr>
								<th>
									�˻����
								</th>
								<td colspan="3">					    		
						    		${model.thyj}
						    	</td>
							</tr>
						</logic:notEmpty>	
						<logic:notEmpty name="model" property="fj">
							<tr>
								<th>
									��֤����
								</th>
								<td colspan="3" id="fileTd" rowspan="5">					    		
						    		<a href="javascript:void(0);" onclick="window.open('zhf_sq.do?method=downloadFile&id=${model.id }');return false;" class="name">����</a>&nbsp;${model.fjmc }
						    	</td>
							</tr>
						</logic:notEmpty>					
					</tbody>
				</table>
			</div>
			<div>
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
			</div>
		</html:form>
	</body>
</html>

