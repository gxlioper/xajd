<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/xgutil.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cjff.js'></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>	
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
			if(ele("queryequals_gwxz")){
				gwxzmc = selText('queryequals_gwxz');
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
					if($('day').value<31){
					     viewMoreWorkPay('add'); 
					 }else{
					     alert('ʱ�䳬��,���������!');		
					     return false;		  
					 }
				 }
			}
		}
		
		function viewMoreWorkPay(){
			if (curr_row == null) {
				alert("����ѡ����Ӧ�ĸ�λ��¼!");
				return false;
			}
			var pkValue = curr_row.getElementsByTagName("input")[0].value;
			showTopWin("qgzxcjff.do?method=txxd&pkValue="+pkValue,800,600);
		}
	</script>
</head>	
	<body>
		<html:form action="/qgzxcjff.do" method="post">
			<input type="hidden" id="xxdm" name="xxdm" value="<bean:write name="xxdm"/>" />
			<input type="hidden" id="day" name="day" value="<bean:write name="day"/>" />
			<input type="hidden" id="cjffsj" name="cjffsj" value="${conf.cjffsj}" />
			<input type="hidden" id="userName" name="userName" value="${userName}" />
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
						<%--�й����ʴ�ѧ--%>
						<logic:equal value="10491" name="xxdm">
							<li> <a href="#" onclick="writeList();" class="btn_zj">�¹���</a> </li>
							<li> <a href="#" onclick="fillMonth('/xgxt/cjff.do?method=xzffsj&type=lsgzff')" class="btn_zj">��ʱ����</a> </li>
							<li> <a href="#" onclick="rePut();" class="btn_zj">���ʲ���</a> </li>
						</logic:equal>
						<%--end�й����ʴ�ѧ--%>	

						<%--���й����ʴ�ѧ--%>
						<logic:notEqual value="10491" name="xxdm">
							<li> <a href="#" onclick="writeList();" class="btn_zj">��д�굥</a> </li>
						</logic:notEqual>
						<%--end���й����ʴ�ѧ--%>	
					</logic:equal>
					<!--end��дȨ-->

				
					<li> <a href="#" onclick="printPayReport();" class="btn_dy">���ʷ��ű�</a> </li>						
					

					<%--���й����ʴ�ѧ--%>
					<logic:notEqual value="10491" name="xxdm">
					<li> <a href="#" onclick="printGzhzb();" class="btn_dy">���ʻ��ܱ�</a> </li>
					</logic:notEqual>
					<%--end���й����ʴ�ѧ--%>	
					
					<logic:equal value="12977" name="xxdm">
					<li>
						<a href="#" onclick="wjcfDataExport('qgzxcjff.do?method=gzqfb');" class="btn_dy"><bean:message key="lable.xsgzyxpzxy" />����ǩ�ձ�</a> 
					</li>
					</logic:equal>
					
			    </ul>
			  </div>			 
			 <!--��ѯ����-->
			<div class="searchtab">
		    <table width="100%" border="0">
		      <tfoot>
		        <tr>
		          <td colspan="6">
		            <div class="btn">
					  	<input type="hidden" name="go" value="a" />
						<button type="button" class="btn_cx" 
								id="search_go"
								onclick="allNotEmpThenGo('/xgxt/qgzxcjff.do?method=cjff')">
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
						<html:select property="queryequals_xn" style="width:180px" styleId="xn" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
					<th>���</th>
					<td>
						<html:select property="queryequals_nd" styleId="nd" style="width:180px" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
							<html:options collection="xnList" property="nd"
								labelProperty="nd" />
						</html:select>
					</td>	
					<th>ѧ��</th>
					<td>
						<html:select property="queryequals_xueqi" style="width:180px" styleId="xq" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
							<html:option value=""></html:option>
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>				
				</tr>						
				<tr>
					<logic:notEqual name="xxdm" value="11078">
					<th>��λ����</th>
					<td colspan="5">
						<html:select property="queryequals_gwdm" styleId="gwdm" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdm"
								labelProperty="gwdm" />
						</html:select>
					</td>
					</logic:notEqual>
					<logic:equal name="xxdm" value="11078">
						<th>��λ����</th>
						<td>
							<html:select property="queryequals_gwdm" styleId="gwdm" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="gwList" property="gwdm"
									labelProperty="gwdm" />
							</html:select>
						</td>
			      		<th>��λ����</th>
						<td colspan="3">
							<html:select property="queryequals_gwxz" style="width:180px" styleId="xq" onchange="loadGwmcxx('gwdm','xn','nd','xq')">
								<html:option value=""></html:option>
								<html:options collection="gwxzList" property="gwxzdm"
									labelProperty="gwxzmc" />
							</html:select>
						</td>
					</logic:equal>			
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
				<logic:notEmpty name="rs">
					<%--���㽭����ְҵ����ѧԺ--%>
					<logic:notEqual value="12861" name="xxdm">
						<!--�й����ʴ�ѧ-->
						<logic:equal value="10491" name="xxdm">
							<font color="red">${conf.sbts}</font>
						</logic:equal>
						<!--end�й����ʴ�ѧ-->
						<logic:notEqual value="10491" name="xxdm">
							<font color="red">ע�⣺�����˵�λ������ÿ��30��ǰ��д��𷢷��굥��</font>
						</logic:notEqual>
					</logic:notEqual>
					<%--end���㽭����ְҵ����ѧԺ--%>
				</logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr>
			       <logic:iterate id="tit" name="topTr" offset="1">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
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
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qgzxForm"></jsp:include>
			    <!--��ҳ��ʾ-->
				<script type="text/javascript">
				$('choose').className="hide";
				</script>
			</logic:notEmpty>
		</div>	
		</html:form>
	</body>
</html>

