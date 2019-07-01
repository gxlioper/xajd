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
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Copyright" content="������� zfsoft">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>	
	<script>
		
	</script>
	<base target="_self">
	<body>
		<html:form action="/qgzxcjff.do">
			<input type="hidden" name="pkV" id="pkV" value="${pkV}">
			
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ڹ���ѧ - ��𷢷�- ��𷢷����
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								ѧ����𷢷���Ϣ
							</td>
						</tr>
					</thead>

					<tr>
						<td align="right">
							ѧ�ţ�
						</td>
						<td>
							${rs.xh}
						</td>
						<td>
							<div align="right">
								��λ��
							</div>
						</td>
						<td>
							${rs.gwdm}
						</td>
					</tr>
					<tr>
						<td align="right">
							������
						</td>
						<td>
							${rs.xm}
						</td>
						<td>
							<div align="right">
								��ȣ�
							</div>
						</td>
						<td>
							${rs.nd}
						</td>						
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td>
							${rs.xb}
						</td>
						<td align="right">
							�·ݣ�
						</td>
						<td>
							${rs.yf}
						</td>						
					</tr>
					<tr>
						<td align="right" nowrap="nowrap">
							�꼶��
						</td>
						<td>
							${rs.nj}
						</td>
						<td align="right" >
							����ʱ�䣺
						</td>
						<td> 
							${rs.gzsj}
						</td>						
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td>
							${rs.xymc}
						</td>
						<td align="right" nowrap="nowrap">
							����
						</td>
						<td>
							${rs.cjje}
						</td>						
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td>
							${rs.zymc}
						</td>
						<td align="right">
							�������ƣ�
						</td>
						<td>
							${rs.yhmc}
						</td>						
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td>
							${rs.bjmc}
						</td>
						<td align="right">
							���п��ţ�
						</td>
						<td>
							${rs.yhkh}
						</td>
					</tr>
					<tr>
						<td align="right">
							��ע��
						</td>
						<td colspan="3">
							${rs.bz}
						</td>
					</tr>
					<tr>
						<td align="right">
							��˽����
						</td>
						<td colspan="3">
							<html:select property="xxsh" name="rs">
								<html:option value="ͨ��">ͨ��</html:option>
								<html:option value="��ͨ��">��ͨ��</html:option>
								<html:option value="δ���">δ���</html:option>
							</html:select>
						</td>
					</tr>
				</table>
				<center>
					<div class="buttontool" id="btn">
						<button type="button" class="button2"
							onclick="refreshForm('qgzxcjff.do?method=xscjffAudiOne')"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.close();return false;"
							style="width:80px">
							�� ��
						</button>
					</div>
				</center>

			 <logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("�����ɹ���");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();		
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert("����ʧ�ܣ�");
						Close();
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
