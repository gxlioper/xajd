<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<script language="javascript">
</script>
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="checkWinType();tsfjxs();xlcws();">
			<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��</em><a>��Ԣ���� - ��Ϣά�� - ��Դ��ά��</a>
			</p>
		</div>
		<!-- ���� end-->
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript'src='/xgxt/dwr/interface/gyglShareData.js'></script>		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<html:form action="/data_search" method="post">
							<logic:empty name="rs">
								<br />
								<br />
								<p align="center">
									<!--  �д�������-->
								</p>
							</logic:empty>
							<logic:notEmpty name="rs">
								<input type="hidden" id="doType" name="doType" value="<bean:write name="doType" scope="request"/>" />
								<input type="hidden" id="pkValue" name="pkValue" value="<bean:write name="pkValue" scope="request"/>" />
								<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
								<input type="hidden" id="disableEle" name="disableEle" value="" />
								<input type="hidden" id="readonlyEle" name="readonlyEle" value="ssbh" />
								<input type="hidden" id="userOnLine" name="userOnLine" value="<bean:write name="userOnLine" scope="session"/>" />
								<input type="hidden" id="url" name="url" value="/sjcz/ssxxb.jsp" />	
								<!-- ��Ŀ������� -->
								<div class="tab">		
								<table class="formlist" border="0" align="center" style="width: 100%">
									<thead>
										<tr>
											<th colspan="4">
												<span>��Դ��ά��</span>
											</th>
										</tr>
									</thead>
									<tbody>		
										<tr>
											<th align="right">
												<font color="red">*</font>¥������
											</th>
											<td align="left">
<%--											<logic:present name="showdzdx">--%>
											<html:select name="rs" property="lddm" 
													styleId="lddm" onchange="getLcList2();if(document.forms[0].qsh.value!=''&&document.forms[0].lddm.value!='') document.forms[0].ssbh.value = document.forms[0].lddm.value+'-'+document.forms[0].qsh.value">
													<html:option value="">--��ѡ��--</html:option>
													<html:options collection="ldList" property="lddm"
														labelProperty="ldmc" />
												</html:select>
<%--											</logic:present>--%>
<%--											<logic:notPresent name="showdzdx">--%>
<%--											<html:select name="rs" property="lddm" style="width:150px"--%>
<%--													styleId="lddm" onchange="if(document.forms[0].qsh.value!=''&&document.forms[0].lddm.value!='') document.forms[0].ssbh.value = document.forms[0].lddm.value+'-'+document.forms[0].qsh.value">--%>
<%--													<html:option value=""></html:option>--%>
<%--													<html:options collection="ldList" property="lddm"--%>
<%--														labelProperty="ldmc" />--%>
<%--												</html:select>--%>
<%--											</logic:notPresent>--%>
												
												
											</td>
											<th align="right">
												<font color="red">*</font>���ұ��
											</th>
											<td align="left">
												<html:text name='rs' property="ssbh" style="width: 120px"
													styleId="ssbh" readonly="true"/>
											</td>
										</tr>
										<tr>							
											<th align="right">
												<font color="red">*</font>���Һ�
											</th>
											<td align="left">
												<html:text name='rs' property="qsh" style="width: 120px"
													styleId="qsh" maxlength="10"
													onchange="if(document.forms[0].qsh.value!=''&&document.forms[0].lddm.value!='') document.forms[0].ssbh.value = document.forms[0].lddm.value+'-'+document.forms[0].qsh.value" />
											</td>
											<th align="right">
												���ҵ绰
											</th>
											<td align="left">
												<html:text name='rs' property="qsdh" 
													style="width: 120px;ime-mode:disabled"
													onkeydown="return onlyNum(this,15)"
													onmousedown="return onlyNum(this,15)"
													styleId="qsdh" />
											</td>
										</tr>
										<tr>
											<th align="right">
												<font color="red">*</font>��λ��
											</th>
											<td align="left">
												<html:text name='rs' property="cws"
													styleId="cws" 
													onkeydown="return onlyNum(this,2)"
													onmousedown="return onlyNum(this,2)"
													style="width: 120px;ime-mode:disabled"/>
											</td>
											<!-- ��ɳ����ְҵ����<bean:message key="lable.xsgzyxpzxy" /> ���ط����� -->
											<logic:notEqual value="10827" name="xxdm">
												<th align="right">
													<font color="red">*</font>������													
												</th>
												<td align="left">
<%--												<logic:present name="showdzdx">--%>
													<html:select name="rs" property="fpbj" style="width:150px" onchange="tsfjxs()"
														styleId="fpbj">
														<html:option value=""></html:option>
														<html:options collection="fpbjList" property="en" labelProperty="cn"/>
													</html:select>
