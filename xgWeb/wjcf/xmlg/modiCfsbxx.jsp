<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<!-- ͷ�ļ� -->
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
		<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
		<script type="text/javascript">
function guizhdxPrint() {
		var xh = document.getElementById('xh').value;
		var xn = document.getElementById('xn').value;
		var sbsj = document.getElementById('sbsj').value;
		var pkValue = xh+xn+sbsj;
		window.open('wjcfnblgwh.do?method=gzdxCfPrint&pkValue='+pkValue );
	}
	
	function modicfxx(url,pks){
		var eles = pks.split("-");
		for (i = 0; i < eles.length; i++) {
			if($(eles[i])){
				if ($(eles[i]).value == "") {
					alert("�뽫��\"*\"�ŵ���Ŀ����������");
					return false;
				}
			}
		}
		saveinfo(url,pks);
	}
</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - ϵԺ�걨 - �걨��Ϣά��</a>
			</p>
		</div>

		<html:form action="/wjcfxmlgwh" method="post" enctype="multipart/form-data">
			<input type="hidden" value="${rs.sbsj }" id="sbsj" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�����޸�</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
									<br />
									<font color="red">˵��������ѧ�����ύ������˼������ʶ���¼����������֡�<bean:message
											key="lable.xsgzyxpzxy" />���������������ݵ��������.</font>
								</div>

								<div class="btn">
									<logic:notEqual value="view" name="xy_writable">
										<logic:equal name="xxdm" value="10388">
											<button type="button" id="btn_save"
												onclick="modicfxx('wjcf_xmlg_modiCfsbxx.do?operType=save','xn-nd-cflb-cfyy-jtwjsy-xyclyj');">
												�� ��
											</button>
										</logic:equal>
										<logic:notEqual name="xxdm" value="10388">
										 	<button type="button" id="btn_save"
												onclick="saveinfo('wjcf_xmlg_modiCfsbxx.do?operType=save','xn-nd-cflb-cfyy-jtwjsy');">
												�� ��
											</button>
										</logic:notEqual>
										</logic:notEqual>
										<logic:equal value="10657" name="xxdm">
											<button type="button" onclick="guizhdxPrint()">
												�� ӡ
											</button>
										</logic:equal>
										<button type="button" id="btn_close" onclick="window.close();return false;">
											�� ��
										</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:text property="xh" name="rs" styleId="xh" readonly="true"></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:select property="xn" styleId="xn" style="width:90px"
									styleClass="select">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								${rs.xm }
							</td>
							<th>
								<font color="red">*</font>���
							</th>
							<td>
								<html:select property="nd" styleId="nd" style="width:90px">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
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
								<font color="red">*</font>�������
							</th>
							<td>
								<html:select property="cflb" styleId="cflb" styleClass="select">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="cflbList" property="cflbdm"
										labelProperty="cflbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								${rs.nj }
							</td>
							<th>
								<font color="red">*</font>����ԭ��
							</th>
							<td>
								<html:select property="cfyy" styleId="cfyy" styleClass="select">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="cfyyList" property="cfyydm"
										labelProperty="cfyymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								${rs.xymc }
							</td>
							<th>
								������ò
							</th>
							<td>
								${rs.zzmmmc }
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
								Υ��ʱ��
							</th>
							<td>
								<html:text property="wjsj" styleId="wjsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('wjsj','y-mm-dd');"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td>
								${rs.bjmc }
							</td>
