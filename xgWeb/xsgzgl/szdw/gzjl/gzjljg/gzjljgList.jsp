<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/gzjl/gzjljg/gzjljg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {};
			if("11842"==jQuery("#xxdm").val()){
				gridSetting = {
						caption : "工作记录列表",
						pager : "pager",
						url : "gzjljg.do?method=gzjljgList&type=query",
						colList : [ {
							label : 'key',
							name : 'jgid',
							index : 'jgid',
							key : true,
							hidden : true
						},{
							label : 'sjly',
							name : 'sjly',
							index : 'sjly',
							hidden : true
						}, {
							label : '职工号',
							name : 'zgh',
							index : 'zgh',
							width : '10%',
							formatter : zghLink
						},{
							label : '姓名',
							name : 'xm',
							index : 'xm',
							width : '10%'
						}, {
							label : '<bean:message key="lable.xb" />',
							name : 'xymc',
							index : 'xymc',
							width : '15%'
						}, {
							label : '工作类别',
							name : 'gzlbmc',
							index : 'gzlbmc',
							width : '10%'
						}, {
							label : '六困生',
							name : 'lksmc',
							index : 'lksmc',
							width : '10%'
						},{
							label : '工作时间',
							name : 'gzsj',
							index : 'gzsj',
							width : '10%'
						},{
							label : '记录时间',
							name : 'jlsj',
							index : 'jlsj',
							width : '20%'
						},{
							label : '备注',
							name : 'bz',
							index : 'bz',
							width : '10%',
							hidden : true
						},{
							label : '备注',
							name : 'bzstr',
							index : 'bzstr',
							width : '15%',
							formatter : setBz
						}],
						sortname : "gzsj",
						sortorder : "desc"
					}
			}else{
				gridSetting = {
						caption : "工作记录列表",
						pager : "pager",
						url : "gzjljg.do?method=gzjljgList&type=query",
						colList : [ {
							label : 'key',
							name : 'jgid',
							index : 'jgid',
							key : true,
							hidden : true
						},{
							label : 'sjly',
							name : 'sjly',
							index : 'sjly',
							hidden : true
						}, {
							label : '职工号',
							name : 'zgh',
							index : 'zgh',
							width : '10%',
							formatter : zghLink
						},{
							label : '姓名',
							name : 'xm',
							index : 'xm',
							width : '10%'
						}, {
							label : '<bean:message key="lable.xb" />',
							name : 'xymc',
							index : 'xymc',
							width : '15%'
						}, {
							label : '工作类别',
							name : 'gzlbmc',
							index : 'gzlbmc',
							width : '15%'
						}, {
							label : '工作时间',
							name : 'gzsj',
							index : 'gzsj',
							width : '15%'
						},{
							label : '记录时间',
							name : 'jlsj',
							index : 'jlsj',
							width : '20%'
						},{
							label : '备注',
							name : 'bz',
							index : 'bz',
							width : '10%',
							hidden : true
						},{
							label : '备注',
							name : 'bzstr',
							index : 'bzstr',
							width : '15%',
							formatter : setBz
						}],
						sortname : "gzsj",
						sortorder : "desc"
					}
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
		<html:form action="/gzjljg">
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >填写</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
						<logic:equal name="xxdm" value="11458">
						<logic:notEqual value="xx" name="userStatus">
						<logic:notEqual value="xy" name="userStatus">
						<li>
							<a href="javascript:void(0);"
								onclick="gzjltj('js');return false;" class="btn_dy">工作记录汇总表</a>
						</li>
						</logic:notEqual>
						</logic:notEqual>
						<logic:equal value="xy" name="userStatus">
						<li>
							<a href="javascript:void(0);"
								onclick="gzjltj('xy');return false;" class="btn_dy">院级工作记录汇总表</a>
						</li>
						</logic:equal>
						<logic:equal value="xx" name="userStatus">
						<li>
							<a href="javascript:void(0);"
								onclick="gzjltj('xx');return false;" class="btn_dy">校级工作记录汇总表</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="printGzjlb('gzjljg.do?method=printGzjlb');return false;" class="btn_down">学生事务工作登记表</a></li>
						</logic:equal>
						<%--浙江树人大学 五个一--%>
						<logic:equal name="xxdm" value="11842">
							<li>
								<a href="javascript:void(0);"
									onclick="gzjlb('z');return false;" class="btn_dy">周工作记录表</a>
							</li>
							<li>
								<a href="javascript:void(0);"
									onclick="gzjlb('y');return false;" class="btn_dy">月工作记录表</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>工作记录列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
