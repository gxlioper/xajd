<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="xgxt.action.Base"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/comm/commFunction.js"></script>
	<script type="text/javascript">
		//ע��
		function zc(checkboxName,url,type){
			var checkbox = jQuery('input[name='+checkboxName+']:checked');
			if(checkbox.length == 0){
				alertInfo('��ѡ����Ҫ����������');
				return false;
			}

			var xhArr = jQuery('input[name=cbv]');
			//ȡ��ע��ʱ��֤
			if('qxzc'==type){
				var zcztArr = jQuery('input[name=zcztArr]');
				var cont = 0;
				jQuery(xhArr).each(function(i,n){
					if(n.checked){
						if(zcztArr[i].value!='��ע��'){
							cont++;
						}
					}
				});
				if(cont>0){
					alertInfo('ֻ�ܶ���ע���ѧ�����ò�����');
					return false;
				}
			} 

			//����ע����֤��ش���
			var sfkzcArr = jQuery('input[name=sfkzc]');
			var bkzcxh = '';//����ע��ѧ��
			jQuery(xhArr).each(function(i,n){
				if(n.checked){
					if(sfkzcArr[i].value=='����ע��'){
						bkzcxh += n.value + ',';
					}
				}
			});
			if(bkzcxh!=''){
				var allbkzcxh = bkzcxh.substr(0,bkzcxh.length-1);
				var arr = allbkzcxh.split(',');
				var xhpz = '';
				for(var i=0;i<arr.length;i++){
					//����ע���ѧ�������ĸ�ʱ��ֻ�г��ĸ�...
					if(i<=3){
						xhpz += arr[i]+',';
						if(i==1)
							xhpz+='<br/>';//�ڶ���ѧ�ſ�ʼ���У������Ȳ���
					}
				}
				alertInfo('����ѧ������ע�᣺<br/>' + xhpz.substr(0,xhpz.length-1) + (arr.length>4 ? '��' : '') + '��<br/>��˶��佻�����');
				return false;
			}
			
			//��ʾȷ����Ϣ
			confirmInfo('ȷ��������', function(tag){
				if(tag == 'ok'){
					document.forms[0].action = url;
					document.forms[0].submit();
				}
			});
			if ($("pt")) {
				BatAlert.showTips('���ڲ�������ȴ�...');
			}
		}

		//δע��
		function wzc(checkboxName,url){
			var checkbox = jQuery('input[name='+checkboxName+']:checked');
			if(checkbox.length == 0){
				alertInfo('��ѡ����Ҫ����������');
				return false;
			} 
			var cbs = '';
			$(checkbox).each(function(i,n){
				cbs += n.value;
				if((checkbox.length-1)!=i){
					cbs += ',';
				}
			});
			showTopWin(url + '&cb='+cbs,500,280);
		}

		function query(){
			document.getElementById('search_go').click();
		}

		//˫���鿴
		function view(){
			if(curr_row==null){
				alert('��ѡ��Ҫ�鿴�����ݣ�');
				return false;
			}
			showTopWin('xszc.do?method=xszcdetail&xh='+curr_row.getElementsByTagName('input')[0].value+'&xn='+document.getElementById('xn').value + '&xq=' + document.getElementById('xq').value,660,430);
		}

		function changeDis(){
			var xn = document.getElementById('xn').value;
			var dqxn = document.getElementById('cur_dqxn').value;
			var xq = document.getElementById('xq').value;
			var dqxq = document.getElementById('cur_dqxq').value;
			if(xn!=dqxn || xq!=dqxq){
				var xhArr = jQuery('input[name=cbv]');
				jQuery(xhArr).each(function(i,n){
					n.disabled = 'disabled';
				});
			}
		}

	</script>
