<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/jtgl/ystwh/js/ystwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "社团结果列表",
				pager : "pager",
				url : "ystglYstwh.do?method=getYstwhList&type=query",
				colList : [
							{ label : 'key', name : 'ystid', index : 'ystid',key : true, hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
							{ label : 'sqsj', name : 'sqsj', index : 'sqsj', hidden : true },
							{ label : 'sqkg', name : 'sqkg', index : 'sqkg', hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly', hidden : true },
							{ label : 'sqkssj', name : 'sqkssj', index : 'sqkssj', hidden : true },
							{ label : 'sqjssj', name : 'sqjssj', index : 'sqjssj', hidden : true },
							{ label : 'xmlbdm', name : 'xmlbdm', index : 'xmlbdm', hidden : true },
							{ label : '学年', name : 'xn', index : 'xn', width : '15%' },
							{ label : '艺术团名称', name : 'ystxmmc', index : 'ystxmmc', width : '15%',formatter : xmmcLink  },
							{ label : '艺术团类别', name : 'ystlbmc', index : 'ystlbmc', width : '10%' },
							{ label : '项目类别', name : 'xmlbmc', index : 'xmlbmc', width : '12%' },
							{ label : '有效学年', name : 'xn', index : 'xn', width : '12%' },
							{ label : '联系电话', name : 'lxdh', index : 'lxdh', width : '5%' },
							{ label : '指导老师', name : 'zdlsxm', index : 'zdlsxm', width : '12%' },
							{ label : '申请开关', name : 'sqkg', index : 'sqkg', width : '15%' ,formatter : setXmsz}
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
		<html:form action="/ystglYstwh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<logic:notEqual value="stu" name="usertype">
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
									<a href="javascript:void(0);" onclick="copyYstxx();return false;" class="btn_fz" >复制</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr">导入</a>
								</li>
							</logic:notEqual>
						</logic:equal>
							<li><a href="javascript:void(0);" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>社团结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
