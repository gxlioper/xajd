<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript"  src="js/AjaxFunction.js"></script>
	<script language="javascript"  src="js/gygl/gyglFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
	<script language="javascript">
function wsjcSave(mustFill){
	var zs = document.getElementById("zs").value;
	var eles = mustFill.split("-");
	for (i = 0; i < eles.length; i++) {
		if (document.getElementById(eles[i]).value == "") {
			alert("�뽫��\"*\"�ŵ���Ŀ����������");
			return false;
		}
	}
	var url = "/xgxt/modiData.do?realTable=";
	url += window.dialogArguments.document.forms[0].realTable.value;
	var tmpTable = window.dialogArguments.document.forms[0].realTable.value;
	url += "&doType=save";
	url += "&tableName=";
	url += window.dialogArguments.document.forms[0].tableName.value;
	url += "&pk=";
	url += window.dialogArguments.document.forms[0].pk.value;
	url += "&pkValue=";
	url += document.forms[0].pkValue.value;
	url += "&from=";
	url += window.dialogArguments.document.forms[0].act.value;
	url += "&zs="+zs;
	document.forms[0].action = url;
	document.forms[0].submit();
	if (tmpTable=='wjcfb') {
		if (var_w<2) {
			setInterval("waitTime()",2200);
		}
		return;
	} else {
		alert("����ɹ���");
		Close();
		dialogArgumentsQueryChick();
	}

}
function toSave(){
    	  var wxnr = "";
	      var bz   = "";
	      
	      if($("bz")){
	         bz = $("bz").value;
	      }	  
	      
	      if(bz.length>100){
	          alert("��ע����������100���ڣ�");
	          return false;
	      } 
    dataDoSave('xn-xq-qsh-jcsj-jcbm');
}

