<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdjdsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"活动阶段审核列表",
				pager:"pager",
				url:"hdgl_hdjdsh.do?method=getHdjdshList&type=query",
				colList : [
			                {label:'活动id',name:'hdid', index: 'hdid',key:true,hidden:true},
			                {label:'阶段类型',name:'jdlx', index: 'jdlx',hidden:true},
			                {label:'报名类型',name:'bmlx', index: 'bmlx',hidden:true},
			                {label:'活动名称',name:'hdmc', index: 'hdmc',width:'18%'},
			                {label:'发布方',name:'fbf', index: 'fbf',width:'18%'},
			                {label:'活动类型',name:'hdlxmc', index: 'hdlcmc',width:'18%'},
			                {label:'报名类型',name:'bmlxmc', index: 'bmlxmc',width:'18%'},
			                {label:'活动时间',name:'hdsj', index: 'fbsj',width:'18%'},
			                {label:'活动阶段',name:'jdmc', index: 'jdmc',width:'10%'}
							],
			 	radioselect:false
		}
		
		jQuery(function(){
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
		<html:form action="/hdgl_hdjdsh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					  <logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="hdjdsh();return false;"
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>
					  </logic:equal>
					  <%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>--%>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>活动阶段审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
