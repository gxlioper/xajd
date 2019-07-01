<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
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
	if(confirm('您确定删除选中的记录吗？')){
		document.forms[0].action=url;
    	document.forms[0].submit();
	}
	
}

function ybkn(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("请选择要批量修改为一般困难的记录！");
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

function tskn(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("请选择要批量修改为特殊困难的记录！");
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

function pd(url){
	var RowsStr= false;
	
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("请选择要批量设定为困难生的记录！");
		return false;
	}
	
	alert("如果所选择的学生已在本学年存在困难记录，则保留当前记录。");
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
	document.forms[0].action = "/xgxt/n05_xszz.do?method=knsrd3Exp";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
		</script>
		</head>
	<body onload="xyDisabled('xy');">
		<html:form action="/n05_xszz.do?method=knsrd3sh" method="post">
			<div class="tab_cur">
				<p class="location">
				<em>您的当前位置：</em><a>
					<logic:equal name="isQuery" value="is">
					学生资助 - 申请结果查询 - 经济困难生认定
					</logic:equal>
					<logic:notEqual name="isQuery" value="is">
					学生资助 - 审核 - 经济困难生认定
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
			<input type="hidden" id="isQuery" name="isQuery"
				value="<bean:write name="isQuery" scope="request"/>" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<div class="toolbox">
			 <logic:equal value="yes" name="writeAble" scope="request">	
					 <div class="buttonbox">
					 <ul>
					 	<logic:equal name="isQuery" value="is">
						<li> <a href="#" onclick="add('/xgxt/n05_xszz.do?method=knsrd3sq');" class="btn_zj"> 增加 </a> </li>
					    <li> <a href="#" onclick="modi('/xgxt/n05_xszz.do?method=knsrd3sq&type=modi');" class="btn_xg"> 修改 </a> </li>
						<li> <a href="#" onclick="del('/xgxt/n05_xszz.do?method=knsrd3sh&go=del');" class="btn_sc"> 删除 </a> </li>
						<li> <a href="#" onclick="impAndChkData();" class="btn_dr"> 导入 </a> </li>
						<li> <a href="#"  onclick="dataExport2()"  class="btn_dc"> 导出 </a> </li>
						<logic:equal name="xxdm" value="11078">
							<logic:equal name="userType" value="xx">
								<li> <a href="#" onclick="pd('/xgxt/n05_xszz.do?method=knsrd3sh&go=pd');"  class="btn_plsh"> 批量评定 </a> </li>
							</logic:equal>
						</logic:equal>
						</logic:equal>
						<logic:notEqual name="isQuery" value="is">
							<li> <a href="#"  onclick="ybkn('/xgxt/n05_xszz.do?method=knsrd3sh&go=ybkn');" class="btn_zj"> 一般困难 </a> </li>
							<li> <a href="#"  onclick="kn('/xgxt/n05_xszz.do?method=knsrd3sh&go=kn');" class="btn_zj"> 困 难 </a> </li>
							<li> <a href="#" onclick="tskn('/xgxt/n05_xszz.do?method=knsrd3sh&go=tskn');" class="btn_zj"> 特殊困难 </a> </li>
							<li> <a href="#"  onclick="bkn('/xgxt/n05_xszz.do?method=knsrd3sh&go=bkn');" class="btn_zj"> 不困难 </a> </li>
							</logic:notEqual>						
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
								<button class="btn_cx"   id="search_go"
									onclick="allNotEmpThenGo('/xgxt/n05_xszz.do?method=knsrd3sh&go=go')">
									查询
								</button>
		                         &nbsp;&nbsp;&nbsp;&nbsp;
		                         <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
		              	           	 重 置
		                         </button>
		                    </div>
						</td>
					</tr>
				</tfoot>
				<tbody>
						<tr>
							<th>
								学年
							</th>
							<td>
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
							</td>
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
						</tr>
						<tr>
							<th>
								姓名
							</th>
							<td>	
								<html:text property="xm" styleId="xm" style="width:50px;inputtext"
								styleClass="inputtext"></html:text>
							</td>
							<th>	
								性别
							</th>
							<td>
								<html:select property="xb">
									<html:option value=""></html:option>
									<html:option value="男">男</html:option>
									<html:option value="女">女</html:option>
								</html:select>
							</td>
							<th>	
								身份证号
							</th>
							<td>	
								<html:text property="sfzh" styleId="sfzh" style="width:130px;inputtext" maxlength="18"
								styleClass="inputtext"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:150px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>	
								专业
							</th>
							<td>	
								<html:select property="zydm" onchange="initBjList()" style="width:150px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>	
								班级
							</th>
							<td>	
								<html:select property="bjdm" style="width:150px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								民主评议结果
							</th>
							<td>	
								<html:select property="fdysh" style="width:150px">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="一般困难">一般困难</html:option>
									<html:option value="困难">困难</html:option>
									<html:option value="特殊困难">特殊困难</html:option>
									<html:option value="不困难">不困难</html:option>
									<html:option value="非困难生3">非困难生(包括不困难和未审核)</html:option>
									<html:option value="困难生3">困难生(包括三个困难级别)</html:option>
								</html:select>
							</td>
							<th>	
								民主认定结果
							</th>
							<td>	
								<html:select property="xysh" style="width:150px">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="一般困难">一般困难</html:option>
									<html:option value="困难">困难</html:option>
									<html:option value="特殊困难">特殊困难</html:option>
									<html:option value="不困难">不困难</html:option>
									<html:option value="非困难生3">非困难生(包括不困难和未审核)</html:option>
									<html:option value="困难生3">困难生(包括三个困难级别)</html:option>
								</html:select>
							</td>
							<th>	
								学校审核
							</th>
							<td>	
								<html:select property="xxsh" style="width:150px">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="一般困难">一般困难</html:option>
									<html:option value="困难">困难</html:option>
									<html:option value="特殊困难">特殊困难</html:option>
									<html:option value="不困难">不困难</html:option>
									<html:option value="非困难生3">非困难生(包括不困难和未审核)</html:option>
									<html:option value="困难生3">困难生(包括三个困难级别)</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
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
							提示：提示：双击一行可以查看详细信息；单击表头可以排序</font>
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
									<logic:iterate id="tit" name="topTr" offset="3" scope="request">
										<td id="${tit.en}"
											onclick="tableSort(this)" nowrap>
											${tit.cn}
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
								<logic:equal name="isQuery" value="is">
								<tr onclick="rowOnClick(this)"
									ondblclick="chkAssisOne('/xgxt/n05_xszz.do?method=knsrd3sq&type=modi')"
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
									ondblclick="chkAssisOne('/xgxt/n05_xszz.do?method=knsrd3shOne')"
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
							</tbody>
						</table>
						<!--分页显示-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xszzCommonN05ActionForm"></jsp:include>
					  <!--分页显示-->
					  </div>
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
	</body>
</html>
