<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/jqlx/js/jqlxsq.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"学生假期留校申请列表",
				pager:"pager",
				url:"rcsw_jqlxsq.do?method=jqlxSqManage&type=query",
				colList:[
				   {label:'key',name:'sqid', index: 'sqid',key:true ,hidden:true},
				   {label:'审批流程',name:'lcid', index: 'lcid',hidden:true},
				   {label:'审核状态',name:'sqzt', index: 'sqzt',hidden:true},
				   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
				   {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				   {label:'性别',name:'xb', index: 'xb',width:'6%'},
				   {label:'民族',name:'mzmc', index: 'mz',width:'6%'},
				   {label:'班级',name:'bjmc', index: 'bjdm',width:'15%'},
				   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'17%'},
				   {label:'留校开始时间',name:'lxkssj', index: 'lxkssj',width:'12%'},
				   {label:'留校截止时间',name:'lxjzsj', index: 'lxjzsj',width:'12%'},
				   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'10%'},
				   {label:'sqzt',name:'sqzt',index:'sqzt',hidden:true}
				],
				sortname: "sqsj",
			 	sortorder: "desc"
			}

			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);

				var isopen = jQuery("#isopen").val();
				if(isopen==null||isopen==''){
					jQuery("#prompt_isopen").show();
					jQuery("#prompt_null_isopen").show();
					return false;
				}
				if ("false" == isopen){
					jQuery("#prompt_isopen").show();
					jQuery("#prompt_false_isopen").show();
					return false;
				}
			});
		</script>
	</head>

	<body>
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
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_rcxwwh_rcxwxxwhgl">
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_jqlx"/>
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;">申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="lcgzInfo();return false;" class="btn_cs">流程跟踪</a>
						</li>
						<!-- 四川职业技术学院打印申请表 begin-->
						<logic:equal value="12970" name="xxdm">
							<li>
								<a href="javascript:void(0);" onclick="printjqlxsqb('rcsw_jqlx.do?method=printjqlxsqb');return false;" class="btn_down">下载申请表</a>
							</li>
						</logic:equal>
						<!-- 四川职业技术学院打印申请表 end-->						 
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>学生申请假期留校信息维护列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
