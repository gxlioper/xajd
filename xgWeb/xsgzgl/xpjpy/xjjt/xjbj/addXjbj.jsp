<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/xjjt/xjbj/js/xjbj.js"></script>
		<script type="text/javascript">
		function getSqlTj(){
			var xn = jQuery("#xn").val();
			var sqlTj = " and xn = '"+xn+"' ";
			return sqlTj;
		}
		function changeXmmc(){
			// 重置
			//jQuery("#bjmc").val("");
			//取得数据表：BKS_BJDM; 字段：bjmc																							
			var autoSetting = {
				dataTable:"BKS_BJDM",
				dataField:"bjmc",
				//sqlTj: getSqlTj,
				scrollHeight:135										
			}
			// 模糊搜索下拉【项目名称】
			jQuery("#bjmc").setAutocomplete(autoSetting);
		}
		jQuery(function(){	
			changeXmmc();
		});
		</script>
	</head>
	<body>
		<html:form action="/xpjpy_xjbj" method="post" styleId="xjbjForm">
			<input type="hidden" id="type" value="${type}"/>
			<input type="hidden" id="guid" value="${xjbjForm.guid}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
				    		<th><span class="red">*</span>录入时间</th>
							<td colspan="3">
							<html:text   property="lrsj" styleId="lrsj" onclick="return showCalendar('lrsj','yyyy-MM-dd HH:mm:ss');" style="width:155px"></html:text>
							</td>	
					    </tr>
					    <tr>
							<th><span class="red">*</span>学年</th>
							<td>
								<html:select property="xn" styleId="xn" onchange="changeXmmc();" style="width:160px">
									<html:options collection="xnList" labelProperty="xn" property="xn" />
								</html:select>
							</td>
							<th><span class="red">*</span>先进班级名称</th>
							<td>
								<html:text  property="bjmc" styleId="bjmc" style="width:155px;" styleClass="text_nor"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								备注&nbsp;&nbsp;&nbsp;<br/>
								<font color="red">&lt;限200字&gt;</font>
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea property="bz" styleId="bz" style="width:100%;" rows="6" onblur="checkLen(this,200);"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										保 存
									</button>
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