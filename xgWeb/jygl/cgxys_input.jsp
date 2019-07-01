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
		document.forms[0].action = "/xgxt/cgjyxys.do?act=add&doType=save";
		document.forms[0].submit();
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
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/jyxxtjwh.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã���ҵ���� - ��ҵ��Ϣ - ��ҵ��Ϣ ͳ��ά��
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    alert("�������ѧ����Ч!");
    </script>
				</logic:equal>
				<html:hidden name="rs" property="nd" />

				<table width="100%" id="rsT" class="tbstyle">
					<thead>
					</thead>
					<tr style="height:22px">
							<td align="right" style="width=18%">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left" style="width=32%">
								<html:text name='rs' property="xh" styleId="xsxh"
									style="width:210px"/>
								<button onclick="showTopWin('/xgxt/jyxyTurnInfo.do?cgxys=cgxys',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						<td align="right" style="width:20%">
							<font color="red">*</font>������
						</td>
						<td align="left">
							<html:text name="rs" property="xm"  style="width: 210px"/>
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							רҵ���ƣ�
						</td>
						<td align="left">
							<html:text name="rs" property="zymc" 
								style="width:210px" />
						<td align="right">
							��ͥ��ַ��
						</td>
						<td align="left">
							<html:text name="rs" property="jtdz" 
								style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							�ع�Э����ԭ��
						</td>
						<td align="left">
							<html:select name="rs" property="zgxysyy" style="width:210px">
								<html:option value=""></html:option>
								<html:option value="Э����">Э����</html:option>
								<html:option value="Э����ʧ">Э����ʧ</html:option>
								<html:option value="��Ϣ����">��Ϣ����</html:option>
							</html:select>
						</td>
						<td align="right">
							��Э�����ţ�
						</td>
						<td align="left">
							<html:text name="rs" property="xxysbh" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							ԭǩԼ��λ��
						</td>
						<td align="left">
							<html:text name="rs" property="yqydw" style="width:210px" />
						</td>
						<td align="right">
							��λ���ʣ�
						</td>
						<td align="left">
							<html:text name="rs" property="dwxz" style="width:210px" />
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right">
							��ҵ��λ��
						</td>
						<td align="left">
							<html:text name="rs" property="jygw" style="width:210px" />
						</td>
						<td align="right">
							Э����ԭ��
						</td>
						<td align="left">
							<html:select name="rs" property="xyjcyy" style="width:210px" >
								<html:option value=""></html:option>
								<html:option value="�������ϲ���">�������ϲ���</html:option>
								<html:option value="�������ϴ�">�������ϴ�</html:option>
								<html:option value="������Ϣ����">������Ϣ����</html:option>
								<html:option value="���˵�λ��Ϣ����">���˵�λ��Ϣ����</html:option>
								<html:option value="���˵�λ���ܲ�����Ϣ����">���˵�λ���ܲ�����Ϣ����</html:option>
							</html:select>
						</td>
					</tr>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="savejyxy()">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();window.dialogArguments.document.getElementById('search_go').click();">
						�ر�
					</button>
					
				</div>
			</logic:notEmpty>
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
