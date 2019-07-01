<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/comm/message.js"></script>
		<script language="javascript">
		function modi(url,h,w){
			if(curr_row != null){
				var flag = true;
				var sTitle = "修改寝室信息";
				if(url == "gyglnew_qsgl.do?method=qswhView"){
					flag = false;
					sTitle = "查看寝室信息";
				}
				if(curr_row.getElementsByTagName('input')[1].value != '全空' && flag){
					alertError('该寝室已入住，暂不能修改信息！');
					return false;
				}
				
				//showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
				showDialog(sTitle, w, h, url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
				return true;
			}else{
				alertError('请选择要操作的数据行！');
				return false;
			}
		}
		
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('gyglnew_qsgl_qsgl.do?doType=go');
		}

		function importUpdateQsxx(){
			var realTable = "";
			var tableName = "";
			var sty = "toolbar=no,location=no,directories=no,status=yes";
			sty += ",menubar=no,scrollbars=yes,resizable=yes,width=600,height=400,top=100";
			sty += ",left=200";
			if($("realTable")) realTable = document.getElementById("realTable").value;
			if($("tableName")) tableName = document.getElementById("tableName").value;
			url = 'gyglnew_qsgl.do?method=importData';
			url += "&realTable=" + realTable;
			url += "&tableName=" + tableName;
			//showTopWin(url,600,500);
			//refreshForm(url);
			//window.open(url,'',sty);
			showDialog('导入更新寝室信息',560,250,url);
		}

		function qscsh(){
			var checkBoxArr = document.getElementsByName("primarykey_cbv");
			var dis = document.getElementsByName("dis");
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;	
					if(dis[i].value!="全空"){
						alertError("选中记录中含有已入住的床位，请选择未入住的床位进行床位所属初始化！");
						return false;
					}	
				}
			}
			//showTopWin('gyglnew_qsgl.do?method=qsglQsssInit',600,250);
			showDialog('寝室所属初始化', 520, 270, 'gyglnew_qsgl.do?method=qsglQsssInit');
		}

		//删除
		function del(){
			var checkBoxArr = document.getElementsByName("primarykey_cbv");
			var dis = document.getElementsByName("dis");
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;	
					if(dis[i].value!="全空"){
						flag="选中记录中含有非空寝室，请选择全部床位未入住的寝室进行删除操作！"
						break;	
					}	
				}
			}
			if(num==0){
				alertInfo("请选择要操作的记录！");
			}else{
				if (flag==""){
					batchData('primarykey_cbv','gyglnew_qsgl.do?method=qsglManage&go=go&doType=del','del');
				}else{
					alertError(flag);
				}		
			}
		}
				
		function qsxxglExportConfig() {
		//DCCLBH导出功能编号,执行导出函数 
		customExport("gyglnew_qsgl_qsgl.do", qsxxglExportData);
		}
		
	
		
		// 导出方法
		function qsxxglExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyglnew_qsgl.do?method=qsxxglExportData&dcclbh=" + "gyglnew_qsgl_qsgl.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//批量改宿舍规格
		function plwhTypeOfQs(){
			var checkBoxArr = document.getElementsByName("primarykey_cbv");
			var dis = document.getElementsByName("dis");
			var primarykey_checkVal = new Array();
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					primarykey_checkVal.push(checkBoxArr[i].value);
					num++;	
					if(dis[i].value!="全空"){
						flag="选中记录中含有非空寝室，请选择全部床位未入住的寝室进行操作！"
						break;	
					}	
				}
			}
			if(num==0){
				alertInfo("请选择要操作的记录！");
			}else{
				if (flag==""){
					//gyglnew_qsgl.do?method=
					showDialog("批量修改", 588, 248, "gyglnew_qsgl.do?method=plwhTypeOfQs&primarykey_checkVal="+primarykey_checkVal);
				}else{
					alertError(flag);
				}		
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_qsgl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" id="kcshqss" value="${kcshqss}"/>
			<input type="hidden" id="searchTjstr" value="${searchTjstr}"/>
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help" >
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>			
			
			<!-- 提示信息 end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					当寝室中的床位已入住时，寝室不可删除；寝室所属初始化操作仅可操作全部床位未入住的寝室；<br/>
					查询结果中的“空闲床位数”指除去保留床位后寝室中的空闲床位的数量；
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="showDialog('增加寝室信息', 760, 480, 'gyglnew_qsgl.do?method=qswhAdd');;return false;" class="btn_zj"> 增加 </a></li>
						<li><a href="#" onclick="modi('gyglnew_qsgl.do?method=qswhModi',480,760);return false;" class="btn_xg"> 修改 </a></li>
						<li><a href="#" onclick="plwhTypeOfQs();return false;" class="btn_xg"> 批量修改 </a></li>
						<li><a href="#" onclick="del();return false;" class="btn_sc"> 删除 </a></li>
						<%--<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">导入数据</a></li>		
						<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">导出数据</a></li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">导出字段确认</a></li>--%>
						<li><a href="#" onclick="qscsh();return false;" class="btn_csh">寝室所属初始化</a></li>
						<li><a href="#" onclick="importUpdateQsxx();return false;" class="btn_dr">导入更新寝室信息</a></li>
						</logic:equal>
						<%--<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportDataZdy('${path }');return false;">导出数据</a></li>--%>
						<%--<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">导出数据</a></li>--%>
						<li><a href="#" class="btn_dc" onclick="qsxxglExportConfig();return false;">导出</a></li>
					</ul>
				</div>
				
			     <logic:equal name="superSearch" value="yes">
			     	<%@ include file="/comm/search/superSearchArea.jsp"%>
			     </logic:equal>
			    

			</div>
				 <logic:present name="message">
			     <input type="hidden" id="message" name="message" value="${message }"/>
			     <script type="text/javascript">
			     showAlertDivLayer($('message').value,{},{"clkFun":function(){
	 				if (parent.window){
	 					refershParent();
	 				}
	 			   }});
			     </script>
		         </logic:present>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序;双击寝室显示寝室基本信息及各床位相关信息</font>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }" onclick="tableSort(this)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:empty name="rs">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr>
								<td>
									<input type="checkbox" name="pk" value="" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s">
								<logic:iterate id="dis" offset="10" length="1" name="s"></logic:iterate>
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="modi('gyglnew_qsgl.do?method=qswhView',480,760);">
									<td>								
										<input type="checkbox" name="primarykey_cbv" id="pkV" 
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="dis" id="dis"
											value="<logic:iterate id="v" name="s" offset="9" length="1">${v }</logic:iterate>" />										
									</td>
									<logic:iterate id="v" name="s" offset="1" length="9">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							int pageSize = (Integer)request.getAttribute("pageSize");
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td>
									<input type="checkbox" name="primarykey_cbv" value="" disabled="disabled"></input>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
					</logic:notEmpty>
				</table>
				<!--分页显示-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewQsglForm"></jsp:include>
				<!--分页显示-->
			</div>
		</html:form>

	</body>
</html>
