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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script language="javascript">
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
		
function initBjListT(){
	var xydm ="";
	var zydm ="";
	var nj ="";
	var query = "";
	var userName = "";
	var yjdm = "";
	var ljdm = "";
	var pjdm = "";
	if($("userName")){userName = $("userName").value};
	if($("xy")){xydm = $("xy").value};
	if($("zy")){zydm = $("zy").value};
	if($("nj")){nj = $("nj").value};
	if($("yjdm")){yjdm = $("yjdm").value};
	if($("ljdm")){ljdm = $("ljdm").value};	
	if($("pjdm")){pjdm = $("pjdm").value};	
	if(xydm == null || xydm == ""){
		xydm = "%";
	} else{
		xydm = "%" + xydm + "%";
	}
	if(zydm == null || zydm == ""){
		zydm = "%";
	} else{
		zydm = "%" + zydm + "%";
	}
	if(nj == null || nj == ""){
		nj = "%";
	} else{
		nj = "%" + nj + "%";
	}
	query += xydm;
	query += "!!-!!";
	query += zydm;
	query += "!!-!!";
	query += nj;
		GetListData.getNblgJxBjList(query,yjdm,ljdm,pjdm,userName,function initTjList(data){
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
		<html:form action="/ArmyStuInfo" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ѵ���� - ��Ϣά�� - �̹���Ϣά��
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<input type="hidden" name="xyV" id="xyV" />
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />
					<input type="hidden" name="yjdmV" id="yjdmV" />
					<input type="hidden" name="ljdmV" id="ljdmV" />
					<input type="hidden" name="pjdmV" id="pjdmV" />
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									�̹ٱ�ţ�
									<html:text property="jgbh" style="width:85px"></html:text>
									&nbsp;&nbsp;&nbsp;������
									<html:text property="xm" style="width:85px"></html:text>
									<html:select property="nj" onchange="initBjListT();initYjList();initLjList();initPjList();"
										style="width:60px;padding-left:80px;visibility: hidden">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/jgxxInfo_nblg.do')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									Ӫ����
									<html:select property="yjdm" onchange="initLjList();initPjList();initBjListT();"
										 style="width:150px" styleId="yjdm">
										<html:option value=""></html:option>
										<html:options collection="yjList" property="yjdm"
											labelProperty="yjmc" />
									</html:select>
									&nbsp;������
									<html:select property="ljdm" onchange="initPjList();initBjListT();"
										style="width:180px" styleId="ljdm">
										<html:option value=""></html:option>
										<html:options collection="ljList" property="ljdm"
											labelProperty="ljmc" />
									</html:select>
									&nbsp;�ż���
									<html:select property="pjdm" onchange="initBjListT();"
										 style="width:180px" styleId="pjdm">
										<html:option value=""></html:option>
										<html:options collection="pjList" property="pjdm"
											labelProperty="pjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									�༶��
									<html:select property="bjdm" style="width:180px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									&nbsp;��ѵ��ݣ�
									<html:select property="jxnd" styleId="jxnd">
									<html:options collection="xnList" labelProperty="nd" property="nd"/>
									</html:select>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<logic:empty name="rs">
					<p align="center">
						δ�ҵ��κμ�¼��
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="showDataFrame('jgxxInfoOne_nblg.do','modi',550,350)">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble" scope="request">
							<button type="button" class="button2" onclick="showDataFrame('jgxxInfoOne_nblg.do','add',550,350)"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="showDataFrame('jgxxInfoOne_nblg.do','modi',550,350)"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="showDataFrame('jgxxInfoOne_nblg.do','del',550,350)"
								style="width:80px">
								ɾ ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:equal>
						<button type="button" class="button2" onclick="impAndChkData();"
							style="width:80px">
							��������
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="dataExport()" style="width:80px">
							��������
						</button>
					</div>
				</center>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("�����ɹ�!");
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��!");
				window.document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
