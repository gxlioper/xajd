<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			searchRs();
		}

		//����
		function addCfsb(){
			var url="general_wjcf_cfsb_ajax.do?method=zjWjcf";
			//showTopWin(url,'780','660');
			showDialog("", 800, 500, url);
		}

		//�޸�
		function modCfsb(){
			var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			var cfId="";
			if(objs.length!=1){
				alertError("��ѡ��һ����¼��");
				return false;
			}else{
				cfId+=objs[0].value;
			}

			var pk = new Array();   	
			var i=0;			  
			jQuery("input[name=primarykey_checkVal]:checked").each(function(){pk[i] = jQuery(this).val();i++;});
			
			jQuery.ajaxSetup({async:false});
			var url = "general_wjcf_cfsb_ajax.do?method=checkCfsb";
			//����
		 	var parameter = {
		 			"primarykey_checkVal":pk
			};
			
			jQuery.post(url,
				parameter,
				function(result){
				if(""!=result){
					alertError("ѧ��Ϊ��"+result.substr(0,result.length-1)+"��Υ�ʹ����Ѿ���ˣ������޸ģ�");
					return false;
					}else{
						var url="general_wjcf_cfsb_ajax.do?method=xgCfsb&cfId="+cfId;
						//showTopWin(url,'780','660');
						showDialog("", 800, 500, url);
					}
				}
			);
			jQuery.ajaxSetup({async:true});

		}

		//�鿴
		function viewCfsb(){
			var objs = jQuery("input[type=checkbox][name=primarykey_checkVal]:checked");
			var cfId="";
			if(objs.length!=1){
				alertError("��ѡ��һ����¼��");
				return false;
			}else{
				cfId+=objs[0].value;
			}
			var url="general_wjcf_cfsb_ajax.do?method=ckCfsb&cfId="+cfId;
			//showTopWin(url,'780','660');
			showDialog("", 800, 500, url);
		}

		//ɾ��
		function csCfsb(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var flag = false;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
				}
			}
			var pk = new Array();   	
			var i=0;			  
			jQuery("input[name=primarykey_checkVal]:checked").each(function(){pk[i] = jQuery(this).val();i++;});
			
			if(flag){
				confirmInfo("�˲�������ɾ��������ѡ�ļ�¼���Ƿ�ȷ����",function(tag){
					if(tag=="ok"){

						jQuery.ajaxSetup({async:false});
						var url = "general_wjcf_cfsb_ajax.do?method=checkCfsb";
						//����
					 	var parameter = {
					 			"primarykey_checkVal":pk
						};
						
						jQuery.post(url,
							parameter,
							function(result){
							if(""!=result){
								alertError("ѧ��Ϊ��"+result.substr(0,result.length-1)+"��Υ�ʹ����Ѿ���˻���������ˣ����ֳ�����������ɾ����");
								return false;
								}else{

									jQuery.ajaxSetup({async:false});
									var url = "general_wjcf_cfsb_ajax.do?method=scCfsb";
									//����
								 	var parameter = {
								 			"primarykey_checkVal":pk
									};
									
								 	$("divWaiting").style.display="";
									$("divDisable").style.display="";
									
									jQuery.post(url,
										parameter,
										function(result){
											alertInfo(result);
											searchRs();
										}
									);
									jQuery.ajaxSetup({async:true});
									
								}
							}
						);
						jQuery.ajaxSetup({async:true});
						
					}
				});
		}else{
			alertInfo("��ѡ��Ҫɾ�������ݣ�");
			return false;
			}
		}

		//ִ�в�ѯ����
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			var v4Path = stylePath;//v4��ʽ·��
			var url = "general_wjcf_cfsb_ajax.do?method=searchWjcfResult";
			var ie = "ie";
			
			var otherValue = [ie,v4Path];
			searchRsByAjax(url,otherValue);
			setTimeout("setDivHeight()","2000");
			jQuery.ajaxSetup({async:true});
		}
		
		jQuery(function(){
			onShow();
		})
		
		function cfsbExportConfig() {
		//DCCLBH�������ܱ��,ִ�е������� 
		customExport("general_wjcf_cfsb_ajax.do", cfsbExportData);
		}
		
	
		
		// ��������
		function cfsbExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "general_wjcf_cfsb_ajax.do?method=cfsbExportData&dcclbh=" + "general_wjcf_cfsb_ajax.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
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
		
		
		<html:form action="/general_xsxx" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="addCfsb();return false;" class="btn_zj">
								����
							</a>
						</li>
						<li>
							<a href="#" onclick="modCfsb();return false;" class="btn_xg">
								�޸�
							</a>
						</li>
						<li>
							<a href="#" onclick="csCfsb();return false;" class="btn_sc">
								ɾ��
							</a>
						</li>
						
						</logic:equal>
						<li>
							<a href="#" onclick="viewCfsb();return false;" class="btn_ck">
								�鿴
							</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="cfsbExportConfig();return false;">����</a></li>
						
					</ul>
				</div>
				<!-- ��ť end-->
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
			<h3 class="datetitle_01">
					<span> ��ѯ���</span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=wjcfGeneralForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
		<logic:present name="message">
			<script>
				alertInfo('${message}');
			</script>
		</logic:present>
	</body>
</html>