<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stgl/stjg/js/stjg.js"></script>		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/stglRtjg" method="post" styleId="RtjgForm">
		   <input name="usertype" type="hidden" value="${usertype}"/>
		   <input name="stid" type="hidden" value="${rs.stid}"/>
		   <input name="stxmmc" type="hidden" value="${rs.stxmmc}"/>
		   <input name="xmlbdm" type="hidden" value="${rs.xmlbdm}"/>
		   <input name="flagnum" id="flagnum" type="hidden" value="${flagnum}"/>
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���Ż�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								������Ŀ����
							</th>
							<td width="30%">
								${rs.stxmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�������
							</th>
							<td width="30%">
								${rs.stlbmc}
							</td>
							<th width="20%">
								��Ŀ���
							</th>
							<td width="30%">
								${rs.xmlbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��Чѧ��
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							<th width="20%">
								�ҿ���λ
							</th>
							<td width="30%">
								${rs.gkdw}
							</td>
						</tr>
						<!--
						<tr>
							<th width="20%">
								���ſ�ʼʱ��
							</th>
							<td width="30%">
								${rs.kssj}
							</td>
							<th width="20%">
								���Ž���ʱ��
							</th>
							<td width="30%">
								${rs.jssj}
							</td>
						</tr>
						  -->
						<tr>
							<th width="20%">
								���������
							</th>
							<td width="30%">
								${rs.fzrlb}
							</td>
							<th width="20%">
								���Ÿ�����
							</th>
							<td width="30%">
								${rs.stfzrxm}
							</td>
						</tr>
					<thead>
					<tr>
						<th colspan="4">
							<span>ָ����ʦ</span>

						</th>
					</tr>
					</thead>
					<tbody>
					<tr colspan="4">
						<td width="100%" colspan="4">
							<div width="100%" id="autotable">
								<table width="100%" id="tablebody">
									<tr>
										<th width="30%" style="text-align:left;">ָ����ʦ����</th>
										<th width="20%" style="text-align:left;">��������</th>
										<th width="20%" style="text-align:left;">��ϵ�绰</th>
										<th width="20%" style="text-align:left;">ְ��</th>
									</tr>
									<logic:iterate id="i" name="ZdlsInfoList">
										<tr name="deltr">
											<td><input name="zgh" type="hidden" value="${i.zgh}" style="width:90%"/><label name = "xm">${i.xm}</label></td>
											<td><input name="bmdm" type="hidden" value="${i.bmdm}" style="width:90%"/><label name = "bmmc">${i.bmmc}</label></td>
											<td><label name = "lxdh">${i.lxdh}</label></td>
											<td><input name="zc" type="hidden" value="${i.zc}" style="width:90%"/><label name = "zcmc">${i.zcmc}</label></td>
										</tr>
									</logic:iterate>
								</table>
							</div>
						</td>
					</tr>
						<tr>
							<th width="20%">
								������ϵ�绰
							</th>
							<td width="30%" >
								${rs.lxdh}
							</td>
							<th width="20%">
								������
							</th>
							<td width="30%">
								${rs.jtrxm}
							</td>	
						</tr>
						<tr>
							<th width="20%">
								���ų���ʱ��
							</th>
							<td width="30%" >
								${rs.stclsj}
							</td>
							<th width="20%">
								����ʱ��
							</th>
							<td width="30%">
								${rs.sqsj}
							</td>	
						</tr>
					</tbody>
					<thead >
						<tr>
							<th colspan="4">
								<span>���ų�Ա��Ϣ</span>
							</th>
						</tr>
					</thead>
				</table>
			</div>
			<div style="overflow-y:auto;height:220px;">
		
				 <table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
							<tr>
								<td colspan="7">
								<logic:notEqual name="usertype" value="stu">
									<button type="button" onclick="addXs();return false;" class="btn_01">����ѧ��</button>
								    <button type="button" onclick="delXs();return false;" class="btn_01">ɾ��ѧ��</button>
								</logic:notEqual>
								</td>
							</tr>
							<tr>
								<logic:notEqual name="usertype" value="stu">
								 <td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
								</logic:notEqual>
								<td width='15%'>ѧ��</td>
								<td width='15%'>����</td>
								<td width='10%'>�Ա�</td>
								<td width='15%'>������Ա����</td>
								<td width='20%'><font color='red'>*</font>�س�</td>
								<td width='25%'><font color='red'>*</font>��������</td>
							</tr>
					</thead>
						<tbody id="tbody_xmsqxx" style="">
							<logic:iterate id="i" name="stcyxx" indexId="index01">
							<tr>
							 <logic:notEqual name="usertype" value="stu">
								<td>
									 <logic:notEqual name="i" property="sjly" value="1" >
									    <input type="checkbox"  id="checkbox_${index01}"/>
									 </logic:notEqual>
									 <logic:equal name="i" property="sjly" value="1">
									   <input type="checkbox" disabled="disabled" id="checkbox_${index01}"/>
									 </logic:equal>
								
								</td>
							 </logic:notEqual>
							<td>${i.xh}
								<input type="hidden" name='sjly' id="sjly_${index01}" value='${i.sjly}'/>
								<input type="hidden" name='rtid' id="rtid_${index01}" value="${i.rtid}">
								<input type="hidden" name='xh' id="xh_${index01}" value="${i.xh}">
							</td>
							<td >${i.xm}</td>
							<td >${i.xb}</td>
							<td>
								<logic:notEqual name="i" property="sjly" value="1" >
									 <html:select property="rylbdm" styleId="rylbdm_${index01}"  value="${i.rylbdm}">
									 	<html:options  collection="rylblist" property="dm" labelProperty="mc"/>
									 </html:select>
								</logic:notEqual>
								<logic:equal name="i" property="sjly" value="1">
									  <html:select property="rylbdm" styleId="rylbdm_${index01}" disabled="true"  value="${i.rylbdm}">
									 	<html:options collection="rylblist" property="dm" labelProperty="mc"/>
									 </html:select>
								</logic:equal>
							</td>
							<td>
								<logic:notEqual name="i" property="sjly" value="1" >
									 <input name = "tc" id="tc_${index01}" value="${i.tc}"  maxlength="100">
								</logic:notEqual>
								<logic:equal name="i" property="sjly" value="1">
									 <input name = "tc" id="tc_${index01}" value="${i.tc}" readonly="readonly" maxlength="100">
								</logic:equal>
							</td>
							<td>
							    <logic:notEqual name="i" property="sjly" value="1" >
							    	 <input name = "sqly" id="sqly_${index01}" value="${i.sqly}" maxlength="100">
								</logic:notEqual>
								<logic:equal name="i" property="sjly" value="1">
								     <input name = "sqly" id="sqly_${index01}" value="${i.sqly}"  readonly="readonly" maxlength="100">
								</logic:equal>
							</td>
							</tr>
							</logic:iterate>
							</tbody>
						</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
								</div>
								<div class="btn">
								    <button type="button" onclick="saveJgwh();">
										����
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