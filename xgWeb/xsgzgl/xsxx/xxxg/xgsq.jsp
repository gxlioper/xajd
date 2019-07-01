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
		//���ø��� 
								jQuery(function(){
									jQuery.MultiUploader({
										maxcount : 3,
										//��׺
										accept : 'png|gif|jpg|jpeg|doc|docx|rar|zip',
										//����ļ���С ��λM
										maxsize: 10,
										//��Ÿ������������id
										elementid : 'fjid'
										});
										if("12303" == jQuery("#xxdm").val()){
										var shgxgx2 = jQuery("#shgxgx2").val();
										jQuery("td").on("change","#shgxgx2",function(){
											if(this.value == '��ע��'){
												jQuery(this).after("<label id='zdybdcon_th_wzlj'>�й�����־Ը����</label>" +
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
								
								//���������Ƿ���֤������������ñ������ñ�����������true
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
			<!-- ������ -->
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
				<h3><span>��ʾ��</span> </h3>
				<p></p>
			</div>
			<!-- ������� -->
			<div style="height: 50px;">
				<div id="navigation" style="background: #fff; expression(eval(document.documentElement.scrollTop));">
					<div class="title_xxxx">
						<span id = "no" class="people_xx"><span id="xmView"></span> ��ѧ�ţ� ${xh }��</span>
						<span class="wxts">��ܰ���ѣ� <span>�����������
								���Կ��ٶ�λ������Ҫ�鿴����Ϣ</span>
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
								<div class="bz">"<span class="red">*</span>"Ϊ������  </div>
								<div class="btn">
									<logic:notEqual name="xxdm" value="11600">
										<logic:notEqual name="xxdm" value="10834">
											<logic:notEqual name="xxdm" value="9800003">
												<logic:notEqual name="xxdm" value="10264">
													<logic:notEqual name="xxdm" value="110501">
														<logic:notEqual name="xxdm" value="12866">
															<button type="button" type="button" onclick="printTyXjk();return false;">
																ѧ���ǼǱ��ӡ
															</button>
														</logic:notEqual>
													</logic:notEqual>
												</logic:notEqual>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<logic:equal name="xxdm" value="10264">
										<button type="button" type="button" onclick="printXsdjb();return false;">
											�����ǼǱ��ӡ
										</button>
									</logic:equal>
									<logic:equal name="xxdm" value="12866">
										<button type="button" type="button" onclick="printXsdjb();return false;">
											�����ǼǱ��ӡ
										</button>
									</logic:equal>
									<button name="����" id="buttonSave" type="button" onclick="saveForm('save');return false;">
										����ݸ�
									</button>
									<button name="�ύ" id="buttonSubmit" type="button" onclick="saveForm('submit');return false;">
										�ύ����
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
		
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
	</body>
	<script type='text/javascript'>
		jQuery(function() {		
			var appendHtml="";
			if(jQuery("td[name = 'zdybdcon_td_xh']").html() != null && jQuery("#userType").val() != 'stu') {
				var input = "<input type='text' id='xh' name='xh' readonly='readonly' class='text_nor'>";
				var btn = "<button type='button' class='btn_01' id='button_xh' onclick='showDialog(\""+'��ѡ��һ��ѧ��'+"\",800,500,\""+'xsxx_xsgl.do?method=showStudents&goto=${path}'+"\");return false;'>ѡ��</button>";
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

