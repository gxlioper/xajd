<%@ page language="java" import="java.util.*,xgxt.utils.String.StringUtils" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgwnew/sh/js/xsgwsh.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgwnew/sq/js/xsgwsq.js"></script>
		
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/xgservice.js"></script>
		<script type='text/javascript' src="js/moveDiv.js"></script>
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/messageFunction.js"></script>
		
		
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#but_close").click();
				jQuery("#but_save").click();
				jQuery("#tbody_gwxx").load("xsgwsqnew_sq.do?method=gwxx&gwdm=${model.gwdm}&tt="+new Date().getTime(),function(){
					jQuery("#tbody_gwxx tr:first").hide();
				});
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqbh}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
				
			});
			//ѡ�������λ
			function wdgwxzCx(){
				var xh = jQuery("#xh").val();
				var gwdm=jQuery("#gwdm").val();
				if(xh==null || xh==""){
					showAlert("��ѡ��һ��ѧ��");
				}else{
					showDialog("������λ",800,500,"xsgwsqnew_sq.do?method=wdgwxzCx&gwdm="+gwdm+"&xh="+xh+"&lx=tz",{
						close:function(){
						}
					});
				}
				
			}
						
		</script>
	</head>
	<body style="width:100%">
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		<html:form action="/xsgwshnew_sh" method="post" styleId="demoForm">
			<html:hidden name="model" property="sqbh" styleId="sqbh"/>
			<html:hidden name="model" property="splc" styleId="splc"/>
			<html:hidden name="model" property="xh" styleId="xh"/>
			<html:hidden name="model" property="sqbh" styleId="sqbh"/>
			<html:hidden name="model" property="shzt" styleId="shzt"/>
			<html:hidden name="model" property="oldgwdm" styleId="oldgwdm" value="${model.gwdm}"/>
			<div style='tab;width:100%;height:490px;overflow-x:hidden;overflow-y:auto;'>
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
								<span>�������϶���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="knslist">
								<tr>
									<td colspan="4">
									<table style="width:100%">
										<thead><tr>
											<td>ѧ��</td>
											<td>ѧ��</td>
											<td>�϶�����</td>
											<td>�϶�ʱ��</td>
										</tr></thead>
										<logic:iterate id="kns" name="knslist" indexId="index">
										<tbody>
											<tr>
											<td>${kns.xn}</td>
											<td>${kns.xqmc}</td>
											<td>${kns.dcmc}</td>
											<td>${kns.sqsj}</td>												
											</tr>										
										</tbody>
										</logic:iterate>
									</table>
									</td>
								</tr>
						</logic:notEmpty>
						<logic:empty name="knslist">
						<tr><td colspan="4">���������϶���Ϣ</td></tr>
						</logic:empty>
					</tbody>
					<logic:empty name="kzyz">
						<thead>
							<tr>
								<th colspan="4">
									<span>��λ��Ϣ</span> 
								</th>
							</tr>
						</thead>
						<tbody id="tbody_gwxxOne">
							<tr>
								<td colspan="4"><button class="btn_01" onclick="wdgwxzCxF();return false;" type="button">ѡ���λ</button></td>
							</tr>
						</tbody>
					</logic:empty>
					<logic:notEmpty name="kzyz">
						<tr>
							<th colspan="4" style="text-align:left">
								<span>��λ��Ϣ <button class="btn_01" onclick="wdgwxzCx();return false;" type="button">������λ</button></span> 
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
					</tbody>
					</logic:notEmpty>
					<logic:equal name="cssz" property="kcxs" value="yes">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ������ʱ��</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td colspan="4">
							<table style="width:100%;">
								<thead >
									<tr id="qgmxTr">
										<th style="text-align: center;">����</th>
										<logic:iterate id="q" name="qgxmList">
											<th style="text-align: center;" xmdm="${q.dm }">${q.mc }</th>
										</logic:iterate>
									</tr>
								</thead>
								<tbody id="qgmxTbody">
									<%
										List<HashMap<String,String>> qgmxList = (List<HashMap<String,String>>)request.getAttribute("qgmxList");
										List<HashMap<String,String>> qgxmList = (List<HashMap<String,String>>)request.getAttribute("qgxmList");
									
										if (qgmxList != null){
											
											for (int i = 0 ; i < qgmxList.size() ; i++){
												
												String[] qgmxArr = null ;
												
												if(StringUtils.isNotNull(qgmxList.get(i).get("qgmx"))){
													qgmxArr = qgmxList.get(i).get("qgmx").split(",");
												}
												
											%>
												<tr>
													<td align="center">
														<%=qgmxList.get(i).get("qgrq") %>
														<input type="hidden" value="<%=qgmxList.get(i).get("qgrq") %>" name="mxrq"/>
													</td>
													<%
														for (int j = 0 ; j < qgxmList.size() ; j++){
															%>		
															<td align="center">
																<input type="checkbox" value="<%=qgxmList.get(j).get("dm") %>" name="mxxm<%=i %>"
																	disabled="disabled"
																	<%
																	
																		if (StringUtils.stringExistArray(qgxmList.get(j).get("dm"),qgmxArr)){
																			%>
																				checked="checked"
																			<%		
																		}
																	%>
																
																/>
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
					<input type="hidden" name="xxdm" id="xxdm" value=${xxdm } />
					<% if("10511".equals(xxdm) || "10352".equals(xxdm)){ %>
						<thead>
							<tr>
								<th colspan="4">
									<span><bean:message key="lable.qgzx_xys" /></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr >
								<th >
									<span>������Ϣ</span>
								</th>
								<td colspan="3">
									<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
									<html:hidden name="cssz" property="dsfxy" styleId="dsfxy"/>
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
					<logic:equal value="10344" name="xxdm">
						<tr>
							<th >
								<span>�Ƿ���Ӱ���</span>
							</th>
							<td >
								${model.sffcap}
							</td>
							<th >
								<span>�Ƿ���ǿ���Ա</span>
							</th>
							<td >
								${model.sfzqscy}
							</td>
						</tr>
					</logic:equal>
						<tr>
							<th width="16%">
								<font color="red"></font>����ʱ��
							</th>
							<td  colspan="3">
								${model.sqsj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td  colspan="3">
								${model.sqly}
							</td>
						</tr>
					</tbody>
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
						<tr>
							<th>
								<font color="red">*</font>��˽��
							</th>
							<td colspan="3" id="shjgSpan">
								
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>������
								<br />
								<font color="red">��200��</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xsgwsh&id=shyj" />
								<textarea rows="5" style="width: 100%;margin-top: 5px" id="shyj" name="shyj"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
			<tfoot>
						<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
								<button type="button" id="btqd" onclick="save_sh();">
											ȷ��
								</button>
								<%--
									<button type="button" onclick="save_sh('1','ͨ��');">
										ͨ��
									</button>
									<button type="button" onclick="save_sh('2','��ͨ��');">
										��ͨ��
									</button>
									--%>
									<button type="button" name="�� ��" onclick="iFClose();">
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

