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
			<logic:equal value="ok" name="inserted">
				<script type="text/javascript">
					alert('�����ɹ�!');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script type="text/javascript">
					alert('����ʧ��!');
				</script>
			</logic:equal>
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
			<logic:equal value="reinsert" name="inserted">
				<script type="text/javascript">
					alert('�ļ��ϴ�ʧ�ܣ������ļ����Ƿ��ظ�');
				</script>
			</logic:equal>
			<logic:equal name="alert" value="cannot">
				<script type="text/javascript">
					alert('�ļ��ϴ�ʧ�ܣ��ļ���СӦ������10M����!');
				</script>
			</logic:equal>
	<div class="title">
    <div class="title_img" id="title_m"> �� �� �� �� �� ��</div> 
    </div>
  <fieldset> 
  <legend> &nbsp;&nbsp;һ�����ڷ������ĵ�&nbsp;&nbsp; </legend> 
  <logic:iterate id="v" name="allrs" offset="0" >
     <logic:iterate id="t" name = "v" property="wdzlrs" offset="1">
     		 <legend><bean:write name = "t"/>&nbsp;&nbsp; </legend> 
     </logic:iterate> 
    
	<table border="0" id="rsTable" width="100%">
	 <logic:notEmpty  name = "v" property="wdrs">
  		<logic:iterate id="s" name = "v" property="wdrs">
    	<tr onmouseover="rowOnClick(this)">
    	<logic:iterate id="k" name="s" offset="2" length="1">
											<td> <a  target="_blank"> &nbsp;&nbsp;<bean:write name = "k"/> </a> </td> 
		</logic:iterate>
		<logic:iterate id="k1" name="s" offset="3" length="1">										
			<td align=right> <a href="szdw_wdgl.do?method=downLoadFile&dir=<bean:write name = "k1"/>" >���ظ���</a>/<a href="#" onclick="if(confirm('ȷʵҪɾ����'))location='szdw_wdgl.do?method=delFile&dir=<bean:write name = "k1"/>'" >ɾ��</a> </td>
       </logic:iterate> 
	</tr>
    	</logic:iterate>
     </logic:notEmpty>  
    <tr onmouseover="rowOnClick(this)"> 
    	<logic:iterate id="l" name = "v" property="wdzlrs" offset="0" length="1" >
       <td align=right colspan="2" width="100%"><a  align=right style="color=blue" href="#" onclick="showTopWin('szdw_wdgl.do?method=wdzldd&wdzldm=<bean:write name = "l"/>',800,600)" >�鿴����ȫ���ļ�</a> </td>
       </logic:iterate>
    </tr>
   
  </table>
  
  </logic:iterate>
    </fieldset> 
   
  <fieldset> 
  <legend> �����Ϣ </legend> 
    <input type="hidden" name="isModi" id="isModi" value="no" /> 
    <input type="hidden" name="documentId" id="documentId" value="" />
    <input type="hidden" name="type" id="type" value="fdykhpy" />
  <TABLE class="tbstyle" width="100%"> 
  <TR>
      <TD align=right width="100"> �ļ����ͣ� </TD> 
      <TD align=left colspan="3"> 
      <html:select property="wjlx" style="width:180px" styleId="wjlx" >
										<html:options collection="lxrs" property="wdzldm"
											labelProperty="wdzlmc" />
		</html:select>
	  </TD> 
    </TR>
    <TR>
      <TD align=right width="100"> �ļ����⣺ </TD> 
      <TD align=left colspan="3"> <input type="text" name="title" id="title" style="width:80%" value=""> </TD> 
    </TR> 
    <tr>
						<td align="center">
							   &nbsp;&nbsp;&nbsp;&nbsp;������
						</td>
						<td align="left" colspan="3">
							<input type="file" name="uploadFile" style="width:60%" id="kk" >
							&nbsp;&nbsp;
							<font color="red">(�ļ���С&lt;10M&gt;)</font>
						</td>
					</tr>
    <TR> 
      <TD colspan=4 align=center> <button type="button" class="button2" onclick="showTips('�ļ������У����Ժ�...');refreshForm('szdw_wdgl.do?method=wdwjSave');"> �� �� </button>
        <INPUT type=reset name=b2 value="����" class="button2"> </TD> 
    </TR> 
  </TABLE> 
  </fieldset> 
</html:form>
</body>
</html>
