<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/jtqkdc/js/jtqkdc.js"></script>
		<script type="text/javascript" src="js/check.js"></script>

		<script type="text/javascript">
            function getJtzsr(){
				var jtrs = jQuery("input[name='jtrs']").val();
				var jtnzsr = jQuery("input[name='jtnzsr']").val();
				//var jtrjnsrsx = jQuery("#jtrjysrsx").val();
				jQuery("input[name='jtrjsr']").attr("readonly","readonly");
				if(""!=jtrs&&null!=jtrs&&""!=jtnzsr&&null!=jtnzsr){
					if('0'==jtnzsr||'0'==jtrs){
						jQuery("input[name='jtrjsr']").val('0');
						}
					else{
						var jtrjsr = parseFloat(jtnzsr)/parseFloat(jtrs);
						if(jtrjsr.toString().indexOf(".")!=-1){
							jQuery("input[name='jtrjsr']").val(jtrjsr.toFixed(1));
							
							}
						else{
							jQuery("input[name='jtrjsr']").val(jtrjsr);
						}
					}
                }
            }
			jQuery(function(){
				jQuery.ajaxSetup({
					async: false
				});
				//��������ѡ��
				loadJtcySelectOptions();
				//���ؼ�ͥ���������ѡ��
				loadMkxxSelectOptions();
				//���ؼ�ͥ�����radioѡ��
				loadMkxxRadioOptions();
                //���ؼ�ͥ�����Checkboxѡ��
                loadMkxxCheckboxOptions();


				//�Ƿ�ƶ���� ƶ���صȼ�����
				sfpkxldsz();
				//�Ƿ�м����м��������
                sfcj();
                //�Ƿ�������Ȼ�ֺ�
                zrzh();
                //Ƿծ���
                qzzh();
                //�Ƿ�¶����Ƿ���
				sfgeDq();
				jQuery.ajaxSetup({
					async: true
				});

				if (frameElement && frameElement.api){
					jQuery("#buttonClose").show();
				} else {
					jQuery("#buttonClose").hide();
				}
			});



			//��ӡ����
			function printJtqkdc(){
				var xh = jQuery("#xh").val();
                var url="xszz_jtqkdc.do?method=printJsp";

				if (null==xh || ""==xh){
					showAlertDivLayer("��ѡ����Ҫ�����ѧ����");
				} else {
					var url= url+"&xh="+xh;
					 window.open(url);
			    }
			}

			function getWord(type){
				//����֤ѧ��
				var xh = jQuery("#xh").val();
				
				if (jQuery.trim(xh) == ""){
					showAlertDivLayer("����ѡ��ѧ����");
					return false;
				}
				
				if (!checkMustNotNull()){
					showAlertDivLayer("�뽫��������д������");
					return false;
				}
				
				//��֤��ͥ��Ա������
				var btx = jQuery("input[name=btx]");
				var jtcyFlg = true;
				var jtcyNum = true;

				jQuery.each(btx,function(i,e){
					var name = jQuery(e).val();
					var inputList = jQuery("input[labName="+name+"]");
					var selectList = jQuery("select[labName="+name+"]");

					var inputNum = 0;
					jQuery.each(inputList,function(i,e){
						var textVal = jQuery(e).val();
						if (textVal != null && textVal != ''){
							inputNum++;
						}else{
							//��֤������ֵΪ�գ�ͬһ��������Ԫ���Ƿ��зǿ���
							var input = jQuery(e).parents("tr").eq(0).find("input").not(jQuery(e));
							var select = jQuery(e).parents("tr").eq(0).find("select");

							var textFlg = false;
							jQuery.each(input,function(i,e){
								var textValT = jQuery(e).val();
								if (textValT != null && textValT != ''){
									textFlg = true;
									return;
								}
							});
							
							var selectFlg = false;
							jQuery.each(select,function(i,e){
								var selectValT = jQuery(e).val();
								if (selectValT != null && selectValT != ''){
									selectFlg = true;
									return;
								}
							});
							
							if (textFlg || selectFlg){
								jtcyFlg = false;
								return;
							}
						}
					});
					
					var selectNum = 0;
					jQuery.each(selectList,function(i,e){
						var selectVal = jQuery(e).val();
						if (selectVal != null && selectVal != ''){
							selectNum++;
						}else{
							//��֤������ֵΪ�գ�ͬһ��������Ԫ���Ƿ��зǿ���
							var input = jQuery(e).parents("tr").eq(0).find("input");
							var select = jQuery(e).parents("tr").eq(0).find("select").not(jQuery(e));

							var textFlg = false;
							jQuery.each(input,function(i,e){
								var textValT = jQuery(e).val();
								if (textValT != null && textValT != ''){
									textFlg = true;
									return;
								}
							});
							
							var selectFlg = false;
							jQuery.each(select,function(i,e){
								var selectValT = jQuery(e).val();
								if (selectValT != null && selectValT != ''){
									selectFlg = true;
									return;
								}
							});
							
							if (textFlg || selectFlg){
								jtcyFlg = false;
								return ;
							}
						}
					});
					
					//��֤�Ƿ�ȫ�������Ϊ�գ�������һ�еı������Ϊ�ա���
					if (inputNum == 0 && selectNum == 0){
						jtcyNum = false;
						return ;
					}
				});
				
				
				if (!jtcyNum){
					showAlertDivLayer("��������дһ����ͥ��Ա��");
					return false;
				}
				
				if (!jtcyFlg){
					showAlertDivLayer("��ͥ��Ա�������Ϊ�գ�");
					return false;
				}

				var url = "xszz_jtqkdc.do?method=saveDcxx";
				ajaxSubFormWithFun("jtqkdcForm",url,function(data){
					if(data["message"] == "����ɹ���"){
						var url="";
						if(type == 'jtqkdc'){
							url="xszz_jtqkdc.do?method=getJtqkdcWord";
						}else if(type == 'fzghyzzsqb'){ // ��ӡ��չ�滮��������������ݴ�ѧ��
							url="xszz_jtqkdc.do?method=getFzghyzzsqbWord";
						}
						url= url+"&xh="+xh;
						 var w  = window.open(url,'_blank');
					     w.location.href = url;   
					     
					}
				});
				
			}
			

			/*ͨ�ü�ͥ��Ա��������*/
			function xsxxxg(){ 
				jQuery("#jtqkdcForm > div > table > thead:eq(1) > tr > th > span").append("<lable style='font-weight:normal'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ͥ��Ա����&nbsp;&nbsp;</lable>" +
				"<input type='text' name='checkjtrs'  id='checkjtrs' class='text_nor' onkeyup='checkInput(this)' style='width:35px;'>");
				var jtrs = 0;
				jQuery("#jtqkdc_jtcy > tbody:eq(0) > tr").each(function(e){
					if(jQuery(this).find("td:eq(0) > input").val() != ""){
						jtrs++;
					}
				});
				if(jtrs > 0){
					jQuery("#checkjtrs").val(jtrs);
				}
			}



			//�Ƿ�ƶ���� ƶ���صȼ�����
			function sfpkxldsz(){
				if(jQuery("input[name='sfpkx']")&&jQuery("select[name='pkxjb']")){

					var pkjjbsfbt = jQuery("select[name='pkxjb']").attr("sfbt");
					
					if(null == jQuery("input:radio[name='sfpkx']:checked").val()
							|| "" == jQuery("input:radio[name='sfpkx']:checked").val()
							|| "0" == jQuery("input:radio[name='sfpkx']:checked").val()){
						jQuery("th[name='th_pkxjb']").text("");
						jQuery("select[name='pkxjb']").val("");
						jQuery("select[name='pkxjb']").attr("sfbt","no");
						jQuery("select[name='pkxjb']").hide();
				    }
					var pkjjbth = "";
					if(pkjjbsfbt == "yes"){
						pkjjbth = "<font color='red'>*</font>";
					}
					pkjjbth += "ƶ���صȼ�";
					jQuery("input[name='sfpkx']").change(function() {
						if ("1" == jQuery(this).val()) {
							jQuery("th[name='th_pkxjb']").html(pkjjbth);
							jQuery("select[name='pkxjb']").show();
							jQuery("select[name='pkxjb']").attr("sfbt",pkjjbsfbt);
						}else{
							jQuery("th[name='th_pkxjb']").text("");
							jQuery("select[name='pkxjb']").val("");
							jQuery("select[name='pkxjb']").attr("sfbt","no");
							jQuery("select[name='pkxjb']").hide();
						}
					});
				}
			}


			//�Ƿ������������
			function sfdkldsz(){			
					if(jQuery("input:radio[name='ylzd10']:checked").length < 1 ||  "0" == jQuery("input:radio[name='ylzd10']:checked").val()){
						jQuery("th[name='th_ylzd11']").text("");
						jQuery("select[name='ylzd11']").val("");
						jQuery("select[name='ylzd11']").attr("sfbt","no");
						jQuery("select[name='ylzd11']").hide();
				    }
					jQuery("input[name='ylzd10']").change(function() {
						var ylzd11th = "<font color='red'>*</font>��������";
						if ("1" == jQuery(this).val()) {
							jQuery("th[name='th_ylzd11']").html(ylzd11th);
							jQuery("select[name='ylzd11']").show();
							jQuery("select[name='ylzd11']").attr("sfbt","yes");
						}else{
							jQuery("th[name='th_ylzd11']").text("");
							jQuery("select[name='ylzd11']").val("");
							jQuery("select[name='ylzd11']").attr("sfbt","no");
							jQuery("select[name='ylzd11']").hide();
						}
					});
				}

            //�Ƿ�м���������
            function sfcj(){
                if(jQuery("input:radio[name='ylzd7']:checked").length < 1 ||
					"0" == jQuery("input:radio[name='ylzd7']:checked").val()){
                    jQuery("th[name='th_ylzd4']").text("");
                    jQuery("select[name='ylzd4']").val("");
                    jQuery("select[name='ylzd4']").attr("sfbt","no");
                    jQuery("select[name='ylzd4']").hide();
                }
                if("1" == jQuery("input:radio[name='ylzd7']:checked").val()){
                    var html = "<font color='red'>*</font>�м�����";
                    jQuery("th[name='th_ylzd4']").html(html);
				}
                jQuery("input[name='ylzd7']").change(function() {
                    var html = "<font color='red'>*</font>�м�����";
                    if ("1" == jQuery(this).val()) {
                        jQuery("th[name='th_ylzd4']").html(html);
                        jQuery("select[name='ylzd4']").show();
                        jQuery("select[name='ylzd4']").attr("sfbt","yes");
                    }else{
                        jQuery("th[name='th_ylzd4']").text("");
                        jQuery("select[name='ylzd4']").val("");
                        jQuery("select[name='ylzd4']").attr("sfbt","no");
                        jQuery("select[name='ylzd4']").hide();
                    }
                });
            }

            //��ͥ������Ȼ�ֺ������������
            function zrzh(){
                if("1" == jQuery("input:radio[name='ylzd8']:checked").val()){
                    var html = "<font color='red'>*</font>��ͥ������Ȼ�ֺ����<br/><font color=\"red\">&lt;������100���ַ�&gt;</font>";
                    jQuery("th[name='th_jtszqk']").html(html);
                    jQuery("textarea[name='jtszqk']").attr("sfbt","yes");
                }
                jQuery("input[name='ylzd8']").change(function() {
                    var html = "<font color='red'>*</font>��ͥ������Ȼ�ֺ����<br/><font color=\"red\">&lt;������100���ַ�&gt;</font>";
                    if ("1" == jQuery(this).val()) {
                        jQuery("th[name='th_jtszqk']").html(html);
                        jQuery("textarea[name='jtszqk']").attr("sfbt","yes");
                    }else{
                        html = "��ͥ������Ȼ�ֺ����<br/><font color=\"red\">&lt;������100���ַ�&gt;</font>";
                        jQuery("th[name='th_jtszqk']").html(html);
                        jQuery("textarea[name='jtszqk']").attr("sfbt","no");
                    }
                });
            }
            //��ͥǷծ�����������
            function qzzh(){
                if("1" == jQuery("input:radio[name='sfqz']:checked").val()){
                    var html = "<font color='red'>*</font>��ͥǷծ���<br/><font color=\"red\">&lt;������100���ַ�&gt;</font>";
                    jQuery("th[name='th_jtqzqk']").html(html);
                    jQuery("textarea[name='jtqzqk']").attr("sfbt","yes");
                }
                jQuery("input[name='sfqz']").change(function() {
                    var html = "<font color='red'>*</font>��ͥǷծ���<br/><font color=\"red\">&lt;������100���ַ�&gt;</font>";
                    if ("1" == jQuery(this).val()) {
                        jQuery("th[name='th_jtqzqk']").html(html);
                        jQuery("textarea[name='jtqzqk']").attr("sfbt","yes");
                    }else{
                        html = "��ͥǷծ���<br/><font color=\"red\">&lt;������100���ַ�&gt;</font>";
                        jQuery("th[name='th_jtqzqk']").html(html);
                        jQuery("textarea[name='jtqzqk']").attr("sfbt","no");
                    }
                });
            }
			//�Ƿ�¶����Ƿ���
            function sfgeDq(){
                if("1" == jQuery("input:radio[name='sfdq']:checked").val()){
                    jQuery("input:radio[name='sfgc'][value='0']").attr("checked","checked");
                    jQuery("input:radio[name='sfgc'][value='1']").attr("disabled",true);
                }
                jQuery("input[name='sfdq']").change(function() {
                    if("1" == jQuery("input:radio[name='sfdq']:checked").val()){
                        jQuery("input:radio[name='sfgc'][value='0']").attr("checked","checked");
                        jQuery("input:radio[name='sfgc'][value='1']").attr("disabled",true);
                    }else{
                        jQuery("input:radio[name='sfgc'][value='1']").removeAttr("disabled");
					}
				});
			}
		</script>
	</head>
	<body>
		<input type="hidden" value="${xxdm}" id="xxdm"/>
		<input type="hidden" value="jtqkdc" id="flag"/>
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>--%>
		<html:form action="/xszz_jtqkdc" method="post" styleId="jtqkdcForm">
		   <input type="hidden" value="${jtrjysrsx }" id="jtrjysrsx"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/selectStudent.jsp" %>
					<thead>
					<tr>
						<th colspan="4">
							<span>��ͥͨѶ��Ϣ</span>
						</th>
					</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/jttxxxUpdate.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ͥ��Ա</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/jtcyUpdate.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ͥ���</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxUpdate.jsp" %>
					<thead>
					<tr>
						<th colspan="4">
							<span>Ӱ���ͥ����״���й���ϸ��Ϣ</span>
						</th>
					</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/yxjtjjqkUpdate.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>����</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th>
							<logic:present name="fjxx">
								<logic:equal value="yes" name="fjxx" property="sfbt">
									<font color="red">*</font>
								</logic:equal>								
							</logic:present>
							<logic:notEqual name="xxdm" value="12869">�ϴ���ͥ��������֤��</logic:notEqual>
						</th>
						<td colspan="3">
						<%--<logic:equal value="12866" name="xxdm">
							<span style="color: red; line-height:20px;display:block;">
								��ͥ��������֤��Ϊ�硢�򡢽ֵ��������������ų��ߵ�����֤����������Ч��
							</span>
							&nbsp;
						</logic:equal>--%>
						
							<span style="color: red; line-height:20px;display:block;">
								�������������ͱ������ϴ����֤�����ϡ�
							</span>
							<html:hidden property="ylzd3" styleId="fjid"/>
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									jQuery.MultiUploader({
										maxcount : 3,
										//��׺
										accept : 'png|gif|jpg|jpeg|doc|docx',
										//����ļ���С ��λM
										maxsize: 10,
										//��Ÿ������������id
										elementid : 'fjid'
										});
								});
								
								//���������Ƿ���֤������������ñ������ñ�����������true
								var fjbtsfyz = function(){
									return ${fjxx.sfbt == "yes"};
								}
								
							</script>
						</td>
					</tr>
				</table>
			</div>
			<div style="height: 35px;"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
								<logic:equal name="writeAble" value="yes">	
									<button type="button" type="button" onclick="saveForm();">
										�� ��
									</button>
										<button type="button" type="button" onclick="getWord('jtqkdc');return false;">
											���صǼǱ�
										</button>
								</logic:equal>
									<button type="button" onclick="Close();return false;" style="display: none;" id="buttonClose">�� ��</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</html:form>
	</body>
</html>

