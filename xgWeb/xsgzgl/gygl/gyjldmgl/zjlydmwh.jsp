<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gyjldmgl/js/zjlydmwh.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "代码维护列表",
				pager : "pager",
				url : "gyglnew_gyjldmgl.do?method=gyglDmwhcx",
				colList : [ {
					label : 'key',
					name : 'gyjllbdldm',
					index : 'gyjllbdldm',
					key : true,
					hidden : true
				}, {
					label : '奖惩类别代码',
					name : 'gyjllbdldm',
					index : 'gyjllbdldm',
					width : '25%',
					formatter:xhLink
				}, {
					label : '奖惩类别名称',
					name : 'gyjllbdlmc',
					index : 'gyjllbdlmc',
					width : '25%'
				},{
					label : '类别',
					name : 'lb',
					index : 'lb',
					width : '25%'
				},{
					label : '分值',
					name : 'fz',
					index : 'fz',
					width : '25%'
				}],
				sortname : "gyjllbdldm",
				sortorder : "asc"
			}
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
		<html:form action="/gyglnew_gyjldmgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div style="display:none">
						<%@ include file="/comm/search/superSearchArea.jsp"%>
		  </div>
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
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
						</li>
						</logic:equal>
						<div class="btn" style="display:none">
									<button type="button" class="btn_cx" id="search_go" onclick="setStlbmc_stdmwh();searchRs();return false"">
										查 询
									</button>
						</div>
					</ul>
				</div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>代码维护列表 &nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
