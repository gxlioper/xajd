<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/js/xdsh.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"",
				pager:"pager",
				url:"zxdkDkjg.do?method=getXdshList&type=query",
				colList:[
					{label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'审批流程',name:'splc', index: 'splc',hidden:true},
					{label:'续贷学年',name:'xdxn', index: 'xdxn',width:'5%'},
					{label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
					{label:'姓名',name:'xm', index: 'xm',width:'5%'},
					{label:'学院',name:'xymc', index: 'xymc',width:'10%'},
					{label:'班级',name:'bjmc', index: 'bjmc',width:'10%'},
					{label:'合同编号',name:'htbh', index: 'htbh',width:'10%'},
					{label:'贷款总金额',name:'dkje', index: 'dkje',width:'6%'},
					{label:'累计放贷金额',name:'fkze', index: 'fkze',width:'6%'},
					{label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
					{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					{label:'审核Id',name:'shid', index: 'shid',hidden:true},
					{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"dsh"},
				/*
				sortname: "sqsj",
			 	sortorder: "desc",
			 	sortname: "xymc",
			 	sortorder: "asc",
			 	sortname: "bjmc",
			 	sortorder: "asc",
			 	sortname: "xh",
			 	sortorder: "asc",*/
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"",
				pager:"pager",
				url:"zxdkDkjg.do?method=getXdshList&type=query",
				colList:[
					{label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'审批流程',name:'splc', index: 'splc',hidden:true},
					{label:'续贷学年',name:'xdxn', index: 'xdxn',width:'5%'},
					{label:'学号',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
					{label:'姓名',name:'xm', index: 'xm',width:'5%'},
					{label:'学院',name:'xymc', index: 'xymc',width:'10%'},
					{label:'班级',name:'bjmc', index: 'bjmc',width:'10%'},
					{label:'合同编号',name:'htbh', index: 'htbh',width:'10%'},
					{label:'贷款总金额',name:'dkje', index: 'dkje',width:'6%'},
					{label:'累计放贷金额',name:'fkze', index: 'fkze',width:'6%'},
					{label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'},
					{label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					{label:'审核Id',name:'shid', index: 'shid',hidden:true},
					{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
				],
				params:{shzt:"ysh"},
				/*
				sortname: "sqsj",
			 	sortorder: "desc",
			 	sortname: "xymc",
			 	sortorder: "asc",
			 	sortname: "bjmc",
			 	sortorder: "asc",
			 	sortname: "xh",
			 	sortorder: "asc",*/
			 	radioselect:true
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["shzt"]="dsh";
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
		<html:form action="/gygl_xyzsshgl">
			<input type="hidden" name="shkg" id="shkg" value="${shkg}"/>
			<input type="hidden" id="shzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="dgsh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="splcInfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
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
				<span>续贷审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
