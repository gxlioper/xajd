<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/zzdgl/sq/js/zzdsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "项目申报列表",
				pager : "pager",
				url : "xgygl_zdsq.do?method=getZzdsqList&type=query",
				colList : [
							{ label : 'zzdid', name : 'zzdid', index : 'zzdid',key : true, hidden : true },
							{ label : 'splcid', name : 'splcid', index : 'splcid',hidden : true },
							{ label : '学号', name : 'xh', index : 'xh', width : '15%',formatter:xhLink },
							{ label : '姓名', name : 'xm', index : 'xm', width : '10%' },
							{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
							{ label : '学院', name : 'xymc', index : 'xymc', width : '15%' },
							{ label : '专业', name : 'zymc', index : 'zymc', width : '15%' },
							{ label : '班级', name : 'bjmc', index : 'bjmc', width : '15%' },
							{ label : '学年学期', name : 'xnxq', index : 'xnxq', width : '10%' },
							{ label : '申请时间', name : 'sqsj', index : 'sqsj', width : '15%' },
							{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '10%' },
							{ label : '审核状态', name : 'shzt', index : 'shzt', hidden : true}],
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
		<html:form action="/xgygl_zdsq">
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
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>	
										
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
							<li><a href="javascript:void(0);" onclick="printzzdsqb('xgygl_zdsq.do?method=printzzdsqb');return false;" class="btn_down">下载登记表</a></li>	
						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生转走读明细申请列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
