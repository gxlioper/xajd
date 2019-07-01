<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/interface/systemFunction.js"></script>
	<script	type="text/javascript">
		jQuery(function(){
			// ���水ť
			jQuery('#buttonSave').click(function(){
				saveData('njjs_jygl.do?method=byqxUpdate&doType=save','byqx-yb-lxdz');
			});
			
			// �رհ�ť
			jQuery('#buttonClose').click(function(){
				window.close();
			});	
			
			jQuery("input[name='sjhm']").keyup(function(){
				checkInputData(this);
			});
			
			jQuery("input[name='lxdh']").keyup(function(){
				checkInputData(this);
			});
			
			jQuery("input[name='yb']").keyup(function(){
				checkInputData(this);
			});
			
		});
	</script>
	<style type="text/css">
		table{
			border-collapse:collapse;
		}
		table th{
			width:20%;
		}
		
		table td{
			width:30%;
		}
		
		table span{
			color:red;
		}
		
		#jydw{
			width:88%;
		}
	</style>

</head>
<body>
	<html:form action="/njjs_jygl" method="post">
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>
		<input type="hidden" name="csrq" value="${rs.csrq }"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��ҵȥ��¼��</a>
			</p>
		</div>
		<div class="tab">
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>��ҵ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>ѧ��</th>
					<td>
						${rs.xh }
						<input type="hidden" name="xh" value="${rs.xh }"/>
					</td>
					<th>���֤��</th>
					<td>
						${rs.sfzh }
					</td>
				</tr>
				<tr>
					<th>����</th>
					<td>
						${rs.xm }
					</td>
					<th>�Ա�</th>
					<td>
						${rs.xb }
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xb" /></th>
					<td>
						${rs.xymc }
					</td>
					<th>רҵ</th>
					<td>
						${rs.zymc }
					</td>
				</tr>
				<tr>
					<th>�༶</th>
					<td>
						${rs.bjmc }
					</td>
					<th>
						ѧ��
					</th>
					<td>
						${rs.xlmc }
					</td>
				</tr>
				<tr>
					<th>ѧ��</th>
					<td>
						${rs.xz }
					</td>
					<th>�꼶</th>
					<td>
						${rs.nj }
					</td>
				</tr>
				<tr>
					<th>��ҵ���</th>
					<td>
						${rs.bynd }
					</td>
					<th>������ʽ</th>
					<td>${rs.pyfsmc }</td>
				</tr>
				<tr>
					<th>��ͥ��ַ</th>
					<td colspan="3">${rs.jtszd }</td>
				</tr>
				<tr>
					<th>��ϵ��ʽ</th>
					<td colspan="3">${rs.lxfs }</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>��ҵȥ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th><span>*</span>��ҵȥ��</th>
					<td>
						<html:select property="byqx" styleId="byqx" value="${rs.byqx}">
							<html:option value=""></html:option>
							<html:options collection="byqxList" labelProperty="mc" property="dm"></html:options>
						</html:select>
					</td>
					<th><span>*</span>��ϵ��ַ</th>
					<td>
						<html:text property="lxdz" styleId="lxdz" maxlength="100" value="${rs.lxdz}"></html:text>
					</td>
				</tr>
				<tr>
					<th><span>*</span>��������</th>
					<td>
						<html:text property="yb" styleId="yb" maxlength="6" value="${rs.yb}"></html:text>
					</td>
					<th>��������</th>
					<td>
						<html:text property="email" maxlength="100" value="${rs.email}"></html:text>
					</td>
				</tr>
				<tr>
					<th>��ϵ�绰</th>
					<td>
						<html:text property="lxdh" maxlength="20" value="${rs.lxdh}"></html:text>
					</td>
					<th>�ƶ��绰</th>
					<td>
						<html:text property="sjhm" maxlength="20" value="${rs.sjhm}"></html:text>
					</td>
				</tr>
				<tr>
					<th>��ҵ��λ</th>
					<td colspan="3">
						<html:text property="jydw" maxlength="200" styleId="jydw" value="${rs.jydw}"></html:text>
					</td>
				</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<logic:notEqual value="view" name="doType">
			          		<button id="buttonSave">����</button>
			          	</logic:notEqual>
			            <button id="buttonClose">�ر�</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
		</table>
		</div>
	</html:form>
	<logic:present name="message">
		<input type="hidden" id="message" value="${message }" />
		<script type="text/javascript">
			jQuery(function (){
				alertInfo(jQuery('#message').val(), function(){
					window.close();
					if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
						window.dialogArguments.document.getElementById('search_go').click();
					}
				});
			});
		</script>
	</logic:present>
</body>
</html>
