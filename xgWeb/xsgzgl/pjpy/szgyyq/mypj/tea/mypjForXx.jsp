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
	
		//��ѯ�����
		function searchRs(){
			
			var yhlx = $("yhlx").value;
			var url = "szgyyq_mypj.do?method=getStuTjList";	
			var otherValue = [yhlx];

			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}	
		
		//ǰ���������
		function goFssh(xmdm,shtj){
			var url = "pjpy_szgyyq_fssh.do";
			
			showWaitingDiv("30000");
			
			if(xmdm != ""){
				url = "szgyyq_mypj_tea.do?method=fsshManage";
				url+= "&xmdm="+xmdm;
				url+= "&shtj="+shtj;
			}
			
			refreshForm(url);
		}
		
		//ǰ���ۺϲ���
		function goZhcp(){
			var url = "pjpy_szgyyq_zhcp.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}	

		//ǰ�������ѯ
		function goJgcx(){
			var url = "pjpy_szgyyq_teajgcx.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}	
		</script>
	</head>
	
	<body onload="" >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�ۺ��������ɿ� - �ҵĹ���</a>
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
<%--				${xn } ѧ�� ${xqmc } ѧ�� �ۺ��������ɿ���������</br>--%>
				1.�������в������ǻ���<font color="blue">${xn }</font>ѧ�꣬<font color="blue">${xqmc }</font>ѧ�� չ���ġ�</br>
				2.���ڱ����ڸ�����Ŀ�ķ�ֵ�����������£�<font color="blue">��ע��</font>�������շ���������߷�ʱ�������ܷ�ʱ����߷��㡣</br>
				3.���<font color="blue">�������</font>���������ѧ�������롣</br>
				4.���<font color="blue">�ۺϲ���</font>�����Բ鿴����ѧ�����ۺϲ����֡�</br>
				5.���<font color="blue">�����ѯ</font>�����Բ鿴ѧ����������������</br>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/szgyyq_mypj">
			
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- �û����� -->
			<input type="hidden" name="yhlx" id="yhlx" value="${yhlx }"/>
			<!-- ������ end-->
			
			<!-- �������� -->
			<div style="display:none">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �������� end-->
			
			<!-- ��ݷ�ʽ -->
			<div class="liucheng_xg_pj" style="">
				<div class="liucheng_bar">
					<h3>��<br/>��<br/>��<br/>��</h3>
					<div class="con">
					    <div class="liucheng_font floatleft">
					    	<a href="#" onclick="goFssh('','');return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function72.png" />
								<p>�������</p>
							</a>   	
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goZhcp();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function30.png" />
								<p>�ۺϲ���</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goJgcx();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
								<p>�����ѯ</p>
							</a>
						</div>
						
<%--						<div class="liucheng_font floatleft">--%>
<%--							<a href="#" onclick="goMyts();return false;">--%>
<%--					    		<img src="<%=stylePath%>/images/blue/48-1/Function28.png" />--%>
<%--								<p>�ҵ�Ͷ��</p>--%>
<%--							</a>--%>
<%--						</div>--%>
					</div>
				</div>
				
				<!-- ������ʾ����ʼ -->
				<div class="main_box">
					<div class="mid_box">
						<div class="title">
							<p>
								<!-- ��ѯ�õ�����������ʾ���� -->
							</p>
						</div>
					</div>
					<!-- From���� -->
					<table align="center" width="100%">
						<tr style="">
							<td width="100%" valign="top" style="position: relative;">
								<div id="div_rs">
									<table width="100%" class="dateline">
										<thead>
											<tr>
												<td>�۲���Ŀ</td>
												<td>���������</td>
												<td>��������</td>
												<td>Ͷ������</td>
											</tr>
										</thead>
										<tbody>
											<logic:present name="rs">
											<logic:iterate id="s" name="rs">
												<tr>
													<td>${s.xmmc }</td>
													<!-- ��������� -->											
													<td>
														<logic:equal name="s" property="xshrs" value="0">
															${s.xshrs }
														</logic:equal>
														<logic:notEqual name="s" property="xshrs" value="0">
															<a href="#" onclick="goFssh('${s.xmlx }','xsh');return false;">
																<font color="blue">${s.xshrs }</font>
															</a>
														</logic:notEqual>
													</td>	
													<!-- �������� -->												
													<td>
														<logic:equal name="s" property="ssrs" value="0">
															${s.ssrs }
														</logic:equal>
														<logic:notEqual name="s" property="ssrs" value="0">
															<a href="#" onclick="goFssh('${s.xmlx }','ss');return false;">
																<font color="blue">${s.ssrs }</font>
															</a>
														</logic:notEqual>
													</td>	
													<!-- Ͷ������ -->												
													<td>
														<logic:equal name="s" property="tsrs" value="0">
															${s.tsrs }
														</logic:equal>
														<logic:notEqual name="s" property="tsrs" value="0">
															<a href="#" onclick="goFssh('${s.xmlx }','ts');return false;">
																<font color="blue">${s.tsrs }</font>
															</a>
														</logic:notEqual>
													</td>												
												</tr>
											</logic:iterate>
											</logic:present>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</table>
					<!--��ҳ��ʾ-->
					<script type="text/javascript" defer="defer">
						setTimeout("$('choose').className='hide'",100);
					</script>
					<div style="display:none">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzgyyqMypjForm"></jsp:include>
					</div>
					<!--��ҳ��ʾ-->
				</div>
				<!-- ������ʾ����ʼ end-->
				
			</div>
			<!-- ��ݷ�ʽ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>