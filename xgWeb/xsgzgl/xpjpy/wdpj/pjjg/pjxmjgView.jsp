<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/pjjg/js/pjjg.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
		
	<body>
		<html:form action="/xpj_pjjg" method="post" styleId="pjxmjgForm" onsubmit="return false;">
			<div class="tab" style='width:100%;overflow-x:hidden;overflow-y:auto;' >
				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
						<span>ѧ����Ϣ</span>
						</th>
					</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
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
					    </tr>
					    <tr>
							<th>��Ŀ����</th>
							<td>
								${rs.xmlxmc }
							</td>
							<th>��Ŀ����</th>
							<td>
								${rs.xmxzmc }
							</td>
						</tr>
						<tr>
							<th>��Ŀ����</th>
							<td>
								${rs.xmmc }
							</td>
					    
							<th>���</th>
							<td>
								${rs.xmje }
							</td>
						</tr>
						<logic:equal value="10279" name="xxdm">
						<tr>
							<th>
								��������
							</th>
							<td colspan="3">
								${rs.ylzd1 }
							</td>
						</tr>
						<tr>
							<th>
								����ʱ��
							</th>
							<td colspan="3">
								${rs.ylzd3}
							</td>
						</tr>
						<tr>
							<th>
								���쵥λ
							</th>
							<td colspan="3">
								${rs.ylzd4 }
							</td>
						</tr>
						</logic:equal>
						<tr>
						<logic:equal value="13943" name="xxdm">
						<tr>
							<th>�佱��λ</th>
							<td colspan="3">
								${rs.bjdw }
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
							����ʱ��
							</th>
							<td colspan="3">
								${rs.sqsj }
							</td>
					    </tr>
					    <%--����ҽҩ�ߵ�ר�Ƹ��Ի��ֶ�--%>
						<logic:equal value="70002" name="xxdm">
						<tr>
						   <th>
								���ܺν���
						   </th>
						   <td colspan="3" style="word-break:break-all;">
						   	${rs.djjl}
						   </td>
						</tr>
						</logic:equal>
					    <tr>
							<th>
								<logic:equal value="70002" name="xxdm">
									��Ҫ�¼�
								</logic:equal>
								<logic:notEqual value="70002" name="xxdm">
									��������
								</logic:notEqual>
							</th>
							<td colspan="3">
								${rs.sqly }
							</td>
			      </tr>
					</tbody>
				</table>
			</div>
			<div style="height: 50px"></div>
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
		</html:form>
	</body>
</html>

