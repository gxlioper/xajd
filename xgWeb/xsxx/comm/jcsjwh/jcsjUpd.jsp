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
				//ѧԺ
				checkData = "xydm-xymc-bmlb"
				
			}else if("1" ==xzflg){
				//רҵ
				checkData = "xydmzy-zydm-zymc"
				
			}else if("2" ==xzflg){
				//�༶
				checkData = "xydmbj-zydmbj-bjdm-bjmc-nj"
			}
			
			if(!check(checkData)){
				return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			}
		     var url = "jcsj.do?method=jcsjUpd&type=save&xzflg="+xzflg;
		      ajaxSubFormWithFun("jcsjForm",url,function(data){
		    	 if(data["message"]=="����ɹ���"){
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
				//��ʾѧԺ
				jQuery("#zjxy").show();
				jQuery("#zjbj").hide();
				jQuery("#zjzy").hide();

				jQuery("#xydmShow").hide();
				jQuery("#bmlb").attr("disabled","disabled");
				
				//��ѧ�������޸Ĵ���
				if(xss>0){
					jQuery("#xydmShow").show();
					jQuery("#xydm").hide();
					jQuery("[name='spanBt']").hide();
					
				}
			}else if("1" ==xzflg){
				//��ʾѧԺ
				jQuery("#zjxy").hide();
				jQuery("#zjzy").show();
				jQuery("#zjbj").hide();

				jQuery("#xydmzyShow").hide();
				jQuery("#zydmShow").hide();
				//��ѧ�������޸Ĵ���
				if(xss>0){
					jQuery("#xydmzyShow").show();
					jQuery("#xydmzy").hide();
					jQuery("#zydmShow").show();
					jQuery("#zydm").hide();
					jQuery("[name='spanBt']").hide();
				}		
				
			}else if("2" ==xzflg){
				//��ʾѧԺ
				jQuery("#zjxy").hide();
				jQuery("#zjzy").hide();
				jQuery("#zjbj").show();

				jQuery("#xydmbjShow").hide();
				jQuery("#zydmbjShow").hide();
				jQuery("#bjdmShow").hide();
				
				//��ѧ�������޸Ĵ���
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
		
		// ѧԺ����
		function onChangXyZy(xyId,zyId){
			var xydm = jQuery("#"+xyId).val();
			if(xydm == ""){
				jQuery("#"+zyId).empty();
				jQuery("#"+zyId).append("<option value=''>----��ѡ��----</option>");
			}else{
				
				// ���÷�������zylist
				jQuery.post("jcsj.do?method=onChangXyZy",{xydm:xydm},function(data){

					jQuery("#"+zyId).empty();
					jQuery("#"+zyId).append("<option value=''>----��ѡ��----</option>");
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
							<span class="red" name="spanBt">*</span><bean:message key="lable.xy" />����
						</th>
						<td align="left" colspan="3">
							<html:hidden property="xydmold" styleId="xydmold"></html:hidden>
							<label id="xydmShow">${jcsjForm.xydm}</label>
							<html:text property="xydm" styleId="xydm" maxlength="8"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span><bean:message key="lable.xy" />����
						</th>
						<td align="left" colspan="3">
							<html:text property="xymc" styleId="xymc" maxlength="128"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span>���
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
							<span class="red" name="spanBt">*</span>רҵ����
						</th>
						<td align="left" colspan="3">
							<html:hidden property="zydmold" styleId="zydmold"></html:hidden>
							<label id="zydmShow">${jcsjForm.zydm}</label>
							<html:text property="zydm" styleId="zydm" maxlength="8"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span>רҵ����
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
							<span class="red" name="spanBt">*</span>רҵ
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
							<span class="red" name="spanBt">*</span>�༶����
						</th>
						<td align="left" colspan="3">
							<html:hidden property="bjdmold" styleId="bjdmold"></html:hidden>
							<label id="bjdmShow">${jcsjForm.bjdm}</label>
							<html:text property="bjdm" styleId="bjdm" maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span>�༶����
						</th>
						<td align="left" colspan="3">
							<html:text property="bjmc" styleId="bjmc" maxlength="32"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right">
							<span class="red">*</span>�꼶
						</th>
						<td align="left" colspan="3">
							<html:text property="nj" styleId="nj" maxlength="4"></html:text>( ����2010 )
						</td>
					</tr>
					<logic:equal name="xxdm" value="8403">
					<tr>
						<th align="right">
							�й�<bean:message key="lable.xb" />
						</th>
						<td align="left" colspan="3">
							<html:select property="tgxydm"  styleId="tgxydm" onchange="">
								<html:option value="">----��ѡ��----</html:option>
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
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="saveForm();return false;" id="buttonSave">
									�� ��
								</button>
								&nbsp;&nbsp;
								<button type="button"  onclick="iFClose();" id="buttonClose">
									�� ��
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
