<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/zjlgZbglDAO.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
	function saveZzgx(){
		var xh = $("xh").value;
		var zjsj = $("zjsj").value;
		var zjlx = $("zjlx").value;
		var zjmm = $("zjmm").value;
		
		if(xh==" "){
			alert("ѧ��Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		if(zjsj==""){
			alert("ת��ʱ��Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		if(zjmm==""){
			alert("������òΪ�գ���ȷ�ϣ�");
			return false;
		}
		if(zjlx==""){
			alert("ת������Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		var url = "/xgxt/zjlgDtjsZzgx.do?method=zzgxUpdate&doType=save";
		showTips('���������У���ȴ�......');
		$("buttonSave").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	function getZbmc(){
		var xh = $("xh").value;
		var zbmc = $("zjdz").value;
		dwr.engine.setAsync(false);
		if(xh !=" " && zbmc == ""){
			zjlgZbglDAO.getZbmc(xh,function(data){
				$("zjdz").value=data;		
			});
		}
		dwr.engine.setAsync(true);
	}
	</script>
	</head>
	<body onload="getZbmc()">
		<html:form action="/zjlgDtjsZzgx">
		<input type="hidden" id="url" name="url" value="/zjlgDtjsZzgx.do?method=zzgxUpdate&doType=add"/>
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
							<span>��֯��ϵת��</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height: 23px">
					<th>
						ѧ��
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
						��������
					</th>
					<td align="left">
							<html:text name='rs' property="csrq" styleId="csrq" readonly="true"/>
					</td>
					<th>
						����
					</th>
					<td align="left">
						<html:text name='rs' property="mzmc" styleId="mzmc" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						<font color="red">*</font>������ò
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="zjmm" style="">
								<html:option value=""></html:option>
								<html:option value="ybdy">Ԥ����Ա</html:option>
								<html:option value="zsdy">��ʽ��Ա</html:option>
							</html:select>	
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="zjmm" styleId="zjmm"/>
							<html:select name="rs" property="zjmm" style="" disabled="true">
								<html:option value=""></html:option>
								<html:option value="ybdy">Ԥ����Ա</html:option>
								<html:option value="zsdy">��ʽ��Ա</html:option>
							</html:select>	
						</logic:notEqual>
					</td>
					<th>
						���֤����
					</th>
					<td align="left">
						<html:text name='rs' property="sfzh" styleId="sfzh" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						��ϵ�绰
					</th>
					<td align="left">
						<html:text name='rs' property="lxdh" styleId="lxdh" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<th>
						���ѽ����·�
					</th>
					<td align="left">
						<html:select name="rs" property="dfjzyf" style="">
							<html:option value=""></html:option>
							<html:options collection="yfList" property="yf" labelProperty="yf" />
						</html:select>	
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						�����ű��
					</th>
					<td align="left">
						<html:text name='rs' property="jsxbh" styleId="jsxbh" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<th>
						��Ч��
					</th>
					<td align="left">
						<html:text name="rs" property="yxq" styleId="yxq" readonly="true"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('yxq','y-mm-dd');"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						<font color="red">*</font>ת������
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:select name="rs" property="zjlx" style="">
								<html:option value=""></html:option>
								<html:option value="in">ת��</html:option>
							</html:select>	
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="zjlx" style=""/>
							<html:select name="rs" property="zjlx" style="" disabled="true">
								<html:option value=""></html:option>
								<html:option value="in">ת��</html:option>
								<html:option value="out">ת��</html:option>
								<html:option value="zz">ת��</html:option>
							</html:select>	
						</logic:notEqual>
					</td>
					<th>
						<font color="red">*</font>ת��ʱ��
					</th>
					<td align="left">
						<logic:equal name="doType" value="add">
							<html:text name="rs" property="zjsj" styleId="zjsj" readonly="true"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('zjsj','y-mm-dd');"/>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name="rs" property="zjsj" style=""/>
							<bean:write name="rs" property="zjsj"/>
						</logic:notEqual>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						ԭ��λ��ַ
					</th>
					<td align="left">
						<html:text name='rs' property="ydw" styleId="ydw" style="" maxlength="50"/>
					</td>
					<th>
						ת�ӵ�ַ
					</th>
					<td align="left">
						<html:text name='rs' property="zjdz" styleId="zjdz" style="" readonly="true"/>
					</td>
				</tr>
				<tr style="height: 23px">
					<th>
						��ע
					</th>
					<td align="left" colspan="3">
						<html:textarea name='rs' property="bz" styleId="bz" rows="5" style="width: 95%;word-break:break-all;" onblur="chLeng(this,60)"/>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						  <logic:notEqual name="doType" value="view">
							<button type="button" id="buttonSave" onclick="saveZzgx()">
								����
							</button>
						  </logic:notEqual>
						  <button type="button" id="buttonClose" name="�ر�" onclick="window.close();return false;">�ر�</button>
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
