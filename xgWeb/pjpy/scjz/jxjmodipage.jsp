<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/pjpyscjzJs.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
<script type="text/javascript">
<!--
	function save() {
		var jxjdm = document.getElementById('jxjdm').value;
		var zdcj = document.getElementById('zdcj').value;
		var pjcj = document.getElementById('pjcj').value;
		if (document.getElementById('xh').value==''||jxjdm=='') {
			alert('�뽫��*�ŵ�������������');
			return;	
		}
		pjpyZjsyzy.jxjsqTj(jxjdm,zdcj,pjcj,function(data){
 		if (data) {
 			saveinfo('pjpy_scjz_jxjmodi.do?act=save','xh-jxjdm');
			BatAlert.showTips('���ڲ�������ȴ�...');
 		} else {
 			alert('�������Ƴɼ���ƽ���ɼ�δ�ﵽ����������');
 			return;
 		}
 	});
	}
	function getJxjleandje() {
		var jxjdm = document.getElementById('jxjdm').value;
		pjpyscjzJs.getJxjlbandje(jxjdm,function(data){
			if (data != '' && data != null) {
				var array = data.split("-");
				document.getElementById('jxjlb').innerText=array[0];	
				document.getElementById('jlje').innerText=array[1];	
			}
		});
	}
//-->
</script>
<body>
	<html:form action="/pjpyscjzwh" method="post">
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ�ã��������� - ��ѧ������ - ��д�����
			</div>
		</div>
		<table class="tbstyle" width="100%"> 
			<thead>
				<tr style="height:23px">
					<td colspan="4" align="center">
						�����޸�
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right" width="25%">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left" width="25%">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
				</td>
				<td align="right" width="25%">
					ѧ�꣺
				</td>
				<td align="left" width="25%">
					${xn }
					<input type="hidden" name="xn" id="xn" value="${xn }">
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
					ѧ�ڣ�
				</td>
				<td align="left">
					${xqmc }
					<input type="hidden" name="xq" id="xq" value="${xqmc }">
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
					<font color="red">*</font>��ѧ��
				</td>
				<td align="left">
					<html:select property="jxjdm" styleId="jxjdm" onchange="getJxjleandje()">
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					�꼶��
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				<td align="right">
					��ѧ�����
				</td>
				<td align="left">
<%--					<div id="jxjlb"></div>--%>
					<html:text property="jxjlb" styleId="jxjlb" readonly="true"></html:text>
				</td> 
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					ϵ��
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					������
				</td>
				<td align="left">
				<html:text property="jlje" styleId="jlje" readonly="true"></html:text>
<%--					<div id="jlje"></div>--%>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right">
					רҵ��
				</td>
				<td align="left">
					<bean:write name="rs" property="zymc" />
				</td>
				<td align="right">
					����ְ��
				</td>
				<td align="left">
					<html:text property="drzw" styleId="drzw"></html:text>
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
					�ֻ����룺
				</td>
				<td align="left">
					<html:text property="sjhm" styleId="sjhm" maxlength="12" onblur="ckinpjedata(this)"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					����ˮƽ��
				</td>
				<td align="left">
					<html:select property="wysp" styleId="wysp" styleId="khss" style="width:150px">
						<html:option value=""></html:option>
						<html:option value="����Ӣ�����">����Ӣ�����</html:option>
						<html:option value="����Ӣ������">����Ӣ������</html:option>
						<html:option value="����Ӣ���ļ�">����Ӣ���ļ�</html:option>
						<html:option value="����Ӣ������">����Ӣ������</html:option>
						<html:option value="רҵ�����ļ�">רҵ�����ļ�</html:option>
					</html:select>
				</td>
				<td align="right">
					�����ˮƽ��
				</td>
				<td align="left">
					<html:select property="jsjsp" styleId="jsjsp" styleId="khss" style="width:150px">
						<html:option value=""></html:option>
						<html:option value="�����һ��">�����һ��</html:option>
						<html:option value="���������">���������</html:option>
					</html:select>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					���п�������
				</td>
				<td align="left">
					<html:select property="khss" styleId="khss" style="width:150px">
						<html:option value="���п���">���п���</html:option>
						<html:option value="��������">��������</html:option>
						<html:option value="������">������</html:option>
					</html:select>
				</td>
				<td align="right">
					���ţ�
				</td>
				<td align="left">
					<html:text property="kh" styleId="kh" style="width:200px" maxlength="20" onblur="ckinpjedata(this)"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					������ͳɼ���
				</td>
				<td align="left">
					${rss.zdcj }
					<input type="hidden" id="zdcj" value="${rs.zdcj }"/>
				</td>
				<td align="right">
					ƽ���ɼ���
				</td>
				<td align="left">
					${rss.pjcj }
					<input type="hidden" id="pjcj" value="${rs.pjcj }"/>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					ѧϰ�ɼ�����
				</td>
				<td align="left" colspan="3">
					�༶��&nbsp;${rss.bjpm }&nbsp;��,רҵ��&nbsp;${rss.zypm }&nbsp;��
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					���޴��֣�
				</td>
				<td align="left" colspan="3">
					<logic:empty name="cfList">
								<p align="">
									��
								</p>
							</logic:empty>
							<logic:notEmpty name="cfList">
								<logic:iterate name="cfList" id="s">
									<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>��&nbsp;&nbsp;
									<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>&nbsp;&nbsp;
									(<logic:iterate id="v" name="s" offset="2" length="1"><bean:write name="v"/></logic:iterate>)
									<logic:iterate id="v" name="s" offset="3" length="1"><bean:write name="v"/></logic:iterate><br/>
								</logic:iterate>
							</logic:notEmpty>
				</td>
			</tr>
			<tr >
				<td align="right">
					������&nbsp;&nbsp;<br/>
					<font color="red">����Уѧϰ��ʵѵ�����ʵ�������������1000���ڣ�</font>
				</td>
				<td align="left" colspan="3">
					<html:textarea property="sqly" styleId="sqly"
						styleClass="inputtext" rows="8" style="width:98%"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" align="center">
					<button class="button2" id="btn_save" 
						onclick="save();return false;" <logic:notEmpty name="cfflag">disabled</logic:notEmpty> <logic:notEmpty name="cjflag">disabled</logic:notEmpty>
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>
	
			<div align="center">
				<logic:notEmpty name="cfflag">
					<span class="style1" align="center">${cfflag }</span><br/>
				</logic:notEmpty>
				<logic:notEmpty name="cjflag">
					<span class="style1" align="center">${cjflag }</span>
				</logic:notEmpty>
			</div>
				<!-- �������ʾҳ�� -->	
		<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert('�����ɹ���');
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert('����ʧ�ܣ�');
				</script>
			</logic:equal>
		</logic:present>
		<logic:present name="failinfo">
				<script>
					alert('����������ѧ������������');
				</script>
			</logic:present>
		<logic:present name="cjFlag">
			<script>
					alert('ѧ���ɼ������޸����γ̳ɼ�,���ȵ���ɼ���');
				</script>
		</logic:present>
	</html:form>
</body>
