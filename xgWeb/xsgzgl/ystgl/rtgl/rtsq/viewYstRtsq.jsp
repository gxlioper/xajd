<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/comm/js/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/ystglRtsq" method="post" styleId="YstRtsqForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
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
								<span>��������Ŀά��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��������Ŀ����</th>
							<td>${ystxx.ystxmmc}</td>
						</tr>
						<tr>
							<th>���������</th>
							<td>${ystxx.ystlbmc}</td>
							<th>��Ŀ���</th>
							<td>${ystxx.xmlbmc}</td>
							
						</tr>
						<tr>
							<th>��ϵ�绰</th>
							<td>${ystxx.lxdh }</td>
							<th>�ҿ���λ</th>
							<td>${ystxx.gkdwmc}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
							   <a style="text-align: left;" onclick="showYstmx(this);" class="down"
									href="javascript:void(0);"> <font color="blue">���չ��/����</font>
								</a>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_toggle" style="display:none">
						<tr>
							<th>���������</th>
							<td>${ystxx.fzrlb }</td>
							<th>������</th>
							<td>${ystxx.stfzrxm }</td>
						</tr>
						<tr>
							<th>ָ����ʦ</th>
							<td>${ystxx.zdlsxm }</td>
							<th>ָ����ʦְ��</th>
							<td>${ystxx.zcmc }</td>
						</tr>
						<tr>
							<th>ָ����ʦ��ϵ��ʽ</th>
							<td>${ystxx.zdlslxfs }</td>
							<th>��������</th>
							<td>${ystxx.ssbmmc }</td>
						</tr>
						<tr>
							<th>�����ų���ʱ��</th>
							<td>${ystxx.ystclsj }</td>
							<th>����ʱ��</th>
							<td>${ystxx.sqsj }</td>
						</tr>
						<tr>
							<th>
								�����ż��
							</th>
							<td colspan="3" name="stsm">
								${ystxx.ystjj}
							</td>
						</tr>
						<tr>
							<th>
								�����Ż����
							</th>
							<td colspan="3" name="sthjqk">
								${ystxx.ysthjqk}
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<th>�س�</th>
							<td colspan="3">${ystxx.tc}</td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3">${ystxx.sqly}</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
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
		</html:form>
	</body>
	
</html>