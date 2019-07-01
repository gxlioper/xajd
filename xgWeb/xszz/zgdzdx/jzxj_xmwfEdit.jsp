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

	<%@ include file="/syscommon/pagehead_V4.ini"%>
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
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function yz(titName){
			var xmdm = document.getElementById('xmdm').value;
			if((xmdm == null) || (xmdm == "")){
				alert("��Ŀ���벻��Ϊ��!");
				return false;
			}
			document.forms[0].action = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmwfEdit&act=save";
			document.forms[0].submit();
			$("bottonSave").disabled=true;
			
		}
		
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		
		function trScJb(){
			var len = document.getElementById("flag").rows.length;
    		count=len+1; 
			var xmjb = $("xmjb").value; 
			var trl  = document.getElementsByName("jbtr").length;
			var tab = document.getElementById("flag");
			var cellfu =[
			function(data){	    
			return count;
		    },
		    function(data){	    
			return "<input type = 'text' name='jbmc' style='width:250px' align='center' maxlength='50'>";	 
		    },
		     function(data){	    
			return "<input type = 'text' name='jbje' style='width:180px'  align='center' onkeyup=ckinpdata(this) maxlength='20'>";	 
		    }
			];
			if(len<xmjb){
				 for(i=len;i<xmjb;i++){          
         			 DWRUtil.addRows(tab,[''],cellfu,{escapeHtml:false});
          			 document.getElementById("flag").rows[i].cells[0].align="center";
          			 document.getElementById("flag").rows[i].cells[1].colSpan="2";
          			 count++;
       				}
			}else{
				for(var i = 0;i<(len-xmjb);i++){
						tab.deleteRow(len-1-i);
				}
			}
		}
	</script>
</head>

<body>
	<html:form action="/zgdzdx_xszz.do?method=jzxj_xmwfEdit" method="post">
<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>����ѧ�� - ��������ά�� - ��Ŀά��</a>
				</p>
			</div>
			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	window.close();
	         	window.dialogArguments.document.getElementById("search_go").click();
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<logic:present name="have">
				<logic:match value="have" name="have">
					<script language="javascript">
	         	alert("��Ŀ�����Ѵ��ڣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<input type="hidden" id="doit" name="doit"
				value="<bean:write name="doit" scope="request"/>" />
				
				<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>��Ŀά��</span></th>
			        </tr>
			    </thead>
			    	
			 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          <button type="button" class="" id="bottonSave"
					onClick="yz();">
					����
				</button>
				<button type="button" class=""
					onClick="Close();window.dialogArguments.document.getElementById('search_go').click();">
					�ر�
				</button>
				</td>
			      </tr>
			    </tfoot>
			    	<tbody>
			      <tr>
			        <th width="25%"><font color="red">*</font>��Ŀ����</th>
			        <td width="25%">
			        	<logic:equal name="doit" value="add">
						<input type="text" id="xmdm" name="xmdm"
							style="width:50%;heigh:100%"
							value="<bean:write name="rs" property="xmdm"/>" maxlength="20">
						</logic:equal>
						<logic:notEqual name="doit" value="add">
						<input type="text" id="xmdm" name="xmdm"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xmdm"/>" readonly="readonly">
						</logic:notEqual>
			        </td>
			        <th width="25%">��Ŀ����</th>
			        <td width="25%">
			        	<input type="text" id="xmmc" name="xmmc" maxlength="100"
							style="width:100%;heigh:100%"
							value="<bean:write name="rs" property="xmmc"/>">
			        </td>
			      </tr>
			       <tr>
			        <th width="">��Ŀ��־</th>
			        <td width="">
			        	<html:select name="rs" property="xmbz">
							<html:option value="ѧ��">ѧ��</html:option>
							<html:option value="ѧ��">ѧ��</html:option>
						</html:select>
			        </td>
			        <th width="">��Ŀ�ο����</th>
			        <td width="">
			        	<input type="text" id="jlckje" name="jlckje" maxlength="20"
							
							value="<bean:write name="rs" property="jlckje"/>">
			        </td>
			      </tr>
			    <tr>
					<th>
							��ע
					</th>
					<td colspan="3">
						<input type="text" id="bz" name="bz" maxlength="200"
							style="width:450px"
							value="<bean:write name="rs" property="bz"/>">
					</td>
				</tr>
			      </tbody>
				<tr>
					<th>
							��Ŀ�ȼ�
					</th>
					<td >
						<html:select name="rs" property="xmjb" styleId = "xmjb" onchange="trScJb()">
							<html:option value="0">�޵ȼ�</html:option>
							<html:option value="2">2�ֵȼ�</html:option>
							<html:option value="3">3�ֵȼ�</html:option>
							<html:option value="4">4�ֵȼ�</html:option>
							<html:option value="5">5�ֵȼ�</html:option>
							<html:option value="6">6�ֵȼ�</html:option>
							<html:option value="7">7�ֵȼ�</html:option>
						</html:select>
					</td>
					<th>
							��Ŀ���
					</th>
					<td >
						<html:select name="rs" property="lbdm" styleId = "lbdm">
							<html:option value=""></html:option>
							<html:options collection="lbList" property="lbdm"
											labelProperty="lbmc" />
						</html:select>
					</td>
				</tr>
				<tr align="center">
					<td align="center">
						���
					</td>
					<td  colspan="2" align="center">
						��������
					</td>
					<td align="center">
						������
					</td>
				</tr>
				<tbody width="100%" class="tbstyle" id="flag" align="center">
				<logic:notEmpty name = "jbList">
				<logic:iterate id="jbl" name ="jbList">
					<tr name = "jbtr" align="center">
					<td align="center">
						<bean:write name = "jbl" property="num" />
					</td>
					<td colspan="2" align="center">
						<html:text name = "jbl" property="jbmc" style='width:250px' maxlength='50'/>
					</td>
					<td align="center">
						<html:text name = "jbl" property="jbje" maxlength='20' style='width:180px'  onkeyup="ckinpdata(this)"/>
					</td>
				</tr>
				</logic:iterate>
				</logic:notEmpty>
				
				</tbody>
			</table>
			
		</html:form>
</body>
</html:html>
