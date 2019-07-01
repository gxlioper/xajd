<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/ylbx/js/ylbx.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "医疗保险",
				pager : "pager",
				url : "zjly_ylbx.do?method=getYlbxCx&type=query",
				colList : [ {
					label : 'key',
					name : 'id',
					index : 'id',
					key : true,
					hidden : true
				},{
					label : '学年',
					name : 'xn',
					index : 'xn',
					width : '8%'
				},{
					label : '班级',
					name : 'bjmc',
					index : 'bjmc',
					width : '10%'
				},{
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '10%',
					formatter : xhLink
				},{
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '6%'
				},{
					label : '证历本号',
					name : 'zlbh',
					index : 'zlbh',
					width : '8%'
				}/*,{
					label : '持证信息',
					name : 'czxx',
					index : 'czxx',
					width : '8%'
				},{
					label : '持证证件编号',
					name : 'czzjbh',
					index : 'czzjbh',
					width : '15%'
				},{
					label : '持证开始时间',
					name : 'czkssj',
					index : 'czkssj',
					width : '17%'
				},{
					label : '持证结束时间',
					name : 'czzzsj',
					index : 'czzzsj',
					width : '17%'
				}*/,{
					label : '参续保类别',
					name : 'cxblb',
					index : 'cxblb',
					width : '8%'
				},{
					label : '审核标志',
					name : 'shbz',
					index : 'shbz',
					hidden : true
				}]
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<input type="hidden" name="cxblb" id="cxblb" value=""/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zjly_ylbx">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="add()" class="btn_zj">增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="importXX();return false" class="btn_dr" >导入</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig('1');return false;">新参保导出</a></li>	
						<li><a href="#" class="btn_dc" onclick="exportConfig('0');return false;">续保导出</a></li>
					
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>医疗保险&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
