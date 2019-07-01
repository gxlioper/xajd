<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsxx/cxdd/jg/js/cxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "结果列表",
				pager : "pager",
				url : "cxdd_pyjg.do?method=getPyjgList&type=query",
				colList : [ {
					label : 'key',
					name : 'xsid',
					index : 'xsid',
					key : true,
					hidden : true
				}, {
					label : '学年',
					name : 'xn',
					index : 'xn',
					width : '8%'
				}, {
					label : '学期',
					name : 'xqmc',
					index : 'xq',
					width : '8%'
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
					width : '6%'
				},{
					label : '性别',
					name : 'xb',
					index : 'xb',
					width : '5%'
				},{
					label : '班级',
					name : 'bjmc',
					index : 'bjmc',
					width : '12%'
				},{
					label : '评价',
					name : 'cxdjmc',
					index : 'cxdjmc',
					width : '6%'
				},{
					label : '数据来源',
					name : 'sjly',
					index : 'sjly',
					hidden : true
				}],
				sortname : "tjsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		//下载成绩报告当
		function exportCjbgd(){
			var ids = jQuery("#dataTable").getSeletIds();
			var len = ids.length;
			if (len == 1) {
				var url = "cxdd_pyjg.do?method=exportCjbgd";
				url += "&xsid=" + ids;
				window.open(url);
			} else if (len == 0) {
				showAlertDivLayer("请选择您要下载的记录！");
				return false;
			} else {
				var url = "cxdd_pyjg.do?method=getCjbgdZip";
				url += "&value=" + ids;
				window.open(url);
			}
		}

		//开学信息设置
		function kxxxSz(){
			var url = "cxdd_pyjg.do?method=kxxxSz";
			var title = "开学信息设置";
			showDialog(title, 500, 350, url);
		}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/cxdd_pyjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="importConfig();return false" class="btn_dr" >导入</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						<logic:equal name="xxdm" value="70002">
							<li><a href="javascript:void(0);" onclick="exportCjbgd();return false;" class="btn_down">下载成绩报告单</a></li>
							<li><a href="javascript:void(0);" onclick="kxxxSz();return false;" class="btn_down">开学信息设置</a></li>
						</logic:equal>
					</ul>
				</div>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
