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
		<title><bean:message key="lable.title" /></title>
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
	<script type="text/javascript">
	     function hiddenKm(){
	        var sfjl = "";
	        if($("sfjlxm"))sfjl= $("sfjlxm").value;	        
	        if(sfjl=="��"){
	          if ($("kmdm")) $("kmdm").disabled="true";
	          if($("kmdm"))$("kmdm").value= "";
	        }else{
	          if ($("kmdm")) $("kmdm").disabled=false;
	        }
	     }
	    function tzInfoAddSave(){	          
	       if($("sfjlxm")){
	         if($("sfjlxm").value=="��"){
	            if ($("kmdm")&&$("kmdm").value==""){
	                alert("������Ŀ����Ϊ�գ�");
	                return false;
	            }
	         }
	       } 
	       if($("xn")&&$("xn").value==""){
	         alert("ѧ�겻��Ϊ�գ�");
	         return false;
	       }
	       if($("xq")&&$("xq").value==""){
	         alert("ѧ�ڲ���Ϊ�գ�");
	         return false;
	       }                
	       if($("xmmc")&&$("xmmc").value==""){
	         alert("��Ŀ���Ʋ���Ϊ�գ�");
	         return false;
	       } 
	       if($("sj")&&$("sj").value==""){
	         alert("����ʱ�䲻��Ϊ�գ�");
	         return false;
	       } 	       
	       if($("bz")&&$("bz").value.length>300){
	         alert("��ע�������ܳ���300�֣�");
	         return false;
	       } 	               	                       
           refreshForm("/xgxt/nbzy_sztz.do?method=tzInfoModi&doType=Save");
           $('buttonSave').disabled=true;
	   }      
	</script>
	<body onload="hiddenKm()">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/nbzy_sztz.do" method="post">	
		   <input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" />" />	
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�������չ - ������չ��Ϣ - ��Ϣά�� 
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr align="center">
						<td height="22" colspan="4">
							����������չ��Ϣ
						</td>
					</tr>
				</thead>
				<tr>
					<td height="22" align="right" style="width: 10%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td height="22" align="left" style="width: 30%">
						<html:text name="rs" property="xh" styleId="xh" maxlength="15" disabled="true"/>
						
					</td>
					<td height="22" align="right" style="width: 18%">
						�Ƿ�����Ŀ��
					</td>
					<td height="22" align="left">
						<html:select name="rs" styleId="sfjlxm" property="sfjlxm"
							onchange="hiddenKm()" style="background-color:#FFFFFF;">
							<html:option value="��">��</html:option>
							<html:option value="��">��</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						������
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<td height="22" align="right">
						<font color="red">*</font>������Ŀ��
					</td>
					<td height="22" align="left">
						<html:select name="rs" styleId="kmdm" property="kmdm"
							style="background-color:#FFFFFF;">
							<html:option value=""></html:option>
							<html:options collection="kmdmList" property="kmdm"
								labelProperty="kmm"></html:options>
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�Ա�
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="xb" />
					</td>
					<td height="22" align="right">
						<font color="red">*</font>ѧ�꣺
					</td>
					<td height="22" align="left">
						<html:select name="rs" property="xn" style="background-color:#FFFFFF"
							styleId="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�꼶��
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="nj" />
					</td>
					<td height="22" align="right">
						<font color="red">*</font>ѧ�ڣ�
					</td>
					<td height="22" align="left">
						<html:select name="rs" property="xq" style="background-color:#FFFFFF"
							styleId="xq">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<td height="22" align="right">
						<font color="red">*</font>��Ŀ���ƣ�
					</td>
					<td height="22" align="left">
						<html:text name="rs" property="xmmc" styleId="xmmc" style="width:250px;"
							maxlength="50" />
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						רҵ��
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="zymc" />
					</td>
					<td height="22" align="right" style="width: 15%">
						����ɼ���
					</td>
					<td height="22" align="left">
						<html:text name="rs" property="cj" styleId="cj" style="width:250px;"
							maxlength="50" />
					</td>
				</tr>
				<tr>
					<td height="22" align="right">
						�༶��
					</td>
					<td height="22" align="left">
						<bean:write name="rs" property="bjmc" />
					</td>
					<td height="22" align="right" style="width: 15%">
						<font color="red">*</font>����(��)ʱ�䣺
					</td>
					<td height="22" align="left">
						<html:text name="rs" property="sj" styleId="sj" onblur="dateFormatChg(this)"
							style="cursor:hand;width:80px;"
							onclick="return showCalendar('sj','y-mm-dd');" readonly="true" />
					</td>
				</tr>
				<tr align="left">
					<td align="right">
						��ע:
						<br />
						(��300��)

					</td>
					<td colspan="4">
						<html:textarea name="rs" property="bz" style="width:570px" rows="4" />
					</td>
				</tr>
			</table>
			<div class="buttontool" id="button" align="center">
			 <logic:notEqual value="view" name="act">
				<button class="button2" onclick="tzInfoAddSave()" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
				</logic:notEqual>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonSave">
					�� ��
				</button>
			</div>
			<logic:equal value="true" name="done">
				<script type="text/javascript">
				alert("�����ɹ���");
				Close();
				dialogArgumentsQueryChick();
			</script>
			</logic:equal>
			<logic:equal value="false" name="done">
				<script type="text/javascript">
				alert("����ʧ��,��ϵͳ���Ѵ������\"*\"����Ŀ��ͬ�ļ�¼��������������ݺ����ύ��");
			</script>
			</logic:equal>
		</html:form>

	</body>

</html>
