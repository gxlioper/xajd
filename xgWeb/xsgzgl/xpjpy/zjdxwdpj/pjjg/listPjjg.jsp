<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zjdxwdpj/pjjg/js/pjjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				pager:"pager",
				url:"xpjpy_pjjg.do?method=getPjjgList&type=query",
				colList:[
					{ label : 'key', name : 'id', index : 'id',key : true, hidden : true },
					{ label : 'sjly',name : 'sjly', index : 'sjly',hidden:true},
					{ label : '学号', name : 'xh', index : 'xh', width : '6%', formatter : xhLink},
					{ label : '姓名', name : 'xm', index : 'xm', width : '7%'},
					{ label : '性别', name : 'xb', index : 'xb', width : '1%'},
					{ label : '年级', name : 'nj', index : 'nj', width : '2%'},
					{ label : '班级', name : 'bjmc', index : 'bjdm', width : '11%'},
					{ label : '学年', name : 'xn', index : 'xn', width : '6%'},
					{ label : '项目名称', name : 'xmmc', index : 'xmmc', width : '11%'},
					{ label : '金额', name : 'xmje', index : 'xmje', width : '4%'}
				  ],
					sortname : "sqsj",
				    sortorder : "desc" }
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
		<html:form action="/xpjpy_pjjg">
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
								<a href="javascript:void(0);" class="btn_xg" onclick="update();return false;" >修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_sc" onclick="del();return false;" >删除</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_sz" onclick="scyxxs();return false;" >生成优秀学生</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_dr" onclick="importPjjg();return false;" >导入</a>
							</li>
						</logic:equal>
						
						<logic:equal value="zf01" name="userName">
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
				<span>评奖结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>