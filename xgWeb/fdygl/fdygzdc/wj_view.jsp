<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/fdyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	<body onload="loadTestSelectItem();">
		<html:form action="/wj_view" method="post">
			<input type="hidden" id="xxStr" name="xxStr" value="<bean:write name="xxStr"/>" />	
			<input type="hidden" id="stLen" name="stLen" value="<bean:write name="stLen"/>" />	
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>����Ա���� - ��Ϣά�� - ����Ա���������ʾ�ά�� - �ʾ�Ԥ��</a>
				</p>
			</div>
			<logic:empty name="stList">
			<div align="center"><font color="red"><br/><br/><br/>��������!</font></div>
			</logic:empty>
			<logic:notEmpty name="stList">
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">

						<tr>
							<td align="center">
								<B><font size="5"> ����Ա�������ѧ���ʾ� </font> </B>
								<div align="right">
								<bean:write name="fdyglForm" property="xn" />
								ѧ�� (
								<bean:write name="fdyglForm" property="nd" />
								���)�� ��
								<bean:write name="fdyglForm" property="xq" />
								ѧ�� 
								</div>
							</td>
						</tr>						
						<tr>
							<td align="center">
								<bean:message key="lable.xsgzyxpzxy" />___________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								�Ա�___________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								�꼶___________&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td align="left">
								<B>ͬѧ���ã�<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ϊ���˽⸨��Ա����״�������ǽ�����γ������飬�������ݾ�����ͳ���о����밴ʵ���������ʵ�뷨�ش����⡣��л���Ա��ε���Ĵ���֧�֡�ף��ѧϰ������<br>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									ѧ��������������</B>
							</td>
						</tr>
						<tr>
							<td align="left">
								<logic:notEmpty name="stList">
									<table width="100%" class="tbstyle" id="tp">
										<logic:iterate id="stList" name="stList">
											<tr>
												<td align="left"
													id="<bean:write name="stList" property="id"/>" width="100%">
													<B><bean:write name="stList" property="id" />
													.</B>&nbsp;
													<bean:write name="stList" property="stmc" />
													(&nbsp; )
												</td>
											</tr>
										</logic:iterate>
									</table>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;�ʾ��˽������ٴθ�л������������Ϻ�����
							</td>
						</tr>
					</table>
						<div class="buttontool" id="btn" align="center">
						<button onclick="expTab('rsT','','')">
							��ӡ 
						</button>
						</div>			
				</div>
			</logic:notEmpty>
		</html:form>		
	</body>
</html>
