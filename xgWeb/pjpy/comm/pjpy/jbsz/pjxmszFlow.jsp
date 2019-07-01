<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//保存基本设置
		function saveXmsz(){
<%--			changePjzq();--%>
			if($("xmdm").value==""){
				alert("项目代码不能为空!");
				return false;
			}
			if($("xmmc").value==""){
				alert("项目名称不能为空!");
				return false;
			}
			if($("xmlx").value==""){
				alert("项目类型不能为空!");
				return false;
			}
			if($("xmxz").value==""){
				alert("项目性质不能为空!");
				return false;
			}
			if($("xmfw").value==""){
				alert("项目范围不能为空!");
				return false;
			}
			
			
			var b=saveUpdateCheck();
			if(!b){return false;}
			refreshForm("/xgxt/pjpyXmsz.do?method=pjxmszOne&doType=save");
		}

		//保存基本设置
		function saveXmszFlow(){
<%--			changePjzq();--%>
			if($("xmdm").value==""){
				alert("项目代码不能为空!");
				return false;
			}
			if($("xmmc").value==""){
				alert("项目名称不能为空!");
				return false;
			}
			if($("xmlx").value==""){
				alert("项目类型不能为空!");
				return false;
			}
			if($("xmxz").value==""){
				alert("项目性质不能为空!");
				return false;
			}
			if($("xmfw").value==""){
				alert("项目范围不能为空!");
				return false;
			}
			
			
			var b=saveUpdateCheck();
			if(!b){return false;}
			var sfqy = $("save_sfqy").value;
			if(sfqy=="否"){
				refreshForm("/xgxt/pjpyXmsz.do?method=pjxmszOne&doType=save");
				window.close();
				window.dialogArguments.window.alertInfo("保存成功");
			}else{
			   confirmInfo("评奖项目设置已完成,此项设置不可返回,如果需要修改请到评奖项目设置中直接修改,是否要继续下一步的设置?",mbDownLoad);
			}
		}
		function mbDownLoad(tag){
			if(tag=='ok'){
				refreshForm("/xgxt/pjpyXmsz.do?method=pjxmszFlow&doType=save");
			}
		}
		
		//修改基本设置
		function updateXmsz(){
<%--			changePjzq();--%>
			if($("xmdm").value==""){
				alert("项目代码不能为空!");
				return false;
			}
			if($("xmmc").value==""){
				alert("项目名称不能为空!");
				return false;
			}
			if($("xmlx").value==""){
				alert("项目类型不能为空!");
				return false;
			}
			if($("xmxz").value==""){
				alert("项目性质不能为空!");
				return false;
			}
			if($("xmfw").value==""){
				alert("项目范围不能为空!");
				return false;
			}
			
			
			if($("save_sfsh_no").checked){
				$("save_lcid").value="";
			}
			
			var b=saveUpdateCheck();
			if(!b){return false;}
			refreshForm("/xgxt/pjpyXmsz.do?method=pjxmszUpdate&doType=save");
		}
		
		//保存修改验证--该方法只验证 人数设置和审核设置
		function saveUpdateCheck(){
			var b=false;
			if($('save_rssz_yes').checked){//人数设置
				var kzfw=document.getElementsByName('save_kzfw');
				for(var i=0;i<kzfw.length;i++){
					if(kzfw[i].checked){
						b=true;
						break;
					}
				}
				if(!b){
					alertInfo("控制范围必须选择！");
					return false;
				}
				
			}
			b=false;
			if($('save_sfsh_yes').checked){//是否审核
				var lcid=document.getElementsByName('lcid');
				for(var i=0;i<lcid.length;i++){
					if(lcid[i].checked){
						b=true;
						break;
					}
				}
				if(!b){
					alertInfo("审核流程必须选择！");
					return false;
				}
			}
			return true;;
		}
		
		
		
		//涉及金额
		function changeSjje(yesNo){
			$("xmjeTr").style.display=yesNo;
			var rownum = $("t_pjtjk_t").rows.length;
			if(yesNo==""){
				$("t_pjtjk_t").deleteRow(rownum-2);
			}
			if(yesNo=="none"){
				$("t_pjtjk_t").insertRow(rownum-2).setAttribute("height",23);
			}

			
		}
		
		function changeRssz(yesNo){
			//$("kzjbTr").style.display=yesNo;
			$("kzfwTr").style.display=yesNo;
			$("rssxTr").style.display=yesNo;
			var rownum = $("t_pjtjk_f").rows.length;
			if(""==yesNo){
				$("t_pjtjk_f").deleteRow(rownum-2);
				$("t_pjtjk_f").deleteRow(rownum-3);
			}
			if("none"==yesNo){
				$("t_pjtjk_f").insertRow(rownum-1).setAttribute("height",23);
				$("t_pjtjk_f").insertRow(rownum-2).setAttribute("height",23);
			}
		}
		
		function changeSfsh(yesNo){
			$("shlcTr").style.display=yesNo;
			if('none' == yesNo){
				var lcs = document.getElementsByName('save_lcid');
				lcs[0].checked = "checked";

				var rownum = $("t_pjtjk_s").rows.length;
				$("t_pjtjk_s").insertRow(rownum-1).setAttribute("height",23);
				$("t_pjtjk_s").insertRow(rownum-2).setAttribute("height",23);
				$("t_pjtjk_s").insertRow(rownum-3).setAttribute("height",23);
			}
			if(yesNo==""){
				var rownum = $("t_pjtjk_s").rows.length;
				$("t_pjtjk_s").deleteRow(rownum-2);
				$("t_pjtjk_s").deleteRow(rownum-3);
				$("t_pjtjk_s").deleteRow(rownum-4);
			}
			
			if('none' == yesNo){
				var lcs = document.getElementsByName('save_lcid');
				lcs[0].checked = "checked";
				$("save_lcid").value="";
			}else{
				if($("lcid_0")){
					$("save_lcid").value=$("lcid_0").value;
				}
			}
			//$("rsszTr").style.display=yesNo;
		}

		function init(){
			var sjje='${rs.save_sjje}';
			if(sjje=="是"){
				$("xmjeTr").style.display="";
			}
		
			var kzfw='${rs.save_kzfw}';
			var kzfw_rd=document.getElementsByName("save_kzfw");
			if(kzfw_rd){
				for(var i=0;i<kzfw_rd.length;i++){
					if(kzfw_rd[i].value==kzfw){
						kzfw_rd[i].checked="checked";
						break;
					}
				} 
			}
			var lcid='${rs.save_lcid}';
			var lcid_rd=document.getElementsByName("lcid");
			if(lcid_rd){
				for(var i=0;i<lcid_rd.length;i++){
					if(lcid_rd[i].value==lcid){
						lcid_rd[i].checked="checked";
						break;
					}
				} 
			}
			
			
			var rssz='${rs.save_rssz}';
			var sfsh='${rs.save_sfsh}';
			if(rssz=="是"){
				$("save_rssz_yes").checked="checked";
				$("kzfwTr").style.display="";
				$("rssxTr").style.display="";
			}
			if(sfsh=="是"){
				$("save_sfsh_yes").checked="checked";
				$("shlcTr").style.display="";
			}
		}
		
		function showTsDiv(id){
			if($(id)){
				$(id).style.display = "";
			}
		}
		
		function hiddTsDiv(id){
			if($(id)){
				$(id).style.display = "none";
			}
		}
		
