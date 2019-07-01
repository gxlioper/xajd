<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="js/xgutil.js"></script>
<script type="text/javascript">	
	function save() {
		var url = "pjpy_tyb_pjsjsz.do?act=save";
		var jxjsqnd = $('jxjsqnd').value;
		var jxjsqxn = $('jxjsqxn').value;
		var jxjsqxq = $('jxjsqxq').value;
		
		var ndbz = $('ndbz').checked;
		var xnbz = $('xnbz').checked;
		var xqbz = $('xqbz').checked;
		
		//�۲�������Ϣ
		var zcxn = false;
		var zcnd = false;
		var zcxq = false;
		if($("zcxn")){
			zcxn = $('zcxn').checked;
		}
		if($("zcnd")){
			zcnd = $('zcnd').checked;
		}
		if($("zcxq")){
			zcxq = $('zcxq').checked;
		}
		if (jxjsqnd=='' || jxjsqxn=='' || jxjsqxq=='') {
			alert("��*���ֶα���ѡ��!");
			return false;
		}
		if (ndbz ==false && xnbz==false && xqbz ==false) {
			alert("��*���ֶα���ѡ��!");
			return false;
		}
		if ($('zcxn')) {
			if (zcxn ==false && zcnd==false && zcxq ==false) {
				alert("��*���ֶα���ѡ��!");
				return false;
			}
			if (zcnd != false && (zcxn != false || zcxq != false)) {
				alert("�۲����ڣ����,ѧ���ѧ�ڲ���ͬʱѡ��!");
				return false;
			}
		}
		
		
		if (ndbz != false && (xnbz != false || xqbz != false)) {
			alert("�������ڣ����,ѧ���ѧ�ڲ���ͬʱѡ��!");
			return false;
		}
		
		
		refreshForm('pjpy_tyb_pjsjsz.do?act=save');
	}
</script>
</head>

<body onload="">
	<html:form action="/pjpyTybPjzq">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�������� - �������� - ��������</a>
				</p>
			</div>
			<div class="tab">
			<table class="formlist" id="rsTable" style="width: 100%">
				<thead>
					<tr>
						<th colspan="2"><span>��������ʱ������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th><span class="red">*</span>�����������</th>
						<td>
							<html:select property="jxjsqnd" style="jxjsqnd" value="${rs.jxjsqnd }">
								<html:options collection="ndList" property="nd" labelProperty="nd" />
							</html:select>			
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>��������ѧ��</th>
						<td>
							<html:select property="jxjsqxn" style="jxjsqxn" value="${rs.jxjsqxn }">
								<html:options collection="xnList" property="xn" labelProperty="xn" />
							</html:select>			
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>��������ѧ��</th>
						<td>
							<html:select property="jxjsqxq" style="jxjsqxq" value="${rs.jxjsqxq }">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
				</tbody>
			</table>

			<table class="formlist" id="rsTab" style="width: 100%">
				<thead>
					<tr>
						<th colspan="2"><span>����������������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th><span class="red">*</span>������������</th>
						<td>
							<input type="checkbox" value="checked" name="ndbz" id="ndbz" ${rs.nd }/>���	
							<br/>	
							<input type="checkbox" value="checked" name="xnbz" id="xnbz" ${rs.xn }/>ѧ��
							<input type="checkbox" value="checked" name="xqbz" id="xqbz" ${rs.xq }/>ѧ��
						</td>
					</tr>
					<!--�������ۺϲ�������-->
					<logic:equal value="true" name="hasZczq">
							<tr>
								<th><span class="red">*</span>�ۺϲ�������</th>
								<td>
									<input type="checkbox" value="checked" name="zcnd" id="zcnd" ${rs.zcnd }/>���	
									<br/>	
									<input type="checkbox" value="checked" name="zcxn" id="zcxn" ${rs.zcxn }/>ѧ��
									<input type="checkbox" value="checked" name="zcxq" id="zcxq" ${rs.zcxq }/>ѧ��
								</td>
							</tr>
					</logic:equal>
					<!--end�������ۺϲ�������-->
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">			            
						<button type="button" class="button2" onclick="save();return false;" style="width:80px"
							id="btn_save">
							�� ��
						</button>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.close();return false;" style="width:80px;display:none" 
							id="buttonClose">
							�� ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>

			<logic:present name="inserted">
				<logic:equal value="yes" name="inserted">
					<script>
						alert('�����ɹ���');
						window.close();
						if (window.dialogArguments) {
							window.dialogArguments.document.getElementById('search_go').click();
						}
					</script>
				</logic:equal>
				<logic:equal value="no" name="inserted">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
			<script>
				if(window.dialogArguments){
					ele('buttonClose').style.display='';
				}
			</script>
		</html:form>
	</body>
