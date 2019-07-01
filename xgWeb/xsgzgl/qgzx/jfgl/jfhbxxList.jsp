<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/jfgl/js/jfhbzjdx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"经费划拨信息列表",
				pager:"pager",
				url:"qgzx_jfgl.do?method=jfhbxxList&type=query",
				colList:[
					{ label : 'pkvalue', name : 'pkvalue', index : 'pkvalue',key : true, hidden : true },
					{ label : '行号', name : 'rowindex', index : 'rowindex', width : '2%'},
					{ label : '年度', name : 'nd', index : 'nd', width : '4%'},
					{ label : '用人单位', name : 'bmmc', index : 'yrdwdm', width : '10%'},
					{ label : '划拨总金额<元>', name : 'hbzje', index : 'hbzje', width : '10%'},
					{ label : '已发放金额<元>', name : 'ffzje', index : 'ffzje', width : '8%'},
					{ label : '剩余金额<元>', name : 'syje', index : 'syje', width : '8%'}
				],
				sortname : "bmmc",
			    sortorder : "asc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_jfgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >经费增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_xg" onclick="showModi('update');return false;" >经费修改</a>
						</li>
						
						<li>
							<a href="javascript:void(0);" class="btn_dr" onclick="importJfhb();return false;" >导入</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="view();return false;" >查看明细</a>
						</li>	
						
						<logic:equal name="userName" value="zf01">
							<li>
								<a href="#" class="btn_dc" onclick="exportConfig();return false;" >导出</a>
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
				<span>经费划拨信息列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>