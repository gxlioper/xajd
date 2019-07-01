<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script language="javascript" src="js/xsgyglFunction.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>	
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em>
				<a>${title }</a>
			</p>
		</div>
		<!-- 标题 end-->	
		<html:form action="/zgdzdx_Gygl" method="post">
			<input type="hidden" id="userName" name="userName"
				value="<bean:write name="userName" scope="request"/>" />
			<input type="hidden" id="delPk" name="delPk" value="" />
				<!-- 中国地质大学 -->
				<logic:equal value="10491" name="xxdm">
				<!-- 页签 -->
				<div class="compTab" id="card">
					<div class="comp_title" id="div1">
						<ul id="ul1">
							<li>
								<a href="#" 
									onclick="$('go').value='';refreshForm('dorm_Using_Search.do?act=usingInfo')">
									<span>本校学生</span>
								</a>
							</li>
							<li class="ha">
								<a href="#" 
									onclick="$('go').value='';refreshForm('wxs_dormUser_Manage.do')">
									<span>其他学生</span>
								</a>
							</li>
						</ul>
					</div>
				</div>
				<!-- 页签 end-->
			  	</logic:equal>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="wxsDUserAdd()"
									class="btn_zj">增加</a>
							</li>
							<li>
								<a href="#"
									onclick="wxsDUserModi()"
									class="btn_xg">修改</a>
							</li>
							<li>
								<a href="#"
									onclick="batchDel()"
									class="btn_sc">删除</a>
							</li>
							<li>
								<a href="#" 
									onclick="batchTs()"
									class="btn_dr">退宿</a>
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
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/zgdzdx_Gygl.do?method=wxsDormUserManage')">
											查询
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th>
									校区
								</th>
								<td>
									<html:select property="xqdm" style="width:100px" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>											
								</td>
								<th>
									楼栋
								</th>
								<td>
									<html:select property="lddm" style="width:100px" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									所属层数
								</th>
								<td>
									<html:select property="cs" style="width:100px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									寝室号
								</th>
								<td>
									<html:select property="qsh" style="width:100px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>		
								</td>
							</tr>
							<!-- 第二行 -->
							<tr>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" styleId="xh" style="width:100px"></html:text>								
								</td>
								<th>
									姓名	
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="width:80px"></html:text>
								</td>
								<th>
									性别
								</th>
								<td>
									<html:select property="xb" style="width:110px" styleId="xb">
										<html:option value=""></html:option>
										<html:option value="男">男</html:option>
										<html:option value="女">女</html:option>
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
				<!-- 过滤条件 end-->
				<!-- 查询结果-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> 查询结果&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">未找到任何记录！</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- 表头 -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="fyxx" value="all"
											onclick="chec()"/>
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
									ondblclick="wxsDUserView()">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pkV"
												value="<bean:write name="v"/>"/>
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
					</logic:notEmpty>
				</div>
				<!-- 查询结果 end-->
			</div>	
			<div id="clinDiv" style="display:none" align="center">
				<!-- 卫生检查情况 -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="2">
							<span>退宿时间设置</span>
						</th>
					</tr>
				</thead>
				<tbody>		
						<tr>
							<td align='right' width='40%'>
								<font color=red>*</font>退宿时间：
							</td>
							<td align='left'>
								<input type='text' name='tssjv' id="tssjv"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('tssjv','y-mm-dd','aa');"
									readonly="true"/>
							</td>
						</tr>
				</tbody>
				<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
								<button  id="clinBtnSave" onclick='wxs_closeToSave()'>
									提交
								</button>
								</div>
							</td>
						</tr>
						</tfoot>
					</table>
				</fieldset>
			</div>
		</html:form>
		<script type="text/javascript">
		 function wxsDUserAdd(){
	            var url = "/xgxt/zgdzdx_Gygl.do?method=wxsDormUserAdd";
	            showTopWin(url,"800","600");
	       }
	     function wxsDUserModi(){
	           if (curr_row == null) {
		          alert("请选要修改的记录！\n单击一行记录即可");
		          return false;
	           } else {
	            var url = "/xgxt/zgdzdx_Gygl.do?";
	            url +="method=wxsDormUserModi";
	            url +="&pkValue=";	
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value;   	                      
	            showTopWin(url,"800","600");
	           }
	      }
	      function wxsDUserView(){	           
	            var url = "/xgxt/zgdzdx_Gygl.do?";
	            url +="method=wxsDormUserModi";
	            url +="&pkValue=";	
	            url +=(curr_row.cells[0].getElementsByTagName("input"))[0].value; 
	              url +="&view=true";   	                      
	            showTopWin(url,"800","600"); 
	      }  
		 function chec(){
             for(i=0;i<document.getElementsByName("pkV").length;i++){
      	         document.getElementsByName("pkV")[i].checked=document.getElementsByName("fyxx")[0].checked;
             }
         }
   function batchDel(){
           var url = "/xgxt/zgdzdx_Gygl.do?method=wxsDormUserDel"; 
		   var RowsStr="!!";		  
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	  }
		   }
		   document.forms[0].delPk.value = RowsStr;
		   
		   if (RowsStr=="!!"){
			   alert("请选择要删除的记录！\n(单击每条记录前复选框)");
			   return false;
		   }
		
		   if (!confirm("确定要删除所选记录？")){
			  return false;
		   }
<%--		   else{--%>
<%--			      url += "&toHistory=no";--%>
<%--		   }	   --%>
	       refreshForm(url);          
         }
  function batchTs(){
<%--           var url = "/xgxt/zgdzdx_Gygl.do?method=wxsDormUserDel"; --%>
		   var RowsStr="!!";		  
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	  }
		   }
		   document.forms[0].delPk.value = RowsStr;
		   
		   if (RowsStr=="!!"){
			   alert("请选择要退宿的学生记录！\n(单击每条记录前复选框)");
			   return false;
		   }
		
		   if (!confirm("确定要对所选的学生进行退宿操作？\n\n点击\'确定\'该生住宿信息将被转入历史库中，\n\n同时外校生住宿信息库中该条记录将被删除。\n\n点击\'取消\'将中止退宿操作！")){
			  return false;
		   }
		   
<%--		   else{--%>
<%--		      url += "&toHistory=yes";			 --%>
<%--		   }--%>
           if($("tssjv")){
              $("tssjv").value=getCurrDate();
           }
		   showMessage('clinDiv',true,'#C7DEFC')		   	   
	               
  }
function wxs_closeToSave() {
    var tssj = "";
    if($("tssjv")) {
        tssj = $("tssjv").value;
    } 
    $("clinBtnSave").disabled=true;     
	refreshForm("/xgxt/zgdzdx_Gygl.do?method=wxsDormUserDel&toHistory=yes&tssjv="+tssj);        

}         
 	</script>
	</body>
</html>
