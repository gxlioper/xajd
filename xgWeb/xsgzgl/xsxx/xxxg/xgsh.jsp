<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xxxg/js/xgsh.js"></script>
		<script type="text/javascript">

		var gridSetting = {
				caption : "学生信息修改审核列表",
				pager : "pager",
				url : "xsxx_xsxxxg.do?method=xgsh&type=query",
				colList : [ {
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '12%',
					formatter : setXh
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '10%'
				}, {
					label : '性别',
					name : 'xb',
					index : 'xb',
					width : '5%'
				}, {
					label : '<bean:message key="lable.xb" />',
					name : 'xymc',
					index : 'xydm',
					width : '20%'
				}, {
					label : '班级',
					name : 'bjmc',
					index : 'bjdm',
					width : '20%'
				}, 
				<logic:equal name='xxdm' value='12309'>
				{
					label : '辅导员',
					name : 'fdy',
					index : 'fdy',
					width : '10%'
				}, 
				</logic:equal>
				{
					label : '申请时间',
					name : 'xgsj',
					index : 'xgsj',
					width : '18%'
				}, {
					label : 'gwid',
					name : 'gwid',
					index : 'gwid',
					hidden : true
				}, {
					label : 'lcid',
					name : 'lcid',
					index : 'lcid',
					hidden : true
				}, {
					label : 'ywid',
					name : 'ywid',
					index : 'ywid',
					hidden : true
				}, {
					label : '审核岗位',
					name : 'mc',
					index : 'mc',
					hidden : true
				}, {
					label : '审核id',
					name : 'guid',
					index : 'guid',
					hidden : true
				}, {
					label : '审核状态',
					name : 'shzt',
					index : 'shzt',
					width : '15%',
					formatter : setShzt
				} ],
				params : {
					shzt : "dsh"
				},// 默认待审核
				sortname : "xgsj",
				sortorder : "desc"
			}
			var gridSetting2 = {
				caption : "学生信息修改审核列表",
				pager : "pager",
				url : "xsxx_xsxxxg.do?method=xgsh&type=query",
				colList : [ {
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '12%',
					formatter : setXh
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '10%'
				}, {
					label : '性别',
					name : 'xb',
					index : 'xb',
					width : '5%'
				}, {
					label : '<bean:message key="lable.xb" />',
					name : 'xymc',
					index : 'xydm',
					width : '15%'
				}, {
					label : '班级',
					name : 'bjmc',
					index : 'bjdm',
					width : '20%'
				},
				<logic:equal name='xxdm' value='12309'>
				{
					label : '辅导员',
					name : 'fdy',
					index : 'fdy',
					width : '10%'
				}, 
				</logic:equal>
				 {
					label : '审核时间',
					name : 'shsj',
					index : 'shsj',
					width : '18%'
				}, {
					label : 'gwid',
					name : 'gwid',
					index : 'gwid',
					hidden : true
				}, {
					label : '审核id',
					name : 'guid',
					index : 'guid',
					hidden : true
				}, {
					label : 'lcid',
					name : 'lcid',
					index : 'lcid',
					hidden : true
				}, {
					label : 'ywid',
					name : 'ywid',
					index : 'ywid',
					hidden : true
				}, {
					label : '审核岗位',
					name : 'mc',
					index : 'mc',
					hidden : true
				}, {
					label : '审核状态',
					name : 'shzt',
					index : 'shzt',
					width : '15%',
					formatter : setShzt

				} ],
				params : {
					shzt : "ysh"
				},// 已审核
				sortname : "shsj",
				sortorder : "desc"
			};
			jQuery(function() {
				var map = getSuperSearch();
				map["shzt"] = "dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
		
		</script>
	</head>

	<body style="min-height: 800px;">
	
		<input type="hidden" value="dsh" id="shzt"/>
		<input type="hidden" name="shkg" id="shkg" value='${shkg}'/>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xszz_jtqkdc">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
					
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="xgshZj();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cxshnew();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>		
						</logic:equal>				
						<li><a href="javascript:void(0);" onclick="shlcInfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>
							   
						<logic:equal value="10335" name="xxdm">
							<logic:equal value="zf01" name="userName">
								<li>
									<a href="#" class="btn_dc" onclick="exportConfigSh();return false;">导出</a>
								</li>
							</logic:equal>
						</logic:equal>
						<logic:notEqual value="10335" name="xxdm">
							<li>
								<a href="#" class="btn_dc" onclick="exportConfigSh();return false;">导出</a>
							</li>
						</logic:notEqual>
		
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>信息修改审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
