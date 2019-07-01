<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
		<script language="javascript" src="js/qgzxFunction.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
		<script language="javascript">
	function getRqVal(name)
			{
			var rq=document.getElementById(name).value;
			if (rq!="")
			{
				rqs=rq.split("-");
				rq="";
				for (var i=0;i<rqs.length;i++)
				{
					rq+=rqs[i];
				}
				document.getElementById(name).value=rq;
			}
			}
		//��֤���ݸ�ʽ�Ƿ�������
		function ckinpdata(obj){
			obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
			var inputStr = obj.value;
			
			if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 100){
				alert('���ݸ�ʽ����ȷ���۷��ֶ�ֻ������λ���֣�');
				obj.value = '';
				return false;
			}
			return true;
		}
		function savewjxx(url, pkFields, tabFlag, act) {
		
			
			var eles = pkFields.split("-");
			var valu = "";
			for (i = 0; i < eles.length; i++) {
				if($(eles[i])){
					if ($(eles[i]).value == "") {
						alert("�뽫��\"*\"�ŵ���Ŀ����������");
						return false;
					}
				}
			}
			
			for (i = 0; i < eles.length; i++) {
				if (eles[i] != 'bz') {
					valu += document.getElementById(eles[i]).value;					
				}
			}
			
			if($("cfwj")){
			var cfwj = document.getElementById('cfwj').value;
		   	if (cfwj != null && cfwj != '') {
		   		if (cfwj.length <= 4) {
		   			alert("�ϴ��ļ�·������ȷ,������ѡ��!");
		   			return false;
		   		}
		   		var kzm = cfwj.substr(cfwj.length-3,3);
		   		if (kzm == 'txt' || kzm == 'xls' ||kzm == 'doc' ||kzm == 'pdf' ||kzm == 'chm' || kzm=='rar') {
		   			
		   		} else{
		   			alert("�ϴ��ļ���ʽ����ȷ,ֻ���ϴ�txt,doc,xls,pdf,chm,rar���͵��ļ�!");
		   			return false;
		   		}
		   	}
		   	}
			if(confirm('��ȷ��Ҫ�ύ������')){
				url = url + "?tab=" + tabFlag + "&pkValue=" + valu + "&act=" + act;
				document.forms[0].action = url;
				document.forms[0].submit();
			
				BatAlert.showTips('���ڲ�������ȴ�...');
				return true;
			}
		}
		function cbbprint() {
		var cfpk = document.getElementById('cfpk').value;
    	window.open('wjcf_nbcs_cbbprint.do?cfpk=' + cfpk);
    }
    function zjcmcfbprint() {
		var cfpk = document.getElementById('cfpk').value;
    	window.open('wjcf_zjcm_cfbprint.do?cfpk=' + cfpk);
    }
