<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/dxsylbx/ylbxwh/js/czqebzlxwhManage.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>

		<script type="text/javascript">
		
			function add(){
				var url = "rcsw_dxsylbx_ylbxwhgl.do?method=addCzqebzlx";
				var title = "增加补助类型";
				showDialog(title,470,180,url);
			}
			
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要修改的记录！");
				} else {
					var url = "rcsw_dxsylbx_ylbxwhgl.do?method=updateCzqebzlx&czqebzdm="+rows[0]["czqebzdm"];
					var title = "修改补助类型";
					showDialog(title,470,180,url);
				}
			}
			
		</script>
		
	</head>
	<body>
	<html:form action="/rcsw_dxsylbx_ylbxwhgl" method="post">
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
					<li><a href="javascript:void(0);" onclick="delCzqebzlx();" class="btn_sc">删除</a></li>						
				</ul>
			</div>
			</logic:equal>
			<div class="compTab" id="card">
				<div class="comp_title"><ul>
						<li class="ha"><a href="#" onclick="newChgCode(this);return false;" id="rcsw_dxsylbx_ylbxwhgl.do?method=czqebzlxListManage"><span>补助类型</span> </a>
						<li><a href="#" onclick="newChgCode(this);return false;" id="rcsw_dxsylbx_ylbxwhgl.do?method=cbzklxListManage"><span>参保状况类型</span> </a>
				</div>
			</div>	
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="14%">补助类型名称</th>
						<td>
							<input type="text" id="czqebzmc" name="czqebzmc" maxleng="20" style="width:220px;"
							onkeypress="if(pressEnter(event)){query();return false;}"
							/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
			<div class="formbox">
			<!--标题start-->
				<h3 class="datetitle_01">
					<span> 补助类型维护列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
