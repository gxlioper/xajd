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
	function bgTj(){
		if($("bgdm").value == ""){
			alert("��ȷ���������ƣ���");
			return false;
		}
		showTips('���������У���ȴ�......');
		refreshForm('/xgxt/xmlgszbgtj.do?method=aqbgUpdate&doType=save');
		$("buttonSave").disabled=true;
	}
	
	function setLxmc(lx){
		getXmlgSzdwDAO.getMcList("xmlg_szdw_aqbgmc",lx,function(data){
			var objId = "bgdm";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"dm","mc");	
				$(objId).options[0].value="";		
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
		<html:form action="/xmlgszbgtj" method="post">
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
					��ȫ����
				</legend>
				<table align="center" width="100%" class="tbstyle">
					<tr>
						<td align="right" style="width:20%">
							ְ���ţ�
						</td>
						<td align="left" style="width:30%">
							<bean:write name="rs" property="zgh"/>
						</td>
						<td align="right" style="width:20%">
							�������ƣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="bmmc"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="">
							������
						</td>
						<td align="left">
							<bean:write name="rs" property="xm"/>
						</td>
						<td align="right" style="">
							ְ�����ƣ�
						</td>
						<td align="left">
							<bean:write name="rs" property="zwmc"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="">
							�Ա�
						</td>
						<td align="left">
							<bean:write name="rs" property="xb"/>
						</td>
						<td align="right" style="">
							��ϵ�绰��
						</td>
						<td align="left">
							<bean:write name="rs" property="lxdh"/>
						</td>
					</tr>
					<tr>
						<td align="right" style="">
							��ȫ�������ͣ�
						</td>
						<td align="left">
							<html:select property="lx" style="" onchange="setLxmc(this.value)">
								<html:option value=""></html:option>
								<html:options collection="lxList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
						<td align="right" style="">
							��ȫ�������ƣ�
						</td>
						<td align="left">
							<html:select property="bgdm" style="">
								<html:options collection="mcList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right" style="">
							��ȫ�������ݣ�
						</td>
						<td align="left" colspan="3">
							<html:textarea property="bgnr" style="width:100%" rows="5" onblur="chLeng(this,500)"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right" style="">
							Ӧ�԰취��
						</td>
						<td align="left" colspan="3">
							<html:textarea property="ydbf" style="width:100%" rows="5"></html:textarea>
						</td>
					</tr>
					<tr>
						<td align="right" style="">
							��ע��
						</td>
						<td align="left" colspan="3">
							<html:textarea property="bz" style="width:100%" rows="5" onblur="chLeng(this,150)"></html:textarea>
						</td>
					</tr>
				</table>
			</fieldset>
			<div class="buttontool">
				<logic:empty name="doType">
				<button type="button" class="button2"
						onclick="bgTj();"
						style="width:80px" id="buttonSave">
						�� ��
				</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
				</logic:empty>
				<logic:notEmpty name="doType">
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
				</button>
				</logic:notEmpty>
			</div>
			<logic:present name="result">
				<logic:equal name="result" value="yes">
					<script>
				    alert("�ύ�ɹ���");
				    //dialogArgumentsQueryChick();
					//Close();
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
