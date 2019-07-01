<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�yyp -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
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
		function searchRs(){
			var url = "qgzx_cxtj_ajax.do?method=cjffCx";
			var ie = "ie";
			var otherValue = [ie];
			searchRsByAjax(url,otherValue);
		    setTimeout("setDivHeight()","1000")
		}

		//�鿴
		function showView(){
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len==1){	
				var pkValue=jQuery("[name=div_pkValue]:checked").val();	
				var url="qgzx_cxtj.do?method=cjffCk";		
				url+="&pkValue="+pkValue;
				//showTopWin(url,800,600);
				showDialog("", 760, 525, url);
			}else{
				alertInfo("�빴ѡһ����¼���в鿴��");
				return false;
			}
		}
		
		
		function cjffcxExportConfig() {
			if(jQuery("#xxdm").val()=="13855"){
				var url = "qgzx_cxtj_ajax.do?method=cjffCxExportDataJn";
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
				}
			else if("12874"==jQuery("#xxdm").val()){
				//����ְҵ����ѧԺ�ڹ���ѧ����ͳ�Ƽ����������嵥
				if("0"==jQuery("#rsSize").val()){
					showAlertDivLayer("û�����ݿ��Ե�����");
					return false;
					}
				var url = "qgzx_cxtj_ajax.do?method=cjffCxExportDataJzy";
				setSearchTj();
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
				
			}
			else{
			customExport("cjffCx_qgzx_cxtj_ajax.do", cjffcxExportData);
			}
		}
			
		
				
		// ��������
		function cjffcxExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "qgzx_cxtj_ajax.do?method=cjffCxExportData&dcclbh=" + "cjffCx_qgzx_cxtj_ajax.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
				
		// ������ҵ��ѧ �����걨��
		function cjffcxExportData_10022() {
			var ndNum = jQuery("a[name=a_name_nd]").length;
			if(ndNum != 1){
				showAlertDivLayer("��ѡ��һ��������ȣ�");
				return false;
			}
			var yfs = jQuery("a[name=a_name_yf]");
			var yfNum = yfs.length;
			if(yfNum > 0){
				if(yfNum > 1){
					var yfNewArr = new Array();
					for(var i = 0; i < yfNum; i++){
						// ���滻a_id_�õ�"01","02"..."10","11","12"�����滻��õ�"1","2"..."10","11","12"
						yfNewArr[i] = yfs.eq(i).attr("id").replace("a_id_","").replace(/0(\d)/g, "$1");
					}
					var count = 0; // ����
					var yfNewStr = yfNewArr.join("!!@@!!") + "!!@@!!"; // �����·���ɵ��ַ���
					for(var i = 0; i < yfNewArr.length; i++){
						// ����ʱ���ѵ�ǰ���·ݼ�1��Ȼ���ڡ������·���ɵ��ַ������в��ң�û�ҵ��Ļ���������1
						if(yfNewStr.indexOf((parseInt(yfNewArr[i]) + 1) + "!!@@!!") < 0){
							count++;
						}
					}
					if(count != 1){
						showAlertDivLayer("��ѡ�������ķ����·ݣ�");
						return false;
					}
				}
			}else{
				showAlertDivLayer("��ѡ������һ�������·ݣ�");
				return false;
			}
			setSearchTj();//���ø߼���ѯ����
			var url = "qgzx_cxtj.do?method=getCjffSbbDclist";
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		// ��ý���Ի���������
		function cjffcxExportConfigCm() {
			
			var url = "qgzx_cxtj_ajax.do?method=cjffCxExportDataCm";

			var njs = new Array();//�꼶
			var xydms = new Array();//ѧԺ
			var zydms = new Array();//רҵ
			var bjdms = new Array();//�༶
			var ffyf = new Array();//�����·�
			var ffnd = new Array();//�������
			
			
			//�꼶
		    n=0;
			
			jQuery("a[name=a_name_nj]").each(function(){
				var id = jQuery(this).attr("id");
				var nj = id.replace("a_id_","");
				if(nj !=null && nj!=""){
					njs[n] = nj;
					n++;
				}
			});
			
			if(njs != null && njs.length>0){
				url+= "&array_njs="+njs.join("!!array!!");
			}
				
			//ѧԺ		
			n=0;
			
			jQuery("a[name=a_name_xy]").each(function(){
				var id = jQuery(this).attr("id");
				var xy = id.replace("a_id_","");
				if(xy !=null && xy!=""){
					xydms[n] = xy;
					n++;
				}
			});
			
			if(xydms != null && xydms.length>0){
				url+= "&array_xydms="+xydms.join("!!array!!");
			}
				
			//רҵ		
			n=0;

			jQuery("a[name=a_name_zy]").each(function(){
				var id = jQuery(this).attr("id");
				var zy = id.replace("a_id_","");
				if(zy !=null && zy!=""){
					zydms[n] = zy;
					n++;
				}
			});
	
			if(zydms != null && zydms.length>0){
				url+= "&array_zydms="+zydms.join("!!array!!");
			}
			
			//�༶		
			n=0;
			
			jQuery("a[name=a_name_bj]").each(function(){
				var id = jQuery(this).attr("id");
				var bj = id.replace("a_id_","");
				if(bj !=null && bj!=""){
					bjdms[n] = bj;
					n++;
				}
			});
			
			if(bjdms != null && bjdms.length>0){
				url+= "&array_bjdms="+bjdms.join("!!array!!");
			}


			//���		
			n=0;
			
			jQuery("a[name=a_name_nd]").each(function(){
				var id = jQuery(this).attr("id");
				var nd = id.replace("a_id_","");
				if(nd !=null && nd!=""){
					ffnd[n] = nd;
					n++;
				}
			});
			
			if(ffnd != null && ffnd.length>0){
				url+= "&array_ffnd="+ffnd.join("!!array!!");
			}

			//�·�		
			n=0;
			
			jQuery("a[name=a_name_yf]").each(function(){
				var id = jQuery(this).attr("id");
				var yf = id.replace("a_id_","");
				if(yf !=null && yf!=""){
					ffyf[n] = yf;
					n++;
				}
			});
			
			if(ffyf != null && ffyf.length>0){
				url+= "&array_ffyf="+ffyf.join("!!array!!");
			}

			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}


		// �°�鿴������
		function zxsxxView(xh) {
			showDialog("ѧ����Ϣ��ѯ", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
					+ "&xs");
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


		<html:form action="/qgzx_cxtj" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" class="btn_ck" onclick="showView();return false;">�鿴��ϸ</a>
						</li>
						
						<li><a href="#" onclick="cjffcxExportConfig();return false;" class="btn_dc">����</a></li>
						
						<%--<li><a href="#" onclick="configureExportData();return false;" class="btn_dc">��������</a></li>--%>
						<logic:equal name="xxdm" value="11647">
							<li><a href="#" onclick="cjffcxExportConfigCm();return false;" class="btn_dc">�������ܵ���</a></li>
						</logic:equal>
						<logic:equal name="xxdm" value="10022">
							<li><a href="#" onclick="cjffcxExportData_10022();return false;" class="btn_down">�����걨��</a></li>
						</logic:equal>
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
					<span> ��ѯ���&nbsp;&nbsp; </span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qgzxCxtjForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>