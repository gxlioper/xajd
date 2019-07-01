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
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script>
		function save(){
			if(filedNotNull('xh-lfrq','-')){
				xsfwzdzx.checkXslfxx({xh:val('xh'),lfrq:val('lfrq')},function(data){
					if(data == true){
						alert('��Ҫ��ӵļ�¼�Ѿ����ڣ�');
						return false;
					}else{
						refreshForm('xsfwzdzx.do?method=saveXslfxx&doType=add');		
					}
				});
				
			} else {
				alert ('�뽫��\*�ŵ���Ŀ��д������');
				return false;
			}
		}
		
		function checkBm(value){
			xsfwzdzx.getBmslr(value,function(data){		
				if(data != null){
					DWRUtil.removeAllOptions("slr");	
					DWRUtil.addOptions("slr",data,"slryhm","slrxm");
				}				
			});
		}
	</script>
	
	<base target="_self">
	<body>
		<html:form action="/xsfwzdzx.do">
			<input type="hidden" name="url" id="url" value="/xsfwzdzx.do?method=xslfxxAdd">
			<input type="hidden" value="xh" id="getStuInfo" name="getStuInfo" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ճ����� - ѧ��ָ���������� - �����طõǼ� - ����Ǽ�
				</div>
			</div>
				<table width="100%" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								����Ǽ�
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
								�����˲��ţ�
							</div>
						</td>
						<td>
							<html:select property="slbmdm" onchange="checkBm(this.value)">
								<html:option value=""></html:option>
								<html:options collection="slbmList" property="slbmdm" labelProperty="slbmmc"/>
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
							�����ˣ�
						</td>
						<td>							
							<html:select property="slr"></html:select>
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
							���÷�ʽ��
						</td>
						<td>			
							<html:select property="lffs">
								<html:option value="����">����</html:option>
								<html:option value="����">����</html:option>
							</html:select>		
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
							<font color="red">*</font>�������ڣ�
						</td>
						<td>							
							<html:text property="lfrq" onclick="return showCalendar('lfrq','y-mm-dd');" readonly="true"></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							��ȣ�
						</td>
						<td>							
							${rs.nd}
							<html:hidden property="nd" name="rs"/>
						</td>	
						<td align="right">
							��ϵ�绰��
						</td>
						<td>							
							<html:text property="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>						
					</tr>
					<tr>
						<td align="right">
							�·ݣ�
						</td>
						<td>							
							${rs.yf}
							<html:hidden property="yf" name="rs"/>
						</td>	
						<td align="right">
							�������ɣ�
						</td>
						<td>							
							<html:text property="lfsy" maxlength="150"></html:text>
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
