<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/ypzl/sq/js/ypzl.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/comm/js/comm.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/ypzl_jg" method="post" styleId="ypzljgForm" onsubmit="return false;">
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
								<span>�����˾����������ѧ�꣩</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							</td>
							<th>ѧ��</th>
							<td>
								${xqsr}
							</td>
						</tr>			
						<logic:notEqual value="10511" name="xxdm">
						<tr>
					    	<th>�����Ԫ��</th>
					    	<td>
								${rs.sqje}
							</td>
						</tr>
						</logic:notEqual>
						<logic:equal value="10511" name="xxdm">
						<tr>
					    	<th>������Ԫ��</th>
					    	<td>
								${rs.sqje}
							</td>
							<th>����ԭ�����</th>
							<td>
								${ytmc}
							</td>
						</tr>
						<tr>
							<th>
								�����ϴ�
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
					            <html:hidden property="ytms" styleId="ytms" value="${rs.ytms}" />
					            <script type="text/javascript">
							         //���ø��� 
									jQuery(function(){
										var gid = jQuery('#ytms').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>
							</td>
						</tr>
						</logic:equal>
						<logic:notEqual value="10511" name="xxdm">
							<tr>
								<th align="right" width="10%">
									������Ϣ
								</th>
								<td colspan="3">
									<div id="commonfileupload-list-0" style="padding: 5px;"></div>
									<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											var gid = "${rs.filepath}";
											jQuery.MultiUploader_q({
												gid : gid,
												targetEl : 'commonfileupload-list-0'
											});
										});
									</script>
								</td>
							</tr>	
						</logic:notEqual>
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								${rs.sqly}
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

