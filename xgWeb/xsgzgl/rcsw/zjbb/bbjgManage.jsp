<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
			<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
			<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>	
		
		<script language="javascript" defer="defer">
		//ҳ���ʼ��
		function onShow(){
			
			searchRs()
			
		}

		//��ѯ�����
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "rcsw_zjbb_ajax.do?method=bbjgSearch";
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
		
		function showView(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len!=1){
				
				alertInfo("�빴ѡһ����Ҫ�鿴�ļ�¼��");
			}else {
				var sqid=jQuery("[name=div_pkValue]:checked").eq(0).val();
				var url = "rcsw_zjbb.do?method=zjbbCk";
				url+="&sqid="+sqid;
				showDialog("֤����������ѯ", 800, 500, url);
				//showTopWin(url,"800","600");
			}
		}
		
		jQuery(function(){
			onShow();
		})
		
		function zjbbjgExportConfig() {
			customExport("rcsw_zjbb_bbjg.do", zjbbjgExportData);
		}
			
		
			
		// ��������
		function zjbbjgExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "rcsw_zjbb_ajax.do?method=zjbbjgExportData&dcclbh=" + "rcsw_zjbb_bbjg.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
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
						<li><a href="#" class="btn_dc" onclick="zjbbjgExportConfig();return false;">����</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>	
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
