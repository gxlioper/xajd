<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		   function tsfjxs(){	       
		       var fpbzV = $("fpbz").value;
		       if(fpbzV=="����"){
		           $("tsfjid").style.display="";
		       }else{
		           $("tsfjid").style.display="none";
		       }
		   }
		</script>
	</head>
	<body onload="tsfjxs();">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��ʿ����ѡ����</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/data_search" method="post">
		<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm" />" />
							<logic:empty name="rs">
								<br />
								<br />
								<p align="center">
									û���ҵ������ң�
								</p>
							</logic:empty>
							<logic:notEmpty name="rs">
								<input type="hidden" id="disableEle" name="disableEle" value="" />
								<input type="hidden" id="readonlyEle" name="readonlyEle" value="ssbh" />
								<input type="hidden" id="ssbh" name="ssbh" value="<bean:write name="ssbh" />" />
							<table class="formlist" border="0" align="center" style="width: 100%">
								<thead>
									<tr>
										<th colspan="4">
											<span>��Դ��Ϣ</span>
										</th>
									</tr>
								</thead>
								<tbody>		
								
										<tr>
											<th align="right">
												¥�����ƣ�
											</th>
											<td align="left">
												<html:text name='rs' property="ldmc" style="width: 120px"
													styleId="ldmc" disabled="true"/>
											</td>
											<th align="right">
												���Һţ�
											</th>
											<td align="left">
												<html:text name='rs' property="qsh" style="width: 120px"
													styleId="qsh" disabled="true"/>
											</td>
										</tr>
										<tr>
											<th align="right">
												����ס������
											</th>
											<td align="left">
												<html:text name='rs' property="krzrs" style="width: 120px"
													styleId="krzrs" disabled="true"/>
											</td>
											<th align="right">
												����ס������
											</th>
											<td align="left">
												<html:text name='rs' property="yrzrs" style="width: 120px"
													styleId="yrzrs" disabled="true"/>
											</td>
										</tr>
										
										<tr>
											<th align="right">
												���ҵ绰��
											</th>
											<td align="left">
												
												<logic:empty name="noAdmin">
													<html:text name='rs'
													property="qsdh" 
													style="width: 120px;ime-mode:disabled"
													onkeydown="return onlyNum(this,15)"
													onmousedown="return onlyNum(this,15)"
													styleId="qsdh" />
												</logic:empty>
												<logic:notEmpty name="noAdmin">
													<bean:write name='rs' property="qsdh"/>
												</logic:notEmpty>
											</td>
											<th align="right">
												�����ǣ�
											</th>
											<td align="left">
												<logic:empty name="noAdmin">
													<html:select name='rs' property="fpbz" styleId="fpbz" onchange="tsfjxs()">
													<html:options collection="qsLxList" property="en" labelProperty="cn"/>
													</html:select>
												</logic:empty>
												<logic:notEmpty name="noAdmin">
												<html:text name="rs" property="fpbz" readonly="true" styleId="fpbz"></html:text>													
												</logic:notEmpty>
											</td>
											
										</tr>
<%--									<logic:equal value="10491" name="xxdm"><!-- �й����ʴ�ѧ -->	--%>
										<tr>											
											<th align="right">
												�շѱ�׼��
											</th>
											<td align="left">
												<logic:empty name="noAdmin">
													<html:text name='rs' property="sfbz" 
													style="width: 120px;ime-mode:disabled"
													onkeydown="return onlyNum(this,7)"
													onmousedown="return onlyNum(this,7)"
													styleId="sfbz"/>
												</logic:empty>
												<logic:notEmpty name="noAdmin">
													<bean:write name='rs' property="sfbz"/>
												</logic:notEmpty>			
											</td>											
											<th align="right">												
											</th>
											<td align="left">																		
											</td>																					
										</tr>
										<tr id="tsfjid" style="display: none">											
											<th align="right">
												���ⷿ��˵����
											</th>
											<td align="left">
													<html:text name='rs' property="sffqfj" style="width: 120px"	maxlength="15"/>
											</td>											
											<th align="right">												
											</th>
											<td align="left">																		
											</td>																					
										</tr>
<%--										</logic:equal>--%>
										<tr>
											<th align="right">
												��ע��
											</th>
											<td colspan="3">
												<logic:empty name="noAdmin">
													<html:textarea name='rs' property='bz' style="width:95%"
													onblur="chLeng(this,150)"
													rows='4' />
												</logic:empty>
												<logic:notEmpty name="noAdmin">
													<html:textarea name='rs' property='bz' style="width:95%"
													onblur="chLeng(this,150)"
													rows='4' readonly="true"/>
												</logic:notEmpty>
											</td>
										</tr>
										</tbody>	
									</table>	
									<table class="formlist" border="0" align="center" style="width: 100%">
									<thead>
										<tr>
											<th colspan="7">
												<span>��סѧ��������Ϣ����</span>
											</th>
										</tr>
									</thead>
									<tbody>		
										
										
												
													<tr>
														<logic:iterate id="s" name="formtop">
															<td>${s}</td>
														</logic:iterate>
													</tr>
													<logic:iterate id="s1" name="rs" property="xsxx">
													<tr>
															<logic:iterate id="s2" name="s1">
																<td>${s2}</td>
															</logic:iterate>
													</tr>
													</logic:iterate>
													<logic:empty name="rs" property="xsxx">
														<tr><td colspan="7" align="center">û��ѧ����ס��</td></tr>
													</logic:empty>
											</tbody>
										<tfoot>
											<tr>
												<td colspan="7">
													<div class="btn">
														<button id="buttonSave" 
															onclick="refreshForm('/xgxt/zgdzdx_Gygl.do?method=saveInfo')"
															style="width: 80px">
															��	��
														</button>
														&nbsp;&nbsp;
														<button id="buttonClose" onclick="Close();return false;"
															style="width: 80px">
															��	��
														</button>
													</div>
												</td>
											</tr>
										</tfoot>
									</table>
								
								<logic:equal name="flag" value="ok">
									<script>				   
										alert("�����ɹ�!");	
										close();				
									</script>
								</logic:equal>
								<logic:equal name="flag" value="on">
									<script>				   
										alert("����ʧ��!");					
									</script>
								</logic:equal>
							</logic:notEmpty>
		</html:form>
	</body>
</html>