<%--												</logic:present>--%>
<%--												<logic:notPresent name="showdzdx">														--%>
<%--													<html:select name="rs" property="fpbj" style="width:150px"--%>
<%--														styleId="fpbj" onchange="tsfjxs()">--%>
<%--														<html:option value=""></html:option>--%>
<%--															<html:option value="һ��">һ��(���ơ�ר��)</html:option>															--%>
<%--															<html:option value="����">����</html:option>--%>
<%--														</html:select>													--%>
<%--												</logic:notPresent>--%>
												</td>
											</logic:notEqual>
											<logic:equal value="10827" name="xxdm">
												<td align="right">
												</td>
												<td align="right">
												</td>
												<input type="hidden" name="fpbj" id="fpbj" value="һ��">
											</logic:equal>
										</tr>
										<logic:equal name="xxdm" value="11417">
										<tr>
											<th align="right">
												�ɷ��䴲λ
											</th>
											<td align="left">
												<html:text name='rs' property="kfpcws" style="width: 120px"
														styleId="kfpcws" onblur="getXls()" onkeyup="value=value.replace(/[^\d]/g,'') "/>
											</td>
											<th align="right">
												���λ��
											</th>
											<td align="left">
												<html:text name='rs' property="xlcws" style="width: 120px"
														styleId="xlcws" readonly="true"/>
											</td>
										</tr>
										</logic:equal>
										<logic:present name="showhzy">
											<tr>
												<th align="right">
													������
												</th>
												<td align="left">
													<html:text name='rs' property="shq" style="width: 120px"
														styleId="shq" />
												</td>
												<td align="right">
<%--													����--%>
												</td>
												<td align="left">
<%--													<html:text name='rs' property="cs" style="width: 120px"--%>
<%--														styleId="c" onkeypress="return numJc(this,3)"/>--%>
												</td>
											</tr>
											<tr>
												<th align="right">
													���ŵ绰
												</th>
												<td align="left">
													<html:text name='rs' property="dxdh" style="width: 120px"
														styleId="dx" />
												</td>
												<th align="right">
													��ͨ�绰
												</th>
												<td align="left">
													<html:text name='rs' property="ttdh" style="width: 120px"
														styleId="tt" />
												</td>
											</tr>
											<tr>
												<th align="right">
													���߶˿�
												</th>
												<td align="left">
													<html:text name='rs' property="wxport" style="width: 120px"
														styleId="port" />
												</td>
												<td align="right">
												</td>
												<td align="left">
												</td>
											</tr>
										</logic:present>										
											<tr style="">
												<th align="right">
													<font color="red">*</font>���ڲ���
												</th>
												<td align="left">
													<html:select name="rs" property="cs" styleId="cs" styleClass="select" style="width:90px">														
														<html:options collection="csList" property="dm" labelProperty="mc"/>
													</html:select>
												</td>
												
												<th align="right">
													�շѱ�׼
												</th>
												<td align="left">
													<html:text name='rs'  
													style="width: 120px;ime-mode:disabled"
													onkeydown="return onlyNum(this,7)"
													onmousedown="return onlyNum(this,7)"
													property="sfbz" style="width: 120px;ime-mode:disabled"
													styleId="sfbz" />
												</td>
												
											</tr>
										<tr id="tsfjid" style="display: none">
											<th align="right">
												���ⷿ��˵��
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
										<tr>
											<th align="right">
												��ע<br>
												<font color="blue">��150���ַ�</font>
											</th>
											<td colspan="3">
												<html:textarea name='rs' property='bz' style="width:400px"
													onblur="chLeng(this,150)"
													rows='4' />
											</td>
										</tr>
										</tbody>
									</table>
									<table border="0" class="formlist" align="center" style="width: 100%">
										<tfoot>
											<tr>
												<td colspan="4">
													<div class="bz">"<span class="red">*</span>"Ϊ�����
													<font color="blue">���ұ����¥��+���Һ��Զ�����</font>
													</div>
													<div class="btn">	
														<logic:notEqual name="doType" value="view">
															<button type="button" class="button2"
																onclick="isSsbhExists()"
																style="width:80px" id="buttonSave">
																�� ��
															</button>	
														</logic:notEqual>
														&nbsp;&nbsp;&nbsp;&nbsp;
														<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
															id="buttonClose">
															�� ��
														</button>
													</div>
												</td>
											</tr>
										</tfoot>
							</logic:notEmpty>
			<logic:equal value="true" name="isSuccess">
			  <script type="text/javascript">
			    alert('�����ɹ���');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="isSuccess">
			  <script type="text/javascript">
			    alert('����ʧ�ܣ�');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>		
		</html:form>
	</body>
	<script type="text/javascript">
	   function tsfjxs(){	       
	       var fpbzV = $("fpbj").value;
	       if(fpbzV=="����"){
	           $("tsfjid").style.display="";
	       }else{
	           $("tsfjid").style.display="none";
	       }
	   }
	   
	function getXls(){
	   	var cws = $("cws").value;
	   	var kfpcws = $("kfpcws").value;
	   	if( cws != "" && kfpcws != ""){
	   		if(cws<(parseInt(kfpcws)+1)){
	   			alert("�ɷ��䴲λ�������λ֮�ͣ����ܴ��ڴ�λ��������ȷ��");
	   			$("kfpcws").focus();
	   		}else{
			   	var xlcws = parseInt(cws) - parseInt(kfpcws);
			   	$("xlcws").value = xlcws;
		   	}
	   	}else if(kfpcws == ""){
	   		$("xlcws").value = "0";
	   	}
	}
	
	function xlcws(){
	   if($("xxdm").value=="11417"){	    
		   var xlcws =$("xlcws").value;
		   if(xlcws == ""){
			  $("xlcws").value = 0;
		   }
		}
	}
	</script>
</html>
