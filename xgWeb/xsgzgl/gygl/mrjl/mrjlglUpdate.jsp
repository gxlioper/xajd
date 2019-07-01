<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/check.js"></script>
	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
	<script	type="text/javascript">
		jQuery(function(){
			// ���水ť
			jQuery('#buttonSave').click(function(){
				saveData('gyglnew_mrjlgl.do?method=mrjlglUpdate&doType=save','sj-lddm-zbrydm');
			});
			
			// �رհ�ť
			jQuery('#buttonClose').click(function(){
				window.close();
			});	
			
			jQuery("textarea").blur(function(){
				chLeng(this, 1000);
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
		textarea {
			width:90%;
			word-break:break-all;
		}
	</style>

</head>
<body>
	<html:form action="/gyglnew_mrjlgl" method="post">
		<input type="hidden" name="pkValue" value="${param.pkValue }"/>

		<div class="tab">
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="4">
						<span>ÿ�ռ�¼��Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th><span>*</span>ʱ��</th>
					<td>
						<html:text property="sj" styleId="sj" readonly="true" maxlength="30" onkeydown="onlyBackSpace(this,event);" onblur="dateFormatChg(this)"
						onclick="return showCalendar('sj','y-mm-dd');"></html:text>
					</td>
					<th><span>*</span>¥��</th>
					<td>
						<html:select property="lddm" styleId="lddm">
							<html:option value=""></html:option>
							<html:options collection="ldList" property="lddm" labelProperty="ldmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th><span>*</span>ֵ����Ա</th>
					<td>
						<html:select property="zbrydm" styleId="zbrydm">
							<html:option value=""></html:option>
							<html:options collection="zbryList" property="zbrydm" labelProperty="zbrymc" />
						</html:select>
					</td>
					<th>����</th>
					<td>
						<html:text property="tq" styleId="tq" maxlength="30"></html:text>
					</td>
				</tr>
				<tr>
					<th>ֵ���¼<br/>
						<span>(��������1000)</span>
					</th>
					<td colspan="3">
						<html:textarea property="zbjl" cols="5" rows="12" styleId="zbjl"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>ͻ���¼�������<br/>
						<span>(��������1000)</span>
					</th>
					<td colspan="3">
						<html:textarea property="tfsjjcl" cols="5" rows="12" styleId="tfsjjcl"></html:textarea>
					</td>
				</tr>
			</tbody>
			<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<logic:notEqual value="view" name="doType">
			          		<button type="button" id="buttonSave">����</button>
			          	</logic:notEqual>
			            <button type="button" id="buttonClose">�ر�</button>
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
