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
		//ҳ���ʼ��
		function onShow(){
			
			searchRs()
			
		}

		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "gyglnew_jqlx_ajax.do?method=jqlxjgSearch";
			var ie = "ie";// ie�汾
			var v4Path = stylePath;//v4��ʽ·��
			
			// ��Ҫ�����̨����������
			var otherValue = [ie,v4Path];

			// loding
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			// ��ѯ����
			searchRsByAjax(url,otherValue);

			// ���� loding
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			
			jQuery.ajaxSetup({async:true});
		}

		//������Ŀ���(������ˡ��������)
		function showShxxDiv(){
			var pk = jQuery("input[name=div_pkValue]:checked").eq(0).val();
			var len = jQuery("input[name=div_pkValue]:checked").length;
			
			tipsWindown("ϵͳ��ʾ","id:div_002","500","300","true","","true","id");
			
		}


		//ǰ����Ŀ���
		function showSpgw(){
		
			var url="gyglnew_jqlx_ajax.do?method=showShgwDiv";
			
			var parameter={};
			
			jQuery("#div_spgw").load(url,parameter,function(){
				
				var len=jQuery("[name=spgw]").length;
		
				tipsWindown("ϵͳ��ʾ","id:div_spgw","300","170","true","","true","id");
					
			});
		}
		
		function checkSpgw(){
		
			$("spgw").value=jQuery("[name=spgw]:checked").val();
			
			closeWindown();
			
			searchRs();
			
		}
		
		function saveShzt(shzt){

			jQuery.ajaxSetup({async:false});
			
			var spgw = $("spgw").value;
		
			confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",function(tag){
				
				if(tag=="ok"){
				
					closeWindown();
					
					//����
					var sqid=new Array();
					
					jQuery("[name=div_pkValue]:checked").each(function(i){
						sqid[i]=jQuery(this).val();
					});
					
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
					
					parameter["str_spgw"]=spgw;
					
					parameter["array_sqid"]=sqid.join("!!array!!");
					
					parameter["str_shzt"]=shzt;
					
					parameter["str_shyj"]=escape(jQuery("#shyj").val());
					
					var url = "gyglnew_jqlx_ajax.do?method=plsh";
		          	
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							
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
		
		
		//���ù�������
		function czSearch(){

		
			//����ģ����ѯ
			if($("input_mhcx")){
				$("input_mhcx").value = "";
			}
			
			//���ø߼���ѯ
			$("yxtj_div").style.display = "none";
			$("yxtj_dl").innerHTML = "";
			if($("yxtj_gxh_div")){
				$("yxtj_gxh_div").style.display  = "none";
				$("yxtj_gxh_dl").innerHTML = "";
			}
			
			var a_num = document.getElementsByTagName('a').length;
	
			for(var i=0;i<a_num;i++){
				
				var a_className = document.getElementsByTagName('a')[i].className;
				
				if(a_className == "selectedValue"){
					document.getElementsByTagName('a')[i].className = "";
				}
			}
			
			var kssj_count = document.getElementsByName("searchModel.search_tj_kssj").length;
			var jssj_count = document.getElementsByName("searchModel.search_tj_jssj").length;
			
			//��ʼʱ��
			for(var i=0;i<kssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_kssj")[i];
				obj.value = "";
			}
			
			//����ʱ��
			for(var i=0;i<jssj_count;i++){
				var obj = document.getElementsByName("searchModel.search_tj_jssj")[i];
				obj.value = "";
			}
		}
		
		function checkItsDis(obj){
			
			if(obj.disabled){
				
				return false;
			}else{
				
				return true;
			}		
		}
		
		function showView(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len!=1){
				
				alertInfo("�빴ѡһ����Ҫ�鿴�ļ�¼��");
			}else {
				var sqid=jQuery("[name=div_pkValue]:checked").eq(0).val();
				var url = "gyglnew_jqlx.do?method=lxjgCk";
				url+="&sqid="+sqid;
				showTopWin(url,"800","600");
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


		<html:form action="/gyglnew_jqlx" method="post">

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
					page="/sjcz/turnpage.jsp?form=jqlxForm"></jsp:include>
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
