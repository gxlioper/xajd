<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cjff.js'></script>
	<script language="javascript" src="js/xgutil.js"></script>	
	<script>
		function singleWorkPay(url){
			if(curr_row==null){
				alert('��ѡ��Ҫ�������ŵĸ�λ!');
				return false;
			}
			var pk = document.forms[0].pk.value;
			var pkValue = curr_row.getElementsByTagName("input")[0].value;
			var gwxz = curr_row.cells[6].innerText;
			var yrdw = curr_row.cells[5].innerText;
			url += "&pk=";
			url += pk;
			url += "&pkValue=";
			url += pkValue;
			url += "&gwxz=";
			url += gwxz;
			url += "&yrdw=";
			url += yrdw;
			
			showTopWin(url,800,600);
			
		}
		
		//���ʷ��ű��ӡ
		function printPayReport(){
			var xxdm = document.getElementById('xxdm').value;
			var gwxzmc = "";
			if(ele("gwxz")){
				gwxzmc = selText('gwxz');
			}
			var url = "qgzxLogic.do?method=printPayReport&gwxzmc="+gwxzmc;
			if(xxdm == "13022"){//������		
				url = "qgzxNblg.do?method=printYbb";
			}
			window.open(url);
		}
		
		//��д�굥
		function writeList(){
			var xxdm = document.getElementById('xxdm').value;
			var cjffsj = document.getElementById('cjffsj').value;
			if(xxdm == '10856'){//�Ϻ����̼�����ѧ
				singleWorkPay('qgzxLogic.do?method=showSinglePage');
			}else if(xxdm == '10491'){//�й����ʴ�ѧ
				fillMonth('/xgxt/cjff.do?method=xzffsj&type=ygzff');
			}else{//����
				if(cjffsj != ""){//����ڲ��������������˹��������·�
					viewMoreWorkPay('add');
				}else{
					//if($('day').value<31){
					     viewMoreWorkPay('add'); 
					 //}else{
					     //alert('ʱ�䳬��,���������!');		
					     //return false;		  
					 //}
				 }
			}
		}
	</script>
</head>
	<body>
		<html:form action="/data_search" method="post">
			<input type="hidden" id="tableName" name="tableName" value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act" value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable" value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="day" name="day" value="<bean:write name="day" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
			<input type="hidden" id="cjffsj" name="cjffsj" value="${conf.cjffsj}" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em>
					<a>
					<%--��ɳ����--%>
					<logic:equal value="10827" name="xxdm">
						ѧ���幤 - ��𷢷� - ��𷢷�
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						�ڹ���ѧ - ��𷢷� - ��𷢷�
				    </logic:notEqual>
					</a>
				</p>
			</div>
			<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					<!--��дȨ-->
					<logic:equal value="yes" name="writeAble" scope="request">					
					<%--�人����ѧ ֻ�����˵�λ��д--%>
					<logic:equal value="10497" name="xxdm">
						<li> <a href="#" onclick="writeList();" class="btn_zj">��д�굥</a> </li>
					</logic:equal>
					<%--end�人����ѧ--%>
					
					<%--���人����ѧ--%>
					<logic:notEqual value="10497" name="xxdm">
						<li> <a href="#" onclick="writeList();" class="btn_zj">
							<%--�й����ʴ�ѧ--%>
							<logic:equal value="10491" name="xxdm">
								�¹���
							</logic:equal>
							<%--end�й����ʴ�ѧ--%>
							<%--���й����ʴ�ѧ--%>
							<logic:notEqual value="10491" name="xxdm">
								��д�굥
							</logic:notEqual>	
							<%--end���й����ʴ�ѧ--%>
						</a> </li>
					</logic:notEqual>
					<%--end���人����ѧ--%>	
					
					<%--�й����ʴ�ѧ--%>
					<logic:equal value="10491" name="xxdm">
						<li> <a href="#" onclick="fillMonth('/xgxt/cjff.do?method=xzffsj&type=lsgzff');" class="btn_zj">��ʱ����</a> </li>	
						<li> <a href="#" onclick="rePut();" class="btn_zj">���ʲ���</a> </li>
					</logic:equal>
					<%--end�й����ʴ�ѧ--%>
					</logic:equal>
					<!--end��дȨ-->
					<%--�㶫Ů��ְҵ����ѧԺ--%>
					<logic:equal value="12742" name="xxdm">
						<li> <a href="xlsDown/qgzx_gdnzzy_gzffb.doc" class="btn_xz">���ʷ��ű�����</a> </li>
					</logic:equal>
					<%--end�㶫Ů��ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
					
					<%--�ǹ㶫Ů��ְҵ����<bean:message key="lable.xsgzyxpzxy" />--%>
					<logic:notEqual value="12742" name="xxdm">
						<li> <a href="#" onclick="printPayReport();" class="btn_dy">���ʷ��ű�</a> </li>
					</logic:notEqual>
					<%--end�ǹ㶫Ů��ְҵ����ѧԺ--%>
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
									onclick="allNotEmpThenGo('/xgxt/work_pay_put.do?act=workPayPut')">
									��ѯ
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>���</th>
						<td>
							<html:select property="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					  </tr>
					  <tr>
						<th>��λ����</th>
						<td>
							<html:select property="gwxz">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm"
									labelProperty="gwxzmc" />
							</html:select>
						</td>
						<th>��λ����</th>
						<td>
							<html:select property="gwdm">
								<html:option value=""></html:option>
								<html:options collection="gwList" property="gwmc"
									labelProperty="gwmc" />
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>
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
						<!--���Ϻ����̼�����ѧ-->
						<logic:notEqual value="10856" name="xxdm">
							<%--���㽭����ְҵ����ѧԺ--%>
							<logic:notEqual value="12861" name="xxdm">
								<logic:equal value="10491" name="xxdm">
									<font color="red">${conf.sbts}</font>
								</logic:equal>
								<logic:notEqual value="10491" name="xxdm">
									<font color="red">ע�⣺�����˵�λ������ÿ��30��ǰ��д��𷢷��굥��</font>
								</logic:notEqual>
							</logic:notEqual>
							<%--end���㽭����ְҵ����ѧԺ--%>
						</logic:notEqual>
						<!--end���Ϻ����̼�����ѧ-->
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <logic:iterate id="tit" name="topTr" offset="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody>
			      <logic:iterate name="rs" id="s">
					<tr onclick="rowOnClick(this)" style="cursor:hand">
						<td>
							<logic:iterate id="v" name="s" offset="0" length="1">
								<input type="hidden" value="<bean:write name="v"/>" />
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="1" length="1">
								<bean:write name="v" />
							</logic:iterate>
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
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
				</logic:notEmpty>
			</div>
		</html:form>		
	</body>
</html>

