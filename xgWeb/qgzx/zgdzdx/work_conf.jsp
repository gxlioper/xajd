<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript">
		function saveData(){
			if(!filedNotNull([],'')){
				alert('�뽫��*��Ŀ��д������');
				return false;
			}else{
				//���ʱ��
				if(ele('kssqsj1')){
					var kssqsj = val('kssqsj1').replace('-','').replace('-','')+val('kssqsj2')+val('kssqsj3')+val('kssqsj4');
					var jssqsj = val('jssqsj1').replace('-','').replace('-','')+val('jssqsj2')+val('jssqsj3')+val('jssqsj4');
					if(toInt(kssqsj)>toInt(jssqsj)){
						alert('У��ѧ�������λ��ʼʱ�����ڽ���ʱ�䣡');
						return false;
					}
				}
				if(ele('xwkssqsj1')){
					var xwkssqsj = val('xwkssqsj1').replace('-','').replace('-','')+val('xwkssqsj2')+val('xwkssqsj3')+val('xwkssqsj4');
					var xwjssqsj = val('xwjssqsj1').replace('-','').replace('-','')+val('xwjssqsj2')+val('xwjssqsj3')+val('xwjssqsj4');
					if(toInt(xwkssqsj)>toInt(xwjssqsj)){
						alert('У��ѧ�������λ��ʼʱ�����ڽ���ʱ�䣡');
						return false;
					}
				}
				if(ele('shkssj1')){
					var shkssj = val('shkssj1').replace('-','').replace('-','')+val('shkssj2')+val('shkssj3')+val('shkssj4');
					var shjssj = val('shjssj1').replace('-','').replace('-','')+val('shjssj2')+val('shjssj3')+val('shjssj4');
					if(toInt(shkssj)>toInt(shjssj)){
						alert('���˵�λ��˿�ʼʱ�����ڽ���ʱ�䣡');
						return false;
					}
				}
				
				saveTrainConf('kssqsj','jssqsj','xn','nd','work_conf.do?act=save');
			}
		}
	</script>
