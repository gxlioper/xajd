<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//ҳ���ʼ��
		function onShow(){
			//����ѧ��
			var pjxn = jQuery("#hidden_xn").val();
			if(pjxn != ""){
				jQuery("#pjxn").val(pjxn);
			}

			//����ѧ��
			var pjxq = jQuery("#hidden_xq").val();
			if(pjxq != ""){
				jQuery("#pjxq").val(pjxq);
			}

			//��Ŀ����
			var xmlx = jQuery("#xmlx_check").val();
			if(xmlx != ""){
				jQuery("input[type=radio][name=xmlx][value="+xmlx+"]").attr("checked",true);
			}
		}
		
		//���������Ϣ
		function setOtherXsxx(xh){
			
		}

		//��֤������ʷ����
		function checkSaveLspj(){

			var flag = true;
			var xh = jQuery("#input_xh").val();
			var pjxn = jQuery("#pjxn").val();
			var pjxq = jQuery("#pjxq").val();
			var xmmc = jQuery("#xmmc").val();
			
			if(xh == ""){
				alertError("����ѡ��ѧ��");
				flag = false;
			}else if(pjxn == ""){
				alertError("����ѧ�겻��Ϊ�գ�����ȷ��");
				flag = false;
			}else if(xmmc == ""){
				alertError("��Ŀ���Ʋ���Ϊ�գ�����ȷ��");
				flag = false;
			}

			if(pjxq == ""){
				jQuery("#hidden_xq").val("no");
			}
			
			if(flag){
				confirmInfo("����ȷ���Ƿ�ִ�б��������",saveLspj);
			}
		}

		//������ʷ����
		function saveLspj(tag){
				
			if(tag=="ok"){
				// �õ�JSON����
		        var parameter ={};
		      	//ָ����ȡ�Ŀؼ����ͣ�����ѭ��
				jQuery("input,textarea").each(function(){
					//��ȡ���ؼ�name
					var name=jQuery(this).attr("name");
					//����json����
					parameter[name]=escape(jQuery(this).val());
				});
				
				var url = "general_wdpj_jgcx_ajax.do?method=savePjlsxx";

				jQuery.ajaxSetup({async:false});
				
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
					}
				);

				jQuery.ajaxSetup({async:true});
			}
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab" style="width:100%;height:380px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">	
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<!-- ���� begin -->
						<logic:equal name="doType" value="add">
							<tr>
								<th width="20%">
									<font color="red">*</font>ѧ��
								</th>
								<td width="30%">
									<input type="text" name="str_xh" 
										readonly="readonly" id="input_xh" 
										style="width:100px" value="${rs.xh }"/>
									<button type="button"  class="btn_01" onclick="showChooseDiv()">ѡ��</button>
								</td>
								<th width="20%">
									����		
								</th>
								<td width="">
									<span id="span_xm">${rs.xm }</span>
								</td>
							</tr>
						</logic:equal>
						<!-- ���� end -->
						
						<!-- �޸�or�鿴 begin -->
						<logic:notEqual name="doType" value="add">
							<tr>
								<th width="20%">
									ѧ��
								</th>
								<td width="30%">
									<input type="hidden" name="str_xh" id="input_xh" value="${rs.xh }"/>
									${rs.xh }
								</td>
								<th width="20%">
									����		
								</th>
								<td width="">
									<span id="span_xm">${rs.xm }</span>
								</td>
							</tr>
						</logic:notEqual>
						<!-- �޸�or�鿴 end -->
						<tr>
							<th width="">
								�꼶
							</th>
							<td width="">
								<span id="span_nj">${rs.nj }</span>
							</td>
							<th width="">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td width="">
								<span id="span_xymc">${rs.xymc }</span>
							</td>
						</tr>
						<tr>
							<th width="">
								רҵ
							</th>
							<td width="">
								<span id="span_zymc">${rs.zymc }</span>
							</td>
							<th width="">
								�༶
							</th>
							<td width="">
								<span id="span_bjmc">${rs.bjmc }</span>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>��Ŀ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="">
								<font color="red">*</font>����ѧ��
							</th>
							<td width="">
								<!-- ���� begin -->
								<logic:equal name="doType" value="add">
									<html:select name="rs" property="pjxn" styleId="pjxn" 
										onchange="$('hidden_xn').value=this.value">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</logic:equal>
								<!-- ���� end -->
						
								<!-- �޸�or�鿴 begin -->
								<logic:notEqual name="doType" value="add">
									<html:select name="rs" property="pjxn" styleId="pjxn" disabled="true"
										onchange="$('hidden_xn').value=this.value">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</logic:notEqual>
								<!-- �޸�or�鿴 end -->
						
								<input type="hidden" name="str_xn" id="hidden_xn" value="${rs.xn }"/>
							</td>
							<th width="">
								����ѧ��
							</th>
							<td width="">
								<!-- ���� begin -->
								<logic:equal name="doType" value="add">
									<html:select name="rs" property="pjxq" styleId="pjxq" 
										onchange="$('hidden_xq').value=this.value">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</logic:equal>
								<!-- ���� end -->
						
								<!-- �޸�or�鿴 begin -->
								<logic:notEqual name="doType" value="add">
									<html:select name="rs" property="pjxq" styleId="pjxq" disabled="true"
										onchange="$('hidden_xq').value=this.value">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
									</html:select>
								</logic:notEqual>
								<!-- �޸�or�鿴 end -->
								
								<input type="hidden" name="str_xq" id="hidden_xq" value="${rs.xq }"/>
							</td>
						</tr>	
						<tr>
							<th width="">
								<font color="red">*</font>��Ŀ����	
							</th>
							<td width="">
								<!-- ���� begin -->
								<logic:equal name="doType" value="add">
									<input type="text" name="str_xmmc" id="xmmc" value="${rs.xmmc }" maxlength="20"/>
								</logic:equal>
								<!-- ���� end -->
						
								<!-- �޸�or�鿴 begin -->
								<logic:notEqual name="doType" value="add">
									<input type="text" name="str_xmmc" id="xmmc" value="${rs.xmmc }" readonly="readonly"/>
								</logic:notEqual>
								<!-- �޸�or�鿴 end -->
							</td>
							<th width="">
								��Ŀ����
							</th>
							<td width="">
								<!-- ���� begin -->
								<logic:equal name="doType" value="add">
									<input type="radio" name="xmlx" id="xmlx_01"
										value="01" onclick="setCheckedValue(this)" 
										checked="checked"/>��ѧ��
									<input type="radio" name="xmlx" id="xmlx_02"
										value="02" onclick="setCheckedValue(this)" 
										/>�����ƺ�
									</logic:equal>
								<!-- ���� end -->
						
								<!-- �޸�or�鿴 begin -->
								<logic:notEqual name="doType" value="add">
									<input type="radio" name="xmlx" id="xmlx_01"
										value="01" onclick="setCheckedValue(this)" 
										disabled="disabled"/>��ѧ��
									<input type="radio" name="xmlx" id="xmlx_02"
										value="02" onclick="setCheckedValue(this)" 
										disabled="disabled"/>�����ƺ�
								</logic:notEqual>
								<!-- �޸�or�鿴 end -->
								<input type="hidden" name="str_xmlx" id="xmlx_check" value="${rs.xmlx }"/>
							</td>
						</tr>
						<tr>
							<th width="">
								���
							</th>
							<td width="">
								<input type="text" name="str_xmje" 
									onkeyup="checkInputNum(this)"
									onblur="checkInputNum(this)"
									maxlength="5"
									id="xmje" value="${rs.xmje }"/>
							</td>
							<th width="">
								���ʱ��
							</th>
							<td width="">
								<input type="text" id="hdsj" readonly="readonly"
									name="str_hdsj" value="${rs.hdsj }"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar(this.id,'yyyyMMdd');"/>
							</td>
						</tr>	
						<tr>
							<th width="">
								��ע
								<br />
								<font color="blue">(��500��)</font>
							</th>
							<td width="" colspan="3">
								<textarea rows="5" name="str_bz" cols="" 
									onblur="chLeng(this,500)"
									id="bz" style="width:99%">${rs.bz }</textarea>
							</td>
						</tr>
					</tbody>
			    </table>
		    </div>
		    
		    <div>
		    	<table width="100%" border="0" class="formlist">	
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									<!-- �޸�or���� begin -->
									<logic:notEqual name="doType" value="view">
										<button type="button"  name="����" onclick="checkSaveLspj();">�� ��</button>
									</logic:notEqual>
									<!-- �޸�or���� end -->
									<button type="button"  name="�ر�" onclick="Close();return false;">�� ��</button>
								</div>
							</td>
						</tr>
				    </tfoot>
			    </table>
		    </div>
		    <!-- ѧ��ѡ�� -->
			<%@ include file="/comm/other/choiceXh.jsp"%>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>