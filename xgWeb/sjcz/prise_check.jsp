<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/chgJxjlist.js'></script>
		<script type="text/javascript" src="pjpy/ynys/ynysJs/ynys.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script language="javascript">
	
	function chgJxjlists(xydms) {
		var xydm = document.getElementById(xydms).value;
		chgJxjlist.xyJxjList(xydm,function(data) {
					DWRUtil.removeAllOptions('jxjdm');			
					var o = [{id:'',labelText:''}];
					DWRUtil.addOptions('jxjdm',o,'id','labelText');
					for(var i=0;i<data.length;i++){
						o = [{id:data[i].jxjdm,labelText:data[i].jxjmc}];
					DWRUtil.addOptions('jxjdm',o,'id','labelText');
					}
		});
	}
	function querydata() {
		var xxdm = document.getElementById('xxdm').value;
		var isFdy = document.getElementById('isFdy').value;
		var xmdm = document.getElementById('xmdm').value;
				var bj = document.getElementById('bj').value;
		
			refreshForm('/xgxt/prise_check.do');
		
	}
	function ffbhz(typ) {
		if (typ=='0') {
			if (document.getElementById('jxjdm').value=='') {
				alert('��ѡ��Ҫ���ܵĽ�ѧ��!');
				return;
			}  
			window.open('pjpy_xcxy_jxjffb.do?jxjdm='+document.getElementById('jxjdm').value);	
		} else {
			window.open('pjpy_xcxy_yxxsjxjffb.do');
		}
	}
	function xcxydybb() {
		if($('tjblx').value==''){
			alert('��ѡ��Ҫͳ�Ƶı�������');
		}else{
			if($('tjblx').value=='myzyjxjmx'||$('tjblx').value=='myzyjxjhz'||$('tjblx').value=='yxxsjxjffhz'||$('tjblx').value=='yxxsjxjff'){
				if($('xy').value==''){
					alert('���౨��ͳ�Ʊ���ѡ��<bean:message key="lable.xsgzyxpzxy" />!');
					return;
				}
			}
			window.open('pjpy_xcxy_yxxsjxjffb.do?xydm='+$('xy').value+'&tjblx='+$('tjblx').value);
		}
	}

</script>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script language="javascript" src="js/webPrint.js"></script>
		<script type="text/javascript">
	function chkPriseOnes(url, w, h) {
	var xxdm = "";
	if($('xxdm')){
		xxdm = document.getElementById("xxdm").value;
	}
	if (w == null) {
		w = 700;
	}
	if (h == null) {
		h = 500;
	}	
	if (curr_row == null) {
		alert("��ѡ��Ҫ�������У�");
		return false;
	} else {		
		var val = curr_row.cells[0].getElementsByTagName("input")[0].value;		
		url += val;
		var tab='';
		if ($('realTable')) {
				tab = document.getElementById('realTable').value;
			}
		if((xxdm=='11551' && tab=='xsjxjb') || ((xxdm=='11551') && tab=='xsrychb')){
			url += "&xh=";
			url += curr_row.cells[5].innerText;
		}
		
		//if ((xxdm=='13022' && tab=='xsjxjb') || ((xxdm=='13022') && tab=='xsrychb')) {
					
		//	url += '&jqfpm=';
		//	url += curr_row.cells[0].getElementsByTagName("input")[1].value;
		//	url += '&zhszcpzfpm=';
		//	url += curr_row.cells[0].getElementsByTagName("input")[2].value;
		//}
		if (xxdm=='10628#') {
			url = 'pjpy_xcxy_jxjshone.do?pkVal=';
			url += curr_row.cells[0].getElementsByTagName("input")[0].value;	
		}
		
		showTopWin(url, w, h);
   }
}

		
	function checkQhts() {
			var jxjmc = selText('jxjdm');
			var qhtsjxjmc = '�庮��ʹ��ѧ��';
			if(qhtsjxjmc==jxjmc){
				refreshForm('/xgxt/nbty_qhtsjxj.do?method=qhtsjxjsh');
			}else{
				refreshForm('/xgxt/prise_check.do')
			}
		}
	</script>
	</head>
	<!-- ��ɳ����������� -->
	<logic:present name="iscsmz">
		<body onload="xyDisabled('xy');chgDisp('dispFlag')">
			<script language="javascript" src="js/function.js"></script>
			<script language="javascript" src="js/pjpyFunction.js"></script>
			<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
			<html:form action="/prise_conf_rs" method="post">
				<div class="tab_cur">
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a><bean:write name="tips" scope="request" />
						</a>
					</p>
				</div>
				
