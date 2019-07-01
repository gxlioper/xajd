<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script type="text/javascript" src="pjpy/nbzy/nbzyJs.js">
</script>

<script language="javascript">
	function savetj() {
	var xn = document.getElementById('xn').value;
	var jxjdm = document.getElementById('rychdm').value;
	var tj = document.getElementById('tj').value;
	if (xn==''||jxjdm==''||tj=='') {
		alert('������������Ϊ��,��ȷ��!');
		return;	
	} else {
		refreshForm('pjpy_nblg_rychtjsz.do?act=save');
		BatAlert.showTips('���ڲ�������ȴ�...');
	}
	
}
function chgts() {
	var tj = document.getElementById('tj').value;
	var ts = '';
	if (tj=='dycj') {
		ts = '(%)';
	} else if (tj=='tycj') {
		ts = '(��)';
	} else if (tj=='jxj') {
		ts = '(ͬ�ȼ�����)';
	}
	document.getElementById('ts').innerText=ts;
}

function chkdatatype(obj) {
	obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
		var inputStr = obj.value;
		
		if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g))){
			alert('���ݸ�ʽ����ȷ��ֻ�������֣�');
			obj.value = '';
			return false;
		}
		return true;
}
</script>
</head>
<body onload="chgts()">
	<html:form action="/pjpynblgwh" method="post">
		<div class="tab_cur">
					<p class="location">
						<em>��ǰ����λ�ã�</em><a>�������� - �������� - �����ƺ���������</a>
					</p>
		</div>
		<logic:equal value="yes" name="writeAble">
					<div class="toolbox">
					<div class="buttonbox">	
						<input type="hidden" name="ts" id="ts"/>
						<ul>
							<li><a href="#" class="btn_sc" onclick="deldata1('pjpy_nblg_rychtjsz.do?act=del');">ɾ������</a></li>
							<li><a href="#" class="btn_ccg" onclick="savetj()">��������</a></li>
						</ul>
					</div>
					</div>
		</logic:equal>
		<input type="hidden" id="zyV" name="zyV" value="" />
		<input type="hidden" id="bjV" name="bjV" value="" />
		<input type="hidden" id="search_go"
			onclick="refreshForm('pjpy_nblg_rychtjsz.do');" />
		<input type="hidden" id="tname" name="tname" value="${tname }" />
		<div class="searchtab">
				<table width="100%" class="" border="0">
					<tbody>
					<tr>
					<th align="left">ѧ��</th>
						<td><html:select property="xn" style="width:90px" styleClass="select"
								styleId="xn" onchange="refreshForm('pjpy_nblg_rychtjsz.do');">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th align="left">�����ƺ�</th>
						<td>
						<html:select property="rychdm" styleId="rychdm"
								onchange="refreshForm('pjpy_nblg_rychtjsz.do');"
								style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="rychList" property="rychdm"
									labelProperty="rychmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="left">����</th>
							<td><html:select property="tj" styleId="tj" 
							onchange="chgts();refreshForm('pjpy_nblg_rychtjsz.do')" >
								<html:option value=""></html:option>
								<html:options collection="tjList" property="en"
									labelProperty="cn" />
							</html:select>
						    </td>
						    <td>
                               &nbsp;&nbsp; ---> &nbsp;&nbsp;
							<logic:equal value="yes" name="chgjxj">
								<html:select property="pm" styleId="pm" style="width:150px">
									<html:option value=""></html:option>
									<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
								</html:select>
							</logic:equal>
							<logic:notEqual value="yes" name="chgjxj">
								<html:text property="pm" styleId="pm" style="width:100px"
								onblur="ckinpdata(this)"></html:text>
							</logic:notEqual>
							<font color="red"><span id="ts"></span></font> &nbsp;&nbsp;
						    </td>
					</tr>
					</tbody>
				</table>
				</div>
		
		 <div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<fieldset>
					 <table width="99%" id="rsTable" class="dateline">
							<thead>
									<tr align="left" style="cursor:hand">
									<td><input type="checkbox" onclick="selectAllCheckBox()" /></td>
										<logic:iterate id="tit" name="topTr" >
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)">
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand;">
									<td align=center>
										<input type="checkbox" id="cbv" name="cbv"
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td align=center>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
					</fieldset>
				</logic:notEmpty>
				<div id="tmpdiv"></div>
			</div>
	</html:form>
	<script type="text/javascript" src="js/bottomButton.js"></script>
	<logic:present name="deleted">
		<logic:equal value="yes" name="deleted">
			<script>
	 			alert('�����ɹ���');
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
		<logic:equal value="no" name="deleted">
			<script>
	 			alert('����ʧ��!');
	 			document.getElementById('search_go').onclick();
	 		</script>
		</logic:equal>
	</logic:present>
</body>
</html>