<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/gygl/gyglFunction.js"></script>
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
	<body onload="tsfjxs()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/gygl/gyglFunction.js"></script>
		<html:form action="/zgdzdx_Gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:equal value="true" name="flag">
		      <script language="javascript">
			   alert("�޸ĳɹ���");
			   Close();			 
	          window.dialogArguments.document.getElementById("searchGo").click();			   
	         </script>
	        </logic:equal>
	        <logic:equal value="false" name="flag">
		      <script language="javascript">
			   alert("�޸�ʧ�ܣ�");
			    Close();
			   window.dialogArguments.document.getElementById("searchGo").click();
	          </script>
	        </logic:equal>
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>������Ϣά��</span>
							<input type="hidden" id="ssbh" name="ssbh"
									value="<bean:write name='rs' property="ssbh" scope="request"/>" />
							<input type="hidden" id="lddm" name="lddm"
									value="<bean:write name='rs' property="lddm" scope="request"/>" />
							<input type="hidden" id="qsh" name="qsh"
									value="<bean:write name='rs' property="qsh" scope="request"/>" />
							<input type="hidden" id="cs" name="cs"
									value="<bean:write name='rs' property="cs" scope="request"/>" />
						</th>
					</tr>
				</thead>
				<tbody>		
										<tr>
											<th align="right">
												��ţ�
											</th>
											<td align="left">
												<html:text name='rs' property="ssbh" style="width: 120px"
													styleId="ssbh" disabled="true" />
											</td>
											<th align="right">
												¥�����ƣ�
											</th>
											<td align="left">
												<html:text name='rs' property="ldmc" style="width: 120px"
													styleId="ldmc" disabled="true" />
											</td>
										</tr>
										<tr>
										   
											<th align="right">
												���Һţ�
											</th>
											<td align="left">
												<html:text name='rs' property="qsh" style="width: 120px"
													styleId="qsh" disabled="true" />
											</td>
											<th align="right">
												������
											</th>
											<td align="left">
												<html:text name='rs' property="cs" style="width: 120px"
													styleId="cs" disabled="true" />
											</td>
											
										</tr>
										<tr>
											<th align="right">
												�����ǣ�
											</th>
											<td align="left">
												<html:select name="rs" property="fpbz" styleId="fpbz" onchange="tsfjxs()">
													<html:options collection="fpbjList" property="en" labelProperty="cn"/>
												</html:select>
											</td>
											<th align="right">
												��λ����<br>(��������)
											</th>
											<td align="left">
												<html:text name='rs' property="jcws" style="width: 120px"
													styleId="jcws" onkeypress="chkonlynum()" maxlength="2"
													onblur="onlyNumInput(this)" />
											</td>
											
										</tr>
										<tr>
										<th align="right">
												��׼�շ�
											</th>
											<td align="left">
												<html:text name='rs' property="sfbz"
													styleId="sfbz"
													onkeydown="return onlyNum(this,5)"
													onmousedown="return onlyNum(this,5)"
													maxlength="5" 
													style="width: 120px;ime-mode:disabled"/>
											</td>
																					<td align="right">
<%--										�Ƿ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>���޷��䣺--%>
										</td>
										<td>
<%--										<html:select name="rs" property="sffqfj" styleId="fpbz">													--%>
<%--													<html:option value=""></html:option>--%>
<%--													<html:option value="��">��</html:option>--%>
<%--													<html:option value="��">��</html:option>--%>
<%--												</html:select>--%>
										</td>
										</tr>
										<tr id="tsfjid" style="display: none">
											<th align="right">
												���ⷿ��˵����
											</th>
											<td align="left">
												<html:text name='rs' property="sffqfj" style="width: 120px"
													maxlength="15" />
											</td>
											<td align="right">
											</td>
											<td align="left">
											</td>
										</tr>	
										</tbody>									
									</table>
<%--									<div align="left">--%>
<%--										<input type="checkbox" name="related" value="do">--%>
<%--										<font color="red">�Ƿ�Ӱ�쵽��¥�������������Ʒ���</font>--%>
<%--									</div>--%>
		<table border="0" class="formlist" align="center" style="width: 100%">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<!-- �ǲ鿴 -->
								<logic:notEqual name="doType" value="view">
									<button onclick="refreshForm('/xgxt/zgdzdx_Gygl.do?method=roomInfoModSave')" id="buttonSave">
										�� ��
									</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:notEqual>
								<button onclick="window.close();return false;" id="buttonClose">
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
