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
		    var zgh = "";
			var xm = "";
			var xn = "";				
			var qsjsqk="";
            var act = "";
            var lddm="";
            var qsh="";
            var lc ="";
			if($("act")){					
	           act = $("act").value;
	        }
	        if($("zgh")){					
	           zgh = $("zgh").value;
	        }
	        if($("xm")){					
	           xm = $("xm").value;
	        }	
	        if($("lddm")){					
	           lddm = $("lddm").value;
	        }
	        if($("qsh")){					
	           qsh = $("qsh").value;
	        }
	        if($("lc")){					
	           lc = $("lc").value;
	        }	
			if((zgh == null) || (zgh == "")){
				alert("ְ���Ų���Ϊ��!");
				return false;
			}
			if((xm == null) || (xm == "")){
				alert("��������Ϊ��!");
				return false;
			}	
			if((lddm==""||lddm=="null")||(lc==""||lc=="null")||(qsh==""||qsh=="null")){
			    alert("¥����¥������Ҳ���Ϊ��!");
				return false;
			}	
			if(act=="modi"){										  		
			    refreshForm("/xgxt/jhzy_gygl.do?method=fdyRzInfoAdd&doType=save");
			}else{
			   getSztzData.getInfoEx("fdyrzxxb","zgh",zgh,"1=1",function(bool){
			       if(bool){
			           alert("��ְ�����Ѿ����ڣ����ʵ���ٱ��档");
			           return false;
			       }else{
			           refreshForm("/xgxt/jhzy_gygl.do?method=fdyRzInfoAdd&doType=save");			          
			       }
			    });
			}   				   							
		}		
		
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã���Ԣ���� - ��Ϣά�� - ����Ա��ס��Ϣ - ά�� 
		</div>
	</div>
	<html:form action="/jhzy_gygl" method="post">
		<input type="hidden" name="qshV" id="qshV" />
		<input type="hidden" name="act" id="act" value="${act}" />
		<table class="tbstyle" width="100%">
			<tr>
				<td width="16%">
					<div align="center">
						ְ����
					</div>
				</td>
				<td width="34%">
					<logic:notEmpty name="act">
					   <html:hidden name="rs" property="zgh"/>					    
						<bean:write name="rs" property="zgh"/>
					</logic:notEmpty>
					<logic:empty name="act">
						<html:text name="rs" property="zgh"  maxlength="25"/>
					</logic:empty>
				</td>
				<td width="16%">
					<div align="center">
						ְ������
					</div>
				</td>
				<td width="34%">
					<html:text  name="rs" property="xm"  style="width:100px" maxlength="25"/>
				</td>
			</tr>
			<tr>
			    <td width="16%">
					<div align="center">
						�Ա�
					</div>
				</td>
				<td width="34%">
					<html:select name="rs" property="xb">
						<html:option value="Ů">Ů</html:option>
						<html:option value="��">��</html:option>							
					</html:select>
				</td>
				<td width="16%">
					<div align="center">
						¥��
					</div>
				</td>
				<td width="34%">
					<html:select name="rs" property="lddm" styleId="lddm" onchange="getLcList()" >						
						<html:options collection="ldList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>				
			</tr>
			<tr>
				<td>
					<div align="center">
						ְ��
					</div>
				</td>
				<td>
					<html:text  name="rs" property="zw" maxlength="15"/>
				</td>
				<td>
					<div align="center">
						¥��
					</div>
				</td>
				<td>
					<html:select name="rs" property="lc" styleId="lc" onchange="GetBlQshList()" >
						<html:options collection="lcList" property="dm" labelProperty="mc" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
					<html:select name="rs" property="xydm">
						<html:option value="">--��ѡ��--</html:option>
						<html:options collection="xyList" labelProperty="xymc"
							property="xydm" />
					</html:select>
				</td>				
				<td width="16%">
					<div align="center">
						����
					</div>
				</td>
				<td width="34%">
					<html:select  name="rs" property="qsh" styleId="qsh" >
						<html:options collection="qshList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>
			</tr>
			<tr>				
				<td>
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td>
					<html:text  name="rs" property="lxdh" maxlength="20"/>
				</td>
				<td>
					<div align="center">						
					</div>
				</td>
				<td>					
				</td>				
			</tr>								
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">		
			<logic:notEqual value="view" name="act">			
				<button class="button2" onClick="dataSave()" id="buttonSave">
					��&nbsp;&nbsp;��
				</button>	
			&nbsp;&nbsp;
			</logic:notEqual>
			<button class="button2" onClick="Close();">
				��&nbsp;&nbsp;��
			</button>
		</div>	
	</html:form>
				<logic:equal value="true" name="done">
			  <script type="text/javascript">
			    alert('����ɹ���');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="done">
			  <script type="text/javascript">
			    alert('����ʧ�ܣ�');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>
</body>
<logic:present name="msg">
	<script>
			alert(''+document.getElementById('msg').value);
		</script>
</logic:present>
</html:html>
