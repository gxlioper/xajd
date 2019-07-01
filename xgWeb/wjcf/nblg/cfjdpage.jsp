<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/wjcfFuction.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript">
		function chgcflb() {
			var jd = document.getElementById('jd').value;
			if ('���Ĵ���'==jd) {
				document.getElementById('ggcflbdm').disabled=false;
			} else {
				document.getElementById('ggcflbdm').selectedIndex=0;
				document.getElementById('ggcflbdm').disabled=true;
			}
		}
	</script>
	<body onload="chgcflb">
	    <html:form action="/wjcfnblgwh" method="post">
	    <input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>"/>
			 <div class="tab_cur">
			<p class="location">
				<em>��ǰ����λ��Υ�ʹ��� - ����������� - ίԱ�����</em>
			</p>
		</div>	
			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
			<fieldset>               			    
			       <logic:notEmpty name="rswj"> 			
  					<table border="0" id="rsTable" width="100%" class="formlist"> 
  						<thead><tr><th colspan="5"><span>�� �� �� ֤ �� �� �� �� ��</span></th></tr></thead>
    					<logic:iterate id="list" name="rswj"> 
    						<tr onmouseover="rowOnClick(this)"> 
      							<td> <a href="downloadfile.do?len=<bean:write name="list" property="len"/>&wjsclj=<bean:write name="list" property="wjsclj" />"  target="_blank">����     							
      							</a> </td> 
      							<td > <bean:write name="list" property="cfwh" /> </td> 
      							<td > <bean:write name="list" property="cflbmc" /> </td> 
       							<td > <bean:write name="list" property="cfyymc" /> </td>
       							<td > <bean:write name="list" property="sssj" /> </td>
<%--       							<td>--%>
<%--        							<a href="#" onclick="if(confirm('ȷʵҪɾ����ǰ�ļ���'))location='fileDel.do?wjh=<bean:write name="list" property="wjh"/>';" >ɾ��</a> </td>--%>
    						</tr> 
    					</logic:iterate> 
  					</table> 
  				</logic:notEmpty> 
  				<logic:empty name="rswj"> 
  				<table border="0" id="rsTable" width="100%" class="formlist"> 
  						<thead><tr><th colspan="5"><span>�� �� �� ֤ �� �� �� �� ��</span></th></tr></thead>
    					<tr><td colspan="5" align="center">���޲��ϻ�֤������</td></tr> 
  				</table>
  				</logic:empty> 
			</fieldset>
				<fieldset>
					<legend>
						&nbsp;&nbsp;���������������&nbsp;&nbsp;
					</legend>
					<table width="100%" class="formlist">
						<thead>
							<tr style="height:22px">
								<td colspan="5">
									���������������
								</td>
							</tr>
						</thead>
						<tfoot>
								<tr>
									<td colspan="5">
										<div class="btn">
													<button type="button"
														onclick="refreshForm('wjcf_nblg_cfjdUpdate.do?act=save');BatAlert.showTips('���ڲ�������ȴ�...');"
														id="buttonSave">
														�� ��
													</button>
											<button type="button" onclick="Close();return false;" id="buttonClose">
												�� ��
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
						<tr>
							<th width="15%">
								ѧ��
							</th>
							<td align="left" width="30%">
								<bean:write name="rs" property="xh" />
							</td>
							<th width="18%">
								�����ļ���
							</th>
							<td align="left">
								<bean:write name="rs" property="cfwh" />
							</td>
							<td align="left" width="15%" rowspan="5">
								<img border="0" style="height:120px;width:100px;"
									src="/xgxt/pictures/<bean:write name="rs" property="xh" />.jpg">
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td align="left">
								<bean:write name="rs" property="xm" />
							</td>
							<th>
								���
							</th>
							<td align="left">
								<bean:write name="rs" property="nd" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td align="left">
								<bean:write name="rs" property="xb" />
							</td>
							<th>
								ѧ��
							</th>
							<td align="left">
								<bean:write name="rs" property="xn" />
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td align="left">
								<bean:write name="rs" property="nj" />
							</td>
							<th>
								ѧ��
							</th>
							<td align="left">
								<bean:write name="rs" property="xq" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>
							<th>
								����ʱ��
							</th>
							<td align="left">
								<bean:write name="rs" property="cfsj" />
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
							<th>
								�������
							</th>
							<td align="left" colspan="2">
								<bean:write name="rs" property="cflbmc" />
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
							<th>
								��������
							</th>
							<td align="left" colspan="2">
								<bean:write name="rs" property="cfyymc" />
							</td>
						</tr>
						<tr>
							<th>
								��ϵ��ַ
							</th>
							<td align="left">
								<bean:write name="rs" property="dz" />
							</td>
							<th>
					      	 	����ʱ��
							</th>
							<td align="left" colspan="2">
								<bean:write name="rs" property="sssj" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td align="left">
								<bean:write name="rs" property="yb" />
							</td>
							<th>
								ίԱ�����
							</th>
							<td align="left" colspan="2" >
								<html:select property="jd" style="width:100px"
									styleId="jd" onchange="chgcflb()">
									<html:options collection="jdList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��ϵ�绰
							</th>
							<td align="left">
								<bean:write name="rs" property="lxdh" />
							</td>
								<th>
									���Ĵ������
								</th>
								<td align="left" colspan="2">
									<html:select property="ggcflbdm" styleId="ggcflbdm" style="width:100px">
										<html:option value=""></html:option>
										<html:options collection="cflbList" property="cflbdm" labelProperty="cflbmc"/>
									</html:select>
								</td>
						</tr>
						<tr>
							<th>
								���ߴ����ĺ�
							</th>
							<td align="left" colspan="">
								<html:text  property="jdwh" maxlength="15"></html:text>
							</td>	
							<th>
								���ߴ���ʱ��
							</th>
							<td align="left" colspan="2">
								<html:text  style="cursor:hand; width:85px;"
									styleId="dateF" property="jdsj" onblur="dateFormatChg(this)"
									style="cursor:hand;"
									onclick="return showCalendar('dateF','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
					   �ı�<bean:message key="lable.xsgzyxpzxy" />&nbsp;&nbsp;&nbsp;<br>����Ҫ��
							</th>
							<td align="left" colspan="4">
								<bean:write name="rs" property="yq" />
							</td>
						</tr>
						<tr>
							<th>
								���������&nbsp;&nbsp;&nbsp;
								<br>
								���ݻ�����
							</th>
							<td align="left" colspan="4">
								<html:textarea  property="jdly" rows="7"
									style="width:98%" styleId="jdly">
								</html:textarea>
							</td>
						</tr>
					</table>
				</fieldset>
			</logic:notEmpty>
		  <logic:equal value="yes" name="done">
			<script>
				alert("�����ɹ�!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		   <logic:equal value="no" name="done">
			<script>
				alert("����ʧ��!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
	  </html:form>	
	</body>
</html>
