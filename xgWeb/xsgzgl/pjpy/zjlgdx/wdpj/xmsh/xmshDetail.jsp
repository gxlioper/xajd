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
		<script language="javascript" src="js/xsgzgl/xtwh/commUtil.js"></script>
		<script language="javascript" defer="defer">
		function saveShzt(shzt){

			jQuery.ajaxSetup({async:false});
			
			var xmdm = $("xmdm").value;
			var spgw = $("spgw").value;
			var xh = $("xh").value;
			
			confirmInfo("�Ƿ�Ҫ�������޸ĵ����ݣ�",function(tag){
				
				if(tag=="ok"){
					
					//����
					var pkValue=new Array();
		
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
					
					parameter["str_xmdm"]=xmdm;
					
					parameter["str_spgw"]=spgw;
					
					parameter["array_xh"]=xh;
					
					parameter["str_shzt"]=shzt;
					
					parameter["str_shyj"]=escape(jQuery("#shyj").val());
					
					var url = "general_wdpj_xmsh_ajax.do?method=saveShzt";
		          	
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
				
			});
			
		}
		</script>
	</head>
	<body onload="" ondrag="return false">
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �ҵ����� - ��Ŀ���</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" 
					onmouseover ="showHelpDiv()"
					onmouseout="showHelpDiv()"
				>
				��������</a><img src="<%=stylePath%>/images/ico_new02.gif" />
			</p>
		</div>
		<!-- ���� end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab" style="width:100%;height:430px;overflow-x:hidden;overflow-y:auto;">
				<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }"/>
				<input type="hidden" name="spgw" id="spgw" value="${spgw }"/>
				<table width="99%" border="0" class="formlist">	
					<thead onclick="hiddenMk('mk_xsxx')">
						<tr style="height:22px">
							<th colspan="4">
								<span>ѧ��������Ϣ��ע�����ѧ�ſɲ鿴��ѧ������ϸ��Ϣ��</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_xsxx">
						<tr>
							<th width="16%">
								ѧ��			
							</th>
							<td width="34%">							
								<a href="#" onclick="showXsxxDetail('${map.xh}')"><font color="blue">${map.xh }</font></a>							
								<input type="hidden" name="xh" id="xh" value="${map.xh}" />
							</td>
							<th width="16%">
								����		
							</th>
							<td width="34%">
								${map.xm }
							</td>
						</tr>
						<tr>
							<th width="">
								�꼶
							</th>
							<td width="">
								${map.nj }
							</td>
							<th width="">
								Ժϵ
							</th>
							<td width="">
								${map.xymc }
							</td>
						</tr>
						<tr>
							<th width="">
								רҵ
							</th>
							<td width="">
								${map.zymc }
							</td>
							<th width="">
								�༶
							</th>
							<td width="">
								${map.bjmc }
							</td>
						</tr>
					</tbody>
					<thead onclick="hiddenMk('mk_zccj')">
						<tr>
							<th colspan="4">
								<span>�۲���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_zccj">
						<logic:iterate name="map" property="zccjInfo"  id="zccj">
							<tr>
								<th width='16%'>
									${zccj.left.cn}
								</th>
								<td width='34%'>
									${zccj.left.zcinfo}
								</td>
								<th  width='16%'>
									${zccj.right.cn}
								</th>
								<td width='34%'>
									${zccj.right.zcinfo}
								</td>
							</tr>
						</logic:iterate>
					</tbody>
					<thead  onclick="hiddenMk('mk_xscj')">
						<tr>
							<th colspan="4">
								<span>ѧ���ɼ�</span>
							</th>
						</tr>
					</thead>
		
					<tbody id="mk_xscj">
						<tr>
							<td colspan="4">
								<table width="99%">
								<tr>
									<th>
									ѧ��
									</th>
									<th>
									ѧ��
									</th>
									<th>
									�γ�����
									</th>
									<th>
									�γ�����
									</th>
									<th>
									�ɼ�
									</th>
								</tr>
								<logic:iterate name="map" property="kccjInfo"  id="kccj">
									<tr>
										<td>
											${kccj.xn }
										</td>
										<td>
										${kccj.xqmc }
										</td>
										<td>
										${kccj.kcmc }
										</td>
										<td>
										${kccj.kcxz }
										</td>
										<td>
										${kccj.cj }
										</td>
									</tr>
								</logic:iterate>
								</table>
							</td>
						</tr>
					</tbody>
					<thead  onclick="hiddenMk('mk_xssq')">
						<tr style="height:22px">
							<th colspan="4">
								<span>��Ŀ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_xssq">
						<tr>
							<th width="">
								������Ŀ		
							</th>
							<td width="">
								${map.xmmc }
							</td>
							<th width="">
								����ʱ��
							</th>
							<td width="">
								${map.sqsj }
							</td>
						</tr>
						<tr>
							<th width="">
								��������
							</th>
							<td colspan="3" style="word-break:break-all;">
								${map.sqly }
							</td>
						</tr>
						<logic:iterate name="map" property="xmshInfo" id="xmsh">
						<tr>
							<th width="">
								${xmsh.gwmc }
							</th>
							<td colspan="3">
								<table width="99%">
									<tr>
										<td width="16%">
											���״̬
										</td>
										<td colspan="3">
											<logic:equal name="xmsh" property="shzt" value="tg">
												<p><img src="<%=stylePath%>images/ico_shtg.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="xmsh" property="shzt" value="wsh">
												<p><img src="<%=stylePath%>images/ico_dsh.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="xmsh" property="shzt" value="btg">
												<p><img src="<%=stylePath%>images/ico_shbtg.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="xmsh" property="shzt" value="th">
												<p><img src="<%=stylePath%>images/ico_shth.gif" width="52" height="18" /></p>
											</logic:equal>
											<logic:equal name="xmsh" property="shzt" value="xcs">
												<p><img src="<%=stylePath%>images/ico_shxcs.gif" width="52" height="18" /></p>
											</logic:equal>
											
										</td>
									</tr>
									<tr>
										<td width="16%">
											�����
										</td>
										<td width="34%">
											${xmsh.shrxm }
										</td>
										<td width="16%">
											���ʱ��
										</td>
										<td width="34%">
											${xmsh.shsj }
										</td>
									</tr>
									<tr>
										<td width="16%">
											������
											<logic:equal name="xmsh" property="spgw" value="${spgw}">
											<br/><font color="blue"><B>(��500��)</B></font>
											</logic:equal>
										</td>
										<td colspan="3" style="word-break:break-all;">
											<logic:equal name="xmsh" property="spgw" value="${spgw}">
												<html:textarea name='xmsh' property='shyj' styleId="shyj" style="word-break:break-all;width:95%" onblur="chLeng(this,500);"
													rows='4' />
											</logic:equal>
											<logic:notEqual name="xmsh" property="spgw" value="${spgw}">
												${xmsh.shyj }
											</logic:notEqual>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						</logic:iterate>
					</tbody>
			    </table>
		    </div>
		    
		    <div>
		    	<table width="100%" border="0" class="formlist">	
					<tfoot>
						<tr>
							<td>
								<div class="btn">
									<button type="button" onclick="saveShzt('tg');return false;">ͨ  ��</button>
									<button type="button" onclick="saveShzt('btg');return false;">��ͨ��</button>
									<logic:equal name="first" value="false">
									<button type="button" onclick="saveShzt('th')">��  ��</button>
									</logic:equal>
									<button type="button" onclick="Close();return false;">�� ��</button>
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