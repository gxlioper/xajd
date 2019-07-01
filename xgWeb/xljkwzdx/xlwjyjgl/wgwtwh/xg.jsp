<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" defer="defer">

		jQuery(function(){
			jQuery('input[type="radio"]').click(function(){
				calc();
			});;
			calc();
		});


		//分值计算
		function calc(){
			var wt1qk = jQuery("input[name='wt1qk']:checked").val();	
			var wt2qk = jQuery("input[name='wt2qk']:checked").val();
			var wt3qk = jQuery("input[name='wt3qk']:checked").val();
			var wt4qk = jQuery("input[name='wt4qk']:checked").val();
			var wt5qk = jQuery("input[name='wt5qk']:checked").val();
			jQuery('#zf').val((wt1qk?new Number(wt1qk):0) + 
								(wt2qk?new Number(wt2qk):0) + 
								(wt3qk?new Number(wt3qk):0) + 
								(wt4qk?new Number(wt4qk):0) + 
								(wt5qk?new Number(wt5qk):0));
		}
		
		function addAction() {
			var xh = jQuery('#xh').val();
			if (xh == "") {
				showAlert("请选择一个学生！");
				return false;
		}

		if (jQuery("input[name='wt1qk']:checked").length != 1
				|| jQuery("input[name='wt2qk']:checked").length != 1
				|| jQuery("input[name='wt3qk']:checked").length != 1
				|| jQuery("input[name='wt4qk']:checked").length != 1
				|| jQuery("input[name='wt5qk']:checked").length != 1) {
			showAlert("请将带'*'的项目填写完整!");
			return false;
		}

		var url = "xljk_xlwjyjgl_wgwtwh.do?method=xgAction";
		ajaxSubFormWithFun("wgwtwhForm", url, function(data) {
			showAlertDivLayer(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		});
	}
	
	/**
	 * 加载信息
	 * @param obj
	 * @return
	 */
	function showXx(obj){
		var className = jQuery(obj).attr("class");
		var newClass = className == "up" ? "down" : "up";

		jQuery(obj).attr("class",newClass);
		jQuery("#t_xx").toggle();
	}
</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display: none"
			onclick=
	reloadWindow();;
></button>
		<html:form action="/xljk_xlwjyjgl_wgwtwh" method="post"
			styleId="wgwtwhForm">
			<div
				style='width: 100%; height: 470px; overflow-x: hidden; overflow-y: auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp"%>
					<html:hidden property="xh"/>
					<thead>
						<tr>
							<th colspan="6">
								<span>五个问题情况</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="100%">
									<tr>
										<th>
											<span class="red">*</span>评估自杀、自伤计划
										</th>
										<td  width="70px">
											<html:radio property="wt1qk" value="0" >无</html:radio>
										</td>
										<td  width="70px">
											<html:radio property="wt1qk" value="1" >有（低）</html:radio>
										</td>
										<td  width="70px">
											<html:radio property="wt1qk" value="2" >有（高）</html:radio>
										</td>
											<th>
											备注
										</th>
										<td>
											<html:text styleId="wt1bz" property="wt1bz"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<span class="red">*</span>评估既往相关自杀、自伤经历
										</th>
										<td>
											<html:radio property="wt2qk" value="0">无</html:radio>
										</td>
										<td>
											<html:radio property="wt2qk" value="1">有（低）</html:radio>
										</td>
										<td>
											<html:radio property="wt2qk" value="2">有（高）</html:radio>
										</td>
										<th>
											备注
										</th>
										<td>
											<html:text styleId="wt2bz" property="wt2bz"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<span class="red">*</span>评估目前现实压力
										</th>
										<td>
											<html:radio property="wt3qk" value="0">无</html:radio>
										</td>
										<td>
											<html:radio property="wt3qk" value="1">有（低）</html:radio>
										</td>
										<td>
											<html:radio property="wt3qk" value="2">有（高）</html:radio>
										</td>
										<th>
											备注
										</th>
										<td>
											<html:text styleId="wt3bz" property="wt3bz"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<span class="red">*</span>评估目前支持资源
										</th>
										<td>
											<html:radio property="wt4qk" value="2">无</html:radio>
										</td>
										<td>
											<html:radio property="wt4qk" value="1">有（低）</html:radio>
										</td>
										<td>
											<html:radio property="wt4qk" value="0">有（高）</html:radio>
										</td>
										<th>
											备注
										</th>
										<td>
											<html:text styleId="wt4bz" property="wt4bz"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											<span class="red">*</span>临床症状
										</th>
										<td>
											<html:radio property="wt5qk" value="0">无</html:radio>
										</td>
										<td>
											<html:radio property="wt5qk" value="1">有（低）</html:radio>
										</td>
										<td>
											<html:radio property="wt5qk" value="2">有（高）</html:radio>
										</td>
										<th>
											备注
										</th>
										<td>
											<html:text styleId="wt5bz" property="wt5bz"></html:text>
										</td>
									</tr>
									<tr>
										<th style="font-weight: bold;color:red">
											总分
										</th>
										<td colspan="5">
											<html:text styleId="zf" property="zf" readonly="true"  style="font-weight:bold;color:red;border-width:0"></html:text>
										</td>
									</tr>
									<tr>
										<th>
											总分说明
										</th>
										<td colspan="5">
											2分-潜在危险 4分-轻度危险 6分-中度危险 8分-高度危险 10分-极危险
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="6">
								<span>评分详细说明
								<a onclick="showXx(this);" class="down" href="javascript:void(0);">
										   <font color="blue">点击展开/收起</font>	
										</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_xx">
						<tr>
							<td colspan="6">
								<table width="100%">
										<tr>
											<th width="231px" style="font-weight: bold;">自杀风险评估列表</th>
											<th style="font-weight: bold;">无</th>
											<th style="font-weight: bold;">有（低）</th>
											<th style="font-weight: bold;">有（高）</th>
										</tr>
										<tr>
											<th >评估自杀、自伤计划</th>
											<th>0</th>
											<th>1</th>
											<th>2</th>
										</tr>
										<tr>
											<th>评估既往相关自杀、自伤经历</th>
											<th>0</th>
											<th>1</th>
											<th>2</th>
										</tr>
										<tr>
											<th>评估目前现实压力</th>
											<th>0</th>
											<th>1</th>
											<th>2</th>
										</tr>
										<tr>
											<th>评估目前支持资源</th>
											<th>2</th>
											<th>1</th>
											<th>0</th>
										</tr>
										<tr>
											<th>临床症状</th>
											<th>0</th>
											<th>1</th>
											<th>2</th>
										</tr>
										<tr>
											<td colspan="6">
								注：“有（低）”“有（高）”的界定：<br/>
								评估自杀、自伤计划：<br/>
								有（低）——偶尔有过自杀的想法计划，且计划较模糊的，计1分；<br/>
								有（高）——常常有自杀的想法计划，或者偶尔有计划但计划详细可操作性高，计2分。<br/>
								评估既往自杀经历：<br/>
								有（低）——曾经有过低风险的自杀经历，计1分；<br/>
								有（高）——曾经有过多次自杀经历或是有过高风险的自杀经历，计2分。<br/>
								现实压力：现实压力的高低应以来访者的主观体验来定。<br/>
								支持的资源：<br/>
								有（低）——有一定的社会支持，但难以被利用，计1分；<br/>
								有（高）——有良好的社会支持，且能被利用，计0分；<br/>
								临床症状：<br/>
								有（低）——存在一般或严重心理问题，计1分；<br/>
								有（高）——疑似神经症或重性精神病，计2分。<br/>
											</td>
										</tr>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button id="save_button" type="button" onclick=
	addAction();;
>
										保存
									</button>
									<button type="button" name="关 闭" onclick=
	iFClose();;
>
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

