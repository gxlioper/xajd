<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function submitYhsz(spgwStr){
				refreshForm("/xgxt/splcNew.do?method=splcYhsz&spgw="+spgwStr+"&lcid="+$('lcid').value);
				//var url = "/xgxt/splcNew.do?method=splcYhsz&spgw="+spgwStr+"&lcid="+$('lcid').value
				//showDialog('',700,400,url);
				//return false;
			}

			//��ֵ
			function fz(obj,xh){
				var value = jQuery(obj).val();
				if(value == null || value == ""){
					jQuery("#newspgwzdm"+xh).val("blank");
				}else{		
					jQuery("#newspgwzdm"+xh).val(value);		
				}
			}

			function saveSplc(){

				if($("lcmc") && $("lcmc").value==""){
					alertError("�������Ʋ���Ϊ��!");
					return false;
				}
				if($("ms") && $("ms").value==""){
					alertError("����˵������Ϊ��!");
					return false;
				} 

				var olds = jQuery("input[name='oldspgwzdm']");
				var news = jQuery("input[name='newspgwzdm']");
				var flg = true;
				for(var n = 0;n<olds.length;n++){
					if(jQuery(news[n]).val() == "blank" || jQuery(news[n]).val() == null || jQuery(news[n]).val() == ""){
						continue;
					}
					if(jQuery(olds[n]).val() != jQuery(news[n]).val()){
						flg = false;
						break;
					}
				}
				if(!flg){
				     showConfirm("ע�⣺��λ���ݷ�Χ�޶��е������ø�λ���û������Ƿ�ȷ�����³�ʼ����",{"okFun":function(){
				    	 refreshForm('splcNew.do?method=splcUpdate&doType=update&type=csh');
					  },"cancelFun":function(){
							 refreshForm('splcNew.do?method=splcUpdate&doType=update');
						}});
				}else{
					refreshForm('splcNew.do?method=splcUpdate&doType=update');
				}		
			}
			
		</script>
	</head>
	
	<body>
		<html:form action="/splcNew" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<html:hidden property="id" name="rs"/>
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
				</p>
			</div>
			
			<!-- ��ʾ��Ϣ end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						1.ͨ�ø�λ�����Զ��������ͬʱʹ�ã�����<font color="red">�û�����</font>����������������ʹ�õ�ǰͨ�ø�λ����ԱҲ����֮�����仯��
					<br />
						2.�Զ����λ��ֻ����ڵ�ǰ��������У�����<font color="red">�û�����</font>�������������������е������Ա����Ӱ�졣
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			
			<input type="hidden" name="lcid" id="lcid" value="<bean:write name = "rs" property="id" />" /> 
			<div class="tab" id="dgncz">
				<table width="100%" border="0" class="formlist">
					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" class=""
										onclick="saveSplc();return false;"
										id="btn_bc">
										�� ��
									</button>
									<button type="button" class="" onclick="Close();return false;" id="btn_fh">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>���̻�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>��������
							</th>
							<td colspan="3" style="width: 84%" >
								<html:text  property="mc" styleId="mc" name = "rs" style="width:120px;" maxlength="32"  styleClass="text_nor" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								����ģ��
							</th>
							<td  id="p_ld"  style="width:34%" >
								<html:select name = "rs" property="djlx" styleId="djlx" style="width:190px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="ssmkList" property="mkdm" 
										labelProperty="mkmc" />
								</html:select>
							</td>
							<th style="width:16%">
								���̷ּ���
							</th>
							<td id="p_ld" style="width:84%">
							
								<html:select property="maxSize" styleId="maxSize" style="width:150px" disabled="true">
									<html:option value="1">һ</html:option>
									<html:option value="2">��</html:option>
									<html:option value="3">��</html:option>
									<html:option value="4">��</html:option>
									<html:option value="5">��</html:option>
									<html:option value="6">��</html:option>
									<html:option value="7">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<font color="red">*</font>����˵��
							</th>
							<td colspan="3" style="width:84%">
								<html:textarea property='ms' name = "rs" style="width:95%" styleId="ms" rows='2' onblur="checkLen(this,64);"/>
							</td>
						</tr>
					<thead>
						<tr>
							<th colspan="4">
								<span>
									����ά��
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div id="rsTable" style="width:100%;">
									<table  style="width:100%;" border="0">
									<thead>
										<tr align="center" style="cursor:hand;height: 20px">
											<td>
												�������
											</td>
											<td>
												��λ����
											</td>
											<td>
												�����û���
											</td>
											<td>
												��λ����
											</td>
											<td>
												���ݷ�Χ�޶�
											</td>
											<td>
												�û�����
											</td>
										</tr>
									</thead>
										<logic:iterate name="lcgwrs" id="s" indexId="f">
											
											<tr style="height: 20px">	
												<td style="width:16%">
													${f+1}
												</td>
												<logic:iterate id="v" name="s" offset="1" length="1">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="3" length="1">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
												<logic:iterate id="e" name="s" offset="2" length="1">
												</logic:iterate>
												<logic:iterate id="w" name="s" offset="4" length="1">
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="0" length="1">
												<td>
													<logic:equal name="e" value="1">
															ͨ�ø�λ
													</logic:equal>
													<logic:notEqual name="e" value="1">
															�Զ����λ
													</logic:notEqual>
												</td>
												<td>
													<html:select property="spgwzdm${f}"  value="${w}" onchange="fz(this,${f})">
															<html:option value=""></html:option>
															<html:option value="bzr">�����ο�</html:option>
															<html:option value="fdy">����Ա��</html:option>
															<html:option value="bzrfdy">������+����Ա��</html:option>
													</html:select>
													<input type="hidden" id="oldspgwzdm${f}" value="${w}" name="oldspgwzdm" />
													<input type="hidden" id="newspgwzdm${f}" name="newspgwzdm"/>													
												</td>
													<td>
<%--													<logic:equal name="e" value="1">--%>
<%--															�̶���λ--%>
<%--													</logic:equal>--%>
<%--													<logic:notEqual name="e" value="1">--%>
													<button type="button" onclick="submitYhsz('<bean:write name="v" />')">�û�����</button>
<%--													</logic:notEqual>--%>
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>

				<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
			<!-- ��ȴ� -->
			<%@ include file="/comm/loading.jsp"%>
			<!-- ��ȴ� end-->
		</html:form>
	</body>
</html>