</script>
		<script type="text/javascript">
	function print(typ) {
		var url = 'wjcf_nblg_cfprint.do';
			var pk = document.getElementById('cfpk').value;
			var xh = document.getElementById('xh').value;
			if (pk==null || pk=='') {
				url += '?xh=';
				url += xh;	
			} else {
				url += '?pk=';
				url += pk;
			}
			url += '&typ='+typ;
			window.open(url);
			return;
	}
	function myPrintReport(url){			
		url += document.getElementById("xh").value;
		var xxdm = document.getElementById('xxdm').value;	
		var cflb = document.getElementById('cflb').value;
		var cfyy = document.getElementById('cfyy').value;
		if ('11641'==xxdm) {
			var xh = document.getElementById('xh').value;
			
			url = 'wjcf_hhgxy_cfbprint.do?xh=';
			url += xh;
			url += '&cflb=';
			url += cflb;
			url += '&cfyy=';
			url += cfyy;
			window.open(url);
			return;
		} 
		if ($('bz')) {
			url += "&bz=";
			url += document.getElementById('bz').value;
			url += "&cflb=";
			url += cflb;
			url += "&cfyy=";
			url += cfyy;
		}
	   	window.open(url);
    }
    
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title}</a>
			</p>
		</div>


		<html:form action="/stuPunishApp" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="cfpk" id="cfpk" value="${cfpk }" />
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
    alert("�������ѧ����Ч!");
    </script>
			</logic:equal>
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/stuPunishApp.do" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��д�����</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<!-- ��������ѧԺ ѧԺ������� ����д -->
									<logic:equal name="xxdm" value="10388">
										<button type="button"
										onclick="savewjxx('/xgxt/applySave.do','xh-xm-cflb-cfyy-bz-xyclyj','wjcf')">
										�ύ����
									</button>
									</logic:equal>
									<logic:notEqual name="xxdm" value="10388">
									<button type="button"
										onclick="savewjxx('/xgxt/applySave.do','xh-xm-cflb-cfyy-bz','wjcf')">
										�ύ����
									</button>
									</logic:notEqual>
									<logic:equal value="13022" name="xxdm">
										<button type="button" onclick="print('1')">
											��ӡ(��ʽ1)
										</button>
										<button type="button" onclick="print('2')">
											��ӡ(��ʽ2)
										</button>
									</logic:equal>
									<logic:equal value="12645" name="xxdm">
										<button type="button" onclick="cbbprint()">
											�ʱ����ӡ
										</button>
									</logic:equal>
									<logic:equal value="11647" name="xxdm">
										<button type="button" onclick="zjcmcfbprint()">
											�ʱ����ӡ
										</button>
									</logic:equal>
									
									<!-- �ɶ����������ӡ -->
									<logic:equal value="true" name="print">
										<button type="button" onclick="window.open('wjcf_cdty_cfbprint.do?cfpk=' + document.getElementById('cfpk').value);">
											�ʱ����ӡ
										</button>
									</logic:equal>
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
								<logic:equal name="userOnLine" value="teacher" scope="session">
									<html:text name='rs' property="xh" styleId="xh"
										onkeypress="autoFillStuInfo(event.keyCode,this);checkXhExists('xm-xb-zzmmmc-nj-xymc-zymc-bjmc')" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:equal>
								<logic:equal name="userOnLine" value="student" scope="session">
									<html:hidden name='rs' property="xh" styleId="xh" />
									<bean:write name='rs' property="xh" />
								</logic:equal>

							</td>
							<th width="16%">
								���
							</th>
							<td width="34%">
								<bean:write name='rs' property="nd" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����
							</th>
							<td>
								<html:text property="xm" name="rs" disabled="true"></html:text>
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<bean:write name='rs' property="xn" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								<html:text property="xb" name="rs" disabled="true"></html:text>
							</td>
							<th>
								
								<logic:equal value="10290" name="xxdm"><font color="red">*</font>��������</logic:equal>
								<logic:notEqual value="10290" name="xxdm"><font color="red">*</font>�������</logic:notEqual>
							</th>
							<td>
								<html:select name="rs" property="cflb" style="width:150px"
									styleId="cflb">
									<html:option value=""></html:option>
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
								<html:text property="nj" name="rs" disabled="true"></html:text>
							</td>
							<th>
								רҵ
							</th>
							<td>
								<html:text property="zymc" name="rs" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:text property="xymc" name="rs" disabled="true"></html:text>
							</td>
							<th>
								������ò
							</th>
							<td>
								<html:text property="zzmmmc" name="rs" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td>
								<html:text property="bjmc" name="rs" disabled="true"></html:text>
							</td>
							<th>
								<font color="red">*</font>��������
							</th>
							<td>
								<html:select name="rs" property="cfyy" styleId="cfyy" style="width:160px">
									<html:option value=""></html:option>
									<html:options collection="cfyyList" property="cfyydm"
										labelProperty="cfyymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								Υ��ʱ��
							</th>
							<td>
								<html:text name='rs' property="wjsj" styleId="wjsj"
									onclick="return showCalendar('wjsj','y-mm-dd');"
									onblur="getRqVal('wjsj')" />
							</td>
							<logic:equal value="1049701" name="xxdm">
								<th>
									�۷�
								</th>
								<td>
									<html:text property="kf" name="rs" onkeyup="ckinpdata(this)"
										maxlength="10" style="90px" />
								</td>
							</logic:equal>
							<logic:notEqual value="1049701" name="xxdm">
								<logic:equal value="10388" name="xxdm">
									<th>
										�걨��
									</th>
									<td>
										<input type="text" value="${rs.sbr}" readonly="true"/>
									</td>
								</logic:equal>
								<logic:notEqual name="xxdm" value="10388">
								<logic:equal name="jsqr" value="true">
									<th>
										�Ƿ��֪ѧ��
									</th>
									<td>
										<html:select name ="rs" property="xsqr" styleId="xsqr">
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
										</html:select>
									</td>
								</logic:equal>
								<logic:notEqual name="jsqr" value="true">
								<th>

								</th>
								<td>

								</td>
								</logic:notEqual>
								</logic:notEqual>
							</logic:notEqual>
						</tr>
					<thead>
						<tr>
							<td colspan="4">
								<span>��ʷΥ�ʹ�����Ϣ &nbsp;&nbsp;<a
									onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none';getStucfxx();">�鿴</a>
								</span>
							</td>
						</tr>

					</thead>

					<tr>
						<td colspan="4">
							<div id="child4" style="display:none">
								<table width="95%" border="1" align="center" class="tbstyle">
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
									<tbody width="95%" class="tbstyle" id="cfxx" align="center"></tbody>
									
								</table>
							</div>
						</td>
					</tr>
					<logic:equal value="10290" name="xxdm">
						<tr style="height:22px">
							<th>
								��������
								<br />
								<font color="red">(������300������)</font>
							</th>
							<td align="left" colspan="3">
								<html:textarea name="rs" property="cfyj" styleId="cfyj" rows="9"
									style="width:95%" onkeyup="checkLen(this,300)">
								</html:textarea>
							</td>
						</tr>
					</logic:equal>
					<tr style="height:22px">
						<th>
							<logic:notEqual value="yes" name="isXBEMY">
								<font color="red">*</font>Υ����ʵ<br />
								<font color="red">(������1000������)</font>
							</logic:notEqual>
							<logic:equal value="yes" name="isXBEMY">
								<font color="red">*</font>Υ������
									</logic:equal>
						</th>
						<td colspan="3" align="left" style="word-break:break-all;">
							<font color="red">(����ʵ˵��Υ��ʱ��,�ص�,�¼���������ɺ��)</font>
							<html:textarea rows="10" name='rs' style="width:95%"
								property="bz" styleId="bz" onkeyup="checkLen(this,1000)" />
						</td>
					</tr>
					<logic:notEqual value="11078" name="xxdm">
						<tr>
							<th>
								��������顢������ϻ򸽼�
							</th>
							<td align="left" colspan="3">
								<font color="red">(����Ϲ��࣬�����ϴ�)</font>
								<br />
								<input type="file" name="uploadFile" id="cfwj"
									contentEditable="false" style="width:60%" />
							</td>
						</tr>
					</logic:notEqual>

					<tr>
						<th>
							<logic:equal name="xxdm" value="10388">
							<font color="red">*</font>
							</logic:equal>
							<bean:message key="lable.xsgzyxpzxy" />
							��ϵ��������&nbsp;&nbsp;
							<br />
							���������&nbsp;&nbsp;&nbsp;
							<br />
							<font color="red">(������500������)</font>
						</th>
						<td align="left" colspan="3" style="word-break:break-all;">
							<html:textarea name="rs" property="xyclyj" styleId="a" rows="6"
								style="width:95%" onkeyup="checkLen(this,500)">
							</html:textarea>
						</td>
					</tr>
					</tbody>
				</table>
			</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
				    alert("����ɹ���");
				    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
				    alert("����ʧ�ܣ�");
				    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
