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
					caption:"医疗保险申请列表",
					pager:"pager",
					url:"rcsw_ylbx_ylbxsqgl.do?method=ylbxsqManage&type=query",
					colList:[      
					   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
					   {label:'审批流程',name:'splc', index: 'splc',hidden:true},
					   <logic:notEqual name="xxdm" value="14073">
					   {label:'学年',name:'xn', index: 'xn',width:'10%'},
					   </logic:notEqual>
					   <logic:equal name="xxdm" value="14073">
					   {label:'年度',name:'zd5', index: 'zd5',width:'10%'},
					   </logic:equal>
					   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
					   {label:'年级',name:'nj', index: 'nj',width:'6%'},
					   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'12%'},
					   {label:'专业',name:'zymc', index: 'zymc',width:'12%'},
					   {label:'班级',name:'bjmc', index: 'bjdm',width:'12%'},
					   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'17%'},
					   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'10%'},
					   {label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true}
					],
					sortname: "sqsj",
				 	sortorder: "desc"
				};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function add(){
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('基础设置未初始化，请联系管理员！');
				return false;
			}
			if ("false" == isopen){
				showAlertDivLayer("当前未开放申请，请联系管理员！");
				return false;
			}
			var url = "rcsw_ylbx_ylbxsqgl.do?method=addYlbxsq";
			var title = "新增"+jQuery("#gnmkmc").val();
			showDialog(title,790,476,url);
		}

		function update() {
			var isopen = jQuery("#isopen").val();
			if(isopen==null||isopen==''){
				showAlertDivLayer('基础设置未初始化，请联系管理员！');
				return false;
			}
//			if ("false" == isopen){
//				showAlertDivLayer("当前未开放申请，请联系管理员！");
//				return false;
//			}
			var rows = jQuery("#dataTable").getSeletRow();

			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要修改的记录！");
			} else {
				var shzt = rows[0]["shzt"];
				if ("0" != shzt&&"3" != shzt) {
					showAlertDivLayer("只有未提交和已退回的记录才能修改！");
					return false;
				}
				var url = 'rcsw_ylbx_ylbxsqgl.do?method=updateYlbxsq&sqid=' + rows[0]["sqid"] + '&xh=' + rows[0]["xh"];
				var title = "修改"+jQuery("#gnmkmc").val();
				showDialog(title,790,470,url);
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
				}
				showConfirmDivLayer("您确定要删除选择的记录吗？", {
					"okFun" : function() {
						jQuery.post("rcsw_ylbx_ylbxsqgl.do?method=delYlbxsq", { values : ids.toString() },
								function(data) {
									var mes = "成功删除了<font color='green'>&nbsp;" + data["num"] + "&nbsp;</font>条数据";
									showAlertDivLayer(mes);
									jQuery("#dataTable").reloadGrid();
								}, 'json');
					}
				});
			}
		}

		function viewYlbxsq(sqid, xh) {
			showDialog(jQuery("#gnmkmc").val()+"查看", 700, 495, "rcsw_ylbx_ylbxsqgl.do?method=viewYlbxsq&sqid=" + sqid + '&xh=' + xh);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='viewYlbxsq(\"" + rowObject["sqid"] + "\",\"" + rowObject["xh"] + "\");'>" + cellValue
					+ "</a>";
		}

		function submitBusi(){
			var ids = jQuery("#dataTable").getSeletIds();
			if(ids.length == 0){
				showAlertDivLayer("请选择您要提交的记录！");
			}else if (ids.length >1 ){
				showAlertDivLayer("请选择一条您要提交的记录！");
			}else{
				var rows = jQuery("#dataTable").getSeletRow();
				var isopen = jQuery("#isopen").val();
				if(isopen==null||isopen==''){
					showAlertDivLayer('基础设置未初始化，请联系管理员！');
					return false;
				}
				if('3'!=rows[0]["shzt"]&&"true"!=isopen){
					showAlertDivLayer("当前未开放申请，请联系管理员！");
					return false;
				}
				var url = "rcsw_ylbx_ylbxsqgl.do?method=submitYlbxsq";
				if (rows[0]["shzt"] != "0" && rows[0]["shzt"] != "3") {
					showAlertDivLayer("请选择未提交或者已退回的记录！");
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
				}
				showConfirmDivLayer("您确定要撤销选择的记录吗？",{"okFun":function(){
					jQuery.post("rcsw_ylbx_ylbxsqgl.do?method=cancelYlbxsq",
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
			if (ids.length != 1){
				showAlertDivLayer("请选择一条流程跟踪记录！");
			} else {
				var shzt = rows[0]["shzt"];
				if ("0" == shzt) {
					showAlertDivLayer("该记录为未提交状态，请先提交！");
					return false;
				}
				showDialog("审批流程跟踪",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['sqid']+"&splc="+rows[0]['splc']);
			}
		}

		var DCCLBH = "rcsw_ylbx_ylbxsq.do";//dcclbh,导出功能编号

		//自定义导出 功能
		function exportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport(DCCLBH, ylbxsqExportData);
		}

		// 导出方法
		function ylbxsqExportData() {
			setSearchTj();//设置高级查询条件
			var url = "rcsw_ylbx_ylbxsqgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		function exportZp(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("请选择您要打印照片的学生！");
			} else{
				var url="rcsw_ylbx_ylbxsqgl.do?method=getZpZip&value="+ids;
				window.open(url);
			}
		}
		
		</script>
	</head>

	<body>
		<html:form action="/rcsw_ylbx_ylbxjggl">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
			<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
			<input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >申请</a>
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
							<a href="javascript:void(0);" onclick="ylbxLcinfo();return false;" title="选中一条记录，点击该按钮可以查看审核流程。" class="btn_cs">流程跟踪</a>
						</li>	
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
							<logic:equal name="xxdm" value="126880">	
								<li><a href="#" class="btn_dc" onclick="exportZp();return false;">学生照片导出</a></li>	
							</logic:equal>
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
				<span>${gnmkmc}列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
