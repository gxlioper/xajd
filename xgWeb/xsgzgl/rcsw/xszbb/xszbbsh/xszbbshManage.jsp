<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xszbb/xszbbsh/js/xszbbshManage.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"证件补办审核",
				pager:"pager",
				url:"rcsw_xszbb_bbshgl.do?method=xszbbshManage&type=query",
				colList:[
					{label:'key',name:'bbsqid', index: 'bbsqid',key:true ,hidden:true},
					{label:'审批流程',name:'splc', index: 'splc',hidden:true},
					{label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
					{label:'姓名',name:'xm', index: 'xm',width:'10%'},
					{label:'年级',name:'nj', index: 'nj',width:'7%'},
					{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'12%'},
					{label:'专业',name:'zymc', index: 'zymc',width:'15%'},
					{label:'行政班级',name:'bjmc', index: 'bjdm',width:'15%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'15%'},
					{label:'申请时间',name:'sqsj', index: 'sqsj',width:'12%'},
					{label:'补办证件',name:'xszbblxmc', index: 'xszbblxmc',width:'10%'},
					{label:'审核状态',name:'shztmc', index: 'shztmc',width:'7%'},
					{label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
					{label:'补办证件代码',name:'xszbblxdm', index: 'xszbblxdm',hidden:true},
					{label:'审核Id',name:'shid', index: 'shid',hidden:true},
					{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"dsh"},//默认待审核
				sortname: "sqsj",
			 	sortorder: "desc",
			 	radioselect:false
			 		
		}

		var gridSetting2 = {
				caption:"证件补办审核",
				pager:"pager",
				url:"rcsw_xszbb_bbshgl.do?method=xszbbshManage&type=query",
				colList:[
					{label:'key',name:'bbsqid', index: 'bbsqid',key:true ,hidden:true},
					{label:'审批流程',name:'splc', index: 'splc',hidden:true},
					{label:'学号',name:'xh', index: 'xh',width:'14%',formatter:xhLink},
					{label:'姓名',name:'xm', index: 'xm',width:'10%'},
					{label:'性别',name:'xb', index: 'xb',width:'7%'},
                    {label:'行政班级',name:'bjmc', index: 'bjdm',width:'15%'},
                    {label:'专业班级',name:'zybjmc', index: 'zybjmc',width:'15%'},
					{label:'补办证件',name:'xszbblxmc', index: 'xszbblxmc',width:'12%'},
					{label:'申请补办时间',name:'sqsj', index: 'sqsj',width:'19%'},
					{label:'审核状态',name:'shztmc', index: 'shztmc',width:'16%'},
					{label:'审核状态名称',name:'shzt', index: 'shzt',hidden:true},
					{label:'补办证件代码',name:'xszbblxdm', index: 'xszbblxdm',hidden:true},
					{label:'审核Id',name:'shid', index: 'shid',hidden:true}
				],
				params:{shzt:"ysh"},//默认待审核
				sortname: "sqsj",
			 	sortorder: "desc",
			 	radioselect:true
		}
			
		jQuery(function(){
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
							<a href="javascript:void(0);" onclick="xszbbSh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelShnew();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="xszbbshLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
						<!-- <logic:equal name="writeAble" value="yes">	   
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:equal>		-->		
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
				<span>证件补办审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
