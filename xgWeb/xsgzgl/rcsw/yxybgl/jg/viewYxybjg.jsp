<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/yxybgl/jg/js/yxybjg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/yxybgl_jg" method="post" styleId="yxybjgForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�±���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							</td>
							<th>ѧ��</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>
								��д��
							</th>
							<td>
								${rs.mz}
							</td>
							</td>
							<th>�·�</th>
							<td>
								${rs.yf}
							</td>
						</tr>
						<tr>
					    	<th>ѧԺ</th>
					    	<td>
								${rs.xymc}
							</td>							
						</tr>
								<tr>
									<th>
										���¹�����չ���
									</th>
									<td colspan="3">
										${rs.bygzkzqk}						
									</td>
								</tr>
						<tr>
							<th>
								ѧ����ע�ȵ�
							</th>
							<td colspan="3">
								${rs.xsgzrd}						
							</td>
			      		</tr>
			      		<tr>
							<th>
								ѧ��˼�붯̬
							</th>
							<td colspan="3">
								${rs.xssxdt}						
							</td>
			      		</tr>
			      		<tr>
							<th>
								ѧ�����󼰹�������
							</th>
							<td colspan="3">
								${rs.xstsjgzjy}				
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

