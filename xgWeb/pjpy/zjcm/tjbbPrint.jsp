<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript" src="js/sztzFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSzPjpyDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cpzZjcmDAO.js'></script>
	<script type="text/javascript">	
		function printBb(){
			var xn = $("xn").value;
			var xq = $("xq").value;
			var bblx = $("bblx").value;
			var xydm = $("xydm").value;
			var bjdm = $("bjdm").value;
			if(bblx == ""){
				alert("��ȷ���������ͣ���");
				return false;
			}
			if(xn == "" || xq == ""){
				alert("����ѧ����ѧ�ڲ���Ϊ�գ���");
				return false;
			}
			if(bblx == "xyzh"){
				if(bjdm == ""){
					alert("��ȷ���༶���ƣ���");
					return false;
				}
			}
			
			if( bblx == "pjmd"){
				if(xydm == ""){
					alert("��ȷ��<bean:message key="lable.xsgzyxpzxy" />���ƣ���");
					return false;
				}
			}
			var url = "/xgxt/zjcm_tjsz.do?method=tjbbPrint&doType=print";
			
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
			//window.open(url);
		}
	</script>
	</head>
	<body onload="">
		<html:form action="/zjcm_cpz">
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="ndV" id="ndV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<div class="tab">
			<table class="formlist" border="0" id="rsTable" align="center" style="width: 50%">
				<thead>
					<tr>
						<th colspan="2">
							<span>ͳ�Ʊ���</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr style="height: 23px">
					<th align="right" width="25%">
						����ѧ��
					</th>
					<td align="left" width="">
						<html:select property="xn" style="" styleClass="select"styleId="xn">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right" width="">
						����ѧ��
					</th>
					<td align="left" width="">
						<html:select property="xq" style="" styleClass="select"styleId="xq">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right" width="">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<html:select property="xydm" onchange="initZyList();initBjList()"
						styleClass="select" style="" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right">
					 	רҵ
					</th>
					<td>
						<html:select property="zydm" style="" styleId="zy"
							onchange="initBjList()">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right" width="">
						�༶
					</th>
					<td align="left">
						<html:select property="bjdm" style="" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right">
					 	��������
					</th>
					<td>
						<html:select property="bblx" style="">
							<html:option value=""></html:option>
							<html:option value="xyzh"><bean:message key="lable.xsgzyxpzxy" />�ۺ����ʲ�����</html:option>
							<html:option value="pjmd">���������������ܱ�</html:option>
						</html:select>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="2">
			          <div class="btn">
			          	<button id="buttonSave" onclick="printBb()">
							��ӡ
						</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
			<logic:present name="msg">
				<script>
					alert($("msg").value);
				</script>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