<%--		function changePjzq(){--%>
<%--			var save_sqzq_xn=document.getElementById("save_sqzq_xn");--%>
<%--			var save_sqzq_xq=document.getElementById("save_sqzq_xq");--%>
<%--			var save_sqzq_nd=document.getElementById("save_sqzq_nd");--%>
<%--			if(save_sqzq_xn.checked){--%>
<%--				$("save_pjxq").value="无";--%>
<%--				$("save_pjnd").value="无";--%>
<%--			}else if(save_sqzq_xq.checked){--%>
<%--				$("save_pjxn").value=$("pjxn").value;--%>
<%--				$("save_pjxq").value=$("pjxq").value;--%>
<%--				$("save_pjnd").value="无";--%>
<%--			}else if(save_sqzq_nd.checked){--%>
<%--				$("save_pjxn").value=$("pjxn").value;--%>
<%--				$("save_pjnd").value=$("pjnd").value;--%>
<%--				$("save_pjxq").value="无";--%>
<%--			}--%>
<%--		}--%>

         //下一步的控制
          //第0步
         function pjtjsz0(){
        	 if($("xmdm").value==""){
 				alertInfo("项目代码不能为空!");
 				return false;
 			}
 			if($("xmmc").value==""){
 				alertInfo("项目名称不能为空!");
 				return false;
 			}
 			if($("xmlx").value==""){
 				alertInfo("项目类型不能为空!");
 				return false;
 			}
 			if($("xmxz").value==""){
 				alertInfo("项目性质不能为空!");
 				return false;
 			}
 			if($("xmfw").value==""){
 				alertInfo("项目范围不能为空!");
 				return false;
 			}
 			 document.getElementById("first_li").className='current';	
 			 document.getElementById("second_li").className = 'current-next';
 			 document.getElementById("third_li").className = 'current-next';	
 			 document.getElementById("forth_li").className = 'last';
        	 document.getElementById("pjtjk_o").style.display="";
        	 document.getElementById("pjtjk_t").style.display="none";
        	 document.getElementById("pjtjk_f").style.display="none";
        	 document.getElementById("pjtjk_s").style.display="none";
         }
         //第一步
         function pjtjsz1(){
             var flag = false;
        	 var url = "/xgxt/pjpyXmsz.do?method=xmdmExist";
        	 jQuery.ajax({
        		   type: "POST",
        		   async: false,
        		   url: url,
        		   data: "xmdm="+$("xmdm").value,
        		   success: function(msg){
  	        		 if(msg == "true"){
  							alertInfo("项目代码已存在,请重新填写！");
  							return false;
  	        		 }else{
                            flag = true;
  	  	             }
        		   }
        	     });
            if(flag){
	        	if($("xmdm").value==""){
	        		alertInfo("项目代码不能为空!");
	 				return false;
	 			}
	 			var b=checkIsChinese($("xmdm").value);
	 			if(!b){return false;}

	 			var c = checkIsEn($('ywmc').value);
	 			if(!c){return false;}
	 			
	 			if($("xmmc").value==""){
	 				alertInfo("项目名称不能为空!");
	 				return false;
	 			}
	 			if($("xmlx").value==""){
	 				alertInfo("项目类型不能为空!");
	 				return false;
	 			}
	 			if($("xmxz").value==""){
	 				alertInfo("项目性质不能为空!");
	 				return false;
	 			}
	 			if($("xmfw").value==""){
	 				alertInfo("项目范围不能为空!");
	 				return false;
	 			}
	 			 document.getElementById("first_li").className='done current-prev';	
	 			 document.getElementById("second_li").className = 'current';
	 			 document.getElementById("third_li").className = 'current-next';	
	 			 document.getElementById("forth_li").className = 'last';
	        	 document.getElementById("pjtjk_o").style.display="none";
	        	 document.getElementById("pjtjk_t").style.display="";
	        	 document.getElementById("pjtjk_f").style.display="none";
	        	 document.getElementById("pjtjk_s").style.display="none";
            }
         }
         //第二步
         function pjtjsz2(){
        	 document.getElementById("first_li").className='done';	
 			 document.getElementById("second_li").className = 'done current-prev';
 			 document.getElementById("third_li").className = 'current';	
 			 document.getElementById("forth_li").className = 'last';
        	 document.getElementById("pjtjk_o").style.display="none";
        	 document.getElementById("pjtjk_t").style.display="none";
        	 document.getElementById("pjtjk_f").style.display="";
        	 document.getElementById("pjtjk_s").style.display="none";
         }
        //第三步
         function pjtjsz3(){
        	 var b=saveUpdateCheck();
 			 if(!b){return false;}
 			 document.getElementById("first_li").className='done';	
			 document.getElementById("second_li").className = 'done';
			 document.getElementById("third_li").className = 'done current-prev';
			 document.getElementById("forth_li").className = 'current';
        	 document.getElementById("pjtjk_o").style.display="none";
        	 document.getElementById("pjtjk_t").style.display="none";
        	 document.getElementById("pjtjk_f").style.display="none";
        	 document.getElementById("pjtjk_s").style.display="";
         }

         function   checkIsChinese(str) 
         { 
                 //如果值为空，通过校验 
                 if   (str=="") 
                         return   true; 
                 var pattern = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
                 if   (!pattern.test(str)) 
                         return   true; 
                 else {
                         alertInfo("项目代码请不要输入中文汉字!");
                         $('xmdm').focus();
                         return   false; 
                 }
         }//~~~

         function checkIsEn(str)
         {
           var pattern = /[^a-zA-Z]/g;
           if   (!pattern.test(str)) 
               return   true; 
           else {
               alertInfo("英文名称请输入英文!!");
               return   false; 
       }
         }
		</script>
	</head>
	<body onload="init()" >
	<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优-基本设置-评奖项目设置 </a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		
		 <!-- 流程按钮控制 -->
		<div class="flow-steps">
		     <ol class="num3">
			    <li class="current" id="first_li" style ="width:23%"><span class="first">1. 项目信息设置</span></li>
			    <li class="current-next" id="second_li" style ="width:23%"><span>2. 项目相关设置</span></li>
			     <li class="current-next" id="third_li" style ="width:23%"><span>3. 项目人数设置</span></li>
			    <li class="last" id="forth_li" style ="width:23%"><span>4. 项目审核设置</span></li>
		    </ol>
      </div>
				
		 <!-- 提示信息 start-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。</br>
				<span id="div_help" style="display: none">
				评奖周期：评奖学年(${myForm.pjxn })&nbsp;&nbsp;评奖学期(${myForm.pjxqmc })&nbsp;&nbsp;评奖年度(${myForm.pjnd })</br>
				1.项目名称设置<font color="blue">评奖的奖项</font>。<br />
				2.奖学金的申请周期，与综测分及评奖条件<font color="blue">无关</font>。<br />
				3.只有奖学金<font color="blue">启用</font>时，才能对该奖学金进行下一步设置及学生进行对奖学金的申请。<br />
				4.<font color="blue">项目金额</font>是学生获得该奖学金时拿到奖励金额。<br />
				5.<font color="blue">人数上限</font>:全校总共能获得该奖学金的人数（人数设置中关联，影响设置方式）。<br />
				6.<font color="blue">控制级别</font>:奖学金审核时,哪一级别的人数限制起作用，如: （设置成<bean:message key="lable.xb" />，则在审核时，只控制<bean:message key="lable.xb" />获奖人数上限）。<br />
				7.<font color="blue">人数设置</font>:该奖学金是否要控制人数，在审核中起作用。<br />
				8.<font color="blue">审核流程设置</font>在：“系统维护-审批流程维护”模块下维护。
				</span>
			</p>
		</div>
		<!-- 提示信息 end-->				
		<!-- 标题 end-->
		<html:form action="/pjpyXmsz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab">
			<div style="display:none">
			<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>评奖项目设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								<font color="red">*</font>评奖学年
							</th>
							<td align="left" width="70%" colspan="3">
								<html:select property="pjxn" styleId="pjxn"  disabled="true" style="width: 150px">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								<html:hidden property="save_pjxn" styleId="save_pjxn"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>评奖学期
							</th>
							<td align="left" colspan="3">
								<html:select property="pjxq" styleId="pjxq" disabled="true" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								<html:hidden property="save_pjxq" styleId="save_pjxq" />
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>评奖年度
							</th>
							<td align="left" colspan="3">
								<html:select property="pjnd" styleId="pjnd"  disabled="true" style="width: 150px">
									<html:options collection="ndList" property="nd"
										labelProperty="nd" />
								</html:select>
								<html:hidden property="save_pjnd" styleId="save_pjnd"/>
							</td>
						</tr>
					</tbody>
					</table>
			    </div>
				<!-- 评奖时间设置 -->
				
				<div id="pjtjk_o" style="height:150px">
				<table class="formlist" border="0" align="center" style="width: 100%">
					<!-- 人数分配方式 -->
					<thead>
						<tr>
							<th colspan="4">
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>项目代码
							</th>
							<td>
								<logic:empty name="rs">
									<html:text property="save_xmdm" styleId="xmdm" maxlength="10" value=""  onchange="checkIsChinese(this.value)" onfocus="displayMsgDiv()" onblur="hideMsgDiv()"  />
								</logic:empty>
								<logic:equal name="doType" value="update">
									<input type="text" disabled="disabled" value="${rs.save_xmdm}"/>
									<html:hidden name="rs" property="save_xmdm" styleId="xmdm"/>
								</logic:equal>
								 <div id="msg_div" class="hide">
						              <div class="prompcon">
						                <p>同个评奖周期项目代码不能重复</p>
						              </div>
					              </div>
							</td>
							<th>
								显示顺序
							</th>
							<td>
								<logic:empty name="rs">
									<html:text property="save_xssx" styleId="xssx" maxlength="2" />
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_xssx" styleId="xssx" maxlength="2"/>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>项目名称
							</th>
							<td>
								<logic:empty name="rs">
									<html:text property="save_xmmc" styleId="xmmc" maxlength="25" />
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_xmmc" styleId="xmmc" maxlength="25"/>
								</logic:notEmpty>
							</td>
							<th>
								英文名称
							</th>
							<td>
								<logic:empty name="rs">
									<html:text property="save_ywmc" styleId="ywmc" maxlength="25" onchange="checkIsEn(this.value)" />
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_ywmc" styleId="ywmc" maxlength="25"/>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>项目类型
							</th>
							<td>
								<logic:empty name="rs">
									<html:select property="save_xmlx" styleId="xmlx" style="width: 150px">
											<html:option value=""></html:option>
											<html:options collection="xmlxList" property="en"
												labelProperty="cn" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:select name="rs" property="save_xmlx" styleId="xmlx" style="width: 150px">
											<html:option value=""></html:option>
											<html:options collection="xmlxList" property="en"
												labelProperty="cn" />
									</html:select>
								</logic:notEmpty>
							</td>
							<th>
								<font color="red">*</font>项目性质
							</th>
							<td>
								<logic:empty name="rs">
									<html:select property="save_xmxz" styleId="xmxz"
											style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="xmxzList" property="dm"
												labelProperty="mc" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:select name="rs" property="save_xmxz" styleId="xmxz"
											style="width:150px">
											<html:option value=""></html:option>
											<html:options collection="xmxzList" property="dm"
												labelProperty="mc" />
									</html:select>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>项目范围
							</th>
							<td colspan="3">
								<logic:empty name="rs">
								<html:select property="save_xmfw" styleId="xmfw"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xmfwList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:select name="rs" property="save_xmfw" styleId="xmfw"
										style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="xmfwList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								项目说明
							</th>
							<td colspan="3" height="92px">
								<logic:empty name="rs">
									<html:textarea rows="6" style="width:98%" property="save_xmsm" onblur="checkLen(this,500)"></html:textarea>
								</logic:empty>
								<logic:notEmpty	name="rs">
									<html:textarea rows="6" style="width:98%" name="rs" property="save_xmsm" onblur="checkLen(this,500)"></html:textarea>
								</logic:notEmpty>
							</td>
						</tr>
						<tr></tr>
					</tbody>
					<tfoot>
					   <tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
										<button type="button" onclick="pjtjsz1()" id="buttonSave">
											下一步
										</button>
									<button type="button" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					</table>
					</div>
					
					
					
					<!-- 评奖条件库 -->
					<div id="pjtjk_t" style="display:none;height:350px">
					<table class="formlist" border="0" align="center" style="width: 100%" id="t_pjtjk_t">
					<thead>
						<tr>
							<th colspan="4">
								<span>相关设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>申请方式
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<input type="radio" name="save_sqfs" id="save_sqfs" value="xssq" checked="checked"/>
									学生申请
									<input type="radio" name="save_sqfs" id="save_sqfs" value="lssb"/>
									老师上报
								</logic:empty>
								<logic:notEmpty name="rs">
										<html:radio name="rs" property="save_sqfs" styleId="save_sqfs" value="xssq"/>
										学生申请
										<html:radio name="rs"  property="save_sqfs" styleId="save_sqfs" value="lssb"/>
										老师上报
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>申请周期
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<input type="radio" name="save_sqzq" id="save_sqzq_xn" value="xn" checked="checked"/>
									学年
									<input type="radio" name="save_sqzq" id="save_sqzq_xq" value="xq"/>
									学期
									<input type="radio" name="save_sqzq" id="save_sqzq_nd" value="nd" />
									年度
								</logic:empty>
								<logic:notEmpty	name="rs">
									<html:radio name="rs" property="save_sqzq" styleId="save_sqzq_xn" value="xn"  />
									学年
									<html:radio name="rs" property="save_sqzq" styleId="save_sqzq_xq" value="xq" />
									学期
									<html:radio name="rs" property="save_sqzq" styleId="save_sqzq_nd" value="nd" />
									年度
