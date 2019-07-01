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

		/**
		 * 保存调整参评班级
		 */

		function saveCpxs(){
			
			var tzqbjdm = jQuery('#tzqbjdm').val();		//调整前班级代码
			var tzhbjdm = jQuery('#bjdm').val();		//调整后班级代码
			var ids = jQuery('#ids').val();				//选中id
			var bjmc = jQuery('#bjmc').val();			//调整后班级名称
			
			
			if(tzhbjdm == ""){
				showAlertDivLayer("参评班级不能为空");	
				return false;
			}if(tzqbjdm == tzhbjdm){
				showAlertDivLayer("参评班级不能和所在班级一致，请重新选择参评班级")
				return false;
			}
			
			showConfirmDivLayer("您确定将该学生调整到“"+bjmc+"”吗？",{"okFun":function(){
				var url = "xpj_cpmd.do?method=updateCpxs&tzqbjdm="+tzqbjdm+"&ids="+ids+"&tzhbjdm="+tzhbjdm;
				jQuery.post(url,{},function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}})
				});
			}});
			
		}
		</script>
	</head>
	<body>
		<html:form action="/xpj_cpmd" method="post" styleId="sqshForm" onsubmit="return false;">
		<input type="hidden" name="ids" id="ids" value="${ids }"/>
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<tbody>
						<tr>
							<td colspan="6" width="100%" >
								<table width="98%" border="0" class="formlist">
									<tbody>
										<tr>
											<th align="right" width="20%">
												<font color="red">*</font>学号
											</th>
											<td align="left" width="30%">
												<html:text  property="xh" styleId="wjxh" value="${jbxx.xh}"  
												 maxlength="20"/>
											</td>
											<th align="right" width="20%">
												姓名
											</th>
											<td align="left" id="xm">
												${jbxx.xm}
											</td>
											<th align="right" width="20%">
												性别
											</th>
											<td align="left" id="xm">
												${jbxx.xb}
											</td>
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
											<th colspan="2" align="center" style="height:21px;" >所在单位</th>
										</tr>
										<tr>
											<th style="height:21px;">所在年级</th>
											<td>
												${jbxx.nj }
											</td>
										</tr>
										<tr>
											<th style="height:21px;">所在<bean:message key="lable.xb" /></th>
											<td>
												${jbxx.xymc }
											</td>
										</tr>
										<tr>
											<th style="height:21px;">所在专业</th>
											<td>
												${jbxx.zymc }
											</td>
										</tr>
										<tr>
											<th style="height:21px;">所在班级</th>
											<td>
												${jbxx.bjmc }
											</td>
										</tr>
									</tbody>
								</table>
							</td>
							<td colspan="2">
								<table width="99%" border="0" class="formlist">
									<tbody>
										<tr >
											<th colspan="2" align="center" style="height:21px;">参评单位</th>
										</tr>
										<tr>
											<th style="height:21px;">参评年级</th>
											<td>
												<html:select name='jbxx'  property="nj" styleId="nj"
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
									<button name="保存" id="buttonSave" type="button" onclick="saveCpxs();">
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

