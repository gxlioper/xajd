<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zyjgl/grzyjwh/js/grzyjwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
					caption:"专业奖信息维护列表",
					pager:"pager",
					url:"pjpy_zyjwhwh.do?method=grzyjwhManage&type=query",
					colList:[
					    {label:'key',name:'id', index: 'id',key:true ,hidden:true},
						{label:'学号',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
						{label:'姓名',name:'xm', index: 'xm',width:'6%'},
						{label:'性别',name:'xb', index: 'xb',width:'3%'},
						{label:'班级',name:'bjmc', index: 'bjdm',width:'8%'},
						{label:'学院',name:'xymc', index: 'xymc',width:'8%'},
					    {label:'比赛名称',name:'bsmc', index: 'bsmc',width:'13%'},
					    {label:'主办单位',name:'zbdw', index: 'zbdw',width:'8%'},
					    {label:'认定等级',name:'rddjmc', index: 'rddjmc',width:'6%'},
					    {label:'获奖时间',name:'hjsj', index: 'hjsj',hidden:true}
					],
					sortname: "hjsj",
				 	sortorder: "desc"
				}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		
		</script>
	</head>

	<body style="min-height: 800px;">
	
		<input type="hidden" value="dsh" id="shzt"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/pjpy_zyjwhwh">
		<%--<html:hidden property="sqzq" styleId="sqzq" value="${sqzq}"/>--%>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">				
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
							</li>
							
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
							</li>
							
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
							</li>
								<logic:notEqual value="stu" name="userType">				
									<li><a href="#" class="btn_dc" onclick="rending();return false;">认定</a></li>
								</logic:notEqual>			
						</logic:equal>					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>专业奖信息维护列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
