<%@ page language="java" contentType="text/html; charset=gb2312"%>
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
		<title>��ҵ������Ϣϵͳ</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
		<base target="_self">
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript">
	
	function add(){
	 var xh = document.getElementById("xh").value;
	 var xsxm = document.getElementById("xsxm").value;
	 var zymc = document.getElementById("zymc").value;
     var email = document.getElementById("email").value;
     var lxdh = document.getElementById("lxdh").value;
 
     if(xh==""){
     alert("ѧ�Ų���Ϊ�գ�");
     return false;
     }
     //if(!isNumber(num)){
     //alert("���ӦΪ���֣�");
     //return false;
     //}
     if(xsxm==""){
     alert("��������Ϊ�գ�");
     return false;
     }
     if(zymc==""){
     alert("רҵ���Ʋ���Ϊ�գ�");
     return false;
     }
     //if(isNumber(name)){
     //alert("��������Ϊ���֣�");
     //return false;
     //}
     if(lxdh!=""&&!isNumber(lxdh)){
     alert("��ϵ�绰ӦΪ���֣�");
     return false;
     }  
     if((email != null) && (email != "") && (!isEmail(email))){
     alert("�������䲻�Ϸ���");
     return false;
     }

     var zxnr = document.getElementById("zxnr").value;
		if(zxnr.length>600){
			alert("��ѯ���ݲ��ܳ���600������");
			return false;
		}
		
		 document.forms[0].action = "/xgxt/jyglxszxsq.do?act=savesq";
		 document.forms[0].submit();
        
    }
    
    //exclude left and right space; 
	function trim(s){
 		return rtrim(ltrim(s)); 
	}
	//exclude left space; 
	function ltrim(s){
 		return s.replace( /^\s*/, ""); 
	} 
	//exclude right space; 
	function rtrim(s){ 
 		return s.replace( /\s*$/, ""); 
	}
    
    function isEmail(s){
	s = trim(s); 
 	var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	return p.test(s);
    }
    
    function isNumber(s){
	var p = /^(-|\+)?\d+$/;
	return p.test(s); 
    } 
	
	function winclo(){
		dialogArgumentsQueryChick();;
	   	window.Close();
	}
	</script>

	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ְҵ��ѯ - ������ѯ����
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="center" colspan="4" height="">
							<b>������ѯ�ǼǱ�</b>
						</td>
					</tr>
				</thead>
				<tr>
					<td width="15%">
						ѧ��<font color="red">*</font>��
					</td>
					<td width="35%">
						<html:text name="rs" property="xh" maxlength="30" style="width=70%"/>
					</td>
					<td width="15%">
						����<font color="red">*</font>��
					</td>
					<td>
						<html:text name="rs" property="xsxm" maxlength="20" style="width=55%"/>
					</td>
				</tr>
				<tr>
					<td align="">
						���䣺
					</td>
					<td>
						<html:text name="rs" property="age" maxlength="2" style="width=70%"/>
					</td>
					<td align="">
						�Ա�
					</td>
					<td>
						<html:select name="rs" property="xb">
							<html:option value="��">��</html:option>
							<html:option value="Ů">Ů</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td align="" >
						רҵ����<font color="red">*</font>��
					</td>
					<td>
						<html:text name="rs" property="zymc" maxlength="28" style="width=70%"/>
					</td>
					<td align="">
						��������<font color="red">*</font>��
					</td>
					<td>
						<html:text name="rs" property="email" style="width=55%" maxlength="38"/>
					</td>
				</tr>
				<tr>
					<td align="">
						��ϵ�绰<font color="red">*</font>��
					</td>
					<td>
						<html:text name="rs" property="lxdh" style="width=70%" maxlength="28"/>
					</td>
					
				</tr>
				<tr>
					<td align="">
						��ѯ���ݣ�
					</td>
					<td colspan="3">
						<html:textarea name="rs" property="zxnr" rows="12"
							style="width=100%" />
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button class="button2" onclick="add()" style="width:80px">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" type="button" style="width:80px" onclick="winclo();">
					�ر�
				</button>
			</div>
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
		</logic:notEmpty>
	</body>
</html>

