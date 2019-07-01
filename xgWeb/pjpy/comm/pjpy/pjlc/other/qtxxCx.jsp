<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			
			searchRs()
			
		}
		
		//��ѯ�����
		function searchRs(){
		
			var url = "pjpyQtxx_ajax.do?method=qtxxCx";

			var ie = "10.0";

			var otherValue = [ie];

			searchRsByAjax(url,otherValue);
			
		}
		
		function save(tag){
			
			
			if(tag=="ok" ){
		
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};;
			
				jQuery("[name=div_pkValue]:checked").each(function(i){
							
					pkValue[i] =escape(jQuery(this).val());
							
				});
				
				var xh=new Array();
				jQuery("[name=div_pkValue]:checked").each(function(i){
							
					xh[i] =escape(jQuery(this).val());
							
				});
				
				var url = "xljk_hzny_ajax.do?method=zxsglModi";
	          	 
			 	parameter["str_byjd"]=escape(byjdV);
				parameter["array_pkValue"]=pkValue.join("!!array!!");
				parameter["array_xh"]=xh.join("!!array!!");
	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result,function(tag){
						
							if(tag=="ok"){
						
								closeWindown();	
								searchRs();
								
							}
						});
						
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		
		}
		
		function showModi(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
				var url="pjpyQtxx.do?method=qtxxXg";
				
				url+="&pkValue="+pkValue;
				
				showTopWin(url,600,600);
			}else{
				
				alertInfo("�빴ѡһ����Ҫ�鿴�ļ�¼��");
				
				return false;
			}
		}
		
		function showView(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
				var url="pjpyQtxx.do?method=qtxxCk";
				
				url+="&pkValue="+pkValue;
				
				showTopWin(url,600,600);
			}else{
				
				alertInfo("�빴ѡһ����Ҫ�鿴�ļ�¼��");
				
				return false;
			}
		}
		
		function deleteQtxx(){
			
			var n=jQuery("[name=div_pkValue]:checked").length;
			
			var blog=true;
			if(n>0){
				

				if(blog){
					confirmInfo("�ò�������ɾ��������ѡ�ļ�¼���Ƿ�ȷ������������",function(tag){
						
						if(tag=="ok"){
							
							var pkValue=new Array();
							
							var xh=new Array();
							
							jQuery("[name=div_pkValue]:checked").each(function(i){
								
								pkValue[i]=jQuery(this).val();
							
							});
							
							var parameter={}
							
							
							parameter["pkValue"]=escape(pkValue.join("!!array!!"));
							
							var url= "pjpyQtxx_ajax.do?method=delete";
							
							jQuery.ajaxSetup({async:false});	
							
							jQuery.post(url,
								parameter,
								function(result){
								
									alertInfo(result,function(tag){
										
										if(tag=="ok"){
											searchRs();
										}
									
									});
									
								}
							);
							
							jQuery.ajaxSetup({async:true});
						}
					});
				}
			}else{
				
				alertInfo("�빴ѡ��Ҫɾ�������ݣ�",function(tag){
					
					if(tag=="ok"){
						return false;
					}
				
				});
			}
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body >

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/gyglnew_jcrcgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />

			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showView();return false;" class="btn_ck">�鿴</a>
						</li>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="showTopWin('pjpyQtxx.do?method=qtxxZj',600,600);return false;" class="btn_zj">����</a>
							</li>
							<li>
								<a href="#" onclick="showModi();return false;" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#" onclick="deleteQtxx();return false;" class="btn_sc">ɾ��</a>
							</li>
							<logic:equal name="userType" value="admin">
							<li>
								<a href="#" onclick="impAndChkData();return false;" class="btn_dr">��������</a>
							</li>
							</logic:equal>
							<logic:equal name="userType" value="xx">
							<li>
								<a href="#" onclick="impAndChkData();return false;" class="btn_dr">��������</a>
							</li>
							</logic:equal>
						</logic:equal>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
						
					</ul>
				</div>
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �๦�ܲ����� end-->

			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:notEmpty name="rsList">
							<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gyglJcrcglForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--						$('choose').className="hide";--%>
<%--				</script>--%>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
