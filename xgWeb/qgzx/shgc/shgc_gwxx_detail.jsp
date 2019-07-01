<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<body>
		<html:form action="/data_search" method="post">
			<div class="title">
					<div class="title_img" id="title_m">
						��ǰλ�ã��ڹ���ѧ - ��λ��Ϣ��ѯ - ��λ��ϸ��Ϣ
					</div>
			</div>
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							��λ��ϸ��Ϣ
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td width="24%" align="right">
						��λ���ƣ�
					</td>
					<td width="20%" align="left">
						<bean:write name="rs" property="gwdm" />
					</td>
					<td width="31%" align="right">
						������λ��
					</td>
					<td width="25%" align="left">
						<bean:write name="rs" property="yrdwmc" />
					</td>
				</tr>

				<tr style="height:22px">
					<td align="right">
						��λ���ʣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="gwxzmc" />
					</td>
					<td align="right">
						У����
					</td>
					<td align="left">
						<bean:write name="rs" property="xq" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="rs" property="xn" />
					</td>
					<td align="right">
						������ʼʱ�䣺
					</td>
					<td align="left">
						<bean:write name="rs" property="gzksrq" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ȣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="nd" />
					</td>
					<td align="right">
						��������ʱ�䣺
					</td>
					<td align="left">
						<bean:write name="rs" property="gzjsrq" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��Ҫ������
					</td>
					<td align="left">
						<bean:write name="rs" property="xyrs" />
					</td>
					<td align="right">����ʹ��������
					</td>
					<td align="left">
						<bean:write name="rs" property="sqsyrs" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��Ҫ����������
					</td>
					<td align="left">
						<bean:write name="rs" property="syknss" />
					</td>
					<td align="right">����ʹ��������������
					</td>
					<td align="left">
						<bean:write name="rs" property="sqsyknss" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ƴ귽ʽ��
					</td>
					<td align="left">
						<bean:write name="rs" property="jcfsmc" />
					</td>
					<td align="right">�����Ƴ��׼��
					</td>
					<td>
						<bean:write name='rs' property="spbcbz" />
					</td>
				</tr>				
				<tr style="height:22px">				 	
			      <td align="left"><div align="right">��ϵ�绰��</div></td>
			      <td align="left" colspan="3"><bean:write name="rs" property="lxdh" /></td>
			  </tr>
			  <tr style="height:22px">
                  <td height="17" align="right"> �������ݣ�</td>
                  <td align="left" colspan="3"> <bean:write name="rs" property="gznr" /> </td>
			  </tr>
			  <tr style="height:22px">				 	
			      <td align="left"><div align="right">����ʱ�䣺</div></td>
			      <td align="left" colspan="3">
							<logic:present name="whkxList">
								<table id="tbSj">
									<tr>
										<td align="center">
											ʱ��
										</td>
										<td>
											����һ
										</td>
										<td>
											���ڶ�
										</td>
										<td>
											������
										</td>
										<td>
											������
										</td>
										<td>
											������
										</td>
										<td>
											������
										</td>
										<td>
											������
										</td>
									</tr>
									<logic:iterate id="kxsj" name="whkxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="��" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="��" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="��" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="��" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="��" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="��" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="��" />
											</td>
										</tr>
									</logic:iterate>
								</table>
								<!-- begin ��ȡ�Ѿ����ڵĿ���ʱ����Ϣ -->
								<logic:present name="kxbz">
									<input type="hidden" id="kxbzNum"
										value="<bean:write name="kxbzNum"/>" />
									<logic:iterate id="kxinfo" name="kxbz" indexId="index">
										<input type="hidden" id="kxinfo${index}"
											value="${kxinfo.xq}${kxinfo.sj}" />
									</logic:iterate>
									<script>
							var length = document.getElementById('kxbzNum').value;
							for(var i=0; i<parseInt(length); i++){
								var id =  document.getElementById("kxinfo"+i).value;
								document.getElementById(id).checked="checked";
							}
						</script>
								</logic:present>
								<!-- end ��ȡ�Ѿ����ڵĿ���ʱ����Ϣ -->
							</logic:present>
						</td>
			  </tr>
				<tr style="height:22px">
					<td height="17" align="right">
						<FONT color="red">������������</FONT></td>
					<td align="left">
						<FONT color="red"><bean:write name="rs" property="sqrs" /></FONT>
					</td>
				    <td align="left"><div align="right"><FONT color="red">��¼��������</FONT></div></td>
				    <td align="left">
				    	<FONT color="red"><bean:write name="rs" property="tgrs" /></FONT>
				    </td>
				</tr>				
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>					
		</html:form>
	</body>
</html>
