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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/sxjyFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript">
	var obj_bgc = "#FFFFFF";
	var cur_bgc = "#ffdead";//ѡ���б������ַ�����
	var curr_row = null;
	var sort_col = null;
	var splitSignOne = "!!SplitSignOne!!";
	var temppk = "";
	//��ѡ����
	function rowMore(objTr,tag) {
		curr_row = objTr;
		var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
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
				var pk2 = new Array();
					pk2 = pk.split("&");
					pk = pk2[1]+pk2[2]+pk2[3];
					temppk = temppk.replace(pk,"");
			}else{
				Rows[Rows.length]=iRow;
			}
		}
		else{	
			if (Rows.length!=0){
				for (i=0; i<Rows.length; i++){	
					if (Rows[i]!=null && Rows[i].tagName.toLowerCase() == "tr") {
						Rows[i].style.backgroundColor = obj_bgc;
	    			}
				}
			}
			if(document.all("ycxh")){
				document.getElementById("ycxh").value=curr_row.cells[1].innerText.trim();
			}
			obj_bgc = curr_row.style.backgroundColor;
			curr_row.style.backgroundColor = cur_bgc;
			Rows.length=1;
			Rows[0]=iRow;
		}
		var pk1 = new Array();
			pk1 = pk.split("&");
			pk = pk1[1]+pk1[2]+pk1[3];
		changeC(iRow);
		temppk = temppk + pk + splitSignOne;
	}
	//ѡ���б�ɫ
	function changeC(E){
		for(i=0;i<Rows.length;i++){
			Rows[i].style.backgroundColor=cur_bgc;
		}
	}
	function rowSelect(objTr) {
	if (curr_row != null && curr_row.tagName.toLowerCase() == "tr") {
		curr_row.style.backgroundColor = obj_bgc;
	}
	curr_row = objTr;
	obj_bgc = curr_row.style.backgroundColor;
	curr_row.style.backgroundColor = cur_bgc;
	}
	
	function searchCheck(url){
		var xh = document.getElementById("xhV").value;
		if(xh != ""){
			if(xh.match(/^\d+\.{0,1}\d{0,3}$/)==null){
				alert("ѧ�ű���Ϊ���֣�");
				return false;
			}
		}
		allNotEmpThenGo(url);
	}

	function fzdxOne(url){
		if(curr_row == null){
			alert('��ѡ��Ҫ�޸ĵ�ѧ��!');
			return false;
		}
		var pk = curr_row.cells[0].getElementsByTagName('input')[0].value;
		var temppk = new Array();
		temppk = pk.split("&");
		pk = temppk[1]+temppk[2]+temppk[3];
		showTopWin(url+pk,600,480);
	}
	
	function fzdxDel(){
		if(curr_row == null){
			alert('��ѡ��Ҫɾ����ѧ��!');
			return false;
		}
		showTips('���������У���ȴ�......');
		refreshForm('/xgxt/dtjs_zjcm.do?method=delFzdx&go=go&pk='+temppk)
	}
	function addOne(){
		showTopWin('/xgxt/XsgyglHhDispatch.do?method=zrsglEdit&type=add&pkValue='+"",700,600);
	}
	
	function editOne(){
		if (curr_row==null || curr_row =='') {
			alert('��ѡ��Ҫ���������ݣ�');
			return;
		}
		var xn = curr_row.cells[0].innerText;
		var xq = curr_row.cells[1].innerText;
		var ssbh=curr_row.cells[2].innerText;
		
		ssbh=ssbh.replace("��¥","-").replace("����","");
		var pkValue = curr_row.cells[0].getElementsByTagName('input')[0].value;
		showTopWin('/xgxt/XsgyglHhDispatch.do?method=zrsglEdit&type=modi&pkValue='+pkValue,700,600);
	}
	</script>
	<body>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>	
		<center>
			<html:form action="/XsgyglHhDispatch" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName"/>" scope="request" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable"/>" scope="request"/>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
			<input type="hidden" id="method" name="method"
				value="swcl" />
			<input type="hidden" id="writeAble" name="writeAble"
				value="<bean:write name="writeAble"/>" />
			<input type="hidden" id="delFzdx" name="delFzdx" value="${delFzdx}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�<bean:write name = "title" />
				</div>
			</div>
			<fieldset>
				<legend>
					�� ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
								&nbsp;&nbsp;ѧ�꣺											
								<html:select property="xn" style="width:90px" styleId="xn"
										onchange="">
									<html:options collection="xnList" property="xn"
												labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;ѧ�ڣ�
								<html:select property="xq" style="width:60px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;ѧ�ţ�
								<html:text property="xh" styleId="xhV" maxlength="10"/>
							</td>
							<td width="10" rowspan="3" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="searchCheck('/xgxt/XsgyglHhDispatch.do?method=zrsgl')">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<input type="hidden" name="zyV" value=""/>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" style="width:180px" styleId="xy" 
									onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>
									&nbsp;&nbsp;רҵ��
								<html:select property="zydm" style="width:180px" styleId="zy"
										onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="bjdm" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								<input type="hidden" name="bjV" value=""/>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;&nbsp;������
								<html:text property="xm" />
								&nbsp;&nbsp;���Һţ�
								<html:text property="qsh" />
								<font color="red">���Ҹ�ʽ���ơ�04-110��</font>
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
							<font color="blue">��ʾ��˫��һ�п���ѡ����������ͷ�������򣻰�סCtrl���Զ�ѡ</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="top" name="topTr" offset="0" length="1">
									<td id="<bean:write name="top" property="en"/>"
											onclick="tableSort(this)" nowrap>
										<bean:write name="top" property="cn" />		
									</td>
								</logic:iterate>
								<logic:iterate id="top" name="topTr" offset="1">
									<td id="<bean:write name="top" property="en"/>"
											onclick="tableSort(this)" nowrap>
										<bean:write name="top" property="cn" />	
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<logic:equal value="yes" name="writeAble">
								<tr onclick="rowMore(this)" style="cursor:hand"
									ondblclick="editOne();">	
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v" />" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td>
										<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:equal>
						</logic:iterate>
					</table>
						<TABLE width="100%" id="Table" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=xsgyglForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
				</fieldset>						
			</logic:notEmpty>
			<br><br><br>
			<logic:equal value="yes" name="writeAble">
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					&nbsp;
					<button class="button2"
						onclick="addOne();" style="width:80px">
						�� ��
					</button>
					&nbsp;
					<button class="button2" style="width:80px"
					onclick="editOne();">
						�� ��
					</button>
					&nbsp;
					<button class="button2" onclick="impAndChkData()"
						style="width:80px">
						��������
					</button>
					&nbsp;
					<button class="button2" onclick="dataExport()"
						style="width:80px">
						��������
					</button>
				</div>
			<logic:present name="delFzdx">
				<script>
					alert(''+document.getElementById('delFzdx').value);
				</script>
			</logic:present>
			</logic:equal>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>