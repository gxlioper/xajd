<%@ page language="java"
	import="java.util.*,xgxt.utils.String.StringUtils"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/wdgwsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#but_close").click(iFClose);
				//jQuery("#tbody_gwxx").load("xsgzgl/qgzx/xsgw/gwxx.jsp");
					if("10704"==jQuery("#xxdm").val()&&'${model.gwxzmc }'=="�̶���"){
						jQuery("#gdgcjbzTr").show();
						jQuery("#gwcjsxTr").hide();
					}
				var isopen = jQuery("#xssqkg").val();
				var shzt = jQuery("#shzt1").val();
				if('3' != shzt && (isopen==null||isopen==''||"false" == isopen)){
					jQuery("#tssub").hide();
				}
			});
		</script>
	</head>
	<body style="width: 99%">
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		<html:form action="qgzx_wdgwsq.do?method=wdgwxg" method="post"
			styleId="demoForm">
			<input type="hidden" name="xh" value="${xh}" id="xh" />
			<input type="hidden" name="sqbh" value="${model.sqbh}" id="sqbh" />
			<input type="hidden" name="shzt" value="${shzt}" id="shzt" />
			<input type="hidden" name="shzt1" value="${model.shzt }" id="shzt1" />
			<input type="hidden" name="xssqkg" value="${xssqkg }" id="xssqkg" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<div style='width: 100%; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>

							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp"%>
					<logic:equal value="10344" name="xxdm">
						<thead>
							<tr>
								<th colspan="4">
									<span>�������϶���Ϣ</span>
								</th>
							</tr>
						</thead>
						<logic:notEmpty name="knsInfoList">
							<tbody>
								<tr>
									<td colspan="4">
										<div class="con_overlfow">
											<table class="dateline" width="100%">
												<thead>
													<tr>
														<td>
															ѧ��
														</td>
														<td>
															ѧ��
														</td>
														<td>
															�϶�����
														</td>
														<td>
															<b>�϶�ʱ��</b>
														</td>
														<logic:equal value="10335" name="xxdm" scope="request">
															<td>
																<b>�϶�״̬</b>
															</td>
														</logic:equal>
													</tr>
												</thead>
												<tbody>
													<logic:present name="knsInfoList">
														<logic:notEmpty name="knsInfoList">
															<logic:iterate id="k" name="knsInfoList">
																<tr>
																	<td>
																		${k.xn }
																	</td>
																	<td>
																		${k.xqmc }
																	</td>
																	<td>
																		${k.dcmc }
																	</td>
																	<td>
																		${k.sqsj}
																	</td>
																	<logic:equal value="10335" name="xxdm" scope="request">
																		<td>
																			${k.sfqxrd}
																		</td>
																	</logic:equal>
																</tr>
															</logic:iterate>
														</logic:notEmpty>
														<logic:empty name="knsInfoList">
															<tr>
																<td colspan="5" style="text-align: center;">
																	δ�ҵ��κμ�¼��
																</td>
															</tr>
														</logic:empty>
													</logic:present>
													<logic:notPresent name="knsInfoList">

													</logic:notPresent>
												</tbody>
											</table>
										</div>
									</td>
								</tr>
							</tbody>
						</logic:notEmpty>
						<logic:empty name="knsInfoList">
							<tbody>
								<tr>
									<td colspan="5" style="text-align: center;">
										δ�ҵ��κμ�¼��
									</td>
								</tr>
							</tbody>
						</logic:empty>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<logic:notEqual value="stu" name="userType">
							<tr>
								<td colspan="4">
								<logic:equal value="10344" name="xxdm">
										<div>
										   1.ÿ��ֻ������һ����λ������һ�˲μӶ����λ����������λ�����ڹ���ѧ���ꡣ <br />
										   2.��δ����׼�򱸰����ڹ���ѧ���ѧУһ�ɲ�֧�����ꡣ<br/>
										   3.ѧ���ڹ���ѧ������ѧ�������ÿ�·���һ�Ρ�<br />
										</div>
							    </logic:equal>
							    <br />
									<button class="btn_01" onclick="wdgwxzCx();return false;"
										type="button">
										ѡ���λ
									</button>
									<font color="red">${message}</font>
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th width="16%">
			       					  ѧ��
								<input type="hidden" id="gwdm" name="gwdm"
									value="${model.gwdm }">
							</th>
							<td width="34%">
										${model.xn }
							</td>
							<th width="16%">
								���˵�λ
							</th>
							<td width="34%">
								${model.yrdwmc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								��λ����
							</th>
							<td width="34%">
									${model.gwmc }
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								<logic:equal name="model" property="gwxzdm" value="0"> ��ʱ</logic:equal>
								<logic:equal name="model" property="gwxzdm" value="1">��ʽ</logic:equal>
							</td>
						</tr>
						<tr>
							<th width="16%">
								��Ƹ����
							</th>
							<td width="34%">
									${model.xqrs}��
							</td>

							<th width="16%">
								��λ����
							</th>
							<td>
								<logic:equal name="model" property="gwlx" value="0">��ʱ</logic:equal>
								<logic:equal name="model" property="gwlx" value="1">����</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								��λ���
							</th>
							<td >
									${model.gwxzmc}
							</td>
							<th>
								��λн������
							</th>
							<td>
									${model.gwcjsx}Ԫ
							</td>
						</tr>
						<tr>
							<th>
								��ʱ����
							</th>
							<td colspan="3">
									${model.gssx}Сʱ
								<span id="label"></span>
							</td>
						</tr>
						<tr>
							<th>
								��Ƹ��ʼʱ��
							</th>
							<td>
									${model.zpkssj}
							</td>
							<th>
								��Ƹ����ʱ��
							</th>
							<td>
								<logic:equal name="model" property="cq" value="1">����</logic:equal>
								<logic:notEqual name="model" property="cq" value="1">${model.zpjssj}</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								��ƸҪ��
							</th>
							<td colspan="3">
									${model.gwryyq}
							</td>
						</tr>
						<logic:equal name="cssz" property="kcxs" value="yes">
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�빴ѡ�����ʱ��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table style="width: 100%;">
									<thead>
										<tr id="qgmxTr">
											<th style="text-align: center;">
												����
											</th>
											<logic:iterate id="q" name="qgxmList">
												<th style="text-align: center;" xmdm="${q.dm }">
													${q.mc }
												</th>
											</logic:iterate>
										</tr>
									</thead>
									<tbody id="qgmxTbody">
										<%
											List<HashMap<String,String>> qgmxList = (List<HashMap<String,String>>)request.getAttribute("qgmxList");
											List<HashMap<String,String>> qgxmList = (List<HashMap<String,String>>)request.getAttribute("qgxmList");
										
											if (qgmxList != null){
												
												for (int i = 0 ; i < qgmxList.size() ; i++){
													
													String[] qgmxArr = null;
													if(StringUtils.isNotNull(qgmxList.get(i).get("qgmx"))){
														qgmxArr = qgmxList.get(i).get("qgmx").split(",");
													}
													
												%>
										<tr>
											<td align="center">
												<%=qgmxList.get(i).get("qgrq") %>
												<input type="hidden"
													value="<%=qgmxList.get(i).get("qgrq") %>" name="mxrq" />
											</td>
											<%
															for (int j = 0 ; j < qgxmList.size() ; j++){
																%>
											<td align="center">
												<input type="checkbox"
													value="<%=qgxmList.get(j).get("dm") %>" name="mxxm<%=i %>"
													<%
																		
																			if (StringUtils.stringExistArray(qgxmList.get(j).get("dm"),qgmxArr)){
																				%>
													checked="checked"
													<%		
																			}
																		%> />
											</td>
											<%
															}
														%>
										</tr>
										<%	
												}
											}
										%>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
					</logic:equal>
					<%--����ʦ�����Ի� ������Э��--%>
					<% if("10511".equals(xxdm) || "10352".equals(xxdm)){ %>
					<thead>
						<tr>
							<th colspan="4">
								<span><bean:message key="lable.qgzx_xys" />
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span>������Ϣ</span>
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden name="cssz" property="dsfxy" styleId="dsfxy" />
								<script type="text/javascript">
										//���ø��� 
										jQuery(function(){
											var gid = jQuery('#dsfxy').val();
											jQuery.MultiUploader_q({
												gid : gid
												});
										});
									</script>
							</td>
						</tr>
					</tbody>
					<% } %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>��������
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="4">
								<textarea rows="5" onblur="checkLen(this,500)"
									style="width: 100%" id="sqly" name="sqly">${model.sqly}</textarea>
								<%--
								<html:textarea rows="5" style="width: 90%" property="sqly" styleId="sqly" value=""></html:textarea>
							--%>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px">
			</div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"
								<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" type="button" id="but_save"
									onclick="updateForm('update');return false;">
									����ݸ�
								</button>
								<button type="button" id="tssub"
									onclick="updateForm('submit');return false;" id="buttonSave">
									�ύ����
								</button>
								<button type="button" type="button" id="but_close">
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

