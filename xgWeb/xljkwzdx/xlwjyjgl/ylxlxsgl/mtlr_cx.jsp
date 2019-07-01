<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		pager : "pager",
		url : "xljk_xlwjyjgl_ylxlxsglwh.do?method=query",
		colList : [
				{ label : 'key', name : 'xh', index : 'xh',key : true, hidden : true },
				{ label : 'sfmtdm', name : 'sfmtdm', index : 'sfmtdm', hidden : true },
				{ label : 'yjkdm', name : 'yjkdm', index : 'yjkdm', hidden : true },
				{ label : '学号', name : 'xh', index : 'xh', width : '12%',formatter : link},
				{ label : '姓名', name : 'xm', index : 'xm', width : '10%'},
				{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
				{ label : '年级', name : 'nj', index : 'nj', width : '7%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%' },
				{ label : '班级', name : 'bjmc', index : 'bjmc', width : '15%' },
				{ label : '是否已面谈', name : 'sfmtmc', index : 'sfmtmc', width : '10%' },
				{ label : '心理问题类型', name : 'lxmc', index : 'lxmc', width : '10%' },
				{ label : '判断等级', name : 'gzdj', index : 'gzdj', width : '6%' },
				{ label : '是否提交预警库', name : 'yjkcx', index : 'yjkcx',  width : '8%'}],
		sortname : "sfmtmc", sortorder : "desc" };
	
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
	 * 面谈录入
	 * @return
	 */
	function mtlr(){
		var rows = jQuery("#dataTable").getSeletRow();
		if (rows.length != 1){
			showAlertDivLayer("请选择一条您要面谈录入的记录！");
			return false;
		} else{
			if(rows[0]['yjkdm'] == 'y'){
				showAlertDivLayer("记录已提交预警库，不能修改！");
				return false;
			}
			showDialog('面谈录入',780,600,'xljk_xlwjyjgl_ylxlxsglwh.do?method=mtlr&xh=' + rows[0]['xh'] );
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
		var onclickfn = "onclick=\"" + "showDialog('详细信息' , 780,530 , 'xljk_xlwjyjgl_ylxlxsglwh.do?method=mtlrck&xh=" + rowObject['xh'] + "')" + "\"";
		
		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue + "</a>";
		
		return el;
	}


	var DCCLBH = "xljk_xlwjyjgl_ylxlxsgl.do";//dcclbh,导出功能编号


	//自定义导出 功能
	function exportConfig() {
		//DCCLBH导出功能编号,执行导出函数 
		customExport(DCCLBH, exportData);
	}

	// 导出方法
	function exportData() {
		setSearchTj();//设置高级查询条件
		var url = "xljk_xlwjyjgl_ylxlxsglwh.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
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
		<html:form action="/xljk_xlwjyjgl_ylxlxsglwh">
			<input type="hidden" id="query_type" value="0"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);"
								onclick="mtlr();return false;" class="btn_xg" style="font-weight: bold"
							>面谈录入</a>
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
