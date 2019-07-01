<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getOtherData.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
		<script language="javascript">
		var Rows=new Array();	//所有选中的行对象
		var ShiftStartRow="";		//Shift多选时存储开始行对象
		var cur_bgc = "#ffdead";//选中行背景（字符串）
		
function rowOver(objTr) {//
	curr_row = objTr;
}

function rowOut(objTr) {//
	curr_row = null;
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

function del(url){
	var RowsStr="!#!";
	
	for (i=0; i<document.getElementsByName("pkCh").length; i++){
    	if(document.getElementsByName("pkCh")[i].checked){
    		RowsStr+=document.getElementsByName("pkCh")[i].value+"!#!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!#!"){
		alert("请选择要批量删除的记录！");
		return false;
	}
	
	if (!confirm("<bean:message key="lable.xsgzyxpzxy" />用户不能删除已通过学校审核的数据，确定要删除所选记录？")){
		return false;
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}

function notPass(url){
	var RowsStr="!#!";
	
	for (i=0; i<document.getElementsByName("pkCh").length; i++){
    	if(document.getElementsByName("pkCh")[i].checked){
    		RowsStr+=document.getElementsByName("pkCh")[i].value+"!#!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!#!"){
		alert("请选择要批量修改为不通过的记录！");
		return false;
	}
	
	document.forms[0].action=url;
    document.forms[0].submit();
}

function pass(url){
	var RowsStr="!#!";
	
	for (i=0; i<document.getElementsByName("pkCh").length; i++){
    	if(document.getElementsByName("pkCh")[i].checked){
    		RowsStr+=document.getElementsByName("pkCh")[i].value+"!#!";
    	}
	}
	document.forms[0].pkDel.value = RowsStr;
	
	if (RowsStr=="!#!"){
		alert("请选择要批量修改为通过的记录！");
		return false;
	}
	
	var i;
	
	dwr.engine.setAsync(false);
	getOtherData.getJzxjPlshNum(RowsStr,document.getElementById("userType").value,function(data){
       if(data!=null){
       	i=data;
       }
    });
    dwr.engine.setAsync(true);
	
	var kshrs = document.getElementById('kshrs').value;
	if (Math.round(i)>Math.round(kshrs)){
		alert("本次批量审核新增的通过学生有"+i+"人，比可通过剩余人数"+kshrs+"人多了"+(Math.round(i)-Math.round(kshrs))+"人！");
		return false;
	}
	document.forms[0].action=url;
	document.forms[0].submit();
}

function chec(){
      for(i=0;i<document.getElementsByName("pkCh").length;i++){
      	document.getElementsByName("pkCh")[i].checked=document.getElementsByName("qbxz")[0].checked;
      }
}

function tgzg(){
	var kshrs = document.getElementById('kshrs').value;
	var num = 0;
	
	for (i=0; i<document.getElementsByName("pkCh").length; i++){
    	if(document.getElementsByName("pkCh")[i].checked){
    		alert(document.getElementsByName("pkCh")[i].value);
    	}
	}
	
	if (kshrs == "0") {
		document.getElementById('tgBut').disabled = true;
	} else {
		document.getElementById('tgBut').disabled = false;
	}
}

function zsPrintTo() {
    var url = "/xgxt/zgdzdx_xszz.do?method=zsPrintDate";
	if (curr_row == null) {
	    alert("请选择要打印的记录！\n（单击相应的行）");
		return false;
	} else {
		url += "&pkValue=";
		url += curr_row.getElementsByTagName("input")[0].value;
		url += "&xmdm=";
		url += curr_row.getElementsByTagName("input")[1].value;		
		showOpenWindow(url, 350,250);
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
	url += "&xmdm=";
	url += curr_row.getElementsByTagName("input")[2].value;
	return showTopWin(url,750,550);
}

function zsgssz() {
	window.open("/xgxt/zgdzdx_xszz.do?method=zsGsSz", 1000, 1000);
	return true;
}
</script>
</head>
<body onload="xyDisabled('xy')">
		<html:form action="/zgdzdx_xszz" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>奖助学金 - 证书打印 - 证书打印</a>
				</p>
			</div>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<input type="hidden" name="userType"  id="userType"  value="${userType}"/>
			<div class="toolbox">
			 <!-- 按钮 -->
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="zsgssz()" class="btn_sz"> 证书格式设置 </a> </li>
			    <li> <a href="#" onclick="zsPrintTo()" class="btn_dy"> 打印 </a> </li>
			    </ul>
			 </div>
			
			<div class="searchtab">
			<table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <button type="button" class="btn_cx" id="search_go" 
		              	onclick="refreshForm('/xgxt/zgdzdx_xszz.do?method=zsPrintDef&go=go');this.disabled=true">
		              	查 询
		              </button>
		              &nbsp;&nbsp;&nbsp;&nbsp;
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
								  学年：
								 </th>
								 <td>
									<html:select  property="xn">										
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>
								<th>
									年级：
								</th>
								<td>
									<html:select  property="nj" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />：
								</th>
								<td>
									<html:select  property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
								</td>
								
							</tr>
							<tr>
								<th>
									专业：
								</th>
								<td>
									<html:select  property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
									</html:select>									
								</td>
								<th>
									班级：
								</th>
								<td>
									<html:select  property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
									</html:select>	
								</td>																		
								<th>
									项目：
								</th>
								<td>
									<html:select  property="xmdm" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="jzxjxmList" property="xmdm"
											labelProperty="xmmc" />
									</html:select>
								</td>
							</tr>	
							<tr>
								<th>
									学号：
								</th>
								<td>
									<html:text property="xh"></html:text>
								</td>
								<th>
									&nbsp;&nbsp;姓名：
								</th>
								<td>	
									<html:text property="xm"></html:text>
								</td>
								<td colspan="2">
							</tr>					
					</tbody>
				</table>
				</div>
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	查询结果&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">未找到任何记录！</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;						
						<font color="blue">提示：单击表头可以排序</font>						
			 		 </logic:notEmpty>
			    </span>
			    </h3>

			<logic:notEmpty name="rs">
					 <table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
<%--								<td>--%>
<%--									<input type="checkbox" name="qbxz" value="all"--%>
<%--										onclick="chec('qbxz')">--%>
<%--								</td>--%>
								<logic:iterate id="tit" name="topTr" offset="2">
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
									style="cursor:hand;background-color:"
									ondblclick="">
<%--									<td align="center">--%>
<%--										<logic:iterate id="v" name="s" offset="0" length="1">--%>
<%--											<input type="checkbox" name="pkCh"--%>
<%--												value="<bean:write name="v"/>">--%>
<%--										</logic:iterate>--%>
<%--									</td>--%>
									<td align="center">										
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
											<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="2" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="3">
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
	</body>

</html>
