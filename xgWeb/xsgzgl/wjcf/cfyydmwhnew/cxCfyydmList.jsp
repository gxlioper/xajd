<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/wjcf/cfyydmwhnew/js/cfyydm.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"处分原因信息列表",
				pager:"pager",
				url:"wjcf_cfyydmwh.do?method=cxCfyydmList&type=query",
				colList:[
			       {label:'pk',name:'cfyydm', index: 'cfyydm',key:true,hidden:true},
				   {label:'名称',name:'cfyymc', index: 'cfyymc',width:'40%'},
			       {label:'扣分值',name:'kffs', index: 'kffs',width:'30%'},
                    {label:'创建时间',name:'cjsj', index: 'cjsj',width:'30%'}
				],
				sortname: "cjsj",
			 	sortorder: "asc"
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["cfyymc"] = jQuery("#cfyymc").val();
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
	
		<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal value="yes" name="writeAble">
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="showDialog('增加处分原因',450,150,'wjcf_cfyydmwh.do?method=cfyydmAdd');" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
				</ul>
			</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody>
						<tr>
							<th width="15%">处分原因名称</th>
							<td>
								<input type="text" id="cfyymc" onkeypress="if(event.keyCode==13){query();}"/>
							</td>
							<td>
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="query()">
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
				<span> 处分原因信息列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
