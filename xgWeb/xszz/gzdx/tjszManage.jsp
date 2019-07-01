<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
<script language="javascript" src="js/xgutil.js"></script>
<script language="javascript" src="js/xszz/xszzFunction.js"></script>
<script language="javascript">
//�ı�����
function chTj(){
	//�����ֶ�
	var tjzd = $("tjzd").value;
	
	dwr.engine.setAsync(false);
	if(tjzd != ""){
		var tableName = "xszz_tjb";
		var colList = ["tsgs"];
		var pk = "tjdm";
		var pkValue = tjzd;
		var query = "";
		getOtherData.getTableInfo(tableName,colList,pk,pkValue,query,function(data){
			if(data != null){
				var tsgs = data[0];
				
				if(tsgs == "%"){
					$("tjlx").value = "";
					$("tjlx").disabled = false;
					$("lrk").value = "";
					$("lrk").style.display = "";
					$("lrk").disabled = false;
					$("xzk").disabled = true;
					$("xzk").style.display = "none";		
					$("bfh").style.display = "";
				}else if(tsgs == "sf"){
					$("tjlx").value = "=";
					$("tjlx").disabled = true;
					$("xzk").style.display = "";
					$("xzk").disabled = false;
					$("lrk").disabled = true;
					$("lrk").style.display = "none";
					$("bfh").style.display = "none";
				}else{
					$("tjlx").value = "";
					$("tjlx").disabled = false;
					$("lrk").value = "";
					$("lrk").style.display = "";
					$("lrk").disabled = false;
					$("xzk").disabled = true;
					$("xzk").style.display = "none";
					$("bfh").style.display = "none";
				}
			}	
		});
	}
	dwr.engine.setAsync(true);
}

//��������
function saveTj(){
	var xmdm = $("xmdm").value;
	var tjzd = $("tjzd").value;
	var tjlx = $("tjlx").value;
	var lrk = $("lrk").value;
	var xzk = $("xzk").value;
	
	if(xmdm == ""){
		alert("��ѡ����Ҫ������������Ŀ��");
		return false;
	}
	
	if(tjzd == "" || tjlx == "" || (lrk == "" && xzk == "")){
		alert("������Ϣ����������ȷ����");
		return false;
	}
	
	saveUpdate('/xgxt/gzdxXszz.do?method=tjszManage&doType=save','');
}
</script>
	<body onload="chTj()">
		<html:form action="/commXszz">
			<!-- ������ -->
			<%@ include file="/xszz/hiddenValue.jsp"%>
			<!-- ������ end-->
			<!-- ���� -->
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�<bean:write name="title" />
				</div>
			</div>
			<!-- ���� end-->
			<div class="rightcontent">
				<!-- �������� -->
				<fieldset>
					<legend>
						�� ѯ
					</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									&nbsp;&nbsp;ѧ�꣺
									<html:select property="queryequals_xn" style="width:120px" styleId="xn" disabled="true">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;������Ŀ��
									<html:select property="queryequals_xmdm" style="" styleId="xmdm">
										<html:options collection="xmList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:25px" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/gzdxXszz.do?method=tjszManage');return false;">
										��ѯ
									</button>
									<br>
									<button class="button2" style="height:25px" id="cz"
										onclick="czSearchCond('xmdm-tjzd-tjlx-lrk-xzk');return false;">
										����
									</button>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;&nbsp;����������
									<html:select property="tjzd" style="" styleId="tjzd" onchange="chTj()">
										<html:options collection="tjList" property="dm" labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;
									<html:select property="tjlx" style="" styleId="tjlx">
										<html:options collection="tjlxList" property="en" labelProperty="cn" />
									</html:select>
									&nbsp;&nbsp;
									<html:text property="tjz" styleId="lrk"
										onkeypress="return onlyNum(this,5)" 
										maxlength="5" style="width:5%;ime-mode:disabled"/>
										
									<html:select property="tjz" style="display:none" styleId="xzk">
										<html:option value="������">������</html:option>
										<html:option value="��">��</html:option>
									</html:select>
										
									<span id="bfh" style="display:none">%</span>
										
									<logic:equal value="yes" name="writeAble">
									&nbsp;&nbsp;
									<button class="button2" onclick="saveTj()">
										���
									</button>
									</logic:equal>
								</td>
							</tr>
						</thead>
					</table>
				</fieldset>
				<!-- �������� end-->
				<!-- ��ѯ�޽�� -->
				<logic:empty name="rs">
					<p align="center">
						δ�ҵ��κμ�¼��
					</p>
				</logic:empty>
				<!-- ��ѯ�޽�� end-->
				<!-- ��ѯ��� -->
				<logic:notEmpty name="rs">
					<fieldset>
						<legend>
							��¼����
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">��ʾ��������ͷ��������.</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<!-- ��ͷ -->
							<thead>	
								<tr align="center" style="cursor:hand">
									<td width="5%">
										<input type="checkbox" id="selall" name="selall" onclick="selAll()">
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" 
									style="cursor:hand" 
									ondblclick="">
									<!-- checkbox -->								
									<logic:iterate id="v" name="s" offset="0" length="1">
										<td align="center">
											<input type="checkbox" id="checkVal"  name="primarykey_checkVal"  value="<bean:write name="v"/>">
										</td>
									</logic:iterate>
									<!-- ��Ŀ��Ϣ -->		
									<logic:iterate id="v" name="s" offset="1">
										<td align="left">
											<bean:write name="v" />
										</td>
									</logic:iterate>	
								</tr>
							</logic:iterate>
						</table>
						<!--���� end -->
						<!-- ��ѯ��� end-->
						<!-- ��ҳ -->
						<TABLE width="100%" id="Table" class="tbstyle">
							<TR>
								<TD height=3></TD>
							</TR>
							<TR>
								<TD>
									<jsp:include flush="true"
										page="/sjcz/turnpage.jsp?form=xszzTyForm"></jsp:include>
								</TD>
							</TR>
							<TR>
								<TD height=3></TD>
							</TR>
						</TABLE>
						<!-- ��ҳ end-->
					</fieldset>
				</logic:notEmpty>
				<!-- ��ѯ���end -->
				<!-- ������ť -->
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal name="writeAble" value="yes">
							<button class="button2"
								onclick="sumitInfo('/xgxt/gzdxXszz.do?method=tjszManage','del')"
								style="width:80px">
								ɾ&nbsp;&nbsp;��
							</button>
						</logic:equal>
					</div>
				</center>
			<!-- ������ť end-->
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/xszz/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>