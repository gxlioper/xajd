<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
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
			checkBoxArr[i].checked = true;
		}
	} else {
		for(var i=0;i<checkBoxArr.length;i++){
			checkBoxArr[i].checked = false;
		}
	}
}

function del(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].cbVal.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("请选择要批量删除的记录！");
		return false;
	}
	if (!confirm("<bean:message key="lable.xsgzyxpzxy" />用户不能删除已通过学校审核的数据，确定要删除所选记录？")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function tg(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].cbVal.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("请选择要批量修改为通过的记录！");
		return false;
	}
	if($('userType').value == "xy"){
		if (!confirm("<bean:message key="lable.xsgzyxpzxy" />用户不能修改已通过学校审核的数据，确定要修改所选记录？")){
			return false;
		}
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function btg(url){
	var RowsStr="!!splitOne!!";
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].cbVal.value = RowsStr;
	
	if (RowsStr=="!!splitOne!!"){
		alert("请选择要批量修改为不通过的记录！");
		return false;
	}
	if($('userType').value == "xy"){
		if (!confirm("<bean:message key="lable.xsgzyxpzxy" />用户不能修改已通过学校审核的数据，确定要修改所选记录？")){
			return false;
		}
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
		showTopWin(url, 788,590);
		return true;
	}
}

function add(url){
	return showTopWin(url,788,590);
}

function modi(url){
	if((curr_row == null) || (curr_row == "")){
		alert("请选择要修改的记录！");
		return false;
	}
	url += "&pkVal=";
	url += curr_row.getElementsByTagName("input")[0].value;
	return showTopWin(url,788,590);
}

function dataExport2() {
	document.forms[0].action = "/xgxt/zgmsxy_xszz.do?method=bysxxExp";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
function ndDisabled() {
	var isQuery = document.getElementById('isQuery').value;
	if (isQuery != "is"){
		document.getElementById("xn").disabled = true;
	}
}
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<html:form action="/zgmsxy_xszz" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>
					<logic:equal name="isQuery" value="is">
					${title }
					</logic:equal>
					<logic:notEqual name="isQuery" value="is">
					助学贷款 - 审核 - 毕业生信息
					</logic:notEqual>
					</a>
				</p>
			</div>
			<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<ul>
						<logic:equal name="isQuery" value="is">
							<li><a href="#" class="btn_zj" onclick="add('/xgxt/zgmsxy_xszz.do?method=bysxxsq');">增加</a></li>
							<li><a href="#" class="btn_xg" onclick="modi('/xgxt/zgmsxy_xszz.do?method=bysxxsq&doType=modi');">修改</a></li>
							<li><a href="#" class="btn_sc" onclick="del('/xgxt/zgmsxy_xszz.do?method=bysxxsh&go=del');">删除</a></li>
							<li><a href="#" class="btn_dr" onclick="impAndChkData()">导入</a></li>
							<li><a href="#" class="btn_dc" onclick="dataExport()">导出</a></li>			
						</logic:equal>
						<logic:notEqual name="isQuery" value="is">
							<li><a href="#" class="btn_shtg" onclick="tg('/xgxt/zgmsxy_xszz.do?method=bysxxsh&go=tg');">通过</a></li>
							<li><a href="#" class="btn_shbtg" onclick="tg('/xgxt/zgmsxy_xszz.do?method=bysxxsh&go=btg');">不通过</a></li>
						</logic:notEqual>
												
						</ul>
					</div>
					</div>
				</logic:equal>
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
			<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
						<tr>
							<th>学号</th>
							<td><html:text property="xh" styleId="xh" style="width:150px;inputtext"
								styleClass="inputtext"></html:text></td>
							<th>姓名</th>
							<td><html:text property="xm" styleId="xm" style="width:150px;inputtext"
								styleClass="inputtext"></html:text></td>
							<th>年级</th>
							<td><html:select property="nj" onchange="initBjList()" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select></td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" onchange="initZyList();initBjList()" style="width:150px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select></td>
							<th>专业</th>
							<td><html:select property="zydm" onchange="initBjList()" style="width:150px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select></td>
							<th>班级</th>
							<td colspan="2"><html:select property="bjdm" style="width:150px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" />审核</th>
							<td><html:select property="xysh" style="width:150px">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select></td>
							<th>学校审核</th>
							<td><html:select property="xxsh" style="width:150px">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="通过">通过</html:option>
									<html:option value="不通过">不通过</html:option>
								</html:select>
							</td>
							<th></th><td></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="btn">
							<input type="hidden" name="go" value="a" />
							<button type="button" id="search_go" onclick="refreshForm('/xgxt/zgmsxy_xszz.do?method=bysxxsh&go=go')">
							查 询
							</button>
							 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
								重 置
							 </button>
							</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</div>
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	查询结果&nbsp;&nbsp;
							<font color="red">未找到任何记录！</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">双击一行可以查看详细信息；单击表头可以排序</font> 
						</span>
					</h3>
						<div class="con_overlfow">
							<table width="99%" id="rsTable" class="dateline tablenowrap">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="2" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)">
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<logic:equal name="isQuery" value="is">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/zgmsxy_xszz.do?method=bysxxsq&doType=modi')"
										style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     ">
										<td align=center>
											<input type="checkbox" id="pk" name="pk" 
											value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:iterate id="v" name="s" offset="1" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="2">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
									</logic:equal>
									<logic:notEqual name="isQuery" value="is">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/zgmsxy_xszz.do?method=bysshOne&doType=modi')"
										style="cursor:hand;background-color:
    <logic:iterate id="v" name="s" offset="0" length="1">
    <bean:write name="v"/>
    </logic:iterate>
     ">
										<td align=center><input type="checkbox" id="pk" name="pk" 
										value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" />
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="2">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
									</logic:notEqual>
								</logic:iterate>
							</table>
							</div>
					 <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xszzZgmsxyActionForm"></jsp:include>
					</logic:notEmpty>
					
					<div id="tmpdiv"></div>
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
		 
		  <logic:present name="message">
		 	<input type="hidden" id="msg" value="${message }"/>
		 	<script type="text/javascript">
		 		alert($('msg').value);
		 	</script>
		 </logic:present>
	</body>
</html>
