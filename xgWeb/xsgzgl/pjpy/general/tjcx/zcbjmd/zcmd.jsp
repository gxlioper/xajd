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
			var message = "������ͳ�Ʊ���ѡ��";
				message+= "<font color='red'>һ��</font>ѧ�꼰";
				message+= "<font color='red'>һ��</font>�༶";
			
			alertInfo(message);
		}
		
		//��ѯ�����
		function searchRs(){
		
			jQuery.ajaxSetup({async:false});
			
			var url = "general_tjcx_zcbjmd_ajax.do?method=createZcbjmdNoDjksHTML";
			var ie = "ie";
			
			var otherValue = [ie];
			
			if(checkSearch()){
				searchRsByAjax(url,otherValue);
			}
			
			jQuery.ajaxSetup({async:true});
		}
		
		//����ɷ��ѯ
		function checkSearch(){
		
			var flag = true;

			var xn_num =  jQuery("a[name=a_name_xn]").length;
			var bj_num =  jQuery("a[name=a_name_bj]").length;
			
			if(xn_num != "1"){
				alertError("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
				flag = false;
			}else if( bj_num != "1"){
				alertError("�༶��������Ϊ�գ���ֻ��ѡ��һ����");
				flag = false;
			}

			return flag;
		}
		
		//����ΪExcel
		function expToExcel(){
		
			if(checkSearch()){
				var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
				var bjdm =jQuery("a[name=a_name_bj]").eq(0).attr("id").replace("a_id_","");
				
				var url = "general_tjcx_zcbjmd_ajax.do?method=expZcbjmd";
					url+= "&str_xn="+xn;
					url+= "&str_bjdm="+bjdm;
					url+= "&str_lx=nodjks";
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
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
		</div>
		<!-- ���� end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="expToExcel();return false;" class="btn_dc">
								����
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->

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
					<div id="div_rs">
					
					</div>			
				</div>
				<!-- ������ʾ����ʼ end-->
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>