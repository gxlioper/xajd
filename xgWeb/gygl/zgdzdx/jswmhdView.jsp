<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>		
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ������������ - ���������</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zgdzdx_Gygl" method="post">
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>      
			<logic:notEmpty name="rs">			 					 		
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							<span>���������</span>
						</td>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th height="22" width="15%" align="right">
						����ƣ�
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="hdmc" maxlength="40" readonly="true"></html:text>
					</td>
					<th height="22" align="right" width="15%">
						���쵥λ��
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="zbdw" maxlength="40" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right" width="20%">
						������������
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="fzrxm" readonly="true"></html:text>
					</td>
					<th height="22" align="right" width="20%">
						��������ϵ��ʽ��
					</th>
					<td height="22" align="left">
						<html:text  name="rs" property="fzrlxfs" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						������������
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="jsrxm" readonly="true"></html:text>
					</td>
					<th height="22" align="right">
						��������ϵ��ʽ��
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="jsrlxfs" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						��ص㣺
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="hddd" readonly="true"></html:text>
					</td>
					<th height="22" align="right">
						�μ�������
					</th>
					<td height="22" align="left">
						<html:text name="rs" property="cjrs" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th height="22" align="right">
						���ʼ���ڣ�
					</th>
					<td height="22" align="left">
							<html:text name="rs" property="hdksrq" styleId="hdksrq"
							 style="width:90px"	readonly="true" />(��)<html:text name="rs" property="hdkssj" styleId="hdkssj"
							 style="width:70px" readonly="true" />(ʱ)
					
					</td>
					<th height="22" align="right">
						��������ڣ�
					</th>
					<td height="22" align="left">
							<html:text name="rs" property="hdjsrq" styleId="hdjsrq"
							 style="width:90px"
							readonly="true" />(��)<html:text name="rs" property="hdjssj" styleId="hdjssj"
							 style="width:70px" readonly="true"/>(ʱ)
					
					</td>					
				</tr>
				
				<tr>
					<th height="22" align="right">
						����ݣ�
					</th>
					<td height="22" align="left" colspan="3">
						<html:textarea name="rs" property='hdnr'style="width:99%"
							rows='15' readonly="true" />
					</td>					
				</tr>
				</tbody>
				<tfoot>
				<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
									<button id="buttonSave" 
									onclick="jswmsqb()"
										style="width: 80px">
										������ӡ
									</button>
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
		</logic:notEmpty>											
		</html:form>		
	</body>

</html>
