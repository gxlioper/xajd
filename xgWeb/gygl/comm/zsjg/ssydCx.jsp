<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglCwgl.js"></script>	
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglZsjg.do?method=ssydCx&doType=query');
		}
		
		function goSsydtj(){
			refreshForm("gygl_zsjg_ydtj.do");
		}
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ס�޽�� - �����춯��ѯ</a>
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
				������ģ��չʾѧ����λ�춯��Ϣ(ע:����ʾ��Уѧ����λ�춯��Ϣ)
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		<!-- ���� end-->
		<html:form action="/gyglZsjg">
			<input type="hidden" name="fpdx" id="fpdx" value="${fpdx}"/>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ȷ�� -->
						<li>
							<a href="#" id="btn_fh"
								onclick="goSsydtj();return false;"
								class="btn_fh" title="��������䡱��ť�����ֶ����䴲λ����">
								����
							</a>
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				
			</div>
			<!-- �๦�ܲ����� end -->
			
			<!-- ��ѯ���-->
			<div class="formbox" id="cxjg">		
				<h3 class="datetitle_01">
					<span>
						��ѯ���
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue"></font> 
						</logic:notEmpty>
						
<%--						<font color="blue"></font>--%>
					</span>
				</h3>
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<logic:iterate id="tit" name="topTr" offset="0">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:notEmpty name="rsArrList" >
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<!-- ��ʾ��Ϣ -->
								<logic:iterate id="v" name="s" offset="0">
									<td align="left" nowrap="nowrap">
										${v }
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:notEmpty>
					<!--���� end-->
					<!-- ������ -->
					<%@ include file="/comm/noRows.jsp"%>
					<!-- ������ end-->
				</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglZsjgForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
			
			
			<div id="fpxx" style="display: none" style="width:650px">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span>
											������Ϣ
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<p id="Bmfpxx" class="tab" style="width:100%;height:200px;overflow-x:hidden;overflow-y:auto;">
										</p>
									</td>
								</tr>
							</tbody>
							<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- ȷ�� -->
										<button onclick="zdfpcw();">
											<bean:message key="lable.btn_qd_space" />
										</button>
									</div>
								</td>
							</tr>
							</tfoot>
						</table>
				</div>
				
			<!-- ��ʾ��Ϣ -->
			<logic:present name="message">
				<!-- ���������ʾ�� -->
				<div id="tsxxDiv" style="display: none">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span>
											�Ѿ��ɹ�������${message }����λ����ȷ����һ������
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="hidden"  name="next" id="next_cz" value="jg"/>
										<input type="radio" name="next" id="next_jg" value="jg" onclick="$('next_cz').value = this.value" checked="checked"/>
										�鿴�Ѿ�����Զ�����Ĵ�λ
										</br>
										<input type="radio" name="next" id="next_sd" value="sd" onclick="$('next_cz').value = this.value"/>
										ǰ��"��λ�ֶ�����"ģ��
										</br>
										<input type="radio" name="next" id="next_gb" value="gb" onclick="$('next_cz').value = this.value"/>
										�رձ�ҳ�棬����Ϊ����������з���
									</td>
								</tr>
							</tbody>
							<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- ȷ�� -->
										<button onclick="nextCz()">
											<bean:message key="lable.btn_qd_space" />
										</button>
									</div>
								</td>
							</tr>
							</tfoot>
						</table>
					</div>
				</div>
				<script defer="defer">
					viewTempDiv("��ѡ����һ������","tsxxDiv",350,200);
					if($("message") && $("message").value != ""){
						$("message").value = "";
						$("doType").value = "";	
					}
				</script>
			</logic:present>
			<!-- ��ʾ��Ϣend -->
			<!-- ��ʾ��Ϣend -->
			
			
			
			<!-- ��ȴ� -->
			<%@ include file="/comm/loading.jsp"%>
			<!-- ��ȴ� end-->
		</html:form>
	</body>
</html>