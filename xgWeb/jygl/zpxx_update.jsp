<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
	function isEmail(sEmail){	      
 	      var p = /^[_\.0-9a-z-]+@([0-9a-z][0-9a-z-]+\.){1,4}[a-z]{2,3}$/i; 
 	      return p.test(sEmail);
     }
	
	function zpxxupdate(){
      var email = document.getElementById("email").value;   
      var zpzw = document.getElementById("zpzw").value;   
      var gsmc = document.getElementById("gsmc").value;  
      var day = document.getElementById("day").value;
      var hour = document.getElementById("hour").value;
      var min = document.getElementById("min").value; 
     
      if(zpzw==""){
      alert("��Ƹְλ����Ϊ�գ�");
      return false;
      }
      if(gsmc==""){
      alert("��˾���Ʋ���Ϊ�գ�");
      return false;
      }      
     if(!isEmail(email)){
     alert("�������䲻�Ϸ���");
     return false;
     }
     if((day==""&&(hour!=""||min!=""))||(day!=""&&hour==""&&min!="")||(day!=""&&hour!=""&&min=="")){
     alert("����ʱ���������");
     return false;
     }
     
		 document.forms[0].action = "/xgxt/zpxxupdate.do?doType=update&act=update";
		 document.forms[0].submit();
        
    }
	
	</script>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��Ƹ��Ϣ�޸�</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ƹ��Ϣ�޸�</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="zpxxupdate()">
										�� ��
									</button>
									<button type="reset">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								����ʱ��
							</th>
							<td colspan="3">
								<html:text name="rs" property="fbsj" style="width:162px"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th width="13%">
								��Ƹְλ
							</th>
							<td width="37%">
								<html:text name="rs" property="zpzw" style="width=100%"
									readonly="true" />
							</td>
							<th width="13%">
								��˾����
							</th>
							<td width="37%">
								<html:text name="rs" property="gsmc" style="width=100%"
									readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<html:text name="rs" property="email" style="width=100%"
									maxlength="30" />
							</td>
							<th>
								��ϵ�绰
							</th>
							<td>
								<html:text name="rs" property="lxdh" style="width=100%"
									maxlength="15" />
							</td>
						</tr>
						<tr>
							<th>
								�����ص�
							</th>
							<td>
								<html:text name="rs" property="gzdd" style="width=100%"
									maxlength="25" />
							</td>
							<th>
								��Ƹ����
							</th>
							<td>
								<html:text name="rs" property="zprs" style="width=100%"
									maxlength="3" />
							</td>
						</tr>
						<tr>
							<th>
								��ҵ����
							</th>
							<td>
								<html:select name="rs" property="hyfl" styleId="hyfl"
									style="width=100%">
									<html:option value=""></html:option>
									<html:options collection="hyflList" property="hyfl"
										labelProperty="hyfl" />
								</html:select>
							</td>
							<th>
								����Ҫ��
							</th>
							<td>
								<html:text name="rs" property="wyyq" style="width=100%"
									maxlength="10" />
							</td>
						</tr>
						<tr>
							<th>
								������нˮ
							</th>
							<td>
								<html:select name="rs" property="syxs" style="width=100%">
									<html:option value=""></html:option>
									<html:option value="����">����</html:option>
									<html:option value="1000����">1000����</html:option>
									<html:option value="1000-1500">1000-1500</html:option>
									<html:option value="1500-2500">1500-2500</html:option>
									<html:option value="2500-3500">2500-3500</html:option>
									<html:option value="3500-5000">3500-5000</html:option>
									<html:option value="5000-7000">5000-7000</html:option>
									<html:option value="7000-10000">7000-10000</html:option>
									<html:option value="10000����">10000����</html:option>
								</html:select>
							</td>
							<th>
								ת����нˮ
							</th>
							<td>
								<html:select name="rs" property="zzxs" style="width=100%">
									<html:option value=""></html:option>
									<html:option value="����">����</html:option>
									<html:option value="1000����">1000����</html:option>
									<html:option value="1000-1500">1000-1500</html:option>
									<html:option value="1500-2500">1500-2500</html:option>
									<html:option value="2500-3500">2500-3500</html:option>
									<html:option value="3500-5000">3500-5000</html:option>
									<html:option value="5000-7000">5000-7000</html:option>
									<html:option value="7000-10000">7000-10000</html:option>
									<html:option value="10000����">10000����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								ѧ��Ҫ��
							</th>
							<td>
								<html:select name="rs" property="xlyq" style="width=100%">
									<html:option value=""></html:option>
									<html:option value="ר��">ר��</html:option>
									<html:option value="����">����</html:option>
									<html:option value="˶ʿ">˶ʿ</html:option>
									<html:option value="��ʿ">��ʿ</html:option>
								</html:select>
							</td>
							<th>
								����ʱ��
							</th>
							<td>
								<html:text name="rs" style="cursor:hand; width:105px;"
									styleId="day" property="day"
									onclick="return showCalendar('day','y-mm-dd');" readonly="true" />
								<html:select name="rs" property="hour">
									<html:option value=""></html:option>
									<html:option value="06">06</html:option>
									<html:option value="07">07</html:option>
									<html:option value="08">08</html:option>
									<html:option value="09">09</html:option>
									<html:option value="10">10</html:option>
									<html:option value="11">11</html:option>
									<html:option value="12">12</html:option>
									<html:option value="13">13</html:option>
									<html:option value="14">14</html:option>
									<html:option value="15">15</html:option>
									<html:option value="16">16</html:option>
									<html:option value="17">17</html:option>
									<html:option value="18">18</html:option>
									<html:option value="19">19</html:option>
									<html:option value="20">20</html:option>
									<html:option value="21">21</html:option>
									<html:option value="22">22</html:option>
								</html:select>
								ʱ
								<html:select name="rs" property="min">
									<html:option value=""></html:option>
									<html:option value="00">00</html:option>
									<html:option value="10">10</html:option>
									<html:option value="20">20</html:option>
									<html:option value="30">30</html:option>
									<html:option value="40">40</html:option>
									<html:option value="50">50</html:option>
								</html:select>
								��
							</td>
						</tr>
						<tr>
							<th>
								����Я��
							</th>
							<td>
								<html:text name="rs" property="msxd" style="width:225px"
									maxlength="30" />
							</td>
							<th>
								���Եص�
							</th>
							<td>
								<html:text name="rs" property="msdd" style="width:200px"
									maxlength="30" />
							</td>
						</tr>
						<logic:notEqual name="xxdm" value="10690" scope="session">
							<tr>
								<th>
									��λְ��
								</th>
								<td colspan="3" >
									<html:textarea name="rs" property="gwzz" rows="6"
										style="width:500px" />
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th>
								ְλҪ��
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="zwyq" rows="6"
									style="width:500px" />
							</td>
						</tr>
						<tr>
							<th>
								��˾���
							</th>
							<td colspan="3">
								<html:textarea name="rs" property="gsjj" rows="6"
									style="width:500px" />
							</td>
						</tr>
						</tbody>
				</table>
		</html:form>
		<logic:notEmpty name="update">
			<logic:equal name="update" value="ok">
				<script>
                      alert("�޸ĳɹ���");
                    </script>
			</logic:equal>
			<logic:equal name="update" value="no">
				<script>
                      alert("����ʧ��!");
                    </script>
			</logic:equal>
		</logic:notEmpty>

	</body>
</html>
