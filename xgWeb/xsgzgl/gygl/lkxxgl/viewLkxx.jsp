<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/lkxxgl/js/lkxx.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xgygl_lkxxdj" method="post" styleId="lkxxForm" onsubmit="return false;">
		<html:hidden property="id" styleId="id"></html:hidden>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ס���˻�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th width="20%">
							����
						</th>
						<td width="30%">
							${rs.xm}
						</td>
						<th>���֤��</th>
						<td>
							${rs.sfzh}
						</td>
					</tr>
					<tr>
						<th width="20%">
							�Ա�
						</th>
						<td width="30%">							
							${rs.xb}
						</td>
						<th>�������ڵ�</th>
						<td>
							${rs.hkszd}
						</td>
					</tr>
					<thead>
						<tr>
							<th colspan="4">
								<span>ס����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								��ס¥��
							</th>
							<td width="30%">
								${rs.rzld}
							</td>
							<th>��ס����</th>
							<td>
								${rs.rzfj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ס��λ
							</th>
							<td width="30%">
								${rs.rzcw}
							</td>
							<th>��סѺ��Ԫ��</th>
							<td>
								${rs.rzyj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��סʱ��
							</th>
							<td width="30%">
								${rs.rzsj}
							</td>
							<th>����ʱ��</th>
							<td>
								${rs.tssj}
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3">
								${rs.bz}
							</td>
			      		</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:35px"></div>  
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

