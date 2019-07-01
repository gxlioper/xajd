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
	
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xsfwzdzx.js'></script>
	<script>
		function save(){
			refreshForm('bylfgl.do?method=saveBylfsqsh');
		}
	</script>
	
	<base target="_self">
	<body>
		<html:form action="/bylfgl.do">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ճ����� - ѧ��ָ���������� - ��ҵ�����װ���� - ���
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								<bean:message key="lable.xsgzyxpzxy" />��ҵ���������Ϣ
							</td>
						</tr>
					</thead>
					
					<tr>
						<td style="width:40%">
							<div align="right">
								��ȣ�
							</div>
						</td>
						<td>
							${rs.nd}
							<html:hidden property="nd" name="rs"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td>				
							${rs.xymc}
							<html:hidden property="xydm" name="rs"/>
						</td>						
					</tr>					
					<tr>
						<td align="right">
							��ȡ�ˣ�
						</td>
						<td>
							${rs.lqr}
						</td>					
					</tr>
					<tr>
						<td align="right">
							���Ʒ���
						</td>
						<td>
							<b>xl</b>:${rs.bkfxl}
							<b>l</b>:${rs.bkfl}
							<b>m</b>:${rs.bkfm}
							<b>s</b>:${rs.bkfs}
						</td>						
					</tr>
					<tr>
						<td align="right">
							ר�Ʒ���
						</td>
						<td>
							<b>xl</b>:${rs.zkfxl}
							<b>l</b>:${rs.zkfl}
							<b>m</b>:${rs.zkfm}
							<b>s</b>:${rs.zkfs}
						</td>						
					</tr>
					<tr>
						<td align="right">
							У������
						</td>
						<td>
							<b>xl</b>:${rs.xzfxl}
							<b>l</b>:${rs.xzfl}
							<b>m</b>:${rs.xzfm}
							<b>s</b>:${rs.xzfs}
						</td>						
					</tr>
					<tr>
						<td align="right">
							��ʦ����
						</td>
						<td>
							<b>xl</b>:${rs.dsfxl}
							<b>l</b>:${rs.dsfl}
							<b>m</b>:${rs.dsfm}
							<b>s</b>:${rs.dsfs}
						</td>						
					</tr>
					<tr>
						<td align="right">
							ñ�ӣ�
						</td>
						<td>
							${rs.maozi}
						</td>						
					</tr>
					<tr>
						<td align="right">
							���磺
						</td>
						<td>
							${rs.pijian}
						</td>						
					</tr>
					<tr>
						<td align="right">
							�����
						</td>
						<td>
							${rs.lingdai}
						</td>						
					</tr>
					<tr>
						<td align="right">
							��᣺
						</td>
						<td>
							${rs.lingjie}
						</td>						
					</tr>
					<tr>
						<td align="right">
							��˽����
						</td>
						<td>
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
							onclick="save();return false;"
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
					if(window.dialogArguments){
						window.dialogArguments.document.getElementById('search_go').click();
					}		
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
