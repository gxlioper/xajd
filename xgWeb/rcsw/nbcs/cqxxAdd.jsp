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
			var ykfts = val('ykfts');
			var jscqts = val('jscqts');
			var xscqts = val('xscqts');
			
			ykfts = ykfts == null || ykfts=="" ? "0" : ykfts;
			jscqts = jscqts == null || jscqts=="" ? "0" : jscqts;
			xscqts = xscqts == null || xscqts=="" ? "0" : xscqts;
			
			if(filedNotNull('nd-yf-slbmdm','-')){
				if(toInt(ykfts)<toInt(jscqts)+toInt(xscqts)){
					alert('�ܳ������������¿���������');
					return false;
				}else{
					xsfwzdzx.checkCqxx({nd:val('nd'),yf:val('yf'),slbmdm:val('slbmdm')},function(data){
						if(data == true){
							alert('��Ҫ��ӵļ�¼�Ѿ����ڣ�');
							return false;
						}else{						
							refreshForm('xsfwzdzx.do?method=saveCqxx&doType=add');		
						}
					});
				}
			} else {
				alert ('�뽫��\*�ŵ���Ŀ��д������');
				return false;
			}
		}
	</script>
	
	<base target="_self">
	<body>
		<html:form action="/xsfwzdzx.do">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ճ����� - ѧ��ָ���������� - ������Ϣ- ����
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								������Ϣ
							</td>
						</tr>
					</thead>
					
					<tr>
						<td>
							<div align="right">
								<font color="red">*</font>�����ţ�
							</div>
						</td>
						<td>
							<html:select property="slbmdm">
								<html:options collection="slbmList" property="slbmdm" labelProperty="slbmmc"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>��ȣ�
						</td>
						<td>							
							<html:select property="nd">
								<html:options collection="ndList" property="nd" labelProperty="nd"/>
							</html:select>
						</td>						
					</tr>	
					<tr>
						<td align="right">
							<font color="red">*</font>�·ݣ�
						</td>
						<td>							
							<html:select property="yf">
								<html:options collection="yfList" property="yf" labelProperty="yf"/>
							</html:select>
						</td>						
					</tr>					
					<tr>
						<td align="right">
							�¿���������
						</td>
						<td>
							<html:text property="ykfts" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>					
					</tr>
					<tr>
						<td align="right">
							��ʦ����������
						</td>
						<td>
							<html:text property="jscqts" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							ѧ������������
						</td>
						<td>
							<html:text property="xscqts" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							��ע��
						</td>
						<td>
							<html:textarea property="bz" onblur="chLeng(this,300)"></html:textarea>
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
