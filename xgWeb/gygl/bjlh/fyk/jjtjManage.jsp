<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script type='text/javascript'
	src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
<script language="javascript" src="js/AjaxFunction.js"></script>
<script language="javascript" src="js/gygl/bjlh/bjlhFunction.js"></script>
<script type="text/javascript">
<!--
function dispXylist() {
    	var fpbj = document.getElementById('fbbj').value;
    	if (fpbj != null && fpbj != '') {
    		//document.getElementById('fbbj').disabled = true;
    	}
    	if (fpbj!='qrz' && fpbj != '') {
    		document.getElementById('xy').selectedIndex = 0;
    		document.getElementById('xy').disabled = true;
    	} else {
    		document.getElementById('xy').disabled = false;
    	}
    	var isXy = document.getElementById('isXy').value;
    	if (isXy=='yes') {
    		document.getElementById('fbbj').disabled = true;
    		document.getElementById('xy').disabled = true;
    	} 
    }
function subForm() {
	var zt = document.getElementById('zt').value;
	var url = "/xgxt/bjlh_fyk.do?method=jjtjManage";
	if (zt == '������') {
		refreshForm(url);
		return;
	}
	showTips('����ͳ���У���ȴ�......');
	refreshForm(url);
}

function changeZt() {
	var zt = document.getElementById('zt').value;
	if (zt == '������') {
		document.getElementById('fbbj').selectedIndex = 0;
		document.getElementById('sclx').selectedIndex = 0;
		document.getElementById('xy').selectedIndex = 0;
		document.getElementById('fbbj').disabled = true;
		document.getElementById('sclx').disabled = true;
		document.getElementById('xy').disabled = true;
	} else {
		document.getElementById('fbbj').disabled = false;
		document.getElementById('sclx').disabled = false;
		document.getElementById('xy').disabled = false;
	}
}
//-->
</script>
<body onload="xyDisabled('xy');changeZt();dispXylist();">
	<html:form action="/bjlh_fyk.do" method="post">
		<input type="hidden" name="zyV" id="zyV" value="" />
		<input type="hidden" name="bjV" id="bjV" value="" />
		<input type="hidden" name="operType" value="query" />
		<input type="hidden" name="pt" id="pt" />
		<input type="hidden" name="userType" id="userType"
			value="${userType }" />
<input type="hidden" name="isXy" id="isXy"
			value="${isXy }" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã���Ԣ����-��Դ������-����ͳ��
			</div>
		</div>
		<fieldset>
			<legend>
				����ѡ��
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="">
							<input type="radio" name="cjlx" value="0" id="cjlx" 
								onclick="refreshForm('/xgxt/bjlh_fyk.do?method=fykManage');">
								�����ѯ
							&nbsp; 
							<input type="radio" name="cjlx" value="1" id="cjlx" 
								onclick="refreshForm('/xgxt/bjlh_fyk.do?method=tbtjManage');" >
							&nbsp; 
								ͼ��ͳ��
							<input type="radio" name="cjlx" value="2" id="cjlx"  checked>
								����ͳ��		
						</td>
						<td width="10" rowspan="4" align="center" valign="middle">
							<input type="hidden" name="go" value="a" />
							<button class="button2" style="height:40px" id="search_go"
								onclick="subForm();">
								ͳ��
							</button>
						</td>
					</tr>
					<tr>
						<td align="" nowrap="nowrap">
							У&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;����
							<html:select property="xqdm" style="" styleId="xqdm"
								onchange="setLdList()">
								<html:options collection="xqList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp;&nbsp;¥����
							<html:select property="lddm" style="" styleId="lddm"
								onchange="setXqList();setCsList();setQsList();">
								<html:options collection="ldList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp;&nbsp;����������
							<html:select property="cs" style="" styleId="cs"
								onchange="setQsList();">
								<html:options collection="csList" property="dm"
									labelProperty="mc" />
							</html:select>
							&nbsp;&nbsp;���Һţ�
							<html:select property="qsh" style="" styleId="qsh" onchange="">
								<html:options collection="qsList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							�����ǣ�
							<html:select property="fbbj" styleId="fbbj" 
								onchange="dispXylist();">
								<html:option value=""></html:option>
								<html:options collection="fpbjList" property="en"
									labelProperty="cn" />
							</html:select>
							&nbsp;&nbsp; ͳ�����
							<html:select property="zt" styleId="zt" onchange="changeZt();">
								<html:options collection="tjlxList" property="en"
									labelProperty="cn" />
							</html:select>
							&nbsp;&nbsp; ���ģ�巽ʽ��
							<html:select property="sclx" styleId="sclx" style="width:120px">
								<html:options collection="sclxList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="left" nowrap>
							Ժ&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ϵ��
							<html:select property="xydm" style="width:175px"
								styleClass="select" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
<%--							&nbsp;&nbsp; ��ҵʱ�䣺--%>
<%--							<html:text property="bysj" styleId="bysj" maxlength="10"--%>
<%--								style="width:80px"></html:text>--%>
						</td>
					</tr>
				</thead>
			</table>
		</fieldset>
		<logic:empty name="query">
			<br />
			<br />
			<p align="center">
				δ�ҵ��κμ�¼��
			</p>
		</logic:empty>
		<logic:notEmpty name="query">
			<fieldset>
				<legend>
					��Դ��ͳ����Ϣ
				</legend>
				
					<!-- ѭ����� -->
					<logic:notEmpty name="rs">
					<table width="100%" id="rsTable" class="tbstyle">
					
						<logic:iterate id="s" name="rs">
							<tr onclick="rowOnClick(this)" style="cursor:hand" align="center" >
								<td colspan="8" align="center" >
									<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>
								</td>
								</tr>
							<tr onclick="rowOnClick(this)" style="cursor:hand">
								<td align="center">
									��������
								</td>
								<td align="center">
									<logic:iterate id="v" name="s" offset="1" length="1">
										${v }
									</logic:iterate>
								</td>
								<td align="center">
									��λ����
								</td>
								<td align="center">
									<logic:iterate id="v" name="s" offset="2" length="1">
										${v }
									</logic:iterate>
								</td>
								<td align="center">
									�մ�λ����
								</td>
								<td align="center">
									<logic:iterate id="v" name="s" offset="3" length="1">
										${v }
									</logic:iterate>
								</td>
								<td align="center">
									���λ����
								</td>
								<td align="center">
									<logic:iterate id="v" name="s" offset="4">
										${v }
									</logic:iterate>
								</td>
							</tr>
						</logic:iterate>
						</table>
					</logic:notEmpty>
					<logic:empty name="rs">
							<p align="center">���޼�¼!</p>
					</logic:empty>
			</fieldset>
		</logic:notEmpty>
		<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button class="button2" id="btn_add" onclick="wjcfDataExport('bjlh_gygl_expfpjg.do')" style="width:80px">
						��������
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
