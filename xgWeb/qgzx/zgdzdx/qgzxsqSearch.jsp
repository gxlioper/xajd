<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">	
	function szgw(){
		var checkBoxArr = document.getElementsByName('cbv');
		var yzchk=true;
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked){
			yzchk =false;
				break;
			}
		}
		if(yzchk==true){
			alert("�빴ѡ���������õ�ѧ��");
			return;
		}
		var d_width = document.body.clientWidth;
		var d_height = document.body.clientHeight ;
		var d_left = 0;
		var d_top = 0;
		var d_color = "#EEF4F9";
		var d_width_top = 250;
		var d_height_top = 120;
		var d_left_top = (d_width - d_width_top) / 2;
		var d_top_top = (d_height - d_height_top)/ 2;
		var d_color_top = "#EEF4F9";
		dd_html = "<div oncontextmenu='return false' onselectstart='return false' style='filter:alpha(opacity=50);position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width + "px; height:" + d_height + "px; top:" + d_top + "px; left:" + d_left + "px; background-color:" + d_color + "'>";
		dd_html += "</div>";
		dd_html += "<div id='layerTop' style='position:absolute;align:middle;text-align:center;padding-top:10px;border: 1px solid #5E88B8; width:" + d_width_top + "px; height:" + d_height_top + "px; top:" + d_top_top + "px; left:" + d_left_top + "px; background-color:" + d_color_top + "'>";
		dd_html += "<table width='300' class='tbstyle'>";
		dd_html += "<thead>";
		dd_html += "<tr height='30'>";
		dd_html += "<td colspan='2'>";
		dd_html += "----------------��ѡ��---------------";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		dd_html += "<tbody>";
		
		dd_html += "<tr height='30'>";
		dd_html += "<td align='right' width='30%'>";
		dd_html += "��ʱ��λ:";
		dd_html += "</td>";
		dd_html += "<td align='left'>";
		dd_html += "<select name='lsgw' id ='lsgw' onchange='chLsgw(this.value)'>" 
		dd_html += $('lsgw').innerHTML;
		dd_html += "</td>";
		dd_html += "</tr>";
		
		dd_html += "<tr bgcolor='EEF4F9'>";
		dd_html += "<td colspan='2' align='center'>";
		dd_html += "<button type='button' class='button2' onclick='saveSzgw()';>ȷ��</button>";
		dd_html += "&nbsp;&nbsp;&nbsp;&nbsp;";
		dd_html += "<button type='button' class='button2' onclick='closeAdd1()'>�ر�</button>";
		dd_html += "</td>";
		dd_html += "</tr>";
		dd_html += "<tbody>";
		dd_html += "</table>";
		dd_html += "</div>";
		tmpdiv1.innerHTML = dd_html;
	}

		function saveSzgw() {
		
			if($("lsgw")){
				if($("lsgw").value == ""){
					alert("��ȷ��Ҫ���õ���ʱ��λ!");
					return false;
				}
			}
			
			var url = "qgzxZgdzdx.do?method=qgzxsqSearch&doType=save";
			showTips('���������У���ȴ�......');
			refreshForm(url);
		}

		function chLsgw(lsgw){
			if($("lsgw")){
				$("lsgw").value = lsgw;
			}
		}
		
		function modidata(url,w,h){
			if(curr_row == null){
				alert("��ѡ��һ��Ҫ�޸ĵļ�¼��");
				return false;
			}
			var pkValue = curr_row.cells[0].getElementsByTagName("input")[0].value;
			url += pkValue;
			showTopWin(url,w,h);
		}	
		
		function del(){
			var RowsStr="!!";	
			var mes = "ȷ��Ҫ������ѡ��¼��";
			for (i=0; i<document.getElementsByName("cbv").length; i++){
		    	if(document.getElementsByName("cbv")[i].checked){
		    		RowsStr+=document.getElementsByName("cbv")[i].value+"!!";
		    	}
			}
			
			if (RowsStr=="!!"){
				alert("��ѡ��Ҫ���������ļ�¼��");
				return false;
			}
			
			if (!confirm(mes)){
				return false;
			}
			
			url = "qgzxZgdzdx.do?method=delQgzxsq";
			refreshForm(url);
		}		
		
		//���ݵ���
		function exportData(){
			var url = 'qgzxZgdzdx.do?method=expXsqgzxsq';
			var eleArr = ["xn","nd","xq","gwxzdm","nj","xh","xm","xysh","xxsh"];
			url += "&xydm=" + val("xy"); 
			url += "&zydm=" + val("zy");
			url += "&bjdm=" + val("bj");
			for(var i=0; i<eleArr.length;i++){
				if(ele(eleArr[i])){
					url += "&" + eleArr[i] + "=" + val(eleArr[i]);
				}
			}
			window.open(url)
		}
	</script>
