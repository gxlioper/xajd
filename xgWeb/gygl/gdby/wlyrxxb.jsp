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
	</head>	
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ϣά�� - ������Ա���õǼ� - ������Ա������Ϣά��</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/XsGyGlLogic.do?method=xsGySdCbGl" method="post">
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />

			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>������Ա������Ϣά��</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr valign="middle">
					    <th align="right" width="25%">
							<font color="red">*</font>¥�����ƣ�
						</th>
						<td align="left">
							<html:select name="rs" property="lddm" style="width:130px"
								styleId="lddm" styleId="lddm">
								<html:option value=""></html:option>
								<html:options collection="ldList" property="lddm"
									labelProperty="ldmc" />
							</html:select>
						</td>
						<th align="right" width="15%">
							<font color="red">*</font>�������ڣ�
						</th>
						<td align="left">
							<html:text name="rs" property="lfrq"  styleId="lfrq"
							readonly="true" onblur="dateFormatChg(this)"
						    onclick="return showCalendar('lfrq','y-mm-dd');" style="width:100px;cursor:hand " ></html:text>
						</td>
						
					</tr>
					<tr valign="middle">
						<th align="right" width="25%">
							<font color="red">*</font>������������
						</th>
						<td align="left">
						<html:text name="rs" property="xm"style="width:100px"   styleId="xm" maxlength="15"  ></html:text>
						</td>
						<th align="right" width="25%">
						   <font color="red">*</font>����ˣ�							
						</th>
						<td align="left">	
						<html:text  name="rs" property="hjr"style="width:100px"  styleId="hjr" maxlength="15"  ></html:text>						
						</td>
					</tr>
					<tr valign="middle">
					<th align="right" width="25%">
							����ʱ�䣺
						</th>
						<td align="left">
							<html:text  name="rs" property="lfsj" style="width:100px"  maxlength="15"  ></html:text>
						</td>
						<th align="right">
							�뿪ʱ�䣺
						</th>
						<td align="left">
							<html:text name="rs" property="lksj"style="width:100px"  maxlength="15"  ></html:text>
						</td>
					</tr>
					<tr valign="middle">
						<th align="right">
							<logic:equal name="xxdm" value="10690">
								��ǰ�Ǽ��ˣ�
							</logic:equal>
							<logic:notEqual name="xxdm" value="10690">
								ֵ����(�Ǽ���)��
							</logic:notEqual>
						</th>
						<td align="left">
							<bean:write name="rs" property="zbrxm"/>
						</td>
						<th align="right" width="25%">							
						</th>
						<td align="left">							
						</td>
					</tr>
					
					<tr style="height:22px">
						<th align="right">
							֤����
						</th>
						<td align="left" colspan="3">
						<html:text name="rs" property="zj"style="width:300px"  maxlength="50" ></html:text>
                        </td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							�������ɣ�<br>
							(��200��)
						</th>
						<td align="left" colspan="3">
						<html:textarea name="rs" property="lfsy" rows="5" cols="60"></html:textarea>
                        </td>
					</tr>
					</tbody>
					<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
								<logic:notEqual name="doType" value="view">
									<button id="buttonSave" 
										onclick="wlyrsave()"
										style="width: 80px">
										��	��
									</button>
								</logic:notEqual>
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
		</html:form>
<logic:equal value="ok" name="done">
<script language="javascript">
alert("�����ɹ���");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
<logic:equal value="no" name="done">
<script language="javascript" >
  alert("����ʧ�ܣ�");
Close();
window.dialogArguments.document.getElementById('search_go').click();   
</script>
</logic:equal>
</body>
<script type="text/javascript">
function wlyrsave(){
   if($("lfsy").value.length>200){
       alert("���������������ܴ���200�֣�");
       return false;
   }
   if(IsNoEmpty('lddm-lfrq-xm-hjr')){
    refreshForm('/xgxt/XsGyGlLogic.do?method=xsGyWlRy_Modi&doType=save');
    $("buttonSave").disabled=true;	
   }
  
}

</script>
</html>
		

		
