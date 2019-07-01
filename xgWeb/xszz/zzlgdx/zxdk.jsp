<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
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

function chkAssisOne(url) {
	if (curr_row == null) {
		return false;
	} else {
		url += "&guid=";
		url += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(url, 650,380);
		return true;
	}
}

function add(url){
	return showTopWin(url,650,380);
}

function modi(url){
	if((curr_row == null) || (curr_row == "")){
		alert("请选择要修改的记录！");
		return false;
	}
	url += "&guid=";
	url += curr_row.getElementsByTagName("input")[0].value;
	return showTopWin(url,650,380);
}

function dataExport2() {
	document.forms[0].action = "/xgxt/zzlgdx_xszz.do?method=zxdkExp";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
		</script>
	</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/zzlgdx_xszz.do?method=zxdk" method="post">
			<div class="tab_cur">
				<p class="location">
				<em>您的当前位置：</em><a>
					<logic:equal name="isQuery" value="is">
					学生资助 - 信息维护 - 助学贷款
					</logic:equal>
					<logic:notEqual name="isQuery" value="is">
					学生资助 - 信息维护 - 助学贷款
					</logic:notEqual>
					</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<div class="toolbox">
			<logic:equal value="yes" name="writeAble" scope="request">
					 <div class="buttonbox">
						<ul>
						<li> <a href="#"onclick="add('/xgxt/zzlgdx_xszz.do?method=zxdkEdit');" class="btn_zj"> 增加 </a> </li>
					    <li> <a href="#" onclick="modi('/xgxt/zzlgdx_xszz.do?method=zxdkEdit&act=modi');" class="btn_xg"> 修改 </a> </li>
						<li> <a href="#" onclick="del('/xgxt/zzlgdx_xszz.do?method=zxdk&go=del');" class="btn_sc"> 删除 </a> </li>
						<li> <a href="#" onclick="impAndChkData();" class="btn_dr"> 导入 </a> </li>
						<li> <a href="#" onclick="dataExport2()"  class="btn_dc"> 导出 </a> </li>
					    </ul>
					 </div>
			</logic:equal>
			<div class="searchtab">
			<table width="100%" border="0">
				<tfoot>
				 	<tr>
				 		<td colspan="6" >
							<input type="hidden" name="go" value="a" />
							<div class="btn">
								<input type="hidden" name="go" value="a" />
								<div class="bz">
									<!--辅导员提交信息-->
									<logic:present name="tjxx">
									<tr>
										<td colspan="2">
											<font color="red"><b><bean:write name="tjxx" property="bjmc"/>提交结果：<bean:write name="tjxx" property="tjzt"/></b></font>
										</td>
									</tr>
									</logic:present>
									<!--end辅导员提交信息-->
								</div>
								<button type="button" class="btn_cx"   id="search_go"
									onclick="allNotEmpThenGo('/xgxt/zzlgdx_xszz.do?method=zxdk&go=go')">
									查 询
								</button>
		                         &nbsp;&nbsp;&nbsp;&nbsp;
		                         <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              	           	 重 置
		                         </button>
		                    </div>
						</td>
					</tr>
				</tfoot>
				<tbody>
						<tr>
							<th>
								年级
							</th>
							<td>	
								<html:select property="nj" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>	
								学号
							</th>
							<td>	
								<html:text property="xh" styleId="xh" style="width:80px;inputtext"
								styleClass="inputtext"></html:text>
							</td>
							<th>	
								姓名
							</th>
							<td>	
								<html:text property="xm" styleId="xm" style="width:50px;inputtext"
								styleClass="inputtext"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>	
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>	
								专业
							</th>
							<td>	
								<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>	
								班级
							</th>
							<td>	
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>	
								身份证号
							</th>
							<td>	
								<html:text property="sfzh" styleId="sfzh" style="width:130px;inputtext" maxlength="18"
								styleClass="inputtext"></html:text>
							</td>
							<th>	
								贷款合同号
							</th>
							<td>	
								<html:text property="dkhth" styleId="dkhth" style="width:130px;inputtext" 
									styleClass="inputtext"></html:text>
							</td>
							<th></th>
							<td></td>
						</tr>
					</thead>
				</table>
			</div>
			</div>
			<div class="formbox" id="result">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
						<font color="blue"> 
							提示：双击一行可以查看详细信息；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
				 <logic:notEmpty name="rs">
						<div class="con_overlfow">
							<table summary="" id="rsTable" class="dateline" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1" scope="request">
											<td id="${tit.en}"
												onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/zzlgdx_xszz.do?method=zxdkEdit&act=modi')"
										style="cursor:hand;">
										<td align=center>
											<input type="checkbox" id="pk" name="pk" 
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="hidden" value="<bean:write name="v"/>" />
											</logic:iterate>
									    </td>
										<logic:iterate id="v" name="s" offset="1">
											<td align=center>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
								</tbody>
							</table>
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xszzZzlgdxActionForm"></jsp:include>
						</div>
					</logic:notEmpty>
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
