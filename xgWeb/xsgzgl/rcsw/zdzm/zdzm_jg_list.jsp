<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zdzm/js/zdzm_jg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript" src="xsgzgl/rcsw/zdzm/js/print.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "在读证明结果表",
		pager : "pager",
		url : "rcsw_zdzm_jggl.do?method=queryZdzmJgList&type=query",
		colList : [
				{ label : 'key', name : 'zdzmjgid', index : 'zdzmjgid',key : true, hidden : true },
				{ name : 'sjly', index : 'sjly', hidden : true },
				{ label : '学号', name : 'xh', index : 'xh', width : '10%',formatter : xhLink },
				{ label : '姓名', name : 'xm', index : 'xm', width : '10%' },
				{ label : '年级', name : 'nj', index : 'nj', width : '5%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xydm', width : '13%' },
				{ label : '专业', name : 'zymc', index : 'zydm', width : '13%' },
				{ label : '班级', name : 'bjmc', index : 'bjdm', width : '13%' },
				{ label : '培养层次', name : 'pyccmc', index : 'pycc', width : '8%' },
				{ label : '申请时间', name : 'sqsj', index : 'sqsj', width : '8%' }],
		sortname : "sqsj", sortorder : "desc" }

	jQuery(function() {
		gridSetting["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);

	});


	/**
	导入
	*/
	function importConfig(){
		toImportData("IMPORT_RCSW_ZDZM");
		return false;
	}
	
	/**
	 * 学号链接
	 * 
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function xhLink(cellValue, rowObject) {
		var onclickfn = "onclick=\""
				+ "showDialog('在读证明申请详情' , 720 , 300 , 'rcsw_zdzm_jggl.do?method=viewZdzmJg&zdzmjgid="
				+ rowObject['zdzmjgid'] + "')" + "\"";

		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
				+ "</a>";

		return el;
	}
</script>
	</head>

	<body>
		<input type="hidden" name="kgzt" id="kgzt" value="${csszModel.ksqkg }"/>
		<input type="hidden" name="xzkg" id="xzkg" value="${csszModel.xzkg }"/>
		<input type="hidden" name="kxzkz" id="kxzkz" value="${csszModel.kxzkz }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/rcsw_zdzm_sqgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addZdzmJg();return false;"  title="点击该按钮，打开申请表填写页面。">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updateZdzmJg();return false;" class="btn_xg" title="选中一条记录，点击该按钮可修改申请表。">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="deleteZdzmJg();return false;" class="btn_sc" title="只能取消未审核的记录，已经在审核的不能取消。" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a>
						</li>	
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="printZdzmJg('rcsw_zdzm_jggl.do?method=doPrint');return false;" class="btn_down">下载在读证明</a></li>						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>在读证明申请&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
