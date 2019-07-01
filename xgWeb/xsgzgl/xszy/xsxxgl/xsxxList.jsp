<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
						caption:"新生信息列表",
						pager:"pager",
						url:"xszyXsxxgl.do?method=getXsxxList&type=query",
						colList:[
							{label:'学号',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
							{label:'姓名',name:'xm', index: 'xm',width:'11%'},
							{label:'性别',name:'xb', index: 'xb',width:'6%'},
							{label:'大类',name:'dl', index: 'dl',width:'16%'},
							{label:'班级',name:'bjmc', index: 'bjmc',width:'15%'},
							{label:'楼栋',name:'ldmc', index: 'ldmc',width:'8%'},
							{label:'寝室号',name:'qsh', index: 'qsh',width:'6%'},
							{label:'学园',name:'xymc', index: 'xymc',width:'11%'},
							{label:'所属院系',name:'ssyxmc', index: 'ssyxmc',width:'15%'},
							{label:'所属院系',name:'ssyxdm', index: 'ssyxdm',hidden:true}
						],
						sortname: "xh",
					 	sortorder: "asc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			

			var btndr=jQuery("#btn_dr");
			//导入
			if(btndr!=null){
				btndr.click(function () {
					//调用通用的导入function，参数是导入功能模块代码。
					toImportDataNew("IMPORT_N950101_XSXXGL");
					return false;
				});
			}
		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function update() {
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要修改的记录！");
			}else{
				var url = 'xszyXsxxgl.do?method=updateXszyXsxx&xh=' + rows[0]["xh"];
				var title = "修改新生信息";
				showDialog(title, 680, 458, url);
			}
		}

		function xszyXsxxView(xh) {
			showDialog("新生信息查看", 700, 450, "xszyXsxxgl.do?method=viewXszyXsxx&xh=" + xh);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='xszyXsxxView(\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}

		var DCCLBH = "xszy_xsxxgl.do";//dcclbh,导出功能编号

		//自定义导出 功能
		function exportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport(DCCLBH, xszyXsxxExportData);
		}

		// 导出方法
		function xszyXsxxExportData() {
			setSearchTj();//设置高级查询条件
			var url = "xszyXsxxgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszyXsxxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:notEqual name="userType" value="stu">
					<logic:equal name="writeAble" value="yes">
						<!-- 按钮 -->
						<div class="buttonbox">
							<ul>
								<li>
									<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
								</li>
								<li><a href="#" class="btn_dr" id="btn_dr">导入</a></li>
								<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
							</ul>
						</div>
					</logic:equal>
				</logic:notEqual>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>新生信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
