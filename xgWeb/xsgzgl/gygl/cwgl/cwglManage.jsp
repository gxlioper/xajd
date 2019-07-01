<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="common.Globals"%>
<%@page import="xsgzgl.gygl.comm.GyglNewInit"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('gyglnew_cwgl_cwgl.do');
		}

		//入住
		function modi(url,h,w){
			var checkBoxArr = jQuery("input[name='primarykey_checkVal']:checked");
			var num=checkBoxArr.size();
			if(num==1){
				var sfbl=jQuery("input[name='primarykey_checkVal']:checked~input").eq(1).val();
				if(sfbl=="是"){
					alertError('保留床位不可入住');
					return false;
				}else if(jQuery("input[name='primarykey_checkVal']:checked~input").eq(2).val()!=""){
					alertError('床位已入住，请先退宿学生，再进行入住！');
					return false;
				}else{
					showDialog('床位入住', h, w, url + '&pkValue='+checkBoxArr.val());
					return true;
				}
			} else {
				alertInfo('请选择一条要操作的数据行');
				return false;
			}
		}

		function view(url,h,w){

			
			//showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);

			showDialog('查看床位详细信息', 790, 525, url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
		}	

		//批量保留
		function modibl(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var dis = document.getElementsByName("dis");
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;	
					if(dis[i].value=="disabled"){
						flag="选中记录中含有不可保留的床位，请选择未分配且未入住的床位！"
						break;	
					}				
				}
			}
			if(num==0){
				alertInfo("请选择要操作的记录！");
			}else{
				if (flag==""){
					//showTopWin('gyglnew_cwgl.do?method=cwwhModibl',350,200);
					showDialog('批量保留床位信息', 500, 300, 'gyglnew_cwgl.do?method=cwwhModibl');
					//showTopWin('gyglnew_cwgl.do?method=cwwhModibl&pkValue='+curr_row.getElementsByTagName('input')[0].value,350,200);
					return true;
				}else{
					alertError(flag);
				}		
			}		
		}

		//删除
		function del(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var xh = document.getElementsByName("xh");
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;	
					if(xh[i].value!=""){
						alertError("选中记录中含有已入住的床位，请选择未入住的床位进行删除！");
						return false;
					}	
				}
			}
			if(num==0){
				alertInfo("请选择要操作的记录！");
			}else{
				if (flag==""){
					batchData('primarykey_checkVal','gyglnew_cwgl.do?method=cwglManage&go=go&doType=del','del');
				}else{
					alertError(flag);
				}		
			}
		}

		function cwdd_submit(){
			if($("rzsj").value==""){
				alertInfo("请选择入住时间！");
				return false;
			}else{
				document.forms[0].action = 'gyglnew_cwgl.do?method=cwglManage&go=go&doType=cwdd';
				document.forms[0].submit();
			}
		}

		function cwdd(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var sfbl = document.getElementsByName("sfbl");
			var flag = "";
			var num=0;
			var tr_td=new Array();
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;
					if(sfbl[i].value=="是"){
						flag="选中记录中含有保留的床位，不可进行对调！";
					}
					tr_td[tr_td.length]=checkBoxArr[i].parentNode.parentNode.cells;
				}
			}
			if(num!=2){
				alertError("选中床位数不为2，请选择两张床位进行操作！");
			}else{
				var xh0 = jQuery(tr_td[0][7]).text().trim();
				var xh1 = jQuery(tr_td[1][7]).text().trim();
				if(<%="bjdm".equals(GyglNewInit.CWFPDX)%>) {
					xh0 = jQuery(tr_td[0][8]).text().trim();
					xh1 = jQuery(tr_td[1][8]).text().trim();
				}
				if(<%="zydm".equals(GyglNewInit.CWFPDX)%>) {
					xh0 = jQuery(tr_td[0][8]).text().trim();
					xh1 = jQuery(tr_td[1][8]).text().trim();
				}
				if (flag==""){
					//判断学号是否为空，即床位是否入住
					if(xh0==""&&xh1==""){
						alertInfo("选中床位无学生入住，不需对调！");
						return false;
					}
					//判断性别是否一致
					if(jQuery(tr_td[0][4]).text().trim() != jQuery(tr_td[1][4]).text().trim()){
						alertError("选中床位性别不同，无法对调！");
						return false;
					}
					var confirmValue="选中床位所属不同，保存将自动更新床位所属！";;
					//判断床位所属年级、学院、班级是否一致
					if(<%="bjdm".equals(GyglNewInit.CWFPDX)%> 
							&& tr_td[0][5].innerHTML==tr_td[1][5].innerHTML 
							&& tr_td[0][6].innerHTML==tr_td[1][6].innerHTML 
							&& tr_td[0][7].innerHTML==tr_td[1][7].innerHTML){
						confirmValue="";
					}
					if(<%="zydm".equals(GyglNewInit.CWFPDX)%> 
						&& tr_td[0][5].innerHTML==tr_td[1][5].innerHTML 
						&& tr_td[0][6].innerHTML==tr_td[1][6].innerHTML 
						&& tr_td[0][7].innerHTML==tr_td[1][7].innerHTML){
					confirmValue="";
					}

					if(<%="xydm".equals(GyglNewInit.CWFPDX)%>
							&& tr_td[0][5].innerHTML==tr_td[1][5].innerHTML 
							&& tr_td[0][6].innerHTML==tr_td[1][6].innerHTML){
						confirmValue="";
					}
					
					$("submit_bz").innerHTML=confirmValue;
					var idList = "";        

					jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").each(function(){
						idList += jQuery(this).val() + ',';        
					});
					url = "gyglnew_cwgl.do?method=cwDd&idList="+idList;
					url1 =encodeURI(encodeURI(url));
					showDialog('床位对调', 580, 250, url1);
					//tipsWindown("床位对调","id:tempDiv","400","200","true","","true","id");
				}else{
					alertError(flag);
				}		
			}		
		}
		

		/*
		数据导入检测
		*/	
		function cwrzglImpAndChkData(){
			var realTable = "";
			var tableName = "";
			var sty = "toolbar=no,location=no,directories=no,status=yes";
			sty += ",menubar=no,scrollbars=yes,resizable=yes,width=600,height=400,top=100";
			sty += ",left=200";
			if($("realTable")) realTable = document.getElementById("realTable").value;
			if($("tableName")) tableName = document.getElementById("tableName").value;
			url = 'gyglnew_cwrzgl.do?method=importData';
			url += "&realTable=" + realTable;
			url += "&tableName=" + tableName;
			//window.open(url,'',sty);
			showDialog('导入数据', 600, 300, url);
		}

		//退宿
		function plts(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var xh = document.getElementsByName("xh");
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;	
					if(xh[i].value==""){
						alertError("选中记录中含有未入住的床位，请选择已入住的床位进行退宿！");
						return false;
					}	
				}
			}
			if(num==0){
				var searchTjstr = document.getElementById("searchTjstr").value;
				if(searchTjstr==''){
					alertError("请选择筛选条件并查询出结果后再退宿！");
					return false;
				}
			}
			showDialog('退宿', 730, 365, 'gyglnew_cwgl.do?method=cwglPlts');
			
		}

		function cwcsh(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var xh = document.getElementsByName("xh");
			var xydm = document.getElementsByName("dis");
			var flag = "";
			var num=0;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;	
					if(xh[i].value!=""){
						alertError("选中记录中含有已入住的床位，请选择未入住的床位进行床位所属初始化！");
						return false;
					}else if(xydm[i].value=="") {
						alertError("选中记录中含有未分配的床位，无需进行初始化操作！");
						return false;
					}
				}
			}
			//showTopWin('gyglnew_cwgl.do?method=cwglCwssInit',600,300);
			showDialog('床位初始化', 600, 300, 'gyglnew_cwgl.do?method=cwglCwssInit');
		}

		//单击学号查看
		function zxsxxView(xh){
			
				var pkValue=xh;
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				//showTopWin(url,850,640);
				showDialog('查看学生详细信息',850,640,url);
		}
				
		function cwxxglExportConfig() {
		//DCCLBH导出功能编号,执行导出函数 
		customExport("gyglnew_cwgl_cwgl.do", cwxxglExportData);
		}
		
	
		
		// 导出方法
		function cwxxglExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyglnew_cwgl.do?method=cwxxglExportData&dcclbh=" + "gyglnew_cwgl_cwgl.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
	
		
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_cwgl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="searchTjstr" value="<bean:write name="searchTjstr"/>"/>
			<input type="hidden" id="yrzcws" value="<bean:write name="yrzcws"/>"/>
			<input type="hidden" id="wrzcws" value="<bean:write name="wrzcws"/>"/>
			<!-- 隐藏域 -->
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
				</p>
			</div>
			<!-- 提示信息 end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					“删除”仅可操作未入住的床位；“入住”时系统会根据学生所属自动更新床位所属；<br/>“对调”仅可操作2个相同性别且至少其中1个已入住的床位；
				</p>
				<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="showDialog('增加床位信息', 600, 330, 'gyglnew_cwgl.do?method=cwwhAdd');return false;" class="btn_zj"> 增加 </a></li><%--
						<li><a href="#" onclick="modi('gyglnew_cwgl.do?method=cwwhModi',800,600);return false;" class="btn_xg"> 修改 </a></li>
						--%><li><a href="#" onclick="del();return false;" class="btn_sc"> 删除 </a></li>
						<li><a href="#" onclick="modibl();return false;" class="btn_sz"> 批量保留 </a></li>
						<li><a href="#" onclick="modi('gyglnew_cwgl.do?method=cwwhModi',800,450);return false;" class="btn_sh"> 入住 </a></li>
						<li><a href="#" onclick="plts();return false;" class="btn_shbtg">退宿</a></li>
						<li><a href="#" onclick="cwdd();return false;" class="btn_sx"> 床位对调 </a></li>						
						<li><a href="#" onclick="cwcsh();return false;" class="btn_csh">床位所属初始化</a></li>
						<li><a href="#" onclick="cwrzglImpAndChkData();return false;" class="btn_dr">导入数据</a></li>
						</logic:equal>
						<%if(Globals.XXDM_zjgmzyjsxy.equals(Base.xxdm)||Globals.XXDM_ZJCMXY.equals(Base.xxdm)){ %>
							<li><a href="#" onclick="setSearchTj();configureExportDataZdy('${path}');return false;" class="btn_dc">导出数据</a></li>
						<%} else { %>
							<li><a href="#" class="btn_dc" onclick="cwxxglExportConfig();return false;">导出</a></li>
						<%} %>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序;双击床位显示床位基本信息</font>
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
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="view('gyglnew_cwgl.do?method=cwwhView',900,700);">
									<td>								
										<input type="checkbox" name="primarykey_checkVal" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="dis" id="dis"
											value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>" />
										
										<!-- 设置hidden，不同的床位分配方式sfbl、xh字段在结果集的不同位置 -->
										<%if("xydm".equals(GyglNewInit.CWFPDX)) {%>	
										<input type="hidden" name="sfbl" id="sfbl"
											value="<logic:iterate id="v" name="s" offset="10" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="xh" id="xh"
											value="<logic:iterate id="v" name="s" offset="8" length="1">${v }</logic:iterate>" />
										<%}else{ %>
										<input type="hidden" name="sfbl" id="sfbl"
											value="<logic:iterate id="v" name="s" offset="11" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="xh" id="xh"
											value="<logic:iterate id="v" name="s" offset="9" length="1">${v }</logic:iterate>" />
										<%} %>	
										
									</td>
									<%if("xydm".equals(GyglNewInit.CWFPDX)) {%>	
										<logic:iterate id="v" name="s" offset="2" length="6" >
											<td>${v }</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="8" length="1">
											<td><a class="name" href="#" onclick="zxsxxView('${v }')">${v }</a></td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="9">
											<td>${v }</td>
										</logic:iterate>
									<%}else{ %>
										<logic:iterate id="v" name="s" offset="2" length="7">
											<td>${v }</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="9" length="1">
										<td><a class="name" href="#" onclick="zxsxxView('${v }')">${v }</a></td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="10">
											<td>${v }</td>
										</logic:iterate>
										
									<%} %>	
									
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							int pageSize = (Integer)(request.getAttribute("pageSize"));
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewCwglForm"></jsp:include>
				<!--分页显示-->
			</div>
			
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>对调时间</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="60px">
									入住时间
								</th>
								<td>
									<input type="text" id="rzsj" name="rzsj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd')"/>
								</td>
							</tr>
							<tr>
								<th>
									备注
								</th>
								<td>
									<input type="text" id="bz" name="bz" style="width: 95%;height: 50px" />
								</td>
							</tr>
							<tr>
								<th>
									注意
								</th>
								<td>
									<div id="submit_bz" class="bz" style="color: red;"></div>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									
									<div class="btn">
										<button type="button" name="保存"  onclick="cwdd_submit()">
											保存 
										</button>
										<button type="button" name="取消"  onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>

		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}');
			</script>
		</logic:present>
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
