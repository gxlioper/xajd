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
				alertInfo("����д�������!");
				return false;
		 	}
			var url="wjcfJcsz_cflbdm.do?method=cflbdmXg&doType=update";
			refreshForm(url);
		}
		//�������ߺ�������
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

		//�����Ƿ����ʾ������
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
		
		//���߳�ʼ��
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

		//������֤  ֻ��������
		function onyInt(obj){
			obj.value = obj.value.replace(/[^(\d]/g,'');
			return true;
		}

		//Ψһ��֤
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
					alertError("�ô�����������Ѿ����ڣ�");
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
						<em>���ĵ�ǰλ��:</em><a>${title } - �޸�</a>
					</p>
			</div>--%>
<!--			<div class="prompt" id="span_qsh" style="display: none">-->
<!--		       <h3><span>ϵͳ��ʾ��</span></h3>-->
<!--		       <p>�ô�����������Ѿ����ڣ�<br/></p>-->
<!--		   	</div>-->
		   	
		   	<logic:equal value="true" name="iskxg">
		   	<div class="prompt" id="spans" >
		       <h3><span>��ʾ��</span></h3>
		       <p>����ѧ���������������δ�����������޸���������<br/></p>
		   	</div>
		   	</logic:equal>
		   	
			<div class="con_overlfow">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���ִ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="rs">
						<tr>
							<th style="width:40%">
								<font color="red">*</font>
								�����������
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
								�����
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
								�Ƿ������
							</th>
							<td  style="width:60%">
								<input type="checkbox" id="bkss" value="no"  onclick="sfkyss('sfkss',this);"/>��������
								<input type="checkbox" id="xskss" value="xs" onclick="sfkyss('sfkss',this);"/>ѧ��������
								<input type="checkbox" id="jskss" value="js" onclick="sfkyss('sfkss',this);"/>��ʦ������
								<html:hidden property="sfkss" name="rs" styleId="sfkss" onclick="sfkyss('sfkss',this);"/>
							</td>
						</tr>
						<tr  id="ssgzr">
							<th style="width:40%">
								������������
							</th>
							<td  style="width:60%">
								<input type="text" name="ssslgzr" id="ssslgzr" onkeyup="onyInt(this);" value="7" maxlength="2" style="width:50px;" value="${rs.ssslgzr}"/>(��)
							</td>
						</tr>
						<tr>
							<th style="width:40%">
								�Ƿ��������
							</th>
							<td  style="width:60%">
								<input type="checkbox" id="bksqjc" value="no" onclick="sfkyss('sfksqjc',this);"/>���ɽ��
								<input type="checkbox" id="xsksqjc" value="xs" onclick="sfkyss('sfksqjc',this);"/>ѧ���ɽ��
								<input type="checkbox" id="jsksqjc" value="js" onclick="sfkyss('sfksqjc',this);"/>��ʦ�ɽ��
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
										"<font color="red">*</font>"����Ϣ����¼��
								</div>
								<div class="btn">
									<button type="button"  class="button2" id="btn_bc"  onclick="modi()">
										�� ��
									</button>
									<button type="button"  class="button2"  onclick="Close();return false;">
										�� ��
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
					 showAlert("����ʧ��!",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent12();
		    				}
		    			}});
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					 showAlert("�����ɹ�!",{},{"clkFun":function(){
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
