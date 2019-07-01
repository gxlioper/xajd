<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getJxglTyDAO.js"></script>
		
		<script language="javascript">
		function searchRs(){
		
			allNotEmpThenGo('/xgxt/jxgl_jxjg_jxmd.do');
		}
		
		function show(pk,pkValue,objId,jb){
			
			getJxglTyDAO.getJxjzList(pk, pkValue,jb,function(data){
				DWRUtil.removeAllOptions(objId);			
				DWRUtil.addOptions(objId,data,jb+"dm",jb+"mc");	
			});
		}
		
		function showView(url){
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alertError('��ѡ��Ҫ�鿴�������У�');
				return false;
			}
		}
		
		function expData(url){
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		//���ɵ�ǰ��ȵ�ǰѧ������
		function scmd(){
			getJxglTyDAO.scmd(function(data){
				if(data){
					alertInfo("���������ɹ�","",function(){});
					searchRs();
				}else{
					alertInfo("��������ʧ��","",function(){});
				}
			});
		}
		</script>
	</head>
	<body onload="">

		<html:form action="/jxglJxjg" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm}"/>
			<!-- ������ -->
			
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul><!-- ����ʦ��ѧԺ -->
						<logic:equal name="lx" value="mdsc">
						<li><a href="#" onclick="scmd();return false;" class="btn_sx">��������</a></li>
						</logic:equal>
						<!-- ����ʦ��ѧԺend -->
						<li><a href="#" onclick="expData('/xgxt/jxglJxjg.do?method=expExcel');return false;" class="btn_dc">����</a></li>
						
					</ul>
				</div>
				<div class="searchtab">
			<table width="100%" border="0">
			      <tfoot>
			        <tr>
			          <td colspan="6">
			            <div class="btn">
			              <button type="button" class="btn_cx" id="search_go" 
			              	onclick="searchRs();return false;">
			              	�� ѯ
			              </button>
			              &nbsp;&nbsp;&nbsp;&nbsp;
			              <button type="button" class="btn_cz" id="btn_cz"  	onclick="searchReset();return false;">
			              	�� ��
			              </button>
			            </div>
			          </td>
			        </tr>
			      </tfoot>

					<tbody>
						<tr>
							
							<th >
								ѧ��
							</th>
							<td>
								<html:text property="xh" styleId="xh"/>
							</td>
							<th>
								����
							</th>
							<td>
								<html:text property="xm" styleId="xm"/>
							</td>
							<th>
								�꼶
							</th>
							<td>
								<html:select property="nj" styleId="nj"  style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								<logic:equal name="userType" value="xy">
									<html:select property="xydm" styleId="xy" disabled="true" value="${userDep }" style="width:150px"
										onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
									</html:select>
									<html:hidden property="xydm" value="${userDep }"/>
								</logic:equal>
								<logic:notEqual name="userType" value="xy">
								<html:select property="xydm" styleId="xy"  style="width:150px"
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
								<html:select property="zydm" styleId="zy"  style="width:150px"
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
								<html:select property="bjdm" styleId="bj"  style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>	
						<tr>
							<th>
								�ż�
							</th>
							<td>
								<html:select property="dm1" styleId="dm1"  style="width:150px"
									onchange="show('tjdm',this.value,dm2,'yj');show('tjdm',this.value,dm3,'lj')"
										>
										<html:option value=""></html:option>
										<html:options collection="firstList" property="bzdm"
										labelProperty="bzmc" />
								</html:select>
							</td>
							<th>
								Ӫ��
							</th>
							<td>
								<html:select property="dm2" styleId="dm2"  style="width:150px"
										onchange="show('yjdm',this.value,dm3,'lj')">
										<html:option value=""></html:option>
										<html:options collection="secoundList" property="bzdm"
										labelProperty="bzmc" />
								</html:select>
							</td>
							<th>
								����
							</th>
							<td>
								<html:select property="dm3" styleId="dm3"  style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="thirdList" property="bzdm"
										labelProperty="bzmc" />
								</html:select>
							</td>
						</tr>		                       
					</tbody>
				</table>
			</div>
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty>  
						
					</span>
				</h3>

				
				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" >
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" 
									ondblclick="showView('/xgxt/jxglJxjg.do?method=jxmdOne',800,600);return false;" style="cursor:hand">
									<td style="display:none">
										<input type="hidden" name="pkValue" id="pkValue" value="<logic:iterate id="v" name="s"  length='1'>${v}</logic:iterate>"/>
										
									</td>
									<logic:iterate id="v" name="s" >
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<!-- ������ -->
							<%@ include file="/comm/noRows.jsp"%>
						<!-- ������ end-->
						
						</tbody>
						
					</table>
					</div>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jxglJxjgForm"></jsp:include>
				
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				$("doType").value="";
				alertError("${message}!");	
			</script>
		</logic:present>
	</body>
	<%@ include file="/comm/loading.jsp"%>
</html>
