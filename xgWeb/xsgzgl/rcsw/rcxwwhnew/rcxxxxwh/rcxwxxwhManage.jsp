<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

		var gridSetting = {
			caption:"日常行为信息维护列表",
			pager:"pager",
			url:"rcsw_rcxwwhnew_rcxwxxwhgl.do?method=rcxwxxwhManage&type=query",
			colList:[
			   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
			   {label:'splc',name:'splc', index: 'splc',hidden:true},
			   {label:'学号',name:'xh', index: 'xh',width:'9%',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'性别',name:'xb', index: 'xb',width:'4%'},
			   {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
			   {label:'行为类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'12%'},
               {label:'行为大类',name:'dlxx', index: 'dlxx',width:'15%'},
			   {label:'行为大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',hidden:true},
			   {label:'行为小类',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'12%'},
			   {label:'发生时间',name:'fssj', index: 'fssj',width:'9%'},
			   {label:'申请评定分值',name:'fz', index: 'fz',width:'9%'},
			   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'9%'},
			   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
			   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
			   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true},
			   {label:'shzt',name:'shzt', index: 'shzt',hidden:true}
			],
			sortname: "rcxwjlsj",
		 	sortorder: "desc"
		}

		jQuery(function(){
			var xxdm = jQuery("#xxdm").val();
			
			//西安科技大学个性化
			if(xxdm == '10704'){
				gridSetting["caption"] = "综合测评信息维护列表";
				gridSetting["colList"] = [
				           			   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
				        			   {label:'splc',name:'splc', index: 'splc',hidden:true},
				        			   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
				        			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				        			   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				        			   {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
				        			   {label:'综合测评类别',name:'rcxwlbmc', index: 'rcxwlbmc',width:'8%'},
				        			   {label:'综合测评大类',name:'rcxwlbdlmc', index: 'rcxwlbdlmc',width:'8%'},
				        			   {label:'综合测评小类',name:'rcxwlbxlmc', index: 'rcxwlbxlmc',width:'8%'},
				        			   {label:'发生时间',name:'fssj', index: 'fssj',width:'15%'},
				        			   {label:'申请评定分值',name:'fz', index: 'fz',width:'12%'},
				        			   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
				        			   {label:'rcxwlbdm',name:'rcxwlbdm', index: 'rcxwlbdm',hidden:true},
				        			   {label:'rcxwlbdldm',name:'rcxwlbdldm', index: 'rcxwlbdldm',hidden:true},
				        			   {label:'rcxwlbxldm',name:'rcxwlbxldm', index: 'rcxwlbxldm',hidden:true},
				        			   {label:'shzt',name:'shzt', index: 'shzt',hidden:true}
				        			]
			}
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
			if(xxdm == "10504"){
				var userStatus = jQuery("#userStatus").val();
				if(userStatus == "stu"){
					jQuery("#btn_zj").hide();
					jQuery("#btn_xg").hide();
					jQuery("#btn_sc").hide();
				}else{
					jQuery("#btn_shuc").hide();
					jQuery("#btn_sr").hide();
				}
			}
		});
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		function add(){
			var url = "rcsw_rcxwwhnew_rcxwxxwhgl.do?method=addXwxx";
			var title = "增加日常行为信息";
			if(${xxdm=="12970"}){
				title = "增加素质学分信息";
			}
			//西安科技大学
			if(jQuery("#xxdm").val() == '10704'){
				title = "增加综合测评信息";
			}
			showDialog(title,950,450,url);
		}
		function update() {
			var rows = jQuery("#dataTable").getSeletRow();

			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要修改的记录！");
			} else {
				var shzt = rows[0]["shzt"];
				if(shzt=='0'||shzt=='3'){
					var url = 'rcsw_rcxwwhnew_rcxwxxwhgl.do?method=updateXwxx&id=' + rows[0]["id"]
		      		+ '&xh=' + rows[0]["xh"] +'&rcxwlbdldm=' + rows[0]["rcxwlbdldm"] 
		      		+ '&rcxwlbdlmc=' + rows[0]["rcxwlbdlmc"] +'&rcxwlbdlmc=' + rows[0]["rcxwlbmc"];
		      		var title = "修改日常行为信息";
		      		if(${xxdm=="12970"}){
						title = "修改素质学分信息";
					}
		      		if(jQuery("#xxdm").val() == '10704'){
						title = "修改综合测评信息";
					}
		      		showDialog(title, 850, 450, url);
				}else{
					showAlertDivLayer("只能修改未提交或者已退回的记录！");
					return false;
				}
			}
		}

		function del(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0){
				showAlertDivLayer("请选择您要删除的记录！");
			} else {
				var shzt = rows[0]["shzt"];
				
				if(shzt=='0'){
					showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
						jQuery.post("rcsw_rcxwwhnew_rcxwxxwhgl.do?method=delXwxx",{values:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}else{
					showAlertDivLayer("只能删除未提交的记录！");
					return false;
				}
			}
		}


		function xwxxView(id, xh) {
			var title = "查看日常行为信息";
			if(${xxdm=="12970"}){
				title = "查看素质学分信息";
			}
			if(jQuery("#xxdm").val() == '10704'){
				title = "查看综合测评信息";
			}
			showDialog(title, 720, 520, "rcsw_rcxwwhnew_rcxwxxwhgl.do?method=viewXwxx&id=" + id
					+ "&xh=" + xh);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='xwxxView(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}

		var DCCLBH = "rcsw_rcxwwhnew_rcxwxxwhgl.do";//dcclbh,导出功能编号

		//自定义导出 功能
		function exportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport(DCCLBH, rcxwxxwhExportData);
		}

		// 导出方法
		function rcxwxxwhExportData() {
			setSearchTj();//设置高级查询条件
			var url = "rcsw_rcxwwhnew_rcxwxxwhgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		function submitBusi(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length != 1) {
				showAlertDivLayer("请选择一条您要提交的记录！");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				var shzt = rows[0]["shzt"];
				var url = "";
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shzt']!='0' && rows[i]['shzt']!='3' ){
						showAlertDivLayer("请选择未提交或者已退回的记录！");
						return false;
					}
					if(rows[i]['shzt']!='3'){
						url = "rcsw_rcxwwhnew_rcxwxxwhgl.do?method=submitXwxx&returnflag=back";
					}else{
						url = "rcsw_rcxwwhnew_rcxwxxwhgl.do?method=submitXwxx";
					}
					
				}
				
				showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
								
					jQuery.post(url,
						{values:ids.toString(),
						 xh : rows[0]['xh'],  
						 rcxwlbdldm : rows[0]['rcxwlbdldm']
						},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}
		}

		function cancel(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("请选择您要撤销的记录！");
			} else if (ids.length >1 ) {
				showAlertDivLayer("请选择一条您要撤销的记录！");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shzt']!='5'){
						showAlertDivLayer("只有审核中的记录才能被撤销！");
						return false;
					}
				}
				showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
					jQuery.post("rcsw_rcxwwhnew_rcxwxxwhgl.do?method=cancelRcxwxx",
						{
						 values:ids.toString(),
						 splcid : rows[0]['splc'] 
						},function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
			}
			
		}

		function rcxwxxwhLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			var shzt = rows[0]["shzt"];
			if (ids.length != 1){
				showAlertDivLayer("请选择一条流程跟踪记录！");
			} else {	
				if ("0" == shzt){
					showAlertDivLayer("无相关流程信息！");
					return false;
				}
				showDialog("审批流程跟踪",600,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splc']);
			}
		}
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_rcxwwhnew_rcxwxxwhgl">
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_rcxwxxwh"/>
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">	
						<li id="btn_zj">
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" 
							>增加</a>
						</li>
						<li id="btn_xg">
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
							>修改</a>
						</li>
						<li id="btn_sc">
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
							>删除</a>
						</li>
						<li id="btn_shuc">
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li id="btn_sr">
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="rcxwxxwhLcinfo();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>	
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<logic:equal name="xxdm" value="12970">	
					<span>素质学分信息维护列表&nbsp;&nbsp; </span>
				</logic:equal>
				<logic:notEqual name="xxdm" value="12970">
					<logic:equal value="10704" name="xxdm">
						<span>综合测评信息维护列表&nbsp;&nbsp; </span>
					</logic:equal>
					<logic:notEqual value="10704" name="xxdm">
						<span>日常行为信息维护列表&nbsp;&nbsp; </span>
					</logic:notEqual>
				</logic:notEqual>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