<%--							<th>--%>
<%--								Υ��ʱ��--%>
<%--							</th>--%>
<%--							<td>--%>
<%--								<html:text property="wjsj" styleId="wjsj"--%>
<%--									onblur="dateFormatChg(this)" style="cursor:hand;"--%>
<%--									onclick="return showCalendar('wjsj','y-mm-dd');"></html:text>--%>
<%--							</td>--%>
							<logic:equal name="xxdm" value="10388">
							<th>
								�걨��
							</th>
							<td>
								<input type="text" value="${rs.sbr}" readonly="true"/>
							</td>
							</logic:equal>
							<logic:notEqual name="xxdm" value="10388">
								<th>��˽��</th>
								<td>
									${rs.xxsh }
								</td>
							</logic:notEqual>
						</tr>
					<thead>
						<tr>
							<td colspan="4">
								<span>��ʷΥ�ʹ�����Ϣ&nbsp;&nbsp; <a
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';getStucfxx();">�鿴</a>
								</span>

							</td>
						</tr>
					</thead>

					<tr>
						<td colspan="4">
							<div id="child4" style="display:none">
								<table width="100%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td align="center" width="80px">
												ѧ��
											</td>
											<td align="center" width="110px">
												�������
											</td>
											<td align="center" width="110px">
												����ԭ��
											</td>
											<td align="center" width="80px">
												����ʱ��
											</td>
											<td align="center" width="110px">
												�����ĺ�
											</td>
											<td align="center" width="80px">
												Υ��ʱ��
											</td>
											<td align="center">
												Υ����ʵ
											</td>
										</tr>
									</thead>
									<!-- ������ͨ��DWR���е��õ� -->
									<tbody width="100%" class="tbstyle" id="cfxx" align="center"></tbody>
								</table>
							</div>
						</td>
					</tr>
					<logic:notEqual value="11078" name="xxdm">
						<tr>
							<th>
								���������
								<br />
								������ϻ򸽼�
							</th>
							<td align="left" colspan="3">
								<input type="file" name="uploadFile" style="width:58%" />
								<logic:notEmpty name="rs" property="fjsclj">
									<a href="downloadfilewj.do?len=&wjsclj=${rs.fjsclj }"
										target="_blank">����</a>
								</logic:notEmpty>
							</td>
						</tr>
					</logic:notEqual>
					<tr style="height:23px">
						<th>
							<font color="red">*</font>Υ����ʵ
							<br />
							<font color="red">(������2000������)</font>
						</th>
						<td align="left" colspan="3">
							<font color="red">(����ʵ˵��Υ��ʱ��,�ص�,�¼���������ɺ��)</font>
							<br />
							<html:textarea rows="7" style="width:500px" property="jtwjsy"
								styleId="jtwjsy" onkeyup="checkLen(this,2000)" />
						</td>
					</tr>
					<logic:equal value="10338" name="xxdm">
						<tr>
							<th>
								��ʷΥ�ͼ�¼
								<br />
								<font color="red">(������800������)</font>
							</th>
							<td colspan="3" align="left">
								<html:textarea rows="6" style="width:500px" property="lswjjl"
									styleId="lswjjl" onkeyup="checkLen(this,800)" />
							</td>
						</tr>
					</logic:equal>
					<tr>
						<th>
							<logic:equal name="xxdm" value="10388">
							<font color="red">*</font>
							</logic:equal>
							<bean:message key="lable.xsgzyxpzxy" />
							��ϵ��������&nbsp;&nbsp;
							<br />
							���������
							<br />
							<font color="red">(������500������)</font>
						</th>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="xyclyj" rows="5"
								style="width:500px" onkeyup="checkLen(this,500)">
							</html:textarea>
						</td>
					</tr>




					<logic:equal name="xxdm" value="11355">
						<logic:equal name="isFdy" value="true">
							<tr>
								<th>
									����Ա���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br />
									<font color="red">(������100������)</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="bzryj" styleId="bzryj"
										rows="6" style="width:500px" onblur="chLeng(this,100)">
									</html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual name="isFdy" value="true">
							<logic:equal name="userType" value="xy">
								<tr>
									<th>
										����Ա���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<br />
										<font color="red">(������100������)</font>
									</th>
									<td align="left" colspan="3">
										<html:textarea name="rs" property="bzryj" styleId="bzryj"
											rows="6" style="width:500px" onblur="chLeng(this,100)">
										</html:textarea>
									</td>
								</tr>
							</logic:equal>
						</logic:notEqual>
						<logic:equal name="userType" value="xx">
							<tr>
								<th>
									����Ա���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br />
									<font color="red">(������100������)</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="bzryj" styleId="bzryj"
										rows="6" style="width:500px" onblur="chLeng(this,100)">
									</html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									ѧУ���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br />
									<font color="red">(������100������)</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="xgcyj" styleId="xgcyj"
										rows="6" style="width:500px" onblur="chLeng(this,100)">
									</html:textarea>
								</td>
							</tr>
						</logic:equal>
						<logic:equal name="userType" value="admin">
							<tr>
								<th>
									����Ա���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br />
									<font color="red">(������100������)</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="bzryj" styleId="bzryj"
										rows="6" style="width:500px" onblur="chLeng(this,100)">
									</html:textarea>
								</td>
							</tr>
							<tr>
								<th>
									ѧУ���&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br />
									<font color="red">(������100������)</font>
								</th>
								<td align="left" colspan="3">
									<html:textarea name="rs" property="xgcyj" styleId="xgcyj"
										rows="6" style="width:500px" onblur="chLeng(this,100)">
									</html:textarea>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>
								��ע
								<br />
								<font color="red">(������100������)</font>
							</th>
							<td align="left" colspan="3">
								<html:textarea name="rs" property="bz" styleId="bz" rows="5"
									style="width:500px;inputtext" onblur="chLeng(this,100)">
								</html:textarea>
							</td>
						</tr>
					</logic:equal>
				</table>
			</div>
			<!-- �������ʾҳ�� -->
			<logic:present name="inserted">
				<logic:equal value="yes" name="inserted">
					<script>
				alert("�����ɹ���");
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
				</logic:equal>
				<logic:equal value="no" name="inserted">
					<script>
					alert("����ʧ�ܣ�");
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
				</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
