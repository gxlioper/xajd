<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true" ></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/bjxwjl/js/wh.js"></script>
		<script type="text/javascript" defer="defer">


		jQuery(function(){
				jQuery('#save_button').click(function(){
					
					var jlsj = jQuery('#_jlsj').val();
					var jlnr = jQuery('#_jlnr').val();
					
					if (jlsj == "" || jlnr == ""){
						showAlertDivLayer("请将必填项填写完整！");
						return false;
					}
					if(jlnr.length > 500){
						showAlertDivLayer("记录内容最大字数不超过"+500+",请确认！");
						return false;
					}
					var url = "szdw_bjxwjlwh.do?method=updateAction";
						ajaxSubFormWithFun("bjxwjlwhForm",url,function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
						});
			});
		});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		
		<html:form action="/szdw_bjxwjlwh" method="post" styleId="bjxwjlwhForm">
		<input name="xn" value="${bjxwjlxx.xn}" type="hidden"/>
		<input name="guid" value="${bjxwjlxx.guid}" type="hidden"/>
		<input name="xqdm" value="${bjxwjlxx.xqdm}" type="hidden"/>
		<input name="jlr" value="${bjxwjlxx.jlr}" type="hidden"/>
			<div style='tab'>
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
							<th>学年</th>
							<td style="font-weight: bold">${bjxwjlxx.xn}</td>
							<th>学期</th>
							<td style="font-weight: bold">${bjxwjlxx.xqmc}</td>
						</tr>
						<tr>
							<th>职工号</th>
							<td>${bjxwjlxx.jlr}</td>
							<th>姓名  </th>
							<td>${bjxwjlxx.jlrmc}</td>
						</tr>
						<tr>
							<th>部门  </th>
							<td>${bjxwjlxx.jlrbmmc}</td>
							<th>岗位</th>
							<td>${bjxwjlxx.gwmc}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>班级行为记录</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>班级</th>
							<td>
								<html:select property="bjdm" value="${bjxwjlxx.bjdm}" >
									<html:options collection="fdybjxxList" property="bjdm" labelProperty="bjmc" />
								</html:select>
							</td>
							<th><font color="red">*</font>类别</th>
							<td>
								<html:select property="xndsdm" value="${bjxwjlxx.xndsdm}">
									<html:options collection="lbxxList" property="xndsdm" labelProperty="xndsmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>记录时间
							</th>
							<td colspan="3">
								<html:text styleId="_jlsj" property="jlsj" value="${bjxwjlxx.jlsj}" onclick="return showCalendar(this.id,'yyyyMMdd');"></html:text>
							</td>
							
						</tr>
						<tr>
							<th>
								<font color="red">*</font>记录内容
							</th>
							<td colspan="3">
								<html:textarea styleId="_jlnr" property="jlnr" rows="3" style="width:97%" onblur="checkLen(this,500);" value="${bjxwjlxx.jlnr}"></html:textarea>
							</td>
						</tr>
					</tbody>
					</table>
					
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button id="save_button" type="button" >
										保存
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

