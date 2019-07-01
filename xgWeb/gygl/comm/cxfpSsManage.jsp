<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/xgutil.js"></script>
		<script language="javascript" src="js/gygl/gyglTyFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getGyglDAO.js'></script>
		<script language="javascript">
		
		//���峷���������
		function ztcx_ss(){
		
			var nj = $("nj").value;
			var xydm = $("xy").value;
			var xqdm = $("xqdm").value;
			var lddm = $("lddm").value;
			var cs = $("cs").value;
			var qsh = $("qsh").value;
			
			var msg = "";
			
			//����������ûѡ
			if(nj == "" && xydm == "" && xqdm == "" && lddm == "" && cs == "" && qsh == "")	{
				msg = "δѡ���κ�����������ȫ���������\n";
				msg+="ȷ���밴'ȷ��'��ť��������ȡ��";
			}else{
				msg = "��������ѡ������������ڳ��������������������᣺\n";
				
				//�꼶
				if(nj != ""){
					msg+="�꼶��"+ nj + "\n";
				}
				
				//ѧԺ
				if(xydm != ""){
				
					var xy = $("xy");
					var xymc = "";
					
					for(var i=0;i<xy.options.length;i++){
						if(xy.options[i].selected){
							xymc=xy.options[i].text;
						}
					}
					msg+="<bean:message key="lable.xb" />��"+ xymc + "\n";
				}
				
				//У��
				if(xqdm != ""){
				
					var xq = $("xqdm");
					var xqmc = "";
					
					for(var i=0;i<xq.options.length;i++){
						if(xq.options[i].selected){
							xqmc=xq.options[i].text;
						}
					}
					msg+="У����"+ xqmc + "\n";
				}
				
				//¥��
				if(lddm != ""){
				
					var ld = $("lddm");
					var ldmc = "";
					
					for(var i=0;i<ld.options.length;i++){
						if(ld.options[i].selected){
							ldmc=ld.options[i].text;
						}
					}
					msg+="¥����"+ ldmc + "\n";
				}
				
				//����
				if(cs != ""){
					msg+="������"+ cs + "\n";
				}
				
				//����
				if(qsh != ""){
					msg+="���ң�"+ qsh + "\n";
				}
				
				msg+="ȷ���밴'ȷ��'��ť��������ȡ��";
				
			}
			
			if(confirm(msg)){
				var url = "/xgxt/commGygl.do?method=cxfpSsManage&doType=plcx"
		    	saveUpdate(url,'');
		   	}
		}
		</script>
	</head>

	<body onload="">
		<html:form action="/commGygl">
			<!-- ���� -->
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<!-- ���� end-->
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->		
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#"
									onclick="sumitInfo('/xgxt/commGygl.do?method=cxfpSsManage','del')"
									class="btn_fh">��������</a>
							</li>
							<li>
								<a href="#"
									onclick="ztcx_ss()"
									class="btn_sy">���峷��</a>
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
										<button style="" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/commGygl.do?method=cxfpSsManage');">
											��ѯ
										</button>
										<button style="" id="cz"
											onclick="czSearchCond('nj-xy-xqdm-lddm-cs-qsh');return false;">
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
									<html:select property="queryequals_nj" style="" styleId="nj" onchange="">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="nj" />
									</html:select>												
								</td>
								<th>
									Ժϵ
								</th>
								<td>
									<html:select property="queryequals_xydm" style="" styleId="xy" onchange="">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									У��
								</th>
								<td>
									<html:select property="queryequals_xqdm" style="" styleId="xqdm" onchange="setLdList()">
										<html:options collection="xqdmList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<!-- �ڶ��� -->
							<tr>
								<th>
									¥��										
								</th>
								<td>
									<html:select property="queryequals_lddm" style="" styleId="lddm" onchange="setXqList();setCsList();setQsList();">
										<html:options collection="ldList" property="dm" labelProperty="mc" />
									</html:select>											
								</td>
								<th>
									��������
								</th>
								<td>
									<html:select property="queryequals_cs" style="" styleId="cs" onchange="setQsList();">
										<html:options collection="csList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								<th>
									���Һ�
								</th>
								<td>
									<html:select property="queryequals_qsh" style="" styleId="qsh" onchange="">
										<html:options collection="qsList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<div class="formbox">
					<h3 class="datetitle_01">
						<span> ��ѯ���&nbsp;&nbsp; 
							<logic:empty name="rs">
								<font color="red">δ�ҵ��κμ�¼��</font>
							</logic:empty>
						</span>
					</h3>
					<logic:notEmpty name="rs">
						<table summary="" class="dateline" align="" width="100%">
							<!-- ��ͷ -->
							<thead>
								<tr align="center" style="cursor:hand">
									<td width="5%">
										<input type="checkbox" id="selall" name="selall" onclick="selAll()"/>
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
											<input type="checkbox" id="checkVal"  name="primarykey_checkVal"  value="${v }"/>	
										</td>
									</logic:iterate>
									<!-- ��Ŀ��Ϣ -->		
									<logic:iterate id="v" name="s" offset="1" >
										<td align="left">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						<!--���� end-->
						</table>
						<!--��ҳ-->
						<jsp:include flush="true"
							page="/sjcz/turnpage.jsp?form=gyglTyForm"></jsp:include>
						<!--��ҳend-->
					</logic:notEmpty>
				</div>
				<!-- ��ѯ��� end-->
			</div>	
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
	</body>
</html>