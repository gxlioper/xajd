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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getSztzData.js'></script>
    <script type='text/javascript'src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript">
		function dataSave(){
		   var userType = document.getElementById('userType').value;
		   var lddm = document.getElementById('lddm').value;
		   var lc = document.getElementById('lc').value;
		   var qsh = document.getElementById('qsh').value;
		   var dj = document.getElementsByName('dj');
		   var qsjsqk = document.getElementById('qsjsqk').value;
		   var num = 0;
		   if(userType != 'stu'){	
		   		if(lddm == '' || lddm == 'null'){
		   	   		alert('¥��Ϊ���');
		   	   		return false;
		   		}
		   		if(lc=='' || lc == 'null'){
		   	   		alert('¥��Ϊ���');
		   	   		return false;
		   		}	
		   		if(qsh == '' || qsh == 'null'){
		   	   		alert('���Һ�Ϊ���');
		   	   		return false;
		   		}				
		   }
		   for(var i=0;i<dj.length;i++){
		   		if(dj[i].checked==true){
		   			num++;
		   		}
		   }
		   if(num == 0){
		   	   alert('����ȼ�Ϊ���');
		   	   return false;
		   }
		   if(qsjsqk.length >1000){
		   	   alert('���ҽ���������ܳ���1000�֣�');
		   	   return false;
		   }
		   document.forms[0].action = "/xgxt/jhzy_gygl.do?method=xjqsSq&doType=save";
		   document.forms[0].submit();
		}		
		function toPrintOut(){//�����Ӧ�Ĵ�ӡҳ�� 

			var userType = document.getElementById('userType').value;
			var lddm = document.getElementById('lddm').value;
			var lc = document.getElementById('lc').value;
			var qsh = document.getElementById('qsh').value;
			if(userType != 'stu'){	
		   		if(lddm == '' || lddm == 'null'){
		   	   		alert('¥��Ϊ���');
		   	   		return false;
		   		}
		   		if(lc == '' || lc == 'null'){
		   	   		alert('¥��Ϊ���');
		   	   		return false;
		   		}	
		   		if(qsh == '' || qsh == 'null'){
		   	   		alert('���Һ�Ϊ���');
		   	   		return false;
		   		}			
			}
			document.forms[0].action = "/xgxt/jhzy_gygl.do?method=sqbPrint&xmk=xjqs";
			document.forms[0].target = "_blank";
	        document.forms[0].submit();
	        document.forms[0].target = "_self";
		}
	</script>
