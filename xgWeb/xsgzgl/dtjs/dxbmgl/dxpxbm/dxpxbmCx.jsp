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
				caption:"培训信息结果列表",
				pager:"pager",
				url:"dtjs_dxbmgl_dxpxbmCx.do?type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',key:true,hidden:true},
				   {label:'sfbm',name:'sfbm', index: 'sfbm',hidden:true},
				   {label:'培训期次',name:'pxqc', index: 'pxqc',formatter:dcmcLink},
				   {label:'培训时间',name:'pxsj', index: 'xm'},
				   {label:'报名开始时间',name:'bmkssj', index: 'bmkssj'},
				   {label:'报名结束时间',name:'bmjssj', index: 'bmjssj'},
				   {label:'发布人',name:'fbrxm', index: 'fbrxm'},
				   {label:'联系电话',name:'lxdh', index: 'lxdh'},
				   {label:'是否已报名',name:'sfbmmc', index: 'sfbmmc'},
				   {label:'审核状态',name:'shztmc', index: 'shztmc'}
				],
				sortname: "sfbm,pxsj",
			 	sortorder: "asc"
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs() {
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function reload(){
				jQuery("#dataTable").reloadGrid();
			}
			function dcmcLink(cellValue, rowObject) {
				var id = rowObject["id"];
				var pxqc = rowObject["pxqc"];
				return "<a href='javascript:void(0);' onclick=\"ckxx('"+id+"')\" class='name'>"+pxqc+"</a>";
			}
			function ckxx(id) {
				var url = "dtjs_dxbmgl_dxpxbmCk.do?id=" + id;
				var title = "查看培训信息";
				showDialog(title, 700, 250, url);
			}
			function add(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer("请选择一条您要修改的记录！");
				}else if (rows[0]["sfbm"] == '1') {
					showAlertDivLayer("已经报名的项目不需要重复报名！");
				} else {
					showConfirmDivLayer("您确定要报名吗？", {
						"okFun" : function() {
						jQuery.post("dtjs_dxbmgl_dxpxbmBm.do", {pxid : rows[0]["id"]}, function(data) {
							if(data){
								showAlertDivLayer("报名成功!");
							}else{
								showAlertDivLayer("报名失败!");
							}
							jQuery("#dataTable").reloadGrid();
						}, 'json');
					}
					});
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
	<html:form action="dxbmgl_dxpxbm.do" >
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" id="search_go" onclick="reload()" />
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li>
						<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">报名</a>
					</li>
				</ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>培训信息结果列表</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
