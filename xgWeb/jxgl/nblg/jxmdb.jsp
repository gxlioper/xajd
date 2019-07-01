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
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getJxLdjzList.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript">
		function getNj(){
			document.getElementById('nj').value = document.getElementById('nd').value;
		}
		function getLdbh(){
			document.getElementById('ldbh').value = document.getElementById('pjdm').value;
		}
		
		function initYjList(){
			var nj = document.getElementById('nj').value;
			var query = "";
			GetListData.getNblgJxYjbzList(nj,function initTjList(data){
				if (data != null && typeof data == 'object') {
					var objId = data[0].dm;
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"dm","mc");			
						$(objId).options[0].value = "";
						if(objId + "V"){
							if($(objId +"V").value != "" && $(objId + "V").value != null){
								for(var i = 0;i < $(objId).options.length; i++){
									if($(objId).options[i].value == $(objId +"V").value){
										$(objId).options[i].selected = true;
										return true;
									}
								}
							}
						}
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}
			});
		}
		
		function initLjList(){
			var nj = document.getElementById('nj').value;
			var yjdm = "";
			var query = "";
			if($("yjdm")){yjdm = $("yjdm").value};	
			GetListData.getNblgJxLjbzList(yjdm,nj,function initTjList(data){
				if (data != null && typeof data == 'object') {
					var objId = data[0].dm;
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"dm","mc");			
						$(objId).options[0].value = "";
						if(objId + "V"){
							if($(objId +"V").value != "" && $(objId + "V").value != null){
								for(var i = 0;i < $(objId).options.length; i++){
									if($(objId).options[i].value == $(objId +"V").value){
										$(objId).options[i].selected = true;
										return true;
									}
								}
							}
						}
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}
			});
		}
		
		function initPjList(){
			var nj = document.getElementById('nj').value;
			var yjdm = "";
			var ljdm = "";
			var query = "";
			if($("yjdm")){yjdm = $("yjdm").value};
			if($("ljdm")){ljdm = $("ljdm").value};	
			GetListData.getNblgJxPjbzList(yjdm,ljdm,nj,function initTjList(data){
				if (data != null && typeof data == 'object') {
					var objId = data[0].dm;
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
						DWRUtil.removeAllOptions(objId);			
						DWRUtil.addOptions(objId,data,"dm","mc");			
						$(objId).options[0].value = "";
						if(objId + "V"){
							if($(objId +"V").value != "" && $(objId + "V").value != null){
								for(var i = 0;i < $(objId).options.length; i++){
									if($(objId).options[i].value == $(objId +"V").value){
										$(objId).options[i].selected = true;
										return true;
									}
								}
							}
						}
					}
				}else{
					showMsgWin("�д�����֣�Զ�����ݶ�ȡʧ�ܣ�");
				}
			});
		}
</script>
		<html:form action="/viewArmyStu" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�޼�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("�������ѧ����Ч!");
    				</script>
				</logic:equal>
				<input type="hidden" name="yjdmV" id="yjdmV" />
				<input type="hidden" name="ljdmV" id="ljdmV" />
				<input type="hidden" name="pjdmV" id="pjdmV" />
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/viewArmyStu.do" />
				<input type="hidden" id="nj" name="nj"
					value="<bean:write name='rs' property='nd' />" />
				<input type="hidden" id="ldbh" name="ldbh" 
					value="<bean:write name='rs' property='ldbh' />" />
				<fieldset>
					<legend>
						��ѵ������Ϣ
					</legend>
					<table width="100%" class="tbstyle">
						<tr>
							<td align="right">
								<font color="red">*</font>ѧ�ţ�
							</td>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									readonly="readonly"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�Ա�
							</td>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<td align="right">
								<bean:message key="lable.xsgzyxpzxy" />��
							</td>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<td align="right">
								<font color="red">*</font>��ȣ�
							</td>
							<td align="left">
								<html:select name="rs" property="nd" style="width:100px" styleId="nd" onchange="getNj();initYjList();initLjList();initPjList();">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						
						<tr>
							<td align="right">
								רҵ��
							</td>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
						</tr>
						<tr>
							<td align="right">
								����Ӫ��
							</td>
							<td align="left">
								<html:select name="rs" property="yjdm" onchange="initLjList();initPjList();"
										 styleId="yjdm">
										<html:option value=""></html:option>
										<html:options collection="yjList" property="yjdm"
											labelProperty="yjmc" />
									</html:select>
							</td>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:select name="rs" property="ljdm" onchange="initPjList();"
										styleId="ljdm">
										<html:option value=""></html:option>
										<html:options collection="ljList" property="ljdm"
											labelProperty="ljmc" />
									</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								�����ţ�
							</td>
							<td align="left">
								<html:select name="rs" property="pjdm" styleId="pjdm">
										<html:option value=""></html:option>
										<html:options collection="pjList" property="pjdm"
											labelProperty="pjmc" />
									</html:select>
							</td>
							<td align="right">
								�Ƿ�ѵ
							</td>
							<td align="left">
								<html:select property="sfbx" style="width:90px" styleId="xb">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right">
								��ѵ���֣�
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='jxbx' style="width:99%" rows='5' />
							</td>
						</tr>
						<tr>
							<td align="right">
								��ѵ���ã�
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='jxbz' style="width:99%" rows='5' />
							</td>
						</tr>
						<tr>
							<td align="right">
								��ѵ�ͷ���
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='jxcf' style="width:99%" rows='5' />
							</td>
						</tr>
				</table>
				</fieldset>
				<div class="buttontool">
					<button type="button" class="button2"
						onclick=""
						style="width:80px" id="buttonModi">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2"
						onclick="getLdbh();Savedata('xh-nd','ArmyStuInfoSave.do')"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("�����ɹ�!");
				dialogArgumentsQueryChick();
				window.close();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��!");
			</script>
		</logic:equal>
	</body>
</html>
