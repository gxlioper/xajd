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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript"></script>
	<body onload="xyDisabled('xy');chgDispconf('dispFlag')">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_whlgdx.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getJxjList.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/prise_conf_rs" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>
			<input type="hidden" id="isFdy" value=""/>
			<input type="hidden" id="zyV" name="zyV"/>
			<input type="hidden" id="bjV" name="bjV"/>
			<input type="hidden" id="xq" name="xq" value="${xq }"/>
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
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<fieldset>
				<legend>
					基本参数
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学年：
								<html:select property="xn" style="width:120px" disabled="true"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;年度：
								<html:select property="nd" style="width:90px" disabled="true"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>

								<%--非武汉理工大学--%>
								<logic:notEqual value="10497" name="xxdm">
									&nbsp;&nbsp;奖学金：
									<html:select property="xmdm" style="width:150px"
										styleId="jxjdm">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm"
											labelProperty="jxjmc" />
									</html:select>
								</logic:notEqual>
								<%--非武汉理工大学end--%>

								&nbsp;&nbsp;显示方式：
								<html:select property="dispFlag" style="width:70px"
									styleId="dispFalg" onchange="chgDispconf('dispFlag');">
									<html:option value="xydm"><bean:message key="lable.xsgzyxpzxy" /></html:option>
									<html:option value="zydm">专业</html:option>
									<html:option value="bjdm">班级</html:option>
								</html:select>
							</td>

							<%--武汉理工大学--%>
							<logic:equal value="10497" name="xxdm">
								<td width="10" rowspan="3" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="listPriseConfJxj('/xgxt/prise_conf_rs.do')">
										查询
									</button>
								</td>
								<tr>
									<td>
										奖学金分类：
										<html:select property="jxjfl" style="width:150px" styleId="jxjfl" onchange="initJxjList()">
											<html:option value=""></html:option>
											<html:options collection="jxjflList" property="jxjfldm"
												labelProperty="jxjflmc" />
										</html:select>
										&nbsp;&nbsp;奖学金名称：
										<html:select property="xmdm" style="width:150px"
											styleId="jxjdm">
											<html:option value=""></html:option>
											<html:options collection="jxjList" property="jxjdm"
												labelProperty="jxjmc" />
										</html:select>
									</td>
								</tr>
							</logic:equal>
							<%--武汉理工大学end--%>
							
							<logic:notEqual value="10497" name="xxdm">
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="listPriseConfJxj('/xgxt/prise_conf_rs.do')">
										查询
									</button>
								</td>
							</logic:notEqual>
						</tr>
						<tr>
							<td align="left" nowrap>
							年级：
									<html:select property="nj" styleId="nj" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj"/>
									</html:select>
									&nbsp;&nbsp;
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:160px" styleId="xy"
									onchange="refreshForm('/xgxt/prise_conf_rs.do')">
									<html:option value="">    全部    </html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<span id="dispZy"> &nbsp;&nbsp;专业： <html:select
										property="zydm" style="width:160px;" styleId="zy"
										onchange="refreshForm('/xgxt/prise_conf_rs.do')">
										<html:option value="">    全部    </html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select> </span>
								<span id="dispBj"> &nbsp;&nbsp;班级： <html:select
										property="bjdm" style="width:140px" styleId="bj">
										<html:option value="">    全部    </html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select> </span>
									
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue"> <logic:equal value="12764" name="xxdm">提示：双击一行可以调整金额；单击表头可以排序</logic:equal><logic:notEqual value="12764" name="xxdm">提示：双击一行可以调整人数；单击表头可以排序</logic:notEqual> </font>
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
							<tr onclick="rowOnClick(this)" align="center" style="cursor:hand"
								ondblclick="if(curr_row.cells[4].innerText==''){alert('尚未进行批量设置生成建议人数，不能调整人数！');return false;}showTopWin('/xgxt/prise_conf_one.do',450,400);">

								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
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
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
<%--			<logic:notEqual name="commanForm" property="xydm" value="all"--%>
<%--				scope="request">--%>
<%--				<div id="jxjtmp" style="position:absolute;width:98%" align="center">--%>
<%--					<fieldset>--%>
<%--						<legend>--%>
<%--							<bean:write name="userDepName" />--%>
<%--							奖学金参评人数--%>
<%--						</legend>--%>
<%--						<table width="100%" class="tbstyle">--%>
<%--							<thead>--%>
<%--								<tr>--%>
<%--									<td>--%>
<%--										项 目--%>
<%--									</td>--%>
<%--									<td>--%>
<%--										学业一等--%>
<%--									</td>--%>
<%--									<td>--%>
<%--										学业二等--%>
<%--									</td>--%>
<%--									<td>--%>
<%--										学业三等--%>
<%--									</td>--%>
<%--									<td>--%>
<%--										社会工作--%>
<%--									</td>--%>
<%--									<td>--%>
<%--										社会实践--%>
<%--									</td>--%>
<%--									<td>--%>
<%--										文体活动--%>
<%--									</td>--%>
<%--									<td>--%>
<%--										奖金总额--%>
<%--									</td>--%>
<%--								</tr>--%>
<%--							</thead>--%>
<%--							<tr onclick="rowOnClick(this)" style="cursor:hand">--%>
<%--								<td>--%>
<%--									建 议--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs1" />--%>
<%--									人--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs2" />--%>
<%--									人--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs3" />--%>
<%--									人--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs4" />--%>
<%--									人--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs5" />--%>
<%--									人--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs6" />--%>
<%--									人--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs7" />--%>
<%--									元--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--							<tr onclick="rowOnClick(this)" style="cursor:hand"--%>
<%--								style="color:<bean:write name="bgColor" />">--%>
<%--								<td>--%>
<%--									调整后--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs11" />--%>
<%--									人--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs21" />--%>
<%--									人--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs31" />--%>
<%--									人--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs41" />--%>
<%--									人--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs51" />--%>
<%--									人--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs61" />--%>
<%--									人--%>
<%--								</td>--%>
<%--								<td>--%>
<%--									<bean:write name="rs71" />--%>
<%--									元--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--							<thead>--%>
<%--								<tr>--%>
<%--									<td colspan="5"--%>
<%--										style="height:18px;filter:alpha(opacity=0);cursor:hand;"--%>
<%--										align="right">--%>
<%--									</td>--%>
<%--									<td onclick="hidFlag=!hidFlag;setInterval('tm(125,85)',10);"--%>
<%--										colspan="3"--%>
<%--										style="height:18px;filter:alpha(opacity=50);cursor:hand;"--%>
<%--										align="right">--%>
<%--										<bean:write name="userDepName" />--%>
<%--										奖学金参评人数--%>
<%--									</td>--%>
<%--								</tr>--%>
<%--							</thead>--%>
<%--						</table>--%>
<%--					</fieldset>--%>
<%--				</div>--%>
<%--			</logic:notEqual>--%>
			<logic:notEmpty name="initOK" scope="request">
				<logic:equal value="ok" name="initOK">
					<script language="javascript">
    alert("数据初始化完成！");
    </script>
				</logic:equal>
				<logic:equal value="no" name="initOK">
					<script language="javascript">
    alert("数据初始化失败！");
    </script>
				</logic:equal>
			</logic:notEmpty>
			<logic:equal value="yes" name="writeAble" scope="request">
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<%--					<logic:equal name="userType" value="xy" scope="request">--%>
					<%--						<button type="button" class="button2"--%>
					<%--							onclick="showTopWin('xySetStuNum.do',450,405);">--%>
					<%--							上报参评人数--%>
					<%--						</button>--%>
					<%--					</logic:equal>--%>
					<logic:equal name="userType" value="xx" scope="request">
					    
    &nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2"
							onclick="showTopWin('viewTotStuNum.do',700,500)" style="width:100px">
							查看参评人数
						</button>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <button type="button" class="button2" onclick="chkPriseBat()" style="width:100px">
							批量设置
						</button>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <logic:notEqual value="北京林业大学" name="xxmc" scope="session">
							<%--武汉理工大学--%>
    						<logic:equal value="10497" name="xxdm">
    						<button type="button" class="button2"
								onclick="viewFpb()" style="width:100px">
								名额分配表
							</button>
    						</logic:equal>
    						<%--武汉理工大学end--%>
    						<%--<logic:notEqual value="10497" name="xxdm">
							<button type="button" class="button2"
								onclick="showTopWin('viewFpb.do',750,500)">
								名额分配表
							</button>
							</logic:notEqual>
    &nbsp;&nbsp;&nbsp;&nbsp;
    --%></logic:notEqual>
						<button type="button" class="button2" onclick="priseDataInit()" style="width:100px">
							初始化数据
						</button>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <logic:notEqual value="北京林业大学" name="xxmc" scope="session">
							<button type="button" class="button2"
								onclick="showTopWin('chg_prise_xn.do',300,200)" style="width:100px">
								调整学年
							</button>
						</logic:notEqual>
					</logic:equal>
					
					&nbsp;&nbsp;
						<button type="button" id="exp_datas" class="button2" style="width:100px" onclick="openWins()">
							按<bean:message key="lable.xsgzyxpzxy" />导出数据
						</button>
					
				</div>
			</logic:equal>
			<div id="tmpdiv"></div>
			<logic:present name="add">
			<logic:equal name="add" value = "yes">
				<script language="javascript">
					alert("增加条件成功");
				</script>
			</logic:equal>
			<logic:equal name="add" value = "no">
				<script language="javascript">
					alert("增加条件失败，请确认没有该类型条件后再增加");
				</script>
			</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript">
if(document.getElementById("jxjtmp") != null){
	document.getElementById("jxjtmp").style.top = -85;
}
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);

function openWins(){
	if (document.getElementById('jxjdm').value=='') {
		alert('请选择奖学金类别!');
		return;
	} else {
		url = 'jxjrsdataexp.do?jxjdm=';
		url += document.getElementById('jxjdm').value;
		url += '&xydm=';
		url += document.getElementById('xy').value;
		url += '&zydm=';
		url += document.getElementById('zy').value;
		url += '&bjdm=';
		url += document.getElementById('bj').value;
		url += '&bmlb=';
		url += document.getElementById('dispFalg').value;
		url += '&nj=';
		url += document.getElementById('nj').value;
		window.open(url);
	} 
}
</script>
	</body>
</html>
