<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<base target="_self">
<body >
	<html:form action="/wjcfhngywh.do" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>	
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ��:Υ�ʹ��� - ����ά�� - �ճ���Ϊ��¼
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
				<td align="right">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left">
					<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
				</td>
				<td align="right" style="width:22%">
					<font color="red">*</font>ѧ�꣺
				</td>
				<td align="left">
					<html:select property="xn" styleId="xn" styleClass="select" style="width: 90px">
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
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
					<font color="red">*</font>��ȣ�
				</td>
				<td align="left">
					<html:select property="nd" styleId="nd" styleClass="select" style="width: 90px">
						<html:options collection="xnList" property="nd" labelProperty="nd"/>
					</html:select>
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
					<font color="red">*</font>ѧ�ڣ�
				</td>
				<td align="left">
					<html:select property="xq" styleId="xq" styleClass="select" style="width: 90px">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
					</html:select>
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
					<font color="red">*</font>Υ��ʱ��:
				</td>
				<td align="left">
					<html:text property="wjsj" styleId="wjsj" style="width:120px"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('wjsj','y-mm-dd');" />
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
					
				</td>
				<td align="left">
					
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
					
				</td>
				<td align="left">
					
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
					
				</td>
				<td align="left">
					
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					Υ�����:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="wjqk" styleId="wjqk" style="width:95%" rows="8">
					</html:textarea>
				</td>
 			</tr>
 			<tr style="height:23px">
				<td align="right">
					��ע:
				</td>
				<td align="left" colspan="3">
					<html:textarea property="bz" styleId="bz" style="width:95%" rows="5">
					</html:textarea>
				</td>
 			</tr>
		</table>
		<div class="buttontool" align="center">
						<logic:notPresent name="act">
							<button type="button" class="button2" id="btn_save" 
							onclick="saveinfo('wjcf_hngy_rcxwmodisave.do','xh-xn-xq-nd-wjsj');"
							style="width:80px">
							�� ��
							</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notPresent>
						<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
		</div>
		<!-- ������ʾ��Ϣ -->
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>