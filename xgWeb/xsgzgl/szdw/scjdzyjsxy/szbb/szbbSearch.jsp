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
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "szdw_szbb.do?method=searchSzbb";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);
			setDivHeight();
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		// ����Ա���
		function fdybbSetting(){
		
			var length=jQuery("[name=div_pkValue]:checked").length;
			var nj="";
			var xydm="";
			var zydm="";
			var bjdm="";
			if(length>1){
				
				alertInfo("����ѡ��һ���༶!");
				return false;
			}else if(length==1){
				
				var pkValue=jQuery("[name=div_pkValue]:checked").eq(0).val();
				var pkArr=new Array();
				pkArr=pkValue.split('!!luojw!!');
				nj=pkArr[0];
				xydm=pkArr[1];
				zydm=pkArr[2];
				bjdm=pkArr[3];
				
			}
			
			var url = "general_szdw.do?method=szbbSetting&fplx=fdy";
			url+="&nj="+nj;
			url+="&xydm="+xydm
			url+="&zydm="+zydm
			url+="&bjdm="+bjdm
			refreshForm(url);
			
		}
		
		// �����α��
		function bzrbbSetting(){
		
			var length=jQuery("[name=div_pkValue]:checked").length;
			var nj="";
			var xydm="";
			var zydm="";
			var bjdm="";
			if(length>1){
				
				alertInfo("����ѡ��һ���༶!");
				return false;
			}else if(length==1){
				
				var pkValue=jQuery("[name=div_pkValue]:checked").eq(0).val();
				var pkArr=new Array();
				pkArr=pkValue.split('!!luojw!!');
				nj=pkArr[0];
				xydm=pkArr[1];
				zydm=pkArr[2];
				bjdm=pkArr[3];
				
			}
			var url = "general_szdw.do?method=szbbSetting&fplx=bzr";
			
			url+="&nj="+nj;
			url+="&xydm="+xydm
			url+="&zydm="+zydm
			url+="&bjdm="+bjdm
			refreshForm(url);
			
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
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					ѡ����Ӧ�İ༶�����з��丨��Ա������εĲ�����
				</span>
			</p>			
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/general_szdw" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" class="btn_sr" onclick="fdybbSetting();return false;">
									���Ӹ���Ա
								</a>
							</li>
							<li>
								<a href="#" class="btn_gx" onclick="bzrbbSetting();return false;">
									���Ӱ�����
								</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
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
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>
				
				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalSzdwGeneralForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>