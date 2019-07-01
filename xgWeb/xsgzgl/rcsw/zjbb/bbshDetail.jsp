<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script language="javascript" defer="defer">
		
		function saveShzt(shzt){

			jQuery.ajaxSetup({async:false});
			
			var spgw = $("spgw").value;
			
			var xmid = $("xmid").value;
			var message = "";
			if("tg"==shzt){
				message = "�������ͨ���Ľ��";
			}else if("btg"==shzt){
				message = "������˲�ͨ���Ľ��";
			}else if("th"==shzt){
				message = "�������";
			}
		
			confirmInfo("�Ƿ�Ҫ"+message+"��",function(tag){
				
				if(tag=="ok"){
				
					closeWindown();
					
					//����
					var sqid=new Array();
					
					sqid[0]=$("sqid").value;
					
					jQuery.ajaxSetup({async:false});
					
					// �õ�JSON����
			        var parameter ={};
					
					parameter["str_spgw"]=spgw;
					
					parameter["str_xmid"]=xmid;
					
					parameter["array_sqid"]=sqid.join("!!array!!");
					
					parameter["str_shzt"]=shzt;
					
					parameter["str_shyj"]=escape(jQuery("#shyj").val());
					
					var url = "rcsw_zjbb_ajax.do?method=plsh";
		          	
				 	$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,
						function(result){
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							if(result=="��˳ɹ�"){
								showAlert(result,{},{"clkFun":function(){
									if (parent.window){
										refershParent();
									}
								}});
							}else{
								alertInfo(result);
							}
							
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
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>--%>
		<!-- ���� end-->
		
		<html:form action="/rcsw_zjbb" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab" style="width:100%;height:430px;overflow-x:hidden;overflow-y:auto;">
				<input type="hidden" name="xmid" id="xmid" value="${xmid }"/>
				<input type="hidden" name="sqid" id="sqid" value="${map.id }"/>
				<input type="hidden" name="spgw" id="spgw" value="${spgw}"/>
				<table width="99%" border="0" class="formlist">	
					<thead onclick="hiddenMk('mk_xsxx')">
						<tr style="height:22px">
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="mk_xsxx">
						<tr>
							<th width="16%">
								ѧ��			
							</th>
							<td width="34%">
								${map.xh }
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
								����֤������
							</th>
							<td width="">
								${map.zjmc }
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
							<td width="" colspan="3" style="word-break:break-all;width:100%">
								${map.sqly }
							</td>
						</tr>
						<logic:iterate name="xmshInfo" id="xmsh">
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
									<button type="button" onclick="saveShzt('th')">��  ��</button>
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