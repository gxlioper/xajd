<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script language="javascript">
		//��Ƭ�ϴ�
		function uploadStuPic(){
			jQuery.ajaxFileUpload({
			  url:'general_xsxx_zxxs_ajax.do?method=uploadStuPic&xh=${rs.xh }',//�������˳���
			  secureuri:false,
			  fileElementId:'stuPic',//input���ID
			  async: false,
			  dataType: 'json',//������������
			  success: function (data){//�ϴ��ɹ�
				  jQuery('#stuImg').empty();
				  jQuery("#stuImg").html('<img src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }&flg=true" border="0" align="absmiddle" style="width:120px;height:160px" />').show();
			  }
			});
		}

		//��Ᵽ��ѧ����Ϣ
		function checkSaveXsxx(){

			var xm = jQuery("#xm").val();
			var xb = jQuery("input[name=xb]:checked").val();
			jQuery("#xb").val(xb);

			var jgshen = jQuery("#jgshen").val();
			var jgshi = jQuery("#jgshi").val();
			var jgxian = jQuery("#jgxian").val();

			var jg = jgshen + "/" + jgshi + "/" + jgxian;
			jQuery("#jg").val(jg);

			var sydshen = jQuery("#sydshen").val();
			var sydshi = jQuery("#sydshi").val();
			var sydxian = jQuery("#sydxian").val();
			
			var syd = sydshen + "/" + sydshi + "/" + sydxian;
			jQuery("#syd").val(syd);

			if(xm == ""){
				alertError("��������Ϊ�գ���ȷ��");
				return false;
			}else if(xm == ""){
				alertError("�Ա���Ϊ�գ���ȷ��");
				return false;
			}
			confirmInfo('��Ҫִ�б������������ȷ��',saveXsxx);
		}

		//����ѧ����Ϣ
		function saveXsxx(tag){

			if(tag == "ok"){
				jQuery("#addPic").html("");
				
				jQuery.ajaxSetup({async:false});
				
				var url = "general_xsxx_zxxs_ajax.do?method=saveXsxx";
	
			 	//����һ��json����
				var parameter={};
				
				//ָ����ȡ�Ŀؼ����ͣ�����ѭ��
				var hid_obj=jQuery("input").each(function(){
					
					//��ȡ���ؼ�name
					var name=jQuery(this).attr("name");
					//����json����
					parameter[name]=escape(jQuery(this).val());
				});
	
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,
					parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}
		</script>
	</head>
	<body onload="" >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<html:form action="/general_xsxx" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab" style="width:100%;height:435px;overflow-x:hidden;overflow-y:auto;">
				
				<!-- ������Ϣ -->
				<table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="5" style="cursor:hand" onclick="hiddenMk('tb_jbxx')">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_jbxx">
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="24%">
								${rs.xh }
								<input type="hidden" name="str_xh"  id="xh" value="${rs.xh }" />
							</td>
							<th width="15%">
								<font color="red">*</font>����
							</th>
							<td width="25%">
								<input type="text" name="str_xm" style="width:180px" 
									id="xm" value="${rs.xm }" maxlength="16"/>
							</td>
							<td rowspan="6">
								<div id="stuImg">
									<img style="width:120px;height:160px" 
										src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
										border="0">
								</div>
								<br />
								<button type="button"
									onclick='tipsWindown("ϵͳ��ʾ","id:addPic","380","130","true","","true","id");'
									style="width:100px" class="btn_01">
									�ϴ���Ƭ
								</button>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�Ա�
							</th>
							<td>
								<input type="radio" name="xb" value="��"
									<logic:equal name="rs" property="xb" value="��">
									checked="checked"
									</logic:equal>
								/>��
								<input type="radio" name="xb" value="Ů"
									<logic:equal name="rs" property="xb" value="Ů">
									checked="checked"
									</logic:equal>
								/>Ů
								<input type="hidden" name="str_xb"  id="xb" value="${rs.xb }" />
							</td>
							<th>
								��������
							</th>
							<td>
								<input type="text" name="str_csrq" id="csrq" 
									style="width:180px" readonly
									onclick="showCalendar(this.id,'ymmdd')" 
									value="${rs.csrq}"/>
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								<input type="hidden" name="str_mz"  id="mz" value="${rs.mz }" />
								<html:select name="rs" property="mz" style="width:180px" onchange="$('mz').value=this.value"
									  onmouseover="">
									<html:options collection="mzList" property="mzdm" labelProperty="mzmc" />
								</html:select>
							</td>
							<th>
								������ò
							</th>
							<td>
								<input type="hidden" name="str_zzmm"  id="zzmm" value="${rs.zzmm }" />
								<html:select name="rs" property="zzmm" style="width:180px" styleId="zzmm" onchange="$('zzmm').value=this.value"
								  onmouseover="">
									<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								���֤��
							</th>
							<td>
								<input type="text" name="str_sfzh" 
									style="width:180px" 
									id="sfzh" value="${rs.sfzh }" maxlength="18"/>
							</td>
							<th>
								
							</th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td colspan="3">
								<input type="hidden" name="str_jg"  id="jg" value="${rs.jg }" />
								<html:select name="rs" property="jgshen" styleId="jgshen"
									style="width:130px"   onmouseover=""
									onchange="loadShi('jgshen','jgshi','jgxian');">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="ssList" property="ssdm" labelProperty="ssmc" />
								</html:select>
								ʡ
								<html:select name="rs" property="jgshi" 
									style="width:130px"   onmouseover=""
									styleId="jgshi" onchange="loadXian('jgshi','jgxian')">
									<html:options collection="jgshiList" property="shidm" labelProperty="shimc" />
								</html:select>
								��
								<html:select name="rs" property="jgxian" 
									style="width:130px"   onmouseover=""
									styleId="jgxian">
									<html:options collection="jgxianList" property="xiandm" labelProperty="xianmc" />
								</html:select>
								��/��
							</td>
						</tr>
						<tr>
							<th>
								��Դ����(��Դ��)
							</th>
							<td colspan="3">
								<input type="hidden" name="str_syd"  id="syd" value="${rs.syd }" />
								<html:select name="rs" property="sydshen" styleId="sydshen"
									style="width:130px"   onmouseover=""
									onchange="loadShi('sydshen','sydshi','sydxian');">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="ssList" property="ssdm" labelProperty="ssmc" />
								</html:select>
								ʡ
								<html:select name="rs" property="sydshi" 
									style="width:130px"   onmouseover=""
									styleId="sydshi" onchange="loadXian('sydshi','sydxian')">
									<html:options collection="syshiList" property="shidm" labelProperty="shimc" />
								</html:select>
								��
								<html:select name="rs" property="sydxian" 
									style="width:130px"   onmouseover=""
									styleId="sydxian">
									<html:options collection="syxianList" property="xiandm" labelProperty="xianmc" />
								</html:select>
								��/��
							</td>
						</tr>
					</tbody>
			    </table>
		   		<!-- ������Ϣ  end-->
		   		
		   		<!-- ��У��Ϣ -->
		    	<table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="5" style="cursor:hand" onclick="hiddenMk('tb_zxxx')">
								<span>��У��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_zxxx">
						<tr>
							<th width="16%">
								�꼶
							</th>
							<td width="24%">
								${rs.nj }
							</td>
							<th width="15%">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td  colspan="2">
								${rs.xymc }
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td>
								${rs.zymc }
							</td>
							<th>
								�༶
							</th>
							<td colspan="2">
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								${rs.xz }
							</td>
							<th>
								
							</th>
							<td colspan="2">
								
							</td>
						</tr>
					</tbody>
			    </table>
			    <!-- ��У��Ϣ end -->
			    
			    <!-- ������Ϣ -->
		    	<table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="5" style="cursor:hand" onclick="hiddenMk('tb_qtxx')">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_qtxx" style="display:none">
						<tr>
							<th width="16%">
								�ֻ�����
							</th>
							<td width="24%">
								<input type="text" name="str_sjhm" id="sjhm" 
									onkeydown="return onlyNum(this,20)"
									onmousedown="return onlyNum(this,20)"
									maxlength="20" 
									style="width : 180px;ime-mode:disabled"
									value="${rs.sjhm }" 
								/>
							</td>
							<th width="15%">
								QQ����
							</th>
							<td  colspan="2">
								<input type="text" name="str_qqhm" id="qqhm" 
									onkeydown="return onlyNum(this,20)"
									onmousedown="return onlyNum(this,20)"
									maxlength="20" 
									style="width : 180px;ime-mode:disabled"
									value="${rs.qqhm }" 
								/>
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td colspan="4">
								<input type="text" name="str_dzyx" id="dzyx"
									style="width:500px"
									value="${rs.dzyx}" maxlength="32"/>
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<input type="hidden" name="str_yhdm"  id="yhdm" value="${rs.yhdm }" />
								<html:select name="rs" property="yhdm"  style="width:180px;" onchange="$('yhdm').value=this.value"
									  onmouseover="">
									<html:option value=""></html:option>
									<html:options collection="yhList" property="yhdm" labelProperty="yhmc" />
								</html:select>
							</td>
							<th>
								���п���
							</th>
							<td colspan="2">
								<input type="text" name="str_yhkh" id="yhkh" 
									onkeydown="return onlyNum(this,20)"
									onmousedown="return onlyNum(this,20)"
									maxlength="20" 
									style="width : 180px;ime-mode:disabled"
									value="${rs.yhkh }" 
								/>
							</td>
						</tr>
					</tbody>
			    </table>
			    <!-- ������Ϣ end -->
			    
			    <!-- ��ͥ��Ϣ -->
		    	<table width="100%" border="0" class="formlist">	
					<thead>
						<tr>
							<th colspan="5" style="cursor:hand" onclick="hiddenMk('tb_jtxx')">
								<span>��ͥ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_jtxx" style="display:none">
						<tr>
							<th width="16%">
								��ͥ�绰
							</th>
							<td width="24%">
								<input type="text" name="str_lxdh1" id="lxdh1" 
									onkeydown="return onlyNum(this,20)"
									onmousedown="return onlyNum(this,20)"
									maxlength="20" 
									style="width : 180px;ime-mode:disabled"
									value="${rs.lxdh1 }" 
								/>
							</td>
							<th width="15%">
								��ͥ�ʱ�
							</th>
							<td  colspan="2">
								<input type="text" name="str_yb" id="yb" 
									onkeydown="return onlyNum(this,10)"
									onmousedown="return onlyNum(this,10)"
									maxlength="10" 
									style="width : 180px;ime-mode:disabled"
									value="${rs.yb }" 
								/>
							</td>
						</tr>
						<tr>
							<th>
								��ͥ��ַ
							</th>
							<td colspan="4">
								<input type="text" name="str_jtszd" id="jtszd" 
									style="width:500px"
									value="${rs.jtszd }" 
									maxlength="25"/>
							</td>
						</tr>
						<tr>
							<td colspan="5">
								<table width="100%">
									<!-- ��ͥ��Ա1 -->
									<tbody>
										<tr>
											<td colspan="4" align="center">
												<span>��ͥ��Ա1</span>
											</td>
										</tr>
										<tr>
											<th width="16%">
												����
											</th>
											<td width="24%">
												<input type="text" name="str_jtcy1_xm" id="jtcy1_xm" 
													style="width:180px" 
													value="${rs.jtcy1_xm }" maxlength="25"/>
											</td>
											<th width="15%">
												�뱾�˹�ϵ
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_gx" id="jtcy1_gx" 
													style="width:180px" 
													value="${rs.jtcy1_gx}" maxlength="10"/>
											</td>
										</tr>
										<tr>
											<th width="">
												��������
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_nl" id="jtcy1_nl" 
													style="width:180px" readonly
													onclick="showCalendar(this.id,'ymmdd')" 
													value="${rs.jtcy1_nl}"/>
											</td>
											<th width="">
												���֤��
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_sfzh" id="jtcy1_sfzh" 
													style="width:180px" 
													value="${rs.jtcy1_sfzh }" maxlength="18"/>
											</td>
										</tr>
										<tr>
											<th width="">
												����
											</th>
											<td width="">
												<input type="hidden" name="str_jtcy1_mz"  id="jtcy1_mz" value="${rs.jtcy1_mz }" />
												<html:select name="rs" property="jtcy1_mz" 
													style="width:180px"  onmouseover=""
													onchange="$('jtcy1_mz').value=this.value">
													<html:options collection="mzList" property="mzdm" labelProperty="mzmc" />
												</html:select>
											</td>
											<th width="">
												������ò
											</th>
											<td width="">
												<input type="hidden" name="str_jtcy1_zzmm"  id="jtcy1_zzmm" value="${rs.jtcy1_zzmm }" />
												<html:select name="rs" property="jtcy1_zzmm" 
													style="width:180px"    onmouseover=""
													onchange="$('jtcy1_zzmm').value=this.value">
													<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
												</html:select>
											</td>
										</tr>
										<tr>
											<th width="">
												ְҵ
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_zy" id="jtcy1_zy" 
													style="width:180px" 
													value="${rs.jtcy1_zy }" maxlength="10"/>
											</td>
											<th width="">
												ְ��
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_zw" id="jtcy1_zw" 
													style="width:180px" 
													value="${rs.jtcy1_zw }" maxlength="10"/>
											</td>
										</tr>
										<tr>
											<th width="">
												������λ�绰
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_lxdh1" id="jtcy1_lxdh1" 
													onkeydown="return onlyNum(this,20)"
													onmousedown="return onlyNum(this,20)"
													maxlength="20" 
													style="width:180px;ime-mode:disabled"
													value="${rs.jtcy1_lxdh1 }" 
												/>
											</td>
											<th width="">
												���˵绰
											</th>
											<td width="">
												<input type="text" name="str_jtcy1_lxdh2" id="jtcy1_lxdh2" 
													onkeydown="return onlyNum(this,20)"
													onmousedown="return onlyNum(this,20)"
													maxlength="20" 
													style="width:180px;ime-mode:disabled"
													value="${rs.jtcy1_lxdh2 }" 
												/>
											</td>
										</tr>
										<tr>
											<th width="">
												������ַ
											</th>
											<td width="" colspan="3">
												<input type="text" name="str_jtcy1_gzdz" id="jtcy1_gzdz" 
													style="width:500px" 
													value="${rs.jtcy1_gzdz }" maxlength="25"/>
											</td>
										</tr>
									</tbody>
									<!-- ��ͥ��Ա1 end-->
									
									<!-- ��ͥ��Ա2-->
									<tbody>
										<tr>
											<td colspan="4" align="center">
												<span>��ͥ��Ա2</span>
											</td>
										</tr>
										<tr>
											<th width="16%">
												����
											</th>
											<td width="24%">
												<input type="text" name="str_jtcy2_xm" id="jtcy2_xm" 
													style="width:180px" 
													value="${rs.jtcy2_xm }" maxlength="25"/>
											</td>
											<th width="15%">
												�뱾�˹�ϵ
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_gx" id="jtcy2_gx" 
													style="width:180px" 
													value="${rs.jtcy2_gx}" maxlength="10"/>
											</td>
										</tr>
										<tr>
											<th width="">
												��������
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_nl" id="jtcy2_nl" 
													style="width:180px" readonly
													onclick="showCalendar(this.id,'ymmdd')" 
													value="${rs.jtcy2_nl}"/>
											</td>
											<th width="">
												���֤��
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_sfzh" id="jtcy2_sfzh" 
													style="width:180px" 
													value="${rs.jtcy2_sfzh }" maxlength="18"/>
											</td>
										</tr>
										<tr>
											<th width="">
												����
											</th>
											<td width="">
												<input type="hidden" name="str_jtcy2_mz"  id="jtcy2_mz" value="${rs.jtcy2_mz }" />
												<html:select name="rs" property="jtcy2_mz" 
													style="width:180px"   onmouseover=""
													onchange="$('jtcy2_mz').value=this.value">
													<html:options collection="mzList" property="mzdm" labelProperty="mzmc" />
												</html:select>
											</td>
											<th width="">
												������ò
											</th>
											<td width="">
												<input type="hidden" name="str_jtcy2_zzmm"  id="jtcy2_zzmm" value="${rs.jtcy2_zzmm }" />
												<html:select name="rs" property="jtcy2_zzmm" 
													style="width:180px"   onmouseover=""
													onchange="$('jtcy2_zzmm').value=this.value">
													<html:options collection="zzmmList" property="zzmmdm" labelProperty="zzmmmc" />
												</html:select>
											</td>
										</tr>
										<tr>
											<th width="">
												ְҵ
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_zy" id="jtcy2_zy" 
													style="width:180px" 
													value="${rs.jtcy2_zy }" maxlength="10"/>
											</td>
											<th width="">
												ְ��
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_zw" id="jtcy2_zw" 
													style="width:180px" 
													value="${rs.jtcy2_zw }" maxlength="10"/>
											</td>
										</tr>
										<tr>
											<th width="">
												������λ�绰
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_lxdh1" id="jtcy2_lxdh1" 
													onkeydown="return onlyNum(this,20)"
													onmousedown="return onlyNum(this,20)"
													maxlength="20" 
													style="width:180px;ime-mode:disabled"
													value="${rs.jtcy2_lxdh1 }" 
												/>
											</td>
											<th width="">
												���˵绰
											</th>
											<td width="">
												<input type="text" name="str_jtcy2_lxdh2" id="jtcy2_lxdh2" 
													onkeydown="return onlyNum(this,20)"
													onmousedown="return onlyNum(this,20)"
													maxlength="20" 
													style="width:180px;ime-mode:disabled"
													value="${rs.jtcy2_lxdh2 }" 
												/>
											</td>
										</tr>
										<tr>
											<th width="">
												������ַ
											</th>
											<td width="" colspan="3">
												<input type="text" name="str_jtcy2_gzdz" id="jtcy2_gzdz" 
													style="width:500px" 
													value="${rs.jtcy2_gzdz }" maxlength="25"/>
											</td>
										</tr>
									</tbody>
									<!-- ��ͥ��Ա2 end-->
								</table>
							</td>
						</tr>
					</tbody>
			    </table>
			    <!-- ��ͥ��Ϣ end -->
			    
		    </div>
		    <div>
		    	<table width="100%" border="0" class="formlist">
			    	<tfoot>
						<tr>
							<td colspan="2">
								<div class="btn">
									<button type="button" name="����" onclick="checkSaveXsxx();">����</button>
									<button type="button" name="�ر�" onclick="Close();return false;">�� ��</button>
								</div>
							</td>
						</tr>
				    </tfoot>
			    </table>
			</div>
			
			<!-- �ϴ���Ƭ -->
			<div id="addPic" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ϴ���Ƭ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="file" id="stuPic" name="stuPic" style="width:90%"
										   onchange='uploadStuPic();closeWindown();'
									/>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>