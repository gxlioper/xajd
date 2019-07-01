<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/rcxwmark/js/rcxwmark.js"></script>
		<script type="text/javascript">
		var gridSetting = {};
		
		jQuery(function(){
			if('${cxlx}' == 'wclcx'){
				gridSetting = {
						caption : "未处理列表",
						pager : "pager",
						url : "rcsw_rcxwmark.do?method=getWclCx&type=query",
						colList : [ {
							label : 'key',
							name : 'rcxwjgid',
							index : 'rcxwjgid',
							key : true,
							hidden : true
						}, {
							label : '学号',
							name : 'xh',
							index : 'xh',
							width : '10%',
							formatter : xhLink
						}, {
							label : '姓名',
							name : 'xm',
							index : 'xm',
							width : '6%'
						}, {
							label : '班级',
							name : 'bjmc',
							index :'bjmc',
							width : '15%'
						}, {
							label : '学年',
							name : 'xn',
							index : 'xn',
							width : '10%'
						}, {
							label : '行为大类',
							name : 'rcxwlbdlmc',
							index : 'rcxwlbdlmc',
							width : '10%'
						},{
							label : '行为类别',
							name : 'rcxwlbmc',
							index : 'rcxwlbmc',
							width : '10%'
						},{
							label : '评定分值',
							name : 'fz',
							index : 'fz',
							width : '5%'
						}],
						sortname : "xn",
						sortorder : "desc"
					}
			  }else{
				
					gridSetting = {
							caption : "已处理列表",
							pager : "pager",
							url : "rcsw_rcxwmark.do?method=getYclCx&type=query",
							colList : [{
								label : 'id',
								name : 'id',
								index :'id',
								hidden : true,
								key : true
							},{
								label : 'key',
								name : 'rcxwjgid',
								index : 'rcxwjgid',
								hidden : true
							}, {
								label : '学号',
								name : 'xh',
								index : 'xh',
								width : '10%',
							   formatter : xhLink
							}, {
								label : '姓名',
								name : 'xm',
								index : 'xm',
								width : '6%'
							}, {
								label : '班级',
								name : 'bjmc',
								index :'bjmc',
								width : '5%'
							}, {
								label : '评奖学年',
								name : 'xn',
								index : 'xn',
								width : '5%'
							}, {
								label : '行为大类',
								name : 'rcxwlbdlmc',
								index : 'rcxwlbdlmc',
								width : '10%'
							},{
								label : '行为类别',
								name : 'rcxwlbmc',
								index : 'rcxwlbmc',
								width : '10%'
							},{
								label : '评定分值',
								name : 'fz',
								index : 'fz',
								width : '5%'
							},{
								label : '设置奖项',
								name : 'xmmc',
								index : '',
								width : '5%'
							}],
							sortname : "xn",
							sortorder : "desc"
						}
			}
			   
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		}
		)
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<input type="hidden" id="cxlx" value="${cxlx}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/gygl_xyzsjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:equal name="cxlx" value="wclcx">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="sz();return false;"  >设置</a>
						    </li>
						</logic:equal>
						<logic:equal name="cxlx" value="yclcx">
							<li>
							  <a href="javascript:void(0);" class="btn_qxsh" onclick="qxsz();return false;"  >取消设置</a>
						    </li>
						    <li>
							 <a href="javascript:void(0);" onclick="xg();return false;" class="btn_xg" >修改</a>
						    </li>
						</logic:equal>
					   </logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			      <logic:equal name="cxlx" value="wclcx">
				     <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wsz');"><span>未设置</span></a></li>
			         <li><a href="javascript:void(0);" onclick="selectTab(this,'ysz');"><span>已设置</span></a></li>
			      </logic:equal>
			      <logic:notEqual name="cxlx" value="wclcx">
			      	 <li ><a href="javascript:void(0);" onclick="selectTab(this,'wsz');"><span>未设置</span></a></li>
			         <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'ysz');"><span>已设置</span></a></li>
			      </logic:notEqual>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>其他奖项设置列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
