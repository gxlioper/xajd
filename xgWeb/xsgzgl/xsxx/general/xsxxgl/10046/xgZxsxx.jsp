<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�lt -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>

		<script type="text/javascript">

		/**
		 * ���������
		 */
		function showAlertDivLayer() {
			var argumentsArr = Array.prototype.slice.call(arguments);
			if(argumentsArr[0] == null) return;
			
			var clickFun = null;
			
			if (argumentsArr.length == 3){
				clickFun = argumentsArr[2]["clkFun"];
			}
			ymPrompt.alert({
				title:"ϵͳ��ʾ",
				useSlide:true,
				maskAlphaColor:"#FFFFFF",
				maskAlpha:0.3,
				message:argumentsArr[0],
				width:340,
				winPos:[180,160],
				height:160,
				//showMask:false,
				handler:clickFun
			});
			//setTimeout(function(){ymPrompt.doHandler();},3000);
		}
		
		function displayZdsx() {
			
			//�Բ���ѧ��Ϊ׼���ֶν��в����޸Ĵ���
			var zdpzstr = jQuery('#zdpzstr').val();
			var zdpzArr = zdpzstr.split("LiTTLiTT");
			var xgzt = jQuery('#xgzt').val();
			var btzdId = [];
			var xszp = jQuery('#xszp');
			if ("1"==xgzt) {//�������ֶεĴ���

				
				if (zdpzArr != null && zdpzArr.length > 0) {

					for (var i=0;i<zdpzArr.length;i++) {
						var zdid = zdpzArr[i].split("!!@@!!")[0];
						var sfzd = zdpzArr[i].split("!!@@!!")[1];
						var sfbt = zdpzArr[i].split("!!@@!!")[2];

						if(zdid=="zp"){
							if(sfzd=='y'){
								document.getElementById("zpscbtn").style.display = "none";
								}
							if(sfzd=='null'){
								document.getElementById("zpscbtn").style.display = "";
								}
							if(sfbt=='y'){
								document.getElementById("zpscbtn").style.display = "";
								jQuery('#zpsfbt').attr("value","y");
								}
							}
						
						if ('y'==sfzd) {//ֻ���Ĵ���
							var tagValue;

							if (zdid == 'jg'){
								tagValue = jQuery("#jgshen option:selected[value !='']").text();
								tagValue += jQuery("#jgshi option:selected[value !='']").text(); 
								tagValue += jQuery("#jgxian option:selected[value !='']").text();

								var html = "<input type='hidden' name='jgshen' value='"+jQuery("#jgshen").val()+"'/>";
									html+= "<input type='hidden' name='jgshi' value='"+jQuery("#jgshi").val()+"'/>";
									html+= "<input type='hidden' name='jgxian' value='"+jQuery("#jgxian").val()+"'/>";

								jQuery('#jgtd').html("<div style='word-break:break-all;width:97%'>" + tagValue +"</div>");
								jQuery('#jgtd').append(html);
										
							} else if (zdid == 'hkszd'){
								tagValue = jQuery("#hkshen option:selected[value !='']").text();
								tagValue += jQuery("#hkshi option:selected[value !='']").text();
								tagValue += jQuery("#hkxian option:selected[value !='']").text();
								
								var html = "<input type='hidden' name='hkshen' value='"+jQuery("#hkshen").val()+"'/>";
								html+= "<input type='hidden' name='hkshi' value='"+jQuery("#hkshi").val()+"'/>";
								html+= "<input type='hidden' name='hkxian' value='"+jQuery("#hkxian").val()+"'/>";

								jQuery('#hktd').html("<div style='word-break:break-all;width:97%'>" + tagValue +"</div>");
								jQuery('#hktd').append(html);
							} else if (zdid == 'syd'){
								tagValue = jQuery("#syshen option:selected[value !='']").text();
								tagValue += jQuery("#syshi option:selected[value !='']").text(); 
								tagValue += jQuery("#syxian option:selected[value !='']").text();
								
								var html = "<input type='hidden' name='syds' value='"+jQuery("#syshen").val()+"'/>";
								html+= "<input type='hidden' name='sydshi' value='"+jQuery("#syshi").val()+"'/>";
								html+= "<input type='hidden' name='sydx' value='"+jQuery("#syxian").val()+"'/>";

								jQuery('#sytd').html("<div style='word-break:break-all;width:97%'>" + tagValue +"</div>");
								jQuery('#sytd').append(html);
							} else if (zdid=='xb') {
								var xb = jQuery('input[name=xb]:checked').val();
								jQuery('input[name=xb]:checked').parent().html(xb+"<input type='hidden' name='xb' value='"+xb+"'/>");
								jQuery('#xbn').parent().html("");
							} else if (document.getElementById(zdid)){
								var tagName = document.getElementById(zdid).tagName.toLowerCase();

								if (tagName =='select') {
									tagValue = jQuery("#"+zdid+" option:selected[value !='']").text();
								}else {
									tagValue = jQuery('#'+zdid).val();
								}
								var hiddenInput = jQuery("<input type='hidden' name='"+jQuery('#'+zdid).attr("name")+"' value='"+jQuery('#'+zdid).val()+"'/>");
								jQuery('#'+zdid).parent().html("<div style='word-break:break-all;width:97%'>" + tagValue +"</div>").append(hiddenInput);
							}
							
						} else if ('y' == sfbt) {//����Ĵ���

							var isSimple = true;
							
							if (zdid == "jg"){
								zdid = "jgshen";
							} else if (zdid == "hkszd"){
								zdid = "hkshen";
							} else if (zdid == "syd"){
								zdid = "syshen";
							} else if (zdid.length > 4 && zdid.substr(0,4) == "jtcy"){
								isSimple = false;
							}
							btzdId.push(zdid);

							//�Ǽ�ͥ��Ա��ص���th�м�*
							if (isSimple){
								//if (zdid=='xm' || zdid=='xb') {continue;}
								if (zdid=='xb') {
									jQuery('#xbn').parent().prev().html("<font color='red'>*</font>" + jQuery('#xbn').parent().prev().html());
								} else {
									jQuery('#'+zdid).parent().prev().html("<font color='red'>*</font>" + jQuery('#'+zdid).parent().prev().html());
								}
							} else {
								jQuery('#'+zdid).parent().html("<font color='red'>*</font>" + jQuery('#'+zdid).parent().html());
							}
							
						}
					}
				}	


				
			} else {//δ�����ֶεĴ���
				//��ʱ�����κ���
			}

			//ѧ���û�Ҫ��ѧԺ��רҵ���༶���꼶��Ϊ�����޸�
			initStuzd();

			//���ύ �¼�
			jQuery("#buttonSave").bind("click",function(){
				saveZxsSq(btzdId.join("-"));
			});
		}

		//��ʼ��ѧ�����޸ĵ��ֶ�
		function initStuzd() {
			if ('stu'==jQuery('#userType').val()) {
				jQuery('#nj').parent().html("<div style='word-break:break-all;width:97%'>" 
						+ jQuery('#nj').val()+"</div>");
				jQuery('#xymc').parent().html("<div style='word-break:break-all;width:97%'>" 
						+ jQuery('#xymc').val()+"</div>");
				jQuery('#zymc').parent().html("<div style='word-break:break-all;width:97%'>" 
						+ jQuery('#zymc').val()+"</div>");
				jQuery('#bjmc').parent().html("<div style='word-break:break-all;width:97%'>" 
						+ jQuery('#bjmc').val()+"</div>");
				//jQuery('#button_bj').css("display","none");
				document.getElementById('njbt').style.display = "none";
				document.getElementById('xybt').style.display = "none";
				document.getElementById('zybt').style.display = "none";
				document.getElementById('bjbt').style.display = "none";
			}
		}

		function getBjbyPzzd(){
			var zdpzstr = jQuery('#zdpzstr').val();
			getBj(zdpzstr);
		}
		
		//������У��������Ϣ
		function saveZxsSq(btzdId){
			var sfbt =  jQuery('#zpsfbt').val();
			var sfcz =jQuery('#zpsfcz').val();
			if(sfbt=="y"&&"false"==sfcz){
				alertError("�����ϴ�һ����Ƭ��")
				return false;
				}
			var str = "xh-nj-xydm-zydm-bjdm-"+btzdId;
			var flag = checkNotNull(str);
			if (!flag) {
				alertError("��\'*\'���ֶα�����д��");
				return false;
			}

			if (btzdId.indexOf("xb") > -1) {
				if (jQuery("input[name=xb]:checked").length <= 0) {
					alertError("��\'*\'���ֶα�����д��");
					return false;
				}
			}
		
			var xh = $("xh").value;
			if(xh.length < 6){
				alertError("ѧ��������ά��6λ��");
			    return false;
			}
			
			var sfxgFlag = false;
			var userType = jQuery('#userType').val();
			if ('stu'==userType) {
				var xsxxstr = jQuery('#xsxxstr').val();
				if (xsxxstr != "") {
					var array = xsxxstr.split("LiTT!!LiTT");
					
					jQuery.ajaxSetup({async:false});
					for (var i=0;i<array.length;i++) {
						var zd = array[i].split("LiTT")[0];
						var zdz = array[i].split("LiTT").length > 1 ? array[i].split("LiTT")[1] : "";
						zdz = zdz ==null || zdz=="null" ? "" : zdz;
						
						if (document.getElementById(zd) && zdz != document.getElementById(zd).value && zd!='xb') {
							if (zd=='jgshen' || zd=='jgshi' || zd=='jgxian'||zd=='hkshen'||zd=='hkshi'||zd=='hkxian'||zd=='syshen'||zd=='syshi'||zd=='syxian') {continue;}
							//alert(zd + "  -> " + "zdz:" +zdz + "    :"+document.getElementById(zd).value + "???");
							sfxgFlag = true;
							break;
						}
						if (zd=='xb' && jQuery('input[name=xb]')) {
							
							if (zdz != jQuery('input[name=xb]:checked').val()) {
								sfxgFlag = true;
							}
						}

						//���ᴦ��
						if (zd=='jg') {
							if ($('jgxian').value != null && $('jgxian').value !='') {
								if (zdz!=$('jgxian').value) {
									sfxgFlag = true;
								}
							} else if ($('jgshi').value != null && $('jgshi').value !='') {
								if (zdz!=$('jgshi').value) {
									sfxgFlag = true;
								}
							}else if ($('jgshen').value != null && $('jgshen').value !='') {
								if (zdz!=$('jgshen').value) {
									sfxgFlag = true;
								}
							}
						}
						if (zd=='hkszd') {
							if ($('hkxian').value != null && $('hkxian').value !='') {
								if (zdz!=$('hkxian').value) {
									sfxgFlag = true;
								}
							} else if ($('hkshi').value != null && $('hkshi').value !='') {
								if (zdz!=$('hkshi').value) {
									sfxgFlag = true;
								}
							}else if ($('hkshen').value != null && $('hkshen').value !='') {
								if (zdz!=$('hkshen').value) {
									sfxgFlag = true;
								}
							}
						}
						if (zd=='syd') {
							if ($('syxian').value != null && $('syxian').value !='') {
								if (zdz!=$('syxian').value) {
									sfxgFlag = true;
								}
							} else if ($('syshi').value != null && $('syshi').value !='') {
								if (zdz!=$('syshi').value) {
									sfxgFlag = true;
								}
							}else if ($('syshen').value != null && $('syshen').value !='') {
								if (zdz!=$('syshen').value) {
									sfxgFlag = true;
								}
							}
						}
						
					}
					//if (!flag) {
						//alertError("��δ�޸��κ���Ϣ����ȷ�ϣ�");
						//return false;
				//	} 
					jQuery.ajaxSetup({async:true});
				}
				
			}
			//alert(sfxgFlag);
			refreshForm('xsxx_tygl.do?method=bcZxsxx&doType=xg&sfxgFlag='+sfxgFlag+'');
		}
		//��ʾTBODY���� 
		function displayTbody(tbodyId) {
			if (document.getElementById(tbodyId)) {
				document.getElementById(tbodyId).style.display=(document.getElementById(tbodyId).style.display==''?'none':'');
			}
		}
		//��ʾ�ϴ���Ƭ
		function showZpscDiv(){
	
			var xh = jQuery("#xh").val();
			
			if(xh == ""){
				alertError("������дѧ�ţ�");
			}else{
				tipsWindown("ϵͳ��ʾ","id:addPic","380","130","true","","true","id");
			}
		}
		//��Ƭ�ϴ�
		function uploadStuPic(){
		
			jQuery.ajaxSetup({async:false,dataType:'text'});
			
			var xh = jQuery("#xh").val();
		
			jQuery.ajaxFileUpload({
			  url:'general_xsxx_zxxs_ajax.do?method=uploadStuPic&xh='+xh,//�������˳���
			  secureuri:false,
			  fileElementId:'stuPic',//input���ID
			  success:function(data,type){
				if (type=='success'){
					jQuery("#xszp").attr("src","xsxx_xsgl.do?method=showPhoto&xh="+xh+"&tt="+new Date());
					jQuery('#zpsfcz').attr("value","true");
					alertInfo(data);
				}
			  }
			});
			jQuery.ajaxSetup({async:true});
		}
		
		//�ƶ����λ��
		function moveTable(tabid) {
			jQuery("#"+tabid+"_a").click(function(){
				jQuery("#"+tabid).focus();
				return false;
			});
			
		}

		function onShow(){
			var xh = jQuery("#xh").val();
			//��ֻ���������ֶεĴ���
			displayZdsx();
			document.getElementById("pts").style.display="none";
			var xgts = jQuery("#xgts").val();
			if(xgts!=""&&xgts!=null){
				document.getElementById("pts").style.display="";
				}
			jQuery("#xszp").attr("src","xsxx_xsgl.do?method=showPhoto&xh="+xh+"&tt="+new Date());
		}
						
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body >
	
		<html:form action="/xsxx_tygl"  method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>

			<!-- ��ѧ��Ϊ׼���ֶ�����  -->
			<input type="hidden" name="zdpzstr" id="zdpzstr" value="${zdpzstr }"/>
			<input type="hidden" name="xgzt" id="xgzt" value="${xgzt }"/>
			<input type="hidden" name="fromQzxg" id="fromQzxg" value="${fromQzxg }"/>
			<input type="hidden" name="zpsfbt" id="zpsfbt" />
			<input type="hidden" name="zpsfcz" id="zpsfcz"  value="${zpsfcz }"/>

