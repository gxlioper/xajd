<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxdk/jg/js/xnwxdkjg.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xnwxdk/js/util.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xnwxdk_jg" method="post" styleId="XnwxdkJgModel">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
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
							<th>ѧ��</th>
							<td>
								${jg.xn}
							</td>
							<th>ѧ��</th>
							<td>
							    ${xqmc}
							</td>
						</tr>
						<tr>
							<th>��ͥ�ṩ��Ԫ��</th>
							<td>
								${jg.jttg}
							</td>
							<th>��ѧ��Ԫ��</th>
							<td>
								${jg.zxj}
							</td>
						</tr>
						<tr>
							<th>��ѧ��Ԫ��</th>
							<td>
							    ${jg.jxj}
							</td>
							<th>�ڹ���ѧ���루Ԫ��</th>
							<td>
							    ${jg.qgzxsr}
							</td>
						</tr>
						<tr>
							<th>У����Ϣ���  ��Ԫ��</th>
							<td>
							    ${jg.xnwxjk}
							</td>
							<th>�������� ��Ԫ��</th>
							<td>
							     ${jg.qtsr}
							</td>
						</tr>
						<tr>
							<th>��ѧ�����Ԫ��</th>
							<td>
							     ${jg.zxdksqje}
							</td>
							<th>��ѧ����ʱ�� </th>
							<td>
							    ${jg.zxdksqsj}
							</td>
						</tr>
						<tr>
							<th>���Ž�Ԫ��</th>
							<td>
							    ${jg.ffje}
							</td>
							<th>����ʱ�� </th>
							<td>
							    ${jg.ffsj}
							</td>
						</tr>
						<tr>
							<th>���������Ԫ��</th>
							<td>
							     ${jg.sqje}
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3">
								 ${jg.sqly}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${jg.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
					<div style="height:30px"></div>	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
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