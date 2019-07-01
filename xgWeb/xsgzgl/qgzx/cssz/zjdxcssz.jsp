<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript">
		function changKssj(value){
			if(value==""){
				$("kssj").focus();
				jQuery("span[id=cjffError]").text("酬金发放开放时间不能为空!");
		 		return false;
			}
			if(Number(value)>Number(jQuery("#jssj").val())){
				$("kssj").focus();
				jQuery("span[id=cjffError]").text("时间区间不正确!");
				return false;
			}else{
				jQuery("span[id=cjffError]").text("");
			}
		}
		
		function changJssj(value){
			if(value==""){
				$("jssj").focus();
				jQuery("span[id=cjffError]").text("酬金发放结束时间不能为空!");
		 		return false;
			}
			if(Number(value)<Number(jQuery("#kssj").val())){
				$("jssj").focus();
				jQuery("span[id=cjffError]").text("时间区间不正确!");
		 		return false;
			}else{
				jQuery("span[id=cjffError]").text("");
			}
		}

		function SaveXqgzx(){
			var cjbz = jQuery("#cjbz").val();
			var sxsz = jQuery("#sxsz").val();
			var ksyf = jQuery("#ksyf").val();
			var jsyf = jQuery("#jsyf").val();
			var sfyxcgcjsx = jQuery("input[name='sfyxcgcjsx']:checked").val();
			if(cjbz == "" || cjbz == null|| cjbz<=0){
				showAlertDivLayer("酬金标准不能为空也不能为0!");
				return false;
			}
			if(sxsz == "" || sxsz == null|| sxsz<=0){
				showAlertDivLayer("学生每月最高报酬不能为空也不能为0!");
				return false;
			}
			if((ksyf == "" || ksyf == null) && (jsyf == "" || jsyf == null)){
				showAlertDivLayer("至少填写一个酬金发放月份");
				return false;
			}
			if(Number($("kssj").value) > Number($("jssj").value)){
		 		alertInfo("酬金发放开始时间不能大于结束时间!");
		 		return false;
			}
			if("" == sfyxcgcjsx || null == sfyxcgcjsx){
				showAlertDivLayer("请选择是否允许超过酬金上限！");
				return false;
			}
			var url = "qgzx_jcsz.do?method=saveZjdxqgzx";
			ajaxSubFormWithFun("qgzxCsszForm",url,function(data){
				showAlertDivLayer(data["message"]);
			});
		}
		/*加载，为【是否允许超过酬金上限】赋值*/
		jQuery(function(){
			jQuery("input[name='sfyxcgcjsx'][value='${sfyxcgcjsx}']").attr("checked",true); 
		})
	</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /> </a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmouseover ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		<!-- 提示信息 -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					1、酬金发放时间截止后系统将<font color="blue">自动提交</font>酬金信息，提交后（除勤工管理员用户）<font color="blue">不能再次修改</font>酬金信息。
				</br>2、酬金发放开放时间默认为开始日期的00:00:00至结束时间的23:59:59。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		<html:form action="/qgzx_jcsz" method="post" styleId="qgzxCsszForm">
		<input type="hidden" name="sxzd" id="sxzd" value="je"/>
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 end-->
			<div class="tab">
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>酬金发放参数设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>酬金标准
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="cjbz" styleId="cjbz"  size="10" maxlength="5" onkeyup="checkInput(this)"></html:text>
								元/小时（默认按此标准计算酬金）
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>学生每月最高报酬
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="sxsz" styleId="sxsz"  size="10" maxlength="6" onkeyup="checkInput(this)"></html:text>
								<font id="font_sxsz">元（学生每月酬金不得超过该值）</font>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>酬金发放月份设定
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="ksyf" styleId="ksyf"   size="10"
									onclick="return showCalendar('ksyf','yyyyMM');" readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>酬金发放开放时间
							</td>
							<td align="left" style="width: 60%">
								<html:select name="rs" property="kssj" styleId="kssj" style="width:50px" onchange="changKssj(this.value);">
									<html:option value="1">1</html:option><html:option value="2">2</html:option>
									<html:option value="3">3</html:option><html:option value="4">4</html:option>
									<html:option value="5">5</html:option><html:option value="6">6</html:option>
									<html:option value="7">7</html:option><html:option value="8">8</html:option>
									<html:option value="9">9</html:option><html:option value="10">10</html:option>
									<html:option value="11">11</html:option><html:option value="12">12</html:option>
									<html:option value="13">13</html:option><html:option value="14">14</html:option>
									<html:option value="15">15</html:option><html:option value="16">16</html:option>
									<html:option value="17">17</html:option><html:option value="18">18</html:option>
									<html:option value="19">19</html:option><html:option value="20">20</html:option>
									<html:option value="21">21</html:option><html:option value="22">22</html:option>
									<html:option value="23">23</html:option><html:option value="24">24</html:option>
									<html:option value="25">25</html:option><html:option value="26">26</html:option>
									<html:option value="27">27</html:option><html:option value="28">28</html:option>
									<html:option value="29">29</html:option><html:option value="30">30</html:option>
									<html:option value="31">31</html:option>
								</html:select>日
								-
								<html:select name="rs" property="jssj" styleId="jssj" style="width:50px" onchange="changJssj(this.value);">
									<html:option value="1">1</html:option><html:option value="2">2</html:option>
									<html:option value="3">3</html:option><html:option value="4">4</html:option>
									<html:option value="5">5</html:option><html:option value="6">6</html:option>
									<html:option value="7">7</html:option><html:option value="8">8</html:option>
									<html:option value="9">9</html:option><html:option value="10">10</html:option>
									<html:option value="11">11</html:option><html:option value="12">12</html:option>
									<html:option value="13">13</html:option><html:option value="14">14</html:option>
									<html:option value="15">15</html:option><html:option value="16">16</html:option>
									<html:option value="17">17</html:option><html:option value="18">18</html:option>
									<html:option value="19">19</html:option><html:option value="20">20</html:option>
									<html:option value="21">21</html:option><html:option value="22">22</html:option>
									<html:option value="23">23</html:option><html:option value="24">24</html:option>
									<html:option value="25">25</html:option><html:option value="26">26</html:option>
									<html:option value="27">27</html:option><html:option value="28">28</html:option>
									<html:option value="29">29</html:option><html:option value="30">30</html:option>
									<html:option value="31">31</html:option>
								</html:select>日
									(只有在酬金发放时间段内才能发放酬金)  <span id ="cjffError" style="color:red"></span>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>是否允许超过酬金上限
							</td>
							<td>
								<input type="radio" name="sfyxcgcjsx" property="sfyxcgcjsx" value="是"/>是
								<input type="radio" name="sfyxcgcjsx" property="sfyxcgcjsx" value="否"/>否
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td align="center" colspan="2">
			        			<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
								<logic:equal name="writeAble" value="yes">
									<button type="button" onclick="SaveXqgzx();return false;" id="buttonSave">
										保 存
									</button>
								</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>