<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		pager : "pager",
		url : "xljk_xlwjyjgl_xlwjyjkglwh.do?method=query",
		colList : [
				{ label : 'key', name : 'xh', index : 'xh',key : true, hidden : true },
				{ label : '学号', name : 'xh', index : 'xh', width : '10%',formatter : link},
				{ label : '姓名', name : 'xm', index : 'xm', width : '10%'},
				{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
				{ label : '年级', name : 'nj', index : 'nj', width : '7%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%' },
				{ label : '班级', name : 'bjmc', index : 'bjmc', width : '15%' },
				{ label : '问题类型', name : 'lxmc', index : 'lxmc', width : '12%' },
				{ label : '关注等级', name : 'gzdj', index : 'gzdj', width : '6%' },
				{ label : '入库时间', name : 'rksj', index : 'rksj', width : '10%' },
				{ label : '最近辅导时间', name : 'zjfdsj', index : 'zjfdsj',  width : '10%'}],
		sortname : "rksj", sortorder : "desc" };
	
	jQuery(function() {
		gridSetting["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);
	});

	/**
	 * 高级查询
	 * @return
	 */
	function searchRs() {
		var map = getSuperSearch();
		jQuery("#dataTable").reloadGrid(map);
	}

	/**
	 * 新增
	 * @return
	 */
	function add(){
		showDialog('上报',680,430,'xljk_xlwjyjgl_xlwjyjkglwh.do?method=xz');
	}

	/**
	 * 删除
	 * @return
	 */
	function del(){
		var rows = jQuery("#dataTable").getSeletRow();
		var ids = jQuery("#dataTable").getSeletIds();
		if (rows.length == 0){
			showAlertDivLayer("请选择一条您要删除的记录！");
			return false;
		} else{
			showConfirmDivLayer("您确定要删除所选记录？",{"okFun":function(){
				jQuery.post("xljk_xlwjyjgl_xlwjyjkglwh.do?method=delAction",{xhs:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}
	}
	
	/**
	 * 修改
	 * @return
	 */
	function update(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("请选择一条您要修改的记录！");
			return false;
		} else{
			showDialog('修改',680,430,'xljk_xlwjyjgl_xlwjyjkglwh.do?method=xg&xh=' + rows[0]['xh'] );
		}
	}

	/**
	 * 链接
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function link(cellValue,rowObject){
		//return "<a href='javascript:void(0);' class='name' onclick='knsView(\""+rowObject["id"]+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
		var onclickfn = "onclick=\"" + "showDialog('详细信息' , 680,430 , 'xljk_xlwjyjgl_xlwjyjkglwh.do?method=ck&xh=" + rowObject['xh'] + "')" + "\"";
		
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
	}

	var DCCLBH = "xljk_xlwygl_xlwjyjkglwh.do";//dcclbh,导出功能编号


	//自定义导出 功能
	function exportConfig() {
		//DCCLBH导出功能编号,执行导出函数 
		customExport(DCCLBH, exportData);
	}

	// 导出方法
	function exportData() {
		setSearchTj();//设置高级查询条件
		var url = "xljk_xlwjyjgl_xlwjyjkglwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
		url = addSuperSearchParams(url);//设置高级查询参数
		jQuery("form").eq(0).attr("action", url);
		jQuery("form").eq(0).submit();
	}

	/**
	导入
	*/
	function importConfig(){
		toImportData("IMPORT_XLZX_XLWJYJK");
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
		<html:form action="/xljk_xlwjyjgl_xlwjyjkglwh">
			<input type="hidden" id="query_type" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
								onclick="add();return false;">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);"
								onclick="update();return false;" class="btn_xg"
							>修改</a>
						</li>
						<li>
								<a href="javascript:void(0);"
									onclick="del();return false;" class="btn_sc"
									>删除</a>
							</li>
							<li>
								<a href="javascript:void(0);"
									onclick="importConfig();return false;" class="btn_dr">导入</a>
							</li>
							<li>
								<a href="javascript:void(0);"
									onclick="exportConfig();return false;" class="btn_dc">导出</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>查询结果&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
