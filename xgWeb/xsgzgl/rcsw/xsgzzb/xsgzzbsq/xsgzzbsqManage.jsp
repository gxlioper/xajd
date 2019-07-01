<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
					caption:"周报列表",
					pager:"pager",
					url:"rcsw_xsgzzb_xsgzzbsqgl.do?method=xsgzzbsqManage&type=query",
					colList:[      
					   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
					   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
					   {label:'学年',name:'xn', index: 'xn',width:'10%'},
					   {label:'学期',name:'xqmc', index: 'xqmc',width:'7%'},
					   {label:'周次',name:'zcmc', index: 'zcmc',width:'7%'},
					   {label:'周次起止日期',name:'zcksjsrq', index: 'zcksjsrq',width:'21%'},
					   {label:jQuery("#xymc").val(),name:'bmdmmc', index: 'bmdmmc',width:'17%',formatter:bmdmmcLink},
					   {label:'填写时间',name:'lrsj', index: 'lrsj',width:'18%'},
					   {label:'填写人',name:'lrrxm', index: 'lrrxm',width:'10%'},
					   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'10%'},
					   {label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
					   {label:'填写人用户名',name:'lrr', index: 'lrr',hidden:true}
					],
					sortname: "lrsj",
				 	sortorder: "desc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);

			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_null_isopen").show();
				return false;
			}
			if ("false" == isopen){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_false_isopen").show();
				return false;
			}
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function add(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('<bean:message key="lable.jcszwcsh" />');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer('<bean:message key="lable.dqwkfsq" />');
				return false;
			}
			if("${zcListSize}" == "0"){
				showAlertDivLayer("学期周数未初始化，请联系管理员！");
				return false;
			}
			var url = "rcsw_xsgzzb_xsgzzbsqgl.do?method=addXsgzzbsq";
			var title = "新增周报";
			showDialog(title,790,452,url);
		}

		function update() {
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('<bean:message key="lable.jcszwcsh" />');
				return false;
			}
			if("${zcListSize}" == "0"){
				showAlertDivLayer("学期周数未初始化，请联系管理员！");
				return false;
			}
			var rows = jQuery("#dataTable").getSeletRow();

			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要修改的记录！");
			} else {
				var shzt = rows[0]["shzt"];
				if ("0" != shzt&&"3" != shzt) {
					showAlertDivLayer("只有未提交和已退回的记录才能修改！");
					return false;
				}
				var userName = jQuery("#userName").val();
				if (userName != rows[0]["lrr"]) {
					showAlertDivLayer("不能修改其他用户填写的周报！");
					return false;
				}
				var url = 'rcsw_xsgzzb_xsgzzbsqgl.do?method=updateXsgzzbsq&sqid=' + rows[0]["sqid"];
				var title = "修改周报";
				showDialog(title,790,445,url);
			}
		}

		//删除
		function del() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0) {
				showAlertDivLayer("请选择您要删除的记录！");
			} else {
				var userName = jQuery("#userName").val();
				for (var i = 0; i < ids.length; i++) {
					if (rows[i]["shzt"] != "0" && rows[i]["shzt"] != "3") {
						showAlertDivLayer("只能删除未提交或者已退回的记录！");
						return false;
					}
					if (userName != rows[i]["lrr"]) {
						showAlertDivLayer("不能删除其他用户填写的周报！");
						return false;
					}
				}
				showConfirmDivLayer("您确定要删除选择的记录吗？", {
					"okFun" : function() {
						jQuery.post("rcsw_xsgzzb_xsgzzbsqgl.do?method=delXsgzzbsq", { values : ids.toString() },
								function(data) {
									var mes = "成功删除了<font color='green'>&nbsp;" + data["num"] + "&nbsp;</font>条数据";
									showAlertDivLayer(mes);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
					}
				});
			}
		}

		function viewXsgzzbsq(sqid) {
			showDialog("周报查看", 700, 445, "rcsw_xsgzzb_xsgzzbsqgl.do?method=viewXsgzzbsq&sqid=" + sqid);
		}

		function bmdmmcLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='viewXsgzzbsq(\""
					+ rowObject["sqid"] + "\");'>" + cellValue
					+ "</a>";
		}

		function submitBusi(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('<bean:message key="lable.jcszwcsh" />');
				return false;
			}
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length != 1){
				if ("false" == isopen){
					showAlertDivLayer('<bean:message key="lable.dqwkfsq" />');
					return false;
				}
				showAlertDivLayer("请选择一条您要提交的记录！");
			}else{
				var rows = jQuery("#dataTable").getSeletRow();
				if ('3'!=rows[0]["shzt"] && "false" == isopen){
					showAlertDivLayer('<bean:message key="lable.dqwkfsq" />');
					return false;
				}
				var url = "rcsw_xsgzzb_xsgzzbsqgl.do?method=submitXsgzzbsq";
				if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
					showAlertDivLayer("请选择未提交或者已退回的记录！");
					return false;
				}
				var userName = jQuery("#userName").val();
				if (userName != rows[0]["lrr"]) {
					showAlertDivLayer("不能提交其他用户填写的周报！");
					return false;
				}
				showConfirmDivLayer("您确定要提交选择的记录吗？",{"okFun":function(){
					jQuery.post(url,
						{values:ids.toString(),
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
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('<bean:message key="lable.jcszwcsh" />');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer('<bean:message key="lable.dqwkfsq" />');
				return false;
			}
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("请选择您要撤销的记录！");
			} else if (ids.length >1 ) {
				showAlertDivLayer("请选择一条您要撤销的记录！");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				var userName = jQuery("#userName").val();
				for(var i=0;i<ids.length;i++){
					if (rows[i]['shzt'] != '5') {
						showAlertDivLayer("只有审核中的记录才能被撤销！");
						return false;
					}
					if (userName != rows[i]["lrr"]) {
						showAlertDivLayer("不能撤销其他用户填写的周报！");
						return false;
					}
				}
				showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
					jQuery.post("rcsw_xsgzzb_xsgzzbsqgl.do?method=cancelXsgzzbsq",
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

		function xsgzzbLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length != 1){
				showAlertDivLayer("请选择一条流程跟踪记录！");
			} else {
				var shzt = rows[0]["shzt"];
				if ("0" == shzt) {
					showAlertDivLayer('<bean:message key="lable.wxglcxx" />');
					return false;
				}
				showDialog("周报审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
			}
		}

		var DCCLBH = "rcsw_xsgzzb_xsgzzbsq.do";//dcclbh,导出功能编号

		//自定义导出 功能
		function exportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport(DCCLBH, xsgzzbsqExportData);
		}

		// 导出方法
		function xsgzzbsqExportData() {
			setSearchTj();//设置高级查询条件
			var url = "rcsw_xsgzzb_xsgzzbsqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
		</script>
	</head>

	<body>
		<html:form action="/rcsw_xsgzzb_xsgzzbjggl">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
			</div>
			<div class="prompt" id="prompt_isopen" style="display:none;">
				<h3>
					<span>提示：</span>
				</h3>
				<p id="prompt_null_isopen" style="display:none;">
					<bean:message key="lable.jcszwcsh_prompt" />
				</p>
				<p id="prompt_false_isopen" style="display:none;">
					<bean:message key="lable.dqwkfsq_prompt" />
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_xsgzzb_xsgzzbsqb"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >填写</a>
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
							<a href="javascript:void(0);" onclick="xsgzzbLcinfo();return false;" title="选中一条记录，点击该按钮可以查看审核流程。" class="btn_cs">流程跟踪</a>
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
				<span>周报列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
