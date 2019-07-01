<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xsgzgl.gygl.comm.GyglNewInit"%>
<%@page import="common.Globals"%>
<%@page import="xgxt.action.Base"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('gyglnew_zsxxgl_zsxxgl.do');
		}
		
		function modi(url,h,w){
			if(curr_row != null){
				if(curr_row.getElementsByTagName('input')[2].value =="是"){
					alertError('保留床位不可入住');
					return false;					
				}else if(curr_row.getElementsByTagName('input')[3].value !=""){
					alertError('床位已入住，请先退宿学生，再进行入住！');
					return false;				
				}else{
					//showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
					showDialog("床位入住", h, w, url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
					return true;
				}
			}else{
				alertInfo('请选择要操作的数据行');
				return false;
			}
		}

		function view(url,h,w){
			showDialog("床位基本信息",h,w,url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
		}	

		function cwdd_submit(){
			if($("rzsj").value==""){
				alertInfo("请选择入住时间！");
				return false;
			}else{
				document.forms[0].action = 'gyglnew_zsxxgl.do?method=zsxxglManage&go=go&doType=cwdd';
				document.forms[0].submit();
			}
		}
		
		function cwdd(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var sfbl = document.getElementsByName("sfbl");
			var njxybj = document.getElementsByName("njxybj");
			var index=new Array();
			var flag = "";
			var num=0;
			var tr_td=new Array();
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					num++;
					if(sfbl[i].value=="是"){
						flag="选中记录中含有保留的床位，不可进行对调！";
					}
					index[index.length]=njxybj[i].value;
					tr_td[tr_td.length]=checkBoxArr[i].parentNode.parentNode.cells;
				}
			}
			if(num!=2){
				alertError("选中床位数不为2，请选择两张床位进行操作！");
			}else{
				var xh0 = jQuery(tr_td[0][5]).text().trim();
				var xh1 = jQuery(tr_td[1][5]).text().trim();
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
					var confirmValue="选中床位所属不同，保存将自动更新床位所属！";
					//判断床位所属年级、学院、班级是否一致
					if(index[0]==index[1]){
						confirmValue="";
					}
					
					$("submit_bz").innerHTML=confirmValue;
					var idList = "";        

					jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").each(function(){
						idList += jQuery(this).val() + ',';        
					});

					url = "gyglnew_cwgl.do?method=cwDd&idList="+idList;
					showDialog('床位对调', 580, 250, url);
					//url = "gyglnew_zsxxgl.do?method=zsxxCwdd&idList="+idList;
					//showDialog('', 400, 250, url);
					//tipsWindown("床位对调","id:tempDiv","400","200","true","","true","id");
				}else{
					alertError(flag);
				}		
			}			
		}

		//单击学号查看
		function zxsxxView(xh){
			
				var pkValue=xh;
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				showTopWin(url,850,640);
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
			//showTopWin(url,600,500);
			//refreshForm(url);
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
			//showTopWin('gyglnew_cwgl.do?method=cwglPlts',700,500);
			showDialog("退宿", 730, 365, 'gyglnew_cwgl.do?method=cwglPlts');
		}
		//批量离校
		function pllx(){
			showDialog("批量离校退宿", 760, 550, 'gyglnew_zsxxgl.do?method=plLx');
		}
		/**
		  * 导出床头卡
		  * @return
		  */
		 function gygl_exp_ctk(){
			 
			var url = "gyglnew_zsxxgl.do?method=expCtk";
		 	document.forms[0].action = url;
		 	document.forms[0].target = "_blank";
		 	document.forms[0].submit();
		 	document.forms[0].target = "_self";
			
		 }
		
		function zsxxglExportConfig() {
			customExport("gyglnew_zsxxgl_zsxxgl.do", zsxxglExportData);
		}
		//甘肃交通职业技术学院
		function zsxxglExportConfigForgsjt() {
			customExport("xg_xajt_gxhzsdc.do", zsxxglExportDatagsjt);
		}
		function gxexport(){
			setSearchTj();
			var map = getSuperSearch();
			if(map["searchTj"].indexOf("searchModel.search_tj_ld")==-1){
				return alertError("请选择具体楼栋!");
			}
			if(jQuery("#ld_ul").find(".selectedValue").length >1){
				return alertError("只能选择一个楼栋!");
			}
			document.forms[0].action = 'gyglnew_zsxxgl.do?method=geExport';
			document.forms[0].submit();
		}
			
		// 导出方法
		function zsxxglExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyglnew_zsxxgl.do?method=zsxxglExportData&dcclbh=" + "gyglnew_zsxxgl_zsxxgl.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		// 甘肃交通个性化导出方法
		function zsxxglExportDatagsjt() {
			setSearchTj();//设置高级查询条件
			var url = "gyglnew_zsxxgl.do?method=zsxxglExportDataForGsjt&dcclbh=" + "xg_xajt_gxhzsdc.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		// 新版查看弹出层
		function zxsxxView(xh) {
			showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
					+ "&xs");
		}

		
		function wjxxView(index){

			var xh = jQuery("#xh"+index).val();
			var n = jQuery("#n"+index).val();
			if(n == 0) {
				alertError("该生无违纪情况！");
				return false;
			}	
			showDialog("违纪详细信息",690,400,"gyglnew_zsxxgl.do?method=wjxx&xh="+xh);
		}	
		
		//修改备注
		function updateBz(){
			var pkValues = jQuery("input[name='primarykey_checkVal']:checked").map(function(){ 
				return jQuery(this).val();
			}).get(); 
			if(pkValues.length==0){
				alertError("请至少选择一条记录修改备注！");
			}else if(pkValues.length==1){
				showDialog("修改备注", 800, 400, 'gyglnew_zsxxgl.do?method=updateBz&pkValue='+pkValues[0]);
			}else{
				showDialog("批量修改备注", 600, 200, 'gyglnew_zsxxgl.do?method=updateBzBatch&pkValues='+pkValues);
			}
		}

		//重庆工商大学（寝室换人）
		function qshr(){
			var pkValues = jQuery("input[name='primarykey_checkVal']:checked");
			if(pkValues.length != 1){
				showAlertDivLayer("请选择一条记录");				
			}else{
				var checkbox = pkValues[0];
				var lddm = jQuery(checkbox).siblings("[name='lddm']").val();
				var qsh = jQuery(checkbox).siblings("[name='qsh']").val();
				var cwh = jQuery(checkbox).siblings("[name='cwh']").val();
				showDialog("请选择学生",800,600,'xsxx_xsgl.do?method=showStudentsForQshr&lddm='+lddm+'&qsh='+qsh+'&cwh='+cwh);
			}
		}
		
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_zsxxgl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="searchTjstr" value="<bean:write name="searchTjstr"/>"/>
			<input type="hidden" id="yrzcws" value="<bean:write name="num"/>"/>
			<input type="hidden" id="num" value="<bean:write name="num"/>"/>
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
					“入住”时系统会根据学生所属自动更新床位所属；“对调”仅可操作2个相同性别且至少其中1个已入住的床位；
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<logic:equal value="true" name="czkz">
							<li><a href="#" onclick="modi('gyglnew_cwgl.do?method=cwwhModi',800,450);return false;" class="btn_sh" > 入住 </a></li>
							<li><a href="#" class="btn_shbtg" onclick="plts();return false;">退宿</a></li>
							<li><a href="#" onclick="cwdd();return false;" class="btn_sx"> 床位对调 </a></li>
							<logic:equal value="true" name="pllxtx">
								<li><a href="#" class="btn_shbtg" onclick="pllx();return false;">批量离校退宿</a></li>
							</logic:equal>
							<li><a href="#" onclick="cwrzglImpAndChkData();return false;" class="btn_dr">导入数据</a></li>
							</logic:equal>
							<logic:equal value="false" name="czkz">
							<li><a href="#"  class="btn_sh" title="不在操作时间范围内" > <font color="gray">入住</font> </a></li>
							<li><a href="#" class="btn_shbtg"  title="不在操作时间范围内"><font color="gray">退宿</font></a></li>
							<li><a href="#"  class="btn_sx" title="不在操作时间范围内"> <font color="gray">床位对调</font> </a></li>
							</logic:equal>
						</logic:equal>
						
						<%if(Globals.XXDM_zjgmzyjsxy.equals(Base.xxdm)){ %>
							<logic:equal value="yes" name="writeAble">
							<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportDataZdy('${path_dc}');return false;">导出简表数据</a></li>
							<li><a href="#" onclick="setSearchTj();configureExportDataZdy('${path}');return false;" class="btn_dc">导出详表数据</a></li>
							</logic:equal>
						<%} else { %>
							
							<li><a href="#" onclick="zsxxglExportConfig();return false;" class="btn_dc">导出</a></li>
							<%--甘肃交通一行多床位个性化自定义导出【通用导出框架导出】--%>
							<logic:equal value="13519" name="xxdm">
								<li><a href="#" onclick="zsxxglExportConfigForgsjt();return false;" class="btn_dc">一行多床位导出</a></li>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportDataZdy('gyglnew_zsxxgl_zsxxgl.do');return false;">导出简表数据</a></li>			
						<%} %>
						<%if(Globals.XXDM_XZGYZYJSXY.equals(Base.xxdm)){ %>
							<logic:equal value="yes" name="writeAble">
							<li><a href="#" class="btn_dc" onclick="setSearchTj();gygl_exp_ctk();return false;">导出床头卡</a></li>
							</logic:equal>
						<%} %>
						<li><a href="#" onclick="gxexport();return false;" class="btn_dc">寝室名单导出</a></li>
						<li><a href="javascript:void(0)" onclick="updateBz();return false;" class="btn_xg">修改备注</a></li>
						<!-- 重庆工商大学 -->
							<logic:equal value="11799" name="xxdm">
								<li><a href="#" class="btn_sx" onclick="qshr();return false;">寝室换人</a></li>
							</logic:equal>
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
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand" 
									ondblclick="view('gyglnew_cwgl.do?method=cwwhView',800,500);" 
									title="所属年级:<logic:iterate id="v" name="s" offset="11" length="1">${v };</logic:iterate>所属<bean:message key="lable.xb" />:<logic:iterate id="v" name="s" offset="14" length="1">${v }</logic:iterate><%if("bjdm".equals(GyglNewInit.CWFPDX)){%>;所属班级:<logic:iterate id="v" name="s" offset="15" length="1">${v }</logic:iterate><%} %><%else if("zydm".equals(GyglNewInit.CWFPDX)){%>;所属专业:<logic:iterate id="v" name="s" offset="16" length="1">${v }</logic:iterate><%} %>">
									<td>								
										<input type="checkbox" name="primarykey_checkVal" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="dis" id="dis"
											value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="sfbl" id="sfbl"
											value="<logic:iterate id="v" name="s" offset="10" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="xh" id="xh${index}"
											value="<logic:iterate id="v" name="s" offset="6" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="n" id="n${index}"
											value="<logic:iterate id="v" name="s" offset="19" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="njxybj" id="njxybj"
											value="<logic:iterate id="v" name="s" offset="11" length="3">${v }</logic:iterate>"
										/>
										<input type="hidden" name="lddm" value="<logic:iterate id="v" name="s" offset="19" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="qsh" value="<logic:iterate id="v" name="s" offset="3" length="1">${v }</logic:iterate>" />
										<input type="hidden" name="cwh" value="<logic:iterate id="v" name="s" offset="4" length="1">${v }</logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="2" length="6" indexId="indexId">
										<td>
											<logic:equal name="indexId" value="6">
												<a class="name" href='javascript:void(0);' onclick='zxsxxView("${v }");return false;' >${v }</a>
											</logic:equal>
											<logic:notEqual name="indexId" value="6">
												${v }
											</logic:notEqual>
										</td>
									</logic:iterate>
									<logic:equal name="xxdm" value="12216">
										<logic:iterate id="v" name="s" offset="20" length="1" >
											<td>${v }</td>
										</logic:iterate>
									</logic:equal>
									
									<logic:iterate id="v" name="s" offset="8" length="3" indexId="indexId">
										<td>${v }</td>
									</logic:iterate>
									
										<logic:iterate id="v" name="s" offset="17" length="1">
											<td title="<logic:iterate id="vv" name="s" offset="18" length="1">${vv }</logic:iterate>">${v }</td>
										</logic:iterate>
										<logic:equal name="xxdm" value="13033">
											<logic:iterate id="v" name="s" offset="18" length="1">
												<td title="<logic:iterate id="v" name="s" offset="19" length="1">${v }</logic:iterate>">
													<a class="name" href='javascript:void(0);' onclick='wjxxView(${index});return false;' >${v }</a>
												</td>
											</logic:iterate>
										</logic:equal>
										<logic:equal name="xxdm" value="10868">
											<logic:iterate id="v" name="s" offset="19" length="3">
												<td>
													${v }
												</td>
											</logic:iterate>
										</logic:equal>
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewZsxxglForm"></jsp:include>
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