<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />
				<input type="hidden" id="tableName" name="tableName"
					value="<bean:write name="tableName" scope="request"/>" />
				<input type="hidden" id="act" name="act"
					value="<bean:write name="act" scope="request"/>" />
				<input type="hidden" id="realTable" name="realTable"
					value="<bean:write name="realTable" scope="request"/>" />
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="pk" scope="request"/>" />
				<input type="hidden" id="bmlb" name="bmlb" value="xy" />
				<input type="hidden" name="zyV" id="zyV" value="" />
				<input type="hidden" name="bjV" id="bjV" value="" />
				<input type="hidden" name="tableName" id="tableName"
					value="${tableName }" />
				<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
				<input type="hidden" name="userName" id="userName"
					value="<bean:write name="userName" scope="session"/>" />
				<fieldset>
					<legend>
						��������
					</legend>
					<
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									ѧ��
									<html:select property="xn" style="width:90px" disabled="true"
										styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									<!-- ���ݴ�ѧ�����ǰ�ѧ�� -->
									<logic:notEqual value="11078" name="xxdm">
										&nbsp;&nbsp;���
										<html:select property="nd" style="width:70px" disabled="true"
											styleId="nd">
											<html:options collection="xnList" property="nd"
												labelProperty="nd" />
										</html:select>
									</logic:notEqual>
									<!-- end -->
									<logic:equal value="10827" name="xxdm">
										<logic:equal value="xy" name="userType">
										&nbsp;
										�����
										<html:select property="jxjlb" styleId="jxjlb"
												onchange="refreshForm('/xgxt/prise_check.do')">
												<html:option value="1">Ժ��</html:option>
												<html:option value="2">ϵ��</html:option>
											</html:select>
										</logic:equal>
									</logic:equal>

									&nbsp;&nbsp;��ѧ��
									<html:select property="xmdm" style="width:170px"
										styleId="jxjdm">
										<html:option value=""></html:option>
										<html:options collection="jxjList" property="jxjdm"
											labelProperty="jxjmc" />
									</html:select>
									&nbsp;&nbsp;��ʾ��ʽ
									<html:select property="dispFlag" style="width:50px"
										styleId="dispFalg" onchange="chgDisp('dispFlag');">
										<html:option value="xydm">ϵ</html:option>
										<html:option value="zydm">רҵ</html:option>
										<html:option value="bjdm">�༶</html:option>
									</html:select>

								</td>
								<logic:notPresent name="iscsmzFdy">
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<input type="hidden" name="tab" id="tab" value="qtjxj" />
										<!-- ��ɳ������άѧ�� -->
										<button type="button" class="button2" style="height:40px" id="search_go"
											onclick="refreshForm('/xgxt/prise_check.do')">
											��ѯ
										</button>
									</td>
								</logic:notPresent>
								<logic:present name="iscsmzFdy">
									<td width="10" rowspan="2" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<input type="hidden" name="tab" id="tab" value="qtjxj" />
										<!-- ��ɳ������άѧ�� -->
										<button type="button" class="button2" style="height:40px" id="search_go"
											onclick="refreshForm('/xgxt/prise_check.do');">
											��ѯ
										</button>
									</td>
								</logic:present>

							</tr>
							<tr>
								<td align="left" nowrap>
									ϵ
									<html:select property="xydm" style="width:170px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value="">    ȫ��    </html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									<span id="dispZy"> &nbsp;&nbsp;רҵ <html:select
											property="zydm" style="width:170px;" styleId="zy"
											onchange="initBjList()">
											<html:option value="">    ȫ��    </html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
										</html:select> </span>
									<span id="dispBj"> &nbsp;&nbsp;�༶ <html:select
											property="bjdm" style="width:150px" styleId="bj">
											<html:option value="">    ȫ��    </html:option>
											<html:options collection="bjList" property="en"
												labelProperty="cn" />
										</html:select> </span> &nbsp;&nbsp;״̬
									<html:select property="zt" styleId="zt">
										<html:option value=""></html:option>
										<html:option value="δ���">δ���</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
										<html:option value="ͨ��">ͨ��</html:option>
									</html:select>
								</td>

							</tr>
							<logic:present name="showhzy">
								<tr>
									<td align="left" nowrap>
										<span id="dispShf">��˷� <html:select property="yesNo">
												<html:option value="">    ȫ��    </html:option>
												<html:options collection="chkList" property="en"
													labelProperty="cn" />
											</html:select> </span>
									</td>
								</tr>
							</logic:present>
							<%--<logic:equal value="yes" name="isAHJG">
							<tr>
								<td align="left" nowrap="nowrap">
									�Զ��������&nbsp;ƽ����
									<html:text property="pjf" styleId="pjf" styleClass="inputtext;"
										maxlength="4" onblur="ckdata(this)"></html:text>
									&nbsp;&nbsp;&nbsp;&nbsp;��ռ�༶����
									<html:text property="bjbl" styleId="bjbl"
										styleClass="inputtext;" maxlength="4" 
										onblur="ckdata(this)" ></html:text>(%)
								</td>
							</tr>
						</logic:equal>
					--%>
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
							��¼��
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ˫��һ�п��Բ鿴��ϸ��Ϣ�������Ըı����״̬��������ͷ��������,����������Ϊ
								${jxjrs } </font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" id="selall" name="selall"
											onclick="selAll()" />
									</td>
									<logic:iterate id="tit" name="topTr" offset="4">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap="nowrap">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" align="center"
									style="cursor:hand;background-color:
					    <logic:iterate id="v" name="s" offset="2" length="1">
					    <bean:write name="v"/>
					    </logic:iterate>
					    "
									ondblclick="chkPriseOnes('/xgxt/priseChkOne.do?act=view&pkVal=',550,550)">
									<td>
										<input type="checkbox" id="checkVal" name="checkVal"
											<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>
											value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>
									<td>
										<logic:iterate id="v" name="s" offset="3" length="1">
											<input type="hidden" id="keyval" name="keyval"
												value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="7" length="1">
											<input type="hidden" id="xhval" name="xhval"
												value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="4" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="5">
										<td>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>

				<logic:match value="yes" name="rw" scope="request">
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:notPresent name="iscsmzFdy">
							<button type="button" class="button2" onclick="checkAll('pass')">
								���ͨ��
							</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="checkAll('nopass')">
								��˲�ͨ��
							</button>
						&nbsp;&nbsp;
						<button type="button" class="button2"
								onclick="priseAutoChk('/xgxt/priseAutoCheck.do')">
								�Զ����
							</button>
							<logic:notPresent name="cqkjisFdy">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
									onclick="if(confirm('��������pdf��ʽ��ʾ��\n��ǰ��ȷ�����ļ�����ϰ�װ�˿��Բ鿴pdf�ļ�����������\nȷ��Ҫ����')){chgRight('/xgxt/nameList.do?typ=prise','_blank');document.forms[0].target = '_self';}return false;">
									��������
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
									onclick="document.all('tableName').value='view_jxjhz';dataExport()">
									�������
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
									onclick="showTopWin('/xgxt/viewJxjHz.do',750,600)">
									�������
								</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
									onclick="document.all('tableName').value='tem_jxjlb';dataExport()">
									����һ����
								</button>
							</logic:notPresent>
						</logic:notPresent>
						<logic:present name="iscsmzFdy">
							<button type="button" class="button2" onclick="checkAll('pass')">
								���ͨ��
							</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="checkAll('nopass')">
								��˲�ͨ��
							</button>
						</logic:present>
						<logic:present name="showxcxy">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="djbPrint()">
								�ڽ��ǼǱ��ӡ
							</button>
						</logic:present>
					</div>
					<logic:present name="showxcxy">
						<script language="javascript">
				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 55;
				document.getElementById("btn").style.width = "96%";
				window.setInterval("initBTNTool('btn')",1);
				</script>
					</logic:present>
					<logic:notPresent name="showxcxy">
						<script language="javascript">
				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
				document.getElementById("btn").style.width = "96%";
				window.setInterval("initBTNTool('btn')",1);
				</script>
					</logic:notPresent>
				</logic:match>
				<div id="tmpdiv"></div>
				<logic:present name="autoChk">
					<logic:equal name="autoChk" value="ok" scope="request">
						<script language="javascript">
      alert("�Զ������ɣ�");
	  </script>
					</logic:equal>
					<logic:equal name="autoChk" value="no" scope="request">
						<script language="javascript">
      alert("�ۺ����ʲ�����δ��ȫ���룬�����Զ���ˡ�\n���ȼ���ۺ����ʲ������ݣ�");
 	  </script>
					</logic:equal>
					<logic:equal name="autoChk" value="ov" scope="request">
						<script language="javascript">
      alert("����ѡ����˵�λ�Ľ�ѧ���ܽ��ޡ�\n���ȼ�����ã�");
 	  </script>
					</logic:equal>
				</logic:present>
			</html:form>
			<script language="javascript">
