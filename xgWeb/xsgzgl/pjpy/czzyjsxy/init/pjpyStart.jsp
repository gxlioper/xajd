<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){
			var pjzq = jQuery("#pjzq_checked").val();
			if(pjzq == "xn"){

			}else if(pjzq == "xq"){
				$('tr_pjxq').style.display='';
			}
			
			var cpz = jQuery("#cpz_checked").val();
			clickCpz(cpz,"onshow");
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
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
				<span>
				1.����Ҫ�ڱ�ҳ��Ա���������һЩ<font color="blue">��������</font>�������ã���Щ���ý�Ӱ��<font color="blue">������������</font>��<br/>
				2.<font color="blue">��������</font>��ζ��ѧУ��������ѧ��Ϊ��λ������ѧ��Ϊ��λ�ģ�������ͬʱӰ��<font color="blue">�۲������</font>�Լ�<font color="blue">��������</font>�Ļ�ȡ��<br/>
				3.�����������Ϊ<font color="blue">ѧ��</font>�������ñ���<font color="blue">����ѧ��</font>��������Ӱ����������������<font color="blue">ѧ���ȡֵ</font>��<br/>
				4.�����������Ϊ<font color="blue">ѧ��</font>�������ñ���<font color="blue">����ѧ�꼰ѧ��</font>��������Ӱ����������������<font color="blue">ѧ�ꡢѧ�ڵ�ȡֵ</font>��<br/>
				5.<font color="blue">�۲���������������</font>��ѡ����ҪӰ���ۺ����ʲ����ܷ�<font color="blue">����</font>ʱ��������ʲôΪ׼��<br/>
				6.��<font color="blue">�������ڻ�������ѧ�ꡢ����ѧ�ڷ����仯</font>�Ļ���ϵͳ��ΪѧУ�Ѿ�����<font color="blue">�µ���������</font>��<br/>
				7.ÿ�˽���<font color="blue">�µ���������</font>��ϵͳ��ִ��������ݵĳ�ʼ���������������<font color="blue">ҳ�汸ע</font>��<br/>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="hidden_start" value="no"/>
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">	
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>������������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="30%">
								��������				
							</th>
							<td width="">
								<html:radio property="pjzq" styleId="pjzq_xn" 
									value="xn" 
									onclick="checkRadio(this);$('tr_pjxq').style.display='none';setStartBz();"/>��ѧ������
<%--								<html:radio property="pjzq" styleId="pjzq_xq" --%>
<%--									value="xq"  disabled="true"--%>
<%--									onclick="checkRadio(this);$('tr_pjxq').style.display='';setStartBz();"/>��ѧ������--%>
								<html:hidden property="pjzq" styleId="pjzq_checked"/>
								<input type="hidden" id="hidden_pjzq" value="${pjzq }"/>
							</td>
						</tr>
						<tr>
							<th width="">
								<font color="red">*</font>����ѧ��		
							</th>
							<td width="">
								<html:select property="pjxn" styleId="pjxn" onchange="setStartBz()">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
								<input type="hidden" id="hidden_pjxn" value="${pjxn }"/>
							</td>
						</tr>
						<tr id="tr_pjxq" style="display:none">
							<th width="">
								<font color="red">*</font>����ѧ��
							</th>
							<td width="">
								<html:select property="pjxq" styleId="pjxq" onchange="setStartBz()">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
								<input type="hidden" id="hidden_pjxq" value="${pjxq }"/>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="">
							<th width="">
								������
							</th>
							<td width="">
								<html:radio property="cpz" styleId="cpz_no" 
									value="no" 
									onclick="checkRadio(this);clickCpz(this.value,'');"
								/>���������
								<html:radio property="cpz" styleId="cpz_yes" 
									value="yes" 
									onclick="checkRadio(this);clickCpz(this.value,'');"
								/>��Ҫ������
								<html:hidden property="cpz" styleId="cpz_checked"/>
							</td>
						</tr>
						<tr id="tr_zcpm">
							<th width="">
								�۲�����
							</th>
							<td width="">
								<html:radio property="zcpm" styleId="zcpm_njxy" value="1" onclick="checkRadio(this)"/>�꼶+Ժϵ����
								<html:radio property="zcpm" styleId="zcpm_njzy" value="2" onclick="checkRadio(this)"/>�꼶+רҵ����
								<html:radio property="zcpm" styleId="zcpm_bj" value="3" onclick="checkRadio(this)"/>�༶����
								<html:radio property="zcpm" styleId="zcpm_cpz" value="0" onclick="checkRadio(this)"/>����������
								<html:hidden property="zcpm" styleId="zcpm_checked"/>
							</td>
						</tr>
						<tr id="tr_zypm">
							<th width="">
								��������
							</th>
							<td width="">
								<html:radio property="zypm" styleId="zypm_njxy" value="1" onclick="checkRadio(this)"/>�꼶+Ժϵ����
								<html:radio property="zypm" styleId="zypm_njzy" value="2" onclick="checkRadio(this)"/>�꼶+רҵ����
								<html:radio property="zypm" styleId="zypm_bj" value="3" onclick="checkRadio(this)"/>�༶����
								<html:radio property="zypm" styleId="zypm_cpz" value="0" onclick="checkRadio(this)"/>����������
								<html:hidden property="zypm" styleId="zypm_checked"/>
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<td colspan="2">
							<!--��ע begin-->
							<div class="readme"  id="div_csh_no" style="height: 220px">
								<h2>����δ�����仯</h2>
								<div class="readcon">
									<ul>
										<li>ϵͳ����ִ����س�ʼ�������������޸�������ã���ǰ�����ģ����д���</li>
									</ul>
								</div>
							</div>	
							<div class="readme"  id="div_csh_yes" style="height: 220px" style="display: none">
								<h2>���ڷ����仯�������ϵͳ�����Զ�ִ�����й���</h2>
								<div class="readcon">
									<ul>
										<li id="li_001">���Ǳ����ڵ�������¼���Ƶ�������ʷ�⣬�������Щ��Ϣ</li>
										<li id="li_002">����ʼ��������Ա�⣬�����������ǰ��"������Ա����"����</li>
										<li id="li_003">����ʼ����һ�������ڵĲ���С�����ã�����������Ҫ������Ļ�����ǰ��"����С������"ά��</li>
										<li id="li_004">��Ҫ����ǰһ��������Ŀ�����б������ǰ��"������Ŀά��"���е���</li>
										<li id="li_008">��Ҫ����ǰһ���۲���Ŀ�����б������ǰ��"�۲���Ŀά��"���е���</li>
										<li id="li_005">��Ҫ����ǰһ�������������ã����б������ǰ��"������Ŀά��"���е���</li>
										<li id="li_006">��Ҫ����ǰһ������������ã����б������ǰ��"������Ŀά��"���е���</li>
										<li id="li_007">��Ҫ����ǰһ�ΰ༶�������ã����б������ǰ��"�༶��������"���е���</li>
									</ul>
								</div>
							</div>	
							<!--��ע end-->
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" name="����" onclick="checkSaveStart();return false;">�� ��</button>
									<button type="button" name="�ر�" onclick="Close();return false;">�� ��</button>
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