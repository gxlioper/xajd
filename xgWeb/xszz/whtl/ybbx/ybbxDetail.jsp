<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		<script language="javascript">	     
     function saveYbbx(){
     	
     	if($("xh") && $("xh").value==""){
     		
     		alertInfo("����ѡ��һ��ѧ��!",function(tag){
     		
     			if(tag=="ok"){
     			
     				return false;
     			}
     		});
     		return false;
     	}
     	
     	if(checkSaveInfo()){
     	
     		confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",saveBatch);
     	}
     }
     
     function saveYbbxSh(shzt){
     	
     	$("shzt").value=shzt;
     	confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",dgsh);
     }
     
     function saveBatch(tag){
     		
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
			
				//������ֶ�
				var save_array=new Array();
				save_array=jQuery("#save_array").val().split("-");
				
			    for(var i=0;i<save_array.length;i++){
						// ��ȡ��չ�ֶ������ֵ
						var zczx=save_array[i];
						
						// ����һ�����������Ϣ������;
						var array=new Array();
						
						jQuery("input[name="+zczx+"],textarea[name="+zczx+"]").each(function(j){
							
								array[j] =escape(jQuery(this).val());
							
						});
					
						//�����۲��������JSON����
						parameter["array_"+zczx]=array.join("!!array!!");
						
				}
				
				parameter["array_pkValue"]=jQuery("#xn").val()+"!!@@!!"+jQuery("#xh").val();
				
				parameter["str_xh"]=jQuery("#xh").val();
				
				parameter["xh"]=jQuery("#xh").val();
				
				parameter["str_xn"]=jQuery("#xn").val();
				
				parameter["xn"]=jQuery("#xn").val();
				
				var url = "xszzYbbxAjax.do?method=saveBatch";
	          	
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
		
		 function dgsh(tag){
     		
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
			
				//������ֶ�
				var save_array=new Array();
				save_array=jQuery("#save_array").val().split("-");
				
			    for(var i=0;i<save_array.length;i++){
						// ��ȡ��չ�ֶ������ֵ
						var zczx=save_array[i];
						
						// ����һ�����������Ϣ������;
						var array=new Array();
						
						jQuery("input[name="+zczx+"],textarea[name="+zczx+"]").each(function(j){
							
								array[j] =escape(jQuery(this).val());
							
						});
					
						//�����۲��������JSON����
						parameter["array_"+zczx]=array.join(" !!array!! ");
						
				}
				
				parameter["array_pkValue"]=jQuery("#xn").val()+"!!@@!!"+jQuery("#xh").val();
				
				parameter["str_xh"]=jQuery("#xh").val();
				
				parameter["xh"]=jQuery("#xh").val();
				
				parameter["str_xn"]=jQuery("#xn").val();
				
				parameter["xn"]=jQuery("#xn").val();
				
				parameter["xysh"]=escape(jQuery("#shzt").val());
				
				parameter["xyshyj"]=escape(jQuery("#xyshyj").val());
				
				parameter["xxsh"]=escape(jQuery("#shzt").val());
				
				parameter["xxshyj"]=escape(jQuery("#xxshyj").val());
				
				var url = "xszzYbbxAjax.do?method=dgsh";
	          	
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
		
		function deploy(id){
			document.getElementById(id).style.display = (document.getElementById(id).style.display == 'none') ? '' : 'none';  
		}
		jQuery(function(){
			onShow();
		})

</script>
	</head>
	<body >
		<script type="text/javascript" src="js/xszz/whtl/ybbxDetail.js"></script>
		<html:form action="/xszzYbbx" method="post">
			<input type="hidden" id="url" name="url"
				value="xszzYbbx.do?method=ybbxDetail" />
			<input type="hidden" id="tableName" name="tableName"
				value="xg_view_xszz_dxsybbx" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="xn" id="xn" value="${xn}" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="have" id="have" value="${have}" />
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<logic:notEqual name="doType" value="sh">
				<input type="hidden" name="save_array" id="save_array"
					value="bxje-mzyy-wzsj-ylyt" />
			</logic:notEqual>
			<logic:equal name="doType" value="sh">
				<input type="hidden" name="save_array" id="save_array"
					value="bxje-mzyy-wzsj-ylyt-shje" />
			</logic:equal>
			<input type="hidden" name="shzt" id="shzt" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
		
			<div style='width:98%;height:510px;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr onclick="deploy('tbody_jbxx')">
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th align="right" style="width: 10%">
								<font color="red">*</font>ѧ��
							</th>
							<logic:equal name="userOnLine" value="teacher" scope="session">
								<td align="left">

									<logic:equal name="doType" value="add">
										<html:text property="xh" styleId="xh" value="${rs.xh}" />
										<html:hidden property="xh" value="${rs.xh}" />
									</logic:equal>
									<logic:equal name="doType" value="save">
										<html:text property="xh" styleId="xh" value="${rs.xh}" />
									<html:hidden property="xh" value="${rs.xh}" />
									</logic:equal>
									<logic:notEqual name="doType" value="add">
										<logic:notEqual name="doType" value="save">
										${rs.xh}
										<html:hidden property="xh" styleId="xh" value="${rs.xh}" />
										</logic:notEqual>
									</logic:notEqual>

									<logic:equal name="doType" value="add">
										<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</logic:equal>
									<logic:equal name="doType" value="save">
										<button type="button" onclick="sendXx();return false;" class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</logic:equal>
								</td>
							</logic:equal>
							<logic:equal name="userOnLine" value="student" scope="session">
								<td align="left">
									${userName }
									<html:hidden name='rs' property="xh" styleId="xh"
										value="${userName}" />

								</td>
							</logic:equal>

							<th align="right" style="width: 10%">
								�꼶
							</th>
							<td align="left" style="width: 40%">
								${rs.nj}
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								����
							</th>
							<td align="left">
								${rs.xm}
							</td>
							<th align="right">
								�Ա�
							</th>
							<td align="left">
								${rs.xb }
							</td>
						</tr>

						<tr style="height:22px">
							<th align="right">
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								${rs.xymc}
							</td>
							<th align="right">
								רҵ
							</th>
							<td align="left">
								${rs.zymc }
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								�༶
							</th>
							<td align="left">
								${rs.bjmc }
							</td>
							<th>
								���
							</th>
							<td>
								${xn }
							</td>
						</tr>
					</tbody>
				</table>


				<div style='width:100%;height:300px;overflow:auto;overflow-x:hidden'>
					<h3 class="datetitle_01">
						<span onclick="deploy('tbody_bxxx')"> ҽ��������Ϣ </span>
					</h3>

					<table width="100%" border="0" class="formlist">
						<tbody id="tbody_bxxx">

							<p>
								<logic:notEqual value="add" name="doType">
									<logic:notEqual value="modi" name="doType">

										<input type="hidden" name="numAdd" id="numAdd1"
											style="width: 20px" />

										<input type="hidden" name="numDel" id="numDel1"
											style="width: 20px" />

									</logic:notEqual>
								</logic:notEqual>

								<logic:equal value="add" name="doType">
									<button type="button" value="+"
										onclick="trAdd('flag1','add','numAdd1','add');">
										�� ��
									</button>
									<button type="button" value="-" onclick="trDel('flag1','delRow1');">
										ɾ ��
									</button>
									<input type="hidden" name="numAdd" id="numAdd1"
											style="width: 20px"
										 />
								
									<input type="hidden"name="numDel" id="numDel1"
											style="width: 20px"  />
							</logic:equal>
								<logic:equal value="save" name="doType">
									<button type="button" value="+"
										onclick="trAdd('flag1','add','numAdd1','add');">
										�� ��
									</button>
									<button type="button" value="-" onclick="trDel('flag1','delRow1');">
										ɾ ��
									</button>
									<input type="hidden" name="numAdd" id="numAdd1"
											style="width: 20px"
										 />
								
									<input type="hidden"name="numDel" id="numDel1"
											style="width: 20px"  />
							</logic:equal>
								<logic:equal value="modi" name="doType">
									<button type="button" value="+"
										onclick="trAdd('flag1','add','numAdd1','add');">
										�� ��
									</button>
									<button type="button" value="-" onclick="trDel('flag1','delRow1');">
										ɾ ��
									</button>
									<input type="hidden" name="numAdd" id="numAdd1"
											style="width: 20px"
										 />
								
									<input type="hidden"name="numDel" id="numDel1"
											style="width: 20px"  />
							</logic:equal>
							</p>
							<tr>
								<td>
									<div class="formbox">
										<table summary="" class="datelist" align="" width="100%">
											<!-- ��ӡʱ��һ�в���ʾ- -->

											<thead>
												<tr>
													<logic:notEqual name="doType" value="sh">
														<logic:notEqual name="doType" value="view">
														<td align="center" nowrap="nowrap" style='width:6%'>
															ɾ����
														</td>
														</logic:notEqual>
													</logic:notEqual>
													<td align="center" nowrap="nowrap" style='width:17%'>
														����ҽԺ
													</td>
													<td align="center" nowrap="nowrap" style='width:17%'>
														�������
													</td>
													<td align="center" nowrap="nowrap" style='width:17%'>
														����ʱ��
													</td>
													<td align="center" nowrap="nowrap" style='width:20%'>
														ҽ����;
													</td>
													<logic:equal name="doType" value="sh">
														<td align="center" nowrap="nowrap" style='width:20%'>
															��˽��(
															<font color="blue">��ʦ��д</font>)
														</td>
													</logic:equal>
												</tr>
											</thead>
											<tbody width="100%" align="center" class="tbstyle" id="flag1">
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<logic:equal name="doType" value="sh">
						<table width="100%" border="0" class="formlist">
							<thead>
								<tr onclick="deploy('tbody_shxx')">
									<th colspan="4">
										<span>�����Ϣ</span>
									</th>
								</tr>
							</thead>
							<tbody id="tbody_shxx">

								<tr style="height:22px">
									<th align="right" style="width: 10%">
										<bean:message key="lable.xb" />������
									</th>
									<td align="left" colspan="3">
										<html:textarea name='rs' property='xyshyj' styleId="xyshyj"
											style="word-break:break-all;width:95%"
											onblur="chLeng(this,500);" rows='4' />

									</td>
								</tr>


								<logic:equal name="doType" value="sh">
									<logic:equal name="userType" value="xx">
										<tr style="height:22px">
											<th align="right" style="width: 10%">
												ѧУ������
											</th>
											<td align="left" colspan="3">
												<html:textarea name='rs' property='xxshyj' styleId="xxshyj"
													style="word-break:break-all;width:95%"
													onblur="chLeng(this,500);" rows='4' />
											</td>
										</tr>
									</logic:equal>

									<logic:equal name="userType" value="admin">
										<tr style="height:22px">
											<th align="right" style="width: 10%">
												ѧУ������
											</th>
											<td align="left" colspan="3">
												<html:textarea name='rs' property='xxshyj' styleId="xxshyj"
													style="word-break:break-all;width:95%"
													onblur="chLeng(this,500);" rows='4' />
											</td>
										</tr>
									</logic:equal>
								</logic:equal>
							</tbody>
						</table>
					</logic:equal>
				</div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td>
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>

								<div class="btn">
									<%--						            <logic:equal name="writeAble" value="yes">--%>
									<logic:notEqual name="doType" value="sh">
										<logic:notEqual name="doType" value="view">
											
											<button type="button" id="buttonSave" onclick="saveYbbx();"
												<logic:equal name="have" value="sh">disabled="true"</logic:equal>
												>
												�� ��
											</button>
											
										</logic:notEqual>
									</logic:notEqual>


									<logic:equal name="doType" value="sh">
										<button type="button" id="buttonSave" onclick="saveYbbxSh('�˻�');">
											�� ��
										</button>
										<button type="button" id="buttonSave" onclick="saveYbbxSh('ͨ��');">
											ͨ ��
										</button>
										<button type="button" id="buttonSave" onclick="saveYbbxSh('��ͨ��');">
											��ͨ��
										</button>
									</logic:equal>

									<%--									</logic:equal>--%>
								
										<logic:notEqual name="doType" value="add">
										<button type="button" onclick="Close();return false;">
											�� ��
										</button>
										</logic:notEqual>
									
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

