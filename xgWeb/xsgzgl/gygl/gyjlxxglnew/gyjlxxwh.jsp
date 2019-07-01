 <%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�cq -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>	
		--%><%@ include file="/syscommon/head.ini"%>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
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


		//��ѯ
		function searchRs(){
			var url = "gyjl_gyjlglnew_ajax.do?method=gyjlxxwh";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//����
		function addGyjl(){
			var url="gyjl_gyjlglnew.do?method=gyjlZj";
			//showTopWin(url,'800','600');
			showDialog("���ӹ�Ԣ��¼��Ϣά��", 800, 480, url);
		}

		//�޸�
		function showModi(){
			
			var len=jQuery("[name=div_pkValue]:checked").length;
			
			if(len==1){	
				
				var pk_value=jQuery("[name=div_pkValue]:checked").eq(0).val();

				var xh=pk_value.split("!!shen!!")[0];

				var jlsj=pk_value.split("!!shen!!")[2];
				
				var gyjllbdm=pk_value.split("!!shen!!")[1];
				
				var url="gyglnew_gyjlgl_gyjlxg.do";  
				
				url+="?xh="+xh+"&jlsj="+jlsj+"&gyjllbdm="+gyjllbdm;
				 
				//showTopWin(url,800,600);
				showDialog("�޸Ĺ�Ԣ��¼��Ϣά��", 800,480, url);
			}else{
				
				alertInfo("�빴ѡһ����Ҫ�޸ĵļ�¼��");
				
				return false;
			}
		}

		//ɾ��
		function del(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			var blog=true;
			if(len>=1){	
				if(blog){
					confirmInfo("�ò�������ɾ��������ѡ�����ݣ��Ƿ�ȷ������������",function(tag){
						if(tag=="ok"){
							var array = jQuery("[name=div_pkValue]:checked");
							var str = "";
							for (var i=0;i<array.length;i++) {
								var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
								str += pkValue+"!!@@";
							}
							var parameter={}
							var url="gyjl_gyjlglnew.do?method=gyjlSc";	
							parameter["str"]=str;							
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

		//˫���鿴
		function ShowView(){
			
			var pk_value=curr_row.getElementsByTagName('input')[0].value;
			
			var xh=pk_value.split("!!shen!!")[0];
			
			var jlsj=pk_value.split("!!shen!!")[2];
				
			var gyjllbdm=pk_value.split("!!shen!!")[1];
			
			var url="gyglnew_gyjlgl.do?method=gyjlcxView";
			
			url+="&xh="+xh+"&wjsj="+jlsj+"&gyjllbdm="+gyjllbdm;

			//showTopWin(url,800,650);
			if(jQuery("#xxdm").val() == '11799'){
				showDialog('�鿴ѧ����Ԣ������Ϣ', 850, 500, url);
			}else{
				showDialog('�鿴ѧ����Ԣ������Ϣ', 850, 500, url);
			}
		}
		//����ѧ�Ų鿴
		function zxsxxView(xh){
			
				var pkValue=xh;
				var url="xsxx_tygl.do?method=ckZxsxx";
				url+="&xh="+pkValue;
				var width=850;
				//showTopWin(url,850,640);
				showDialog('�鿴ѧ����ϸ��Ϣ', 850, 500, url);
		}
		
		function gyjlxxwhExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("gyjl_gyjlglnew_ajax.do", gyjlxxwhExportData);
			}
			
		
			
		// ��������
		function gyjlxxwhExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyjl_gyjlglnew_ajax.do?method=gyjlxxwhexportData&dcclbh=" + "gyjl_gyjlglnew_ajax.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		/**
		 * ����
		 */
		function importConfig() {
			toImportData("IMPORT_N380803");
			return false;
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


		<html:form action="/gyjl_gyjlglnew" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<logic:equal name="writeAble" value="yes">
					<!-- ��ť -->
					<div class="buttonbox">
						<ul>
						
							<li>
								<a href="#" onclick="addGyjl();return false;" class="btn_zj">
									����
								</a>
							</li>
							<li>
								<a href="#" onclick="showModi();return false;" class="btn_xg">
									�޸�
								</a>
							</li>
							<logic:notEqual name="xxdm" value="11647">
								<li>
									<a href="#" onclick="del();return false;" class="btn_sc">
										ɾ��
									</a>
								</li>
							</logic:notEqual>
							<logic:equal name="xxdm" value="11647">
								<logic:equal name="userType" value="admin">
									<li>
										<a href="#" onclick="del();return false;" class="btn_sc">
											ɾ��
										</a>
									</li>
								</logic:equal>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="gyjlxxwhExportConfig();return false;">����</a></li>
						</ul>
					</div>
					<!-- ��ť end-->
				</logic:equal>
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
					<span> ��ѯ���&nbsp;&nbsp;<font color="blue">˫����¼�ɲ鿴��ϸ��Ϣ;</font></span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gyjlxxglNewForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>