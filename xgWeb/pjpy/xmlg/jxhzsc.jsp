<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<script type="text/javascript">
<!--
	//���ؽ�ѧ��ѡ���ֵ
	function loadjxjxx() {
		var jxjmc = document.getElementById('jxjmc').value;
		if (jxjmc == null || jxjmc == '') {
			document.getElementById('jxjmc').value = "����ѡ��ѧ����Ϣ!";
		} 
	}
	//��ʾ��ѧ��ѡ���б��
	function showJxjxx() {
		var jxjmc = document.getElementById('jxjmc').value;
		if (jxjmc == '����ѡ��ѧ����Ϣ!') {
			document.getElementById('jxjmc').value = "";
		} 
		var items = document.getElementById("items");
		items.style.left = (screen.availwidth)/6;
		items.style.top = ((screen.availheight)/6);
		items.style.display = "block";
	}
	//ȫѡ��ѧ��
	function chkxy(){
				var ischecked = document.getElementById("chkallxy").checked;
				var chkoneList = document.getElementsByName("chkonexy");
				for (var i=0;i<chkoneList.length;i++) {
					chkoneList[i].checked = ischecked;
				}
			}
	//���ȷ���¼�
	function changeJxj() {
		//��ѡ��Ľ�ѧ��ֵ���ı���
		var chkoneList = document.getElementsByName("chkonexy");
		var jxjdm = "";
		var jxjmc = "";
		var jdm = "";
		var jmc = "";
		for (var i=0;i<chkoneList.length;i++) {
			var value = chkoneList[i].value;
			if (chkoneList[i].checked==true) {
				jxjdm = jxjdm += value.substr(0,value.indexOf("!@")+2);
				jxjmc = jxjmc += value.substr(value.indexOf("!@")+2,value.length-value.indexOf("!@")-2) + ",";
				document.getElementById('jxjdm').value= jxjdm;
				document.getElementById('jxjmc').value= jxjmc;
			} else {
				jdm = value.substr(0,value.indexOf("!@")+2);
				jmc = value.substr(value.indexOf("!@")+2,value.length-value.indexOf("!@")-2) + ",";
				document.getElementById('jxjdm').value= document.getElementById("jxjdm").value.replace(jdm,'');
				document.getElementById('jxjmc').value= document.getElementById("jxjmc").value.replace(jmc,'');
			}
		}
		if (jxjmc == null || jxjmc == '') {
			document.getElementById('jxjmc').value = "����ѡ��ѧ����Ϣ!";
		} 	
		var items = document.getElementById("items");
		items.style.left = (screen.availwidth)/6;
		items.style.top = ((screen.availheight)/6);
		items.style.display = "none";
	}
	//�ر��¼�
	function closeDiv() {
		var jxjmc = document.getElementById('jxjmc').value;
		if (jxjmc == null || jxjmc == '') {
			document.getElementById('jxjmc').value = "����ѡ��ѧ����Ϣ!";
		} 
		document.getElementById('items').style.display='none'	
	}
	//���ʵ��������
	function outputJxjje() {
		var jxjmc = document.getElementById('jxjmc').value;
		if (jxjmc == null || jxjmc == '' || jxjmc=='����ѡ��ѧ����Ϣ!') {
			alert("��ѡ��Ҫ��������Ľ�ѧ��!");
			return false;
		} 
		var xn = document.getElementById('xn').value;
		var jxjdm = document.getElementById('jxjdm').value;
		var xydm = document.getElementById('xydm').value;
		
		if (xydm == null || xydm == '') {
			if (confirm("δѡ���κ�<bean:message key="lable.xsgzyxpzxy" />,���ۼ��������<bean:message key="lable.xsgzyxpzxy" />�Ľ�ѧ���ʵ�����,Ҫ������?")) {
				window.open('pjpy_xmlg_jxjjehzsc.do?xn=' + xn + '&jxjdm=' + jxjdm +'&xydm=' + xydm);
			}
		} else {
			window.open('pjpy_xmlg_jxjjehzsc.do?xn=' + xn + '&jxjdm=' + jxjdm +'&xydm=' + xydm);
		}
	}
//-->
</script>
<body onload="xyDisabled('xy');loadjxjxx();">
	<html:form action="/xmlgpjpy.do" method="post">
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
		<input type="hidden" name="jxjdm" id="jxjdm" />
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�� - �������� - ��� - ����������
			</div>
		</div>
		
		<!-- ѡ��ѧ����б�� -->
		<div id="items" name="items" style="display:none; position: absolute;background-color: #D0E0EE; width: 330px" >
			<table class="tbstyle" style=" width: 330px">
				<tr>
					<td>
						<input type="checkbox" id="chkallxy" name="chkallxy" onclick="chkxy()" style="CURSOR: hand;"/><B> ȫѡ:</B>
						<logic:notEmpty name="jxjList">
							<table border="0" cellpadding="0" cellspacing="0" >   
	                               <logic:iterate   name="jxjList"   id="xyV"   indexId="index">   
	                                     <%if((index.intValue()+1)%2==1){%>   
	                                        
	                                      <tr   class="alt">     
	                                      <%}%>                                         
	                                          <td nowrap="nowrap" width="165px">
	                                          		<input type="checkbox" id="chkonexy" name="chkonexy"
													value="${xyV.dm }!@${xyV.mc }" style="CURSOR: hand;" />${xyV.mc }
	                                          </td>   
	                              			<%if((index.intValue()+1)%2==0){%>   
	                                      </tr>     
	                                      <%}%>                 
	                                      </logic:iterate>   
	                                      </table>
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<td align="center">
						<button  style="height: 19px" class="button2" onclick="changeJxj()">ȷ ��</button>
						&nbsp;&nbsp;
						<button  style="height: 19px" class="button2"
						onclick="closeDiv()">�� ��</button>
					</td>
				</tr>
			</table>
		</div>
		
		<fieldset>
			<legend>
				����ѡ��
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align="" nowrap="nowrap">
							ѧ�꣺
							<html:select property="xn" styleId="xn"
								styleClass="select">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;
							<bean:message key="lable.xsgzyxpzxy" />��
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							&nbsp; ��ѧ�����
							<html:select property="lbdm" styleId="lbdm"
								onchange="refreshForm('pjpy_xmlg_jxhzsc.do')">
								<html:option value=""></html:option>
								<html:options collection="jxjlbList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp; ��ѧ��
							<html:text property="jxjmc" styleId="jxjmc" 
								onclick="showJxjxx()" readonly="true" style="width:270px;cursor:hand"></html:text>
						</td></tr>
				</thead>
			</table>
		</fieldset>
		<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
		<center>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button class="button2" id="btn_sc" onclick="outputJxjje()">
					��ѧ���ʵ����
				</button>
			</div>
		</center>
		<div id="tmpdiv"></div>
	</html:form>
	<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
</body>
