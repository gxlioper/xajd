<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript">

		//���� 
		function sjbc(){
			
			//�����ֶβ���Ϊ��
			var array = jQuery(".bcClass");
			var flag = true;
			jQuery(array).each(function (i,n) {
				if (jQuery(n).val()=="" || jQuery(n).val()==null) {
					flag = false;
					alertError("��*���ֶα�����д��");
					return false;	
				}
			});	

			//�Լ�ͥ��Ա��Ϣ�����ж�
			var fxm = jQuery('#fxm').val();
			if (fxm != null && fxm != "") {
				jQuery('.fClass').each(function(i,n){
					if (jQuery(n).val()=="" || jQuery(n).val()==null) {
						flag = false;
						alertError("��ͥ��Աһ����Ϣ������д������");
						return false;	
					}
				});
			}
			var sxm = jQuery('#sxm').val();
			if (sxm != null && sxm != "") {
				jQuery('.sClass').each(function(i,n){
					if (jQuery(n).val()=="" || jQuery(n).val()==null) {
						flag = false;
						alertError("��ͥ��Ա������Ϣ������д������");
						return false;	
					}
				});
			}
			var txm = jQuery('#txm').val();
			if (txm != null && txm != "") {
				jQuery('.tClass').each(function(i,n){
					if (jQuery(n).val()=="" || jQuery(n).val()==null) {
						flag = false;
						alertError("��ͥ��Ա������Ϣ������д������");
						return false;	
					}
				});
			}

			if ((fxm==''&&sxm==''&&txm=='')) {
				flag = false;
				alertError("��������дһ���ͥ��Ա��Ϣ��");
				return false;	
			} else {
				if ((fxm==sxm&&fxm!='') || (sxm==txm&&sxm!='')) {
					flag = false;
					alertError("��ͥ��Ա�����ظ���д����ȷ�ϣ�");
					return false;	
				}
			}
			
			//���б������
			if (flag) {
				refreshForm('xszz_jhzy_zjJtqkdz.do?act=save');
			}
		}
		</script>
		
	</head>
	<body >
		<html:form action="/jtqkdzGl" method="post" >
					<input type="hidden" name="url" id="url" value="jtqkdzGl.do?method=zjJtqkdz">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">		
					<input type="hidden" name="message" id="message" value="${message }">		
					<input type="hidden" name="doType" id="doType"  >
		
		<div style="width:100%;height:630px;overflow-x:hidden;overflow-y:auto;">
		
					<table class="formlist" border="0" align="center" style="width: 100%">
				<tr style="height: 23px">
					<td align="center" colspan="4">
						<font size="5">
							${zzxn }ѧ���ͥ������� 
						</font>
					</td>
				</tr>
			</table>
				
					<jsp:include page="/xsgzgl/xszz/jhzy/jtqkdz/xsxxzj.jsp" flush="true"></jsp:include>
					
				
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="9">
									<span>��ͥ�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>���</th>
								<th align="" >
									<div align="center"><font color="red">*</font>����</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>����</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>��ϵ</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>����(ѧϰ)��λ</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>ְҵ</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>��ϵ�绰</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>������</div>
								</th>
								<th align="right" >
									<div align="center"><font color="red">*</font>����״��</div>
								</th>
								</tr>
									<tr>
								<td>1</td>
								<td>
									<html:text property="cyxm" styleClass="fClass" styleId="fxm" maxlength="10" style="width:75px" value="${jtxxList1.cyxm}"></html:text>
								</td>
								<td>
									<html:text property="cynl" styleClass="fClass" onkeyup="checkInputData(this)" maxlength="2"  style="width:40px" value="${jtxxList1.cynl}"></html:text>
								</td>
								<td>
									<html:select property="cygx"  style="width:73px" styleClass="fClass" value="${jtxxList1.cygx}">
										<html:option value=""></html:option>
										<html:option value="����">����</html:option>
										<html:option value="ĸ��">ĸ��</html:option>
										<html:option value="��ż">��ż</html:option>
										<html:option value="үү">үү</html:option>
										<html:option value="����">����</html:option>
										<html:option value="�⹫">�⹫</html:option>
										<html:option value="����">����</html:option>
										<html:option value="����">����</html:option>
									</html:select>
								</td>
								<td>
									<html:text property="cygzxxdw" styleClass="fClass" maxlength="15" style="width:150px" value="${jtxxList1.cygzxxdw}"></html:text>
								</td>
								<td>
									<html:text property="cyzy" styleClass="fClass" maxlength="10" style="width:70px" value="${jtxxList1.cyzy}"></html:text>
								</td>
								<td>
									<html:text property="cylxdh" styleClass="fClass" maxlength="20" style="width:70px" value="${jtxxList1.cylxdh}"></html:text>
								</td>
								<td>
									<html:text property="cynsr"  styleClass="fClass" onkeyup="checkInputNum(this)" maxlength="8" style="width:70px" value="${jtxxList1.cynsr}"></html:text>
								</td>
								<td>
									<html:text property="cyjkzk" styleClass="fClass" maxlength="8" style="width:70px" value="${jtxxList1.cyjkzk}"></html:text>
								</td>
							</tr>
							
							<tr>
								<td>2</td>
								<td>
									<html:text property="cyxm"  styleId="sxm" styleClass="sClass" maxlength="10" style="width:75px" value="${jtxxList2.cyxm}" ></html:text>
								</td>
								<td>
									<html:text property="cynl" styleClass="sClass" onkeyup="checkInputData(this)" maxlength="2" style="width:40px" value="${jtxxList2.cynl}"></html:text>
								</td>
								<td>
									<html:select property="cygx"  style="width:73px" styleClass="sClass" value="${jtxxList2.cygx}">
										<html:option value=""></html:option>
										<html:option value="����">����</html:option>
										<html:option value="ĸ��">ĸ��</html:option>
										<html:option value="��ż">��ż</html:option>
										<html:option value="үү">үү</html:option>
										<html:option value="����">����</html:option>
										<html:option value="�⹫">�⹫</html:option>
										<html:option value="����">����</html:option>
										<html:option value="����">����</html:option>
									</html:select>
								</td>
								<td>
									<html:text property="cygzxxdw"  styleClass="sClass" maxlength="15" style="width:150px" value="${jtxxList2.cygzxxdw}"></html:text>
								</td>
								<td>
									<html:text property="cyzy" styleClass="sClass" maxlength="10" style="width:70px" value="${jtxxList2.cyzy}"></html:text>
								</td>
								<td>
									<html:text property="cylxdh"  styleClass="sClass" maxlength="20" style="width:70px" value="${jtxxList2.cylxdh}"></html:text>
								</td>
								<td>
									<html:text property="cynsr" styleClass="sClass" onkeyup="checkInputNum(this)"  maxlength="8" style="width:70px" value="${jtxxList2.cynsr}"></html:text>
								</td>
								<td>
									<html:text property="cyjkzk"  styleClass="sClass" maxlength="8" style="width:70px" value="${jtxxList2.cyjkzk}"></html:text>
								</td>
							</tr>
							
							<tr>
								<td>3</td>
								<td>
									<html:text property="cyxm" styleId="txm" styleClass="tClass" maxlength="10" style="width:75px" value="${jtxxList3.cyxm}"></html:text>
								</td>
								<td>
									<html:text property="cynl" styleClass="tClass" onkeyup="checkInputData(this)" maxlength="2" style="width:40px" value="${jtxxList3.cynl}"></html:text>
								</td>
								<td>
									<html:select property="cygx"  style="width:73px" styleClass="tClass" value="${jtxxList3.cygx}">
										<html:option value=""></html:option>
										<html:option value="����">����</html:option>
										<html:option value="ĸ��">ĸ��</html:option>
										<html:option value="��ż">��ż</html:option>
										<html:option value="үү">үү</html:option>
										<html:option value="����">����</html:option>
										<html:option value="�⹫">�⹫</html:option>
										<html:option value="����">����</html:option>
										<html:option value="����">����</html:option>
									</html:select>
								</td>
								<td>
									<html:text property="cygzxxdw"  styleClass="tClass" maxlength="15" style="width:150px" value="${jtxxList3.cygzxxdw}"></html:text>
								</td>
								<td>
									<html:text property="cyzy" styleClass="tClass" maxlength="10" style="width:70px" value="${jtxxList3.cyzy}"></html:text>
								</td>
								<td>
									<html:text property="cylxdh"  styleClass="tClass" maxlength="20" style="width:70px" value="${jtxxList3.cylxdh}"></html:text>
								</td>
								<td>
									<html:text property="cynsr" styleClass="tClass" onkeyup="checkInputNum(this)"  maxlength="8" style="width:70px" value="${jtxxList3.cynsr}"></html:text>
								</td>
								<td>
									<html:text property="cyjkzk"  styleClass="tClass" maxlength="8" style="width:70px" value="${jtxxList3.cyjkzk}"></html:text>
								</td>
							</tr>
						</tbody>
					</table>
					
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>��ͥ�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>�Ƿ�²�
						</th>
						<td align="left" width="30%">
							
							<input type="radio" name="sfgc" value="��" <logic:equal value="��" name="rs" property="sfgc">checked</logic:equal>/>��
							<input type="radio" name="sfgc" value="��" <logic:equal value="��" name="rs" property="sfgc">checked</logic:equal>/>��
						</td>
						<th align="right" width="20%">
							<font color="red">*</font>�Ƿ���
						</th>
						<td align="left" width="30%">
							<input type="radio" name="sfdq" value="��" <logic:equal value="��" name="rs" property="sfdq">checked</logic:equal>/>��
							<input type="radio" name="sfdq" value="��" <logic:equal value="��" name="rs" property="sfdq">checked</logic:equal>/>��
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>��ʿ��Ů
						</th>
						<td align="left" width="30%">
							<input type="radio" name="sflszn" value="��" <logic:equal value="��" name="rs" property="sflszn">checked</logic:equal>/>��
							<input type="radio" name="sflszn" value="��" <logic:equal value="��" name="rs" property="sflszn">checked</logic:equal>/>��
						</td>
						<th align="right" width="20%">
							<font color="red">*</font>�Ƿ�ͱ�
						</th>
						<td align="left" width="30%">
							<input type="radio" name="sfdb" value="��" <logic:equal value="��" name="rs" property="sfdb">checked</logic:equal>/>��
							<input type="radio" name="sfdb" value="��" <logic:equal value="��" name="rs" property="sfdb">checked</logic:equal>/>��
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>��ͥ����
						</th>
						<td align="left" width="30%">
							<input type="radio" name="jthk" value="����" <logic:equal value="����" name="rs" property="jthk">checked</logic:equal>/>����
							<input type="radio" name="jthk" value="ũ��" <logic:equal value="ũ��" name="rs" property="jthk">checked</logic:equal>/>ũ��
						</td>
						<th></th>
						<td></td>
						
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>����ʡ����
						</th>
						<td align="left" colspan="3">
							<html:select  property="syshen" styleId="syshen" value="${rs.syshen}"
										onchange="loadShi('syshen','syshi','syxian')" style="width:200px">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;
							<html:select  property="syshi" styleId="syshi" value="${rs.syshi}"
										onchange="loadXian('syshi','syxian')" style="width:200px">
										<html:options collection="shiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;
							<html:select  property="syxian" styleId="syxian" value="${rs.syxian}" style="width:200px">
										<html:options collection="xianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
						</td>
					</tr>
					
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>��ͥ��ַ
						</th>
						<td align="left" width="30%">
							<html:text property="jtdz" styleId="jtdz" maxlength="40" value="${rs.jtdz}"></html:text>
						</td>
						<th align="right" width="20%">
							<font color="red">*</font>��ͥ�绰
						</th>
						<td align="left" width="30%">
							<html:text property="jtdh" styleId="jtdh" maxlength="20" value="${rs.jtdh}"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>��ͥ�ʱ�
						</th>
						<td align="left" width="30%">
							<html:text property="jtyb" styleId="jtyb" maxlength="7" onkeyup="checkInputData(this)" value="${rs.jtyb}"></html:text>
						</td>
						<th align="right" width="20%">
							<font color="red">*</font>��ͥ�˿���
						</th>
						<td align="left" width="30%">
							<html:text property="jtrks" onkeyup="checkInputData(this)" styleId="jtrks" maxlength="2" value="${rs.jtrks}"></html:text>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>��ͥ�˾�����
						</th>
						<td align="left" width="30%">
							<html:text property="jtrjsr" styleId="jtrjsr" maxlength="10" onkeyup="checkInputNum(this)" value="${rs.jtrjsr}"></html:text>��Ԫ��
						</td>
						<th align="right" width="20%">
							<font color="red">*</font>��ͥ��������
						</th>
						<td align="left" width="30%">
							<html:text property="jtnzsr" styleId="jtnzsr" maxlength="10" onkeyup="checkInputNum(this)" value="${rs.jtnzsr}"></html:text>��Ԫ��
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>������Դ
						</th>
						<td align="left" width="30%">
							<html:select property="srly" styleId="srly" style="width:148px" value="${rs.srly}">
								<html:option value=""></html:option>
								<html:option value="�����ȼ�">�����ȼ�</html:option>
								<html:option value="��������">��������</html:option>
								<html:option value="��ũ">��ũ</html:option>
								<html:option value="��">��</html:option>
								<html:option value="С����">С����</html:option>
								<html:option value="����">����</html:option>
							</html:select>
						</td>
						<th align="right" width="20%">
							
						</th>
						<td align="left" width="30%">
							
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>�ѻ��������</br><font color="red">(����500������)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="yhzzqk" id="yhzzqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.yhzzqk}</textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>��ͥ�������</br><font color="red">(����500������)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="jtszqk" id="jtszqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.jtszqk }</textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>ͻ���¼����</br><font color="red">(����500������)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="tfsjqk" id="tfsjqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.tfsjqk }</textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>�м��������</br><font color="red">(����500������)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="cjnmqk" id="cjnmqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.cjnmqk }</textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>��ͥ��ҵ���</br><font color="red">(����500������)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="jtsyqk" id="jtsyqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.jtsyqk }</textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>��ͥǷծ���</br><font color="red">(����500������)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="jtqzqk" id="jtqzqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.jtqzqk }</textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							<font color="red">*</font>��ͥ�������</br><font color="red">(����500������)</font>
						</th>
						<td align="left" colspan="3">
							<textarea name="jtqtqk" id="jtqtqk" style="word-break:break-all;width:97%"
										rows='4' onblur="checkLen(this,500)" type="_moz">${rs.jtqtqk }</textarea>
						</td>
					</tr>
					</tbody>
				</table>
				</div>
				<table class="formlist">
								<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="sjbc();" id="buttonSave">
									�� ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
