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
						caption:"对外交流结果列表",
						pager:"pager",
						url:"xg_dwjl_dwjl.do?method=dwjlcx&type=query",
						colList:[
						   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
						   {label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
						   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
						   {label:'交流学校',name:'jlxx', index: 'jlxx',width:'15%'},
						   {label:'交流专业',name:'jlzy', index: 'jlzy',width:'15%'},
						   {label:'交流开始时间',name:'jlkssj', index: 'jlkssj',width:'15%'},
						   {label:'交流结束时间',name:'jljssj', index: 'jljssj',width:'15%'}
						],
						sortname: "jlkssj",
					 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			

		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function add(){
			var url = "xg_dwjl_dwjl.do?method=addDwjl";
			showDialog("增加",790,476,url);
		}
		function update() {
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要修改的记录！");
			}else {
				var url = 'xg_dwjl_dwjl.do?method=updateDwjl&id='+ rows[0]["id"];
				showDialog("修改", 790,470, url);
			}

		}
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		alertInfo("请选择您要删除的记录！");
	} else {
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("xg_dwjl_dwjl.do?method=dwjlDel", {
					values : ids.toString()
				}, function(data) {
					alertInfo(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

		function dwjlView(id, xh) {
			showDialog("查看", 700, 401, "xg_dwjl_dwjl.do?method=viewDwjl&id=" + id
					+ "&xh=" + xh);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='dwjlView(\""
					+ rowObject["id"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}

	
		//自定义导出 功能
		function exportConfig() {
			customExport( "xg_dwjl_dwjljg.do", ExportData);
		}

		// 导出方法
		function ExportData() {
			setSearchTj();//设置高级查询条件
			var url = "xg_dwjl_dwjl.do?method=exportData&dcclbh=xg_dwjl_dwjljg.do";
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		
		// 导出
		function zpdc() {
			showDialog("", 400, 150, 'rcsw_ylbx_ylbxjggl.do?method=zpdc');
		}
		// 导出
		function dszmdy() {
			var ids = jQuery("#dataTable").getSeletIds();
			if(ids.length!=1){
				showAlertDivLayer("请选择一条您要打印的记录！");
				return false;
				}
			var url="rcsw_ylbx_ylbxjggl.do?method=dszmdy&jgid="+ids;
			window.open(url);
		}
		//新版导入
		function dr() {
			toImportDataNew("IMPORT_N011201_DWJLWH");
			return false;

		}
				
		</script>
	</head>

	<body>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>
		<html:form action="/xg_dwjl_dwjl" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:notEqual name="userType" value="stu">
				  		    <logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
							</li>
							<li><a href="#" class="btn_dr" onclick="dr();return false;" id="btn_dr">导入</a></li>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:notEqual>
						
					</ul>
				</div>
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>对外列表</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
