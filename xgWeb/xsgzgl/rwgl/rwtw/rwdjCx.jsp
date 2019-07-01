<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
			<script type='text/javascript' src="js/xgutil.js"></script>
			<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
			<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>
			<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>	
		<script language="javascript" defer="defer">
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
			var btndr=jQuery("#btn_dr");
			//����
			if(btndr!=null){
				btndr.click(function () {
					//����ͨ�õĵ���function�������ǵ��빦��ģ����롣
					toImportData("IMPORT_N460201_RWDJGL");
					return false;
				});
			}
		});

		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		function searchRs(){
			var url = "rwgl_rwtwgl_ajax.do?method=rwdjCx";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//�޸�
		function rwdjXg(type){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){
				var pkValue = jQuery("[name=div_pkValue]:checked").val()
				var parameter={}
				var url="rwgl_rwtwgl.do?method=rwdjXg&doType="+type+"&pkValue="+pkValue;
				showDialog("", 700, 440, url);
				//showTopWin(url,700,420);
			}else{
				alertInfo("�빴ѡһ����Ҫ�޸ĵļ�¼��",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		
		//ɾ����Ϣ
		function rwdjSc(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){
				confirmInfo("�Ƿ�ȷ��ɾ����ѡ�ļ�¼��",function(tag){
					if(tag=="ok"){
						var array = jQuery("[name=div_pkValue]:checked");
						var str = "";
						for (var i=0;i<array.length;i++) {
							var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
							str += pkValue+"!!@@!!";
						}
						var parameter={}
						var url="rwgl_rwtwgl_ajax.do?method=rwdjSc";
						parameter["pkValue"]=escape(str);
						jQuery.ajaxSetup({async:false});	
						jQuery.post(url,
							parameter,
							function(result){
								alertInfo(result);
								searchRs();
							}
						);
						jQuery.ajaxSetup({async:true});
					}
				});
			}else{
				alertInfo("�빴ѡ��Ҫɾ���ļ�¼��Ϣ��",function(tag){
					if(tag=="ok"){
						return false;
					}
				});
			}
		}
		
		function rwdjglExportConfig() {
			customExport("rwgl_rwtwgl_rwdjgl.do", rwdjglExportData);
			}
			
		
			
		// ��������
		function rwdjglExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "rwgl_rwtwgl_ajax.do?method=rwdjglExportData&dcclbh=" + "rwgl_rwtwgl_rwdjgl.do";//dcclbh,�������ܱ��
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


		<html:form action="/rwgl_rwtwgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" class="btn_zj" onclick="showDialog('', 700, 440, 'rwgl_rwtwgl.do?method=rwdjZj');return false;">����</a>
						</li>
						<li>
							<a href="#" class="btn_xg" onclick="rwdjXg('update');return false;">�޸�</a>
						</li>
						<li>
							<a href="#" class="btn_sc" onclick="rwdjSc();return false;">ɾ��</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" class="btn_ck" onclick="rwdjXg('view');return false;">�鿴</a>
						</li>
						<logic:equal value="yes" name="writeAble">
							<!-- ���ݴ�ѧ -->
							<logic:equal name="xxdm" value="10351">	
							<li><a href="#" class="btn_dr" id="btn_dr">����</a></li>	
							</logic:equal>
							<li><a href="#" onclick="rwdjglExportConfig();return false;" class="btn_dc">����</a></li>
						</logic:equal>
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
					page="/sjcz/turnpage.jsp?form=rwglRwtwForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>