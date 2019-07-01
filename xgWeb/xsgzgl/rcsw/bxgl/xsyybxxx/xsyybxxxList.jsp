<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/bxgl/xsyybxxx/js/xsyybxxx.js"></script>
				<script type="text/javascript">
					jQuery(function(){
					     var gridSetting = {
									caption:"学生预约报销信息",
									pager:"pager",
									url:"rcsw_bxgl_xsyybx.do?method=xsyybxxxList&type=query",
									params:getSuperSearch(),
									colList:[
									   {label:'预约报销id',name:'yybxid', index: 'yybxid',key:true,hidden:true},
									   {label:'学号',name:'xh', index: 'xh', width : '10%',formatter:xhLink},
									   {label:'姓名',name:'xm', index: 'xm'},
									   {label:'性别',name:'xb', index: 'xb'},
									   {label:'年级',name:'nj', index: 'nj', width : '6%'},
									   {label:'系部',name:'xymc', index: 'xymc'},
									   {label:'专业',name:'zymc', index: 'zymc'},
									   {label:'班级',name:'bjmc', index: 'bjmc'},
									   {label:'预约时间',name:'yysj', index: 'yysj', width : '8%'},
									   {label:'办理内容',name:'blnr', index: 'blnr'}
									],
									sortname: "yysj",
								 	sortorder: "desc"
								}
							jQuery("#dataTable").initGrid(gridSetting);
					    	jQuery("#btn_zj").click(add);
							jQuery("#btn_xg").click(update);
							jQuery("#btn_sc").click(deletes);
					});
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/rcsw_bxgl_xsyybx?method=xsyybxxxList&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" id="btn_zj" class="btn_zj">填写</a></li>
						<li><a href="javascript:void(0);" id="btn_xg" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" id="btn_sc" class="btn_sc">删除</a></li>
					</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_dc">导出</a>
						</li>		   
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
				<span style="width: 50%"> 学生预约报销信息<a id="title" href="javascript:;" style="float:right;margin-right:30px;color: blue"></a> </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
