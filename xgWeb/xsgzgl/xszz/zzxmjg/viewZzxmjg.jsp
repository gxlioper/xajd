<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/zzxmjg/js/updateZzxmjg.js"></script>
	</head>
	<body>
		
		<html:form action="/xszz_zzxmjg" method="post" styleId="zzxmjgForm">
			<html:hidden property="guid"  styleId="guid"/>
			
			<div style='tab;width:100%;height:400px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>��������</th>
							<td>
								${rs.xn }&nbsp;${rs.xqmc }
							</td>
							<th>��Ŀ��������</th>
							<td>
								${rs.pdxn }&nbsp;${rs.pdxqmc }
							</td>
					    </tr>
					    <tr>
							<th>��Ŀ���</th>
							<td>
								${rs.lbmc }
							</td>
							<th>��Ŀ����</th>
							<td>
								${rs.xmmc }
							</td>
					    </tr>
					    <tr>
							<th>���</th>
							<td>
								${rs.je }
							</td>
							<th>����ʱ��</th>
							<td>
								${rs.sqsj }
							</td>
					    </tr>
					    <logic:equal value="11799" name="xxdm">
					     <tr>
							<th>
								���ͨ��ʱ��
							</th>
							<td colspan="3">
								${rs.lastshsj }
							</td>
			            </tr>
					    <tr>
							<th>
								�������
							</th>
							<td colspan="3">
								${rs.ylzd1 }
							</td>
			            </tr>
			            </logic:equal>
					    <tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden name="rs" property="ylzd5" styleId="fjid"/>
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
							<th>
								��������
								<br />
							</th>
							<td colspan="3"  style="word-wrap: break-word!important; word-break: break-all!important">
								${rs.sqly }
							</td>
			      </tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
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
		</html:form>
	</body>
</html>

