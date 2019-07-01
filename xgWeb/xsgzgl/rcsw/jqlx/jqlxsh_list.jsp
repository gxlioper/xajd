<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/jqlx/js/jqlxsh.js"></script>
		<script type="text/javascript">
			var lsxq = true;
			var sqly = false;
			if(jQuery('#xxdm').val="11647"){
				lsxq = false;
				sqly = true;
			}
			
			var gridSetting = {
					caption:"学生假期留校审核",
					pager:"pager",
					url:"rcsw_jqlx.do?method=jqlxShManage&type=query",
					colList:[
						{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						{label:'审批流程',name:'lcid', index: 'lcid',hidden:true},
						{label:'gwid',name:'gwid', index: 'gwid',hidden:true},
						{label:'审核Id',name:'shid', index: 'shid',hidden:true},
						{label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					    {label:'姓名',name:'xm', index: 'xm',width:'8%'},
					    {label:'性别',name:'xb', index: 'xb',width:'5%'},
					    {label:'民族',name:'mzmc', index: 'mz',width:'6%'},
					    {label:'行政班级',name:'bjmc', index: 'bjdm',width:'13%'},
                        {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'13%'},
					    {label:'联系电话',name:'sjhm', index: 'sjhm',width:'9%'},
					    {label:'申请理由',name:'sqly', index: 'sqly',width:'14%',hidden:sqly},
					    <logic:notEqual value="10344" name="xxdm">
					    {label:'留宿校区',name:'lsxqmc', index: 'lsxqmc',width:'14%',hidden:lsxq},
					    </logic:notEqual>
					    {label:'留校开始时间',name:'lxkssj', index: 'lxkssj',width:'11%'},
					    {label:'留校截止时间',name:'lxjzsj', index: 'lxjzsj',width:'11%'},
					    <logic:equal value="12309" name="xxdm">
					    {label:'辅导员',name:'fdyxm', index: 'fdyxm',width:'10%'},
					    </logic:equal>
					    {label:'审核状态',name:'shztmc', index: 'shztmc',width:'11%'}
					],
					params:{shzt:"dsh"},//默认待审核
					sortname: "sqsj",
				 	sortorder: "desc"
			}
	
			var gridSetting2 = {
					caption:"学生假期留校审核",
					pager:"pager",
					url:"rcsw_jqlx.do?method=jqlxShManage&type=query",
					colList:[
						{label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
						{label:'审批流程',name:'lcid', index: 'lcid',hidden:true},
						{label:'gwid',name:'gwid', index: 'gwid',hidden:true},
						{label:'审核Id',name:'shid', index: 'shid',hidden:true},
						{label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					    {label:'姓名',name:'xm', index: 'xm',width:'8%'},
					    {label:'性别',name:'xb', index: 'xb',width:'5%'},
					    {label:'民族',name:'mzmc', index: 'mz',width:'6%'},
					    {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
					    {label:'联系电话',name:'sjhm', index: 'sjhm',width:'9%'},
					    {label:'申请时间',name:'sqsj', index: 'sqsj',width:'14%',hidden:sqly},
					    <logic:notEqual value="10344" name="xxdm">
					    {label:'留宿校区',name:'lsxqmc', index: 'lsxqmc',width:'14%',hidden:lsxq},
					    </logic:notEqual>
					    {label:'留校开始时间',name:'lxkssj', index: 'lxkssj',width:'11%'},
					    {label:'留校截止时间',name:'lxjzsj', index: 'lxjzsj',width:'11%'},
					    <logic:equal value="12309" name="xxdm">
					    {label:'辅导员',name:'fdyxm', index: 'fdyxm',width:'10%'},
					    </logic:equal>
					    {label:'审核状态',name:'shztmc', index: 'shztmc',width:'11%'}
					],
					params:{shzt:"ysh"},//默认待审核
					sortname: "sqsj",
				 	sortorder: "desc"
			}
				
			jQuery(function(){
				var map = getSuperSearch();
					map["shzt"] = "dsh";
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
		<html:form action="/rcsw_rcxwwh_rcxwshgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="lxsqSh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelShnew();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="lcgzInfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a>
						</li>		
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
				<span>学生假期留校审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
