<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
				<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
	</head>
	<body>
	<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ���� - ѧ���߶�(��ס)����</a>
			</p>
		</div>
		<!-- ���� end-->	
		<html:form action="/XsgyglDispatch.do?method=xsZdSq" method="post">			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">							
				<fieldset>	
			<logic:notEqual value="stu" name="userType">					
			   <div align="center"><font color="red">ֻ��ѧ���û���������!</font> </div>
		     </logic:notEqual>
								
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<td colspan="4" align="center">
								<span>ѧ���߶�����</span>
							</td>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<th align="right">
								ѧ�ţ�
							</th>
							<td align="left">							
								<html:text name='rs' property="xh" readonly="true"
									styleId="xh"/>							
							</td>
							<th align="right">
								��ס��ʼѧ�꣺
							</th>
							<td align="left">
								<html:select name="rs" property="xn" styleId="xn">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
						    <th align="right">
								������
							</th>
							<td align="left">
								<bean:write name="rs" property="xm"/>
							</td>						
							<th align="right">
								��ס��ʼѧ�ڣ�
							</th>
							<td align="left">
							  <html:select name="rs" property="xq" styleId="xq">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								�꼶��
							</th>
							<td align="left">
								<bean:write name="rs" property="nj"/>
							</td>
							 <th align="right">
								��ס��ʼ���ڣ�
							</th>
							<td align="left">
                                   <html:text name="rs" property="wzksrq" readonly="true"
									onclick="return showCalendar('wzksrq','y-mm-dd');"
									onblur="dateFormatChg(this)" style="cursor:hand " property="wzksrq"/>							</td>
						</tr>
						<tr>
							<th align="right">
								�Ա�
							</th>
							<td align="left">
								<bean:write name="rs" property="xb"/>
							</td>
							<th align="right">
								�ƻ���סʱ�䣺
							</th>
							<td align="left">
								<html:text name='rs' property="jhwzsj" styleId="jhwzsj" style="width:80px"/><span style="color: red">�磺12����</span>						
							</td>
						</tr>
						
						<tr>						   
							<th align="right">
								רҵ��
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc"/>
							</td>
							<th align="right">
								��ס���ͣ�
							</th>
							<td align="left">
								<html:select name="rs" property="wzlxdm" styleId="wzlxdm">
									<html:options collection="wzlxList" property="wzlxdm" labelProperty="wzlxmc" />
								</html:select>
							</td>
						</tr>
						<tr>						   
							<th align="right">
								�༶��
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc"/>
							</td>							
							<th align="right">
								��ס��ַ��
							</th>
							<td align="left">
								<html:text name="rs" property="wzdz" styleId="wzdz"/>
							</td>
						</tr>
						<tr>						   
							<th align="right">
								�ֻ����룺
							</th>
							<td align="left">
								<bean:write name="rs"  property="sjhm"/>
							</td>							
							<th align="right">
								�ҳ��Ƿ�ͬ�⣺
							</th>
							<td align="left">
								<html:select name="rs" property="jzsfty" styleId="jzsfty">
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>	
						<tr>						   
							<th align="right">
								�̶��绰��
							</th>
							<td align="left">
								<bean:write name="rs"  property="lxdh"/>
							</td>							
							<th align="right">
								
							</th>
							<td align="left">								
							</td>
						</tr>	
						<tr>						   
							<th align="right">
								�������䣺
							</th>
							<td align="left">
								<bean:write name="rs"  property="lxdzxx"/>
							</td>							
							<th align="right">
								
							</th>
							<td align="left">								
							</td>
						</tr>					
						<tr>
							<th align="right">
								��סԭ��<br>(��200����)
							</th>
							<td align="left" colspan="3">
							    <html:textarea  name="rs" property="wzyy" styleId="wzyy" rows="6"  
									style="width:95% " ></html:textarea>								
							</td>
							
						</tr>
						</tbody>
						<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
									<button id="buttonSave" 
										onclick="wzDataSave()"
										style="width: 80px">
										�� ��
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
</table>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("����ɹ�!");				
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��,���������룡");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function wzDataSave(){
            if(mstFill("xh-xn-xq-wzksrq-jhwzsj-wzlxdm-wzdz-wzyy-lxfs-jzsfty"))
            { 
               if($('wzyy').value.length>200){
                  alert('��סԭ����������200�֣�');
                  return false;
               }else{                  
                  refreshForm('/xgxt/XsgyglDispatch.do?method=xsZdSqSave');
                   $("buttonSave").disabled=true;
               }
          }    
       }
       function mstFill(mustFill){
           var eles = mustFill.split("-");
	       for (i = 0; i < eles.length; i++) {
		      if($(eles[i])){
			     if (document.getElementById(eles[i]).value == "") {
				    alert("����ѡ���Ϊ�գ�");
				    return false;
			     }
		      }
	       }
	       return true;
       }
	</script>
</html>
