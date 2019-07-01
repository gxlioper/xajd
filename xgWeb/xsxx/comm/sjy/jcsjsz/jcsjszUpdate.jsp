<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">		
		//保存字段设置
		function saveZdsz(){
			var xsmc = $("text_xsmc").value;
			
			if (xsmc == ""){
				alert("请确定该字段的页面显示名称");
				return false;
			}
			
			if (confirm("请再次确认你所设置的字段相关信息?")) {
				saveUpdate('/xgxt/sjyJcsjsz.do?method=jcsjszUpdate&doType=save','');
			}
		}
		
		//设置显示名称
		function clickXsmc(value){
			if(value == "same"){
				$("text_xsmc").value = $("zdm").value;
				$("text_xsmc_nr").value = "选择其他才可维护";
				$("text_xsmc_nr").disabled = "disabled";
			}else if(value == "other"){
				$("text_xsmc_nr").disabled = "";
				$("text_xsmc_nr").value = "";
				$("text_xsmc").value = "";
			}
		}
		
		//点击设置项
		function clickSzx(lx,value){
			var text_id = "text_"+lx;
			$(text_id).value = value;
		}
		</script>
	</head>
	<body onload="">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				如果不明白各个设置项的意义，建议请先完成“设置向导”。
			</p>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/sjyJcsjsz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="zd" id="zd" value="${rs.zd }"/>
			<input type="hidden" name="zdm" id="zdm" value="${rs.zdm }"/>
			<!-- 隐藏域 end-->
			
			<div class="tab">		
				<!-- 页面基本情况 -->
				<table class="formlist" border="0" align="center" style="width: 100%;">
					<thead>
						<tr>
							<th colspan="2">
								<span>对字段“${rs.zdm }”进行设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="20%">
								页面显示
							</td>
							<td>
								<input type="radio" name="rad_xsmc" 
									id="rad_xsmc_same" 
									onclick="clickXsmc(this.value)" 
									value="same"
									<logic:equal name="rs" property="zdm" value="${rs.xsmc }">checked="checked"</logic:equal>
								/>
								同字段名
								<input type="radio" name="rad_xsmc" 
									id="rad_xsmc_other" 
									onclick="clickXsmc(this.value)" 
									value="other"
									<logic:notEqual name="rs" property="zdm" value="${rs.xsmc }">checked="checked"</logic:notEqual>
								/>	
								其他
								<logic:equal name="rs" property="zdm" value="${rs.xsmc }">
									<input type="text" id="text_xsmc_nr" 
										style="width: 150px" onblur="$('text_xsmc').value=this.value;" 
										disabled="disabled" value="选择其他才可维护"/>				
								</logic:equal>
								<logic:notEqual name="rs" property="zdm" value="${rs.xsmc }">
									<input type="text" id="text_xsmc_nr" 
										style="width: 150px" onblur="$('text_xsmc').value=this.value;" 
										value="${rs.xsmc }"/>
								</logic:notEqual>
								<input type="hidden" name="xsmc" id="text_xsmc" value="${rs.xsmc }"/>
							</td>
						</tr>
						<tr>
							<td>
								学工为准
							</td>
							<td>
								<input type="radio" name="rad_xgwz" 
									id="rad_xgwz_yes" onclick="clickSzx('xgwz',this.value)" 
									value="是"
									<logic:equal name="rs" property="xgwz" value="是">checked="checked"</logic:equal>
								/>
								是
								<input type="radio" name="rad_xgwz" 
									id="rad_xgwz_no" onclick="clickSzx('xgwz',this.value)" 
									value="否"
									<logic:equal name="rs" property="xgwz" value="否">checked="checked"</logic:equal>
								/>	
								否
								<input type="hidden" name="xgwz" id="text_xgwz" value="${rs.xgwz }"/>
							</td>
						</tr>
						<tr>
							<td>
								录入限制
							</td>
							<td>
								<logic:iterate name="jbszLrxzList" id="lrxzRs">
									<input type="radio" name="rad_lrxz" 
										onclick="clickSzx('lrxz',this.value)" 
										value="${lrxzRs.en }"
										<logic:equal name="lrxzRs" property="en" value="${rs.lrxz }">checked="checked"</logic:equal>
									/>${lrxzRs.cn }
								</logic:iterate>
								<input type="hidden" name="lrxz" id="text_lrxz" value="${rs.lrxz }"/>
							</td>
						</tr>
						<tr>
							<td>
								为空限制
							</td>
							<td>
								<input type="radio" name="rad_wkxz" 
									id="rad_xgwz_yes" onclick="clickSzx('wkxz',this.value)" 
									value="可以为空"
									<logic:equal name="rs" property="wkxz" value="可以为空">checked="checked"</logic:equal>
								/>
								可以为空
								<input type="radio" name="rad_wkxz" 
									id="rad_xgwz_no" onclick="clickSzx('wkxz',this.value)" 
									value="不可为空"
									<logic:equal name="rs" property="wkxz" value="不可为空">checked="checked"</logic:equal>
								/>	
								不可为空
								<input type="hidden" name="wkxz" id="text_wkxz" value="${rs.wkxz }"/>
							</td>
						</tr>
						<tr>
							<td>
								录入形式
							</td>
							<td>
								<logic:iterate name="rs" property="lrxsList" id="lrxsRs">
									<input type="radio" name="rad_lrxs" 
										onclick="clickSzx('lrxs',this.value)" 
										value="${lrxsRs.en }"
										<logic:equal name="lrxsRs" property="en" value="${rs.lrxs }">checked="checked"</logic:equal>
									/>${lrxsRs.cn }
								</logic:iterate>
								<input type="hidden" name="lrxs" id="text_lrxs" value="${rs.lrxs }"/>
							</td>
						</tr>
						<tr>
							<td>
								数据来源表
							</td>
							<td>
								<logic:empty name="rs" property="lybList">
									<input type="radio" name="rad_lyb" checked="checked"/>
									无需来源表
								</logic:empty>
								<logic:notEmpty name="rs" property="lybList">
									<logic:iterate name="rs" property="lybList" id="lybRs">
										<input type="radio" name="rad_lyb" 
											onclick="clickSzx('lyb',this.value)" 
											value="${lybRs.en }"
											<logic:equal name="lybRs" property="lyb" value="${rs.lyb }">checked="checked"</logic:equal>
										/>${lybRs.lybm }
									</logic:iterate>
								</logic:notEmpty>
								<input type="hidden" name="lyb" id="text_lyb" value="${rs.lyb }"/>
							</td>
						</tr>
						<tr>
							<td>
								是否启用
							</td>
							<td>
								<input type="radio" name="rad_sfqy" 
									id="rad_sfqy_yes" onclick="clickSzx('sfqy',this.value)" 
									value="是"
									<logic:equal name="rs" property="sfqy" value="是">checked="checked"</logic:equal>
								/>
								是
								<input type="radio" name="rad_sfqy" 
									id="rad_sfqy_no" onclick="clickSzx('sfqy',this.value)" 
									value="否"
									<logic:equal name="rs" property="sfqy" value="否">checked="checked"</logic:equal>
								/>	
								否
								<input type="hidden" name="sfqy" id="text_sfqy" value="${rs.sfqy }"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									
									<button type="button" onclick="saveZdsz()" id="buttonSave">
										保 存
									</button>
										
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