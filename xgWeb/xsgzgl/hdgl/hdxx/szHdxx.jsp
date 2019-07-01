<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/hdgl/js/hdbljg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/jmf.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f.js"></script>
		<script type='text/javascript'>

            jQuery(function(){
                var hdxs = jQuery("#hdxs").val();
                if("�γ�" == hdxs){
                    jQuery("#jzlxTr").show();
                    jQuery("tr[name='zjrxx_tr']").hide();
                }else if("����" == hdxs){
                    jQuery("tr[name='zjrxx_tr']").show();
                    jQuery("#lx_span").html("��������");
                    jQuery("#con_span").html("��������");

                    jQuery("#jzlxTr").hide();
                }else{
                    jQuery("tr[name='zjrxx_tr']").hide();
                    jQuery("#jzlxTr").hide();
                }
                kcjbChange();
            });

            function changeHdxs(){
                var hdxs = jQuery("#hdxs").val();
                if("�γ�" == hdxs){
                    jQuery("#jzlxTr").show();
                    jQuery("tr[name='zjrxx_tr']").hide();
                    JzInfoEmpty();
                }else if("����" == hdxs){

                    jQuery("tr[name='zjrxx_tr']").show();
                    jQuery("#lx_span").html("��������");
                    jQuery("#con_span").html("��������");

                    jQuery("#jzlxTr").hide();
                    jQuery("#jzlx").val("");
                    jQuery("#zxkclx").val("");
                }else{
                    jQuery("tr[name='zjrxx_tr']").hide();
                    JzInfoEmpty();
                    jQuery("#jzlxTr").hide();
                    jQuery("#jzlx").val("");
                    jQuery("#zxkclx").val("");

                }
            }
            function JzInfoEmpty(){
                var tr = jQuery("tr[name='zjrxx_tr']");
                tr.hide();
                tr.find("input").val("");
                tr.find("textarea").val("");
                tr.find("select").val("");
                jQuery("#lx_span").html("�����");
                jQuery("#con_span").html("����ݼ��ĵ�");
            }
            /**
             * �γ̼���change
             */
            function kcjbChange(){
                var v = jQuery("#jzlx").val();
                if("003" == v){
                    jQuery("#zxkclxTh").removeAttr("style");
                    jQuery("#zxkclxTd").removeAttr("style");
                } else {
                    jQuery("#zxkclxTh").attr("style","display:none");
                    jQuery("#zxkclxTd").attr("style","display:none");
                }
            }

			var i = 0;
			function addRow(){
				i++;
				var tr = "<tr><td align='center'><input type='checkbox' name='ckr' /></td>" +
				"<td><select name='jdlxs'><option value='1'>ѧ��</option><option value='2'>��ʦ</option></select></td>" +
				"<td><input type='text' name='jdmcs' maxlength='20' style='width:80px'></td><td><input type='text' name='jdkssjs' id='kssjs"+i+"' maxlength='20' onfocus='showCalendar(\"kssjs"+i+"\",\"y-mm-dd\");' style='width:80px'></td><td><input type='text' name='jdjssjs' id='jssjs"+i+"' maxlength='20' onfocus='showCalendar(\"jssjs"+i+"\",\"y-mm-dd\");' style='width:80px'></td>"+
				"<td><select name='sfsljxs' style='width:80px'><option value='0'>��</option><option value='1'>��</option></select></td><td><select name='sfsldfs' style='width:80px'><option value='0'>��</option><option value='1'>��</option></select></td><td><select name='sfslxfs' style='width:80px'><option value='0'>��</option><option value='1'>��</option></select></td>";
				tr+="<td><select name='sftjs' style='width:80px'><option value='0'>��</option><option value='1'>��</option></select></td></tr>";
				jQuery("#jdsz").append(tr);
			}

			function delRow(){
				var checkboxs = jQuery("input[name='ckr']:checked");
				if(checkboxs.length > 0){
					jQuery(checkboxs).each(function(){
						jQuery(this).parents("tr").eq(0).remove();
					})
				}else{
					showAlertDivLayer("�빴ѡҪɾ���ļ�¼��");
					return false;
				}
			}

			//���ӽ���
			function addRowJx(){
				var tr = "<tr><td align='center' style='width:10%'><input type='checkbox' name='ckrs' /></td>" +
				"<td style='width:30%'><input type='text' name='jxmcs' maxlength='20' style='width:100%'/></td>" +
				"<td style='width:60%'><input type='text' name='jxsms' maxlength='1000' style='width:100%'/></td></tr>";
				jQuery("#jxsz").append(tr);
			}

			//ɾ������
			function delRowJx(){
				var checkboxs = jQuery("input[name='ckrs']:checked");
				if(checkboxs.length > 0){
					jQuery(checkboxs).each(function(){
						jQuery(this).parents("tr").eq(0).remove();
					})
				}else{
					showAlertDivLayer("�빴ѡҪɾ���ļ�¼��");
					return false;
				}
			}

			//��������
			function saveSz(){
				var checkboxs = jQuery("[name='hdbqs']:checked").length;
				if(checkboxs < 1){
					showAlertDivLayer("��ѡ����ǩ��");
					return false;
				}
                var hdxs = jQuery("#hdxs").val();
                var jzlx = jQuery("#jzlx").val();
                var ids = "zbf-hdlx-hddd-xsxxlx-hdkclx-hdxs-jzjb";
                if("����" == hdxs){
                    ids += "-zjrxm-zjrdw-zjrzc-zjrzw";
                }
                if(!checkNotNull(ids)){
                    showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
                    return false;
                }
                var nlbqLen = jQuery("[name='nlbqs']:checked").length;
                if(nlbqLen > 3){
                    showAlert("������ǩ���ֻ��ѡ��������ȷ�ϣ�");
                    return false;
                }
                if("�γ�" == hdxs){
                    if(jzlx == null || jzlx == ''){
                        showAlert("��ѡ��γ̼���");
                        return false;
                    } else {
                        var zxkclx = jQuery("#zxkclx").val();
                        if(zxkclx == ""){
                            showAlert("��ѡ����ѡ�γ����ͣ�");
                            return false;
                        }
                    }
                }

                if(jQuery(".MultiFile-label").length < 1){
                    showAlert("���ϴ�����");
                    return false;
                }

				var hdjdmcs = jQuery("[name='jdmcs']");
				var kssjs = jQuery("[name='jdkssjs']");
				var jssjs = jQuery("[name='jdjssjs']");

				var flg = true;
				
				jQuery(hdjdmcs).each(function(){
					if((!!jQuery(this).val()) == false){
						flg = false;
						return;
					}
				});
				if(!flg){
					showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
					return false;
				}
				
				jQuery(kssjs).each(function(){
					if((!!jQuery(this).val()) == false){
						flg = false;
						return;
					}
				});
				if(!flg){
					showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
					return false;
				}

				jQuery(jssjs).each(function(){
					if((!!jQuery(this).val()) == false){
						flg = false;
						return;
					}
				});
				if(!flg){
					showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
					return false;
				}

				if(trim(jQuery("#jdsz").html())== null || trim(jQuery("#jdsz").html()) == ""){
					showAlert("�����û�׶Σ�");
					return false;
				}

				if(!!(jQuery("#jxsz"))){
					jQuery("[name='jxmcs']").each(function(){
						if(!!jQuery(this).val() == false){
							flg = false;
							return;
						}
					})
				}

				if(!flg){
					showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
					return false;
				}

				var url = "hdgl_hdxx.do?method=saveSz";
				ajaxSubFormWithFun("hdxxForm", url, function(data) {
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
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/hdgl_hdxx" method="post" styleId="hdxxForm" onsubmit="return false;">
		<html:hidden property="hdid" styleId="hdid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 500px' >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th  colspan="4">
								<span>���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th width="15%">
							<span><font color="red">*</font>�����</span>
						</th>
						<td width="35%">
								${data.hdmc}
						</td>
						<th width="15%">
							<span><font color="red">*</font>�ʱ��</span>
						</th>
						<td width="35%">
								${data.hdkssj} �� ${data.hdjssj}
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>���췽
						</th>
						<td >
							<html:text property="zbf" styleId="zbf" maxlength="50"/>
						</td>
						<th width="15%">
							<font color="red">*</font>��ص�
						</th>
						<td width="35%">
							<html:text property="hddd" styleId="hddd" maxlength="20"/>
						</td>
					</tr>
					<tr>
						<th width="15%">
							<span><font color="red">*</font>����or���»</span>
						</th>
						<td width="35%">
							<html:select property="xsxxlx" styleId="xsxxlx"  style="width:173px">
								<html:option value="">--��ѡ��--</html:option>
								<html:option value="����">���ϻ</html:option>
								<html:option value="����">���»</html:option>
							</html:select>
						</td>
						<th width="15%">
							<span><font color="red">*</font>���ʽ</span>
						</th>
						<td width="35%">
							<html:select property="hdkclx" styleId="hdkclx"  style="width:173px">
								<html:option value="">--��ѡ��--</html:option>
								<html:option value="������">�����ࣨ��׶Σ�</html:option>
								<html:option value="������">�����ࣨ���׶Σ�</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<th width="15%">
							<span><font color="red">*</font>�����</span>
						</th>
						<td width="35%">
							<html:select property="hdxs" styleId="hdxs" onchange="changeHdxs()" style="width:173px">
								<html:option value="">--��ѡ��--</html:option>
								<html:option value="�">�</html:option>
								<html:option value="�γ�">�γ�</html:option>
								<html:option value="����">����</html:option>
							</html:select>
						</td>
						<th>
							<font color="red">*</font><span id="lx_span">�����</span>
						</th>
						<td>
							<html:select property="hdlx" styleId="hdlx" style="width:173px">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="hdlxList" labelProperty="hdlxmc" property="hdlxdm"/>
							</html:select>
						</td>
					</tr>

					<tr name="zjrxx_tr">
						<th>
							<font color="red">*</font>����������
						</th>
						<td>
							<html:text property="zjrxm" styleId="zjrxm" maxlength="10"/>
						</td>
						<th >
							<font color="red">*</font>�����˵�λ
						</th>
						<td >
							<html:text property="zjrdw" styleId="zjrdw" maxlength="20"/>
						</td>
					</tr>
					<tr name="zjrxx_tr">
						<th>
							<font color="red">*</font>������ְ��
						</th>
						<td>
							<html:select property="zjrzc" styleId="zjrzc" style="width:173px">
								<html:option value="">--��ѡ��--</html:option>
								<html:options collection="zjrzcList" labelProperty="mc" property="dm"/>
							</html:select>
						</td>
						<th >
							<font color="red">*</font>������ְ��
						</th>
						<td >
							<html:text property="zjrzw" styleId="zjrzw" maxlength="10"/>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>�����

						</th>
						<td colspan="3">
							<html:select property="jzjb" styleId="jzjb" style="width:173px">
								<html:option value="">--��ѡ��--</html:option>
								<html:option value="У���">У���</html:option>
								<html:option value="Ժ���">Ժ���</html:option>
								<html:option value="��ѡ�">��ѡ�</html:option>
							</html:select>
						</td>

					</tr>
					<tr name="zjrxx_tr">
						<th>
							�����˽���
							<br><font color="red">(��100��)</font>
						</th>
						<td colspan="3">
							<html:textarea rows="2" property="zjrjs" styleId="zjrjs"
										   style="width:95%" onblur="checkLen(this,100);"/>
						</td>
					</tr>


					<tr id="jzlxTr">
						<th>
							<font color="red">*</font>�γ̼���
						</th>
						<td>
							<html:select property="jzlx" styleId="jzlx" style="width:173px" onchange="kcjbChange()">
								<html:option value="">---&nbsp;��ѡ��γ̼���&nbsp;---</html:option>
								<html:options collection ="jzlxList" property="jzlxdm" labelProperty="jzlxmc" />
							</html:select>
						</td>
						<th id="zxkclxTh" style="display: none;">
							<font color="red">*</font>��ѡ�γ�����
						</th>
						<td id="zxkclxTd" style="display: none;">
							<html:select property="zxkclx" styleId="zxkclx" style="width:173px">
								<html:option value="">---&nbsp;��ѡ����ѡ�γ�����&nbsp;---</html:option>
								<html:options collection ="zxckclxList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							<font color='red'>*</font>���ǩ
						</th>
						<td colspan="3">
							<logic:iterate name="activityLabelList" id="bq">
								<%--<html:checkbox property="hdbqs" value="${bq.dm}">${bq.mc}</html:checkbox>--%>
								<label><html:multibox property="hdbqs" value="${bq.hdbqdm}"/>${bq.hdbqmc}</label>
							</logic:iterate>
						</td>
					</tr>
					<tr>
						<th>
							������ǩ

						</th>
						<td colspan="3">
							<logic:iterate name="abilityLabelList" id="bq">
								<label><html:multibox property="nlbqs" value="${bq.nlbqdm}"/>${bq.nlbqmc}</label>
							</logic:iterate>
							<br><font color="red">��ѡ��μӸû���������ۺ�������������</font>
						</td>
					</tr>
					<tr>
						<th>
							�Ƿ���Ҫѧ������
						</th>
						<td >
							<html:select property="sflx" styleId="sflx" style="width:50%">
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
						<th>
							�Ƿ��еڶ�����ѧ��
						</th>
						<td >
							<html:select property="sfdektxf" styleId="sfdektxf" style="width:50%">
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
					</tr>

					<tr>
						<th>
							ǩ����ʼʱ��
						</th>
						<td >
							<html:text property="qdkssj" styleId="qdkssj"  style="width:120px;"
									   onfocus="showCalendar('qdkssj','yyyy-MM-dd HH:mm',true,'qdjssj');"/>
						</td>
						<th>
							ǩ������ʱ��
						</th>
						<td >
							<html:text property="qdjssj" styleId="qdjssj"
									   onfocus="showCalendar('qdjssj','yyyy-MM-dd HH:mm',false,'qdkssj');"/>
						</td>
					</tr>
					<tr>
						<th>
							ǩ�˿�ʼʱ��
						</th>
						<td >
							<html:text property="qtkssj" styleId="qtkssj"  style="width:120px;"
									   onfocus="showCalendar('qtkssj','yyyy-MM-dd HH:mm',false,'qdkssj');"/>
						</td>
						<th>
							ǩ�˽���ʱ��
						</th>
						<td >
							<html:text property="qtjssj" styleId="qtjssj"
									   onfocus="showCalendar('qtjssj','yyyy-MM-dd HH:mm',false,'qtkssj');"/>
						</td>
					</tr>

					<tr>
						<th>
							<span><font color="red">*</font>����</span>
						</th>
						<td colspan="3">
							<html:hidden property="fjpath" styleId="fjpath"/>
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
                                //���ø���
                                jQuery(function(){
                                    jQuery.MultiUploader({
                                        maxcount : 3,
                                        //��׺
                                        accept : 'png|gif|jpg|zip|rar|doc|docx',
                                        //����ļ���С ��λM
                                        maxsize: 10,
                                        //��Ÿ������������id
                                        elementid : 'fjpath'
                                    });
                                });
							</script>
						</td>
					</tr>
					</tbody>
					
					<thead>	
				    	<tr>
				        	<th colspan="4"><span>��������</span>&nbsp;&nbsp;&nbsp;&nbsp;
				        	<logic:equal value="1" name="sfksz">
					        	<a href="#" onclick="addRowJx();"><img src="images/knsrd/jiahao.gif" alt="����" /></a>&nbsp;
					        	<a href="#" onclick="delRowJx();"><img src="images/knsrd/jianhao.gif" alt="ɾ��" /></a>&nbsp;
				        	</logic:equal>
				        </tr>
		    		</thead>
		    		
		    		<tbody>
		    			<tr>
	    					<td colspan="4">
	    						<table style="width:100%">
	    							<thead>
										<tr>
											<logic:equal value="1" name="sfksz">
												<td style="width:5%"></td>
											</logic:equal>
											<td style="width:20%">
												<logic:equal value="1" name="sfksz">
													<font color='red'>*</font>
												</logic:equal>
												��������
											</td>
											<td style="width:75%">
												����˵��
											</td>
										</tr>
									</thead>
									<tbody id="jxsz">
										<logic:equal value="1" name="sfksz">
											<logic:iterate id="hdjx" name="hdjxList">
												<tr>
													<td align='center' style="width:10%"><input type='checkbox' name='ckrs' /></td>
													<td style="width:30%">
														<input type="text" name="jxmcs" value="${hdjx.jxmc}" maxlength="30" style="width:100%"/>
													</td>
													<td style="width:60%">
														<input type='text' name='jxsms' maxlength='1000' value="${hdjx.jxsm}" style="width:100%"/>
													</td>
												</tr>
											</logic:iterate>
										</logic:equal>
										<logic:equal value="2" name="sfksz">
											<logic:iterate id="hdjx" name="hdjxList">
												<tr>
													<td style="width:100px">
														${hdjx.jxmc}
													</td>
													<td style="width:100px">
														${hdjx.jxsm}
													</td>
												</tr>
											</logic:iterate>
										</logic:equal>
									</tbody>
	    						</table>
	    					</td>
		    				</tr>

		    		</tbody>
					
						<thead>	
					    	<tr>
					        	<th colspan="4"><span>�׶�����</span>&nbsp;&nbsp;&nbsp;&nbsp;
					        	<logic:equal value="1" name="sfksz">
						        	<a href="#" onclick="addRow();"><img src="images/knsrd/jiahao.gif" alt="����" /></a>&nbsp;
						        	<a href="#" onclick="delRow();"><img src="images/knsrd/jianhao.gif" alt="ɾ��" /></a>&nbsp;
					        	</logic:equal>
					        </tr>
		    			</thead>
		    			<tbody>
		    				<tr>
		    					<td colspan="4">
		    						<table style="width:100%">
		    							<thead>
											<tr>
												<logic:equal value="1" name="sfksz">
													<td style="width:5%"></td>
												</logic:equal>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													�׶�����
												</td>
												<td style="width:15%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													�׶�����
												</td>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">													
														<font color='red'>*</font>
													</logic:equal>
													��ʼʱ��
												</td>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													��ֹʱ��
												</td>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													��������
												</td>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													�������
												</td>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													����ѧ��
												</td>
												<td style="width:10%">
													<logic:equal value="1" name="sfksz">
														<font color='red'>*</font>
													</logic:equal>
													�Ƿ�׶���ת
												</td>
											</tr>
										</thead>
										<tbody id="jdsz">
											<logic:equal value="1" name="sfksz">
												<logic:iterate id="hdjd" name="hdjdList">
													<tr>
														<td align='center'><input type='checkbox' name='ckr' /></td>
														<td>
															<select name='jdlxs'>
																<logic:equal name="hdjd" property="jdlx" value="1">
																	<option value='1'>ѧ��</option>
																</logic:equal>
																<logic:equal name="hdjd" property="jdlx" value="2">
																	<option value='2'>��ʦ</option>
																</logic:equal>
															</select>
														</td>
														<td>
															<input type='text' name='jdmcs' maxlength='20' value="${hdjd.jdmc}" style="width:80px"/>
														</td>
														<td>
															<input id="jdkssj_${hdjd.jdsx}" type="text" name="jdkssjs" maxlength='15' value="${hdjd.jdkssj}" onfocus="showCalendar('jdkssj_${hdjd.jdsx}','y-mm-dd')" style="width:80px" />
														</td>
														<td>
															<input id="jdjssj_${hdjd.jdsx}" type="text" name='jdjssjs' maxlength='15' value="${hdjd.jdjssj}" onfocus="showCalendar('jdjssj_${hdjd.jdsx}','y-mm-dd')" style="width:80px" />
														</td>
														<td>
															<select name='sfsljxs' style="width:80px">
																<logic:equal name="hdjd" property="sfsljx" value="0">
																	<option value='0' selected='selected'>��</option>
																	<option value='1'>��</option>
																</logic:equal>
																<logic:equal name="hdjd" property="sfsljx" value="1">
																	<option value='0'>��</option>
																	<option value='1' selected='selected'>��</option>
																</logic:equal>
																<logic:empty name="hdjd" property="sfsljx">
																	<option value='0'>��</option>
																	<option value='1'>��</option>
																</logic:empty>
															</select>
														</td>
														<td>
															<select name='sfsldfs' style="width:80px">
																<logic:equal name="hdjd" property="sfsldf" value="0">
																	<option value='0' selected='selected'>��</option>
																	<option value='1'>��</option>
																</logic:equal>
																<logic:equal name="hdjd" property="sfsldf" value="1">
																	<option value='0'>��</option>
																	<option value='1' selected='selected'>��</option>
																</logic:equal>
																<logic:empty name="hdjd" property="sfsldf">
																	<option value='0'>��</option>
																	<option value='1'>��</option>
																</logic:empty>
															</select>
														</td>
														<td>
															<select name='sfslxfs' style="width:80px">
																<logic:equal name="hdjd" property="sfslxf" value="0">
																	<option value='0' selected='selected'>��</option>
																	<option value='1'>��</option>
																</logic:equal>
																<logic:equal name="hdjd" property="sfslxf" value="1">
																	<option value='0'>��</option>
																	<option value='1' selected='selected'>��</option>
																</logic:equal>
																<logic:empty name="hdjd" property="sfslxf">
																	<option value='0'>��</option>
																	<option value='1'>��</option>
																</logic:empty>
															</select>
														</td>
														<td>
															<select name='sftjs' style="width:80px">
																<option value='0' <logic:equal name="hdjd" property="sftj" value="0">selected='selected'</logic:equal>>��</option>
																<option value='1' <logic:equal name="hdjd" property="sftj" value="1">selected='selected'</logic:equal>>��</option>
															</select>
														</td>
													</tr>
												</logic:iterate>
											</logic:equal>
											<logic:equal value="2" name="sfksz">
												<logic:iterate id="hdjd" name="hdjdList">
													<tr>
<%--														<td align='center'><input type='checkbox' name='ckr' /></td>--%>
														<td style="width:150px">
															<logic:equal name="hdjd" property="jdlx" value="1">
																ѧ��
															</logic:equal>
															<logic:equal name="hdjd" property="jdlx" value="2">
																��ʦ
															</logic:equal>
														</td>
														<td>
															${hdjd.jdmc}
														</td>
														<td style="width: 100px">
															${hdjd.jdkssj}
														</td>
														<td style="width: 100px">
															${hdjd.jdjssj}
														</td>
														<td style="width:100px">
															${hdjd.sfsljxmc}
														</td>
														<td style="width:100px">
															${hdjd.sfsldfmc}
														</td>
														<td style="width:100px">
															${hdjd.sfslxfmc}
														</td>
														<td style="width:100px">
															${hdjd.sftjmc}
														</td>
													</tr>
												</logic:iterate>
											</logic:equal>
										</tbody>
		    						</table>
		    					</td>
		    				</tr>
		    			</tbody>						
				 </table>			
				</div>
			  <div style="height:25px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="saveSz();">
									����
								</button>	 			
								<button type="button" onclick="iFClose();">
									�ر�
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

