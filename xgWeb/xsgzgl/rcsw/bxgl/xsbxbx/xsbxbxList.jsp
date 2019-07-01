<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/bxgl/xsbxbx/js/xsbxbx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- 导入功能需要 -->
				<script type="text/javascript">
					jQuery(function(){
					     var gridSetting = {
									caption:"学生保险报销信息",
									pager:"pager",
									url:"rcswBxglXsbxbx.do?method=xsbxbxList&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'报销id',name:'bxid', index: 'bxid',key:true,hidden:true},
									   {label:'学号',name:'xh', index: 'xh', width : '10%',formatter:xhLink},
									   {label:'姓名',name:'xm', index: 'xm'},
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
									   {label:'类型',name:'lxmc', index: 'lxmc'},
									   <logic:equal name="xxdm" value="13871">
									   {label:'发票时间',name:'csfysj', index: 'csfysj'},
									   </logic:equal>
									   <logic:notEqual name="xxdm" value="13871">
									   {label:'产生费用时间',name:'csfysj', index: 'csfysj'},
									   {label:'审核人',name:'bxshr', index: 'bxshr'},
									   </logic:notEqual>
									   {label:'sjly',name:'sjly', index: 'sjly',hidden:true}
									],
									sortname: "csfysj",
								 	sortorder: "desc"
								}
							jQuery("#dataTable").initGrid(gridSetting);
					});
					
					//新版导入
					function dr() {
						// 调用通用的导入function，参数是导入功能模块代码。
						toImportDataNew("IMPORT_N732502");
						return false;
					}
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/rcswBxglXsbxbx?method=xsbxbxList&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" onclick="addBx();return false;" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="updateBx();return false;" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="delBx();return false;" class="btn_sc">删除</a></li>
						<logic:notEqual name="xxdm" value="13871">
						<li><a href="javascript:void(0);" onclick="importBx();return false;" class="btn_dr">导入</a></li>
						</logic:notEqual>
						<logic:equal name="xxdm" value="13871">
						<li><a href="javascript:void(0);" onclick="dr();" class="btn_dr">导入</a></li>
						</logic:equal>
					</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a>
						</li>
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
				<span style="width: 50%"> 学生保险报销信息<a id="title" href="javascript:;" style="float:right;margin-right:30px;color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
