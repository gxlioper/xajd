<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="xyDisabled('xy')">
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

function plsz(){
	var RowsStr= false;
	var n = 0;
	var url = "/xgxt/zgdzdx_xszz.do?method=zxdknxPlsz&pkDel=";
	for (i=0; i<document.getElementsByName("pks").length; i++){
    	if(document.getElementsByName("pks")[i].checked){
    		n++;
    		url += document.getElementsByName("pks")[i].value+"!@";
    		RowsStr = true;
    	}
	}
	if (!RowsStr){
		alert("请选择要批量设置的记录！");
		return false;
	}

	if (n > 180){
		alert("最多只能批量修改180条记录，目前选择记录为:"+n+"条!");
		return false;
	}
	url = url.substr(0,url.length-2);
	return showTopWin(url, 420,300);
}

function chec(){
      for(i=0;i<document.getElementsByName("pks").length;i++){
      	document.getElementsByName("pks")[i].checked=document.getElementsByName("qbxz")[0].checked;
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
	var url = "/xgxt/zgdzdx_xszz.do?method=zxdknxEdit";
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
	var url = "/xgxt/zgdzdx_xszz.do?method=zxdknxcsh";
	var checkBoxArr = document.getElementsByName("pks");
	
	var flag = false;
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
		}
	}
	if (flag){
		if (!confirm('确定要初始化所选择的数据吗？')){
			return false;
		}
	}else{
		if (!confirm("初始化不能保留已设置时间数据，您确定要全部初始化吗？")){
			return false;
		}
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
		<html:form action="/zgdzdx_xszz.do?method=data_zxdknx" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${tips }</a>
				</p>
			</div>
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="pkDel" name="pkDel" value="" />
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<div class="toolbox">
			 <!-- 按钮 -->
			 <logic:equal name="writeAble" value="yes">
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="csh(); " class="btn_cs"> 初始化 </a> </li>
			    <li> <a href="#" onclick="dgsz();" class="btn_csh"> 单个设置 </a> </li>
				<li> <a href="#" onclick="plsz();" class="btn_sz"> 批量设置 </a> </li>
			    </ul>
			 </div>
			</logic:equal>
				<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="8">
			            <div class="btn">
			              <input type="hidden" name="go" value="a"/>
							<button type="button" class="button2" id="search_go"
								onclick="refreshForm('zgdzdx_xszz.do?method=data_zxdknx&go=go');">
								查 询
							</button>
			              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	重 置
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>
			
					<tbody>
							<tr>
								<th align="left">
								年级
								</th>
								<td>
								<html:select property="nj" onchange="initBjList()" style="width:60px;padding-left:80px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								</td>
								<th>
								<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
								<html:select property="xydm" onchange="initZyList();initBjList()" style="width:140px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</td>
								<th>
								专业
								</th>
								<td>
								<html:select property="zydm" onchange="initBjList()" style="width:160px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								</td>
								<th>
								行政班号
								</th>
								<td>
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	<logic:empty name="rs">
						查询结果&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
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
									ondblclick="chkAssisOne('/xgxt/zgdzdx_xszz.do?method=zxdknxEdit&doType=view')">
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pks"
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
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:equal name="result" value="true">
			<script>
				alert('操作成功');
			</script>
		</logic:equal>
		<logic:equal name="result" value="false">
			<script>
				alert('操作失败');
			</script>
		</logic:equal>
	</body>
</html>
