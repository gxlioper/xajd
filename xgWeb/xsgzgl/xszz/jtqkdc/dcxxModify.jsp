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
				//加载下拉选项
				loadJtcySelectOptions();
				//加载家庭情况中下拉选项
				loadMkxxSelectOptions();
				//加载家庭情况中radio选项
				loadMkxxRadioOptions();
                //加载家庭情况中Checkbox选项
                loadMkxxCheckboxOptions();


				//是否贫困县 贫困县等级联动
				sfpkxldsz();
				//是否残疾、残疾类别联动
                sfcj();
                //是否遭受自然灾害
                zrzh();
                //欠债情况
                qzzh();
                //是否孤儿、是否单亲
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



			//打印报表
			function printJtqkdc(){
				var xh = jQuery("#xh").val();
                var url="xszz_jtqkdc.do?method=printJsp";

				if (null==xh || ""==xh){
					showAlertDivLayer("请选择需要申请的学生！");
				} else {
					var url= url+"&xh="+xh;
					 window.open(url);
			    }
			}

			function getWord(type){
				//先验证学号
				var xh = jQuery("#xh").val();
				
				if (jQuery.trim(xh) == ""){
					showAlertDivLayer("请先选择学生！");
					return false;
				}
				
				if (!checkMustNotNull()){
					showAlertDivLayer("请将必填项填写完整！");
					return false;
				}
				
				//验证家庭成员必填项
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
							//验证必填项值为空，同一行中其它元素是否有非空项
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
							//验证必填项值为空，同一行中其它元素是否有非空项
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
					
					//验证是否全部必填项都为空（至少有一行的必填项不能为空。）
					if (inputNum == 0 && selectNum == 0){
						jtcyNum = false;
						return ;
					}
				});
				
				
				if (!jtcyNum){
					showAlertDivLayer("请至少填写一名家庭成员！");
					return false;
				}
				
				if (!jtcyFlg){
					showAlertDivLayer("家庭成员必填项不能为空！");
					return false;
				}

				var url = "xszz_jtqkdc.do?method=saveDcxx";
				ajaxSubFormWithFun("jtqkdcForm",url,function(data){
					if(data["message"] == "保存成功！"){
						var url="";
						if(type == 'jtqkdc'){
							url="xszz_jtqkdc.do?method=getJtqkdcWord";
						}else if(type == 'fzghyzzsqb'){ // 打印发展规划与资助申请表（温州大学）
							url="xszz_jtqkdc.do?method=getFzghyzzsqbWord";
						}
						url= url+"&xh="+xh;
						 var w  = window.open(url,'_blank');
					     w.location.href = url;   
					     
					}
				});
				
			}
			

			/*通用家庭成员人数计算*/
			function xsxxxg(){ 
				jQuery("#jtqkdcForm > div > table > thead:eq(1) > tr > th > span").append("<lable style='font-weight:normal'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;家庭成员人数&nbsp;&nbsp;</lable>" +
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



			//是否贫困县 贫困县等级联动
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
					pkjjbth += "贫困县等级";
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


			//是否贷款联动设置
			function sfdkldsz(){			
					if(jQuery("input:radio[name='ylzd10']:checked").length < 1 ||  "0" == jQuery("input:radio[name='ylzd10']:checked").val()){
						jQuery("th[name='th_ylzd11']").text("");
						jQuery("select[name='ylzd11']").val("");
						jQuery("select[name='ylzd11']").attr("sfbt","no");
						jQuery("select[name='ylzd11']").hide();
				    }
					jQuery("input[name='ylzd10']").change(function() {
						var ylzd11th = "<font color='red'>*</font>贷款类型";
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

            //是否残疾联动设置
            function sfcj(){
                if(jQuery("input:radio[name='ylzd7']:checked").length < 1 ||
					"0" == jQuery("input:radio[name='ylzd7']:checked").val()){
                    jQuery("th[name='th_ylzd4']").text("");
                    jQuery("select[name='ylzd4']").val("");
                    jQuery("select[name='ylzd4']").attr("sfbt","no");
                    jQuery("select[name='ylzd4']").hide();
                }
                if("1" == jQuery("input:radio[name='ylzd7']:checked").val()){
                    var html = "<font color='red'>*</font>残疾类型";
                    jQuery("th[name='th_ylzd4']").html(html);
				}
                jQuery("input[name='ylzd7']").change(function() {
                    var html = "<font color='red'>*</font>残疾类型";
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

            //家庭遭受自然灾害情况联动设置
            function zrzh(){
                if("1" == jQuery("input:radio[name='ylzd8']:checked").val()){
                    var html = "<font color='red'>*</font>家庭遭受自然灾害情况<br/><font color=\"red\">&lt;不超过100个字符&gt;</font>";
                    jQuery("th[name='th_jtszqk']").html(html);
                    jQuery("textarea[name='jtszqk']").attr("sfbt","yes");
                }
                jQuery("input[name='ylzd8']").change(function() {
                    var html = "<font color='red'>*</font>家庭遭受自然灾害情况<br/><font color=\"red\">&lt;不超过100个字符&gt;</font>";
                    if ("1" == jQuery(this).val()) {
                        jQuery("th[name='th_jtszqk']").html(html);
                        jQuery("textarea[name='jtszqk']").attr("sfbt","yes");
                    }else{
                        html = "家庭遭受自然灾害情况<br/><font color=\"red\">&lt;不超过100个字符&gt;</font>";
                        jQuery("th[name='th_jtszqk']").html(html);
                        jQuery("textarea[name='jtszqk']").attr("sfbt","no");
                    }
                });
            }
            //家庭欠债情况联动设置
            function qzzh(){
                if("1" == jQuery("input:radio[name='sfqz']:checked").val()){
                    var html = "<font color='red'>*</font>家庭欠债情况<br/><font color=\"red\">&lt;不超过100个字符&gt;</font>";
                    jQuery("th[name='th_jtqzqk']").html(html);
                    jQuery("textarea[name='jtqzqk']").attr("sfbt","yes");
                }
                jQuery("input[name='sfqz']").change(function() {
                    var html = "<font color='red'>*</font>家庭欠债情况<br/><font color=\"red\">&lt;不超过100个字符&gt;</font>";
                    if ("1" == jQuery(this).val()) {
                        jQuery("th[name='th_jtqzqk']").html(html);
                        jQuery("textarea[name='jtqzqk']").attr("sfbt","yes");
                    }else{
                        html = "家庭欠债情况<br/><font color=\"red\">&lt;不超过100个字符&gt;</font>";
                        jQuery("th[name='th_jtqzqk']").html(html);
                        jQuery("textarea[name='jtqzqk']").attr("sfbt","no");
                    }
                });
            }
			//是否孤儿、是否单亲
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
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>--%>
		<html:form action="/xszz_jtqkdc" method="post" styleId="jtqkdcForm">
		   <input type="hidden" value="${jtrjysrsx }" id="jtrjysrsx"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/selectStudent.jsp" %>
					<thead>
					<tr>
						<th colspan="4">
							<span>家庭通讯信息</span>
						</th>
					</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/jttxxxUpdate.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>家庭成员</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/jtcyUpdate.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>家庭情况</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/mkxxUpdate.jsp" %>
					<thead>
					<tr>
						<th colspan="4">
							<span>影响家庭经济状况有关详细信息</span>
						</th>
					</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/yxjtjjqkUpdate.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>附件</span>
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
							<logic:notEqual name="xxdm" value="12869">上传家庭经济困难证明</logic:notEqual>
						</th>
						<td colspan="3">
						<%--<logic:equal value="12866" name="xxdm">
							<span style="color: red; line-height:20px;display:block;">
								家庭经济困难证明为乡、镇、街道及以上民政部门出具的困难证明，当年有效。
							</span>
							&nbsp;
						</logic:equal>--%>
						
							<span style="color: red; line-height:20px;display:block;">
								建档立卡户、低保户等上传相关证明材料。
							</span>
							<html:hidden property="ylzd3" styleId="fjid"/>
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									jQuery.MultiUploader({
										maxcount : 3,
										//后缀
										accept : 'png|gif|jpg|jpeg|doc|docx',
										//最大文件大小 单位M
										maxsize: 10,
										//存放附件的隐藏域的id
										elementid : 'fjid'
										});
								});
								
								//附件必填是否验证函数，如果配置表中配置必填则函数返回true
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
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
								<logic:equal name="writeAble" value="yes">	
									<button type="button" type="button" onclick="saveForm();">
										保 存
									</button>
										<button type="button" type="button" onclick="getWord('jtqkdc');return false;">
											下载登记表
										</button>
								</logic:equal>
									<button type="button" onclick="Close();return false;" style="display: none;" id="buttonClose">关 闭</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
			</table>
		</html:form>
	</body>
</html>

