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
		<script type="text/javascript">
			function checkxnjxjdmNotNull(){
				if (document.getElementById('xn').selectedIndex<=0 || document.getElementById('jxjdm').selectedIndex<=0){
					alert('ѧ��ͽ�ѧ���ѡ����ѡ����ٲ�ѯ��');
				}else{
					allNotEmpThenGo('zyjxjsbsearch.do');
				}
			}
function xsdetailsset(url,w,h) {
				if (curr_row == null || curr_row == ''){
					alert('��ѡ��Ҫ�������У�');
				}else{
					url += curr_row.cells[0].getElementsByTagName("input")[1].value;
					url += '&xh=';
					url += curr_row.cells[0].getElementsByTagName("input")[2].value;
					url += '&xn=';
					url += curr_row.cells[0].getElementsByTagName("input")[3].value;
					url += '&jxjdm=';
					url += curr_row.cells[0].getElementsByTagName("input")[0].value;
					showTopWin(url,w,h);
				}
			}
function zyjxjautosh(url) {
	if (document.forms[0].jxjdm.value=="") {
		alert('��ѡ��Ҫ�Զ���˵Ľ�ѧ��!');
		return;
	}
	if (document.forms[0].xy.value == "") {
		alert("��ѡ��Ҫ�Զ���˵�<bean:message key="lable.xsgzyxpzxy" />��");
		return;
	} else {
		var confirmTxt = "�Զ���˽���";
		if (document.getElementById('bj').selectedIndex>=1) {
			confirmTxt += "\"�༶\"Ϊ��λ������ˣ��������Ϊ����\��������\"�����õĸ���\"����\"���ޡ�";
		} else {
			if (document.getElementById('zy').selectedIndex>=1) {
				confirmTxt += "\"רҵ\"Ϊ��λ������ˣ��������Ϊ����\��������\"�����õĸ���\"����\"���ޡ�";
			} else {
				confirmTxt += "\"<bean:message key="lable.xsgzyxpzxy" />\"Ϊ��λ������ˣ��������Ϊ����\��������\"�����õĸ���\"����\"���ޡ�";
			}
		}
		confirmTxt += "\n��Ҫ�ı���˷�ʽ������\"ȡ��\"��";
		confirmTxt += "\n\n�Զ���˽��ķѽϳ���ʱ�䣬ȷ��Ҫ��ʼ�Զ������";
		if (confirm(confirmTxt)) {
			var dd_html = "";
			dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>�����Զ���ˣ����Ժ�......<br><br>";
			dd_html += "<span class='roll_tip'></span>";
			dd_html += "</td></tr></table>";
			for (i = 1; i < document.getElementsByTagName("table").length; i++) {
				document.getElementsByTagName("table")[i].style.display = "none";
			}
			showDiv(dd_html, 300, 120);
			alert("���\"ȷ��\"��ʼ��ˣ�");
			refreshForm(url);
			return;
		} else {
			return;
		}
	}
}
		
		function shSubmit1(res){
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		document.forms[0].action="/xgxt/zyjxjsh.do?param1="+res;
		document.forms[0].submit();
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮����ȷ������");
	}
}
		
		</script>
	<script language="javascript" src="js/function.js"></script>	
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script language="javascript" src="/pjpy/jgsdx/jgsdxjs.js"></script>
	<script type="text/javascript" src="wjcf/shgc/shgcjs/shgcjs.js"></script>
	<body onload="xyDisabled('xy');">
		
		<html:form action="/pjpyjgsdxwh" method="post" >
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="xyV" id="xyV" />
		<input type="hidden" name="tableName" id="tableName" value="view_xszyjxj"/>
		<input type="hidden" name="realTable" id="realTable" value="xszyjxjb"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�
				<bean:message bundle="pjpyjgsdx" key="pjpy_jgsdx_zyjxjsb" />
				</div>
			</div>
			<input type="hidden" id="userType" name="userType" 
				value="${userType }" />
			<fieldset>
				<legend>
					����ѡ��
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								ѧ�꣺
								<html:select property="xn" style="width:90px" 
									styleId="xn" onchange="refreshForm('refreshjlje.do')">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;
								�꼶��
								<html:select property="nj" onchange="initBjList()" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;
								��ѧ��
								<html:select property="jxjdm" style="width:180px" onchange="refreshForm('refreshjlje.do')" styleId="jxjdm">
									<html:option value=""></html:option>
									<html:options collection="zyjxjList" property="jxjdm"
										labelProperty="jxjmc" />
								</html:select>
								&nbsp;&nbsp;
								������
								<html:text property="jlje" styleId="jlje" readonly="true"
								style="width:90px" styleClass="inputtext"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="checkxnjxjdmNotNull()">
									��ѯ
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;
								רҵ��
								<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;
								�༶��
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								
							</td>
						</tr>
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
					<br><logic:notEmpty name="rs">
						<fieldset>
							<legend>
								��¼����
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">��ʾ��${fdyxx }</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										<br></td>
										<logic:iterate id="tit" name="topTr" offset="2" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											<br></td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										style="cursor:hand;" ondblclick="xsdetailsset('setzhszandxxcj.do?pkValue=','550','350')">
										<td align=center>
										<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
										<input type="hidden" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"/>
										 <input type="hidden" value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>">
										 <input type="hidden" value="<logic:iterate id="v" name="s" offset="4" length="1"><bean:write name="v"/></logic:iterate>">
										<input type="checkbox" id="cbv" name="cbv" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" />
									   
									    <br></td>
										<logic:iterate id="v" name="s" offset="2">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<button class="button2" style="width:80px"
								onclick="shSubmit1('tg');">
								���ͨ��
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" style="width:80px"
								onclick="shSubmit1('btg');">
								��˲�ͨ��
							</button>
							&nbsp;&nbsp;&nbsp;
							<logic:equal value="true" name="showxyxx">
								<button class="button2" style="width:80px"
								onclick="zyjxjautosh('zyjxjautosh.do');">
									�Զ����
								</button>
							&nbsp;&nbsp;&nbsp;
							</logic:equal>
							<button class="button2" style="width:80px"
								onclick="if (document.getElementById('xn').selectedIndex<=0 || document.getElementById('jxjdm').selectedIndex<=0) alert('ѧ��ͽ�ѧ���ѡ����ѡ����ٲ�ѯ��'); else showTopWin('zyjxjprint.do?xn='+document.getElementById('xn').value,800,600);">
								��ӡ/Ԥ��
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" style="width:80px" id="btn_imp" onclick="impAndChkData();">
								���ݵ���
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" style="width:80px" id="btn_exp" onclick="dataExport()">
								���ݵ���
							</button>
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
					</div>
				</div>
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		 <logic:present name="result">
		 	<logic:equal value="view" name="result">
		 		<script>
		 			alert('�����ɹ���');
		 			document.getElementById('search_go').click();
		 		</script>
		 	</logic:equal>
		 	<logic:equal value="noview" name="result">
		 		<script>
		 			alert('�����ɹ���');
		 		</script>
		 	</logic:equal>
		 </logic:present>
	</body>
</html>
