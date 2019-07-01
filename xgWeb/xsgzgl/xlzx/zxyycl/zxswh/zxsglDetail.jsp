<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/zxswh/js/zxsglDetail.js"></script>
		<script type='text/javascript'>
			jQuery(function(){
				jQuery("#xq").val('${xq}');
			})
		</script>
	</head>
	<body onload="init();">
	
		<html:form action="/xlzx_zxs" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<!--  <input type="hidden" id="url" name="url" value="xlzx_zxs.do?method=zxsglDetail" />-->
		<input type="hidden" name="lxdh" id="lxdh" value="${zxsInfoList.lxdh}" />
		<input type="hidden" name="zgh" id="zgh" value="${zxsInfoList.zgh}" />
		<input type="hidden" name="datazt" id="datazt" value="${zxsInfoList.datazt}" />
		<input type="hidden" name="doType" id="doType" value="${doType}" />

			<div style='tab;width:100%;height:375px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ʦ������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr >
							<th width="20%"  align="right">
								<logic:equal name="doType" value="add"><span class="red">*</span></logic:equal>ְ����
							</th>
							<td width="30%" align="left">
								<logic:equal name="doType" value="add">
									<html:text property="zgh" styleId="zgh" style="width:100px" value="${zxsInfoList.zgh}" readonly="true"/>
									<button type="button" onclick="showDialog('��ʦѡ��',680,480,'szdw_fdyjtff.do?method=showFdys&fdyjtfflx=zxswh&goto=${path}');return false;" class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									${zxsInfoList.zgh}
								</logic:notEqual>
							</td>
							<th width="20%"  align="right">
								����
							</th>
							<td width="30%" align="left">
								${zxsInfoList.xm}
							</td>
						</tr>
						<tr >
							<th align="right">
								�Ա�
							</th>
							<td align="left">
								${zxsInfoList.xb }
							</td>
							<th align="right">
								����
							</th>
							<td align="left">
								${zxsInfoList.age}
							</td>
						</tr>
						<tr >
							<th>
								��ϵ�绰
							</th>
							<td >
								${zxsInfoList.lxdh }								
							</td>
							<th >
								����
							</th>
							<td >
								${zxsInfoList.bmmc}
							</td>
						</tr>
					</tbody>
					
					<thead>
						<tr >
							<th colspan="4">
								<span>��ѯʦ��Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody id="zxsZgInfo">
						<tr>
							<th>
								<logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual>�ڸ�״̬
							</th>
							<td colspan="3">
								<logic:notEqual name="doType" value="view">
									<html:select property="status" styleId="status" value="${zxsInfoList.status }"  style="width:100px">
										<html:option value="1">�ڸ�</html:option>
										<html:option value="2">���ڸ�</html:option>
									</html:select>
								</logic:notEqual>
								<logic:equal name="doType" value="view">
									${zxsInfoList.statusmc }
								</logic:equal>
							</td>
						</tr>
						<logic:equal name="xxdm" value="10026">
							<tr>
								<th>
									<logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual>У��
								</th>
								<td colspan="3">
									<logic:notEqual name="doType" value="view">
										<html:select property="xq" styleId="xq" disabled="false" style="width:100px;">
											<html:options collection="xqList" property="dm" labelProperty="xqmc" />
										</html:select>
									</logic:notEqual>
									<logic:equal name="doType" value="view">
										${zxsInfoList.xqmc }
									</logic:equal>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th>��ԤԼ����</th>
							<td colspan="3">
							<logic:notEqual name="doType" value="view">
								<html:text property="kjdrs" styleId="kjdrs"  value="${zxsInfoList.kjdrs}" style="width:100px" readonly="fasle" maxlength="2" onblur="checkInputData(this);"/>&nbsp;��
								&nbsp;&nbsp;&nbsp;&nbsp;<font color="blue"><B>(��������Ĭ��������)</B></font>
							</logic:notEqual>
							<logic:equal name="doType" value="view">
									${zxsInfoList.kjdrs }
							</logic:equal>
							</td>
						</tr>
						<tr>
							<th>��ѯ��ϸ��ַ</th>
							<td colspan="3">
								<logic:notEqual name="doType" value="view">
									<html:text property="address" styleId="address"  style="width:80%" value="${zxsInfoList.address}" maxlength="50" readonly="fasle" />
								</logic:notEqual>
								<logic:equal name="doType" value="view">
									${zxsInfoList.address }
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								��ְ����
							</th>
							<td colspan="3">
								<logic:notEqual name="doType" value="view">
									<html:text property="zxszg" styleId="zxszg"  style="width:80%"   value="${zxsInfoList.zxszg}" maxlength="60"  readonly="fasle"/>
								</logic:notEqual>
								<logic:equal name="doType" value="view">
									  ${zxsInfoList.zxszg }
								</logic:equal>
							</td>
						</tr>
						<tr >
							<th>
								���<br/>
								<logic:notEqual name="doType" value="view"><font color="red"><B>(��500��)</B></font></logic:notEqual>
							</th>
							<td colspan="3" style="height:60px;">
								<logic:notEqual name="doType" value="view">
									<html:textarea  property='zxsjj' styleId="zxsjj" style="word-break:break-all;width:80%" value="${zxsInfoList.zxsjj}" onblur="chLeng(this,500);"
										rows='4' />
								</logic:notEqual>
								<logic:equal name="doType" value="view">
									${zxsInfoList.zxsjj}
								</logic:equal>
							</td>
						</tr>
					</tbody>
				</table>
			</div>	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td>
								<logic:notEqual name="doType" value="view"><div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div></logic:notEqual>
								<div class="btn">
									<button id="buttonSave" type="button" onclick="saveZxs();return false;">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;">
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

