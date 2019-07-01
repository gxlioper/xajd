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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<script language="javascript">
	function querygo(){
		 	document.forms[0].action = "/xgxt/zpxxquery.do?act=go&doType=query";
		 	document.forms[0].submit();
    }
	</script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">	
		
		function deleteuser(username) {	
		var url="/xgxt/deleteuser_jyxy.do?act=go&doType=del&username=";
		url=url+username;
		 document.forms[0].action = url;
		 document.forms[0].submit();
        }
		
		
		function adduser(){
		    var username = document.getElementById("username").value;
		    var usertype = document.getElementById("usertype").value;
		    
		    if(username==""){
		    alert("�û�������Ϊ�գ�");
		    return false;
		    }
		    if(usertype==""){
		    alert("��ָ���û����ͣ�");
		    return false;
		    }
		    		    
		    document.forms[0].action = "/xgxt/adduser_jyxy.do?act=go&doType=add";
		 	document.forms[0].submit();
		}
		
		function refreshtheweb(){
		var username = document.getElementById("username").value;
		var usertype = document.getElementById("usertype").value;
		var nj = document.getElementById("nj").value;
		var xymc = document.getElementById("xymc").value;
		var zymc = document.getElementById("zymc").value;
		var bj = document.getElementById("bj").value;
		
	    var url ="/xgxt/openJyxyweihuWeb.do?act=go&doType=refresh&username1=";
	    url+=username+"&usertype="+usertype+"&nj="+nj+"&xymc="+xymc+"&zymc="+zymc+"&bj="+bj;
	
		    
		document.forms[0].action = url;
		document.forms[0].submit();
		   
		}
		</script>
		<html:form action="/data_search" method="post">

			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ģ��ά�� - ��ҵЭ��ά��
				</div>
			</div>
			<fieldset>
				<legend>
					ָ���û�
				</legend>
				<table width="100%" class="tbstyle">
					<tr>
						<td>
						&nbsp;&nbsp;<font color="red">*</font>�û�����&nbsp;
							<html:text name="rs1" property="username" style="width:166px" />
							&nbsp;&nbsp;
							<font color="red">*</font>�û����ͣ�
							<html:select name="rs1" property="usertype" styleId="usertype"
								style="width:165px">
								<html:option value=""></html:option>
								<html:option value="����Ա">����Ա</html:option>
								<html:option value="��ʦ">��ʦ</html:option>
							</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							�� ����
							<html:select name="rs1" property="nj" styleId="nj"
								style="width:165px" onchange="refreshtheweb()">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
							     labelProperty="nj" />
							</html:select>
						</td>
						<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px" id="query_go"
									onclick="adduser()">
									�ύ
								</button>
						</td>
					</tr>
					<tr>
						<td>
							<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />���ƣ�
							<html:select name="rs1" property="xymc" styleId="xymc"
								style="width:165px" onchange="refreshtheweb()">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xymc"
							     labelProperty="xymc" />
							</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							רҵ���ƣ�
							<html:select name="rs1" property="zymc" styleId="zymc"
								style="width:164px" onchange="refreshtheweb()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zymc"
							     labelProperty="zymc" />
							</html:select>
							&nbsp;&nbsp;&nbsp;
							�༶���ƣ�
							<html:select name="rs1" property="bj" styleId="bj"
								style="width:165px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjmc"
							     labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>



					
				</table>
			</fieldset>

			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<td align="center">
									����
								</td>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v" />" />
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<td align="center" width="10%">
									<button class="button2"
										onclick="deleteuser(this.parentNode.parentNode.cells[1].innerText)"
										style="width:60px">
										ɾ��
									</button>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:notEmpty name="delete">
			<logic:equal name="delete" value="ok">
				<script>
                       alert("ɾ���ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="delete" value="no">
				<script>
                      alert("ɾ��ʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="add">
			<logic:equal name="add" value="ok">
				<script>
                       alert("�ύ�ɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="add" value="no">
				<script>
                      alert("�ύʧ�ܣ������Ƿ��ظ��ύ");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
