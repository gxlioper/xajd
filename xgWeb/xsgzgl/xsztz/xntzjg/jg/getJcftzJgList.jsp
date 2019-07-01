<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/xsztz/xntzjg/jg/js/jcftzjg.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" >
		jQuery(function() {
			var gridSetting = {
					caption : "评分组列表",
					//radioselect:true,
					pager : "pager",
					url : "jcftz_jg.do?method=getJcftzJgList&type=query",
					colList : [ 
					   {label : 'jgid',name : 'jgid',index : 'jgid',key:true ,hidden:true},					   
					   {label : '学号',name : 'xh',index : 'xh',width : '6%',formatter:xhLink}, 
					   {label : '姓名',name : 'xm',index : 'xm',width : '5%'},
					   {label : '班级',name : 'bjmc',index : 'bjmc',width : '7%'},
					   {label : '项目名称',name : 'xmmc',index : 'xmmc',width : '6%',formatter:xmmcLink},
					   {label : '项目</br>类型',name : 'csmsmc',index : 'csmsmc',width : '4%'},
					   {label : '项目</br>基础分',name : 'jcxf',index : 'jcxf',width : '4%'},
					   {label : '调整后</br>基础分',name : 'tzhjcf',index : 'tzhjcf',width : '4%'},
					   {label : '获得奖项',name : 'jxmc',index : 'jxmc',width : '6%'},
					   {label : '是否</br>缺勤',name : 'sfqqmc',index : 'sfqqmc',width : '4%'},
					   {label : '获得</br>总学分',name : 'zf',index : 'zf',width : '4%'},
					   {label : 'lylcywid',name : 'lylcywid',index : 'lylcywid',hidden:true},
					   {label : 'xmdm',name : 'xmdm',index : 'xmdm',hidden:true},
					   {label : 'xmckjgid',name : 'xmckjgid',index : 'xmckjgid',hidden:true},
					   {label : 'xsckjgid',name : 'xsckjgid',index : 'xsckjgid',hidden:true}
					   
					   <logic:equal name="xxdm" value="13627">
					   ,{label : '备注1',name : 'bz1',index : 'bz1',width : '10%'}
					   ,{label : '备注2',name : 'bz2',index : 'bz2',width : '10%'}
					   ,{label : '备注3',name : 'bz3',index : 'bz3',width : '10%'}
					   ,{label : '备注4',name : 'bz4',index : 'bz4',width : '10%'}
					   ,{label : '备注5',name : 'bz5',index : 'bz5',width : '10%'}
					   </logic:equal>
					   ],
					sortname : "xmdm",
					sortorder : "desc",
					shrinkToFit:false,    
					autoScroll: true,
					<logic:equal name="xxdm" value="13627">
					width:1400
					</logic:equal>
				}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);				
		});		
		</script>
		
	</head>
	<body>
	<html:form action="/jcftz_jg" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
<%--	<input type="hidden" id="sqkg" name="sqkg" value="${sqkg}"/>--%>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_xg" >学分认定</a></li>	
					<li><a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a></li>					
				</ul>
			</div>
			</logic:equal>
				
			<!-- 过滤条件 -->
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
		</div>
			<div class="formbox">
			<!--标题start-->
				<h3 class="datetitle_01">
					<span>校内拓展结果列表 </span>
				</h3>	
				<div class="con_overlfow">
				<table id="dataTable"></table>
				</div>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
