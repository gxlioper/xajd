<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/icon.css" />
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		function viewPic(sqid){
			var url = "<%=request.getContextPath()%>/qjclPic.jsp?id="+sqid; 
			window.open(url);
			//alert(sqid);
		}
		</script>
	</head>
	<body onload="">

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�ճ����� - ��ٹ��� - �鿴���</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->

		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����
				</br>
				<span id="div_help" style="display: none"> 
					1.�鿴�����ϸ��Ϣ������ͼ��</br> 
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
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
			
			<div style="width:98%;height:480px;overflow-x:hidden;overflow-y:auto;">
				<!-- ѧ��������Ϣ -->
				<table class="formlist" width="">
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
								����ͼ		
							</th>
							<td colspan="3" >
								<table cellpadding="0" cellspacing="0" align="left" border="0">
									<tr>
										<td style='BORDER-RIGHT: white 1px solid; BORDER-TOP: white 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: white 1px solid; BORDER-BOTTOM: white 1px solid; BACKGROUND-COLOR: white'>
											<logic:equal value="1" name="qstz" >
												<table style='BORDER: red 2px solid; HEIGHT: 79px; WIDTH:120PX ' cellSpacing=1 cellPadding=1>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid;COLOR: white; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #00bdff' vAlign=top align=center><b>�ύ����</b></td>
													</tr>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #f5f5f5' vAlign=top align=left>
														����������
														<BR>&nbsp;<BR>
														<DIV align=right>&gt;&gt;&gt; </DIV>
														</td>
													</tr>
												</TABLE>
											</logic:equal>
											<logic:notEqual value="1" name="qstz" >
												<table style='HEIGHT: 79px; WIDTH:120PX ' cellSpacing=1 cellPadding=1>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid;COLOR: white; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #00bdff' vAlign=top align=center><b>�ύ����</b></td>
													</tr>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #f5f5f5' vAlign=top align=left>
														���ύ
														<BR>&nbsp;<BR>
														<DIV align=right>&gt;&gt;&gt; </DIV>
														</td>
													</tr>
												</TABLE>
											</logic:notEqual>
										</td>
										<td style='BORDER-RIGHT: white 1px solid; BORDER-TOP: white 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: white 1px solid; BORDER-BOTTOM: white 1px solid; BACKGROUND-COLOR: white'>
											<img src='xsgzgl/rcsw/qjgl/mygz/arrow_foward.jpg'>
										</td>
										<logic:iterate name="lctLists" id="e" indexId="i">
										<td style='BORDER-RIGHT: white 1px solid; BORDER-TOP: white 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: white 1px solid; BORDER-BOTTOM: white 1px solid; BACKGROUND-COLOR: white'>
											<logic:equal value="δ���" name="e" property="shzt">
												<table style='BORDER: red 2px solid; HEIGHT: 79px; WIDTH:120PX ' cellSpacing=1 cellPadding=1>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid;COLOR: white; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #00bdff' vAlign=top align=center><b><bean:write name="e" property="mc" />���</b></td>
													</tr>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #f5f5f5' vAlign=top align=left>
														<bean:write name="e" property="shzt" />
														<BR>&nbsp;<BR>
														<DIV align=right>&gt;&gt;&gt; </DIV>
														</td>
													</tr>
												</TABLE>
											</logic:equal>
											
											<logic:notEqual value="δ���" name="e" property="shzt">
												<table style='HEIGHT: 79px; WIDTH:120PX ' cellSpacing=1 cellPadding=1>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid;COLOR: white; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #00bdff' vAlign=top align=center><b><bean:write name="e" property="mc" />���</b></td>
													</tr>
													<tr>
														<td style='BORDER-RIGHT: black 1px solid; BORDER-TOP: black 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: black 1px solid; BORDER-BOTTOM: black 1px solid; BACKGROUND-COLOR: #f5f5f5' vAlign=top align=left>
														<bean:write name="e" property="shzt" />
														<BR>&nbsp;<BR>
														<DIV align=right>&gt;&gt;&gt; </DIV>
														</td>
													</tr>
												</TABLE>
											</logic:notEqual>
										</td>
										<logic:notEqual value="${lsszie-1}" name="i" >
											<td style='BORDER-RIGHT: white 1px solid; BORDER-TOP: white 1px solid; PADDING-LEFT: 2px; BORDER-LEFT: white 1px solid; BORDER-BOTTOM: white 1px solid; BACKGROUND-COLOR: white'>
												<img src='xsgzgl/rcsw/qjgl/mygz/arrow_foward.jpg'>
											</td>
										</logic:notEqual>
										</logic:iterate>
									</tr>
								</table>
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
			
			<table class="formlist" width="">
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<!-- �ر�-->
								<button onclick="Close();">
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
