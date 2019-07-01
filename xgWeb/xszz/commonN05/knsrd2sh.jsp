<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript" src="/xgxt/js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/qgzxgzkh.js'></script>
	<script language="javascript">
		var Rows=new Array();	//����ѡ�е��ж���
		var ShiftStartRow="";		//Shift��ѡʱ�洢��ʼ�ж���
		var cur_bgc = "#ffdead";//ѡ���б������ַ�����
		
		function knsrd2Print(url){
		   document.forms[0].target = "_blank";
           document.forms[0].action=url;
           document.forms[0].submit();
        }
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

function selectAll(){
	var checkBoxArr = document.getElementsByName("pk");
	var selall = document.getElementById('cb');
	if(selall.checked==true){
		for(var i=0;i<checkBoxArr.length;i++){
			if (!checkBoxArr[i].disabled) {
				checkBoxArr[i].checked = true;
			}
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}

function del(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("��ѡ��Ҫ����ɾ���ļ�¼��");
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function tbkn(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("��ѡ��Ҫ�����޸�Ϊ�ر����ѵļ�¼��");
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function kn(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("��ѡ��Ҫ�����޸�Ϊ���ѵļ�¼��");
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function bkn(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("��ѡ��Ҫ�����޸�Ϊ�����ѵļ�¼��");
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function chkAssisOne(url) {
	if (curr_row == null) {
		return false;
	} else {
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(url, 750, 550);
		return true;
	}
}

function add(url){
	return showTopWin(url,750,550);
}

function modi(url){
	if((curr_row == null) || (curr_row == "")){
		alert("��ѡ��Ҫ�޸ĵļ�¼��");
		return false;
	}
	url += "&pkVal=";
	url += curr_row.getElementsByTagName("input")[0].value;
	return showTopWin(url,750,550);
}

function dataExport2() {
	document.forms[0].action = "/xgxt/n05_xszz.do?method=knsrd2Exp";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
		function tjjg(){
			var dd_html = "<div>";
			dd_html += "<center><br><table width='350' class='tbstyle'>";
			dd_html += "<thead>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center' colspan='2'>";
			dd_html += "��ѡ����Ҫ�ύ��˽���İ༶";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "</thead>";
			dd_html += "<tbody>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center'>";
			dd_html += "ѧ�꣺";		
			dd_html += "</td><td>";
			dd_html += val('xn');
			dd_html += "</td></tr>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center'>";
			dd_html += "�༶��";		
			dd_html += "</td><td>";
			dd_html += "<select id='tjbjdm' name='tjbjdm'/>";
			dd_html += "</td></tr>";
			dd_html += "<tr height='30' bgcolor='EEF4F9'>";
			dd_html += "<td align='center' colspan='2'>";
			dd_html += "<button class='button2' onclick='commitSh()'>ȷ��</button>&nbsp;&nbsp;";//szGroup()
			dd_html += "<button class='button2' onclick='closeAdd()'>ȡ��</button>";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "<tbody>";
			dd_html += "</table></center>";
			dd_html += "</div>";
			showDiv(dd_html, 400, 170);
			copySelect('bj','tjbjdm');
			setVal('tjbjdm',val('bj'));
		}
		
		function commitSh(){
			//�ж�ѡ��İ༶�Ƿ��Ѿ��ύ��
			var xn = val('xn');
			var bjdm = val('tjbjdm');
			if(bjdm == null || bjdm == "" || bjdm == undefined){
				alert('��ѡ����Ҫ�ύ��˽���İ༶��');
				return false;
			}
			qgzxgzkh.checkExists('xszz_com_bmshtjb','zjz||dm||bm||tjxm||tjzt',xn+bjdm+'bjknsrd2'+'1',function(data){
				if(data != null && data == true){
					alert('�ð༶�Ѿ��ύ��');
					return false;
				}else{//�ύ
					refreshForm("n05_xszz.do?method=knsrd2sh&go=tjsh&xn=" + xn + "&tjbjdm=" + bjdm);
				}
			});
		}
		</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/n05_xszz.do?method=knsrd2sh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<logic:equal name="isQuery" value="is">
					��ǰ����λ�ã�ѧ������ - ��������ѯ - �������϶�
					</logic:equal>
					<logic:notEqual name="isQuery" value="is">
					��ǰ����λ�ã�ѧ������ - ��� - ����ѧ���϶�
					</logic:notEqual>
				</div>
			</div>
			<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="isQuery" name="isQuery"
				value="<bean:write name="isQuery" scope="request"/>" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<fieldset>
				<legend>
					����ѡ��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								ѧ��
								<logic:equal name="isQuery" value="is">
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								</logic:equal>
								<logic:notEqual name="isQuery" value="is">
								<html:select property="xn" 
									styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								</logic:notEqual>
								�꼶
								<html:select property="nj" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								ѧ��
								<html:text property="xh" styleId="xh" style="width:80px;inputtext"
								styleClass="inputtext"></html:text>
								����
								<html:text property="xm" styleId="xm" style="width:50px;inputtext"
								styleClass="inputtext"></html:text>
								�Ա�
								<html:select property="xb">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="Ů">Ů</html:option>
								</html:select>
								���֤��
								<html:text property="sfzh" styleId="sfzh" style="width:130px;inputtext" maxlength="18"
								styleClass="inputtext"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/n05_xszz.do?method=knsrd2sh&go=go')">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								רҵ
								<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								�༶
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<!--����Ա�ύ��Ϣ-->
						<logic:present name="tjxx">
						<tr>
							<td colspan="2">
								<font color="red"><b><bean:write name="tjxx" property="bjmc"/>�ύ�����<bean:write name="tjxx" property="tjzt"/></b></font>
							</td>
						</tr>
						</logic:present>
						<!--end����Ա�ύ��Ϣ-->
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								��¼����
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="3" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<logic:equal name="isQuery" value="is">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/n05_xszz.do?method=knsrd2sq&type=modi')"
										style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="1" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     ">
										<td align=center>
											<input type="checkbox" id="pk" name="pk" <logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>
											value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:iterate id="v" name="s" offset="2" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="3">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
									</logic:equal>
									<logic:notEqual name="isQuery" value="is">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/n05_xszz.do?method=knsrd2shOne')"
										style="cursor:hand;background-color:
	<logic:iterate id="v" name="s" offset="1" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     ">    			
										<td align=center><input type="checkbox" id="pk" name="pk" <logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate> 
										value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
										<logic:iterate id="v" name="s" offset="2" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="3">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
									</logic:notEqual>
								</logic:iterate>
							</table>
							<TABLE width="99%" id="Table" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=xszzCommonN05ActionForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
	                		<logic:equal name="isQuery" value="is">
							<button class="button2" onclick="add('/xgxt/n05_xszz.do?method=knsrd2sq');"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="modi('/xgxt/n05_xszz.do?method=knsrd2sq&type=modi');"
								style="width:80px">
								�� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="del('/xgxt/n05_xszz.do?method=knsrd2sh&go=del');"
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
							&nbsp;&nbsp;&nbsp;&nbsp;
							<!-- ��ӡ���� -->
							<button class="button2" onclick="knsrd2Print('/xgxt/n05_xszz.do?method=knsrd2Print')" style="width:80px">
								���ܴ�ӡ
							</button>
							</logic:equal>
							<logic:notEqual name="isQuery" value="is">
							<button class="button2" onclick="tbkn('/xgxt/n05_xszz.do?method=knsrd2sh&go=ybkn');"
								style="width:80px">
								һ������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="tbkn('/xgxt/n05_xszz.do?method=knsrd2sh&go=kn');"
								style="width:80px">
								��    ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="kn('/xgxt/n05_xszz.do?method=knsrd2sh&go=tskn');"
								style="width:80px">
								��������
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="bkn('/xgxt/n05_xszz.do?method=knsrd2sh&go=bkn');"
								style="width:80px">
								�� �� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:present name="fdyshFlag">
							<button class="button2" onclick="tjjg()" style="width:80px">
								�ύ���
							</button>
							</logic:present>
							</logic:notEqual>
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
					</div>
				</div>
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		 <logic:notEmpty name="result">
		 	<logic:equal value="yes" name="result">
		 		<script>
		 			document.getElementById('search_go').click();
		 		</script>
		 	</logic:equal>
		 </logic:notEmpty>
	</body>
</html>
