<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�������-->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/customForm.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			searchRs();
		}

		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "customForm.do?method=searchCustomForm";
			var ie = "ie";
			
			var otherValue = [ie];
		
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}

		//����O��
		function addCustomForm(){
			var url = "customForm.do?method=customFormParameter";
			showTopWin(url,"600","480");	
			//tipsWindown("ϵͳ��ʾ","id:div_CustomForm","600","400","true","","true","id");
		}

		//�@ʾ�ֶ��O��
		function xszdSetup(form_id,souce_table,detail_view){
			var url = "customForm.do?method=customFormSetting";
				url+= "&form_id="+form_id;
				url+= "&souce_table="+souce_table;
				url+= "&detail_view="+detail_view;
			showTopWin(url,"800","600");
		}
		
		//��ԃ�ֶ��O��
		function jgcxSetup(form_id,souce_table,search_view){
			var url = "customForm.do?method=customFormSearch";
				url+= "&form_id="+form_id;
				url+= "&souce_table="+souce_table;
				url+= "&search_view="+search_view;
			showTopWin(url,"800","600");
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
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
				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����</br>
				<span id="div_help" style="display: none">
				1.������Ĭ��չʾ���Ǳ�����ѧ��ѧ�ڵ����ݡ�</br>
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/customForm" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="addCustomForm();return false;" class="btn_zj">
								����
							</a>
						</li>
						<li>
							<a href="#" onclick="delCustomForm();return false;" class="btn_sc">
								ɾ��
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>

			<!-- ������ʾ����ʼ -->
			<div class="main_box">

				<!-- From���� begin-->
				<div id="div_rs" style="">

				</div>
				<!-- From���� end-->

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=customFormForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>