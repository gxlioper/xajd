<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
		});

		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		function searchRs(){
			var url = "qgzx_cxtj_ajax.do?method=jfhbCx";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//�鿴
		function showView(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();	
				var url="qgzx_cxtj.do?method=jfhbCk";		
				url+="&pkValue="+pkValue;
				//showTopWin(url,800,600);
				showDialog("", 760, 525, url);
			}else{
				alertInfo("�빴ѡһ����¼���в鿴��");
				return false;
			}
		}
		
		function jfhbcxExportConfig() {
			var jfhbfs=jQuery("#jfhbfs").val();
			if("yf"==jfhbfs){
				customExport("jfhbCx_qgzx_cxtj_ajax_yf.do", jfhbcxExportData);
			}else{
				customExport("jfhbCx_qgzx_cxtj_ajax.do", jfhbcxExportData);
			}
		}
			
		
			
		// ��������
		function jfhbcxExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "qgzx_cxtj_ajax.do?method=jfhbCxExportData&dcclbh=" + "jfhbCx_qgzx_cxtj_ajax.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body>

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/qgzx_cxtj" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="jfhbfs" id="jfhbfs" value="${jfhbfs}" />
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_ck" onclick="showView();return false;">�鿴��ϸ</a>
						</li>
							
						<li><a href="#" onclick="jfhbcxExportConfig();return false;" class="btn_dc">����</a></li>
						
						<%--<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">��������</a></li>
					--%></ul>
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
					<span> ��ѯ���&nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qgzxCxtjForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>