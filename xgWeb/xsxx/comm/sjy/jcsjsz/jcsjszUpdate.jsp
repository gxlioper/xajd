<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">		
		//�����ֶ�����
		function saveZdsz(){
			var xsmc = $("text_xsmc").value;
			
			if (xsmc == ""){
				alert("��ȷ�����ֶε�ҳ����ʾ����");
				return false;
			}
			
			if (confirm("���ٴ�ȷ���������õ��ֶ������Ϣ?")) {
				saveUpdate('/xgxt/sjyJcsjsz.do?method=jcsjszUpdate&doType=save','');
			}
		}
		
		//������ʾ����
		function clickXsmc(value){
			if(value == "same"){
				$("text_xsmc").value = $("zdm").value;
				$("text_xsmc_nr").value = "ѡ�������ſ�ά��";
				$("text_xsmc_nr").disabled = "disabled";
			}else if(value == "other"){
				$("text_xsmc_nr").disabled = "";
				$("text_xsmc_nr").value = "";
				$("text_xsmc").value = "";
			}
		}
		
		//���������
		function clickSzx(lx,value){
			var text_id = "text_"+lx;
			$(text_id).value = value;
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				��������׸�������������壬����������ɡ������򵼡���
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/sjyJcsjsz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="zd" id="zd" value="${rs.zd }"/>
			<input type="hidden" name="zdm" id="zdm" value="${rs.zdm }"/>
			<!-- ������ end-->
			
			<div class="tab">		
				<!-- ҳ�������� -->
				<table class="formlist" border="0" align="center" style="width: 100%;">
					<thead>
						<tr>
							<th colspan="2">
								<span>���ֶΡ�${rs.zdm }����������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="20%">
								ҳ����ʾ
							</td>
							<td>
								<input type="radio" name="rad_xsmc" 
									id="rad_xsmc_same" 
									onclick="clickXsmc(this.value)" 
									value="same"
									<logic:equal name="rs" property="zdm" value="${rs.xsmc }">checked="checked"</logic:equal>
								/>
								ͬ�ֶ���
								<input type="radio" name="rad_xsmc" 
									id="rad_xsmc_other" 
									onclick="clickXsmc(this.value)" 
									value="other"
									<logic:notEqual name="rs" property="zdm" value="${rs.xsmc }">checked="checked"</logic:notEqual>
								/>	
								����
								<logic:equal name="rs" property="zdm" value="${rs.xsmc }">
									<input type="text" id="text_xsmc_nr" 
										style="width: 150px" onblur="$('text_xsmc').value=this.value;" 
										disabled="disabled" value="ѡ�������ſ�ά��"/>				
								</logic:equal>
								<logic:notEqual name="rs" property="zdm" value="${rs.xsmc }">
									<input type="text" id="text_xsmc_nr" 
										style="width: 150px" onblur="$('text_xsmc').value=this.value;" 
										value="${rs.xsmc }"/>
								</logic:notEqual>
								<input type="hidden" name="xsmc" id="text_xsmc" value="${rs.xsmc }"/>
							</td>
						</tr>
						<tr>
							<td>
								ѧ��Ϊ׼
							</td>
							<td>
								<input type="radio" name="rad_xgwz" 
									id="rad_xgwz_yes" onclick="clickSzx('xgwz',this.value)" 
									value="��"
									<logic:equal name="rs" property="xgwz" value="��">checked="checked"</logic:equal>
								/>
								��
								<input type="radio" name="rad_xgwz" 
									id="rad_xgwz_no" onclick="clickSzx('xgwz',this.value)" 
									value="��"
									<logic:equal name="rs" property="xgwz" value="��">checked="checked"</logic:equal>
								/>	
								��
								<input type="hidden" name="xgwz" id="text_xgwz" value="${rs.xgwz }"/>
							</td>
						</tr>
						<tr>
							<td>
								¼������
							</td>
							<td>
								<logic:iterate name="jbszLrxzList" id="lrxzRs">
									<input type="radio" name="rad_lrxz" 
										onclick="clickSzx('lrxz',this.value)" 
										value="${lrxzRs.en }"
										<logic:equal name="lrxzRs" property="en" value="${rs.lrxz }">checked="checked"</logic:equal>
									/>${lrxzRs.cn }
								</logic:iterate>
								<input type="hidden" name="lrxz" id="text_lrxz" value="${rs.lrxz }"/>
							</td>
						</tr>
						<tr>
							<td>
								Ϊ������
							</td>
							<td>
								<input type="radio" name="rad_wkxz" 
									id="rad_xgwz_yes" onclick="clickSzx('wkxz',this.value)" 
									value="����Ϊ��"
									<logic:equal name="rs" property="wkxz" value="����Ϊ��">checked="checked"</logic:equal>
								/>
								����Ϊ��
								<input type="radio" name="rad_wkxz" 
									id="rad_xgwz_no" onclick="clickSzx('wkxz',this.value)" 
									value="����Ϊ��"
									<logic:equal name="rs" property="wkxz" value="����Ϊ��">checked="checked"</logic:equal>
								/>	
								����Ϊ��
								<input type="hidden" name="wkxz" id="text_wkxz" value="${rs.wkxz }"/>
							</td>
						</tr>
						<tr>
							<td>
								¼����ʽ
							</td>
							<td>
								<logic:iterate name="rs" property="lrxsList" id="lrxsRs">
									<input type="radio" name="rad_lrxs" 
										onclick="clickSzx('lrxs',this.value)" 
										value="${lrxsRs.en }"
										<logic:equal name="lrxsRs" property="en" value="${rs.lrxs }">checked="checked"</logic:equal>
									/>${lrxsRs.cn }
								</logic:iterate>
								<input type="hidden" name="lrxs" id="text_lrxs" value="${rs.lrxs }"/>
							</td>
						</tr>
						<tr>
							<td>
								������Դ��
							</td>
							<td>
								<logic:empty name="rs" property="lybList">
									<input type="radio" name="rad_lyb" checked="checked"/>
									������Դ��
								</logic:empty>
								<logic:notEmpty name="rs" property="lybList">
									<logic:iterate name="rs" property="lybList" id="lybRs">
										<input type="radio" name="rad_lyb" 
											onclick="clickSzx('lyb',this.value)" 
											value="${lybRs.en }"
											<logic:equal name="lybRs" property="lyb" value="${rs.lyb }">checked="checked"</logic:equal>
										/>${lybRs.lybm }
									</logic:iterate>
								</logic:notEmpty>
								<input type="hidden" name="lyb" id="text_lyb" value="${rs.lyb }"/>
							</td>
						</tr>
						<tr>
							<td>
								�Ƿ�����
							</td>
							<td>
								<input type="radio" name="rad_sfqy" 
									id="rad_sfqy_yes" onclick="clickSzx('sfqy',this.value)" 
									value="��"
									<logic:equal name="rs" property="sfqy" value="��">checked="checked"</logic:equal>
								/>
								��
								<input type="radio" name="rad_sfqy" 
									id="rad_sfqy_no" onclick="clickSzx('sfqy',this.value)" 
									value="��"
									<logic:equal name="rs" property="sfqy" value="��">checked="checked"</logic:equal>
								/>	
								��
								<input type="hidden" name="sfqy" id="text_sfqy" value="${rs.sfqy }"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									
									<button type="button" onclick="saveZdsz()" id="buttonSave">
										�� ��
									</button>
										
									<button type="button" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
									
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<!-- ��ʾ��Ϣ end-->
		</html:form>
	</body>
</html>