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
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/jg/js/tttzxm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
	
			jQuery(function(){
				jQuery("#selhd").unbind('click').bind('click', function(){
				  var url = "ttxm_comm.do?method=getXmSelect";
				  showDialog('��ѡ����Ŀ',770,550,url);
			   });
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
		<html:form action="/ttxm_jg" method="post" styleId="TttzxmJgForm">
			<input type="hidden" id="xmdm" name="xmdm"/>
			<input type="hidden" name="xn"/>
			<input type="hidden" name="xq"/>
			<input type="hidden" id="usertype" value="${usertype}"/>
			<logic:equal name="usertype" value="stu" >
				<input type="hidden" name="dzxh" id="dzxh" value="${xsmap.xh}"/>
			</logic:equal>
			<logic:notEqual name="usertype" value="stu" >
				<input type="hidden" id="dzxh" name="dzxh"/>
			</logic:notEqual>
			<input type="hidden" name="splc" id="splc"/>
			<input type="hidden" name="xmdm" id="xmdm"/>
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
								<input type="text" name="xmmc" value="" id="xmmc" style="width:120px;"  readonly="readonly"/>
								<button class="btn_01" id="selhd" type="button" >ѡ��</button>
							</td>
							<th width="20%">��Ŀ����</th>
							<td width="30%" id="xmjbmc" >
							  
                             </td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td id="xn" >
							</td>
							<th>ѧ��</th>
							<td id="xq" >
							</td>
						</tr>
						<tr>
							<th>�걨����</th>
							<td id="sbbmmc" >
							</td>
							<th>��ϵ��ʽ</th>
							<td id="lxdh" name="lxdh">
							</td>
						</tr>
						<tr>
							<th>������Ŀ</th>
							<td id="sskmmc" name="sskmmc">
							</td>
							<th>����ѧ��</th>
							<td id="jcxf" name="jcxf">
							</td>
						</tr>
						<tr>
							<th>�ɲ����Ŷ���</th>
							<td id="kcyrs" name="kcyrs">
							</td>
							<th>�������Ŷ���</th>
							<td id="sqrs" name="sqrs">
							</td>
						</tr>
						<tr>
							<th>��ͨ���Ŷ���</th>
							<td id="tgrs" name="tgrs">
							</td>
							<th>���ʼʱ��</th>
							<td id="xmkssj" name="xmkssj">
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
									    <td width="20%">${xsmap.xh}<input type="hidden" name="xh" value="${xsmap.xh}"/></td>
									    <td width="20%">${xsmap.xm}</td>
									    <td width="20%">${xsmap.xymc}</td>
									    <td width="20%">${xsmap.bjmc}</td>
									</tr>
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
								<html:text property="tdmc" maxlength="15"  styleId="tdmc"/>
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
									<button type="button" id="bc" onclick="saveTtxmSq('save');">
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