<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript">
		function checkNull(){
			var xq=document.forms[0].xq.value;
			var mxsbc=document.forms[0].mxsbc.value;
			var mtzgxs=document.forms[0].mtzgxs.value;
			var myzgxs=document.forms[0].myzgxs.value;
			if(xq==null||xq==""||mxsbc==null||mxsbc==""||mtzgxs==null||mtzgxs==""||myzgxs==null||myzgxs==""){
				alert("�뽫��\*�ŵ���Ŀ��д������");
				return false;
			}
			return true;
		}
		
		function ckinpdata(obj){
			obj.value = obj.value.replace(/[^(\d|\.)]/g,'');
			var inputStr = obj.value;
			
			if(!(inputStr.match(/\d+/g) || inputStr.match(/\d+\.?\d{0,1}/g)) || inputStr > 100){
				alert('���ݸ�ʽ����ȷ��ֻ�������֣�');
				obj.value = '';
				return false;
			}
			return true;
		}
		
		function save(){
			var ele = ["xn","nd","xq","myzgxs"];
			for(var i=0; i<ele.length; i++){
				if(document.getElementById(ele[i]).value ==''){
					alert('�뽫��\*�ŵ���Ŀ��д������');
					return false;
				}
			}
			if($("cjffsj")){
				var cjffsj = $("cjffsj").value;
				if(cjffsj != ""){
				if (cjffsj.length !=6){
					alert("ʱ���ʽ����Ӧ��Ϊ(��ʽ��199001<1990��01��>)����ȷ�� ");
					return false;
				}
				if (cjffsj.substring(0,4) < 1990){
					alert("ʱ���ʽ����Ӧ��Ϊ(��ʽ��199001<1990��01��>)����ȷ�� ");
					return false;
				}
				if (cjffsj.substring(4,6) >12){
					alert("ʱ���ʽ����Ӧ��Ϊ(��ʽ��199001<1990��01��>)����ȷ�� ");
					return false;
				}
				}
			}
			saveTrainConf('kssqsj','jssqsj','xn','nd','work_conf.do?act=save');
		}
	</script>
