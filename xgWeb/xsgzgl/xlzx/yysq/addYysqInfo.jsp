<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="xsgzgl/xlzx/yysq/js/addYysqInfo.js"></script>
		<script type="text/javascript" >
		function saveYysqInfo(){
			var sjddm = (!jQuery("#sjddm").val()) ? "" : jQuery("#sjddm").val();
			if(jQuery("#xstell").val()=='' || jQuery("#yyzxzt").val()=='' || (jQuery("#sjddm").length > 0 && sjddm=='')){
				return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			}
            if (jQuery("#xstxxx_tb").length > 0 ) {
                var checkId = 'dzyx-fqzy-fxl-mqzy-mxl-jtdz-zxmd';
                if(!checkNotNull(checkId)){
                    showAlertDivLayer("�뽫��<font color='red'>*</font>����Ŀ��д������");
                    return false;
                }
                var hf = jQuery("input[type=radio][name='hf']:checked").length;
                var sfyzn = jQuery("input[type=radio][name='sfyzn']:checked").length;
                var sfdszn = jQuery("input[type=radio][name='sfdszn']:checked").length;
                var syd = jQuery("input[type=radio][name='syd']:checked").length;
                if(hf <=0 || sfyzn <=0 || sfdszn <=0 || syd <=0){
                    showAlertDivLayer("�뽫��<font color='red'>*</font>����Ŀ��д������");
                    return false;
				}
            }
            // �õ�JSON����
            //���±���������ȫ�ǳ�������ԤԼģʽ���ֵ���Ҫ����ֹ������undifined
            var qssj = (!jQuery("#qssj").val()) ? "" : jQuery("#qssj").val();
            var jssj = (!jQuery("#jssj").val()) ? "" : jQuery("#jssj").val();
		    var parameter ={
				xh:jQuery("#xh").val(),
				zgh:jQuery("#zgh").val(),
				status:1,//����ԤԼ������Ϣ����״̬��Ϊ1ԤԼ��.
				xstell:jQuery("#xstell").val(),
				yyzxrq:jQuery("#yyzxrq").val(),
				qssj:qssj,
				jssj:jssj,
				sjddm:sjddm,
				yyzxzt:encodeURI(encodeURI(jQuery("#yyzxzt").val())),
				yyzxxq:encodeURI(encodeURI(jQuery("#yyzxxq").val()))
			};
            if (jQuery("#yytxxx_tb").length > 0 ) {
                var checkId = 'bczxwt-zxhzt';
                if(!checkNotNull(checkId)){
                    showAlertDivLayer("�뽫��<font color='red'>*</font>����Ŀ��д������");
                    return false;
                }
                var qxztzt = jQuery("input[type=radio][name='qxztzt']:checked");
                var qxztjl = jQuery("input[type=radio][name='qxztjl']:checked");
                var qxztyy = jQuery("input[type=radio][name='qxztyy']:checked");
                var sczxhgb = jQuery("input[type=radio][name='sczxhgb']:checked");
                var zjzt = jQuery("input[type=radio][name='zjzt']:checked");
                if(qxztzt.length <=0 || qxztjl.length <=0 || qxztyy.length <=0 || sczxhgb.length <=0 || zjzt.length <=0){
                    showAlertDivLayer("�뽫��<font color='red'>*</font>����Ŀ��д������");
                    return false;
                }
                parameter["qxztzt"] = qxztzt.val();
                parameter["qxztjl"] = qxztjl.val();
                parameter["qxztyy"] = qxztyy.val();
                parameter["sczxhgb"] = sczxhgb.val();
                parameter["zjzt"] = zjzt.val();
                parameter["bczxwt"] = encodeURI(encodeURI(jQuery("#bczxwt").val()));
                parameter["zxhzt"] = encodeURI(encodeURI(jQuery("#zxhzt").val()));
            }

			var url = "xlzx_yysq.do?method=saveYysqInfo";
			showConfirm("ȷ�ϱ���ԤԼ��Ϣ��",{"okFun":function(){
				jQuery.ajaxSetup({async:false});
                ajaxSubFormWithFun("form", "xlzx_xstxxx.do?method=save", function(data) {
                    if(data == true){
                        jQuery.post(url,parameter,function(data){
                            if(data == true){
                                showAlert("����ɹ���",{},{"clkFun":function(){
                                    frameElement.api.opener.refreshForm("xlzx_yysq_yysq.do");
                                    iFClose();
                                }});
                            }else{
                                showAlert("����ԤԼ��Ϣʧ�ܣ�",{},{"clkFun":function(){
                                }});
                            }
                        },'json');
					}else{
                        showAlert("����ԤԼ��Ϣʧ�ܣ�",{},{"clkFun":function(){
                        }});
                    }
                });
				jQuery.ajaxSetup({async:true});
			}});
		}	
		jQuery(function(){
			var flag = "${flag}";
			if(flag == 0 && ${xxdm} == 10026){
				jQuery("#buttonSave").hide();
			}

            jQuery("#div_zmr").hide();
            jQuery("input[type=radio][name=sfdszn]").click(function(){
                var sfdszn = jQuery("input[type=radio][name=sfdszn]:checked").val();
                if(sfdszn == '1'){
                    jQuery("#div_zmr").hide();
                }else{
                    jQuery("#div_zmr").show();
                }
            });
		});
		function checkEmail(obj) {
            var dzyx = jQuery(obj).val();
            if(!isEmail(dzyx) && dzyx!=""){
                showAlertDivLayer("��������ȷ�ĵ������䣡");
                return false;
            }
        }
		</script>
	</head>
	<body >
	
		<html:form styleId="form" action="/xlzx_yysq" method="post" >
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="xh" id="xh" value="${yysqInfo.xh}" />
			<input type="hidden" name="zgh" id="zgh" value="${yysqInfo.zgh}" />
			<input type="hidden" name="yyzxrq" id="yyzxrq" value="${yysqInfo.yyzxrq}" />

			<div style='height:550px;width:100%;overflow-y:auto;overflow-x:hidden'>

				<table width="100%" border="0" class="formlist">
					<thead>
					<tr>
						<th colspan="4">
							<span>ѧ��������Ϣ</span>
						</th>
					</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>

					<thead>
					<tr>
						<th colspan="4">
							<span>ѧ����д��Ϣ</span>
						</th>
					</tr>
					</thead>

					<logic:notEmpty name="xstxxx">
						<%@ include file="/xsgzgl/xlzx/yysq/viewXstxxx.jsp" %>
					</logic:notEmpty>
					<logic:empty name="xstxxx">
					<tbody id="xstxxx_tb">
						<tr>
							<th>
								<span class="red">*</span>���
							</th>
							<td >
								<label><input type="radio" name="hf" value="1" />��</label>
								<label><input type="radio" name="hf" value="0" />��</label>
							</td>
							<th>
								<span class="red">*</span>�Ƿ�����Ů
							</th>
							<td  >
								<label><input type="radio" name="sfyzn" value="1" />��</label>
								<label><input type="radio" name="sfyzn" value="0" />��</label>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>������Ů
							</th>
							<td  >
								<label><input type="radio" name="sfdszn" value="1" />��</label>
								<label><input type="radio" name="sfdszn" value="0" />��</label>
								<div id="div_zmr" style="margin-left: 10px;float: right" >
									����ˣ�<input type="text" name="zmr" style="width: 80px;" id="zmr" onblur="checkLen(this,2)">
								</div>
							</td>
							<th>
								<span class="red">*</span>��������
							</th>
							<td  >
								<input type="text" name="dzyx" id="dzyx"
									   maxlength="30"  onblur="checkEmail(this)" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>����ְҵ
							</th>
							<td >
								<input type="text" name="fqzy" id="fqzy" onblur="checkLen(this,50)"/>
							</td>
							<th>
								<span class="red">*</span>����ѧ��
							</th>
							<td  >
								<input type="text" name="fxl" id="fxl" onblur="checkLen(this,50)"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>ĸ��ְҵ
							</th>
							<td >
								<input type="text" name="mqzy" id="mqzy" onblur="checkLen(this,50)"/>
							</td>
							<th>
								<span class="red">*</span>ĸ��ѧ��
							</th>
							<td  >
								<input type="text" name="mxl" id="mxl" onblur="checkLen(this,50)"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��ͥ��ַ
							</th>
							<td >
								<input type="text" name="jtdz" id="jtdz" onblur="checkLen(this,50)"/>
							</td>
							<th>
								<span class="red">*</span>��Դ��
							</th>
							<td>
								<label><input type="radio" name="syd" value="cs" />����</label>
								<label><input type="radio" name="syd" value="xc" />�س�</label>
								<label><input type="radio" name="syd" value="nc" />����ũ��</label>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��ѯĿ��
								<br />
								<font color="red">(������500����)</font>
							</th>
							<td colspan="3">
							<textarea name="zxmd" id="zxmd" rows="4" cols=""
									  style="width: 98%" onblur="checkLen(this,500)"></textarea>
							</td>
						</tr>
					</tbody>
					</logic:empty>

				</table>
				<logic:equal value="1" name="sfscsq">
				<table width="100%" border="0" class="formlist" id="yytxxx_tb">
					<thead>
					<tr>
						<th colspan="2">
							<span>ԤԼ��д��Ϣ</span>
						</th>
					</tr>
					</thead>
					<tbody>
						<tr>
							<th rowspan="3" width="20%">һ�ܵ�����״̬</th>
							<td width="80%">
								<span class="red">*</span>���壺
								<label><html:radio property="qxztzt" value="hh">�ܺ�</html:radio></label>
								<label><html:radio property="qxztzt" value="jh">�Ϻ�</html:radio></label>
								<label><html:radio property="qxztzt" value="yb">һ��</html:radio></label>
								<label><html:radio property="qxztzt" value="jc">�ϲ�</html:radio></label>
								<label><html:radio property="qxztzt" value="hc">�ܲ�</html:radio></label>
							</td>
						</tr>
						<tr>
							<td>
								<span class="red">*</span>���ǣ�
								<label><html:radio property="qxztjl" value="yz">����</html:radio></label>
								<label><html:radio property="qxztjl" value="jz">����</html:radio></label>
								<label><html:radio property="qxztjl" value="y">�� &nbsp;&nbsp;</html:radio></label>
								<label><html:radio property="qxztjl" value="qw">��΢</html:radio></label>
								<label><html:radio property="qxztjl" value="w">��</html:radio></label>
							</td>
						</tr>
						<tr>
							<td>
								<span class="red">*</span>������
								<label><html:radio property="qxztyy" value="yz">����</html:radio></label>
								<label><html:radio property="qxztyy" value="jz">����</html:radio></label>
								<label><html:radio property="qxztyy" value="y">�� &nbsp;&nbsp;</html:radio></label>
								<label><html:radio property="qxztyy" value="qw">��΢</html:radio></label>
								<label><html:radio property="qxztyy" value="w">��</html:radio></label>
							</td>
						</tr>
						<tr>
							<th ><span class="red">*</span>�ϴ���ѯ��ĸı�</th>
							<td>
								<label><html:radio property="sczxhgb" value="hmx">������</html:radio></label>
								<label><html:radio property="sczxhgb" value="jmx">������</html:radio></label>
								<label><html:radio property="sczxhgb" value="yb">һ��</html:radio></label>
								<label><html:radio property="sczxhgb" value="jbmx">�ϲ�����</html:radio></label>
								<label><html:radio property="sczxhgb" value="bmx">������</html:radio></label>
							</td>
						</tr>
						<tr>
							<th ><span class="red">*</span>���Լ������״̬</th>
							<td>
								<label><html:radio property="zjzt" value="hmy">������</html:radio></label>
								<label><html:radio property="zjzt" value="jmy">������</html:radio></label>
								<label><html:radio property="zjzt" value="yb">һ��</html:radio></label>
								<label><html:radio property="zjzt" value="jbmc">�ϲ�����</html:radio></label>
								<label><html:radio property="zjzt" value="bmy">������</html:radio></label>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>������ѯ������
								<br />
								<font color="red">(������500����)</font>
							</th>
							<td>
								<html:textarea property="bczxwt" styleId="bczxwt" style="width:98%" rows="4" onblur="checkLen(this,500)" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�ϴ���ѯ�����������״̬����Ṧ��
								<br />
								<font color="red">(������500����)</font>
							</th>
							<td>
								<html:textarea property="zxhzt" styleId="zxhzt" style="width:98%" rows="4" onblur="checkLen(this,500)" />
							</td>
						</tr>
					</tbody>
				</table>
				</logic:equal>


				<table width="100%" border="0" class="formlist">
					<thead>
						<tr >
							<th colspan="4">
								<span>ԤԼ������Ϣ</span>
							</th>
						</tr>
					</thead>
						<tbody id="yysqInfo">
						<tr style="height:10px">
							<th  width="16%">
								ԤԼ��ѯ����
							</th>
							<td width="34%">
									<span class="red"><B>${ yysqInfo.yyzxrq}</B></span>
							</td>
						 	<th width="16%">
								<logic:equal value="2" name="pbfs"><span class="red">*</span></logic:equal>ԤԼ��ѯʱ��
							</th>
							<td width="34%">
								<logic:notEqual value="2" name="pbfs">
									<html:text property="qssj" styleId="qssj"   style="width:30%"  value="${yysqInfo.qssj}" onfocus="WdatePicker({dateFmt:'HH:mm',maxDate:'#F{$dp.$D(\\'jssj\\')}'})" readonly="true" />&nbsp;��&nbsp;
									<html:text property="jssj" styleId="jssj"   style="width:30%"  value="${yysqInfo.jssj}" onfocus="WdatePicker({dateFmt:'HH:mm',minDate:'#F{$dp.$D(\\'qssj\\')}'})" readonly="true" />
								</logic:notEqual>
								<logic:equal value="2" name="pbfs">
									<logic:notEqual value="10026" name="xxdm">
										<html:select styleId="sjddm" property="sjddm">
											<html:option value=""/>
											<html:options collection="sjddmList" labelProperty="sjdmc" property="sjddm"/>
										</html:select>
									</logic:notEqual>
									<logic:equal value="10026" name="xxdm">
										<logic:equal value="1" name="flag">
											<html:select styleId="sjddm" property="sjddm">
												<html:option value=""/>
												<html:options collection="sjddmList" labelProperty="sjdmc" property="sjddm"/>
											</html:select>
										</logic:equal>
										<logic:notEqual value="1" name="flag">
											<span style="color:red">��ǰ�޿�ԤԼ��ʱ���</span>
										</logic:notEqual>
									</logic:equal>
								</logic:equal>
							</td>
						</tr>
						
						<tr style="height:10px">	
							<th>
								<span class="red">*</span>Ԥ����ϵ����
							<td>
								<html:text property="xstell" styleId="xstell"  maxlength="11"  value="${ yysqInfo.xstell}" readonly="fasle" onblur="checkInputData(this);"/>
							</td>
							<th width="16%">
								ѧ�� ѧ��
							</th>
							<td width="34%">
								${currxn}&nbsp;&nbsp;${currxq}
							</td>
						</tr>
						<tr style="height:10px">
							<th>
								<span class="red">*</span>��ѯ����
							</th>
							<td colspan="3">
								<html:text property="yyzxzt" styleId="yyzxzt" style="width:98%"   maxlength="100"  value="${ yysqInfo.yyzxzt}" />
							</td>
						</tr>
						<tr style="height:10px">
						<th>��ѯ��Ҫ<br/>
								<font color="red"><B>(��500��)</B></font>
							</th>
							<td colspan="3">
									<html:textarea  property='yyzxxq' styleId="yyzxxq" style="word-break:break-all;width:99%" 
									value="${ yysqInfo.yyzxxq}" onblur="chLengs(this,500);" rows='6' />
							</td>
						</tr>
			
					</tbody>
					</table>
				</div>
				<table  border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="saveYysqInfo();return false;">
										�� ��
									</button>
									<button onclick="Close();return false;">
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

