<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/bxgl/bxbxsh/js/bxbxsh.js"></script>
		<script type="text/javascript">
			     var gridSetting = {
							caption:"学生保险报销审核信息",
							pager:"pager",
							url:"rcswBxglBxbxsh.do?method=bxbxshList&type=query",
							colList:[
							   {label:'sqid',name:'sqid', index: 'sqid',key:true,hidden:true},
							   {label : 'splc', name : 'splc', index : 'splc',hidden : true },
							   {label:'学号',name:'xh', index: 'xh', width : '10%',formatter:xhLink},
							   {label:'姓名',name:'xm', index: 'xm',width : '8%'},
							   <logic:notEqual name="xxdm" value="13871">
							   	{label:'证历本号',name:'zlbh', index: 'zlbh',width : '18%'},
							   </logic:notEqual>
							   <logic:equal name="xxdm" value="13871">
							   	{label:'门诊(住院)号',name:'zlbh', index: 'zlbh',width : '18%'},
							   </logic:equal>
							    <logic:notEqual name="xxdm" value="13871">
								   {label:'材料准备',name:'clzbmc', index: 'clzbmc', width : '6%'},
								   </logic:notEqual>
								   <logic:equal name="xxdm" value="13871">
								   {label:'治疗原因',name:'clzbmc', index: 'clzbmc', width : '6%'},
								   </logic:equal>
							   {label:'报销险种',name:'bxxzmc', index: 'bxxzmc', width : '6%'},
							   <logic:equal name="xxdm" value="13871">
							   {label:'医疗费用/元',name:'ylzd1', index: 'ylzd1', width : '6%'},
							   </logic:equal>
							   {label:'报销金额/元',name:'bxje', index: 'bxje', width : '6%'},
							   {label:'类型',name:'lxmc', index: 'lxmc', width : '6%'},
							   <logic:equal name="xxdm" value="13871">
							   {label:'发票时间',name:'csfysj', index: 'csfysj'},
							   </logic:equal>
							   <logic:notEqual name="xxdm" value="13871">
							   {label:'产生费用时间',name:'csfysj', index: 'csfysj'},
							   </logic:notEqual>
							   {label:'审核状态',name:'shztmc', index: 'shztmc',width : '6%'},
							   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
							   { label:'审核Id',name:'shid', index: 'shid',hidden:true},
								{ label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
							],
							params:{shzt:"dsh"},
							sortname: "csfysj",
						 	sortorder: "desc",
						 	radioselect:false
						}
			     var gridSetting2 = {
							caption:"学生保险报销审核信息",
							pager:"pager",
							url:"rcswBxglBxbxsh.do?method=bxbxshList&type=query",
							colList:[
							   {label:'sqid',name:'sqid', index: 'sqid',key:true,hidden:true},
							   { label : 'splc', name : 'splc', index : 'splc',hidden : true },
							   {label:'学号',name:'xh', index: 'xh', width : '10%',formatter:xhLink},
							   {label:'姓名',name:'xm', index: 'xm',width : '8%'},
							   {label:'证历本号',name:'zlbh', index: 'zlbh',width : '18%'},
							   {label:'材料准备',name:'clzbmc', index: 'clzbmc', width : '6%'},
							   {label:'报销险种',name:'bxxzmc', index: 'bxxzmc', width : '6%'},
							   {label:'报销金额/元',name:'bxje', index: 'bxje', width : '6%'},
							   {label:'类型',name:'lxmc', index: 'lxmc', width : '6%'},
							   <logic:equal name="xxdm" value="13871">
							   {label:'发票时间',name:'csfysj', index: 'csfysj'},
							   </logic:equal>
							   <logic:notEqual name="xxdm" value="13871">
							   {label:'产生费用时间',name:'csfysj', index: 'csfysj'},
							   </logic:notEqual>
							   {label:'审核状态',name:'shztmc', index: 'shztmc',width : '6%'},
							   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
							   { label:'审核Id',name:'shid', index: 'shid',hidden:true},
								{ label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
							],
							params:{shzt:"ysh"},
							sortname: "csfysj",
						 	sortorder: "desc"
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
	<html:form action="/rcswBxglBxbxsh?method=bxbxshList&type=query">
	        <input type="hidden" name="shkg" id="shkg" value="${shkg}"/>
			<input type="hidden" id="shzt" value="dsh"/>
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="bxsh();return false;" 
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
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span style="width: 50%"> 学生保险报销审核信息<a id="title" href="javascript:;" style="float:right;margin-right:30px;color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
