<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
	<body onload="xyDisabled('xy')">		
		<html:form action="/stu_work_checkInit.do?doType=gzjl" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType}" />
			<input type="hidden" id="tableName" name="tableName" value="${tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable}" />
			<input type="hidden" id="pk" name="pk" value="${pk}" />
			<input type="hidden" id="bmlb" name="bmlb" value="xy" />

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${tips }</a>
				</p>
			</div>
			<div class="toolbox">	
			<!-- ��ť -->
			  <div class="buttonbox">
			    <ul>
					<!--��дȨ-->
					<logic:equal value="yes" name="writeAble">						
					<%--�ȷ�ʦ��ѧԺ--%>
					<logic:equal value="10100" name="xxdm">
						<li> <a href="#" onclick="javascript: var xh=curr_row.cells[5].innerText;var gwmc=curr_row.cells[3].innerText;window.open('/xgxt/qgzxLogic.do?method=printReport&&xh='+xh+'&&gwmc='+gwmc,600,800,false)" class="btn_dy">�¿��˱�</a> </li>
				   </logic:equal>
			       <%--end�ȷ�ʦ��ѧԺ--%>
					
					<%--�Ϻ����̼�����ѧ--%>
					<logic:equal value="10856" name="xxdm">
						<li> <a href="#" onclick="refreshForm('qgzxShgc.do?method=printYrdwXskh')" class="btn_dy">�� ӡ</a> </li>
					</logic:equal>
					<%--end�Ϻ����̼�����ѧ--%>
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
								onclick="refreshForm('/xgxt/stu_work_checkInit.do?doType=gzjl&go=go')">
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
						<html:select property="xn" style="width:120px" 
							styleId="xn">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
					<th>���</th>
					<td>
						<html:select property="nd" style="width:90px"
							styleId="nd">
							<html:options collection="xnList" property="nd"
								labelProperty="nd" />
						</html:select>
					</td>						
					<th>�꼶</th>
					<td>
						<html:select property="nj" style="width:90px;padding-left:80px">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>				
				</tr>						
				<tr>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select property="xydm" style="width:180px" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>					
					<th>��λ����</th>
					<td>
						<html:select property="xmdm" style="width:230px" styleId="xmdm">
							<html:option value=""></html:option>
							<html:options collection="gwList" property="gwdm"
								labelProperty="gwdm" />
						</html:select>
					</td>
					<th>��λ����</th>
					<td>
						<html:select property="gwxz"  style="width:120px" styleId="gwxz">
							<html:option value=""></html:option>
							<html:options collection="gwxzList" property="gwxzdm"
								labelProperty="gwxzmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>���˵�λ</th>
					<td>
						<html:select property="yrdwdm"  style="width:120px" styleId="yrdwdm">
							<html:option value=""></html:option>
							<html:options collection="yrdwList" property="yrdwdm"
								labelProperty="yrdwmc" />
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
		</div>

		<div class="formbox">
			<h3 class="datetitle_01">
		    <span>
		    	��ѯ���&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">δ�ҵ��κμ�¼��</font> 
		 		 </logic:empty>
				<logic:notEmpty name="rs">
					��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ�������Ե�����λ��������ͷ��������
				</logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <table summary="" class="dateline" width="100%" id="rsTable">
			    <thead>
			      <tr>
			      	<logic:iterate id="tit" name="topTr" offset="2">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)" nowrap>
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
			      </tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							style="cursor:hand;background-color:
						    <logic:iterate id="v" name="s" offset="0" length="1">
						    ${v}
						    </logic:iterate>
						    "		
							<%--�й�����ѧԺ--%>
							<logic:equal value="10355" name="xxdm">
							ondblclick="updateOrViewOne('/xgxt/stu_work_info.do?act=view&pkVal=',900,760)"
							</logic:equal>
							<%--end�й�����ѧԺ--%>
							<logic:notEqual value="" name="xxdm">
							ondblclick="updateOrViewOne('/xgxt/stu_work_info.do?act=view&pkVal=')"
							</logic:notEqual>>
							<td>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="2" length="1">
									<bean:write name="v" />
								</logic:iterate>
							</td>
							<logic:iterate id="v" name="s" offset="3">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</tbody>			
			   </table>
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
                                                                                                   
