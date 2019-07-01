<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/zzdgl/jg/js/zzdqr.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"学生转走读明细宿舍管理员待确认列表",
				pager:"pager",
				url:"xgygl_zdjg.do?method=getPageListForQr&type=query",
				colList : [
							{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'zzdid', name : 'zzdid', index : 'zzdid', hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
							{ label : '学号', name : 'xh', index : 'xh', width : '15%',formatter:xhLink },
							{ label : '姓名', name : 'xm', index : 'xm', width : '15%' },
							{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
							{ label : '学院', name : 'xymc', index : 'xymc', width : '15%' },
							{ label : '专业', name : 'zymc', index : 'zymc', width : '15%' },
							{ label : '班级', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : '学年学期', name : 'xnxq', index : 'xnxq', width : '10%' },
							{ label : '申请时间', name : 'sqsj', index : 'sqsj', width : '15%' },
							{ label : '管理员确认', name : 'qrztmc', index : 'qrztmc', width : '10%' },
							{ label : '审核状态', name : 'shzt', index : 'shzt', hidden : true}],
				params:{qrzt:"dsh"},
				sortname: "sqsj",
			 	sortorder: "desc",
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"学生转走读明细宿舍管理员已确认列表",
				pager:"pager",
				url:"xgygl_zdjg.do?method=getPageListForQr&type=query",
				colList : [
							{ label : 'jgid', name : 'jgid', index : 'jgid',key : true, hidden : true },
							{ label : 'zzdid', name : 'zzdid', index : 'zzdid', hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
							{ label : '学号', name : 'xh', index : 'xh', width : '15%',formatter:xhLink },
							{ label : '姓名', name : 'xm', index : 'xm', width : '15%' },
							{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
							{ label : '学院', name : 'xymc', index : 'xymc', width : '15%' },
							{ label : '专业', name : 'zymc', index : 'zymc', width : '15%' },
							{ label : '班级', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : '学年学期', name : 'xnxq', index : 'xnxq', width : '10%' },
							{ label : '申请时间', name : 'sqsj', index : 'sqsj', width : '15%' },
							{ label : '管理员确认', name : 'qrztmc', index : 'qrztmc', width : '10%' },
							{ label : '审核状态', name : 'shzt', index : 'shzt', hidden : true}],
				params:{qrzt:"ysh"},
				sortname: "sqsj",
			 	sortorder: "desc",
			 	radioselect:true
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["qrzt"]="dsh";
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
		<html:form action="/xgygl_zdqr">
			<input type="hidden" id="qrzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="qr();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">确认</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancel();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">确认取消</a>
						</li>	
						</logic:equal>					
						<%--<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					--%></ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>未处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生转走读宿舍管理员确认列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
