<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<body onload="">
	<script language="javascript" src="js/pjpyFunction.js"></script>	
	<script type="text/javascript" src="/xgxt/dwr/interface/getJxjList.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script language="javascript" src="js/pjpy/pjpy_whlgdx.js"></script>
	<script type="text/javascript">
		function addTj(){
			var xmdm = document.getElementById("xmdm").value;
			var jxjdm = document.getElementById("jxjdm").value;
			var jxjfl = document.getElementById("jxjfl").value;
			var xn = document.getElementById("xn").value;
			var url = "pjpy_whlgdx.do?method=showTjszAdd";
			url += "&xmdm=";
			url += xmdm;
			url += "&jxjfl=";
			url += jxjfl;
			url += "&jxjdm=";
			url += jxjdm;		
			url += "&xn=";
			url += xn;
			
			showTopWin(url);
		}
	</script>
	<html:form action="/pjpy_whlgdx.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã��������� - �������� - ��������
			</div>
		</div>
		<fieldset>
			<legend>
				��������
			</legend>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td align=left id="vXn">
							ѧ�꣺
							<html:select property="xn" styleId="xn" style="width:90px">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
							&nbsp;&nbsp;��Ŀ��
							<html:select property="xmdm" style="width:150px" styleId="xmdm"
								onchange="initXmflList();refreshForm('pjpy_whlgdx.do?method=tjssInit')">
								<html:option value="jxjdmb">��ѧ��</html:option>
								<html:option value="rychdmb">�����ƺ�</html:option>
							</html:select>
							&nbsp;&nbsp; ���ࣺ
							<html:select property="jxjfl" style="width:150px" styleId="jxjfl"
								onchange="initXmList();">
								<html:option value=""></html:option>
								<html:options collection="jxjflList" property="jxjfldm"
									labelProperty="jxjflmc" />
							</html:select>
							&nbsp;&nbsp;���ƣ�
							<html:select property="jxjdm" style="width:150px"
								onchange="refreshForm('pjpy_whlgdx.do?method=tjssInit')" styleId="jxjdm">
								<html:option value=""></html:option>
								<html:options collection="jxjList" property="jxjdm"
									labelProperty="jxjmc" />
							</html:select>
							<input type="hidden" name="go" value="a" />
							<button class="button2" id="search_go" onclick="allNotEmpThenGo('pjpy_whlgdx.do?method=tjssInit');">
								��ѯ
							</button>
						</td>
					</tr>
				</thead>
			</table>
		</fieldset>
		<br />
		<logic:notEmpty name="rs">
			<fieldset>
				<legend>
					��������
				</legend>
				<table width="100%" id="rsTable" class="tbstyle">
					<thead>
						<tr align="center">
							<logic:iterate id="tit" name="topTr" offset="2">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)" nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:iterate id="s" name="rs" scope="request">
						<tr align="center" onclick="rowOnClick(this)" style="cursor:hand">
							<td>
								<input type="hidden"
									value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
								<input type="hidden"
									value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" />
								<logic:iterate id="v" name="s" offset="2" length="1">
									<bean:write name="v" />
								</logic:iterate>
							</td>
							<logic:iterate id="v" name="s" offset="3">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</table>
			</fieldset>
		</logic:notEmpty>
		<div class="buttontool" id="btn"
			style="position: absolute;left:1%;top:100px" width="100%">
			<button class="button2" onclick="addTj()">
				�޸�����
			</button>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2"
				onclick="modijxjtjsz('pjpy_whlgdx.do?method=tjszDel&pkValue=')">
				ɾ������
			</button>
		</div>
	</html:form>
	<script language="javascript">
document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
document.getElementById("btn").style.width = "96%";
window.setInterval("initBTNTool('btn')",1);

function modijxjtjsz(url,type){
	if(curr_row==null){
		alert('��ѡ��Ҫɾ���ļ�¼��');
		return false;
	}else{
		url += curr_row.cells[0].getElementsByTagName("input")[0].value;
		url += "&tableName=";
		url += document.getElementById("xmdm").value;
		if (confirm('ȷ��Ҫɾ����ѡ���������')) {
			refreshForm(url);
			return;
		}
		return;
	}
}

</script>
	<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
			</script>
		</logic:equal>
		<logic:equal value="result" name="result">
			<script>
				alert("����ʧ�ܣ�");
			</script>
		</logic:equal>
	</logic:present>
</body>
