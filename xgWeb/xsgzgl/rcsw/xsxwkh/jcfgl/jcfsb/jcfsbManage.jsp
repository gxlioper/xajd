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
			caption:"奖惩分申报列表",
			pager:"pager",
			url:"rcsw_rcxwwhnew_rcxwxxwhgl.do?method=rcxwxxwhManage&type=query",
			colList:[
			   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
			   {label:'splc',name:'splc', index: 'splc',hidden:true},
			   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
			   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
			   {label:'学院',name:'xymc', index: 'xymc',width:'15%'},
			   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'13%'},
			   {label:'项目类型',name:'xmlx', index: 'xmlx',width:'8%'},
			   {label:'分值',name:'fz', index: 'fz',width:'12%'},
			   {label:'申报时间',name:'sbsj', index: 'sbsj',width:'15%'},
			   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
			   {label:'shzt',name:'shzt', index: 'shzt',hidden:true}
			],
			sortname: "sbsj",
		 	sortorder: "desc"
		}

		jQuery(function(){
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
			var xxdm = jQuery("#xxdm").val();
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
			var title = "增加奖惩分";
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
		      		var title = "修改奖惩分";
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
			showDialog("查看奖惩分", 720, 520, "rcsw_rcxwwhnew_rcxwxxwhgl.do?method=viewXwxx&id=" + id
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
				<span>奖惩分申报列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
