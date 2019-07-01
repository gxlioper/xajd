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
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			searchRs();
		}

		//ͬ��
		function tongBu(){
			var num = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked").length;
			var flag = true;
			
			if(num == "0"){
				alertError("�빴ѡϣ��ͬ��������");
				flag = false;
			}

			if(flag){
				confirmInfo("��Ҫͬ��������ѡ��¼����ȷ��",saveXjyd);
			}		
		}

		function saveXjyd(tag){
			if(tag == "ok"){
				var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
				var RowsStr="";
				if(objs.length>0){
					for (i=0; i<objs.length; i++){
				     RowsStr+=objs[i].value+",";
					}
				}

				jQuery.ajaxSetup({async:false});
				var url = "general_xsxx_xjyd_ajax.do?method=saveXjyd";
				//����
			 	var parameter = {
			 		"str_xh":RowsStr
				};
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						searchRs();
					}
				);
				
				jQuery.ajaxSetup({async:true});
				
			}
			}

		//ִ�в�ѯ����
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			
			var url = "general_xsxx_xjyd_ajax.do?method=searchXjydResult";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});

			setDivHeight();
		}

		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
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
		
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_xsxx" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="tongBu();return false;" class="btn_sx">
								ͬ��
							</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
					</ul>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div id="div_rs"
					style="width:100%;height:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxGeneralForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>