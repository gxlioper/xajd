<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script language="javascript" src="js/LodopFuncs.js"></script>
		<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
		      <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
		</object>
		<script type="text/javascript">
		var gridSetting = {
				caption:"大二学生信息",
				pager:"pager",
				url:"xsxx_xsxxgl.do?method=xycx&type=query",
				colList:[
					{label:'学号',name:'xh', index: 'xh',key:true,width:'6%',formatter:xhLink },
					{label:'姓名',name:'xm', index: 'xm',width:'6%'},
					{label:'年级',name:'nj', index: 'nj',width:'2%'},
					{label:'当前学院',name:'xymch', index: 'xymch',width:'10%'},
					{label:'主修专业确认前学园',name:'xymc', index: 'xymc',width:'12%'}
				],
			};

		jQuery(function(){
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});

		function xhLink(cellValue,rowObject){
			return "<a href='javascript:void(0);' class='name' onclick='zxsxxView(\""+rowObject["xh"]+"\",\""+cellValue+"\");'>"+cellValue+"</a><input type='hidden' name='key_xh' value='" + rowObject["xh"] + "' >"
		}

		//新版查看弹出层
		function zxsxxView(xh){
			showDialog("学生信息查询",850,500,"xsxx_tygl.do?method=ckZxsxx&xh="+xh+"&xs");
		}

		//高级查询
		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		//查看
		function showzxsxxView(){

			var rows = jQuery("#dataTable").getSeletRow();
			 if (rows.length == 1){
				 var ids = jQuery("#dataTable").getSeletIds();
				 zxsxxView(ids);
			 } else {
				 alertInfo("请<font color='blue'>勾选一条</font>您希望查看的记录！");
		     }
		}

		//导出
		function exportConfig(){
			var DCCLBH='xsxx_xycx.do';
			customExport(DCCLBH, exportData);
		}

		function exportData(){
			var DCCLBH='xsxx_xycx.do';
			setSearchTj();//设置高级查询条件
			var url = "xsxx_xsxxgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		
		// 密码初始化
		function mmcsh() {
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlert("请选择要初始化密码的学生");
			} else {
				showDialog("密码初始化", 350, 210, "xsxx_xsxxgl.do?method=mmcsh");
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

		<html:form action="/xsxx_xsxxgl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xhstr" id="xhstr" />
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showzxsxxView();return false;" id="btn_ck"
								class="btn_ck"> 查看 </a>
						</li>
						<li>
							<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
						</li>
						<li>
							<a href="#" onclick="mmcsh('show');return false;" class="btn_csh">密码初始化</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->

				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生信息</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
