<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/icon.css" />
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��������Ϣ
		function getShInfo(){
			var sqid = $("sqid").value;
			var shgw = $("shgw").value;
			var url = "rcsw_qjgl.do?method=getShInfo";
			
			//����
		 	var parameter = {
				"sqid":sqid,
				"shgw":shgw
			};
			
			jQuery("#div_shInfo").load(
				url,
				parameter,
				function(){}
			);
		}
		jQuery(function(){
			getShInfo();
		});
		
		//�������״̬
		function saveShzt(shzt){
			$("shzt").value = shzt;
			confirmInfo('��ȷ�ϱ�����Ϣ',submitShzt);	
		}
		function viewPic(sqid){
			var url = "<%=request.getContextPath()%>/qjclPic.jsp?id="+sqid; 
			window.open(url);
			//alert(sqid);
		}
		//�������״̬
		function submitShzt(tag){
			
			if(tag == "ok"){
				var sqid = $("sqid").value;
				var shgw = $("shgw").value;
				var shyj = $("shyj").value;
				//var shyj = jQuery("#shyj").val();
				var shzt = $("shzt").value;
				
				var url="rcsw_qjgl.do?method=saveShzt";
				
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
					
				//����
			 	var parameter = {
					"shzt":escape(shzt),
					"sqid":sqid,
					"shgw":shgw,
					"shyj":escape(shyj),
					"qjid":$("qjid").value
				};
				
				jQuery.post(url,parameter,function(result){
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
				});
			}
		}
		</script>
	</head>
	<body >

		<!-- ���� -->
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�ճ����� - ��ٹ��� - �ҵ����</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmouseover="showHelpDiv()"
					onmouseout="showHelpDiv()"> ��������</a>
				<img src="<%=stylePath%>/images/ico_new02.gif" />
			</p>
		</div>--%>
		<!-- ���� end-->

		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>


					1.���<font color="blue">ͨ��</font>��ť����ͬ���ѧ����������롣</br> 
					2.���<font color="blue">��ͨ��</font>��ť������ͬ���ѧ����������룬����ά����������

			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/rcsw_qjgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden name="rs" property="sqid" styleId="sqid" />
			<html:hidden name="rs" property="shgw" styleId="shgw" />
			<html:hidden name="rs" property="qjid" styleId="qjid" />
			<input type="hidden" id="shzt" value=""/>
			<!-- ������ end-->
			
			<div style="width:100%;height:435px;overflow-x:hidden;overflow-y:auto;">
				<!-- ѧ��������Ϣ -->
				<table class="formlist" width="100%">
					<thead onclick="">
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${rs.xh }
							</td>
							<th width="20%">
								����
							</th>
							<td width="30%">
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								${rs.xb }
							</td>
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								��ϵ�绰
							</th>
							<td>
								${rs.lxdh }
							</td>
							<th>
								��ͥ�绰
							</th>
							<td>
								${rs.jtdh }
							</td>
						</tr>
						<tr>
							<th>
								��ͥ��ַ
							</th>
							<td colspan="3">
								${rs.jtdz }
							</td>
						</tr>
					</tbody>
				</table>
				<!-- ѧ��������Ϣ end-->
	
				<!-- ��ٻ�����Ϣ -->
				<table class="formlist" width="">
					<thead onclick="">
						<tr>
							<th colspan="4">
								<span>���������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								��ٿ�ʼʱ��
							</th>
							<td width="30%">
								${rs.kssj }
							</td>
							<th width="20%">
								��ٽ���ʱ��
							</th>
							<td width="30%">
								${rs.jssj }
							</td>
						</tr>
						<tr>
							<th width="20%">
								��������
							</th>
							<td width="30%">
								${rs.sqts }��
							</td>
							<th width="20%">
								�������
							</th>
							<td width="30%">
								<logic:equal value="bj" name ="rs" property="kzzd1" >
									����
								</logic:equal>
								<logic:equal value="sj" name ="rs" property="kzzd1" >
									�¼�
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								�����������			
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.lxmc }
							</td>
						</tr>
						<tr>
							<th>
								��������					
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.sqly }
							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.bz }
							</td>
						</tr>
						<tr>
							<th>
								֤������
							</th>
							<td colspan="3" style="word-break:break-all;">
								<button type="button" onclick="viewPic('${rs.qjcl }');" id="btn_yl">
									Ԥ��
								</button>
							</td>
						</tr>
					</tbody>
				</table>
				<table class="formlist" width="">
					<thead onclick="">
						<tr>
							<th colspan="4">
								<span>�����ʷ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div class="con_overlfow">
									<table class="dateline" width="98%" align="center">
										<thead>
											<tr>
												<td width="12%">��˸�λ</td>
												<td width="12%">�����</td>
												<td width="12%">��˽��</td>
												<td width="19%">���ʱ��</td>
												<td width="45%">������</td>
											</tr>
										</thead>
										<tbody id="shxx">
											<logic:iterate name="shList" id="e" indexId="i">
												<tr>
													<td><bean:write name="e" property="mc" /></td>
													<td><bean:write name="e" property="shr" /></td>
													<td><bean:write name="e" property="shzt" /></td>
													<td><bean:write name="e" property="shsj" /></td>
													<td style="word-break:break-all;"><bean:write name="e" property="shyj"  /></td>
												</tr>
											</logic:iterate>
										</tbody>
									</table>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
				
				<!-- ��ٻ�����Ϣ end-->
				
				<div id="div_shInfo">
				
				</div>
				<!-- 
				<table class="formlist" width="">
				<thead onclick="hiddenMk('mk_qjcl')">
					<tr>
						<th colspan="4">
							<div align="center">֤�����ϣ����չ����ϸ��</div>
						</th>
					</tr>
				</thead>
				<tbody id="mk_qjcl" style="display:none" width="99%">
					
					<tr>
						<td colspan="4">
						<div id="qjclImg">
							<img style="width:800px"
								src="<%=request.getContextPath()%>/qjclPic.jsp?id=${rs.qjcl }"
								border="0"/>
						</div>
						</td>
					</tr>
				</tbody>
				</table>
				 -->
			</div>
			
			<table class="formlist" width="100%">
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<!-- ͨ�� -->
								<button type="button" onclick="saveShzt('ͨ��');" id="btn_bc">
									ͨ ��
								</button>
								
								<!-- ͨ�� -->
								<button type="button" onclick="saveShzt('��ͨ��');" id="btn_bc">
									��ͨ��
								</button>
								
								<!-- �ر�-->
								<button type="button" onclick="Close();return false;">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>

			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			</div>

		</html:form>
	</body>
</html>