</head>
<body>
<html:form action="/work_conf" method="post">
		<input type="hidden" name="xxdm" id="xxdm" value="<bean:write name="xxdm"/>" />
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em>
				<a>
					<%--��ɳ����--%>
					<logic:equal value="10827" name="xxdm">
					ѧ���幤 - �������� - �����趨
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
					�ڹ���ѧ - �������� - �����趨
					</logic:notEqual>
				</a>
			</p>
		</div>
		<div class="tab">
		  <table width="100%" border="0" class="formlist">
			<thead>
				<%--��ɳ����--%>
				<logic:equal value="10827" name="xxdm">
					<tr align="center">
						<th colspan="2">
							<span>ѧ���幤�����趨</span>
						</th>
					</tr>
				</logic:equal>
				<%--�ǳ�ɳ����--%>
				<logic:notEqual value="10827" name="xxdm">
					<tr align="center">
						<th colspan="2">
							<span>�ڹ���ѧ�����趨</span>
						</th>
					</tr>
				</logic:notEqual>
			</thead>
			<tbody>	
				<tr>
					<th><span class="red">*</span>ѧ��</th>
					<td>
						<html:select property="xn" styleId="xn">
							<html:options collection="xnndList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>���</th>
					<td>
						<html:select property="nd" styleId="nd">
							<html:options collection="xnndList" property="nd"
								labelProperty="nd" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>ѧ��</th>
					<td>
						<html:select property="xq" styleId="xq">
							<option value="">
							</option>
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>						
				<%--���Ϻ����̼�����ѧ--%>
				<logic:notEqual value="10856" name="xxdm">						
					<tr>
						<%--�㽭��ýѧԺ--%>
						<logic:equal value="11647" name="xxdm">
							<th>��λ������ʼʱ��</th>
						</logic:equal>
						<%--end�㽭��ýѧԺ--%>
						<%-- ���㽭��ýѧԺ--%>
						<logic:notEqual value="11647" name="xxdm">
							<th>���뿪ʼʱ��</th>
						</logic:notEqual>
						<%-- end���㽭��ýѧԺ--%>
						<td>
							<input type="hidden" name="kssqsj" id="kssqsj" value="" />
							<input type="text" readonly style="cursor:hand;width:80px"
								onclick="return showCalendar('kssqsj1','y-mm-dd');"
								value="<bean:write name="kssj1" />" name="kssqsj1"
								id="kssqsj1" />
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
						<%--�㽭��ýѧԺ--%>
						<logic:equal value="11647" name="xxdm">
							<th>��λ��������ʱ��</th>
						</logic:equal>
						<%--end�㽭��ýѧԺ--%>
						<%-- ���㽭��ýѧԺ--%>
						<logic:notEqual value="11647" name="xxdm">
							<th>�������ʱ��</th>
						</logic:notEqual>
						<%-- end���㽭��ýѧԺ--%>						
						<td>
							<input type="hidden" name="jssqsj" id="jssqsj" value="" />
							<input type="text" readonly style="cursor:hand;width:80px"
								onclick="return showCalendar('jssqsj1','y-mm-dd');"
								value="<bean:write name="jssj1" />" name="jssqsj1"
								id="jssqsj1" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>23){this.value=23}if(this.value<0){this.value=0}"/>
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
				</logic:notEqual>
				<tr>
					<th><span class="red">*</span>ÿ����߹���Сʱ��</th>
					<td>
						<html:text property="myzgxs" styleId="myzgxs" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>248){this.value=248}if(this.value<0){this.value=0}"/>Сʱ
					</td>
				</tr>
				<!--�������ϴ�ѧ-->
				<logic:equal value="11417" name="xxdm">
				<tr>
					<th><span class="red">*</span>ÿ����߹���Сʱ��</th>
					<td>
						<html:text property="mtzgxs" styleId="mtzgxs" onkeyup="value=value.replace(/[^\d]/g,'') "/> Сʱ
					</td>
				</tr>
				</logic:equal>
				<!--end�������ϴ�ѧ-->
				<!--�Ǳ������ϴ�ѧ-->
				<logic:notEqual value="11417" name="xxdm">
					<tr>
						<th>ÿ����߹���Сʱ��</th>
						<td>
							<html:text property="mtzgxs" styleId="mtzgxs" onkeyup="value=value.replace(/[^\d]/g,'');if(this.value>8){this.value=8}if(this.value<0){this.value=0} " maxlength="2"/> Сʱ
						</td>
					</tr>
				</logic:notEqual>
				<!--end�Ǳ������ϴ�ѧ-->
				<%--�й����ʴ�ѧ--%>
				<logic:equal value="10491" name="xxdm">							
					<tr>
						<th>ÿ����߳����</th>
						<td>
							<html:text property="myzgbc" styleId="myzgbc" />
							Ԫ
						</td>
					</tr>
				</logic:equal>
				<%--�������ϴ�ѧ--%>
				<logic:equal value="11417" name="xxdm">
					<tr>
						<th><span class="red">*</span>ÿСʱ���</th>
						<td>
							<html:text property="mxsbc" styleId="mxsbc" />
						</td>
					</tr>	
				</logic:equal>		
				<!--���ϴ�ѧ	-->
				<logic:equal value="10589" name="xxdm">
					<tr>
						<th>��ѧ�ڲ�����Ŀ�����ø���</th>
						<td>
							<html:text property="bkkmsxz" 
							           styleId="bkkmsxz" 
							           maxlength="6"
							           onkeyup="value=value.replace(/[^\d]/g,'') "/>��
						</td>
					</tr>						
				</logic:equal>
				<!--end���ϴ�ѧ	-->			
				<%--��ɳ����ְҵ����ѧԺ--%>
				<logic:equal value="10827" name="xxdm">
					<tr>
						<th>У�ڹ̶���λʹ������������</th>
						<td>
							<html:text property="knsbl" styleId="knsbl" style="width:40px"
								onblur="numFormatChk(this,0,100)" />
						</td>
					</tr>
				</logic:equal>
				<%--�ǳ�ɳ����ְҵ����ѧԺ--%>
				<logic:notEqual value="10827" name="xxdm">
					<tr>
						<th>У�ڹ̶���λʹ���������������õ���</th>
						<td>
							<html:text property="knsbl" styleId="knsbl" style="width:40px"
								onblur="numFormatChk(this,0,100)" />
							%
						</td>
					</tr>
				</logic:notEqual>						
				<tr>
					<th>��𷢷��·�</th>
					<td>
						<html:text property="cjffsj" styleId="cjffsj" onkeyup="value=value.replace(/[^\d]/g,'') ;" maxlength="6"/> (��ʽ��199001<1990��01��>)
					</td>
				</tr>
				<tr>
					<th>���þ��ѹ�����</th>
					<td>
						<html:radio property="jfglkg" value="1">��</html:radio>
						<html:radio property="jfglkg" value="0">��</html:radio>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<span class="red">ע����𷢷��·ݲ�����ʱ��Ĭ�Ϸ����·�Ϊ���¡�</span>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <%--�������ϴ�ѧ--%>
						<logic:equal value="11417" name="xxdm">
							<button type="button" class="button2"
								onclick="if(checkNull()) saveTrainConf('kssqsj','jssqsj','xn','nd','work_conf.do?act=save')">
								����
							</button>
						</logic:equal>
						<%--�Ǳ������ϴ�ѧ--%>
						<logic:notEqual value="11417" name="xxdm">
							<button type="button" class="button2"
								onclick="save();return false;">
								����
							</button>
						</logic:notEqual>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
		</div>
		<logic:notEmpty name="ok">
			<logic:equal name="ok" value="ok">
				<script>alert("����ɹ�!")</script>
			</logic:equal>
			<logic:equal name="ok" value="no">
				<script>alert("����ʧ��!")</script>
			</logic:equal>
		</logic:notEmpty>
	</html:form>
</body>
</html>
