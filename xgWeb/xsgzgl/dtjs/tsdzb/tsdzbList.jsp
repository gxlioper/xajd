<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/tsdzb/js/tsdzb.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "特色党支部申报列表",
				pager : "pager",
				url : "dtjs_tsdzb.do?method=getTsdzbList&type=query",
				colList : [
							{ label : 'dzbid', name : 'dzbid', index : 'dzbid',key : true, hidden : true },
							{ label : '党支部', name : 'dzbmc', index : 'dzbmc', width : '15%',formatter:xhLink },
							{ label : '负责人', name : 'fzr', index : 'fzr', width : '10%' },
							{ label : '联系方式', name : 'lxfs', index : 'lxfs', width : '15%' },
							{ label : '创建时间', name : 'cjsj', index : 'cjsj', width : '15%' },
							{ label : '管辖班级', name : 'bmmc', index : 'bmmc', width : '35%' },
							{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '5%' },
							{ label : 'shzt', name : 'shzt', index : 'shzt', hidden : true },
							{ label : '评分', name : 'pf', index : 'pf', width : '5%' }
						  ]
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
		<html:form action="/dtjs_tsdzb">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >申请</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
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
				<span>特色党支部申报列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
