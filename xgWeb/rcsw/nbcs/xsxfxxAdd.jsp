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
	<script type='text/javascript' src='/xgxt/dwr/interface/xsxfxxgl.js'></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script>
		function save(){
			if(filedNotNull('xh-nd','-')){
				xsxfxxgl.checkXsxfxx({xh:val('xh'),nd:val('nd')},function(data){
					if(data == true){
						alert('��Ҫ��ӵļ�¼�Ѿ����ڣ�');
						return false;
					}else{
						refreshForm('xfxx.do?method=saveXsxfxx&doType=add');		
					}
				});
				
			} else {
				alert ('�뽫��\*�ŵ���Ŀ��д������');
				return false;
			}
		}
	</script>
	
	<base target="_self">
	<body>
		<html:form action="/xfxx.do">
			<input type="hidden" name="url" id="url" value="/xfxx.do?method=xsxfxxAdd">
			<input type="hidden" value="xh" id="getStuInfo" name="getStuInfo" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ճ����� - ѧ�Ѵ߽� - ѧ����Ϣά�� - ����
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								ѧ����Ϣ
							</td>
						</tr>
					</thead>
					
					<tr>
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td>							
							<html:text name="rs" property="xh" styleId="xh" onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />
							<button type="button" class="button2"
								onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								style="width:20px" id="buttonFindStu">
								ѡ��
							</button>
						</td>
						<td>
							<div align="right">
								<font color="red">*</font>��ȣ�
							</div>
						</td>
						<td>
							<html:select property="nd">								
								<html:options collection="ndList" property="nd" labelProperty="nd"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							������
						</td>
						<td>							
							${rs.xm}
						</td>	
						<td align="right">
							Ƿ��ѧ�ѣ�
						</td>
						<td>							
							<html:text property="qjxf" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "></html:text>
						</td>						
					</tr>	
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td>							
							${rs.xymc}
						</td>	
						<td align="right">
							���������ѣ�
						</td>
						<td>			
							<html:text property="qtf" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "></html:text>
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
							Ƿ�ɴ��ܷѣ�
						</td>
						<td>							
							<html:text property="qjdgf" maxlength="10" onkeyup="value=value.replace(/[^\d|.]/g,'') "></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							������Σ�
						</td>
						<td>							
							<html:text property="pycc" maxlength="15" name="rs"></html:text>
						</td>	
						<td align="right">
							����״̬��
						</td>
						<td>							
							<html:text property="jfzt" maxlength="15" name="rs"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							ѧϰ��ʽ��
						</td>
						<td>							
							<html:text property="xxxs" maxlength="15" name="rs"></html:text>
						</td>	
						<td align="right">
							׼��֤�ţ�
						</td>
						<td>							
							<html:text property="zkzh" maxlength="150" name="rs"></html:text>
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
