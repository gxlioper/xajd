<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
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
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xszbb/comm/js/comm.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			displayBbyhk();
			displayHcDdAndSj();
		});
		
		function displayBbyhk(){
			var bbyhk = jQuery("#xszbblxdm").val();
			
			//�����Żݿ�����
			if (bbyhk == "001"){
				jQuery('.bbyhk').css("display","");
			} else {
				//�ر�
				jQuery('.bbyhk').css("display","none");
			}
		}
		</script>

	</head>
	<body>

		<html:form action="/rcsw_rcxwwh_rcxwxxwhgl" method="post"
			styleId="rcxwxxwhForm">
			<input type="hidden" name="xszbblxdm" id="xszbblxdm" value="${rs.xszbblxdm}" />
			<input type="hidden" name="sfbbhcyhk" id="sfbbhcyhk" value="${rs.sfbbhcyhk}" />
			<div style='tab;width:100%;height:428px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>����֤��</th>
							<td>
								${rs.xszbblxmc}
							</td>
							<logic:equal value="11400" name="xxdm">
					    		<th>
									
							    </th>
								<td>
								
								</td>
					    	</logic:equal>
					    	<logic:notEqual value="11400" name="xxdm">
					    		<th>
								  <font class="bbyhk" style="display: none">�Ƿ񲹰���Żݿ�</font>
							    </th>
								<td>
									&nbsp;&nbsp;<font class="bbyhk" style="display: none">${rs.sfbbhcyhk}</font>
								</td>
					    	</logic:notEqual>
					    </tr>
					    <logic:equal value="12684" name="xxdm">
					     <tr class="bbyhk bbyhk_sjdd" style="display:none">
					     	<th>ʱ��</th>
							<td>
								${rs.sj}
							</td>
							<th>�ص�</th>
							<td>
							${rs.dd}	
							</td>
					     </tr>
					     </logic:equal>
					     <logic:equal value="13011" name="xxdm">
						     <logic:equal name="rs" property="sfbbhcyhkmc" value="y">					     
							    <tr id="qj">						     	
							     	<th>
							     		�˳�����
							     	</th>					     	
							     	<td colspan="3">
							     		${rs.ccqdz}��${rs.cczdz}
							     	</td>						     							     	
							    </tr>
						     </logic:equal>					     
					     </logic:equal>
					     <logic:equal value="10695" name="xxdm">
						     <logic:equal name="rs" property="sfbbhcyhkmc" value="y">					     
							    <tr id="qj">						     	
							     	<th>
							     		�˳�����
							     	</th>					     	
							     	<td colspan="3">
							     		${rs.ccqdz}��${rs.cczdz}
							     	</td>						     							     	
							    </tr>
						     </logic:equal>					     
					     </logic:equal>
					    <tr>
							<th>���벹��ʱ��</th>
							<td colspan="3">
								${rs.sqsj}
							</td>
					    </tr>
					    <tr>
							<th>
								���벹������
							</th>
							<td colspan="3">
								${rs.sqly}
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
										var gid = "${rs.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
						</tr>
					    <tr>
							<th>�Ƿ���ȡ</th>
							<td >
								${rs.sflq}
							</td>
							<th>
								��ȡʱ��
							</th>
							<td >
								${rs.lqsj}
							</td>
			      		</tr>
					    <tr>
							<th>��ע</th>
							<td colspan="3">
								${rs.bz}
							</td>
					    </tr>
					</tbody>
				</table>
			</div>
				<div style="height: 15px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
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
		</table>
		</html:form>
	</body>
</html>

