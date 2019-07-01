<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/cssz/js/szdw_cssz.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#but_save").click(saveBgbcssz);
			});
			
		</script>
	</head>
	<body style="width:100%">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>思政队伍-基础设置-班干部参数设置</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/szdw_cssz.do?method=bgbcssz" method="post" styleId="demoForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist" align="center">
					<thead>
						<tr>
							<th colspan="2">
								<span>学生干部职务参数设置</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_zwcs">
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>职务申请审批流
							</td>
							<td align="left" style="width: 60%">
								<html:select property="splc" name="zwModel" styleId="zwsqSplc">
									<html:option value=""></html:option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" id = "but_save" >
										保 存
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

