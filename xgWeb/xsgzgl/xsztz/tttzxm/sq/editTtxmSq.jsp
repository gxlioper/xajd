<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/comm/js/comm.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/sq/js/tttzxm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
	
			jQuery(function(){
				jQuery("#selhd").unbind('click').bind('click', function(){
				  var url = "ttxm_comm.do?method=getXmSelect";
				  showDialog('��ѡ����Ŀ',770,550,url);
			   });
				calculateRs();
			});
		</script>
		<style type = "text/css">
			#xxdz{width:250px;}
			.fontstyl{float:left}
			.fontstyl1{margin-left:5px}
			#autotable table th{text-align:left} 
			#autotable table td{text-align:left} 
		</style>
	</head>
	<body>
		<html:form action="/ttxm_sq" method="post" styleId="TttzxmForm">
			<html:hidden  styleId="xmdm" property="xmdm"/>
			<input type="hidden" name="xn"/>
			<input type="hidden" name="xq"/>
			<input type="hidden" id="usertype" value="${usertype}"/>
			<html:hidden property="dzxh" styleId="dzxh" />
			<html:hidden property="splc" styleId="splc"/>
			<html:hidden property="xmdm" styleId="xmdm"/>
			<html:hidden property="ttsqid" styleId="ttsqid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ��Ϣ</span>
								<a  style="text-align: left;" onclick="showPfzmx(this,'orign');" class="up"
									href="javascript:void(0);"> <font color="blue">���չ��/����</font>
								</a>
							</th>
							
						</tr>
					</thead>
					<tbody id="autotbody">
						<tr>
							<th width="20%"><font color="red">*</font>��Ŀ����</th>
							<td width="30%">
							   ${xmxxmap.xmmc}
								<input type="hidden" name="xmmc" value="${xmxxmap.xmmc}" id="xmmc"  />
							</td>
							<th width="20%">��Ŀ����</th>
							<td width="30%" id="xmjbmc" >
							  	${xmxxmap.xmjbmc}
                             </td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td id="xn" >
								${xmxxmap.xn}
							</td>
							<th>ѧ��</th>
							<td id="xq" >
							    ${xmxxmap.xqmc}
							</td>
						</tr>
						<tr>
							<th>�걨����</th>
							<td id="sbbmmc" >
								${xmxxmap.sbbmmc}
							</td>
							<th>��ϵ��ʽ</th>
							<td id="lxdh" name="lxdh">
								${xmxxmap.lxdh}
							</td>
						</tr>
						<tr>
							<th>������Ŀ</th>
							<td id="sskmmc" name="sskmmc">
								${xmxxmap.sskmmc}
							</td>
							<th>����ѧ��</th>
							<td id="jcxf" name="jcxf">
								${xmxxmap.jcxf}
							</td>
						</tr>
						<tr>
							<th>�ɲ����Ŷ���</th>
							<td id="kcyrs" name="kcyrs">
								${xmxxmap.kcyrs}
							</td>
							<th>�������Ŷ���</th>
							<td id="sqrs" name="sqrs">
								${xmxxmap.sqrs}
							</td>
						</tr>
						<tr>
							<th>��ͨ���Ŷ���</th>
							<td id="tgrs" name="tgrs">
								${xmxxmap.tgrs}
							</td>
							<th>���ʼʱ��</th>
							<td id="xmkssj" name="xmkssj">
								${xmxxmap.xmkssj}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ա��Ϣ </span> 
								<logic:equal name="usertype" value="stu" >
									<label  id="cyrs" style="margin-left:10px">1��</label>
									<button type="button" style="margin-top:2px;margin-left:30px" onclick="addRow();return false;">����</button>
									<button type="button" onclick="delRow();return false;">ɾ��</button>
								</logic:equal>
								<logic:notEqual name="usertype" value="stu">
									<label id="cyrs" style="margin-left:10px">0��</label>
									<button type="button" style="margin-top:2px;margin-left:30px" onclick="addRowDialog();return false;">����</button>
									<button type="button" onclick="delRow();return false;">ɾ��</button>
								</logic:notEqual>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr colspan="4">
							<td width="100%" colspan="4">
								<div width="100%" id="autotable">
								<table width="100%" id="tablebody">
									<logic:equal name="usertype" value="stu" >
									<tr>
										<th width="5%"><input type="checkbox" onclick="selectAll(this);" name="chkall"/></th>
										<th width="10%"><font color="red">*</font>��ɫ</th>
									    <th width="20%"><font color="red">*</font>ѧ��</th>
									    <th width="20%"><font color="red">*</font>����</th>
									    <th width="20%"><font color="red">*</font>ѧԺ</th>
									    <th width="20%"><font color="red">*</font>�༶</th>
									</tr>
									<tr>
										<td></td>
									    <td>�ӳ�</td>
									    <td width="20%">${dzxxmap.xh}<input type="hidden" name="xh" value="${dzxxmap.xh}"/></td>
									    <td width="20%">${dzxxmap.xm}</td>
									    <td width="20%">${dzxxmap.xymc}</td>
									    <td width="20%">${dzxxmap.bjmc}</td>
									</tr>
									<logic:iterate id="i" name="dyzzlist">
										<tr>
											<td><input type='checkbox' name='chk'></td>
										    <td>��Ա</td>
										    <td width="20%"><input value='${i.xh}'  name='xh' onfocus='setStyle(this)' title='��������ûس�' onKeyDown = 'Enterxh(event,this)' style='width:90%' onblur='inputBlur(this)'/></td>
										    <td width="20%">${i.xm}</td>
										    <td width="20%">${i.xymc}</td>
										    <td width="20%">${i.bjmc}</td>
										</tr>
									</logic:iterate>
									</logic:equal>
									<logic:notEqual name="usertype" value="stu" >
									<tr>
										<th width="5%"><input type="checkbox" onclick="selectAll(this);" name="chkall"/></th>
										<th width="10%"><font color="red">*</font>�ӳ�</th>
									    <th width="20%"><font color="red">*</font>ѧ��</th>
									    <th width="20%"><font color="red">*</font>����</th>
									    <th width="20%"><font color="red">*</font>ѧԺ</th>
									    <th width="20%"><font color="red">*</font>�༶</th>
									 </tr>
									 <tr>
										<td><input type='checkbox' name='chk'></td>
									    <td><input type='checkbox' name='dzchk' checked="checked" onclick='selectdz(this)'/></td>
									    <td width="20%"><input name='xh' type='hidden' value='${dzxxmap.xh}' style='width:90%'/><label name = 'xhname'>${dzxxmap.xh}</label></td>
									    <td width="20%"><label name = 'xm'>${dzxxmap.xm}</label></td>
									    <td width="20%"><label name = 'xymc'>${dzxxmap.xymc}</label></td>
									    <td width="20%"><label name = 'bjmc'>${dzxxmap.bjmc}</label></td>
									</tr>
									<logic:iterate id="i" name="dyzzlist">
										<tr>
											<td><input type='checkbox' name='chk'></td>
										    <td><input type='checkbox' name='dzchk'  onclick='selectdz(this)'/></td>
										    <td width="20%"><input name='xh' type='hidden' value='${i.xh}' style='width:90%'/><label name = 'xhname'>${i.xh}</label></td>
										    <td width="20%"><label name = 'xm'>${i.xm}</label></td>
										    <td width="20%"><label name = 'xymc'>${i.xymc}</label></td>
										    <td width="20%"><label name = 'bjmc'>${i.bjmc}</label></td>
										</tr>
									</logic:iterate>
									</logic:notEqual>
									
								</table>
							</div>
							</td>
							
						</tr>
						<tr>
							
						</tr>
						<tr>
							<th width="20%"><font color="red">*</font>�Ŷ�����</th>
							<td colspan="3">
								<html:text property="tdmc" maxlength="6"  styleId="tdmc"/>
							</td>
						</tr>
						<tr>
							<th width="20%"><font color="red">*</font>��������<br/><font color="red"><��500��></font></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
								   style="width:99%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveTtxmSq('update');">
										����ݸ�
									</button>
									<button type="button" id="tjsq" onclick="saveTtxmSq('updatesubmit');">
										�ύ����
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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