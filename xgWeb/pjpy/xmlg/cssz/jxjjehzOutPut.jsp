<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<body onload="">
	<html:form action="/xmlgpjpy" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		��ǰλ��:�������� - �������� - ���鿴
       		</div>
    	</div>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="${num }" align="center">
						<b>	${xn }ѧ���<bean:message key="lable.xsgzyxpzxy" />��ѧ����������ܱ�</b>
						</td>
					</tr>
				</thead>
				<!-- �����ͷ -->
				<logic:notEmpty name="title">
					<logic:iterate name="title" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;" align="center">
								<logic:iterate id="v" name="s" >
									<td>
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
				</logic:notEmpty>
				<!-- ������ -->
				<logic:notEmpty name="rs">
					<logic:iterate name="rs" id="oneRs">
							<tr onclick="rowOnClick(this)" style="cursor:hand;" align="center">
								<logic:iterate id="oneData" name="oneRs" >
									<td>
										${oneData }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
				</logic:notEmpty>
			</table>
			<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>	
	</html:form>
</body>