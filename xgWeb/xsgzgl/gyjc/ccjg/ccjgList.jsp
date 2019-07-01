<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gyjc/ccjg/js/ccjg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gyjc_ccjgcx.do?method=ccjgList&type=query",
				colList : [ {
					label : 'key',
					name : 'guid',
					index : 'guid',
					key : true,
					hidden : true
				}, {
					label : '学院',
					name : 'xymc',
					index : 'xymc',
					width : '10%'
				}, {
					label : '楼栋',
					name : 'ldmc',
					index : 'ldmc',
					width : '10%'
				}, {
					label : '寝室',
					name : 'qsh',
					index : 'qsh',
					width : '5%'
				}, {
					label : '类别',
					name : 'jclxmc',
					index : 'jclxmc',
					width : '10%'
				}, {
					label : '文明寝室等级',
					name : 'qsdj',
					index : 'qsdj',
					width : '10%'
				},
				{
					label : '不达标说明</br>请参考卫生准则',
					name : 'bhgsm',
					index : 'bhgsm',
					width : '10%'
				},
				{
					label : '检查人',
					name : 'tjrxm',
					index : 'tjrxm',
					width : '10%'
				},
				{
					label : '检查时间',
					name : 'jcrq',
					index : 'jcrq',
					width : '10%'
				}],
				radioselect:false
			}
			var map = getSuperSearch();
			map["flag"] = jQuery("#flag").val();
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
		<html:form action="/gyjc_ccjgcx" styleId="CcjgForm">
			<html:hidden property="flag" styleId="flag"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:equal  name="flag" value="cc">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addCcjg();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
						</li>
						</logic:equal>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="viewCcjg();return false" class="btn_ck" >查看</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false" class="btn_dc" >导出</a>
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
				<span>抽查结果列表&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