</head>
<body onload="xyDisabled('xy')">
	<html:form action="/qgzxZgdzdx.do" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<input type="hidden" name="realTable" id="realTable" value="${realTable}"/>
			<input type="hidden" name="tableName" id="tableName" value="${tableName}"/>
			<input type="hidden" name="xyV" value=""/>
			<input type="hidden" name="zyV" value=""/>
			<input type="hidden" name="bjV" value=""/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
				
			<logic:equal value="11078" name="xxdm">
			<html:select property="lsgw" styleId="lsgw" style="display : none">
				<html:option value=""></html:option>
				<html:options collection="lsgwList" property="dm" labelProperty="mc"/>
			</html:select>		
			</logic:equal>

			<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
					<ul>
					<logic:notEqual value="stu" name="userType">
					<logic:equal value="yes" name="writeAble">
						<li> <a href="#" onclick="refreshForm('qgzxZgdzdx.do?method=qgzxsq');" class="btn_zj">�� ��</a> </li>
						<li> <a href="#" onclick="modidata('qgzxZgdzdx.do?method=qgzxsqModi&pkValue=',800,600);" class="btn_xg">�� ��</a> </li>
						<li> <a href="#" onclick="del();" class="btn_sc">ɾ ��</a> </li>
					    <li> <a href="#" onclick="impAndChkData();" class="btn_dr">��������</a> </li>
					</logic:equal>	
					</logic:notEqual>
				    	<li> <a href="#" onclick="exportData();" class="btn_dc">��������</a> </li>
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="allNotEmpThenGo('qgzxZgdzdx.do?method=qgzxsqSearch')">
									��ѯ
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>ѧ��</th>
						<td>
							<html:select property="xn" styleId="xn" style="width:80px">
								<html:options collection="xnList" property="xn" labelProperty="xn"/>
							</html:select>	
						</td>
						<th>���</th>
						<td>
							<html:select property="nd" styleClass="select" styleId="nd">
								<html:options collection="xnList" property="nd" labelProperty="nd" />
							</html:select>	
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xq" styleId="xq" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" 
								labelProperty="xqmc"/>
							</html:select>
						</td>
					  </tr>		
					  <tr>
						<th>�꼶</th>
						<td>
							<html:select property="nj" style="width:90px"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:text property="xh" style="width:80px"/>	
						</td>
						<th>����</th>
						<td>
							<html:text property="xm" style="width:80px"/>
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" style="width:175px" styleClass="select" styleId="xy" 
								onchange="initZyList();initBj();">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm" labelProperty="xymc" />
							</html:select>	
						<br/>
						</td>
						<th>רҵ</th>
						<td>
							<html:select property="zydm" style="width:175px" styleClass="select" styleId="zy"
								onchange="initBj();">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm" labelProperty="zymc" />
							</html:select>	
						</td>
						<th>�༶</th>
						<td>
							<html:select property="bjdm" style="width:175px" styleClass="select" styleId="bj">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>		
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" />���</th>
						<td>
							<html:select property="xysh">
								<html:option value=""></html:option>
								<html:options collection="getChkList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
						<th>ѧУ���</th>
						<td>
							<html:select property="xxsh">
								<html:option value=""></html:option>
								<html:options collection="getChkList" property="en" labelProperty="cn"/>
							</html:select>	
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>
					  <!--���ݴ�ѧ-->
					  <logic:equal value="11078" name="xxdm">	
					  <tr>
						<th>��λ����</th>
						<td>
							<html:select property="gwxzdm" styleId="gwxzdm">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm" 
								labelProperty="gwxzmc"/>
							</html:select>	
						</td>
						<th>�Ƿ�Υ��</th>
						<td>
							<html:select property="sfwj">	
								 <html:option value=""></html:option>
								 <html:option value="01">��</html:option>
								 <html:option value="02">��</html:option>
							</html:select>		
						</td>
						<th>�ҿƴ���</th>
						<td>
							<html:select property="pdfh">
								 <html:option value=""></html:option>
								 <html:option value="01">&gt;</html:option>
								 <html:option value="02">=</html:option>
							</html:select>
							<html:text property="gkms" style="width:40px" onkeypress="return onlyNum(this,20)"/>
						</td>
					  </tr>	
					  </logic:equal>
					  <!--end���ݴ�ѧ-->
					  </tbody>
					</table>
				</div>
				</div>
				<div class="formbox">
					<h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font> 
				 		 </logic:empty>
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ���������޸ģ�������ͷ��������</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td>
						<input type="checkbox" id="cb" name="cb" onclick="selectAll('cbv')" />
					</td>
					<logic:iterate id="tit" name="topTr" offset="2">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
					<tr onclick="rowOnClick(this)"
						style="cursor:hand;" align="center"  
						ondblclick="modidata('qgzxZgdzdx.do?method=qgzxsqModi&pkValue=',800,600)">
						<td>
							<input type="hidden" id="pkV" name="pkV" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>"/>
							<input type="checkbox" id="cbv" name="cbv" value="<logic:iterate id="v" name="s" offset="1" length="1"><bean:write name="v"/></logic:iterate>" />
						</td>							
						<logic:iterate id="v" name="s" offset="2">
							<td>
								<bean:write name="v" />
							</td>
						</logic:iterate>
					</tr>
				</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qgzxZgdzdxForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
		<div id="tmpdiv1"></div>
	</html:form>

	<logic:present name="result">
		<logic:equal value="true" name="result">
			<logic:empty name="mes">
				<script>
					alert('�����ɹ�!');						
					document.getElementById('search_go').click();
				</script>	
			</logic:empty>
			<logic:notEmpty name="mes">
				<input type="hidden" id="mes" name="mes" value="${mes}"/>
				<script>
					alert(document.getElementById('mes').value);
					document.getElementById('search_go').click();
				</script>
			</logic:notEmpty>
		</logic:equal>
		<logic:equal value="false" name="result">
		<logic:empty name="mes">
				<script>
					alert('����ʧ��!');
					document.getElementById('search_go').click();
				</script>	
			</logic:empty>
			<logic:notEmpty name="mes">
				<input type="hidden" id="mes" name="mes" value="${mes}"/>
				<script>
					alert(document.getElementById('mes').value);
					document.getElementById('search_go').click();
				</script>
			</logic:notEmpty>
	</logic:equal>	
	</logic:present>
	</body>
</html>
