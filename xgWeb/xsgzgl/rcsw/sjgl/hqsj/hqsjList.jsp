<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/sjgl/hqsj/js/hqsj.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
					caption:"考勤数据列表",
					pager:"pager",
					url:"hqsj.do?method=hqsjManage&type=query",
					colList:[
					    {label:'key',name:'id', index: 'id',key:true ,hidden:true},
						{label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
						{label:'姓名',name:'xm', index: 'xm',width:'10%'},						
						{label:'学年',name:'xn', index: 'xn',width:'10%'},
						{label:'学期',name:'xqmc', index: 'xq',width:'5%'},
						{label:'班级',name:'bjmc', index: 'bjdm',width:'25%'},
						{label:'寝室脏乱差次数',name:'qszlccs', index: 'qszlccs',width:'10%'},
					    {label:'优秀寝室次数',name:'yxqscs', index: 'yxqscs',width:'10%'},
						{label:'优秀寝室加分',name:'yxqsjf', index: 'yxqsjf',width:'10%'},
						{label:'寝室长',name:'qsz', index: 'qsz',width:'8%'}					
					],
					sortname: "xh",
				 	sortorder: "desc"
				}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		
		</script>
	</head>

	<body style="min-height: 800px;">
	
		<input type="hidden" value="dsh" id="shzt"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/hqsj">
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
								<a href="javascript:void(0);" onclick="importHqsj();return false;" class="btn_dr"
								>导入</a>
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
				<span>后勤数据维护列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
