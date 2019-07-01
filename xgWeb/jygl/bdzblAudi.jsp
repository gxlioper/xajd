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
	<script>
		function save(){
			var pkValue = val('xh');
			if(filedNotNull('xh-bdzlx-bdzkwdwmc-dajwdwmc','-')){
				refreshForm('bdzbl.do?method=saveBdzblsq');
			} else {
				alert ('�뽫��\*�ŵ���Ŀ��д������');
				return false;
			}
		}
	</script>
	<base target="_self">
	<body>
		<html:form action="/bdzbl.do">
			<input type="hidden" name="url" id="url" value="/bdzbl.do?method=bdzblsq">
			<input type="hidden" value="xh-xm-xb-nj-xymc-zymc-bjmc" id="getStuInfo" name="getStuInfo" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ����֤������� - ����֤�������
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								����֤����������Ϣ
							</td>
						</tr>
					</thead>

					<tr>
						<td align="right">
							ѧ�ţ�
						</td>
						<td>
							${rs.xh}
							<html:hidden property="xh" name="rs"/>
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
							������
						</td>
						<td>
							${rs.xm}
						</td>
						<td>
							<div align="right">
								����֤���ͣ�
							</div>
						</td>
						<td>
							${rs.bdzlx}
						</td>						
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td>
							${rs.xb}
						</td>
						
						<td>
							<div align="right">
								����֤������
							</div>
						</td>
						<td>
							${rs.bdzkwdwmc}
						</td>						
					</tr>
					<tr>
						<td align="right" nowrap="nowrap">
							�꼶��
						</td>
						<td>
							${rs.nj}
						</td>
						
						<td align="right">
							����������
						</td>
						<td>
							${rs.dajwdwmc}
							${rs.dajwdwbm}
						</td>						
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td>
							${rs.xymc}
						</td>
						
						<td align="right" >
							������ϵ��ַ��
						</td>
						<td> 
							${rs.lxdz}
						</td>					
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td>
							${rs.zymc}
						</td>
						<td align="right" nowrap="nowrap">
							������ϵ�ʱࣺ
						</td>
						<td>
							${rs.lxyb}
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
							���˳�����ϵ��ʽ��
						</td>
						<td>
							${rs.lxfs}
						</td>	
					</tr>
					<tr>		
						<td align="right">
							ѧ�ƣ�
						</td>
						<td>
							${rs.xz}
						</td>				
						<td align="right">
							�ֻ����룺
						</td>
						<td>
							${rs.sjhm}
						</td>						
					</tr>
					<tr>
					<logic:equal value="true" name="isFdy">
						<td align="right">
							��˽����
						</td>
						<td colspan="3">
							<html:select property="fdysh" name="rs">
								<html:option value="ͨ��">ͨ��</html:option>
								<html:option value="��ͨ��">��ͨ��</html:option>
								<html:option value="δ���">δ���</html:option>
							</html:select>
						</td>
					</logic:equal>
					<logic:notEqual value="true" name="isFdy">
						<td align="right">
							��˽����
						</td>
						<td colspan="3">
							<html:select property="xysh" name="rs">
								<html:option value="ͨ��">ͨ��</html:option>
								<html:option value="��ͨ��">��ͨ��</html:option>
								<html:option value="δ���">δ���</html:option>
							</html:select>
						</td>
					</logic:notEqual>
					</tr>
				</table>
				<center>
					<div class="buttontool" id="btn">
						<button class="button2"
							onclick="refreshForm('bdzbl.do?method=audiBdzblOne');return false;"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="window.close();return false;"
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
