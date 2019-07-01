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
		//ҳ���ʼ��
		function onShow(){
			searchRs();
		}

		//��ѯ�����
		function searchRs(){

			jQuery.ajaxSetup({async:false});
			
			var yhlx = $("yhlx").value;
			var url = "general_pjpy_wdpj_ajax.do?method=searchPjpyWdpj";	
			var otherValue = [yhlx];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}	
		
		//��ʾѧ������
		function showWdpjView(xmdm,xh){
			var url = "general_pjpy.do?method=wdpjXmshDetail&xmdm="+xmdm;
			url+="&opera=view";
			url+="&xh="+xh
			showTopWin(url,"800","600");
		}
		
		function checkShjl(){
		
			var flag=false;
			jQuery('tr', jQuery("#wdpjTable")).each(function(){
				flag=true;
			});
			
			if(!flag){
				alertInfo("��û����Ҫ��˵ļ�¼����ȷ�ϣ�");
				return false;
			}else {
				return true;
			}
			
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
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.������Ĭ��չʾ���Ǳ�����ѧ��ѧ�ڵ����ݡ�</br>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="bjdm" id="bjdm" value=""/>
			<input type="hidden" name="xmdm" id="xmdm" value="" />
			<button type="button" id="forward" onclick="goWdpjLssb()" style="display: none">��ת</button>
			<!-- �๦�ܲ����� -->
			<div class="toolbox">				
				<div style="display:none">
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				</div>
			</div>
			
			<!-- ��ݷ�ʽ -->
			<div class="liucheng_xg_pj" style="">
			
				<!-- ѧ���汾 -->
				<logic:equal name="userType" value="stu">
					<div class="liucheng_bar">
						<h3>��<br/>��<br/>��<br/>��</h3>
						<div class="con">
						    <div class="liucheng_font floatleft">
						    	<a href="#" onclick="goWdpjXssq();return false;">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
									<p>��Ŀ����</p>
								</a>   	
							</div>
							
							<div class="liucheng_font floatleft">
								<a href="#" onclick="showTopWin('/xgxt/general_pjpy.do?method=wdpjXssqJgcx',600,550);return false;">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
									<p>�ҵ�����</p>
								</a>
							</div>
						</div>
					</div>
				</logic:equal>
				<!-- ѧ���汾  end-->
				<logic:notEqual name="userType" value="stu">
				<div class="liucheng_bar">
					<h3>��<br/>��<br/>��<br/>��</h3>
					<div class="con">
					   
						<div class="liucheng_font floatleft">
							<a href="#" onclick="showTopWin('/xgxt/general_pjpy.do?method=lssbFwChoose',600,550);return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function46.png" />
								<p>��ʦ�ϱ�</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="if(checkShjl()){goWdpjXmsh()};return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function72.png" />
								<p>��Ŀ���</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goWdpjJgcx();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
								<p>�����ѯ</p>
							</a>
						</div>
					</div>
				</div>
				</logic:notEqual>
				
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
					<table align="center" width="100%" id="wdpjTable">
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
					<!--��ҳ��ʾ-->
				</div>
				<!-- ������ʾ����ʼ end-->
				
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>