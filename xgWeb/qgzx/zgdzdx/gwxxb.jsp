<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/String.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script language="javascript">	
	function saveGwxxInfo(url,obj){
		var value = obj.split("-"); 
	    for(var i=0; i<value.length; i++){
		 	if(document.getElementById(value[i]).value==""){
		 		alert("�뽫��\*�ŵ���Ŀ��д������");
		 		return false;
		 	}
	 	}	 
		refreshForm(url);
		BatAlert.showTips('���ڲ����У����Ե�...'); 
	}	
	</script>
</head>
	<body>		
		<html:form action="/whlggwgl" method="post">
		<html:hidden name="rs" property="sqsj" styleId="sqsj"/>			
		<input type="hidden" name="doType" id="doType" value="${doType}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>

		<logic:empty name="rs">
			<br />
			<br />
			<p align="center">
				�д�������
			</p>
		</logic:empty>

		<logic:notEmpty name="rs">	
			<div class="tab">	
			<table width="100%" id="rsT" class="formlist" border="1">
				<thead>
					<tr>
						<th colspan="4">
							<span>��λ��Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>��λ����</th>
					<td>
						<logic:equal value="add" name="doType">
							<html:text name="rs" property="gwmc" styleId="gwmc" onkeyup="value=value.replace('-','') "/>
						</logic:equal>
						<logic:equal value="modi" name="doType">
							<html:text name="rs" property="gwmc" styleId="gwmc" readonly="true"/>
						</logic:equal>							
					</td>
					<th><span class="red">*</span>��λ����</th>
					<td>
						<html:text name="rs" property="sqdwmc" styleId="sqdwmc"/>							
					</td>
				</tr>
				<tr>
					<th>��λ��ַ</th>
					<td colspan="3">
						<html:text name="rs" property="sqdwdz" style="width:100%" />
					</td>
				</tr>
				<tr>
					<th>ѧ��</th>
					<td>
						<html:text name="rs" property="xn" style="width: 90px" readonly="true" />
					</td>
					<th>���</th>
					<td>
						<html:text name="rs" property="nd" style="width: 90px"
							readonly="true" />							
					</td>
				</tr>
				<tr>
					<th>ѧ��</th>
					<td>
						<html:text name="rs" property="xq" style="width: 90px" readonly="true" />
					</td>
					<th>��ϵ�绰</th>
					<td>
						<html:text name="rs" property="lxdh"/>
					</td>
				</tr>
				<tr>
					<th>������ʼ����</th>
					<td>
						<html:text name='rs' property="gzkssj" styleId="gzkssj"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('gzkssj','y-mm-dd');" readonly="true"/>
					</td>
					<th><span class="red">*</span>������������</th>
					<td>
						<html:text name='rs' property="gzjssj" styleId="gzjssj"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('gzjssj','y-mm-dd');" readonly="true"/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>��������</th>
					<td>
						<html:text name="rs" property="xyrs" styleId="xyrs" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<th><span class="red">*</span>ʹ����������</th>
					<td>
						<html:text name="rs" property="xyknsrs" styleId="xyknsrs" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>�Ƴ귽ʽ</th>
					<td>
						<html:select name="rs" property="jcfs" onchange="subloadPost()" styleId="jcfs">
							<html:option value="">------��ѡ��------</html:option>
							<html:option value="h">��Сʱ</html:option>
							<html:option value="d">����</html:option>
							<html:option value="w">����</html:option>
							<html:option value="m">����</html:option>
						</html:select>
					</td>
					<th><span class="red">*</span>���鱨���׼</th>
					<td>
						<html:text name="rs" property="jcbz" styleId="jybcbz" onkeyup="value=value.replace(/[^\d]/g,'') "/>
						<span id="jybcbzDw"></span>
					</td>
				</tr>			
				<tr>
				  <th>����ʱ��</th>
				  <td colspan="3">
				  	<html:textarea name="rs" property="gzsj" style="width:100%" styleId="gzsj"/>
				  </td>
			  </tr>
				<tr>
				  <th>��������</th>
				  <td colspan="3">
				  	<html:textarea name="rs" property="gznr" style="width:100%" styleId="gznr"/>
				  </td>
			  </tr>		
			  <tr>
				  <th>����Ҫ��</th>
				  <td colspan="3">
				  	<html:textarea name="rs" property="gzyq" style="width:100%" styleId="gzyq"/>
				  </td>
			  </tr>			
			  <tr>
					<th>��ע</th>
					<td colspan="3">
						<html:textarea name="rs" property="bz" style="width:100%"
							rows="5"></html:textarea>
					</td>
			  </tr>
			  </tbody>
			  <tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
		          <div class="btn">
		            <button type="button" class="button2"
						onclick="saveGwxxInfo('qgzxZgdzdx.do?method=saveGwxx','gwmc-sqdwmc-xyrs-xyknsrs-gzjssj-jcfs-jcbz')"
						style="width:80px" id="buttonSave">
						�� ��
					</button>
					<button type="button" class="button2" onclick="expAppTab('rsT','�ڹ���ѧ��λ������Ϣ','')">
						��ӡ����
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
			</table>
		  </div>
		</logic:notEmpty>

		<logic:present name="result">
		<logic:equal value="true" name="result">
			<script>
				alert("�����ɹ���");			
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script>
				alert("����ʧ�ܣ�");		
			</script>
		</logic:equal>
		</logic:present>
		</html:form>
	</body>
</html>
