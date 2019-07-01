<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/rzkh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "校外住宿结果列表",
				pager : "pager",
				url : "szdw_xsgb_rzkhjg.do?method=rzkhjgList&type=query",
				colList : [ {
					label : 'key',
					name : 'khjgid',
					index : 'khjgid',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '10%',
					formatter : xhLink
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '12%'
				},{
					label : '学年',
					name : 'xn',
					index : 'xn',
					width : '10%'
				}, {
					label : '学期',
					name : 'xqmc',
					index : 'xq',
					width : '5%'
				}, {
					label : '所在大队',
					name : 'xymc',
					index : 'xydm',
					width : '10%'
				},{
					label : '所在区队',
					name : 'bjmc',
					index : 'bjdm',
					width : '10%'
				},{
					label : '担任职务',
					name : 'zwmcname',
					index : 'zwmc',
					width : '12%'
				},{
					label : '任职时间',
					name : 'rzsj',
					index : 'rzsjname',
					width : '10%'
				},{
					label : '个人自评',
					name : 'grzp',
					index : 'grzp',
					width : '10%'
				},{
					label : 'zydm',
					name : 'zymc',
					index : 'zydm',
					hidden : true
				},{
					label : 'nj',
					name : 'nj',
					index : 'nj',
					hidden : true
				}],
				sortname : "rzsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/szdw_xsgb_rzkhjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update()" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="KhjgViewv2();return false;" id="btn_ck" class="btn_ck">查看</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a></li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						<li><a href="#" class="btn_dy" onclick="printXyzsjgsqb();return false;">下载考核表</a></li>	
						<li><a href="#" class="btn_dy" onclick="printKhhzb();return false;">下载考核汇总表</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生干部任职考核结果&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
