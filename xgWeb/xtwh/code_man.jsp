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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<body onload="pageCardOn('')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/twgzFunction.js"></script>
		<script type="text/javascript" src="pjpy/nblg/nblgjs/nblgjs.js"></script>
		<script type="text/javascript" src="pjpy/shcbys/shcbys.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/systemFunction"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzDao.js'></script>
		<script language="javascript">
			function addyz(){
				var tName = document.getElementById('tName').value;
				if(tName == "gdnzzyxy_dknlldmb"){
					alert("不能增加年利率数据！");
					return false;
				}
				SelcodeConf('add');
			}
			function Delyz(){
				var tName = document.getElementById('tName').value;
				if(tName == "bjlydx_knssrly"){
					alert("收入来源项目不能被删除！");
					return false;
				}
				if(tName == "gdnzzyxy_dknlldmb"){
					alert("年利率不能被删除！");
					return false;
				}
				codeConf('del');
			}
			
			function DelAllyz(){
				var tName = document.getElementById('tName').value;
				if(tName == "bjlydx_knssrly"){
					alert("收入来源项目不能被删除！");
					return false;
				}	
				if(tName == "gdnzzyxy_dknlldmb"){
					alert("年利率不能被删除！");
					return false;
				}			
				Alldel();
			}
		</script>
		<script type="text/javascript">
			function SelcodeConf(flag){
				var xxdm = document.getElementById('xxdm').value;
				var realTable = document.getElementById('realTable').value;
				var codeType = document.getElementById("codeType").value;
				if(codeType == "twgz"){
					twgzCodeConf(flag);
				} else if (codeType == 'prise') {
					if ((xxdm=='13022' && realTable=='jxjlbdmb') || (xxdm=='13022' && realTable=='jxjdmb')) {
						if (flag=='add') {
							addjxjdm(realTable);
						} else {
							modijxjdm(realTable);
						}
					} else {
						codeConf(flag);
					}
				} else{
					codeConf(flag);
				}
			}
			function modidm(url,dotype){
				if (curr_row==null || curr_row =='') {
					alert('请选择要操作的行数据！');
					return;
				} else {
					var realTable = document.getElementById('realTable').value;
					url += realTable;
					url += '&pkValue=';
					url += curr_row.cells[1].innerText;
					url += '&xydm=';
					if (dotype=='del') {
						if (confirm('（请勿轻易删除该行）\n确定要删除选中的行吗？')) {
							var dm;
							if (realTable=='jxjdmb') {
								dm = curr_row.cells[0].getElementsByTagName("input")[0].value;
								url += dm;
							}
							if (realTable=='rychdmb') {
								dm = curr_row.cells[0].getElementsByTagName("input")[0].value;
								url += dm;
							}
							var xybmdm = document.getElementById('xybmdm').value;
							if (realTable=='jxjdmb' || realTable=='rychdmb') {
								if (xybmdm==dm) {
								refreshForm(url);
								} else {
								alert('您无权删除该信息，请确认！');
								}
							} else {
								refreshForm(url);
							}
							return;
						}
					}
					else {
						showTopWin(url,'550','380');
						return;
					}
				}
			}
			function delalldm(url) {
				url += document.getElementById('realTable').value;
				if (confirm('（请勿轻易删除该行）\n确定要删除所有的数据吗？')) {
					refreshForm(url);
				}
			}
			function adddm() {
				var realTable = document.getElementById('realTable').value;
				if (realTable == 'jxjdmb') {
					showTopWin('pjpy_hygxy_jxjdmwh.do',480,340);
				}else if (realTable == 'jxjlbdmb') {
					showTopWin('pjpy_hygxy_jxjlbdmwh.do',480,340);
				} else {
					addyz();
				}
			}
			function modidm_hy() {
				var realTable = document.getElementById('realTable').value;
				var dm = curr_row.cells[1].innerText;
				if (realTable == 'jxjdmb') {
					showTopWin('pjpy_hygxy_jxjdmmodi.do?pkValue=' + dm,480,340);
				}else if (realTable == 'jxjlbdmb') {
					showTopWin('pjpy_hygxy_jxjlbdmmodi.do?pkValue=' + dm,480,340);
				} else {
					SelcodeConf('modi');
				}
			}
		</script>
		<html:form action="/code_man" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：系统维护 - 代码维护 -<span id="expTit">
					<bean:write name="tips" scope="request" /></span>
				</div>
			</div>
			<input type="hidden" id="delV" name="delV" value="" />
			<input type="hidden" id="delKey" name="delKey" value="" />
			<input type="hidden" id="htmlContent" name="htmlContent" value="" />
			<input type="hidden" id="selToInit1" name="selToInit1"
				value="<bean:write name="selInit_1" scope="request"/>" />
			<input type="hidden" id="selToInit2" name="selToInit2"
				value="<bean:write name="selInit_2" scope="request"/>" />
			<input type="hidden" id="selToInit3" name="selToInit3"
				value="<bean:write name="selInit_3" scope="request"/>" />
			<input type="hidden" id="selToInit4" name="selToInit4"
				value="<bean:write name="selInit_4" scope="request"/>" />
			<input type="hidden" id="selToInit5" name="selToInit5"
				value="<bean:write name="selInit_5" scope="request"/>" />
			<input type="hidden" id="tname" name="tName"
				value="<bean:write name="tName" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="tName" scope="request"/>" />
			<input type="hidden" id="codeType" name="codeType"
				value="<bean:write name="codeType" scope="request"/>" />
			<input type="hidden" id="pk" name="pk" value="delcode" />
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>"/>
			<input type="hidden" id="xybmdm" name="xybmdm" value="${xybmdm }"/>
			<input type="hidden" id="codeByDept" name="codeByDept" value="${codeByDept }"/>
			<input type="hidden" id="userName" name="userName" value="${userName}"/>
			<div class="xxk">
				<logic:notEmpty name="pageCard">
				
					<logic:iterate id="card" name="pageCard">
						<!-- 在代码中用br来标示换行，避免产生杂乱的图标     sjf-->
						<logic:equal value="br" name="card" property="cn">
							<br/>
							<br/>
							<br/>
						</logic:equal>
						
						<logic:notEqual value="br" name="card" property="cn">
						<ul>
							<li id="<bean:write name="card" property="en"/>l"
								class="xxk_off_l"></li>
							<li id="<bean:write name="card" property="en"/>"
								onclick="chgCode(this)" class="xxk_off_m">
								&nbsp;
								<bean:write name="card" property="cn" />
								&nbsp;
							</li>
							<li id="<bean:write name="card" property="en"/>r"
								class="xxk_off_r"></li>
						</ul>
						</logic:notEqual>
					</logic:iterate>
				</logic:notEmpty>
			</div>

			<fieldset>
				<legend>
					基础代码维护&nbsp;&nbsp;<font color="red">(建议:&nbsp;对代码进行统一编码，同时请勿轻易删除以下记录！)</font>
				</legend>
				<logic:present name="showcsmz">
					<table width="100%" id="rsTable" class="tbstyle">
					
					<logic:equal value="yes" name="iscsmz">
					<thead>
						<tr align="center" style="cursor:hand">
							<logic:iterate id="tit" name="topTr" offset="1">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)">
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
						<logic:notEmpty name="rs">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand" align="center">
								<td>
									<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v" /></logic:iterate>"/>
									<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v" /></logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td><bean:write name="v" /></td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:notEmpty>	
					</logic:equal>
					<logic:notPresent name="iscsmz">
					<thead>
						<tr align="center" style="cursor:hand">
							<logic:iterate id="tit" name="topTr">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)">
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:notEmpty name="rs">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand" align="center">
								<logic:iterate id="v" name="s">
									<td><bean:write name="v" /></td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					</logic:notPresent>
				</table>
				</logic:present>
				<logic:notPresent name="showcsmz">
					<table width="100%" id="rsTable" class="tbstyle">
					<thead>
						<tr align="center" style="cursor:hand">
							<logic:iterate id="tit" name="topTr">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)">
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:notEmpty name="rs">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand" align="center">
								<logic:iterate id="v" name="s">
									<td><bean:write name="v" /></td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
				</table>
				</logic:notPresent>
				
				<div id="tmpdiv"></div>
				</fieldset>
				<br><br><br><br>
				<div class="buttontool" id="btn" style="position: absolute;left:0px;top:100px" width="100%">
				<logic:equal value="yes" name="writeAble" scope="request">
					<%--长沙民政评奖评优--%>
					<logic:present name="showcsmz">
						<%--军训奖项代码表--%>
						<logic:equal value="jxjxdmb" name="tName">
							<button type="button" class="button2" onclick="addyz()">
								&nbsp;&nbsp;增&nbsp;&nbsp;加&nbsp;&nbsp;
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="Delyz()">
								&nbsp;&nbsp;删&nbsp;&nbsp;除&nbsp;&nbsp;
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="SelcodeConf('modi')">
								&nbsp;&nbsp;修&nbsp;&nbsp;改&nbsp;&nbsp;
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:equal>
						<%--end军训奖项代码表--%>
						
						<%--非奖学金代码--%>
						<logic:notEqual value="jxjxdmb" name="tName">
							<button type="button" class="button2" onclick="showTopWin('pjpydmwhadd.do?realTable='+document.getElementById('realTable').value,'550','380')">
								&nbsp;&nbsp;增&nbsp;&nbsp;加&nbsp;&nbsp;
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="modidm('pjpydmwhdel.do?realTable=','del')">
								&nbsp;&nbsp;删&nbsp;&nbsp;除&nbsp;&nbsp;
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="modidm('pjpydmmodi.do?realTable=','modi')">
								&nbsp;&nbsp;修&nbsp;&nbsp;改&nbsp;&nbsp;
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:notEqual>
						<button type="button" class="button2" onclick="impAndChkData();" style="width:80px">
							导入数据
						</button>
					</logic:present>
					<%--end长沙民政评奖评优--%>
					
					<%--非长沙民政评奖评优--%>
					<logic:notPresent name="showcsmz">
						<logic:equal value="11049" name="xxdm"><!-- 淮阴工<bean:message key="lable.xsgzyxpzxy" /> -->
								<button type="button" class="button2" onclick="adddm()">
									&nbsp;&nbsp;增&nbsp;&nbsp;加&nbsp;&nbsp;
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2" onclick="Delyz()">
									&nbsp;&nbsp;删&nbsp;&nbsp;除&nbsp;&nbsp;
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" class="button2" onclick="modidm_hy()">
									&nbsp;&nbsp;修&nbsp;&nbsp;改&nbsp;&nbsp;
								</button>
						</logic:equal>
						<logic:notEqual value="11049" name="xxdm"><!-- 其它学校 -->
							<button type="button" class="button2" <logic:equal value="11733" name="xxdm">onclick="shysadddm()"</logic:equal><logic:notEqual value="11733" name="xxdm">onclick="addyz()"</logic:notEqual>>
								&nbsp;&nbsp;增&nbsp;&nbsp;加&nbsp;&nbsp;
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="Delyz()">
								&nbsp;&nbsp;删&nbsp;&nbsp;除&nbsp;&nbsp;
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" <logic:equal value="11733" name="xxdm">onclick="modidm()"</logic:equal><logic:notEqual value="11733" name="xxdm">onclick="SelcodeConf('modi')"</logic:notEqual>>
								&nbsp;&nbsp;修&nbsp;&nbsp;改&nbsp;&nbsp;
							</button>
						</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="impAndChkData();" style="width:80px">
						导入数据
					</button>
					</logic:notPresent>
					<%--非长沙民政评奖评优--%>
				</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="dataExport()" style="width:80px">
						导出数据
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="expTab('rsTable','','expTit')" style="width:80px">
						打印列表
					</button>
				</div>
			
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
