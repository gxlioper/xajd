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
				//��������ѡ��
				loadViewMkxxSelectOptions();
				//����radioѡ��
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					})
				}
				//�ǵ�һ��������˻ذ�ť
//				var firstRddc = jQuery("input[name=tempXmdm]").first().val();
//				if (firstRddc != ""){
//					jQuery("#btn_th").css("display","");
//				}

				//var shxm = jQuery("input[name=tempXmdm][value!=]").last().val();

				var xmdms = jQuery("input[name=tempXmdm]");
				var shxm = "";
				jQuery.each(xmdms,function(i,e){
					var textVal = jQuery(e).val();
					if (textVal != null && textVal != ''){
						shxm = textVal;
					}
				});

				if (shxm != ""){
					jQuery("#shxmdm").val(shxm);
				}
				jQuery.ajaxSetup({async:false});
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#guid").val()+"&tt="+new Date().getTime());
				getXmje();
				jQuery.ajaxSetup({async:true});
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+jQuery("#shlc").val()+"&shid="+jQuery("#shid").val());
				jQuery.ajaxSetup({async:true});

				//�жϽ���Ƿ��������޸�
				var jesfxssq=jQuery("#jesfxssq").val();
				if(jesfxssq=='1'){
					jQuery('.je').css("display","");
				}else{
					jQuery('.je').css("display","none");
				}
			});
			function jgcxView(){
				var url = "xszz_xszzbjpy_jgcxgl.do?method=jgcxView&xn=${mkxxForm.xn}&xq=${mkxxForm.xq}&sqr=${mkxxForm.xh}&shztbjpy=99&xmdm=${mkxxForm.dqxmdm}";
				var title = "�鿴��������";
				showDialog(title,800,500,url);
			}
		</script>
	</head>
	<body>
		<html:form action="/xszz_sqsh" method="post" styleId="sqshForm">
			<html:hidden property="guid" styleId="guid"/>
			<html:hidden property="xtgwid" styleId="xtgwid"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<html:hidden property="shlc" styleId="shlc"/>
			<html:hidden property="shid" styleId="shid"/>
			<input type="hidden" name="jesfxssq" id="jesfxssq" value="${xmwhForm.jesfxssq }"/>
			<input type="hidden" name="jesx" id="jesx" value="${xmwhForm.je}"/>
			<input type="hidden" name="oldshxmdm" id="oldshxmdm" value="${xmwhForm.xmdm}"/>
			<input type="hidden" name="sqje" id="sqje" value="${mkxxForm.je}"/>
		
			<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>��ͥ���
									<a onclick="showJtqk(this);" class="up" 
									   href="javascript:void(0);">
									   <font color="blue">���չ��/����</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<!-- ���칤�̿�ʼ -->
					<logic:equal value="11799" name="xxdm" >
						<tbody id="t_jtqk" style="display: none;">
							<tr>
								<td colspan="4">
									<div id="div_jtqk">
									
									</div>
								</td>
							</tr>
						</tbody>
						<thead>
							<tr>
								<th colspan="4">
									<span>�������϶���Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody id="knsrd">
							<tr>
								<td colspan="4">
									<div class="con_overlfow">
										<table class="dateline" width="100%">
											<thead>
												<tr>
													<td>ѧ��</td>
													<td>ѧ��</td>
													<td>�϶�����</td>
													<td ><b>�϶�ʱ��</b></td>
													<logic:equal value="10335" name="xxdm" scope="request">
														<td ><b>�϶�״̬</b></td>
													</logic:equal>	
												</tr>
											</thead>
											<tbody>
												<logic:present name="knsInfoList">
													<logic:notEmpty name="knsInfoList">
														<logic:iterate id="k" name="knsInfoList">
															<tr>
																<td>${k.xn }</td>
																<td>${k.xqmc }</td>
																<td>${k.dcmc }</td>
																<td >
																	${k.sqsj}
																</td>
																<logic:equal value="10335" name="xxdm" scope="request">
																	<td >
																		${k.sfqxrd}
																	</td>	
																</logic:equal>	
															</tr>
														</logic:iterate>
													</logic:notEmpty>
													<logic:empty name="knsInfoList">
														<tr>
															<logic:equal value="10335" name="xxdm" scope="request">
																<td colspan="5" style="text-align:center;">δ�ҵ��κμ�¼��</td>
															</logic:equal>
															<logic:notEqual value="10335" name="xxdm" scope="request">
																<td colspan="4" style="text-align:center;">δ�ҵ��κμ�¼��</td>
															</logic:notEqual>
														</tr>
													</logic:empty>
												</logic:present>
												<logic:notPresent name="knsInfoList">
													<tr>
														<logic:equal value="10335" name="xxdm" scope="request">
															<td colspan="5" style="text-align:center;">δ�ҵ��κμ�¼��</td>
														</logic:equal>
														<logic:notEqual value="10335" name="xxdm" scope="request">
															<td colspan="4" style="text-align:center;">δ�ҵ��κμ�¼��</td>
														</logic:notEqual>
													</tr>
												</logic:notPresent>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<!-- ���칤�̽��� -->
					<tbody id="t_jtqk" style="display: none;">
						<tr>
							<td colspan="4">
								<div id="div_jtqk">
								
								</div>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��������</th>
							<td>${mkxxForm.xn }&nbsp;${mkxxForm.xqmc }</td>
							<th>��Ŀ��������</th>
							<td>${mkxxForm.pdxn }&nbsp;${mkxxForm.pdxqmc }</td>
						</tr>
						<tr>
							<th>��Ŀ����</th>
							<td>${mkxxForm.xmmc }</td>
							<th>���</th>
							<td>${mkxxForm.je }</td>
						</tr>
						<tr>
							<th>
								������Ϣ
							</th>
							<td colspan="3">
								<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
								<html:hidden property="ylzd5" styleId="fjid"/>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = jQuery('#fjid').val();
										jQuery.MultiUploader_q({
											gid : gid
											});
									});
								</script>
							</td>
						</tr>
					</tbody>
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxView.jsp" %>
					<logic:equal name="SFBJPY_Y" value="true">
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶������<a href='javascript:void(0);' class='name' onclick='jgcxView();return false;'>�鿴��������</a></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								����С���Ա
							</th>
							<td colspan="3">
								${mkxxForm.bjpyxzcyxms }
							</td>
						</tr>
						<tr>
							<th>
								����С�����
							</th>
							<td colspan="3">
								${mkxxForm.bjpyxzdbxms }
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgshztmc }
							</td>
						</tr>
						<tr>
							<th>
								�����ʱ��
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgpyhsj }
							</td>
						</tr>
						<tr>
							<th>
								�����ص�
							</th>
							<td colspan="3">
								${mkxxForm.bjpyjgpyhdd }
							</td>
						</tr>
						<tr>
							<th>
								�������
							</th>
							<td colspan="3" style="word-break: break-all;">
								${mkxxForm.bjpyjgpyyj }
							</td>
						</tr>
					</tbody>
					</logic:equal>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
						
						<logic:present name="tzxmList">
							<tr>
								<th>�����Ŀ</th>
								<td >
									<html:select property="shxmdm" styleId="shxmdm" onchange="jeSfkt(this)">
										<html:option value="${xmwhForm.xmdm }">${xmwhForm.xmmc }</html:option>
										<logic:equal value="false" name="isSame">
										<html:option value="${mkxxForm.xmdm }">${mkxxForm.xmmc }</html:option>
										</logic:equal>
										
										<html:options collection="tzxmList" property="dm" labelProperty="mc"/>
									</html:select>
								</td>
								<th>
									<font class="je" style="display: none" color="red">*</font>
									<font class="je" style="display: none">��Ŀ���</font>
								</th>
								<td>
									<html:text onkeyup="checkInputData(this);checkJesxSh(this)" maxlength="7" styleClass="je" value="${mkxxForm.je }" property="shxmje" styleId="shxmje" style="display: none;width:100px"></html:text>
									<font class="je" id="message" style="display: none" color="blue">���޽�${xmwhForm.je }</font>
								</td>
							</tr>
						</logic:present>
						
						<logic:notPresent name="tzxmList">
							<tr style="display:none;">
								<th>�����Ŀ</th>
								<td colspan="3">
									<input name="shxmdm" value="${mkxxForm.xmdm }"/>
								</td>
							</tr>
						</logic:notPresent>
						<tr>
							<th>
								<font color="red">*</font>��˽��
							</th>
							<td colspan="3" id="shjgSpan">
								
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">*</font>
								������
								<br/>
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xszz&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px;" rows="5"
											   onblur="checkLen(this,200);" styleId="shyj"
								></html:textarea>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�ѻ�������Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table class="formList" width="100%">
									<thead align="center">
										<tr align="center" style="text-align: center;">
											<td>
												<b>ѧ��</b>
											</td>
											<td>
												<b>ѧ��</b>
											</td>
											<td>
												<b>��Ŀ����</b>
											</td>
											<td>
												<b>���</b>
											</td>
											<td>
												<b>����ʱ��</b>
											</td>
										</tr>
									</thead>
									<tbody align="center">
										<logic:notEmpty name="zzxmjgInfoList">
											<logic:iterate name="zzxmjgInfoList" id="zzxmjgInfo">
												<tr style="cursor: hand">
													<td>
														${zzxmjgInfo.xn}
													</td>
													<td>
														${zzxmjgInfo.xqmc}
													</td>
													<td>
														${zzxmjgInfo.xmmc}
													</td>
													<td>
														${zzxmjgInfo.je}
													</td>
													<td>
														${zzxmjgInfo.sqsj}
													</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</tbody>
								</table>
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
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" id="btqd" onclick="saveZzsh();">
										�� ��
									</button>
									<%--<button type="button" onclick="saveZzsh('1','ͨ��');">
										ͨ��
									</button>
									<button type="button" onclick="saveZzsh('2','��ͨ��');">
										��ͨ��
									</button>
									<button type="button" onclick="saveZzsh('3','�˻�');" id="btn_th" style="display:none;">
										�˻�
									</button>
									--%><button type="button" name="�� ��" onclick="iFClose();">
										�� ��
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

