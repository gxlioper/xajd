<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script>   
		var mb = 0;			
		function printZS(mod){
			var url="";
			var mb=document.getElementById("mb").value;
			if(curr_row==null){
				 alert('��ѡ��Ҫ��ӡ�ļ�¼��');
				 return false;
			}
			var xh = curr_row.cells[0].innerText;
			var zmlx = curr_row.cells[7].innerText;
			window.open("certificatePrintAll.do?doType=printone&xh="+xh+"&mb="+mb + "&zmlx="+zmlx);
		}
	
		function check_users()
		{
			var user=document.all['userType'].value;
			if("xy"==user)
			{
				document.getElementById('xy').disabled=true;
			}
			else if("xx"==user)
			{
				document.getElementById('xy').disabled=false;
			}
		}
	</script>
	<style media="print">
		.noprint{
			display:none;
		}
		.print{
			display:block;
		}
	</style>
</head>	
	<body onload="check_users()">
		<html:form action="/certificate_print.do" method="post">
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />		
			<input type="hidden" value="${userType}" id="userType" name="userType"/>
			<input type="hidden" value="" id="nj" name="nj"/>
			<input type="hidden" value="a" id="xmdm" name="jxjdm"/>
			<input type="checkbox" style="display:none" id="chgMode"/>

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips}</a>
				</p>
			</div>
			<div class="toolbox noprint">				 
				  <!-- ��ť -->				  
				  <div class="buttonbox">
					   <ul>
							<li> <a href="#" onclick="printZS(0);" class="btn_dy">��ӡ����ҳ</a> </li>
							<li> <a href="#" onclick="printZS(1);" class="btn_dy">������ӡ</a> </li>
							<li> <a href="#" onclick=" viewTempDiv('��ʾ��Ϣ','xxtsDiv',350,100);" class="btn_dy">֤������</a> </li>
					   </ul>
				  </div>

				  <logic:notEqual value="student" name="user">
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>						  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button id="search_go"
									onclick="allNotEmpThenGo('/xgxt/certificate_print.do')">
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
							<html:text property="xh" styleId="xh" style="width:180px"></html:text>
						</td>
						<th>֤������</th>
						<td>
							<html:select property="zmlx" styleId="zmlx" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="typeList" labelProperty="cn" property="en"/>	
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="xydm" styleId="xy"
								onchange="initZyList();initBjList();" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>רҵ</th>
						<td>
							<html:select property="zydm" styleId="zy"
								onchange="initBjList();" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>
						</td>
						<th>�༶</th>
						<td>
							<html:select property="bjdm" styleId="bj" style="width:180px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  <tr>
						<th>ѡ��ģ��</th>
						<td>
							<html:select property="mb" styleId="mb" style="width:180px">
								<html:option value=""></html:option>										
								<html:option value="2">����ģ��</html:option>
								<html:option value="3">Ӣ��ģ��</html:option>
							</html:select>
						</td>
						<th></th>
						<td>
							
						</td>
						<th></th>
						<td>
							
						</td>
					  </tr>
					  </tbody>
					</table>
				</div>
				</logic:notEqual>
				</div>
		
				<div class="formbox">
					<h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
				    	<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font> 
				 		 </logic:empty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <logic:iterate id="tit" name="topTr">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
				<tbody id="tabPri">
			      <logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)" style="cursor:hand;">
							<logic:iterate id="v" name="s">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
			    </tbody>
				</table>
				</div>
				</logic:notEmpty>
				</div>
			<div id="xxtsDiv" style="display:none">
				<table class='formlist'>
					<tbody>
					<tr><td>�˲�����������ӡ��ʾ��ѧ������,ȷ��Ҫ���˲�����</td></tr>
					</tbody>
					<tfoot>
					<tr>
						<td align='center'><button onclick='exePrint()'>ȷ��</button>
						<button onclick="hiddenMessage(true,true);">�ر�</button>
						</td>
					</tr>
					</tfoot>
					</table>
			</div>
			<div id="tempdiv"></div>
		</html:form>
	</body>
</html>
