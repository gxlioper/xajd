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
		if(filedNotNull(['bmjb','bmlb'])){
			//�ύ
			refreshForm('xxcshgl.do?method=xxbmUpdate&doType=save');
		}else{
			alert('�뽫��*�ŵ���Ŀ��д������');
			return false;
		}	
	}
	
</script>
</head>

<body>
	<html:form action="/xxcshgl.do?method=xxbmUpdate" method="post">
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
						<th width="24%"><font color="red">*</font>���Ŵ���</th>
						<td width="26%">
							${rs.bmdm}
							<input type="hidden" name="save_bmdm" value="${rs.bmdm}"/>						
						</td>
						<th>��������</th>
						<td>
							<input type="text" name="save_bmmc" value="${rs.bmmc}" maxlength="128"/>
						</td>
					</tr>
					<tr>
						<th width="24%">���Ÿ�����</th>
						<td width="26%">
							<input type="text" name="save_bmfdm" value="${rs.bmfdm}" maxlength="8"/>
						</td>
						<th>����ƴ�����</th>
						<td><input type="text" name="save_bmpyjc" value="${rs.bmpyjc}" onkeyup="value=value.replace(/[^a-zA-Z\d]/g,'') " maxlength="64"/></td>
					</tr>
					<tr>
						<th><font color="red">*</font>���ż���</th>
						<td><input type="text" name="save_bmjb" value="${rs.bmjb}" id="bmjb" onkeyup="value=value.replace(/[^\d]/g,'') "/></td>
						<th><font color="red">*</font>�������</th>
						<td>
							<html:select property="save_bmlb" name="rs" styleId="bmlb">
								<html:options collection="xxbmlbList" property="dm" labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>��ע</th>
						<td colspan="3">
							<html:textarea property="save_bz" name="rs" rows="3" cols="60" onblur="chLeng(this,128)"></html:textarea>				
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