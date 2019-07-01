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
		function viewPic(sqid){
			var url = "<%=request.getContextPath()%>/qjclPic.jsp?id="+sqid; 
			window.open(url);
			//alert(sqid);
		}
		
		//�������״̬
		function saveShzt(shzt){
		
			var sqid = $("sqid").value;
			var shgw = $("shgw").value;
			var shyj = $("shyj").value;
			
			var url="rcsw_qjgl.do?method=saveShzt";
			
			$("divWaiting").style.display="";
			$("divDisable").style.display="";
				
			//����
		 	var parameter = {
				"shzt":shzt,
				"sqid":sqid,
				"shgw":shgw,
				"shyj":escape(shyj)
			};
			
			jQuery.post(url,parameter,function(result){
				$("divWaiting").style.display="none";
				$("divDisable").style.display="none";
				alertInfo(result);
			});
		}
		</script>
	</head>
	<body onload="getShInfo()">

		<!-- ���� -->
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�ճ����� - ��ٹ��� - �����ѯ</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmouseover="showHelpDiv()"
					onmouseout="showHelpDiv()"> ��������</a>
				<img src="<%=stylePath%>/images/ico_new02.gif" />
			</p>
		</div>--%>
		<!-- ���� end-->

		<html:form action="/rcsw_qjgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden name="rs" property="sqid" styleId="sqid" />
			<html:hidden name="rs" property="shgw" styleId="shgw" />
			<!-- ������ end-->
			
			<div style="width:100%;height:435px;overflow-x:hidden;overflow-y:auto;">
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
					<thead >
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:iterate name="qjshList" id="qjsh">
						<tr>
							<th>
								��˸�λ
							</th>
							<td>
								${qjsh.gwmc}
							</td>
							<th>
								���״̬
							</th>
							<td>
								${qjsh.shzt}
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td colspan="3">
							${qjsh.shyj}
							<%--
								<html:textarea name="qjsh" property="shyj" style="word-break:break-all;width:96%"  rows="4" disabled="true"/>
							--%></td>
						</tr>
						<tr>
						</logic:iterate>
					</tbody>
				</table>
				<!-- ��ٻ�����Ϣ end-->
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
						<div id="qjclImg" >
							<img style="width:800px"
								src="<%=request.getContextPath()%>/qjclPic.jsp?id=${rs.qjcl }"
								border="0"/>
						</div>
						</td>
					</tr>
				</tbody>
				</table>
				 -->
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			</div>
			<table class="formlist" width="">
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								
								<!-- �ر�-->
								<button type="button" onclick="Close();return false;">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</html:form>
	</body>
</html>
