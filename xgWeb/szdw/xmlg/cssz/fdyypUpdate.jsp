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
	<script language="javascript">
	function saveCssz(obj){
		if($("xn").value == ""){
			alert("ѧ�겻��Ϊ�գ���ȷ�ϣ���");
			return false;
		}
		if($("xq").value == ""){
			alert("ѧ�ڲ���Ϊ�գ���ȷ�ϣ���");
			return false;
		}
		if($("zd").value == ""){
			alert("�ֶβ���Ϊ�գ���ȷ�ϣ���");
			return false;
		}
		if($("zdm").value == ""){
			alert("�ֶ�������Ϊ�գ���ȷ�ϣ���");
			return false;
		}
		showTips('���������У���ȴ�......');
		refreshForm('/xgxt/xmlgfdyyp.do?method=csszUpdate&doType=save');
		obj.disabled=true;
	}
	
	function checkZd(obj,zbid){
		var pk = $("xn").value+$("xq").value+zbid;
		getXmlgSzdwDAO.getZd("xmlg_fdyyp_hdbzd",pk,function(data){
			if (data != null) {
				if(data != "0"){
					alert("���ֶ��Ѿ����ڣ���ȷ�Ϻ���������");
					obj.value="";
					obj.focus();
				}
			}
		});
	}
	
	function chHd(hddm){
		var hddm = $("hddm").value;
		var xn = $("xn").value;
		if(hddm == "" || xn == ""){
			$("zd").disabled=true;
			$("zd").value= "";
		}else{
			$("zd").disabled=false;
		}
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXmlgSzdwDAO.js'></script>
		<html:form action="/xmlgfdyyp" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				<span id="tipFollow"><bean:write name="title"/></span>
			</div>
		</div>
			<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
			<fieldset>
				<legend>
						�ֶι���
				</legend>
				<table width="100%" class="tbstyle">
					<tr>
						<td align="right" style="width:50%">
								ѧ�꣺
						</td>
						<td align="left">
							<logic:equal name="doType" value="add">
							<html:select name="rs" property="xn" style="">
								<html:options collection="xnList" property="xn"
											labelProperty="xn" />
							</html:select>	
							</logic:equal>
							<logic:notEqual name="doType" value="add">
								<html:text name="rs" property="xn" style="" readonly="true"/>	
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<td align="right" style="width:50%">
								ѧ�ڣ�
						</td>
						<td align="left">
							<logic:equal name="doType" value="add">
							<html:select name="rs" property="xq" style="">
								<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
							</html:select>	
							</logic:equal>
							<logic:notEqual name="doType" value="add">
								<html:hidden name="rs" property="xq"/>	
								<html:text name="rs" property="xqmc" style="" readonly="true"/>	
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<td align="right">
								�ֶΣ�
						</td>
						<td align="left">
							<logic:equal name="doType" value="add">
								<html:text name='rs' property="zd" styleId="zd" onblur="checkZd(this,this.value)"/>
							</logic:equal>
							<logic:notEqual name="doType" value="add">
								<html:text name='rs' property="zd" styleId="zd" onblur="" readonly="true"/>
							</logic:notEqual>
						</td>
					</tr>						
					<tr>
						<td align="right">
								�ֶ�����
						</td>
						<td align="left">
							<html:text name='rs' property="zdm" styleId="zdm"/>
						</td>
					</tr>
					<tr>
						<td align="right">
								�ֶ����ͣ�
						</td>
						<td align="left">
							<html:select name="rs" property="zdlx">
								<html:option value="001">�ı�</html:option>
								<html:option value="002">ʱ��</html:option>
								<html:option value="003">�ı���</html:option>
							</html:select>		
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<font color="red">ע���ֶν�������ƴ����Ӣ����д��������ָ����ʦ��Ϊzdls��������¼�봿���ֻ��֣�</font>
							<font color="red">�ֶ�������¼��������ĺ��ֻ�Ӣ�ġ�</font>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool">
				<logic:notEqual name="doType" value="view">
				<button type="button" class="button2"
						onclick="saveCssz(this);"
						style="width:80px" id="buttonSave">
						�� ��
				</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:notEqual>
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
				</button>
			</div>
			<logic:present name="result">
				<logic:equal name="result" value="yes">
					<script>
				    alert("�ύ�ɹ���");
				    dialogArgumentsQueryChick();
					Close();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="no">
					<script>
				    alert("�ύʧ�ܣ�");
				    </script>				
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
