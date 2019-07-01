<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type='text/javascript' src="js/comm/message.js"></script>
	<script type='text/javascript' src="js/uicomm.js"></script>
	<script type='text/javascript' src="js/String.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	<script language="javascript" src="js/comm/commFunction.js"></script>
	<script	type="text/javascript">
	</script>
</head>
<body>
	
	<html:form action="/gyglnew_qsgl" method="post">
		<input type="hidden" id="refreshParent" value="true"/>
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>	
		<input type="hidden" name="lddm" value="${rs.lddm}"/>
		<logic:notEqual value="10351" name="xxdm">
			<input type="hidden" name="qsh" value="${rs.qsh}"/>
		</logic:notEqual>
		<logic:equal value="10351" name="xxdm">
			<input type="hidden" name="yqsh" value="${rs.qsh}"/>
		</logic:equal>
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>������Ϣ�޸�</a>
			</p>
		</div>		
		--%><div class="prompt" id="span_qsh" style="display: none">
	       <h3><span>��ʾ��</span></h3>
	       <p>�����Һ��ڱ�¥�����Ѵ��ڣ�<br/></p>
	   	</div>
		
		<div class="tab" style="width:100%;height:435px;overflow-x:hidden;overflow-y:auto;">
		<table class="formlist" width="95%">
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>¥����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="16%">
					¥������
				</th>
				<td align="left" width="34%" nowrap="nowrap">
					${rs.ldmc }
				</td>
				<th width="16%">
					¥���Ա�
				</th>
				<td width="34%" id="ldxb">
					${rs.ldxb }
				</td>
			</tr>
			<tr>
				<th>¥������</th>
				<td id="ldcs">
					${rs.ldcs }
				</td>
				<th>¥����ʼ��</th>
				<td id="qsch">
					${rs.qsch }
				</td>
			</tr>
			<tr>
				<th>�Ƿ�0��</th>
				<td>${rs.sfhlc }</td>
				<th></th>
				<td></td>
			</tr>
			</tbody>
			
			<thead>
				<tr style="height:22px">
					<th colspan="4">
						<span>������Ϣ</span>
					</th>
				</tr>
			</thead>
			
			<tbody>
			<tr>
				<th>���</th>
				<td>
					${rs.chmc }
				</td>
				<th>
					���Һ�				
				</th>
				<!-- ���ݴ�ѧ���Ի� -->
				<logic:equal value="10351" name="xxdm">
					<td>
					<html:text property="qsh" styleId="qsh" maxlength="20"  value="${rs.qsh }" styleClass="text_nor"></html:text>
					<html:hidden property="xxdm" value="${xxdm}"/>
					</td>
				</logic:equal>
				<logic:notEqual value="10351" name="xxdm">
					<td>
					${rs.qsh }
					</td>
				</logic:notEqual>
			</tr>
			<tr>
				<th>�����Ա�</th>
				<td>
					<logic:equal value="true" name="qsqtxx" property="xbsfkxg">
						<html:select property="qsxb" name="rs" styleId="qsxb" style="width:50px;">
							<html:option value="��">��</html:option>
							<html:option value="Ů">Ů</html:option>
						</html:select>					
					</logic:equal>
					<logic:notEqual value="true" name="qsqtxx" property="xbsfkxg">
						${rs.qsxb }
					</logic:notEqual>
				</td>
				<th>��λ��</th>
				<td>
					<logic:equal value="0" name="qsqtxx" property="cwfprzgs">
						<html:text property="cws" styleId="cws" maxlength="2" styleClass="text_nor" value="${rs.cws}" onkeyup="checkInputData(this);"></html:text>
					</logic:equal>
					<logic:notEqual value="0" name="qsqtxx" property="cwfprzgs">
						<html:text property="cws" styleId="cws" maxlength="2" styleClass="text_nor" value="${rs.cws}" disabled="true"></html:text>
					</logic:notEqual>
				</td>		
			</tr>
			<tr>
				<th><font color="red">*</font>�շѱ�׼</th>
				<td>
					<html:text property="sfbz" styleId="sfbz" maxlength="5" styleClass="text_nor" value="${rs.sfbz }" onkeyup="checkInputData(this);"></html:text>
				</td>
				<th>���ҵ绰</th>
				<td>
					<html:text property="qsdh" styleId="qsdh" maxlength="20" styleClass="text_nor" value="${rs.qsdh }" onblur="checkPhone(this);"></html:text>
				</td>
			</tr>
			<tr>
				<th>���޿յ�</th>
				<td>
					<div>
						<html:radio property="ywkt" value="��" styleId="ywkt" name="rs" >��</html:radio>
						<html:radio property="ywkt" value="��" styleId="ywkt" name="rs"  >��</html:radio>
					</div>
				</td>
			</tr>
			<tr>
				<th>
					��ע
					<br/><font color="red">(������1000����)</font>
				</th>
				<td colspan="3" >
					<html:textarea property='bz' style="width:95%;word-break:break-all;" styleId="bz" rows='7' value="${rs.bz}" onblur="chLeng(this,1000);"/>
				</td>
			</tr>
			</tbody>
		</table>
		</div>
		<table class="formlist" width="95%">
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button" name="�ύ" id="buttonSave" onclick="saveData('gyglnew_qsgl.do?method=qswhModi&doType=save','qsh-sfbz');">����</button>
			            <button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			</tfoot>
		</table>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
		showAlert(jQuery("#message").val(),{},{"clkFun":function(){
			if (parent.window){
				refershParent();
				
			}
		}});
		</script>
	</logic:present>
</body>
</html>
