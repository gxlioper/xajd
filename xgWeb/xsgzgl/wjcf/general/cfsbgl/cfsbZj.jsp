<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>	
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/js/check.js'></script>
		<script type="text/javascript">

		//���� 
		function save(){
			confirmInfo("ȷ��Ҫ������",function(tag){
				if(tag=="ok"){
					var xh = $("xh").value;
					var cfyydm = $("cfyydm").value;
					var cflbdm = $("cflbdm").value;
					var wjsj = $("wjsj").value;
					var fjmc = $("fj").value;
					if (fjmc != null && fjmc != "") {
						var hz = fjmc.substr(fjmc.lastIndexOf(".")+1,fjmc.length);
						if (hz!='doc'&&hz!='rar'&&hz!='pdf'&&hz!='bmp'&&hz!='jpg'&&hz!='gif'&&hz!='png'){
							alertError("�ϴ��ļ�����ֻ��Ϊ��doc,rar,pdf,bmp,jpg,gif,png");
							return false;
						}
					}
					var fjmcleng = fjmc.substr(fjmc.lastIndexOf("\\")+1,(fjmc.length-fjmc.lastIndexOf("\\")));
					if(fjmcleng.length > 50){
						alertError("�ļ�������,���޸��ļ��������ϴ�");
						return false;
					}
					
					return false;
					jQuery("#fjmc").val(fjmc);
					getSztzData.getInfoEx("xg_wjcf_wjcfsbb","xh||cfyydm||cflbdm||wjsj",xh+cfyydm+cflbdm+wjsj ," 1=1",function(bool){
					       if(bool){
					    	   alertError("�Ѵ��ڸ���������Ϣ�����ܱ��棡");
					           return false;
					       }else{
					    	   if(""==xh||""==cfyydm||""==cflbdm||""==wjsj){
									alertError("�뽫��*����Ŀ��д������");
									return false;
									}
									refreshForm('general_wjcf_cfsb_ajax.do?method=saveCfsb');
								}
						});		
						}
			});
		}

		</script>
		
	</head>
	<body >
		<html:form action="/general_wjcf_cfsb_ajax" method="post" enctype='multipart/form-data'>
					<input type="hidden" name="url" id="url" value="general_wjcf_cfsb_ajax.do?method=zjWjcf">	
					<input type="hidden" name="tableName" id="tableName" value="view_xsjbxx">		
					<input type="hidden" name="message" id="message" value="${message }">		
					<input type="hidden" name="doType" id="doType"  >
					<html:hidden property="fjmc" styleId="fjmc"/>	
					<div  style="width:100%;height:460px;overflow-x:hidden;overflow-y:auto;">	
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>ѧ����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<td align="right" width="20%">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left" width="30%">
							
							<html:text  property="xh" styleId="xh" value="${rs.xh}" readonly="true"/>
							
							<button type="button"  onclick="showDialog('��ѡ��һ��ѧ��',680,500,'xsxx_xsgl.do?method=showStudents&goto=general_wjcf_cfsb_ajax.do?method=zjWjcf');return false;"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
						<td align="right" width="20%">
							������
						</td>
						<td align="left">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
							<td align="left">
							${rs.xb}
						</td>
						<td align="right">
							�꼶��
						</td>
							<td align="left">
							${rs.nj}
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xb" />��
						</td>
							<td align="left">
							${rs.xymc}
						</td>
						<td align="right">
							רҵ��
						</td>
							<td align="left">
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<td align="right">
							�༶��
						</td>
						<td align="left">
							${rs.bjmc}
						</td>
						<td align="right">
							������ò��
						</td>
							<td align="left">
							${rs.zzmmmc}
						</td>
						
					</tr>
					
					</tbody>
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����ϱ���Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<td align="right" width="20%">
							����ѧ�꣺
						</td>
						<td align="left" width="30%">
							${dqxn}
						</td>
						<td align="right" width="20%">
							����ѧ�ڣ�
						</td>
						<td align="left" width="30%">
							${dqxq}
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>����ԭ��
						</td>
						<td align="left">
							<html:select property="cfyydm" styleId="cfyydm" style="width:140px">
								<html:option value=""></html:option>
								<html:options collection="cfyyList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
						<td align="right">
							<font color="red">*</font>�������
						</td>
						<td align="left">
							<html:select property="cflbdm" styleId="cflbdm" style="width:140px">
								<html:option value=""></html:option>
								<html:options collection="cflbList" property="dm" labelProperty="mc"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							<font color="red">*</font>Υ��ʱ�䣺
						</td>
						<td align="left" colspan="3">
							<html:text property="wjsj" styleId="wjsj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('wjsj','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						<td align="right" >
							�������������ϻ򸽼���
							<br/>(��doc,rar,ͼƬ��ʽ)
						</td>
						<td align="left" colspan="3" >
							<html:file  property='fj' styleId ="fj" style="width:99%" />
						</td>
					</tr>
					<tr>
						<td align="right">
							Υ����ʵ������<br/>
							<font color="red"><B>(��1000��)</B></font>
						</td>
						<td align="left" colspan="3" >
								<html:textarea  property='wjssjg' style="width:600px"
								rows='5' onblur="checkLen(this,1000)"/>
						</td>
					</tr>
						<tr>
						<td align="right">
							��ע��<br/>
							<font color="red"><B>(��1000��)</B></font>
						</td>
						<td align="left" colspan="3" >
								<html:textarea  property='bz' style="width:600px"
								rows='5' onblur="checkLen(this,1000)"/>
						</td>
					</tr>
					</tbody>
					
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>���ܴ������</span>
								</th>
							</tr>
						</thead>
						
						<tr>
						<td colspan="4">
							<table class="formList" width="100%">
								<thead align="center">
									<tr align="center">
										<td ><b>ѧ��</b></td>
										<td ><b>ѧ��</b></td>
										<td><b>�������</b></td>
										<td ><b>����ԭ��</b></td>
										<td ><b>����ʱ��</b></td>
										<td ><b>�����ĺ�</b></td>
									</tr>
								</thead>
								<tbody align="center">
							<logic:notEmpty name="yscfqkList">
							<logic:iterate name="yscfqkList" id="s">
										<tr  style="cursor:hand">
										<td >
												${s.xn}
											</td>
											<td >
												${s.xqmc}
											</td>
											<td >
												${s.cflbmc}
											</td>
											<td >
												${s.cfyymc}
											</td>
											<td>
												${s.cfsj}
											</td>
											<td>
												${s.cfwh}
											</td>
										</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="yscfqkList">
									<tr style="height:22px">
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
											<td>
											</td>
										</tr>
							</logic:empty>
							</tbody>
							</table>
							</td>
				</tr>
						
						<tbody>
					
					</tbody>
		
				</table>
			</div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="save();return false;" id="buttonSave">
									�� ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
</html>
