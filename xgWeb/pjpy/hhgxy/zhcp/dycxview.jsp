<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
		<script type="text/javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<body onload="checkWinType();">
		<html:form action="/pjpyhhgxyzhwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ��: �������� - ��Ϣά�� - �������з�ά��
				</div>
			</div>
			<input type="hidden" name="pkValue" value="${pkValue }"/>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								�����޸�
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left">
							${rs.xh }
						</td>
						<td align="right">
							<font color="red">*</font>ѧ�꣺
						</td>
						<td align="left">
							<html:select property="xn" style="width:90px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							������
						</td>
						<td align="left">
							${rs.xm }
						</td>
						<td align="right">
							<font color="red">*</font>ѧ�ڣ�
						</td>
						<td align="left">
							<html:select property="xq" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							${rs.xb }
						</td>
						<td align="right">
							<font color="red">*</font>��Ŀ��
						</td>
						<td align="left">
							<html:select property="dm" id="dm">
								<html:option value=""></html:option>
								<html:options collection="cxList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							${rs.nj}
						</td>
						<td align="right">
							�÷֣�
						</td>
						<td align="left">
							<html:text property="df" styleId="df" maxlength="6" onblur="ckinpdata(this)"></html:text>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							${rs.xymc }
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
					</tr><tr>
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							${rs.zymc }
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							${rs.bjmc }
						</td>
						<td align="right">
							&nbsp;
						</td>
						<td align="left">
							&nbsp;
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
						<logic:notEqual value="view" name="yes">
							<button type="button" class="button2"
							onclick="saveinfo('hhgxy_dycxfview.do','jxjdm');"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEqual>
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
	<logic:present name="inserted">
		<logic:equal value="yes" name="inserted">
			<script>
				alert("�����ɹ���");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="no" name="inserted">
			<script>
				alert("����ʧ�ܣ�");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
	</logic:present>
