<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
	</head>
	<body onload="checkWinType();">		
		<html:form action="/rcsw_xszgl.do" method="post">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("�������ѧ����Ч!");
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xh-xm-xb-xy-nj-zy-bj-xz-sfzh-sjhm" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xh-xm-xb-xymc-nj-zymc-bjmc-sfzh-sjhm-xz" />
				<input type="hidden" id="url" name="url" value="/rcsw_xszgl.do?method=xszbbsqAdd" />
				<input type="hidden" id="pkValue" name="pkValue" value="${rs.pkValue}" />
				
				
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" class="button2"
										onclick="saveData('rcsw_xszgl.do?method=xszdjModi&type=save','xh')"
										>
										�� ��
									</button>
									<button type="button" class="button2" onclick="window.close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<th colspan="4">
								<span>�Ǽ���Ϣά��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td>
								<html:text name='rs' 
								           property="save_xh" 
								           readonly="true"
									       styleId="xh"/>								
							</td>	
							<th>
								�꼶
							</th>
							<td>
								<html:text name='rs' property="nj" styleId="nj" disabled="true"/>
							</td>						
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								<html:text name='rs' property="xm" styleId="xm" disabled="true"/>
							</td>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:text name='rs' property="xymc" styleId="xy" disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								<html:text name='rs' property="xb" styleId="xb" disabled="true" />
							</td>
							<th>
								רҵ
							</th>
							<td>
								<html:text name='rs' property="zymc" styleId="zy" disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								<html:text name='rs' property="mzmc" styleId="mzmc" disabled="true" />
							</td>
							<th>
								�༶
							</th>
							<td>
								<html:text name='rs' property="bjmc" styleId="bj" disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								������ò
							</th>
							<td>
								<html:text name='rs' property="zzmmmc" styleId="zzmmmc" disabled="true" />
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<html:text name='rs' property="xz" styleId="xz" disabled="true" />
							</td>							
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<html:text name='rs' property="csrq" styleId="csrq" disabled="true" />
							</td>
							<th>
								��ѧʱ��
							</th>
							<td>
								<html:text name='rs' property="rxrq" styleId="rxrq" disabled="true" />
							</td>
						</tr>						
						<tr>
							<th>
								���֤��
							</th>
							<td>
								<html:text name='rs' property="sfzh" styleId="sfzh" disabled="true" />
							</td>
							<th>
								�ֻ�����
							</th>
							<td>
								<html:text name='rs' property="sjhm" styleId="sjhm" disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								�Ǽ�ʱ��
							</th>
							<td colspan="3">
								<input type="text" name="save_djsj" value="${rs.djsj}" readonly="readonly"/>
							</td>								 
						</tr>
						<tr>
							<th>
								�˳�����
							</th>
							<td colspan="3">
								<html:text property="save_ccqj" name='rs' maxlength="100" style="width:100%"></html:text>
							</td>							 
						</tr>
						</tbody>
					</table>
				</div>
		</html:form>
		<logic:present name="message">
			<script>
				alert('${message}');
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>
</html>
