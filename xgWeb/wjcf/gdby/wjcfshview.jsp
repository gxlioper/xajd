<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
	<body onload="checkWinType();">
		<html:form action="/wjcfgdbywh" method="post">
			<div class="title">
					<div class="title_img" id="title_m">
						<bean:message key="wjcf_gdby_wjcfshone" bundle="wjcfgdby"/>
					</div>
			</div>
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }"/>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							�������
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width:20%">
						ѧ�ţ�
					</td>
					<td align="left" style="width:25%">
						<bean:write name="rs" property="xh"/>
						<input type="hidden" name="xh" id="xh" value="<bean:write name="rs" property="xh"/>" />
					</td>
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="nd"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name="rs" property="xm"/>
					</td>
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="rs" property="xn"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name="rs" property="xb"/>
					</td>
					<td align="right">
						�������
					</td>
					<td align="left">
					<html:select property="cflb" styleId="cflb" style="width:150" styleClass="select">
						<html:options collection="cflbList" property="cflbdm" labelProperty="cflbmc"/>
					</html:select>
						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="nj"/>
					</td>
					<td align="right">
						����ԭ��
					</td>
					<td align="left">
						<html:select property="cfyy" styleId="cfyy" style="width:150" styleClass="select">
							<html:options collection="cfyyList" property="cfyydm" labelProperty="cfyymc"/>
						</html:select>
						
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>
					</td>
					<td align="right">
						��ˣ�
					</td>
					<td align="left">
						<html:select property="sh">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc"/>
					</td>
					<td align="right">
					<font color="red">*</font>����ʱ�䣺
					</td>
					<td align="left">
						<html:text property="cfsj" styleId="cfsj"
							onblur="dateFormatChg(this)" style="cursor:hand;" readonly="true"
							onclick="return showCalendar('cfsj','y-mm-dd');" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<td align="right">
						<font color="red">*</font>�����ĺţ�
					</td>
					<td align="left">
						<html:text property="cfwh" styleId="cfwh"  maxlength="30"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						��������
					</td>
					<td colspan="4" align="left">
						<html:textarea rows="5" style="width:98%" property="clyj" styleId="a" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
						<button type="button" class="button2"
					onclick="if (chknotnull1()) refreshForm('wjcfsh.do');"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
		</html:form>
		<script language="javascript">
	function getRqVal(name)
			{
			var rq=document.getElementById(name).value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
			}
	function chknotnull1(){
		if (document.getElementById('cfwh').value==null || document.getElementById('cfwh').value==''
		||document.getElementById('cfsj').value==null|| document.getElementById('cfsj').value=='' ) {
			alert('��*������Ϊ�����');
			return false;
		}
		return true;
	}
</script>
	</body>
