<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
	<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	<script type="text/javascript" src="pjpy/zjcm/cssz/cssz.js"></script>
	<script type="text/javascript" src="pjpy/zjkjxy/cssz.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
</head>
<body onload="xyDisabled('xy');dispconf('fs')">
	<html:form action="/pjpyzjkj.do?method=rsszManage" method="post">		
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
		<input type="hidden" id="doType" name="doType" value=""/>
		<input type="hidden" id="userType" name="userType" value="${userType}"/>
		<input type="hidden" id="message" name="message" value="${message}"/>
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
    	<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<div class="compTab" id="card" style="width: 100%">
			<div class="comp_title" id="div1">
				<ul id="ul1">
					<li class="ha">
						<a href="#" onclick="refreshForm('pjpyzjkj.do?method=rsszManage')">
							<span>��ѧ���������</span>
						</a>
					</li>
					<!--����ְͨҵ��ѧ-->
					<logic:notEqual value="11052" name="xxdm">
					<li class="">
						<a href="#" onclick="refreshForm('pjpyzjkj.do?method=rychRsszManage')">
							<span>�����ƺű�������</span>
						</a>
					</li>
					</logic:notEqual>
					<!--end����ְͨҵ��ѧ-->					
				</ul>				
			</div>
		</div>
		<div class="toolbox">
		  <!-- ��ť -->				  
		  <div class="buttonbox">
		    <ul>
			  <!--��дȨ-->
			  <logic:equal value="yes" name="writeAble">
			  <!--��ѧԺ�û�-->
			  <logic:notEqual value="xy" name="userType">
					<li> <a href="#" onclick="showTopWin('viewTotStuNum.do',700,500);" class="btn_ck">�鿴��������</a> </li>
				    <li> <a href="#" onclick="baseDataInit('jxj');" class="btn_csh">��ʼ������</a> </li>
					<li> <a href="#" onclick="showTopWin('pjpyzjkj.do?method=jxjblPlsz',500,500);" class="btn_sz">�������ñ���</a> </li>
				</logic:notEqual>
				<!--end��ѧԺ�û�-->
				<!--��ְͨҵ��ѧ-->
				<logic:equal value="11052" name="xxdm">
				<li> <a href="#" onclick="rstz('pjpyzjkj.do?method=jxjrstz&act=qry','jxj');" class="btn_ccg">����</a> </li>
				</logic:equal>
				<!--end��ְͨҵ��ѧ-->
				<!--����ְͨҵ��ѧ-->
				<logic:notEqual value="11052" name="xxdm">
				<li> <a href="#" onclick="rstz('pjpyzjkj.do?method=jxjrstz&act=qry','jxj');" class="btn_ccg">��������</a> </li>
				</logic:notEqual>
				<!--end����ְͨҵ��ѧ-->
				</logic:equal>
			</div>
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
							onclick="refreshForm('pjpyzjkj.do?method=rsszManage&act=qry');">
							��ѯ
						</button>
		            </div>
		          </td>
		        </tr>
		      </tfoot>
			  <tbody>
			  <!--��������Ϊѧ��-->
			  <logic:equal value="xn" name="pjzq">
				 <tr>
					<th>�꼶</th>
					<td>
						<html:select property="nj" styleId="nj" onchange="initZyList();initBjList();refreshForm('pjpyzjkj.do?method=rsszManage&act=qry');"
							 styleClass="select" style="width:90px">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
					</td>
					<th>ѧ��</th>
					<td>
						<html:select property="xn" style="width:90px" styleClass="select"
								styleId="xn" disabled="true">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						<html:hidden property="xn"/>
					</td>
					<th></th>
					<td>		
					</td>
				  </tr>
				</logic:equal>
				<!--end��������Ϊѧ��-->

				<!--��������Ϊѧ��-->
				<logic:equal value="xq" name="pjzq">
				  <tr>
						<th>�꼶</th>
						<td>
							<html:select property="nj" styleId="nj" onchange="initZyList();initBjList();refreshForm('pjpyzjkj.do?method=rsszManage&act=qry');"
								 styleClass="select" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn" disabled="true">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							<html:hidden property="xn"/>
						</td>
						<th>ѧ��</th>
						<td>
							<html:select property="xq" styleId="xq" disabled="true">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
							</html:select>
							<html:hidden property="xq"/>
						</td>
				  </tr>
				  </logic:equal>							
				  <!--end��������Ϊѧ��-->

				  <!--��������Ϊ���-->
				  <logic:equal value="nd" name="pjzq">
					<tr>
						<th>�꼶</th>
						<td>
							<html:select property="nj" styleId="nj" onchange="initZyList();initBjList();refreshForm('pjpyzjkj.do?method=rsszManage&act=qry');"
								 styleClass="select" style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj" labelProperty="nj"/>
								</html:select>
						</td>
						<th>���</th>
						<td>
							<html:select property="nd" styleId="nd" disabled="true">
								<html:options collection="ndList" property="nd" labelProperty="nd"/>
							</html:select>
							<html:hidden property="nd"/>
						</td>
						<th></th>
						<td>		
						</td>
				    </tr>
				 </logic:equal>
				 <!--end��������Ϊ���-->
				  <tr>
				
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>	
					<html:select property="xydm" onchange="initZyList();initBjList();refreshForm('pjpyzjkj.do?method=rsszManage&act=qry');"
						styleClass="select" style="width:180px" styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
					</html:select>	
				</td>
				<th>רҵ</th>
				<td>
					<html:select property="zydm" onchange="initBjList();refreshForm('pjpyzjkj.do?method=rsszManage&act=qry');" style="width:180px" 
					styleClass="select" styleId="zy">
						<html:option value=""></html:option>
						<html:options collection="zyList" property="zydm"
							labelProperty="zymc" />
					</html:select>								
				</td>
				<th>�༶</th>
				<td>	
					<html:select property="bjdm" style="width:180px" 
						styleClass="select" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>		
				</td>
			  </tr>	
					<tr>
					<th>��ѧ�����</th>
					<td>
						<html:select property="jxjlb" styleId="jxjlb" onchange="changeJxj('pjpyzjkj.do?method=rsszManage&act=qry')">
							<html:options collection="lbList" property="dm" labelProperty="mc"/>
						</html:select>
					</td>
					<th>��ѧ��</th>
					<td>	
						<html:select property="jxjdm" styleId="dm"  style="width:150px" onchange="refreshForm('pjpyzjkj.do?method=rsszManage&act=qry');">
							<html:options collection="dmList" property="dm" labelProperty="mc"/>
						</html:select>	
					</td>
					<th>��ʾ��Χ</th>
					<td>
						<html:select property="fs" styleId="fs" onchange="dispconf('fs');refreshForm('pjpyzjkj.do?method=rsszManage&act=qry');">
							<html:options collection="cpfwList" property="dm" labelProperty="mc"/>
						</html:select>
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
							<font color="blue">��ʾ��<logic:notEqual value="11052" name="xxdm">˫��һ�п��Բ鿴��ϸ��Ϣ;</logic:notEqual>������ͷ���Խ�������</font>
						</logic:notEmpty>
				    </span>
				    </h3>			 
				<logic:notEmpty name="rs">
				<div class="con_overlfow">
				<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
				<thead>
			      <tr>
			        <td>
						<input type="checkbox" id="cb" name="cb" onclick="selectAll()" style="height: 17.5px"/>
					</td>
					<logic:iterate id="tit" name="topTr" offset="1" scope="request">
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
							style="cursor:hand;" 
							ondblclick="modiAndDel('pjpy_zjcm_jxjrstz.do?act=view&pkValue=','modi','620','520');">
							<td align=center>
							<input type="checkbox" id="cbv" name="cbv" style="height: 17.5px"
							 value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
							<input type="hidden" name="checkVal" value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
						    </td>
							<logic:iterate id="v" name="s" offset="1" length="11">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="12">
								<td>
									<input type="text" id="jxjrs" name="jxjtzrs" value="${v}"/>													
								</td>
							</logic:iterate>
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
			<div id="tmpdiv"></div>
	</html:form>
	<!-- ��ʾ��Ϣ -->
	<%@ include file="tsxx.jsp"%>
	<!-- ��ʾ��Ϣ end-->
</body>