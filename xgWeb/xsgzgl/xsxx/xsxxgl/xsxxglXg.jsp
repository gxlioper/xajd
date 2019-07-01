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
		<script type="text/javascript" src="xsgzgl/xsxx/xsxxgl/js/xsxxglXg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
			<script type="text/javascript">
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
										// 中国青年志愿者  http://www.zgzyz.org.cn/
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
							</script>
	</head>
	<body >	
	<html:form action="/xsxx_xsxxgl" method="post" styleId="form1">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="sfxsgban" id="sfxsgban" value="${sfxsgban} "/>
			<input type="hidden" name="zpsfbt" id="zpsfbt" />
			<input type="hidden" name="zpsfcz" id="zpsfcz"  value="${zpsfcz }"/>
			<input type="hidden" name="xxdm" id="xxdm"  value="${xxdm }"/>
			<input type="hidden" name="xh" id="xh" value='${xh}'/>
			<input type="hidden" name="yyfj" id="yyfj" value="${yyfj}" />
			<!-- 浮动框架 -->
			<div style="height: 50px;">
				<div id="navigation" style="top: 0; background: #fff; position: fixed; width: 100%;_position:absolute;_bottom:auto;_top:expression(eval(document.documentElement.scrollTop));">
					<div class="title_xxxx">
						<span class="people_xx"><span id="xmView"></span> （学号： ${xh }）</span>
						<logic:notEqual value="11458" name="xxdm">
						<span class="wxts">温馨提醒： <span>
						点击下面的类别，
								可以快速定位到您所要查看的信息
						</span>
						</span>
					
						</logic:notEqual>
					</div>
					<logic:equal value="11458" name="xxdm">
					<div style="background-color:#fffedf;height: 95px;line-height: 25px;border-bottom: #4381d1 1px solid;margin-top: 4px;">
						<span style="float:left; color:#ff9600; padding-left:10px;">温馨提醒： <span style="color:#666666;">
						自本学期开始，请各位同学及时通过学生事务信息系统来更新学生的相关信息。
						此信息将与学生的资助学金、就业、评优评奖、毕业等息息相关，请务必重视！
						在开展奖学金、助学金以及其他很多工作我们大都使用本系统基本信息中大学填写的手机号码和e-mail跟大家联系，
						在联系中我们发现很多同学手机号码没有或者停机，e-mail地址填写错误，
						导致最后联系不上大家，这样或许会使你丧失某些你很希望得到的机会，
						为避免这种情况，建议你随时保持你基本信息最新状态，尤其是正确的手机号码、准确无误的电子信箱和现住址。
						</span></span>
						</div>
					</logic:equal>
					
				</div>
			</div>
			<logic:notEqual value="11458" name="xxdm">
			<div class="demo_xxxx" style="margin-top: 20px; overflow-x:hidden;" id="content">
			</logic:notEqual>
			<logic:equal value="11458" name="xxdm">
			<div class="demo_xxxx" style="margin-top: 120px; overflow-x:hidden;" id="content">
			</logic:equal>
			</div>
			<logic:equal name="xxdm" value="13943">
			<div
				 style='width: 100%;overflow-x: hidden; overflow-y: auto;'>
				<table width="100%"  border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>院系意见</span>
							</th>
						</tr>
					</thead>
				<tbody>
					<tr>
							<th align="right">
								院系意见
								<br />
								<font color="red"><B>(限100字)</B>
								</font>
							</th>
							<td align="left" colspan="100">
								<html:textarea property='shgxgzdw1' styleId="shgxgzdw1" style="width:700px" rows='7' value="${shgxgzdw1}"
									onblur="checkLen(this,100)" />
							</td>
						</tr>
				</tbody>
				</table>
			</div>
			</logic:equal>
			<div style="height: 15px"></div>			
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">"<span class="red">*</span>"为必填项  </div>
								<div class="btn">
										<button name="保存" id="buttonSave" type="button" onclick="saveForm();">
											保 存
										</button>
									<button type="button"  name="关闭" onclick="Close()" id="buttonClose">关 闭</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
						
			<div id="zpHidDiv" style="display: none;">			
				<jsp:include flush="true" page="zpxg.jsp"></jsp:include>
			</div>

			<div id="jtcyxxHidDiv" style="display: none;">
				<jsp:include flush="true" page="jtcyxxxg.jsp"></jsp:include>
			</div>						
				
			</html:form>
		
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		<script type="text/javascript" src="js/station/station.js"></script>
	</body>
</html>

