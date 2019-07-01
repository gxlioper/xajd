<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript">
		//�����λ
		function clickGw(shjb){
			var tb_id = "tb_"+shjb;
			if($(tb_id)){
				if($(tb_id).style.display == "none"){
					$(tb_id).style.display = "";
				}else{
					$(tb_id).style.display = "none";
				}
			}
		}
		
		//�������״̬
		function saveShzt(shzt){
			var xxdm = jQuery('#xxdm').val();
			var shyj = jQuery('textarea[name=shyj]').val();
			
			//���ݴ�ѧ����������
			if (shyj == '' && xxdm=='10657'){
				alert('����д������!');
				return false;
			}
			
			var shjb = $("shjb").value;
			$("shzt").value = shzt;
			
			var msg = "ȷ���޸�ѡ�м�¼�����״̬��\n";
				msg+= "ע��\n";
				msg+= "ͨ  ������һ����ɲ鿴���������¼\n";
				msg+= "��ͨ������һ�����޷��鿴���������¼\n";
				if(shjb != "1"){
					msg+= "��  �أ���Ҫ��һ�����������ͨ���󣬱�����Ų鿴���������¼";
				}
				
			if (confirm(msg)) {
				url = "/xgxt/pjpyXmsh.do?method=xmshDetail&doType=sh";
				saveUpdate(url,'');
			}
		}
		
		//����˳����Ŀ
		function saveSyxm(){
			url = "/xgxt/pjpyXmsh.do?method=xmshDetail&doType=xmsy";
			saveUpdate(url,'');
		}
		
		</script>
	</head>
	<body style="overflow-x:hidden">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/pjpyXmsh">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- �����Ŀ -->
			<input type="hidden" name="shxm" id="shxm" value="${shxm }"/>
			<!-- ��˼��� -->
			<input type="hidden" name="shjb" id="shjb" value="${shjb }" />
			<!-- ���״̬ -->
			<input type="hidden" name="shzt" id="shzt" value="" />
			<!-- ���״̬ -->
			<input type="hidden" name="xh" id="xh" value="${rs.xh }" />
			<!-- ��ϸ��Ϣ -->
			<div class="tab">

				<table class="formlist" width="">
					<%@include file="../xmsq/xmxx.jsp" %>
					<%@include file="../xmsq/xsxx.jsp" %>
					<%@include file="../xmsq/zcxx.jsp" %>
					<%@include file="../xmsq/cjxx.jsp" %>
					<%@include file="../xmsq/sqxx.jsp" %>
					<%@include file="../xmsq/qtxx.jsp" %>
				<!-- �����Ϣ -->
				<logic:notEmpty name="rsList">
					<logic:iterate id="shInfo" name="rsList">
						<logic:lessEqual name="shInfo" property="shjb" value="${shjb }">
							<!-- �Ǳ��� -->
							<logic:notEqual name="shInfo" property="shjb" value="${shjb }">
								<thead onclick="hiddenMk('tb_${shInfo.shjb }')">
									<tr style="cursor:hand">
										<th colspan="4">
											<span>��˸�λ��${shInfo.mc}
											</span>
										</th>
									</tr>
								</thead>
								<tbody id="tb_${shInfo.shjb }" style="display: none">
									<tr style="">
										<th align="right" width="20%">
											��˽��
										</th>
										<td align="left" width="30%">
											<logic:equal name="shInfo" property="shzt" value="δ���">
												<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="ͨ��">
												<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="��ͨ��">
												<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="�˻�">
												<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="������">
												<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
											</logic:equal>
										</td>
										<th align="right" width="20%">
											���ʱ��
										</th>
										<td align="left" width="">
											${shInfo.shsj }
										</td>
									</tr>
									<tr style="">
										<th align="right" width="20%">
											������
										</th>
										<td align="left" colspan="3">
											<textarea rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,500)"
												id="shyj_${shjb }" readonly="readonly" type="_moz"><logic:notEqual name="shInfo" property="shyj" value="null">${shInfo.shyj }</logic:notEqual></textarea>
										</td>
									</tr>
								</tbody>
							</logic:notEqual>
							<!-- ���� -->
							<logic:equal name="shInfo" property="shjb" value="${shjb }">
								<thead>
									<tr>
										<th colspan="4">
											<span>��˸�λ��${shInfo.mc }</span>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr style="">
										<th align="right" width="20%">
											��˽��
										</th>
										<td align="left" width="30%">
											<logic:equal name="shInfo" property="shzt" value="δ���">
												<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="ͨ��">
												<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="��ͨ��">
												<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="�˻�">
												<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="shInfo" property="shzt" value="������">
												<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
											</logic:equal>
										</td>
										<th align="right" width="20%">
											���ʱ��
										</th>
										<td align="left" width="">
											<logic:notEqual name="shInfo" property="shzt" value="δ���">
												${shInfo.shsj }
											</logic:notEqual>	
										</td>
									</tr>
									<tr style="">
										<th align="right" width="20%">
											������
											</br>
											<font color="red">(����¼��500��)</font>
										</th>
										<td align="left" colspan="3">
											<textarea rows="5" name="shyj" style="width: 95%;word-break:break-all;" onblur="chLeng(this,500)"
												id="shyj_${shjb }" type="_moz"><logic:notEqual name="shInfo" property="shyj" value="null">${shInfo.shyj }</logic:notEqual></textarea>
										</td>
									</tr>
								</tbody>
							</logic:equal>
						</logic:lessEqual>
					</logic:iterate>
				</logic:notEmpty>
				<!-- �����Ϣ end-->
	
					<!-- ���� -->
					<tfoot>
						<tr>
							<td colspan='4'>
								<div class="bz">

								</div>
								<div class="btn">	
									<!-- �ǲ鿴 -->
									<logic:notEqual name="doType" value="view">						
										<button type="button" onclick="saveShzt('ͨ��')">
											ͨ ��
										</button>
										<button type="button" onclick="saveShzt('��ͨ��')">
											��ͨ��
										</button>
										<!-- ��һ�������˻� -->
										<logic:notEqual name="shjb" value="1">
											<button type="button" onclick="saveShzt('�˻�')">
												�� ��
											</button>
										</logic:notEqual>
									</logic:notEqual>		
									<button type="button" onclick="Close();return false;" id="btn_gb">
										<bean:message key="lable.btn_gb_space" />
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<!-- ���� end-->
			</div>
			<!-- ��ϸ��Ϣ end-->

			<logic:notEmpty name="xmsy">
				<!-- ���״̬ѡ��Div-->
				<div id="syxmDiv" style="display: none;">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th colspan="2">
										<span>���ʧ�ܣ���ȷ����Ҫ˳�ӵ���Ŀ</span>
									</th>
								</tr>
							</thead>
							<tbody>						
								<tr>
									<th width="20%">
										��Ŀѡ��
									</th>
									<td>
										<logic:iterate name="syxmList" id="syxm" indexId="num">
											<logic:equal name="num" value="0">
												<input type="radio" name="rad_shzt" 
													id="syxm_${syxm.xmdm }" value="${syxm.xmdm }" 
													onclick="$('syxm').value = this.value" 
													checked="checked"/>
													${syxm.xmmc }
													</br>
													<input type="hidden" name="syxm"id="syxm" value="${syxm.xmdm }"/>
											</logic:equal>
											<logic:notEqual name="num" value="0">
												<input type="radio" name="rad_shzt" 
													id="syxm_${syxm.xmdm }" value="${syxm.xmdm }" 
													onclick="$('syxm').value = this.value"/>
													${syxm.xmmc }
												</br>
											</logic:notEqual>
										</logic:iterate>	
									</td>
								</tr>	
								<tr>
									<th width="20%">
										���ʧ��ԭ��
									</th>
									<td>
										${message }
									</td>
								</tr>							
							</tbody>
							<tfoot>
							<tr>
								<td colspan='2'>
									<div class="btn">
										<!-- ȷ�� -->
										<button type="button" onclick="saveSyxm();hiddenMessage(true,true);">
											<bean:message key="lable.btn_qd_space" />
										</button>
										<!-- �ر� -->
										<button type="button" onclick="hiddenMessage(true,true);return false;">
											<bean:message key="lable.btn_gb_space" />
										</button>
									</div>
								</td>
							</tr>
							</tfoot>
						</table>
					</div>
				</div>
				<!-- ���״̬Div end-->
				<script language="javascript" defer="defer">
					setTimeout("viewTempDiv('˳����Ŀѡ��ѡ��','syxmDiv','400','250')",100)
				</script>
			</logic:notEmpty>
			<logic:empty name="xmsy">
				<!-- ��ʾ��Ϣ -->
				<%@ include file="/comm/other/tsxx.jsp"%>
			</logic:empty>
			<!-- ���״̬Div end-->
			<script language="javascript" defer="defer">
				$("sqly").readOnly = "true";
			</script>
		</html:form>
	</body>
</html>
