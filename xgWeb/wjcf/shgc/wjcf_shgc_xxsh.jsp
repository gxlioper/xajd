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
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script language="javascript" src="/xgxt/wjcf/shgc/shgcjs/shgcjs.js"></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<html:form action="/shgcwjcfwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					当前位置：违纪处分 - 审核 - 学校审核
				</div>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />
			<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
			<logic:present name="result">
				<input type="hidden" id="result" name="result" value="<bean:write name="result"/>" />
			</logic:present>
			<fieldset>
				<legend>
					查询条件
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
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年度：
								<html:select property="nd" style="width:90px" disabled="true"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;年级：
								<html:select property="nj" onchange="initZyList();initBjList()" style="width:90px;padding-left:80px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;处分类别：
								<html:select property="cflb" style="width:90px"
								styleId="cflb">
								<html:option value=""></html:option>
								<html:options collection="cflbList" property="cflbdm"
									labelProperty="cflbmc" />
							</html:select>
							</td>
							<td width="10" rowspan="3" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('shgcxxshqry.do')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:230px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								专业：
								<html:select property="zydm" onchange="initBjList()" style="width:180px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								班级：
								<html:select property="bjdm" style="width:180px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
							&nbsp;
								学号:
								<html:text property="xh" styleId="xh"></html:text>
								&nbsp;
								姓名:
								<html:text property="xm" styleId="xm"></html:text>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
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
						<font color="blue">提示：双击一行可以查看详细信息，并可以改变审核状态；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
							<td><input type="checkbox" id="cb" name="cb" onclick="selectAll()"/> </td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)"
								style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="1" length="1">
    <bean:write name="v"/>
    </logic:iterate>
    "
								ondblclick="chkPriseOne_shgc('/xgxt/shgcxxsh.do?pkVal=',650,530)">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
										<input type="checkbox" id="cbv" name="cbv" value="<bean:write name="v"/>"/>
									</logic:iterate>
								</td>
								<td>	
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<button type="button" class="button2" style="width:100px" onclick="shres('wjcf_shgc_wjshres.do?res=tg')">
								审核通过
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" style="width:100px" onclick="shres('wjcf_shgc_wjshres.do?res=btg')">
								审核不通过
							</button>
							&nbsp;&nbsp;&nbsp;
							 <button type="button" class="button2"
								onclick="chkPriseOne6('delshxx.do')"
								style="width:100px">
								删除
							</button>
							&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="wjcfysbprint('wjcfysbprint.do?pkVal=')" style="width:90px">
								打印/预览
							</button>
						</div>
					</center>
				</logic:equal>
				<div id="tmpdiv"></div>
			<div id="tmpdiv"></div>
		</html:form>
		<script language="javascript">
		function wjcfysbprint(url){
			if (curr_row == null){
				window.open(url);
			}else {
				var pkVal = curr_row.cells[0].getElementsByTagName("input")[0].value;
				window.open(url+pkVal);
			}
			}
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		function shres(url) {
			var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (confirm('确定要审核所选择的数据吗？')){
			document.forms[0].action = url;
			document.forms[0].submit();
		}
	}else{
		alert("没有选择相应记录，请选择之后再确定！！");
	}
		}
		</script>
			<logic:equal value="view" name="result">
			<script>
				alert("操作成功");
				window.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:present name="result">
		<logic:notEqual value="view" name="result">
			<script>
				alert("操作失败" + document.getElementById("result").value);
				window.document.getElementById('search_go').click();
			</script>
		</logic:notEqual>
		</logic:present>
	</body>
</html>
