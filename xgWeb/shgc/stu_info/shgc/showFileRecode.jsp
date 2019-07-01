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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onLoad="">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript">
			function save(url,eles){
				var ele = eles.split("-");
				for(var i=0;i<ele.length;i++){
					if(document.getElementById(ele[i]).value==null || document.getElementById(ele[i]).value==""){
						alert("�뽫��\*�ŵ���Ŀ��д������");
						return false;
					}
				}
			refreshForm(url);
			}
		</script>
		<html:form action="/business.do?method=saveFileRecode" method="post">
		<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/shgc/stu_info/shgc/showFileRecode.jsp" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�ѧ����Ϣ - �ļ� - �ļ�����ά��
				</div>
			</div>			
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							�ļ���Ϣ
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="right">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) return false;" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<td align="right">
									<font color="red">*</font>ѧ�ţ�
								</td>
								<td align="left">
									<input type="text" id="xh" name="xh" value="<bean:write name='rs' property="xh" />" readonly="readonly" />
								</td>
							</logic:equal>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<bean:write name="rs" property="zymc"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right" >
								������
							</td>
							<td align="left">
								<bean:write name='rs' property="xm" />
							</td>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<bean:write name="rs" property="xymc"/>
							</td>
						</tr>
						<tr style="height:22px">
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<bean:write name='rs' property="xb" />
							</td>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<bean:write name="rs" property="bjmc"/>
							</td>
						</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red">*</font>�ļ��ţ�
					</td>
					<td align="left">
						<logic:equal value="modi" name="doType">
							<html:text property="wjh" name="rs" styleId="wjh" readonly="true"></html:text>
						</logic:equal>
						<logic:notEqual value="modi" name="doType">
							<html:text property="wjh" name="rs" styleId="wjh"></html:text>
						</logic:notEqual>
						
					</td>
					<td align="right">
						<font color="red">*</font>��ȣ�
					</td>
					<td align="left">
					<html:select property="nd" style="width:120px" styleId="nd" name="rs">
											<html:option value=""></html:option>
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>	
					</td>
				</tr>
				<tr style="height:22px">
							<td align="right">
								<font color="red">*</font>�������ڣ�
							</td>
							<td align="left">
								<html:text property="lrrq" name="rs" onclick="return showCalendar('lrrq','y-mm-dd');" styleId="lrrq" ></html:text>
							</td>
							<td align="right">
								<font color="red">*</font>�ļ����ͣ�
							</td>
							<td align="left">
								<html:select property="wjlx" name="rs" styleId="wjlx">
									<html:option value=""></html:option>
									<html:option value="��ѧ�ļ�"> ��ѧ�ļ� </html:option>
									<html:option value="�����ļ�"> �����ļ� </html:option>
								</html:select>
							</td>
						</tr>
			</table>
			<logic:equal value="yes" name="writeAble" scope="request">
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="save('/xgxt/business.do?method=saveFileRecode','xh-wjh-lrrq-wjlx-nd')"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onClick="Close()" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
			</logic:equal>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