<!-- ������� -->
<div style="height:50px;">
<div id="position-fixed" style="top:0; background:#fff;position:fixed;width:100%">
<div class="title_xxxx">
	<span class="people_xx">${rs.xm } ��ѧ�ţ� ${rs.xh }��</span>
    <span class="wxts">��ܰ���ѣ� <span>����������� ���Կ��ٶ�λ������Ҫ�鿴����Ϣ</span></span>
</div>
	<div class="prompt" id="pts">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				${xgts}
			</p>
		</div>
<!-- ��ϸ��Ϣ��λ -->
<div class="position_xxxx after" id="position-fixed" >
    <ul class="list_xxxx">
    	<li id="tab_jbxx_a" ><a href="#tab_jbxx" class="smooth">������Ϣ</a></li>
        <li id="tab_lxfs_a" ><a href="#tab_lxfs" class="smooth">��ϵ��ʽ</a></li>
        <li id="tab_jtcyxx_a" ><a href="#tab_jtcyxx" class="smooth">��ͥ��Ա��Ϣ</a></li>
        <li id="tab_qtxx_a" ><a href="#tab_qtxx" class="smooth">������Ϣ</a></li>
        <logic:notEmpty name="shxxList">
        	<li id="tab_shxx_a" ><a href="#tab_shxx" class="smooth">�����Ϣ</a></li>
        </logic:notEmpty>
    </ul>
