<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<script language="javascript">
		function getXyValue(){
   			var getSelectText = $("xydm").options[$("xydm").selectedIndex].text;
   			var realValue = document.getElementById("format").value;
   			if(realValue==""){
   				document.getElementById("format").value = getSelectText; 
   			}else{
   				document.getElementById("format").value = realValue+","+getSelectText;
   			}
   			var getSelectValue =document.getElementById("xydm").value;
   			var realValue = document.getElementById("sxsfdm").value;
   			if(realValue==""){
   				document.getElementById("sxsfdm").value = getSelectValue; 
   			}else{
   				document.getElementById("sxsfdm").value = realValue+","+getSelectValue;
   			}                
		}
</script>
</head>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<html:form action="/szdwfzjy" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��չ����-��չ�������-������ѧ����</a>
				</p>
			</div>
			
			<logic:notEmpty name="rs">
				<input type="hidden" id="sxsfdm" name="sxsfdm" value=""/>
				<input type="hidden" id="pk" name="pk"
					value="<bean:write  name="pk" scope="request"/>" />
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope = "session"/>" />
				<input type="hidden" id="tableName" name="tableName"
					value="<bean:write name="tableName" scope="request"/>" />
					<div class="tab">
					  <table width="100%"  border="0" class="formlist">
					    <thead>
					    	<tr>
					        	<th colspan="4"><span>������ѧ����</span></th>
					        </tr>
					    </thead>
						 <tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
					          <logic:notEqual name="doType" value="view">
					          		<button type="button" name="����" id="buttonSave" onclick="szsxDataDoSave('szdwfzjy.do?method=saveJyltxtOne','fssj-zjr');">�� ��</button>
					          </logic:notEqual>
					            <button type="button" name="�ر�" onclick="Close();return false;"  id="buttonClose">�� ��</button>
					          </div></td>
					      </tr>
					    </tfoot>
						<tbody>
						<tr>
							<th align="right">
								<font color="red">*</font>����ʱ��:
							</th>
							<td align="left">
								<html:text name= "rs"  property="fssj" styleId="fssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('fssj','y-mm-dd');" />
							</td>
							<th align="right">
								<font color="red">*</font>��֯��:
							</th>
							<td align="left">
								<html:text name = "rs" property="zjr" />
							</td>
						</tr>
						<tr>
							<th align="right">
								��֯�˵�λ:
							</th>
							<td align="left">
								<html:text name = "rs" property="zjrdw" />
							</td>
							<th align="right">
								ѡ�����<bean:message key="lable.xsgzyxpzxy" />:
							</th>
							<td align="left">
									<html:select name = "rs" property="xydm" styleId="xydm" onchange="getXyValue()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								��ѡ�����<bean:message key="lable.xsgzyxpzxy" />:
							</th>
							<td align="left" colspan="3">
	        						<html:textarea name = "rs" property="cyxy" styleId="format"
									style="width:99%" rows='3'/>
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								�γ����� 
							</th>
							<td colspan="3">
								<html:text name = "rs" property="kcmc" style="width:99%"  />
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								��ע
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</table>
				</div>
			</logic:notEmpty>
			<logic:present name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("�ύ�ɹ���");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("�ύʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
