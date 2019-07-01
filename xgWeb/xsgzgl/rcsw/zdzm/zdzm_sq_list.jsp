<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zdzm/js/zdzm_sq.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zdzm/js/print.js"></script>
		<script type="text/javascript">
		
	var gridSetting = {
		caption : "在读证明申请表",
		pager : "pager",
		url : "rcsw_zdzm_sqgl.do?method=queryZdzmSqList&type=query",
		colList : [
				{ label : 'key', name : 'zdzmsqid', index : 'zdzmsqid',key : true, hidden : true },
				{ label : '学号', name : 'xh', index : 'xh', width : '10%',formatter : xhLink },
				{ label : '姓名', name : 'xm', index : 'xm', width : '10%' },
				{ label : '年级', name : 'nj', index : 'nj', width : '5%' },
				{ label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xydm', width : '13%' },
				{ label : '专业', name : 'zymc', index : 'zydm', width : '13%' },
				{ label : 'splcid', name : 'splcid', index : 'splcid',hidden : true},
				{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
				{ label : '班级', name : 'bjmc', index : 'bjdm', width : '13%' },
				{ label : '培养层次', name : 'pyccmc', index : 'pycc', width : '8%' },
				{ label : '申请时间', name : 'sqsj', index : 'sqsj', width : '8%' },
				{ label : '审核状态', name : 'shztmc', index : 'shzt', width : '6%' }],
		sortname : "sqsj", sortorder : "desc" }

	jQuery(function() {
		gridSetting["params"] = getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);

	});

	/**
	 * 学号链接
	 * 
	 * @param cellValue
	 * @param rowObject
	 * @return
	 */

	function xhLink(cellValue, rowObject) {
		var onclickfn = "onclick=\""
				+ "showDialog('在读证明申请详情' , 720 , 350 , 'rcsw_zdzm_sqgl.do?method=viewZdzmSq&zdzmsqid="
				+ rowObject['zdzmsqid'] + "')" + "\"";

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
		<input type="hidden" name="isopen" id="isopen" value="${csszModel.isopen }" />
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					取消申请：仅可取消“未审核”的申请
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/rcsw_zdzm_sqgl">
			
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addZdzmSq();return false;"  title="点击该按钮，打开申请表填写页面。">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="updateZdzmSq();return false;" class="btn_xg" title="选中一条记录，点击该按钮可修改申请表。">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="deleteZdzmSq();return false;" class="btn_sc" title="只能取消未审核的记录，已经在审核的不能取消。" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a>
						</li>	
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a>
						</li>	
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="printZdzmSq('rcsw_zdzm_sqgl.do?method=doPrint');return false;" class="btn_down">下载在读证明</a></li>						
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
