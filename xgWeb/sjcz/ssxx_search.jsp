<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
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
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<body  onload="viewXsCxTj()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="tips" scope="request" /></a>
			</p>
		</div>
		<!-- 标题 end-->
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>		
		<html:form action="/data_search" method="post">
			<logic:notEmpty name="imageview">
			</logic:notEmpty>
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="dxq" name="dxq"
				value="<bean:write name="writeAble" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
			<input type="hidden" name="lcV" id="lcV" value="" />
			<input type="hidden" name="qshV" id="qshV" value="" />
			<input type="hidden" id="delPk" name="delPk" value="pk" />
			<input type="hidden" name="userName" id="userName"
				value="<bean:write name="userName"/>" />
				<!-- 中国地质大学 -->
				<logic:equal value="10491" name="xxdm">
			   <div class="compTab" id="card">
				   <div class="comp_title" id="div1">
						<ul>
							<li id="li-${s}" class="ha">
								<a href="#" onclick="$('go').value='';refreshForm('ssxx_search.do?act=dormInfo')">
									<span>普通查询</span>
								</a>
							</li>
							<li id="li-${s}" >
								<a href="#" onclick="$('go').value='';refreshForm('zgddGygl.do?method=rykTbtj')">
									<span>图表统计</span>
								</a>
							</li>
						</ul>
				  	</div>
			  	</div>
			  	</logic:equal>
			  	<!-- 中国地质大学 end-->
			  	<!-- 信阳师范 -->
				<logic:equal value="10477" name="xxdm">
			   <div class="compTab" id="card">
				   <div class="comp_title" id="div1">
						<ul>
							<li id="li-${s}" class="ha">
								<a href="#" onclick="$('go').value='';refreshForm('ssxx_search.do?act=dormInfo')">
									<span>普通查询</span>
								</a>
							</li>
							<li id="li-${s}" >
								<a href="#" onclick="$('go').value='';refreshForm('zgddGygl.do?method=rykTbtj')">
									<span>图表统计</span>
								</a>
							</li>
						</ul>
				  	</div>
			  	</div>
			  	</logic:equal>
			  	<!-- 信阳师范 end-->
			<div class="toolbox">
				<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<logic:equal value="yes" name="writeAble">
								<li>
									<a href="#" 
										onclick="viewMore('add')"
										class="btn_zj">增加</a>
								</li>
								<li>
									<a href="#"
										onclick="viewMore('modi')"
										class="btn_xg">修改</a>
								</li>
								<li>
									<a href="#"
										onclick="batch()"
										class="btn_sc">删除</a>
								</li>
								<logic:equal value="11407" name="xxdm"><%--西北第二民族<bean:message key="lable.xsgzyxpzxy" />--%>
								<li>
									<a href="#"
										onclick="dataTjExport('/xgxt/XsgyglDispatch.do?method=fykBbtj')"
										class="btn_tj">房源库统计</a>
								</li>
								</logic:equal>
								<li>
									<a href="#" 
										onclick="impAndChkData();" 
										class="btn_dr">导入</a>
								</li>
							</logic:equal>
							<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" 
									onclick="dataExport()" 
									class="btn_dc">导出</a>
							</li>
							</logic:equal>
						</ul>
					</div>
				<!-- 按钮 end-->
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="SsXXSearch();">
											查 询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 中国地质大学 -->
							<logic:equal value="10491" name="xxdm">
							<!-- 第一行 -->
							<tr>
								<th width="20%">
									校区
								</th>
								<td width="10%">
									<html:select property="xqdm" style="" styleId="xqdm" onchange="setLdList()">
											<html:options collection="xqdmList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
								<th width="20%">
									园区		
								</th>
								<td width="10%">
									<html:select property="yqdm"  styleId="yqdm" onchange="setYqLdList();setXqYqList();">
											<html:options collection="yqList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
								<th width="20%">
									楼栋
								</th>
								<td>
									<html:select property="lddm" style="" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
											<html:options collection="ldList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									层数
								</th>
								<td>
									<html:select property="cs" style="" styleId="cs" onchange="setQsList();">
											<html:options collection="csList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
								<th>
									寝室号
								</th>
								<td>
									<html:select property="qsh" style="" styleId="qsh" onchange="">
											<html:options collection="qsList" property="dm" labelProperty="mc" />
										</html:select>	
								</td>								
								<td colspan="2">
									<input type="checkbox" name="tb" id="tbview" value="tbxs" onclick="viewXsCxTj()" style="display: none"><!-- 图表显示 -->
								</td>
							</tr>
							</logic:equal>
							<!-- 非中国地质大学 -->
							<logic:notEqual value="10491" name="xxdm">
							<!-- 第一行 -->
							<tr>
								<th>
									校区
								</th>
								<td>
									<html:select property="xqdm" style="" styleId="xqdm" onchange="setLdList()">
											<html:options collection="xqdmList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
								<th>
									楼栋
								</th>
								<td>
									<html:select property="lddm" style="" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
											<html:options collection="ldList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
								<th>
									层数
								</th>
								<td>
									<html:select property="cs" style="" styleId="cs" onchange="setQsList();">
											<html:options collection="csList" property="dm" labelProperty="mc" />
										</html:select>
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									寝室号
								</th>
								<td>
									<html:select property="qsh" style="" styleId="qsh" onchange="">
											<html:options collection="qsList" property="dm" labelProperty="mc" />
										</html:select>	
								</td>
								<th>
								图表统计
								</th>
								<td colspan="3">
									<input type="checkbox" name="tb" id="tbview" value="tbxs" onclick="viewXsCxTj()" style="">
								</td>
							</tr>
							</logic:notEqual>
							<!-- 第三行 -->
							<tr id="xsCxTj" style="display : none" >
								<th>
									年级
								</th>
								<td>
									<html:select property="imgNj" >
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
								</td>
								<th>
									院系
								</th>
								<td>
									<logic:equal name="userType" value="xy">
										<html:hidden property="imgXy"/>
										<html:select property="imgXy" styleId="xy" style="100px" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
													labelProperty="xymc"/>
										</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="xy">
										<html:select property="imgXy" styleId="xy" style="100px">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
										</html:select>
									</logic:notEqual>
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
								<th>
									
								</th>
								<td>
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
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
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="fyxx" value="all"
											onclick="chec()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- 表头 end-->
							<!--内容 -->
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="viewMore('view')">
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pkV"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<!--内容 end-->
						</table>
						<!--分页-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						<!--分页end-->
					</logic:notEmpty>
				</div>
			</div>
		</html:form>
	</body>
