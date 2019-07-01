<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
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
				var e = jQuery('input[name="lx"]:checked');
				if(e.length == 0){
					disableFdlx(1);
				}else if(e.val() == '0'){
					disableFdlx(1);
				}else{
					disableFdlx(0);
				}
			});
		
			function disableFdlx(e){

				if(e == 1){
					jQuery('.fdlxtr').hide();
					jQuery('#fdlxdm').attr("disabled" , true);
				}else if(e == 0){
					jQuery('.fdlxtr').show();
					jQuery('#fdlxdm').attr("disabled" , false);
				}
			}
		
			function addAction(){
				var rzksrq = jQuery('#rksj').val();
				
				var checkids = "sj-thnrfdcs";
				
				if(!checkNotNull(checkids)){
					showAlert("请将带<font color='red'>*</font>的项目填写完整!");
					return false;
				}

				if(jQuery('#thnrfdcs').val().length > 500){
					showAlert("辅导措施/谈话内容最大字数不超过"+500+",请确认！");
					return false;
				}
				
				var url = "xljk_xlwjyjgl_xlfdjlglwh.do?method=xgAction";
					ajaxSubFormWithFun("xlfdjlForm",url,function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							if (parent.window){
								refershParent();
							}
						}});
					});
			}
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/xljk_xlwjyjgl_xlfdjlglwh" method="post" styleId="xlfdjlForm">
			<html:hidden property="fdid"/>
			<html:hidden property="xh"/>
			<html:hidden property="zgh"/>
			<div style='tab;width:100%;height:440px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>心理辅导记录信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>职工号</th>
							<td>
								${xlfxxsxx.zgh}
							</td>
							<th>教师</th>
							<td>${xlfxxsxx.fdyxm}</td>
						</tr>
						<tr>
							<th><span class="red">*</span>时间</th>
							<td  colspan="3">
								<html:text styleId="sj" property="sj" 
									onclick="return showCalendar('sj','yyyy-MM-dd HH:mm');"  readonly="true"></html:text>
									
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>类型</th>
							<td colspan="1">
								<html:radio property="lx" value="0" onclick="disableFdlx(1);">谈话</html:radio>
								<html:radio property="lx" value="1" onclick="disableFdlx(0);">辅导</html:radio>
							</td>
							<th class="fdlxtr"><span class="red">*</span>辅导类型</th>
							<td colspan="1" class="fdlxtr">
								<logic:present name="fdlxList">
									<html:select property="fdlxdm" styleId="fdlxdm">
										<html:options collection="fdlxList" property="fdlxdm" labelProperty="fdlxmc" />
									</html:select>							
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>谈话内容
								<br />
								<font color="red">(限500字)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="thnrfdcs" styleId="thnrfdcs" style="width:95%;" rows="5"></html:textarea>
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
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button id="save_button" type="button"  onclick="addAction();">
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

