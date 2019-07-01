<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
		
		function saveXmsz(){
			if($("xmmc").value==""){
				alert("项目名称不能为空!");
				return false;
			}
			
			if($("xmlbdm").value==""){
				alert("项目类别不能为空!");
				return false;
			}
			
			if($("xmsm").value==""){
				alert("项目说明不能为空!");
				return false;
			}
			refreshForm("/xgxt/mdqr.do?method=mdqrXmsz&doType=save");
		}
		
		function updateXmsz(){
			refreshForm("/xgxt/mdqr.do?method=mdqrXmUpdate&doType=update");
		}
		
		function fdysz(){
			$('sxysh').style.display="";
			$('sxxsh').style.display="";
			$('sxyxsh').style.display="";
			$('sbsh').style.display="";
			$('xyNbspone').style.display="none";
			$('xyNbsptwo').style.display="none";
			$('xxNbspone').style.display="none";
			$('xxNbsptwo').style.display="none";
		}
		
		function xysz(){
			$('sxysh').style.display="none";
			$('sxxsh').style.display="";
			$('sxyxsh').style.display="none";
			$('sbsh').style.display="";
			$('xyNbspone').style.display="";
			$('xyNbsptwo').style.display="";
			$('xxNbspone').style.display="none";
			$('xxNbsptwo').style.display="none";
		}
		
		function xxsz(){
			$('sxysh').style.display="none";
			$('sxxsh').style.display="none";
			$('sxyxsh').style.display="none";
			$('sbsh').style.display="";
			$('xyNbspone').style.display="none";
			$('xyNbsptwo').style.display="none";
			$('xxNbspone').style.display="";
			$('xxNbsptwo').style.display="";
		}
		
		function ufdysz(){
			$('sxysh').style.display="";
			$('sxxsh').style.display="";
			$('sxyxsh').style.display="";
			$('sbsh').style.display="";
			$('bsh').checked="true";
			$('xyNbspone').style.display="none";
			$('xyNbsptwo').style.display="none";
			$('xxNbspone').style.display="none";
			$('xxNbsptwo').style.display="none";
		}
		
		function uxysz(){
			$('sxysh').style.display="none";
			$('sxxsh').style.display="";
			$('sxyxsh').style.display="none";
			$('sbsh').style.display="";
			$('bsh').checked="true";
			$('xyNbspone').style.display="";
			$('xyNbsptwo').style.display="";
			$('xxNbspone').style.display="none";
			$('xxNbsptwo').style.display="none";
		}
		
		function uxxsz(){
			$('sxysh').style.display="none";
			$('sxxsh').style.display="none";
			$('sxyxsh').style.display="none";
			$('sbsh').style.display="";
			$('xyNbspone').style.display="none";
			$('xyNbsptwo').style.display="none";
			$('xxNbspone').style.display="";
			$('xxNbsptwo').style.display="";
			$('bsh').checked="true";
		}
		
		function loadShlc(){
			if($("add") && $("add").value=="yes"){
				$("fsq").checked="true";
				$("fsh").checked="true";
				$("fqr").checked="true";
				$("wxz").checked="true";
				$("wxzqr").checked="true";
				$("wzq").checked="true";
				$("bsh").checked="true";
			}
			if($("mdsz").value=="fdy"){
				 fdysz();
			}else if($("mdsz").value=="xy"){
				 xysz();
			}else if($("mdsz").value=="xx"){
				 xxsz();
			}else if($("mdsz").value=="no"){
				 fdysz();
			}
		}
		
		function showSz(){
			if($("ksq") && $("ksq").checked){
				$('showMdsz').style.display="";
			}else{
				$('showMdsz').style.display="none";
			}
			
			if($("ksh") && $("ksh").checked){
				$('fdysz').style.display="";
			}else{
				$('fdysz').style.display="none";
			}
			
			if($("kqr") && $("kqr").checked){
				$('showMdqr').style.display="";
			}else{
				$('showMdqr').style.display="none";
			}
		}
		
	</script>
	</head>
	<body onload="loadShlc();showSz()"  >
		
		<html:form action="/mdqr" method="post">
			<input type="hidden" name ="xxdm" id = "xxdm" value="<bean:write name="xxdm" />"/>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title}
					<logic:equal name="doType" value="add">
					-增加
					</logic:equal>
					<logic:equal name="doType" value="modi">
					-修改
					</logic:equal>
					<logic:equal name="doType" value="view">
					-查看
					</logic:equal>
					</a>
				</p>
			</div>
					<input type="hidden" id="doType" name="doType"
						value="${doType }" />
					<input type="hidden" id="userType" name="userType" value="${userType }" />
					<input type="hidden" id="pkValue" name="pkValue"
						value="${pkValue }" />
					<input type="hidden" id="disableEle" name="disableEle" value="" />
					<input type="hidden" id="readonlyEle" name="readonlyEle"
						value="zgh" />
					<input type="hidden" id="getStuInfo" name="getStuInfo" value="" />
					<input type="hidden" id="url" name="url" value="/sjcz/xspxxxb.jsp" />
					<logic:equal name="doType" value="add">
						<input type="hidden" id="save_xmdm" name="save_xmdm" value="${xmdm }"/>
					</logic:equal>
					<input type="hidden" name="message" id="message" value="${message }"/>
					<logic:equal name="doType" value="modi">
						<input type="hidden" name="save_xmdm" id="save_xmdm" value="${rs.xmdm}"/>
					</logic:equal>
					<logic:empty name="rs">
						<input type="hidden" name="add" id="add" value="yes"/>
					</logic:empty>
					<input type="hidden" name="mdsz" id="mdsz" value="${rs.save_mdsz}"/>
						<div class="tab">
							<table width="100%"  border="0" class="formlist">
							 <thead>
			    				<tr>
			        				<th colspan="6"><span>项目设置</span></th>
			        			</tr>
			   				 </thead>
			   				
			   				 <tbody>
							<tr>
								<th style="width:16%">
									<font color="red">*</font>项目名称
								</th>
								<td>
									<logic:empty name="rs">
										<html:text  property="save_xmmc" styleId="xmmc" maxlength="15"/>
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:text name="rs"  property="save_xmmc" styleId="xmmc" maxlength="15"/>
									</logic:notEmpty>
								</td>
								<th>
									<font color="red">*</font>项目类别
								</th>
								<td>
									<logic:empty name="rs">
										<html:select property="save_xmlbdm" styleId="xmlbdm">
											<html:option value=""></html:option>
											<html:options collection="xmlbList" property="dm"
												 labelProperty="mc"/>
										</html:select>
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:select name="rs" property="save_xmlbdm" styleId="xmlbdm">
											<html:option value=""></html:option>
											<html:options collection="xmlbList" property="dm"
												 labelProperty="mc"/>
										</html:select>
									</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th>
									<font color="red">*</font>项目说明
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:textarea   property="save_xmsm" styleId="xmsm" cols="67" rows="5" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"></html:textarea>
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:textarea name="rs"  property="save_xmsm"  styleId="xmsm"  cols="67" rows="5" style="word-break:break-all;width:99%" onblur="chLeng(this,500);"></html:textarea>
									</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th align="right">
									可否设置
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:radio property="save_kfsq" styleId="ksq" value="yes" onclick="$('showMdsz').style.display=''"></html:radio>开放
										<html:radio property="save_kfsq" styleId="fsq" value="no" onclick="$('showMdsz').style.display='none'"></html:radio>关闭
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:radio name="rs" property="save_kfsq"  styleId="ksq" value="yes" onclick="$('showMdsz').style.display=''"></html:radio>开放
										<html:radio name="rs" property="save_kfsq"  styleId="fsq" value="no"  onclick="$('showMdsz').style.display='none'"></html:radio>关闭
									</logic:notEmpty>
									<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									提示：设置项目是否开放设置名单功能</font>
								</td>
							</tr>
							<tr id="showMdsz" style="display:none">
								<th align="right">
									名单设置
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:radio property="save_mdsz" value="fdy" onclick='fdysz()'/>辅导员
									 	<html:radio property="save_mdsz" value="xy" onclick='xysz()'/><bean:message key="lable.xb" />
									 	<font color="blue">
									 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 	提示：设置项目在名单设置时的用户权限。</font><br/>
									 	<html:radio property="save_mdsz" value="xx" onclick="xxsz()"/>学校
									 	<html:radio property="save_mdsz" styleId="wxz"  value="no" onclick='fdysz()' />无限制
									</logic:empty>
									<logic:notEmpty name="rs">
									 	<html:radio name="rs" property="save_mdsz"   value="fdy" onclick="ufdysz();"/>辅导员
									 	<html:radio name="rs" property="save_mdsz" value="xy" onclick="uxysz();"/><bean:message key="lable.xb" />
									 	<font color="blue">
									 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 	提示：设置项目在名单设置时的用户权限。</font><br/>
									 	<html:radio name="rs" property="save_mdsz" value="xx" onclick="uxxsz();"/>学校
									 	<html:radio name="rs" property="save_mdsz"  value="no" onclick="ufdysz();"/>无限制
								 	</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th align="right">
									可否审核
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:radio property="save_kfsh" styleId="ksh" value="yes" onclick="$('fdysz').style.display=''"></html:radio>开放
										<html:radio property="save_kfsh" styleId="fsh" value="no" onclick="$('fdysz').style.display='none'"></html:radio>关闭
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:radio name="rs" property="save_kfsh" value="yes" styleId="ksh" onclick="$('fdysz').style.display=''"></html:radio>开放
										<html:radio name="rs" property="save_kfsh" value="no"  styleId="fsh" onclick="$('fdysz').style.display='none'"></html:radio>关闭
									</logic:notEmpty>
									<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									提示：设置项目是否开放审核功能。</font>
								</td>
							</tr>
							<logic:empty name="rs">
										<tr id="fdysz" style="display:none">
											<th>
												审核流程
											</th>
											<td colspan="3">
												<span id="sxysh"><html:radio  property="save_shjb" styleId="xysh" value="xysh"/><bean:message key="lable.xb" />审核</span>
										 		<span id="sxxsh"><html:radio  property="save_shjb" styleId="xxsh" value="xxsh"/>学校审核</span>
										 		<font color="blue">
										 		<span id="xyNbspone" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		<span id="xxNbspone" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提示：设置项目在审核时的审核流程，不同的设置名单权限，</font>
										 		<br/>
										 		<span id="sxyxsh"><html:radio  property="save_shjb" styleId="xyxsh" value="xyxsh"/><bean:message key="lable.xb" />审核 - 学校审核</span>
										 		<span id="sbsh"><html:radio  property="save_shjb" styleId="bsh" value="no"/>不审核</span>
										 		<font color="blue">
										 		<span id="xyNbsptwo" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		<span id="xxNbsptwo" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;能够选择的审核流程也不同。</font>
										 		
										 	</td>
										 </tr>
								</logic:empty>
								<logic:notEmpty name="rs">
									 	<tr id="fdysz" style="display:none">
											<th>
												审核流程
											</th>
											<td colspan="3">
									 			<span id="sxysh"><html:radio name="rs" property="save_shjb" styleId="xysh" value="xysh"/><bean:message key="lable.xb" />审核</span>
										 		<span id="sxxsh"><html:radio name="rs" property="save_shjb" styleId="xxsh" value="xxsh"/>学校审核</span>
										 		<font color="blue">
										 		<span id="xyNbspone" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		<span id="xxNbspone" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提示：设置项目在审核时的审核流程，不同的设置名单权限，</font>
										 		<br/>
										 		<span id="sxyxsh"><html:radio name="rs" property="save_shjb" styleId="xyxsh" value="xyxsh"/><bean:message key="lable.xb" />审核 - 学校审核</span>
										 		<span id="sbsh"><html:radio name="rs" property="save_shjb" styleId="bsh" value="no"/>不审核</span>
										 		<font color="blue">
										 		<span id="xyNbsptwo" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		<span id="xxNbsptwo" style="display:none">
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										 		</span>
										 		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;能够选择的审核流程也不同。</font>
										 	</td>
										 </tr>
						 		</logic:notEmpty>
							<tr>
								<th align="right">
									可否确认
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:radio property="save_kfqr" styleId="kqr" value="yes" onclick="$('showMdqr').style.display=''"></html:radio>开放
										<html:radio property="save_kfqr" styleId="fqr" value="no" onclick="$('showMdqr').style.display='none'"></html:radio>关闭
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:radio name="rs" property="save_kfqr" styleId="kqr"  value="yes" onclick="$('showMdqr').style.display=''"></html:radio>开放
										<html:radio name="rs" property="save_kfqr" styleId="fqr" value="no" onclick="$('showMdqr').style.display='none'"></html:radio>关闭
									</logic:notEmpty>
									<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									提示：设置项目是否开放确认名单功能。</font>
								</td>
							</tr>
							<tr id="showMdqr" style="display:none">
								<th align="right">
									名单确认
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:radio property="save_mdqr" value="fdy"/>辅导员
									 	<html:radio property="save_mdqr" value="xy"/><bean:message key="lable.xb" />
									 	<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 	提示：设置项目在名单确认时的权限。</font>
									 	<br/>
									 	<html:radio property="save_mdqr" value="xx"/>学校
									 	<html:radio property="save_mdqr" styleId="wxzqr" value="no"/>不确认
									</logic:empty>
									<logic:notEmpty name="rs">
									 	<html:radio name="rs"  property="save_mdqr" value="fdy"/>辅导员
									 	<html:radio name="rs"  property="save_mdqr" value="xy"/><bean:message key="lable.xb" />
									 	<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 	提示：设置项目在名单确认时的权限。</font>
									 	<br/>
									 	<html:radio name="rs"  property="save_mdqr" value="xx"/>学校
									 	<html:radio name="rs"  property="save_mdqr" value="no"/>不确认
									 </logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th align="right">
									周期
								</th>
								<td colspan="3">
									<logic:empty name="rs">
										<html:radio property="save_szzq" value="xn" ></html:radio>学年
										<html:radio property="save_szzq" value="xq"></html:radio>学期
										<html:radio property="save_szzq" value="nd"></html:radio>年度
										<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提示：设置项目在名单设置时的周期性，</font>
										<br/>
										<html:radio property="save_szzq" styleId="wzq" value="wzq" ></html:radio>无周期
										<html:radio property="save_szzq" value="jyc"></html:radio>仅一次
										<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										同时影响审核和名单的确认。</font>
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:radio name="rs" property="save_szzq" value="xn"></html:radio>学年
										<html:radio name="rs" property="save_szzq" value="xq"></html:radio>学期
										<html:radio name="rs" property="save_szzq" value="nd"></html:radio>年度
										<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;提示：设置项目在名单设置时的周期性，</font>
										<br/>
										<html:radio name="rs" property="save_szzq" value="wzq"></html:radio>无周期
										<html:radio name="rs" property="save_szzq" value="jyc"></html:radio>仅一次
										<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										同时影响审核和名单的确认。</font>
									</logic:notEmpty>
									
								</td>
							</tr>
								
							
							</tbody>
							<tfoot>
								<tr>
									 <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
										<div class="btn">
											<logic:equal name="doType" value="add">
												<button onclick="saveXmsz();return false;">保 存</button>
											</logic:equal>
											<logic:equal name="doType" value="modi">
												<button onclick="updateXmsz();return false;">保 存</button>
											</logic:equal>
											<button onclick="Close();return false;">关 闭</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
						</div>
						<logic:present name="result">
						<script>
							alert(''+$('message').value);
							if (window.dialogArguments) {
								window.close();
								window.dialogArguments.document.getElementById('search_go').click();
							}
						</script>
						</logic:present>
		</html:form>
	</body>
</html>