</head>
	<body>
			<html:form action="/chgPriseBat" method="post">
				<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}"/>
				<div class="tab_cur">
					<p class="location">
						<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - �������� - �����趨</a>
					</p>
				</div>
				<div class="tab">
					<table width="80%" align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ڹ���ѧ�����趨</span>
								</th>
							</tr>
						</thead>
						<tbody>
						<tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select property="xn">
									<html:options collection="xnndList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>���</th>
							<td>
								<html:select property="nd">
									<html:options collection="xnndList" property="nd"
										labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select property="xq">
									<option value="">
									</option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>У<font color="red">��</font>ѧ�������λ��ʼʱ��</th>
							<td>
								<input type="hidden" name="kssqsj" id="kssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('kssqsj1','y-mm-dd');"
									value="<bean:write name="kssj1" />" name="kssqsj1" id="kssqsj1" />
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssj2" />"
									name="kssqsj2" id="kssqsj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssj3" />"
									name="kssqsj3" id="kssqsj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="kssj4" />"
									name="kssqsj4" id="kssqsj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
							</td>
						</tr>
						<tr>
							<th>У<font color="red">��</font>ѧ�������λ����ʱ��</th>
							<td>
								<input type="hidden" name="jssqsj" id="jssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('jssqsj1','y-mm-dd');"
									value="<bean:write name="jssj1" />" name="jssqsj1" id="jssqsj1" />
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssj2" />"
									name="jssqsj2" id="jssqsj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssj3" />"
									name="jssqsj3" id="jssqsj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="jssj4" />"
									name="jssqsj4" id="jssqsj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
							</td>
						</tr>
						<tr>
							<th>У<font color="red">��</font>ѧ�������λ��ʼʱ��</th>
							<td>
								<input type="hidden" name="xwkssqsj" id="xwkssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('xwkssqsj1','y-mm-dd');"
									value="<bean:write name="xwkssj1" />" name="xwkssqsj1" id="xwkssqsj1" />
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="xwkssj2" />"
									name="xwkssqsj2" id="xwkssqsj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="xwkssj3" />"
									name="xwkssqsj3" id="xwkssqsj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="xwkssj4" />"
									name="xwkssqsj4" id="xwkssqsj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
							</td>
						</tr>
						<tr>
							<th>У<font color="red">��</font>ѧ�������λ����ʱ��</th>
							<td>
								<input type="hidden" name="xwjssqsj" id="xwjssqsj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('xwjssqsj1','y-mm-dd');"
									value="<bean:write name="xwjssj1" />" name="xwjssqsj1" id="xwjssqsj1" />
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="xwjssj2" />"
									name="xwjssqsj2" id="xwjssqsj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="xwjssj3" />"
									name="xwjssqsj3" id="xwjssqsj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="xwjssj4" />"
									name="xwjssqsj4" id="xwjssqsj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
							</td>
						</tr>
						<tr>
							<th>���˵�λ��˿�ʼʱ��</th>
							<td>
								<input type="hidden" name="shkssj" id="shkssj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('shkssj1','y-mm-dd');"
									value="<bean:write name="shkssj1" />" name="shkssj1" id="shkssj1" />
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="shkssj2" />"
									name="shkssj2" id="shkssj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="shkssj3" />"
									name="shkssj3" id="shkssj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="shkssj4" />"
									name="shkssj4" id="shkssj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
							</td>
						</tr>
						<tr>
							<th>���˵�λ��˽���ʱ��</th>
							<td>
								<input type="hidden" name="shjssj" id="shjssj" value="" />
								<input type="text" readonly style="cursor:hand;width:80px"
									onclick="return showCalendar('shjssj1','y-mm-dd');"
									value="<bean:write name="shjssj1" />" name="shjssj1" id="shjssj1" />
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="shjssj2" />"
									name="shjssj2" id="shjssj2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="shjssj3" />"
									name="shjssj3" id="shjssj3" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
								��
								<input type="text" onkeypress="return numInputValue(this,2,event)"
									style="width:20px" value="<bean:write name="shjssj4" />"
									name="shjssj4" id="shjssj4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>59){this.value=59}if(this.value<0){this.value=0}"/>
							</td>
						</tr>
						<tr>
							<th>ÿ����߹���ʱ����</th>
							<td>
								<html:text property="mtzgxs" styleId="mtzgxs" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>24){this.value=24}if(this.value<0){this.value=0} "/>Сʱ				
							</td>
						</tr>
						<tr>
							<th>ÿ����߹���ʱ����</th>
							<td>
								<html:text property="myzgxs" styleId="myzgxs" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>744){this.value=744}if(this.value<0){this.value=0} "/>
								<html:select property="myzgsjfs" styleId="myzgsjfs">
									<html:option value="h">Сʱ</html:option>
<!--									<html:option value="d">��</html:option>-->
<!--									<html:option value="w">��</html:option>-->
<!--									<html:option value="m">��</html:option>-->
								</html:select>
							</td>
						</tr>
						<tr>
							<th>ÿ����߳����</th>
							<td>
								<html:text property="myzgbc" styleId="myzgbc" maxlength="5" onkeyup="value=value.replace(/[^\d|.]/g,'') "/>
								Ԫ
							</td>
						</tr>
						<tr>
							<th>��𷢷�ʱ����</th>
							<td>
								<html:text property="ffsjjg" styleId="ffsjjg" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="4"/>
								��
							</td>
						</tr>
						<tr>
							<th>У�ڹ̶���λʹ���������������õ���</th>
							<td>
								<html:text property="knsbl" styleId="knsbl" style="width:40px"
									onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>100){this.value=100}if(this.value<0){this.value=0} " maxlength="3" />
								%
							</td>
						</tr>
						<tr>
							<th>�ϱ���ʾ</th>
							<td>
								<html:text property="sbts" styleId="sbts" style="width:150px"
									maxlength="50"/>
							</td>
						</tr>
						</tbody>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
					            <button type="button"
									onclick="saveData()">
									����
								</button>
					          </div>
					        </td>
					      </tr>
					    </tfoot>
					</table>
				</div>				
			</html:form>

			<logic:notEmpty name="ok">
				<logic:equal name="ok" value="ok">
					<script>alert("����ɹ�!")</script>
				</logic:equal>
				<logic:equal name="ok" value="no">
					<script>alert("����ʧ��!")</script>
				</logic:equal>
			</logic:notEmpty>
	</body>
</html>
