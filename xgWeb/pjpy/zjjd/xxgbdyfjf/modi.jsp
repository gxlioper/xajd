<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body onload="checkWinType();">
	<html:form action="/pjpyzjjdwh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		<bean:message bundle="pjpyzjjd" key="pjpy_zjjd_xxgbdyfjf" />
       		</div>
    	</div>
    	<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("�������ѧ����Ч!");
    				</script>
			</logic:equal>
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }"/>
			<logic:notEmpty name="rs">
				<input type="hidden" id="oldxn" name="oldxn" value="<bean:write name="rs" property="xn"/>"/>
			<input type="hidden" id="oldxq" name="oldxq" value="<bean:write name="rs" property="xq"/>"/>
			<input type="hidden" id="olddrzw" name="olddrzw" value="<bean:write name="rs" property="drzw"/>"/>
			<input type="hidden" id="oldrzsj" name="oldrzsj" value="<bean:write name="rs" property="rzsj"/>"/>
			<input type="hidden" id="oldkhdj" name="oldkhdj" value="<bean:write name="rs" property="khdj"/>"/>
			<input type="hidden" id="oldjf" name="oldjf" value="<bean:write name="rs" property="jf"/>"/>
			<input type="hidden" id="oldbz" name="oldbz" value="<bean:write name="rs" property="bz"/>"/>
			</logic:notEmpty>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							��������
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right" width="85px">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left">
						<html:text name='rs' property="xh"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							
					</td>
					<td align="right">
						<font color="red">*</font>ѧ�꣺
					</td>
					<td align="left">
						<html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn">
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
						<html:select property="xq" styleId="xq" 
						 styleClass="select" style="width:90px">
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
						<font color="red">*</font>����ְ��
					</td>
					<td align="left">
							<html:text property="drzw" styleId="drzw" styleClass="inputtext">
							</html:text>
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
						��ְʱ�䣺
					</td>
					<td align="left">
						<html:text property="rzsj" styleId="rzsj" readonly="true"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('rzsj','y-mm-dd');" />
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
						���˵ȼ���
					</td>
					<td align="left">
						<html:select property="khdj" styleId="khdj" style="width:80px" styleClass="select">
							<html:option value=""></html:option>
							<html:options collection="djList" property="en" labelProperty="cn"/>
						</html:select>
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
						�ӷ֣�
					</td>
					<td align="left">
						<html:text property="jf" styleId="jf" styleClass="inputtext"
						onblur="ckinpdata(this)" maxlength="5"></html:text>
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
						��ע��
					</td>
					<td colspan="3" align="left">
						<html:textarea property="bz" styleClass="inputtext" 
						rows="4" styleId="bz" style="width:98%"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="if (savexxExists()) {alert('��δ���κ��޸ģ�');return;} else saveinfo('xsgbdyedit.do','xh-xn-xq-drzw');"
						style="width:80px">
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