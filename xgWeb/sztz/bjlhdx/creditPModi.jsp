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
		<base target="_self" />
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>

		<html:form action="/bjlhdx_sztz" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�������չ - ��Ϣά�� - ���ÿ۷�ά�� - �޸�
				</div>
			</div>			
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />

			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">

				<table width="100%" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								�۳�����Ϣ
							</td>
						</tr>
					</thead>
					<tr>
						<td height="22" align="right">
							ѧ�ţ�
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xh" styleId="xh"
								disabled="true" />
						</td>
						<td height="22" align="right">
							<font color="red">*</font>ѧ�꣺
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="xn" styleId="xn" disabled="true" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							������
						</td>
						<td height="22" align="left">
						<html:text name="rs" property="xm" styleId="xm" disabled="true" />						
						</td>
						<td height="22" align="right">
							<font color="red">*</font>ѧ�ڣ�
						</td>
						<td height="22" align="left">						
							<html:text name="rs" property="xq" styleId="xq" disabled="true" />
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							�꼶��
						</td>
						<td height="22" align="left">
						<html:text name="rs" property="nj" styleId="nj" disabled="true" />	
						</td>

						<td height="22" align="right">
							���ó�ʼ�֣�
						</td>
						<td height="22" align="left">
							<bean:write name="csf"/>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td height="22" align="left">
							<html:text name="rs" name="rs" property="xymc"  disabled="true"></html:text>
						</td>
						<td height="22" align="right">
							�۷�ʱ�䣺
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="kfsj" styleId="kfsj" disabled="true" disabled="true" />
						</td>
						
					</tr>
					<tr>
						<td height="22" align="right">
                          רҵ��
						</td>
						<td height="22" align="left">
                      <html:text name="rs" name="rs" property="zymc" disabled="true" ></html:text>
						</td>
						<td height="22" align="right">
							�۷�ֵ��
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="kfs" style="cursor:hand;"></html:text>
						</td>
					</tr>
										<tr>
						<td height="22" align="right">
                          �༶��
						</td>
						<td height="22" align="left">
                     <html:text name="rs" name="rs" property="bjmc" disabled="true" ></html:text>
						</td>
						<td height="22" align="right">
							
						</td>
						<td height="22" align="left">
							
						</td>
					</tr>

					<tr align="left">
						<td align="right">
							�۷����飺
						</td>
						<td colspan="4">
						<bean:write name="rs" property="kfxq"/>
							
						</td>
					</tr>
				</table>
				<br />
				<div class="buttontool" id="button" align="center">
					<button class="button2" onclick="kfsSave()"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("�����ɹ���");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("����ʧ��,��������������ݺ����ύ��");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function kfsSave(){	
	          var kfs = $("kfs").value;
	          if(kfs==""){
	             alert("�۷�ֵ����Ϊ�գ�");
	             return false;
	          } 
	          if(kfs.match(/^\d+\.{0,1}\d{0,3}$/)==null){
		        alert("�۷�ֵ��Ϊ���֣�");
		        return false;
	         }	            
	          var url = "/xgxt/bjlhdx_sztz.do?method=kfModi&doType=save";
	          url +="&pkValue=";
	          url +=$("pkValue").value;
              refreshForm(url);		
              $("buttonSave").disabled=true;       	           
	     }
	</script>
</html>
