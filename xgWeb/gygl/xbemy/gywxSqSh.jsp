<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
        <script language="javascript" src="js/sztzFunction.js"></script>		
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getSztzData.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>	
	</head>
	<body onload="if($('yesNo').value=='ͨ��'){$('wxfp1').style.display='';$('wxfp2').style.display='';}">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��� - ��Ԣ(����)ά����� - �������</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/XsgyglDispatch.do?method=gywxSqSh" method="post">		

			<input type="hidden" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>�������</span>
						</th>
					</tr>
				</thead>
				<tbody>	
				<tr style="height:22px;">
					<th align="right" width="15%">
						¥�����ƣ�
					</th>
					<td align="left" width="80px">
						<bean:write name="rs" property="ldmc"/>
					</td>
					<th align="right" width="20%">
						ѧ�꣺
					</th>
					<td align="left" width="80px">
						<bean:write name="rs" property="xn"/>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						���Һţ�
					</th>
					<td align="left">
						<bean:write name="rs" property="qsh"/>
					</td>
					<th align="right">
						����ʱ�䣺
					</th>
					<td align="left">
						<bean:write name="rs" property="bxsj"/>
					</td>
					
				</tr>				
				<tr style="height:22px">
					<th align="right">
						������������
					</th>
					<td align="left">
						<bean:write name="rs" property="bxrxm"/>
					</td>
					<th align="right">
						 ��ϵ��ʽ��
					</th>
					<td align="left">
						<bean:write name="rs" property="lxfs"/>
					</td>						
				</tr>	
				<tr style="height:22px">								
					<th align="right">
						 ��ˣ�
					</th>
					<td align="left">
						<html:select name="rs"  property="yesNo" onchange="FpWx()">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>	
					<th align="right">
					
					</th>
					<td align="left">
						
					</td>		
				</tr>	
				<tr  style="height:22px">
					<th align="right">
						ά�����ݣ�
					</th>
					<td align="left"colspan="3" >
						<bean:write name="rs" property="wxnr"/>
					</td>	
									
				</tr>
				<tr>
						<th align="right">
							ѧУ���˵����<BR>
							(С��300��) 							                   
						</th>
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
				<logic:present name="isCSMZZYJSXY"><!-- ��ɳ����ְҵ����<bean:message key="lable.xsgzyxpzxy" /> -->
				<tr  style="height:22px">
					<th align="right">
						���뱸ע��
					</th>
					<td align="left"colspan="3" >
						<bean:write name="rs" property="sqbz"/>
					</td>										
				</tr>	
				</logic:present>			
				<tr id="wxfp1" style="display:none;height:22px"   >
					<th align="right">
						Ԥά��ʱ�䣺
					</th>
					<td align="left" >
                     <html:text name="rs" property="wxsj"  onblur="dateFormatChg(this);"
								onclick="return showCalendar('wxsj','y-mm-dd');" style="cursor:hand " />
					</td>
					<th align="right">
						Ԥ��ǲά����Ա��
					</th>
					<td align="left">
					   <html:select name="rs" property="rydm" style="width:150px" styleId="rydm">
								<html:option value=""></html:option>
								<html:options collection="ryList" property="rydm" labelProperty="rymc" />
						</html:select>
					</td>				
				</tr>
				<tr  id="wxfp2" style="display:none;height:22px">
					<th align="right">
						��ע��
					</th>
					<td align="left"colspan="3" >
						<html:textarea name="rs" property="bz" rows="3" cols="50"></html:textarea>
					</td>
					
				</tr>	
				</tbody>	
					<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<logic:equal value="yes" name="writeAble">
									<button id="buttonSave" 
										onclick="toSave();return false;"
										style="width: 80px">
										��	��
									</button>
								</logic:equal>
								&nbsp;&nbsp;
								<logic:equal value="no" name="writeAble">
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									��	��
								</button>
								</logic:equal>
							</div>
						</td>
					</tr>
				</tfoot>	
     		</table>
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
		</html:form>
	</body>
<script type="text/javascript">
  function FpWx(){
    var id1 = document.getElementById("wxfp1");
    var id2 = document.getElementById("wxfp2");
    if($("yesNo").value=="ͨ��"){
     id1.style.display="";
     id2.style.display="";
    }else{
     id1.style.display="none";    
     id2.style.display="none";
    }
  }
  
  function toSave(){
       if($("xxshyy")){
           if($("xxshyy").value.length>300){
             alert("ѧУ���˵����������300�֣�");
             return false;
          }         
       }
       var pkValue="";
       pkValue = $("pkValue").value;
		getSztzData.getInfoEx("gywxglb","ssbh||bxsj",pkValue,"wxsj<>'δά��' ",function(str){
		         if(str){		         	
		            alert("��������ά�ޣ������ٽ�����˲�����");		          
			        return false;
		         }else{
                    refreshForm('/xgxt/XsgyglDispatch.do?method=gywxSqSh&doType=save');
                    $("buttonSave").disabled='true';
		         }
    	});	       

    }
 </script>
</html>
