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
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/sxjyFunction.js"></script>
<script language="javascript">
	
</script>

<body> 
<html:form action="/szdw_wdgl" method="post" enctype="multipart/form-data">
			<logic:equal value="yes" name="del">
				<script type="text/javascript">
					alert('ɾ���ɹ�!');
				</script>
			</logic:equal>
			<logic:equal value="no" name="del">
				<script type="text/javascript">
					alert('ɾ��ʧ��!');
				</script>
			</logic:equal>
	<div class="title">
    <div class="title_img" id="title_m"> �� �� �� �� �� ��</div> 
    </div>
  <fieldset> 
  <legend> &nbsp;&nbsp;����ȫ���ĵ�&nbsp;&nbsp; </legend> 
  <input type = "hidden" name ="wdzldm" value ="<bean:write name ="wdzldm"/>"/> 
  <logic:notEmpty  name = "rs">
 	 <table border="0" id="rsTable" width="100%">
  	<logic:iterate id="v" name="rs">
    	<tr onmouseover="rowOnClick(this)">
    	<logic:iterate id="k" name="v" offset="2" length="1">
											<td> <a  target="_blank"> &nbsp;&nbsp;<bean:write name = "k"/> </a> </td> 
		</logic:iterate>
		<logic:iterate id="k1" name="v" offset="3" length="1">										
			<td align=right> <a href="szdw_wdgl.do?method=downLoadFile&dir=<bean:write name = "k1"/>" >���ظ���</a> </td>
       </logic:iterate> 
		</tr>
     </logic:iterate>
  </table>
  </logic:notEmpty>  
  
    </fieldset>  
</html:form>
</body>
</html>
