
<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body>
	<html:form action="/pjpyzjjdwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<input type="hidden" name="oldtzjkbzdj" id="oldtzjkbzdj" value="<bean:write name="rs" property="tzjkbzdj"/>"/>
		<input type="hidden" name="oldbjpddj" id="oldbjpddj" value="<bean:write name="rs" property="bjpddj"/>"/>
		<input type="hidden" name="oldjxjdm" id="oldjxjdm" value="<bean:write name="rs" property="oldjxjdm"/>"/>
		<input type="hidden" name="oldszxyj" id="oldszxyj" value="<bean:write name="rs" property="oldszxyj"/>"/>
		<input type="hidden" name="oldfdyyj" id="oldfdyyj" value="<bean:write name="rs" property="oldfdyyj"/>"/>
		<input type="hidden" name="xysh" id="xysh" value="${xysh }"/>
		<input type="hidden" name="xxsh" id="xxsh" value="${xxsh }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyzjjd" key="pjpy_zjjd_jxjsq" />
			</div>
		</div>
		
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�����޸�
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="20%">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left" width="30%">
					<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
				</td>
				<td align="right" width="16%">
					��ȣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="nd" />
					<input type="hidden" name="nd" id="nd" value="<bean:write name="rs" property="nd" />">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������
				</td>
				<td align="left">
					<bean:write name="rs" property="xm" />
				</td>
				<td align="right">
					ѧ�꣺
				</td>
				<td align="left">
					<bean:write name="rs" property="xn" />
					<input type="hidden" name="xn" id="xn" value="<bean:write name="rs" property="xn" />">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�Ա�
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					ѧ�ڣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="xq"/>
					<input type="hidden" name="xq" id="xq" value="<bean:write name="rs" property="xq" />">
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				<td align="right">
					�ۺϲ����ɼ���
				</td>
				<td align="left">
					<bean:write name="rs" property="zhszcpzf" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					�ۺϲ������Σ�
				</td>
				<td align="left">
					<bean:write name="rs" property="zhszmc" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					רҵ��
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				<td align="right">
					������ͳɼ���
				</td>
				<td align="left">
					<bean:write name="rs" property="dkzdcj" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶��
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td align="right">
					�����ɼ���
				</td>
				<td align="left">
					<bean:write name="rs" property="tycj" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					���ʽ�����׼�ȼ���
				</td>
				<td align="left">
					<html:text property="tzjkbzdj" styleId="tzjkbzdj" styleClass="inputtext" maxlength="20"></html:text>
				</td>
				<td align="right">
					���������ɼ���
				</td>
				<td align="left">
					<bean:write name="rs" property="dyzf" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶�����ȼ���
				</td>
				<td align="left">
					<html:select property="bjpddj" styleId="bjpddj" styleClass="select" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="pdList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
				<td align="right">
					<font color="red">*</font>��ѧ��
				</td>
				<td align="left">
					<html:select property="jxjdm" styleId="jxjdm" styleClass="select" style="width:150px">
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<td colspan="4" style="padding:0;">
					<%@ include file="/pjpy/zjjd/yhkh.jsp"%>
				</td>
			</tr>
			<tr >
				<td align="right">
					����ϵ�����
				</td>
				<td align="left" colspan="3">
					<html:textarea property="szxyj" styleId="szxyj"
						styleClass="inputtext" rows="5" style="width:98%"></html:textarea>
				</td>
			</tr>
			<tr >
				<td align="right">
					��ע��
				</td>
				<td align="left" colspan="3">
					<html:textarea property="fdyyj" styleId="fdyyj" 
					style="width: 98%" styleClass="inputtext" rows="5"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="if (jxjsavechk()) {alert('��δ���κ��޸ģ�');return;} else { if (jxjshtg()) {alert('�ý�ѧ���Ѿ���ز�����˲�ͨ���������޸ģ�');return;} else saveinfo('jxjmodi.do','xh-jxjdm');}"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
				<!-- ������ʾ -->
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>		
	</html:form>
</body>
