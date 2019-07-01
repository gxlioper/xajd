<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"  src="js/sztzFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script language="javascript"  src="js/gygl/gyglTyFunction.js"></script>
	</head>

	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zjcmGygl">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			<!-- ���������� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>����������</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="height: 23px">
						<th align="right" width="20%">
							У����
						</th>
						<td align="left" width="30%">
							<bean:write name="rs" property="xqmc"/>
						</td>
						<th align="right" width="20%">
							¥����
						</th>
						<td align="left">
							<bean:write name="rs" property="ldmc"/>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							����������
						</th>
						<td align="left">
							��<bean:write name="rs" property="cs"/>��
						<th align="right">
							���Һţ�
						</th>
						<td align="left">
							<bean:write name="rs" property="qsh"/>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							ѧ�꣺
						</th>
						<td align="left">
							<html:hidden name='rs' property="xn" styleId="xn"/>
							<html:select name="rs" property="xn" style="" styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>
						</td>
						<th align="right">
							ѧ�ڣ�
						</th>
						<td align="left">
							<html:hidden name='rs' property="xq" styleId="xq"/>
							<html:select name="rs" property="xq" style="" styleId="xq" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							���ʱ�䣺
						</th>
						<td align="left">
							<bean:write name="rs" property="jcsj"/>
						</td>
						<th align="right">
							<font color="red">*</font>�������֣�
						</th>
						<td align="left">
							<html:text name='rs' property="fs" styleId="fs" onblur="chMax(this,100)"
								onkeypress="return onlyNum(this,5)"
								maxlength="3" style="width:100%;ime-mode:disabled"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<logic:notPresent name="rsList">
								���������˾�ס
							</logic:notPresent>
							<logic:present name="rsList">
							<fieldset>
								<legend>
									�����Ҿ�ס������ <bean:write name="rsNum" />��
								</legend>
								<table width="100%" id="rsTable" class="tbstyle">
									<thead>	
										<tr align="center" style="cursor:hand">
											<logic:iterate id="tit" name="topTr" offset="0">
												<td id="<bean:write name="tit" property="en"/>" nowrap>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="rsList" id="s" indexId="index">
										<tr>
											<logic:iterate id="v" name="s">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</fieldset>
							</logic:present>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
							<div class="btn">
								<logic:notEqual name="doType" value="view">
									<button id="buttonSave" onclick="saveUpdate('/xgxt/zjcmGygl.do?method=wsjcView&doType=save','fs')"
										style="width: 80px">
										��	��
									</button>
								</logic:notEqual>
								&nbsp;&nbsp;
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									��	��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