if(document.getElementById("jxjtmp") != null){
	document.getElementById("jxjtmp").style.top = -435;
}
</script>
			<script language="javascript">
     function djbPrint(){
        if(curr_row == null){
            alert('��ѡ��Ҫ��ӡ�����ݣ�\n��������Ӧ���У�');
            return false;
        }else{
            var pkV =  curr_row.getElementsByTagName("input")[1].value;
            var xh = curr_row.getElementsByTagName("input")[2].value;
            var jxjdm = pkV.substring(pkV.indexOf(xh)+xh.length,pkV.length);
            window.open('jxjpsdjb.do?xh='+xh+'&jxjdm='+jxjdm);
        }
     } 
</script>
		</body>
	</logic:present>


	<logic:notPresent name="iscsmz">
		<body onload="xyDisabled('xy');chgDisp('dispFlag')">
			<script language="javascript" src="js/function.js"></script>
			<script language="javascript" src="js/pjpyFunction.js"></script>
			<script language="javascript" src="pjpy/ahjg/ahjgjs/ahjgjs.js"></script>
			<html:form action="/prise_conf_rs" method="post">
				<div class="tab_cur">
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a><bean:write name="tips" scope="request" />
						</a>
					</p>
				</div>

				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="request"/>" />

				<logic:equal value="12872" name="xxdm">
					<input type="hidden" id="tableName" name="tableName" value="xsjxjb" />
					<input type="hidden" id="realTable" name="realTable" value="xsjxjb" />
				</logic:equal>
				<logic:notEqual value="12872" name="xxdm">
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
				</logic:notEqual>
				<input type="hidden" id="act" name="act"
					value="<bean:write name="act" scope="request"/>" />

				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="pk" scope="request"/>" />
				<input type="hidden" id="bmlb" name="bmlb" value="xy" />
				<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />
				<input type="hidden" id="jmc" name="jmc" value="${jmc }" />
				<input type="hidden" id="shresult" name="shresult"
					value="${shresult}" />
				<input type="hidden" id="sJxjtg" name="sJxjtg" value="${sJxjtg}" />
				<%--			<input type="hidden" id="isFdy" name="isFdy" value="${isFdy}" />--%>
				<input type="hidden" name="zyV" id="zyV" value="" />
				<input type="hidden" name="bjV" id="bjV" value="" />
				<input type="hidden" name="tableName" id="tableName"
					value="${tableName }" />
				<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
				<input type="hidden" name="userName" id="userName"
					value="<bean:write name="userName" scope="session"/>" />

				<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<logic:notPresent name="showzjjd">
								<logic:match value="yes" name="rw" scope="request">
									<logic:notPresent name="iscsmzFdy">
										<%--�人����ѧ--%>
										<!-- ��ְԺ -->
										<logic:equal value="12872" name="xxdm">
											<logic:notEqual value="true" name="isFdy">
												<!-- ����Ա����ʾ��˰�ť -->
												<li>
													<a href="#" class="btn_shtg" onclick="checkAll('pass')">���ͨ��</a>
												</li>
												<li>
													<a href="#" class="btn_shbtg" onclick="checkAll('nopass')">��˲�ͨ��</a>
												</li>

											</logic:notEqual>
										</logic:equal>

										<!-- �Ǻ�ְԺ -->
										<logic:notEqual value="12872" name="xxdm">
											<li>
												<a href="#" class="btn_shtg" onclick="shdata()">���ͨ��</a>
											</li>
											<li>
												<a href="#" class="btn_shbtg" onclick="checkAll('nopass')">��˲�ͨ��</a>
											</li>
										</logic:notEqual>

										<logic:equal value="11647" name="xxdm">
											<li>
												<a href="#" class="btn_dy" onclick="pjpyprint()">��ӡ����</a>
											</li>
										</logic:equal>
										<logic:equal value="13108" name="xxdm">
											<li>
												<a href="#" class="btn_sh"
													onclick="priseAutoChk('/xgxt/priseAutoCheck.do')">�Զ����</a>
											</li>
										</logic:equal>
										<logic:equal value="10878" name="xxdm">
											<li>
												<a href="#" class="btn_sh" onclick="ahjgqutosh()">�Զ����</a>
											</li>
										</logic:equal>

										<logic:notPresent name="cqkjisFdy">
											<%--�人����ѧ--%>
											<logic:equal value="10497" name="xxdm">
												<li>
													<a href="#" class="btn_dr" onclick="impAndChkData()">������˽��</a>
												</li>
												<li>
													<a href="#" class="btn_dr"
														onclick="if(document.getElementById('jxjdm').value==null || document.getElementById('jxjdm').value==''){alert('��ѡ��ѧ��!')}else{document.forms[0].target = '_blank';refreshForm('pjpy_whlgdx.do?method=priseExport');document.forms[0].target = '_self';}">������ʽ��</a>
												</li>
											</logic:equal>
										</logic:notPresent>
									</logic:notPresent>

									<!-- ��ɳ��������Ա -->
									<logic:present name="iscsmzFdy">
										<li>
											<a href="#" class="btn_shtg" onclick="checkAll('pass')">���ͨ��</a>
										</li>
										<li>
											<a href="#" class="btn_shbtg" onclick="checkAll('nopass')">��˲�ͨ��</a>
										</li>
									</logic:present>

									<!-- ����<bean:message key="lable.xsgzyxpzxy" /> -->
									<logic:present name="showxcxy">
										<li>
											<a href="#" class="btn_dy" onclick="djbPrint()">�ڽ��ǼǱ��ӡ</a>
										</li>
										<li>
											<a href="#" class="btn_dy" onclick="pjpytjbdy()">ͳ�Ʊ��ӡ</a>
										</li>
									</logic:present>

									<!-- ��ְԺ -->
									<logic:present name="showhzy">
										<li>
											<a href="#" class="btn_zj" onclick="plqm('xsjxjb')">����ǩ��</a>
										</li>
										<li>
											<a href="#" class="btn_dy" onclick="hzyprint()">��ӡ����</a>
										</li>
										<li>
											<a href="#" class="btn_dy" onclick="zsldprint()">��������</a>
										</li>
									</logic:present>
								</logic:match>
							</logic:notPresent>


							<logic:present name="showzjjd">
								<logic:match value="yes" name="rw" scope="request">
									<li>
										<a href="#" class="btn_shtg" onclick="checkAll('pass')">���ͨ��</a>
									</li>
									<li>
										<a href="#" class="btn_shbtg" onclick="checkAll('nopass')">��˲�ͨ��</a>
									</li>
									<li>
										<a href="#" class="btn_zj"
											onclick="if(confirm('��������pdf��ʽ��ʾ��\n��ǰ��ȷ�����ļ�����ϰ�װ�˿��Բ鿴pdf�ļ�����������\nȷ��Ҫ����')){chgRight('/xgxt/nameList.do?typ=prise','_blank');document.forms[0].target = '_self';}return false;">��������</a>
									</li>
									<li>
										<a href="#" class="btn_sh"
											onclick="document.all('tableName').value='view_jxjhz';dataExport()">�������</a>
									</li>
									<logic:notEqual name="xxdm" value="12861">
										<li>
											<a href="#" class="btn_shtg"
												onclick="showTopWin('/xgxt/viewJxjHz.do',750,600)">�������</a>
										</li>
									</logic:notEqual>
								</logic:match>
							</logic:present>

						</ul>
					</div>
				</div>

				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<logic:present name="showhzy">

											<input type="hidden" name="go" value="a" />
											<button type="button" id="search_go"
												onclick="refreshForm('/xgxt/prise_check.do')">
												��ѯ
											</button>

										</logic:present>
										<logic:notPresent name="showhzy">
											<logic:present name="shownblg">

												<input type="hidden" name="go" value="a" />
												<button type="button" id="search_go"
													onclick="if (document.getElementById('jxjdm').value=='' || document.getElementById('nj').value=='') {alert('��ѯ�������꼶�뽱ѧ��Ϊ��ѡ����ȷ�ϣ�');return;} else refreshForm('/xgxt/prise_check.do');">
													��ѯ
												</button>

											</logic:present>
											<logic:notPresent name="shownblg">
												<logic:present name="isAHJG">

													<input type="hidden" name="go" value="a" />
													<button type="button" id="search_go"
														onclick="refreshForm('/xgxt/prise_check.do')">
														��ѯ
													</button>

												</logic:present>
												<logic:notPresent name="isAHJG">

													<input type="hidden" name="go" value="a" />
													<input type="hidden" name="tab" id="tab" value="qtjxj" />
													<!-- ��ɳ������άѧ�� -->
													<button type="button" id="search_go" onclick="querydata()">
														��ѯ
													</button>

												</logic:notPresent>
											</logic:notPresent>
										</logic:notPresent>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>

						<tbody>
							<tr>
								<th>
									ѧ��
								</th>
								<td>
									<html:select property="xn" style="width:95px" disabled="true"
										styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
								</td>

								<logic:notEqual value="13108" name="xxdm">
									<!-- ���ݴ�ѧ�����ǰ�ѧ�� -->
									<logic:notEqual value="11078" name="xxdm">

										<th>
											���
										</th>
										<td>
											<html:select property="nd" style="width:70px" disabled="true"
												styleId="nd">
												<html:options collection="xnList" property="nd"
													labelProperty="nd" />
											</html:select>
										</td>
									</logic:notEqual>
								</logic:notEqual>

								<th>
									�꼶
								</th>
								<td>
									<html:select property="nj" styleId="nj"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
							</tr>
							<tr>

								<th>
									ϵ
								</th>
								<logic:equal value="10827" name="xxdm">
									<td>
										<html:select property="xydm" style="width:200px" styleId="xy"
											onchange="initZyList();initBjList();chgJxjlists('xy');">
											<html:option value="">    ȫ��    </html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
								</logic:equal>
								<logic:notEqual value="10827" name="xxdm">
									<td>
										<html:select property="xydm" style="width:170px" styleId="xy"
											onchange="initZyList();initBjList()">
											<html:option value="">    ȫ��    </html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
									</td>
								</logic:notEqual>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="zydm" style="width:150px;" styleId="zy"
										onchange="initBjList()">
										<html:option value="">    ȫ��    </html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="bjdm" style="width:120px" styleId="bj"
										onchange="refreshForm('/xgxt/prise_check.do');">
										<html:option value="">    ȫ��    </html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<logic:equal value="10827" name="xxdm">
									<logic:equal value="xy" name="userType">
										<th>
											�����
										</th>
										<td>
											<html:select property="jxjlb" styleId="jxjlb"
												onchange="refreshForm('/xgxt/prise_check.do')">
												<html:option value="1">Ժ��</html:option>
												<html:option value="2">ϵ��</html:option>
											</html:select>
										</td>
									</logic:equal>
								</logic:equal>
								<th>
									��ѧ��
								</th>
								<logic:equal value="10827" name="xxdm">
									<td>
										<html:select property="xmdm" style="width:160px"
											styleId="jxjdm">
											<html:option value=""></html:option>
											<html:options collection="jxjList" property="jxjdm"
												labelProperty="jxjmc" />
										</html:select>
									</td>
								</logic:equal>
								<logic:notEqual value="10827" name="xxdm">
									<td>
										<html:select property="xmdm" style="width:160px"
											onchange="checkQhts();" styleId="jxjdm">
											<html:option value=""></html:option>
											<html:options collection="jxjList" property="jxjdm"
												labelProperty="jxjmc" />
										</html:select>
									</td>
								</logic:notEqual>
								<th>
									��ʾ��ʽ
								</th>
								<td>
									<html:select property="dispFlag" style="width:50px"
										styleId="dispFlag" onchange="chgDisp('dispFlag');">
										<html:option value="xydm">ϵ</html:option>
										<html:option value="zydm">רҵ</html:option>
										<html:option value="bjdm">�༶</html:option>
									</html:select>
								</td>
								<logic:present name="showhzy">
									<span id="dispShf">��˷� <html:select property="yesNo">
											<html:option value="">    ȫ��    </html:option>
											<html:options collection="chkList" property="en"
												labelProperty="cn" />
										</html:select> </span>
								</logic:present>
								<logic:notEqual value="12872" name="xxdm">
									<th>
										״̬
									</th>
									<td>
										<html:select property="zt" styleId="zt">
											<html:option value=""></html:option>
											<html:option value="δ���">δ���</html:option>
											<html:option value="��ͨ��">��ͨ��</html:option>
											<html:option value="ͨ��">ͨ��</html:option>
										</html:select>
									</td>
								</logic:notEqual>
							</tr>

						</tbody>
					</table>
				</div>

				<div class="formbox">
					<logic:empty name="rs">
						<h3 class="datetitle_01">
							<span> ��ѯ���&nbsp;&nbsp; <font color="red">δ�ҵ��κμ�¼��</font>
							</span>
						</h3>
					</logic:empty>
					<logic:notEmpty name="rs">

						<span> <logic:equal value="12764" name="xxdm">
								<logic:equal value="true" name="isFdy">
									<font color="blue">��ʾ˫��һ�п��Բ鿴��ϸ��Ϣ����������뽱ѧ��&nbsp;${bjsqrs
										}&nbsp;����������&nbsp;${bjysqrs }&nbsp;����δ���&nbsp;${bjwshrs
										}&nbsp;���� </font>
								</logic:equal>
								<logic:notEqual value="true" name="isFdy">
									<font color="blue">��ʾ˫��һ�п��Բ鿴��ϸ��Ϣ�������Ըı����״̬��������ͷ�������� </font>
								</logic:notEqual>
							</logic:equal> <logic:notEqual value="12764" name="xxdm">
								<h3 class="datetitle_01">
									<span> ��ѯ���&nbsp;&nbsp;<font color="blue">��¼��: <bean:write
												name="rsNum" /> ˫��һ�п��Բ鿴��ϸ��Ϣ�������Ըı����״̬��������ͷ��������</font> </span>
								</h3>

							</logic:notEqual> </span>
						<!-- <bean:message key="lable.xsgzyxpzxy" /> -->
						<logic:equal value="xy" name="userType">
							<!-- ��ɳ���� -->
							<logic:equal value="10827" name="xxdm">
								<table width="100%" id="rsTable" class="dateline">
									<thead>
										<tr align="left" style="cursor:hand">
											<td>
												<input type="checkbox" id="selall" name="selall"
													onclick="selAll()" />
											</td>
											<logic:iterate id="tit" name="topTr" offset="4">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)">
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)" align="left"
											style="cursor:hand;background-color:
					    <logic:iterate id="v" name="s" offset="2" length="1">
					    <bean:write name="v"/>
					    </logic:iterate>
					    "
											ondblclick="chkPriseOnes('/xgxt/priseChkOne.do?act=view&pkVal=',780,650)">
											<td>
												<input type="checkbox" id="checkVal" name="checkVal"
													<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>
													value="<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate>" />
												<logic:present name="shownblg">
													<input type="hidden"
														value="<logic:iterate id="v" name="s" offset="11" length="1"><bean:write name="v" /></logic:iterate>" />
													<input type="hidden"
														value="<logic:iterate id="v" name="s" offset="12" length="1"><bean:write name="v" /></logic:iterate>" />
												</logic:present>
											</td>
											<td>
												<logic:iterate id="v" name="s" offset="3" length="1">
													<input type="hidden" id="keyval" name="keyval"
														value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="7" length="1">
													<input type="hidden" id="xhval" name="xhval"
														value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="4" length="1">
													<bean:write name="v" />
												</logic:iterate>
											</td>
											<logic:iterate id="v" name="s" offset="5">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</table>
							</logic:equal>
							<!-- �ǳ�ɳ���� -->
							<logic:notEqual value="10827" name="xxdm">
								<table width="100%" id="rsTable" class="dateline">
									<thead>
										<tr align="left" style="cursor:hand">
											<td>
												<input type="checkbox" id="selall" name="selall"
													onclick="selAll()" />
											</td>
											<logic:iterate id="tit" name="topTr" offset="3">
												<td id="<bean:write name="tit" property="en"/>"
													onclick="tableSort(this)">
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
										</tr>
									</thead>
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)" align="center"
											style="cursor:hand;background-color:
					    <logic:iterate id="v" name="s" offset="1" length="1">
					    <bean:write name="v"/>
					    </logic:iterate>
					    "
											<logic:equal value="10690" name="xxdm">ondblclick="shJxj('ynys_jxjsh.do?pkValue=')"</logic:equal>
											<logic:notEqual value="10690" name="xxdm"> ondblclick="chkPriseOnes('/xgxt/priseChkOne.do?act=view&pkVal=',780,650)"</logic:notEqual>>
											<td>
												<input type="checkbox" id="checkVal" name="checkVal"
													value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
												<logic:present name="shownblg">
													<input type="hidden"
														value="<logic:iterate id="v" name="s" offset="10" length="1"><bean:write name="v" /></logic:iterate>" />
													<input type="hidden"
														value="<logic:iterate id="v" name="s" offset="11" length="1"><bean:write name="v" /></logic:iterate>" />
												</logic:present>
											</td>
											<td>
												<logic:iterate id="v" name="s" offset="2" length="1">
													<input type="hidden" id="keyval" name="keyval"
														value="<bean:write name="v"/>" />
												</logic:iterate>
												<logic:iterate id="v" name="s" offset="6" length="1">
													<input type="hidden" id="xhval" name="xhval"
														value="<bean:write name="v"/>" />
												</logic:iterate>

												<logic:iterate id="v" name="s" offset="3" length="1">
													<bean:write name="v" />
												</logic:iterate>

											</td>

											<logic:iterate id="v" name="s" offset="4" length="1">
												<td>
													<bean:write name="v" />
													<input type="hidden" value="<bean:write name="v"/>" />
												</td>
											</logic:iterate>


											<logic:iterate id="v" name="s" offset="5">
												<td>
													<bean:write name="v" />
												</td>
											</logic:iterate>

										</tr>
									</logic:iterate>
								</table>
							</logic:notEqual>
						</logic:equal>
						<!-- ��<bean:message key="lable.xsgzyxpzxy" /> -->
						<logic:notEqual value="xy" name="userType">
							<table width="100%" id="rsTable" class="dateline">
								<thead>
									<tr align="left" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="3">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)" align="left"
										style="cursor:hand;background-color:
					    <logic:iterate id="v" name="s" offset="1" length="1">
					    <bean:write name="v"/>
					    </logic:iterate>
					    "
										<logic:equal value="10690" name="xxdm">ondblclick="shJxj('ynys_jxjsh.do?pkValue=')"</logic:equal>
										<logic:notEqual value="10690" name="xxdm">ondblclick="chkPriseOnes('/xgxt/priseChkOne.do?act=view&pkVal=',780,650)"</logic:notEqual>>
										<td>
											<input type="checkbox" id="checkVal" name="checkVal"
												value="<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>" />
											<logic:present name="shownblg">
												<input type="hidden"
													value="<logic:iterate id="v" name="s" offset="10" length="1"><bean:write name="v" /></logic:iterate>" />
												<input type="hidden"
													value="<logic:iterate id="v" name="s" offset="11" length="1"><bean:write name="v" /></logic:iterate>" />
											</logic:present>
										</td>
										<td>
											<logic:iterate id="v" name="s" offset="2" length="1">
												<input type="hidden" id="keyval" name="keyval"
													value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="6" length="1">
												<input type="hidden" id="xhval" name="xhval"
													value="<bean:write name="v"/>" />
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="3" length="1">
												<bean:write name="v" />
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="4" length="1">
											<td>
												<a href="javascript:showXsxx('<bean:write name="v" />')"><bean:write
														name="v" /> </a>
												<input type="hidden" value="<bean:write name="v"/>" />
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="5">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
						</logic:notEqual>

					</logic:notEmpty>
				</div>


				<logic:present name="autoChk">
					<logic:equal name="autoChk" value="ok" scope="request">
						<script language="javascript">
		      alert("�Զ������ɣ�");
			  </script>
					</logic:equal>
					<logic:equal name="autoChk" value="no" scope="request">
						<script language="javascript">
      alert("�ۺ����ʲ�����δ��ȫ���룬�����Զ���ˡ�\n���ȼ���ۺ����ʲ������ݣ�");
 	  </script>
					</logic:equal>
					<logic:equal name="autoChk" value="ov" scope="request">
						<script language="javascript">
      alert("����ѡ����˵�λ�Ľ�ѧ���ܽ��ޡ�\n���ȼ�����ã�");
 	  </script>
					</logic:equal>
				</logic:present>

				<!-- ��������˲�ͨ�� -->
				<logic:present name="shresult">
					<script>
					alert(''+document.getElementById('shresult').value);
				</script>
				</logic:present>
				<logic:present name="sJxjtg">
					<script>
					alert(''+document.getElementById('sJxjtg').value);
				</script>
				</logic:present>
				<logic:present name="mes">
					<logic:notEmpty name="mes">
						<input id="mes" name="mes" value="${mes}" type="hidden" />
						<script>
				alert(document.getElementById('mes').value);
			</script>
					</logic:notEmpty>
				</logic:present>
				<div id="tmpdiv1"></div>
			</html:form>
			<script language="javascript">
