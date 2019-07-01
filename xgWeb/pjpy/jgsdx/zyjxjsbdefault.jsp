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
		<meta name="Copyright" content="正方软件 zfsoft" />
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
					alert('学年和奖学金必选，请选择后再查询！');
				}else{
					allNotEmpThenGo('zyjxjsbsearch.do');
				}
			}
function xsdetailsset(url,w,h) {
				if (curr_row == null || curr_row == ''){
					alert('请选择要操作的行！');
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
		alert('请选择要自动审核的奖学金!');
		return;
	}
	if (document.forms[0].xy.value == "") {
		alert("请选择要自动审核的<bean:message key="lable.xsgzyxpzxy" />！");
		return;
	} else {
		var confirmTxt = "自动审核将以";
		if (document.getElementById('bj').selectedIndex>=1) {
			confirmTxt += "\"班级\"为单位进行审核，审核依据为您在\条件设置\"中设置的各个\"条件\"上限。";
		} else {
			if (document.getElementById('zy').selectedIndex>=1) {
				confirmTxt += "\"专业\"为单位进行审核，审核依据为您在\条件设置\"中设置的各个\"条件\"上限。";
			} else {
				confirmTxt += "\"<bean:message key="lable.xsgzyxpzxy" />\"为单位进行审核，审核依据为您在\条件设置\"中设置的各个\"条件\"上限。";
			}
		}
		confirmTxt += "\n若要改变审核方式，请点击\"取消\"。";
		confirmTxt += "\n\n自动审核将耗费较长的时间，确定要开始自动审核吗？";
		if (confirm(confirmTxt)) {
			var dd_html = "";
			dd_html += "<table width='200' class='tbstyle'><tr><td height='60' align='center'>正在自动审核，请稍候......<br><br>";
			dd_html += "<span class='roll_tip'></span>";
			dd_html += "</td></tr></table>";
			for (i = 1; i < document.getElementsByTagName("table").length; i++) {
				document.getElementsByTagName("table")[i].style.display = "none";
			}
			showDiv(dd_html, 300, 120);
			alert("点击\"确定\"后开始审核！");
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
		alert("没有选择相应记录，请选择之后再确定！！");
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
					当前所在位置：
				<bean:message bundle="pjpyjgsdx" key="pjpy_jgsdx_zyjxjsb" />
				</div>
			</div>
			<input type="hidden" id="userType" name="userType" 
				value="${userType }" />
			<fieldset>
				<legend>
					条件选择
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学年：
								<html:select property="xn" style="width:90px" 
									styleId="xn" onchange="refreshForm('refreshjlje.do')">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;
								年级：
								<html:select property="nj" onchange="initBjList()" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;
								奖学金：
								<html:select property="jxjdm" style="width:180px" onchange="refreshForm('refreshjlje.do')" styleId="jxjdm">
									<html:option value=""></html:option>
									<html:options collection="zyjxjList" property="jxjdm"
										labelProperty="jxjmc" />
								</html:select>
								&nbsp;&nbsp;
								奖励金额：
								<html:text property="jlje" styleId="jlje" readonly="true"
								style="width:90px" styleClass="inputtext"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="checkxnjxjdmNotNull()">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;
								专业：
								<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;
								班级：
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
							未找到任何记录！
						</p>
					</logic:empty>
					<br><logic:notEmpty name="rs">
						<fieldset>
							<legend>
								记录数：
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：${fdyxx }</font>
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
								审核通过
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" style="width:80px"
								onclick="shSubmit1('btg');">
								审核不通过
							</button>
							&nbsp;&nbsp;&nbsp;
							<logic:equal value="true" name="showxyxx">
								<button class="button2" style="width:80px"
								onclick="zyjxjautosh('zyjxjautosh.do');">
									自动审核
								</button>
							&nbsp;&nbsp;&nbsp;
							</logic:equal>
							<button class="button2" style="width:80px"
								onclick="if (document.getElementById('xn').selectedIndex<=0 || document.getElementById('jxjdm').selectedIndex<=0) alert('学年和奖学金必选，请选择后再查询！'); else showTopWin('zyjxjprint.do?xn='+document.getElementById('xn').value,800,600);">
								打印/预览
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" style="width:80px" id="btn_imp" onclick="impAndChkData();">
								数据导入
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" style="width:80px" id="btn_exp" onclick="dataExport()">
								数据导出
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
		 			alert('操作成功！');
		 			document.getElementById('search_go').click();
		 		</script>
		 	</logic:equal>
		 	<logic:equal value="noview" name="result">
		 		<script>
		 			alert('操作成功！');
		 		</script>
		 	</logic:equal>
		 </logic:present>
	</body>
</html>
