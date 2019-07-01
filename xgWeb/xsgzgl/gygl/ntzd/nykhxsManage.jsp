<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"月考核系数列表",
					pager:"pager",
					url:"gygl_ntzd.do?method=nykhxsManage&type=query",
					colList:[
					   {label:'年月',name:'ny', index: 'ny',width:'20%',key:true},
					   {label:'考核系数',name:'khxs', index: 'khxs',width:'40%'},
					   {label:'当月宿舍考核分值',name:'dysskhfz', index: 'dysskhfz',width:'40%'}
					],
					sortname: "ny",
				 	sortorder: "desc"
			}
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function add(){
				showDialog("增加月考核系数",400,195,"gygl_ntzd.do?method=nykhxsAdd");
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("请选择一条您要修改的记录！");
				} else {
					showDialog("修改月考核系数",400,195,'gygl_ntzd.do?method=nykhxsUpdate&ny='+rows[0]["ny"]);
				}
			}
			function deletes(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					alertInfo("请选择您要删除的记录！");
				} else {
					confirmInfo("您确定要删除"+ids.length +"条记录吗?",function(ty){
						if(ty=="ok"){
							jQuery.post("gygl_ntzd.do?method=nykhxsDelete",{values:ids.toString()},function(data){
								alertInfo(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
						}
					});
				}
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				//为button注册事件
				jQuery("#btn_zj").click(add);
				jQuery("#btn_xg").click(update);
				jQuery("#btn_sc").click(deletes);
				//jQuery("#search_go").click(query);
				jQuery("#btn_cz").click(function(){searchReset()});
			});
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_ntzd.do?method=nykhxsManage">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li><a href="javascript:void(0);" id="btn_zj" class="btn_zj">增加</a></li>
							<li><a href="javascript:void(0);" id="btn_xg" class="btn_xg">修改</a></li>
							<li><a href="javascript:void(0);" id="btn_sc" class="btn_sc">删除</a></li>	
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>	
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>月考核系数列表</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
