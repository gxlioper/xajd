<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/pjpyZjsyzy.js"></script>
<script type="text/javascript">
	function print(url){
		var xh = document.getElementById('xh').value;
		var jxjdm = document.getElementById('jxjdm').value;
		
		var jxjmc = "";		 
		var jd = document.getElementById('jd').value;
		var sqly = document.getElementById('sqly').value;
		var drzw = document.getElementById('drzw').value;
		if(document.getElementById('jxjdm').selectedIndex>=0){
			jxjmc = document.getElementById('jxjdm').options[document.getElementById('jxjdm').selectedIndex].text;
		}
		
		url += "?xh=";
		url += xh;
		url += "&jxjdm=";
		url += jxjdm;
		url += "&jxjmc=";
		url += jxjmc;
		url += "&drzw=";
		url += drzw;
		url += "&jd=";
		url += jd;
		url += "&sqly=";
		url += sqly;
		
		window.open(url);
	}
	
	function chksqtj() {
		var xh = document.getElementById('xh').value;
		var jxjdm = document.getElementById('jxjdm').value;
		var jd = document.getElementById('jd').value;
		var sqly = document.getElementById('sqly').value;
		if (xh==''||jxjdm==''||jd==''||xh==null||jxjdm==null||jd==null||sqly==''||sqly==null) {
			alert('��*��Ϊ���������д������');
			return;
		}
		var jxjlb = document.getElementById('jxjlb').value;
			pjpyZjsyzy.chkjxjSqtj(xh,jxjdm,jxjlb,function (data) {
			if (data=='sxy') {
				alert('����ʧ��,�ý�ѧ��ֻ�������꼶ѧ�����룡');
				return;
			} else if (data=='ys') {
				alert('����ʧ��,�ý�ѧ��ֻ�����������ϵѧ�����룡');
				return;
			} else if (data=='jd') {
				alert('����ʧ��,��ѧ�����������ͬ�ཱѧ��,�����ٴ����룡');
				return;
			} else if (data=='by') {
				alert('����ʧ��,�����ѱ�ҵ,���������뽱ѧ��');
				return;
			}else if (data=='xy') {
				alert('����ʧ��,�ý�ѧ��ֻ����һ�����꼶ѧ�����룡');
				return;
			}else {
				saveinfo('pjpy_shcbys_jxjsave.do','xh-jxjdm-jd');
				document.getElementById('btn_save').disabled=true;	
			}
		});
		
		
	}
</script>
<body>
	<html:form action="/pjpyshcbyswh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_shcb_jxjsq.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				<bean:message bundle="pjpyshcbys" key="pjpy_shcbys_jxjsq" />
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
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left">
					<logic:present name="showstu">
						<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="s" />
					</logic:present>
					<logic:notPresent name="showstu">
						<html:text name='rs' property="xh" styleId="xh"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
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
					�꼶��
				</td>
				<td align="left">
					<bean:write name="rs" property="nj" />
				</td>
				<td align="right">
					��������:
				</td>
				<td align="left">
					<html:text name="rs" property="csrq" styleId="csrq"></html:text>
				</td>
				
			</tr>
			<tr style="height:23px">
				<td align="right"> 
					&#31995;&#21035;&#65306; 
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
				</td>
				<td align="right">
					����:
				</td>
				<td align="left">
					<html:text name="rs" property="mz" styleId="mz"></html:text>
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
					��Դ��:
				</td>
				<td align="left">
					<html:text name="rs" property="syd" styleId="syd"></html:text>
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
					������ò:
				</td>
				<td align="left">
					<html:text name="rs" property="zzmm" styleId="zzmm"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					ְ��
				</td>
				<td align="left">
					<html:text property="drzw" styleId="drzw"></html:text>
				</td>
				<td align="right">
					<font color="red">*</font>��ѧ�ڼ���:
				</td>
				<td align="left">
					<html:text property="jd" styleId="jd" readonly="true"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>��ѧ��:
				</td>
				<td align="left" colspan="">
					<html:select property="jxjdm" styleId="jxjdm" onchange="refreshForm('pjpy_shcb_jxjsq.do')" style="width:200px">
						<html:option value=""></html:option>
						<html:options collection="jxjList" property="jxjdm" labelProperty="jxjmc"/>
					</html:select>
<%--					<input type="hidden" id="jxjdm" name="jxjdm" value="<bean:write name="rs" property="jxjdm"/>"/>--%>
					<input type="hidden" id="jxjlb" value="${rs.jxjlb }"/>
				</td>
			<td align="right">
					����רҵ����:
				</td>
				<td align="left" colspan="">
					<html:text property="mc1" styleId="mc1" readonly="true"></html:text>
				</td>
			</tr>
			<tr style="width:22px">
					<td colspan="4">
							<table width="100%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>���Ƴɼ�</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
			</tr>
			<tr>
				<td colspan="4">
					<div id="child2" style="display:none">
						<table width="90%" border="1" align="center" class="tbstyle">
							<tr>
								<td align="center">
									ѧ��
								</td>
								<td align="center">
									ѧ��
								</td>
								<td align="center">
									�γ�����
								</td>
								<td align="center">
									�ɼ�
								</td>
							</tr>
							<logic:notEmpty name="rss">
								<logic:iterate name="rss" id="s">
									<tr style="cursor:hand;">
										<logic:iterate id="v" name="s" >
											<td align="center">
												<bean:write name="v"/>
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
							<logic:empty name="rss">
								<tr>
									<td align="center" colspan="4">
										δ�ҵ��κμ�¼��
									</td>
								</tr>
							</logic:empty>
						</table>
					</div>
				</td>
			</tr>
			<tr style="height:23px">
				<td align="right"><font color="red">*</font>&#20010;&#20154;&#30003;&#35831;: 
				</td>
				<td align="left" colspan="3" >
					<html:textarea property="sqly" rows="7" styleId="sqly" 
					style="width:95%"></html:textarea>
				</td>
		</table>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="chksqtj()"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_print" onclick="print('pjpy_shcbys_jxjsqprint.do')" style="width:80px"
						id="buttonClose">
						�����ӡ
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_cjprint" onclick="window.open('pjpy_shcbys_cjprint.do?xh='+document.getElementById('xh').value)">
						�ɼ���ӡ
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
			<logic:equal value="wcj" name="inserted">
				<script>
					alert('����ʧ��,��ѧ�ڳɼ������޴�ѧ���ɼ���Ϣ,��˶�!');
				</script>
			</logic:equal>
			<logic:equal value="cjbhg" name="inserted">
				<script>
					alert('����ʧ��,��ѧ�ڸ����в��ֿγ̳ɼ�������,��˶�!');
				</script>
			</logic:equal>
			<logic:equal value="kqbhg" name="inserted">
				<script>
					alert('����ʧ��,��ѧ�ڸ��������п���,�ٵ�,���˴���������,��˶�!');
				</script>
			</logic:equal>
			<logic:equal value="cjhkbhg" name="inserted">
				<script>
					alert('����ʧ��,��ѧ�ڸ����в��ֿγ̳��ֻ������,��˶�!');
				</script>
			</logic:equal>
			<logic:equal value="jdbhg" name="inserted">
				<script>
					alert('����ʧ��,��ѧ�ڸ����ɼ����㲻���ϴ˽�ѧ����������,��˶�!');
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
