<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>	
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
	</head>
	<body onload="checkWinType();">		
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ������������ - ��������</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/data_search" method="post">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					<!--�д�������-->
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="url" name="url" value="/sjcz/wmqspbb.jsp" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle" value="" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="xxdm" name="xxdm"
					value="<bean:write name="xxdm" scope="request"/>" />
			    <input type="hidden" id="V_lddm" name="V_lddm" value="" />	
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<td colspan="4" align="center">
								<span><bean:write name="clin"/></span>
							</td>
						</tr>
					</thead>
					<tbody>		
					<tr>
						<th align="right">
							<font color="red">*</font>ѧ�꣺
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="xn" value="${rs.xn}" readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xn" style="width:90px" styleId="xn" >
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</logic:equal>
						</td>
						<th align="right">
							<font color="red">*</font>ѧ�ڣ�
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:hidden name="rs" property="xq" value="${rs.xq}"/>
								<input type="text" value="${rs.xqmc}" readonly="true"/>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xq" style="width:90px" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</logic:equal>
						</td>					
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>¥�����ƣ�
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<input type="text" value="${rs.ldmc}" readonly="true"/>
								<html:hidden name="rs" property="lddm"  value="${rs.lddm}"></html:hidden>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="lddm" style="width:150px" styleId="lddm"  onchange="GetQshList()">								
									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
								</html:select>
							</logic:equal>
						</td>
						<th align="right">
							<font color="red">*</font>���Һţ�
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="qsh" value="${rs.qsh}" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<input type="hidden" name="qshV" value=""/>
								<html:select property="qsh" style="width:110px" onchange="showXsxx()">
									<html:option value=""></html:option>
									<html:options collection="ssList" property="qsh"
										labelProperty="qsh" />
								</html:select>
							</logic:equal>						
						</td>					
					</tr>
					<tr>
						<th align="right">
							<font color="red">*</font>���ڣ�									
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="pysj" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="pysj" readonly="true" onblur="dateFormatChg(this)"
									onclick="return showCalendar('pysj','y-mm-dd');" style="cursor:hand " />
							</logic:equal>			
						</td>
							
						<th align="right">
						<logic:present name="showhzy">
								��������				
						</logic:present>							
						</th>
						<td align="left">
						<logic:present name="showhzy">
							<html:text name="rs" property="shq" readonly="true"></html:text>	
						</logic:present>				
						</td>
								
					</tr>
<%--					<logic:present name="showlb">--%>
						<tr>
							<th align="right">							
								�������
								<logic:equal value="11641" name="xxdm"><!--������<bean:message key="lable.xsgzyxpzxy" />  -->
								<br>(���ȵȼ�)
								</logic:equal>								
							</th>
							<td align="left">
							<html:select name="rs" property="qslb" style="width:110px">									
									<html:options collection="qslbList" property="lbdm"
										labelProperty="lbmc" />
							</html:select>
							</td>
							<th align="right">							
							</th>
							<td>
							</td>
						</tr>
<%--					</logic:present>--%>
					<logic:present name="showzgdzdx">
					<tr>
						<th align="right">
							ѧϰ��Χ��		
							<br>
							<span style="color: red">
							<��300��></span>								
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" cols="60" name="xxfw" id="xxfw" type="_moz" onblur="chLeng(this,300)">${rs.xxfw}</textarea>			
						</td>					
					</tr>
					</logic:present>
					<tr id="rsxsxx" style="display:none">
						<td colspan="4">
						    <fieldset>
									<legend>
									    <span id="ldqs"></span>���ҵ�ǰ��ס��������<span id="rsNum"></span>��
									</legend>
							<table width="100%" class="tbstyle">							
								<tbody id="rsTables">
								  
								</tbody>								
							</table>
							</fieldset>
						</td>
					</tr>					
					<tr>
						<th align="right">
							��ע��
							<br>
							<span style="color: red">
							<��100��></span>											
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" cols="60" name="bz" id="bz" type="_moz" onblur="chLeng(this,100)">${rs.bz}</textarea>			
						</td>					
					</tr>
					</tbody>
					<tfoot>
						<tr bgcolor="EEF4F9" align="center">
							<td colspan="4">
								<div class="btn">
										<button type="button" id="buttonSave" 
											onclick="wmqssave()"
											style="width: 80px">
											��	��
										</button>
									&nbsp;&nbsp;
									<button type="button" id="buttonClose" onclick="Close();return false;"
										style="width: 80px">
										��	��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</logic:notEmpty>
			<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('�����ɹ���');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('����ʧ�ܣ�');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>					
		</html:form>
		<script type="text/javascript">
		      function wmqssave(){
		           if(IsNoEmpty('xn-xq-qsh-pysj')){                      
                    var xn = $("xn").value;
                    var xq = $("xq").value;
                    var lddm = $("lddm").value;
                    var qsh = $("qsh").value;
                    pkV = lddm+qsh+xn+xq;
                    var doType = $("doType").value;
                      getSztzData.getInfoEx("view_wmqspbxx","lddm||qsh||xn||xq",pkV,"1=1",function(str){
		               if(str){		         	
		                  if(confirm("��ѧ�ꡢѧ�ڡ������ҵ������Ϣ�Ѵ��ڣ�\n\nȷ��Ҫ���棿\n\n���\"ȷ��\"���������ݲ������Ѵ�����Ϣ��\n���\"ȡ��\"�����������ģ�")){
		                    dataDoSave('xn-xq-qsh-pysj');
                            $("buttonSave").disabled=true;
		                  }else{
		                     return false;
		                  }	          			              
		               }else{
		                  if(confirm("ȷ��Ҫ���棿\n\n���\"ȷ��\"��������Ϣ��\n���\"ȡ��\"�����������棡")){
		                    dataDoSave('xn-xq-qsh-pysj');
                            $("buttonSave").disabled=true;		                  
		                  }else{
		                     return false;
		                  }	
 		               }
                      }); 
                 } 
             }  	
       function showXsxx(){
         if($("qsh").value!=""){
	      document.getElementById("rsxsxx").style.display="";
	      getRzXsXx();
	      }else{
	      document.getElementById("rsxsxx").style.display="none";
	     }
      }     	
		</script>
  </body>
</html>
