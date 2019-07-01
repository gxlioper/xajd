<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript">
		//ͳ�Ʊ�����Ϣ
		function bxtj(){
			var tjfs = $("tjfs").value;
			var tjfw = $("tjfw").value;
			
			if(tjfs != "" && tjfw != ""){
				allNotEmpThenGo('/xgxt/zjcmGygl.do?method=bxtjManage');
			}else{
				alert("��ȷ��ͳ�Ʒ�Χ�Լ�ͳ�Ʒ�ʽ��");
			}
		}
		
		//���excel
		function expInfo(){
			var tjfs = $("tjfs").value;
			var tjfw = $("tjfw").value;
		
			if(tjfs != "" && tjfw != ""){
				wjcfDataExport('/xgxt/zjcmGygl.do?method=bxtjManage&doType=exp');
			}else{
				alert("��ȷ��ͳ�Ʒ�Χ�Լ�ͳ�Ʒ�ʽ��");
			}
		}
		//�޸�ͳ�Ʒ�ʽ
		function chTjfs(){
		
			dwr.engine.setAsync(false);
			
			var id = "tjfw";
			var tjfs = $("tjfs").value;
			var fw = $("fw").value;
			
			if(tjfs != ""){	
				
				getGyglDAO.getSelectList(tjfs,function(data) {
					
					DWRUtil.removeAllOptions(id);
					DWRUtil.addOptions(id,data,"en","cn");
					$(id).value = "";
				});
				
			}else{
				DWRUtil.removeAllOptions(id);
			}
			
			if(tjfs == "bxtjfw_cl"){
				$("clId").style.display = "";
			}else{
				$("clId").style.display = "none";
			}
			
			if(fw != ""){
				$(id).value = fw;
			}
			dwr.engine.setAsync(true);
		}
		</script>
	</head>

	<body onload="chTjfs()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zjcmGygl" >
		
			<!-- ������ -->
			<%@ include file="/gygl/hiddenValue.jsp"%>
			<input type="hidden" id="fw" value="${fw }"/>
			<!-- ������ end-->
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal value="yes" name="writeAble">
						<li>
							<a href="#" 
								onclick="expInfo()" 
								class="btn_dc">����</a>
						</li>
						</logic:equal>
					</ul>
				</div>
				<!-- ��ť end-->	
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<input type="hidden" name="go" value="a" />
										<button class="btn_cx" id="search_go"
											onclick="bxtj()">
											ͳ��
										</button>
										<button class="button2" style="" id="cz"
											onclick="czSearchCond('nj-xn-xq-nd-xy-zy-bj-xh-xm-xqdm-lddm-cs-qsh-querygreaterequal_bxsj-querylessequal_bxsj-sfsf-sfwg-tjcllx-tjclmc-tjfs-tjfw')">
											����
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<!-- ��һ�� -->
							<tr>
								<th>
									�꼶
								</th>
								<td>
									<html:select property="queryequals_nj" styleId="nj" style="" onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>												
								</td>
								<th>
									ѧ��
								</th>
								<td>
									<!-- ѧ���û� -->
									<logic:equal name="userType" value="stu">
										<html:text property="querylike_xh" styleId="xh" style="width: 100px" maxlength="20" readonly="true"/>
									</logic:equal>
									<!-- ��ѧ���û� -->
									<logic:notEqual name="userType" value="stu">
										<html:text property="querylike_xh" styleId="xh" style="width: 100px" maxlength="20"/>
									</logic:notEqual>
								</td>
								<th>
									����
								</th>
								<td>
									<html:text property="querylike_xm" styleId="xm" style="width: 100px" maxlength="20"/>	
								</td>
								<th>
									�Ա�
								</th>
								<td>
									<html:select property="xb" style="" styleId="xb">
										<html:option value=""></html:option>
										<html:options collection="xbList" property="en" labelProperty="cn" />
									</html:select>	
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<!-- ��ѧԺ�û� -->
									<logic:equal name="isxy" value="true">
										<html:hidden property="queryequals_xydm"/>
										<html:select property="xydm" style="" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>	
									</logic:equal>
									<!-- ��ѧԺ�û� end -->
									<!-- ��ѧԺ�û� -->
									<logic:equal name="isxy" value="false">
										<html:select property="queryequals_xydm" style="width: 100px" styleId="xy" 
											onchange="initZyList();initBjList();">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc" />
										</html:select>	
									</logic:equal>
									<!-- ��ѧԺ�û� end-->											
								</td>
								<th>
									רҵ
								</th>
								<td>
									<html:select property="queryequals_zydm" style="width: 100px" styleId="zy" onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm" labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									�༶
								</th>
								<td>
									<html:select property="queryequals_bjdm" style="width: 100px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
									</html:select>
								</td>
								<th>
									�Ƿ��깤
								</th>
								<td>
									<html:select property="sfwg" style="width: 100px" styleId="sfwg">
										<html:option value="">----��ѡ��----</html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									У��
								</th>
								<td>
									<html:select property="queryequals_xqdm" style="width: 100px" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>								
								</td>
								<th>
									¥��
								</th>
								<td>
									<html:select property="queryequals_lddm" style="width: 100px" styleId="lddm" 
										onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="queryequals_cs" style="width: 100px" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									���Һ�
								</th>
								<td>
									<html:select property="queryequals_qsh" style="width: 100px" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>				
								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									<font color="red">*</font>ͳ�Ʒ�ʽ
								</th>
								<td>
									<html:select property="tjfs" styleId="tjfs" style="width: 100px" onchange="chTjfs()">
										<html:option value="">----��ѡ��----</html:option>
										<html:options collection="tjfsList" property="en" labelProperty="cn" />
									</html:select>				
								</td>
								<th>
									�Ƿ��շ�
								</th>
								<td>
									<html:select property="queryequals_sfsf" style="width: 100px" styleId="sfsf">
										<html:option value="">----��ѡ��----</html:option>
										<html:options collection="sfList" property="en" labelProperty="cn" />
									</html:select>
								</td>
								<th>
									����ʱ��
								</th>
								<td colspan="3">
									<html:text property="querygreaterequal_bxsj" styleId="querygreaterequal_bxsj"
										onblur="dateFormatChg(this)" style="cursor:hand;width:100px"
										onclick="return showCalendar('querygreaterequal_bxsj','y-mm-dd');"/>	
									��
									<html:text property="querylessequal_bxsj" styleId="querylessequal_bxsj"
										onblur="dateFormatChg(this)" style="cursor:hand;width:100px"
										onclick="return showCalendar('querylessequal_bxsj','y-mm-dd');"/>	
								</td>
							</tr>
							<!-- ������ -->
							<tr>
								<th>
									<font color="red">*</font>ͳ�Ʒ�Χ
								</th>
								<td>
									<html:select property="tjfw" styleId="tjfw" style="" onchange="">
										<html:option value="">----��ѡ��----</html:option>
									</html:select>
								</td>
								<td colspan="6">
									<span id="clId" style="display : none">
									&nbsp;&nbsp;&nbsp;&nbsp;��������
									<html:select property="tjcllx" styleId="tjcllx" style="width: 100px" onchange="">
										<html:options collection="cllxList" property="dm" labelProperty="mc" />
									</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									��������&nbsp;&nbsp;
									<html:text property="tjclmc" styleId="tjclmc" style="width: 100px" maxlength="25"/>		
									</span>
								</td>
								
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<logic:empty name="rsArrList">
					    <h3 class="datetitle_01">
					    <span>
					    	��ѯ���&nbsp;&nbsp;
								<font color="red">δ�ҵ��κμ�¼��</font> 
					    </span>
					    </h3>
					</logic:empty>
				<logic:notEmpty name="rsArrList">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
						</span>
					</h3>
						<table summary="" class="dateline tablenowrap" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="0">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<!-- ��ͷ end-->
							<!--���� -->
							<logic:iterate name="rsArrList" id="rs" indexId="index">
								<!-- �����ܼ� -->
								<logic:notEqual name="index" value="${rsNum }">
									<tr onclick="rowOnClick(this);" style="cursor:hand">
										<logic:iterate id="v" name="rs">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:notEqual>
								<!-- �ܼ� -->
								<logic:equal name="index" value="${rsNum }">
									<tr onclick="rowOnClick(this);" style="cursor:hand">
										<logic:iterate id="v" name="rs" offset="0" length="1">
											<td align="left" colspan="${topNum }">
												${v }
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="rs" offset="${topNum }">
											<td align="left">
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:equal>
							</logic:iterate>
							<!--���� end-->
						</table>
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>	
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>