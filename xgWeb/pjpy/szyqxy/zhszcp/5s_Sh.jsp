<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
	<script type="text/javascript">	

function ztbhSave(the_tab){
	var tabobj = document.getElementById(the_tab);
	var rowLen = tabobj.rows.length;
	var nullCout = 0;
	var xh = $("xh").value;
	var xn = $("xnV").value;
	var xq = $("xqV").value;
	
	if(xh == ""){
		alert("��ȷ���������ѧ��");
		return false;
	}
	for(var i=1;i<=rowLen;i++){
		if($("lrrq"+i)){
			if($("fzxm"+i).value == ""){
				alert("��"+i+"������Ϊ�գ���ȷ��");
				return false;
			}
		}
		if($("fz"+i)){
			if($("fz"+i).value == ""){
				alert("��"+i+"�з�ֵΪ�գ���ȷ��");
				return false;
			}
		}
	}

   showTips('���������У���ȴ�......');
   refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_5sAdd&doType=save&xn='+xn+'&xq='+xq);
   $("buttonSave").disabled=true;
}

function toChk(shzt){
	var pk = $("pk").value;
	var num=$("num").value;
	var flg = true;
	if(num != null && num >0){
		for(var i=0;i<num;i++){
			if($("checkVal"+i)){
				if ($("checkVal"+i).checked){
					flg = false;
					break;
				}
			}
		}
	}
	if(flg){
		alert('�빴ѡҪ��˵���Ϣ!');
		return false;
	}
   showTips('���������У���ȴ�......');
   refreshForm('/xgxt/pjpyszyqwh.do?method=szyc_5sSh&doType=save&shzt='+shzt+'&pkValue='+pk);
   $("buttonSave").disabled=true;
}
	</script>
	</head>
	<body onload="">
		<html:form action="/pjpyszyqwh">
			<input type="hidden" id="url" name="url" value="/pjpy/szyqxy/zhszcp/5s_Add.jsp"/>
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc"/>
			<input type="hidden" id="pk" name="pk" value="${pk}"/>
			<input type="hidden" id="xnV" name="xnV" value="${xnV}"/>
			<input type="hidden" id="xqV" name="xqV" value="${xqV}"/>
			<input type="hidden" id="msg" name="msg" value="${msg}"/>
			<input type="hidden" id="num" name="num" value="${rsNum}"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ۺ�����-ѧ���ۺ��������ɿ�-5s�����</a>
				</p>
			</div>
			<table class="formlist" border="0" id="rsTable" align="center"
				style="width: 100%">
				<tr style="height: 23px">
					<th>
						ѧ��
					</th>
					<td align="left">
						<html:text name='rs' property="xh" styleId="xh" readonly="true" />
					</td>
					<th>
						ѧ��
					</th>
					<td align="left">
						<html:text name='rs' property="xn" styleId="xn" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						�Ա�
					</th>
					<td align="left">
							<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
					</td>
					<th>
						ѧ��
					</th>
					<td align="left">
							<html:text name='rs' property="xq" styleId="xq" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						����
					</th>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:text name='rs' property="xymc" styleId="xymc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						רҵ
					</th>
					<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
					</td>
					<th>
						�༶
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
				</tr>
			</table>
			<fieldset>
				<legend>
					��¼��:
					${rsNum}
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<font color="blue">�μӻ�б�</font>
				</legend>
				<table width="99%" class="formlist" >
					<thead style="height: 23px">
						<tr >
						    <th>
								
						    </th>
							<th nowrap="nowrap" style='width:5%'>
								���
							</th>
							<th nowrap="nowrap" style='width:25%'>
								��Ŀ
							</th>
							<th nowrap="nowrap" style='width:12%'>
								�Ӽ���
							</th>
							<th nowrap="nowrap" style='width:10%'>
								��ֵ
							</th>
							<th nowrap="nowrap" style='width:15%'>
								����
							</th>
							<th nowrap="nowrap" style='width:30%'>
								�ӷ�ԭ��
							</th>
							<th nowrap="nowrap" style='width:30%'>
								���״̬
							</th>
						</tr>
					</thead>
					<logic:iterate name="rsList" id="s" indexId="index">
						<tr>
							<td>
								<input type="checkbox" id="checkVal${index}" name="checkVal"
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
			<div align="right">
				<button type="button" class="button2" id="buttonSave" onclick="toChk('yes')">
					ͨ��
				</button>
				<button type="button" class="button2" id="buttonSave" onclick="toChk('no')">
					��ͨ��
				</button>
				<button type="button" class="button2" id="buttonClose" 
					onclick="window.close();window.dialogArguments.document.getElementById('search_go').click();">
					�ر�
				</button>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
