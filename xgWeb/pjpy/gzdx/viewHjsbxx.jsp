<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script language="javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/gzdxpjpy.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
<script type="text/javascript">
<!--
	function saveData() {
		var dm = document.getElementById('dm').value;
		if (dm == null || dm == '') {
			alert("��*������Ϊ������!");
			return false;
		}
		var cj = document.getElementById('cj').value;
		var wj = document.getElementById('wj').value;
		if (cj == '��') {
			alert("��ǰ�걨�Ļ��������в���ѧ���ɼ������񣬲������걨����!");
			return false;
		} else if (wj == '��') {
			alert("��ǰ�걨�Ļ��������в���ѧ���ܹ������Ҵ��ֵȼ��ѳ���ѧУ���õĴ������Ƽ��𣬲������걨����!");
			return false;
		}
		refreshForm('pjpy_gzdx_viewHjsbxx.do?operType=save');
	}
//-->
</script>
<body onload="checkWinType();">
	<html:form action="/gzdxPjpy" method="post">
		<input type="hidden" name="pkValue" value="${pkValue }"/>
		<input type="hidden" name="lb" value="${lb }"/>
		<input type="hidden" name="key" value="${key }"/>
		<input type="hidden" name="cj" value="${cj }"/>
		<input type="hidden" name="wj" value="${wj }"/>
		<div class="title">
       		<div class="title_img" id="title_m">
         		��ǰλ��:�������� - ��� - �걨������
       		</div>
    	</div>
			
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							�걨��ϸ��Ϣ
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right">
						ѧ�ţ�
					</td>
					<td align="left">
						<html:text property="xh" name="rs" readonly="true"></html:text>
				</td>
					<td align="right">
						ѧ�꣺
					</td>
					<td align="left">
						<html:text property="xn" name="rs" readonly="true"></html:text>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							������
						</td>
						<td align="left">
							${rs.xm }
						</td>
						<td align="right">
						ѧҵ�����֣�
					</td>
					<td align="left">
						${rs.xycpf }
					</td>
					
				</tr>
				<tr style="width:22px">
					<td align="right">
							�Ա�
						</td>
						<td align="left">
							${rs.xb }
						</td>
					<td align="right">
						��Ϊ�����֣�
					</td>
					<td align="left">
						${rs.xwbxf }
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							�꼶��
						</td>
						<td align="left">
							${rs.nj }
						</td>
					<td align="right">
						ͻ�������֣�
					</td>
					<td align="left">
						${rs.tcbxf }
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							${rs.xymc }
						</td>
					<td align="right">
						�ۺϱ��ַ֣�
					</td>
					<td align="left">
						${rs.zhbxf }
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
							רҵ��
						</td>
						<td align="left">
							${rs.zymc }
						</td>
					<td align="right">
						�ۺ����ʲ����ܷ֣�
					</td>
					<td align="left">
						${rs.zf }
					</td>
				</tr>
				<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							${rs.bjmc }
						</td>
						<td align="right">
							�����ְܷ༶������
						</td>
						<td align="left">
							${rs.bjpm }
						</td>
				</tr>
				<tr>
					<td align="right">
							�걨���
						</td>
						<td align="left">
							<html:select property="dm" styleId="dm">
								<html:option value=""></html:option>
								<html:options collection="dmList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<td align="right">
							ѧУ��ˣ�
						</td>
						<td align="left">
							${rs.xxsh }
						</td>
				</tr>
				<tr>
					<td align="right">
							ѧУ��������
						</td>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="xxyj" styleId="yj" style="width:450px" rows="4" disabled="true"></html:textarea>
						</td>
				</tr>
				<tr align="left">
						<td align="center" colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none';getStucjList();">
											<div align="center" class="style1 style3">
												<strong>${rs.xn }ѧ��γ̳ɼ���Ϣ</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
					<td colspan="4">
							<div id="child2" style="display:none">
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td align="center" >
												ѧ��
											</td>
											<td align="center">
												�γ�����
											</td>
											<td align="center">
												�γ�
											</td>
											<td align="center">
												�ɼ�
											</td>
											<td align="center">
												�����ɼ�
											</td>
											<td align="center">
												���޳ɼ�
											</td>
										</tr>
										</thead>
										<!-- ������ͨ��DWR���е��õ� -->
										<tbody width="100%" class="tbstyle" id="cjxx" align="center"></tbody>
								</table>
							</div>
						</td>
				</tr>
				<!-- ������Ϣ -->
			<tr>
				<td align="right" colspan="4">
					<table width="100%" border="1" class="tbstyle">
						<tr>
							<td bgcolor="#CCCCCC">
								<div id="main4" style="color:blue;cursor:hand"
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';getStucfList();">
									<div align="center" class="style1 style3">
										<strong>${rs.xn }ѧ��Υ�ʹ�����Ϣ</strong>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child4" style="display:none">
						<table width="100%" border="1" align="center" class="tbstyle">
						<thead>
							<tr>
								<td align="center">
									ѧ��
								</td>
								<td align="center">
									�������
								</td>
								<td align="center">
									����ԭ��
								</td>
								<td align="center">
									����ʱ��
								</td>
								<td align="center">
									�����ĺ�
								</td>
							</tr>
							</thead>
							<!-- ������ͨ��DWR���е��õ� -->
							<tbody width="100%" class="tbstyle" id="cfxx" align="center"></tbody>
						</table>
					</div>
				</td>
			</tr>
			</table>
			<div class="buttontool" align="center">
<%--			<logic:notEqual value="yes" name="view">--%>
<%--					<button class="button2" id="btn_save" --%>
<%--						onclick=""--%>
<%--						style="width:80px">--%>
<%--						�� ��--%>
<%--					</button>--%>
<%--					&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--			</logic:notEqual>--%>
					<button class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>