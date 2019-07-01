<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/cdgl/js/cdsq.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "场地申请列表",
		pager : "pager",
		url : "rcsw_cdgl_cdsq.do?method=query",
		colList : [
				{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
				<logic:equal name="xxdm" value="10277">
					{ label : '负责人', name : 'fzrxm', index : 'fzrxm', width : '10%',formatter : cdsqLink},
					{ label : '负责人联系电话', name : 'fzrlxfs', index : 'fzrlxfs', width : '9%'},
					{ label : '使用部门', name : 'bmlbmc', index : 'bmlbmc', width : '10%' },
					{ label : '学号', name : 'xh', index : 'xh', hidden : true },
   				</logic:equal>
				<logic:notEqual name="xxdm" value="10277">
					{ label : '学号', name : 'xh', index : 'xh', width : '11%',formatter : cdsqLink },
					{ label : '姓名', name : 'xm', index : 'xm', width : '8%'},			
					{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '10%' },
				</logic:notEqual>
				{ label : '申请场地', name : 'cdmc', index : 'cdmc', width : '12%' },
				{ label : '申请使用时间段', name : 'sqsjd', index : 'sqsjd', width : '28%' },
				{ label : '申请时间', name : 'sqsj', index : 'sqsj', width : '10%' },
				{ label : 'shzt', name : 'shzt', index : 'shzt',hidden : true },
				{ label : 'splcid', name : 'splcid', index : 'splcid',hidden:true},
				{ label : 'cdid', name : 'cdid', index : 'cdid',hidden:true},
				{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '8%' }],
		sortname : "xm", sortorder : "asc" }

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

	function cdsqLink(cellValue, rowObject) {
		var onclickfn = "onclick=\""
				+ "showDialog('场地申请信息详情' , 780 , 485 , 'rcsw_cdgl_cdsq.do?method=cdsqCk&sqid="
				+ rowObject['sqid'] + "&xh=" + rowObject['xh'] +"')" + "\"";

		var href = "href = 'javascript:void(0);'";

		var el = "<a " + href + " class='name' " + onclickfn + " >" + cellValue
				+ "</a>";

		return el;
	}

	//场地申请表导出
	function cdsqbdc(){
		var ids = jQuery("#dataTable").getSeletIds();
		if(ids.length == 0){
			return showAlertDivLayer("请至少选择一条记录导出！");
		}else if(ids.length == 1){
			var url = "rcsw_cdgl_cdsq.do?method=exportCdsqb";
			url += "&sqid=" + ids+"&flag=sq";
			window.open(url);
		}else{
			var url = "rcsw_cdgl_cdsq.do?method=exportCdsqbTy";
			url += "&value=" + ids+"&flag=sq";
			window.open(url);
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
		<html:form action="/rcsw_cdgl_cdxxwh">

			<%@ include file="/comm/hiddenValue.jsp"%>

			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj"
									onclick="addCdsq();return false;">申请</a>
							</li>
							<li>
								<a href="javascript:void(0);"
									onclick="updateCdsq();return false;" class="btn_xg"
									>修改</a>
							</li>
							<li>
								<a href="javascript:void(0);"
									onclick="deleteCdsq();return false;" class="btn_sc"
									>删除</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">撤销</a>
							</li>
							</logic:equal>
							<li>
								<a href="javascript:void(0);" onclick="lcinfo();return false;" class="btn_cs">流程跟踪</a>
							</li>	
							<logic:equal name="xxdm" value="10346">
								<li>
								<a href="javascript:void(0);"
										onclick="pj();return false;" class="btn_sh">评价</a>
								</li>
							</logic:equal>
							<!-- 场地申请表 -->
							<logic:equal name="xxdm" value="12309">
								<li>
									<a href="javascript:void(0);" onclick="cdsqbdc();" class="btn_dc">场地申请表</a>
								</li>
							</logic:equal>
							<%--<li>
								<a href="javascript:void(0);"
									onclick="exportConfig();return false;" class="btn_dc">导出</a>
							</li>
						--%>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>

		<div class="main_box">
			<h3 class=datetitle_01>
				<span>场地申请列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