function getWsdjFs(){
	var tableName = "zgdd_wsjcdj";
	var dm = "fs";
	var pk = "mc";
	var pkValue = $("dj").value;
	getGyglDAO.getOneValue(tableName,dm,pk,pkValue,function (data){
		$("fs").value = data;
	});
}
	</script>
	</head>
	
	<body onload="loadScore()">		
		<html:form action="/data_search" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ԣ���� - ��Ϣά�� - �������</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					<!--�д�������-->
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="url" name="url" value="/sjcz/gywsjcb.jsp" />
				<input type="hidden" id="doType" name="doType"
					value="${doType }" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="${pkValue }" />
				<input type="hidden" id="disableEle" name="disableEle" value="" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="userType" name="userType"
					value="${userType }" />
				<input type="hidden" id="xxdm" name="xxdm"
					value="${xxdm }" />
			    <input type="hidden" id="V_lddm" name="V_lddm" value="" />
					<div class="tab">
						<table width="100%"  border="0" class="formlist">
						 <thead>
		    				<tr>
		        				<th colspan="6"><span>${clin }</span></th>
		        			</tr>
		   				 </thead>
		   				 <tbody>
						<tr>
						<th>
							<font color="red">*</font>ѧ��
						</th>
						<td>
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="xn" value="${rs.xn}" readonly="true"></html:text>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="xn" style="width:90px" styleId="xn" >
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</logic:equal>
						</td>
						<th>
							<font color="red">*</font>ѧ��
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
						<th>
							<font color="red">*</font>¥������
						</th>
						<td>
							<logic:notEqual value="add" name="doType">
								<input type="text" value="${rs.ldmc}" name="ldmc" readonly="true"/>
								<html:hidden name="rs" property="lddm" value="${rs.lddm}"></html:hidden>
							</logic:notEqual>
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="lddm" style="width:150px" styleId="lddm" onchange="GetQshList()">									
									<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
								</html:select>
							</logic:equal>
						</td>
						<th>
							<font color="red">*</font>���Һ�
						</th>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="qsh" value="${rs.qsh}" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<input type="hidden" name="qshV" value=""/>
								<html:select property="qsh" styleId="qsh" style="width:110px" onchange="showXsxx()">
									<html:option value=""></html:option>
									<html:options collection="ssList" property="qsh"
										labelProperty="qsh" />
								</html:select>
							</logic:equal>
						</td>					
					</tr>
					<logic:notEqual name="xxdm" value="11641">
					<logic:notEqual name="xxdm" value="10338">
					<tr>
						<th>
							<font color="red">*</font>���ʱ��								
						</th>
						<td>
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="jcsj" value="${rs.jcsj}" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="jcsj" readonly="true" onblur="dateFormatChg(this)"
									onclick="return showCalendar('jcsj','y-mm-dd');" style="cursor:hand " />
							</logic:equal>			
						</td>
						<th>
							<font color="red">*</font>��鲿��					
						</th>
						<td align="left">
						<logic:present name="noshowbm">
						<html:text name="rs" property="jcbm" maxlength="10"></html:text>
						</logic:present>
						<logic:notPresent name="noshowbm">
							<logic:notEqual value="add" name="doType">
								<input type="text" value="${rs.bmmc}" readonly="true"/>
								<html:hidden name="rs" property="jcbm" value="${rs.jcbm}"></html:hidden>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="jcbm" style="width:150px" styleId="jcbm">
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
								</html:select>
							</logic:equal>	
						</logic:notPresent>													
						</td>					
					</tr>
					<tr>
						<th>
							����						
						</th>
						<td align="left">
						    <html:text name="rs" property="fs" styleId="fs" onkeypress="return numInputValue(this,8,event)"></html:text>
						</td>
						<th>
							�ȼ�		
						</th>
						<td>
							<!-- ���й����ʴ�ѧ -->
							<logic:notEqual name="xxdm" value="10491">			
							<logic:equal value="1049701" name="xxdm">
								<html:select name="rs" property="dj" style="width:150px" styleId="dj" onchange="GetFs()">
									<html:option value=""></html:option>
									<html:options collection="djList" property="nwwsdjdm" labelProperty="nwwsdjmc" />
								</html:select>
								</logic:equal>
							<logic:notEqual value="1049701" name="xxdm">
								<html:select name="rs" property="dj" style="width:150px" styleId="dj" onchange="autoScore()">
									<html:options collection="djList" property="en" labelProperty="cn" />
								</html:select>
							</logic:notEqual>
							</logic:notEqual>
							<!-- ���й����ʴ�ѧ end-->
							<!-- �й����ʴ�ѧ -->
							<logic:equal name="xxdm" value="10491">		
								<html:select name="rs" property="dj" style="width:150px" styleId="dj" onchange="getWsdjFs()">
									<html:option value=""></html:option>
									<html:options collection="djList" property="mc" labelProperty="mc" />
								</html:select>
							</logic:equal>
							<!-- �й����ʴ�ѧ end-->
						</td>					
					</tr>
					</logic:notEqual>
					</logic:notEqual>
					<logic:equal name="xxdm" value="10338">
					<tr>
						<th>
							<font color="red">*</font>���ʱ��							
						</th>
						<td align="left">
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="jcsj" readonly="true" onblur="dateFormatChg(this);getZs(this);"
								onclick="return showCalendar('jcsj','y-mm-dd');" style="cursor:hand " />
							</logic:equal>
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="jcsj" readonly="true" />
							</logic:notEqual>	
						</td>
						<th>
							�����ʱ						
						</th>
						<td>
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="zs" value="${rs.zs}" readonly="true" styleId="zs"></html:text>
							</logic:equal>
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="zs" value="��${rs.zs}��" readonly="true" styleId="zs"></html:text>
							</logic:notEqual>									
						</td>					
					</tr>
					<tr>
						<th>
							����					
						</th>
						<td align="left">
						    <html:text name="rs" property="fs" styleId="fs" onkeypress="return numInputValue(this,8,event)"></html:text>
						</td>
						<th>		
						</th>
						<td align="left">
						</td>					
					</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="11641">
					<tr>
						<th>
							<font color="red">*</font>�����ʱ									
						</th>
						<td align="left" colspan="3">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="zs" value="��${rs.zs}��" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="zs" style="width:150px" styleId="zs">
									<html:options collection="zsList" property="dm" labelProperty="mc" />
								</html:select>
							</logic:equal>			
						</td>
						<!-- <td align="right">
							<font color="red">*</font>���ʱ�䣺									
						</td>
						<td align="left">
							<logic:notEqual value="add" name="doType">
								<html:text name="rs" property="jcsj" value="${rs.jcsj}" readonly="true"></html:text>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:text name="rs" property="jcsj" readonly="true" onblur="dateFormatChg(this)"
									onclick="return showCalendar('jcsj','y-mm-dd');" style="cursor:hand " />
							</logic:equal>			
						</td> -->
					</tr>
					<tr>
						<th>
							�ɼ�		
						</th>
						<td>
							<html:select name="rs" property="fs" style="width:150px" styleId="fs">
								<html:option value=""></html:option>
								<html:options collection="wsjcdjList" property="cj" labelProperty="dj" />
							</html:select>
							 <html:text name="rs" property="fs" styleId="fs" onkeypress="return numInputValue(this,8,event)"
							 style="display:none"></html:text>
						</td>	
						<th>
							<font color="red">*</font>��鲿��					
						</th>
						<td align="left">
						<logic:present name="noshowbm">
						<html:text name="rs" property="jcbm" ></html:text>
						</logic:present>
						<logic:notPresent name="noshowbm">
							<logic:notEqual value="add" name="doType">
								<input type="text" value="${rs.bmmc}" readonly="true"/>
								<html:hidden name="rs" property="jcbm" value="${rs.jcbm}"></html:hidden>
							</logic:notEqual>							
							<logic:equal value="add" name="doType">
								<html:select name="rs" property="jcbm" style="width:150px" styleId="jcbm">
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
								</html:select>
							</logic:equal>	
						</logic:notPresent>													
						</td>							
					</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="1049701">
						<tr>
							<th>
								�����									
							</th>
							<td align="left">
							   <html:select name="rs" property="zs" style="width:150px" styleId="zs">
									<html:options collection="zsList" property="en" labelProperty="cn" />
								</html:select>		
							</td>
							<th>		
							</th>
							<td align="left">
							</td>					
						</tr>
					</logic:equal>
	
					<logic:present name="showhzy">
						<tr>
							<th>
								������							
							</th>
							<td>
								<html:text name="rs" property="shq" readonly="true"></html:text>		
							</td>
							<th>		
							</th>
							<td align="left">
							</td>					
						</tr>
					</logic:present>
					<tr id="rsxsxx" style="display:none">
						<td colspan="4">
									<legend>
									    <span id="ldqs"></span>���ҵ�ǰ��ס��������<span id="rsNum"></span>��
									</legend>
							<table width="100%" class="tbstyle">							
								<tbody id="rsTables">
								  
								</tbody>								
							</table>
						</td>
					</tr>
					<tr>
						<th>
							��ע
							<br>
							<span style="color: red">
							<��100��>	</span>									
						</th>
						<td align="left" colspan="3">
							<textarea rows="5" cols="60" name="bz" id="bz" type="_moz" 
							     style="word-break:break-all;width:99%"	onblur="chLeng(this,100)">${rs.bz}</textarea>			
						</td>					
					</tr>
					</tbody>
					<tfoot>
						 <tfoot>
						      <tr>
						        <td colspan="6"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
						          <div class="btn">
						          	<button type="button" class="button2" onclick="dataCanModi(true)" style="width:80px" id="buttonModi">
										�� ��
									</button>
									<logic:notEqual name="xxdm" value="11641">
									<button type="button" class="button2" onclick="toSave()" style="width:80px" id="buttonSave">
										�� ��
									</button>
									</logic:notEqual>
									<logic:equal name="xxdm" value="11641">
									<button type="button" class="button2" onclick="wsjcSave('xn-xq-qsh-jcbm');" style="width:80px" id="buttonSave">
										�� ��
									</button>
									</logic:equal>
									<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
										�� ��
									</button>						           
						          </div>
						          </td>
						      </tr>
						    </tfoot>
					</tfoot>
				</table>
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
<script type='text/javascript'>
function loadScore(){
  if($('doType').value=='add'){
   if($("xxdm").value!="1049701" && $("xxdm").value!="10338"){//�人������<bean:message key="lable.xsgzyxpzxy" />,�㽭��
   document.forms[0].fs.value='90';
   }
  }
if($('doType').value != 'add'){
document.getElementById('rsxsxx').style.display=''; 
getRzXsXx();
}
}
function autoScore(){
 if($("xxdm").value!="1049701"){//�人������<bean:message key="lable.xsgzyxpzxy" />
   if($("xxdm").value=="11647"){
      if(document.forms[0].dj.value=='����'){
          document.forms[0].fs.value='90';
      }else if(document.forms[0].dj.value=='����'){
          document.forms[0].fs.value='80';   
      }else if(document.forms[0].dj.value=='�ϸ�'){
          document.forms[0].fs.value='70';
      }else if(document.forms[0].dj.value=='���ϸ�'){
          document.forms[0].fs.value='60';
      }     
   } 
   if($("xxdm").value=="10338"){
   
   }
   if($("xxdm").value=="10863"){
      if(document.forms[0].dj.value=='A��'){
          document.forms[0].fs.value='90';
      }else if(document.forms[0].dj.value=='B��'){
          document.forms[0].fs.value='80';   
      }else if(document.forms[0].dj.value=='C��'){
          document.forms[0].fs.value='60';
      }
   }else{  
      if(document.forms[0].dj.value=='����'){
          document.forms[0].fs.value='90';
      }else if(document.forms[0].dj.value=='����'){
          document.forms[0].fs.value='80';
      }else if(document.forms[0].dj.value=='�е�'){
          document.forms[0].fs.value='70';
      }else if(document.forms[0].dj.value=='����'){
          document.forms[0].fs.value='60';
      }else if(document.forms[0].dj.value=='������'){
          document.forms[0].fs.value='50';
      }
   }
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
</html>