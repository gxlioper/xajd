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
						caption:"信息列表",
						pager:"pager",
						url:"zzyrxmglfdxxgl.do?method=fdxxglManage&type=query",
						colList:[
							{label:'key',name:'fdxxid', index: 'fdxxid',key:true ,hidden:true},
						 	{label:'助教学号',name:'fdrxh', index: 'fdrxh',width:'10%'},
							{label:'助教姓名',name:'fdrxm', index: 'fdrxm',width:'10%'},
							{label:'学员学号',name:'bfdrxh', index: 'bfdrxh',width:'10%'},
							{label:'学员姓名',name:'bfdrxm', index: 'bfdrxm',width:'10%'},
						 	{label:'辅导科目',name:'fdkm', index: 'fdkm',width:'14%'},
						 	{label:'参加时间',name:'tysj', index: 'tysj',width:'19%'},
						 	{label:'学生评价',name:'xspjjg', index: 'xspjjg',width:'10%'},
						 	{label:'老师辅导评价',name:'lspjjg', index: 'lspjjg',width:'10%'}
						],
						sortname: "tysj",
					 	sortorder: "asc"
				};
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);			
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function addPj(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_pj").val());
				} else {
					var xspjjg = rows[0]["xspjjg"];
					if(xspjjg == "" || xspjjg == null){
						showAlertDivLayer("暂时不能评价！");
						return false;
					}
					var url = 'zzyrxmglfdxxgl.do?method=addFdxxglpj&fdxxid=' + rows[0]["fdxxid"];
					var title = "评价";
					showDialog(title,450,155,url);
				}
			}
			function view() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer(jQuery("#lable_one_ck").val());
				} else {
					var url = 'zzyrxmglfdxxgl.do?method=viewFdxxgl&fdxxid=' + rows[0]["fdxxid"];
					var title = "查看";
					showDialog(title,750,465,url);
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
		<html:form action="/zzyrxmglfdxxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" onclick="addPj();return false;" class="btn_zj">评价</a>
							</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="view();return false;" class="btn_ck">查看明细</a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
