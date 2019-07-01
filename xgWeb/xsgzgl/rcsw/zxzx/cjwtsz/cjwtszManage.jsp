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
						caption:"常见问题列表",
						pager:"pager",
						url:"rcsw_zxzx_cjwtszgl.do?method=cjwtszManage&type=query",
						colList:[
							{label:'key',name:'zxid', index: 'zxid',key:true ,hidden:true},
						   {label:'咨询版块',name:'bkmc', index: 'bkmc',width:'15%'},
						   {label:'咨询主题',name:'zxzt', index: 'zxzt',width:'75%',formatter:zxztLink},
						   {label:'是否置顶',name:'sfzdmc', index: 'sfzdmc',width:'10%'}
						],
						sortname: "sfzd desc,zxsj",
					 	sortorder: "desc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function cjwtszView(zxid) {
				showDialog("查看常见问题", 750,300, "rcsw_zxzx_cjwtszgl.do?method=viewCjwtsz&zxid=" + zxid);
			}
			function zxztLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='cjwtszView(\"" + rowObject["zxid"] + "\");'>" + cellValue + "</a>";
			}
			function add(){
				var url = "rcsw_zxzx_cjwtszgl.do?method=addCjwtsz";
				var title = "增加常见问题";
				showDialog(title,750,400,url);
			}
			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var url = 'rcsw_zxzx_cjwtszgl.do?method=updateCjwtsz&zxid=' + rows[0]["zxid"];
					var title = "修改常见问题";
					showDialog(title,750,400,url);
				}
			}
			function del() {
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0) {
					showAlertDivLayer(jQuery("#lable_some_sc").val());
				} else {
					showConfirmDivLayer(jQuery("#lable_confirm_sc").val(), {
						"okFun" : function() {
						jQuery.post("rcsw_zxzx_cjwtszgl.do?method=delCjwtsz", { values : ids.toString() },
								function(data) {
									showAlertDivLayer(data["message"]);
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
		<html:form action="/rcsw_zxzx_cjwtszgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
							<li>
								<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
							</li>
					</ul>
				</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>常见问题列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
