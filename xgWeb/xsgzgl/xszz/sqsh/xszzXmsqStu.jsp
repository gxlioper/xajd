<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/sqsh/js/sqsh.js"></script>
		<script type="text/javascript">

			jQuery(function(){
				//加载下拉选项
				loadMkxxSelectOptions();
				//加载radio选项
				loadMkxxRadioOptions();

				var api = frameElement.api;
				W = api.opener;
				var grid = W.jQuery("#dataTable");
				var selectBox = grid.getSeletRow();
				
				var tbody = jQuery("#xmInfo");
					tbody.find("tr").remove();
					
				jQuery.each(selectBox,function(i,e){
					var jesfxssq=e['jesfxssq'];
					if(jesfxssq=='1'){
						var trHtml = "<tr>";
						trHtml+="<td style='width:30%'>";
						trHtml+="<input type='hidden' name='xmdmArray' value='"+e['xmdm']+"'/>";
						trHtml+=e['xmmc'];
						trHtml+="</td><td style='width: 30%'>";
						trHtml+=e['lbmc'];
						trHtml+="</td><td style='width: 40%'>";
						trHtml+="<input type='hidden' name='je' value='"+e['je']+"'/>";
						trHtml+="<input type='text' name='ylzd1'  onkeyup='checkInputData(this);checkJesx(this)'/>";
						trHtml+="<font color='blue'>上限金额："+e['je']+"<font/>";
						trHtml+="</td></tr>";
						tbody.append(trHtml);
					}else{
						var trHtml = "<tr>";
						trHtml+="<td style='width: 30%'>";
						trHtml+="<input type='hidden' name='xmdmArray' value='"+e['xmdm']+"'/>";
						trHtml+=e['xmmc'];
						trHtml+="</td><td style='width: 30%'>";
						trHtml+=e['lbmc'];
						trHtml+="</td><td style='width: 40%'>";
						trHtml+="<input type='hidden' name='ylzd1' />";
						trHtml+=e['je'];
						trHtml+="</td></tr>";
						tbody.append(trHtml);
					}
				});

				if ("10351" == jQuery("#xxdm").val()){
					if ("" == jQuery("textarea[name=sqly]").val()) {
						jQuery("textarea[name=sqly]").val("    本人学习态度端正，勤奋刻苦，多次获得一等奖学金，并以优异的成绩进入了“溯初班”；注重德智体美劳全面发展，积极参与各类竞赛，并多次获奖；作为一名学生干部，严于律己，热情服务同学，出色地完成了各项工作任务，深受好评；注重理论与实践相结合，敢于创新，将所学知识运用到科研课题之中，主持校级课题《*******》、《******》2项，省级课题《*********》1项；并积极参与社会实践，热心公益慈善活动，曾被评为*****。");
					}
				}
				
			});
			
		</script>
	</head>
	<body>
	
		<html:form action="/xszz_sqsh" method="post" styleId="xmsqForm" onsubmit="return false;">
			<html:hidden property="guid"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
			<input type="hidden" name="xh" value="${xh}"/>
			<div style='tab;width:100%;height:395px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">		
					<thead>
						<tr>
							<th colspan="4">
								<span>
									资助项目申请信息
								</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<td colspan="4">
								<div class="con_overlfow" style="width:100%;height:150px;overflow-x:hidden;overflow-y:auto;vertical-align: top;">
									<table class="dateline" width="100%">
										<thead>
											<tr>
												<td>项目名称 </td>
												<td>项目类别 </td>
												<td>项目金额 </td>
											</tr>
										</thead>
										<tbody id="xmInfo" name="se">
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<th>学年</th>
							<td>${currXn}</td>
							<th>学期</th>
							<td>${currXq}</td>
						</tr>
						<tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<logic:equal value="10335" name="xxdm">
									<span style="color: blue; line-height:20px;display:block;">
										根据学院(园)通知要求，如明确必须上传附件则按要求上传；如无附件要求则无需上传。
									</span>
									&nbsp;
								</logic:equal>
								<html:hidden property="ylzd5" styleId="ylzd5" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<logic:notEqual value="11799" name="xxdm">
								<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//后缀
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
													//最大文件大小 单位M
													maxsize: 10,
													//存放附件的隐藏域的id
													elementid : 'ylzd5'
													});
											});
										</script>
								</logic:notEqual>
								<logic:equal value="11799" name="xxdm">
									<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//后缀
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf|mp4',
													//最大文件大小 单位M
													maxsize: 300,
													//存放附件的隐藏域的id
													elementid : 'ylzd5'
													});
											});
										</script>
								</logic:equal>
							</td>
						</tr>
					</tbody>
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxUpdate.jsp" %>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" id="save_button" type="button" onclick="saveXmsq('save');">
										保存草稿
									</button>
									<button type="button" id="save_button" type="button" onclick="saveXmsq('submit');">
										提交申请
									</button>
									<button type="button" name="关 闭" onclick="iFClose();">
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

