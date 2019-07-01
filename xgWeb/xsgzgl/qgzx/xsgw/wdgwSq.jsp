<%@ page language="java" import="java.util.*,xgxt.utils.String.StringUtils" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgw/js/wdgwsq.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#but_close").click(iFClose);
				//jQuery("#but_save").click(saveForm);
				/*jQuery("#but_save").click(function(){
					saveForm();
				})*/
				//jQuery("#tbody_gwxx").load("xsgzgl/qgzx/xsgw/gwxx.jsp");
				var qgxmSize = jQuery("#qgxmSize").val();
				if("0"==qgxmSize) { 
					initQgmx();
				 } 
			});
			function initQgmx(){
				
				jQuery("#qgmxTbody").empty();
				
				for (var i = 0 ; i < "7"; i++){
					var nextDate = getNextDate(i);
					var rInput = jQuery("<input type='hidden' name='mxrq' value='"+nextDate+"'/>");
					var td = jQuery("<td align='center'>"+nextDate+"</td>");
					var tr = jQuery("<tr></tr>");
					td.append(rInput);
					tr.append(td);
					
					var qjxmCount = jQuery("#qgmxTr th").size()-1;
					
					for (var j = 0 ; j < qjxmCount ; j++){
						var cbox = jQuery("<input name='mxxm"+i+"' type='checkbox' value='"+jQuery("#qgmxTr th").eq(j+1).attr("xmdm")+"'/>");
						var td = jQuery("<td align='center'></td>");
						td.append(cbox);
						tr.append(td);
					}
					
					jQuery("#qgmxTbody").append(tr);
				}
			};
			
			function getNextDate(curDate){
				switch(curDate) { 
					case 0: 
						return "����һ"; 
					break; 
					case 1: 
						return "���ڶ�";  
					break; 
					case 2: 
						return "������";  
					break;
					case 3: 
						return "������"; 
					break; 
					case 4: 
						return "������";  
					break; 
					case 5: 
						return "������";  
					break; 
					case 6: 
						return "������";  
					break; 
					default: 
					break;
				}
			}
			
		</script>
	</head>
	<body style="width:100%">
		<% String xxdm = (String) request.getAttribute("xxdm"); %>
		<html:form action="qgzx_wdgwsq.do?method=wdgwSq" method="post" styleId="demoForm">
			<input type="hidden" name="qgxmSize" id="qgxmSize" value="${qgxmSize }"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist" >
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<logic:notEqual value="10704" name="xxdm">
						<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>					
					</logic:notEqual>
					<logic:equal value="10704" name="xxdm">
						<%@ include file="/xsgzgl/qgzx/xsgw/selectStudent.jsp" %>
					</logic:equal>
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
												<td>ѧ��</td>
												<td>ѧ��</td>
												<td>�϶�����</td>
												<td ><b>�϶�ʱ��</b></td>
												<logic:equal value="10335" name="xxdm" scope="request">
													<td ><b>�϶�״̬</b></td>
												</logic:equal>	
											</tr>
										</thead>
										<tbody>
											<logic:present name="knsInfoList">
												<logic:notEmpty name="knsInfoList">
													<logic:iterate id="k" name="knsInfoList">
														<tr>
															<td>${k.xn }</td>
															<td>${k.xqmc }</td>
															<td>${k.dcmc }</td>
															<td >
																${k.sqsj}
															</td>
															<logic:equal value="10335" name="xxdm" scope="request">
																<td >
																	${k.sfqxrd}
																</td>	
															</logic:equal>	
														</tr>
													</logic:iterate>
												</logic:notEmpty>
												<logic:empty name="knsInfoList">
													<tr>
														<td colspan="5" style="text-align:center;">δ�ҵ��κμ�¼��</td>
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
							<td colspan="5" style="text-align:center;">δ�ҵ��κμ�¼��</td>
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
						<tr>
							<td colspan="4">
							<logic:equal value="10344" name="xxdm">
								<div>
								   1.ÿ��ֻ������һ����λ������һ�˲μӶ����λ����������λ�����ڹ���ѧ���ꡣ <br />
								   2.��δ����׼�򱸰����ڹ���ѧ���ѧУһ�ɲ�֧�����ꡣ<br/>
								   3.ѧ���ڹ���ѧ������ѧ�������ÿ�·���һ�Ρ�<br />
								</div>
							</logic:equal>
							<br/><button class="btn_01" onclick="wdgwxzCx();return false;" type="button">ѡ���λ</button>
							
							</td>
						</tr>
					</tbody>
					<logic:equal name="cssz" property="kcxs" value="yes">
					<thead>
						<tr>
							<th colspan="4">
								<span>�빴ѡ�����ʱ��</span>
								<label>
									<input type="checkbox" onclick="checkAll(this);" checked="checked"/>ȫѡ/ȡ��ȫѡ
								</label>
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
					
