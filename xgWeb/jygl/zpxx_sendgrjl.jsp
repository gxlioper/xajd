<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
	 function sendgrjl(){
	   var xxsh = document.getElementById("xxsh").value;
	   if(xxsh=="δ���"||xxsh=="δͨ��X"){
	       if (confirm("������δͨ����ˣ���ȷ��Ҫ��Ͷ�ݼ���Ȼ��ȴ����ͨ����")) {
		      document.forms[0].action = "/xgxt/sendgrjl.do?act=send&doType=view";
		      document.forms[0].submit();			
		      return true;
		   } else {
				return false;
		   }
	   }else{
	     document.forms[0].action = "/xgxt/sendgrjl.do?act=send&doType=view";
		 document.forms[0].submit();
	   }
	 }
	 
	</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��Ƹ��ϸ��Ϣ</a>
			</p>
		</div>


		<html:form action="/data_search" method="post">
			<html:hidden name="rs" property="xxsh" />
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ƹ��ϸ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="sendgrjl();return false;">
										Ͷ�ݼ���
									</button>
									<button onclick="Close();return false;">
										�رմ���
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="13%">
								��Ƹְλ
							</th>
							<td width="37%">
								<html:text name="rs" property="zpzw" readonly="true"
									style="width=100%" />
							</td>
							<th width="13%">
								��˾����
							</th>
							<td width="37%">
								<html:text name="rs" property="gsmc" readonly="true"
									style="width=100%" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<bean:write name="rs" property="email" />
							</td>
							<th>
								��ϵ�绰
							</th>
							<td>
								<bean:write name="rs" property="lxdh" />
							</td>
						</tr>
						<tr>
							<th>
								�����ص�
							</th>
							<td>
								<bean:write name="rs" property="gzdd" />
							</td>
							<th>
								��Ƹ����
							</th>
							<td>
								<bean:write name="rs" property="zprs" />
							</td>
						</tr>
						<tr>
							<th>
								��ҵ����
							</th>
							<td>
								<bean:write name="rs" property="hyfl" />
							</td>
							<th>
								����Ҫ��
							</th>
							<td>
								<bean:write name="rs" property="wyyq" />
							</td>
						</tr>
						<tr>
							<th>
								������нˮ
							</th>
							<td>
								<bean:write name="rs" property="syxs" />
							</td>
							<th>
								ת����нˮ
							</th>
							<td>
								<bean:write name="rs" property="zzxs" />
							</td>
						</tr>
						<tr>
							<th>
								ѧ��Ҫ��
							</th>
							<td>
								<bean:write name="rs" property="xlyq" />
							</td>
							<th>
								����ʱ��
							</th>
							<td>
								<bean:write name="rs" property="mssj" />
							</td>
						</tr>

						<tr>
							<th>
								��λְ��
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="gwzz" rows="8" cols="75%"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								ְλҪ��
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="zwyq" rows="8" cols="75%"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								��˾���
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="gsjj" rows="8" cols="75%"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								����ʱ��
							</th>
							<td>
								<bean:write name="rs" property="fbsj" />
							</td>
							<th>

							</th>
							<td>

							</td>
						</tr>
					</tbody>
				</table>
		</html:form>
		<logic:notEmpty name="insert">
			<logic:equal name="insert" value="no">
				<script>
                       alert("������ͬһ�ҹ�˾ͬһ��ְλ�ظ�Ͷ������");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="havegrjl">
			<logic:equal name="havegrjl" value="no">
				<script>
                       alert("���˼����޷��ҵ������Ƚ��и��˼����Ǽǣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
		<logic:notEmpty name="send">
			<logic:equal name="send" value="ok">
				<script>
                       alert("�ύ�ɹ����뾲��֪ͨ");
                    </script>
			</logic:equal>
			<logic:equal name="send" value="no">
				<script>
                       alert("�ύʧ�ܣ�");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
