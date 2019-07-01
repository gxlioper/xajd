<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/pjpy/pjpyFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script language="javascript" src="js/wjdc/wjdcMk.js"></script>
		<script language="javascript" src="js/wjdc/wjdc.js"></script>
		<script type="text/javascript">	
		function saveSt(){
			var stlx = $("stlx").value;
			var mklx = $("mklx").value;
			var url = "/xgxt/wjdc.do?method=";
			
			if( stlx == "questions"){//�ʴ���
				saveUpdate('/xgxt/wjdc.do?method=stSave&doType=save','stmc-stlx');
			}else{//ѡ����
				saveXzt();
			}
		}
		
		//����ѡ����
		function saveXzt(){
		
			var the_tab = "";
			var stlx = $("stlx").value;
			var stbh = $("stbh").value;
			var stmc = $("stmc").value;
			//oneChoose : ��ѡ��
			//allChoose ����ѡ��
			if(stlx == "oneChoose"){
				the_tab = "one_flag";
			}else if(stlx == "allChoose"){
				the_tab = "all_flag";
			}else if(stlx == ""){
				alert("��ȷ����������!");
				return false;
			}
			
			if(stmc == ""){
				alert("��ȷ����������!");
				return false;
			}
			
			var tabobj = document.getElementById(the_tab);
			var rowLen = tabobj.rows.length;
			var nullCout = 0;
			
			if(rowLen == 0){
				alert("����Ŀ����Ϊ�գ���ȷ�ϣ�");
				return false;
			}
			for(var i=1;i<=rowLen;i++){
				if($("damc"+stlx+i)){
					if($("damc"+stlx+i).value == ""){
						alert("��"+i+"�д�����Ϊ�գ���ȷ��");
						return false;
					}
				}
			}
			saveUpdate('/xgxt/wjdc.do?method=stSave&doType=save','stmc-stlx');
		}
	</script>
	</head>
	<body onload="onShow()">
		<html:form action="/wjdc">
			<!-- ������ -->
			<html:hidden name="rs" property="mklx" />
			<html:hidden name="rs" property="jlsj" />
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
									<logic:notEqual name="doType" value="view">
										<button id="buttonSave" onclick="saveSt()">
											�� ��
										</button>
									</logic:notEqual>
									<button onclick="window.close();return false;" id="buttonClose">
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
									������Ϣ
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th width="15%">
							������
						</th>
						<td width="35%">
							<html:hidden name="rs" property="stbh" />
							${rs.stbh }
						</td>
						<th width="15%">
							<font color="red">*</font>��������
						</th>
						<td>
							<html:select name="rs" property="stlx" style="width:120px"
								styleId="stlx" onchange="showStDiv()">
								<html:options collection="stlxList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							��������
						</th>
						<td>
							<html:select name="rs" property="stss" style="width:120px"
								styleId="stss" onchange="">
								<html:options collection="stssList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th>
							ģ������
						</th>
						<td>
							<html:select name="rs" property="mklx" style="" styleId="mklx"
								disabled="true">
								<html:option value=""></html:option>
								<html:options collection="mklxList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							<font color="red">*</font>��Ŀ
							<br/>
							<font color="red">(��500��)</font>
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="stmc" rows="5" styleId="stmc"
								onblur="chLeng(this,500)" style="width: 90%" />
						</td>
					</tr>
					<tr>
						<th>
							��ע
							<br/>
							<font color="red">(��500��)</font>
						</th>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="bz" rows="5" styleId="bz"
								onblur="chLeng(this,500)" style="width: 90%" />
						</td>
					</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4">
								<span>
									�������ݣ���ѡ���������ͣ�
								</span>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<div id="oneChoose" style="display : none">
									<%@ include file="stxx/stOneChoose.jsp"%>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="allChoose" style="display : none">
									<%@ include file="stxx/stAllChoose.jsp"%>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div id="questions" style="display : none">
									<%@ include file="stxx/stQueChoose.jsp"%>
								</div>
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
