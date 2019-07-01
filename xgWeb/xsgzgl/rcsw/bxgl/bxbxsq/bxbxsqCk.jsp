<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/bxgl/bxbxsq/js/bxbxsq.js"></script>
		<script type="text/javascript">
		function onShow(gndm) {
			var url = "rcswBxglBxbxsq.do?method=getBxsqxx";
			jQuery.ajax( {
				type : "post",
				async : false,
				url : url,
				data : {
					bxid : jQuery("#sqid").val()
				},
				dataType : "json",
				success : function(data) {
					zdybdInit(gndm, data);
				}
			});
		}
		jQuery(function(){
			//�ֶ��Զ�������� 
			onShow("rcsw_bxbx_query");
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#sqid").val()+"&tt="+ new Date().getTime());
		});
		</script>	
	</head>
	<body>
		<html:form method="post" styleId="BxbxSqForm" action="/rcswBxglBxbxsq"
			enctype="multipart/form-data">
			<html:hidden property="sqid"  styleId="sqid"/>
			<html:hidden property="xh" styleId="xh" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
					</table>
					
					<div class=""  id="content" style="margin-top: 5px; overflow-x:hidden;" >
				    </div>
				    <table width="100%" border="0" class="formlist">
					<tr>
							<th align="right" width="15%">
								������Ϣ
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="filepath" styleId="filepath" value="${model.filepath}"/>
									<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											var gid = jQuery('#filepath').val();
											jQuery.MultiUploader_q({
												gid : gid
												});
										});
									</script> 
							</td>
						</tr>
					</table>
				  <logic:notEqual value="�������" name="shztmc">
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
				</logic:notEqual>
			</div>
			<div style="height:35px"></div>
			<div>
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
			</div>
		</html:form>
	</body>
</html>
