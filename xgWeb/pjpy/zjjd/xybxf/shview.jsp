<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body onload="checkWinType();">
	<html:form action="/pjpyzjjdwh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		<bean:message bundle="pjpyzjjd" key="pjpy_zjjd_xybxf" />
       		</div>
    	</div>
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }"/>
			<input type="hidden" id="oldsh" name="oldsh" value="${oldsh }"/>
			<input type="hidden" id="oldyj" name="oldyj" value="${oldyj }"/>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							�������
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left">
						<html:text name='rs' property="xh"
								styleId="xh" readonly="true"/>
					</td>
					<td align="right" width="85">
						<font color="red">*</font>ѧ�꣺
					</td>
					<td align="left">
						<html:select name="rs" property="xn" style="width:90px" styleClass="select"
								disabled="true"	styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							������
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
						</td>
					<td align="right">
						<font color="red">*</font>ѧ�ڣ�
					</td>
					<td align="left">
						<html:select name="rs" property="xq" styleId="xq" disabled="true"
						onchange="refreshForm('xybxfModi.do')" styleClass="select" style="width:90px">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
						</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							�Ա�
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
						</td>
					<td align="right">
						<font color="red">*</font>�·ݣ�
					</td>
					<td align="left">
						<html:select name="rs" property="yf" style="width:70px;padding-left:60px"
							disabled="true"	styleId="yf" styleClass="select" >
									<html:option value=""></html:option>
									<html:options collection="yfList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							�꼶��
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
						</td>
					<td align="right">
						�ӷ֣�
					</td>
					<td align="left">
						<html:text name="rs" property="jf" styleId="jf" styleClass="inputtext"
						onblur="ckinpdata(this)" maxlength="5" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
						</td>
					<td align="right">
						�۷֣�
					</td>
					<td align="left">
						<html:text name="rs" property="kf" styleId="kf" styleClass="inputtext" 
						onblur="ckinpdata(this)" maxlength="5" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							רҵ��
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
						</td>
					<td align="right">
						��ˣ�
					</td>
					<td align="left">
						<html:select name="rs" property="sh" styleId="sh"
						style="width:70px" styleClass="select">
							<html:options collection="chkList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
				</tr>
				<tr>
					<td colspan="" align="right">
						���
					</td>
					<td colspan="3" align="left">
						<html:textarea name="rs" property="sx" styleClass="inputtext" disabled="true"
						rows="4" styleId="sx" style="width:98%"></html:textarea>
					</td>
				</tr>
				<logic:notEqual name="userType" value="stu">
				<logic:notEqual name="userType" value="xy">
				<tr>
					<td colspan="" align="right">
						<bean:message key="lable.xsgzyxpzxy" />�����
					</td>
					<td colspan="3" align="left">
						<html:textarea name="rs" property="xyyj" styleClass="inputtext" disabled="true"
						rows="4" styleId="xyyj" style="width:98%"></html:textarea>
					</td>
				</tr>
				</logic:notEqual>
				</logic:notEqual>
				<tr>
					<td colspan="" align="right">
						��������
					</td>
					<td colspan="3" align="left">
						<html:textarea name="rs" property="yj" styleClass="inputtext"
						rows="4" styleId="yj" style="width:98%"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="if (shExists()) {alert('��δ���κ��޸ģ�');return;} else shRes('xybxfshone.do');"
						style="width:80px" id="btn_Save" >
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>