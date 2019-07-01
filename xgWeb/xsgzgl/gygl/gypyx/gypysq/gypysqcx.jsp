<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gypyx/gypysq/js/gypysq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "gypynew_gypysq.do?method=searchForSqList",
				colList : [ {
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : '楼栋',
					name : 'ldmc',
					index : 'ldmc',
					width : '10%'
					,formatter : qshLink
				}, {
					label : '寝室号',
					name : 'qsh',
					index : 'qsh',
					width : '10%'
				}, {
					label : '楼层',
					name : 'ch',
					index : 'ch',
					width : '10%'
				}, {
					label : '申请星级',
					name : 'sqxj',
					index : 'sqxj',
					width : '10%'
				}, {
					label : '是否为再次挂星',
					name : 'sfzcgx',
					index : 'sfzcgx',
					width : '10%'
				},{
					label : '申请时间',
					name : 'sqsj',
					index : 'sqsj',
					width : '17%'
				},{
					label : '审核状态',
					name : 'shztmc',
					index : 'shztmc',
					width : '17%'
				},{
					label : 'shzt',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				},
				{
					label : 'splc',
					name : 'splc',
					index : 'splc',
					hidden : true
				}]
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
			if('${splc}'==null||'${splc}'==''){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_null_isopen").show();
				return false;
			}
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<div class="prompt" id="prompt_isopen" style="display:none;">
			<h3>
				<span>提示：</span>
			</h3>
			<p id="prompt_null_isopen" style="display:none;">
				审核流程未设置，请联系管理员！
			</p>
			<p id="prompt_false_isopen" style="display:none;">
				<bean:message key="lable.dqwkfsq_prompt" />
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/gypynew_gypysq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:notEmpty name="splc">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="submitBusi();return false" class="btn_shuc" >提交</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
							</li>
						</logic:notEmpty>
						
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false" class="btn_sr" >撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>	
						<li><a href="#" class="btn_dy" onclick="printGxsqb();return false;">下载登记表</a></li>	
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
				<span>星级寝室申请列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
