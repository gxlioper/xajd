<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
	</head>
	<body>	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ������� - ����λ������</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/acws_DormDis" method="post">
		    <input type="hidden" id="nj" name="nj"
							value="<bean:write name="nj" scope="request"/>" />
			 <input type="hidden" id="addordel" name="addordel"
							value="<bean:write name="addordel" scope="request"/>" />	
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span> 
							 <logic:equal value="add" name="addordel"><font color="bule">��λ����</font></logic:equal>
			      			 <logic:equal value="del" name="addordel"><font color="bule">��λ�ͷ�</font></logic:equal>
			      			 </span>
						</th>
					</tr>
				</thead>		
				<tbody>			
					<tr>
						<td colspan="4" align="center">
						    <br/>
							 ѡ&nbsp;��&nbsp;��&nbsp;λ&nbsp;��:
							 <html:select property="cws" style="width:70px" styleId="cws">
							  <html:options collection="cwsList" labelProperty="cn" property="en"/>
							 </html:select>
							 <p>
							 <p>
						</td>
					</tr>	
				</tbody>
				<tfoot>
					<tr bgcolor="EEF4F9" align="center">
						<td colspan="4">
							<div class="btn">
									<button id="buttonSave" 
										onclick="AcwsFpSend();return false;"
										style="width: 80px">
										ȷ  ��
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
		</html:form>		
  </body>
</html>

