<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
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

function plsz(){
	if($('xm').value=='' && $('xy').value==''){
		alert("请按<bean:message key="lable.xsgzyxpzxy" />或奖学金批量设置！");
		return false;
	}
	var RowsStr="!!splitOne!!";
	for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	if (RowsStr=="!!splitOne!!"){
		alert("请选择要批量设置的记录！");
		return false;
	}
	if (!confirm("确定要批量设置所选记录？")){
		return false;
	}
	var url = "/xgxt/nblg_xszz.do?method=zzsjPlsz";
	url += "&pkDel=" + RowsStr;
	showTopWin(url, 420,300);
	return true;
}

function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
      }
}

function chkAssisOne(url) {
	if (curr_row == null) {
		return false;
	} else {
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(url, 420,300);
		return true;
	}
}

function dgsz(){
	var url = "/xgxt/nblg_xszz.do?method=zzsjEdit";
	if((curr_row == null) || (curr_row == "")){
		alert("请选择要设置的记录！");
		return false;
	} else {
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
	}
	return showTopWin(url,420,300);
}

function csh(){
	var url = "/xgxt/nblg_xszz.do?method=zzsjcsh";
	if (!confirm("初始化不能保留已设置时间数据，是否继续？")){
			return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function dataExport2() {
	document.forms[0].action = "/xgxt/expData2.do";
	document.forms[0].target = "_blank";
	document.forms[0].submit();
	document.forms[0].target = "_self";
}
		</script>
		</head>
		<body onload="xyDisabled('xy')">
		<html:form action="/nblg_xszz.do?method=data_zzsj" method="post">
				<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>
					<bean:write name="tips" scope="request" /></a>
					</p>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="pkDel" name="pkDel" value="" />
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="csh();" class="btn_csh"> 初始化  </a>
						</li>
						<li>
							<a href="#"  onclick="dgsz();" class="btn_xg"> 单个设置 </a>
						</li>
						<li>
							<a href="#"  onclick="plsz();" class="btn_sc"> 批量设置 </a>
						</li>
					</ul>
				</div>
				</logic:equal>
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a"/>
										<button type="button" class="button2" id="search_go"
											onclick="refreshForm('nblg_xszz.do?method=data_zzsj&go=go');">
											查询
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
									项目名称
								</th>
								<td>
									<html:select name="rs1" property="xmmc" style="width:180px" styleId="xm">
										<html:option value=""></html:option>
										<html:options collection="xmList" property="xmmc"
											labelProperty="xmmc" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select name="rs1" property="xydm" style="width:180px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
								</th>
								<td>
								</td>
							</tr>
						</tbody>
					</table>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty>  
						<logic:notEmpty name="rs">
							<font color="blue">	
								提示：双击一行可以维护具体项目时间信息；单击表头可以排序</font>	
						</logic:notEmpty>
					</span>
				</h3>
				<logic:notEmpty name="rs">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')">
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)"
									ondblclick="chkAssisOne('/xgxt/nblg_xszz.do?method=zzsjEdit')">
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pk"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="center">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
						</logic:iterate>
						</tbody>
					</table>
			</logic:notEmpty>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
