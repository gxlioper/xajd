<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='js/xgutil.js'></script>
		<script type='text/javascript' src='js/xszz/xszztjbbInit.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/xszzComm.js'></script>
	</head>
	<body onload="disabledElement()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/commXszz">
			<!-- ������ -->
			<%@ include file="/xszz/hiddenValue.jsp"%>
			<input type="hidden" name="btzd" id="btzd" value="tjbbdm"/>
			<!-- ������ end-->
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">
			<table class="formlist" border="0" align="center" style="width: 50%">
				<thead>
					<tr>
						<th colspan="2">
							<span>ͳ�Ʊ���</span>
						</th>
					</tr>
				</thead>
				<tr>
					<th align="right">
					 	<font color="red">*</font>�������ͣ�
					</th>
					<td>
						<html:select property="tjbbdm" onchange="changeBb(this.value)" styleId="tjbbdm">
							<html:options collection="tjbbList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				<tr id="xnTr" style="height: 23px">
					<th align="right">
						ѧ�꣺
					</th>
					<td align="left" width="">
						<html:select property="xn" style="" styleClass="select" styleId="xn">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr id="ndTr" style="height: 23px">
					<th align="right" >
						��ȣ�
					</th>
					<td align="left" width="">
						<html:select property="nd" style="" styleClass="select" styleId="nd">
							<html:options collection="ndList" property="nd" labelProperty="nd" />
						</html:select>
					</td>
				</tr>
				<tr id="xqTr">
					<th align="right" width="">
						ѧ�ڣ�
					</th>
					<td align="left" width="">
						<html:select property="xq" style="" styleClass="select" styleId="xq">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>				
				<tr id="njTr" style="display:none">
					<th align="right" width="">
						�꼶��
					</th>
					<td align="left">
						<html:select property="nj" styleId="nj" onchange="initZyList();initBjList()">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
						</html:select>
					</td>
				</tr>
				<tr id="xyTr">
					<th align="right" width="">
						<bean:message key="lable.xsgzyxpzxy" />��
					</th>
					<td align="left">
						<logic:equal name="userType" value="xy">
							<html:hidden property="xydm"/>
							<html:select property="xydm" styleClass="select" style="" styleId="xy" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
						</logic:equal>
						<logic:notEqual name="userType" value="xy">
							<html:select property="xydm" onchange="initZyList();initBjList()" styleClass="select" style="" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr id="zyTr">
					<th align="right">
					 	רҵ��
					</th>
					<td>
						<html:select property="zydm" style="" styleId="zy" onchange="initBjList()">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr id="bjTr" >
					<th align="right" width="">
						�༶��
					</th>
					<td align="left">
						<html:select property="bjdm" style="" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr id="xhTr" >
					<th align="right" width="">
						ѧ�ţ�
					</th>
					<td align="left">
						<html:text property="xh" styleId="xh" style="" maxlength="20"/>
					</td>
				</tr>
				<tr id="xmTr" >
					<th align="right" width="">
						������
					</th>
					<td align="left">
						<html:text property="xm" styleId="xm" style="" maxlength="20"/>
					</td>
				</tr>
				<tfoot>
				<tr bgcolor="EEF4F9" align="center">
					<td colspan="2">
						<div class="btn">
							<button type="button" id="buttonSave" onclick="printBb()">
								��ӡ
							</button>
							
							<logic:equal value="10058" name="xxdm">
								<button type="button" onclick="showOpenWindow('commXszz.do?method=dowDqfbMb',400,200);">
									����
								</button>
							</logic:equal>
						</div>
					</td>
				</tr>
				</tfoot>
			</table>
			</logic:empty>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
	</body>
</html>