<script type="text/javascript">
	function chec(){
     	 for(i=0;i<document.getElementsByName("pkV").length;i++){
      		document.getElementsByName("pkV")[i].checked=document.getElementsByName("fyxx")[0].checked;
      	}
    }
	function batch(){
        var url = "/xgxt/XsGyGlLogic.do?method=xsGyGlDelete"; 
		var RowsStr="!!";
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	  }
		   }
		   document.forms[0].delPk.value = RowsStr;
		
		   if (RowsStr=="!!"){
			   alert("请选择要批量操作的记录！");
			   return false;
		   }
		
		   if (!confirm("确定要操作所选记录？")){
			  return false;
		   }
	       refreshForm(url);
}  
	function dataTjExport(url) {
		document.forms[0].action = url;
		document.forms[0].target = "_blank";
		document.forms[0].submit();
		document.forms[0].target = "_self";
	}    

function SsXXSearch(){  
    if($("tb")){
       if($("tb").checked){
         var xiaoqu = $("xqdm").value;
         var lddm   = $("lddm").value;
         var qsh    = $("qsh").value;
         
         url = "/xgxt/zgdzdx_Gygl.do?method=getDormImageWiew";
         url +="&xqdm="+xiaoqu;
         url += "&lddm="+lddm;
         url += "&qsh="+qsh;
         allNotEmpThenGo(url);
       }else{
         allNotEmpThenGo('/xgxt/ssxx_search.do?act=dormInfo');
       }
    }else{ 
       allNotEmpThenGo('/xgxt/ssxx_search.do?act=dormInfo');
    }
}
function viewsearch(){   
    if($("tb")){
       if($("tb").checked){
         var xiaoqu = $("xqdm").value;
         var lddm   = $("lddm").value;
         var qsh    = $("qsh").value;
         
         url = "/xgxt/zgdzdx_Gygl.do?method=getDormImageWiew";
         url +="&xqdm="+xiaoqu;
         url += "&lddm="+lddm;
         url += "&qsh="+qsh;
         allNotEmpThenGo(url);
       }
    }else{
    	allNotEmpThenGo('/xgxt/ssxx_search.do?act=dormInfo');
    }
}


</script>	
</html>
