<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/bxgl/bxbxsq/js/bxbxsq.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
				<script type="text/javascript">
					jQuery(function(){
					      var gridSetting = {
									caption:"学生保险报销申请信息",
									pager:"pager",
									url:"rcswBxglBxbxsq.do?method=bxbxsqList&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'sqid',name:'sqid', index: 'sqid',key:true,hidden:true},
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
									   {label:'类型',name:'lx', index: 'lx', width : '6%',hidden:true},
									   <logic:equal name="xxdm" value="13871">
									   {label:'发票时间',name:'csfysj', index: 'csfysj'},
									   </logic:equal>
									   <logic:notEqual name="xxdm" value="13871">
									   {label:'产生费用时间',name:'csfysj', index: 'csfysj'},
									   </logic:notEqual>
									   {label:'审核状态',name:'shztmc', index: 'shztmc',width : '6%'},
									   {label:'splc',name:'splc', index: 'splc',width : '6%',hidden:true},
									   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true}
									],
									sortname: "csfysj",
								 	sortorder: "desc"
								}
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
	<html:form action="/rcswBxglBxbxsq?method=bxbxsqList&type=query">
	<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" onclick="bxbxsqZj();return false;" class="btn_zj">申请</a></li>
						<li><a href="javascript:void(0);" onclick="bxbxsqXg();return false;" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="bxbxsqDel();return false;" class="btn_sc">删除</a></li>
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
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
					<logic:equal value="13023" name="xxdm">
						<li>
							<a href="javascript:void(0);" onclick="printBxbxZm();return false;" class="btn_dy">医保证明打印</a>
						</li>
					</logic:equal>		   
				</ul>
			</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span style="width: 50%"> 学生保险报销申请信息<a id="title" href="javascript:;" style="float:right;margin-right:30px;color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
