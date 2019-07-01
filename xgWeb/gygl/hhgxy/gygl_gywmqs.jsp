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
		<meta name="Copyright" content="正方软件 zfsoft" />
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
	var cur_bgc = "#ffdead";//选中行背景（字符串）
	var curr_row = null;
	var sort_col = null;
	var splitSignOne = "!!SplitSignOne!!";
	var temppk = "";
	//多选功能
	function rowMore(objTr,tag) {
		curr_row = objTr;
		var pk = curr_row.cells[0].innerText+curr_row.cells[1].innerText+curr_row.cells[2].innerText+curr_row.cells[3].innerText;
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
				temppk = temppk.replace(pk,"");
				pk="";
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
		changeC(iRow);
		temppk = temppk + pk + splitSignOne;
	}
	//选中行变色
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
		allNotEmpThenGo(url);
	}

	function wmqsOne(url){
		if(curr_row == null){
			alert('请选择要修改状态的寝室!');
			return false;
		}
		var pk = curr_row.cells[0].innerText+splitSignOne+curr_row.cells[1].innerText+splitSignOne+curr_row.cells[2].innerText+splitSignOne+curr_row.cells[3].innerText;
		showTopWin(url+pk,600,480);
	}
	
	function wmqsAll(url){
		if(curr_row == null){
			alert('请选择要设置为文明寝室的寝室!');
			return false;
		}
		refreshForm(url+temppk+"&type=allsave")
	}
	
	function wmqsDel(url){
		if(curr_row == null){
			alert('请选择要取消文明寝室的寝室!');
			return false;
		}
		refreshForm(url+temppk+"&type=alldel")
	}
	
	function wmqsPrint(url){
		var lddm=document.getElementById("lddm").value;
		var qsh=document.getElementById("qsh").value;
		var xn=document.getElementById("xn").value;
		var xq=document.getElementById("xq").value;
		var ssbh = "";
		var pk="";
		if (lddm != "") {
			if (qsh != "qsh") {
				ssbh = lddm + "-" + qsh;
			}
		}
		if( ssbh!="" ){
			pk += " and a.ssbh = '" + ssbh + "'";
		}
		if( xn!="" ){
			pk += " and a.xn = '" + xn + "'";
		}
		if( xq!="" ){
			pk += " and a.xq = '" + xq + "'";
		};
		window.open(url+pk);
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
			<input type="hidden" name="lcV" id="lcV" value=""/>
			<input type="hidden" name="qshV" id="qshV" />	
			<input type="hidden" id="msg" name="msg" value="${msg}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name = "title" />
				</div>
			</div>
			<fieldset>
				<legend>
					查 询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td>
								&nbsp;&nbsp;学年：											
								<html:select name="rs1" property="xn" style="width:90px" styleId="xn"
										onchange="">
									<html:options collection="xnList" property="xn"
												labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select name="rs1" property="xq" style="width:60px" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="searchCheck('/xgxt/XsgyglHhDispatch.do?method=gywmqs')">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td>
								&nbsp;&nbsp;楼栋名:
								<html:select name="rs1" property="lddm"  styleId="lddm"
									onchange="getLcList()">
									<html:option value="">--请选择--</html:option>
									<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
								</html:select>
								&nbsp;&nbsp;楼层:
								<html:select name="rs1" property="lc"  styleId="lc"
									onchange="getQshList2()">
									<html:options collection="lcList" property="dm"
											labelProperty="mc" />
								</html:select>
								&nbsp;&nbsp;寝室号:
								<html:select name="rs1" property="qsh"  styleId="qsh">
									<html:options collection="qshList" property="dm"
									labelProperty="mc" />
								</html:select>
								&nbsp;&nbsp;文明寝室：
								<html:select property="sfwmqs" style="width:50px">
									<html:option value=""></html:option>
									<html:option value="1">是</html:option>
									<html:option value="0">否</html:option>
								</html:select>
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
							<font color="blue">提示：双击一行可以选定；单击表头可以排序；按住Ctrl可以多选</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="top" name="topTr">
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
									ondblclick="fzdxOne('/xgxt/dtjs_zjcm.do?method=fzdxOne&db=db&pk=');">	
										<logic:iterate id="v" name="s">
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
			<logic:equal value="yes" name="writeAble">
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button class="button2" style="width:80px"
					onclick="wmqsAll('/xgxt/XsgyglHhDispatch.do?method=gywmqspx&pkall=');">
						批量设置
					</button>
					&nbsp;
					<button class="button2" style="width:80px"
					onclick="wmqsDel('/xgxt/XsgyglHhDispatch.do?method=gywmqspx&pkall=');">
						批量取消
					</button>
					&nbsp;
					<button class="button2" style="width:80px"
					onclick="wmqsOne('/xgxt/XsgyglHhDispatch.do?method=gywmqspx&pk=');">
						评 选
					</button>
					&nbsp;
					<button class="button2" onclick="impAndChkData()"
						style="width:80px">
						导入数据
					</button>
					&nbsp;
					<button class="button2" onclick="dataExport()" style="width:80px">
						导出数据
					</button>
					&nbsp;
					<button class="button2" onclick="wmqsPrint('/xgxt/XsgyglHhDispatch.do?method=gywmqsPrint&pk=');"
						style="width:80px">
						打印
					</button>
				</div>
			</logic:equal>
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
<logic:present name="msg">
	<script>
		alert(''+document.getElementById('msg').value);
	</script>
</logic:present>