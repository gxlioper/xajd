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

		<meta name="Copyright" content="������� zfsoft" />
		
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
	function savejyxy(){
		var bbsm = document.getElementById("bbsm").value;
		var xh = document.getElementById("xh").value;
		if(xh == ""){
			alert("����дѧ�ţ�����");
			return false;
		}
		if(bbsm.length>2000){
			alert("����˵�����ܳ���2000�����֣�����");
			return false;
		}
		if(confirm("�����ѧ�ŵ�ѧ���Ѿ����룬�����ύ���ᱻ���ǵ���ǰ�ύ����Ϣ")){
			document.forms[0].action = "/xgxt/jyxysbbsq.do?act=add&doType=save";
			document.forms[0].submit();
		}
    }
    
   
    
    function refreshtheweb(){
    var byqxdm = document.getElementById("byqxdm").value;
    var dwdq = document.getElementById("dwdq").value;
    var xsxh = document.getElementById("xsxh").value;
    var zgbm = document.getElementById("zgbm").value;
    
                document.forms[0].action = "/xgxt/savejyxy.do?doType=go&byqxdm="+byqxdm+"&dwdq="+dwdq+"&xsxh="+xsxh+"&zgbm"+zgbm;
                document.forms[0].submit();       
    }
    
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    }  
	
	function jyxyreinput(){
	            window.location.href="/xgxt/jyxy_input.do?act=first";  
	}
	function ysbb(){
		var bbyy = document.getElementById("bbyy").value;
		if(bbyy == "��ʧ"){
			document.getElementById("ys").style.display='inline';
			document.getElementById("gh").style.display='none';
		}else if(bbyy == "����"){
			document.getElementById("ys").style.display='none';
			document.getElementById("gh").style.display='inline';
		}else{
			document.getElementById("ys").style.display='none';
			document.getElementById("gh").style.display='none';
		}
	}
	</script>
	<body onload="ysbb();">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/jyxysbbsq" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã���ҵ���� - ��ҵЭ�鷽�� - ��ҵЭ���鲹������
				</div>
			</div>
			<logic:notEmpty name="errMsg">
				<center><font color="red" size="8"><bean:write name="errMsg"/></font></center>
			</logic:notEmpty>
			<logic:empty name="errMsg">
				<table width="100%" id="rsT" class="tbstyle">
					<thead align="center">
						<tr>
							<td align="center" colspan="4">
								Э���鲹������
							</td>
						</tr>
					</thead>

					<tr>
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td>
<%--							<logic:equal value="student" name="userType">--%>
							<html:text name="rs" property="xh" styleId="xh" readonly="true"/>
<%--							</logic:equal>--%>
<%--							<logic:notEqual value="student" name="userType">--%>
<%--								<html:text name="rs" property="xh" styleId="xh" onkeypress="if(event.keyCode == 13) autoFillStuInfo2(this);" />--%>
<%--								<button class="button2"--%>
<%--									onclick="showTopWin('/xgxt/stu_info.do',750,550);"--%>
<%--									style="width:20px" id="buttonFindStu">--%>
<%--									ѡ��--%>
<%--								</button>--%>
<%--							</logic:notEqual>--%>
						</td>
						<td align="right">
							������
						</td>
						<td>
							<bean:write name="rs" property="xm" />
						</td>
					</tr>
					<tr>
						<td>
							<div align="right">
								��ҵ��ȣ�
							</div>
						</td>
						<td>
							<html:text name="rs" property="byny" readonly="true"></html:text>
						</td>
						<td align="right" nowrap="nowrap">
							�꼶��
						</td>
						<td>
							<bean:write name="rs" property="nj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td>
							<bean:write name="rs" property="xb" />
						</td>
						<td align="right">
							ѧ�ƣ�
						</td>
						<td>
							<bean:write name="rs" property="xz" />
						</td>							
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td>
							<bean:write name="rs" property="xymc" />
						</td>
						<td align="right">
							רҵ��
						</td>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>				
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td>
							<bean:write name="rs" property="bjmc" />
						</td>
						<td>
							<div align="right">
								<font color="red">*</font>����ԭ��
							</div>
						</td>
						<td>
							<html:select property="bbyy" styleId="bbyy" onchange="ysbb();">
								<html:option value=""></html:option>
								<html:option value="��ʧ">��ʧ</html:option>
								<html:option value="����">����</html:option>
							</html:select>
						</td>	
					</tr>
					<tr>
						<td align="right">
							����˵��
						</td>
						<td colspan="3">
							<html:textarea property="bbsm" rows="10" style="width: 750px"></html:textarea>
						</td>
					</tr>
					<tr id="ys" style="display: none">
						<td colspan="4" style="width: 800px;">
							<font color="red">
							��ʾ������ʧ����ԭ�򣺸��ݽ���ʡ�������Ĺ涨������ʧ�ĵ��صǱ��������ǹ������еı�ֽ��
							��������ʧ���¡�����ҵ����ѯԭЭ�����ţ����м����Ϲ������еı����Ͽ���ԭЭ����������
							�ϵ���ʧ���£��迯����������ҵԺУ����ҵЭ�����ţ�������������ʧ��ҵ����ҵЭ���飬
							����ѧУ ������������������������ϡ���
							</font>
						</td>
					</tr>
					<tr id="gh" style="display: none">
						<td colspan="4" style="width: 800px;">
							<font color="red">
							��ʾ����ԭ��λ��Ч�˺������֣�һ��ֱ���ڡ�Э���顷��ע������Э���ѽ����������Э��ר���£�
							���ǵ�����������Ч��Լ֤��������Э��ר���£��������ѿ����ı���֤��ע����ͬ����ɡ���
							���ɸõ�λ���»��ɱ�ҵ��Ŀǰ�����йܵ�λ�������¾ֻ��˲��г������¡���λ�ѽ���򵹱յģ�
							��ز��ţ���ԭ��λ���ҿ����˲��г�����֤���ɴ�����Ч�˺�����
							</font>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="savejyxy()">
						���벹��
					</button>
				</div>
				</logic:empty>
			<logic:notEmpty name="save">
				<logic:equal name="save" value="ok">
					<script>
					    alert("�ύ�ɹ���");
					    </script>
				</logic:equal>
				<logic:equal name="save" value="no">
					<script>
    				alert("�ύʧ�ܣ�");
   				 </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="iszc">
				<logic:equal name="iszc" value="iszc">
					<script>
					    alert("���û��Ѿ������ˣ���Ҫ�ظ��ύͬһ��ѧ��");
					    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
