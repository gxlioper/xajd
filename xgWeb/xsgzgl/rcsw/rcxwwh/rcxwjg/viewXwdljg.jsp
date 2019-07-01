<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>

		<script type="text/javascript">
		jQuery(function() {
			rcxwjl();  //日常行为记录展示
		});

		//日常行为记录
		function rcxwjl(){
			var index=4;
			if("1"==jQuery("#zq").val()){
				index=3;
				}
			jQuery("#tab_rcxw").find("tr").each(function(){
				var rcxwjlsj=jQuery(this).find("td").eq(index).text();
				if(jQuery.trim(rcxwjlsj)=="0"){
					jQuery(this).find("td").eq(index-2).attr("colspan","3");
					jQuery(this).find("td").eq(index-1).hide();
					jQuery(this).find("td").eq(index).hide();
					}
				if(jQuery.trim(rcxwjlsj)=="9999999999"){
					var obj=jQuery(this);
					jQuery(this).attr("style","cursor:pointer");
					jQuery(this).find("td").eq(index-2).attr("colspan","3");
					jQuery(this).find("td").eq(index-1).hide();
					jQuery(this).find("td").eq(index).hide();
					jQuery(this).bind("click",function(){
						jQuery(obj).nextAll("tr").each(function(){
							var rcxw=jQuery(this).find("td").eq(index).text();
							if(jQuery.trim(rcxw)!="0"&&jQuery.trim(rcxw)!="9999999999"){
								if(jQuery(this).is(":hidden")){
									jQuery(this).show();
								}else{
									jQuery(this).hide();
								}
							}else{
								return false;
							}
						});
					});
				}
			});
            var temp = "行为";
            if(jQuery("#xxdm").val() == "13431") temp="加分";
			jQuery("#zdybdfl_xsxx_query_rcsw_rcxwjl table tr th span").append("<font size='0' color='blue'>&nbsp;&nbsp;&nbsp;点击"+temp+"大类行、可查看明细</font>");
			
			jQuery("#tab_rcxw").find("tr").click();
		}
		function getXwlbList(obj) {
			jQuery.post('rcsw_rcxwwh_rcxwxxwhgl.do?method=getXwlbList', {
				rcxwlbdldm : obj.value
			}, function(data) {
				var option = "<option value=''></option>";
				for ( var i = 0; i < data.length; i++) {
					option += "<option value='"+data[i].rcxwlbdm+"'>"
							+ data[i].rcxwlbmc + "</option>";
				}
				jQuery('#rcxwlbdm').empty().append(option);
				//jQuery("#rcxwlbdm").val(jQuery("#rcxwlbdm_value").val());	
			}, 'json');
		}

</script>

	</head>
	<body>

		<html:form action="/rcsw_rcxwwh_rcxwxxwhgl" method="post"
			styleId="rcxwxxwhForm">
			<html:hidden property="id" styleId="id" />
			<input type="hidden" id="zq" value="${zq}"/>
			<input type="hidden" id="xxdm" value="${xxdm}"/>
			<div style="height:480px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>

					<thead>
						<tr>
							<th colspan="4">
								<logic:equal name="xxdm" value="13431">
									<span>已获加分记录信息
								<font size="0" color="blue">&nbsp;&nbsp;&nbsp;点击加分大类行、可查看明细</font>
								</span>
								</logic:equal>
								<logic:notEqual name="xxdm" value="13431">
									<span>已获行为记录信息
								<font size="0" color="blue">&nbsp;&nbsp;&nbsp;点击行为大类行、可查看明细</font>
								</span>
								</logic:notEqual>

							</th>
						</tr>
					</thead>

					<tr>
						<td colspan="6">
							<div>
								<table class="formList" width="100%" id="tab_rcxw">
									<thead>
										<tr align="left">
											<td align="center" width="12%">
												学年
											</td>
											<logic:equal value="0" name="zq">
											<td align="center" width="12%">
												学期
											</td>
											</logic:equal>
											<td align="center" width="18%">
												<logic:equal name="xxdm" value="13431">
													加分大类
												</logic:equal>
												<logic:notEqual name="xxdm" value="13431">
													行为大类
												</logic:notEqual>
											</td>
											<td align="center" width="18%">
												<logic:equal name="xxdm" value="13431">
													加分类别
												</logic:equal>
												<logic:notEqual name="xxdm" value="13431">
													行为类别
												</logic:notEqual>
											</td>
											<td align="center" width="18%">
												发生时间
											</td>
											<td align="center" width="11%">
												评定分值
											</td>
											<td align="center" width="11%">
												记录人
											</td>
										</tr>
									</thead>
									<logic:empty name="rsArrList">
										<tr>
											<td align="left" colspan="7">
												该学生无历史违纪记录！
											</td>
										</tr>
									</logic:empty>
									<logic:notEmpty name="rsArrList">
										<logic:iterate name="rsArrList" id="s">
											<tr>
												<!-- 显示信息 -->
												<logic:iterate id="v" name="s" offset="0" length="7">
													<td align="center">
														${v }
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
									</logic:notEmpty>
								</table>
									</tbody>
									<%--<tfoot>
										<tr>
											<td colspan="5">
												<div class="btn">
													<button type="button" type="button" onclick="iFClose();">
														关 闭
													</button>
												</div>
											</td>
										</tr>
									</tfoot>
								--%></table>
							</div>
							
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="5">
							<div class="btn">
								<button type="button" type="button" onclick="iFClose();">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		</html:form>
	</body>
</html>

