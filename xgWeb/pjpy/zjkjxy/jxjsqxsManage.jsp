<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="js/xgutil.js"></script>
<script language="javascript" src="js/xszz/xszzFunction.js"></script>
<script language="javascript">
function setKgzt(value,num){
	var id = "kgzt"+num;
	$(id).value = value;
}

function setAllKgzt(value,num){
	var s = document.getElementsByTagName('input');
	for(var i=0; i<s.length;i++){
		if(s[i].type=="radio"){
			if(s[i].name != "kg"){
				if(s[i].value == value){
					s[i].checked = true;
				}
			}
		}else if(s[i].type =="hidden"){
			if(s[i].name == "sqzt"){
				s[i].value = value;
			}
		}
	}
}

function saveSqzt(){
	//�ж��Ƿ�������
	if(ele('rsTable') == null || ele('rsTable') == undefined){
		alert('û�пɱ�������ݣ�');
		return false;
	}
	if (confirm('ȷ�������Ƿ��������Ϣ��')){
		saveUpdate('/xgxt/pjpyzjkj.do?method=jxjsqxsManage&doType=save','');
	}
}
</script>
</head>
	<body onload="">
		<html:form action="/pjpyzjkj.do?method=jxjsqxsManage">
			<!-- ������ -->
			<%@ include file="/xszz/hiddenValue.jsp"%>
			<!-- ������ end-->
			<!-- ���� -->
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<!-- ���� end-->
			<!-- ������Ϣ -->
			<logic:notEmpty name="msg">
				<div align="center">
					<FONT color="red" size="6"><bean:write name="msg" /></FONT>
				</div>
			</logic:notEmpty>
			<!-- ������Ϣ end-->

			<!-- �޴��� -->
			<logic:empty name="msg">
				<div class="compTab" id="card" style="width: 100%">
					<div class="comp_title" id="div1">
						<ul id="ul1">
							<li id="li_jxj" class="ha">
								<a href="#" onclick="refreshForm('pjpyzjkj.do?method=jxjsqxsManage')">
									<span>��ѧ��</span>
								</a>
							</li>
							<li id="li_rych">
								<a href="#" onclick="refreshForm('pjpyzjkj.do?method=rychsqxsManage')">
									<span>�����ƺ�</span>
								</a>
							</li>						
						</ul>				
					</div>
				</div>

				<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
						<logic:notEmpty name="yhInfo">
							<li> <a href="#" onclick="saveSqzt();" class="btn_ccg" disabled="disabled">����</a> </li>
						</logic:notEmpty>	
						<logic:empty name="yhInfo">
							<li> <a href="#" onclick="saveSqzt();" class="btn_ccg">����</a> </li>
						</logic:empty>		
					  </logic:equal>
						<li> <a href="#" onclick="wjcfDataExport('pjpyzjkj.do?method=jxjsqxsManage&doType=exp');" class="btn_dc">��������</a> </li>
					</ul>					
				  </div>

				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>				
						  <td colspan="">
							<!--����������ʾ-->
							<logic:notEmpty name="yhInfo">
								<font color="red">��ʾ��${ yhInfo}</font>
							</logic:notEmpty>
							<!--end����������ʾ-->
						  </td>		  
				          <td colspan="5">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
								<button type="button" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/pjpyzjkj.do?method=jxjsqxsManage&doType=query');">
									��ѯ
								</button>
								<button type="button" id="cz"
									onclick="czSearchCond('nj-xh-xm-xy-zy-bj-sfksq');return false;">
									����
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
					  <tbody>
					  <tr>
						<th>ѧ��</th>
						<td>
							<html:select property="queryequals_xn" 
								styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
						<th>���</th>
						<td>
							<html:select property="queryequals_nd" 
								styleId="xn" disabled="true">
								<html:options collection="ndList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="queryequals_xq" 
								styleId="xn" disabled="true">
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					  </tr>		
					  <tr>
						<th>�꼶</th>
						<td>
							<html:select property="queryequals_nj"
								onchange="initZyList();initBjList()" styleId="nj">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:text property="querylike_xh" maxlength="20" styleId="xh" style="width:80px"></html:text>						
						</td>
						<th>����</th>
						<td>
							<html:text property="querylike_xm" maxlength="20" styleId="xm" style="width:80px"></html:text>
						</td>
					  </tr>		
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="queryequals_xydm"
								onchange="initZyList();initBjList()" styleId="xy"
								style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>רҵ</th>
						<td>
							<html:select property="queryequals_zydm" onchange="initBjList()"
								styleId="zy" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>						
						</td>
						<th>�༶</th>
						<td>
							<html:select property="queryequals_bjdm" styleId="bj"
								style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>
		  			  <tr>
						<th>��ѧ��</th>
						<td>
							<html:select property="queryequals_jxjdm" styleId="jxjdm">
								<html:options collection="jxjList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
						<th>�Ƿ������</th>
						<td>
							<html:select property="queryequals_sfksq" styleId="sfksq">
								<html:option value="">----��ѡ��----</html:option>
								<html:options collection="flagList" property="en"
									labelProperty="cn" />
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
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td width="5%">
<!--						<input type="checkbox" id="selall" name="selall" onclick="selAll()">-->
					</td>
					
					<!--��������ֻ��һ���ֶ�-->
					<logic:equal value="1" name="sqzqLength">										
					<logic:iterate id="tit" name="topTr" offset="6" length="6">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
					<logic:iterate id="tit" name="topTr" offset="12" length="1">
						<td id="<bean:write name="tit" property="en"/>">
							<bean:write name="tit" property="cn" />
							(
							<logic:iterate name="flagList" id="kg" indexId="titleIndex">
								${kg.en }
								<input type="radio" value="${kg.en }" name="kg" onclick="setAllKgzt(this.value,${titleIndex})"/>
							</logic:iterate>
							)
						</td>
					</logic:iterate>
					</logic:equal>
					<!--end��������ֻ��һ���ֶ�-->

					<!--�������������ֶ�-->
					<logic:equal value="2" name="sqzqLength">										
					<logic:iterate id="tit" name="topTr" offset="6" length="7">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
					<logic:iterate id="tit" name="topTr" offset="13" length="1">
						<td id="<bean:write name="tit" property="en"/>">
							<bean:write name="tit" property="cn" />
							<logic:iterate name="flagList" id="kg" indexId="titleIndex">
								${kg.en }
								<input type="radio" value="${kg.en }" name="kg" onclick="setAllKgzt(this.value,${titleIndex})"/>
							</logic:iterate>
						</td>
					</logic:iterate>
					</logic:equal>
					<!--end�������������ֶ�-->

					<!--�����������ֶ�-->
					<logic:empty name="sqzqLength">																				
					<logic:iterate id="tit" name="topTr" offset="6" length="5">
						<td id="<bean:write name="tit" property="en"/>"
							onclick="tableSort(this)">
							<bean:write name="tit" property="cn" />
						</td>
					</logic:iterate>
					<logic:iterate id="tit" name="topTr" offset="11" length="1">
						<td id="<bean:write name="tit" property="en"/>">
							<bean:write name="tit" property="cn" />
							<logic:iterate name="flagList" id="kg" indexId="titleIndex">
								${kg.en }
								<input type="radio" value="${kg.en }" name="kg" onclick="setAllKgzt(this.value,${titleIndex})"/>
							</logic:iterate>
						</td>
					</logic:iterate>
					</logic:empty>
					<!--end�����������ֶ�-->
			      </tr>
			    </thead>
				<tbody>
				<!--����-->
			      <logic:iterate name="rs" id="s" indexId="index">
					<tr onclick="rowOnClick(this);" 
						style="cursor:hand">
						<!-- checkbox -->								
						<logic:iterate id="v" name="s" offset="0" length="1">
							<td align="center">
								<input type="hidden" name="checkVal" value="${v }"/>
								<logic:iterate id="mrxm" name="s" offset="1" length="1">
									<input type="checkbox" id="checkVal"  name="primarykey_checkVal" disabled="disabled"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1" length="1">
									<input type="hidden" name="xhV" value="${v }"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="2" length="1">
									<input type="hidden" name="xnV" value="${v }"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="3" length="1">
									<input type="hidden" name="ndV" value="${v }"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="4" length="1">
									<input type="hidden" name="xqV" value="${v }"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="5" length="1">
									<input type="hidden" name="xmdmV" value="${v }"/>
								</logic:iterate>
							</td>
						</logic:iterate>
						<!--��������ֻ��һ���ֶ�-->
						<logic:equal value="1" name="sqzqLength">
						<!-- ��Ŀ��Ϣ -->		
						<logic:iterate id="v" name="s" offset="6" length="6">
							<td align="left" nowrap="nowrap">								
								<bean:write name="v" />
							</td>
						</logic:iterate>
						<!-- ���� -->		
						<logic:iterate id="v" name="s" offset="12" length="1">
							<td align="center">
								<logic:iterate name="flagList" id="kg" indexId="ind">
									${kg.en }
									<logic:equal name="kg" property="en" value="${v }">
										<input type="radio" value="${kg.en }" name="kg${index }" id="kg${ind}" checked="checked" onclick="setKgzt(this.value,${index })"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="kg" property="en" value="${v }">
										<input type="radio" value="${kg.en }" name="kg${index }" id="kg${ind}" onclick="setKgzt(this.value,${index })"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:notEqual>
								</logic:iterate>
								<input type="hidden" name="sqzt" id="kgzt${index }" value="${v }"/>
							</td>
						</logic:iterate>
						</logic:equal>
						<!--end��������ֻ��һ���ֶ�-->

						<!--�������������ֶ�-->
						<logic:equal value="2" name="sqzqLength">
						<!-- ��Ŀ��Ϣ -->		
						<logic:iterate id="v" name="s" offset="6" length="7">
							<td align="left" nowrap="nowrap">								
								<bean:write name="v" />
							</td>
						</logic:iterate>
						<!-- ���� -->		
						<logic:iterate id="v" name="s" offset="13" length="1">
							<td align="center">
								<logic:iterate name="flagList" id="kg" indexId="ind">
									${kg.en }
									<logic:equal name="kg" property="en" value="${v }">
										<input type="radio" value="${kg.en }" name="kg${index }" id="kg${ind}" checked="checked" onclick="setKgzt(this.value,${index })"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="kg" property="en" value="${v }">
										<input type="radio" value="${kg.en }" name="kg${index }" id="kg${ind}" onclick="setKgzt(this.value,${index })"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:notEqual>
								</logic:iterate>
								<input type="hidden" name="sqzt" id="kgzt${index }" value="${v }"/>
							</td>
						</logic:iterate>
						</logic:equal>
						<!--end�������������ֶ�-->

						<!--�����������ֶ�-->
						<logic:empty name="sqzqLength">
							<!-- ��Ŀ��Ϣ -->		
						<logic:iterate id="v" name="s" offset="6" length="5">
							<td align="left" nowrap="nowrap">								
								<bean:write name="v" />
							</td>
						</logic:iterate>
						<!-- ���� -->		
						<logic:iterate id="v" name="s" offset="11" length="1">
							<td align="center">
								<logic:iterate name="flagList" id="kg" indexId="ind">
									${kg.en }
									<logic:equal name="kg" property="en" value="${v }">
										<input type="radio" value="${kg.en }" name="kg${index }" id="kg${ind}" checked="checked" onclick="setKgzt(this.value,${index })"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:equal>
									<logic:notEqual name="kg" property="en" value="${v }">
										<input type="radio" value="${kg.en }" name="kg${index }" id="kg${ind}" onclick="setKgzt(this.value,${index })"/>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:notEqual>
								</logic:iterate>
								<input type="hidden" name="sqzt" id="kgzt${index }" value="${v }"/>
							</td>
						</logic:iterate>	
						</logic:empty>
					</tr>
				</logic:iterate>
			    </tbody>
				</table>
				</div>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyZjkjxyActionForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
				</logic:notEmpty>
				</div>
			</logic:empty>
			<!-- �޴��� end-->
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="tsxx.jsp"%>
		<!-- ��ʾ��Ϣ end-->
	</body>
</html>