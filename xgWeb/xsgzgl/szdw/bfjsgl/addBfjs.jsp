<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/bfjsgl/js/bfjsgl.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/bfjsgl/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
<%--		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>--%>
<%--		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>--%>
<%--		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>--%>
		<script type='text/javascript'>
			function qqxszj(jclx,html){
				jQuery("#tbody_"+jclx+"ryxx").append(html);	
			}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/bfjsgl_bfjsglwh" method="post" styleId="bfjsglForm" onsubmit="return false;">
			
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
							<th>
								<span><font color="red">*</font>�������</span>
							</th>
							<td>
								<html:text property="jcrq" styleId="jcrq" onfocus="showCalendar('jcrq','y-mm-dd');"></html:text>
							</td>
							<th>
								<span><font color="red">*</font>�༶</span>
							</th>
							<td>
								<input type="text" id="bjmc" value="" style="width:200px;" readonly="readonly" title=""/>
								<input type="hidden" name="bjdm" id="bjdm" value=""/>
								<button class="btn_01" type="button" onclick="showBj();return false;">ѡ��</button>
							</td>
						</tr>
					</tbody>
					</table>
					
					<div style="height:440px;overflow-y:auto;">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>���</span>
								</th>
							</tr>
						</thead>
							<tr>
								<td colspan="4">						
									<table width="100%" border="0" class="datelist">					
										<thead>
											<tr>
												<td colspan="4">									
													<button type="button" onclick="addLxxs('zc');return false;" class="btn_01">����ѧ��</button>
													<button type="button" onclick="delLxxs('zc');return false;" class="btn_01">ɾ��ѧ��</button>
												</td>
											</tr>
											<tr>
												<td width="5%"><input type="checkbox" name="th" onclick="selectAll(this,'zc');"></td>
												<td width="15%">ѧ��</td>
												<td width="15%">����</td>						
												<td width="15%"><font color="red">*</font>��������</td>															
											</tr>
										</thead>
										<tbody id="tbody_zcryxx"></tbody>							   
									</table>
								</td>						
							</tr>									
						<thead>
							<tr>
								<th colspan="4">
									<span>���</span>
								</th>
							</tr>
						</thead>
						<tr>
							<td colspan="4">							
								<table width="100%" border="0" class="datelist">					
									<thead>
										<tr>
											<td colspan="4">									
												<button type="button" onclick="addLxxs('zd');return false;" class="btn_01">����ѧ��</button>
												<button type="button" onclick="delLxxs('zd');return false;" class="btn_01">ɾ��ѧ��</button>
											</td>
										</tr>
										<tr>
											<td width="5%"><input type="checkbox" name="th" onclick="selectAll(this,'zd');"></td>
											<td width="15%">ѧ��</td>
											<td width="15%">����</td>						
											<td width="15%"><font color="red">*</font>��������</td>															
										</tr>
									</thead>
									<tbody id="tbody_zdryxx"></tbody>
							   </table>
							</td>
						</tr>
						<thead>
							<tr>
								<th colspan="4">
									<span>�Ͽ�</span>
								</th>
							</tr>
						</thead>					
						<tr>
							<td colspan="4">														
								<table width="100%" border="0" class="datelist" style="margin:2px auto;">					
									<thead>
										<tr>
											<td colspan="4">									
												<button type="button" onclick="addLxxs('sk');return false;" class="btn_01">����ѧ��</button>
												<button type="button" onclick="delLxxs('sk');return false;" class="btn_01">ɾ��ѧ��</button>
												</td>
											</tr>
											<tr>
												<td width="5%"><input type="checkbox" name="th" onclick="selectAll(this,'sk');"></td>
												<td width="15%">ѧ��</td>
												<td width="15%">����</td>						
												<td width="15%"><font color="red">*</font>��������</td>															
											</tr>
									</thead>
									<tbody id="tbody_skryxx"></tbody>
								  </table>	
							  </td>						
						</tr>					
						<thead>
							<tr>
								<th colspan="4">
									<span>����ϰ</span>
								</th>
							</tr>
						</thead>					
						<tr>
							<td colspan="4">
								<table width="100%" border="0" class="datelist" style="margin:2px auto;">					
									<thead>
										<tr>
											<td colspan="4">									
												<button type="button" onclick="addLxxs('wzx');return false;" class="btn_01">����ѧ��</button>
												<button type="button" onclick="delLxxs('wzx');return false;" class="btn_01">ɾ��ѧ��</button>
											</td>
										</tr>
										<tr>
											<td width="5%"><input type="checkbox" name="th" onclick="selectAll(this,'wzx');"></td>
											<td width="15%">ѧ��</td>
											<td width="15%">����</td>						
											<td width="15%"><font color="red">*</font>��������</td>															
										</tr>
									</thead>
									<tbody id="tbody_wzxryxx"></tbody>
							   </table>
							  </td>
						</tr>
					</table>				
				</div>
				 			
			  <div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="addSave();">
										����
									</button>
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

