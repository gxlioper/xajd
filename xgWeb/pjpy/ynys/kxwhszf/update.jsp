<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js">
</script>
<body>
	<html:form action="/pjpyynyszhszcp" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="failinfo" id="failinfo" value="${failinfo }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/ynys_kxwhszfAdd.do" />
		<input type="hidden" id="delIds" name="delIds"
			value="" />
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyynys" key="pjpy_ynys_szcpwh" />
			</div>
		</div>
		<logic:present name="rs" >
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						��ѧ�Ļ����ʷ�����
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					ѧ�ţ�
				</td>
				<td align="left">
					<bean:write name="rs" property="xh" />
				</td>
				<td align="right">
					ѧ�꣺
				</td>
				<td align="left">
					<bean:write name="rs" property="xn" />
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
					�Ա�
				</td>
				<td align="left">
				<bean:write name="rs" property="xb" />
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
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
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
					�༶��
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					���״̬��
				</td>
				<logic:equal name="userType" value="stu" scope="session">
				<td align="left">
					<bean:write name="rs" property="shzt" />
				</td>
				</logic:equal>
				<logic:notEqual name="userType" value="stu" scope="session">
				<td align="left">
					<html:select name="rs" property="shzt" styleId="shzt" styleClass="select">
						<html:option value="δ���">δ���</html:option>
						<html:option value="ͨ��">ͨ��</html:option>
						<html:option value="��ͨ��">��ͨ��</html:option>
					</html:select>
				</td>
				</logic:notEqual>
				<td align="right">
				</td>
				<td align="left">
				</td>
			</tr>
		</table>
		<fieldset>
				<legend>�γ�
				</legend>
				<table class="tbstyle" align="center" width="93%" id="tTb">
					<thead style="height:23px">
						<tr>
							<td align="center">
								�γ�����
							</td>
							<td align="center">
								����
							</td>
							<td align="center">
								�γ�����
							</td>
							<td align="center">
								�Ƿ���޿�
							</td>
							<td align="center">
								�÷�
							</td>
							<td align="center">
								����
							</td>
						</tr>
					</thead>
					<logic:iterate id="r" name = "rsList">
					<tr id="<bean:write name="r" property="xid"/>">
						<td align="center">
							<input type="hidden" name="xid" value="<bean:write name="r" property="xid"/>">
							<html:text name = 'r' property="kcmc" styleId="kcmc1"></html:text>
						</td>
						<td align="center">
							<html:text  name = 'r' property="cj" onblur="ckinpdata(this)" styleId="cj1"
							 	style="width:50px"></html:text>
						</td>
						<td align="center">
							<html:select name = 'r' property="kclx" styleId="kclx1" style="width:80px">
								<html:option value="0">רҵ��</html:option>
								<html:option value="1">�Ļ���</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:select name = 'r' property="sfbxk" styleId="sfbxk1" style="width:50px">
								<html:option value="0">��</html:option>
								<html:option value="1">��</html:option>
							</html:select>
						</td>
						<td align="center">
							<html:text  name = 'r' property="df"  style="width:70px" onkeypress="return sztzNumInputValue(this,9,event)" onkeyup="value=value.replace(/[^(\d|\.)]/g,'') "></html:text>
						</td>
						<td align="center">
							<button type="button" class="button2" id="btn_del" 
							onclick="$('<bean:write name="r" property="xid"/>').style.display='none';$('delIds').value+='<bean:write name="r" property="xid"/>'+'-'"
							style="width:40px">
							ɾ��
							</button>
						</td>
					</tr>
					</logic:iterate>
				</table>
		</fieldset>
		<div class="buttontool" align="center">
					<logic:equal name="userType" value="stu" scope="session">
					<logic:notEqual name="rs" property="shzt" value="ͨ��">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('/xgxt/ynys_kxwhszfbmodisave.do','xh-xn');"
						style="width:80px">
						�� ��
					</button>
					</logic:notEqual>
					</logic:equal>
					<logic:notEqual name="userType" value="stu" scope="session">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('/xgxt/ynys_kxwhszfbmodisave.do','xh-xn');"
						style="width:80px">
						�� ��
					</button>
					</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" 
						style="width:80px" id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:present>
				<!-- ������ʾ��Ϣ -->
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>	
	</html:form>
</body>
