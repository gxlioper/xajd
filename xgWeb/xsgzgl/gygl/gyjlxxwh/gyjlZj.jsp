<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gyjlxxwh/js/gyjlxxwh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script	type="text/javascript">
		function getJllbList(obj){
			jQuery.post('gyglnew_gyjlgl.do?method=getJllbList',{jldldm:obj.value},function(data){
					var option = "";
					for(var i = 0; i < data.length; i++){
						option +="<option value='"+data[i].jllbdm+"'>"+data[i].jllbmc+"</option>";
					}
					jQuery('#jllbdm').empty().append(option);			
			},'json');
		}
	</script>
</head>
<body>
	<html:form action="/gyjl_gyjlwh" method="post" styleId="gyjlwhForm" onsubmit="return false">	
		<input type="hidden" id="username" name="username" value="${username}"/>
		<input type="hidden" name="objStr" id="objStr"/>
		<html:hidden property="wjxn" styleId="wjxn" value="${xn}"/>
		<html:hidden property="wjxq" styleId="wjxq" value="${xq}"/>
		<div class="tab" style="width:100%;overflow-x:hidden;overflow-y:auto;" >
		<table class="formlist" border="0" width="100%">	
			<thead>
				<tr>
					<th colspan="4">
					<span>��Ԣ������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					<font color="red">*</font>Υ��ѧ��
				</th>
				<td align="left" width="30%">
					${xn}
				</td>
					<th align="right" width="20%">
						<font color="red">*</font>Υ��ѧ��
					</th>
				<td align="left" width="30%">
					${xqmc}
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					<font color="red">*</font>���ɴ���
				</th>
				<td align="left" width="30%">
					<html:select property="jldldm" styleId="jldldm" style="width:140px" onchange="getJllbList(this)">
					<html:options collection="jldlList" labelProperty="jldlmc" property="jldldm"/>
					</html:select>
				</td>
				<th align="right" width="20%">
					<font color="red">*</font>�������				
				</th>
				<td align="left" width="30%" >
					<html:select property="jllbdm" styleId="jllbdm" style="width:140px" >
					<html:options collection="jllbList" labelProperty="jllbmc" property="jllbdm"/>
					</html:select>
				</td>
			</tr>
			<tr>
					<th width="20%">
						<font color="red">*</font>Υ��ʱ��				
					</th>
					<td align="left" width="30%" colspan="3">
						<html:text property="wjsj" styleId="wjsj" style="width:140px" onkeypress="onlyBackSpace(this,event);"
							onclick="return showCalendar(this.id,'yyyy-MM-dd')"  readonly="true"></html:text>
					</td>
			</tr>
			<tr>
					<th>
						��ע
						<br /><font color="red">(������100����)</font>
					</th>
					<td colspan="4">
						<html:textarea property='bz' style="width:98%" styleId="bz" rows='3' onblur="chLeng(this,100);"/>
					</td>
			</tr>
			</tbody>
			
			<thead>
				<tr>
					<th colspan="4">
						<span>Υ����Ϣ <font color='red'>����������ʽ��1��¥-101-1</font></span>
						
					</th>
				</tr>
				<tr>
					<td colspan="6">
					<font>ѧ��</font>
					<input type="text" id="cxzd" name="cxzd" maxleng="20"/>
					<font>¥��-���Һ�-��λ��</font>
					<input type="text" id="qsxx" name="qsxx" maxleng="50" width="200px"/>
					<font<b>ͬ��</b></font><input id="tqs" class="checkbox" type="checkbox" name="tqs" value="Y"/>
					<button type="button" onclick="addWjxs();return false;" class="btn_01">����</button>
					<button type="button" onclick="delWjxs();return false;" class="btn_01">ɾ��</button>
					</td>
				</tr>
			</thead>
			
				<table id="xs_body" border="0" width="100%" class="datelist">
					<thead>
						<tr>
							<th>
								<input name="th" type="checkbox" onclick="selectAll(this);"/>
							</th>
							<th>
								ѧ��
							</th>
							<th>
								����
							</th>
							<th>
								�Ա�
							</th>
							<th>
								�༶����
							</th>
							<th>
								ס������
							</th>
							<th>
								Υ��ʱ��
							</th>
							<th>
								�������
							</th>
							<th>
								����Ա
							</th>
						</tr>
					</thead>
					<tbody id="tbody_wjxs">
					</tbody>
			</table>
			
			</table>
		</div>
			<div style="height: 28px"></div>
			<div>
			<table width="100%" border="0" class="formlist"
				style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
			      <tr style="height:22px">
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button"  name="�ύ" id="buttonSave" onclick="saveWjxx('save');return false;">����</button>
			            <button type="button"  name="�ر�" id="buttonClose" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			 </tfoot>
			</table>
		</div>
	</html:form>
</body>
</html>