<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script	type="text/javascript">
		function getXwlbList(obj){
			jQuery.post('rcsw_rcxwwh_rcxwxxwhgl.do?method=getXwlbList',{rcxwlbdldm:obj.value},function(data){
					var option = "<option value=''></option>";
					for(var i = 0; i < data.length; i++){
						option +="<option value='"+data[i].rcxwlbdm+"'>"+data[i].rcxwlbmc+"</option>";
					}
					jQuery('#rcxwlbdm').empty().append(option);	
					//jQuery("#rcxwlbdm").val(jQuery("#rcxwlbdm_value").val());	
			},'json');
		}

		function xwlbChange(obj){
			jQuery.post('rcsw_rcxwwh_rcxwdmwhgl.do?method=getFzqjxx',{rcxwlbdm:obj.value},function(data){
				var rcxwlbzdfz=data['rcxwlbzdfz'];
				var rcxwlbzgfz=data['rcxwlbzgfz'];
				jQuery("#fzqj").html(rcxwlbzdfz+'-'+rcxwlbzgfz);
				jQuery("#rcxwlbzdfz").val(rcxwlbzdfz);
				jQuery("#rcxwlbzgfz").val(rcxwlbzgfz);
				jQuery("#fz").val('');
			},'json');
		}
		
		
		function saveForm(){
			//����֤ѧ��
            var temp = "��Ϊ";
            if(jQuery("#xxdm").val() == "13431") temp="�ӷ�";
			var xh = jQuery("#xh").val();
			var xn = jQuery("#xn").val();
			var xq = jQuery("#xq").val();
			var rcxwlbdldm = jQuery("#rcxwlbdldm").val();
			var rcxwlbdm = jQuery("#rcxwlbdm").val();
			var fz = jQuery("#fz").val();
			var fzsfgd = jQuery("#fzsfgd").val();
			var zgfz = jQuery("#zgfz").val();
			var zdfz = jQuery("#zdfz").val();

			if (jQuery.trim(xn) == ""){
				showAlert("����ѡ��ѧ�꣡");
				return false;
			}
			if (jQuery.trim(xq) == ""){
				showAlert("����ѡ��ѧ�ڣ�");
				return false;
			}
			
			if (jQuery.trim(rcxwlbdldm) == ""){
				showAlert("����ѡ��"+temp+"���࣡");
				return false;
			}
			if (jQuery.trim(rcxwlbdm) == ""){
				showAlert("����ѡ��"+temp+"���");
				return false;
			}
			if (jQuery.trim(fz) == ""){
				showAlert("����д������ֵ��");
				return false;
			}

			if (jQuery.trim(jQuery("#fssj").val()) == ""){
				showAlert("����д����ʱ�䣡");
				return false;
			}

			if("10956" == jQuery("#xxdm").val() || "13011" == jQuery("#xxdm").val()) {
				if (jQuery.trim(jQuery("#gfly").val()) == ""){
					showAlert("����д�������ɣ�");
					return false;
				}
			}
            if("13431" == jQuery("#xxdm").val()) {
                if (jQuery.trim(jQuery("input[name=lbfj]").val()) == ""){
                    showAlert("��ѡ�񸽼���");
                    return false;
                }
            }
			if(zgfz!=zdfz){
				if(fzsfgd!=null && fzsfgd=="zdy"){
					if(parseFloat(jQuery.trim(fz))<parseFloat(jQuery.trim(zdfz))||parseFloat(jQuery.trim(fz))>parseFloat(jQuery.trim(zgfz))){
						showAlert("������ֵ�����ڷ�ֵ��Χ�ڣ�");
						return false;
					}
				}
			}
			/*
			var rcxwlbzdfz = jQuery("#rcxwlbzdfz").val();
			var rcxwlbzgfz = jQuery("#rcxwlbzgfz").val();
			if(parseFloat(fz)<parseFloat(rcxwlbzdfz)||parseFloat(fz)>parseFloat(rcxwlbzgfz)){
				showAlert("��ֵ�����ڷ�ֵ��Χ�ڣ�");
				return false;
			}
			*/
		     var url = "rcsw_rcxwwh_rcxwjggl.do?method=updateXwjg&type=update";
		      ajaxSubFormWithFun("rcxwjgForm",url,function(data){
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
		
		//��ȡ��Ϊ�����Ϣ
		function getXwlbxx(obj){
			jQuery.post('rcsw_rcxwwh_rcxwxxwhgl.do?method=getXwlbxx',{rcxwlbdm:obj.value},function(data){
					if(data!=null && data.length>0){
						var fzlx = data[0].rcxwfzlxmc;
						var fzqj = data[0].fzqj;
						var pzsm = data[0].rcxwlbbz;
						var pzsmsj = data[0].rcxwlbbzsj;//����
						var fzsfgd = data[0].fzsfgd;
						var zgfz = data[0].rcxwlbzgfz;
						var zdfz = data[0].rcxwlbzdfz;
						document.getElementById("fzlxDiv").innerHTML=fzlx;
						document.getElementById("fzqjDiv").innerHTML=fzqj;
						document.getElementById("pzsmDiv").innerHTML=pzsm;
						if(fzsfgd!=null && fzsfgd=="gd"){
							document.getElementById("fzDiv").innerHTML=fzqj+"<input type='hidden' name='fz' id='fz' value='"+fzqj+"'/><input type='hidden' name='fzsfgd' id='fzsfgd' value='"+fzsfgd+"'/>";
						}else {
							if(zgfz==zdfz){
								document.getElementById("fzDiv").innerHTML=fzqj+"<input type='hidden' name='fz' id='fz' value='"+fzqj+"'/><input type='hidden' name='fzsfgd' id='fzsfgd' value='"+fzsfgd+"'/>";
							}else{
								document.getElementById("fzDiv").innerHTML="<input type='text' name='fz' id='fz' style=\"width:130px\" onkeyup=\"checkInputNum(this)\" maxlength='8'/><input type='hidden' name='fzsfgd' id='fzsfgd' value='"+fzsfgd+"'/><input type='hidden' name='zdfz' id='zdfz' value='"+zdfz+"'/><input type='hidden' name='zgfz' id='zgfz' value='"+zgfz+"'/>";
							}
						}
					}
			},'json');
		}
		
		
		function delFile(id){
			jQuery.post("rcsw_rcxwwh_rcxwjggl.do?method=deleteFile",{id:id},function(data){
				jQuery("#fileTd").empty().append("<input type='file' name='lbfj' onchange='checkFileType(this)'/>");
			});
		}
		
		function checkFileType(obj){
			var type = obj.value.substr(obj.value.lastIndexOf(".")+1);
			var types = ["png","gif","jpg","jpeg","zip","rar","pdf","txt","doc","docx","xls","xlsx"];
			if (jQuery.inArray(type, types) == -1){
				showAlert("����ѡ����ļ����Ͳ������ϴ���");
				obj.value="";
			}
		}
		</script>
		
	</head>
	<body>
		
		<html:form action="/rcsw_rcxwwh_rcxwjggl" method="post" styleId="rcxwjgForm" enctype="multipart/form-data">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="id"  styleId="id"/>
			<div style='tab;width:100%;height:410px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>

									<logic:equal name="xxdm" value="13431">
										�ӷּ�¼��Ϣ
									</logic:equal>
									<logic:notEqual name="xxdm" value="13431">
										��Ϊ��¼��Ϣ
									</logic:notEqual>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:130px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xq" styleId="xq" style="width:130px">
								<html:option value=""></html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>
								<logic:equal name="xxdm" value="13431">
									�ӷִ���
								</logic:equal>
								<logic:notEqual name="xxdm" value="13431">
									��Ϊ����
								</logic:notEqual>
							</th>
							<td>
								<html:select property="rcxwlbdldm" styleId="rcxwlbdldm" style="width:130px" onchange="getXwlbList(this)">
									<html:option value=""></html:option>
									<html:options collection="xwdlList" property="rcxwlbdldm" labelProperty="rcxwlbdlmc" />
								</html:select>
							</td>
							<th>
							<span class="red">*</span>
								<logic:equal name="xxdm" value="13431">
									�ӷ����
								</logic:equal>
								<logic:notEqual name="xxdm" value="13431">
									��Ϊ���
								</logic:notEqual>
							</th>
							<td>
								<input type="hidden" id="rcxwlbdm_value" value="${rcxwlbdm_value}"/>
								<html:select property="rcxwlbdm" styleId="rcxwlbdm" onchange="getXwlbxx(this)" style="width:130px">
								<html:option value=""></html:option>	
								<html:options collection="xwlbList" property="rcxwlbdm" labelProperty="rcxwlbmc" />
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th>��ֵ����</th>
							<td>
								<font color="red"><div id="fzlxDiv"><bean:write name="xwlbxx" property="rcxwfzlxmc"/></div></font>
							</td>
							<th>��ֵ</th>
							</th>
							<td>
								<div id="fzqjDiv"><bean:write name="xwlbxx" property="fzqj"/></div>
							</td>
					    </tr>
					    <tr>
							<th >����˵��</th>
							<td colspan="3" id="pzsmDiv">
								${xwlbxx.rcxwlbbz }
							</td>							
					    </tr>
					    <tr>
							<th><span class="red">*</span>������ֵ</th>
							<td>
								<div id="fzDiv">
									<html:text property="fz" styleId="fz" onkeyup="checkInputNum(this)" maxlength="8" style="width: 130px"></html:text>
									<input type="hidden" id="fzsfgd" name="fzsfgd" value="${xwlbxx.fzsfgd }"/>
									<input type="hidden" id="zgfz" name="zgfz" value="${xwlbxx.rcxwlbzgfz }"/>
									<input type="hidden" id="zdfz" name="zdfz" value="${xwlbxx.rcxwlbzdfz }"/>
								</div>
							</td>
							<th><span class="red">*</span>����ʱ��</th>
							</th>
							<td>
								<html:text property="fssj" styleId="fssj" style="width:130px" onkeypress="onlyBackSpace(this,event);"
									onclick="return showCalendar(this.id,'yyyy-MM-dd')"  readonly="true"></html:text>
							</td>
					    </tr>

					    <logic:equal value="13022" name="xxdm">

					    <tr>
					    	<th>����<br /><font color="red">&lt;��5M&gt;</font></th>
					    	<td colspan="3" id="fileTd">
					    		<logic:notEmpty name="rcxwjgForm" property="fjlj">
					    			<a href="javascript:delFile('${rcxwjgForm.id }');" class="name">ɾ��</a>
					    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwjggl.do?method=downloadFile&id=${rcxwjgForm.id }');return false;" class="name">����</a>&nbsp;${rcxwjgForm.fjmc }
					    		</logic:notEmpty>
					    		<logic:empty name="rcxwjgForm" property="fjlj">
					    			<input type='file' name='lbfj' onchange="checkFileType(this)"/>
					    		</logic:empty>
					    	</td>
					    </tr>
						</logic:equal>
						 
						 <logic:equal value="10344" name="xxdm">
						    <tr>
						    	<th>����<br /><font color="red">&lt;��5M&gt;</font></th>
						    	<td colspan="3" id="fileTd">
						    		<logic:notEmpty name="rcxwjgForm" property="fjlj">
						    			<a href="javascript:delFile('${rcxwjgForm.id }');" class="name">ɾ��</a>
						    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwjggl.do?method=downloadFile&id=${rcxwjgForm.id }');return false;" class="name">����</a>&nbsp;${rcxwjgForm.fjmc }
						    		</logic:notEmpty>
						    		<logic:empty name="rcxwjgForm" property="fjlj">
						    			<input type='file' name='lbfj' onchange="checkFileType(this)"/>
						    		</logic:empty>
						    	</td>
						    </tr>
						</logic:equal>
						<logic:equal value="13871" name="xxdm">
						    <tr>
						    	<th>����<br /><font color="red">&lt;��5M&gt;</font></th>
						    	<td colspan="3" id="fileTd">
						    		<logic:notEmpty name="rcxwjgForm" property="fjlj">
						    			<a href="javascript:delFile('${rcxwjgForm.id }');" class="name">ɾ��</a>
						    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwjggl.do?method=downloadFile&id=${rcxwjgForm.id }');return false;" class="name">����</a>&nbsp;${rcxwjgForm.fjmc }
						    		</logic:notEmpty>
						    		<logic:empty name="rcxwjgForm" property="fjlj">
						    			<input type='file' name='lbfj' onchange="checkFileType(this)"/>
						    		</logic:empty>
						    	</td>
						    </tr>
						</logic:equal>
						<logic:equal value="70002" name="xxdm">
						    <tr>
						    	<th>����<br /><font color="red">&lt;��5M&gt;</font></th>
						    	<td colspan="3" id="fileTd">
						    		<logic:notEmpty name="rcxwjgForm" property="fjlj">
						    			<a href="javascript:delFile('${rcxwjgForm.id }');" class="name">ɾ��</a>
						    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwjggl.do?method=downloadFile&id=${rcxwjgForm.id }');return false;" class="name">����</a>&nbsp;${rcxwjgForm.fjmc }
						    		</logic:notEmpty>
						    		<logic:empty name="rcxwjgForm" property="fjlj">
						    			<input type='file' name='lbfj' onchange="checkFileType(this)"/>
						    		</logic:empty>
						    	</td>
						    </tr>
						</logic:equal>
						<logic:equal value="13431" name="xxdm">
							<tr>
								<th><font color="red">*</font>����<br /><font color="red">&lt;��5M&gt;</font></th>
								<td colspan="3" id="fileTd">
									<logic:notEmpty name="rcxwjgForm" property="fjlj">
										<a href="javascript:delFile('${rcxwjgForm.id }');" class="name">ɾ��</a>
										<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwh_rcxwjggl.do?method=downloadFile&id=${rcxwjgForm.id }');return false;" class="name">����</a>&nbsp;${rcxwjgForm.fjmc }
									</logic:notEmpty>
									<logic:empty name="rcxwjgForm" property="fjlj">
										<input type='file' name='lbfj' onchange="checkFileType(this)"/>
									</logic:empty>
								</td>
							</tr>
						</logic:equal>
					    <tr>
							<th>
							   	<logic:equal value="10956" name="xxdm"><span class="red">*</span></logic:equal>
							   	<logic:equal value="13011" name="xxdm"><span class="red">*</span></logic:equal>��������	
								<br /><font color="red">&lt;��50��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='gfly' style="width:98%" styleId="gfly" rows='3' onblur="checkLen(this,50);"/>
							</td>
			      		</tr>
					    <tr>
							<th>
							   	��ע	
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='5' onblur="checkLen(this,500);"/>
							</td>
			      </tr>
					</tbody>
				</table>
			</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

