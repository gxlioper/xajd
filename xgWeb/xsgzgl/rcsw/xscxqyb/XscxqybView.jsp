<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xscxqyb/js/xscxqyb.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/rcsw_xscxqybgl" method="post" styleId="xscxqybForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�鿴�±���Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${xxck.xn}
							</td>
							</td>
							<th>ѧ��</th>
							<td>
								${xxck.xqmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��д��
							</th>
							<td width="30%">
								${xxck.xm}
							</td>
							</td>
							<th>�·�</th>
							<td>
								${xxck.yf}
							</td>
						</tr>
						<tr>
					    	<th>���¹�����չ���</th>
					    	<td colspan="3">
								${xxck.bygzkzqk}
							</td>
						</tr>
						<tr>							
							<th >ѧ����ע�ȵ�</th>
							<td colspan="3">
								${xxck.xsgzrd}
							</td>
						</tr>
						<tr>
							<th  >ѧ��˼�붯̬</th>
					    	<td   colspan="3">
								${xxck.xssxdt}
						    </td>
						 </tr>
						 <tr>
						    <th  >ѧ�����󼰹�������</th>
					    	<td  colspan="3">
								${xxck.xstsjgzjy}
						    </td>
						 </tr>			
					</tbody>
				 </table>			
				</div>
			  <div style="height:30px"></div>   
				  <div>
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

