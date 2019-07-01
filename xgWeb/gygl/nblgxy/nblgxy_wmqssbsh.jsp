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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>	
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>	
	
		<html:form action="/nblgxy_gygl" method="post">
			<input type="hidden" name="qshV" id="qshV" />
			<input type="hidden" name="pkValue" id="pkValue"
			value="<bean:write name="pkValue" scope="request"/>"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ��Ԣ���� - ��� - �����������
				</div>
			</div>
			<logic:equal value="true" name="done">
				<script type="text/javascript">
	            alert("����ɹ���");
	            Close();
				window.dialogArguments.document.getElementById('search_go').click();
	            </script>
			</logic:equal>
			<logic:equal value="false" name="done">
				<script type="text/javascript">
	            alert("����ʧ�ܣ�");
	            Close();
				window.dialogArguments.document.getElementById('search_go').click();
	           </script>
			</logic:equal>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
               <fieldset>
                  <legend>
                  <bean:write name="rs" property="xn" />ѧ�꣬��<bean:write name="rs" property="xq" />ѧ�ڣ�
                  <bean:write name="rs"  property="ldmc" /> <bean:write name="rs" property="qsh" />�ң���סѧ��Υ�ʹ�����Ϣ                
                  </legend>
                   <table width="100%" class="tbstyle">
                  <tr>
						<td align="left">
							Ŀǰ��ס��������<bean:write name="rs1"  property="rzrs" />��,
							��Υ�ʹ��ּ�¼������<bean:write name="rs1"  property="wjrs" />��
						</td>						
					</tr>
					</table>
               </fieldset>
                <fieldset>
                  <legend>
                  <bean:write name="rs2" property="xn" />ѧ�꣬������������(��λ����)�������������Ϣ<bean:write name="rs2" property="noSet" /> 
                  </legend>
                   <table width="100%" class="tbstyle">
                  <tr>
					<td align="left">					
					            <input type="hidden" name="blSet" id="blSet" value="<bean:write name="rs2" property="Set" />">
								<input type="hidden" name="sxbz" id="sxbz" value="<bean:write name="rs2" property="sxbz" />">
								�������ұ�����
								<bean:write name="rs2" property="bz" />
								�� ���������ͨ����
								<bean:write name="rs2" property="ybqss" />
								�� �������������ޣ�
								<bean:write name="rs2" property="yxqss" />
								�� ����������
								<bean:write name="rs2" property="qszs" />	
					</td>
				</tr>					
					</table>
               </fieldset>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								�������
							</td>
						</tr>
					</thead>
					<tr>
						<td align="right">
							¥�����ƣ�
						</td>
						<td>						 
						<bean:write name="rs"  property="ldmc" />
						</td>
						<td align="right">
							ѧ�꣺
						</td>
						<td align="left">
							<bean:write name="rs" property="xn" />
						</td>
					</tr>
					<tr>
						<td align="right">
							���Һţ�
						</td>
						<td align="left">
							<bean:write name="rs" property="qsh" />
						</td>
						<td align="right">
							ѧ�ڣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="xq" />
						</td>
					</tr>
					<tr>
						<td align="right">
							����ʱ�䣺
						</td>
						<td align="left">
							<bean:write name="rs" property="pysj" />
						</td>
						<td align="right">
							�����ˣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="xm" />
						</td>
					</tr>
					<tr>
						<td align="right">
							�������
						</td>
						<td align="left">
							<bean:write name="rs" property="lbmc" />
						</td>
						<td align="right">
						<font color="red"><bean:write name="shtype"
									scope="request" />���</font>��
						</td>
						<td align="left">
							<html:select name="rs" property="yesNo" style="width:80px"
								styleId="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							��ע��
						</td>
						<td align="left" colspan="3">
							<bean:write name="rs" property="bz"/>
						</td>
					</tr>
				</table>
			</logic:notEmpty>
			<div class="buttontool" align="center">
					<button class="button2"
						onclick="shDataSave(this)"
						style="width:80px" id="buttonSave">
						�� ��
					</button>	
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="Close();return false;"
						style="width:80px" id="buttonSave">
						�� ��
					</button>				
			</div>
		</html:form>	
	</body>
   <script type="text/javascript">
     function shDataSave(obj){
        var sxbz  = ($("sxbz").value);
        var blSet = ($("blSet").value);
       if(blSet=="yes"){ 
         if(sxbz=="1"){
           if($("yesNo").value=="ͨ��"){
                alert("�����������Ѵﵽ�򳬹����ޣ���ǰ\'ͨ��\'�����Ч��");
                return false;
            }else{
              if(confirm("ȷ��\'"+$("yesNo").value+"\'�������룿")){
                   refreshForm('/xgxt/nblgxy_gygl.do?method=wmqsSbShSave');
                   obj.disabled="true";
              }else{
                   return false;
              }            
           }
         }else{
            if(confirm("ȷ��\'"+$("yesNo").value+"\'�������룿")){
                  refreshForm('/xgxt/nblgxy_gygl.do?method=wmqsSbShSave');
                  obj.disabled="true";
            }else{
                  return false;
            }            
        }
       }else if(blSet=="no"){
         alert("�����ڲ��������������������ұ�����");
       }
     }
</script>
</html>
