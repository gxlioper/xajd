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
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript" src="/xgxt/js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/qgzxgzkh.js'></script>
	<script language="javascript">
		var Rows=new Array();	//所有选中的行对象
		var ShiftStartRow="";		//Shift多选时存储开始行对象
		var cur_bgc = "#ffdead";//选中行背景（字符串）
		
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

	//Ctrl多选
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

//选中行变色
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
		alert("请选择要批量删除的记录！");
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
		alert("请选择要批量修改为特别困难的记录！");
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
		alert("请选择要批量修改为困难的记录！");
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
		alert("请选择要批量修改为不困难的记录！");
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
		alert("请选择要修改的记录！");
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
			dd_html += "请选择您要提交审核结果的班级";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "</thead>";
			dd_html += "<tbody>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center'>";
			dd_html += "学年：";		
			dd_html += "</td><td>";
			dd_html += val('xn');
			dd_html += "</td></tr>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center'>";
			dd_html += "班级：";		
			dd_html += "</td><td>";
			dd_html += "<select id='tjbjdm' name='tjbjdm'/>";
			dd_html += "</td></tr>";
			dd_html += "<tr height='30' bgcolor='EEF4F9'>";
			dd_html += "<td align='center' colspan='2'>";
			dd_html += "<button class='button2' onclick='commitSh()'>确定</button>&nbsp;&nbsp;";//szGroup()
			dd_html += "<button class='button2' onclick='closeAdd()'>取消</button>";
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
			//判断选择的班级是否已经提交过
			var xn = val('xn');
			var bjdm = val('tjbjdm');
			if(bjdm == null || bjdm == "" || bjdm == undefined){
				alert('请选择您要提交审核结果的班级！');
				return false;
			}
			qgzxgzkh.checkExists('xszz_com_bmshtjb','zjz||dm||bm||tjxm||tjzt',xn+bjdm+'bjknsrd2'+'1',function(data){
				if(data != null && data == true){
					alert('该班级已经提交！');
					return false;
				}else{//提交
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
					当前所在位置：学生资助 - 申请结果查询 - 困难生认定
					</logic:equal>
					<logic:notEqual name="isQuery" value="is">
					当前所在位置：学生资助 - 审核 - 困难学生认定
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
					条件选择
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学年
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
								年级
								<html:select property="nj" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								学号
								<html:text property="xh" styleId="xh" style="width:80px;inputtext"
								styleClass="inputtext"></html:text>
								姓名
								<html:text property="xm" styleId="xm" style="width:50px;inputtext"
								styleClass="inputtext"></html:text>
								性别
								<html:select property="xb">
									<html:option value=""></html:option>
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
								</html:select>
								身份证号
								<html:text property="sfzh" styleId="sfzh" style="width:130px;inputtext" maxlength="18"
								styleClass="inputtext"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/n05_xszz.do?method=knsrd2sh&go=go')">
									查询
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
								专业
								<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								班级
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<!--辅导员提交信息-->
						<logic:present name="tjxx">
						<tr>
							<td colspan="2">
								<font color="red"><b><bean:write name="tjxx" property="bjmc"/>提交结果：<bean:write name="tjxx" property="tjzt"/></b></font>
							</td>
						</tr>
						</logic:present>
						<!--end辅导员提交信息-->
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
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								记录数：
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
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
								增 加
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="modi('/xgxt/n05_xszz.do?method=knsrd2sq&type=modi');"
								style="width:80px">
								修 改
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="del('/xgxt/n05_xszz.do?method=knsrd2sh&go=del');"
								style="width:80px">
								删 除
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								导入数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="dataExport2()" style="width:80px">
								导出数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<!-- 打印报表 -->
							<button class="button2" onclick="knsrd2Print('/xgxt/n05_xszz.do?method=knsrd2Print')" style="width:80px">
								汇总打印
							</button>
							</logic:equal>
							<logic:notEqual name="isQuery" value="is">
							<button class="button2" onclick="tbkn('/xgxt/n05_xszz.do?method=knsrd2sh&go=ybkn');"
								style="width:80px">
								一般困难
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="tbkn('/xgxt/n05_xszz.do?method=knsrd2sh&go=kn');"
								style="width:80px">
								困    难
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="kn('/xgxt/n05_xszz.do?method=knsrd2sh&go=tskn');"
								style="width:80px">
								特殊困难
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="bkn('/xgxt/n05_xszz.do?method=knsrd2sh&go=bkn');"
								style="width:80px">
								不 困 难
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<logic:present name="fdyshFlag">
							<button class="button2" onclick="tjjg()" style="width:80px">
								提交结果
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
