<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type="text/javascript" src="js/xgutil.js"></script>
<script type="text/javascript" src="js/String.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript">
	
	
	function valueOf(array,str) {
		for ( var i = 0 ; i< array.length; i ++) {
			if (array[i].trim()==str.trim()) {
				return true;
			}
		}
		return false;
	}
	
	function setLpcl(text) {
		var lpclArr = $('lpcls').value.replace('[','').replace(']','').split(',');
		
		lpcl.innerHTML = "";
		GetListData.getLpcl(text,function(data){
			
			if (data.length>0) {
				dd_html = "";
				for (var i = 0 ; i < data.length ; i ++ ) {
					
					dd_html += data[i].mc;
					dd_html += ":<input type='checkbox' name='save_lpcl'";
					
					if (valueOf(lpclArr,data[i].dm)) {
						dd_html += "checked=true";
					}
					
					if ('sh' == $('doType').value) {
						dd_html += " disabled=true ";
					}
					dd_html += "value='";
					dd_html += data[i].dm;
					dd_html +="'/>&nbsp;&nbsp;";
				}
				
				lpcl.innerHTML=dd_html;
				
				if ('sh' == $('doType').value){
					setLpje($('shjg').value);
				}
				
			}
			
		});
	}
	
	
	
	function setLpje(text) {
		if ('ͨ��' == text) {
			$('lpsj').value = $('nowTime').value;
			$('lpje').disabled=false;
		} else {
			$('lpje').disabled=true;
		}
	}
	
	
	function saveShjg() {
		var shjg = $('shjg').value;
		
		if (''==shjg) {
			alert('��ѡ����˽��!');
			return false;
		} else if ('ͨ��'==shjg && ''==$('lpje').value) {
			alert('������������!');
			return false;
		} else if ('ͨ��'!=shjg){
			$('lpsj').value = "";
			$('lpje').value = "";
		}
		
		showTips('���������У���ȴ�......');
		refreshForm("/xgxt/bxxx.do?method=lpInfo&doType=shOne");
	}
</script>

<body  onload="setLpcl('${rs.lpxm }')"> 
	<html:form action="/bxxx" method="post" >
		<div class="title">
				<div class="title_img" id="title_m" >
					��ǰ����λ�ã�${title }
				</div>
		</div>
		<input type="hidden" id="lpcls" value="${rs.lpcl }" />
		<input type="hidden" id="url" name="url" value="/bxxx.do?method=lpsqUpdate" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-nj-zymc-xymc-bjmc"/>
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="pkValue" value="${pkValue }"/>
		<input type="hidden" id="nowTime" name="nowTime" value="${nowTime }"/>
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		<input type="hidden"  name="save_sqsj" value="${rs.sqsj }"/>
		
		<table class="tbstyle" width="100%">
			<tr>
				<td align="right" width="16%">
					<font color="red">*</font>ѧ�ţ�
				</td>
				<td align="left" width="34%">
					${rs.xh }
					<html:hidden property="save_xh" name="rs"/>
				</td>
				<td width="16%">
					<div align="right">
						������
					</div>
				</td>
				<td width="34%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<td align="right">�Ա�</td>
				<td>${rs.xb }</td>
				<td align="right"><bean:message key="lable.xsgzyxpzxy" />��</td>
				<td>${rs.xymc }</td>
			</tr>
			<tr>
				<td align="right">רҵ��</td>
				<td>${rs.zymc }</td>
				<td align="right">�༶��</td>
				<td>${rs.bjmc }</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>������Ŀ��</td>
				<td>
					
					<logic:equal value="update" name="doType">
						<html:select property="save_lpxm" name="rs" onchange="setLpcl(this.value)">
							<html:options collection="lpxmList" property="dm" labelProperty="mc"/>
						</html:select>
					</logic:equal>
					<logic:notEqual value="update" name="doType">
						${rs.lpxmmc }
					</logic:notEqual>
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td align="right">������ϣ�</td>
				<td colspan="3">
					<div id="lpcl">
						
					</div>
				</td>
			</tr>
			<logic:equal value="view" name="doType">
				<tr>
					<td align="right">��˽����</td>
					<td>
						${rs.shjg }
					</td>
					<td align="right">�����</td>
					<td>
						${rs.lpje }
					</td>
				</tr>
				<tr>
					<td align="right">����ˣ�</td>
					<td>
						${rs.shr }
					</td>
					<td align="right">���ʱ�䣺</td>
					<td>
						${rs.lpsj}
					</td>
				</tr>
			</logic:equal>
			<logic:equal value="sh" name="doType">
				<tr>
					<td align="right"><font color="red">*</font>��˽����</td>
					<td>
						<html:select property="save_shjg" styleId="shjg" name="rs" onchange="setLpje(this.value)">
							<html:option value=""></html:option>
							<html:options collection="shztList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
					<td align="right">�����</td>
					<td>
						<input type="hidden" name="save_shr" value="${userName }"/>
						<input type="hidden" name="save_lpsj" id="lpsj" name="rs"/>
						
						<html:text property="save_lpje" styleId="lpje" onblur="checkMoney(this)" maxlength="5" name="rs"></html:text>
					</td>
				</tr>
				
			</logic:equal>
			<tr>
				<td align="right">����˵����</td>
				<td colspan="3">
					<logic:equal value="sh" name="doType">
						<html:textarea property="save_jtsm" onblur="checkLen(this,500)" rows="8" style="width:85%" name="rs" readonly="true"></html:textarea>
					</logic:equal>
					<logic:notEqual value="sh" name="doType">
						<html:textarea property="save_jtsm" onblur="checkLen(this,500)" rows="8" style="width:85%" name="rs"></html:textarea>
					</logic:notEqual>
					
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<logic:equal value="sh" name="doType">
				<button class="button2" id="buttonSave" onclick="saveShjg();">
					��&nbsp;&nbsp;��
				</button>
			</logic:equal>
			<logic:equal value="update" name="doType">
				<button class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/bxxx.do?method=lpInfo&doType=modify','save_lpxm');">
					��&nbsp;&nbsp;��
				</button>
			</logic:equal>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<button class="button2"  onclick="window.close();return false;">
				��&nbsp;&nbsp;��
			</button>
			
		</div>
	</html:form>
	<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