if(document.getElementById("jxjtmp") != null){
	document.getElementById("jxjtmp").style.top = -435;
}
</script>
			<script language="javascript">
     function djbPrint(){
        if(curr_row == null){
            alert('��ѡ��Ҫ��ӡ�����ݣ�\n��������Ӧ���У�');
            return false;
        }else{
            //var pkV =  curr_row.getElementsByTagName("input")[1].value;
            var xh = curr_row.cells[2].innerText;
            //var jxjdm = pkV.substring(pkV.indexOf(xh)+xh.length,pkV.length);
            var pkValue = curr_row.getElementsByTagName("input")[0].value;
            window.open('jxjpsdjb.do?pkValue='+pkValue+'&xh='+xh);
        }
     }
     function hzyprint() {
     	if (curr_row==null || curr_row=='') 
     	{
     		alert('��ѡ��Ҫ��ӡ�������ݣ�����һ�м���!');
     		return;
     	}
     	 else {
     	 	var url = 'dxjxjsp.do?method=dxjxjsp&pk=';
     	 	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
     	 	window.open(url);
     	 }
     }
     function zsldprint() {
     	var url = 'hzzyzsld.do?pkValue=';
     	var jxjdm = document.getElementById('jxjdm').value;
     	if (jxjdm == null || jxjdm == '') {
     		alert('��ѡ��Ҫ��ӡ�Ľ�ѧ��!');
     	} else {
     		
			var boxes = document.getElementsByName("checkVal");
			var canModi = false;
			for(var i = 0; i < boxes.length; i++){
				if(boxes[i].checked){
					canModi = true;
					break;
				}
			}
			if(canModi){
			    BatAlert.MyAlert("ȷ��Ҫ���˲����𣿴˲�����������ӡѡ����ѧ������","",function(){
			 	   BatAlert.showTips("���ݲ����У����Ժ�...");
			    	var pk;
					if($("rsTable").rows.length > 1){
						//for(i = 1;i<$("tabPri").rows.length;i++){
							rowOnClick($("rsTable").rows[1]);
							var temp = kshTaoPrint();
							var tempArray = temp.split(',');
							pk=tempArray[0];
							tempArray.splice(0,1);
							window.open(url+pk+'&pks='+tempArray.join(','));
						
						//}
						BatAlert.closeTips();
					 } else{
					    BatAlert.MyAlert("û�пɴ�ӡ�����ݣ�");
						return false;
					 }
				},true);
				return false;  
			}else{
				BatAlert.MyAlert("�˲�����Ҫ��ѡ�еĸ�ѡ����ѡ��");
				return false;
			} 
		 }
     } 
     function ahjgqutosh() {
     	var xxdm = document.getElementById('xxdm').value;
     	var isFdy = document.getElementById('isFdy').value;
     	if (xxdm=='10878' && isFdy=='true') {//����Ա���
     		var jxjdm = document.getElementById('jxjdm').value;
     		var bjdm = document.getElementById('bjdm').value;
     		if (jxjdm==''||bjdm==''||bjdm=='ȫ��') {
     			alert('��ѯ�����н�ѧ����༶Ϊ��ѡ!');
     			return;
     		} else {
     			priseAutoChk('/xgxt/priseAutoCheck.do');
     		}
     	} else {//<bean:message key="lable.xsgzyxpzxy" />,ѧУ���
     		var jxjdm = document.getElementById('jxjdm').value;
     		var xydm = document.getElementById('xydm').value;
     		if (jxjdm==''||xydm==''||xydm=='ȫ��') {
     			alert('��ѯ�����н�ѧ����<bean:message key="lable.xsgzyxpzxy" />Ϊ��ѡ!');
     			return;
     		} else {
     			priseAutoChk('/xgxt/priseAutoCheck.do');
     		}
     	}
     }
     function pjpyprint() {
     	var url = 'pjpy_zjcm_jxjprint.do?pkValue=';
     	if (curr_row==''||curr_row==null) {
     		if (confirm('û��ѡ���κ�����,����һ�м���,Ҫ������ӡ��?')) {
     			window.open(url);
     		}
     		return;
     	} else {
     		var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
     		url += pk;
     		window.open(url);
     	}
     }
     var b = false;;
