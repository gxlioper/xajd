<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwwh/rcxxxxwh/js/rcxwxxwhManage.js"></script>
		<script type="text/javascript">
		function submitBusi(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length != 1) {
				showAlertDivLayer("请选择一条您要提交的记录！");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				var shzt = rows[0]["shzt"];
				var url = "";
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shztmc']!='未提交' && rows[i]['shztmc']!='退回' ){
						showAlertDivLayer("请选择未提交或者退回的记录！");
						return false;
					}
					if(rows[i]['shztmc']!='退回'){
						url = "rcsw_rcxwwh_rcxwxxwhgl.do?method=submitXwxx&returnflag=back";
					}else{
						url = "rcsw_rcxwwh_rcxwxxwhgl.do?method=submitXwxx";
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
					if(rows[i]['shztmc']!='审核中'){
						showAlertDivLayer("只能撤销已提交未审核的记录！");
						return false;
					}
				}
				showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
					jQuery.post("rcsw_rcxwwh_rcxwxxwhgl.do?method=cancelRcxwxx",
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
			var shzt = rows[0]["shztmc"];
			if (ids.length != 1){
				showAlertDivLayer("请选择一条流程跟踪记录！");
			} else {	
				if ("未提交" == shzt){
					showAlertDivLayer("该记录为未提交状态，请先提交！");
					return false;
				}else if("无需审核" == shzt){
					showAlertDivLayer("该记录为无需审核状态，不需要走审核流！");
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
		<html:form action="/rcsw_rcxwwh_rcxwxxwhgl">
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
							<a href="javascript:void(0);" onclick="submitPl();return false;" class="btn_shuc">提交</a>
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
						<li><a href="#" class="btn_dr" onclick="importXX();return false;">导入</a></li>		
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
				<logic:equal name="xxdm" value="13815">	
					<span>素质学分信息维护列表&nbsp;&nbsp; </span>
				</logic:equal>
				<logic:notEqual name="xxdm" value="13815">
					<logic:notEqual name="xxdm" value="13431">
						<span>日常行为信息维护列表&nbsp;&nbsp; </span>
					</logic:notEqual>
				</logic:notEqual>
				<logic:equal name="xxdm" value="13431">
					<span>加分申请信息维护列表&nbsp;&nbsp; </span>
				</logic:equal>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
