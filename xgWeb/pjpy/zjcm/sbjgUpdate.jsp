<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">

	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
	<script type="text/javascript">	

	</script>
	
	<body onload="">
		<html:form action="/zjcmPjpy">
			<input type="hidden" id="sbznum" name=sbznum value="${sbznum}"/>
			<input type="hidden" id="xhnum" name=xhnum value="${xhnum}"/>
			<logic:present name="sbzxh">
			<logic:iterate name="sbzxh" id="num" indexId="index">
				<input type="hidden" id="sbzxh${index }" name="sbzxh" value="${num}"/>
			</logic:iterate>
			</logic:present>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�<bean:write name="title" />
				</div>
			</div>
			<table class="tbstyle" border="0" id="rsTable" align="center"
				style="width: 100%">
				<thead>
					<tr>
						<td colspan="4" align="center">
							��ϸ��Ϣ
						</td>
					</tr>
				</thead>
					<tr>
						<td align="right" width="20%">
							ѧ�ţ�
						</td>
						<td align="left" width="30%">
							<html:text name="rs" property="xh" style="" readonly="true"/>
						</td>
						<td align="right" width="20%">
							����ѧ�꣺
						</td>
						<td align="left" width="30%">
							<html:select name="rs" property="xn" style="" onchange="" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>			
						</td>
					</tr>
					<tr>
						<td align="right">
							������
						</td>
						<td align="left">
							<html:text name="rs" property="xm" style="" readonly="true"/>
						</td>
						<td align="right">
							����ѧ�ڣ�
						</td>
						<td align="left">
							<html:select name="rs" property="xq" style="" onchange="" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<html:select name="rs" property="nj" style="" styleId="nj" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj" />
							</html:select>
						</td>
						<logic:equal name="lx" value="jxj">
							<td align="right">
								�����뽱ѧ��
							</td>
							<td align="left">
								<html:hidden name="rs" property="jxjdm"/>
								<html:select name="rs" property="jxjdm" style="" onchange="" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</logic:equal>
						<logic:equal name="lx" value="rych">
							<td align="right">
								�����������ƺţ�
							</td>
							<td align="left">
								<html:select name="rs" property="rychdm" style="" onchange="" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="rychList" property="dm" labelProperty="mc" />
								</html:select>
							</td>
						</logic:equal>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<bean:write name="rs" property="xymc"/>
						</td>
						<td align="right">
							����У��ͨ����
						</td>
						<td align="left">
							<bean:write name="rs" property="xjtb"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<bean:write name="rs" property="zymc"/>
						</td>
						<td align="right">
							���������
						</td>
						<td align="left">
							<bean:write name="rs" property="wjInfo"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<bean:write name="rs" property="bjmc"/>
						</td>
						<td align="right">
							���ν�����
						</td>
						<td align="left">
							<bean:write name="rs" property="kkjs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							������Ƿѧ�ѣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="tqxf"/>
						</td>
						<td align="right">
							ѧϰ�ɼ�������������
						</td>
						<td align="left">
							<bean:write name="rs" property="bjgs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							Ӣ��ȼ����Գɼ���
						</td>
						<td align="left">
							<bean:write name="rs" property="yycj"/>
						</td>
						<td align="right">
							������ȼ����Գɼ���
						</td>
						<td align="left">
							<bean:write name="rs" property="pccj"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							�����֣�
						</td>
						<td align="left">
							<bean:write name="rs" property="dyf"/>(<bean:write name="rs" property="dyzhf"/>)
						</td>
						<td align="right">
							�༶������
						</td>
						<td align="left">
							<bean:write name="rs" property="dypm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							�����֣�
						</td>
						<td align="left">
							<bean:write name="rs" property="zyf"/>(<bean:write name="rs" property="zyzhf"/>)
						</td>
						<td align="right">
							�༶������
						</td>
						<td align="left">
							<bean:write name="rs" property="zypm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							�����֣�
						</td>
						<td align="left">
							<bean:write name="rs" property="tyf"/>(<bean:write name="rs" property="tyzhf"/>)
						</td>
						<td align="right">
							�༶������
						</td>
						<td align="left">
							<bean:write name="rs" property="typm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							�����֣�
						</td>
						<td align="left">
							<bean:write name="rs" property="nlf"/>(<bean:write name="rs" property="nlzhf"/>)
						</td>
						<td align="right">
							�༶������
						</td>
						<td align="left">
							<bean:write name="rs" property="nlpm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							�ۺϷ֣�
						</td>
						<td align="left">
							<bean:write name="rs" property="zhf"/>
						</td>
						<td align="right">
							�༶������
						</td>
						<td align="left">
							<bean:write name="rs" property="zhpm"/>
						</td>
					</tr>
					<logic:equal name="lx" value="jxj">
						<tr>
							<td align="right" style="">
								����Ա��������
							</td>
							<td align="left" colspan="3">
								<html:textarea  name="stuInfo" property="fdyyj" style="width:100%;height: 70px" readonly="true"></html:textarea>
							</td>
						</tr>
					</logic:equal>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��������
						</td>
						<td align="left" colspan="3">
							<html:textarea  name="stuInfo" property="xyyj" style="width:100%;height: 70px" readonly="true"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							ѧУ��������
						</td>
						<td align="left" colspan="3">
							<html:textarea  name="stuInfo" property="xxyj" style="width:100%;height: 70px" readonly="true"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right">
							��ע��
						</td>
						<td align="left" colspan="3">
							<html:textarea name="stuInfo" property="bz" style="width:100%;height: 70px" readonly="true"></html:textarea>
						</td>
					</tr>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="4">
						<button class="button2" id="buttonClose" onclick="Close();return false;"
							style="width: 80px">
							��	��
						</button>
					</td>
				</tr>
			</table>
			
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
