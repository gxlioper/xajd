<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">

		//�������״̬
		function saveShzt(shzt){
			
			$("shzt").value = shzt;
			
			confirmInfo("���Ƿ�Ҫ���"+shzt+"�������¼? ��ȷ��",submitShzt);

		}
		
		function submitShzt(tag){	
			if(tag == "ok"){
				showTips('�����У����Ժ�......');
				var url = "gyglZjjs.do?method=xszdUpdate&doType=save";
					url+= "&pk="+$("id_pk").value;
				refreshForm(url);
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
		
		<html:form action="/gyglZjjs" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="pk" styleId="id_pk" value="${rs.pk }" />
			<html:hidden property="shzt" styleId="shzt" value="" />
			<!-- ������ end-->
			
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
						<th width="20%">ѧ��</th>
						<td width="30%">
							${rs.xh }
						</td>
						<th width="20%">����</th>
						<td width="30%">
							${rs.xm }
						</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>
							${rs.xb }
						</td>
						<th>�༶</th>
						<td>
							${rs.bjmc }
						</td>
					</tr>
					<tr>
						<th>��ϵ�绰</th>
						<td>
							${rs.lxdh }
						</td>
						<th>��ͥ�绰</th>
						<td>
							${rs.jtdh }
						</td>
					</tr>
					<tr>
						<th>��ͥ��ַ</th>
						<td colspan="3">
							${rs.jtdz }
						</td>
					</tr>
					<tr>
						<th>ס�޵�ַ</th>
						<td colspan="3">
							${rs.zsdd }
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>�߶���ʼʱ��</th>
						<td>
							${rs.zdkssj }
						</td>
						<th><font color="red">*</font>�߶�����ʱ��</th>
						<td>
							${rs.zdjssj }
						</td>
					</tr>
					<tr>
						<th>��������</th>
						<td colspan="3" style="word-break:break-all;" >
							${rs.sqly }
						</td>
					</tr>
					<tr>
						<th>��ע</th>
						<td colspan="3" style="word-break:break-all;" >
							${rs.bz }
						</td>
					</tr>
					<logic:empty name="mklx">
						<logic:equal name="userStatus" value="fdy">
							<tr>
								<td align="right">����Ա������<br><font color="red">(��250��)</font></td>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="bjshyj"  
										styleId="bjpyyj" rows="5" 
										style="word-break:break-all;width:100%" 
										onblur="chLeng(this,'250')"></html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="userStatus" value="xy">
							<tr>
								<td align="right">����Ա������</td>
								<td align="left" colspan="3" style="word-break:break-all;" >
									${rs.bjshyj }
								</td>
							</tr>
							<tr>
								<td align="right">Ժϵ������<br><font color="red">(��250��)</font></td>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="xyshyj"  
										styleId="xyshyj" rows="5" 
										style="word-break:break-all;width:100%" 
										onblur="chLeng(this,'250')"></html:textarea>
								</td>
							</tr>
						</logic:equal>
					</logic:empty>
					
					<logic:notEmpty name="mklx">
						<tr>
							<td align="right">����Ա������</td>
							<td align="left" colspan="3" style="word-break:break-all;" >
								${rs.bjshyj }
							</td>
						</tr>
						<tr>
							<td align="right">Ժϵ������</td>
							<td align="left" colspan="3" style="word-break:break-all;" >
								${rs.xyshyj }
							</td>
						</tr>
					</logic:notEmpty>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<logic:empty name="mklx">
									<button
										onclick="saveShzt('ͨ��')">
										ͨ ��
									</button>
									
									<button
										onclick="saveShzt('��ͨ��')">
										��ͨ��
									</button>
								</logic:empty>
								<button onclick="Close();return false;">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ѧ��������Ϣ end-->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
				
		</html:form>
	</body>
</html>