<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			xsbjfj();
			jQuery("#shlccx").load(
					"comm_spl.do?method=lccx&sqid=$${model.stid}&tt="
							+ new Date().getTime());
		});
		//ѡ��ѧ����ʾ�༶
		function xsbjfj(){
			var fzrlb='${rs.fzrlb}';
			if(fzrlb=="ѧ��"){
				document.getElementById("bjmctr").style.display = "";
			}else{
				document.getElementById("bjmctr").style.display = "none";
			}
		}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/stglStjg" method="post" styleId="StjgForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								������Ŀ����
							</th>
							<td width="30%">
								${rs.stxmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�������
							</th>
							<td width="30%">
								${rs.stlbmc}
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
								${rs.gkdw}
							</td>
						</tr>
						<!--
						<tr>
							<th width="20%">
								���ſ�ʼʱ��
							</th>
							<td width="30%">
								${rs.kssj}
							</td>
							<th width="20%">
								���Ž���ʱ��
							</th>
							<td width="30%">
								${rs.jssj}
							</td>
						</tr>
						  -->
						<tr>
							<th width="20%">
								���������
							</th>
							<td width="30%">
								${rs.fzrlb}
							</td>
							<th width="20%">
								���Ÿ�����
							</th>
							<td width="30%">
								${rs.stfzrxm}
							</td>
						</tr>
						<tr id='bjmctr'>
							<th width="20%">
								����������ѧԺ
							</th>
							<td width="30%">
								${rs.fzrxy}
							</td>
							<th width="20%">
								���������ڰ༶
							</th>
							<td width="30%">
								${rs.fzrbj}
							</td>
						</tr>
					<thead>
					<tr>
						<th colspan="4">
							<span>ָ����ʦ</span>

						</th>
					</tr>
					</thead>
					<tbody>
					<tr colspan="4">
						<td width="100%" colspan="4">
							<div width="100%" id="autotable">
								<table width="100%" id="tablebody">
									<tr>
										<th width="30%" style="text-align:left;">ָ����ʦ����</th>
										<th width="20%" style="text-align:left;">��������</th>
										<th width="20%" style="text-align:left;">��ϵ�绰</th>
										<th width="20%" style="text-align:left;">ְ��</th>
									</tr>
									<logic:iterate id="i" name="ZdlsInfoList">
										<tr name="deltr">
											<td><input name="zgh" type="hidden" value="${i.zgh}" style="width:90%"/><label name = "xm">${i.xm}</label></td>
											<td><input name="bmdm" type="hidden" value="${i.bmdm}" style="width:90%"/><label name = "bmmc">${i.bmmc}</label></td>
											<td><label name = "lxdh">${i.lxdh}</label></td>
											<td><input name="zc" type="hidden" value="${i.zc}" style="width:90%"/><label name = "zcmc">${i.zcmc}</label></td>
										</tr>
									</logic:iterate>
								</table>
							</div>
						</td>
					</tr>
						<tr>
							<th width="20%">
								������ϵ�绰
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
								���ų���ʱ��
							</th>
							<td width="30%" >
								${rs.stclsj}
							</td>
							<th width="20%">
								����ʱ��
							</th>
							<td width="30%">
								${rs.sqsj}
							</td>	
						</tr>
					<logic:equal value="12872" name = "xxdm">
						<tr>
							<th width="20%">
								�Ǽ�
							</th>
							<td width="30%">
									${rs.stxj}
							</td>
							<th width="20%">
							</th>
							<td width="30%">
							</td>
						</tr>
					</logic:equal>
						<tr>
							<th width="20%">
								���ż��
							</th>
							<td colspan="3">
								${rs.stsm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								���Ż����
							</th>
							<td colspan="3">
								${rs.sthjqk}
							</td>
						</tr>
						<tr>
							<th>
								����ƻ���
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="fj" styleId="fjid" value="${rs.fj}"/>
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
							<th width="20%">
								�����������
							</th>
							<td colspan="3">
								${splcname}
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

