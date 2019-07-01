<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/jxgl/xnjxjg/js/xnjxjg.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "校内奖项申请列表",
				pager : "pager",
				url : "jxgl_xnjxjg.do?method=xnjxjgList&type=query",
				colList : [ {
					label : 'key',
					name : 'jgid',
					index : 'jgid',
					key : true,
					hidden : true
				}, {
					label : '学年',
					name : 'xn',
					index : 'xn',
					width:"9%"
				}, {
					label : '学期',
					name : 'xqmc',
					index : 'xq',
					width:"5%"
				}, {
					label : '项目名称',
					name : 'xmmc1',
					index : 'xmmc1',
					width : '13%'
				}, {
					label : '项目级别',
					name : 'xmjbmc',
					index : 'xmjbdm',
					width : '10%'
				},{
					label : '奖项名称',
					name : 'jxmc',
					index : 'jxdm',
					width : '10%'
				},{
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '7%',
					formatter : jxLink
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '班级',
					name : 'bjmc',
					index : 'bjdm',
					width : '7%'
				}, {
					label : '学院',
					name : 'xymc',
					index : 'xydm',
					hidden : true
				}, {
					label : '申请时间',
					name : 'sqsj',
					index : 'sqsj',
					width : '10%'
				},{
					name : 'shlc',
					index : 'shlc',
					hidden : true
				},{
					name : 'nj',
					index : 'nj',
					hidden : true
				},{
					name : 'xb',
					index : 'xb',
					hidden : true
				},{
					name : 'sskmmc',
					index : 'sskmdm',
					hidden : true
				},{
					name : 'xmkssj',
					index : 'xmkssj',
					hidden : true
				},{
					name : 'xmkssj',
					index : 'xmkssj',
					hidden : true
				},{
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},{
					name : 'xmdm',
					index : 'xmdm',
					hidden : true
				},{
					name : 'sjly1',
					index : 'sjly1',
					hidden : true
				},{
					name : 'xq',
					index : 'xq',
					hidden : true
				}
				],
				sortname : "sqsj",
				sortorder : "desc"
			}
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
		<html:form action="/jxgl_xnjxsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
						</li>						
						<li>
							<a href="javascript:void(0);" onclick="importXnjxjg();return false;" class="btn_dr" >导入</a>
						</li>	
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>奖项申报列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
