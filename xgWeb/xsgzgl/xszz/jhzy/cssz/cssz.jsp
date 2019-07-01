<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" defer="defer">
		function saveSpl(){
			var array = jQuery(".sj_class");
			var flag = true;
			jQuery(array).each(function (i,n) {
				if (jQuery(n).val()=="" || jQuery(n).val()==null) {
					flag = false;
					alertError("带*号字段必须填写！");
					return false;	
				}
			});
			if (flag) {
				var xn = jQuery('#xn').val();
				var jtqkdzkssj   = jQuery("#jtqkdzkssj").val(); 
				var jtqkdzjssj   = jQuery("#jtqkdzjssj").val();
				var knssqkssj    = jQuery("#knssqkssj").val();
				var knssqjssj    = jQuery("#knssqjssj").val();

				var lzjxjsqkssj  = jQuery("#lzjxjsqkssj").val();
				var lzjxjsqjssj  = jQuery("#lzjxjsqjssj").val();

				var gjzxjsqkssj  = jQuery("#gjzxjsqkssj").val();
				var gjzxjsqjssj  = jQuery("#gjzxjsqjssj").val();
				
				
				if (jtqkdzjssj < jtqkdzkssj) {
					alertError("家庭调查结束时间必须晚于开始时间！");
					return false;
				}
				if (knssqjssj < knssqkssj) {
					alertError("困难生认定申请结束时间必须晚于开始时间！");
					return false;
				}
				if (lzjxjsqjssj < lzjxjsqkssj) {
					alertError("国家励志奖学金申请结束时间必须晚于开始时间！");
					return false;
				}
			
				if (gjzxjsqjssj < gjzxjsqkssj) {
					alertError("国家助学金申请结束时间必须晚于开始时间！");
					return false;
				}
			
				refreshForm('xszz_jhzy_cssz.do?act=save');
			}
		}
		</script>
	</head>
	<body onload="" >
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>学生资助 - 参数设置 - 参数设置</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
<!--		<div class="prompt">-->
<!--			<h3>-->
<!--				<span>系统提示：</span>-->
<!--			</h3>-->
<!--			<p>-->
<!--				-->
<!--			</p>-->
<!--		</div>-->
		<!-- 提示信息 end-->
		
		<html:form action="/csszGl" method="post">
			
			<!-- 维护信息 -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="2">
							<span>时间设置</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							资助学年
						</th>
						<td width="">
							<html:select property="xn" styleClass="sj_class" styleId="xn" style="width:155px" value="${rs.xn}">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							家庭情况调查起始时间
						</th>
						<td width="">
							<html:text property="jtqkdzkssj" styleId="jtqkdzkssj" styleClass="sj_class" onclick="return showCalendar('jtqkdzkssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.jtqkdzkssj}"></html:text>
							->
							<html:text property="jtqkdzjssj" styleId="jtqkdzjssj" styleClass="sj_class" onclick="return showCalendar('jtqkdzjssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.jtqkdzjssj}"></html:text>
						</td>
					</tr>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							困难生认定申请起始时间设置
						</th>
						<td width="">
							<html:text property="knssqkssj" styleId="knssqkssj" styleClass="sj_class" onclick="return showCalendar('knssqkssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.knssqkssj}"></html:text>
							->
							<html:text property="knssqjssj" styleId="knssqjssj" styleClass="sj_class" onclick="return showCalendar('knssqjssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.knssqjssj}"></html:text>
						</td>
					</tr>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							国家励志奖学金申请起始时间设置
						</th>
						<td width="">
							<html:text property="lzjxjsqkssj" styleId="lzjxjsqkssj" styleClass="sj_class" onclick="return showCalendar('lzjxjsqkssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.lzjxjsqkssj}"></html:text>
							->
							<html:text property="lzjxjsqjssj" styleId="lzjxjsqjssj" styleClass="sj_class" onclick="return showCalendar('lzjxjsqjssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.lzjxjsqjssj}"></html:text>
						</td>
					</tr>
					<tr>
						<th width="30%">
							<font color="red">*</font>
							国家助学金申请起始时间设置
						</th>
						<td width="">
							<html:text property="gjzxjsqkssj" styleId="gjzxjsqkssj" styleClass="sj_class" onclick="return showCalendar('gjzxjsqkssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.gjzxjsqkssj}"></html:text>
							->
							<html:text property="gjzxjsqjssj" styleId="gjzxjsqjssj" styleClass="sj_class" onclick="return showCalendar('gjzxjsqjssj','y-mm-dd');" 
							onblur="dateFormatChg(this)" style="cursor:hand;" value="${rs.gjzxjsqjssj}"></html:text>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='2'>
							<div class="btn">
								<!-- 保存 -->
								<button type="button" onclick="saveSpl();return false;">
									保存
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 维护信息 end-->
		</html:form>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("操作失败！");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("操作成功！");
					</script>
				</logic:equal>
		</logic:notEmpty>
	</body>
</html>