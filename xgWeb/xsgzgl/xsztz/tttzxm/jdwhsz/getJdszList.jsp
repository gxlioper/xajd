<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/jdwhsz/js/jdwhsz.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "项目阶段维护列表",
				pager : "pager",
				url : "grttxm_jdsz.do?method=getJdszList&type=query",
				colList : [ {
					label : 'key',
					name : 'jdid',
					index : 'jdid',
					key : true,
					hidden : true
				}, {
					label : 'key',
					name : 'xmdm',
					index : 'xmdm',
					hidden : true
				}, {
					label : '项目名称',
					name : 'xmmc',
					index : 'xmmc',
					width : '10%',
				    formatter:xmmcLink
				}, {
					label : '项目类型',
					name : 'csmsmc',
					index : 'csmsmc',
					width : '10%'
					
				},{
					label : '项目级别',
					name : 'xmjbmc',
					index : 'xmjbmc',
					width : '10%'
				}, {
					label : '所属科目',
					name : 'sskmmc',
					index : 'sskmmc',
					width : '10%'
				}, {
					label : '项目开始时间',
					name : 'xmkssj',
					index : 'xmkssj',
					width : '10%'
				},{
					label : '项目阶段',
					name : 'jdmc',
					index : 'jdmc',
					width : '10%'
				},
				{
					label : '成员数（人/团）',
					name : 'jdcynum',
					index : 'jdcynum',
					width : '5%'
				},
				{
					label : '认定状态',
					name : 'rdzt',
					index : 'rdzt',
					width : '5%'
				}
				],
				sortname : "xn,xq,xmjbdm,sskmdm,sbbmdm,xmdm,to_number(jdsx)",
				sortorder : "desc"
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
		<html:form action="/gygl_xyzsjggl">
			
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="jdwhsz();return false;" class="btn_xg" >成员维护</a>
						</li>
						<li><a href="#" class="btn_dr" onclick="drjdwhcy();return false;">导入</a></li>	
						</logic:equal>
						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		<%@include file="/comm/hiddenValue.jsp"%></html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>项目阶段维护列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
