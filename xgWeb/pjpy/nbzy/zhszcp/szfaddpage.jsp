<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="pjpy/nbzy/nbzyJs.js">
</script>
<body>
	<html:form action="/pjpynbzywh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
	<input type="hidden" name="failinfo" id="failinfo" value="${failinfo }"/>
	<input type="hidden" name="act" id="act" value="${act }"/>
		<input type="hidden" id="disableEle" name="disableEle"
			value="xm-xb-xy-nj-zy-bj" />
		<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-nj-zymc-bjmc" />
		<input type="hidden" id="url" name="url"
			value="/pjpy_nbzy_zyszfAdd.do" />
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰ����λ��: �������� - ��Ϣά�� - �ۺ����ʲ���
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
						${title }
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<td align="right">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left">
					<html:text name='rs' property="xh" styleId="xh"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
						class="btn_01" id="buttonFindStu">
						ѡ��
					</button>
				</td>
				<td align="right">
					<font color="red">*</font>ѧ�꣺
				</td>
				<td align="left">
					<html:select property="xn" styleId="xn" styleClass="select">
						<html:options collection="xnList" property="xn" labelProperty="xn"/>
					</html:select>
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
					<font color="red">*</font>ѧ�ڣ�
				</td>
				<td align="left">
					<html:select property="xq" styleId="xq" style="width:90px">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
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
					<bean:message key="lable.xsgzyxpzxy" />��
				</td>
				<td align="left">
					<bean:write name="rs" property="xymc" />
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
					�༶��
				</td>
				<td align="left">
					<bean:write name="rs" property="bjmc" />
				</td>
			</tr>
		</table>
		<fieldset>
				<legend>�Ƿ�ϸ��&nbsp;&nbsp;&nbsp;
						<font color="blue"><button type="button" class="button2" onclick="add2('tTb')" 
							style="height:18px;width:20px">+</button> 
							&nbsp;<button type="button" class="button2" onclick="decrease2('tTb')" 
							style="height:18px;width:20px">-</button></font>
				</legend>
				<table class="tbstyle" align="center" width="93%" id="tTb">
					<thead style="height:23px">
						<tr>
							<td align="center">
								��Ŀ����
							</td>
							<td align="center">
								����
							</td>
							<td align="center">
								�Ƿ�����
							</td>
						</tr>
					</thead>
					<tr style="display:none"  id="1">
						<td align="center">
							<html:text property="lr" styleId="lr1" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs1"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt1" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="2">
						<td align="center">
							<html:text property="lr" styleId="lr2" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs2"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt2" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="3">
						<td align="center">
							<html:text property="lr" styleId="lr3" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs3"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt3" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="4">
						<td align="center">
							<html:text property="lr" styleId="lr4" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs4"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt4" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="5">
						<td align="center">
							<html:text property="lr" styleId="lr5" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs5"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt5" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="6">
						<td align="center">
							<html:text property="lr" styleId="lr6" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs6"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt6" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="7">
						<td align="center">
							<html:text property="lr" styleId="lr7" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs7"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt7" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="8">
						<td align="center">
							<html:text property="lr" styleId="lr8" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs8"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt8" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="9">
						<td align="center">
							<html:text property="lr" styleId="lr9" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs9"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt9" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="10">
						<td align="center">
							<html:text property="lr" styleId="lr10" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs10"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt10" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="11">
						<td align="center">
							<html:text property="lr" styleId="lr11" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs11"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt11" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="12">
						<td align="center">
							<html:text property="lr" styleId="lr12" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs12"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt12" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="13">
						<td align="center">
							<html:text property="lr" styleId="lr13" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs13"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt13" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="14">
						<td align="center">
							<html:text property="lr" styleId="lr14" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs14"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt14" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="15">
						<td align="center">
							<html:text property="lr" styleId="lr15" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs15"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt15" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="16">
						<td align="center">
							<html:text property="lr" styleId="lr16" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs16"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt16" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="17">
						<td align="center">
							<html:text property="lr" styleId="lr17" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs17"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt17" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="display:none"  id="18">
						<td align="center">
							<html:text property="lr" styleId="lr18" style="width:330px"></html:text>
						</td>
						<td align="center">
							<html:text property="fs" onblur="ckinpdata(this)" styleId="fs18"
							 	style="width:60px"></html:text>
						</td>
						<td align="center">
							<html:select property="zt" styleId="zt18" style="width:80px">
								<html:option value="0">�ӷ�</html:option>
								<html:option value="1">�۷�</html:option>
							</html:select>
						</td>
					</tr>
				</table>
		</fieldset>
		<div class="buttontool" align="center">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_nbzy_zysyfsave.do','xh-xn-xq');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" 
						style="width:80px" id="buttonClose">
						�� ��
					</button>
				</div>
				<!-- ������ʾ��Ϣ -->
			<logic:present name="inserted">
			<logic:equal value="yes" name="inserted">
				<script>
					alert("�����ɹ�!");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="no" name="inserted">
				<script>
					alert(""+document.getElementById('failinfo').value);
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:present>
	</html:form>
</body>
