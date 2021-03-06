<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"jddj_dmwh.do?method=getDmwhList",
				colList:[
				   {label:'代码',name:'dm', index: 'dm',hidden:true,key:true},
				   {label:'名称',name:'mc', index: 'dm'}
				],
				params:{lx:"1"},
				sortname: "dm",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["lx"] = jQuery("#lx").val();
				map["mc"] = jQuery("#mc").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					alertInfo("请选择一条您要修改的记录！");
				} else {
					showDialog('修改',400,120,'jddj_dmwh.do?method=dmwhEdit&dm='+rows[0]["dm"]);
				}
			}

			function dmwhDel(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					alertInfo("请选择您要删除的记录！");
				} else {
					showConfirmDivLayer("您确定要删除该记录吗？",{"okFun":function(){
						jQuery.post("jddj_dmwh.do?method=dmwhDel",{ids:ids.toString()},function(data){
							alertInfo(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
			
			function addDmwh(){
				var lx = jQuery("#lx").val();
				showDialog('增加',400,120,'jddj_dmwh.do?method=dmwhAdd&lx='+lx);;
			}
			
			function selectTab(obj,lx){
				jQuery("#lx").val(lx);
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				query();
			}
		</script>
	</head>

	<body>
		<input type="hidden" name="lx" id="lx" value="1"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="addDmwh()" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="dmwhDel();" class="btn_sc">删除</a></li>						
				</ul>
			</div>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
						
							<th>名称</th>
							<td>
								<input type="text" id="mc"/>
							</td>
							<td align="right">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="comp_title" id="comp_title">
	      <ul style="width:90%">
	        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>项目名称</span></a></li>
	        <li><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>项目级别</span></a></li>
	      </ul>
	    </div>
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 数据列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
