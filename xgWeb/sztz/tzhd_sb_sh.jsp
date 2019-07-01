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
	<body onload="checkWinType();document.all('buttonClose').focus()">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/sztz_xfsb_sh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�������չ - ��չ��Ŀ��� - ��� - �������
				</div>
			</div>
			<input type="hidden" name="pkValue"
				value="" />
			<input type="hidden" id="tableName" name="tableName"
						value="" />
			<table width="100%" align="center" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							������չ��Ŀ�걨���
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td width="15%" align="right" nowrap >
						ѧ�꣺
				    </td>
					<td align="left">
						<bean:write name="rs" property="xn" />						
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xq" />
					</td>
				</tr>
				<tr style="height:22px">
					<td width="15%" align="right" nowrap >
						��Ŀ���ƣ�
				    </td>
					<td align="left">
						<bean:write name="rs" property="xmmc" />
						<input type="hidden" name="xmdm" value="<bean:write name="rs" property='xmdm' />">
					</td>
					<td align="right">
						��Ŀ���ƣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="kmm" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						���벿�ţ�
					</td>
					<td align="left">
						<bean:write name="rs" property="bmmc" />
					</td>
					<td align="right">
						ִ�е�λ��
					</td>
					<td align="left">
						<bean:write name="rs" property="xsdw" />
					</td>
				 </tr>			
			     <tr style="height:22px">
					<td align="right">
						ѧ�֣�
					</td>
					<td align="left">
						<bean:write name="rs" property="xf" />
					</td>
					<td align="right">
						��ˣ�
					</td>
					<td align="left">
						<html:select name="rs" property="yesNo">
							<html:options collection="chkList" property="en"
								labelProperty="cn" />
						</html:select>
					</td>
				</tr>
				<tr valign="middle">
					<td width="15%" align="right" nowrap>
						���뿪ʼʱ�䣺
					</td>
					<td width="35%" align="left">
						<bean:write name="rs" property="sqkssj" />
					</td>
					<td width="15%" align="right" nowrap>
						�������ʱ�䣺
					</td>
					<td width="35%" align="left">
						<bean:write name="rs" property="sqjssj" />
					</td>
				</tr>				
				<tr style="height:22px">
					<td align="right">
						��Ŀ������
					</td>
					<td align="left"colspan="3">
						<bean:write name="rs" property="xmms" />
					</td>
				</tr>
     		</table>
     		<table width="100%" align="center"  class="tbstyle">
					<thead>
						<tr>						    
							<td align="center" style="cursor:hand" title="����������ʾ��������ϸ"
							onclick="document.getElementById('ly').style.display=(document.getElementById('ly').style.display==''?'none':'')">
								�����걨����
							</td>
						</tr>
					</thead>
					<tr id="ly" style="display:none">
						<td>
						<fieldset>
							<logic:empty name="rsly">
								<br />
								<br />
								<p align="center">
									δ�ҵ��κμ�¼��
								</p>
							</logic:empty>
							<logic:notEmpty name="rsly">
								<legend>
									��¼����
									<bean:write name="rslyNum" />
								</legend>

								<table width="99%" id="rsTable" class="tbstyle">
									<thead>
										<tr align="center">
											<td>
											    �к�
											</td>
											<td>
												����
											</td>
											<td>
												����
											</td>
										</tr>
									</thead>
									<logic:iterate name="rsly" id="s">
										<tr style="cursor:hand">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</logic:notEmpty>
						</fieldset>
					 </td>
				  </tr>
				</table>
				 <table width="100%" align="center" class="tbstyle">
					<thead>
						<tr>
							<td align="center" style="cursor:hand" title="����������ʾ��������ϸ"
							onclick="document.getElementById('lb').style.display=(document.getElementById('lb').style.display==''?'none':'')">
								&nbsp;��&nbsp;��&nbsp;��&nbsp;��&nbsp;
							</td>
						</tr>
					</thead>
					<tr id="lb" style="display:none">
						<td>
							<fieldset>
							<logic:empty name="rslb">
								<br />
								<br />
								<p align="center">
									δ�ҵ��κμ�¼��
								</p>
							</logic:empty>								
                            <logic:notEmpty name="rslb">
								<legend>
									��¼����
									<bean:write name="rslbNum" />
								</legend>

								<table width="99%" id="rsTable" class="tbstyle">
									<thead>
										<tr align="center">
											<td>
											    �к�
											</td>
											<td>
												����
											</td>
											<td>
												ѧ��
											</td>
										</tr>
									</thead>
									<logic:iterate name="rslb" id="s">
										<tr style="cursor:hand">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</logic:notEmpty>	
							</fieldset>							
						</td>
					</tr>
				</table>	
			<div class="buttontool" align="center">
				<button class="button2"
					onclick="refreshForm('/xgxt/sztz_hd_check.do?doType=save');this.disabled=true;"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>
		</html:form>
		<logic:equal value="ok" name="isEXIST">
         		<script language="javascript">
	         		alert("�����ɹ���");
	         		Close();
	         		window.dialogArguments.document.getElementById('search_go').click();
	         	</script>
	    </logic:equal>
	    <logic:equal value="no" name="isEXIST">
         	<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
	        </script>
	   </logic:equal>
	</body>
</html>
