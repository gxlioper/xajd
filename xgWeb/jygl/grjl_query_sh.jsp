
<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
	    function grjlquerygo(){
		 	document.forms[0].action = "/xgxt/grjlquerysh.do?act=go&doType=query";
		 	document.forms[0].submit();
        }
		
		function viewMoreinfo(doType){
		var url ="/xgxt/grjlViewMoreinfo.do?doType=view&pkValue=";
		var pkValue ="";
		
		 if (doType == "view"){
		 pkValue = curr_row.getElementsByTagName("input")[0].value;
		 url += pkValue;
		 showTopWin(url, 840, 600);
		 }
		}
		
		
		function grjlUpdate(doType){
		var url ="/xgxt/grjlUpdate.do?pkValue=";
		var pkValue ="";
		
		 if (doType == "update"){
		    if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要修改该行数据吗？")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 840, 600);
				return true;
			} else {
				return false;
			}
		   }
	      }		
		}
		
		function JyglGrjlDel(doType2){
		var url = "/xgxt/JyglGrjlDel.do?doType2=del&doType=query&act=go&pkValue=";
		var pkValue = "";
			
		if (doType2 == "del") {
		   if (curr_row == null) {
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		    } else {
			if (confirm("确定要删除该行数据吗？")) {
				pkValue = curr_row.getElementsByTagName("input")[0].value;
				url += pkValue;
				refreshForm(url);
				return true;
			} else {
				return false;
			}
		  }
		   return;
	      }
		}
		
		function grjlsh(doType){
			var url ="/xgxt/grjlshwindow.do?doType=shenhe&pkValue=";
			var pkValue ="";
		
			if (doType == "shenhe") {
		   		if (curr_row == null) {
					alert("请选择要审核的数据！\n（单击相应的行）");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showTopWin(url, 840, 600);
		    		return true;
		    	}
		 	}else{
		    	return true;
		 	}
		}
    /*
	全部选中
	*/
      function chec(){
         for(i=0;i<document.getElementsByName("pk").length;i++){
      	    document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
         }
      }		
	
	/*
	批量审核通过
	*/
	 function pass(url){
	   var RowsStr="!!#!!";
	
	   for (i=0; i<document.getElementsByName("pk").length; i++){
		 if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	 }
	   }
	   document.forms[0].pkstring.value = RowsStr;
	
	   if (RowsStr=="!!#!!"){
		  alert("请选择要批量审核的记录！");
		  return false;
	   }
	
	   if (!confirm("确定要通过所选记录？")){
		  return false;
	   }
	   document.forms[0].action=url;
       document.forms[0].submit();
    }
    
    /*
	批量审核否决
	*/
    function notpass(url){
	var RowsStr="!!#!!";
	
	  for (i=0; i<document.getElementsByName("pk").length; i++){
    	if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	  }
	  }
	  document.forms[0].pkstring.value = RowsStr;
	
	  if (RowsStr=="!!#!!"){
		alert("请选择要批量审核的记录！");
		return false;
	  }
	
	  if (!confirm("确定要否决所选记录？")){
		return false;
	  }
	document.forms[0].action=url;
    document.forms[0].submit();
   }
    function dataExportzjlg(){
    	document.forms[0].action = "/xgxt/dataExportzjlg.do";
    	document.forms[0].target = "_blank";
    	document.forms[0].submit();
    	document.forms[0].target = "_self";
    }
	</script>
	</head>
	<body>
		<html:form action="/data_search" method="post">
			<input type="hidden" name="pkstring" value="" />
			<logic:notEmpty name="who">
				<input type="hidden" id="ldgxfdy" name="ldgxfdy" value="<bean:write name="who"/>" />
			</logic:notEmpty>
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>就业管理 - 学生信息 - 个人简历审核</a>
				</p>
			</div>
			
			<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_shtg" onclick="pass('/xgxt/grjlshallpass.do?doType=query&doType3=pass&act=go')">批量通过</a></li>
						<li><a href="#" class="btn_shbtg" onclick="notpass('/xgxt/grjlshallpass.do?doType=query&doType3=notpass&act=go')">批量否决</a></li>
						<li><a href="#" class="btn_sh" onclick="grjlsh('shenhe')">审核</a></li>
						<li><a href="#" class="btn_xg" onclick="grjlUpdate('update')">修改</a></li>
						<li><a href="#" class="btn_sc" onclick="JyglGrjlDel('del')">删除</a></li>
						<li><a href="#" class="btn_dc" onclick="dataExportzjlg()">导出</a></li>							
					</ul>
				</div>
			</div>
			
			<div class="searchtab">		
				<table width="100%" class="">
					<tbody>
						<tr>
							<logic:equal name="who" value="teacher">
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><html:select name="rs1" property="xymc" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xymc"
											labelProperty="xymc" />
									</html:select></td>
							</logic:equal>
							<logic:equal name="who" value="fudaoyuan">
								<th><bean:message key="lable.xsgzyxpzxy" /></th>
								<td><html:text name="rs1" property="xymc" style="width:150px"
										readonly="true" /></td>
							</logic:equal>
							<th>学校审核</th>
							<td><html:select name="rs1" property="xxsh" style="width:127px">
								<html:option value=""></html:option>
								<html:option value="未审核">未审核</html:option>
								<html:option value="已通过√">已通过√</html:option>
								<html:option value="未通过X">未通过X</html:option>
							</html:select></td>
							<th>发布时间</th>
							<td><html:select name="rs1" property="xjsj" style="width:150px">
								<html:option value=""></html:option>
								<html:option value="-1">当天</html:option>
								<html:option value="-2">近两天</html:option>
								<html:option value="-7">一周内</html:option>
								<html:option value="-15">半月内</html:option>
								<html:option value="-30">一月内</html:option>
								<html:option value="-90">三月内</html:option>
								<html:option value="-180">半年内</html:option>
								<html:option value="-365">一年内</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>学号</th>
							<td><html:text name="rs1" property="xsxh" style="width:150px" /></td>
							<th>姓 名</th>
							<td><html:text name="rs1" property="name" /></td>
							<th>性别</th>
							<td><html:select name="rs1" property="xb" style="width:60px">
									<html:option value=""></html:option>
									<html:option value="男">
										男
									</html:option>
									<html:option value="女">
										女
									</html:option>
								</html:select></td>
						</tr>
						<tr>
							<th>年级</th>
							<td><html:select name="rs1" property="nj" style="width:80px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<td colspan="4">
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
							<div class="btn">
								<button id="query_go" onclick="grjlquerygo()">
									查询
								</button>
								 <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
									重置
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
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')"/>
								</td>
								<logic:notEqual value="12453" name="xxdm" scope="session">
								<logic:iterate id="tit" name="topTr" offset="1" length="9">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								</logic:notEqual>
								<logic:equal value="12453" name="xxdm" scope="session">
								<logic:iterate id="tit" name="topTr" offset="1" length="8">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<logic:equal value="xx" name="userType" scope="session">
									<logic:iterate id="tit" name="topTr" offset="9" length="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								</logic:equal>
								<logic:iterate id="tit" name="topTr" offset="10" length="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								</logic:equal>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewMoreinfo('view')">
								<td align="center">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="pk"
											value="<bean:write name="v"/>" />
									</logic:iterate>
								</td>
								<logic:equal value="12453" name="xxdm" scope="session">
								<logic:iterate id="v" name="s" offset="1" length="8">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:equal value="xx" name="userType" scope="session">
									<logic:iterate id="v" name="s" offset="9" length="1">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								</logic:equal>
								
									<logic:iterate id="v" name="s" offset="10">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								</logic:equal>
								<logic:notEqual value="12453" name="xxdm" scope="session">
								<logic:iterate id="v" name="s" offset="1" length="9">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								</logic:notEqual>
							</tr>
						</logic:iterate>
					</table>
			</logic:notEmpty>
			</div>

			<logic:notEmpty name="delete">
				<logic:equal name="delete" value="ok">
					<script>
                       alert("删除成功！");
                    </script>
				</logic:equal>
				<logic:equal name="delete" value="no">
					<script>
                      alert("删除失败！");
                    </script>
				</logic:equal>
			</logic:notEmpty>

			<logic:notEmpty name="allpass">
				<logic:equal name="allpass" value="ok">
					<script>
                       alert("批量通过成功！");
                    </script>
				</logic:equal>
				<logic:equal name="allpass" value="no">
					<script>
                      alert("批量通过失败！");
                    </script>
				</logic:equal>
			</logic:notEmpty>
			
			<logic:notEmpty name="allnotpass">
				<logic:equal name="allnotpass" value="ok">
					<script>
                       alert("批量否决成功！");
                    </script>
				</logic:equal>
				<logic:equal name="allnotpass" value="no">
					<script>
                      alert("批量否决失败！");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
