<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript"
	src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type="text/javascript">
<!--
		
//-->
</script>
<body onload="checkWinType();">
	<html:form action="/xmlgpjpy" method="post">
		<input type="hidden" name="pt" id="pt"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ��:�������� - �������� - ��ѧ�����
			</div>
		</div>
		<table style="width:100%" class="tbstyle">
			<thead>
				<tr>
					<td colspan="2" align="left">
						�����������Ʒ�ʽ����
						<br/>
						<font color="red">˵�����ò��������<bean:message key="lable.xsgzyxpzxy" />���н�ѧ����������ʱ���������á�<br/>
						      ��ʽ1�������������ƣ�<bean:message key="lable.xsgzyxpzxy" />�ڵ�����ѧ������ʱ��<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���ܳ���ѧУ���ñ��������ɽ����������������뷶Χ.<br/>
						      ��ʽ2��������ƣ�<bean:message key="lable.xsgzyxpzxy" />�ڵ�����ѧ������ʱ��<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ڲ�����ѧУ���ñ��������ɵ��ܽ���ǰ���£�<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���Ը���ʵ�����������������. 
						 </font>
					</td>
				</tr>
			</thead>
			<tr style="width:22px">
				<td align="right" width="50%">
					<font color="red">*</font>���Ʒ�ʽ��
				</td>
				<td align="left">
					<html:select property="xzfs" styleId="xzfs">
						<html:option value=""></html:option>
						<html:options collection="xzfsList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
				<button type="button" class="button2" id="btn_save"
					onclick="saveinfo('pjpy_xmlg_tzrsFssz.do?operType=save','xzfs')"
					style="width:80px">
					�� ��
				</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="button2" id="btn_close" onclick="Close();return false;"
				style="width:80px" id="buttonClose">
				�� ��
			</button>
		</div>
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
