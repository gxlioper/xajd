<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/dxsylbx/ylbxjg/js/ylbxjgManage.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		
		function getWord(){
			 var rows = jQuery("#dataTable").getSeletRow();
			 if (rows.length == 0){
				showAlertDivLayer("请选择一条记录！");
			 } else if (rows.length > 1){
				var url="rcsw_dxsylbx_ylbxjggl.do?method=getDxsylbxZip";
				var ids = jQuery("#dataTable").getSeletIds();
				var url= url+"&value="+ids;
				window.open(url);
			 } else {
				var url="rcsw_dxsylbx_ylbxjggl.do?method=getDxsylbxWord";
				var url= url+"&yljgid="+rows[0]["yljgid"]+"&xh="+rows[0]["xh"];
				window.open(url);
		     }
		}
		jQuery(function(){
			var btndr=jQuery("#btn_dr");
			//导入
			if(btndr!=null){
				btndr.click(function () {
					//调用通用的导入function，参数是导入功能模块代码。
					toImportData("IMPORT_N730805_YLBXJG");
					return false;
				});
			}
		});
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_dxsylbx_ylbxjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" 
							>增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg"
							>修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc"
							>删除</a>
						</li>
						</logic:equal>
						<!-- 读写权 -->
						<logic:equal name="writeAble" value="yes">				
							<!-- 温州大学 -->
							<logic:equal name="xxdm" value="10351">	
							<li><a href="#" class="btn_dr" id="btn_dr">导入</a></li>	
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>医疗保险结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
