<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	</head>
	<body onload="disBZ()">	
		<html:form action="/XsgyglDispatch.do?method=ssYdSqSh" method="post">
		<input type="hidden" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
		<input type="hidden" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ԣ���� - ���� - �����춯��� - �������</a>
				</p>
			</div>
			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>�������</span></th>
			        </tr>
			    </thead>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          <logic:equal value="yes" name="writeAble">
						<button onclick="toSave()" id="buttonSave">
							�� ��
						</button>
					  </logic:equal>
						<logic:equal value="no" name="writeAble">
							<script>
					         alert("���û�ֻ�ж�Ȩ��!");
					        </script>
						</logic:equal>
						<button  onclick="Close();return false;" id="buttonClose">
							�� ��
						</button>
			          </div></td>
			      </tr>
			    </tfoot>
				<tbody>
					<tr>
						<td align="right" style="width: 100">
							ѧ�ţ�
						</td>
						<td align="left" >
						    <bean:write name="rs" property="xh"/>												
						</td>
						<td align="right" style="width: 100">
							������
						</td>
						<td align="left" style="width: 200">
						 <bean:write name="rs" property="xm"/>	
						</td>						
					</tr>					
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							 <bean:write name="rs" property="xb"/>	
						</td>
						<td align="right">
							ѧ�꣺
						</td>
						<td align="left">
							 <bean:write name="rs" property="xn"/>	
						</td>											
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							 <bean:write name="rs" property="nj"/>	
						</td>
						<td align="right">
							ѧ�ڣ�
						</td>
						<td align="left">
							 <bean:write name="rs" property="xq"/>	
						</td>				
					</tr>					
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							 <bean:write name="rs" property="xymc"/>	
						</td>
						<td align="right">
							�춯��¥�����ƣ�
						</td>
						<td align="left">
					 <bean:write name="rs" property="ldmc"/>								
						</td>				
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							 <bean:write name="rs" property="zymc"/>	
						</td>
						<td align="right">
							�춯�����Һţ�
						</td>
						<td align="left">
							 <bean:write name="rs" property="qsh"/>	
						</td>									
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							 <bean:write name="rs" property="bjmc"/>	
						</td>
						<td align="right">
							�춯��λ�ţ�
						</td>
						<td align="left">
							 <bean:write name="rs" property="ydhcwh"/>	
						</td>												
					</tr>
					<tr>
						<td align="right">
							�춯ǰ¥�����ƣ�
						</td>
						<td align="left">
							 <bean:write name="rs" property="ydqldmc"/>	
						</td>
						<td align="right">
							�����춯ʱ�䣺									
						</td>
						<td align="left">
						 <bean:write name="rs" property="sqydsj"/>	
						</td>							
					</tr>
					<tr>
						<td align="right">
							�춯ǰ���Һţ�
						</td>
						<td align="left">
							 <bean:write name="rs" property="ydqqsh"/>	
						</td>
						<td align="right">
							��ˣ� 
						</td>
						<td align="left">
							<html:select name="rs"  property="yesNo" onchange="FpWx()">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�춯ǰ��λ�ţ�
						</td>
						<td align="left">
							 <bean:write name="rs" property="ydqcwh"/>	
						</td>
						<td align="right">
							�춯ǰ��סʱ�䣺
						</td>
						<td align="left">
							 <bean:write name="rs" property="ydqrzsj"/>	
						</td>
					</tr>					
					<tr>
						<td align="right">
							�춯���ɣ�	
							<br>							
						</td>
						<td align="left" colspan="3">
							<bean:write name="rs" property="ydly"/>
						</td>					
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />���˵����<BR>
							(С��300��)
						</td>
						<td align="left" colspan="3" >							
							<logic:equal name="userType" value="xx">	
							<html:textarea name="rs"  property="xyshyy" styleId="xyshyy" rows="6" cols="76" style="width:95% " disabled="true">
							</html:textarea>
							</logic:equal>	
							<logic:equal name="userType" value="xy">	
							<html:textarea name="rs"  property="xyshyy" styleId="xyshyy" rows="6" cols="76" style="width:95% " >
							</html:textarea>
							</logic:equal>								
						</td>
					</tr>
					<tr>
						<td align="right">
							ѧУ���˵����<BR>
							(С��300��) 							                   
						</td>
						<td align="left" colspan="3">
						<logic:equal name="userType" value="xy">	
							<html:textarea name="rs" property="xxshyy" styleId="xxshyy" rows="6"							
								cols="76" style="width:95% " disabled="true"></html:textarea>
						</logic:equal>
						<logic:equal name="userType" value="xx">	
							<html:textarea name="rs" property="xxshyy" styleId="xxshyy" rows="6"							
								cols="76" style="width:95% "></html:textarea>
						</logic:equal>						
						</td>
					</tr>
											
				    <tr  id="id1" style="display:none;">
						<td align="right">
							��ע��
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="bz" rows="3" cols="60"></html:textarea>
						</td>
					</tr>	
					</tbody>
				</table>
			</logic:notEmpty>		
		</html:form>		
		<logic:equal value="yes" name="done">
			<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		   <logic:equal value="no" name="done">
			<script>
				alert("����ʧ��!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>	
  </body>
  <script type="text/javascript">
  function disBZ(){
     if($("userType").value!='xy'){ 
        if($('yesNo').value=='ͨ��'){
          $('id1').style.display='';
        }
     }
  }
  function FpWx(){
    var id1 = document.getElementById("id1");
    if($("userType").value!='xy'){
         if($("yesNo").value=="ͨ��"){
            id1.style.display="";    
         }else{
            id1.style.display="none";   
         }
    }
  }

  function toSave(){
       if($("xyshyy")){
          if($("xyshyy").value.length>300){
             alert("<bean:message key="lable.xsgzyxpzxy" />���˵����������300�֣�");
             return false;
          }
       }
       if($("xxshyy")){
           if($("xxshyy").value.length>300){
             alert("ѧУ���˵����������300�֣�");
             return false;
          }         
       }
       refreshForm('/xgxt/XsgyglDispatch.do?method=ssYdSqSh&doType=save');
       $("buttonSave").disabled='true';
    }

  </script>
</html>
