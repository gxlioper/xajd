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
	<script type='text/javascript' src='/xgxt/dwr/interface/xszzCommonN05DWR.js'></script>
	<script language="javascript">
		var Rows=new Array();	//所有选中的行对象
		var ShiftStartRow="";		//Shift多选时存储开始行对象
		var cur_bgc = "#ffdead";//选中行背景（字符串）
		
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
	
	function tg(url){
		var RowsStr= false;
		
		for (i=0; i<document.getElementsByName("pk").length; i++){
	    	if(document.getElementsByName("pk")[i].checked){
	    		RowsStr = true;
	    	}
		}
		if (!RowsStr){
			alert("请选择要批量修改为通过的记录！");
			return false;
		}
		document.forms[0].action=url;
	    document.forms[0].submit();
	}
	
	function btg(url){
		var RowsStr= false;
		
		for (i=0; i<document.getElementsByName("pk").length; i++){
	    	if(document.getElementsByName("pk")[i].checked){
	    		RowsStr = true;
	    	}
		}
		if (!RowsStr){
			alert("请选择要批量操作的记录！");
			return false;
		}
		document.forms[0].action=url;
	    document.forms[0].submit();
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
		document.forms[0].action = "/xgxt/n05_xszz.do?method=wszxj1Exp";
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}
	
	function chkAssisOne(url, act) {
		if (curr_row == null) {
			return false;
		} else {
			url += "&xmdm=";
			url += val('xmdm');
			url += "&pkVal=";
			url += curr_row.getElementsByTagName("input")[0].value;	
			url += "&operFlag=";
			url += 	curr_row.getElementsByTagName("input")[0].disabled;		
			url += "&shjg=";
			url += curr_row.cells[7].innerText;
			showTopWin(url, 750, 550);
			return true;
		}
	}	
	
	function tjjg(){
		url = "n05_xszz.do?method=wszxj1shDeptModel&go=tjjg";
		//判断是否已经提交
		var pk = "bm||dm||zjz||tjxm||tjzt";
		var pkV = 'xy'+val('userDep') + val('xn') + val('xmdm') +"wszxj1" + "1";
		qgzxgzkh.checkExists('xszz_com_bmshtjb',pk,pkV,function(data){
			if(data){
				alert('已经提交，暂时不能再次提交！');
				return false;
			}else{
				pk = "xn||zxjdm||xydm";
				pkV = val('xn')+val('xmdm')+val('userDep');				
				xszzCommonN05DWR.jlsfqbsh('wszxj1','xn||zxjdm',pk,pkV,val('shjb'),function(data){
					if(data){
						alert('还有数据未审核,暂时不能提交结果！');
						return false;
					}else{
						if(confirm('您确定要提交审核结果吗，提交审核结果后将不能再进行审核操作！')){
							refreshForm(url);
						}
					}
				});				
			}
		});			
	}
	</script>
	<body onload="xyDisabled('xy');">
		<html:form action="/n05_xszz.do?method=wszxj1shdm" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 审核 - <bean:write name="xmmc"/>
				</div>
			</div>
			<input type="hidden" id="shjb" name="shjb" 
				value="${shjb}" />
			<input type="hidden" id="userDep" name="userDep" 
				value="<bean:write name="userDep" scope="session"/>" />
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
			<input type="hidden" id="xmdm" name="xmdm"
				value="<bean:write name="xmdm" />">
			<fieldset>
				<legend>
					条件选择
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								学年
								<html:select property="xn" 
									styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;年级
								<html:select property="nj" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<!--<bean:message key="lable.xsgzyxpzxy" />用户-->
								<logic:equal value="xy" name="userType" scope="session">
								&nbsp;&nbsp;专业
								<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								</logic:equal>
							</td>
							<td width="10" rowspan="" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/n05_xszz.do?method=wszxj1shDeptModel&go=go')">
									查询
								</button>
							</td>
						</tr>
						<!--<bean:message key="lable.xsgzyxpzxy" />提交信息-->
						<logic:equal value="xy" name="userType" scope="session">
						<tr>
							<td colspan="2">
								<font color="red"><b>提交结果：<bean:write name="tjjg"/></b></font>
							</td>
						</tr>
						</logic:equal>
						<!--end<bean:message key="lable.xsgzyxpzxy" />提交信息-->						
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
										<td>
											操作
										<td>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/n05_xszz.do?method=wszxj1shdmOne')"
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
										<td>
											<a href="#" onclick="rowOnClick(findParents(this,'TR'));chkAssisOne('/xgxt/n05_xszz.do?method=wszxj1shdmOne&doType=view')"><font color="blue">查看详细</font></a>
										</td>
									</tr>
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
									<TD height="3"></TD>
								</TR>
							</TABLE>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<button class="button2" onclick="tg('/xgxt/n05_xszz.do?method=wszxj1sh&go=tg');"
								style="width:80px">
								通 过
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="btg('/xgxt/n05_xszz.do?method=wszxj1sh&go=btg');"
								style="width:80px">
								不通过
							</button>

							<!--学校用户-->
							<logic:notEqual value="xy" name="userType" scope="session">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="btg('/xgxt/n05_xszz.do?method=wszxj1sh&go=th');"
								style="width:80px">
								退回重审
							</button>
							</logic:notEqual>
							<!--end学校用户-->

							<!--<bean:message key="lable.xsgzyxpzxy" />用户-->
							<logic:equal value="xy" name="userType" scope="session">
							<!--三级审核-->
							<logic:equal value="3" name="shjb">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button class="button2" onclick="btg('/xgxt/n05_xszz.do?method=wszxj1sh&go=th');"
									style="width:80px">
									退回重审
								</button>
							</logic:equal>
							<!--end三级审核-->
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="tjjg()" style="width:80px">
								提交结果
							</button>
							</logic:equal>
							<!--end<bean:message key="lable.xsgzyxpzxy" />用户-->

							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="dataExport2()" style="width:80px">
								导出数据
							</button>
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
