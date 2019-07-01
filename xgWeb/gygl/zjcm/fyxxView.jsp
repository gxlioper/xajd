<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"  src="js/sztzFunction.js"></script>
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
		
			<!-- ��Դ����Ϣ -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>��Դ����Ϣ</span>
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
							�����ǣ�
						</th>
						<td align="left">
							<bean:write name="rs" property="fpbj"/>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							¥����
						</th>
						<td align="left">
							<bean:write name="rs" property="ldmc"/>
						</td>
						<th align="right">
							��λ����
						</th>
						<td align="left">
							<bean:write name="rs" property="cws"/>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							����������
						</th>
						<td align="left">
							��<bean:write name="rs" property="cs"/>��
						<th align="right">
							���ҵ绰��
						</th>
						<td align="left">
							<bean:write name="rs" property="qsdh"/>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							���Һţ�
						</th>
						<td align="left">
							<bean:write name="rs" property="qsh"/>
						</td>
						<th align="right">
							�շѱ�׼��
						</th>
						<td align="left">
							<bean:write name="rs" property="sfbz"/>
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
						<td colspan="4">
							<div class="btn">
								<button id="buttonClose" onclick="Close();return false;"
									style="width: 80px">
									��	��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
