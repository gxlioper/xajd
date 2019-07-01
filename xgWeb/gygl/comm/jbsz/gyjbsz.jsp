<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsxx/comm/jbsz/ymsz.js"></script>
		<script language="javascript">
		
		//�����ֶ�����
		function saveJbsz(){
			if (confirm("��Ҫ���������õĸ����������ȷ�ϣ�")) {
				saveUpdate('/xgxt/gyglJbsz.do?method=gyjbsz&doType=save','');
			}
		}
		
		//ҳ���ʼ��
		function onShow(){
		
			//����У��԰��ֵ
			setXqYqValue();
			
			//���ô�����ϵ
			setCsgx();		
		}
		
		//����У��԰��ֵ
		function setXqYqValue(){
		
			//У��
			if($("czxq_yes").checked){
				$("hid_czxq").value = $("czxq_yes").value;
			}else{
				$("hid_czxq").value = $("czxq_no").value;
			}
			
			//԰��
			if($("czyq_yes").checked){
				$("hid_czyq").value = $("czyq_yes").value;
			}else{
				$("hid_czyq").value = $("czyq_no").value;
			}
		}
		
		//���ô�����ϵ
		function setCsgx(){
		
			//����У��
			var czxq = $("hid_czxq").value;
			//����԰��
			var czyq = $("hid_czyq").value;
			
			if(czxq == "��" && czyq == "��"){
				$("p_xyl").style.display = "";
				$("p_xl").style.display = "";
				$("p_yl").style.display = "";
			}else if(czxq == "��" && czyq == "��"){
				$("p_xyl").style.display = "none";
				$("p_xl").style.display = "";
				$("p_yl").style.display = "none";
			}else if(czxq == "��" && czyq == "��"){
				$("p_xyl").style.display = "none";
				$("p_xl").style.display = "none";
				$("p_yl").style.display = "";
			}else{
				$("p_xyl").style.display = "none";
				$("p_xl").style.display = "none";
				$("p_yl").style.display = "none";
			}
		}
		
		//���У��
		function clickXq(obj){
			$('hid_czxq').value = obj.value;
			setCsgx();
			$("csgx_l").checked = true;
		}
		
		//���԰��
		function clickYq(obj){
			$('hid_czyq').value = obj.value;
			setCsgx();
			$("csgx_l").checked = true;
		}
		</script>
	</head>
	
	<body onload="onShow()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/gyglJbsz">
		
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			
			<div class="tab">		
				<!-- ҳ�������� -->
				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>��Ԣ��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<!-- У�� -->
						<tr>
							<th width="30%">
								����<bean:message key="lable.xiaoqu" />
							</th>
							<td>
								<html:radio property="czxq" styleId="czxq_yes" value="��" 
									onclick="clickXq(this)"/>��
								<html:radio property="czxq" styleId="czxq_no" value="��"
									onclick="clickXq(this)"/>��
								<input type="hidden" id="hid_czxq" value=""/>
								
								<!-- ��ʾ -->
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>
									<font color="blue">
										���ڿ��ƴ�����ϵ�Լ������������Ƿ���Ҫ����<bean:message key="lable.xiaoqu" />��Ŀ
									</font>
								</span>
							</td>
						</tr>
						<!-- ԰�� -->
						<tr>
							<th>
								����<bean:message key="lable.yuanqu" />
							</th>
							<td>
								<html:radio property="czyq" styleId="czyq_yes" value="��"
									onclick="clickYq(this)"/>��
								<html:radio property="czyq" styleId="czyq_no" value="��"
									onclick="clickYq(this)"/>��
								<input type="hidden" id="hid_czyq" value=""/>
								
								<!-- ��ʾ -->
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>
									<font color="blue">
										���ڿ��ƴ�����ϵ�Լ������������Ƿ���Ҫ����<bean:message key="lable.yuanqu" />��Ŀ
									</font>
								</span>
								
							</td>
						</tr>
						<!-- ¥��������ϵ -->
						<tr>
							<th>
								<bean:message key="lable.ld" />������ϵ
							</th>
							<td>
								
								<!-- У��,԰��,¥�� -->
								<span id="p_xyl" style="display:none">
									<html:radio property="csgx" styleId="csgx_xyl" value="1"/>
									<bean:message key="lable.xiaoqu" /> -->
									<bean:message key="lable.yuanqu" /> -->
									<bean:message key="lable.ld" /></br>
								</span>
								
								<!-- У��,¥�� -->
								<span id="p_xl" style="display:none">
								<html:radio property="csgx" styleId="csgx_xl" value="2"/>
								<bean:message key="lable.xiaoqu" />-->
								<bean:message key="lable.ld" /></br>	
								</span>
								
								<!-- ԰��,¥�� -->
								<span id="p_yl" style="display:none">
								<html:radio property="csgx" styleId="csgx_yl" value="3"/>
								<bean:message key="lable.yuanqu" />-->
								<bean:message key="lable.ld" /></br>
								</span>
								
								<!-- ¥�� -->
								<span id="p_l">
								<html:radio property="csgx" styleId="csgx_l" value="4"/>
								��<bean:message key="lable.ld" />
								</span>
								
								<!-- ��ʾ -->
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>
									<font color="blue">
										���ڿ������ҷ��䣬��λ����ȹ���ģ������Ҵ�����ϵ��ʾ
									</font>
								</span>
							</td>
						</tr>
						<!-- ¥��������ϵ -->
						<tr>
							<th>
								<bean:message key="lable.qs" />�������
							</th>
							<td>
								<!-- ѧԺ -->
								<html:radio property="fpdx" value="xy"/>
								<bean:message key="lable.xy" /></br>
								<!-- �꼶+ѧԺ -->
								<html:radio property="fpdx" value="njxy"/>
								�꼶+<bean:message key="lable.xy" /></br>
								<!-- �꼶+רҵ -->
								<html:radio property="fpdx" value="njzy"/>
								�꼶+<bean:message key="lable.zy" /></br>
								<!-- �༶ -->
								<html:radio property="fpdx" value="bj"/>
								<bean:message key="lable.bj" />
								
								<!-- ��ʾ -->
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<span>
									<font color="blue">
										���ڿ������ҷ���ı���������Լ�����ģ�������������ʾ
									</font>
								</span>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									
								</div>
								<div class="btn">
									<!-- ������� -->
									<button id="btn_bc" onclick="saveJbsz()" style="width: 80px">
										<bean:message key="lable.btn_bc_space" />
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
	
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>