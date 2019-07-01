<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">	
		//��֤�ӷ�
		function checkJf(xmdm,jfdm){
		
			var shfs_id = "shfs_"+xmdm+"_"+jfdm;
			var xxf_id = "xxf_"+xmdm+"_"+jfdm;
			var sxf_id = "sxf_"+xmdm+"_"+jfdm;
			
			var shfs = $(shfs_id).value;
			var xxf = $(xxf_id).value;
			var sxf = $(sxf_id).value;
			
			if(shfs != ""){
				if(parseInt(shfs)<parseInt(xxf)){
					$(shfs_id).value = xxf;
				}else if(parseInt(shfs)>parseInt(sxf)){
					$(shfs_id).value = sxf;
				}
			}
		}
		
		//����ӷ����
		function saveJfsh(){
			if (confirm(checkInputValue())) {
				saveUpdate("/xgxt/zhcpJfsq.do?method=zcjfshDetail&doType=save","");
			}
		}
		
		//���¼��ֵ
		function checkInputValue(){
			var num =  document.getElementsByName("shfs").length;
			var flag = true;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("shfs")[i];
				
				if(obj.value==""){
					flag = false;
					break;
				}
			}
			
			if(flag){
				return "ȷ��������˵ļӷ֣�";
			}else{
				return "����˷���Ϊ�գ����ս���Ϊ0�ּ��㣬��ȷ�ϣ�";
			}
			
		}
		
		//ͬ�������
		function argeeSqf(){
		
			if (confirm("ִ�б���������˷�������ѧ���������Ϊ׼����ȷ��")) {
				var num =  document.getElementsByName("sqfs").length;
				var flag = true;
				
				for(var i=0;i<num;i++){
					var obj = document.getElementsByName("sqfs")[i];
					var sqfs = obj.value;
					var sqfs_id = obj.id;
					var shfs_id = sqfs_id.replace("sqfs","shfs");
					$(shfs_id).value=sqfs;
				}
			}
		}
		
		//�ύ�ӷ����
		function submitJfsh(){
			
			var flag=false;
			
			flag=checkShfs();
			
			if(flag){
				if (confirm("�ύ�󲻿��ٶԷ��������޸ģ���ȷ��")) {
					saveUpdate("/xgxt/zhcpJfsq.do?method=zcjfshDetail&doType=submit","");
				}
			}
		}
		
		function setDivHeight(){
			if($("div_jfxm")){
				if($("tb_xsxx").style.display == "none"){
					$("div_jfxm").style.height="300px";
				}else{
					$("div_jfxm").style.height="230px";
				}
			}			
		}
		
		 function checkShfs(){
     		
			var flag=false;
			
			//����
			var pkValue=new Array();

			jQuery.ajaxSetup({async:false});
			
			// �õ�JSON����
	        var parameter ={};
			
			parameter["xh"]=jQuery("#xh").val();
			
			var url = "zhcpJfsq.do?method=checkShfs";
          	
		 	$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery.post(url,parameter,
				function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					
					if(result!="true"){
					
						if(confirm(result)){
							
							flag=true;
							
						}else {
						
							flag=false;
						
						}
						
						
					}else{
				
						flag= checkShfIsModi();
					}
					
					
				}
			);
			
			jQuery.ajaxSetup({async:true});
			
			return flag;
			
		}
		
		
		 function checkShfIsModi(){
		 	// �õ�JSON����
	        var parameter ={};
		  	
		  	var flag=false;
		  
		 	parameter["xh"]=jQuery("#xh").val();
		 	
		 	jQuery("[name=shfs]").each(function(){
		 		
		 		parameter["str_"+jQuery(this).attr("id")]=jQuery(this).val();
		 	});
			
			var url = "zhcpJfsq.do?method=checkShfIsModi";
          	
		 	$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			jQuery.ajaxSetup({async:false});
			
			jQuery.post(url,parameter,
				
				function(result){
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
					
					if(result!="true"){
					
						if(confirm(result)){
							
							flag=true;
							
						}else {
						
							flag=false;
						
						}
						
					}else{
					
						flag= true;
					}
					
					
				}
			);
			
			jQuery.ajaxSetup({async:true});
			
			return flag;
			
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt">
			<h3>
				<span>�۲����ڣ�</span>
			</h3>
			<p>
				����ѧ��(${pjxn })&nbsp;&nbsp;
				<logic:equal name="zczq" value="xq">
				����ѧ��(${pjxqmc })&nbsp;&nbsp;
				</logic:equal>		
				
				�۲�����
				<logic:equal name="zczq" value="xn">
					(ѧ��)
				</logic:equal>
				<logic:equal name="zczq" value="xq">
					(ѧ��)
				</logic:equal>		
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->	
		
		<html:form action="/zhcpJfsq">
			<!-- ������ -->
			<input type="hidden" id="tableName" name="tableName" value="view_xsjbxx"/>
			<input type="hidden" id="lx" name="lx" value="ѧ��"/>
			<input type="hidden" id="url" name="url" value="/xgxt/zhcpJfsq.do?method=zcjfsq"/>
			<%@ include file="/comm/hiddenValue.jsp"%>		
			<input type="hidden" id="zcjflx" value="${zcjflx }"/>
			<div class="tab">		
				<table class="formlist" border="0" align="center">
				
					<!-- ����������Ϣ -->
<%--					<thead>--%>
<%--						<tr onclick="showHiddenNr('tb_zcxx')" style="cursor: hand">--%>
<%--							<th colspan="4">--%>
<%--								<span>�۲������Ϣ</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody id="tb_zcxx" style="display:none">		--%>
<%--						<tr>--%>
<%--							<th width="16%">--%>
<%--								����ѧ��--%>
<%--							</th>--%>
<%--							<td width="34%">							--%>
<%--								${pjxn }--%>
<%--							</td>--%>
<%--							<th width="16%">--%>
<%--								����ѧ��--%>
<%--							</th>--%>
<%--							<td width="34%">							--%>
<%--								${pjxqmc }--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--						<tr>--%>
<%--							<th>--%>
<%--								�������--%>
<%--							</th>--%>
<%--							<td>							--%>
<%--								${pjnd }--%>
<%--							</td>--%>
<%--							<th>--%>
<%--								�۲�����--%>
<%--							</th>--%>
<%--							<td>--%>
<%--								<logic:equal name="zczq" value="xn">--%>
<%--									ѧ��--%>
<%--								</logic:equal>--%>
<%--								<logic:equal name="zczq" value="xq">--%>
<%--									ѧ��--%>
<%--								</logic:equal>		--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</tbody>--%>
					<!-- ����������Ϣ end-->
					
					<!-- ѧ��������Ϣ -->
					<thead>
						<tr onclick="showHiddenNr('tb_xsxx');setDivHeight()" style="cursor: hand">
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tb_xsxx">		
						<tr>
							<th width="16%">
								ѧ��	
							</th>
							<td width="34%">		
								${rs.xh }
								<input type="hidden" name="xh" id="xh"value="${rs.xh }" />
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%">							
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>							
								${rs.xm }
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
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
							<td>
								${rs.bjmc }
							</td>
						</tr>
					</tbody>
					<!-- ѧ��������Ϣ end-->
					
					<!-- �۲�ӷ���Ϣ -->
					<logic:notEmpty name="rs" property="zcxmList">
						<thead>
							<tr onclick="showHiddenNr('tb_zcjf')" style="cursor: hand">
								<th colspan="4">
									<span>����ӷ���Ŀ</span>
								</th>
							</tr>
						</thead>
						<tbody id="tb_zcjf">		
							<tr>
								<td colspan="4">
									<!-- ��ʾ��Ϣ end-->
									<logic:notEqual name="doType" value="jg">
										<div class="prompt">
												��ʾ��1��ϣ����˷����������ͬ����ִ�С�ͬ����֡�������2�����ڣ�С�ڣ����޷֣����޷֣��Ļ��������޷֣����޷֣�Ϊ׼��
											<a class="close" title="����"
											   onclick="this.parentNode.style.display='none';"></a>
										</div>
									</logic:notEqual>
									<!-- ��ʾ��Ϣ end-->									
									<div style="width:100%;height:230px;overflow-x:hidden;overflow-y:auto;" id="div_jfxm">		
										<logic:iterate name="rs" property="zcxmList" id="zcxm">
											<logic:notEqual name="zcxm" property="count" value="0">
												<table width="100%">
													<thead>
														<tr onclick="showHiddenNr('tb_jfxm_${zcxm.xmdm }')" style="cursor: hand">
															<td colspan="2">
																${zcxm.xmmc }
															</td>
														</tr>
													</thead>
													<tbody id="tb_jfxm_${zcxm.xmdm }">
														<tr>
															<td width="16%">
																�������
															</td>
															<td>
																<logic:iterate name="zcxm" property="xmList" id="jfxm">
																	<logic:notEmpty name="jfxm" property="sqfs">
																		<span title="${jfxm.jfmc }">${jfxm.jfmc }(${jfxm.sqfs }��)</span>
																	</logic:notEmpty>
																</logic:iterate>
															</td>
														</tr>
														<tr>
															<td>
																��������
															</td>
															<td>
																<textarea id="sqly_${zcxm.xmdm }" rows="5" readonly="readonly"
																	style="word-break:break-all;width:90%" type="_moz">${zcxm.sqly }</textarea>
															</td>
														</tr>
														<logic:notEqual name="mklx" value="jg">
															<tr>
																<td>
																	��˷���
																</td>
																<td>
																	<table>
																	<%int n=0; %>
																	<tr>
																	<logic:iterate name="zcxm" property="xmList" id="jfxm">
																		<logic:notEmpty name="jfxm" property="sqfs">
																			<%n++; %>
																			<td>
																				<span title="${jfxm.jfmc }">${jfxm.showmc }</span>
																				<input type="hidden" name="xmdm" value="${zcxm.xmdm }"/>
																				<input type="hidden" name="jfdm" value="${jfxm.jfdm }"/>
																				<input type="hidden" name="sqfs" id="sqfs_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sqfs }"/>
																				<input type="hidden" name="sqly" id="sqly_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sqly }"/>
																				<input type="hidden" name="hid_count" id="hid_count_${rs.xmdm }" value="${rs.jfxmNum }"/>
																			</td>
																			<td>
																				<input type="text" style="width: 50px"
																					value="${jfxm.shfs }" name="shfs" 
																					onblur="checkJf('${zcxm.xmdm }','${jfxm.jfdm }');"
																					id="shfs_${zcxm.xmdm }_${jfxm.jfdm }"
																					onkeydown="return onlyNum(this,2)"
																					onmousedown="return onlyNum(this,2)"
																					maxlength="2" 
																					style="width : 80px;ime-mode:disabled"/>
																			</td>
																			<td>
																				(������Χ��${jfxm.xxf }-${jfxm.sxf }��)
																				<input type="hidden" id="xxf_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.xxf }"/>
																				<input type="hidden" id="sxf_${zcxm.xmdm }_${jfxm.jfdm }" value="${jfxm.sxf }"/>
																			</td>
																		</logic:notEmpty>
																		<%if(n%2==0){%>
																		</tr>
																		<%}%>
																	</logic:iterate>
																</table>
																</td>
															</tr>
														</logic:notEqual>
													</tbody>
												</table>
											</logic:notEqual>
										</logic:iterate>
									</div>
								</td>
							</tr>
						</tbody>
					</logic:notEmpty>
					<!-- �۲�ӷ���Ϣ end-->
					
					<tfoot>
						<tr>
							<td colspan="4">
							
								<!-- ���ύ -->
								<logic:equal name="rs" property="sftj1" value="��">
									<logic:notEqual name="doType" value="jg">
										<div class="bz"><span class="red">ע����ѧ������ļӷ��ѱ�����ύ</span></div>
									</logic:notEqual>
								</logic:equal>
								<!-- ���ύ end-->
									
								<div class="btn">
									<!-- δ�ύ -->
									<logic:notEqual name="rs" property="sftj1" value="��">
										<logic:notEqual name="doType" value="jg">
											<button type="button" onclick="argeeSqf()" style="width: 80px">
												ͬ�����
											</button>

											<button type="button" onclick="saveJfsh()" id="buttonSave" style="width: 80px">
												�� ��
											</button>
											
											<button type="button" onclick="submitJfsh()" style="width: 80px">
												�� ��
											</button>

										</logic:notEqual>
									</logic:notEqual>
									<!-- δ�ύ end-->
									
									<button type="button" onclick="Close();return false;" id="buttonClose" style="width: 80px">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>	
					
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>