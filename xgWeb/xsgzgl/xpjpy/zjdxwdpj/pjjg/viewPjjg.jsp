<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/pjjg/js/pjjg.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
		
	<body>
		<html:form action="/xpjpy_pjjg" method="post" styleId="pjjgForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>ѧ����Ϣ</span>
						</th>
					</tr>
					</thead>
						<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
							<span>������Ŀ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>ѧ��</th>
							<td colspan="3">
								${rs.xn }
							</td>
					    </tr>
					    <tr>
							<th>��Ŀ����</th>
							<td>
								${rs.lxmc }
							</td>
							<th>��Ŀ����</th>
							<td>
								${rs.xzmc }
							</td>
						</tr>
						<tr>
							<th>��Ŀ����</th>
							<td>
								${rs.xmmc }
							</td>
					    
							<th>���</th>
							<td>
								${rs.xmje }
							</td>
						</tr>
						<tr>
							<th>����ˮƽ</th>
							<td>${rs.wysp}</td>
							<th>����绰</th>
							<td>${rs.ssdh}</td>
						</tr>
						<tr>
							<th>������Ṥ��ְ��</th>
							<td colspan="3">${rs.gzzw}</td>
						</tr>
						<tr>
							<th>����ѧϰ����</th>
							<td colspan="3">${rs.grxxjl}</td>
						</tr>
						<tr>
							<th>�μӿ������</th>
							<td colspan="3">${rs.cjkyqk}</td>
						</tr>
						<tr>
							<th>���轱��λ����ʶ</th>
							<td colspan="3">${rs.dwrs}</td>
						</tr>
						<tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden name="rs" property="ylzd5" styleId="fjid"/>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>
							</td>
						</tr>
					    <tr>	
							<th>
							����ʱ��
							</th>
							<td colspan="3">
								${rs.sqsj }
							</td>
					    </tr>
					    <tr>
							<th>
								��������
							</th>
							<td colspan="3">
								${rs.sqly }
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