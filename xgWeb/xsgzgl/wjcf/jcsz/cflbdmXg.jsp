<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		
		
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript">
		function modi(){
			if ($("cflbmc").value=="") {
				alertInfo("请填写处分类别!");
				return false;
		 	}
			var url="wjcfJcsz_cflbdm.do?method=cflbdmXg&doType=update";
			refreshForm(url);
		}
		//控制申诉和申请解除
		function sfkyss(type,obj){
			var val="";
			var bkss=null;
			var xskss=null;
			var jskss=null;
			var sfkss=null;
			var jQobj=jQuery(obj);
			if("sfkss" == type){
				bkss=jQuery("#bkss");
				xskss=jQuery("#xskss");
				jskss=jQuery("#jskss");
				sfkss=jQuery("#sfkss");
				sfXsgzr(jQobj);
			}else if("sfksqjc" == type){
				bkss=jQuery("#bksqjc");
				xskss=jQuery("#xsksqjc");
				jskss=jQuery("#jsksqjc");
				sfkss=jQuery("#sfksqjc");
			}else{
				return ;
			}
			if(jQobj.attr("id")==bkss.attr("id") && bkss.prop("checked")){
				val=bkss.val();
				xskss.removeAttr("checked");
				jskss.removeAttr("checked");
			}
			if(jQobj.attr("id")==xskss.attr("id") || jQobj.attr("id")==jskss.attr("id")){
				bkss.removeAttr("checked");
				if(xskss.prop("checked")){
					val=xskss.val();
				}
				if(jskss.prop("checked")){
					if(val==""){
						val=jskss.val();
					}else{
						val=val+","+jskss.val();
					}
				}
			}

			sfkss.val(val);
			return ;
		}

		//控制是否可显示工作日
		function sfXsgzr(obj){
			var bkss=jQuery("#bkss");
			var xskss=jQuery("#xskss");
			var jskss=jQuery("#jskss");
			if(obj.attr("id")==bkss.attr("id") && bkss.prop("checked")){
				$("ssslgzr").value="";
				document.getElementById('ssgzr').style.display = "none";
			}
			if(obj.attr("id")==xskss.attr("id") || obj.attr("id")==jskss.attr("id")){
				document.getElementById('ssgzr').style.display = "block";
				}
			}
		
		//申诉初始化
		function sscsh(){
			var sfkss=jQuery("#sfkss").val();
			var sfksqjc=jQuery("#sfksqjc").val();
			if(sfkss!=""){
				if(sfkss.indexOf("no") > -1){
					jQuery("#bkss").attr("checked","checked");
					document.getElementById('ssgzr').style.display = "none";
				}
				if(sfkss.indexOf("xs") > -1){
					jQuery("#xskss").attr("checked","checked");
					document.getElementById('ssgzr').style.display = "block";
				}
				if(sfkss.indexOf("js") > -1){
					jQuery("#jskss").attr("checked","checked");
					document.getElementById('ssgzr').style.display = "block";
				}
			}
			if(sfksqjc!=""){
				if(sfksqjc.indexOf("no") > -1){
					jQuery("#bksqjc").attr("checked","checked");
				}
				if(sfksqjc.indexOf("xs") > -1){
					jQuery("#xsksqjc").attr("checked","checked");
				}
				if(sfksqjc.indexOf("js") > -1){
					jQuery("#jsksqjc").attr("checked","checked");
				}
			}
		}

		//数据验证  只能是数字
		function onyInt(obj){
			obj.value = obj.value.replace(/[^(\d]/g,'');
			return true;
		}

		//唯一验证
		function checkExists(tableName, pk){
			var pkV = jQuery('#cflbmc').val();
			var checkExistsPk = jQuery('#checkExistsPk').val();
			if(pkV==checkExistsPk){
				jQuery('#span_qsh').hide('normal')
				jQuery('#btn_bc').removeAttr('disabled');
				return ;
			}
			dwr.engine.setAsync(false);
			systemFunction.checkExists(tableName,pk,pkV,function(data){
				if(data){
					//jQuery('#span_qsh').show('normal')
					alertError("该处分类别名称已经存在！");
					jQuery('#btn_bc').attr('disabled', 'disabled');
					return false;
				}else{
					//jQuery('#span_qsh').hide('normal')
					jQuery('#btn_bc').removeAttr('disabled');
				}
			});
			dwr.engine.setAsync(true);
		}
		
		function refershParent12(){
			
			if(frameElement.api){
				var api = frameElement.api,W = api.opener;
				jQuery(W.document).find('#search_go').click();
				closeDialog();
			} else {
				jQuery(parent.window.document).find('#search_go').click();
				iFClose();
			}
		}
		</script>
	</head>
	<body onload="sscsh();">
		<html:form action="/wjcfJcsz_cflbdm" method="post">
			<%--<div class="tab_cur" >
					<p class="location">
						<em>您的当前位置:</em><a>${title } - 修改</a>
					</p>
			</div>--%>
<!--			<div class="prompt" id="span_qsh" style="display: none">-->
<!--		       <h3><span>系统提示：</span></h3>-->
<!--		       <p>该处分类别名称已经存在！<br/></p>-->
<!--		   	</div>-->
		   	
		   	<logic:equal value="true" name="iskxg">
		   	<div class="prompt" id="spans" >
		       <h3><span>提示：</span></h3>
		       <p>部分学生处分审核流程尚未结束，不能修改审批流！<br/></p>
		   	</div>
		   	</logic:equal>
		   	
			<div class="con_overlfow">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>处分代码信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="rs">
						<tr>
							<th style="width:40%">
								<font color="red">*</font>
								处分类别名称
							</th>
							<td style="width:60%">
								<html:text property="cflbmc" name="rs" onblur="checkExists('xg_wjcf_cflbdmb','cflbmc');" styleId="cflbmc" maxlength="100"></html:text>
								<input type="hidden" id="checkExistsPk" name="checkExistsPk" value="<bean:write name="rs" property="cflbmc"/>"/>
								<html:hidden property="cflbdm" name="rs" styleId="cflbmc"/>
							</td>
						</tr>
						<tr>
							<th style="width:40%">
								<font color="red">*</font>
								审核流
							</th>
							<td  style="width:60%">
								<logic:equal value="true" name="iskxg">
								<html:select name="rs" property="ssspl" styleId="ssspl" style="width:240px" value="${rs.spl}" disabled="true">
										<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>
									</html:select>
								<input type="hidden" name="spl" id="spl" value="${rs.spl }"/>
								</logic:equal>
								<logic:notEqual value="true" name="iskxg">
									<html:select name="rs" property="spl" styleId="spl" style="width:240px" value="${rs.spl}">
										<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th style="width:40%">
								是否可申诉
							</th>
							<td  style="width:60%">
								<input type="checkbox" id="bkss" value="no"  onclick="sfkyss('sfkss',this);"/>不可申诉
								<input type="checkbox" id="xskss" value="xs" onclick="sfkyss('sfkss',this);"/>学生可申诉
								<input type="checkbox" id="jskss" value="js" onclick="sfkyss('sfkss',this);"/>教师可申诉
								<html:hidden property="sfkss" name="rs" styleId="sfkss" onclick="sfkyss('sfkss',this);"/>
							</td>
						</tr>
						<tr  id="ssgzr">
							<th style="width:40%">
								申诉受理工作日
							</th>
							<td  style="width:60%">
								<input type="text" name="ssslgzr" id="ssslgzr" onkeyup="onyInt(this);" value="7" maxlength="2" style="width:50px;" value="${rs.ssslgzr}"/>(天)
							</td>
						</tr>
						<tr>
							<th style="width:40%">
								是否可申请解除
							</th>
							<td  style="width:60%">
								<input type="checkbox" id="bksqjc" value="no" onclick="sfkyss('sfksqjc',this);"/>不可解除
								<input type="checkbox" id="xsksqjc" value="xs" onclick="sfkyss('sfksqjc',this);"/>学生可解除
								<input type="checkbox" id="jsksqjc" value="js" onclick="sfkyss('sfksqjc',this);"/>教师可解除
								<html:hidden property="sfksqjc" name="rs" styleId="sfksqjc" onclick="sfkyss('sfkss',this);"/>
							</td>
						</tr>
						</logic:present>
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
										"<font color="red">*</font>"的信息必须录入
								</div>
								<div class="btn">
									<button type="button"  class="button2" id="btn_bc"  onclick="modi()">
										保 存
									</button>
									<button type="button"  class="button2"  onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				
			</div>

			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					 showAlert("操作失败!",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent12();
		    				}
		    			}});
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					 showAlert("操作成功!",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent12();
		    				}
		    			}});
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
