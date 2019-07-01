<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/hczd/js/dmwh.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"火车站点维护列表",
				pager:"pager",
				url:"hczdgl_hczdwh.do?method=hczdList&type=query",
				colList:[
			       {label:'站点名称',name:'zdmc', index:'zdmc',key:true,width:'50%'},
				   {label:'省份',name:'shenfen', index:'shenfen',width:'50%'}
				]
			};
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["zdmc"] = jQuery("#zdmc").val();
				map["shenfen"] = jQuery("#shenfen").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
			//导入
			function dr() {
				// 调用通用的导入function，参数是导入功能模块代码。
				toImportDataNew("IMPORT_HCZD");
				return false;

			}

			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<input type="hidden" id="text"
			value="<bean:message key="wjcf.text" />" />
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal value="yes" name="writeAble">
			<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);"
								onclick="showDialog('增加站点',450,310,'hczdgl_hczdwh.do?method=hczdAdd');"
								class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">导入</a>
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
								站点名称
							</th>
							<td>
								<input type="text" id="zdmc"
									onkeypress="if(event.keyCode==13){query();}" />
							</td>
							<th width="15%">
								省份
							</th>
							<td>
								<input type="text" id="shenfen"
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
				<span>火车站点维护列表</span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
