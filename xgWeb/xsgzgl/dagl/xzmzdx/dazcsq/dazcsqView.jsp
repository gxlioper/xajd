<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${dazcsqForm.sqid}&tt="+new Date().getTime());
				var zcfs = "${rs.zcfs}";
				if("�ʼ�" == zcfs){
					/*�����ֶ�*/
					jQuery("#mailedOne").show();
					jQuery("#mailedTwo").show();
					jQuery("#mailedThree").show();
					jQuery("#byoOne").hide();
				}else{
					/*�����ֶ�*/
					jQuery("#mailedOne").hide();
					jQuery("#mailedTwo").hide();
					jQuery("#mailedThree").hide();
					jQuery("#byoOne").show();
				}
			})
		</script>
	</head>
	<body>
		<html:form action="/dagl_dazcsq" method="post" styleId="dazcsqForm">
			<div style='tab;width:100%; overflow-x:hidden;overflow-y:auto;'>
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
								<span>����ת��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ת����ʽ</th>
							<td colspan="3">${rs.zcfs}</td>
						</tr>
						
						<tr id="mailedOne">
							<th>�ʼĵ�ַ</th>
							<td>${rs.yjdz}</td>
							<th>��������</th>
							<td>${rs.yzbm}</td>
						</tr>
						
						<tr id="mailedTwo">
							<th>�ռ���</th>
							<td>${rs.sjr}</td>
							<th>�ռ��˵绰</th>
							<td>${rs.sjrdh}</td>
						</tr>
						
						<tr id="mailedThree">
							<th>��λ����</th>
							<td>${rs.dwmc}</td>
							<th>��λ��ַ</th>
							<td>${rs.dwdz}</td>
						</tr>
						
						<tr id="byoOne">
							<th>�Դ�������ŵ</th>
							<td>
								�����Ĳ�����
								<a href="javascript:void(0);" onclick="window.open('common_upload.do?method=asyncDownload&fid=${dazccsszForm.uploadid}');return false;" class="name" style="margin-left: 0px;">
									������ת��Э�顷
								</a>
							</td>
							<th>Ԥ���ᵵ����</th>
							<td>${rs.yqtdrq}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
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
			<div style="height: 30px"></div>
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