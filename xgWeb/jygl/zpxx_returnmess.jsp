<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
	function zcwjupdateok(){
	    var url ="/xgxt/zcwjupdate.do?act=update";
		document.forms[0].action = url;
		document.forms[0].submit();   
    }	
    
    function updateokturn(){
        var url2 ="/xgxt/zcwjfb.do";
        document.forms[0].action = url2;
		document.forms[0].submit();
    }
    
    function zpxxreturnmesssave(){   
		 document.forms[0].action = "/xgxt/zpxxReturnMess.do?doType=save";
		 document.forms[0].submit();
	}
    
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵ���� - ��ҵ��Ƹ - ��λ�������</a>
			</p>
		</div>

		<html:form action="/savezcwj.do" method="post">
			<div class="tab" style="margin-top: 0px; padding-top: 0px">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ�������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>

							<th width="16%">
								��λ����
							</th>
							<td width="30%">
								<logic:equal name="updategsmc" value="no">
									<html:text name="rs" property="gsmc" style="width:260px"
										readonly="true" />
								</logic:equal>
								<logic:equal name="updategsmc" value="ok">
									<html:text name="rs" property="gsmc" style="width:260px" />
								</logic:equal>
							</td>


							<th width="16%">
								��λ����
							</th>
							<td width="30%">
								<html:select name="rs" property="dwxz" styleId="dwxz"
									style="width:80px">
									<html:option value=""></html:option>
									<html:options collection="dwxzdm2List" property="dwxz"
										labelProperty="dwxz" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table>
									<tbody>
						<tr>
							<td>
								1����λ�Ա�У����֮רҵ�γ̵������
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs1" value="�ǳ�����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs1" value="����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs1" value="һ��"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs1" value="������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs1" value="�ǳ�������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								2����λ��Ϊ��У����֮���ݷ���ҵ��ʵ������
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs2" value="�ǳ�����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs2" value="����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs2" value="һ��"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs2" value="������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="zyzs2" value="�ǳ�������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								3����У��ҵ����ѧ���ܺ͹���ʵ���ν�
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx1" value="�ǳ�����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx1" value="����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx1" value="һ��"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx1" value="������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx1" value="�ǳ�������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								4����У��ҵ�����ַ��Ϲ�˾������
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx2" value="�ǳ�����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx2" value="����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx2" value="һ��"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx2" value="������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="gzbx2" value="�ǳ�������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								5����У��ҵ���ı���빵ͨ����
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq1" value="�ǳ�����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq1" value="����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq1" value="һ��"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq1" value="������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq1" value="�ǳ�������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								6����У��ҵ���ĵ�����������
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq2" value="�ǳ�����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq2" value="����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq2" value="һ��"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq2" value="������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq2" value="�ǳ�������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								7����У��ҵ���Ĵ��⼰˼������
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq3" value="�ǳ�����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq3" value="����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq3" value="һ��"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq3" value="������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq3" value="�ǳ�������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								8����У��ҵ��������Ự����
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq4" value="�ǳ�����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq4" value="����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq4" value="һ��"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq4" value="������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq4" value="�ǳ�������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								9����У��ҵ���Ķ���˼�����������
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq5" value="�ǳ�����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq5" value="����"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq5" value="һ��"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq5" value="������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<html:radio name="rs" property="jnjq5" value="�ǳ�������"></html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td>
								10����λ����У��ҵ�����������
							</td>
							<td colspan="3">
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="myd" value="�ǳ�����" checked="checked" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="myd" value="����" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="myd" value="һ��" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="myd" value="������" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="myd" value="�ǳ�������" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="red">*Ĭ��ֵΪ�ǳ�����</font>
							</td>
						</tr>
					</tbody>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>��ϸ��Ϣ����</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								�����������
							</th>
							<td colspan="3">
								<html:text name="rs" property="yjfkbt" style="width:100%" />
							</td>
						</tr>
						<tr>
							<th>
								�����������
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="yjfknr" style="width:100%"
									rows="26" />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="zpxxreturnmesssave();">
										�� ��
									</button>
									<button type="reset">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>

		<logic:notEmpty name="insert">
			<logic:equal name="insert" value="ok">
				<script>
                      alert("�ύ�ɹ�!");
                    </script>
			</logic:equal>
			<logic:equal name="insert" value="no">
				<script>
                      alert("�ύʧ��");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>

</html>
