<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"书院维护列表",
				pager:"pager",
				url:"xtwh_syglwh.do?method=syList&type=query",
				colList:[
			       {label:'书院代码',name:'sydm', index:'sydm',key:true,hidden:true},
				   {label:'书院名称',name:'symc', index:'symc',width:'50%'},
				   {label:'下辖班级数',name:'xxbjs', index:'xxbjs',width:'50%'}
				]
			};
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["symc"] = jQuery("#symc").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			

		function add(){
			var url = "xtwh_syglwh.do?method=syadd";
			showDialog("增加书院",700,400,url);
		}
		function update() {
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要修改的记录！");
			}else {
				var url = 'xtwh_syglwh.do?method=syupdate&sydm='+ rows[0]["sydm"];
				showDialog("修改书院",700,400, url);
			}

		}
		function bjsz() {
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要设置的记录！");
			}else {
				var url = 'xtwh_syglwh.do?method=bjManage&sydm='+ rows[0]["sydm"];
				showDialog("班级设置",800,500, url);
			}
		}
		function del() {
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length != 1) {
				alertInfo("请选择一条您要删除的记录！");
			} else {
				showConfirmDivLayer("您确定要删除选择的记录吗？", {
					"okFun" : function() {
						jQuery.post("xtwh_syglwh.do?method=sydel", {
							values : ids.toString()
						}, function(data) {
							alertInfo(data["message"]);
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
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal value="yes" name="writeAble">
			<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);"onclick="add();" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="bjsz();return false;" class="btn_sz">班级设置</a>
						</li>
				</ul>
			</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%">
								书院名称
							</th>
							<td>
								<input type="text" id="symc"
									onkeypress="if(event.keyCode==13){query();}" />
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go"
										onclick="query()">
										查 询
									</button>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>


		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>书院维护列表</span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
