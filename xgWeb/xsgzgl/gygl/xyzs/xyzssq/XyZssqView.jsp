<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/xyzs/xyzsjg/js/xyzsjg.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/gygl_xyzssqgl" method="post" styleId="XyzsSqForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:450px;margin-bottom: 0px;" >
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
								<span>У��ס����Ϣ</span>&nbsp;<lable style="line-height:20px">(${zsjgxx.xn}&nbsp;ѧ��)</lable>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>������ʼʱ��</th>
							<td>
								${zsjgxx.sqsjstart}
							</td>
							<th>�������ʱ��</th>
							<td>
								${zsjgxx.sqsjend}
							</td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td >
								${xlxx.xlmc}
							</td>
							<th>��ϵ�绰</th>
							<td>
								${zsjgxx.lxdh}
							</td>
						</tr>
						<tr>
							<th>�ҳ���ϵ��ʽ</th>
							<td>
								${zsjgxx.parentslxdy}
							</td>
						
							<th>У��ס�޵���ϸ��ַ</th>
							<td>
								${zsjgxx.xxdz}
							</td>
						</tr>
						<tr>
							<th id ="yy"  id="yy"></font>�����סԭ��</th>
							 <td colspan="3" >${xyjzyy.mc}</td>
						</tr>
						<tr>
							<th id ="yy"  id="yy"></font>��ע</th>
							 <td colspan="3" >${zsjgxx.bz}</td>
						</tr>
						<tr>
							<th align="right" width="15%">
								����
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="filepath" styleId="filepath" />
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
					</tbody>
				</table>
			</div>
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