<%--									onclick="changePjzq()"--%>
								</logic:notEmpty>
							</td>
						</tr>
						<tr  style="display: none;">
							<th>
								作为前置条件
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<input type="radio" name="save_qztj" id="save_qztj"  value="是"/>
									是
									<input type="radio" name="save_qztj" id="save_qztj"  value="否" checked="checked"/>
									否
								</logic:empty>
								<logic:notEmpty	name="rs">
										<html:radio name="rs" property="save_qztj" styleId="save_qztj" value="是" />
										是
										<html:radio name="rs" property="save_qztj" styleId="save_qztj" value="否"/>
										否
								</logic:notEmpty>
							</td>
						</tr>
						<tr style="display: none;">
							<th>
								时间控制
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<input type="radio" name="save_sjkz" id="save_sjkz" value="需要"/>
									需要
									<input type="radio" name="save_sjkz" id="save_sjkz" value="不需要" checked="checked"/>
									不需要
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:radio name="rs" property="save_sjkz" styleId="save_sjkz" value="需要"/>
									需要
									<html:radio name="rs" property="save_sjkz" styleId="save_sjkz" value="不需要"/>
									不需要
								</logic:notEmpty>
							</td>
						</tr>
						<tr>
							<th>
								是否启用
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<input type="radio" name="save_sfqy" id="save_sfqy" value="是" checked="checked"/>
									是
									<input type="radio" name="save_sfqy" id="save_sfqy" value="否" />
									否
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:radio name="rs" property="save_sfqy" styleId="save_sfqy" value="是"/>
									是
									<html:radio name="rs" property="save_sfqy" styleId="save_sfqy" value="否"/>
									否
								</logic:notEmpty>
							</td>
						</tr>
						<tr >
							<th>
								是否涉及金额
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<input type="hidden" name="save_sjje" id="save_sjje" value="否"/>
									<input type="radio" property="save_sjje" name="sjje" id="sjje_yes"  onclick="changeSjje('');$('save_sjje').value=this.value" value="是"/>
									是
									<input type="radio" property="save_sjje" name="sjje" id="sjje_no" checked="checked"  onclick="changeSjje('none');$('save_sjje').value=this.value" value="否"/> 
									否
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:radio name="rs" property="save_sjje" styleId="sjje_yes"  onclick="changeSjje('')" value="是"/>
									是
									<html:radio name="rs" property="save_sjje" styleId="sjje_no"  onclick="changeSjje('none')" value="否"/> 
									否
								</logic:notEmpty>
							</td>
						</tr>
						<tr id="xmjeTr" style="display:none">
							<th>
								项目金额
							</th>
							<td colspan="3">
								<logic:empty name="rs">
									<html:text property="save_xmje" styleId="xmje" value="" maxlength="10" />
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_xmje" styleId="xmje" maxlength="10"/>
								</logic:notEmpty>
							</td>
						</tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
					</tbody>
					<tfoot>
					   <tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
								        <button type="button" onclick="pjtjsz0()" id="buttonSave">
											上一步
										</button>
										<button type="button" onclick="pjtjsz2()" id="buttonSave">
											下一步
										</button>
									<button type="button" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					</table>
					</div>
					<div id="pjtjk_f" style="display:none;height:350px">
					<table class="formlist" border="0" align="center" id="t_pjtjk_f"
					style="width: 100%">
					<!-- 评奖条件库 -->
					<thead>
						<tr>
							<th colspan="4">
								<span>人数设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr id="rsszTr">
							<th>
								人数设置
							</th>
							<td >
								<input type="radio" name="save_rssz" id="save_rssz_yes" maxlength="5" onclick="changeRssz('')" value="是" checked="checked"/>
								是
								<input type="radio" name="save_rssz" id="save_rssz_no"  maxlength="5" onclick="changeRssz('none')" value="否" />
								否
							</td>
						</tr>
						<tr id="kzjbTr" style="display:none" >
							<th>
								控制级别
							</th>
							<td colspan="3">
								<html:radio property="save_kzjb" styleId="kzjb" value="xn"></html:radio>
								学年
								<html:radio property="save_kzjb" styleId="kzjb" value="xq"></html:radio>
								学期
								<html:radio property="save_kzjb" styleId="kzjb" value="nd"></html:radio>
								年度
							</td>
						</tr>
						<tr id="kzfwTr" style="">
							<th>
								控制范围
							</th>
							<td >
							<!----><html:radio property="save_kzfw" styleId="kzfw" value="xy" ></html:radio>
							
							    <!--<input type="radio" name="save_kzfw" id="save_kzfw_xy" maxlength="5"  value="xy" />-->
								<bean:message key="lable.xb" />
								
								<html:radio property="save_kzfw" styleId="kzfw" value="zy"></html:radio>
								
								<!-- <input type="radio" name="save_kzfw" id="save_kzfw" maxlength="5"  value="zy" />-->
								专业
								<html:radio property="save_kzfw" styleId="kzfw" value="bj"></html:radio>
								<!-- <input type="radio" name="save_kzfw" id="save_kzfw" maxlength="5"  value="by" />-->
								班级
								<html:radio property="save_kzfw" styleId="kzfw" value="nj" ></html:radio>
								<!-- <input type="radio" name="save_kzfw" id="save_kzfw" maxlength="5"  value="ny" />-->
								年级
							</td>
						</tr>
						<tr id="rssxTr" style="">
							<th>
								人数上限
							</th>
							<td >
								<logic:empty name="rs">
									<html:text property="save_rssx" onkeydown="return onlyNum(this,10)"
													onmousedown="return onlyNum(this,10)"/>
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_rssx" onkeydown="return onlyNum(this,10)"
													onmousedown="return onlyNum(this,10)"/>
								</logic:notEmpty>
							</td>
						</tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
					</tbody>	
					<tfoot>
					   <tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
								        <button type="button" onclick="pjtjsz1()" id="buttonSave">
											上一步
										</button>
										<button type="button" onclick="pjtjsz3()" id="buttonSave">
											下一步
										</button>
									<button type="button" onclick="winClose();" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					</table>
					</div>
					
					
			<!-- 评奖条件库 -->		
			<div id="pjtjk_s" style="display:none;height:350px">
				<table class="formlist" border="0" align="center" style="width: 100%" id="t_pjtjk_s">
					<thead>
						<tr>
							<th colspan="4">
								<span>审核设置</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								需要审核	
							</th>
							<td colspan="3">
								<input type="hidden" name="save_lcid" id="save_lcid" value=''/>
								<logic:empty name="rs">
									<input type="radio" name="save_sfsh" id="save_sfsh_yes" value="是"
										onclick="changeSfsh('')"/>
									需要
									<input type="radio" name="save_sfsh" id="save_sfsh_no" value="否"
										onclick="changeSfsh('none')" checked="checked"/>
									不需要
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:radio  name="rs" property="save_sfsh" styleId="save_sfsh_yes" value="是"
										onclick="changeSfsh('')"/>
									需要
									<html:radio  name="rs" property="save_sfsh" styleId="save_sfsh_no" value="否"
										onclick="changeSfsh('none')"/>
									不需要
								</logic:notEmpty>
							</td>
						</tr>
						<tr id="shlcTr" style="display:none">
							<th>
								审核流程
							</th>
							<td colspan="3">
								<logic:present name="lcList">
									<logic:iterate id="s" name="lcList" indexId="index">
										<logic:equal name="index" value="0">
											<input type="radio" name="lcid" id="lcid_${index}" checked="checked" onclick='$("save_lcid").value=this.value' value="${s.lcid}"/>${s.lcmc } : ${s.gzgw}<br/>
										</logic:equal>
										<logic:notEqual name="index" value="0">
											<input type="radio" name="lcid" id="lcid_${index}"  onclick='$("save_lcid").value=this.value' value="${s.lcid}"/>${s.lcmc } : ${s.gzgw}<br/>
										</logic:notEqual>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
					    <tr style="height: 23px"></tr>
					 	<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
						<tr style="height: 23px"></tr>
					</tbody>
					<tfoot>
					   <tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
								        <button type="button" onclick="pjtjsz2()" id="buttonSave">
											上一步
										</button>
										<button type="button" onclick="saveXmszFlow()" id="buttonSave">
											保存
										</button>
									<button type="button" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					</table>
					</div>
					<div>
					</div>
					
			</div>
		</html:form>
		
	</body>
</html>
