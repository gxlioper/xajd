<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<html>
	<head>
		<meta http-equiv=Content-Type content="text/html; charset=gbk">
		<title>����ʦ��<bean:message key="lable.xsgzyxpzxy" />����<bean:message key="lable.xsgzyxpzxy" />�Ǽ��������Ȱ취</title>
	</head>
	<script type="text/javascript">
	 function dataSave(){
		  var url = "/xgxt/jhzy_gygl.do?method=wmgyldgSh&chk=chk";
		   document.forms[0].action = url;
	       document.forms[0].submit();
		 }
	</script>
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN>
		<html:form action="/jhzy_gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ��Ԣ���� - ������Ԣ¥���� - ������Ԣ¥�����
				</div>
			</div>
			<div align=center>
				<table border=1 cellspacing=0 cellpadding=0 width="100%"
					class="tbstyle">
					<tr>
						<td class="Normal" align="right">
							<font color="red">*</font>¥����
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="lzm" />
							<input id="pkValue" name="pkValue"
								value="<bean:write name="rs" property="pk"/>" type="hidden" />
							<html:hidden name="rs" property="lzm"></html:hidden>
						</td>
						<td class="Normal" align="right">
							<font color="red">*</font>����<bean:message key="lable.xsgzyxpzxy" />
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="xydm" />
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							<font color="red">*</font>����ѧ��
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="xn" />
						</td>
						<td class="Normal" align="right">
							�꼶
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="nj" />
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							������
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="qss" />
						</td>
						<td class="Normal" align="right">
							ѧ����
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="xss" />
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							�����ϸ���
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="wshgl" />
							%
						</td>
						<td class="Normal" align="right">
							����������
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="wsyxl" />
							%
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							�Ǽ�������
						</td>
						<td colspan=2 class="Normal">
							<bean:write name="rs" property="xjqsl" />
							%
						</td>
					</tr>
					<tr id="gywmgstr">
						<td class="Normal" align="right">
							��Ԣ������
							<br>
							���������
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							¥��ǩ����&nbsp; ��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp;&nbsp; ��--%>
							<bean:write name="rs" property="gywmgs" />
						</td>
					</tr>
					<tr id="fdyyjtr">
						<td class="Normal" align="right">
							¥������Ա���
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							����Աǩ���� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��&nbsp;&nbsp;&nbsp;--%>
							<%--							��&nbsp;&nbsp;&nbsp;&nbsp; ��--%>
							<bean:write name="rs" property="fdyyj" />
						</td>
					</tr>
					<tr id="xyyjtr">
						<td class="Normal" align="right">
							����<bean:message key="lable.xsgzyxpzxy" />���
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							���� �£�--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��--%>
							<bean:write name="rs" property="xyyj" />
						</td>
					</tr>
					<tr id="xxyjtr">
						<td class="Normal" align="right">
							ѧ�������
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<%--							���� �£�--%>
							<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							<%--							��&nbsp;&nbsp;&nbsp; ��&nbsp;&nbsp;&nbsp; ��--%>
							<bean:write name="rs" property="xxyj" />
						</td>
					</tr>
					<tr id="xxyjtr">
						<td class="Normal" align="right">
							ѧУ���
						</td>
						<td colspan=5 valign=bottom class="Normal">
							<html:select name="rs" property="xxsh">
								<html:option value="δ���"></html:option>
								<html:option value="ͨ��"></html:option>
								<html:option value="��ͨ��"></html:option>
							</html:select>
						</td>
					</tr>
					<tr height=0>
						<td width=96 class="Normal"></td>
						<td width=96 class="Normal"></td>
						<td width=108 class="Normal"></td>
						<td width=120 class="Normal"></td>
						<td width=96 class="Normal"></td>
						<td width=84 class="Normal"></td>
					</tr>
				</table>
			</div>
			<div class="buttontool" id="btn" align="center">
				<button class="button2" onclick="dataSave();" style="width: 80px"
					id="buttonSave">
					���
				</button>
				&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width: 80px"
					id="buttonClose">
					�ر�
				</button>
			</div>
		</html:form>
		<logic:equal value="ok" name="done">
			<script language="javascript">
			alert("��˳ɹ���");
			Close();
			window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
		<logic:equal value="no" name="done">
			<script language="javascript">
			alert("���ʧ�ܣ�");
			//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
	</body>
</html>
