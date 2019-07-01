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
	<script language="javascript" src="js/calendar.js"></script>
	<script language="javascript" src="js/calendar-zh.js"></script>
	<script language="javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
		function dataSave(){
		    var xh = "";
		    var act = "";
	        if($("xh")){
	           xh = $("xh").value;
	        }
	        if($("act")){
	           act = $("act").value;
	        }
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			if(act=="modi"){
				refreshForm("/xgxt/jhzy_gygl.do?method=zghryInfoAdd&doType=save");	   							
			} else {
				getSztzData.getInfoEx("zghryxxb","xh",xh," 1=1",function(bool){
			       if(bool){
			           alert("�Ѵ��ڸ�����Ϣ�����ܱ��棡");
			           return false;
			       }else{
			          refreshForm("/xgxt/jhzy_gygl.do?method=zghryInfoAdd&doType=save");			          
			       }
			    });
			}
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã���Ԣ���� - ��Ϣά�� - �Թܻ���Ա��Ϣ - ά�� 
		</div>
	</div>
	<html:form action="/jhzy_gygl" method="post">
		<html:hidden name="rs" property="guid"/>
		<input type="hidden" id="url" name="url"
			value="/jhzy_gygl.do?method=zghryInfoAdd" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xymc-zymc-bjmc-nj" />
		<input type="hidden" name="act" id="act" value="${act}" />
		<table class="tbstyle" width="100%">
			<tr>
				<td align="center" width="16%">
					<font color="red">*</font>ѧ��
				</td>
				<td align="left" width="34%">
					<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					<logic:empty name="act">
						<button onclick="showTopWin('/xgxt/stu_LdQsInfo.do',750,550);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</logic:empty>
				</td>
				<td width="16%">
					<div align="center">
						����
					</div>
				</td>
				<td width="34%">
				${rs.xm }
<%--					<html:text name="rs" property="xm"  style="width:100%" readonly="true"/>--%>
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						<bean:message key="lable.xsgzyxpzxy" />
					</div>
				</td>
				<td>
				${rs.xymc }
<%--					<html:text name="rs" property="xymc"  style="width:100%" readonly="true"/>--%>
				</td>
				<td>
					<div align="center">
						רҵ
					</div>
				</td>
				<td>
				${rs.zymc }
<%--					<html:text name="rs" property="zymc"  style="width:100%" readonly="true"/>--%>
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						�༶
					</div>
				</td>
				<td>
				${rs.bjmc }
<%--					<html:text name="rs" property="bjmc"  style="width:100%" readonly="true"/>--%>
				</td>
				<td>
					<div align="center">
						�꼶
					</div>
				</td>
				<td>
				${rs.nj }
<%--					<html:text name="rs" property="nj"  style="width:100%" readonly="true"/>--%>
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						¥��
					</div>
				</td>
				<td>
				${rs.ldmc }
<%--					<html:text name="rs" property="ldmc"  style="width:100%" readonly="true"/>--%>
				</td>
				<td>
					<div align="center">
						¥��
					</div>
				</td>
				<td>
				${rs.cs }
<%--					<html:text name="rs" property="cs"  style="width:100%" readonly="true"/>--%>
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						���Һ�
					</div>
				</td>
				<td>
				${rs.qsh }
<%--					<html:text name="rs" property="qsh"  style="width:100%" readonly="true"/>--%>
				</td>
				<td>
					<div align="center">
						������
					</div>
				</td>
				<td>
					<html:text name="rs" property="ssbh"  style="width:100%" maxlength="50" readonly="true"/>
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						����
					</div>
				</td>
				<td>
					<html:select name="rs" property="bmdm" styleId="bmdm" >
						<html:options collection="zghbmList" property="dm"
							labelProperty="mc" />
					</html:select>
				</td>
				<td>
					<div align="center">
						ְ��
					</div>
				</td>
				<td>
					<html:text name="rs" property="zw"  style="width:100%" maxlength="50"/>
				</td>
			</tr>
			<tr>
			    <td>
					<div align="center">
						��ϵ�绰
					</div>
				</td>
				<td>
					<input type="text" id="lxdh" name="lxdh"  maxlength="20"
						style="width:100%"
						value="<bean:write name="rs" property="lxdh"/>"
						onkeyup="value=value.replace(/[^\d]/g,'') " 
						onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
				</td>
				<td>
					&nbsp;
				</td>
				<td>
					&nbsp;
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
