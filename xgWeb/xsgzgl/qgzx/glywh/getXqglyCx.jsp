<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script>
		//初始化
		jQuery(function(){ 
			var gridSetting = {
					caption : "",
					pager : "pager",
					url : "glygl_xqgly.do?method=searchForXqgly",
					colList : [ {
						label : '职工号',
						name : 'zgh',
						index : 'zgh',
						width : '10%'
					}, {
						label : 'xq',
						name : 'xq',
						index : 'xq',
						hidden : true
					}, {
						label : '姓名',
						name : 'xm',
						index : 'xm',
						width : '10%'
					}, {
						label : '性别',
						name : 'xb',
						index : 'xb',
						width : '10%'
					}, {
						label : '部门',
						name : 'bmmc',
						index : 'bmmc',
						width : '20%'
					}, {
						label : '学区',
						name : 'xqmc',
						index : 'xqmc',
						width : '20%'
					}]
				}
				jQuery("#dataTable").initGrid(gridSetting);
		});
		function selectTab(url) {
			document.location.href = url ;
		}
		function searchRs() {
			var map = {};
			jQuery("#dataTable").reloadGrid(map);
		}
			function del() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0){
					showAlertDivLayer("请选择您要删除的记录！");
				} else {
					var ids = "";
					for(var i=0;i<rows.length;i++){
						ids += rows[i]["zgh"]+rows[i]["xq"];
						if(i != rows.length-1){
							ids += ",";
						}
					}
					showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
						jQuery.post("glygl_xqgly.do?method=del",{values:ids.toString()},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
		</script>
	</head>
	<body>
		<div class="tab_cur" >
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 提示信息 -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					此菜单维护的数据用于限制用人单位岗位审核中的校区审核范围
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/qgzx_glygl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="showDialog('增加管理员', 550, 400, 'glygl_xqgly.do?method=add');return false;" class="btn_zj">增加</a></li>
						<li><a href="#" onclick="del();return false;" class="btn_sc">删除</a></li>
					</ul>
				</div>
				</logic:equal>
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li><a href="javascript:void(0);" onclick="selectTab('qgzx_glygl_glywh.do');"><span>勤工管理员</span></a></li>
			        <li  class="ha"><a href="javascript:void(0);" onclick="selectTab('qgzx_glygl_xqgly.do');"><span>校区管理员</span></a></li>
			      </ul>
			    </div>
				<div style="display: none;">
				<button type="button" id="search_go" onclick="searchRs();">
										保    存
									</button>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</div>
			</div>	
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>查询结果&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
