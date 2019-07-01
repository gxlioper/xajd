<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		jQuery(function(){
				if(jQuery("#tjlx").val()=="xy"){
					jQuery("#bj").val("");
					jQuery("#zy").val("");
					jQuery("#nj").val("");
					if(jQuery("#userType").val()!="xy"){
						jQuery("#xy").attr("disabled",false);
					}
					jQuery("#bj").attr("disabled",true);
					jQuery("#zy").attr("disabled",true);
					jQuery("#nj").attr("disabled",true);
				}else if(jQuery("#tjlx").val()=="zy"){
					jQuery("#bj").val("");
					jQuery("#nj").val("");
					jQuery("#zy").attr("disabled",false);
					jQuery("#bj").attr("disabled",true);
					if(jQuery("#userType").val()!="xy"){
						jQuery("#xy").attr("disabled",false);
					}
					jQuery("#nj").attr("disabled",true);
				}else if(jQuery("#tjlx").val()=="bj"){
					jQuery("#zy").attr("disabled",false);
					jQuery("#bj").attr("disabled",false);
					if(jQuery("#userType").val()!="xy"){
						jQuery("#xy").attr("disabled",false);
					}
					jQuery("#nj").attr("disabled",false);
				}
		
		
			jQuery("#tjlx").change(function(){
				
				if(jQuery("#tjlx").val()=="xy"){
					jQuery("#bj").val("");
					jQuery("#zy").val("");
					jQuery("#nj").val("");
					if(jQuery("#userType").val()!="xy"){
						jQuery("#xy").attr("disabled",false);
					}
					jQuery("#bj").attr("disabled",true);
					jQuery("#zy").attr("disabled",true);
					jQuery("#nj").attr("disabled",true);
				}else if(jQuery("#tjlx").val()=="zy"){
					jQuery("#bj").val("");
					jQuery("#nj").val("");
					jQuery("#zy").attr("disabled",false);
					jQuery("#bj").attr("disabled",true);
					if(jQuery("#userType").val()!="xy"){
						jQuery("#xy").attr("disabled",false);
					}
					jQuery("#nj").attr("disabled",true);
				}else if(jQuery("#tjlx").val()=="bj"){
					jQuery("#zy").attr("disabled",false);
					jQuery("#bj").attr("disabled",false);
					if(jQuery("#userType").val()!="xy"){
						jQuery("#xy").attr("disabled",false);
					}
					jQuery("#nj").attr("disabled",false);
				}
			}); 
			
			
			
			
			
		});
		
		function checkXnXq(){
			if(jQuery("#xn").val()==""){
				alert("ѧ�����ѡ��!");
				return false;
			}
			
			if(jQuery("#xq").val()==""){
				alert("ѧ�ڱ���ѡ��!")
				return false;
			}
			return true;
		}
		</script>
	</head>
	<body onload="">
		
		<html:form action="/cdfzPjpy" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userName" id="userName" value="${userName}" />
			<input type="hidden" name="userType" id="userType" value="${userType}" />
		    <input type="hidden" name="isFdy" id="isFdy" value="${fdyQx}" />
		    <input type="hidden" name="isBzr" id="isBzr" value="${BzrQx}" />
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			
			<div class="toolbox">
			 <!-- ��ť -->
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" id="a_dc" onclick="if(checkXnXq()){expData('/xgxt/cdfzPjpy.do?method=printJxhz')}" class="btn_dc"> ���� </a> </li>
			    </ul>
			 </div>
			<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="8">
			            <div class="btn">
			              <button class="btn_cx" id="search_go" 
			              	onclick="if(checkXnXq()){allNotEmpThenGo('/xgxt/cdfzPjpy.do?method=jxhzManage')}">
			              	�� ѯ
			              </button>
			              &nbsp;&nbsp;&nbsp;&nbsp;
			              <button class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	�� ��
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>

					<tbody>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								<html:select property="nj" styleId="nj"  style="width:120px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								<logic:equal name="userType" value="xy">
									<html:select property="queryequals_xydm" styleId="xy" disabled="true" value="${userDep }" style="width:120px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
									<html:hidden property="xydm" value="${userDep }"/>
								</logic:equal>
								
								<logic:notEqual name="userType" value="xy">
								<html:select property="xydm" styleId="xy"  style="width:120px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								</logic:notEqual>
							</td>
							<th>
								רҵ
							</th>
							<td>
								<html:select property="zydm" styleId="zy"  style="width:120px"
										onchange="initBjList();">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								�༶
							</th>
							<td>
								<html:select property="bjdm" styleId="bj"  style="width:120px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>	
						<tr id="showTr" >
							<th>
								<font color="red">*</font>ͳ�����
							</th>
							<td>
								<html:select property="tjlx" styleId="tjlx" style="width:120px" 
									>
									<html:option value="xy"><bean:message key="lable.xb" /></html:option>
									<html:option value="zy">רҵ</html:option>
									<html:option value="bj">�༶</html:option>
								</html:select>
							</td>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td>
								<html:select property="xn" styleId="xn"  style="width:120px"
										>
										<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td>
								<html:select property="xq" styleId="xq"  style="width:120px"
										>
										<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
							</td>
							<th>
								
							</th>
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
			    </span>
			    </h3>
	
			<logic:notEmpty name="rs">
			<div class="con_overlfow">
				 <table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
							<tr>
								<td rowspan="2">
									<logic:equal name="tjlx" value="xy">
									ϵ��
									</logic:equal>
									<logic:equal name="tjlx" value="zy">
									רҵ
									</logic:equal>
									<logic:equal name="tjlx" value="bj">
									�༶
									</logic:equal>
								</td>
								<td rowspan="2">
									��������
								</td>
								<logic:iterate id="xm" name="xmList">
									<td colspan="2">
										${xm.mc}
									</td>
								</logic:iterate>
								<td colspan="2">
									�ϼ�
								</td>
							</tr>
							<tr>
								<logic:iterate id="xm" name="xmList">
									<td>
										����
									</td>
									<td>
										���
									</td>
								</logic:iterate>
								<td>
									����
								</td>
								<td>
									���
								</td>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);"
								style="cursor:hand">
								<logic:iterate id="v" name="s" offset="1">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
<%--					<jsp:include flush="true"--%>
<%--						page="/sjcz/turnpage.jsp?form=mdqrForm"></jsp:include>--%>
<%--					<script type="text/javascript">--%>
<%--							$('choose').className="hide";--%>
<%--					</script>--%>
			</div>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>	
  </body>
</html>

