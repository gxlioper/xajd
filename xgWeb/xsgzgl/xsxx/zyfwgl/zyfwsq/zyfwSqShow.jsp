<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<script type='text/javascript'>
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zyfwSqForm.fwid}&tt=" + new Date().getTime());
				
				proviceCiyyLocalMain({type:"view",id:"fwddssx",flag:"yxxdz"});
			});
		</script>
	</head>
	<body style="width: 100%">
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
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
							<span>־Ը����Ǽ���Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">
							ѧ��
						</th>
						<td width="30%">
							${zyfwSqForm.xn}
						</td>
						</td>
						<th width="20%">ѧ��</th>
						<td width="30%">
							${zyfwSqForm.xqmc}
						</td>
					</tr>
					<tr>
						<th width="20%">
							����ʼʱ��
						</th>
						<td width="30%">
							${zyfwSqForm.fwkssj}
						</td>
						<th width="20%">
							�������ʱ��
						</th>
						<td width="30%">
							${zyfwSqForm.fwjssj}
						</td>
					</tr>
					<tr>
						<th width="20%">
							��֤��
						</th>
						<td width="30%">
							${zyfwSqForm.jzr}
						</td>
						<th width="20%">����Сʱ��</th>
						<td width="30%">
							${zyfwSqForm.fwxss}
						</td>
					</tr>
					<tr>
						<th width="20%">
							����ص�
						</th>
						<td colspan="3">
							<input type="hidden" id="fwddssx" value="${zyfwSqForm.fwddssx}"/>
							${zyfwSqForm.fwdd}
						</td>
					</tr>
					<tr>
						<th width="20%" >�������� </th>
						<td colspan="3">
							${zyfwSqForm.fwnr}
						</td>
					</tr>
				</tbody>
			 </table>
			 <table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>�����Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="4" id="shlccx">

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
								<button type="button" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
	</body>
</html>

