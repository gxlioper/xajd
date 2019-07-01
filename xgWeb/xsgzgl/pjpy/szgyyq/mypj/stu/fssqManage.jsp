<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//ǰ���ҵ�����
		function goMypj(){
			var url = "pjpy_szgyyq_mypj.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//ִ�гɹ�
		function dcSuccess(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			$("had_edit").value = "no";
			alertInfo(result,function(tag){if(tag=="ok"){defalutSqxm();}});
		}
		
		//����Ƿ��޸�
		function saveMethod(){
			confirmInfo('���Ѿ�Ϊ����Ŀ����������¼�������Ƿ���Ҫ���棿',saveFssq);
		}
		</script>
	</head>
	<body onload="createFssqDiv()" >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�ۺ��������ɿ� - �ҵĹ��� - ��������</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.�������в������ǻ���<font color="blue">${xn }</font>ѧ�꣬<font color="blue">${xqmc}</font>ѧ�� չ���ġ�</br>
				2.������ͨ�����<font color="blue">����</font>��<font color="blue">ɾ��</font>����ѡ�����Ŀ�������������ɾ��</br>
				3.���鿴������ļ�¼��㡰<font color="blue">�������(XX��)</font>������ɫ��XX(��ֵ)���в鿴��</br>
				4.���������������޵Ļ�����󼴱�ͨ����ˣ�Ҳ������߷ֽ��м��㡣
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/szgyyq_mypj_stu" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������Ŀ  -->
			<input type="hidden" name="czxm" id="shxm" value="${czxm }"/>
			<!-- �Ƿ��޸� -->
			<input type="hidden" id="had_edit" value="no"/>
			<!-- ��ʼ�� -->
			<input type="hidden" name="is_default" id="is_default" value=""/>
			<!-- ˢ��  -->
			<button type="button" id="btn_sx" onclick="defalutSqxm()" style="display:none">
				ˢ��
			</button>
			<!-- ������ end-->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showWaitingDiv('30000');refreshForm('pjpy_szgyyq_mypj.do');return false;" class="btn_fh">
								����
							</a>
						</li>
						<li>
							<a href="#" onclick="confirmInfo('��ȷ�ϱ�����¼�����Ϣ��',saveFssq);return false;" class="btn_ccg">
								����
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->			
				<!-- �������� -->
				<div style="display:none">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ����� end-->
			
			<!-- ѧ��������Ϣ -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>ѧ��������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">ѧ��</th>
						<td width="30%">
							<input type="hidden" name="xh" value="${stuInfo.xh }" />
							${stuInfo.xh }
						</td>
						<th width="20%">����</th>
						<td width="30%">
							${stuInfo.xm }
						</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>
							${stuInfo.xb }
						</td>
						<th>�༶</th>
						<td>
							${stuInfo.bjmc }
						</td>
					</tr>
				</tbody>
				<thead onclick="">
					<tr>
						<th colspan="4">
							<div id="div_sqxm_info">
								
							</div>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>
							<!-- ����� -->
							<div class="listbox">	
								<div class="titlelist" style="height: 352px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">
												<logic:equal name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child" style="background:#dce8f8">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="if(checkHadEdit()){setLiClick('${index}');createFssqDiv();$('is_default').value='no';};return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:equal>
												<logic:notEqual name="xmnr" property="xmdm" value="${czxm}">
													<li id="li_${index}" class="Child">
														<a href="#" name="left_a" id="left_a_${index}" 
															onclick="if(checkHadEdit()){setLiClick('${index}');createFssqDiv();$('is_default').value='no';};return false;">
															<span class="ico"></span>${xmnr.xmmc}
														</a>
														<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
													</li>
												</logic:notEqual>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- ����� end-->
						</td>
						<td colspan="3" valign="top">
							<div>
								<button type="button" onclick="addFssq()" id="btn_add">����</button>
								<button type="button" onclick="confirmInfo('��ȷ��Ҫɾ������ѡ�ļ�¼��',delFssq);">ɾ��</button>	
							</div>
							<br />
							<table class="tbstyle" align="center" width="100%" id="tTb">
								<tr>
									<td>
										<div id="div_fssq" style="height:330px;">

										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='4'>
<%--							<div class="btn">--%>
<%--								<!-- ���� -->--%>
<%--								<button type="button" onclick="saveFssq();">--%>
<%--									<bean:message key="lable.btn_bc_space" />--%>
<%--								</button>--%>
<%--								<!-- ���� -->--%>
<%--								<button type="button" onclick="goMypj();">--%>
<%--									<bean:message key="lable.btn_fh_space" />--%>
<%--								</button>--%>
<%--							</div>--%>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ѧ��������Ϣ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>