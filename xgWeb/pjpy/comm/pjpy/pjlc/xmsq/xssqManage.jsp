<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">

			function xssqUpdate(opera,obj){
				obj.parentNode.parentNode.click();
				var url = "/xgxt/pjpy_comm_xmsq.do?method=xssqUpdate&opera=" + opera;
				url += "&xmdm="+curr_row.getElementsByTagName('input')[0].value;
				showTopWin(url,800,600);
			}

			function plsz(){
				var url = "/xgxt/pjpy_comm_xmsq.do?method=rsszUpdate&doType";
				url += "&szfw=" + $('szfw').value;
				url += "&xmdm=" + $('xmdm').value;

				showTopWin(url,800,600);
			}
			
			function showYy(id){
				alertInfo($(id).value);
			}
			
			//ǰ����Ŀ�ϱ�
			function goXmsb(){
				var url = "pjpyXmsb.do?method=xmsbManage";
					url+= "&xmdm="+$("xmdm").value;
					url+= "&bjdm="+$("bjdm").value;
				
				showWaitingDiv("30000");
				
				refreshForm(url);
			}
			
			//��ʾ��Ŀ�����б�
			function showXmsqList(){
				var onload = $("onload").value;
				if(onload == ""){
					allNotEmpThenGo('/xgxt/pjpy_comm_xmsq.do?method=xssqManage');
				}
			}
		</script>
	</head>
	<body onload="showXmsqList()">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������� - �ҵ����� - ѧ������</a>
			</p>
			
			<!-- ���߰��� -->
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
			<!-- ���߰��� end -->
			
			<!-- ��ع��� -->
			<p class="other" style="position:relative;">
				<a href="#" onclick="return false;" 
					onmouseover ="displayDiv(['liucheng_bar','liucheng_bar'],'')"
					style="display:block;height:30px;">��ع���</a>
			</p>
			<!-- ��ع��� end-->
		</div>
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				1.��������г������������У����뷽ʽΪ<font color="blue">ѧ������</font>��������Ŀ��</br>
				<logic:equal name="userType" value="stu">
				2.��������������Ŀ����������������Ŀ<font color="blue">��������</font>������ɲ鿴��ϸ��</br>
				3.���Ŀǰ���ڸ���Ŀ������ʱ�䷶Χ�ڣ������Ŀ<font color="blue">��������</font>������ɲ鿴��ϸ��</br>
				4.��������������Ŀ������<font color="blue">����</font>��������չʾҳ����д����������ݡ�</br>
				5.������Ѿ�������ĳ��Ŀ�����Ҹ���Ŀ��δ����һ��λ��ˣ����Ե��<font color="blue">�޸�</font>��
				</logic:equal>
				<logic:notEqual name="userType" value="stu">
				2.���������ĳѧ������ĳ��Ŀ������<font color="blue">����</font>����չʾҳ������ѡѧ������ɲ�����</br>
				</logic:notEqual>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->	
		
		<!-- ��ݷ�ʽ -->
		<div class="liucheng_xg_pj" id="div_other_gnmk" style=""
			onmouseover="displayDiv(['liucheng_bar','liucheng_bar'],'')"
			onmouseout="displayDiv(['liucheng_bar','liucheng_bar'],'none')">
		
			<div class="liucheng_bar" id="liucheng_bar" style="position:absolute;top:30px;right:0px;width:auto;background:#fff;display:none">
				<div class="con">
				
					<div class="liucheng_font floatleft">
				    	<a href="#" onclick="goOtherGnmk('pjpy_mypj.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function79.png" />
							<p>�ҵ�����</p>
						</a>   	
					</div>
					
					<logic:notEqual name="userType" value="stu">
					<div class="liucheng_font floatleft">
						<a href="#" onclick="showTopWin('/xgxt/pjpyXmsb.do?method=sbfwChoose',600,550);return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function46.png" />
							<p>��ʦ�ϱ�</p>
						</a>
					</div>
					</logic:notEqual>
					
					<div class="liucheng_font floatleft">
						<a href="#" onclick="goOtherGnmk('pjpy_pjlc_jgcx.do');return false;">
				    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
							<p>�����ѯ</p>
						</a>
					</div>
			
				</div>
			</div>
		</div>
		<!-- ��ݷ�ʽ end-->
		
		<html:form action="/pjpy_comm_xmsq" method="post">
			<input type="hidden" name="onload" value="${onload }"/>
			<input type="hidden" name="pjxn" value="${jbsz.pjxn }"/>
			<input type="hidden" name="pjxq" value="${jbsz.pjxq }"/>
			<input type="hidden" name="pjnd" value="${jbsz.pjnd }"/>
			<input type="hidden" name="bjdm" id="bjdm" value=""/>
			<input type="hidden" name="xmdm" id="xmdm" value="" />
			<button type="button" id="forward" onclick="goXmsb()" style="display: none">��ת</button>
			<div class="toolbox">
			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>����ѧ��</th>
							<td>
								<html:select property="pjxn" style="width: 150px" styleId="xn" value="${jbsz.pjxn}" disabled="true">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
							<th>����ѧ��</th>
							<td>
								<html:select property="pjxq" style="width: 150px" styleId="xq" value="${jbsz.pjxq}" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
							<th>�������</th>
							<td>
								<html:select property="pjnd" style="width: 150px" styleId="nd" value="${jbsz.pjnd}" disabled="true">
									<html:options collection="ndList" property="nd" labelProperty="nd" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>��Ŀ����</th>
							<td>
								<html:select property="xmlx" style="width: 150px">
									<html:option value=""></html:option><!-- 2011.11.9 qlj ��FFҪ�� -->
									<html:option value="01">��ѧ��</html:option>
									<html:option value="02">�����ƺ�</html:option>
								</html:select>
							</td>
							<th>��Ŀ����</th>
							<td>
								<html:select property="xmxz" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="xmxzList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>��Ŀ��Χ</th>
							<td>
								<html:select property="xmfw" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="xmfwList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('/xgxt/pjpy_comm_xmsq.do?method=xssqManage');">
										�� ѯ
									</button>
									 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										�� ��
							 		</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			</div>
			<div class="formbox">
				<logic:empty name="rs">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="rs">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;</font> 
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">&nbsp;
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="map">
							<tr onclick="rowOnClick(this);" 
								ondblclick="" style="cursor:hand">
								<td>
									<input type="hidden" name="xmdm" value="${map.xmdm }" />
									${map.xmmc}
								</td>
								<td>	
									<logic:equal name="map" property="xmlx" value="01">
										��ѧ��
									</logic:equal>
									<logic:equal name="map" property="xmlx" value="02">
										�����ƺ�
									</logic:equal>
								</td>
								<td>
									${map.shlc }
								</td>
								<logic:equal value="stu" name="user" property="userStatus">
									<td>
										${map.mqshzt }
									</td>
								</logic:equal>
								<td>
									<logic:equal name="map" property="cz" value="bkxg">
										<input type="hidden" id="yy_${map.xmdm }" value="${map.yy }"/>
										<button type="button" class="btn_01" onclick="showYy('yy_${map.xmdm}');" style="width:80px;height: 20px">
											��������
										</button>
									</logic:equal>
									<logic:equal name="map" property="cz" value="ksq">
										<button type="button" class="btn_01" onclick="xssqUpdate('add',this);" style="width:80px;height: 20px">
											��&nbsp;&nbsp;&nbsp;&nbsp;��
										</button>
									</logic:equal>
									<logic:equal name="map" property="cz" value="kxg">
										<button type="button" class="btn_01" onclick="xssqUpdate('update',this);" style="width:80px;height: 20px">
											��&nbsp;&nbsp;&nbsp;&nbsp;��
										</button>
									</logic:equal>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</logic:notEmpty>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>