<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/wjcfFuction.js"></script>
<script type="text/javascript">
<!--
	//������Ϣ
//-->
</script>
<body>
	<html:form action="/wjcfxmlgwh" method="post">
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />

		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ��: ��ǰλ�ã�Υ�ʹ��� - �����У�쿴 - ���
			</div>
		</div>
		<table class="tbstyle" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�������
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="15%">
					ѧ�ţ�
				</td>
				<td align="left" width="35%">
					${rs.xh }
				</td>
				<td align="right" width="15%">
					ѧ�꣺
				</td>
				<td align="left" width="35%">
					${rs.xn }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������
				</td>
				<td align="left">
					${rs.xm }
				</td>
				<td align="right">
					��ȣ�
				</td>
				<td align="left">
					${rs.nd }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�Ա�
				</td>
				<td align="left">
					${rs.xb }
				</td>
				<td align="right">
					�������
				</td>
				<td align="left">
					${rs.cflbmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					${rs.nj }
				</td>
				<td align="right">
					����ԭ��
				</td>
				<td align="left">
					${rs.cfyymc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					${rs.xymc }
				</td>
				<td align="right">
					����ѧ��&nbsp;&nbsp;
					<br />
					��ȣ�
				</td>
				<td align="left">
					${rs.xn }/${rs.nd }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					רҵ��
				</td>
				<td align="left">
					${rs.zymc }
				</td>
				<td align="right">
					�����ĺţ�
				</td>
				<td align="left">
					${rs.cfwh }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶��
				</td>
				<td align="left" colspan="">
					${rs.bjmc }
				</td>
				<td align="right">
					����ʱ�䣺
				</td>
				<td align="left">
					${rs.cfsj }
				</td>
			</tr>
			<tr>
				<td align="right">
					��У�쿴��
				</td>
				<td align="center" colspan="3">
					${rs.lxcfsj } �� ${rs.lxcksj }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					ѧ������
					<br />
					����Ҫ��
				</td>
				<td align="left" colspan="3">
					<html:textarea property="xsbx" styleId="xsbx" name="rs" disabled="true" rows="5"
						style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					���ʱ�䣺
				</td>
				<td align="left" colspan="">
					<html:text property="jcsj" styleId="jcsj" readonly="true" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jcsj','y-mm-dd');"></html:text>
				</td>
				<td align="right">
					����ĺţ�
				</td>
				<td align="left">
					<html:text property="jcwh" styleId="jcwh"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					��������
				</td>
				<td align="left">
					<html:select property="jcjg" styleId="jcjg">
						<html:option value=""></html:option>
						<html:options collection="jcList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
				<td align="right">
					��ˣ�
				</td>
				<td align="left" colspan="">
					<html:select property="sh" styleId="sh">
						<html:options collection="shList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />�����
				</td>
				<td align="left" colspan="3">
					<html:textarea property="xyyj" styleId="xyyj" name="rs" disabled="true" rows="5"
						style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					ѧ���������
				</td>
				<td align="left" colspan="3">
					<html:textarea property="xxyj" styleId="xxyj" name="rs" disabled="true" rows="5"
						style="width:95%"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					У�������
				</td>
				<td align="left" colspan="3">
					<html:textarea property="yj" styleId="yj" name="rs" rows="5"
						style="width:95%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
				<button type="button" class="button2" id="btn_save"
					onclick="saveinfo('wjcf_xmlg_xbLxckxxDgsh.do?operType=save','');"
					style="width:80px">
					�� ��
				</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2" id="btn_close" onclick="window.close();return false;"
				style="width:80px">
				�� ��
			</button>
		</div>
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
