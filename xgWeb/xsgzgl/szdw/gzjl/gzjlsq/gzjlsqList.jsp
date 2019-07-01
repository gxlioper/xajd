<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/gzjl/gzjlsq/gzjlsq.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {};
			if("11842"==jQuery("#xxdm").val()){
				gridSetting = {
						caption : "工作记录信息申请列表",
						pager : "pager",
						url : "gzjlsq.do?method=gzjlsqList&type=query",
						colList : [ {
							label : 'key',
							name : 'sqid',
							index : 'sqid',
							key : true,
							hidden : true
						},{
							label : '审批流程',
							name : 'splc',
							index : 'splc',
							hidden : true
						}, {
							label : '职工号',
							name : 'zgh',
							index : 'zgh',
							width : '10%',
							formatter : zghLink
						},{
							label : '姓名',
							name : 'xm',
							index : 'xm',
							width : '10%'
						}, {
							label : '<bean:message key="lable.xb" />',
							name : 'xymc',
							index : 'xymc',
							width : '15%'
						}, {
							label : '工作类别',
							name : 'gzlbmc',
							index : 'gzlbmc',
							width : '10%'
						}, {
							label : '六困生',
							name : 'lksmc',
							index : 'lksmc',
							width : '13%'
						}, {
							label : '工作时间',
							name : 'gzsj',
							index : 'gzsj',
							width : '12%'
						}, {
							label : '备注',
							name : 'bz',
							index : 'bz',
							width : '7%',
							hidden : true
						},{
							label : '备注',
							name : 'bzstr',
							index : 'bzstr',
							width : '10%',
							formatter : setBz
						},{
							label : '记录时间',
							name : 'jlsj',
							index : 'jlsj',
							width : '20%'
						}, {
							label : '审核状态',
							name : 'shztmc',
							index : 'shztmc',
							width : '4%'
						}, {
							label : '审批流程',
							name : 'splc',
							index : 'splc',
							hidden : true
						}, {
							label : '审核状态',
							name : 'shzt',
							index : 'shzt',
							hidden : true
						}],
						sortname : "sqsj",
						sortorder : "desc"
					}
			   }else{
				   gridSetting = {
							caption : "工作记录信息申请列表",
							pager : "pager",
							url : "gzjlsq.do?method=gzjlsqList&type=query",
							colList : [ {
								label : 'key',
								name : 'sqid',
								index : 'sqid',
								key : true,
								hidden : true
							},{
								label : '审批流程',
								name : 'splc',
								index : 'splc',
								hidden : true
							}, {
								label : '职工号',
								name : 'zgh',
								index : 'zgh',
								width : '10%',
								formatter : zghLink
							},{
								label : '姓名',
								name : 'xm',
								index : 'xm',
								width : '10%'
							}, {
								label : '<bean:message key="lable.xb" />',
								name : 'xymc',
								index : 'xymc',
								width : '15%'
							}, {
								label : '工作类别',
								name : 'gzlbmc',
								index : 'gzlbmc',
								width : '20%'
							},  {
								label : '工作时间',
								name : 'gzsj',
								index : 'gzsj',
								width : '15%'
							}, {
								label : '备注',
								name : 'bz',
								index : 'bz',
								width : '7%',
								hidden : true
							},{
								label : '备注',
								name : 'bzstr',
								index : 'bzstr',
								width : '10%',
								formatter : setBz
							},{
								label : '记录时间',
								name : 'jlsj',
								index : 'jlsj',
								width : '20%'
							}, {
								label : '审核状态',
								name : 'shztmc',
								index : 'shztmc',
								width : '4%'
							}, {
								label : '审批流程',
								name : 'splc',
								index : 'splc',
								hidden : true
							}, {
								label : '审核状态',
								name : 'shzt',
								index : 'shzt',
								hidden : true
							}],
							sortname : "sqsj",
							sortorder : "desc"
						}
			   }
			    
			var map = getSuperSearch();
			gridSetting["params"] = map;
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
		<html:form action="/gzjlsq">
			<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >填写</a>
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
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>工作记录信息申请列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
