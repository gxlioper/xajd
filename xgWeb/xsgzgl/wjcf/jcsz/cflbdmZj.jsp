<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.HashMap" />
<jsp:directive.page import="xgxt.action.Base" />
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript'
			src='dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript">
		
		function save(){
			if ($("cflbmc").value.trim()==""){
				alertInfo("����д�������!");
				return false;
		 	}
			var url="wjcfJcsz_cflbdm.do?method=cflbdmZj&doType=save";
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

		//������֤  ֻ��������
		function onyInt(obj){
			obj.value = obj.value.replace(/[^(\d]/g,'');
			return true;
		}

		//Ψһ��֤
		function checkExists(tableName, pk){
			var pkV = jQuery('#cflbmc').val();
			dwr.engine.setAsync(false);
			systemFunction.checkExists(tableName,pk,pkV,function(data){
				if(data){
					jQuery('#span_qsh').show('normal')
					jQuery('#btn_bc').attr('disabled', 'disabled');
				}else{
					jQuery('#span_qsh').hide('normal')
					jQuery('#btn_bc').removeAttr('disabled');
				}
			});
			dwr.engine.setAsync(true);
		}
		</script>
	</head>
	<body>
		<html:form action="/wjcfJcsz_cflbdm" method="post">
			<%--<div class="tab_cur" >
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>${title } - ����</a>
					</p>
			</div>--%>

			<div class="prompt" id="span_qsh" style="display: none">
		       <h3><span>��ʾ��</span></h3>
		       <p>�ô�����������Ѿ����ڣ�<br/></p>
		   	</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���ִ�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:40%">
								<font color="red">*</font>
								�����������
							</th>
							<td style="width:60%">
								<input type="text" name="cflbmc" style="width:235px" id="cflbmc" onblur="checkExists('xg_wjcf_cflbdmb','cflbmc');" maxlength="100" />
							</td>
						</tr>
						<tr>
							<th style="width:40%">
								<font color="red">*</font>
								�����
							</th>
							<td  style="width:60%">
									
									<html:select property="spl" style="width:240px">
										<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>
									</html:select>
							</td>
						</tr>
						<tr>
							<th style="width:40%">
								�Ƿ������
							</th>
							<td  style="width:60%">
								<input type="checkbox" id="bkss" value="no" checked="checked" onclick="sfkyss('sfkss',this);"/>��������
								<input type="checkbox" id="xskss" value="xs" onclick="sfkyss('sfkss',this);"/>ѧ��������
								<input type="checkbox" id="jskss" value="js" onclick="sfkyss('sfkss',this);"/>��ʦ������
								<input type="hidden" name="sfkss" id="sfkss" value="no" onclick="sfkyss('sfkss',this);"/>
							</td>
						</tr>
						
						<tr id="ssgzr" style="display: none">
							<th style="width:40%">
								������������
							</th>
							<td  style="width:60%">
								<input type="text" name="ssslgzr" id="ssslgzr" onkeyup="onyInt(this);" value="7" maxlength="2" style="width:50px;"/>(��)
							</td>
						</tr>
						<tr>
							<th style="width:40%">
								�Ƿ��������
							</th>
							<td  style="width:60%">
								<input type="checkbox" id="bksqjc" value="no" checked="checked" onclick="sfkyss('sfksqjc',this);"/>���ɽ��
								<input type="checkbox" id="xsksqjc" value="xs" onclick="sfkyss('sfksqjc',this);"/>ѧ���ɽ��
								<input type="checkbox" id="jsksqjc" value="js" onclick="sfkyss('sfksqjc',this);"/>��ʦ�ɽ��
								<input type="hidden" name="sfksqjc" id="sfksqjc" value="no" onclick="sfkyss('sfksqjc',this);"/>
							</td>
						</tr>
					</tbody>
				</table>	
				<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									��"<font color="red">*</font>"����Ϣ����¼��
								</div>
								<div class="btn">
									<button class="button2" id="btn_bc" type="button" onclick="save()">
										�� ��
									</button>
									<button class="button2" type="button" onclick="Close()">
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
		    					refershParent();
		    				}
		    			}});
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					 showAlert("�����ɹ�!",{},{"clkFun":function(){
		    				if (parent.window){
		    					refershParent();
		    				}
		    			}});
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
