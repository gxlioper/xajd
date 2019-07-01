<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//ִ����һ������
		function doNext(){
		
			var step_now = $("step").value;
			var num = parseInt(step_now.replace("step",""))+1;
			var step = "step"+num;
			var url = "/xgxt/sjyJcsjsz.do?method=jcsjszGuide";
				url+= "&step="+step;

			//��֤����1
			if(step_now == "step1"){
				if(checkStep1()){
					//�����ֶ���
					creatZdm();
					refreshForm(url);
				}else{
					alert("������ѡ��һ����Ҫ���õ��ֶΣ��Ա��������");
					return false;
				}
			}
			
			//��֤����2
			if(step_now == "step2"){
				if(checkStep2()){
					refreshForm(url);
				}else{
					alert("��ȷ�������ֶε�ҳ����ʾ���Ա��������");
					return false;
				}
			}
			
			//��֤����3
			if(step_now == "step3"){
				if(checkStep3()){
					refreshForm(url);
				}else{
					alert("��ȷ�������ֶ��Ƿ���ѧ��Ϊ׼���Ա��������");
					return false;
				}
			}
			
			//��֤����4
			if(step_now == "step4"){
				if(checkStep4()){
					refreshForm(url);
				}else{
					alert("��ȷ�������ֶε�¼�����ƣ��Ա��������");
					return false;
				}
			}
			
			//��֤����5
			if(step_now == "step5"){
				if(checkStep5()){
					refreshForm(url);
				}else{
					alert("��ȷ�������ֶοɷ�Ϊ�գ��Ա��������");
					return false;
				}
			}
			
			//��֤����6
			if(step_now == "step6"){
				var zdm = checkStep6();
				if(zdm == ""){
					refreshForm(url);
				}else{
					alert("�ֶ�\""+checkStep6()+"\"��δѡ��¼����ʽ����ȷ���Ա��������");
					return false;
				}
			}
			
			//��֤����8
			if(step_now == "step8"){
				if(checkStep8()){
					refreshForm(url);
				}else{
					alert("��ȷ�������ֶ��Ƿ����ã��Ա��������");
					return false;
				}
			}
			
			refreshForm(url);
		}
		
		//ִ����һ������
		function doPrevious(){
			var step_now = $("step").value;
			var num = parseInt(step_now.replace("step",""))-1;
			var step = "step"+num;
			var url = "/xgxt/sjyJcsjsz.do?method=jcsjszGuide";
				url+= "&step="+step;
				
			refreshForm(url);
		}
		
		//�ر���ʾ��
		function closeTsxxDiv(id){
			$(id).style.height = "450px";
		}
		
		//ѡ���ֶ�
		function clickStepZd(zd){
		
			var rad_id = "rad_zd_"+zd;
			var div_id = "div_zdsz_"+zd;
			
			var div_num = $("tb_sznr").getElementsByTagName('div').length;
	
			for(var i=0;i<div_num;i++){
				var obj = $("tb_sznr").getElementsByTagName('div')[i];
				var obj_id = obj.id;
				if(obj_id == div_id){
					$(obj_id).style.display = "";
				}else{
					$(obj_id).style.display = "none";
				}
			}
		}
		
		//�����ֶ�����
		function saveZdsz(){
			if (confirm("���ٴ�ȷ���������õ��ֶ������Ϣ?")) {
				saveUpdate('/xgxt/sjyJcsjsz.do?method=jcsjszGuide&doType=save','');
			}
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>������������ - ������</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/sjyJcsjsz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="step" id="step" value="${step }"/>
			<!-- ������ end-->
			
			<div class="tab">		
				<!-- ҳ�������� -->
				<table class="formlist" border="0" align="center" style="width: 100%;">
					<thead>
						<tr>
							<th colspan="2">
								<span>������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="15%" height="480px">
								<!-- �������� -->
								<table style="width: 100%">
									<logic:iterate name="stepList" id="stepRs" >
										<tr>
											<td>
												<font color="${stepRs.color }">${stepRs.mc }</font>
											</td>
										</tr>
									</logic:iterate>
								</table>
								<!-- �������� end-->
							</td>
							<td>
								<!-- �ǵ�һ�� -->
								<logic:notEqual name="step" value="step1">
									<logic:iterate name="kczzdList" id="kczzd">
										<!-- �ֶ� -->
										<input type="hidden" name="ch_zd" value="${kczzd.zd }"/>
										<!-- �ֶ��� -->
										<input type="hidden" name="ch_zdm" id="ch_zd_${kczzd.zd }" value="${kczzd.zdm }"/>
										<!-- ��Դ������ -->
										<input type="hidden" name="lybNum" id="ch_lybNum_${kczzd.zd }" value="${kczzd.lybNum }"/>
										
										<!-- �ǵڶ��� -->
										<logic:notEqual name="step" value="step2">
											<!-- ��ʾ���� -->
											<input type="hidden" name="xsmc" id="ch_xsmc_${kczzd.zd }" value="${kczzd.xsmc }"/>
										</logic:notEqual>
										
										<!-- �ǵ����� -->
										<logic:notEqual name="step" value="step3">
											<!-- ѧ��Ϊ׼ -->
											<input type="hidden" name="xgwz" id="ch_xhwz_${kczzd.zd }" value="${kczzd.xgwz }"/>
										</logic:notEqual>
										
										<!-- �ǵ��Ĳ� -->
										<logic:notEqual name="step" value="step4">
											<!-- ¼������ -->
											<input type="hidden" name="lrxz" id="ch_lrxz_${kczzd.zd }" value="${kczzd.lrxz }"/>
										</logic:notEqual>
										
										<!-- �ǵ��岽 -->
										<logic:notEqual name="step" value="step5">
											<!-- ¼������ -->
											<input type="hidden" name="wkxz" id="ch_wkxz_${kczzd.zd }" value="${kczzd.wkxz }"/>
										</logic:notEqual>
										
										<!-- �ǵ����� -->
										<logic:notEqual name="step" value="step6">
											<!-- ¼����ʽ -->
											<input type="hidden" name="lrxs" id="ch_lrxs_${kczzd.zd }" value="${kczzd.lrxs }"/>
										</logic:notEqual>
										
										<!-- �ǵ��߲� -->
										<logic:notEqual name="step" value="step7">
											<!-- ��Դ�� -->
											<input type="hidden" name="lyb" id="ch_lyb_${kczzd.zd }" value="${kczzd.lyb }"/>
											<!-- ��Դ������ -->
											<input type="hidden" name="lybm" id="ch_lybm_${kczzd.zd }" value="${kczzd.lybm }"/>
										</logic:notEqual>
										
										<!-- �ǵڰ˲� -->
										<logic:notEqual name="step" value="step8">
											<!-- �Ƿ����� -->
											<input type="hidden" name="sfqy" id="ch_sfqy_${kczzd.zd }" value="${kczzd.sfqy }"/>
										</logic:notEqual>
										
									</logic:iterate>
								</logic:notEqual>
								<!-- ��һ�� -->
								<logic:equal name="step" value="step1">										
									<%@ include file="guide/guideStep1.jsp"%>
								</logic:equal>
								<!-- �ڶ��� -->
								<logic:equal name="step" value="step2">
									<%@ include file="guide/guideStep2.jsp"%>
								</logic:equal>
								<!-- ������ -->
								<logic:equal name="step" value="step3">
									<%@ include file="guide/guideStep3.jsp"%>
								</logic:equal>
								<!-- ���Ĳ� -->
								<logic:equal name="step" value="step4">
									<%@ include file="guide/guideStep4.jsp"%>
								</logic:equal>
								<!-- ���岽 -->
								<logic:equal name="step" value="step5">
									<%@ include file="guide/guideStep5.jsp"%>
								</logic:equal>
								<!-- ������ -->
								<logic:equal name="step" value="step6">
									<%@ include file="guide/guideStep6.jsp"%>
								</logic:equal>
								<!-- ���߲� -->
								<logic:equal name="step" value="step7">
									<%@ include file="guide/guideStep7.jsp"%>
								</logic:equal>
								<!-- �ڰ˲� -->
								<logic:equal name="step" value="step8">
									<%@ include file="guide/guideStep8.jsp"%>
								</logic:equal>
								<!-- ���ղ� -->
								<logic:equal name="step" value="${step_last }">
									<%@ include file="guide/guideFinal.jsp"%>
								</logic:equal>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									
									<!-- �����ղ� -->
									<logic:notEqual name="step" value="${step_last }">
										<!-- �ǵ�һ�� -->
										<logic:notEqual name="step" value="step1">
											<button type="button" onclick="doPrevious()" id="buttonSave">
												��һ��
											</button>
										</logic:notEqual>
									
										<button type="button" onclick="doNext()" id="buttonSave">
											��һ��
										</button>
									</logic:notEqual>
									
									<!-- �����ղ� -->
									<logic:equal name="step" value="${step_last }">
									
										<button type="button" onclick="doPrevious()" id="buttonSave">
											��һ��
										</button>
									
										<button type="button" onclick="saveZdsz()" id="buttonSave">
											�� ��
										</button>
									</logic:equal>
									
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