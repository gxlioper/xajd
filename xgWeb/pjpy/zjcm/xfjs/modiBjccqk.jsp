<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xfjs.js'></script>
	<script type="text/javascript">
		/**
		*����ȱ������
		*/
		function computeQqrs(){
			var ydrs = val("ydrs");
			var sdrs = val("sdrs");
			
			ydrs = ydrs == "" ? "0" : ydrs;
			sdrs = sdrs == "" ? "0" : sdrs;
			
			setVal("ydrs",ydrs);
			setVal("sdrs",sdrs);
			setVal("qqrs",toInt(ydrs)-toInt(sdrs));			
		}
		
		function bjccqkXg(){
			//�жϱ����ֶ��Ƿ���д����
			var notNullArray = ['xn','xq','ccrq','bjdm','jclxdm','ydrs','sdrs','fdyclsj'];
			var flag = filedNotNull(notNullArray);
			if(!flag) {alert("�뽫��\"*\"�ŵ���Ŀ����������"); return false};
			//ʵ���������ܴ���Ӧ������
			var sdrs = val('sdrs') == null || val('sdrs')=="" ? "0" : val('sdrs');
			var ydrs = val('ydrs') == null || val('ydrs')=="" ? "0" : val('ydrs');
			if(toInt(ydrs)-toInt(sdrs) <0){
				alert("ʵ����������Ӧ��������");
				return false;
			}
			//��������
			$("buttonSave").disabled=true;
			refreshForm('/xgxt/pjpyxfjs.do?method=modiBjccqk&type=save');	
		}
	</script>
	</head>
	<body>
		
		<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - ѧ�罨�� - ѧ�������� - �༶�������޸�</a>
				</p>
		</div>
		
		
		<html:form action="/pjpyxfjs" method="post">
			<input type="hidden" name="njV" id="njV"/>
			<input type="hidden" name="xyV" id="xyV"/>
			<input type="hidden" name="zyV" id="zyV"/>
			<input type="hidden" name="bjV" id="bjV"/>
			<input type="hidden" name="ccyhlx" id="ccyhlx" value="${rs.ccyhlx}"/>
			<input type="hidden" name="pk" id="pk" value="${rs.pk}"/>
			
			
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶ѧ����</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" 
										onclick="bjccqkXg()"
										id="buttonSave"
										>
										�� ��
									</button>
									<button type="button" 
										onclick="window.close();return false;"
										>
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				<tbody>
				<tr>
					<th>
						<font color="red">*</font>ѧ��
					</th>
					<td align="left">
						<html:text property="xn" name="rs" readonly="true"></html:text>
					</td>
					<th>
						<font color="red">*</font>�������
					</th>
					<td align="left">
						<html:text property="ccrq" name="rs" styleId="ccrq" readonly="true"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>ѧ��
					</th>
					<td align="left">
						<html:text property="xqmc" name="rs" readonly="true"></html:text>
						<html:hidden property="xq" name="rs" styleId="xq"/>
					</td>
					<th>
						<font color="red">*</font>�������
					</th>
					<td align="left">
						<html:text property="jclxmc" name="rs" readonly="true"></html:text>
						<html:hidden property="jclxdm" name="rs"></html:hidden>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>�༶
					</th>
					<td align="left">
						<html:text property="bjmc" name="rs" readonly="true"></html:text>
						<html:hidden property="bjdm" name="rs" styleId="bjdm"/>
					</td>
					<th>
						<font color="red">*</font>Ӧ������
					</th>
					<td align="left">
						<html:text property="ydrs" name="rs" styleId="ydrs" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'');computeQqrs()"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>ʵ������
					</th>
					<td align="left">
						<html:text property="sdrs" name="rs" styleId="sdrs" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'');computeQqrs()"></html:text>
					</td>
					<th>
						ȱ������
					</th>
					<td align="left">
						<html:text property="qqrs" name="rs" readonly="true" styleId="qqrs"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						��ȱ�����Υ������<br/><div align="center">(��:�Է�,˯����...)</div>
					</th>
					<td align="left">
						<html:text property="wjrs" name="rs" styleId="wjrs" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'');"></html:text>
					</td>
					<th>
						<font color="red">*</font>����ʱ��
					</th>
					<td align="left">
						<html:text property="fdyclsj" name="rs" readonly="true" styleId="fdyclsj" onclick="return showCalendar('fdyclsj','y-mm-dd');"></html:text>
					</td>
				</tr>
				<tr>
					<th>
						��ע
					</th>
					<td align="left" colspan="3" style="word-break:break-all;">
						<html:textarea property="bz" name="rs" cols="50" rows="4" style="width:100%" onblur="chLeng(this,600)"></html:textarea>
					</td>
				</tr>
				</tbody>
			</table>
		</div>
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ�!");
				window.close();
				if(window.dialogArguments){
					window.dialogArguments.document.getElementById('search_go').click();
				}	
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ��");
			</script>
		</logic:equal>		
		</html:form>
	</body>
</html>
