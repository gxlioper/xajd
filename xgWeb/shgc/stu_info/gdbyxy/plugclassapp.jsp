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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
		function chkSave(url,exis){
			var ele = exis.split("-");
			for(var i = 0;i<ele.length;i++){
				if(document.getElementById(ele[i]).value == null || document.getElementById(ele[i]).value==""){
					alert('请将带\*号的项目填写完整！');
					return false;
				}
			}
			refreshForm(url);
		}
	</script>
	
	<body>
		<html:form action="/data_search" method="post">
			<div class="title">
				<logic:equal name="do" value="no">
					<div class="title_img" id="title_m">
						当前位置：学生信息 - 插班申请 - 填写申请表
					</div>
				</logic:equal>
				<logic:equal name="do" value="yes">
					<div class="title_img" id="title_m">
						当前位置：学生信息 - 插班申请 - 修改申请表
					</div>
				</logic:equal>
			</div>			
			<table id='rsTable' class="tbstyle" width="100%">
				 <thead align="center">
						<tr><td align="center" colspan="4">
							<b>插班申请</b>
						</td></tr>
				</thead>	
				
				<tr>
					<td align="right">
						<font color="red">*</font>姓名：
					</td>
					<td width="176">
						<html:text property="xm" name="rs" styleId="xm"/>
					</td>
					<td width="181" align="right">
						性别：
					</td>
					<td width="218">
						<html:radio name="rs" property="xb" value="男">男</html:radio>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<html:radio name="rs" property="xb" value="女">女</html:radio>
						<br />
					</td>
				</tr>
				<tr>
					<td height="26" align="right">
						出生年月：
					</td>
					<td>
						<html:text property="csrq" name="rs"/>
					</td>
					<td align="right">
						家庭地址：
					</td>
					<td>
						<html:text property="jtdz" name="rs"/>
					</td>
				</tr>
				<tr>
					<td height="28" align="right">
						<font color="red">*</font>原读学校：
					</td>
					<td>
						<html:text property="ydxx" name="rs" styleId="ydxx"/>
					</td>
					<td align="right">
						<font color="red">*</font>原读专业：
					</td>
					<td>
						<html:text property="ydzy" name="rs" styleId="ydzy"/>
					</td>
				</tr>
				<tr>
					<td height="32" align="right">
						实习经历：
					</td>
					<td>
						<html:text property="sxjl" name="rs"/>
					</td>
					<td align="right">
						技能证书记录：
					</td>
					<td>
						<html:text property="jnzs" name="rs"/>
					</td>
				</tr>
				<tr>
					<td height="32" align="right">
						<font color="red">*</font>现在要就读的专业：
					</td>
					<td>
						<html:text property="xdzy" name="rs" styleId="xdzy"/>
					</td>
					<td align="right">
						<font color="red">*</font>联系电话：
					</td>
					<td>
						<html:text property="lxdh" name="rs" styleId="lxdh"/>
					</td>
				</tr>
				<tr>
					<td height="163" align="right">
						备注：
					</td>
					<td colspan="3">
						<html:textarea property="bz" name="rs" style="width:100%;height:100%"/>
					</td>
				</tr>
			</table>
			<logic:equal value="yes" name="writeAble">
			<div class="buttontool" align="center">
				<button class="button2" onclick="chkSave('business.do?method=savePlugClassApply','xm-xb-ydxx-ydzy-xdzy-lxdh')">
					提 交 申 请
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="window.open('/xgxt/shgc/stu_info/gdbyxy/stu_gdbyxy_cbspb.jsp')">
					打 印 预 览
				</button>
			</div>
			</logic:equal>
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
					<script>
    					alert("申请成功！");
    				</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
    					alert("申请失败！");
    				</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
