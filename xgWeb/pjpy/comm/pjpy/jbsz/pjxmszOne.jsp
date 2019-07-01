<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
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
					alert("控制范围必须选择！");
					return false;
				}
				if($('save_rssx').value.trim()==""){
					//alert("人数上限必须设置！");
					//return false;
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
					alert("审核流程必须选择！");
					return false;
				}
			}
			return true;;
		}
		
		
		
		//涉及金额
		function changeSjje(yesNo){
			$("xmjeTr").style.display=yesNo;
			
			if(yesNo == "none"){
				if($("xmje")){
					$("xmje").value= "";
				}
			}
		}
		
		function changeRssz(yesNo){
			//$("kzjbTr").style.display=yesNo;
			$("kzfwTr").style.display=yesNo;
			$("rssxTr").style.display=yesNo;
		}
		
		function changeSfsh(yesNo){
		
			$("shlcTr").style.display=yesNo;
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
			if($("xmjeTr")){
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


		function xmxxEdit(id){
			$(id).style.display = "";
			$('xmdm_td_text').style.display = "";
			$('xmdm_td_bean').style.display = "none";
			$('xmlx_td').style.display = "";
			$('xmlx_td_span').style.display = "none";
			getValue;
		}
		
		function getValue(){
			
			var xmlx=document.getElementsByName("xmlx");
			for(var i=0;i<3;i++){
				if($("xmlx") && $("xmlx").options[i].value==$("xmlx").value){
					if($("xmlx_span")){
						$("xmlx_span").innerHTML =$("xmlx").options[i].text;
					}
					break;
				}
			}
			
		}
		</script>
	</head>
	<body onload="init();getValue()">
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- 标题 end-->

		<html:form action="/pjpyXmsz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<logic:notEmpty name="rs">
			<div class="tab">
				<!-- 评奖时间设置 -->
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr onclick="showHiddenNr('tb_pjxmsz');" style="cursor: hand">
							<th colspan="4">
								<span>评奖项目设置</span>
							</th>
						</tr>
					</thead>
					<tbody id = "tb_pjxmsz">
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
					<!-- 人数分配方式 -->
					<thead>
						<tr onclick="">
							<th colspan="4">
								<span><a href="#" onclick="showHiddenNr('tb_xmxx')">项目信息</a></span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    <!--  <a href="#" onclick="xmxxEdit('tb_xmxx')">编辑</a>-->
							</th>
						</tr>
					</thead>
					<tbody id="tb_xmxx">
						<tr>
							<th>
								<font color="red">*</font>项目代码
							</th>
							<td id="xmdm_td_text" style="display:none">
								<logic:empty name="rs">
									<html:text property="save_xmdm" styleId="xmdm" maxlength="10" value="" />
								</logic:empty>
								<logic:equal name="doType" value="update">
									<input type="text" disabled="disabled" value="${rs.save_xmdm}"/>
									<html:hidden name="rs" property="save_xmdm" styleId="xmdm"/>
								</logic:equal>
							</td>
							<td id="xmdm_td_bean">
							     <logic:equal name="doType" value="update">
							       <bean:write name="rs" property="xmdm"/>
							     </logic:equal>
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
									<html:text property="save_ywmc" styleId="ywmc" maxlength="25" />
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
							<td id = "xmlx_td"  style="display:none">
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
							<td id="xmlx_td_span"><span  id="xmlx_span"></span></td>
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
							<td colspan="3">
								<logic:empty name="rs">
									<html:textarea rows="6" style="width:98%" property="save_xmsm"></html:textarea>
								</logic:empty>
								<logic:notEmpty	name="rs">
									<html:textarea rows="6" style="width:98%" name="rs" property="save_xmsm"></html:textarea>
								</logic:notEmpty>
							</td>
						</tr>
					</tbody>
					<!-- 评奖条件库 -->
					<thead>
						<tr onclick="showHiddenNr('tb_xgsz');">
							<th colspan="4">
								<span>相关设置</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<!--  <a href="#">编辑</a>-->
							</th>
						</tr>
					</thead>
					<tbody id="tb_xgsz">
						<tr style="">
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
						<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('zqts')" onmouseout="hiddTsDiv('zqts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：&nbsp;&nbsp;&nbsp;&nbsp;</font>	
								<font color="blue" id="zqts" style="display : none">奖学金的申请周期，与综测分及评奖条件无关。</font>
							</span>
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
									<input type="radio" name="save_sfqy" id="save_sfqy" value="是"/>
									是
									<input type="radio" name="save_sfqy" id="save_sfqy" value="否" checked="checked"/>
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
						<tr>	
							<td align="left" colspan="4">
							<span onmousemove="showTsDiv('qyts')" onmouseout="hiddTsDiv('qyts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：&nbsp;&nbsp;&nbsp;&nbsp;</font>	
								<font color="blue" id="qyts" style="display : none">只有奖学金启用时，才能对该奖学金进行下一步设置及学生进行对奖学金的申请。</font>
							</span>
						</td>
						</tr>
						<tr >
							<th>
								是否涉及金额
							</th>
							<td colspan="3">
								<logic:empty name="rs" property="save_sjje">
									<input type="radio" property="save_sjje" name="sjje" id="sjje_yes"  onclick="changeSjje('')" value="是"/>
									是
									<input type="radio" property="save_sjje" name="sjje" id="sjje_no" checked="checked"  onclick="changeSjje('none')" value="否"/> 
									否
								</logic:empty>
								<logic:notEmpty name="rs" property="save_sjje">
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
							<td >
								<logic:empty name="rs">
									<html:text property="save_xmje" styleId="xmje" value="" maxlength="10" />
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_xmje" styleId="xmje" maxlength="10"/>
								</logic:notEmpty>
							</td>
							<td align="left" colspan="2">
							<span onmousemove="showTsDiv('jets')" onmouseout="hiddTsDiv('jets')">
								<font color="blue">提示：</font>
								<font color="blue" id="jets" style="display : none">
								学生获得该奖学金时拿到奖励金额
								</font>
							</span>		
						</td>
						</tr>
					</tbody>
					<!-- 评奖条件库 -->
					<thead>
						<tr onclick="showHiddenNr('tb_rssz');">
							<th colspan="4">
								<span>人数设置</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<!--<a href="#">编辑</a>-->
							</th>
						</tr>
					</thead>
					<tbody id="tb_rssz">
						<tr id="rsszTr">
							<th>
								人数设置
							</th>
							<td >
								<input type="radio" name="save_rssz" id="save_rssz_yes" onclick="changeRssz('')" value="是"/>
								是
								<input type="radio" name="save_rssz" id="save_rssz_no"   onclick="changeRssz('none')" value="否" checked="checked"/>
								否
							</td>
							<td align="left" colspan="2">
							<span onmousemove="showTsDiv('rskgts')" onmouseout="hiddTsDiv('rskgts')">
								<font color="blue">提示：</font>
								<font color="blue" id="rskgts" style="display : none">
								该奖学金是否要控制人数，在审核中起作用
								</font>
							</span>	
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
						<tr id="kzfwTr" style="display:none">
							<th>
								控制范围
							</th>
							<td >
								<html:radio property="save_kzfw" styleId="kzfw" value="xy"></html:radio>
								<bean:message key="lable.xb" />
								<html:radio property="save_kzfw" styleId="kzfw" value="zy"></html:radio>
								专业
								<html:radio property="save_kzfw" styleId="kzfw" value="bj"></html:radio>
								班级
								<html:radio property="save_kzfw" styleId="kzfw" value="nj"></html:radio>
								年级
							</td>
							<td align="left" colspan="2">
							<span onmousemove="showTsDiv('rsfwts')" onmouseout="hiddTsDiv('rsfwts')">
								<font color="blue">提示：</font>
								<font color="blue" id="rsfwts" style="display : none">
								奖学金审核时,哪一级别的人数限制起作用，如: （设置成<bean:message key="lable.xb" />，则在审核时，只控制<bean:message key="lable.xb" />获奖人数上限）
								</font>
							</span>	
							</td>	
						</tr>
						<tr id="rssxTr" style="display:none">
							<th>
								人数上限
							</th>
							<td >
								<logic:empty name="rs">
									<html:text property="save_rssx" onkeydown="return onlyNum(this,10)"
													onmousedown="return onlyNum(this,10)" />
								</logic:empty>
								<logic:notEmpty name="rs">
									<html:text name="rs" property="save_rssx" onkeydown="return onlyNum(this,10)"
													onmousedown="return onlyNum(this,10)" />
								</logic:notEmpty>
							</td>
							<td align="left" colspan="2">
							<span onmousemove="showTsDiv('rssxts')" onmouseout="hiddTsDiv('rssxts')">
								<font color="blue">提示：</font>
								<font color="blue" id="rssxts" style="display : none">
								   全校总共能获得该奖学金的人数（人数设置中关联，影响设置方式）
								</font>
							</span>	
							</td>	
						</tr>
					</tbody>					<!-- 评奖条件库 -->
					<thead>
						<tr onclick="showHiddenNr('tb_shsz');">
							<th colspan="4">
								<span>审核设置</span>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<!--<a href="#">编辑</a>-->
							</th>
						</tr>
					</thead>
					<tbody id="tb_shsz">
						<tr>
							<th>
								需要审核	
							</th>
							<td colspan="3">
								<input type="hidden" name="save_lcid" id="save_lcid" value='${rs.lcid }'/>
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
						<tr style="height: 23px">
						<td align="left" colspan="4">
							<span onmousemove="showTsDiv('shlcts')" onmouseout="hiddTsDiv('shlcts')">
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：&nbsp;&nbsp;&nbsp;&nbsp;</font>	
								<font color="blue" id="shlcts" style="display : none">审核流程设置在：“系统维护-审批流程维护”模块下维护</font>
							</span>
						</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:equal name="doType" value="add">
										<button type="button" onclick="saveXmsz()" id="buttonSave">
											保 存
										</button>
									</logic:equal>
									<logic:equal name="doType" value="update">
										<button type="button" onclick="updateXmsz()" id="buttonSave">
											保 存
										</button>
									</logic:equal>
									<button type="button" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			</logic:notEmpty>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>	
	</body>
</html>
