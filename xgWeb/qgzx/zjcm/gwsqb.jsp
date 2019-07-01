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
		<title><bean:message key="lable.title" /></title>
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
	<script language="javascript" src="js/qgzxFunction.js"></script>

	<body>
		<html:form action="/data_search" method="post">
		<h3 align="center">�㽭��ý<bean:message key="lable.xsgzyxpzxy" />�ڹ���ѧ��λӦƸ��</h3>
			<table width="798" height="795" class="tbstyle" align="center">			
				<tr>
					<td width="97" height="36">
						<div align="center">
							����
						</div>
					</td>
					<td width="90">
						${rs.xm}
					</td>
					<td width="110">
						<div align="center">
							�Ա�
						</div>
					</td>
					<td width="87">
						${rs.xb}
					</td>
					<td width="80">
						<div align="center">
							����
						</div>
					</td>
					<td width="147">
						${rs.mzmc}
					</td>
					<td width="141" rowspan="5">
						<div align="center">
							��Ƭ
						</div>
					</td>
				</tr>
				<tr>
					<td height="29">
						<div align="center">
							<bean:message key="lable.xsgzyxpzxy" />
						</div>
					</td>
					<td>
						${rs.xymc}
					</td>
					<td>
						<div align="center">
							�༶
						</div>
					</td>
					<td>
						${rs.bjmc}
					</td>
					<td>
						<div align="center">
							������ò
						</div>
					</td>
					<td>
						${rs.zzmmmc}
					</td>
				</tr>
				<tr>
					<td height="32">
						<div align="center">
							ѧ��
						</div>
					</td>
					<td>
						${rs.xh}
					</td>
					<td>
						<div align="center">
							���Һ�
						</div>
					</td>
					<td>
						${rs.ldmc}${rs.qsh}
					</td>
					<td>
						<div align="center">
							�绰
						</div>
					</td>
					<td>
						${rs.lxdh}
					</td>
				</tr>
				<tr>
					<td height="36">
						<div align="center">
							�����ˮƽ
						</div>
					</td>
					<td>
						${rs.jsjsp}
					</td>
					<td>
						<div align="center">
							�����س�
						</div>
					</td>
					<td colspan="3">
						${rs.yhtc}
					</td>
				</tr>
				<tr>
					<td>
						<p align="center">
							�����λ
						<br/>
							(���ţ���λ)
						</p>
					</td>
					<td colspan="5" style="height:20px">
						
							һ��${rs.yrdwmc}
						<br/>
						
							����${rs.gwdm}
						
						<div align="right">
							�����Ƿ���ӷ���
							<html:checkbox property="sffcfp" name="rs" value="1"></html:checkbox>
						</div>
					</td>
				</tr>
				<tr>
					<td height="122">
						<p align="center">
							�������
						</p>
						<p align="center">
							(����ҳ)
						</p>
					</td>
					<td colspan="6">
						${rs.mqqgzxqk}
					</td>
				</tr>
				<tr>
					<td>
						<p>
							&nbsp;
						</p>
						<p align="center">
							��
						</p>
						<p align="center">
							ʱ
						</p>
						<p align="center">
							��
						</p>
						<p align="center">
							��
						</p>
						<p align="center">
							��
						</p>
						<p align="center">
							��
						</p>
						<p>
							&nbsp;
						</p>
					</td>
					<td colspan="3">
						<logic:present name="kxList">
								<table border="0" cellpadding="0" cellspacing="0" align="center">
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
									<logic:iterate id="kxsj" name="kxList">
										<tr>
											<td>
												${kxsj.sj}
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}1"
													value="${kxsj.mon}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}2"
													value="${kxsj.tue}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}3"
													value="${kxsj.wed}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}4"
													value="${kxsj.thu}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}5"
													value="${kxsj.fri}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}6"
													value="${kxsj.sat}" />
											</td>
											<td align="center">
												<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
												<input type="hidden" name="index${kxsj.sjIndex}7"
													value="${kxsj.sun}" />
											</td>

										</tr>
									</logic:iterate>
								</table>
								<script language="javascript">
										for(var i=0;i<8;i++){
											for(var j=1;j<8;j++){
											 	if(document.getElementById("index"+i+j)){
											 		if(document.getElementById("index"+i+j).value==1){
														document.getElementById(i+""+j).checked="checked";
												    }
											 	}
											}
										}
									</script>							
						</logic:present>
						<logic:present name="kxbz">
							<input type="hidden" name="sfwh" value="sfwh" />
								<table border="0" cellpadding="0" cellspacing="0" align="center"
									class="tbstyle">
									<tbody id="tbSj">
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
													<input type="checkbox" name="${kxsj.sjIndex}1" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}2" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}3" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}4" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}5" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}6" value="1" />
												</td>
												<td align="center">
													<input type="checkbox" name="${kxsj.sjIndex}7" value="1" />
												</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
						</logic:present>
					</td>
					<td colspan="3">
						<p>
							<bean:message key="lable.xsgzyxpzxy" />�����
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p align="right">
							ǩ�֣� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;${year}�� 
							&nbsp;&nbsp;&nbsp;&nbsp;${month}��
							&nbsp;&nbsp;&nbsp;&nbsp;${day} ��
						</p>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<p>
							�ù����������
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p align="right">
							ǩ�ָ��£�&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							&nbsp;&nbsp;&nbsp;&nbsp;${year}�� 
							&nbsp;&nbsp;&nbsp;&nbsp;${month}��
							&nbsp;&nbsp;&nbsp;&nbsp; ${day}��
						</p>
					</td>
					<td colspan="3">
						<p>
							ѧ���������
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p>
							&nbsp;
						</p>
						<p align="right">
							ǩ�ָ��£�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							 ${year}��&nbsp;&nbsp;&nbsp;&nbsp; ${month}��&nbsp;&nbsp;&nbsp;&nbsp;${day} ��
						</p>
					</td>
				</tr>
				<tr>
					<td height="51">
						��ע��
					</td>
					<td colspan="6">
						<p>
							1��ѧ���֡���λ������볡�μ���Ƹ�ᣬ�ɶ��λӦƸ����¼��ԭ��Ϊһ��һ�ڡ�
						</p>
						<p>
							2������������������ѧ����Уѧ����ѧ�������칫�ҷ��ŵġ��ڹ���ѧ��Ƹ���̿�����������˲�������¼�á�
						</p>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
