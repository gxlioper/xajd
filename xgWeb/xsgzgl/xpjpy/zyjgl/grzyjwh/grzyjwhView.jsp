<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/xmsb/js/xmsb.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/comm/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript">
		function saveForm(){
			var bsmc = jQuery("#bsmc").val();
			var zbdw = jQuery("#zbdw").val();
			var hjsj = jQuery("#hjsj").val();
			var id = jQuery("#id").val();
			if (jQuery.trim(bsmc) == ""){
				showAlert("������������ƣ�");
				return false;
			}	
			if (jQuery.trim(zbdw) == ""){
				showAlert("���������쵥λ��");
				return false;
			}
			if (jQuery.trim(hjsj) == ""){
				showAlert("�������ʱ�䣡");
				return false;
			}
			var url = "pjpy_zyjwhwh.do?method=updateGrzyj&type=update&id="+id;
	      	ajaxSubFormWithFun("GrzyjwhForm",url,function(data){
		    	 if(data["message"]=="����ɹ���"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
					}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }
			});
		  }
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/pjpy_zyjwhwh" method="post" styleId="GrzyjwhForm" onsubmit="return false;">
			<input type="hidden" id="type" value="${type}" />
			<html:hidden property="id"  styleId="id"/> 
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>

					<%@ include file="/xsgzgl/rcsw/hcyhkgl/comm/viewStudent.jsp" %>

					<thead>
						<tr>
							<th colspan="4">
								<span>רҵ��Ϣά�� </span>
							</th>
						</tr>
					</thead>					
						<tr>
							<th>��������</th>
							<td colspan="5">
								${rs.bsmc}
							</td>
						</tr>
						<tr>
							<th>���쵥λ</th>
							<td>
								${rs.zbdw}
							</td>
							<th>��ʱ��</th>
							<td>
								${rs.hjsj}
							</td>
						</tr>						
					<tr>
						<th>
							֤������
						</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="ylzd3" styleId="fjid"/>
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
					<thead>
						<tr>
							<th colspan="4">
								<span>�ȼ��϶�</span>
							</th>
						</tr>
					</thead>					
						<tr>
							<th>�϶��ȼ�</th>
							<td colspan="3">
								${rs.rddjmc}
							</td>
						</tr>
					<tr>
						<th rowspan="4">��ע</th>
						<td colspan="5" rowspan="4">
							${rs.bz}
						</td>
					</tr>
					
				</table>
			</div>
			<div>	
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="�� ��" onclick="iFClose();">
										�� ��
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

