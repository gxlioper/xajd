<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/dispatch" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - ��� - ������˻���</a>
				</p>
			</div>
			
				<logic:equal value="12872" name="xxdm">
				<div class="tab">
					<table width="100%" align="center" class="formlist">
				<thead>
					<tr style="">
						<th colspan="4">
							<span>�������</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="">
					<th align="right">
						�༶
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<th align="right">
						�೤
					</th>
					<td align="left">
						<bean:write name="rs" property="bzxm"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						ѧ������
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="xsrs"/>
					</td>
					<th align="right">
						������
					</th>
					<td align="left">
						<bean:write name="rs" property="bzr"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						ѧ��
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="xn"/>
					</td>
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						<bean:write name="rs" property="xq"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						���
					</th>
					<td align="left" id="">
						<logic:equal value="xy" name="userType">
							<html:select name="rs" property="xysh" styleId="xysh" >
							
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<html:select name="rs" property="xxsh" styleId="xxsh" >
							
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:notEqual>
					</td>
					
					<logic:equal value="xy" name="userType">
						<th align="right">
						(ϵ)Ժǩ��
					</th>
					<td align="left">
						<html:text property="xyqm" name="rs" styleId="xyqm"></html:text>
					</td>
						
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<td align="right">
						&nbsp;
					</td>
					<td align="left">
						&nbsp;
					</td>
					</logic:notEqual>
					
				</tr>
				
				<tr style="">
					<th align="right">
						��Ҫ�¼�
					</th>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="zysj" rows="8" style="width:98%"></html:textarea>
					</td>	
				</tr>
				<tr style="">
					<th align="right">
						������
					</th>
					<td align="left" colspan="3">
						<logic:notEqual value="xy" name="userType">
							<textarea name="xxyj" id="xxyj" rows="3" style="width:98%"><logic:equal value="" name="xxyj">ͬ��</logic:equal><logic:notEqual value=" " name="xxyj">${xxyj}</logic:notEqual></textarea>
							
						</logic:notEqual>
						<logic:equal value="xy" name="userType">
							<textarea name="xyyj" id="xyyj" rows="3" style="width:98%"><logic:equal value="" name="xyyj">ͬ���Ƽ�</logic:equal><logic:notEqual value=" " name="xyyj">${xyyj}</logic:notEqual></textarea>
						</logic:equal>
					</td>	
				</tr>
				</tbody>
			</table>
			</div>
				</logic:equal>
			<logic:notEqual value="12872" name="xxdm">
			<logic:equal value="10863" name="xxdm">
			<div class="tab">
			<table width="100%" align="center" class="formlist">
				<thead>
					<tr style="">
						<th colspan="4">
							<span>�������</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="">
					<th align="right" style="width:20%">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left" id="" style="width:30%">
						<bean:write name="rs" property="xymc"/>
					</td>
					<th align="right" style="width:20%">
						רҵ
					</th>
					<td align="left" style="width:30%">
						<bean:write name="rs" property="zymc"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						�༶
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<th align="right">
						�೤
					</th>
					<td align="left">
						<bean:write name="rs" property="bzxm"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						ѧ������
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="xsrs"/>
					</td>
					<th align="right">
						������
					</th>
					<td align="left">
						<bean:write name="rs" property="bzr"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						ѧ��
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="xn"/>
					</td>
					<th align="right">
						��֧��
					</th>
					<td align="left">
						${rs.tzs }
					</td>
				</tr>
				<tr style="">
					<th align="right">
						��������&nbsp;&nbsp;&nbsp;<br/>���ϸ�����
					</th>
					<td align="left" id="">
						${rs.bhgqs }
					</td>
					<th align="right">
						����ͬѧ�ܹ�&nbsp;&nbsp;&nbsp;<br/>����,��,�żʹ���
					</th>
					<td align="left">
						${rs.ywcf }
					</td>
				</tr>
				<tr style="">
					<th align="right">
						�༶����
					</th>
					<td align="left" colspan="3">
						${rs.bjry }
					</td>
				</tr>
				<tr style="">
					<th align="right">
						��Ҫ�ɼ�
					</th>
					<td align="left" colspan="3" >
						${rs.zysj }
					</td>
				</tr>
				<tr style="">
					<th align="right">
						���
					</th>
					<td align="left" colspan="3">
						<logic:equal value="xy" name="userType">
							<html:select name="rs" property="xysh" styleId="xysh" >
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<html:select name="rs" property="xxsh" styleId="xxsh" >
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<logic:equal value="xy" name="userType">
					<tr style="">
					<th align="right" >
						<bean:message key="lable.xsgzyxpzxy" />����֧���
					</th>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="yxdzbyj" styleId="yxdzbyj" style="width:95%" rows="5">
						</html:textarea>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
					<tr style="">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />ѧ�������
					</th>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="xxyj" styleId="xxyj" style="width:95%" rows="5">
						</html:textarea>
					</td>
					</tr>
					<tr style="">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />���
					</td>
					<th align="left" colspan="3">
						<html:textarea name="rs" property="xyyj" styleId="xyyj" style="width:95%" rows="5">
						</html:textarea>
					</th>
					</tr>
				</logic:notEqual>
				</tbody>
			</table>
			</div>
			</logic:equal>
				<logic:notEqual value="10863" name="xxdm">
					<div class="tab">
					<table width="100%" align="center" class="formlist">
				<thead>
					<tr style="">
						<th colspan="4">
							<span>�������</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="">
					<th align="right" style="width:20%">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left" id="" style="width:30%">
						<bean:write name="rs" property="xymc"/>
					</td>
					<th align="right" style="width:20%">
						רҵ
					</th>
					<td align="left" style="width:30%">
						<bean:write name="rs" property="zymc"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						�༶
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<th align="right">
						�೤
					</th>
					<td align="left">
						<bean:write name="rs" property="bzxm"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						ѧ������
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="xsrs"/>
					</td>
					<th align="right">
						������
					</th>
					<td align="left">
						<bean:write name="rs" property="bzr"/>
					</td>
				</tr>
				<tr style="">
					<th align="right">
						ѧ��
					</th>
					<td align="left" id="">
						<bean:write name="rs" property="xn"/>
					</td>
					<th align="right">
						���
					</th>
					<td align="left" colspan="">
						<logic:equal value="xy" name="userType">
							<html:select name="rs" property="xysh" styleId="xysh" >
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<html:select name="rs" property="xxsh" styleId="xxsh" >
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<logic:equal value="12682" name="xxdm">
				<tr style="">
					<th align="right">
						����ƺ�
					</th>
					<td align="left" colspan="3">
						${rs.jtch }
					</td>	
				</tr>
				</logic:equal>
				<tr style="">
					<th align="right">
						��Ҫ�¼�
					</th>
					<td align="left" id="" colspan="3" height="70px">
						<html:textarea property="zysj" name="rs" rows="4" style="width:95%" readonly="true"></html:textarea>
					</td>
				</tr>
				<logic:equal value="10355" name="xxdm">
				<tr style="">
					<th align="right">
						��ע<font color="red">(�༶ѧ��<br/>�ܴ���,����,<br/>����������)</font>
					</th>
					<td align="left" id="" colspan="3" height="70px">
					<html:textarea property="bz" name="rs" rows="4" style="width:95%" readonly="true"></html:textarea>
					</td>
				</tr>
				</logic:equal>
				<%--<tr style="">
					<td align="right">
						��ˣ�
					</td>
					<td align="left" colspan="3">
						<logic:equal value="xy" name="userType">
							<html:select name="rs" property="xysh" styleId="xysh" >
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:equal>
						<logic:notEqual value="xy" name="userType">
							<html:select name="rs" property="xxsh" styleId="xxsh" >
							<html:options collection="shList" property="en" labelProperty="cn"/>
						</html:select>
						</logic:notEqual>
					</td>
				</tr>
				--%><logic:equal value="xy" name="userType">
					<tr style="">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />���
					</th>
					<td align="left" colspan="3">
						<html:textarea property="xyyj" styleId="xyyj" style="width:95%" rows="5">
						</html:textarea>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
					<tr style="">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />ѧ�������
					</th>
					<td align="left" colspan="3">
						<html:textarea name="rs" property="xxyj" styleId="xxyj" style="width:95%" rows="5">
						</html:textarea>
					</td>
					</tr>
					
				</logic:notEqual>
				</tbody>
			</table>
			</div>
				</logic:notEqual>
				
			</logic:notEqual>
			
		
			          <div class="btn" align="right">
			          <logic:notEqual name="doType" value="view">
			          		<button name="�ύ" id="buttonSave" onclick="refreshForm('dispatch.do?method=xjbjwmbjSave');">�� ��</button>
			          </logic:notEqual>
			            <button name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
			          </div>
			
		</html:form>
		<logic:present name="result">
			<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:equal>
			<logic:equal value="no" name="result">
				<script>
					alert("����ʧ�ܣ�");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
			</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
