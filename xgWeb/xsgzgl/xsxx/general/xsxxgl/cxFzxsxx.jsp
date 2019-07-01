<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�lt -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
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
			
			var url = "xsxx_tygl.do?method=cxFzxsxxjg";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}
		
		//�鿴
		function showzxsxxView(){
			var len=jQuery("[name=primarykey_checkVal]:checked").length;
	
			if(len==1){	
				var pkValue=jQuery("[name=primarykey_checkVal]:checked").eq(0).val();
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				showDialog("�鿴����У����Ϣ", 850, 500, url);
				//showTopWin(url,850,640);
				var height = 640;
				var left = (screen.width/2) - width/2;
				var top = (screen.height/2) - height/2;
				//var styleStr = 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,copyhistory=yes,width='+width+',height='+height+',left='+left+',top='+top+',screenX='+left+',screenY='+top;
				//window.open(url,"msgWindow", styleStr);
			}else{	
				alertError("��<font color='blue'>��ѡһ��</font>��ϣ���鿴�ļ�¼��");	
				return false;
			}
		}

		//�鿴
		function zxsxxView(xh){
				var pkValue=xh;
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				showDialog("�鿴����У����Ϣ", 850, 500, url);
		}

		
		jQuery(function(){
			onShow();
		})
		
		
		function fzxsxxExportConfig() {
			customExport("xsxx_tygl_cxfzxs.do", fzxsxxExportData,1000,500);
		}
			
		
			
		// ��������
		function fzxsxxExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "xsxx_tygl.do?method=fzxsxxExportData&dcclbh=" + "xsxx_tygl_cxfzxs.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body  >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
<!--			<p class="help">-->
<!--				<a href="#" onclick="return false;" -->
<!--					onmouseover ="showHelpDiv()"-->
<!--					onmouseout="showHelpDiv()"-->
<!--				>-->
<!--				ʹ�ð���</a>-->
<!--			</p>-->
		</div>
		<!-- ���� end-->
		
		<html:form action="/xsxx_tygl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showzxsxxView();return false;" id="btn_ck" class="btn_ck">
								�鿴
							</a>
						</li>
						<li><a href="#" class="btn_qx" onclick="fzxsxxExportConfig();return false;">����</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
					--%></ul>
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
				<div id="div_rs" style="height:420px;overflow-x:hidden;overflow-y:auto;">
				</div>
				
				<!--��ҳ��ʾ-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxtyglForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ����ʼ end-->
			
			<!-- ���̸��ٵ����� -->
			<div id="div_lcgz" style="display:none">
				<div class="open_win01">
					sss
				</div>
			</div>
			<!-- ���̸��ٵ����� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>