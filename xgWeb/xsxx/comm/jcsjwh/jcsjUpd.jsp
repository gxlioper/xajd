<%@ page language="java" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="xsxx/comm/jcsjwh/js/jcsj.js"></script>
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type="text/javascript">
		
		function saveForm(){
			var checkData = "";
			var xzflg = jQuery("#xzflg").val();
			
			if("" ==xzflg || "0" ==xzflg){
				//学院
				checkData = "xydm-xymc-bmlb"
				
			}else if("1" ==xzflg){
				//专业
				checkData = "xydmzy-zydm-zymc"
				
			}else if("2" ==xzflg){
				//班级
				checkData = "xydmbj-zydmbj-bjdm-bjmc-nj"
			}
			
			if(!check(checkData)){
				return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			}
		     var url = "jcsj.do?method=jcsjUpd&type=save&xzflg="+xzflg;
		      ajaxSubFormWithFun("jcsjForm",url,function(data){
		    	 if(data["message"]=="保存成功！"){
		    		 showAlert(data["message"],{},{"clkFun":function(){
				    		 if (parent.window){
		    					refershParent();
		    				 }
		    			}});
		    	 }else{
		    		 showAlert(data["message"]);
		    	 }
				});
		  }
		jQuery(function(){
			
			var xzflg = jQuery("#xzflg").val();
			var xss = jQuery("#xss").val();
			if("" ==xzflg || "0" ==xzflg){
				//显示学院
				jQuery("#zjxy").show();
				jQuery("#zjbj").hide();
				jQuery("#zjzy").hide();

				jQuery("#xydmShow").hide();
				jQuery("#bmlb").attr("disabled","disabled");
				
				//有学生则不能修改代码
				if(xss>0){
					jQuery("#xydmShow").show();
					jQuery("#xydm").hide();
					jQuery("[name='spanBt']").hide();
					
				}
			}else if("1" ==xzflg){
				//显示学院
				jQuery("#zjxy").hide();
				jQuery("#zjzy").show();
				jQuery("#zjbj").hide();

				jQuery("#xydmzyShow").hide();
				jQuery("#zydmShow").hide();
				//有学生则不能修改代码
				if(xss>0){
					jQuery("#xydmzyShow").show();
					jQuery("#xydmzy").hide();
					jQuery("#zydmShow").show();
					jQuery("#zydm").hide();
					jQuery("[name='spanBt']").hide();
				}		
				
			}else if("2" ==xzflg){
				//显示学院
				jQuery("#zjxy").hide();
				jQuery("#zjzy").hide();
				jQuery("#zjbj").show();

				jQuery("#xydmbjShow").hide();
				jQuery("#zydmbjShow").hide();
				jQuery("#bjdmShow").hide();
				
				//有学生则不能修改代码
				if(xss>0){
					jQuery("#xydmbjShow").show();
					jQuery("#xydmbj").hide();
					jQuery("#zydmbjShow").show();
					jQuery("#zydmbj").hide();
					jQuery("#bjdmShow").show();
					jQuery("#bjdm").hide();
					jQuery("[name='spanBt']").hide();
				}
			}
			
		});
		
		// 学院联动
		function onChangXyZy(xyId,zyId){
			var xydm = jQuery("#"+xyId).val();
			if(xydm == ""){
				jQuery("#"+zyId).empty();
				jQuery("#"+zyId).append("<option value=''>----请选择----</option>");
			}else{
				
				// 调用方法返回zylist
				jQuery.post("jcsj.do?method=onChangXyZy",{xydm:xydm},function(data){

					jQuery("#"+zyId).empty();
					jQuery("#"+zyId).append("<option value=''>----请选择----</option>");
					jQuery(data).each(function(){
						jQuery("#"+zyId).append(jQuery("<option/>").text(this.zymc).attr("value",this.zydm));
					});
					
				},'json');
			}
		}
		</script>
	</head>
	<body>
		<html:form action="/jcsj" method="post" styleId="jcsjForm" onsubmit="return false;">
		<input type="hidden" id="xzflg" name="xzflg" value="${jcsjForm.xzflg }"/>
		<input type="hidden" id="xss" name="xss" value="${jcsjForm.xss }"/>
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div style='tab;width:100%;height:240px;overflow-x:hidden;overflow-y:auto;'>
			<br/>
			<table width="95%" border="0" class="formlist">
				<tbody id="zjxy">
					<tr>
						<th align="right">
							<span class="red" name="spanBt">*</span><bean:message key="lable.xy" />代码
						</th>
						<td align="left" colspan="3">
							<html:hidden property="xydmold" styleId="xydmold"></html:hidden>
							<label id="xydmShow">${jcsjForm.xydm}</label>
							<html:text property="xydm" styleId="xydm" maxlength="8"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span><bean:message key="lable.xy" />名称
						</th>
						<td align="left" colspan="3">
							<html:text property="xymc" styleId="xymc" maxlength="128"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span>类别
						</th>
						<td align="left" colspan="3">
							<html:select property="bmlb"  styleId="bmlb">
								<html:options collection="bmlbList" property="bmlbdm"
									labelProperty="bmlbmc" />
							</html:select>
						</td>
					</tr>
				</tbody>
				<tbody id="zjzy">
					<tr>
						<th align="right">
							<span class="red" name="spanBt">*</span><bean:message key="lable.xy" />
						</th>
						<td align="left" colspan="3">
							<label id="xydmzyShow">${jcsjForm.xymc}</label>				
							<html:select property="xydmzy"  styleId="xydmzy" >
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="pyszm" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red" name="spanBt">*</span>专业代码
						</th>
						<td align="left" colspan="3">
							<html:hidden property="zydmold" styleId="zydmold"></html:hidden>
							<label id="zydmShow">${jcsjForm.zydm}</label>
							<html:text property="zydm" styleId="zydm" maxlength="8"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span>专业名称
						</th>
						<td align="left" colspan="3">
							<html:text property="zymc" styleId="zymc" maxlength="32"></html:text>
						</td>
					</tr>
				</tbody>
				<tbody id="zjbj">
					<tr>
						<th align="right">
							<span class="red" name="spanBt">*</span><bean:message key="lable.xy" />
						</th>
						<td align="left" colspan="3">
							<label id="xydmbjShow">${jcsjForm.xymc}</label>
							<html:select property="xydmbj"  styleId="xydmbj" onchange="onChangXyZy('xydmbj','zydmbj');">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="pyszm" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red" name="spanBt">*</span>专业
						</th>
						<td align="left" colspan="3">
							<label id="zydmbjShow">${jcsjForm.zymc}</label>
							<html:select property="zydmbj" styleId="zydmbj" >
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red" name="spanBt">*</span>班级代码
						</th>
						<td align="left" colspan="3">
							<html:hidden property="bjdmold" styleId="bjdmold"></html:hidden>
							<label id="bjdmShow">${jcsjForm.bjdm}</label>
							<html:text property="bjdm" styleId="bjdm" maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span>班级名称
						</th>
						<td align="left" colspan="3">
							<html:text property="bjmc" styleId="bjmc" maxlength="32"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span>年级
						</th>
						<td align="left" colspan="3">
							<html:text property="nj" styleId="nj" maxlength="4"></html:text>( 例：2010 )
						</td>
					</tr>
					<logic:equal name="xxdm" value="8403">
					<tr>
						<th align="right">
							托管<bean:message key="lable.xb" />
						</th>
						<td align="left" colspan="3">
							<html:select property="tgxydm"  styleId="tgxydm" onchange="">
								<html:option value="">----请选择----</html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="pyszm" />
							</html:select>
						</td>
					</tr>
					</logic:equal>
				</tbody>
			</table>
		</div>
		<div>
			<table width="95%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button"  onclick="saveForm();return false;" id="buttonSave">
									保 存
								</button>
								&nbsp;&nbsp;
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
