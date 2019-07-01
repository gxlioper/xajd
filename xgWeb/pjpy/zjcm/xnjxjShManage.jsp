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
	<script language="javascript">	
	
	function xnjxjSb(kshrs){	
		if(curr_row == null){
			alert('��ѡ��Ҫ��˵���Ϣ!');
			return false;
		}
		var jxjdm  = $("jxjdm").value;
		var userType = $("userType").value;
		var cjrs = $("cjrs").value;
		var pk = curr_row.getElementsByTagName("input")[0].value;
		var url = "/xgxt/zjcm_xnjxj.do?method=xnJxjSh";
		url+="&pk="+pk;
		url+="&jxjdm="+jxjdm;
		url+="&cjrs="+cjrs;
		
		var selectId = $("jxjdm").selectedIndex;
		var jxjmc = $("jxjdm").options[selectId].text;
		var isDxjxj = true;
		
		for(var i=0;i<jxjmc.length;i++){
			if(i>0){
				var mc = jxjmc.charAt(i-1)+jxjmc.charAt(i);
				if(mc == "һ��" || mc == "����" || mc == "����"){
					isDxjxj = false;
					break;
				}
			}
		}
		
		if(userType == "xx" || userType == "admin"){
			var xxsh = curr_row.cells[10].innerText;
			if(kshrs == 0 && xxsh !="ͨ�� "){
				if(isDxjxj){
					url+="&dxj=yes";
				}else{
					url+="&cjsh=yes";
				}
			}
		}
		showTopWin(url,680,450);
	}
	
	function xwjxjView(){
		var pk = curr_row.getElementsByTagName("input")[0].value;
		var url = "/xgxt/zjcm_xnjxj.do?method=xnJxjSh";
		url+="&pk="+pk;
		url+="&jg=yes";
		//showTopWin(url,680,450);
		showOpenWindow(url,680,450);
	}
	
	function searchJxjSq(){
		var jxjdm = $("jxjdm").value;
		var bjdm = $("bjdm").value;
		if(jxjdm == ""){
			alert("��ȷ����Ҫ��˵Ľ�ѧ��");
			return false;
		}
		if(bjdm == ""){
			alert("��ȷ����Ҫ��˵İ༶");
			return false;
		}
		allNotEmpThenGo('/xgxt/zjcm_xnjxj.do?method=shManage');
	}
	function xnjxjSh(shzt,kshrs){
		var checkBoxArr = document.getElementsByName("checkVal");
		var jxjdm = $("jxjdm").value;
		var flag = false;
		var chknum = "0"
		var userType = $("userType").value;
		var url = "/xgxt/zjcm_xnjxj.do?method=shManage&doType=save&shzt="+shzt;
		url += "&jxjdm="+jxjdm;
		
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
				if($("xxsh"+i).value != "ͨ��"){
					chknum++;
				}
			}
		}
		
		var selectId = $("jxjdm").selectedIndex;
		var jxjmc = $("jxjdm").options[selectId].text;
		var isDxjxj = true;
		
		for(var i=0;i<jxjmc.length;i++){
			if(i>0){
				var mc = jxjmc.charAt(i-1)+jxjmc.charAt(i);
				if(mc == "һ��" || mc == "����" || mc == "����"){
					isDxjxj = false;
					break;
				}
			}
		}
		
		if(flag){
			if(jxjdm == ""){
				alert("��ȷ����Ҫ��˵Ľ�ѧ��");
				return false;
			}
			if(userType == "xx" || userType == "admin"){
			if(shzt == "tg" && chknum > kshrs){
				if(isDxjxj){
					alert("����������ڸý�ѧ��ʣ������,��ȷ�ϣ���");
					return false;
				}
				if (confirm("����������ڸý�ѧ��ʣ�����������Զ����ͨ����μ���ѧ��\nͬ���밴'ȷ��'��ť,������ȡ��")) {
					 url = "/xgxt/zjcm_xnjxj.do?method=shManage&doType=cjsave&shzt="+shzt;
				}else{
					return false;
				}
			}
			}
			showTips('���������У���ȴ�......');
			refreshForm(url);
		}else{
			alert("�빴ѡҪ���������");
			return false;
		}
	}
	function chRs(shzt,num){
		var jxjrs = $("jxjrs").value;
		var hdrs = $("hdrs").value;
		var kshrs="0";
		if(jxjrs !="" && hdrs != ""){
			if(jxjrs > hdrs){
				kshrs = parseInt(jxjrs)-parseInt(hdrs);
			}
		}
		
		if(num == "2"){
			xnjxjSh(shzt,kshrs);
		}else if(num == "3"){
			xwjxjView();
		}else{
			xnjxjSb(kshrs);
		}
	}
	function jxjsh(istg) {
		var jxjdm = $("jxjdm").value;
		var bjdm = $("bjdm").value;
		var url = "/xgxt/zjcm_xnjxj.do?method=shManage&go=save&lb=" + istg;
		var userType = document.getElementById('userType').value;
		if((jxjdm == ""||bjdm==""||jxjdm==null||bjdm==null) && 'xy'==userType){
			alert("��ѡ��Ҫ��˵İ༶�ͽ�ѧ��!");
			return false;
		}
		if (isSelect('checkVal')) {
			var userType = document.getElementById('userType').value;
			if (istg=='yes' && 'xy' == userType) {
				var jxjrs = document.getElementById('jxjrs').value;
				var hdrs = document.getElementById('hdrs').value;
				var chknum = 0;
				var checkBoxArr = document.getElementsByName('checkVal');

				for(var i=0;i<checkBoxArr.length;i++){
					if(checkBoxArr[i].checked==true){
						chknum++;
					}
				}
				jxjrs = jxjrs != null && jxjrs != "" ? parseInt(jxjrs) : 0;
				hdrs = hdrs != null && hdrs != "" ? parseInt(hdrs) : 0;

				if (jxjrs !=0 && ((hdrs + chknum) > jxjrs)) {
					alert("�ý�ѧ�����ͨ�������ѳ�����������\n�����" + jxjrs + "�ˣ������ͨ����" + hdrs + "�ˣ���ǰ��ˣ�" + chknum + "�ˡ�");
					return false;
				}
			} 
			refreshForm(url);
		}
	}
	//˫��һ����ʾ��ϸ��Ϣ
	function viewJxjDetails(type) {
		if (curr_row == null) {
			alert("��ѡ��Ҫ�����������У�");
			return false;
		}
		var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
		if (pk == null || pk == "") {
			alert("��ѡ��Ҫ�����������У�");
			return false;
		} else {
			showTopWin('pjpy_zjcm_jxjshDetails.do?pkValue=' + pk + '&go='+type,620,540);
		}
	}
	</script>
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/zjcmZhfDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="pjpy/zjcm/cssz/cssz.js"></script>
		<html:form action="/zjcm_zhf" method="post">
			<input type="hidden" name="pjxn" id="pjxn" value="${pjxn}"/>
			<input type="hidden" name="pjxq" id="pjxq" value="${pjxq}"/>
			<input type="hidden" name="jxjrs" id="jxjrs" value="${jxjrs}"/>
			<input type="hidden" name="cjrs" id="cjrs" value="${cjrs}"/>
			<input type="hidden" name="hdrs" id="hdrs" value="${hdrs}"/>
			<input type="hidden" name="msg" id="msg" value="${msg}"/>
			<input type="hidden" name="xyV" id="xyV" value=""/>
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<input type="hidden" name="njV" id="njV" value=""/>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="userName" name="userName" value="${userName }"/>
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName }" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" id="isFdy" name="isFdy" value="${isFdy }" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�<bean:write name="title"/>
				</div>
			</div>
			<div class="rightcontent">
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									�꼶��
									<html:select property="nj" style=""
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									ѧ�꣺
									<html:select property="xn" style="" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									ѧ�ڣ�
									<html:select property="xq" style="" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;ѧ�ţ�
									<html:text property="xh" style="width:85px" maxlength="20"></html:text>
									&nbsp;&nbsp;&nbsp;������
									<html:text property="xm" style="width:85px" maxlength="20"></html:text>								
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
<%--									<input type="hidden" name="go" value="a" />--%>
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/zjcm_xnjxj.do?method=shManage&go=go')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />��
									<html:select property="xydm" style="" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;רҵ��
									<html:select property="zydm" style="" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;�༶��
									<html:select property="bjdm" style="" styleId="bj" onchange="refreshForm('/xgxt/zjcm_xnjxj.do?method=shManage')">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td>
									��ý�ѧ��
									<html:select property="jxjdm" style="" onchange="refreshForm('/xgxt/zjcm_xnjxj.do?method=shManage')">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="dm" labelProperty="mc" />
									</html:select>

								&nbsp;&nbsp;��˽����
								<html:select property="sh" style="">
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
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
							<font color="blue">��ʾ��������ͷ��������;˫����¼�ɲ鿴��ϸ;</font>
							<font color="red">��ɫ��������Ϊ������У�ڼ��ܹ�����.</font>
							<logic:equal value="yes" name="view">
							<font color="red">ע���ý�ѧ�����������Ϊ&nbsp;${jxjrs }&nbsp;�ˣ�����ͨ��&nbsp;${hdrs }&nbsp;��.</font>
							</logic:equal>
							
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand;">
									<td >
										<input type="checkbox" id="selall" name="selall" style="height: 17.5px"
											onclick="selAll()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="3">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" ondblclick="viewJxjDetails('view')" style="cursor:hand;color:<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>">
									<td>
									<input type="checkbox" id="checkVal" name="checkVal" style="height: 17.5px"
								 		value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" <logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>>
								 	<input type=hidden id="xxsh${index}" value="<logic:iterate id="v" name="s" offset="10" length="1"><bean:write name="v"/></logic:iterate>"/>
									</td>	
									<td>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="4" >
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						<TABLE width="100%" id="Table" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=pjpyZjcmActionForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
					</fieldset>
				</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal name="writeAble" value="yes">
							<button class="button2"
								onclick="viewJxjDetails('')"
								style="width:80px">
								�������
							</button>
							&nbsp;&nbsp;
							<button class="button2"
								onclick="jxjsh('yes')"
								style="width:80px">
								���ͨ��
							</button>
							&nbsp;&nbsp;
							<button class="button2"
								onclick="jxjsh('no')"
								style="width:80px">
								��˲�ͨ��
							</button>
						</logic:equal>
					</div>
				</center>
				<div id="tmpdiv"></div>
			</div>
		</html:form>
		<script language="javascript">
                  document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
                  document.getElementById("btn").style.width = "96%";
                  window.setInterval("initBTNTool('btn')",1);
                </script>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
				document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��!"+$("msg").value);
				document.getElementById('search_go').click();
			</script>
		</logic:equal>

	</body>
</html>
