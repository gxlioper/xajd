<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
<script language="javascript" src="/xgxt/js/xgutil.js"></script>
<script language="javascript" src="/xgxt/js/stuinfoFunction.js.js"></script>
<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>	
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/qgzxgzkh.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/CzxxJxjDao.js'></script>
<script type="text/javascript">	  
		function tjjg(){
			var xn = val('xn');
			var xq = val('xq');
			var xydm = val('xy');
			qgzxgzkh.checkExists('view_czxx_xsrychb','xn||xq||xydm',xn+xq+xydm,function(data){
				if (data != null && data == true) {
					qgzxgzkh.checkExists('pjpy_bmshtjb','zjz||dm||bm||tjzt||tjxm',xn+xq+xydm+'xy1rych',function(data){
						if(data != null && data == true){
							alert('��<bean:message key="lable.xsgzyxpzxy" />�����ƺ��������Ѿ��ύ��');
							return false;
						}else{//�ύ
							qgzxgzkh.checkExists('view_czxx_xsrychb','xn||xq||xydm||xysh||fdysh',xn+xq+xydm+'δ���ͨ��',function(data){
								if(data){
									alert('��<bean:message key="lable.xsgzyxpzxy" />���в��ֽ�ѧ������δ���,��ʱ�����ύ�����');
									return false;
								}else{
									if (confirm('ȷ��Ҫ�ύ������˵�������?')) {
										refreshForm("pjpy_czxx_rychBmsh.do?act=tj&tjxydm=" + xydm);										
									}
									return false;
								}
							});
							
						}
					});
				} else {
					alert("��<bean:message key="lable.xsgzyxpzxy" />��δ�����κ������ƺ�����,���ܽ����ύ!");
					return false;
				}
			});
		}
		
		function jxjsh(type) {
			var checkBoxArr = document.getElementsByName("cbv");
			var flag = false;
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].checked==true){
					flag = true;
				}
			}
			if (flag){
				var dd_html = "<div>";
				dd_html += "<center><br><table width='350px' class='tbstyle'>";
				dd_html += "<thead>";
				dd_html += "<tr height='30'>";
				dd_html += "<td align='center' colspan='2'>";
				dd_html += "����������������";
				dd_html += "</td>";
				dd_html += "</tr>";
				dd_html += "</thead>";
				dd_html += "<tbody>";
				dd_html += "<tr height='30'>";
				dd_html += "<td align='center' width='80px'>";
				dd_html += "��������";		
				dd_html += "</td><td>";
				dd_html += "<textarea name='yj' id='yj' rows='5' width='330px' onkeyup='checkLen(this,500)'></textarea>";
				dd_html += "</td></tr>";
				dd_html += "<tr height='30' bgcolor='EEF4F9'>";
				dd_html += "<td align='center' colspan='2'>";
				dd_html += "<button type='button' class='button2' onclick='datasub(\""+type+"\")'>ȷ��</button>&nbsp;&nbsp;";//szGroup()
				dd_html += "<button type='button' class='button2' onclick='closeAdd()'>�ر�</button>";
				dd_html += "</td>";
				dd_html += "</tr>";
				dd_html += "<tbody>";
				dd_html += "</table></center>";
				dd_html += "</div>";
				showDiv(dd_html, 400, 170);
			}else{
				alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
			}
		}
		function datasub(type) {
			refreshForm("pjpy_czxx_rychBmsh.do?act=save&jg="+type + "&yj="+document.getElementById('yj').value);
		}
		
		//��������
	function fhshdata(url) {
	var userType = document.getElementById('userType').value;
	var checkBoxArr = document.getElementsByName("cbv");
	var flag = false;
	var pk = "";
	for(var i=0;i<checkBoxArr.length;i++){
		if(checkBoxArr[i].checked==true){
			flag = true;
			pk += checkBoxArr[i].value;
			pk += "!@";
		}
	}
	if (flag){
		//����Ƿ����������ͨ��
		CzxxJxjDao.queryShjgByrych(pk,userType,function (data) {
			if (data) {
				alert("��ǰѡ��ķ�������������У��в��������Ѿ����ͨ����\n��Ҫ�������������Ƚ����������״̬����Ϊ��ͨ��!");
				return false;
			} else {
				if (confirm('ȷ��Ҫ����������ѡ���������')){
					document.forms[0].action = url;
					document.forms[0].submit();
					if ($("pt")) {
						BatAlert.showTips('���ڲ�������ȴ�...');
					}
				}		
			}
		});
	
	}else{
		alert("û��ѡ����Ӧ��¼����ѡ��֮���ٽ��в�������");
	}
}
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/czxxPjpyRych" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" name="userType" id="userType" value="${userType}" />
		<input type="hidden" name="tableName" id="tableName"
			value="${tableName }" />
		<input type="hidden" name="realTable" id="realTable"
			value="${realTable }" />
		<input type="hidden" name="userName" id="userName"
			value="${userName }" />
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
		<input type="hidden" id="pt" value="pt"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� - ��� - �����ƺ����
			</div>
		</div>
		<div class="rightcontent">
			<fieldset>
				<legend>
					�� ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<logic:equal value="xy" name="userType">
							<tr>
						
							<td align="left">
								ѧ�꣺
								<html:select property="xn" styleId="xn" disabled='true'>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;ѧ�ڣ�
								<html:select property="xq" styleId="xq" disabled='true'>
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;�꼶��
								<html:select property="nj" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;
								�����ƺţ�
								<html:select property="rychdm" styleId="rychdm" >
									<html:option value=""></html:option>
									<html:options collection="rychList" property="rychdm" labelProperty="rychmc"/>
								</html:select>
								&nbsp;
								<logic:equal value="xy" name="userType">
								��˽����
								<html:select property="xysh" styleId="xysh" >
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
								</logic:equal>
								<logic:equal value="xy" name="uesrType">
									��˽����
								<html:select property="xxsh" styleId="xxsh" >
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
								</logic:equal>
								
								<td width="10" rowspan="2" align="center" valign="middle">
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_czxx_rychBmsh.do?act=qry');">
									��ѯ
								</button>
							</td>
							</tr>
						<tr>
							<td align="left">
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select property="zydm" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="bjdm" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						</logic:equal>
					
						<logic:notEqual value="xy" name="userType">
						<tr>
							<td align="left">
								ѧ�꣺
								<html:select property="xn" styleId="xn" disabled='true'>
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;ѧ�ڣ�
								<html:select property="xq" styleId="xq" disabled='true'>
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;
									��˽����
								<html:select property="xxsh" styleId="xxsh" >
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
								<td width="10" rowspan="" align="center" valign="middle">
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_czxx_rychBmsh.do?act=qry');">
									��ѯ
								</button>
							</td>
							</tr>
						</logic:notEqual>
						
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼���� ${rsNum } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��������ͷ��������;
						</font>
						<logic:equal value="xy" name="userType">
						<font color="red"><logic:notEmpty name="tjzt">��<bean:message key="lable.xsgzyxpzxy" />�����ύ״̬: ${tjzt }</logic:notEmpty></font>
						</logic:equal>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px">
								</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand;" ondblclick="modiAndDel('pjpy_czxx_xyrychshView.do?act=view&pkValue=','modi','800','550')">
								<td align="center">
									<input type="checkbox" id="cbv" name="cbv" style="height:17.5px"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
										<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="rsTable" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=pjpyCzxxActionForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
		</div>
	</html:form>
	<div id="tmpdiv"></div>
	<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
	<center>
		<logic:equal value="yes" name="writeAble">
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button type="button" class="button2" id="btn_dc" onclick="jxjsh('tg')"
					style="width:80px">
					���ͨ��
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="jxjsh('btg')"
					style="width:80px">
					��˲�ͨ��
				</button>
				<logic:equal value="xy" name="userType">
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="tjjg()"
					style="width:80px">
					�ύ���
				</button>
				</logic:equal>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="fhshdata('pjpy_czxx_rychBmsh.do?act=fh')"
					style="width:80px">
					��������
				</button>
			</div>
			<script language="javascript">
                  document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
                  document.getElementById("btn").style.width = "96%";
                  window.setInterval("initBTNTool('btn')",1);
                </script>
		</logic:equal>
	</center>
	<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert("�����ɹ�!");
					document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert("����ʧ��!");				
				</script>
			</logic:equal>
		</logic:present>
</body>