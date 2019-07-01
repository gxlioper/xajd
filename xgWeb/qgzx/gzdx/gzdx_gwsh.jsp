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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<base target="_self" />
	<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function save(){
			var xscyj = document.getElementById('xscyj').value;
			var sqsyrs = document.getElementById('sqsyrs').value;
			if (sqsyrs == null || sqsyrs == "") {
				alert("����ʹ����������Ϊ��!");
				return false;
			}
			if(xscyj != ''){
					if(xscyj.replace(/[^\u0000-\u00ff]/g, "**").length > 150){	         
	          		 alert("ѧ����������ܴ���150���ַ�");
	          		 return false;
	       		 }
			}
			refreshForm('/xgxt/postChkOne.do?act=save');
		}
	</script>
	<body>		
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
						��ǰ����λ�ã��ڹ���ѧ - ��� - ��λ���
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">	
			<input type="hidden" name="pkVal" value="<bean:write name="rs" property="gwdm||gwsbsj"/>" />
			<input type="hidden" name="xhStr" value="<bean:write name="xhStr" />" />
			<input type="hidden" name="xxyjStr" value="" />			
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								������λ���
							</td>
						</tr>
					</thead>
					<tr>
						<td height="22" align="right">
							��λ���ƣ�
						</td>
						<td height="22" align="left">
								${rs.gwdm }
						</td>
						<td height="22" align="right">
							���˵�λ(����)��
						</td>
						<td height="22" align="left">
							${rs.yrdwmc }
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							���˵�λ����Ա������
						</td>
						<td height="22" align="left" colspan="">
							${rs.fzr }
						</td>
						<td height="22" align="right">
							��ϵ�绰��
						</td>
						<td height="22" align="left" colspan="">
							${rs.lxdh }
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							ѧ�꣺
						</td>
						<td height="22" align="left">
							${rs.xn }
						</td>
						<td height="22" align="right">
							��ȣ�
						</td>
						<td height="22" align="left">
							${rs.nd }
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							������ʼ���ڣ�
						</td>
						<td height="22" align="left">
							${rs.gzksrq }
						</td>
						<td height="22" align="right">
							�����������ڣ�
						</td>
						<td height="22" align="left">
							${rs.gzjsrq }
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							������������λ����
						</td>
						<td height="22" align="left">
							${rs.xyrs }
						</td>
						<td align="right">
							��˽����
						</td>
						<td align="left">
							<html:select name="rs" property="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							��λ���ʣ�
						</td>
						<td align="left">
							${rs.gwxzmc}
						</td>
						<td height="22" align="right">
							<font color="red">*</font>����ʹ��������
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="sqsyrs" style="width:130px" styleId="sqsysr" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
							��
						</td>						
					</tr>
					<tr>
						<td height="22" align="right">
							����ʱ�䣺
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="gzsj" styleId="gzsj"
								style="width:100%;height:80px" disabled="true"/>
							(������һ�ϣ��ܶ���...)
							<span id="gzsjDw"></span>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							�������ݣ�
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="gznr" styleId="gznr"
								style="width:100%" rows="5" onblur="chLeng(this,'150')" disabled="true"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							����Ҫ��
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="gzyd" styleId="gzyd"
								style="width:100%" rows="5" onblur="chLeng(this,'150')" disabled="true"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							���˵�λ(����)�����
						</td>						
						<td height="22" colspan="3" align="left">
							<logic:equal value="yes" name="notYrdw">
								<html:textarea name="rs" property="sqdwyj" style="width:100%"
									onblur="chLeng(this,'100')" rows="5" readonly="true"></html:textarea>
							</logic:equal>
							<logic:notEqual value="yes" name="notYrdw">
								<html:textarea name="rs" property="sqdwyj" style="width:100%"
									onblur="chLeng(this,'100')" rows="5"></html:textarea>	
							</logic:notEqual>
						</td>
					</tr>					
					<tr>
						<td height="22" align="right">
							ѧ���������
						</td>
						<td height="22" align="left" colspan="3">
							<logic:equal value="yes" name="notYrdw">
								<html:textarea name="rs" property="xgbyj" styleId="xscyj" style="width:100%"
									onblur="chLeng(this,'100')" rows="5" ></html:textarea>
							</logic:equal>
							<logic:notEqual value="yes" name="notYrdw">
								<html:textarea name="rs" property="xgbyj" styleId="xscyj" style="width:100%"
									onblur="chLeng(this,'100')" rows="5" readonly="true"></html:textarea>
							</logic:notEqual>
						</td>
					</tr>
					
				</table>
				<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="save();return false;"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
