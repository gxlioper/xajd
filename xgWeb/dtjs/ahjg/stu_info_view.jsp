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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
		<style type="text/css">
<!--
.style1 {font-size: 16px}
.style2 {font-size: 14px}
.style3 {
	color: #000000;
	font-size: 15px;
}
.style4 {
	font-size: 15px;
	font-weight: bold;
}
-->
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">

	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/studetailFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/dwjlgl.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
	<script type="text/javascript">
	function add(){
		  var xsczqk = document.getElementById("xsczqk").value;   
	     
	      if(xsczqk==""){
	      alert("��ѧ�ڸ��ٷ���ѧ���ɳ��������Ϊ�գ�");
	      document.getElementById("xsczqk").focus();
	      return false;
	      }
	      if(xsczqk.length>800){
		      alert("��ѧ�ڸ��ٷ���ѧ���ɳ����ܴ���800�����֣�");
		      document.getElementById("xsczqk").focus();
		      return false;
		      }
	        BatAlert.showTips('�����ύ�����Ժ�...');
			 document.forms[0].action = "ahjg_wtqtgl.do?act=add&doType=add";
			 document.forms[0].submit();
  }
	</script>
	
	<body>
	<html:form action="/ahjg_wtqtgl.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã�ѧ����Ϣ�鿴
			</div>
		</div>
		
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb" />
		<input type="hidden" id="url" name="url" value="/dtjs/ahjg/stu_info_add.jsp" />
		<input type="hidden" id="from" name="from" value="/dtjs/ahjg/stu_info_add.jsp" />
		<input type="hidden" id="xxdm" name="xxdm"
			value="<bean:write name="xxdm"/>" />
			
		<table width="100%" align="center" class="tbstyle">		
			<tr>
				<td>
<%--					<table width="100%" class="tbstyle">--%>
<%--						<tr>--%>
<%--							<td bgcolor="#CCCCCC">--%>
<%--								<div id="main1" style="color:blue;cursor:hand"--%>
<%--									onclick="document.all.child1.style.display=(document.all.child1.style.display =='none')?'':'none'">--%>
<%--									<div align="center" class="style1 style3">--%>
<%--										<strong>ѧ��������Ϣ</strong>--%>
<%--									</div>--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</table>--%>
				</td>
			</tr>
			<tr>
				<td>
					<div id="child1">
						<table width="100%" align="center" class="tbstyle">
							<tr>
								<td align="right" width="15%">
									ѧ�ţ�
								</td>
								<td align="left" width="25%">
									<bean:write name="rs" property="xh" />
								</td>
								<td align="right" width="15%">
									�꼶��									
								</td>
								<td align="left" width="30%" colspan="3">
									<bean:write name="rs" property="nj" />
								</td>
							</tr>
							<tr>
								<td align="right">
									ѧ�꣺
								</td>
								<td align="left">
									<bean:write name="rs" property="xn" />
								</td>
								<td align="right">
									ѧ�ڣ�
								</td>
								<td align="left">
									<bean:write name="rs" property="dqxq" />
								</td>
							</tr>
							<tr>
								<td align="right">
									������
								</td>
								<td align="left">
									<bean:write name="rs" property="xm" />
								</td>
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />��
								</td>
								<td align="left">
									<bean:write name="rs" property="xymc" />
								</td>
							</tr>
							<tr>
								<td align="right">
									�Ա�
								</td>
								<td align="left">
									<bean:write name="rs" property="xb" />
								</td>
								<td align="right">
									רҵ��
								</td>
								<td align="left">
									<bean:write name="rs" property="zymc" />
								</td>
							</tr>
								<tr>
									<td align="right">
										�������ڣ�
									</td>
									<td align="left">
										<bean:write name="rs" property="csrq" />
									</td>
									<td align="right">
										�༶��
									</td>
									<td align="left">
										<bean:write name="rs" property="bjmc" />
									</td>
								</tr>
							<tr>
								<td align="right">
									���壺
								</td>
								<td align="left">
									<bean:write name="rs" property="mzmc" />
								</td>
								<td align="right">
									ѧ�ƣ�
								</td>
								<td align="left">
									<bean:write name="rs" property="xz" />
								</td>
							</tr>
							
							<tr>
								<td align="right">
									�������ƣ�
								</td>
								<td align="left" colspan="4">
									<bean:write name="rs" property="wtmc" />
								</td>
							</tr>
							<tr>
								<td align="center">
									<font color="red">*</font>��ѧ<br>�ڸ�<br>�ٷ�<br>��<br>ѧ��<br>�ɳ�<br>���
								</td>
								<td align="left" colspan="4">
									<html:textarea name="rs" property="xsczqk" rows="8" style="width=100%" readonly="true"></html:textarea>
								</td>
							</tr>
						</table>
					</div>
				</td>
			</tr>
		</table>
		
		</html:form>
			<logic:notEmpty name="save">
			<logic:equal name="save" value="ok">
				    <script>
                      alert("�ύ�ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="no">
				<script>
                      alert("�ظ��ύ������ʧ��!");
                    </script>
			</logic:equal>
			<logic:equal name="save" value="iscz">
				<script>
                      alert("��ѧ����Ϣ�Ѵ��ڣ�������޸Ĵ���");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
