<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="/xgxt/dwr/interface/cqkjFunc.js"></script>
	<script type="text/javascript">
		function chk(tmp){
		var i=tmp;
			if($('gwdmgwsbsj')){
				if ($("gwdmgwsbsj").selectedIndex <= 0) {
					alert("��ѡ���λ��");
					return false;
				}
			}
			
			if($('xh'+i)){
				if ($('xh'+i).value == "") {
					alert("����дѧ�ţ�");
					return false;
				}
			}
			
			if($('lxdh'+i)){
				if ($("lxdh"+i).value == "") {
					alert("����д��ϵ��ʽ��");
					return false;
				}
			}
			return true;
		}
		
		var n=0;
		function add(str){
			var res="";
			var tmp = str.split("-");
			var table = tmp[0];
			var xh = $(tmp[1]).value;
			var lxdh = $(tmp[4]).value;
			var gw = $('gwdmgwsbsj').options[$('gwdmgwsbsj').selectedIndex].value;
			var gwdm = gw.split("-")[0];
			var gwsbsj = gw.split("-")[1];
			var userName = $('userName').value;
			var cz=""
			if($("ct").checked){
				cz = $('ct').value;
			}else{
				cz = $('hg').value;
			}
			
			dwr.engine.setAsync(false);
			getOtherData.insertYrdwghxslsb(xh,gwdm,gwsbsj,lxdh,cz,userName,table,function(data){
				if(data!=null && (data == "true" || data==true)){
					res = true;
				}else{
					res = false;
					
				}
				if(res==false){
					alert(data);
					return false;
				}
				n++;
				var _row = $(table).insertRow();
				for(i = 1;i<tmp.length;i++){
					_row.insertCell().innerHTML = '<input type="hidden" id="'+table+''+n+''+i+'" value="'+document.getElementById(tmp[i]).value+'"/>'+document.getElementById(tmp[i]).value;
				}
				i=i-4;
				_row.insertCell().innerHTML = '<button type="button" class="button2" onclick="deleteRow(this,'+table+''+n+''+i+'.value)">-</button>';
			});		
			dwr.engine.setAsync(true);
		}
		function deleteRow(obj,num){
			var xh = num;
			var gw = $('gwdmgwsbsj').options[$('gwdmgwsbsj').selectedIndex].value;
			var gwdm = gw.split("-")[0];
			var gwsbsj = gw.split("-")[1];
			
 			dwr.engine.setAsync(false);
 			getOtherData.deleteYrdwghxslsb(xh,gwdm,gwsbsj,function(data){
 				if(data!=null){
 					res = data;
 				}
 			});
 			dwr.engine.setAsync(true);
 			if(!res){
				alert(res);
				return false;
			}
 			//alert(obj.parentElement.parentElement.parentElement.parentElement.id);
 			//alert(obj.parentElement.parentElement.rowIndex);
 			obj.parentElement.parentElement.parentElement.deleteRow(obj.parentElement.parentElement.rowIndex);
		}
	</script>
