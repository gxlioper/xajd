<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript"
			src="xsgzgl/xpjpy/zyjgl/dmwh/js/dmwh.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"专业奖认定等级维护列表",
				pager:"pager",
				url:"pjpy_zyjgldmwh.do?method=rddjList&type=query",
				colList:[
			       {label:'pk',name:'rddjdm', index: 'rddjdm',key:true,hidden:true},
				   {label:'等级名称',name:'rddjmc', index: 'rddjmc',width:'80%'}
				],
				sortname: "rddjmc",
			 	sortorder: "asc"
			};
			function dcmcLink(cellValue, rowObject) {
				var rddjdm = rowObject["rddjdm"];
				return "<a href='javascript:void(0);' onclick=\"ckxx('" + rddjdm
						+ "')\" class='name'>" + cellValue + "</a>";
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["rddjmc"] = jQuery("#rddjmc").val();
				jQuery("#dataTable").reloadGrid(map);
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
								onclick="showDialog('增加认定等级',450,310,'pjpy_zyjgldmwh.do?method=rddjAdd');"
								class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a>
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
								认定等级名称
							</th>
							<td>
								<input type="text" id="rddjmc"
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
				<span>专业奖认定等级维护列表</span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
