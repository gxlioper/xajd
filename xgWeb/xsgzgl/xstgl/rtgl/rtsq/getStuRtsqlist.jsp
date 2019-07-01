<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/rtgl/rtsq/js/rtsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var map = getSuperSearch();
			map["flag"]="wsq";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		var gridSetting = {
				caption : "入团申请信息",
				pager : "pager",
				url : "stglRtsq.do?method=getStuRtsqList&type=query",
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
					width : '6%'
				},{
					label : '班级',
					name : 'bjmc',
					index : 'bjdm',
					width : '15%'
				}, {
					label : '专业',
					name : 'zymc',
					index : 'zydm',
					width : '15%'
				}, {
					label : '学院',
					name : 'xymc',
					index : 'xydm',
					width : '10%'
				}, {
					label : '性别',
					name : 'xb',
					index : 'xb',
					hidden : true
				},{
					label : '社团类别',
					name : 'stlbmc',
					index : 'stlbmc',
					width : '15%'
				},{
					label : '项目类别',
					name : 'xmlbmc',
					index : 'xmlbmc',
					hidden : true
				},{
					label : '社团项目名称',
					name : 'stxmmc',
					index : 'stxmmc',
					width : '15%'
				},
//					{
//					label : '指导老师',
//					name : 'zdlsxm',
//					index : 'zdlsxm',
//					width : '10%'
//				},
                    <logic:equal value="12872" name = "xxdm">
                    { label : '社团星级', name : 'stxj', index : 'stxj', width : '5%' },
                    </logic:equal>
					{
					label : '学年',
					name : 'xn',
					index : 'xn',
					width : '10%'
				},{
					label : '审核状态',
					name : 'shztmc',
					index : 'shzt',
					width : '10%'
				},{
					label : 'stid',
					name : 'stid',
					index : 'stid',
					hidden : true
				},{
					label : 'sqkg',
					name : 'sqkg',
					index : 'sqkg',
					hidden : true
				},{
					label : 'sqsj',
					name : 'sqsj',
					index : 'sqsj',
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
				params:{flag:"wsq"},
				sortname : "sqsj",
				sortorder : "desc"
			};
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
		<html:form action="/stglRtjg">
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
							<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">导入</a></li>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:notEqual>
						<li>
							<a href="javascript:void(0);" onclick="btn_dy();return false;" class="btn_dr">入团申请表</a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wsq');"><span>未申请项目</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysq');"><span>已申请项目</span></a></li>
			      </ul>
			    </div>
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
