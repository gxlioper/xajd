<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<script type="text/javascript" src="js/xgutil.js"></script>
<script>
	function save(){
		//�����ֶ��Ƿ���д
		if(filedNotNull(['zymc','xkmldm','bmdm'])){
			//�ύ
			refreshForm('xxcshgl.do?method=zyxxUpdate&doType=save');
		}else{
			alert('�뽫��*�ŵ���Ŀ��д������');
			return false;
		}	
	}
	
</script>
</head>

<body>
	<html:form action="/xxcshgl.do?method=zyxxUpdate" method="post">
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title}</a>
			</p>
		</div>

		<div class="tab">
			<table class="formlist" width="100%">
				<thead>
					<tr>
						<th colspan="4"> 
							<span>������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="24%"><font color="red">*</font>רҵ����</th>
						<td width="26%">
							${rs.zydm}
							<input type="hidden" name="save_zydm" value="${rs.zydm}"/>						
						</td>
						<th><font color="red">*</font>רҵ����</th>
						<td>
							<input type="text" name="save_zymc" value="${rs.zymc}" id="zymc" maxlength="32"/>
						</td>
					</tr>
					<tr>
						<th width="24%"><font color="red">*</font>���Ŵ���</th>
						<td width="26%">							
							<input type="text" name="save_bmdm" value="${rs.bmdm}" onkeyup="value=value.replace(/[^a-zA-Z\d]/g,'') " id="bmdm" maxlength="8"/>						
						</td>
						<th>רҵ���</th>
						<td>
							<input type="text" name="save_zyjc" value="${rs.zyjc}" maxlength="32"/>
						</td>
					</tr>
					<tr>
						<th width="24%"><font color="red">*</font>ѧ���������</th>
						<td width="26%">
							<input type="text" name="save_xkmldm" value="${rs.xkmldm}" onkeyup="value=value.replace(/[^a-zA-Z\d|-]/g,'') " maxlength="4" id="xkmldm"/>
						</td>
						<th>רҵӢ������</th>
						<td><input type="text" name="save_zyywmc" value="${rs.zyywmc}" onkeyup="value=value.replace(/[^a-zA-Z]/g,'') " maxlength="256"/></td>
					</tr>
					<tr>
						<th>����רҵ����</th>
						<td><input type="text" name="save_gjzydm" value="${rs.gjzydm}" onkeyup="value=value.replace(/[^a-zA-Z\d]/g,'') " maxlength="8"/></td>
						<th>����ʱ��</th>
						<td>
							<input type="text" name="save_jlny" value="${rs.jlny}" onclick="return showCalendar('jlny','y-mm-dd');" onblur="dateFormatChg(this)" id="jlny" maxlength="8"/>
						</td>
					</tr>
					<tr>
						<th>���Ҵ���</th>
						<td><input type="text" name="save_ksdm" value="${rs.ksdm}" onkeyup="value=value.replace(/[^a-zA-Z\d]/g,'') " maxlength="8"/></td>
						<th></th>
						<td>
							
						</td>
					</tr>				
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						<div class="buttontool">
			            <logic:notEqual value="view" name="doType">
							<button type="button" class="" onclick="save();return false;" >
								��&nbsp;&nbsp;��
							</button>
			            </logic:notEqual>
						<button type="button" class="" onclick="Close();return false;">
								��&nbsp;&nbsp;��
						</button>
						</div>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>		
		</div>	

		<logic:present name="result">
			<intput type="hidden" id="message" value="${message}"/>
			<script>
				alert(document.getElementById('message').value);
				if(window.dialogArguments){
			 		window.dialogArguments.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</html:form>
</body>
</html>