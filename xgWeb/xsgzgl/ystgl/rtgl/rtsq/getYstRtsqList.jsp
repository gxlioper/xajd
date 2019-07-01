<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/rtsq/js/rtsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/ystgl/rtgl/comm/js/comm.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "入团申请信息",
				pager : "pager",
				url : "ystglRtsq.do?method=getYstRtsqList&type=query",
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
					label : '艺术团名称',
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
					name : 'yxxn',
					index : 'yxxn',
					width : '10%'
				},{
					label : '审核状态',
					name : 'shztmc',
					index : 'shzt',
					width : '10%'
				},{
					label : 'ystid',
					name : 'ystid',
					index : 'ystid',
					hidden : true
				},{
					label : 'sqsj',
					name : 'sqsj',
					index : 'sqsj',
					hidden : true
				},{
					label : 'sqkg',
					name : 'sqkg',
					index : 'sqkg',
					hidden : true
				},{
					label : 'shzt',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},{
					label : 'splc',
					name : 'splc',
					index : 'splc',
					hidden : true
				}],
				sortname : "sqsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<input type="hidden" name="usertype" value="${usertype}">
		<input type="hidden" id="flag" value="wsq"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/ystglRtsq.do">
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
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false" class="btn_shuc" >提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false" class="btn_sr" >撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>	
						<logic:notEqual name="usertype" value="stu">
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:notEqual>
						<li>
							<a href="javascript:void(0);" onclick="getYstbmb();return false;" class="btn_dy">入团申请表</a>
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
				<span>入团申请信息&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
