<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"通知公告列表",
					pager:"pager",
					radioselect:false,
					rowNum:20,
					url:"jjgl_tzgggl.do?method=queryTzggList&type=0",
					colList:[
					   {name:'sid', index: 'sid',key:true,hidden:true},
					   {label:'标题',name:'title', index: 'title'},
					   {label:'发布时间',name:'publishdate', index: 'publishdate'},
					   {label:'发布人',name:'yhm', index: 'yhm'},
					   {name:'priority', index: 'priority',hidden:true},
					   {label:'优先级',name:'prioritymc', index: 'prioritymc'},
					   {label:'发布状态',name:'sffbmc', index: 'sffbmc'},
					   {name:'iscancel', index: 'iscancel',hidden:true}
					],
					sortname: "publishdate",
				 	sortorder: "desc"
				};
	
			/**
			 * 页签切换
			 * @return
			 */
			function selectTab(obj,query_type){
				gridSetting['url'] =  "jjgl_tzgggl.do?method=queryTzggList&type=" + query_type;
				
				if(query_type == "0"){
					jQuery('#listName').text('已发布列表');
				} else {
					jQuery('#listName').text('未发布列表');
				}
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}

			//修改
			function xg(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条记录！");
				} else {
					var url = "jjgl_tzgggl.do?method=tzggXg&sid="+jQuery("#dataTable").getSeletIds()[0];
					var title = "修改";
					showDialog(title,800,500,url);
				}
			}
			/**
			*查看
			**/
			function ck(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条记录！");
				} else {
					var url = "jjgl_tzgggl.do?method=tzggCk&sid="+jQuery("#dataTable").getSeletIds()[0];
					var title = "查看通知公告详情";
					showDialog(title,800,500,url);
				}
			}

			/**
			 * 新增
			 * @return
			 */
			function xz(){
				showDialog('新增通知公告',800,500,'jjgl_tzgggl.do?method=tzggXz');
			}

			/**
			 * 删除
			 * @return
			 */
			function sc(){
				var rows = jQuery("#dataTable").getSeletRow();
				var ids = jQuery("#dataTable").getSeletIds();
				if (rows.length == 0){
					showAlertDivLayer("请选择一条您要删除的记录！");
					return false;
				} else{
					showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
						jQuery.post("jjgl_tzgggl.do?method=tzggSc",{sid:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			/**
			*初始化
			*/
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			/**
			*重新加载数据
			*/
			function reloadWindow(){
				jQuery("#dataTable").reloadGrid();
			}
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
			<!-- 过滤条件 -->
			<div class="searchtab">
				<html:form action="/jjgl_zcyhgl" method="post" >
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">
							<li><a href="javascript:void(0);" onclick="xz();" class="btn_zj" >新增</a></li>
							<li><a href="javascript:void(0);" onclick="xg();" class="btn_xg" >修改</a></li>
							<li><a href="javascript:void(0);" onclick="sc();" class="btn_sc" >删除</a></li>		
							</logic:equal>
							<li><a href="javascript:void(0);" onclick="ck();" class="btn_ck" >查看</a></li>
						</ul>
					</div>
					<div class="comp_title" id="comp_title">
				      <ul style="width:90%" id="tabUl">
				      	<li class="ha" >
				      		<a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>已发布</span></a>
				      	</li>
						<li ><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>未发布</span></a></li>
				      </ul>
				    </div>
				</html:form>
			</div>
		</div>
		<div class="formbox">
			<div>
				<h3 class="datetitle_01">
					<span id="listName"> 
						通知公告列表
					</span>
				</h3>
			</div>
			<table id="dataTable"></table>
		</div>
		<div id="pager"></div>
		
	</body>
</html>
