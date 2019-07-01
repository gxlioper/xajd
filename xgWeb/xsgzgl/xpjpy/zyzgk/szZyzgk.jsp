<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zyzgk/js/zyzgk.js"></script>
		
		<style type="text/css">
			.zgktable td{border:none;}
		</style>
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
		</div>
		<html:form action="/xpj_zyzgk" method="post" styleId="ZyzgkModel">
			<div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="3">
								<span>专业主干课设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="3">
								<table width="100%" >
								<th width="10%">
									<font color="red">*</font>评奖学年 
								</th>
								<td width="20%" >
									<html:select property="pjxn" styleId="pjxn" style="width:120px" onchange="getZgkList();">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
								<th width="10%">
									学院 
								</th>
								<td width="25%" >
									<html:select property="xydm" styleId="xydm" style="width:96%;"  onchange="changeXy();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</td>
								<th width="10%">
									专业
								</th>
								<td width="25%" >
									<select name="zyCond" id="zyCond" style="width:96%;" onchange="getZgkList();">
								</td>
								</table>
							</td>						
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="3">
								<span>专业课程信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="3">
								<table width="100%" id="zykxx"></table>
							</td>						
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td align="center" colspan="3">							
			        			<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveForm();return false;" id="buttonSave">
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
	<script language="javascript">
		jQuery(function(){
			var pjxnObj = jQuery("#pjxn");
			var pjxn = jQuery("#pjxn").val();
			getZgkList(pjxnObj);
			getYszZgk(pjxn);
		})
	</script>
</html>

