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
	refreshForm('pjpy_hxxy_jxjshone.do?act=save');document.getElementById('btn_save').disabled=true;
		}
</script>
<body onload="checkWinType();">
	<html:form action="/pjpyynyswh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		��ǰ����λ�ã��������� - ��� - ��ѧ�����
       		</div>
    	</div>
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }"/>
			<input type="hidden" id="xxsh" name="xxsh" value="${rs.xxsh}"/>
			<input type="hidden" id="userType" name="userType" value="${userType}"/>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							�������
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right" width="25%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" width="25%">
						<html:text name='rs' property="xh"
								styleId="xh" readonly="true"/>
					</td>
					<td align="right" width="25%">
						ѧ�꣺
					</td>
					<td align="left" width="25%">
						<bean:write name="rs" property="xn"/>
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
						��ѧ��:
					</td>
					<td align="left">
						<bean:write name="rs" property="jxjmc"/>
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
						ƽ��ѧ�֣�
					</td>
					<td align="left">
						${rs.xf }
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
						ƽ��ѧ��������
					</td>
					<td align="left">
						${rs.xfpm }
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
						�ۺ����ʲ����ܷ֣�
					</td>
					<td align="left">
					${rs.zf }	
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
						�ܷ�������
					</td>
					<td align="left">
						${rs.zfpm }
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
							������ͳɼ���
						</td>
						<td align="left">
							${rs.zdcj }
						</td>
				</tr>
				<tr>
						<td align="right">
							�����ɼ���
						</td>
						<td align="left">
							${rs.dcj }
						</td>
						<td align="right">
							�����ӷ֣�
						</td>
						<td align="left">
							${rs.jlf }
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
						<td align="right">
							���ʽ���������
						</td>
						<td align="left">
							${rs.tzjkbzdj }
						</td>
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
						<html:textarea  property="yj" value="${rs.yj }" styleClass="inputtext"
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
						<html:textarea name="rs" property="xyshyj" styleClass="inputtext"
						rows="4" styleId="xyshyj" style="width:98%" disabled="true"></html:textarea>
					</td>
				</tr>
				<tr> 
					<td colspan="" align="right">
						<bean:message key="lable.xsgzyxpzxy" />��������ѧ��<br/>����ίԱ�������
					</td>
					<td colspan="3" align="left">
						<html:textarea property="yj" styleClass="inputtext"
						rows="4" styleId="yj" style="width:98%" value="${rs.yj }" ></html:textarea>
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