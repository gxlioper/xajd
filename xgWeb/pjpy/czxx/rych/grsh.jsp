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
<script type="text/javascript">	  
function tjjg(){
			var dd_html = "<div>";
			dd_html += "<center><br><table width='350px' class='tbstyle'>";
			dd_html += "<thead>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center' colspan='2'>";
			dd_html += "��ѡ����Ҫ�ύ��˽���İ༶";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "</thead>";
			dd_html += "<tbody>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center' width='80px'>";
			dd_html += "ѧ�꣺";		
			dd_html += "</td><td>";
			dd_html += val('xn');
			dd_html += "</td></tr>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center'>";
			dd_html += "ѧ�ڣ�";		
			dd_html += "</td><td>";
			dd_html += selText('xq');
			dd_html += "</td></tr>";
			dd_html += "<tr height='30'>";
			dd_html += "<td align='center'>";
			dd_html += "�༶��";		
			dd_html += "</td><td>";
			dd_html += "<select id='tjbjdm' name='tjbjdm'/>";
			dd_html += "</td></tr>";
			dd_html += "<tr height='30' bgcolor='EEF4F9'>";
			dd_html += "<td align='center' colspan='2'>";
			dd_html += "<button type='button' class='button2' onclick='commitSh()'>ȷ��</button>&nbsp;&nbsp;";//szGroup()
			dd_html += "<button type='button' class='button2' onclick='closeAdd()'>�ر�</button>";
			dd_html += "</td>";
			dd_html += "</tr>";
			dd_html += "<tbody>";
			dd_html += "</table></center>";
			dd_html += "</div>";
			showDiv(dd_html, 400, 170);
			copySelect('bj','tjbjdm');
			setVal('tjbjdm',val('bj'));
		}
		
		function commitSh(){
			//�ж�ѡ��İ༶�Ƿ��Ѿ��ύ��
			var xn = val('xn');
			var xq = val('xq');
			var bjdm = val('tjbjdm');
			if(bjdm == null || bjdm == "" || bjdm == undefined){
				alert('��ѡ����Ҫ�ύ��˽���İ༶��');
				return false;
			}
			
			
			qgzxgzkh.checkExists('view_czxx_xsrychb','xn||xq||bjdm',xn+xq+bjdm,function(data){
				if (data != null && data == true) {
					qgzxgzkh.checkExists('pjpy_bmshtjb','zjz||dm||bm||tjzt||tjxm',xn+xq+bjdm+'bj1rych',function(data){
						if(data != null && data == true){
							alert('�ð༶�����ƺ��������Ѿ��ύ��');
							return false;
						}else{//�ύ
							qgzxgzkh.checkExists('view_czxx_xsrychb','xn||xq||bjdm||fdysh',xn+xq+bjdm+'δ���',function(data){
								if(data){
									alert('�ð༶���в��������ƺ�����δ���,��ʱ�����ύ�����');
									return false;
								}else{
									refreshForm("pjpy_czxx_rychsh.do?act=tj&tjbjdm=" + bjdm);
								}
							});
							
						}
					});
				} else {
					alert("�ð༶��δ�����κ������ƺ�����,���ܽ����ύ!");
					return false;
				}
			});
		}
</script>
<body onload="xyDisabled('xy');">
	<html:form action="/czxxPjpyRych" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		
		<input type="hidden" name="queryequals_xn" id="queryequals_xn" value="${xn }"/>
		<input type="hidden" name="queryequals_xq" id="queryequals_xq" value="${xq }"/>
		
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
						<tr>
							<td align="left">
								ѧ�꣺
								<html:select property="xn" styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;ѧ�ڣ�
								<html:select property="xq" styleId="xq" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;�꼶��
								<html:select property="queryequals_nj" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								
								<td width="10" rowspan="3" align="center" valign="middle">
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="refreshForm('pjpy_czxx_rychsh.do?act=qry');">
									��ѯ
								</button>
							</td>
							</tr>
							<tr>
							<td>
								�����ƺţ�
								<html:select property="queryequals_rychdm" styleId="rychdm" >
									<html:option value=""></html:option>
									<html:options collection="rychList" property="rychdm" labelProperty="rychmc"/>
								</html:select>
								&nbsp;
								��˽����
								<html:select property="queryequals_fdysh" styleId="fdysh" >
									<html:option value=""></html:option>
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
								</html:select>
								&nbsp;
								ѧ�ţ�
								<html:text property="querylike_xh"  styleId="xh" maxlength="19"></html:text>
								&nbsp;������
								<html:text property="querylike_xm" styleId="xm" maxlength="10"></html:text>
							</td>
							</tr>
						<tr>
							<td align="left">
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="queryequals_xydm" styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;רҵ��
								<html:select property="queryequals_zydm" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="queryequals_bjdm" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
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
						<font color="red"><logic:notEmpty name="tjzt">�ð༶�����ύ״̬: ${tjzt }</logic:notEmpty></font>
						
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
							<tr onclick="rowOnClick(this);" style="cursor:hand;" ondblclick="modiAndDel('pjpy_czxx_rychDggrsh.do?act=view&pkValue=','modi','750','700')">
								<td align="center">
									<input type="checkbox" id="cbv" name="primarykey_cbv" style="height:17.5px"
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"
										<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate> />
								</td>
								<logic:iterate id="v" name="s" offset="2" length="3">
									<td align="center">
										<bean:write name="v" />
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="5" length="1">
									<td align="center">
										<a href="#" onclick="queryOne('<bean:write name="v" />')"><bean:write name="v" /></a>
									</td>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="6">
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
				<button type="button" class="button2" id="btn_dc" onclick="shdata('pjpy_czxx_rychsh.do?act=save&jg=tg')"
					style="width:80px">
					���ͨ��
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="shdata('pjpy_czxx_rychsh.do?act=save&jg=btg')"
					style="width:80px">
					��˲�ͨ��
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="modiAndDel('pjpy_czxx_rychDggrsh.do?pkValue=','modi','750','700')"
					style="width:80px">
					�������
				</button>
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" id="btn_dc" onclick="tjjg()"
					style="width:80px">
					�ύ���
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