<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/stzhwh/js/stzhwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form action="/ystglStzhwh" method="post" styleId="StzhwhForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ż�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								��������Ŀ����
							</th>
							<td width="30%">
								${rs.ystxmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								���������
							</th>
							<td width="30%">
								${rs.ystlbmc}
							</td>
							<th width="20%">
								��Ŀ���
							</th>
							<td width="30%">
								${rs.xmlbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��Чѧ��
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							<th width="20%">
								�ҿ���λ
							</th>
							<td width="30%">
								${rs.gkdwmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								���������
							</th>
							<td width="30%">
								${rs.fzrlb}
							</td>
							<th width="20%">
								�����Ÿ�����
							</th>
							<td width="30%">
								${rs.fzrxm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								ָ����ʦ
							</th>
							<td width="30%" >
								${rs.zdlsxm}
							</td>
							<th width="20%">
								ָ����ʦְ��
							</th>
							<td width="30%" >
								${rs.zcmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								ָ����ʦ��ϵ��ʽ
							</th>
							<td width="30%" >
								${rs.zdlslxfs}
							</td>
							<th width="20%">
								��������
							</th>
							<td width="30%" >
								${rs.ssbmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��������ϵ�绰
							</th>
							<td width="30%" >
								${rs.lxdh}
							</td>
							<th width="20%">
								������
							</th>
							<td width="30%">
								${rs.jtrxm}
							</td>	
						</tr>
						<tr>
							<th width="20%">
								�����ų���ʱ��
							</th>
							<td width="30%" >
								${rs.ystclsj}
							</td>
							<th width="20%">
								����ʱ��
							</th>
							<td width="30%">
								${rs.sqsj}
							</td>	
						</tr>
					</tbody>
					
				</table>
			</div>
			<div style="margin-top:1px;">
			  <table width="100%" border="0" class="formlist" >
				<thead>
						<tr>
							<th colspan="8">
								<span>�����ų�Ա��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="15%" style="text-align:left">ѧ��</th>
							<th width="15%" style="text-align:left">����</th>
							<th width="15%" style="text-align:left">�༶</th>
							<th width="20%" style="text-align:left">��������</th>
							<th width="20%" style="text-align:left">�س�</th>
							<th width="15%" style="text-align:left">����״̬</th>
						</tr>
						<logic:iterate id="s" name="zhpdList">
							<tr>
								<td>${s.xh}</td>
								<td>${s.xm}</td>
								<td>${s.bjmc}</td>
								<td>${s.sqly}</td>
								<td>${s.tc}</td>
								<td>${s.tnzt}</td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
			<div style="height:33px;">
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