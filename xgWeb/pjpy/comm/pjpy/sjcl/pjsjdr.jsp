<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
			function pjsjdr(){
				if($("sjdr").value==""){
					alert("����ѡ����Ҫ����ı�");
					return false;
				}
				impAndChkData();
			}
			
			function chanageDrb(){
				$("tableName").value=$("sjdr").value;
				$("realTable").value=$("sjdr").value;
			}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /> </a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/pjpy_comm_sjdr">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab">
				<!-- ����ʱ������ -->
				<table class="formlist" border="0" align="center"
					style="width: 100%">
					<thead>
						<tr>
							<th colspan="4">
								<span>�������ݵ���</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="center">
								�����&nbsp;&nbsp;&nbsp;
								<html:select property="sjdr" styleId="sjdr" onchange="chanageDrb()"
											style="width:150px" >
											<html:option value="">----��ѡ��----</html:option>
											<html:options collection="drbArr" property="en"
												labelProperty="cn" />
								</html:select>
								
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="pjsjdr()" id="dr">
										�� ��
									</button>
									
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>
