<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/pjpyZjsyzy.js'></script>
<script language="javascript">
function saveinfo(url,val) {
	var arrayList = val.split('-');
	for (var i=0; i<arrayList.length;i++) {
		if ($(arrayList[i])) {
			if (document.getElementById(arrayList[i]).value=='') {
				alert("�뽫��\"*\"�ŵ���Ŀ����������");
				return false;
			}
		}
	}
	document.getElementById('btn_save').disabled = true;
	refreshForm(url);
}
function chktj() {
	var xh = document.getElementById('xh').value;
	var jxjdm = document.getElementById('jxjdm').value;
	if (xh==''||jxjdm=='') {
		alert('��*��Ϊ�����');
		return;
	}
	pjpyZjsyzy.chksqtj(xh, function (data){
		if (!data) {
			alert('����������ѧ���в��ֱ��޿γɼ����������Υ�ͼ�¼������������������');
			return;
		} else {
			refreshForm('jxjsqsave.do');
		}
	});
	document.getElementById('btn_save').disabled=true;
}
function print() {
	if (document.getElementById('xh').value==''||document.getElementById('jxjdm').value=='') {
		alert('ѧ�źͽ�ѧ��Ϊ�����');
		return;
	}
	window.open('pjpy_hxxy_jxjprint.do?xh='+document.getElementById('xh').value + '&jxjdm='+document.getElementById('jxjdm').value)
}
</script>
<body>
	<html:form action="/pjpyhxxywh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_whlghxxy_jxjsqdefault.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ�ã��������� - ��ѧ������ - ��д�����
			</div>
		</div>
		<logic:equal name="rs" property="stuExists" value="no">
					<script>
   	 					alert("�������ѧ����Ч!");
    				</script>
			</logic:equal>
		<table class="tbstyle" width="100%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						��д�����
					</td>
					<logic:present name="tj">
					<tr>
					<td colspan="4" align="center">
						<font color="red">����������ѧ���в��ֱ��޿γɼ����������Υ�ͼ�¼������������������</font>
					</td>
					</tr>
					</logic:present>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left">
					<logic:present name="showstu">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					</logic:present>
					<logic:notPresent name="showstu">
						<html:text name='rs' property="xh" styleId="xh"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						ѡ��
					</button>
					</logic:notPresent>
				</td>
				<td align="right">
					��ȣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="nd" />
					<input type="hidden" name="nd" id="nd" value="<bean:write name="rs" property="nd" />">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������
				</td>
				<td align="left">
					<bean:write name="rs" property="xm" />
				</td>
				<td align="right">
					ѧ�꣺
				</td>
				<td align="left">
					<bean:write name="rs" property="xn" />
					<input type="hidden" name="xn" id="xn" value="<bean:write name="rs" property="xn" />">
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�Ա�
				</td>
				<td align="left">
					<bean:write name="rs" property="xb" />
				</td>
				<td align="right">
					ѧ�ڣ�
				</td>
				<td align="left">
					<bean:write name="rs" property="xq"/>
					<input type="hidden" name="xq" id="xq" value="<bean:write name="rs" property="xq" />">
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					���壺
				</td>
				<td align="left">
					<bean:write name="rs" property="mzmc" />
				</td>
				<td align="right">
					������ò��
				</td>
				<td align="left">
					<bean:write name="rs" property="zzmmmc" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					רҵ��
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶��
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					�������£�
				</td>
				<td align="left">
					<bean:write name="rs" property="csrq" />
				</td>
				<td align="right">
					��ѧʱ�䣺
				</td>
				<td align="left">
					<bean:write name="rs" property="rxrq" />
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					����ˮƽ��
				</td>
				<td align="left">
					<html:text property="wysp" styleId="wysp" styleClass="inputtext" maxlength="50"></html:text>
				</td>
				<td align="right">
					��ϵ�绰��
				</td>
				<td align="left">
					<bean:write name="rs" property="lxdh" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					���ι��������
				</td>
				<td align="left">
					<html:text property="drzw" styleId="drzw" styleClass="inputtext" maxlength="100"></html:text>
				</td>
				<td align="right">
					<font color="red">*</font>��ѧ��
				</td>
				<td align="left">
					<html:select property="jxjdm" styleId="jxjdm" styleClass="select" style="width:150px" >
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					ƽ��ѧ�ּ��㣺
				</td>
				<td align="left">
					<bean:write name="rs" property="xf" />
				</td>
				<td align="right"> 
					&#25490;&#21517;&#65306; 
				</td>
				<td align="left">
					<bean:write name="rs" property="xfpm" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�ۺ����ʲ�����
				</td>
				<td align="left">
					<bean:write name="rs" property="zf" />
				</td>
				<td align="right"> 
					&#25490;&#21517;&#65306; 
				</td>
				<td align="left">
					<bean:write name="rs" property="zfpm" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������ͳɼ���
				</td>
				<td align="left">
					<bean:write name="rs" property="zdcj" />
				</td>
				<td align="right">
					������
				</td>
				<td align="left">
					<bean:write name="rs" property="dcj" />
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�����ӷ֣�
				</td>
				<td align="left">
					<bean:write name="rs" property="jlf" />
				</td>
				<td align="right">
					���ʽ���������
				</td>
				<td align="left">
					<html:text property="tzjkbzdj" styleId="tzjkbzdj" styleClass="inputtext" maxlength="20"></html:text>
				</td>
			</tr>
			<tr >
				<td align="right">
					�������ɣ�
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" styleId="sqly"
						styleClass="inputtext" rows="5" style="width:98%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button class="button2" id="btn_save" ${tj }
						onclick="chktj()"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_close" onclick="print()" style="width:80px"
						id="buttonClose">
						�� ӡ
					</button>
				</div>
				<!-- �������ʾҳ�� -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('�����ɹ���');
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('����ʧ�ܣ�');
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
