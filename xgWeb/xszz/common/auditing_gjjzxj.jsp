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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
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
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>	
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script language="javascript">
		var Rows=new Array();	//����ѡ�е��ж���
		var ShiftStartRow="";		//Shift��ѡʱ�洢��ʼ�ж���
		var cur_bgc = "#ffdead";//ѡ���б������ַ�����
		
		function rowMoreClick(objTr) {
		
		if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		curr_row.style.backgroundColor = obj_bgc;
		}
		curr_row = objTr;
		obj_bgc = curr_row.style.backgroundColor;
		curr_row.style.backgroundColor = cur_bgc;
			
		iRow=window.event.srcElement;
		do{
			iRow=iRow.parentElement;
		}while(iRow.tagName!='TR')

		//Ctrl��ѡ
		if(event.ctrlKey){
			var j=-1;
			for(i=0;i<Rows.length;i++){
				if(iRow==Rows[i]){
					j=i;
					break;
				}
			}
			if(j!=-1){
				for(i=j;i<Rows.length-1;i++){
					Rows[i]=Rows[i+1];
				}
				Rows.length=Rows.length-1;
				iRow.style.backgroundColor = "#FFFFFF";
			}else{
				Rows[Rows.length]=iRow;
			}
	//		ShiftStartRow=iRow;
		}
		else{	
			if (Rows.length!=0){
				for (i=0; i<Rows.length; i++){	
					if (Rows[i].tagName.toLowerCase() == "tr") {
						Rows[i].style.backgroundColor = "#FFFFFF";
		    		}
				}
			}
			Rows.length=1;
			Rows[0]=iRow;
			
	//		ShiftStartRow=iRow;
		}
		changeColor(iRow);
	}

		//ѡ���б�ɫ
		function changeColor(E){
			
		/*	for(var i=1;i<E.parentElement.rows.length;i++){
				E.parentElement.rows(i).style.backgroundColor=cur_bgc;
			}
		*/
			for(i=0;i<Rows.length;i++){
				Rows[i].style.backgroundColor=cur_bgc;	
			}
		}
		
		function del(url){
			var RowsStr="!!splitOne!!";
			
			for (i=0; i<document.getElementsByName("pk").length; i++){
		    	if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
		    	}
			}
			document.forms[0].pkDel.value = RowsStr;
			
			if (RowsStr=="!!splitOne!!"){
				alert("��ѡ��Ҫ����ɾ���ļ�¼��");
				return false;
			}
			var msg = "ȷ��Ҫɾ����ѡ��¼��";
			var userType = document.getElementById('userType').value;
			if(userType == "xy"){
				msg = "<bean:message key="lable.xsgzyxpzxy" />�û�����ɾ����ͨ��ѧУ��˵����ݣ�" + msg;
			}
			if (!confirm(msg)){
				return false;
			}
			document.forms[0].action=url;
		    document.forms[0].submit();
		}
		
		
		function chec(){
		      for(i=0;i<document.getElementsByName("pk").length;i++){
		      	document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
		      }
		}
		
		function chkAssisOne(url, act) {
			if (curr_row == null) {
				return false;
			} else {
				url += "?actDo=";
				url += act;
				url +="&act=";
				url += document.getElementById("act").value;
				url += "&pkVal=";
				url += curr_row.getElementsByTagName("input")[0].value;
				url += "&xh=";
				url += curr_row.getElementsByTagName("input")[2].value;
				url += "&queryPK=";
				url += curr_row.getElementsByTagName("input")[3].value;
				url += "&tName=";
				url += document.getElementById("realTable").value;
				url += "&jxjlbType=gjjzxj";
				if(url.search('assisChkOne')){
					url += "&xydm="+document.getElementById("xy").value;
				}
				showTopWin(url, 750, 550);
				return true;
			}
		}
		
		function add(url){
			var jxjlbType = document.getElementById('jxjlbType').value;
			url += "?jxjlbType=" + jxjlbType;
			return showTopWin(url,750,550);
		}
		
		function modi(url){
			var jxjlbType = document.getElementById('jxjlbType').value;
			if((curr_row == null) || (curr_row == "")){
				alert("��ѡ��Ҫ�޸ĵļ�¼��");
				return false;
			}
			url += "?xh=";
			url += curr_row.getElementsByTagName("input")[2].value;
			url += "&queryPK=";
			url += curr_row.getElementsByTagName("input")[3].value;
			url += "&jxjlbType=" + jxjlbType;
			return showTopWin(url,750,550);
		}
		
		function dataExport2() {
			document.forms[0].action = "/xgxt/expData2.do";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		</script>
	<body onload="xyDisabled('xy');initNdList();initNjList();initOnLoadXyList();">
		
		<html:form action="/auditing_gjjzxj" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="isQuery" name="isQuery"
				value="<bean:write name="isQuery" scope="request"/>" />
			<input type="hidden" id="pkDel" name="pkDel" value="" />
			<input type="hidden" id="rsExpString" name="rsExpString"
				value="<bean:write name="rsExpString" scope="request"/>" />
			<input type="hidden" id="colListS" name="colListS"
				value="<bean:write name="colListS" scope="request"/>" />
			<input type="hidden" id="isFdy" name="isFdy" value="${isFdy}"/>
			<input type="hidden" id="isBzr" name="isBzr" value="${isBzr}"/>
			<input type="hidden" name="njV" id="njV" value="${form.nj}"/>
			<input type="hidden" name="xyV" id="xyV" value="${form.xydm}"/>
			<input type="hidden" name="zyV" id="zyV" value="${form.zydm}"/>			
			<input type="hidden" name="bjV" id="bjV" value="${form.bjdm}"/>
			<input type="hidden" name="ndV" id="ndV" value="${form.nd}"/>
			<input type="hidden" name="ndV" id="ndV" value="${form.nd}"/>
			<input type="hidden" name="jxjlbType" id="jxjlbType" value="${shxmdm}"/>
			
			<fieldset>
				<legend>
					��ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								�꼶��
								<html:select property="nj" onchange="initOnLoadXyList();" 
								styleId="nj" style="width:90px;padding-left:80px">
								</html:select>&nbsp;&nbsp;
								��ȣ�
								<html:select property="nd" styleId="nd" style="width:90px;padding-left:80px">
								</html:select>&nbsp;&nbsp;
								ѧ�ţ�
								<html:text property="xh" styleId="xh" style="width:100px;inputtext"
								styleClass="inputtext"></html:text>&nbsp;&nbsp;
								������
								<html:text property="xm" styleId="xm" style="width:100px;inputtext"
								styleClass="inputtext"></html:text>			
								<logic:notEqual value="is" name="isQuery">
									������
									<html:select property="shlb" styleId="shlb" style="width:90px;padding-left:100px">
										<html:option value=""></html:option>
										<html:options collection="checkList" property="en"
											labelProperty="cn" />
									</html:select>
								</logic:notEqual>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/auditing_gjjzxj.do')">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left">
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" onchange="initOnLoadZyList()"
								 style="width:180px" styleId="xy">
								</html:select>&nbsp;&nbsp;
								רҵ��
								<html:select property="zydm" onchange="initBjList()" 
								style="width:180px" styleId="zy">
								</html:select>&nbsp;&nbsp;
								�༶��
								<html:select property="bjdm" style="width:180px" styleId="bj">
								</html:select>	
								&nbsp;&nbsp;
								
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<logic:notEqual value="10690" name="xxdm">
								�����Ŀ��
								<html:select name="rs1" property="shxm" style="width:180px">
									<html:options collection="shxmList" property="shxmdm"
										labelProperty="shxmmc" />
								</html:select>
								&nbsp;&nbsp;
								</logic:notEqual>
								<logic:equal name="xxdm" value="10690">	
								<input type="hidden" id="shxm" name="shxm" value="${rs1.shxmdm}"/>
								����
								<html:select property="jb" styleId="jb">
									<html:option value=""></html:option>
									<html:options collection="jbList" labelProperty="mc" property="dm"/>
								</html:select>
								&nbsp;&nbsp;
								</logic:equal>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
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
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ<logic:notEqual value="is" name="isQuery">�������Ըı����״̬</logic:notEqual>��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')">
								</td>
								<logic:iterate id="tit" name="topTr" offset="4">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<logic:equal name="isQuery" value="no">
								<tr onclick="rowOnClick(this)"
									style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     "
									ondblclick="chkAssisOne('/xgxt/auditing_gjjzxj_one.do','view')">
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pk"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="4" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="5">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:equal>
							<logic:equal name="isQuery" value="is">
								<tr onclick="rowOnClick(this)"
									style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     "
									ondblclick="modi('/xgxt/gjjxjzxj.do')">
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pk"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td align="center">
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="4" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="5">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:equal>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:equal name="isQuery" value="is">
						<button class="button2" onclick="add('/xgxt/gjjxjzxj.do');"
							style="width:80px">
							�� ��
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="modi('/xgxt/gjjxjzxj.do');"
							style="width:80px">
							�� ��
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:equal>
					<button class="button2"
						onclick="del('/xgxt/auditing_gjjzxj_one.do?actDo=del');"
						style="width:80px">
						ɾ ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"
						onclick="impAndChkData();"
						style="width:80px">
						��������
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="dataExport2()" style="width:80px">
						��������
					</button>
				</div>
			</center>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
