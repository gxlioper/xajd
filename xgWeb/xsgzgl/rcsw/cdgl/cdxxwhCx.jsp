<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/cdgl/js/cdxxwh.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "场地信息列表",
		pager : "pager",
		url : "rcsw_cdgl_cdxxwh.do?method=query",
		colList : [
				{ label : 'key', name : 'cdid', index : 'cdid',key : true, hidden : true },
				{ label : '场地名称', name : 'cdmc', index : 'cdmc', width : '15%',formatter : cdmcLink },
				{ label : '楼栋', name : 'ld', index : 'ld', width : '10%' },
				{ label : '房间', name : 'fj', index : 'fj', width : '5%' },
				{ label : '容纳人数', name : 'rnrs', index : 'rnrs', width : '5%' },
				{ label : '收费标准', name : 'sfbz', index : 'sfbz', width : '5%' },
				{ label : 'splcid', name : 'splcid', index : 'splcid',hidden : true },
				{ label : '对外开放时间', name : 'dwkfsj', index : 'dwkfsj', width : '15%' },
				{ label : '是否开放申请', name : 'sfkfsqmc', index : 'sfkfsqmc', width : '5%' },
				{ name : 'sfkfsq', index : 'sfkfsq', hidden : true },
				{ label : '审核流程', name : 'lcxx', index : 'shzt', lcxx : '15%' } ],
		sortname : "cdmc", sortorder : "desc" }

	jQuery(function() {
		gridSetting["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);

	});

	/**
	 * 场地链接
	 * 
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function cdmcLink(cellValue, rowObject) {
		var onclickfn = "onclick=\""
				+ "showDialog('场地信息详情' , 680 , 430 , 'rcsw_cdgl_cdxxwh.do?method=cdxxwhCk&cdid="
				+ rowObject['cdid'] + "')" + "\"";

		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
				+ "</a>";

		return el;
	}
</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_cdgl_cdxxwh">

			<%@ include file="/comm/hiddenValue.jsp"%>

			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj"
									onclick="addCdxx();return false;">增加</a>
							</li>
							<li>
								<a href="javascript:void(0);"
									onclick="updateCdxx();return false;" class="btn_xg"
									>修改</a>
							</li>
							<li>
								<a href="javascript:void(0);"
									onclick="deleteCdxx();return false;" class="btn_sc"
									>删除</a>
							</li>
						</logic:equal>
						
							<li>
								<a href="javascript:void(0);"
									onclick="exportConfig();return false;" class="btn_dc">导出</a>
							</li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>场地维护&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
