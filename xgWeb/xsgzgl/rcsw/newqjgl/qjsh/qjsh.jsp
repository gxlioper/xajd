<%@ page language="java" contentType="text/html; charset=GBK" import="java.util.*,xgxt.utils.String.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsh/js/operation.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${data.qjsqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${data.splcid}&shid=${data.shid}");

			var tr=jQuery("tr[name='qjxx']").first().next();
			tr.show();
			tr.next().show();
			//չ��/��������
			var haveshow=false;
			var havehide=false;
			jQuery("#all").click(function(){
				jQuery("tr[name='qjxx']").each(function(){
						var tr=jQuery(this).next();
						if(tr.is(":hidden")){
							haveshow=true;
							tr.show();
							tr.next().show();
						}else{
							havehide=true;
							tr.hide();
							tr.next().hide();
						}
				});
				//��������ص�������ʾ�ģ�ͳһ����Ϊ����
				if(haveshow&&havehide){
					jQuery("tr[name='qjxx']").each(function(){
						var tr=jQuery(this).next();
							tr.hide();
							tr.next().hide();
					});
					haveshow=false;
					havehide=false;
				}
			});
			//չ�� ����
			jQuery("[name=show]").each(function(){
				jQuery(this).click(function(){
					var tr=jQuery(this).parents("tr[name='qjxx']").next();
					if(tr.is(":hidden")){
						tr.show();
						tr.next().show();
					}else{
						tr.hide();
						tr.next().hide();
					}
				});
			});
			
		});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/qjsh">
		 <html:hidden property="qjsqid"/>
		 <html:hidden property="xn"/>
		 <html:hidden property="xq"/>
		 <html:hidden property="qjzt"/>
		 <html:hidden property="xh"/>
		 <html:hidden property="splcid" styleId="splc"/>
		 <html:hidden property="shid" styleId="shid"/>
		 <html:hidden property="shzt" styleId="shzt"/>
		<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>
				<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							ѧ��
						</th>
						<td align="left">
							${data.xn}
						</td>
						<th align="right">
							ѧ��
						</th>
						<td align="left">
							${data.xqmc}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							�������
						</th>
						<td align="left">
							${data.qjts}&nbsp;&nbsp;��&nbsp;&nbsp;
						</td>
						<th align="right">
							�������
						</th>
						<td align="left">
							${qjlxmc}
						</td>
					</tr>
					<logic:equal value="10351" name="xxdm">
					<tr>
						<th align="right" width="10%">
							��ٽ���
						</th>
						<td align="left" >
							${data.qjjs}&nbsp;&nbsp;��&nbsp;&nbsp;
						</td>
						<th align="right">
							�Ƿ���У
						</th>
						<td align="left">
							${data.sflxmc}
						</td>
					</tr>
					</logic:equal>
					<tr>
						<th align="right" width="10%">
							��ٿ�ʼʱ��
						</th>
						<td align="left">
							${data.kssj }
						</td>
						<th align="right">
							��ٽ���ʱ��
						</th>
						<td align="left">
							${data.jssj }
						</td>
					</tr>
					<logic:equal name="xxdm" value="12727">
						<tr>
							<th align="right" width="10%">
								��ٽڴ�
							</th>
							<td colspan="3">
								${data.mdd }
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="12688">
						<tr>
							<th align="right" width="10%">
								��ٽڴ�
							</th>
							<td colspan="3">
								${data.mdd }
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="70002">
						<tr>
							<th align="right">
								У��У��
							</th>
							 <td align="left">
							 	${data.xnxw }
							 </td>
							 <th></th>
							 <td></td>
						</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="12866">
						<tr>
							<th align="right" width="10%">
								�ҳ��绰
								
							</th>
							<td colspan="3">
								${data.jzdh }
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								��ע&nbsp;
							</th>
							<td colspan="3">
								${data.bz}
							</td>
						</tr>
						</logic:equal>
					<logic:equal value="10511" name="xxdm">
						<tr>
							<th>��ٿγ�</th>
							<td colspan="3">
								<table width="100%">
									<thead>
										<tr>
											<td>�γ�����</td>
											<td>�ο���ʦ����</td>
											<td>�ο���ʦ��ϵ��ʽ</td>
										</tr>
									</thead>
									<tbody id="qjkc">
									<logic:present name="qjkcList">
										<logic:iterate id="qjkc" name="qjkcList" indexId="i">
											<tr>
												<td>
												${qjkc.kcmc }
												</td>
												<td>
												${qjkc.rklsxm }
												</td>
												<td>
												${qjkc.rklslxfs }
												</td>
											</tr>
										</logic:iterate>
										<logic:empty name="qjkcList">
											<tr>
												<td colspan="3" align="center">δ�ҵ��κμ�¼��</td>
											</tr>
										</logic:empty>
									</logic:present>
									</tbody>
								</table>
							</td>
						</tr>
						</logic:equal>
						<logic:equal value="true" name="qjmxEnable" >
							</tbody>
							<thead>
								<tr>
									<th colspan="4">
										<span>�����ϸ���</span>
									</th>
								</tr>
							</thead>
							<tbody>
							<tr>
								<td colspan="4">
									<table style="width:100%;">
										<thead >
											<tr id="qjmxTr">
												<th style="text-align: center;">����</th>
												<logic:iterate id="q" name="qjxmList">
													<th style="text-align: center;" xmdm="${q.dm }">${q.mc }</th>
												</logic:iterate>
											</tr>
										</thead>
										<tbody id="qjmxTbody">
											<%
												List<HashMap<String,String>> qjmxList = (List<HashMap<String,String>>)request.getAttribute("qjmxList");
												List<HashMap<String,String>> qjxmList = (List<HashMap<String,String>>)request.getAttribute("qjxmList");
											
												if (qjmxList != null){
													
													for (int i = 0 ; i < qjmxList.size() ; i++){
														
														String[] qjmxArr = qjmxList.get(i).get("qjmx").split(",");
														
													%>
														<tr>
															<td align="center">
																<%=qjmxList.get(i).get("qjrq") %>
																<input type="hidden" value="<%=qjmxList.get(i).get("qjrq") %>" name="mxrq"/>
															</td>
															<%
																for (int j = 0 ; j < qjxmList.size() ; j++){
																	%>		
																	<td align="center">
																		<input type="checkbox" value="<%=qjxmList.get(j).get("dm") %>" name="mxxm<%=i %>"
																			disabled="disabled"
																			<%
																			
																				if (StringUtils.stringExistArray(qjxmList.get(j).get("dm"),qjmxArr)){
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
						</logic:equal>
						
						<logic:equal value="10695" name="xxdm">
							<tr>
								<th align="right">
									�໤������
								</th>
								<td>
									${data.jhrxm}
								</td>
								<th align="right">
									�໤����ϵ��ʽ
								</th>
								<td>
									${data.jhrlxfs}
								</td>
							</tr>
							<tr>
								<th align="right">
									��ͨ����
								</th>
								<td>
									${data.jtgjmc}
								</td>
								<th align="right">
									Ŀ�ĵ�
								</th>
								<td>
									${data.mdd}
								</td>
							</tr>
						</logic:equal>
					<tr>
						<th align="right" width="10%">
							�������
						</th>
						<td colspan="3">
							${data.qjsy}
						</td>
					</tr>
					<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="filepath" styleId="fjid"/>
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
				</tbody>
				
				<!-- ��ʷ��ټ�¼START -->
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span style="float:left">�����ʷ��Ϣ</span><a href="javascript:;" id="all" style="float:right;margin-right:30px;color: blue">[չ��/��������]</a>
							</th>
						</tr>
					</thead>
					<logic:notEmpty name="history">
					<logic:iterate id="model" name="history" scope="request">
						<tbody id="allmes">
							<tr name="qjxx" bgcolor="#FFFCCC">
								<td align="right"  width="16%">
									���ʱ��:
								</td>
								<td width="34%">
								${model.kssj }~${model.jssj }
								</td>
								<td align="right" width="16%">
									���ʱ��:
								</td>
								<td>
									${model.qjts}&nbsp;&nbsp;��&nbsp;&nbsp;
								</td>
								<td align="center">
									<a href="javascript:;" name="show">[չ��/����]</a>
								</td>
							</tr>
							<tr style="display:none;">
								<th>
									�������:
								</th>
								<td colspan="4">
									${model.qjsy}
								</td>
							</tr>
							<tr style="display:none;">
								<th>
									������Ϣ:
								</th>
								<td colspan="4">
									${model.xjbz}
								</td>
							</tr>
						</tbody>
					</logic:iterate>
					</logic:notEmpty>
					<logic:empty name="history">
						<tr>
							<td colspan="5" align="center">
								����������ʷ��¼
							</td>
						</tr>
					</logic:empty>
				</table>
				<!-- ��ʷ��ټ�¼END -->	
				
				<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>�����Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody><%--
					<tr>
						<th >
							<font color="red">*&nbsp;</font>���״̬
						</th>
						<td align="left" colspan="3">
							<html:select property="shzt" style="width:150px" styleId="shzt">
								<html:option value="0">δ���</html:option>
								
								<html:option value="1">ͨ��</html:option>
								<html:option value="2">��ͨ��</html:option>
								<html:option value="3">�˻�</html:option>
							</html:select>
						</td>
						</tr>
					<tr>
						--%>
					<tr>
						<th>
							<font color="red">*</font>��˽��
						</th>
						<td colspan="3" id="shjgSpan">
							
						</td>
					</tr>
					<tr>
					<th width="20%">
							<font color="red">*&nbsp;</font> ������&nbsp;
							<br />
							<font color="red">(��200��)</font>
						</th>
						<td colspan="3">
							<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=qqgl&id=shyj" />
							<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);">${rs.shyj}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
				<logic:notEqual value="�������" name="shztmc">	
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
									<td colspan="4" id="shlccx">
									
									</td>
								</tr>
							
							</tbody>
						
					</table>
				</logic:notEqual>	
		</div>
		<div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" id="btqd" onclick="save_sh();">
									�� ��
								</button>
							<%--
								<button type="button" onclick="save_sh('1','ͨ��');">
									ͨ��
								</button>
								<button type="button" onclick="save_sh('2','��ͨ��');">
									��ͨ��
								</button>
								<button type="button" onclick="save_sh('3','�˻�');">
									�˻�
								</button>
								--%><%--<button type="button" name="����" id="buttonClose" onclick="save('qjsh.do?method=qjsh&type=save','shzt-shyj');return false;">
									����
								</button>
								--%>
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</div>
		</html:form>
	</body>
			<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
</html>
