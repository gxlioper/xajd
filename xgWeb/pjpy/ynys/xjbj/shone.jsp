<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js">
</script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body onload="">
	<html:form action="/pjpyynyswh" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message key="pjpy_ynys_xjbjsh" bundle="pjpyynys"/>
			</div>
		</div>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<table class="tbstyle" align="center" width="98%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�Ƚ��༶�������
					</td>
				</tr>
			</thead>
			<tr style="height:22px">
				<td align="right" width="20%">
					<bean:message key="lable.xsgzyxpzxy" />:
				</td>
				<td align="left" width="30%">
					<bean:write name="rs" property="xymc"/>					
				</td>
				<td align="right" width="20%">
					ѧ��:
				</td>
				<td align="left" width="30%">
					<bean:write name="rs" property="xn"/>
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
					רҵ:
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc"/>					
				</td>
				<td align="right">
					�༶����:
				</td>
				<td align="left">
					<bean:write name="rs" property="bjch"/>
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
					�༶:
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc"/>					
				</td>
				<td align="right">
					������:
				</td>
				<td align="left">
					<bean:write name="rs" property="bzr"/>
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
					�꼶:
				</td>
				<td align="left">
					<bean:write name="rs" property="nj"/>					
				</td>
				<td align="right">
					���:
				</td>
				<td align="left">
					<html:select property="yesNo" styleId="yesNo"
					 styleClass="select">
						<html:options collection="shList" 
						property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
					�༶������:
				</td>
				<td align="left" colspan="3">
					<html:textarea name="rs" property="bjdbqk" rows="4" 
					disabled="true" style="width:98%"></html:textarea>
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
					���������:
				</td>
				<td align="left" colspan="3">
					<html:textarea name="rs" property="bzryj" rows="3" 
					disabled="true" style="width:98%"></html:textarea>
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
					����Ա���:
				</td>
				<td align="left" colspan="3">
					<html:textarea name="rs" property="fdyyj" rows="3"
					 disabled="true" style="width:98%"></html:textarea>
				</td>
			</tr>
			<tr style="height:22px">
				<td align="right">
				<logic:equal value="xy" name="userType">
					<bean:message key="lable.xsgzyxpzxy" />���:
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
					ѧ�������:
				</logic:notEqual>
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yj" rows="3" style="width:98%">
					</html:textarea>
				</td>
			</tr>
			<logic:notEqual value="xy" name="userType">
				<tr style="height:22px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />���:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="xyyj" rows="3" style="width:98%">
					</html:textarea>
				</td>
			</tr>
			</logic:notEqual>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="refreshForm('ynys_xjbjshoneres.do');document.getElementById('btn_save').disabled=true"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" 
					onclick="window.close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
		<!-- ������ʾ��Ϣ -->
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>