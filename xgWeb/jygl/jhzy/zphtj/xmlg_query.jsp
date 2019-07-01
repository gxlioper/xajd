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
<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
<script language="javascript" src="js/function.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
<script language="javascript">
	function querygo(){
		 	document.forms[0].action = "/xgxt/xmlg_bysdalx.do?method=bysdalx&go=go";
		 	document.forms[0].submit();
    }
    /*
	ȫ��ѡ��
	*/    
      function chec(){
         for(i=0;i<document.getElementsByName("pk").length;i++){
      	    document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
         }
      }		
      /*
	����ɾ��
	*/
      function del(url){
	    var RowsStr="!!#!!";
	
	    for (i=0; i<document.getElementsByName("pk").length; i++){
    	   if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	   }
	    }
	    document.forms[0].pkstring.value = RowsStr;
	       if (RowsStr=="!!#!!"){
	         alert("��ѡ��Ҫ����ɾ���ļ�¼��");
		   return false;
    	}
	
	    if (!confirm("ȷ��Ҫɾ����ѡ��¼��")){
		   return false;
	    }
	    document.forms[0].action=url;
        document.forms[0].submit();
     }

		function zphcsUpdate(){
			var usertype = document.getElementById("userType").value;
			if("xy"==usertype){
				xmlgxysh();
			}else if("xx"==usertype || "admin"==usertype){
				xmlgxxsh();
			}else{
				var url ="/xgxt/xmlg_bysdalx.do?method=bysdalx&doType=add&pkValue=";
				var pkValue ="";
				if (curr_row == null) {
					alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
					return false;
					 } else {
					//if (confirm("ȷ��Ҫ�޸ĸ���������")) {
						 pkValue = curr_row.getElementsByTagName("input")[0].value;
				         url += pkValue;
				         showTopWin(url, 800, 600);
						return true;
					//} else {
					//	return false;
					//}
				   }
			}
	    }	
		
		function dataExport() {
	       document.forms[0].action = "/xgxt/zphcs.do?act=expdata";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
        
		function zphcsAdd(){
			var usertype = document.getElementById("userType").value;
			if("stu" == usertype){
				showTopWin('/xgxt/xmlg_bysdalx.do?method=bysdalx&doType=add',800,600);
			}else{
				alert("��ҳ��ֻ����ѧ����������");
				return false;
			}
		}
		function xmlgxysh(){
			var url ="/xgxt/xmlg_bysdalx.do?method=xmlgsh&doType=add&sh=xy&pkValue=";
			var pkValue ="";
			if (curr_row == null) {
				alert("��ѡ��Ҫ��˵����ݣ�\n��������Ӧ���У�");
				return false;
				 } else {
				//if (confirm("ȷ��Ҫ�޸ĸ���������")) {
					 pkValue = curr_row.getElementsByTagName("input")[0].value;
			         url += pkValue;
			         showTopWin(url, 800, 600);
					return true;
				//} else {
				//	return false;
				//}
			   }
		}
		function xmlgxxsh(){
			var url ="/xgxt/xmlg_bysdalx.do?method=xmlgsh&doType=add&sh=xx&pkValue=";
			var pkValue ="";
			if (curr_row == null) {
				alert("��ѡ��Ҫ��˵����ݣ�\n��������Ӧ���У�");
				return false;
				 } else {
				//if (confirm("ȷ��Ҫ�޸ĸ���������")) {
					 pkValue = curr_row.getElementsByTagName("input")[0].value;
			         url += pkValue;
			         showTopWin(url, 800, 600);
					return true;
				//} else {
				//	return false;
				//}
			   }
		}
		function check_user()
		{
			var user=document.all['userType'].value;
			if("xy"==user)
			{
			document.getElementById('xydm').disabled=true;
			}
			else if("xx"==user)
			{
				document.getElementById('xydm').disabled=false;
			}
		}
		
		function printxmlg(){
			var url = "/xgxt/xmlg_bysdalx.do?method=bysdalx&doType=print&pkValue=";
			var pkValue ="";
			if (curr_row == null) {
				alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
				return false;
				 } else {
					 pkValue = curr_row.getElementsByTagName("input")[0].value;
			         url += pkValue;
				}
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}

		</script>
<body onload=""> 
<html:form action="/jhzyzphcs" method="post">
	<input type="hidden" id="realTable" name="realTable"
		value="<bean:write name='realTable' scope="request" />" />
	<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
	<input type="hidden" name="pkstring" value="" />
	<div class="title">
	<div class="title_img" id="title_m">��ǰ����λ�ã���ҵ���� - ѧ����Ϣ - ��ҵ��������У�鿴</div>
	</div>
	<fieldset><legend> �� ѯ </legend>

	<table width="100%" class="tbstyle">
		<thead>
			<tr style="cursor: hand">
			<logic:equal value="stu" name="userType" scope="session">
				<td>������ <html:text property="xm" readonly="true"></html:text> &nbsp;&nbsp;&nbsp; 
				ѧ�ţ� <html:text property="xh" readonly="true"></html:text>
				</td>
				<td width="10" rowspan="2" align="center" valign="middle">
				<input type="hidden" name="go" value="go" />
				<button class="button2" style="height: 40px" onclick="querygo()"
					id="search_go">��ѯ</button>
				</td>
				</logic:equal>
				<logic:equal value="xy" name="userType" scope="session">
				<td>������ <html:text property="xm"></html:text> &nbsp;&nbsp;&nbsp; 
				ѧ�ţ� <html:text property="xh"></html:text>
				רҵ��<html:select property="zydm"  style="width:160px" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zymc"
								labelProperty="zymc" />
				</html:select><br>
				<bean:message key="lable.xsgzyxpzxy" />��<input name="xydm" disabled="disabled" value="<bean:write name="bmmc" scope="session"/>" />
				</td>
				<td width="10" rowspan="2" align="center" valign="middle">
				<input type="hidden" name="go" value="go" />
				<button class="button2" style="height: 40px" onclick="querygo()"
					id="search_go">��ѯ</button>
				</td>
				</logic:equal>
				<logic:equal value="xx" name="userType" scope="session">
				<td>������ <html:text property="xm"></html:text> &nbsp;&nbsp;&nbsp; 
				ѧ�ţ� <html:text property="xh"></html:text>
				רҵ��<html:select property="zydm"  style="width:160px" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zymc"
								labelProperty="zymc" />
				</html:select>
				</td>
				<td width="10" rowspan="2" align="center" valign="middle">
				<input type="hidden" name="go" value="go" />
				<button class="button2" style="height: 40px" onclick="querygo()"
					id="search_go">��ѯ</button>
				</td>
				</logic:equal>
				<logic:equal value="admin" name="userType" scope="session">
				<td>������ <html:text property="xm"></html:text> &nbsp;&nbsp;&nbsp; 
				ѧ�ţ� <html:text property="xh"></html:text>
				רҵ��<html:select property="zydm"  style="width:160px" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zymc"
								labelProperty="zymc" />
				</html:select>
				</td>
				<td width="10" rowspan="2" align="center" valign="middle">
				<input type="hidden" name="go" value="go" />
				<button class="button2" style="height: 40px" onclick="querygo()"
					id="search_go">��ѯ</button>
				</td>
				</logic:equal>
			</tr>
			<tr>

			</tr>
		</thead>
	</table>
	</fieldset>
	<logic:equal name="rsNum" value = "0">
		<br />
		<br />
		<p align="center">δ�ҵ��κμ�¼��</p>
	</logic:equal>
	<logic:notEqual name="rsNum" value = "0">
		<fieldset><legend> ��¼���� <bean:write name="rsNum" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ</font>
		</legend>
		<table width="100%" id="rsTable" class="tbstyle">
			<thead>
				<tr align="center" style="cursor: hand">
					<td><input type="checkbox" name="qbxz" value="all"
						onclick="chec('qbxz')"></td>
					<logic:iterate id="tit" name="topTr" offset="1" length="12">
						<td id="<bean:write name="tit" property="en"/>">
							<bean:write name="tit" property="cn" /></td>
					</logic:iterate>
				</tr>
			</thead>
			<logic:iterate name="rs" id="s">
				<tr onclick="rowOnClick(this)" style="cursor: hand" ondblclick="zphcsUpdate();">
					<logic:iterate id="v" name="s" offset="0" length="1">
						<input type="hidden" value="<bean:write name="v"/>" />
					</logic:iterate>
					<td align="center">
					<logic:iterate id="v" name="s" offset="0" length="1">
						<input type="checkbox" name="pk" value="<bean:write name="v"/>">
					</logic:iterate></td>
					<logic:iterate id="v" name="s" offset="1" >
						<td align="center"><bean:write name="v" /></td>
					</logic:iterate>
				</tr>
			</logic:iterate>
		</table>
		</fieldset>
	</logic:notEqual>



	<div class="buttontool" id="btn"
		style="position: absolute; left: 0px; top: 100px" width="98%">
	&nbsp;
	<logic:equal value="xy" name="userType" scope="session">
	<button class="button2" onclick="xmlgxysh()" style="width: 80px">
				<bean:message key="lable.xsgzyxpzxy" />���</button>
		</logic:equal>
		<logic:equal value="xx" name="userType" scope="session">
	<button class="button2" onclick="xmlgxxsh()" style="width: 80px">
				ѧУ���</button>
		</logic:equal>
		<logic:notEqual value="xx" name="userType" scope="session">
		<logic:equal value="admin" name="userType" scope="session">
			<button class="button2" onclick="xmlgxxsh()" style="width: 80px">
				ѧУ���</button>
				</logic:equal>
		</logic:notEqual>
	&nbsp;
	<button class="button2" onclick="zphcsAdd()" style="width: 80px">
				����</button>
	&nbsp;
	<button class="button2" onclick="zphcsUpdate()"
		style="width: 80px">�� ��</button>

		<logic:notEqual value="stu" name="userType" scope="session">
	<button class="button2" onclick="del('/xgxt/xmlg_bysdalx.do?method=bysdalx&doType=del')"
		style="width: 80px">ɾ��</button>
		</logic:notEqual>
		&nbsp;
					<button class="button2" onclick="printxmlg();" style="width:80px"
						id="buttonClose">
						��ӡ
					</button>
	</div>

	<logic:notEmpty name="done">
		<logic:equal name="done" value="ok">
			<script>
                      alert("ɾ���ɹ�!");
                      //document.getElementById('search_go').click();
                    </script>
		</logic:equal>
			<logic:equal name="done" value="no">
			<script type="text/javascript">
                      alert("ɾ��ʧ��!");
                      //document.getElementById('search_go').click();
                    </script>
				</logic:equal>
	</logic:notEmpty>
</html:form>
<script type="text/javascript" src="js/bottomButton.js"></script>
</body>
</html>
