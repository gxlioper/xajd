<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript"  src="js/pjpy/pjpyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getPjpyInfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getWjdcDAO.js'></script>
		<script language="javascript"  src="js/wjdc/wjdc.js"></script>
		<script language="javascript"  src="js/wjdc/wjdcMk.js"></script>
		<script language="javascript">
		function getFpbj(){
			var tableName = "view_wjdc_wjfp"; 
			var dm = "bjdm"; 
			var mc = "bjmc";
			var msg = "";
			var pk = "id";
			var pkValue = $("id").value;
			var obId = "bjR";
			if(pkValue == ""){
				pk = "";
			}
				
			getPjpyInfo.getPjpyList(tableName, dm, mc, msg, pk, pkValue,function(data){
				if(data!=null){
					DWRUtil.removeAllOptions(obId);
					DWRUtil.addOptions(obId,data,"dm","mc");
					$(obId).options[0] = null;
				}
			});
		}
		</script>
	</head>
	<body onload="getWjList()">
		<html:form action="/wjdc">
			<!-- ������ -->
			<%@ include file="/wjdc/hiddenValue.jsp"%>
			<!-- ������ end-->
			
			
			
			<div class="tab">
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="saveWjfp()">
										�� ��
									</button>
									<button d="buttonClose" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									�ʾ����
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
				<tr>
					<th width="14%">
						�꼶
					</th>
					<td width="35%">
						<html:select property="nj" style="" onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj" />
						</html:select>
					</td>
					<th width="14%">
						ѧ��
					</th>
					<td width="35%">
						<html:hidden property="xn"/>
						<html:select property="xn" style="width:120px" styleId="xn" disabled="true">
							<html:options collection="xnList" property="xn" labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td>
						<html:select property="xydm" style="" styleId="xy" onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>		
					</td>
					<th>
						���
					</th>
					<td>
						<html:hidden property="nd"/>
						<html:select property="nd" style="" styleId="nd" disabled="true">
							<html:options collection="ndList" property="nd" labelProperty="nd" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						רҵ
					</th>
					<td>
						<html:select property="zydm" style="" styleId="zy" onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
					<th>
						ѧ��
					</th>
					<td>
						<html:hidden property="xq"/>
						<html:select property="xq" style="" styleId="xq" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>�ʾ�
					</th>
					<td>
						<html:select property="id" style="" styleId="id" onchange="getFpbj()">
							<html:options collection="wjList" property="dm" labelProperty="mc" />
						</html:select>
					</td>
					<th>
						ģ������
					</th>
					<td>
						<html:select property="queryequals_mklx" style="" styleId="mklx" disabled="true">
							<html:option value=""></html:option>
							<html:options collection="mklxList" property="en" labelProperty="cn" />
						</html:select>
					</td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<td colspan="4">
						<span>
							<font color="blue">��ʾ����ѡ��༶�Ļ������Ը�����ѡ����������������;��Shift����Ctrl���Խ�������ѡ��༶.</font>
						</span>
					</td>
				</tr>
			</thead>
			<tbody>	
				<tr>
					<td colspan="4">
						<table width="100%">
							<tr>
								<td width="7%" align="right">
									��<br/><br/><br/>��
								</td>
								<td width="42%">
									<html:select property="bjdm" style="width:100% " multiple="multiple"
										styleId="bj" size="12" ondblclick="" onmouseover="">
									</html:select>
								</td>
								<td width="2%">
									<button id="addBj" onclick="addAllRightFrame('bj','bjR')" class="btn_01">
										&gt;&nbsp;&gt;
									</button>
									<br />
									<br />
									<br />
									<button class="btn_01" id="delBj" onclick="addAllLeftFrame('bj','bjR')">
										&lt;&nbsp;&lt;
									</button>
								</td>
								<td width="7%" align="right">
									��<br/>��<br/>��<br/>��
								</td>
								<td width="42%">
									<html:select property="fpbj" style="width:100%" styleId="bjR" multiple="multiple"
										size="12" ondblclick="" onmouseover="">
									</html:select>		
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
		</div>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
		</html:form>
	</body>
</html>
