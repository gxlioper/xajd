<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type="text/javascript">	
	function saveDwjl(){
		var xh = $("xh").value;
		var xn = $("xn").value;
		var xq = $("xq").value;
		var jlxm = $("jlxm").value;
		var cgsj = $("cgsj").value;
		var hgsj = $("hgsj").value;
		if(xh==" "){
			alert("ѧ�Ų���Ϊ�գ�");
			return false;
		}
		if(xn=="" || xq == ""){
			alert("ѧ�����ѧ�ڲ���Ϊ�գ�");
			return false;
		}
		if(jlxm==""){
			alert("��ȷ��������Ŀ��");
			return false;
		}
		if(cgsj !="" && hgsj != ""){
			if(cgsj>hgsj){
				alert("����ʱ����ڻع�ʱ�䣬��ȷ�ϣ�");
				return false;
			}
		}
		var url = "/xgxt/zjlgXsxxDwjl.do?method=dwjlUpdate&doType=save";
		showTips('���������У���ȴ�......');
		$("buttonSave").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	</script>
	</head>
	<body onload="">
		<html:form action="/zjlgXsxxDwjl">
		<input type="hidden" id="url" name="url" value="/zjlgXsxxDwjl.do?method=dwjlUpdate&doType=add"/>
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm-xb-xymc-zymc-bjmc"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<div class="tab">
			<table class="formlist" id="rsTable">
				<thead>
					<tr>
						<th colspan="4">
							<span>���⽻��</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height: 23px">
					<th>
						<font color="red">*</font>ѧ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:text name='rs' property="xh" styleId="xh" readonly="true" />
						</logic:notEqual>
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
						����
					</th>
					<td align="left">
						<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
					</td>
					<th>
						רҵ
					</th>
					<td align="left">
							<html:text name='rs' property="zymc" styleId="zymc" readonly="true"/>
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
						�༶
					</th>
					<td align="left">
						<html:text name='rs' property="bjmc" styleId="bjmc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						<font color="red">*</font>ѧ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
						<html:select name="rs" property="xn" style="">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
						<html:hidden name='rs' property="xn" styleId="xn"/>
						<html:select name="rs" property="xn" style="" disabled="true">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
						</logic:notEqual>
					</td>
					<th>
						<font color="red">*</font>ѧ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
						<html:select name="rs" property="xq" style="">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
						<html:hidden name='rs' property="xq" styleId="xq"/>
						<html:select name="rs" property="xq" style="" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						����������ʱ��
					</th>
					<td align="left">
						<html:text name="rs" property="cgsj" styleId="cgsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('cgsj','y-mm-dd');"/>
					</td>
					<th>
						�ع�������ʱ��
					</th>
					<td align="left">
						<html:text name="rs" property="hgsj" styleId="hgsj" readonly="true"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('hgsj','y-mm-dd');"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						<font color="red">*</font>������Ŀ
					</th>
					<td align="left" colspan="3">
						<logic:equal name="doType" value="add">
						<html:select name="rs" property="jlxm" style="">
							<html:option value=""></html:option>
							<html:options collection="jlxmList" property="xmdm" labelProperty="xmmc" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
						<html:hidden name='rs' property="jlxm" styleId="jlxm"/>
						<html:select name="rs" property="jlxm" style="" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="jlxmList" property="xmdm" labelProperty="xmmc" />
						</html:select>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						��ע<br/>
						<font color="red">(��������150)</font>
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,150)"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
							  <logic:notEqual name="doType" value="view">
								<button type="button" id="buttonSave" onclick="saveDwjl()">
									����
								</button>
							  </logic:notEqual>
							  <button type="button" name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
				          </div></td>
				      </tr>
			    </tfoot>
			</table>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
