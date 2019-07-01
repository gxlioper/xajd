<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrdbjpy/knsrdbjpy/js/knsrdbjpy.js"></script>
		<script type="text/javascript">
	function initGridSetting(){
		var gridSetting = {};
		
		if("xq"==jQuery("#sqzq").val()){
			gridSetting = {
					caption:"困难生认定申请列表",
					pager:"pager",
					url:"xszz_knsrdbjpy.do?method=knssqManage&type=query",
					colList:[
					   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
					   {label:'学号',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
					   {label:'性别',name:'xb', index: 'xb',width:'5%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
					   {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
					   {label:'学年',name:'xn', index: 'xn',width:'10%'},
					   {label:'学期',name:'xqmc', index: 'xqmc',width:'6%'},
					   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'9%'},
					   {label:'申请档次',name:'sqdcmc', index: 'sqdcmc',width:'8%'},
					   {label:'班级评议<br/>推荐档次',name:'pddcmc', index: 'pddcmc',width:'8%'},
					   {label:'认定档次',name:'dcmc', index: 'dcdm',width:'8%'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'xq',name:'xq', index: 'xq',hidden:true},
					   {label:'审核状态',name:'shztmc', index: 'shzt',width:'6%'}
					],
					sortname: "sqsj",
				 	sortorder: "desc"
				}
		}
		else{
			gridSetting = {
					caption:"困难生认定申请列表",
					pager:"pager",
					url:"xszz_knsrdbjpy.do?method=knssqManage&type=query",
					colList:[
					   {label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
					   {label:'学号',name:'xh', index: 'xh',width:'11%',formatter:xhLink},
					   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
					   {label:'性别',name:'xb', index: 'xb',width:'5%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'13%'},
					   {label:'shlc',name:'shlc', index: 'shlc',hidden:true},
					   {label:'班级',name:'bjmc', index: 'bjdm',width:'13%'},
					   {label:'学年',name:'xn', index: 'xn',width:'10%'},
					   {label:'申请时间',name:'sqsj', index: 'sqsj',width:'9%'},
					   {label:'申请档次',name:'sqdcmc', index: 'sqdcmc',width:'8%'},
					   {label:'班级评议<br/>推荐档次',name:'pddcmc', index: 'pddcmc',width:'8%'},
					   {label:'认定档次',name:'dcmc', index: 'dcdm',width:'8%'},
					   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
					   {label:'审核状态',name:'shztmc', index: 'shzt',width:'6%'}
					],
					sortname: "sqsj",
				 	sortorder: "desc"
				}
		}
		gridSetting["params"]=getSuperSearch();
		jQuery("#dataTable").initGrid(gridSetting);
		}
	
		
			jQuery(function(){
				initGridSetting();

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

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}			

		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
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
		<html:form action="/xszz_jtqkdc">
			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_knssqb"/>
			<input type="hidden" name="sfysq" id="sfysq" value="${sfysq }" />
			<html:hidden property="sqzq" styleId="sqzq" value="${sqzq}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="knssq();return false;"  title="点击该按钮，打开申请表填写页面。">申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="knssqUpdate();return false;" class="btn_xg" title="选中一条记录，点击该按钮可修改申请表。">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="knssqDelete();return false;" class="btn_sc" title="只能取消未审核的记录，已经在审核的不能取消。" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="knssqLcinfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a>
						</li>					
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
							<%--<li><a href="#" class="btn_dc" onclick="exportData();return false;">导出数据</a></li>--%>
						<li><a href="javascript:void(0);" onclick="printKnssq('xszz_knsrdbjpy.do?method=printJsp');return false;" class="btn_down">下载登记表</a></li>						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>困难生认定申请列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
