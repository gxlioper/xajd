<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
		<script language="javascript">	
	function qdbffr(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var flag = false;
		var xhzgh = "";
		if($("ffbz").value==""){
			alert("���챸ע����Ϊ��!");
			return false;
		}
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		if(flag){
			//url+="&doType="+doType;
			if (confirm("ȷ��Ҫ��ѡ��ԱΪ���Ŷ���?")) {
				if(checkBoxArr.length>1000){
					alert("���ݳ���1000�������ٶ��е����������ĵȴ�");
				}
				var url = "/xgxt/zjxyRcsw.do?method=swffFfjgUpdate";
				url+="&doType=ff";
				refreshForm(url);
				return true;		
			}
		}else{
			alert("�빴ѡҪ���ŵ���Ա��Ϣ!!");
			return false;
		}
	}
	
	function showSaveXm(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var flag = false;
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				flag = true;
			}
		}
		
		if(flag){
			viewTempDiv('���챸ע','divq',360,200);
		}else{
			alert("�빴ѡҪ���ŵ���Ա��Ϣ!");
			return false;
		}
		
	}
	</script>
	</head>
	<body >
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>��Ϣά�� - ����������Ϣ</a>
			</p>
		</div>
		<html:form action="/zjxyRcsw" method="post">
				<%@ include file="/comm/hiddenValue.jsp"%>
				<html:hidden property="xn" styleId="xn" value="${xmzj.xn}"/>
				<html:hidden property="xq" styleId="xq"  value="${xmzj.xq}"/>
				<html:hidden property="nd" styleId="nd"  value="${xmzj.nd}"/>
				<html:hidden property="xmlx" styleId="xmlx"  value="${xmzj.xmlx}"/>
				<html:hidden property="ffsj" styleId="ffsj"  value="${xmzj.ffsj}"/>
				
				<html:hidden property="ffr" styleId="ffr" value="${userName }"/>
				<div class="toolbox">
					<!-- ��ť -->
					<div class="buttonbox">
						<ul>
								<li>
									<a href="#"
										onclick="showSaveXm();return false;"
										class="btn_ccg">����</a>
								</li>
								<li>
									<a href="#" onclick="allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffFfjgManage&doType=query','800','480');" class="btn_fh"> ���� </a>
								</li>
						</ul>
					</div>
				</div>
				<div class="toolbox">
					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
								<tr>
									<td colspan="8">
										<div class="bz">
											<font color="blue">
											��Ŀ���ƣ�${xmzj.xmmc}
											&nbsp;&nbsp;&nbsp;ѧ�꣺${xmzj.xn
											}&nbsp;&nbsp;&nbsp;ѧ�ڣ�${xmzj.xqmc
											}&nbsp;&nbsp;&nbsp;��ȣ�${xmzj.nd}&nbsp;&nbsp;&nbsp;����ʼʱ�䣺${xmzj.ffsj}
											&nbsp;&nbsp;&nbsp;�������ʱ�䣺${xmzj.ffjssj}</font>
										</div>
										<div class="btn">
											<input type="hidden" name="go" value="a" />
											<button type="button" class="btn_cx" id="search_go"
												onclick="allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=swffFfjgUpdate&doType=query');">
												�� ѯ
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
											<html:select property="nj" 	onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
										<th>
											ѧ��
										</th>
										<td>
											<html:text property="xh" style="width:85px" maxlength="20" />
										</td>
										<th>
											����
										</th>
										<td>
											<html:text property="xm" style="width:85px" maxlength="20" />
										</td>
									</tr>
									<tr>
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
										</th>
										<td>
											<html:select property="xydm" style="width:200px" styleId="xy"
												onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</td>
										<th>
											רҵ
										</th>
										<td>
											<html:select property="zydm" style="width:200px" styleId="zy"
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
												<html:select property="bjdm" style="width:200px" styleId="bj">
													<html:option value=""></html:option>
													<html:options collection="bjList" property="bjdm"
														labelProperty="bjmc" />
												</html:select>
										</td>
									</tr>
									</tbody>
						</table>
					</div>
					<div class="formbox">
						<h3 class="datetitle_01">
							<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rsList">
									<font color="red">δ�ҵ��κμ�¼��</font>
								</logic:empty> </span>
						</h3>

						<logic:notEmpty name="rsList">
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rsList" id="s" indexId="index">
										<tr onclick="rowOnClick(this);" style="cursor:hand">
											<td align="center">
												<input type="checkbox" id="checkVal" name="checkVal"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
							<!--��ҳ��ʾ-->
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
							<!--��ҳ��ʾ-->
						</logic:notEmpty>
					</div>
				</div>
				<div id="divq" style="display: none">
				<div class="tab">
				<table class="formlist">
					
					<tbody>
					<tr>
						<th>
							<font color="red">*</font>���챸ע<br/>
							<font color="red">(��500��)</font>
						</th>
						<td>
							<textarea rows="3" id="ffbz" name="ffbz" style="width:200px" ></textarea>	
						</td>
					</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan='2' align='right'>
							<button type="button" onclick='qdbffr()'>ȷ��</button>
						</td>
					</tr>
					</tfoot>
				</table>
				</div>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						 
         				 alert("����ɹ���");
       				 </script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
         				 alert("����ʧ�ܣ�");
        			</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
