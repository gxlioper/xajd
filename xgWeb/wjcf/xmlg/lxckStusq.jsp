<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/wjcfFuction.js"></script>
<script type="text/javascript">
<!--
	//������Ϣ
function saveSqxx(url,val) {
	var arrayList = val.split('-');
	for (var i=0; i<arrayList.length;i++) {
		if ($(arrayList[i])) {
			if (document.getElementById(arrayList[i]).value=='') {
				alert("��ѡ��Ҫ����Ľ����У�쿴����!");
				return false;
			}
		}
	}
	document.getElementById('btn_save').disabled = true;
	refreshForm(url);
}
//-->
</script>
<body>
	<html:form action="/wjcfxmlgwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="failinfo" id="failinfo" value="${failinfo}"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
	 	<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/wjcf_xmlg_lxckStusq.do" />
		<input type="hidden" name="cflb" id="cflb" value="${rs.cflb }"/>
		<input type="hidden" name="cfyy" id="cfyy" value="${rs.cfyy }"/>
		<input type="hidden" name="cfxn" id="cfxn" value="${rs.xn }"/>
		<input type="hidden" name="cfnd" id="cfnd" value="${rs.nd }"/>
		<input type="hidden" name="cfsbsj" id="cfsbsj" value="${rs.sbsj }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ��: Υ�ʹ��� - �����У�쿴 - ����
			</div>
		</div>
		<table class="tbstyle" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�����У�쿴�����
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<logic:equal value="teacher" name="userOnLine">
					<td align="right" width="15%">
					<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" width="35%">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
						<button type="button" onclick="showTopWin('wjcf_xmlg_lxckxxquery.do',750,600);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>	
					</td>
				</logic:equal>
				<logic:notEqual value="teacher" name="userOnLine">
					<td align="right" width="15%">
					<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" width="35%">
						${rs.xh }
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" onclick="showTopWin('wjcf_xmlg_lxckxxquery.do',750,600);"
							class="btn_01" id="buttonFindStu" title="���ѡ�񴦷���Ϣ">
							ѡ��
						</button> <font color="red">(���ѡ�񴦷���Ϣ)</font>
					</td>
				</logic:notEqual>

				<td align="right" width="15%">
					ѧ�꣺
				</td>
				<td align="left" width="35%">
					${rs.cuxn }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������
				</td>
				<td align="left">
					${rs.xm }
				</td>
				<td align="right">
					��ȣ�
				</td>
				<td align="left">
					${rs.cund }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�Ա�
				</td>
				<td align="left">
					${rs.xb }
				</td>
				<td align="right">
					�������
				</td>
				<td align="left">
					${rs.cflbmc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					${rs.nj }
				</td>
				<td align="right">
					����ԭ��
				</td>
				<td align="left">
					${rs.cfyymc }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					${rs.xymc }
				</td>
				<td align="right">
					����ѧ��&nbsp;&nbsp;<br/>��ȣ�
				</td>
				<td align="left">
					${rs.xn }/${rs.nd }
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					רҵ��
				</td>
				<td align="left">
					${rs.zymc }
				</td>
				<td align="right">
					�����ĺţ�
				</td>
				<td align="left">
					<html:text property="cfwh" name="rs" disabled="true" styleId="cfwh"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�༶��
				</td>
				<td align="left" colspan="">
					${rs.bjmc }
				</td>
				<td align="right">
					����ʱ�䣺
				</td>
				<td align="left">
					<html:text property="cfsj" name="rs" styleId="cfsj" disabled="true"></html:text>
				</td>
			</tr>
			<tr>
				<td align="right">��У�쿴��</td>
				<td align="center" colspan="3">
					${rs.lxcfsj } �� ${rs.lxcksj }
				</td>
			</tr>
		<tr style="height:23px">
				<td align="right">
					ѧ������<br/>����Ҫ��
				</td>
				<td align="left" colspan="3">
					<font color="red">(˵���ܴ�������������ı��������ȡ�õĽ�����������������)</font><br/>
					<html:textarea property="xsbx" styleId="xsbx" rows="10" style="width:95%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
			<logic:equal value="true" name="writable">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveSqxx('wjcf_xmlg_lxckStusq.do?operType=save','xh-cfsj-cfwh');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:equal>
					<logic:notEqual value="true" name="writable">
						<font color="red">(�ô��ֲ쿴����δ��6���£����ܽ����������!)</font>
					</logic:notEqual>
					<button type="button" class="button2" id="btn_close" onclick="window.open('wjcf_xmlg_lxckprint.do?pkValue=' + $('pkValue').value)" style="width:80px">
						�� ӡ
					</button>
				</div>
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert("�����ɹ�!");
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert("����ʧ��,ԭ����������ݿ����Ѵ�����ͬ��¼!");
					
				</script>
			</logic:equal>
			<logic:equal value="exists" name="inserted">
				<script>
					alert("���ݿ����Ѵ�����ͬ��¼����ȷ�ϣ�");
				</script>
			</logic:equal>
		</logic:present>		
	</html:form>
</body>
