<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
<body onload="checkWinType();" onunload="ableAllSel('xn-nd')">
		<html:form action="/xySetStuNum.do?act=save" method="post">
			<br />
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				
				<center>
					<div id="repTit">
						${jxjsqxn }
						ѧ�꣨
						${jxjsqnd }
						��ȣ���ѧ���������һ����
					</div>
				</center>
				<div class="tab">
					<table width="98%" id="rsTable" class="formlist">
						<thead>
							<tr align="left" style="cursor:hand">
								<th >
									���Ŵ���
								</th>
								<th>
									��������
								</th>
								<th >
									�꼶
								</th>
								<th >
									��������(<bean:message key="lable.xsgzyxpzxy" />ʵ������)
								</th>
							</tr>
						</thead>
						<tbody>
						<logic:iterate id="rs" name="rs">
							<tr align="left" onclick="rowOnClick(this)" style="cursor:hand">
								<td>
									<bean:write name="rs" property="bmdm" />
								</td>
								<td>
									<bean:write name="rs" property="mc" />
								</td>
								<td>
									<bean:write name="rs" property="nj" />
								</td>
								<td>
									<bean:write name="rs" property="cprs" />
								</td>
							</tr>
						</logic:iterate>
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="4">
					          <div class="btn">					            
<%--								<button type="button" class="button2" onclick="expTab('rsTable','','repTit')">--%>
<%--									���ɱ���--%>
<%--								</button>--%>
<%--								&nbsp;&nbsp;&nbsp;&nbsp;--%>
								<button type="button" class="button2" onclick="Close();return false;">
									�ر�
								</button>
					          </div>
					        </td>
					      </tr>
					    </tfoot>
					</table>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