</head>
<body>
	<logic:equal value="no" name="rzxx">
		<br>
		<br>
		<br>
		<font color="red">����û����ס�����������Ǽ�����</font>
	</logic:equal>
	<logic:notEqual value="no" name="rzxx">
	<logic:equal value="yes" name="exists">
		<script language="javascript">
			alert('����ס�����Ѿ������Ǽ�����,���������,�����ظ����룡');
		</script>
	</logic:equal>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã���Ԣ���� - �Ǽ��������ҹ��� - ���� - �Ǽ�����
		</div>
	</div>
	<html:form action="/jhzy_yxlcqsz" method="post">	
		<input type="hidden" name="userType" value="<bean:write name="userType"/>" id="userType"/>
		<input type="hidden" name="userName" value="<bean:write name="userName"/>" id="userName"/>
		<logic:present name="ssbh">
			<input type="hidden" name="ssbh" value="<bean:write name="ssbh"/>" id="ssbh"/>
		</logic:present>	
		<logic:equal value="yes" name="xjqswh"> 
			<input type="hidden" name="xjqswh" value="<bean:write name="xjqswh"/>" id="xjqswh"/>
		</logic:equal>
		<table class="tbstyle" width="90%">
			<tr>
				<td width="16%" align="center">
					<font color="red">*</font>¥��
				</td>
				<logic:equal value="stu" name="userType">
					<td width="34%">
					<input type="hidden" name="lddm" value="<bean:write name="rs" property="lddm"/>">
					<html:select name="rs" property="lddm" styleId="lddm" onchange="getLcList()" disabled="true">						
						<html:options collection="ldList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>	
				</logic:equal>
				<logic:notEqual value="stu" name="userType">
					<td width="34%">
					<html:select name="rs" property="lddm" styleId="lddm" onchange="getLcList()">						
						<html:options collection="ldList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>	
				</logic:notEqual>
				<td>
					<div align="center">
						<font color="red">*</font>¥��
					</div>
				</td>
				<logic:equal value="stu" name="userType">
					<td>
					<input type="hidden" name="lc" value="<bean:write name="rs" property="lc"/>">
					<html:select name="rs" property="lc" styleId="lc" onchange="getQshList2()" disabled="true">
						<html:options collection="lcList" property="dm" labelProperty="mc" />
					</html:select>
				</td>	
				</logic:equal>
				<logic:notEqual value="stu" name="userType">
					<td>
					<html:select name="rs" property="lc" styleId="lc" onchange="getQshList2()" >
						<html:options collection="lcList" property="dm" labelProperty="mc" />
					</html:select>
				</td>	
				</logic:notEqual>	
			</tr>
			<tr>
			<td width="16%">
					<div align="center">
						<font color="red">*</font>����
					</div>
				</td>
				<logic:equal value="stu" name="userType">
					<td width="34%">
					<input type="hidden" name="qsh" value="<bean:write name="rs" property="qsh"/>">
					<html:select name="rs" property="qsh" styleId="qsh" disabled="true">
						<html:options collection="qshList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>
				</logic:equal>
				<logic:notEqual value="stu" name="userType">
					<td width="34%">
				
					<html:select name="rs" property="qsh" styleId="qsh" onchange="refreshForm('/xgxt/jhzy_gygl.do?method=xjqsSq');">
						<html:options collection="qshList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>
				</logic:notEqual>
				<td>
					<div align="center">
						ѧ��
					</div>
				</td>
				<td>
					<bean:write name="xn"/>
				</td>
				
			</tr>
			<tr>
				<td>
					<div align="center">
						<font color="red">*</font>�걨�ȼ�
					</div>
				</td>
				<td >
					&nbsp;&nbsp;&nbsp;&nbsp;<html:radio name="rs" property="dj" value="���Ǽ�" ></html:radio>���Ǽ�
					&nbsp;&nbsp;&nbsp;&nbsp;<html:radio name="rs" property="dj" value="���Ǽ�" ></html:radio>���Ǽ�
					&nbsp;&nbsp;&nbsp;&nbsp;<html:radio name="rs" property="dj" value="���Ǽ�" ></html:radio>���Ǽ�
				</td>
				<td>
					<div align="center">
						ѧ��
					</div>
				</td>
				<td>
					<bean:write name="xqmc"/>
				</td>
				
			</tr>
				<tr>
					<td>
						<div align="center">
							��ϵ�绰
						</div>
					</td>
					<td>
						<input type="text" id="lxdh" name="lxdh" maxlength="50"
							style="180px;heigh:100%"
							value="<bean:write name="rs" property="lxdh"/>">
					</td>
					<td>
						<div align="center">
							�·�
						</div>
					</td>
					<td>
                       <bean:write name="yf"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							�����������
						</div>
					</td>
					<td>
						<bean:write name="wsJc" property="anum"/>
					</td>
					<td>
						<div align="center">
							����������
						</div>
					</td>
					<td>
                      <bean:write name="wsJc" property="dbnum"/>
					</td>
				</tr>
				<tr>
				<td>
					<div align="center">
						���������������
						<br>
						<font color="red"><��1000����>
						</font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="qsjsqk" rows='8' styleId="qsjsqk" 
						style="width:100%" />
				</td>
			</tr>						
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:90%">
		<logic:notEqual value="view" name="act">
			<button class="button2" onClick="dataSave()" id="buttonSave"
			<logic:equal value="yes" name="exists"> 
			disabled='true'
			</logic:equal>
			>
				�ύ����
			</button>
		</logic:notEqual>
			<button class="button2" onClick="toPrintOut();">
				��&nbsp;&nbsp;ӡ
			</button>
			<logic:equal value="yes" name="xjqswh"> 
				<button class="button2" onClick=" Close();window.dialogArguments.document.getElementById('search_go').click();">
					��&nbsp;&nbsp;��
				</button>
			</logic:equal>
		</div>
	
	</html:form>
	</logic:notEqual>
				<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('����ɹ���');
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('����ʧ�ܣ�');
			  </script>
			</logic:equal>
</body>
<logic:present name="msg">
	<script>
			alert(''+document.getElementById('msg').value);
		</script>
</logic:present>
</html:html>