</head>
	<body onload="xyDisabled('xy');changeDis();">		
		<html:form action="/xszc" method="post">
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="cb" name="cb" value="" />
			<input type="hidden" id="cur_dqxn" name="cur_dqxn" value="<%=Base.currXn %>"/>
			<input type="hidden" id="cur_dqxq" name="cur_dqxq" value="<%=Base.currXq %>"/>
			
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="toolbox">	
			<!-- ��ť -->
			  <div class="buttonbox">
			    <ul>
					<!--��дȨ-->
					<logic:equal value="yes" name="writeAble">
						<li> <a href="#" onclick="zc('cbv','xszc.do?method=xszc&doType=zc&zt=��ע��');" class="btn_shtg">��ע��</a> </li>
						<li> <a href="#" onclick="wzc('cbv','xszc.do?method=wzcyydetail');" class="btn_shbtg">δע��</a> </li>
						<li> <a href="#" onclick="zc('cbv','xszc.do?method=xszc&doType=qxzc','qxzc');" class="btn_shbtg">ȡ��ע��</a> </li>
					</logic:equal>
					<li><a href="#" class="btn_dc" onclick="expData('xszc.do?method=xszc&doType=exp');">����</a></li>
					<!--end��дȨ-->
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
								onclick="allNotEmpThenGo('xszc.do?method=xszc')">
							��ѯ
						</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
		      	<tr>	
		      		<th>�꼶</th>
					<td>
						<html:select property="nj" onchange="initZyList();initBjList()" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
						</html:select>
					</td>				
					<th>ѧ��</th>
					<td>
						<html:text property="xh" maxlength="20" style="width:180px"></html:text>
					</td>	
					<th>����</th>
					<td>
						<html:text property="xm" maxlength="20" style="width:180px"></html:text>
					</td>	
				</tr>	
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" onchange="initZyList();initBjList()" style="width:180px" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
					<th>רҵ</th>
					<td>
						<html:select property="zydm" onchange="initBjList()" style="width:180px" styleId="zy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
					<th>�༶</th>
					<td>
						<html:select property="bjdm" style="width:180px" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>ѧ��</th>
					<td>
						<html:select property="xn" style="width:180px" onchange="query();">
							<html:options collection="xnList" property="xn" labelProperty="xn"/>
						</html:select>
					</td>
					<th>ѧ��</th>
					<td>
						<html:select property="xq" style="width:180px" onchange="query();">
							<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
						</html:select>
					</td>
					<th>ע��״̬</th>
					<td>
						<html:select property="zczt" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="zcztList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						�������
					</th>
					<td colspan="5">
						<html:select property="sfqf" style="width:180px">
							<html:option value=""></html:option>
							<html:options collection="sfqfList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
			  </tbody>
			</table>				
		</div>
		</div>
		
		
		<div class="formbox" id="result">
			<h3 class="datetitle_01">
		    <span>
		    	��ѯ���&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">δ�ҵ��κμ�¼��</font> 
		 		 </logic:empty>
		 		 <logic:notEmpty name="rs">
					<font color="blue">��ʾ��˫�����Բ鿴��ϸ��Ϣ��������ͷ��������ֻ�ܶԵ�ǰѧ��ѧ�����ݽ��в�����</font> 
		 		 </logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr align="center" style="cursor:hand">
			      		<td>
			      			<input type="checkbox" />
			      		</td>
						<logic:iterate id="tit" name="topTr" scope="request">
							<td id="${tit.en}"
								onclick="tableSort(this)">
								${tit.cn}
							</td>
						</logic:iterate>
					</tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							ondblclick="view();">
							<td>
								<input type="checkbox" name="cbv" value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>"/>
								<input type="hidden" name="sfkzc" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
								<input type="hidden" name="zcztArr" value="<logic:iterate id="v" name="s" offset="7" length="1">${v}</logic:iterate>"/>
							</td>
							<logic:iterate id="v" name="s" offset="1">
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
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxjzcForm"></jsp:include>
			    <!--��ҳ��ʾ-->
			</logic:notEmpty>
			</div>
		</html:form>

		 <logic:present name="result">
			<logic:equal value="true" name="result">
				<script language="javascript">
	         	alert("�����ɹ���");
	         	//document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
	       	 	 alert("����ʧ�ܣ�");
				//document.getElementById('search_go').click();
	         	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
