<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/dxsylbx/ylbxsq/js/ylbxsqManage.js"></script>
		<script type="text/javascript">

		
		function submitBusi(){
			var ids = jQuery("#dataTable").getSeletIds();
			if(ids.length == 0){
				showAlertDivLayer("请选择您要提交的记录！");
			}else if (ids.length >1 ){
				showAlertDivLayer("请选择一条您要提交的记录！");
			}else{
				var rows = jQuery("#dataTable").getSeletRow();
				var isopen = jQuery("#isopen").val();
				if('3'!=rows[0]["shzt"]&&"true"!=isopen){
					showAlertDivLayer("当前未开放申请，请联系管理员！");
					return false;
				}
				var url = "";
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shztmc']!='未提交' && rows[i]['shztmc']!='退回' ){
						showAlertDivLayer("请选择未提交或者退回的记录！");
						return false;
					}
					if(rows[i]['shztmc']!='退回'){
						url = "rcsw_dxsylbx_ylbxsqgl.do?method=submitYlbxsq";
					}else{
						url = "rcsw_dxsylbx_ylbxsqgl.do?method=submitYlbxsq";
					}
					
				}

				var isopen = jQuery("#isopen").val();
				if(isopen==null||isopen==''){
					showAlertDivLayer('基础设置未初始化，请联系管理员！');
					return false;
				}
				if ("false" == isopen && '3'!=rows[0]["shzt"]){
					showAlertDivLayer("当前未开放申请，请联系管理员！");
					return false;
				}

				showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
					jQuery.post(url,
						{values:ids.toString(),
						 xh : rows[0]['xh'],
						 splc : rows[0]["splc"],
						 shzt : rows[0]["shzt"]
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
						showAlertDivLayer("只有审核中的记录才能被撤销！");
						return false;
					}
				}
				showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
					jQuery.post("rcsw_dxsylbx_ylbxsqgl.do?method=cancelYlbxsq",
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

		function ylbxLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			var shzt = rows[0]["shztmc"];
			if (ids.length != 1){
				showAlertDivLayer("请选择一条流程跟踪记录！");
			} else {
				if ("未提交" == shzt){
					showAlertDivLayer("该记录为未提交状态，请先提交！");
					return false;
				}	
				showDialog("大学生医疗保险审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['ylsqid']+"&splc="+rows[0]['splc']);
			}
		}

		function getWord(){
			 var rows = jQuery("#dataTable").getSeletRow();
			 if (rows.length == 0){
				showAlertDivLayer("请选择一条记录！");
			 } else if (rows.length > 1){
				var url="rcsw_dxsylbx_ylbxsqgl.do?method=getDxsylbxZip";
				var ids = jQuery("#dataTable").getSeletIds();
				var url= url+"&value="+ids;
				window.open(url);
			 } else {
				var url="rcsw_dxsylbx_ylbxsqgl.do?method=getDxsylbxWord";
				var url= url+"&ylsqid="+rows[0]["ylsqid"]+"&xh="+rows[0]["xh"];
				window.open(url);
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
		<html:form action="/rcsw_dxsylbx_ylbxjggl">
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_ylbx_ylbxsqb"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" >申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="ylbxLcinfo();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>	
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:equal>
						
						<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a></li>		
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>医疗保险申请信息维护列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
