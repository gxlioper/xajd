<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type="text/javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybd.js"></script>	
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/js/xsxxgl.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/xxxg/js/xgsq.js"></script>
		<script type="text/javascript" src="js/station/station.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>	
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>			
		<script type="text/javascript" defer="defer">
		//调用附件 
								jQuery(function(){
									jQuery.MultiUploader({
										maxcount : 3,
										//后缀
										accept : 'png|gif|jpg|jpeg|doc|docx|rar|zip',
										//最大文件大小 单位M
										maxsize: 10,
										//存放附件的隐藏域的id
										elementid : 'fjid'
										});
										if("12303" == jQuery("#xxdm").val()){
										var shgxgx2 = jQuery("#shgxgx2").val();
										jQuery("td").on("change","#shgxgx2",function(){
											if(this.value == '否，注册'){
												jQuery(this).after("<label id='zdybdcon_th_wzlj'>中国青年志愿者网</label>" +
												"<a id='zdybdcon_th_wzlj' href=' http://www.zgzyz.org.cn/' target='_Blank'>"+
												" http://www.zgzyz.org.cn/"+
												"</a>");
											}else{
												jQuery("label[id='zdybdcon_th_wzlj']").remove();
												jQuery("a[id='zdybdcon_th_wzlj']").remove();
												jQuery("#zdybdcon_table_xsxx_update_qtxx> tbody").append("<input type='hidden' name='shgxgx2' value='' id='shgxgx2' class='text_nor'>");
											}
										});
									}
								});
								
								//附件必填是否验证函数，如果配置表中配置必填则函数返回true
								var fjbtsfyz = function(){
									return ${fjxx.sfbt == "yes"};
								}
			function printTyXjk() {
				var xh = jQuery("#xh").val();
				var url = "xsxx_tygl.do?method=getXjk";
				url += "&xh=" + xh;
				window.open(url);
			}	

			function printXsdjb(){
				var xh = jQuery("#xh").val();
				var url = "xsxx_tygl.do?method=printXsdjb";
				url += "&xh=" + xh;
				window.open(url);
			}
		</script>		
	</head>
	<body >	
	<html:form action="/xsxx_xsxxxg" method="post" styleId="form1">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfxsgban" id="sfxsgban" value="${sfxsgban} "/>
			<input type="hidden" name="zpsfbt" id="zpsfbt" />
			<input type="hidden" name="zpsfcz" id="zpsfcz"  value="${zpsfcz }"/>
			<input type="hidden" name="xgzdJson" id="xgzdJson"  value=""/>
			<input type="hidden" name="kfxg" id="kfxg" value='${kfxg}'/>
			<input type="hidden" name="sqcs" id="sqcs" value='${sqcs}'/>
			<input type="hidden" name="xsxgsplc" id="xsxgsplc" value='${xsxgsplc}'/>
			<input type="hidden" name="dshSqid" id="dshSqid" value='${dshSqid}'/>
			<input type="hidden" name="shzSqid" id="shzSqid" value='${shzSqid}'/>
			<input type="hidden" name="shjg" id ="shjg" value='${shxxRs.shjg}'/>
			<input type="hidden" name="shyj" id ="shyj" value='${shxxRs.shyj}'/>
			<input type="hidden" name="shjgzt" id ="shjgzt" value='${shztRs.shzt}'/>
			<input type="hidden" name="xxdm"	id="xxdm"	value='${xxdm }' />
			<input type="hidden" name="sczp"	id="sczp"	value='${rs.sczp }' />
			<input type="hidden" name="yyfj" id="yyfj" value="${yyfj}" />
			<input type="hidden" name="xh" id="xh" value='${xh}'/>
			<div class="prompt" id="pts" style="display: none;">
				<h3><span>提示：</span> </h3>
				<p></p>
			</div>
			<!-- 浮动框架 -->
			<div style="height: 50px;">
				<div id="navigation" style="background: #fff; expression(eval(document.documentElement.scrollTop));">
					<div class="title_xxxx">
						<span id = "no" class="people_xx"><span id="xmView"></span> （学号： ${xh }）</span>
						<span class="wxts">温馨提醒： <span>点击下面的类别，
								可以快速定位到您所要查看的信息</span>
						</span>
					</div>
				</div>
			</div>
			<div class="demo_xxxx" style="margin-top: 20px; margin-bottom:0;" id="content">
			
			</div>
			<table id="btnTable" width="100%" border="0" class="formlist" style="display:none;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">"<span class="red">*</span>"为必填项  </div>
								<div class="btn">
									<logic:notEqual name="xxdm" value="11600">
										<logic:notEqual name="xxdm" value="10834">
											<logic:notEqual name="xxdm" value="9800003">
												<logic:notEqual name="xxdm" value="10264">
													<logic:notEqual name="xxdm" value="110501">
														<logic:notEqual name="xxdm" value="12866">
															<button type="button" type="button" onclick="printTyXjk();return false;">
																学生登记表打印
															</button>
														</logic:notEqual>
													</logic:notEqual>
												</logic:notEqual>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal name="xxdm" value="10264">
										<button type="button" type="button" onclick="printXsdjb();return false;">
											新生登记表打印
										</button>
									</logic:equal>
									<logic:equal name="xxdm" value="12866">
										<button type="button" type="button" onclick="printXsdjb();return false;">
											新生登记表打印
										</button>
									</logic:equal>
									<button name="保存" id="buttonSave" type="button" onclick="saveForm('save');return false;">
										保存草稿
									</button>
									<button name="提交" id="buttonSubmit" type="button" onclick="saveForm('submit');return false;">
										提交申请
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
						
			<div id="zpHidDiv" style="display: none;">			
				<jsp:include flush="true" page="../xsxxgl/zpxg.jsp"></jsp:include>
			</div>
			<logic:equal name="xxdm" value="10704">
			<div id="fjHidDiv" style="display: none;">
				<input type='hidden' name='zd6_fj' id='zd6_fj'/>
			</div>
			</logic:equal>
			</html:form>
		
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
	</body>
	<script type='text/javascript'>
		jQuery(function() {		
			var appendHtml="";
			if(jQuery("td[name = 'zdybdcon_td_xh']").html() != null && jQuery("#userType").val() != 'stu') {
				var input = "<input type='text' id='xh' name='xh' readonly='readonly' class='text_nor'>";
				var btn = "<button type='button' class='btn_01' id='button_xh' onclick='showDialog(\""+'请选择一个学生'+"\",800,500,\""+'xsxx_xsgl.do?method=showStudents&goto=${path}'+"\");return false;'>选择</button>";
				jQuery("td[name = 'zdybdcon_td_xh']").append(input).append(btn);
				appendHtml=input+btn;
			}
			if(jQuery("#userType").val() != 'stu') {
			 	jQuery("#no").hide();
			}
			if(jQuery("#userType").val() != 'stu' && jQuery("td[name = 'zdybdcon_td_xh']").val() != null ) {
				jQuery("td[name = 'zdybdcon_td_xh']").html(appendHtml);
				jQuery("input[name = 'xh']").val('${xh}');
			}
			parent.moreClick();
		});
	</script>
</html>

