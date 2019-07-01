<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xwhj/jg/js/hjjg.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption:"获奖信息确认列表 ",
					pager:"pager",
					url:"xpj_hjjg.do?method=hjjgList&type=query",
					colList : [
								{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
								{ label : '学号', name : 'xh', index : 'xh', width : '15%', formatter:xhLink },
								{ label : '姓名', name : 'xm', index : 'xm', width : '12%' },
								{ label : '年级', name : 'nj', index : 'nj', width : '8%' },
								{ label : '学院', name : 'xymc', index : 'xymc', width : '15%' },
								{ label : '奖项类别', name : 'jxlbmc', index : 'jxlbmc', width : '15%' },					
								{ label : '竞赛方式', name : 'jsfsmc', index : 'jsfsmc', width : '10%' },
								{ label : '奖项等级', name : 'jxdjmc', index : 'jxdjmc', width : '15%' },
								{ label : '奖项名次', name : 'jxmcmc', index : 'jxmcmc', width : '15%' },
								{ label : 'sjly', name : 'sjly', index : 'sjly', hidden : true },
								{ label : 'lylcywid', name : 'lylcywid', index : 'lylcywid', hidden : true}
							  ],
						sortname : "xh",
					    sortorder : "asc" 
				}
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpj_hjjg">
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
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">导入</a>
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
				<span>获奖信息确认列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
