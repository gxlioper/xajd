<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />    
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//��ѯ�����
		function searchRs(){
			var url = "rcsw_qjgl.do?method=getMygzList";	
			var otherValue = ["1"];
			
			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}	
		
		//ǰ��������
		function goMysh(czxm){
			var url	="rcsw_qjgl.do?method=myshManage";
				url+="&czxm="+czxm;
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//ǰ����ٲ�ѯ
		function goJgcx(){
			var url = "rcsw_qjgl_jgcx.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//ǰ���������
		function goCxqj(){
			var url = "rcsw_qjgl_cxqj.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//ǰ����ٲ�ѯ
		function goBjfx(){
			var url = "rcsw_qjgl_bjfxdj.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//��ʾ�ҵ������ϸ
		function showMyqjDetail(type){
		
			var id="";
			var flag = false;
			
			if(type == "edit"){
				var num = jQuery("input[name=checkVal]:checked").length;
				
				if(num == 0){
					alertError("�빴ѡ����Ҫά����¼");
					flag = false;
				}else if(num > 1){
					alertError("���ܹ�ѡ������¼���빴ѡһλ����Ҫά���ļ�¼");
					flag = false;
				}else{
					id=jQuery("input[name=checkVal]:checked")[0].value;
					flag = true;
				}				
			}else{
				flag = true;
			}

			if(flag){
			
				var url = "/xgxt/rcsw_qjgl.do?method=myqjDetail";
					url+= "&id="+id;
				showDialog("", 800, 520, url);
				//showTopWin(url,'800','620');	
			}	
		}
		</script>
	</head>
	
	<body onload="searchRs()" >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�ճ����� - ��ٹ��� - �ҵĹ���</a>
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
				1.�������չʾ�����ݣ��������ڸ�λ��ѧ����Ҫ��˵�������</br>
				2.���ݷ�Χ�������ڸ�λ����ݾ�����</br>
				3.���<font color="blue">������,</font>�����Կ�ʼ������˹�����</br>
				4.�����Ŀ���<font color="blue">���</font>���ӣ�����ֱ����Ը���Ŀ������ˡ�
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/rcsw_qjgl">
			
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- �û����� -->
			<input type="hidden" name="yhlx" id="yhlx" value="${yhlx }"/>
			<!-- ������ end-->
			
			<!-- �������� -->
			<div style="display:none">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<button type="button" class="btn_cx" id="search_go" style="display: none" onclick="searchRs();return false;"></button>
			<!-- �������� end-->
			
			<!-- ��ݷ�ʽ -->
			<div class="liucheng_xg_pj" style="">
			
				<div class="liucheng_bar">
					<h3>��<br/>��<br/>��<br/>��</h3>
					<div class="con">
					
						
						 <div class="liucheng_font floatleft">
					    	<a href="#" onclick="showMyqjDetail('add');return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function61.png" />
								<p>�������</p>
							</a>   	
						</div>
						<!-- 
					    <div class="liucheng_font floatleft">
					    	<a href="#" onclick="goMysh('');return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
								<p>������</p>
							</a>   	
						</div>
						 -->
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goJgcx();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
								<p>������</p>
							</a>
						</div>
						<!-- 
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goCxqj();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function35.png" />
								<p>�������</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goBjfx();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function34.png" />
								<p>���ٷ�У</p>
							</a>
						</div>
						 -->
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswQjglForm"></jsp:include>
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