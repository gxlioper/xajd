<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/wjcfFuction.js"></script>
<body>
	<html:form action="/wjcfxmlgwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="keys" id="keys" value="${keys }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ��: ��ǰλ�ã�Υ�ʹ��� - У����� 
			</div>
		</div>
		<table class="tbstyle" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�����������
					</td>
				</tr>
			</thead>
			<logic:notEqual value="xy" name="userType">
			<tr style="height:23px">
				<td align="right">
					�����ĺţ�
				</td>
				<td align="left">
					<html:text property="cfwh" styleId="cfwh"></html:text>
				</td>
			</tr>
			<tr>
				<td align="right">
					����ʱ�䣺
				</td>
				<td align="left">
					<html:text property="cfsj" styleId="cfsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd');" readonly="true"></html:text>
				</td>
			</tr>
			</logic:notEqual>
			<tr style="height:23px">
				<td align="right">
					��ˣ�
				</td>
				<td align="left">
					<html:select property="sh" styleId="sh">
						<html:options collection="shList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					��˴��������
				</td>
				<td align="left">
					<html:textarea property="yj" rows="4" style="width:98%">
							</html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('wjcf_xmlg_cfplshbyxb.do?operType=save','');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
						�� ��
					</button>
				</div>
				<!-- �������ʾҳ�� -->	
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
