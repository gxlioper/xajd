<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/kyxmgl/kyxm/js/kyxm.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/kyxmgl/kyxm/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			initFyxj();
			});
			
			
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/qgzx_kycxkyxmgl" method="post" styleId="KyxmglForm" onsubmit="return false;">
			<html:hidden property="xmid" styleId="xmid" value="${rs.xmid}"/>
			<input type="hidden" id="fyxxStr" name="fyxxStr"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								��Ŀ���
							</th>
							<td width="30%">
								${rs.xmbh}
							</td>
							<th width="20%">
								��Ŀ����
							</th>
							<td width="30%">
								${rs.xmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��Ŀ����
							</th>
							<td width="30%">
								${rs.xmsxmc}
							</td>
							<th width="20%">
								��Ŀ������λ
							</th>
							<td width="30%">
								${rs.xmsxmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��Ŀ������ѧ��
							</th>
							<td width="30%">
								${rs.xh}
							</td>
							<th width="20%">
								��Ŀ����������
							</th>
							<td width="30%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ϵ��ʽ
							</th>
							<td width="30%">
								${rs.lxfs}
							</td>
							<th width="20%">
								����ʵ����
							</th>
							<td width="30%">
								${rs.ytsys}
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								��Ŀ��ʼʱ��
							</th>
							<td width="30%">
								${rs.kssj}
							</td>
							<th width="20%">
								��Ŀ����ʱ��
							</th>
							<td width="30%">
								${rs.jssj}
							</td>																			
						</tr>
						<tr>
							<th>
								�о�����
							</th>
							<td width="30%" >
								${rs.yjzq}
							</td>
							<th width="20%">
								����ȼ�
							</th>
							<td width="30%" >
								${rs.lxdjmc}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>����Ԥ��</span>
									<a  style="text-align: left;" onclick="showYsmx(this,'orign');" class="up"
											href="javascript:void(0);"> <font color="blue">���չ��/����</font>
										</a>
							</th>
						</tr>
					</thead>
					<tbody class="ysTbody">
						<tr>
							<td colspan="4">
								<table width="99%" style="text-align: center;">
									<tr>
										<th style="text-align: center;">֧�����</th>
										<th style="text-align: center;">Ԥ���Ԫ��</th>
										<th style="text-align: center;">��Ҫ��;</th>
									</tr>
									<logic:present name="jfysList">
										<logic:iterate id="z" name="jfysList">
											<tr>
												<td>${z.zclb }</td>
												<td>${z.ysje }</td>
												<td>${z.zyyt }</td>
											</tr>
										</logic:iterate>
										<logic:empty name="jfysList">
											<tr>
												<td colspan="5">δ�ҵ��κμ�¼��</td>
											</tr>
										</logic:empty>
									</logic:present>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
				         <th colspan="4">
						<span>��Ŀ����
							<img src="xsxx/fbgl/images/add-icons-1.gif" alt="����" onclick="addXmfy(this);return false;"/>
						    <img src="xsxx/fbgl/images/delete-icons-2.gif" alt="ɾ��" onclick="delXmfy(this);return false;"/>
							</span>
						</th>
				      </tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
							<div id='xmfyDiv' style="display:none">
						<table name='xmfyTab'>
						<tbody name='xmfyTbody'>
						<tr>
								<td>
								<input type="checkbox" id="checkbox"/>
								</td>
								<td>
								<select name="fylb" onchange="initFyxj()">
										<option value="0">��������</option>
										<option value="1">����׷��</option>
								</select>
								</td>
								
								<td>
								<input type='text' name="fymc" style="width:110px" maxlength='20'/>
								</td>
								<td>
								<input type='text' name="fyje" style="width:65px" maxlength='10' onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d)?(?:\d{2})?/ig,'$1$2$3');initFyxj()">
								</td>
								<td>
								<input type='text' name="bxsj" style="width:110px" onfocus="showCalendar(this.id,'yyyyMMdd');">
								</td>
								<td>
								<input type='text' name="bz" style="width:280px" maxlength='100'>
								</td>
								</tr>
								</tbody>
							</table>
						</div>
						<div class="con_overlfow">
							<table width="100%" border="0" class="datelist" style="margin:2px auto;">
									<thead>
										<tr>
											<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
										<td width='10%'>�������</td>
										<td width='15%'><font color="red">*</font>��������</td>
										<td width='5%'>���ý��</td>
										<td width='15%'>����ʱ��</td>
										<td width='50%'>��ע</td>
									</tr>
									<tbody id="tbody_xmfy">
									<logic:present name="xmfyList">
										<logic:iterate id="i" name="xmfyList" indexId="index01">
											<tr>
												<td>
												<input type="checkbox" id="checkbox"/>
												</td>
												<td>
												<html:select  property="fylb" name="fylb" styleId="fylb_${fylb}" value="${i.fylb}" onchange="initFyxj()">
												<html:options collection="fylxList" labelProperty="mc" property="dm"/>
												</html:select>
											    </td>
											    <td>
												<html:text style="width:110px" property="fymc" styleId="fymc_${index01}" value="${i.fymc}" maxlength="20" ></html:text>
											    </td>
											    <td>
											    <input type="text" style="width:65px" name="fyje" id="fyje" maxlength="10" value="${i.fyje}"  onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d)?(?:\d{2})?/ig,'$1$2$3');initFyxj()"/>
											    </td>
											    <td>
													 <input type="text" name="bxsj" id="bxsj${index01}" value="${i.bxsj}" 
														onfocus="showCalendar(this.id,'yyyyMMdd');" style="width:110px"/>
												</td>
											    <td>
											     <input type="text" name="bz" id="bz" maxlength="100" value="${i.bz}" style="width:280px"/>
											    </td>
											</tr>
										</logic:iterate>
									</logic:present>
									</tbody>
								</table>
								</div>
								
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ����С��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						<th>�걨���Ѻϼ�</th>
							<td>
							 <input type="text" id="sbjfhj" name="sbjfhj" readonly="readonly" value="${myForm.sbjfhj }"/>
							</td>
						<th>��׼����</th>
							<td>
							 <input type="text" id="pzjf" name="pzjf" readonly="readonly" value="${rs.pzjf }"/>
							</td>
						</tr>
						<tr>
							<th>�ѱ�������</th>
							<td>
							  <input type="text" id="ybxjf" name="ybxjf" readonly="readonly" "/>
							</td>
						<th>׷�Ӿ���</th>
							<td>
							 <input type="text" id="zjjf" name="zjjf" readonly="readonly" "/>
							</td>
						</tr>
						<tr>
							<th>��Ŀ�ϼ��ܾ���</th>
							<td>
							  <input type="text" id="xmhjzjf" name="xmhjzjf" readonly="readonly" />
							</td>
						</tr>
					</tbody>
				 </table>
				</div>
			
				<div style="height:50px"></div>
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
							<button type="button" onclick="saveFywh();">
										����
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

