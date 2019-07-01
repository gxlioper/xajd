<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/commWjcfDAO.js"></script>
		
		<script type="text/javascript" >
			//������Ϣ
function savecfinfo(url,val) {
	var arrayList = val.split('-');
	for (var i=0; i<arrayList.length;i++) {
		if (document.getElementById(arrayList[i])) {
			if (document.getElementById(arrayList[i]).value=='') {
				alert("�뽫��\"*\"�ŵ���Ŀ����������");
				return false;
			}
		}
	}
	//�ȼ��ͬһѧ��ͬһ�����ĺź�ʱ���Ƿ��ж�������
	var pks = document.getElementById('keys').value;
	var cfsj = document.getElementById('cfsj').value;
	var cfwh = document.getElementById('cfwh').value;
	commWjcfDAO.checkStuWjcfIsExistsBypl(pks, cfsj, cfwh, function(data) {
		if (data != null && data != "") {
			var array = data.split("!@");
			var str = "";
			for (var i=0;i<array.length;i++) {
				if ("1" == array[i]) {
					str+=(i+1) + ",";
				}
			}
			if (str != "") {
				str = str.substr(0,str.length-1);
				alert("��ǰҪ���ĵļ�¼�У���"+str+"����ʹ�ô����ĺ�Ϊ:'" + cfwh+ "'������ʱ��Ϊ:'" + cfsj+"'���з��ġ�\nͬһѧ��ͬһ����ʱ�����ĺ����治���ж��δ��֣����������ʱ����ĺš�");
				return false;
			} else {
				document.getElementById('btn_save').disabled = true;
				refreshForm(url);
			}
		}
	});
}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - ������� - �������</a>
			</p>
		</div>

		<html:form action="/wjcfxmlgwh" method="post">
			<input type="hidden" name="userType" id="userType"
				value="${userType }" />
			<input type="hidden" name="keys" id="keys" value="${keys }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�����������</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="btn_save"
										onclick="savecfinfo('wjcf_xmlg_plsh.do?operType=save','cfwh-cfsj-sh');">
										�� ��
									</button>
									<button type="button" id="btn_close" onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="30%">

								<logic:equal value="11078" name="xxdm">
					Υ�ʹ��־������ĺ�
					</logic:equal>
								<logic:notEqual value="11078" name="xxdm">
						<font color="red">*</font>�����ĺ�
					</logic:notEqual>
							</th>
							<td width="80%">
								<html:text property="cfwh" styleId="cfwh"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����ʱ��
							</th>
							<td align="left">
								<html:text property="cfsj" styleId="cfsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('cfsj','y-mm-dd');"></html:text>
							</td>
						</tr>
						<logic:notEqual value="12645" name="xxdm">
						<logic:equal value="11062" name="xxdm">
						
						
							<tr>
								<th>
									�����У<br/>�쿴ʱ��

								</th>
								<td align="left">
									<html:text property="lxcksj" styleId="lxcksj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('lxcksj','y-mm-dd');"></html:text>
									<br />
									<font color="red">(ֻ������У�쿴���ֵ���¼�������)</font>
								</td>
							</tr>
							</logic:equal>
						</logic:notEqual>
						<tr >
							<th>
								<font color="red">*</font>���
							</th>
							<td align="left">
								<html:select property="sh" styleId="sh">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��˴������
							</th>
							<td align="left">
								<html:textarea property="yj" rows="4" style="width:90%">
								</html:textarea>
							</td>
						</tr>
						</tbody>
					</table>
					</div>
					<!-- �������ʾҳ�� -->
					<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
		</html:form>
	</body>