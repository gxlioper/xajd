<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<center>
			<html:form action="/gygl_ParaSet" method="post">
			<input type="hidden" id="msg" name="msg" value="${msg}"/>
			    <div class="tab_cur">
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>��Ԣ���� - �������� - �����趨</a>
					</p>
				</div>
			    <div class="tab">
				  <table width="100%"  border="0" class="formlist">
				    <thead>
				    	<tr>
				        	<th colspan="4"><span>��������</span></th>
				        </tr>
				    </thead>
				     <tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				          		<button name="����"onclick="paraSet();" id="buttonSave">�� ��</button>
				          </div></td>
				      </tr>
				    </tfoot>
				    
				    <tbody>
						<logic:notPresent name="SSFPMSSZ">
							<tr>
								<td>
									<table width="99%" align="center">
										<thead>										   
											<tr align="center">
												<td height="25" colspan="2">
												 <input type="hidden" name="wmqsbl" id="wmqsbl" value="wmqsbl" />
													�������ұ�������
												</td>
											</tr>											
										</thead>
										<tr>
											<td width="45%" height="25" align="right">
												<font color="red">*</font>ѧ�꣺
											</td>
											<td height="25" align="left">
												<html:select property="xn"
													onchange="refreshForm('/xgxt/gygl_ParaSet.do')">
													<html:options collection="xnList" property="xn"
														labelProperty="xn" />
												</html:select>
											</td>
										</tr>
										<tr>
											<td width="45%" height="25" align="right">
												<font color="red">*</font>ѧ�ڣ�
											</td>
											<td height="25" align="left">
												<html:select property="xq"
													onchange="refreshForm('/xgxt/gygl_ParaSet.do')">
													<option value="">
													</option>
													<html:options collection="xqList" property="xqdm"
														labelProperty="xqmc" />
												</html:select>
											</td>
										</tr>
										<tr>
											<td width="45%" height="25" align="right">
												<font color="red">*</font>�������ұ������ã�
											</td>
											<td height="25" align="left">
												<html:text property="wmqsbz" style="width:50px"
													onkeypress="return sztzNumInputValue(this,4,event)"
													onblur="chkInput(this,event)"></html:text>
												%
												<span style="font-size: 11px;color: red">����ÿѧ�ꡢ��,����������ռ���������ı���</span>
											</td>
										</tr>
										<!--��������ְҵ����<bean:message key="lable.xsgzyxpzxy" />-->
										<logic:equal value="12645" name="xxdm">
											<tr>
											<td width="45%" height="25" align="right">
												<font color="red">*</font>��������Ҫ��
											</td>
											<td height="25" align="left">
												<html:text property="wsjc" style="width:50px"
													onkeypress="return sztzNumInputValue(this,4,event)"
													onblur="chkInput(this,event)"></html:text>
												<span style="font-size: 11px;color: red">&nbsp;&nbsp;&nbsp;&nbsp;������ѡ�������ҵ������������Ҫ��</span>
											</td>
										</tr>
										</logic:equal>
										<!--��������ְҵ����<bean:message key="lable.xsgzyxpzxy" />-->

										<logic:present name="isHHGXY">
											<tr>
												<td width="45%" height="25" align="right">
													<font color="red">*</font>�������ڣ�
												</td>
												<td height="25" align="left">
													<html:select property="pbzq" name="rs">
														<option value=""></option>
														<html:options collection="pbzqList" property="pbzqdm" labelProperty="pbzqmc" />
													</html:select>
												</td>
											</tr>
											<tr align="center">
												<td height="25" colspan="2">
													<font color="red">*</font>�������ȼ�Ҫ��
													<input type="hidden" id="wsjcdjs" name="wsjcdjs" value="<bean:write name="wsjcdjs"/>"/>
												</td>
											</tr>		
											<tr>
												<td height="25" align="center" colspan="2">
												<logic:iterate name="rsList" id="s">
													<logic:iterate id="b" name="s" property="wsjcbfbList" indexId="index">
													<bean:write name="b" property="wsjcdj"/>
													<input type="hidden" id="wsjcdj${index}" name="wsjcdj${index}" value="<bean:write name="b" property="wsjcdj"/>"/>
													<input type="text" id="wsjc${index}" name="wsjc${index}" style="width: 30px"
														value="<bean:write name="b" property="wsjcdjbfb"/>"
														onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="2"/>%
														&nbsp;&nbsp;
													</logic:iterate>
												</logic:iterate>
												</td>
											</tr>
											<tr align="center">
												<td height="25" colspan="2">
													<font color="red">*</font>ס�޼��ɵȼ�Ҫ��
													<input type="hidden" id="cfdjs" name="cfdjs" value="<bean:write name="cfdjs"/>"/>
												</td>
											</tr>
											<tr>
												<td height="25" align="center" colspan="2">
												<logic:iterate name="rsList" id="s">
													<logic:iterate id="b" name="s" property="cfdjsList" indexId="index">
													<bean:write name="b" property="cfdjmc"/>
													<input type="hidden" id="cfdjmc${index}" name="cfdjmc${index}" value="<bean:write name="b" property="cfdjmc"/>"/>
													<input type="text" id="cfdj${index}" name="cfdj${index}" style="width: 30px"
													value="<bean:write name="b" property="cfdjcs"/>"
													onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="2"/>
														&nbsp;&nbsp;
													</logic:iterate>
												</logic:iterate>
												</td>
											</tr>
										</logic:present>
									</table>
								</td>
							</tr>	
							<logic:present name="isNBLGXY">
						    <tr>
								<td>
									<table width="99%" align="center">
										<thead>
											<tr align="center">
												<td height="25" colspan="2">
											   <input type="hidden" name="sjsz" id="sjsz" value="sjsz" />
													��Ԣ����Ա�±���дʱ������
												</td>
											</tr>
										</thead>
										<tr>
											<td width="45%" height="25" align="right">
												��ʼʱ�䣺
											</td>
											<td height="25" align="left">
												<input type="hidden" name="kssqsj" id="kssqsj" value="" />
												<input type="text" readonly style="cursor:hand;width:80px"
													onclick="return showCalendar('kssqsj1','y-mm-dd');"
													value="<bean:write name="kssj1" />" name="kssqsj1"
													id="kssqsj1" />
												��
												<input type="text" onkeypress="return numInputValue(this,2,event)"
													style="width:40px" value="<bean:write name="kssj2" />"
													name="kssqsj2" id="kssqsj2" />
												��
												<input type="text" onkeypress="return numInputValue(this,2,event)"
													style="width:40px" value="<bean:write name="kssj3" />"
													name="kssqsj3" id="kssqsj3" />
												��
												<input type="text" onkeypress="return numInputValue(this,2,event)"
													style="width:40px" value="<bean:write name="kssj4" />"
													name="kssqsj4" id="kssqsj4" />
											</td>
										</tr>
										<tr>
											<td width="45%" height="25" align="right">
												��ֹʱ�䣺
											</td>
											<td height="25" align="left">
												<input type="hidden" name="jssqsj" id="jssqsj" value="" />
												<input type="text" readonly style="cursor:hand;width:80px"
													onclick="return showCalendar('jssqsj1','y-mm-dd');"
													value="<bean:write name="jssj1" />" name="jssqsj1"
													id="jssqsj1" />
												��
												<input type="text" onkeypress="return numInputValue(this,2,event)"
													style="width:40px" value="<bean:write name="jssj2" />"
													name="jssqsj2" id="jssqsj2" />
												��
												<input type="text" onkeypress="return numInputValue(this,2,event)"
													style="width:40px" value="<bean:write name="jssj3" />"
													name="jssqsj3" id="jssqsj3" />
												��
												<input type="text" onkeypress="return numInputValue(this,2,event)"
													style="width:40px" value="<bean:write name="jssj4" />"
													name="jssqsj4" id="jssqsj4" />												
											</td>
										</tr>
									</table>
								</td>
							</tr>
							</logic:present>									
						</logic:notPresent>
						<logic:present name="SSFPMSSZ">
							<tr>
								<td>

									<table width="99%" align="center">
										<thead>
											<tr align="center">
												<td height="25" colspan="2">
													ס�޷���ģʽ
												</td>
											</tr>
										</thead>
										<tr>
											<td width="45%" height="25" align="right">
												<font color="red">*</font>��������ס�޷��估ά��ģʽ��
											</td>
											<td height="25" align="left">
												<input type="radio" name="sfbd" value="1"
													<logic:equal name="sfbd" value="1"> checked </logic:equal>>
												��
												<input type="radio" name="sfbd" value="0"
													<logic:equal name="sfbd" value="0"> checked </logic:equal>>
												��
												<span style="font-size: 11px;color: red"></span>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</logic:present>
						</tbody>
					</table>
				</div>
				<logic:notEmpty name="done">
					<logic:equal name="done" value="true">
						<script>alert("���óɹ�!");refreshForm('/xgxt/gygl_ParaSet.do');</script>
					</logic:equal>
					<logic:equal name="done" value="false">
						<script>alert("����ʧ��!")</script>
					</logic:equal>
				</logic:notEmpty>
			</html:form>
		</center>
	</body>
	<logic:present name="msg">
		<script>
			alert(''+document.getElementById('msg').value);
		</script>
	</logic:present>
	<script type="text/javascript">
	    function paraSet(){
<%--	        if($('sfbd')){//����ģʽ--%>
<%--	           return true;	           	           --%>
<%--	        } --%>
           
	        if($('wmqsbl')){	
	           if($("xn").value==""){
	              alert("�������ұ������ã�ѧ�겻�ܿգ�");
	              return false;
	           }
	           if($("xq").value==""){
	              alert("�������ұ������ã�ѧ�ڲ��ܿգ�");
	              return false;
	           } 
	           if($("wmqsbz").value==""){
	              alert("�������ұ������ò���Ϊ�գ�");
	              return false;
	           }
	           if($("wsjc") && $("wsjc").value == ""){
	               alert("��������Ҫ����Ϊ�գ�");
	               return false;
	           }          	           	          	          	             
	        } 
	        if($("sjsz")){	         
	           var kssqsj1=$("kssqsj1").value;
	           var kssqsj2=$("kssqsj2").value; 
	           var kssqsj3=$("kssqsj3").value;
	           var kssqsj4=$("kssqsj4").value; 
	           var jssqsj1=$("jssqsj1").value; 
	           var jssqsj2=$("jssqsj2").value;
	           var jssqsj3=$("jssqsj3").value; 
	           var jssqsj4=$("jssqsj4").value; 
	           if(kssqsj2>24
	              ||kssqsj3>60
	              ||kssqsj4>60
	              ||jssqsj2>24
	              ||jssqsj3>60
	              ||jssqsj4>60){
	              alert("ʱ����������������������ύ��");
	              return false;
	           }           	           
	           if(!SjParaChk("kssqsj","jssqsj")){
	            return false;
	           }              
	        }	
	        var wsjcmc="";
	        var bfb="";
	        if($("wsjcdjs")){
			  if($("wsjcdjs").value != "0"){	         
				for(var i=0;i<$("wsjcdjs").value;i++){
					var wsjcdj = $("wsjcdj"+i).value;
					var wsjc = $("wsjc"+i).value;
					if(wsjc == ""){
						wsjc = "0";
					}
					bfb += wsjc + splitSignOne;
					wsjcmc += wsjcdj + splitSignTwo;
				}
			  } 
			}
			var cfmc="";
	        var cfcs="";
	        if($("cfdjs")){
			  if($("cfdjs").value != "0"){	         
				 for(var i=0;i<$("cfdjs").value;i++){
					var cfdjmc = $("cfdjmc"+i).value;
					var cfdj = $("cfdj"+i).value;
					if(cfdj == ""){
						cfdj = "0";
					}
					cfcs += cfdj + splitSignOne;
					cfmc += cfdjmc + splitSignTwo;
				}
			  } 
			}
	       refreshForm('/xgxt/gygl_ParaSet.do?doType=save&bfb='+bfb+'&wsjcmc='+wsjcmc+'&cfmc='+cfmc+'&cfcs='+cfcs);
	       $('buttonSave').disabled=true;
	    }
	</script>
</html>
