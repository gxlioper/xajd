<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" />
		</title>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">
</script>
	<body onload="xyDisabled('xy')">
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
	var url = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsjPlsz";
	url += "&pkDel=" + RowsStr;
	showTopWin(url, 420,300);
	return true;
}

function chec(){
      for(i=0;i<document.getElementsByName("pk").length;i++){
      	document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
      }
}

function chkAssisOneByzz(url) {
	if (curr_row == null) {
		return false;
	} else {
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
		showTopWin(url, 530,325);
		return true;
	}
}

function dgsz(){
	var url = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsjEdit";
	if((curr_row == null) || (curr_row == "")){
		alert("请选择要设置的记录！");
		return false;
	} else {
		url += "&pkVal=";
		url += curr_row.getElementsByTagName("input")[0].value;
	}
	return showTopWin(url,530,325);
}

function csh(){
	var url = "/xgxt/zgdzdx_xszz.do?method=jzxj_xmsjcsh";
	if (!confirm("是否保存已设置数据？")){
		if (!confirm("确定不保存所有已设置数据？")){
			return false;
		}
		url += "&sfbc=no";
	}else{
		url += "&sfbc=yes";
	}
	document.forms[0].action=url;
    document.forms[0].submit();
}
		</script>
		<html:form action="/zgdzdx_xszz.do?method=jzxj_xmsjwh" method="post">
			<logic:present name="endCsh">
			<logic:match value="end" name="endCsh">
				<script language="javascript">
	         	alert("初始化结束！");
	         	</script>
			</logic:match>
			</logic:present>
			
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
			
			<div class="toolbox">
			  <!-- 按钮 -->
			  <logic:equal value="yes" name="writeAble">
			  <div class="buttonbox">
			    <ul>
			      <li> <a href="#" onclick="csh();" class="btn_csh">初 始 化</a> </li>
			      <li> <a href="#" onclick="dgsz();" class="btn_sz">单个设置</a> </li>
			      <li> <a href="#" onclick="plsz();" class="btn_sz">批量设置</a> </li>
			    </ul>
			  </div>
			  </logic:equal>
			<div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
		              <button type="button" class="btn_cx" id="search_go"
										onclick="refreshForm('zgdzdx_xszz.do?method=jzxj_xmsjwh&go=go');">
										查询
									</button>
		              &nbsp;&nbsp;&nbsp;&nbsp;
		              <button type="button" class="btn_cz" id="btn_cz" 
		              	onclick="searchReset();return false;">
		              	重 置
		              </button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
		      	<tr>
		      		<th>项目</th>
		      		<td>
		      			<html:select name="rs1" property="xmdm" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="jzxjxmList" property="xmdm"
											labelProperty="xmmc" />
									</html:select>
		      		</td>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
		      		<td><html:select name="rs1" property="xydm" style="width:180px" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select></td>
		        </tr>
			</tbody>
			</table>
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
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			   
			  <logic:notEmpty name="rs">
			  <table summary="" class="dateline" align="center" width="100%">
			    <thead>
			      <tr>
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
									ondblclick="chkAssisOneByzz('/xgxt/zgdzdx_xszz.do?method=jzxj_xmsjEdit')">
									<td align="">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pk"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td align="">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td align="">
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
						</logic:iterate>
			    </tbody>
			  </table>
			  <!--分页显示-->
			  <!--分页显示-->
			  </logic:notEmpty>
			</div>	

		</html:form>
	</body>
</html>
