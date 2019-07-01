<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		
		function searchRs(){
			var url = "gyglnew_fscx_ajax.do?method=fscxCx";

			var ie = "10.0";

			var otherValue = [ie];

			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","1000")
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		function showModi(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
				var lddm=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(0).find("input[type='hidden']").val();
				
				var qsh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(3).html();
				
				var url="gyglnew_fscx.do?method=fscxWh";
				
				url+="&pkValue="+pkValue;
				
				url+="&lddm="+lddm;
				
				url+="&qsh="+qsh;
				
				//showTopWin(url,600,450);
				showDialog("�޸�������",600,390, url);
			}else{
				alertInfo("�빴ѡһ����Ҫ�޸ĵļ�¼��");
				return false;
			}
		}
		
		function showView(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();
				
				var lddm=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(0).find("input[type='hidden']").val();
				
				var qsh=jQuery("[name=div_pkValue]:checked").parent().parent().find("td").eq(3).html();
				
				var url="gyglnew_fscx.do?method=fscxCk";
				
				url+="&pkValue="+pkValue;
				
				url+="&lddm="+lddm;
				
				url+="&qsh="+qsh;
				
				//showTopWin(url,650,500);
				showDialog("�鿴������",600,390, url);
			}else{
				alertInfo("�빴ѡһ����Ҫ�鿴�ļ�¼��");
				return false;
			}
		}
		
		function deleteFsxc(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			var blog=true;
			if(len>=1){	
				if(blog){
					confirmInfo("�ò�������ɾ��������¼����Ϣ���Ƿ�ȷ������������",function(tag){
						if(tag=="ok"){
							var array = jQuery("[name=div_pkValue]:checked");
							var str = "";
							for (var i=0;i<array.length;i++) {
								var pkValue = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='checkbox']").val();
								var lddm = jQuery(array[i]).parent().parent().find("td").eq(0).find("input[type='hidden']").val();
								var qsh = jQuery(array[i]).parent().parent().find("td").eq(3).html();
								str += pkValue+"!@"+lddm+"!@"+qsh+"!!@@";
							}
							var parameter={}
							var url="gyglnew_fscx.do?method=fscxSc";	
							parameter["str"]=str;							
							jQuery.ajaxSetup({async:false});	
							jQuery.post(url,
								parameter,
								function(result){
									alertInfo(result,function(tag){
										if(tag=="ok"){
											onShow();
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
		
		jQuery(function(){
			onShow();
		})
		
		//��������
		function wsfcxExportConfig() {
			if(checkDcCxTj()){
				customExport("gyglnew_wsjc_fscx.do", wsfcxExportData);
			}
		}
			
		
			
		// ��������
		function wsfcxExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyglnew_fscx_ajax.do?method=wsfcxExportData&dcclbh=" + "gyglnew_wsjc_jcrcgl.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		//�´�������ͳ�Ƶ���
		function wsfTjDc(){
			var url ="gyglnew_fscx_ajax.do?method=wsfDcOfWd";
			var xqLength=jQuery("a[name=a_name_xq]").length;
			var xnLength=jQuery("a[name=a_name_xn]").length;
			var ldLength=jQuery("a[name=a_name_ld]").length;
			if(xqLength != "1"){
				showAlertDivLayer("��ѡ��һ��ѧ�ڲ�ѯ������");
				return false;
			}
			if(xnLength != "1"){
				showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
				return false;
			}
			if(ldLength != "1"){
				showAlertDivLayer("��ѡ��һ��¥����ѯ������");
				return false;
			}
			setSearchTj();
			url = addSuperSearchParams(url);
			document.forms[0].action = url;
			document.forms[0].submit();
			
			}

		//��ͳ�Ƶ���
		function zTjDc(){
			var url ="gyglnew_fscx_ajax.do?method=zTjDc";
			setSearchTj();
			var kssj = jQuery("#kssj_kssj").val();
			var jssj = jQuery("#kssj_jssj").val();
			if(kssj == null || kssj == '' ||  jssj == null || jssj == ''){
				showAlertDivLayer("����������ֹʱ�䣡");
				return false;
			}
			url = addSuperSearchParams(url);
			document.forms[0].action = url;
			document.forms[0].submit();			
		}

		//��Ԣ��������ͳ�Ƶ���
		function gywsfDc(){
			showDialog("��Ԣ��������ͳ�Ƶ���", 400, 250, "gyglnew_fscx.do?method=gyywsfDc");
		}

		//��֤������ѯ����   ���Ե�����true�� �����Ե�����false
		function checkDcCxTj(){
			var xxdm = jQuery("#xxdm").val();
			var xxdmMap = {"10338":"10338"};

			//ѧУ�����������֤����
			if(xxdmMap[xxdm] != null && xxdmMap[xxdm] != ""){
				var xqLength=jQuery("a[name=a_name_xq]").length;
				var xnLength=jQuery("a[name=a_name_xn]").length;
				if(xqLength != "1"){
					alert("��ѡ��һ��ѧ�ڲ�ѯ������");
					return false;
				}
				if(xnLength != "1"){
					alert("��ѡ��һ��ѧ���ѯ������");
					return false;
				}
			}
			return true;
		}

		//���������ȼ�
		function pdFsDj(){

			var xn_num = jQuery("a[name=a_name_xn]").length;

			if(xn_num!=1){
				alertInfo("ѧ�������ֻ��ѡ��һ��");
				return false;
			}

			var url="gyglnew_fscx_ajax.do?method=pdFsDj";
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
			
			//��������
		 	var parameter = {
				"input_mhcx":escape(input_mhcx),
				"mhcx_lx":mhcx_lx,
				"searchTj":searchTj.join("!!@@!!"),
				"searchTjz":searchTjz.join("!!@@!!"),
				"searchLx":searchLx.join("!!@@!!")
			};

			//��������������
		 	pdFsLoad();
			jQuery.post(url,parameter,function(data){
				//�����������
				pdFsComplete();
				alert(data);
			});
		}

		//��������������
		function pdFsLoad(){
			jQuery("#divWaiting").show();
			jQuery("#divDisable").show();
			jQuery("#div_rs").hide();
			jQuery("#main_box").hide();
			jQuery(".btn_cx").attr("disabled","disabled");
			jQuery(".btn_sz").attr("disabled","disabled");
			jQuery(".buttonbox ul li a").attr("disabled","disabled");
		}

		//�����������
		function pdFsComplete(){
			jQuery("#divWaiting").hide();
			jQuery("#divDisable").hide();
			jQuery("#div_rs").show();
			jQuery("#main_box").show();
			jQuery(".btn_cx").removeAttr("disabled");
			jQuery(".btn_sz").removeAttr("disabled");
			jQuery(".buttonbox ul li a").removeAttr("disabled");
		}

		// ������ѧ����������
		function bjgmdExportConfig() {
			if(checkDcCxTj()){
				customExport("gyglnew_wsjc_bjgmd.do", bjgmdExportData);
			}
		}
			
		
			
		// ��������
		function bjgmdExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyglnew_fscx_ajax.do?method=bjgmdExportData&dcclbh=" + "gyglnew_wsjc_bjgmd.do";//dcclbh,�������ܱ��
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


		<html:form action="/gyglnew_fslr" method="post">

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
						<logic:notEqual name="userType" value="stu">
							<logic:equal name="writeAble" value="yes">
								<li>
									<a href="#" onclick="showModi();return false;" class="btn_xg">�޸�</a>
								</li>
								<li>
									<a href="#" onclick="deleteFsxc();return false;" class="btn_sc">ɾ��</a>
								</li>
								
								<%--<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>
								--%>
							</logic:equal>
								<li><a href="#" class="btn_dc" onclick="wsfcxExportConfig();return false;">����</a></li>
							<logic:equal name="xxdm" value="10338">
								<li><a href="#" class="btn_dc" onclick="pdFsDj();return false;">�����ȼ�</a></li>
							</logic:equal>
								
	<%--						¼�뷽ʽΪ����ʱ���Ե�����60��Ϊ������--%>
	
	<%--							��������ɣ�δ���ԡ�V513�汾����--%>
							<logic:equal name="sfsdj" value="0">
								<li><a href="#" class="btn_dc" onclick="bjgmdExportConfig();return false;"
								title="����60�ֵ�ѧ��������"
								>��������������</a></li>
							</logic:equal>
							<logic:equal name="xxdm" value="10351">
								<li><a href="#" class="btn_dc" onclick="wsfTjDc();return false;">������������ͳ��</a></li>
							</logic:equal>
							<logic:equal name="xxdm" value="33333">
								<li><a href="#" class="btn_dc" onclick="zTjDc();return false;">����������ֵ���</a></li>
							</logic:equal>
							<!-- ����ҽҩ�ߵ�ְҵѧУ-->
							<logic:equal name="xxdm" value="70002">
								<li><a href="#" class="btn_dc" onclick="gywsfDc();return false;">��Ԣ��������ͳ�Ƶ���</a></li>
							</logic:equal>
						</logic:notEqual>					
					</ul>
				</div>
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �๦�ܲ����� end-->

			<!-- ������ʾ����ʼ -->
			<div class="main_box" id="main_box">
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
					page="/sjcz/turnpage.jsp?form=fscxForm"></jsp:include>
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
