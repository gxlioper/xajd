<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//执行下一步操作
		function doNext(){
		
			var step_now = $("step").value;
			var num = parseInt(step_now.replace("step",""))+1;
			var step = "step"+num;
			var url = "/xgxt/sjyJcsjsz.do?method=jcsjszGuide";
				url+= "&step="+step;

			//验证步骤1
			if(step_now == "step1"){
				if(checkStep1()){
					//创建字段名
					creatZdm();
					refreshForm(url);
				}else{
					alert("请至少选择一个需要设置的字段，以便后续操作");
					return false;
				}
			}
			
			//验证步骤2
			if(step_now == "step2"){
				if(checkStep2()){
					refreshForm(url);
				}else{
					alert("请确认设置字段的页面显示，以便后续操作");
					return false;
				}
			}
			
			//验证步骤3
			if(step_now == "step3"){
				if(checkStep3()){
					refreshForm(url);
				}else{
					alert("请确认设置字段是否以学工为准，以便后续操作");
					return false;
				}
			}
			
			//验证步骤4
			if(step_now == "step4"){
				if(checkStep4()){
					refreshForm(url);
				}else{
					alert("请确认设置字段的录入限制，以便后续操作");
					return false;
				}
			}
			
			//验证步骤5
			if(step_now == "step5"){
				if(checkStep5()){
					refreshForm(url);
				}else{
					alert("请确认设置字段可否为空，以便后续操作");
					return false;
				}
			}
			
			//验证步骤6
			if(step_now == "step6"){
				var zdm = checkStep6();
				if(zdm == ""){
					refreshForm(url);
				}else{
					alert("字段\""+checkStep6()+"\"尚未选择录入形式，请确认以便后续操作");
					return false;
				}
			}
			
			//验证步骤8
			if(step_now == "step8"){
				if(checkStep8()){
					refreshForm(url);
				}else{
					alert("请确认设置字段是否启用，以便后续操作");
					return false;
				}
			}
			
			refreshForm(url);
		}
		
		//执行上一步操作
		function doPrevious(){
			var step_now = $("step").value;
			var num = parseInt(step_now.replace("step",""))-1;
			var step = "step"+num;
			var url = "/xgxt/sjyJcsjsz.do?method=jcsjszGuide";
				url+= "&step="+step;
				
			refreshForm(url);
		}
		
		//关闭提示层
		function closeTsxxDiv(id){
			$(id).style.height = "450px";
		}
		
		//选择字段
		function clickStepZd(zd){
		
			var rad_id = "rad_zd_"+zd;
			var div_id = "div_zdsz_"+zd;
			
			var div_num = $("tb_sznr").getElementsByTagName('div').length;
	
			for(var i=0;i<div_num;i++){
				var obj = $("tb_sznr").getElementsByTagName('div')[i];
				var obj_id = obj.id;
				if(obj_id == div_id){
					$(obj_id).style.display = "";
				}else{
					$(obj_id).style.display = "none";
				}
			}
		}
		
		//保存字段设置
		function saveZdsz(){
			if (confirm("请再次确认你所设置的字段相关信息?")) {
				saveUpdate('/xgxt/sjyJcsjsz.do?method=jcsjszGuide&doType=save','');
			}
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>基础数据设置 - 设置向导</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/sjyJcsjsz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="step" id="step" value="${step }"/>
			<!-- 隐藏域 end-->
			
			<div class="tab">		
				<!-- 页面基本情况 -->
				<table class="formlist" border="0" align="center" style="width: 100%;">
					<thead>
						<tr>
							<th colspan="2">
								<span>设置向导</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="15%" height="480px">
								<!-- 操作步骤 -->
								<table style="width: 100%">
									<logic:iterate name="stepList" id="stepRs" >
										<tr>
											<td>
												<font color="${stepRs.color }">${stepRs.mc }</font>
											</td>
										</tr>
									</logic:iterate>
								</table>
								<!-- 操作步骤 end-->
							</td>
							<td>
								<!-- 非第一步 -->
								<logic:notEqual name="step" value="step1">
									<logic:iterate name="kczzdList" id="kczzd">
										<!-- 字段 -->
										<input type="hidden" name="ch_zd" value="${kczzd.zd }"/>
										<!-- 字段名 -->
										<input type="hidden" name="ch_zdm" id="ch_zd_${kczzd.zd }" value="${kczzd.zdm }"/>
										<!-- 来源表数量 -->
										<input type="hidden" name="lybNum" id="ch_lybNum_${kczzd.zd }" value="${kczzd.lybNum }"/>
										
										<!-- 非第二步 -->
										<logic:notEqual name="step" value="step2">
											<!-- 显示名称 -->
											<input type="hidden" name="xsmc" id="ch_xsmc_${kczzd.zd }" value="${kczzd.xsmc }"/>
										</logic:notEqual>
										
										<!-- 非第三步 -->
										<logic:notEqual name="step" value="step3">
											<!-- 学工为准 -->
											<input type="hidden" name="xgwz" id="ch_xhwz_${kczzd.zd }" value="${kczzd.xgwz }"/>
										</logic:notEqual>
										
										<!-- 非第四步 -->
										<logic:notEqual name="step" value="step4">
											<!-- 录入限制 -->
											<input type="hidden" name="lrxz" id="ch_lrxz_${kczzd.zd }" value="${kczzd.lrxz }"/>
										</logic:notEqual>
										
										<!-- 非第五步 -->
										<logic:notEqual name="step" value="step5">
											<!-- 录入限制 -->
											<input type="hidden" name="wkxz" id="ch_wkxz_${kczzd.zd }" value="${kczzd.wkxz }"/>
										</logic:notEqual>
										
										<!-- 非第六步 -->
										<logic:notEqual name="step" value="step6">
											<!-- 录入形式 -->
											<input type="hidden" name="lrxs" id="ch_lrxs_${kczzd.zd }" value="${kczzd.lrxs }"/>
										</logic:notEqual>
										
										<!-- 非第七步 -->
										<logic:notEqual name="step" value="step7">
											<!-- 来源表 -->
											<input type="hidden" name="lyb" id="ch_lyb_${kczzd.zd }" value="${kczzd.lyb }"/>
											<!-- 来源表名称 -->
											<input type="hidden" name="lybm" id="ch_lybm_${kczzd.zd }" value="${kczzd.lybm }"/>
										</logic:notEqual>
										
										<!-- 非第八步 -->
										<logic:notEqual name="step" value="step8">
											<!-- 是否启用 -->
											<input type="hidden" name="sfqy" id="ch_sfqy_${kczzd.zd }" value="${kczzd.sfqy }"/>
										</logic:notEqual>
										
									</logic:iterate>
								</logic:notEqual>
								<!-- 第一步 -->
								<logic:equal name="step" value="step1">										
									<%@ include file="guide/guideStep1.jsp"%>
								</logic:equal>
								<!-- 第二步 -->
								<logic:equal name="step" value="step2">
									<%@ include file="guide/guideStep2.jsp"%>
								</logic:equal>
								<!-- 第三步 -->
								<logic:equal name="step" value="step3">
									<%@ include file="guide/guideStep3.jsp"%>
								</logic:equal>
								<!-- 第四步 -->
								<logic:equal name="step" value="step4">
									<%@ include file="guide/guideStep4.jsp"%>
								</logic:equal>
								<!-- 第五步 -->
								<logic:equal name="step" value="step5">
									<%@ include file="guide/guideStep5.jsp"%>
								</logic:equal>
								<!-- 第六步 -->
								<logic:equal name="step" value="step6">
									<%@ include file="guide/guideStep6.jsp"%>
								</logic:equal>
								<!-- 第七步 -->
								<logic:equal name="step" value="step7">
									<%@ include file="guide/guideStep7.jsp"%>
								</logic:equal>
								<!-- 第八步 -->
								<logic:equal name="step" value="step8">
									<%@ include file="guide/guideStep8.jsp"%>
								</logic:equal>
								<!-- 最终步 -->
								<logic:equal name="step" value="${step_last }">
									<%@ include file="guide/guideFinal.jsp"%>
								</logic:equal>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									
									<!-- 非最终步 -->
									<logic:notEqual name="step" value="${step_last }">
										<!-- 非第一步 -->
										<logic:notEqual name="step" value="step1">
											<button type="button" onclick="doPrevious()" id="buttonSave">
												上一步
											</button>
										</logic:notEqual>
									
										<button type="button" onclick="doNext()" id="buttonSave">
											下一步
										</button>
									</logic:notEqual>
									
									<!-- 非最终步 -->
									<logic:equal name="step" value="${step_last }">
									
										<button type="button" onclick="doPrevious()" id="buttonSave">
											上一步
										</button>
									
										<button type="button" onclick="saveZdsz()" id="buttonSave">
											保 存
										</button>
									</logic:equal>
									
									<button type="button" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
									
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<!-- 提示信息 end-->
		</html:form>
	</body>
</html>