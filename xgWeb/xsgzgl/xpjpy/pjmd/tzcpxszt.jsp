<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		 <script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/pjmd/js/pjmd.js"></script>
		<script type="text/javascript">

		jQuery(function(){

 			if (jQuery("#xh").val() != ""){
				
				var xhInfo = jQuery('#xhInfo').val();
				
				if(""!=xhInfo){
					showAlert(xhInfo);
					return false;
				}

				var tjzt = jQuery("#tjzt").val();
				var tjr = jQuery("#tjr").val();
				var tzzt = jQuery("#tzzt").val();
				var tzr = jQuery("#tzr").val();

				//判断是否已提交
				if (tjzt == "1"){
					showAlert("该学生已被“"+jQuery("#tjrxm").val()+"”提交，不能再进行调整");
					jQuery("#buttonSave").attr("disabled",true);
				}

				//判断已调整过班级并且用户和调整人不一致
				if(tzzt == "1" && tzr!=jQuery("#userName").val()){
					showAlert("该学生已被“"+jQuery("#tzrxm").val()+"”调整过，不能再进行第二次调整");
					jQuery("#buttonSave").attr("disabled",true);
				}
			}
		})
		
		</script>
	</head>
	<body>
		<html:form action="/xpj_cpmd" method="post" styleId="sqshForm" onsubmit="return false;">
		<input type="hidden" name="userName" id="userName" value="${userName}"/>
		<input type="hidden" name="ids" id="ids" value="${ids }"/>
		<input type="hidden" name="xhInfo" id="xhInfo" value="${xhInfo }" />
		<input type="hidden" name="tjr" id="tjr" value = "${tjxx.tjr}" />
		<input type="hidden" name="tjzt" id="tjzt" value = "${tjxx.tjzt}" />
		<input type="hidden" name="tjrxm" id="tjrxm" value = "${tjxx.tjrxm }" />
		<input type="hidden" name="tzzt" id="tzzt" value = "${tjxx.tzzt }" />
		<input type="hidden" name="tzr" id="tzr" value = "${tjxx.tzr }" />
 		<input type="hidden" name="tzrxm" id="tzrxm" value = "${tjxx.tzrxm }" />
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<td colspan="6" width="100%" >
								<table width="98%" border="0" class="formlist">
									<tbody>
										<tr>
											<th width="15%">学号</th>
											<td align="left" width="25%">
												<html:text  property="xh" styleId="wjxh" value="${jbxx.xh}"  
												 maxlength="20" onblur="checkXh(this.value)"/>
											</td>
											<th width="12%">姓名</th>
											<td width="20">${jbxx.xm }</td>
											<th width="12%">性别</th>
											<td width="10">${jbxx.xb }</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<td colspan="2" width="50%">
								<table width="99%" border="0" class="formlist">
									<tbody>
										<tr>
											<th colspan="2" style="height:21px;" ><center>所在单位<center></th>
										</tr>
										<tr>
											<th style="height:21px;" width="30%">所在年级</th>
											<td >
												${jbxx.nj }
												<html:hidden styleId="tzqnj" property="tzqnj" value="${jbxx.zydm }"/>
											</td>
										</tr>
										<tr>
											<th style="height:21px;" >所在<bean:message key="lable.xb" /></th>
											<td>
												${jbxx.xymc }
												<html:hidden styleId="tzqxydm" property="tzqxydm" value="${jbxx.xydm }"/>
												<html:hidden styleId="tzqxydm" property="tzqxymc" value="${jbxx.xymc }"/>
											</td>
										</tr>
										<tr>
											<th style="height:21px;">所在专业</th>
											<td>
												${jbxx.zymc }
												<html:hidden styleId="tzqzydm" property="tzqzydm" value="${jbxx.zydm }"/>
												<html:hidden styleId="tzqzydm" property="tzqzymc" value="${jbxx.zymc }"/>
											</td>
										</tr>
										<tr>
											<th style="height:21px;">所在班级</th>
											<td>
												${jbxx.bjmc }
												<html:hidden styleId="tzqbjdm" property="tzqbjdm" value="${jbxx.bjdm }"/>
												<html:hidden styleId="tzqbjmc" property="tzqbjmc" value="${jbxx.bjmc }"/>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
							<td colspan="2">
								<table width="99%" border="0" class="formlist">
									<tbody>
										<tr >
											<th colspan="2" align="center" style="height:21px;"><center>参评单位</center></th>
										</tr>
										<tr>
											<th style="height:21px;" width="23%">参评年级</th>
											<td>
												<html:select property="nj" styleId="nj" value="${jbxx.nj}"
													onkeydown="return onlyBackSpace(this);" style="width:90px" onchange="initXyzybj();">
													<html:option value=""></html:option>
													<html:options collection="njList" property="nj"
														labelProperty="nj" />
												</html:select>
											</td>
										</tr>
										<tr>
											<th style="height:21px;">参评<bean:message key="lable.xsgzyxpzxy" />${isxy }</th>
											<td>
												<input type="text" id="xymc"
													onkeydown="return onlyBackSpace(this);" value="" />
												<input type="hidden" name="xydm" id="xydm" value="" />
											</td>
										</tr>
										<tr>
											<th style="height:21px;">参评专业</th>
											<td>
												<input type="text" id="zymc"
													onkeydown="return onlyBackSpace(this);" value="" />
												<input type="hidden" name="zydm" id="zydm"
													value="" />
											</td>
										</tr>
										<tr>
											<th style="height:21px;">参评班级</th>
											<td colspan="">
												<input type="text" id="bjmc"
													onkeydown="return onlyBackSpace(this);" value="" />
												<input type="hidden" id="bjdm"  value="" name="bjdm"/>	
													
												<button type="button" class="btn_01" id="button_bj" style="display: "
													onclick="getBjbyPzzd();">
													选择
												</button>
												
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button name="保存" id="buttonSave" name="buttonSave"  onclick="saveCpxs();">
										保 存
									</button>
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

