<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
	 function dataSave(){
		 var url = "/xgxt/zjlg_xljk.do?method=xlpcbAdd&act=add";
		 var yzdz = "zzjlV-zxjyV-wtgsV";
		 var yzcd = "1200-1200-1200";
		 var mustFill = "xh-xm-xydm-bjdm";
		 //var sjhm = document.getElementById("sjhm").value;
		 //var dzyx = document.getElementById("dzyx").value;
		 //if(!isNumber(sjhm)){
			//alert("�ֻ��������������");
			//return false;
		 //}
		 checkdataSave(url,yzdz,yzcd,mustFill);
		 }
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    } 
	 function checkdataSave(url,yzdz,yzcd,mustFill){
	     //var dzyx = document.getElementById("dzyx").value;
	     //if(dzyx!=""){
		//	if(!isEmail(dzyx)){
		//		alert("���������ʽ����ȷ����");
			//	return false;
		//	}
	    // }
		 var eles = mustFill.split("-");
	    	for (var i = 0; i < eles.length; i++) {
	    		if (document.getElementById(eles[i]) && document.getElementById(eles[i]).value == "") {
	    			alert("�뽫��\"*\"�ŵ���Ŀ����������");
	    			return false;
	    		}
	    	}
	    	var splitDz = "";
	    	var splitCd = "";
	    	if(yzdz != ""){
	    		splitDz = yzdz.split("-");
	    	}
	    	if(yzcd != ""){
	    		splitCd = yzcd.split("-");
	    	}
	    	for (i = 0; i < splitDz.length; i++) {
	    		var dzsjcd = document.getElementById(splitDz[i]).cells[1].getElementsByTagName('textarea')[0].value;
	    		var dzsjmc = document.getElementById(splitDz[i]).cells[0].innerHTML;
	    		if (dzsjcd.length>splitCd[i]) {
	    			alert(dzsjmc.replace("<BR>", "")+"���ܳ���"+splitCd[i]+"���֣�");
	    			return false;
	    		}
	    	}
	    	document.forms[0].action = url;
	    	document.forms[0].submit();
	    	document.getElementById("buttonSave").disabled=true;
	 }
	 function printwmgylgl(){
			document.forms[0].action = "/xgxt/jhzy_gygl.do?method=wmgylprint";
			document.forms[0].target = "_blank";
		    document.forms[0].submit();
		    document.forms[0].target = "_self";
	 }
	</script>
	</head>
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN>
		<html:form action="/zjlg_xljk" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title"/></a>
				</p>
			</div>
			<input type="hidden" id="tableName" name="tableName" value="zjlg_xlcb" />
			<input type="hidden" id="url" name="url" value="/zjlg_xljk.do?method=getxsInfoforxh" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm" />	
			<div class="tab">
				<table width="100%" class="formlist">
					<thead><tr><th colspan="4"><span>�����ղ�</span></th></tr></thead>
					<tbody>
					<tr>			
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual>ѧ��	 
						</th>
						<td  class="Normal">
							<html:text name="rs" property="xh" readonly="true" maxlength="20"></html:text>
							<logic:notEqual value="stu" name="userType" scope="session">
								<logic:equal value="add" name="view">
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" >
									ѡ��
								</button>
								</logic:equal>
							</logic:notEqual>
							<html:hidden name="rs" property="id"/>
						</td>
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual>����
						</th>
						<td  class="Normal">
							<html:text name="rs" property="xm" readonly="true" maxlength="20"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual><bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td  class="Normal">
							<html:select name="rs" property="xydm" onchange="initBjList();"
										styleClass="select" style="width:180px;display: none" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
							</html:select>
							<html:text name="rs" property="xymc" readonly="true" maxlength="20"></html:text>
						</td>
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual> �༶
						</th>
						<td  class="Normal">
							<html:text name="rs" property="bjmc" readonly="true" maxlength="20"></html:text>
							<html:select name="rs" property="bjdm" style="width:180px;display: none" 
										styleClass="select" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							  �꼶
						</th>
						<td  class="Normal">
<%--							<html:select name="rs" property="nj" onchange="" style="width:90px;padding-left:80px">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="njList" property="nj"--%>
<%--										labelProperty="nj" />--%>
<%--							</html:select>--%>
							<html:text name="rs" property="nj" readonly="true"></html:text>
						</td>
						<th>
							 �Ա�
						</th>
						<td  class="Normal">
							<html:text name="rs" property="xb" maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							  ����
						</th>
						<td  class="Normal" colspan="3">
							<html:text name="rs" property="jg"  maxlength="80" style="width: 100%"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							����
						</th>
						<td  class="Normal">
							<html:text name="rs" property="nl" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
						<th>
							 ����
						</th>
						<td  class="Normal">
							<html:text name="rs" property="mz"  maxlength="18"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							 �Ƿ������Ů
						</th>
						<td  class="Normal">
							<html:select name="rs" property="sfdszn">	
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
						<th>
							��������
						</th>
						<td  class="Normal">
							<html:text name="rs" property="csny"  maxlength="10" readonly="true" onclick="return showCalendar('csny','y-mm-dd');" onblur="dateFormatChg(this)"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							����������
						</th>
						<td  class="Normal">
