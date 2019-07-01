<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		var DCCLBH = "general_xsxx_qzxgmd.do";//dcclbh,�������ܱ��
		//�Զ��嵼�� ����
		function exportConfig() {
			customExport(DCCLBH, qzmdExportData);
		}

		// ��������
		function qzmdExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "general_xsxx_qzxgmd.do?method=qzmdExportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		function qzmddc() {
			setSearchTj();//���ø߼���ѯ����
			var url = "general_xsxx_qzxgmd.do?method=qzmdExportData&dcclbh=" + "general_xsxx_qzxgmd.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		//ǿ���޸�
		function qzxg(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var pk = new Array();   	
			var i=0;			  
			jQuery("input[name=primarykey_checkVal]:checked").each(function(){pk[i] = jQuery(this).val();i++;});
						jQuery.ajaxSetup({async:false});
						var url = "general_xsxx_qzxgmd.do?method=checkQzxg";
						//����
						//ģ����ѯ
						var input_mhcx = "";
						if($("input_mhcx")){
							input_mhcx = $("input_mhcx").value;
						}
						
						var mhcx_lx = "";
						if($("mhcx_lx")){
							mhcx_lx = $("mhcx_lx").value;
						}

						//�����ѯ
						var searchLx = new Array();
						var searchTj = new Array();
						var searchTjz = new Array();
						
						var n=0;
						var m=3;
						
						searchLx[0]="xy";
						searchLx[1]="zy";
						searchLx[2]="bj";
						
						for(var i=0;i<jytj.length;i++){
							searchLx[m]=jytj[i];
							m++;
						}

						var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
							
						for(var j=0;j<tj_num;j++){
							var obj = $("searchTjDiv").getElementsByTagName('input')[j];
							searchTj[n]=obj.name;
							searchTjz[n]=escape(obj.value);
							n++;
						}
						setSearchTj();
					 	var parameter = {
					 			"primarykey_checkVal":pk,
					 			"input_mhcx":escape(input_mhcx),
					 			"mhcx_lx":mhcx_lx,
					 			"searchTj":searchTj.join("!!@@!!"),
					 			"searchTjz":searchTjz.join("!!@@!!"),
					 			"searchLx":searchLx.join("!!@@!!")
						};
						jQuery.post(url,
							parameter,
							function(result){
							if(""!=result){
								confirmInfo(result,function(tag){
										if(tag=="ok"){
											jQuery.ajaxSetup({async:false});
											var url = "general_xsxx_qzxgmd.do?method=szQzxg";
											//����
										 	var parameter = {
										 			"primarykey_checkVal":pk,
										 			"input_mhcx":escape(input_mhcx),
										 			"mhcx_lx":mhcx_lx,
										 			"searchTj":searchTj.join("!!@@!!"),
										 			"searchTjz":searchTjz.join("!!@@!!"),
										 			"searchLx":searchLx.join("!!@@!!")
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
									});
									
								}
							}
						);
						jQuery.ajaxSetup({async:true});
				
		}

		function qxqzxg(){
			var checkBoxArr = document.getElementsByName("primarykey_checkVal");
			var pk = new Array();   	
			var i=0;			  
			jQuery("input[name=primarykey_checkVal]:checked").each(function(){pk[i] = jQuery(this).val();i++;});
						jQuery.ajaxSetup({async:false});
						var url = "general_xsxx_qzxgmd.do?method=checkQzxg";
						//����
						//ģ����ѯ
						var input_mhcx = "";
						if($("input_mhcx")){
							input_mhcx = $("input_mhcx").value;
						}
						
						var mhcx_lx = "";
						if($("mhcx_lx")){
							mhcx_lx = $("mhcx_lx").value;
						}

						//�����ѯ
						var searchLx = new Array();
						var searchTj = new Array();
						var searchTjz = new Array();
						
						var n=0;
						var m=3;
						
						searchLx[0]="xy";
						searchLx[1]="zy";
						searchLx[2]="bj";
						
						for(var i=0;i<jytj.length;i++){
							searchLx[m]=jytj[i];
							m++;
						}

						var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
							
						for(var j=0;j<tj_num;j++){
							var obj = $("searchTjDiv").getElementsByTagName('input')[j];
							searchTj[n]=obj.name;
							searchTjz[n]=escape(obj.value);
							n++;
						}
						setSearchTj();
					 	var parameter = {
					 			"primarykey_checkVal":pk,
					 			"input_mhcx":escape(input_mhcx),
					 			"mhcx_lx":mhcx_lx,
					 			"searchTj":searchTj.join("!!@@!!"),
					 			"searchTjz":searchTjz.join("!!@@!!"),
					 			"searchLx":searchLx.join("!!@@!!")
						};
						jQuery.post(url,
							parameter,
							function(result){
							if(""!=result){
								confirmInfo(result,function(tag){
										if(tag=="ok"){
											jQuery.ajaxSetup({async:false});
											var url = "general_xsxx_qzxgmd.do?method=qxQzxg";
											//����
										 	var parameter = {
										 			"primarykey_checkVal":pk,
										 			"input_mhcx":escape(input_mhcx),
										 			"mhcx_lx":mhcx_lx,
										 			"searchTj":searchTj.join("!!@@!!"),
										 			"searchTjz":searchTjz.join("!!@@!!"),
										 			"searchLx":searchLx.join("!!@@!!")
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
									});
									
								}
							}
						);
						jQuery.ajaxSetup({async:true});
			}
		
		//ִ�в�ѯ����
		function searchRs(){
			jQuery.ajaxSetup({async:false});
			var v4Path = stylePath;//v4��ʽ·��
			var url = "general_xsxx_qzxgmd.do?method=searchQzxgmd";
			var ie = "ie";
			
			var otherValue = [ie,v4Path];
			searchRsByAjax(url,otherValue);
			setTimeout("setDivHeight()","2000");
			jQuery.ajaxSetup({async:true});
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
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					��������Ҫ���ѧ����¼ʱ�Ƿ���Ҫǿ���޸�����ѧ����Ϣ�������� ��<br/>
					��<font color="red">����ѡ</font>�κ�ѧ��ʱ���Է���ѡ��<font color="red">��ѯ����</font>��ѧ������ǿ���޸�״̬<br/>
					��<font color="red">��ѡ</font>ѧ��ʱ����<font color="red">ѡ��</font>��ѧ������ǿ���޸�״̬��
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_xsxx_qzxgmd" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						
						<li>
							<a href="#" onclick="qzxg();return false;" class="btn_sz">
								��Ϊǿ���޸�
							</a>
						</li>
						<li>
							<a href="#" onclick="qxqzxg();return false;" class="btn_sz">
								ȡ��ǿ���޸�
							</a>
						</li>
					</logic:equal>
					<li>
						<a href="#" onclick="exportConfig();return false;" class="btn_dc">
							����
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
			<h3 class="datetitle_01">
					<span> ��ѯ���</span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qzxgmdSzForm"></jsp:include>
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