<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">

		//初始化
		function onShow(){
			//初始化评奖项目设置
			defaultPjxmSetting();
			
			if($("xmmc")){
				$("xmmc").focus();
			}
		}

		//初始化评奖项目设置
		function defaultPjxmSetting(){
			
			jQuery.ajaxSetup({async:false});

			//步骤
			var step = jQuery("#step").val();
			//路径
			var url = "general_pjsz_pjxm_ajax.do?method=defaultPjxmSetting";
			//参数
		 	var parameter = {
				"step":step
			};
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery("#div_pjxm").load(
				url,
				parameter,
				function(){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
				}
			);
			
			jQuery.ajaxSetup({async:true});

			defaultHelpMessage();
		}

		//初始化帮助信息
		function defaultHelpMessage(){
			var step = jQuery("#step").val();
			
			if(step == "shlcsz"){//审核
				
				$("span_jbsz").style.display="none";
				$("span_shlc").style.display="";
				$("span_rssz").style.display="none";
				
			}else if(step == "rskzsz"){//人数
				$("span_jbsz").style.display="none";
				$("span_shlc").style.display="none";
				$("span_rssz").style.display="";
			}else{
				$("span_jbsz").style.display="";
				$("span_shlc").style.display="none";
				$("span_rssz").style.display="none";
			}
		}
		
		//上一步
		function previousStep(previousStep){
		
			if(previousStep == "shlcsz"){//从审核流程设置而来
				jQuery("#step").val("");
				defaultPjxmSetting();
				defaultXmInfo(); 
			}else if(previousStep == "rskzsz"){//从人数设置而来

				//判断是否需要审核
				var sfsh = jQuery("#hidden_sfsh").val();
				if(sfsh == "yes"){
					jQuery("#step").val("shlcsz");
				}else{
					jQuery("#step").val("");
				}
				
				defaultPjxmSetting();
				defaultXmInfo();
			}
			checkBtn();
		}
		
		//下一步
		function nextStep(nextStep){
			
			var step = jQuery("#step").val();
			//是否审核
			var sfsh = jQuery("input[name=sfsh]:checked").val();
			//人数设置
			var rssz = jQuery("input[name=rssz]:checked").val();
			//是否启用
			var sfqy = jQuery("input[name=sfqy]:checked").val();

			if(step == ""){//从评奖基本设置而来
				//项目名称
				var xmmc = jQuery("#xmmc").val();
				jQuery("#hidden_xmmc").val(xmmc);
				//项目类型
				var xmlx = jQuery("input[name=xmlx]:checked").val();
				jQuery("#hidden_xmlx").val(xmlx);
				//项目性质
				var xmxz = jQuery("#xmxz").val();
				jQuery("#hidden_xmxz").val(xmxz);
				//申请方式
				var sqfs = jQuery("input[name=sqfs]:checked").val();
				jQuery("#hidden_sqfs").val(sqfs);
				//项目金额
				var xmje = jQuery("#xmje").val();
				jQuery("#hidden_xmje").val(xmje);
				//显示顺序
				var xssx = jQuery("#xssx").val();
				jQuery("#hidden_xssx").val(xssx);
				//项目说明
				var xmsm = jQuery("#xmsm").val();
				jQuery("#hidden_xmsm").val(xmsm);
				//是否审核
				sfsh = jQuery("input[name=sfsh]:checked").val();
				jQuery("#hidden_sfsh").val(sfsh);
				//人数设置
				rssz = jQuery("input[name=rssz]:checked").val();
				jQuery("#hidden_rssz").val(rssz);
				//是否启用
				sfqy = jQuery("input[name=sfqy]:checked").val();
				jQuery("#hidden_sfqy").val(sfqy);
				
				jQuery("#step").val("pjjbsz");
			}

			if(nextStep == ""){
		
				if(sfsh == "yes" && rssz =="yes"){
					//审核流程设置
					jQuery("#step").val("shlcsz");
					defaultPjxmSetting();
					if($("div_shgw")){
						var lc_num = jQuery("input[type=radio][name=lcid]").length;
						if(lc_num !=0){
							var obj = jQuery("input[type=radio][name=lcid]")[0];
							obj.checked = true;
							clickShlc(obj.value);
						}
					}
				}else if(sfsh == "yes" && rssz == "no"){
					//审核流程设置
					jQuery("#step").val("shlcsz");
					defaultPjxmSetting();
					if($("div_shgw")){
						var lc_num = jQuery("input[type=radio][name=lcid]").length;
						if(lc_num !=0){
							var obj = jQuery("input[type=radio][name=lcid]")[0];
							obj.checked = true;
							clickShlc(obj.value);
						}
					}
					$("btn_bc").style.display="";
					$("btn_next").style.display="none";
				}else{
					jQuery("#step").val("rskzsz");
					jQuery("#hidden_rskz").val("sqsb");
					jQuery("#hidden_jdkz").val("sqsb");
					jQuery("#hidden_xmsy").val("sqsb");
					defaultPjxmSetting();
				}
			}else if (nextStep == "shlcsz"){
				//审核流程设置
				jQuery("#step").val("rskzsz");
				//流程ID
				var lcid = jQuery("input[name=lcid]:checked").val();
				var	rskz = jQuery("input[name=rskz]:checked").val();
				var	jdkz = jQuery("input[name=jdkz]:checked").val();
								
				if(lcid !="" && lcid!=null){
					jQuery("#hidden_lcid").val(lcid);
					jQuery("#hidden_jdkz").val(jdkz);
					jQuery("#hidden_rskz").val(rskz);
				}
				
				defaultPjxmSetting();
				defaultXmInfo();

				//特殊人群
				var tsrq = jQuery("#hidden_tsrq").val();
				var i=0;
				jQuery("input[name=tsrq]").each(function(){
					if(jQuery(this).val() == tsrq){
						jQuery(this).attr("checked",true);
					}
					i++;
				});	
			}		
		}

		//点击审核流程
		function clickShlc(lcid){

			var rssz = jQuery("#hidden_rssz").val();
			//路径
			var url = "general_pjsz_pjxm_ajax.do?method=defaultShlcGwxx";
			//参数
		 	var parameter = {
				"lcid":lcid,
				"rssz":rssz
			};
			
			if($("div_shgw")){
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery("#div_shgw").load(
					url,
					parameter,
					function(){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";

						//人数控制
						var rskz = jQuery("#hidden_rskz").val();
						if(rskz == ""){
							rskz = jQuery("input[name=rskz]:checked").val();
							jQuery("#hidden_rskz").val(rskz);
						}else{
							jQuery("input[name=rskz][value="+rskz+"]").attr("checked",true);
						}
						
						//兼得控制
						var jdkz = jQuery("#hidden_jdkz").val();
						if(jdkz == ""){
							jdkz = jQuery("input[name=jdkz]:checked").val();
							jQuery("#hidden_jdkz").val(jdkz);
						}else{
							jQuery("input[name=jdkz][value="+jdkz+"]").attr("checked",true);
						}
						
						//项目顺延
						var xmsy = jQuery("#hidden_xmsy").val();
						if(xmsy == ""){
							xmsy = jQuery("input[name=xmsy]:checked").val();
							jQuery("#hidden_xmsy").val(xmsy);
						}else{
							jQuery("input[name=xmsy][value="+xmsy+"]").attr("checked",true);
						}
					}
				);
			}
		}

		//初始化项目信息
		function defaultXmInfo(){

			jQuery.ajaxSetup({async:false});
			
			//项目名称
			var xmmc = jQuery("#hidden_xmmc").val();
			jQuery("#xmmc").val(xmmc);
			//项目类型
			var xmlx = jQuery("#hidden_xmlx").val();
			jQuery("input[name=xmlx][value="+xmlx+"]").attr("checked",true);
			//项目性质
			var xmxz = jQuery("#hidden_xmxz").val();
			jQuery("#xmxz").val(xmxz);
			//申请方式
			var sqfs = jQuery("#hidden_sqfs").val();
			jQuery("input[name=sqfs][value="+sqfs+"]").attr("checked",true);
			//项目金额
			var xmje = jQuery("#hidden_xmje").val();
			jQuery("#xmje").val(xmje);
			//显示顺序
			var xssx = jQuery("#hidden_xssx").val();
			jQuery("#xssx").val(xssx);
			//项目说明
			var xmsm = jQuery("#hidden_xmsm").val();
			jQuery("#xmsm").val(xmsm);
			//是否审核
			var sfsh = jQuery("#hidden_sfsh").val();
			jQuery("input[name=sfsh][value="+sfsh+"]").attr("checked",true);
			//人数设置
			var rssz = jQuery("#hidden_rssz").val();
			jQuery("input[name=rssz][value="+rssz+"]").attr("checked",true);
			//是否启用
			var sfqy = jQuery("#hidden_sfqy").val();
			jQuery("input[name=sfqy][value="+sfqy+"]").attr("checked",true);
			//流程ID
			var lcid = jQuery("#hidden_lcid").val();
			jQuery("input[name=lcid][value="+lcid+"]").attr("checked",true);
			clickShlc(lcid);
			//控制范围
			var kzfw = jQuery("#hidden_kzfw").val();
			jQuery("input[name=kzfw][value="+kzfw+"]").attr("checked",true);

			jQuery.ajaxSetup({async:true});
		}

		//检测保存评奖项目
		function checkSavePjxm(){
			
			//流程ID
			var lcid = jQuery("input[name=lcid]:checked").val();
			var	rskz = jQuery("input[name=rskz]:checked").val();
			var	jdkz = jQuery("input[name=jdkz]:checked").val();
							
			if(lcid !="" && lcid!=null){
				jQuery("#hidden_lcid").val(lcid);
				jQuery("#hidden_jdkz").val(jdkz);
				jQuery("#hidden_rskz").val(rskz);
			}
			
			confirmInfo('将要执行保存操作，请您确认',savePjxm);
		}
		
		//保存评奖项目
		function savePjxm(tag){
			
			copyValue();
			
			if(tag == "ok"){
				//项目名称
				var xmmc = jQuery("#hidden_xmmc").val().trim();
				//项目类型
				var xmlx = jQuery("#hidden_xmlx").val();
				//项目性质
				var xmxz = jQuery("#hidden_xmxz").val();
				//申请方式
				var sqfs = jQuery("#hidden_sqfs").val();
				//项目金额
				var xmje = jQuery("#hidden_xmje").val();
				//显示顺序
				var xssx = jQuery("#hidden_xssx").val();
				//项目说明
				var xmsm = jQuery("#hidden_xmsm").val();
				//是否审核
				var sfsh = jQuery("#hidden_sfsh").val();
				//人数设置
				var rssz = jQuery("#hidden_rssz").val();
				//是否启用
				var sfqy = jQuery("#hidden_sfqy").val();
				//流程ID
				var lcid = jQuery("#hidden_lcid").val();
				//控制范围
				var kzfw = jQuery("#hidden_kzfw").val();
				//人数控制
				var rskz = jQuery("#hidden_rskz").val();
				//兼得控制
				var jdkz = jQuery("#hidden_jdkz").val();
				//项目顺延
				var xmsy = jQuery("#hidden_xmsy").val();

				jQuery.ajaxSetup({async:false});
				
				var url = "general_pjsz_pjxm_ajax.do?method=savePjxm";
	
			 	//创建一个json对象
				var parameter={};
				
				//指定获取的控件类型，进行循环
				var hid_obj=jQuery("input",jQuery("#div_submit_value")).each(function(){
					
					//获取表单控件name
					var name=jQuery(this).attr("name");
					//构建json对象
					parameter[name]=escape(jQuery(this).val());
				});
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}

		//检测项目名称
		function checkXmmc(xmmcTwo){
			var xmmc = xmmcTwo.trim();
			
			if(xmmc!=""){
				jQuery.ajaxSetup({async:false});
				
				var url = "general_pjsz_pjxm_ajax.do?method=checkXmmc";
	
			 	//创建一个json对象
				var parameter={};
				parameter["str_xmmc"]=escape(xmmc);
				
				jQuery.post(url,
					parameter,
					function(result){
						if(result == ""){
							$("btn_bc").disabled=false;
							$("btn_next").disabled=false;
							hideMsgDiv('input_mhcx_msg');
						}else{
							$("btn_bc").disabled=true;
							$("btn_next").disabled=true;
							displayMsgDiv('input_mhcx_msg');
						}
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}else{
				//是否审核
				var sfsh = jQuery("input[name=sfsh]:checked").val();
				//人数设置
				var rssz = jQuery("input[name=rssz]:checked").val();

				if(sfsh == "yes" && rssz == "yes"){
					$("btn_next").disabled=true;
				}
			}
		}
		
		//检测按钮
		function checkBtn(){
			
			//是否审核
			var sfsh = jQuery("input[name=sfsh]:checked").val();
			//人数设置
			var rssz = jQuery("input[name=rssz]:checked").val();
			
			if(sfsh == "yes" && rssz == "yes"){
				if($("btn_next")){
					$("btn_next").disabled=false;
				}
			}else if(sfsh == "no" && rssz == "no"){
				if($("btn_bc")){
					$("btn_bc").style.display="";
					$("btn_bc").disabled=false;
				}
				if($("btn_next")){
					$("btn_next").style.display="none";
				}
				
			}else{
				if($("btn_bc")){
					$("btn_bc").style.display="none";
				}
				if($("btn_next")){
					$("btn_next").style.display="";
					$("btn_next").disabled=false;
				}
			}
		}
		
		function copyValue(){
			
			var step = jQuery("#step").val();
			//是否审核
			var sfsh = jQuery("input[name=sfsh]:checked").val();
			//人数设置
			var rssz = jQuery("input[name=rssz]:checked").val();
			//是否启用
			var sfqy = jQuery("input[name=sfqy]:checked").val();

			if(step == ""){//从评奖基本设置而来
				//项目名称
				var xmmc = jQuery("#xmmc").val();
				jQuery("#hidden_xmmc").val(xmmc);
				//项目类型
				var xmlx = jQuery("input[name=xmlx]:checked").val();
				jQuery("#hidden_xmlx").val(xmlx);
				//项目性质
				var xmxz = jQuery("#xmxz").val();
				jQuery("#hidden_xmxz").val(xmxz);
				//申请方式
				var sqfs = jQuery("input[name=sqfs]:checked").val();
				jQuery("#hidden_sqfs").val(sqfs);
				//项目金额
				var xmje = jQuery("#xmje").val();
				jQuery("#hidden_xmje").val(xmje);
				//显示顺序
				var xssx = jQuery("#xssx").val();
				jQuery("#hidden_xssx").val(xssx);
				//项目说明
				var xmsm = jQuery("#xmsm").val();
				jQuery("#hidden_xmsm").val(xmsm);
				//是否审核
				sfsh = jQuery("input[name=sfsh]:checked").val();
				jQuery("#hidden_sfsh").val(sfsh);
				//人数设置
				rssz = jQuery("input[name=rssz]:checked").val();
				jQuery("#hidden_rssz").val(rssz);
				//是否启用
				sfqy = jQuery("input[name=sfqy]:checked").val();
				jQuery("#hidden_sfqy").val(sfqy);
				
				jQuery("#step").val("pjjbsz");
			}
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优 - 基本设置 - 项目设置</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>			
		<!-- 标题 end-->
		<!-- 提示信息 START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
					<span id="span_jbsz" style="display: none">
					1.项目名称<font color="blue">必须录入</font>，且<font color="blue">不能重复</font>。<br/>
					2.<font color="blue">学生申请</font>只能由学生用户进行申请，<font color="blue">老师上报</font>则只能由非学生用户进行上报。<br/>
					3.项目性质如果为空，请前往<font color="blue">代码维护 - 评奖评优</font>进行维护。<br/>
					4.如果某些项目<font color="blue">不涉及金钱</font>，项目金额可以不进行维护。<br/>
					5.如果该项目设置为<font color="blue">无需审核</font>，则只要有人申请，就自动获得该项目。<br/>
					6.如果该项目设置<font color="blue">需要人数控制</font>，但未做人数设置，则学生无法被审核通过该项目。<br/>
					</span>
					
					<span id="span_shlc" style="display: none">
					1.审核流程如果为空，请前往<font color="blue">系统维护 - 审批流程维护 - 审批流程</font>进行维护。<br/>
					2.条件控制必须在项目的<font color="blue">申请阶段</font>进行控制，不满足条件的学生无法进行申请。<br/>
					3.人数控制是指控制该项目的获得人数上限，系统默认在<font color="blue">最后一级审核</font>进行控制。<br/>
					4.兼得控制是指该项目与哪些项目不可兼得，系统默认在<font color="blue">最后一级审核</font>进行控制。<br/>
					5.人数控制和兼得控制如果都设置到<font color="blue">申请阶段</font>控制，可能造成申请<font color="blue">不公平</font>现象，请注意。<br/>
					6.该项目的具体人数上限和不可兼得项目，请<font color="blue">完成项目设置后</font>，在管理界面进入<font color="blue">相应的功能</font>进行设置。<br/>
					</span>
					
					<span id="span_rssz" style="display: none">
					1.人数控制范围是指该项目的人数上限控制的为班级人数还是专业人数等，默认为<font color="blue">班级人数</font>。<br/>
					2.特殊人群指该项目人数设置的<font color="blue">基数</font>是否有特殊要求，比如<font color="blue">毕业生，困难生</font>等。<br/>
					3.特殊人群默认为<font color="blue">无</font>，如果您有特殊的要求，请联系<font color="blue">管理员</font>。<br/>
					4.设置完成后，请在管理页面勾选该项目，点击<font color="blue">人数设置</font>，对该项目进行<font color="blue">具体人数上限</font>。<br/>
					</span>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div id="div_submit_value">
				<input type="hidden" name="str_xmmc" id="hidden_xmmc"/>
				<input type="hidden" name="str_xmlx" id="hidden_xmlx"/>
				<input type="hidden" name="str_xmxz" id="hidden_xmxz"/>
				<input type="hidden" name="str_sqfs" id="hidden_sqfs"/>
				<input type="hidden" name="str_xmje" id="hidden_xmje"/>
				<input type="hidden" name="str_xssx" id="hidden_xssx"/>
				<input type="hidden" name="str_sfsh" id="hidden_sfsh"/>
				<input type="hidden" name="str_rssz" id="hidden_rssz"/>
				<input type="hidden" name="str_sfqy" id="hidden_sfqy" value="yes"/>
				<input type="hidden" name="str_xmsm" id="hidden_xmsm"/>
				<input type="hidden" name="str_lcid" id="hidden_lcid"/>
				<input type="hidden" name="str_rskz" id="hidden_rskz"/>
				<input type="hidden" name="str_jdkz" id="hidden_jdkz"/>
				<input type="hidden" name="str_xmsy" id="hidden_xmsy"/>
				<input type="hidden" name="str_tsrq" id="hidden_tsrq" value="no"/>
			
				<logic:equal name="useCpz" value="yes">
					<input type="hidden" name="str_kzfw" id="hidden_kzfw" value="cpz"/>
				</logic:equal>
				<logic:equal name="useCpz" value="no">
					<input type="hidden" name="str_kzfw" id="hidden_kzfw" value="bj"/>
				</logic:equal>
			</div>
			<input type="hidden" id="step" value=""/>
			
			<!-- 评奖项目设置DIV -->
			<div id="div_pjxm" style="width:100%;height:370px;overflow-x:hidden;overflow-y:auto;">
			
			</div>
			
			<div id="input_mhcx_msg" class="hide"
				style="left: 160px;top: 85px;">
				<div class="prompcon" style="width: 250px">
					<p id="tsxx_span">该项目名称<font color="blue">已存在</font>，请录入其他名称，否则系统无法识别哦~^_^||~</p>
				</div>
			</div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>