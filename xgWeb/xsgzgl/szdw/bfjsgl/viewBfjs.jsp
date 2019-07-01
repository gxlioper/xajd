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
		<script type='text/javascript'>
			
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
								<span>�������</span>
							</th>
							<td>
								${map.jcrq}
							</td>
							<th>
								<span>�༶</span>
							</th>
							<td>
								${map.bjmc}
							</td>
						</tr>
					</tbody>
					</table>
					
					<div style="height:440px;overflow-y:auto;">
					<table width="100%" border="0" class="formlist">
					<logic:notEmpty name="map" property="zcqqList">
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
												<td width="15%">ѧ��</td>
												<td width="15%">����</td>						
												<td width="15%">��������</td>															
											</tr>
										</thead>
										<tbody id="tbody_zcryxx">									
																						
												<logic:iterate id="ry" name="map" property="zcqqList" indexId="s"> 
													<tr>													
														<td name='xh'>
															${ry.xh}
														</td>
														<td>
															${ry.xm}
														</td>
														<td>
															${ry.qqlxmc}
														</td>
												</logic:iterate>
																					
										
										</tbody>																																				   
									</table>
								</td>						
							</tr>
							</logic:notEmpty>
							<logic:notEmpty name="map" property="zdqqList">									
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
													<td width="15%">ѧ��</td>
													<td width="15%">����</td>						
													<td width="15%">��������</td>															
												</tr>
											</thead>
											<tbody id="tbody_zdryxx">
													<logic:iterate id="ry" name="map" property="zdqqList" indexId="s"> 
															<tr>
																													
																<td name='xh'>
																	${ry.xh}
																</td>
																<td>
																	${ry.xm}
																</td>
																<td>
																	${ry.qqlxmc}
																</td>
														</logic:iterate>										
											</tbody>
									   </table>
									</td>
								</tr>
						</logic:notEmpty>
						<logic:notEmpty name="map" property="skqqList">
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
													<td width="15%">ѧ��</td>
													<td width="15%">����</td>						
													<td width="15%">��������</td>															
												</tr>
										</thead>
										<tbody id="tbody_skryxx">
											
												<logic:iterate id="ry" name="map" property="skqqList" indexId="s"> 
														<tr>													
															<td name='xh'>
																${ry.xh}
															</td>
															<td>
																${ry.xm}
															</td>
															<td>
																${ry.qqlxmc}
															</td>
													</logic:iterate>																						
										</tbody>
									  </table>	
								  </td>						
							</tr>
							</logic:notEmpty>
							<logic:notEmpty name="map" property="wzxqqList">					
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
													<td width="15%">ѧ��</td>
													<td width="15%">����</td>						
													<td width="15%">��������</td>															
												</tr>
											</thead>
											<tbody id="tbody_wzxryxx">									
												
													<logic:iterate id="ry" name="map" property="wzxqqList" indexId="s"> 
															<tr>																											
																<td name='xh'>
																	${ry.xh}
																</td>
																<td>
																	${ry.xm}
																</td>
																<td>
																	${ry.qqlxmc}
																</td>
														</logic:iterate>													
											</tbody>
									   </table>
									  </td>
								</tr>
							</logic:notEmpty>
					</table>
						
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��ٵ���</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<table width="100%" border="0" class="datelist" style="margin:2px auto;">
										<thead>
											<tr>
												<td>
													ѧ��
												</td>
												<td>
													����
												</td>
											</tr>
										</thead>
										<tbody>
											<logic:notEmpty name="map" property="zccqList">
												<logic:iterate id="ry" name="map" property="zccqList">
													<tr>
														<td>${ry.xh}</td>
														<td>${ry.xm}</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
						<thead>
							<tr>
								<th colspan="2">
									<span>�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<table width="100%" border="0" class="datelist" style="margin:2px auto;">
										<thead>
											<tr>
												<td>
													ѧ��
												</td>
												<td>
													����
												</td>
											</tr>
										</thead>
										<tbody>
											<logic:notEmpty name="map" property="zdcqList">
												<logic:iterate id="ry" name="map" property="zdcqList">
													<tr>
														<td>${ry.xh}</td>
														<td>${ry.xm}</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
						<thead>
							<tr>
								<th colspan="2">
									<span>�Ͽε���</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<table width="100%" border="0" class="datelist" style="margin:2px auto;">
										<thead>
											<tr>
												<td>
													ѧ��
												</td>
												<td>
													����
												</td>
											</tr>
										</thead>
										<tbody>
											<logic:notEmpty name="map" property="skcqList">
												<logic:iterate id="ry" name="map" property="skcqList">
													<tr>
														<td>${ry.xh}</td>
														<td>${ry.xm}</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
						<thead>
							<tr>
								<th colspan="2">
									<span>�����޵���</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<table width="100%" border="0" class="datelist" style="margin:2px auto;">
										<thead>
											<tr>
												<td>
													ѧ��
												</td>
												<td>
													����
												</td>
											</tr>
										</thead>
										<tbody>
											<logic:notEmpty name="map" property="wzxcqList">
												<logic:iterate id="ry" name="map" property="wzxcqList">
													<tr>
														<td>${ry.xh}</td>
														<td>${ry.xm}</td>
													</tr>
												</logic:iterate>
											</logic:notEmpty>
										</tbody>
									</table>
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

