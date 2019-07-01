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
						caption:"咨询版块列表",
						pager:"pager",
						url:"rcsw_zxzx_zxbkszgl.do?method=zxbkszManage&type=query",
						colList:[
							{label:'key',name:'bkid', index: 'bkid',key:true ,hidden:true},
						   {label:'版块名称',name:'bkmc', index: 'bkmc',width:'30%'},
						   {label:'启用状态',name:'sfqymc', index: 'sfqymc',width:'10%'},
						   {label:'显示顺序',name:'xssx', index: 'xssx',width:'10%'}
						],
						sortname: "xssx",
					 	sortorder: "asc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function zxbkszView(bkid) {
				showDialog("查看咨询版块", 470,180, "rcsw_zxzx_zxbkszgl.do?method=viewZxbksz&bkid=" + bkid);
			}
			function bkmcLink(cellValue, rowObject) {
				return "<a href='javascript:void(0);' class='name' onclick='zxbkszView(\"" + rowObject["bkid"] + "\");'>" + cellValue + "</a>";
			}
			function add(){
				var url = "rcsw_zxzx_zxbkszgl.do?method=addZxbksz";
				var title = "增加咨询版块";
				showDialog(title,470,180,url);
			}
			function update() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					var url = 'rcsw_zxzx_zxbkszgl.do?method=updateZxbksz&bkid=' + rows[0]["bkid"];
					var title = "修改咨询版块";
					showDialog(title,470,180,url);
				}
			}
			function updateSfqy(sfqy){
				var msg = "启用";
				if(sfqy == '0'){
					msg = "停用";
				}
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("请选择您要"+msg+"的记录！");
				} else {
					showConfirmDivLayer("您确定要"+msg+"选择的记录吗？",{"okFun":function(){
							jQuery.post("rcsw_zxzx_zxbkszgl.do?method=sfqyZxbksz",{values:ids.toString(),sfqy:sfqy},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
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
						jQuery.post("rcsw_zxzx_zxbkszgl.do?method=delZxbksz", { values : ids.toString() },
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
		<html:form action="/rcsw_zxzx_zxbkszgl">
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
							<li><a href="javascript:void(0);" onclick="updateSfqy('1');" class="btn_shtg">启用</a></li>						
							<li><a href="javascript:void(0);" onclick="updateSfqy('0');" class="btn_shbtg">停用</a></li>
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
				<span>咨询版块列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
