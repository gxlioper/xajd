<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script type="text/javascript">
			function tj(url){
				if($('nj').value == ""){
					alert('��ѡ���꼶��');	
					return false;
				}

				if($('xn').value == ""){
					alert('��ѡ��ѧ�꣡');
					return false;
				}

				if($('xq').value == ""){
					alert('��ѡ��ѧ�ڣ�');
					return false;
				}

				if($('xy').value == ""){
					alert("��ѡ��"+jQuery("#xbmc").val()+"��");
					return false;
				}

				if($('zy').value == ""){
					alert('��ѡ��רҵ��');
					return false;
				}

				if($('bj').value == ""){
					alert ('��ѡ��༶��');
					return false;
				}

				document.forms[0].action = '/xgxt/commWsjc.do?method=xsfsTj&doType=print&bjmc=' + selText('bj');
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
<%--				refreshForm('/xgxt/commWsjc.do?method=xsfsTj&doType=print');--%>
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ͳ������ѡ��</a>
			</p>
		</div>
		<html:form action="/commWsjc" method="post">
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<table class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>������Ϣ</span></th>
					</tr>
				</thead>
				<tr>
					<th>�꼶</th>
					<td>
						<html:select property="nj" onchange="initZyList();initBjList()" styleId="nj">
							<html:options collection="njList" labelProperty="nj" property="nj"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>ѧ��</th>
					<td>
						<html:select property="xn" styleId="xn">
							<html:options collection="xnList" labelProperty="xn" property="xn"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>ѧ��</th>
					<td>
						<html:select property="xq" styleId="xq">
							<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td colspan="2">
						<html:select property="xydm" style="width: 200px" styleId="xy" onchange="initZyList();initBjList();" onmouseover="">
						<html:option value=""></html:option>
						<html:options collection="xyList" property="xydm" labelProperty="xymc" />
					</html:select></td>
		       	</tr> 	
         		<tr>
         			<th>רҵ</th>
					<td>
						<html:select property="zydm" style="width: 200px" styleId="zy" onchange="initBjList()" onmouseover="">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr>
         			<th>�༶</th>
         			<td>
         				<html:select property="bjdm" style="width: 200px" styleId="bj" onmouseover="">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
         		</tr>
         		<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz"><span class="red">����ѡ���Ϊ�����</span></div>
			          <div class="btn">
						  <button type="button" name="�ύ" onclick="tj();return false;">�ύ</button>	
						  <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
		</html:form>
	</body>
</html>