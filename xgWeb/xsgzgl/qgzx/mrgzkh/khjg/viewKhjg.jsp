<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/mrgzkh/khjg/khjg.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/mrgzkhKhjg" method="post" styleId="GzkhKhjgForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:380px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��д��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>���˵�λ</th>
							<td>
								${gzkhKhjgXx.bmmc}
							</td>
							<th>��ʱ</th>
							<td>
								${gzkhKhjgXx.gs}��Сʱ��
							</td>
						</tr>
						<tr>
							<th>��λ</th>
							<td>
								${gzkhKhjgXx.gwmc}
							</td>
							<th>�����ص�</th>
							<td>
								${gzkhKhjgXx.gzdd}
							</td>
						</tr>
						<tr>
						<th>�������ڼ�ʱ���</th>
							<td colspan="3">
								${gzkhKhjgXx.gzrq}&nbsp;${gzkhKhjgXx.gzkssj}ʱ��${gzkhKhjgXx.gzjssj}ʱ
							</td>
						</tr>
						<tr>
						
							<th>��������</th>
							<td colspan="3">
								${gzkhKhjgXx.gznr }
							</td>
						</tr>
						<tr>
							<th>��¼ʱ��</th>
							<td>
								${gzkhKhjgXx.cjsj}
							</td>
							<th>��¼��Ա</th>
							<td>
								${gzkhKhjgXx.czyhxm}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
									<button type="button" onclick="iFClose();">
										�ر�
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