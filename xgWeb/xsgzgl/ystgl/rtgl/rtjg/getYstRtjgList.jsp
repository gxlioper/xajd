<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/rtjg/js/rtjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/comm/js/comm.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "入团结果信息",
				pager : "pager",
				url : "ystglRtjg.do?method=getYstRtjgList&type=query",
				colList : [ {
					label : 'key',
					name : 'rtid',
					index : 'rtid',
					key : true,
					hidden : true
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
					width : '10%'
				},{
					label : '艺术团类别',
					name : 'ystlbmc',
					index : 'ystlbmc',
					width : '15%'
				},{
					label : '项目类别',
					name : 'xmlbmc',
					index : 'xmlbmc',
					width : '15%'
					//hidden : true
				},{
					label : '艺术团项目名称',
					name : 'ystxmmc',
					index : 'ystxmmc',
					width : '15%'
				},{
					label : '指导老师',
					name : 'zdlsxm',
					index : 'zdlsxm',
					width : '10%'
				},{
					label : '有效学年',
					name : 'xn',
					index : 'xn',
					width : '10%'
				},{
					label : '状态',
					name : 'tnzt',
					index : 'tnzt',
					width : '6%'
				},{
					label : 'ystid',
					name : 'ystid',
					index : 'ystid',
					hidden : true
				},{
					label : 'sjly',
					name : 'sjly',
					index : 'sjly',
					hidden : true
				},{
					label : 'sqsj',
					name : 'sqsj',
					index : 'sqsj',
					hidden : true
				}],
				sortname : "tnzt desc,rtsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="usertype" value="${usertype}">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/ystglRtjg.do">
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
						<logic:notEqual name="usertype" value="stu">
						<li>
							<a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a>
						</li>
						</logic:notEqual>
						</logic:equal>
						<logic:notEqual name="usertype" value="stu">
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:notEqual>
						<li>
							<a href="javascript:void(0);" onclick="getYstbmb();return false;" class="btn_dy">入团登记表</a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>入团结果信息&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
