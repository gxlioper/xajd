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
				saveData('njjs_jygl.do?method=xssxUpdate&doType=save','sxjyfs-sxdw-sxdwxz');
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
	</style>

</head>
<body>
	<html:form action="/njjs_jygl" method="post">
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>
		<input type="hidden" name="sfcz" value="${sfcz }"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ѧ��ʵϰ¼��</a>
			</p>
		</div>
		<div class="tab">
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>ѧ����Ϣ</span>
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
					<th>����</th>
					<td>
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th>�꼶</th>
					<td>
						${rs.nj }
					</td>
					<th><bean:message key="lable.xb" /></th>
					<td>
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<th>רҵ</th>
					<td>
						${rs.zymc }
					</td>
					<th>�༶</th>
					<td>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
					<th>��ͥ��ַ</th>
					<td colspan="3">
						<logic:empty name="rs" property="jtszd">
							<input type="text" id="jtdz" name="jtdz" value="" maxlength="25" style="width:80%" />
						</logic:empty>
						<logic:notEmpty name="rs" property="jtszd">
							${rs.jtszd }
						</logic:notEmpty>
					</td>
				</tr>
				<tr>
					<th>��ϵ��ʽ</th>
					<td colspan="3">
						<logic:empty name="rs" property="lxfs">
							<input type="text" id="lxfs" name="lxfs" value="" maxlength="25" style="width:80%" />
						</logic:empty>
						<logic:notEmpty name="rs" property="lxfs">
							${rs.lxfs }
						</logic:notEmpty>
					</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>ʵϰ��Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th><span>*</span>ʵϰ��ҵ��ʽ</th>
					<td>
						<html:text property="sxjyfs" maxlength="10" value="${rs.sxjyfs}"></html:text>
					</td>
					<th><span>*</span>ʵϰ��λ</th>
					<td>
						<html:text property="sxdw" styleId="sxdw" maxlength="100" value="${rs.sxdw}"></html:text>
					</td>
				</tr>
				<tr>
					<th><span>*</span>ʵϰ��λ����</th>
					<td>
						<html:text property="sxdwxz" styleId="sxdwxz" maxlength="100" value="${rs.sxdwxz}"></html:text>
					</td>
					<th>ʵϰ��λ��ַ</th>
					<td>
						<html:text property="sxdwdz" maxlength="100" value="${rs.sxdwdz}"></html:text>
					</td>
				</tr>
				<tr>
					<th>ʵϰ����</th>
					<td>
						<html:text property="sxbm" maxlength="20" value="${rs.sxbm }"></html:text>
					</td>
					<th>ʵϰ��λ</th>
					<td>
						<html:text property="sxgw" maxlength="20" value="${rs.sxgw}"></html:text>
					</td>
				</tr>
				<tr>
					<th>�Ͷ�����</th>
					<td>
						<html:text property="ldbh" maxlength="50" value="${rs.ldbh}"></html:text>
					</td>
					<th>ʳ������</th>
					<td>
						<html:text property="sstj" maxlength="25" value="${rs.sstj}"></html:text>
					</td>
				</tr>
				<tr>
					<th>��ͨ״��</th>
					<td>
						<html:text property="jtzk" maxlength="25" value="${rs.jtzk}"></html:text>
					</td>
					<th>�ֻ�����</th>
					<td>
						<html:text property="sjhm" maxlength="20" value="${rs.sjhm}"></html:text>
					</td>
				</tr>
				<tr>
					<th>��ϵ�绰</th>
					<td>
						<html:text property="lxdh" maxlength="20" value="${rs.lxdh}"></html:text>
					</td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th>ʵϰ��λ�䶯���<br/><span>����������300��</span></th>
					<td colspan="3">
						<html:textarea property="sxdwbdqk" value="${rs.sxdwbdqk}" onblur="chLeng(this,300);"
							cols="4" rows="3" style="width:87%;"></html:textarea>
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
