<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script>
	function saves() {
	var type = document.getElementById('userType').value;
	var xxsh = document.getElementById('xxsh').value;
	if ('xy'==type) {
		if ('ͨ��'==xxsh) {
			alert('����ز��������ͨ��,�����ٲ���!');
			return;
		}
	}
	refreshForm('pjpy_hxxy_rychshone.do?act=save');document.getElementById('btn_save').disabled=true;
		}
</script>
<body onload="checkWinType();">
	<html:form action="/pjpyynyswh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		��ǰ����λ�ã��������� - ��� - �����ƺ����
       		</div>
    	</div>
    		<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }"/>
			<input type="hidden" id="xxsh" name="xxsh" value="${rs.xxsh }"/>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							�������
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
						<tr style="height:22px">
								<td align="right">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td align="left">
									<html:text name='rs' property="xh" styleId="xh" readonly="true" />
								</td>
							<td align="right">
								��ȣ�
							</td>
							<td align="left">
								<html:text name='rs' property="nd" readonly="true"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								������
							</td>
							<td align="left">
								<bean:write name='rs' property="xm" />
							</td>
							<td align="right">
								ѧ�꣺
							</td>
							<td align="left">
								<html:text name='rs' property="xn" readonly="true"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<bean:write name='rs' property="xb" />
							</td>
							<td align="right">
								<font color="red">*</font>���������ƺţ�
							</td>
							<td align="left">
								<bean:write name='rs' property="rychmc" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<bean:write name='rs' property="nj" />
							</td>
							<td align="right">
									������ò��
								</td>
								<td align="left">
									<bean:write name='rs' property="zzmmmc" />
								</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<bean:write name='rs' property="xymc" />
							</td>
							<td align="right">
								���壺
							</td>
							<td align="left">
								<bean:write name='rs' property="mzmc" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<bean:write name='rs' property="zymc" />
							</td>
							<td align="right">
								����ˮƽ��
							</td>
							<td align="left">
								<bean:write name='rs' property="wydj" />
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<bean:write name='rs' property="bjmc" />
							</td>
							<td align="right">
									�������£�
								</td>
								<td>
									<bean:write name='rs' property="csrq" />
								</td>
						</tr>
						
							<tr>
								<td align="right">
									�绰��
								</td>
								<td align="left">
									<bean:write name='rs' property="lxdh" />
								</td>
								<td align="right">
									��ѧʱ�䣺
								</td>
								<td>
									<bean:write name='rs' property="rxrq" />
								</td>
							</tr>
						<tr style="height:22px">
							<td align="right">
								��Ҫ�¼���
								<br/>
							<span class="style1">(������800����)</span>
							</td>
							<td colspan="3" align="left">
								${rs.zysj }
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								������Ṥ�������
								<br/>
							<span class="style1">(������800����)</span>
							</td>
							<td colspan="3" align="left">
								${rs.drshgzqk }
							</td>
						</tr>
				<tr>
						<td align="right">
							��ˣ�
						</td>
						<td align="left">
							<html:select property="yesNo" styleId="yesNo" value="${rs.sh }">
								<html:option value=""></html:option>
								<html:option value="ͨ��">ͨ��</html:option>
								<html:option value="��ͨ��">��ͨ��</html:option>
								<html:option value="δ���">δ���</html:option>
							</html:select>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
				</tr>
				<logic:equal value="xy" name="userType">
				<tr> 
					<td colspan="" align="right">
						����Ա�����
					</td>
					<td colspan="3" align="left">
						<html:textarea  property="fdyyj" value="${rs.fdyyj }" styleClass="inputtext"
						rows="4" styleId="fdyyj" style="width:98%"></html:textarea>
					</td>
				</tr>
				<tr> 
					<td colspan="" align="right">
						ϵ�Ƽ������
					</td>
					<td colspan="3" align="left">
						<html:textarea  property="yj" value="${rs.xyyj }" styleClass="inputtext"
						rows="4" styleId="yj" style="width:98%"></html:textarea>
					</td>
				</tr>
				</logic:equal>
				<logic:notEqual value="xy" name="userType">
				<tr> 
					<td colspan="" align="right">
						����Ա�����
					</td>
					<td colspan="3" align="left">
						<html:textarea name="rs" property="fdyyj" styleClass="inputtext"
						rows="4" styleId="fdyyj" style="width:98%" disabled="true"></html:textarea>
					</td>
				</tr>
				<tr> 
					<td colspan="" align="right">
						ϵ�Ƽ������
					</td>
					<td colspan="3" align="left">
						<html:textarea name="rs" property="xyyj" styleClass="inputtext"
						rows="4" styleId="xyyj" style="width:98%" disabled="true"></html:textarea>
					</td>
				</tr>
				<tr> 
					<td colspan="" align="right">
						<bean:message key="lable.xsgzyxpzxy" />�����
					</td>
					<td colspan="3" align="left">
						<html:textarea property="xxyj" styleClass="inputtext"
						rows="4" styleId="xxyj" style="width:98%" value="${rs.xxyj }" ></html:textarea>
					</td>
				</tr>
				</logic:notEqual>
			</table>
			<div class="buttontool" align="center">
					<button class="button2" id="btn_save"
						onclick="saves()"
						style="width:80px"  >
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>