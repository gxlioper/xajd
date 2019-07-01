<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" >
			var gridSetting = {
					caption:"等级列表",
					pager:"pager",
					url:"xsxx_gygl_cxcxdj.do?method=cxdjwhList&type=query",
					colList:[
					   {label:'操行等级代码',name:'cxdjdm', index: 'cxdjdm',key:true},
					   {label:'操行等级名称',name:'cxdjmc', index: 'cxdjmc',width:'60%'}
					],
					sortname: "cxdjdm",
				 	sortorder: "asc"
				}
	
	
	
				jQuery(function(){
					jQuery("#dataTable").initGrid(gridSetting);
				});
			function add(){
				var url = "xsxx_gygl_cxcxdj.do?method=addCxdj";
				var title = "增加操行等级";
				showDialog(title,400,250,url);
			}
			function query(){
				var map = {};
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要修改的记录！");
				} else {
					var url = 'xsxx_gygl_cxcxdj.do?method=updateCxdj&cxdjdm='+rows[0]["cxdjdm"];
					var title = "修改操行等级";
					showDialog(title,400,250,url);
				}
			}

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					showAlertDivLayer("请选择您要删除的记录！");
				} else {
					showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
							jQuery.post("xsxx_gygl_cxcxdj.do?method=delCxdj",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
					
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
	
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>		
				</ul>
			</div>
			</logic:equal>				
		</div>
		<button type="button" class="btn_cx" id="search_go" onclick="query()" style="display: none">
			查 询
		</button>

		
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 等级代码维护列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