</head>
	<body>
		<html:form action="/data_search" method="post">
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
		<input type="hidden" name="userName" id="userName" value="${rs.userName}"/>
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
			<logic:present name="insert" >
				<script>
   				alert("��ѡѧ�������ظ�����Ч!");
   				</script>
			</logic:present>
			<input type="hidden" id="url" name="url" value="/qgzxYrdwGhXsGwsq.do" />
			
			<div class="tab">
			<table width="100%" id="rsT" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>��д�����</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>��λ����</th>
					<td>
						<html:select name="rs" property="gwdmgwsbsj" styleId="xmdm"
							style="width:150px">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdmgwsbsj"
								labelProperty="gwdm" />
						</html:select>
					</td>
					<th>���˵�λ������</th>
					<td>
						<bean:write name='rs' property="lxr" />
					</td>
				</tr>
				<tr>
					<th>��ϵ�绰</th>
					<td>
						<bean:write name="rs" property="lxdh"/>
					</td>
					<th>��λ�ص�</th>
					<td width="25%">
						<bean:write name="rs" property="yrdwdz"/>
					</td>
				</tr>
				<tr>
					<th colspan="4">
						���˵�λ�������Ա��ϸ��Ϣ:(<input type="radio" name="cz" id="ct" value="����" checked="checked"/>����  <input type="radio" name="cz" id="hg" value="����"/>���� )
					</th>
				</tr>
				<tr>
					<td colspan="4">
					<table width="800px" class="formlist">
						<tr>
							<td style="width: 200px">ѧ��:<br/><input type="text" id="xh1" name="xh1" value="" /></td>
							<td style="width: 200px">����:<br/><input type="text" id="xm1" name="xm1" value="" /></td>
							<td style="width: 200px"><bean:message key="lable.xsgzyxpzxy" />:<br/><input type="text" id="xymc1" name="xymc1" value="" /></td>
							<td style="width: 200px">��ϵ��ʽ:<br/><input type="text" id="lxdh1" name="lxdh1" value="" />
							<td>
							<button type="button" class="button2" onclick="if(chk(1)){add('table1-xh1-xm1-xymc1-lxdh1')}">+</button>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" >
						<table border="1" width="800px" id="table1" class="formlist">
							<colgroup>
								<col style="width: 200px"></col>
								<col style="width: 200px"></col>
								<col style="width: 200px"></col>
								<col style="width: 200px"></col>
								<col></col>
							</colgroup>
						</table>
					</td>
				</tr>
				<tr>
					<th colspan="4">���˵�λ��Ƹ����Ա����ϸ��Ϣ</th>
				</tr>
				<tr>
					<td colspan="4">
					<table width="800px" class="formlist">
						<tr>
						<td style="width: 200px">ѧ��:<br/><input type="text" id="xh2" name="xh2" value=""/></td>
						<td style="width: 200px">����:<br/><input type="text" id="xm2" name="xm2" value=""/></td>
						<td style="width: 200px"><bean:message key="lable.xsgzyxpzxy" />:<br/><input type="text" id="xymc2" name="xymc2" value=""/></td>
						<td style="width: 200px">��ϵ��ʽ:<br/><input type="text" id="lxdh2" name="lxdh2" value=""/>
						<td>
						<button type="button" class="button2" onclick="if(chk(2)){add('table2-xh2-xm2-xymc2-lxdh2')}">+</button>
						</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="4" >
						<table border="1" width="800px" id="table2" class="formlist">
							<colgroup>
								<col style="width: 200px"></col>
								<col style="width: 200px"></col>
								<col style="width: 200px"></col>
								<col style="width: 200px"></col>
								<col></col>
							</colgroup>
						</table>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>������Ա����</th>
					<td colspan="3">
						<html:textarea name='rs' property='sqly' styleId="bz"
							style="width:99%" rows='5' />
					</td>
				</tr>
				<tr>
					<th>��λ����Ҫ��</th>
					<td colspan="3">
						<html:textarea name='rs' property='bz' styleId="bz"
							style="width:99%" rows='5' />
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" class="formlist">
							<tr>
								<td width="20">��<br/>��<br/>��<br/>λ<br/>��<br/>��<br/>��<br/>ʦ<br/>��<br/>��</td>
								<td valign="bottom">ǩ�����£�<br/><br/>���ڣ�</td>
								<td width="20">��<br/>��<br/>��<br/>ѧ<br/>��<br/>��<br/>��<br/>��<br/>��</td>
								<td valign="bottom">ǩ�����£�<br/><br/>���ڣ�</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<th>��ע</th>
					<td colspan="3">
						�����˵�λ�������ø�λ���ڼ�¼�����ڴ���6��ǰ�ɵ�λ����
						�ڱ���𷢷ű����ڹ���ѧ�칫�ң��·�ѧ���������106�ң����������������
						����ѧ�칫����ϵ����ϵ�ˣ����������绰67883311��
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <button type="button" class="button2" onclick="zgdzdx_yrdwghxssq_chkisDataExist();">
							�� �� �� ��
						</button>
						<button type="button" class="button2" onclick="expAppTab('rsT','���˵�λ���ӣ�������ѧ����λ�����','')">
							�� ӡ Ԥ ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>
