<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xhlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dagl/xzmzdx/dazcsq/js/dazcsq.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"学生评价自主申请",
					pager:"pager",
					url:"dagl_dazcsq.do?method=dazcsqList",
					colList:[
					   {label:'sqid',name:'sqid', index: 'sqid',key:true,hidden:true},
					   {label:'审批流程id',name:'splcid', index: 'splcid',hidden:true},
					   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
					   {label:'数据录入时间',name:'sjlrsj', index: 'sjlrsj',hidden:true},
					   {label:'学号',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'6%'},
					   {label:'年级',name:'nj', index: 'nj',width:'4%'},
					   {label:'学院',name:'xymc', index: 'xymc',width:'8%'},
					   {label:'专业',name:'zymc', index: 'zymc',width:'8%'},
					   {label:'班级',name:'bjmc', index: 'bjmc',width:'8%'},
					   {label:'转出方式',name:'zcfsmc', index: 'zcfsmc',width:'4%'},
					   {label:'审核状态',name:'shztmc', index: 'shztmc',width:'6%'}
					],
					sortname: "sjlrsj",
				 	sortorder: "desc"
			};
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
				if('${splc}' == null || '${splc}' == ''){
					jQuery("#prompt_isopen").show();
					jQuery("#prompt_null_isopen").show();
					return false;
				};
			})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
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
		<html:form action="/dagl_dazcsq">
			<input type="hidden" name="isopen" id="isopen" value="${dazccsszForm.isopen}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="dazcsqApply();return false;"
								   title="点击该按钮，打开申请表填写页面">申请</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_xg" onclick="dazcsqUpdate();return false;" >修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_sc" onclick="dazcsqDelete();return false;" >删除</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_ck" onclick="dazcsqView();return false;" >查看</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_shuc" onclick="dazcsqSubmit();return false;" >提交</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_sr" onclick="dazcsqRevoke();return false;" >撤销</a>
							</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" class="btn_cs" onclick="dazcsqTrack();return false;" >流程跟踪</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_dc" onclick="dazcsqExport();return false;" >导出</a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>档案转出申请列表&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>