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
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//��ѯ�����
		function searchRs(){
	
			var url = "pjpyMypj.do?method=getMypjTjList";	
			var otherValue = ["stu"];

			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}		
		
		//ǰ����Ŀ����
		function goXmsq(){
			var url = "pjpy_pjlc_xssq.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//ǰ����Ŀ���
		function goXmjg(){
			var url = "pjpy_pjlc_jgcx.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//��ʾ��Ŀ��ϸ��Ϣ
		function showXmsqDetail(pk,xmdm,shjb){

			var url = "/xgxt/pjpyXmsh.do?method=xmshDetail";
				url+="&pk="+pk;
				url+="&shjb="+shjb;
				url+="&shxm="+xmdm;
				url+="&doType=view";
	
			showTopWin(url,"800","600");
		}
		</script>
	</head>
	<body onload="searchRs()" >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �ҵ�����</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
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
				1.���������ʾ������<font color="blue">�Ѿ�����</font>����Ŀ��</br>
				2.<font color="blue">�鿴��ϸ</font>���Բ鿴��ظ�λ�������Ϣ��</br>
				3.�������µ���Ŀ����<font color="blue">��Ŀ����</font>��</br>
				4.�������鿴��������������Ϣ������<font color="blue">�����ѯ</font>��
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/pjpyMypj">
		
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="bjdm" id="bjdm" value=""/>
			<input type="hidden" name="xmdm" id="xmdm" value="" />
			<button type="button" id="forward" onclick="goXmsb()" style="display: none">��ת</button>
			<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;" style="display: none">��ѯ</button>
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
					    	<a href="#" onclick="goXmsq();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
								<p>��Ŀ����</p>
							</a>   	
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goXmjg();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
								<p>�����ѯ</p>
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyMypjForm"></jsp:include>
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