<%--							<html:text name="rs" property="xlcslb" maxlength="30"></html:text>--%>
							<html:select name="rs" property="xlcslb">
								<html:option value=""></html:option>
								<html:options collection="xlcslbList" property="xlcslbdm" labelProperty="xlcslbmc"/>
							</html:select>
						</td>
						<th>
							�Ƿ�������
						</th>
						<td  class="Normal">
							<html:select name="rs" property="sfkns">	
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							������������
						</th>
						<td  class="Normal">
<%--							<html:text name="rs" property="xlwtlx" maxlength="30"></html:text>--%>
							<html:select name="rs" property="xlwtlx">
								<html:option value=""></html:option>
								<html:options collection="xlwtlxList" property="xlwtlxdm" labelProperty="xlwtlxmc"/>
							</html:select>
						</td>
						<th>
							 ���廯��׼��
						</th>
						<td  class="Normal">
							<html:text name="rs" property="qthbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							���Ǳ�׼��
						</th>
						<td  class="Normal">
							<html:text name="rs" property="jlbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
						<th>
							������׼��
						</th>
						<td  class="Normal">
							<html:text name="rs" property="yybzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							�Ա���׼��
						</th>
						<td  class="Normal">
							<html:text name="rs" property="zbbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
						<th>
							 �罻������׼��
						</th>
						<td  class="Normal">
							<html:text name="rs" property="sjtsbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th nowrap="nowrap">
							�������ϰ���׼��
						</th>
						<td  class="Normal">
							<html:text name="rs" property="xxlzabzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
						<th>
							 ƫִ��׼��
						</th>
						<td  class="Normal">
							<html:text name="rs" property="pzbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							ǿ�ȱ�׼��
						</th>
						<td  class="Normal" colspan="">
							<html:text name="rs" property="qpbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
						<th>
							������׼��
						</th>
						<td  class="Normal">
							<html:text name="rs" property="ynbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th nowrap="nowrap" onkeyup="value=value.replace(/[^\d]/g,'') ">
							�嶯��׼��
						</th>
						<td  class="Normal">
							<html:text name="rs" property="cdbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
						<th nowrap="nowrap">
							 ���������׼��
						</th>
						<td  class="Normal">
							<html:text name="rs" property="jsbqxbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th nowrap="nowrap" onkeyup="value=value.replace(/[^\d]/g,'') ">
							�Ƿ���
						</th>
						<td  class="Normal">
							<html:select name="rs" property="sfdq">	
								<html:option value=""></html:option>
								<html:option value="��">��</html:option>
								<html:option value="��">��</html:option>
							</html:select>
						</td>
						<th>
						</th>
						<td  class="Normal">
						</td>
					</tr>
					<tr id="wtgsV">
						<th>
							 �������
						</th>
						<td  class="Normal" colspan="3">
							<html:textarea name="rs" property="wtgs" rows="5" style="width: 99%"></html:textarea>
						</td>
					</tr>
					<tr id="zxjyV">
						<th>
							���Ľ���
						</th>
						<td  class="Normal" colspan="3">
							<html:textarea name="rs" property="zxjy" rows="5" style="width: 99%"></html:textarea>
						</td>
					</tr>
					<tr id="zzjlV">
						<th>
							׷�ټ�¼
						</th>
						<td  class="Normal" colspan="3">
							<html:textarea name="rs" property="zzjl" rows="5" style="width: 99%"></html:textarea>
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4">
				          <logic:notEqual name="view" value="view">
				        	 <div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          </logic:notEqual>
				          <div class="btn">
							  <logic:notEqual name="view" value="view">
								<button onclick="dataSave();" id="buttonSave">����</button>
							  </logic:notEqual>
							  <button name="�ر�" onclick="window.close();return false;">�ر�</button>
				          </div></td>
				      </tr>
				    </tfoot>
					</table>
			</div>
		</html:form>
<logic:equal value="yes" name="done">
	<script language="javascript">
			alert("�����ɹ���");
			Close();
			window.dialogArguments.document.getElementById('search_go').click();
	</script>
</logic:equal>
<logic:equal value="yes" name="isexists">
	<script language="javascript">
			alert("�����ظ����룡");
			//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
	</script>
</logic:equal>
<logic:equal value="no" name="done">
		<script language="javascript">
				alert("����ʧ�ܣ�");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
		</script>
</logic:equal>
	</body>
</html>
