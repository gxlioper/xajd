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
	
	function xnjxjSb(jg){	
		if(curr_row == null){
			alert('��ѡ��Ҫ��˵���Ϣ!');
			return false;
		}
		var jxjlb=$("jxjlb").value;
		var pk = curr_row.getElementsByTagName("input")[0].value;
		var url = "/xgxt/zjcm_xnjxj.do?method=xnJxjSh";
		url+="&pk="+pk;
		url+="&jg="+jg;
		showOpenWindow(url,680,450);
		//showTopWin(url,680,450);
	}
	
	function xnjxjSh(shzt){
		var checkBoxArr = document.getElementsByName("checkVal");
		var selall = document.getElementById('selall');
		var flag = false;
		var url = "/xgxt/zjcm_xnjxj.do?method=shManage&doType=save&shzt="+shzt;
		
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if(flag){
			showTips('���������У���ȴ�......');
			refreshForm(url);
		}else{
			alert("�빴ѡҪ���������");
			return false;
		}
	}
	function delSb(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var selall = document.getElementById('selall');
		var flag = false;
		
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if(flag){
			if (confirm("ȷ��Ҫɾ������ѡ�����ݣ�\nע��ֻ��ɾ���ϼ�δ��˵�����")) {
				showTips('���������У���ȴ�......');
				var url = "/xgxt/zjcm_xnjxj.do?method=jgManage&doType=del";
				refreshForm(url);
			}
		}else{
			alert("�빴ѡҪ���������");
			return false;
		}
	}
	
	function chXx(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var selall = document.getElementById('selall');
		var flag = false;
		
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if(flag){
			var pjxn=$("pjxn").value;
			var pjxq=$("pjxq").value;
			if (confirm("ȷ��Ҫ����ѡ�����걨"+pjxn+"ѧ��"+pjxq+"ѧ��У�⽱ѧ��\nע��У�ڽ�ѧ��ѧУ����ͨ�����")) {
				addXwjxj();
			}
		}else{
			alert("�빴ѡҪ�걨������");
			return false;
		}
	}
	
	function addXwjxj(){
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top) /2;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'>";
		dd_html += "----------------У�⽱ѧ���걨---------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "У�⽱ѧ��:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='xwjxj' id ='xwjxj' onchange='chJxj(this.value)'>" 
		dd_html += $('xwjxj').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button class='button2' onclick='saveXwJxj()';>ȷ��</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button class='button2' onclick='closeAdd1()'>�ر�</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}
	
	function chJxj(jxjdm){
		$("jxj").value = jxjdm;
	}
	
	function saveXwJxj(){
		var jxjdm = $("jxj").value;
		var url = "/xgxt/zjcm_xnjxj.do?method=jgManage&doType=save&jxjdm="+jxjdm;
		showTips('���������У���ȴ�......');
		refreshForm(url);
	}
	
	function xwBtn(){
		var jxjlb=$("jxjlb").value;
		var jxjdm=$("jxjdm").value;
		var flg = false;
		
		dwr.engine.setAsync(false);	
		zjcmJxjDAO.getJxjlb(jxjdm,function(data){
			if (data != null && data != "") {
				if(data == "У"){
					flg = true;
				}
			}
		});
		dwr.engine.setAsync(true);
		
		if(flg){
			$("xwBtn").style.display = "";
		}else{
			$("xwBtn").style.display = "none";
		}
	}
	
	function chLb(jxjlb){
		zjcmJxjDAO.getJxjList(jxjlb,function(data){
		if (data != null && typeof data == 'object') {
			var objId = "jxjdm";
			if($(objId) && $(objId).tagName.toLowerCase() == "select"){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,"dm","mc");			
				}
			}
		});
	}
	
	</script>
	<body onload="xyDisabled('xy');xwBtn();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/pjpy/pjpy_zjlg.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/zjcmJxjDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/zjcm_zhf" method="post">
			<input type="hidden" name="pjxn" id="pjxn" value="${pjxn}"/>
			<input type="hidden" name="pjxq" id="pjxq" value="${pjxq}"/>
			<input type="hidden" name="xyV" id="xyV" value=""/>
			<input type="hidden" name="zyV" id="zyV" value=""/>
			<input type="hidden" name="bjV" id="bjV" value=""/>
			<input type="hidden" name="njV" id="njV" value=""/>
			<input type="hidden" name="jxj" id="jxj" value=""/>
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>" />
			<input type="hidden" id="userName" name="userName" value="<bean:write name="userName" scope="session"/>" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã�<bean:write name="title"/>
				</div>
			</div>
			<logic:notEmpty name="msg">
				<div align="center"><FONT color="red"><bean:write name="msg"/></FONT></div>
			</logic:notEmpty>
			<logic:empty name="msg">
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
									<html:select property="xn" style="">
										<html:option value=""></html:option>
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									ѧ�ڣ�						
									<html:select property="xq" style="">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									&nbsp;&nbsp;ѧ�ţ�
									<logic:equal name="userType" value="stu">
									<html:text property="xh" style="width:85px" maxlength="20" readonly="true"></html:text>
									</logic:equal>
									<logic:notEqual name="userType" value="stu">
									<html:text property="xh" style="width:85px" maxlength="20"></html:text>
									</logic:notEqual>
									&nbsp;&nbsp;������
									<logic:equal name="userType" value="stu">
									<html:text property="xm" style="width:85px" maxlength="20" readonly="true"></html:text>		
									</logic:equal>		
									<logic:notEqual name="userType" value="stu">
									<html:text property="xm" style="width:85px" maxlength="20"></html:text>		
									</logic:notEqual>			
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/zjcm_xnjxj.do?method=jgManage')">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									<bean:message key="lable.xsgzyxpzxy" />��
									<logic:equal name="userType" value="stu">
									<html:select property="xydm" style="" styleId="xy" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="stu">
									<html:select property="xydm" style="" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:notEqual>
									&nbsp;&nbsp;רҵ��
									<logic:equal name="userType" value="stu">
									<html:select property="zydm" style="" styleId="xy" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="stu">
									<html:select property="zydm" style="" styleId="zy"
										onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									</logic:notEqual>
									&nbsp;&nbsp;�༶��
									<logic:equal name="userType" value="stu">
									<html:select property="bjdm" style="" styleId="xy" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="stu">
									<html:select property="bjdm" style="" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<td>
								��ѧ�����
									<html:select property="jxjlb" style="" onchange="chLb(this.value);">
										<html:option value=""></html:option>
										<html:options collection="jxjLbList" property="dm" labelProperty="mc" />
									</html:select>
								&nbsp;&nbsp;��ý�ѧ��
									<html:select property="jxjdm" style="" onchange="">
										<html:options collection="xnJxjList" property="dm" labelProperty="mc" />
									</html:select>
									<html:select property="xwjxj" style="display:none">
										<html:options collection="xwJxjList" property="dm" labelProperty="mc" />
									</html:select>	
								&nbsp;&nbsp;ѧУ��ˣ�
								<html:select property="xxsh" style="">
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="δͨ��">δͨ��</html:option>
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
							<font color="blue">��ʾ��������ͷ��������;˫���ɲ鿴��ϸ</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" id="selall" name="selall"
											onclick="selAll()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand" ondblclick="xnjxjSb('yes')">
									<td>
									<input type="checkbox" id="checkVal" name="checkVal" 
								 		value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>">
									</td>	
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2" >
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
								onclick="xnjxjSb('yes');"
								style="width:80px">
								��	��
							</button>
							&nbsp;&nbsp;
							<logic:notEmpty name="canWrite">
							<button class="button2"
								onclick="chXx()"
								id="xwBtn"
								style="display:none">
								У�⽱ѧ���걨
							</button>
							&nbsp;&nbsp;
							<button class="button2"
								onclick="delSb();"
								style="width:80px">
								ɾ	��
							</button>
							</logic:notEmpty>
						</logic:equal>
					</div>
				</center>
				<div id="tmpdiv"></div>
			</div>
			</logic:empty>
			<div id="tmpdiv1"></div>
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��,��ѡѧ��û��һ�����ʸ���ѡУ�⽱ѧ��,\n���߽���ͨ��У�⽱ѧ����ˣ���ȷ�ϣ�");
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
