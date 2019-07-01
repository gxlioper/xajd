<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/pjpy/xzhcp/sq/js/xzhcp.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "综合测评登记列表",
				pager : "pager",
				url : "xzhcp_zcdj.do?method=searchForZhcpDj",
				colList : [ {
					label : 'key',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				},{
					label : '学年',
					name : 'xn',
					index : 'xn',
					width : '5%'
				}
				<logic:notEqual name='xxdm' value= '10364'>
				,{
					label : '学期',
					name : 'xqmc',
					index : 'xqmc',
					width : '5%'
				}
				</logic:notEqual>
				, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '10%'
					,formatter : xhLink
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '10%'
				}, {
					label : '年级',
					name : 'nj',
					index : 'nj',
					width : '5%'
				}, {
					label : '学院',
					name : 'xymc',
					index : 'xydm',
					width : '10%'
				}, {
					label : '专业',
					name : 'zymc',
					index : 'zydm',
					width : '10%'
				},{
					label : '班级',
					name : 'bjmc',
					index : 'bjdm',
					width : '10%'
				},{
					label : '审核状态',
					name : 'shztmc',
					index : 'shztmc',
					width : '8%'
				},{
					label : 'shzt',
					name : 'shzt',
					index : 'shzt',
				    hidden:true
				},{
					label : 'splc',
					name : 'splc',
					index : 'splc',
				    hidden:true
				}
				],
				sortname : "sqsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);

			var isopen = jQuery("#isopen").val();
			if(isopen=="false"){
				jQuery("#prompt_isopen").show();
				jQuery("#prompt_null_isopen").show();
				return false;
			}
			if ("true" == isopen){
				jQuery("#prompt_isopen").hide();
				return false;
			}
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${sqkg}"/>
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
				<bean:message key="lable.jcszwcsh_prompt" />
			</p>
			<p id="prompt_false_isopen" style="display:none;">
				<bean:message key="lable.dqwkfsq_prompt" />
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		
		<html:form action="/xzhcp_zcdj">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<logic:equal value="true" name="sqkg">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false" class="btn_shuc" >提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false" class="btn_sr" >撤销</a>
						</li>
						</logic:equal>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcgz();return false;" 
								   title="选中一条记录，点击该按钮可以查看审核流程。"
								   class="btn_cs">流程跟踪</a>
						</li>	
						<logic:equal value="12673" name="xxdm">
							<li><a href="#" class="btn_dy" onclick="printDjb();return false;">综合素质登记表导出</a></li>	
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
				<span>综合测评登记列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