<%--					����ʦ�����Ի� ������Э��--%>
					<% if("10511".equals(xxdm) || "10352".equals(xxdm)){ %>
						<thead>
							<tr>
								<th colspan="4">
									<span><bean:message key="lable.qgzx_xys" /></span>
								</th>
							</tr>
						</thead>
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
					<% } %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<logic:equal value="10344" name="xxdm">
						<tr>
							<th >
								<font color="red">*</font><span>�Ƿ���Ӱ���</span>
							</th>
							<td >
								<html:radio property="sffcap" value="��">��</html:radio>&nbsp;
								<html:radio property="sffcap" value="��">��</html:radio>
							</td>
							<th >
								<font color="red">*</font><span>�Ƿ���ǿ���Ա</span>
							</th>
							<td >
								<html:radio property="sfzqscy" value="��">��</html:radio>&nbsp;
								<html:radio property="sfzqscy" value="��">��</html:radio>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>�к��س�
								<br />
								<font color="red">��500��</font>
							</th>
							<td colspan="3">
								<html:textarea property="yhtc" onblur="checkLen(this,500)" style="width: 100%" styleId="yhtc" rows="5">
								</html:textarea>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>������ͥ���
								<br />
								<font color="red">��500��</font>
							</th>
							<td colspan="3">
								<html:textarea property="jtqk" onblur="checkLen(this,500)" style="width: 100%" styleId="jtqk" rows="5">
								</html:textarea>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>���ڹ���ѧ����ʶ
								<br />
								<font color="red">��500��</font>
							</th>
							<td colspan="3">
								<html:textarea property="qgzxrs" onblur="checkLen(this,500)" style="width: 100%" styleId="qgzxrs" rows="5">
								</html:textarea>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="11488" name="xxdm">
							    <th >
									<font color="red">*</font><span>ѧϰ�ɼ�</span>
								</th>
								<td >
									<html:select property="xxcj" styleId="xxcj" style="width:150px;">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
										<html:option value="һ��">һ��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
								<th >
									<font color="red">*</font><span>����״��</span>
								</th>
								<td >
									<html:select property="stzk" styleId="stzk" style="width:150px;">
										<html:option value=""></html:option>
										<html:option value="����">����</html:option>
										<html:option value="����">����</html:option>
										<html:option value="һ��">һ��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
						</logic:equal>
						<tr>
							<th width="16%">
								<font color="red">*</font>��������
								<br />
								<font color="red">��500��</font>
							</th>
							<td  colspan="4">
								<textarea rows="5" style="width: 100%" onblur="checkLen(this,500)" id="sqly" name="sqly"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
						<tr>
							<td colspan="4" >
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<%--<button type="button" type="button" id = "but_save">
										����ݸ�
									</button>--%>
									<button type="button" id="tssub"  onclick="tj('qgzx_wdgwsq.do?method=wdgwSq&type=submit');return false;" id="buttonSave">
										�ύ����
									</button>
									<button type="button" type="button" id= "but_close">
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

