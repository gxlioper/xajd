<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var zcfs = "${rs.zcfsmc}";
				var yjzt = "${rs.yjztmc}";
				if("�ʼ�" == zcfs){
					/*�����ֶ�*/
					jQuery("#mailedOne").show();
					jQuery("#mailedTwo").show();
					jQuery("#mailedThree").show();
					jQuery("#mailedFour").show();
					if("���ʼ�" == yjzt){
						jQuery("#mailedFive").show();
						jQuery("#mailedSix").show();
					}else{
						jQuery("#mailedFive").hide();
						jQuery("#mailedSix").hide();
					}
					jQuery("#byoOne").hide();
					jQuery("#byoTwo").hide();
				}else if("�Դ�" == zcfs){
					/*�����ֶ�*/
					jQuery("#mailedOne").hide();
					jQuery("#mailedTwo").hide();
					jQuery("#mailedThree").hide();
					jQuery("#mailedFour").hide();
					jQuery("#mailedFive").hide();
					jQuery("#mailedSix").hide();
					jQuery("#byoOne").show();
					jQuery("#byoTwo").show();
				}else{
					jQuery("#mailedOne").hide();
					jQuery("#mailedTwo").hide();
					jQuery("#mailedThree").hide();
					jQuery("#mailedFour").hide();
					jQuery("#mailedFive").hide();
					jQuery("#mailedSix").hide();
					jQuery("#byoOne").hide();
					jQuery("#byoTwo").hide();
				}
			})
		</script>
	</head>
	<body>
		<html:form action="/dagl_dazcjg" method="post" styleId="dazcjgForm">
			<div style='tab;width:100%; overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th>ѧ��</th>
						<td>${jbxx.xh}</td>
						<th>�꼶</th>
						<td>${jbxx.nj}</td>
					</tr>
					<tr>
						<th>����</th>
						<td>${jbxx.xm}</td>
						<th>ѧԺ</th>
						<td>${jbxx.xymc}</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>${jbxx.xb}</td>
						<th>רҵ</th>
						<td>${jbxx.zymc}</td>
					</tr>
					<tr>
						<th>���֤��</th>
						<td>${jbxx.sfzh}</td>
						<th>�༶</th>
						<td>${jbxx.bjmc}</td>
					</tr>
					<logic:equal value="1" name="countRs">
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
							<td colspan="3">${rs.zcfsmc}</td>
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
						
						<tr id="mailedFour">
							<th>�ʼ�״̬</th>
							<td colspan="3">${rs.yjztmc}</td>
						</tr>
						
						<tr id="mailedFive">
							<th>��ݷ�ʽ</th>
							<td>${rs.kdfs}</td>
							<th>��ݵ���</th>
							<td>${rs.kddh}</td>
						</tr>
						
						<tr id="mailedSix">
							<th>�ʼ�ʱ��</th>
							<td colspan="3">${rs.yjsj}</td>
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
						
						<tr id="byoTwo">
							<th>ʵ���ᵵ����</th>
							<td>${rs.sjtdrq}</td>
							<th>ʵ���ᵵ��</th>
							<td>${rs.sjtdr}</td>
						</tr>
					</logic:equal>
					<logic:equal value="0" name="countRs">
							<tr>
								<th colspan="4" style="text-align:center;">
									<span style="color: red;"><b>��ѧ��δ�Ǽ�</b></span>
								</th>
							</tr>
					</logic:equal>
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