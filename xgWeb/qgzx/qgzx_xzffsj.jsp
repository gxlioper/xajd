<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">	
	//�ڹ���ѧ�ύ
	function searchCheck(mustFill){
		var eles = mustFill.split("-");
		var type = document.getElementById('type').value;
		var url = "cjff.do?method=fillMonth";
		if(type == "lsgzff"){
			url = "cjff.do?method=lsgzff";
		}
		
		for (i = 0; i < eles.length; i++) {
			if (document.getElementById(eles[i]).value == "") {
				alert("�뽫��\"*\"�ŵ���Ŀ����������");
				return false;
			}
		}
		if(checkXnNd("xn", "nd")){
			document.forms[0].action = url;
			document.forms[0].submit();
		}
	}
	</script>
</head>
	<body>
		<html:form action="/cjff" method="post">
			<input type="hidden" id="pk" name="pk" value="${pk}" />
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue}" />
			<input type="hidden" id="type" name="type" value="${type}" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ڹ���ѧ - ���ʷ��� - ѡ�񷢷�ʱ��</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" id="rsT" class="formlist" align="center">
				<thead>
					<tr>
						<th colspan="2">
							<span>����ʱ��</span>
						</th>
					</tr>
				</thead>	
				<tbody>
				<tr>
					<th><font color="red">*</font>ѧ��</th>
					<td>
						<html:select property="xn" styleId="xn">
						<html:options collection="ndList" property="xn" labelProperty="xn"/>
						</html:select>
					</td>
				</tr>			
				<tr>
					<th><font color="red">*</font>���</th>
					<td >
						<html:select property="nd" styleId="nd">
						<html:options collection="ndList" property="nd" labelProperty="nd"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>ѧ��</th>
					<td >
						<html:select property="xq" styleId="xq">
						<html:option value=""></html:option>
						<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th><font color="red">*</font>�·�</th>
					<td>
						<html:select property="yf" styleId="yf">
						<html:option value=""></html:option>
						<html:options collection="yfList" property="yf" labelProperty="yf"/>
						</html:select>
					</td>					
				</tr>	
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <button type="button" class="button2" onclick="searchCheck('nd-yf-xn-xq')">
							�� ��
						</button>
						<button type="button" class="button2" onclick="Close();return false;">
							�� ��
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
