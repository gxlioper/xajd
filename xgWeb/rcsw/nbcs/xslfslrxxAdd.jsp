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
			if(filedNotNull('slryhm-slbmdm','-')){
				xsfwzdzx.checkXslfbmlxr({slbmdm:val('slbmdm'),slryhm:val('slryhm')},function(data){
					if(data == true){
						alert('��Ҫ��ӵļ�¼�Ѿ����ڣ�');
						return false;
					}else{
						refreshForm('xsfwzdzx.do?method=saveXslfslrxx&doType=add');		
					}
				});
				
			} else {
				alert ('�뽫��\*�ŵ���Ŀ��д������');
				return false;
			}
		}
		
		function checkYh(value){
			xsfwzdzx.checkYhm(value,function(data){
				if(data == null || data == ""){
					alert('��������û��������ڣ�');
					setVal('slryhm','');
					return false;
				}else{
					setVal('slrxm',data);
				}				
			});
		}
	</script>
	
	<base target="_self">
	<body>
		<html:form action="/xsfwzdzx.do">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ճ����� - ѧ��ָ���������� - ���������˹��� - �޸�
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								������������Ϣ
							</td>
						</tr>
					</thead>
					
					<tr>
						<td>
							<div align="right">
								<font color="red">*</font>�����˲��ţ�
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
							<font color="red">*</font>�������û�����
						</td>
						<td>							
							<html:text property="slryhm" onblur="checkYh(this.value)"></html:text>
						</td>						
					</tr>					
					<tr>
						<td align="right">
							������������
						</td>
						<td>
							<html:text property="slrxm" maxlength="30"></html:text>
						</td>					
					</tr>
					<tr>
						<td align="right">
							��������ϵ�绰��
						</td>
						<td>
							<html:text property="slrlxdh" maxlength="11" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
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
