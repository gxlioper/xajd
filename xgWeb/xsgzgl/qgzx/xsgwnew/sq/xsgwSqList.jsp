<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/xsgwnew/sq/js/xsgwsq.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#btn_zj").click(add);
				jQuery("#btn_cx").click(qx_sh);
				jQuery("#btn_cs").click(lcgz);
				jQuery("#btn_sc").click(del);
				jQuery("#btn_cz").click(function(){searchReset()});

				var isopen = jQuery("#xssqkg").val();
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
		
		<input type="hidden" id="lable_jcszwcsh" value='<bean:message key="lable.jcszwcsh" />'/>
		<input type="hidden" id="lable_dqwkfsq" value='<bean:message key="lable.dqwkfsq" />'/>
		<input type="hidden" id="lable_wxglcxx" value='<bean:message key="lable.wxglcxx" />'/>
		<html:form action="/xsgwsqnew_sq">
		<input type="hidden" id="xssqkg" name="xssqkg" value="${cssz.xssqkg }" />
		<logic:equal value="10704" name="xxdm">
			<logic:equal value="stu" name="userStatus">
				<input type="hidden" id="isTg" value="${isTg}" />
			</logic:equal>
		</logic:equal>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li><a href="javascript:void(0);" id="btn_zj" class="btn_zj">岗位申请</a></li>
							<li><a href="javascript:void(0);" id="btn_sc" class="btn_sc">删除</a></li>	
							<li><a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交</a></li>
							<li><a href="javascript:void(0);" id="btn_cx" class="btn_sr">撤销</a></li>	
							<li><a href="javascript:void(0);" id="btn_cs" class="btn_cs">流程跟踪</a></li>
						</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span> 学生岗位申请</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
