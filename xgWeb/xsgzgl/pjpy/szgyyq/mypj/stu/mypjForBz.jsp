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
		
		//ǰ����������
		function goFssq(){
			var url = "pjpy_szgyyq_fssq.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//ǰ�������ѯ
		function goJgcx(){
			var url = "pjpy_szgyyq_jgcx.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//ǰ���ҵ�����
		function goMyss(){
			var url = "pjpy_szgyyq_myss.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//ǰ��5S��¼��
		function goFiveS(){
			var url = "pjpy_szgyyq_fives.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//ǰ���������
		function goFssh(){
			var url = "pjpy_szgyyq_fssh.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		</script>
	</head>
	
	<body onload="searchRs()" >
	
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
				1.�������в������ǻ���<font color="blue">${xn }</font>ѧ�꣬<font color="blue">${xqmc }</font>ѧ�� չ���ġ�</br>
				2.���ڱ����ڸ�����Ŀ�ķ�ֵ�����������£�<font color="blue">��ע��</font>�������շ���������߷�ʱ�������ܷ�ʱ����߷��㡣</br>
				3.<font color="blue">����鿴��ϸ</font>�����Բ鿴����Ŀ�ľ������������Է��������飬�����ڴ�ִ��<font color="blue">����</font>������</br>
				4.���<font color="blue">��������</font>�����������µķ�����<font color="blue">5S��</font>�������������룬��ע�⡣</br>
				5.���<font color="blue">5S��¼��</font>������ά��ѧ����5S�֡�</br>
				6.���<font color="blue">�������</font>���������ѧ�������롣</br>
				7.���<font color="blue">�����ѯ</font>�����Բ鿴�����ڰ༶��������Ŀ�������</br>
				8.<font color="blue">�ҵ�����</font>�����������Ѿ����߹��ļ�¼��
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
					    	<a href="#" onclick="goFssq();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
								<p>��������</p>
							</a>   	
						</div>
												
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goFiveS();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function35.png" />
								<p>5S��¼��</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goFssh();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function72.png" />
								<p>�������</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goJgcx();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
								<p>�����ѯ</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goMyss();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function21.png" />
								<p>�ҵ�����</p>
							</a>
						</div>
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