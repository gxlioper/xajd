<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->	
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/String.js"></script>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript">
		function checkDzyx(){
			var dzyx = document.getElementById("dzyx").value;
			if(!isEmail(dzyx) && dzyx!=""){
				alert("��������ȷ�ĵ�������!");
				return false;
			}
			return true;
		}
	
	</script>
	<body>		
		<html:form action="/studentMessage_conf" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ��Ϣ�޸�</a>
				</p>
			</div>
			
			<logic:equal value="teacher" name="userOnLine">
				<br/><br/>
				<center> ��ҳ��ֻ��ѧ�����ܷ��ʣ�</center>
			</logic:equal>
			
			<logic:equal value="student" name="userOnLine">
				<div class="tab">
				<table width="80%" class="formlist" align="center">
					<thead>
						<tr>
							<th colspan="2">
								<span>��Ϣ�޸�</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th>��ϵ�绰</th>
						<td>
							<html:text property="lxdh" name="rs" styleId="lxdh" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>						
					</tr>
					<tr>
						<th>�ֻ�����</th>
						<td>							
							<html:text property="sjhm" name="rs" styleId="sjhm" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>						
					</tr>
					<tr>
						<th>QQ����</th>
						<td>
							<html:text property="qqhm" name="rs" styleId="qqhm" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>						
					</tr>
					<tr>
						<th>��������</th>
						<td>
							<html:text property="dzyx" name="rs" styleId="dzyx"/>
						</td>						
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				            <button name="Submit2" class="button2"
								onclick="if(checkDzyx()){refreshForm('stu_info_add.do?method=modiStuInfo&act=save');this.disabled=true;}">
								�� ��
							</button>
				          </div>
				        </td>
				      </tr>
				    </tfoot>
				</table>
			</div>

			<logic:notEmpty name="oper">
				<logic:equal value="true" name="result">
					<script>alert("����ɹ���");</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>alert("����ʧ�ܣ�");</script>
				</logic:equal>	
			</logic:notEmpty>	
			</logic:equal>			
		</html:form>
	</body>
</html>
