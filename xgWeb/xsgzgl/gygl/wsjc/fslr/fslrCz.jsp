<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">		
		//��ѯ�����
		function searchRs(){
		
				var pkValue=jQuery("#pkValue").val();		
				
				var url = "gyglnew_fslr_ajax.do?method=fslrCz";
				
				url+="&pkValue="+pkValue;

				var ie = "10.0";

				var otherValue = [ie];

				searchRsByAjax(url,otherValue);
				
				setTimeout("setDivHeight()","1000")
		}
		
		function skipNext(obj){
			
			var trID = jQuery(obj).parents('tr').attr('id');
			var j=Number(trID.replace('tr',''))+1;
			var newID = "tr"+j;
			if (jQuery('#'+newID)){
				jQuery('#'+newID).click();
				jQuery('#'+newID).find('#fz_'+j).focus();
			}
		}
		
		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}
		
		function checkIn(obj){
			var val=jQuery(obj).val();
			if(val!=null && val!=""){
					jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", true);
			}
			if(val==""){
				jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", false);
			}
		}
		function checkIs(obj){
			var val=jQuery(obj).val();
			var reg=/^(?:0|[1-9][0-9]?|[1-9][0-9]+\.\d{0,2}|[0-9]+\.\d{0,2}|100)$/;
			if(val!=null && val!=""){
				if(reg.test(val)){
					jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", true);
				}else{
					jQuery(obj).val("");
					showAlertDivLayer("������0-100�����֣�",{},{"clkFun":function(){
						obj.focus();
					}});
					return;
				}	
			}
			if(val==""){
				jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", false);
			}
		}
		function checkBz(obj){
			var val=jQuery(obj).val();
			if(val!=null && val!=""){
				jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", true);
			}
		}
		
		function checkIss(obj){
			var sfsdj= jQuery("#sfsdj").val();
			var val;
			if(sfsdj=="0"){
				val=jQuery(obj).parent().parent().find("td").eq(8).find("input[type='text']").val();
			}else{
				val=jQuery(obj).parent().parent().find("td").eq(8).find("select").val();
			}
			var checkVal=jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").prop("checked");
			if(!checkVal){
				jQuery(obj).parent().parent().find("td").eq(8).find("input[type='text']").val(null);
			}
			if(val==""){
				jQuery(obj).parent().parent().find("td").eq(0).find("input[type='checkbox']").attr("checked", false);
				alertInfo("����ʱ����Ϊ��ֵ");
			}
		}
		
		function save(){
		
			var len=jQuery("[name=div_pkValue]:checked").length;
			if(len>=1){	
				
				var pkValue=jQuery("#pkValue").val();
				var array = jQuery("[name=div_pkValue]:checked");
				var sfsdj = jQuery("#sfsdj").val();
				var bzStr="";
				var str = "";
				var flg = true;
				var kfyjStr = "";
				var byqs = "";
				for (var i=0;i<array.length;i++) {
					var lddm = jQuery(array[i]).val();
					var bz = jQuery(array[i]).parent().parent().find("td input[id^='bz']").val();
					//��򾭼�ó�׸��Ի�����
					if(jQuery("#xxdm").val() == '1103202'){
						bz = "";
						var bzObj =  jQuery(array[i]).parent().parent().find("input[name='pfbz']:checkbox:checked");
						var bzObjlen = bzObj.length;
						 jQuery(bzObj).each(function(i,o){
							 bz += this.value.split("-")[0];
							 if(i != bzObjlen-1){
								 bz +=",";
							 }
						 })
					}
					bzStr += lddm+"!!@@"+bz+"!!!@@@";
					if(sfsdj=="1"){
						var dj = jQuery(array[i]).parent().parent().find("td").eq(6).find("select").val();
						if(dj == null || dj == ''){
							flg = false;
							showAlertDivLayer('��ѡ��ȼ���');
							break;
						}
						str += lddm+"!!@@"+dj+"!!!@@@";
					}else{
						var fz = jQuery(array[i]).parent().parent().find("td input[id^='fz']").val();
						if(fz == null || fz == ''){
							flg = false;
							showAlertDivLayer("����д��ֵ��");
							break;	
						}
						str += lddm+"!!@@"+fz+"!!!@@@";
						if(jQuery("#xxdm").val() == '11647'){
							byqs += lddm+"!!@@"+jQuery(array[i]).parent().parent().find("[name='byqs']").val()+"!!!@@@";
						}
						if(jQuery("#xxdm").val() == '33333'){
							var kfyj = jQuery(array[i]).parent().parent().find("td input[id^='kfyj']").val();
							kfyjStr += lddm+"_"+kfyj+'!!';
						}
					}
				}
				if(!flg){
					return false;
				}
				var params = 
					{
						sfsdj:sfsdj,
						pkValue:pkValue
					};
				var urlEncoeded = jQuery.param(params);

				if(jQuery("#xxdm").val() == '33333'){
					showDialog("����������", 600, 350, 'gyglnew_fslr.do?method=fslrCk&' + urlEncoeded+'&bzStr='+bzStr+'&str='+str+'&kfyjStr='+kfyjStr.substr(0,kfyjStr.length-2));
				}else if(jQuery("#xxdm").val() == '11647'){
					showDialog("����������", 600, 350, 'gyglnew_fslr.do?method=fslrCk&' + urlEncoeded+'&bzStr='+bzStr+'&str='+str+'&byqs='+byqs);
				}else{
					showDialog("����������", 600, 350, 'gyglnew_fslr.do?method=fslrCk&' + urlEncoeded+'&bzStr='+bzStr+'&str='+str);
				}
				//showTopWin(url,600,450);
			}else{
				alertInfo("�빴ѡ��Ҫ����ļ�¼��");
				return false;
			}
		}

		//�۷���ϸ
		function kfmx(obj){
			var jcrcid = jQuery("#pkValue").val();
			var lddmm = jQuery(obj).parents("tr").eq(0).find("td").eq(0).find("input:first").val();
			var lddmmarr = lddmm.split("!");
			var lddm = lddmmarr[0];
			var qsh = jQuery(obj).parents("tr").eq(0).find("td").eq(3).text();
			url = 'gyglnew_fslr.do?method=fslrDetail&jcrcid='+jcrcid+'&lddm='+lddm+'&qsh='+qsh;
			showDialog('��ϸ', 800, 550, url);
		}

		function showMessage(){
			jQuery(".datetitle_01").find("span").append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color='blue'>�����۷�����¼�����</font>");
		}

		jQuery(function(){
			searchRs();
			if(jQuery("#xxdm").val() == '33333' && jQuery("#sfsdj").val() == "0"){
				showMessage();
			}
		})
		
		//ѡ��ע
		function selectBz(obj){
			var fzObj = jQuery(obj).parent().parent().find("td input[id^='fz']");
			var zf = parseFloat(jQuery(fzObj).val()) || 0;
			if(obj.checked){
				zf = zf-parseFloat(obj.value.split("-")[1]);
			}else{
				zf = zf+parseFloat(obj.value.split("-")[1]);
			}
			jQuery(fzObj).val(zf);
		}
		//ȫѡ��ѡ
		function selectOrCancelAll(obj){
			var allCheckbox = jQuery(obj).parent().find("input[name='pfbz']");
			jQuery(allCheckbox).each(function(i,o){
				if(o.checked){
					jQuery(o).attr("checked",false);
				}else{
					jQuery(o).attr("checked",true);
				}
				selectBz(o);
			})
		}
	</script>
	</head>
	<body >

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ����-�������-������¼�� </a>
			</p>
		</div>


		<html:form action="/gyglnew_fslr" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<input type="hidden" name="sfsdj" id="sfsdj" value="${sfsdj }" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue}" />
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="save();return false;" class="btn_ccg">�� ��</a></li>
						<li><a href="#" onclick="refreshForm('gyglnew_wsjc_fslr.do');" class="btn_fh">�� ��</a></li>
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
					<span> ��ѯ���&nbsp;&nbsp; <font color="blue">����ճ̣�${jcrc }</font><logic:notEmpty name="rsList">
							<font color="blue">��ʾ��������ͷ��������˫����¼�ɲ鿴��ϸ��Ϣ</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=fslrForm"></jsp:include>
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