</div>
</div>
</div>
<!-- ������Ϣ -->
<logic:equal value="stu" name="userType">
<div class="demo_xxxx" style="margin-top:20px;" id="demo_xxxx" >
</logic:equal>
<logic:notEqual value="stu" name="userType">
<div class="demo_xxxx" style="margin-top:20px;" id="demo_xxxx" >
</logic:notEqual>
				<input type="hidden" name="userType" id="userType" value="${userType }"/>
				<input type="hidden" name="xsxxstr" id="xsxxstr" value="${xsxxstr }"/>
				<input type="hidden" name="xgts" id="xgts" value="${xgts }"/>
				<input type="hidden" name="bjdm" id="bjdm" value="${rs.bjdm}" />
				
				<table width="100%" border="0" style="margin-bottom: 2px" class="formlist" id="tab_jbxx">
					<!-- ѧ��������Ϣ begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5" >
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody id="hi_jbxx">
						<tr>
							<th width="13%">
								ѧ��
							</th>
							<td width="30%">
									<html:text name='rs'  property="xh" styleClass="text_nobor" styleId="xh"
								  maxlength="20" readonly="true"/>
							</td>
							<th  width="13%">
								����
							</th>
							<td width="30%">
								<html:text name='rs'  property="xm" styleId="xm" maxlength="16"
									styleClass="text_nor" />
							</td>
							<td rowspan="5" class="nohover"
								style="vertical-align:middle; text-align:left;width:115px;">
									<div id="stuImg" class="open_img" style="margin-left:0px;margin-top: 0px;height: 130px">
									<img style="width:100px;height:130px" id="xszp"
										src="xsxx_xsgl.do?method=showPhoto&xh=${rs.xh}"
										border="0">
								</div>
								<br/>
								<div id ="zpscbtn" align="center">
									<button  type="button"  onclick="showZpscDiv();" >
									�ϴ���Ƭ
									</button>
								</div>
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								<html:radio name="rs" property="xb" styleId="xbn"  value="��">��</html:radio>
								<html:radio name="rs" property="xb" styleId="xbnv" value="Ů">Ů</html:radio>
							</td>
							<th>
								��������
							</th>
							<td>
								<html:text name='rs'  property="csrq"
									onclick="return showCalendar('csrq','y-mm-dd');"
									styleClass="text_nor" styleId="csrq" readonly="true" />
							</td>
						</tr>
						
						<tr>
							<th width="13%">
								<span class="red" id="njbt">*</span>�꼶
							</th>
							<td width="30%">
								<html:select name='rs'  property="nj" styleId="nj"
									style="width:90px" onchange="initXyzybj();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								ѧ��(��)
							</th>
							<td colspan="">
								<html:text name='rs' property="xz" styleId="xz"
									onkeyup="value=value.replace(/[^\d|.]/g,'')" maxlength="1"
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red" id="bjbt">*</span>�༶
							</th>
							<td colspan="">
								
								<input type="text" id="bjmc"
									onkeydown="return onlyBackSpace(this);" value="${rs.bjmc }" />
								<input type="hidden" id="bjdm"  value="${rs.bjdm }" name="bjdm"/>	
									
								<button type="button" class="btn_01" id="button_bj" style="display: "
									onclick="getBjbyPzzd();">
									ѡ��
								</button>
								
							</td>
						
							
							<th>
								������ò
							</th>
							<td>
								<html:select name='rs'  property="zzmm" styleId="zzmm"
									style="width:150px" >
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red" id="xybt">*</span>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<input type="text" id="xymc"
									onkeydown="return onlyBackSpace(this);" value="${rs.xymc }" />
								<!--  <button class="btn_01" onclick="getXyzybj('xy');" id="button_xy">
									ѡ��
								</button>-->
								
								<input type="hidden" name="xydm" id="xydm" value="${rs.xydm}" />
								
							</td>
						
							
							<th>
								����
							</th>
							<td>
								<html:select name='rs'  property="mz" styleId="mz"
									style="width:150px">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
							
						</tr>
						<tr>
							<th>
								<span class="red" id="zybt">*</span>רҵ
							</th>
							<td>
								
								<input type="text" id="zymc"
									onkeydown="return onlyBackSpace(this);" value="${rs.zymc }" />
								<!-- <button class="btn_01" onclick="getXyzybj('zy');" id="button_zy"
									style="display: ">
									ѡ��
								</button> -->
								
								<input type="hidden" name="zydm" id="zydm"
									value="${rs.zydm}" />
								
							</td>
							<th>
								ѧ��
							</th>
							<td align="left" colspan="2">
								<html:select name='rs' property="xjztm" style="width:150px"
									styleId="xjztm">
									<html:option value=""></html:option>
									<html:options collection="xjztList" property="zxdmmc"
										labelProperty="zxdmmc" />
								</html:select>
							</td>
						</tr>
						
						<tr>
							<th>
								��ѧʱ��
							</th>
							<td colspan="">
							<html:text name='rs'  property="rxrq" styleId="rxrq"
									maxlength="10" styleClass="text_nor"
									onclick="return showCalendar('rxrq','y-mm-dd');" />	
								
							</td>
							<th>
							���֤��
							</th>
							<td align="left" colspan="2">
								<html:text name='rs' property="sfzh" styleId="sfzh"
									styleClass="text_nor" maxlength="18"
									onblur="if(!checkSfzh(this)){this.focus();}" />
							</td>
						</tr>
						
						<tr>
							<th>
								����
							</th>
							<td colspan="4" id="jgtd">
									<!--��ַ��Ϣȡ��׼��-->
									<logic:present name="ssList">
										<html:select name='rs'  property="jgshen" styleId="jgshen"
											onchange="loadShi('jgshen','jgshi','jgxian');" style="width:120px">
											<html:option value=""></html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name='rs'  property="jgshi" styleId="jgshi"
											onchange="loadXian('jgshi','jgxian')" style="width:120px">
											<html:options collection="jgshiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name='rs' property="jgxian" styleId="jgxian" style="width:120px">
											<html:options collection="jgxianList" property="xiandm"
												labelProperty="xianmc" style="width:120px"/>
										</html:select>
									</logic:present>
									<!--end��ַ��Ϣȡ��׼��-->
								
							</td>
						</tr>
						
						<tr>
							<th>
								�������ڵ�
							</th>
							<td colspan="4" id="hktd">
									<!--��ַ��Ϣȡ��׼��-->
								<logic:present name="ssList">
									<html:select name='rs'  property="hkshen" styleId="hkshen"
										onchange="loadShi('hkshen','hkshi','hkxian');" style="width:120px">
										<html:option value=""></html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name='rs'  property="hkshi" styleId="hkshi"
										onchange="loadXian('hkshi','hkxian')" style="width:120px">
										<html:options collection="hkshiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name='rs'  property="hkxian" styleId="hkxian" style="width:120px">
										<html:options collection="hkxianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
								</logic:present>
								<!--end��ַ��Ϣȡ��׼��-->
							</td>
						</tr>
						<tr>
							<th>
								��Դ��(�߿�ʱ�������ڵ�)
							</th>
							<td colspan="4" id="sytd">
									<!--��ַ��Ϣȡ��׼��-->
								<logic:present name="ssList">
									<html:select name='rs'  property="syds" styleId="syshen"
										onchange="loadShi('syshen','syshi','syxian')" style="width:120px">
										<html:option value=""></html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name='rs'  property="sydshi" styleId="syshi" style="width:120px"
										onchange="loadXian('syshi','syxian')">
										<html:options collection="sydshiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name='rs'  property="sydx" styleId="syxian" style="width:120px">
										<html:options collection="sydxianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
								</logic:present>
								<!--end��ַ��Ϣȡ��׼��-->
							</td>
						</tr>
					</tbody>
					
				</table>
				
				<table width="100%" border="0" style="margin-bottom: 2px" class="formlist" id="tab_lxfs">
					<!-- ѧ����ϵ��ʽ begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>��ϵ��ʽ</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_lxfs">
					<tr>
							<th width="13%">
								��ϵ�绰
							</th>
							<td colspan="" width="30%">
							<html:text name='rs' property="sjhm" styleId="sjhm"
									onblur="checkPhone(this)"
									styleClass="text_nor" maxlength="11" />
								
							</td>
							<th width="13%">
							��������
							</th>
							<td align="left" colspan="2">
									<html:text name='rs' property="dzyx" styleClass="text_nor"
									styleId="dzyx" maxlength="25"/>
							</td>
						</tr>
						<tr>
						<th>
								QQ����
							</th>
							<td>
								<html:text name="rs" property="qqhm" styleId="qqhm"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" maxlength="11" />
							</td>
							<th>
							��ͥ�绰
							</th>
							<td align="left" colspan="2">
							<html:text name="rs" property="jtdh" maxlength="25" styleId="jtdh" onblur="checkPhone(this)"/>
							</td>
							</tr>
							<tr>
							<th>
								��ͥ�ʱ�
							</th>
							<td>
								<html:text name='rs' property="jtyb" maxlength="6" styleId="jtyb"
								onkeyup="value=value.replace(/[^\d]/g,'') "/>
							</td>
							<th>
							��ͥ��ַ
							</th>
							<td align="left" colspan="2">
							<html:text name="rs" property="jtszd" maxlength="25" styleId="jtszd" style="width:90%"/>
							</td>
							</tr>
					</tbody>
				</table>
				
				
				<table style="margin-bottom: 2px" width="100%" border="0" class="formlist" id="tab_jtcyxx">
					<!-- ѧ����ͥ��Ա��Ϣ begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>��ͥ��Ա��Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody id="hi_jtcyxx">
						<tr>
							<th width="13%">
								<div align="center">����</div>
							</th>
							<th >
								<div align="center">�뱾�˹�ϵ</div>
							</th>
							<th>
								<div align="center">������λ����ַ</div>
							</th>
							<th>
								<div align="center">��λ�绰</div>
							</th>
							<th>
								<div align="center">���˵绰</div>
							</th>
						</tr>
						<tr>
							<td align="center">
								<html:text name="rs" property="jtcy1_xm" style="width:70px" styleId="jtcy1_xm" maxlength="10"/>
							</td>
							<td align="center">
								<html:select name="rs" property="jtcy1_gx" styleId="jtcy1_gx" style="width:80px">
									<html:option value="" />
									<html:options collection="jtgxList" labelProperty="mc" property="mc"/>
									</html:select>	
							</td>
							<td align="center">
								<html:text name="rs" property="jtcy1_gzdz" styleId="jtcy1_gzdz" maxlength="25" style="width:200px"/>
							</td>
							<td align="center">
								<html:text name="rs" property="jtcy1_lxdh2" style="width:110px"
									styleId="jtcy1_lxdh2" maxlength="20" onblur="checkPhone(this)"/>
							</td>
							<td align="center">
								<html:text name="rs" property="jtcy1_lxdh1" style="width:110px"
								styleId="jtcy1_lxdh1" onblur="checkPhone(this)" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<td align="center">
								<html:text name="rs" property="jtcy2_xm" style="width:70px" styleId="jtcy2_xm" maxlength="10"/>
							</td>
							<td align="center">
								<html:select name="rs" property="jtcy2_gx" styleId="jtcy2_gx" style="width:80px">
									<html:option value="" />
									<html:options collection="jtgxList" labelProperty="mc" property="mc"/>
									</html:select>	
							</td>
							<td align="center">
								<html:text name="rs" property="jtcy2_gzdz" styleId="jtcy2_gzdz" maxlength="25" style="width:200px"/>
							</td>
							<td align="center">
								<html:text name="rs" property="jtcy2_lxdh2" style="width:110px"
									styleId="jtcy2_lxdh2" maxlength="20" onblur="checkPhone(this)"/>
							</td>
							<td align="center">
								<html:text name="rs" property="jtcy2_lxdh1" style="width:110px"
								styleId="jtcy2_lxdh1" onblur="checkPhone(this)" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<td align="center">
								<html:text name="rs" property="jtcy3_xm" style="width:70px" styleId="jtcy3_xm" maxlength="10"/>
							</td>
							<td align="center">
								<html:select name="rs" property="jtcy3_gx" styleId="jtcy3_gx" style="width:80px">
									<html:option value="" />
									<html:options collection="jtgxList" labelProperty="mc" property="mc"/>
									</html:select>	
							</td>
							<td align="center">
								<html:text name="rs" property="jtcy3_gzdz" styleId="jtcy3_gzdz" maxlength="25" style="width:200px"/>
							</td>
							<td align="center">
								<html:text name="rs" property="jtcy3_lxdh2" style="width:110px"
									styleId="jtcy3_lxdh2" maxlength="20" onblur="checkPhone(this)"/>
							</td>
							<td align="center">
								<html:text name="rs" property="jtcy3_lxdh1" style="width:110px"
								styleId="jtcy3_lxdh1" onblur="checkPhone(this)" maxlength="20"/>
							</td>
						</tr>
					</tbody>
					
				</table>
				
				<table style="margin-bottom: 2px" width="100%" border="0" class="formlist" id="tab_qtxx">
					<!-- ѧ��������Ϣ begin-->
					<thead>
						<tr onclick="" style="cursor: hand;">
							<th colspan="5">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_qtxx">
						<tr>
							<th>
								��������
							</th>
							<td align="left">
								<html:select name="rs" property="yhdm" styleId="yhdm">
									<html:option value=""></html:option>
									<html:options collection="yhmcList" labelProperty="yhmc" property="yhdm"/>
								</html:select>
							</td>
							<th>
								���п���
							</th>
							<td colspan="2">
								<html:text name="rs" property="yhkh" maxlength="20" 
										   styleClass="text_nor" styleId="yhkh"
								onkeyup="value=value.replace(/[^0-9]/g,'') "></html:text>
							</td>
						</tr>
					
					
					<tr>
							<th width="13%">
								����ƴ��
							</th>
							<td width="30%">
								<html:text name="rs" property="xmpy" styleId="xmpy"
									maxlength="50" styleClass="text_nor"
									onkeyup="value=value.replace(/[^a-zA-Z]/g,'') " />
							</td>
							<th width="13%">
								������
							</th>
							<td align="left" colspan="2">
								<html:text name="rs" property="cym" styleId="cym"
									styleClass="text_nor" maxlength="16" />
							</td>

						</tr>
						<tr>
							<th>
								���(cm)
							</th>
							<td align="left">
								<html:text name="rs" property="sg" styleId="sg"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" maxlength="3" />
							</td>
							<th>
								����(kg)
							</th>
							<td colspan="2">
								<html:text name="rs" property="tz" styleId="tz"
									onkeyup="value=value.replace(/[^\d|.]/g,'') "
									styleClass="text_nor" maxlength="4" />
							</td>
						</tr>
						<tr>
						<th>
								�س�
							</th>
							<td>
								<html:text name="rs" property="tc" styleId="tc"
									styleClass="text_nor" maxlength="32" />
							</td>
							<th>
										����״��
									</th>
									<td colspan="2">
									<html:text name="rs" property="jkzk" styleId="jkzk"
											styleClass="text_nor" maxlength="30" />
									</td>
							</tr>
							<tr>
							<th>
								�������
							</th>
							<td colspan="">
								<html:select name='rs' property="pycc" styleId="pycc">
										<html:options collection="pyccList" property="dm"
											labelProperty="mc" />
									</html:select>
							</td>
							<th>
								�Ƿ��߶���
							</th>
							<td align="left" colspan="2">
								<html:select name='rs' property="sfzd" style="width:120px"
									styleId="sfzd">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
							<tr>
							<th>
								�������
							</th>
							<td>
							<html:select property="kslb" name="rs" styleId="kslb">
										<html:options collection="kslbList" property="dm"
											labelProperty="mc" />
									</html:select>
									</td>
									<th>
								��ѧ��ʽ
							</th>
							<td colspan="2">
								<html:select property="rxfs" name="rs" styleId="rxfs">
										<html:options collection="rxfsList" property="dm"
											labelProperty="mc" />
									</html:select>
							</td>
							</tr>
							<tr>
							<th>
								������ʽ
							</th>
							<td>
							<html:select property="pyfs" name="rs" styleId="pyfs">
										<html:options collection="pyfsList" property="dm"
											labelProperty="mc" />
									</html:select>
									</td>
									<th>
								
							</th>
							<td colspan="2">
								
							</td>
							</tr>
							<tr>
							<th>
								ѧϰ����<br /><font color="red">(����500������)</font>
							</th>
							<td colspan="4">
								<html:textarea name='rs' property='zd1' styleId="zd1" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)"/>
							</td>
							</tr>
							<tr>
							<th>
								�񽱾���<br /><font color="red">(����500������)</font>
							</th>
							<td colspan="4">
								<html:textarea name='rs' property='zd2' styleId="zd2" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)"/>
							</td>
							</tr>
							<tr>
							<th>
								��ע<br /><font color="red" >(����500������)</font>
							</th>
							<td colspan="4">
								<html:textarea name='rs' property='bz' styleId="bz" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)"/>
							</td>
							</tr>
					</tbody>
				</table>
				
				<logic:notEmpty name="shxxList">
					<table  style="margin-bottom: 2px" width="100%" border="0" class="formlist" id="tab_shxx">
					<logic:iterate name="shxxList" id="s">
					<tr  style="cursor:hand">
										<th width="13%">
										${s.gwmc}����û�
										</th>
										<td  width="30%">
											${s.shrxm}
										</td>
										
										<th width="13%">
										${s.gwmc}���״̬
										</th>
											<td colspan="2">
												<logic:equal name="s" property="shzt" value="tg">
												<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="wsh">
													<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="btg">
													<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="th">
													<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
												</logic:equal>
												<logic:equal name="s" property="shzt" value="xcs">
													<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
												</logic:equal>
											</td>
											</tr>
										<tr>
											<th width="13%">
										${s.gwmc}���ʱ��
										</th>
											<td  >
												${s.shsj}
											</td>
											
										<th width="13%">
										${s.gwmc}������
										</th>
										<td align="left"  colspan="2" style="word-break:break-all;width:100%">
												${s.shyj}
											</td>
										</tr>
					</logic:iterate>
					</table>
				</logic:notEmpty>
				
				<div style="height:15px"></div>
				<table width="100%" border="0" class="formlist" style="position:fixed;bottom:0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">"<span class="red">*</span>"Ϊ������  </div>
								<div class="btn">
									<logic:notEqual name="bcansfxs" value="">
										<button name="����" id="buttonSave" type="button">
											�� ��
										</button>
									</logic:notEqual>
									<logic:notEqual value="yes" name="xsxxxg">
									<button type="button"  name="�ر�" onclick="Close()" id="buttonClose">�� ��</button>
										<script type="text/javascript">
									         jQuery(".smooth").click(function(){
									             var href = jQuery(this).attr("href");
									             var pos = jQuery(href).offset().top;
									             jQuery("html,body").animate({scrollTop: pos-70}, 400);
									             return false;
									         });
										</script>
									</logic:notEqual>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				
			</div>
				<!-- �ϴ���Ƭ -->
			<div id="addPic" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ϴ���Ƭ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="file" id="stuPic" name="stuPic" style="width:90%"
										   onchange='uploadStuPic();closeWindown();'
									/>
									<br/>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>
	<!-- ��ʾ��Ϣ -->
	<%@ include file="/comm/other/tsxx.jsp"%>
</html:form>
</body>
</html>