if(document.getElementById('rsTable')){
	for (i=0; i<document.getElementById("rsTable").rows[0].cells.length; i++){
  		if(document.getElementById("rsTable").rows[0].cells[i].id == "xh"){
  			b = true;
  			break;
  		}
  	}
  	if (b) {
  		for (j=1; j<document.getElementById("rsTable").rows.length; j++){
  		    var xhTd = document.getElementById("rsTable").rows[j].cells[i];
  		    var xhStr = xhTd.innerText.replace(/^\s+/g,"").replace(/\s+$/g,"").replace(/\n/g,"");
  		     var html_content = "<a href=\"javascript:queryOne('"+xhStr+"');\">"+xhStr+"</a>";
  			xhTd.innerHTML = html_content;
  		}
  	}
}
function queryOne(xh) {
	var url = "/xgxt/stu_info_details.do?xh="+xh;
	showTopWin(url, 800, 600);
}
function shdata() {
	var xxdm = document.getElementById('xxdm').value;
	var isFdy = document.getElementById('isFdy').value;
	var xmdm = document.getElementById('xmdm').value;
	var bj = document.getElementById('bj').value;
	if (xxdm=='12764' && 'true'==isFdy) {
		if (xmdm==''||bj==''||bj=='ȫ��') {
			alert('�������ʱ����ѧ��Ͱ༶Ϊ��ѡ��');
			return;
		} else {
		checkAll('pass');
		}
	} else {
		checkAll('pass');
	}
}
</script>
		</body>
	</logic:notPresent>
</html>
