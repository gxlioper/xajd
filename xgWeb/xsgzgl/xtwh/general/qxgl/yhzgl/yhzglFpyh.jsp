<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/systemFunction.js"></script>
		<script language="javascript" src="xsgzgl/xtwh/general/qxgl/yhzgl/yhzglFpyh.js"></script>
	</head>
	<body >
		<html:form action="/xtwh_qxgl_yhgl" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" id="oldZdm" value="${zdm}">
			<input type="hidden" id="fpzt" value="${fpzt}">
			  <!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="reBack();return false;" class="btn_fh">����</a></li>
						<li id="fpButtonId"><a href="#" onclick="fpyh();return false;" class="btn_sz">����</a></li>
						<li id="tyButtonId" style="display:none"><a href="#" onclick="qxfpyh();return false;" class="btn_sc">ȡ������</a></li>
					</ul>
				</div>
				<table style="" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
						<tr>
							<th width="15%">������</th>
							<td width="30%">
								${rs.zmc }
							</td>
							<th width="15%">�û���</th>
							<td>
								<span id ="yhs">${rs.num }</span>
							</td>
						</tr><tr height="2px"></tr>
					</tbody>		
			</table>
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%" id="tabUl">
		      	<li class="ha" id="kfpTabId"><a href="javascript:void(0);" onclick="gofpList('wfp');return false;"><span>�ɷ����û��б�</span></a></li>
				<li id="yfpTabId"><a href="javascript:void(0);" onclick="gofpList('yfp');return false;"><span>�ѷ����û��б�</span></a></li>
		      </ul>
		    </div>
		  	</div>
				<!-- �������� -->
				<div class="searchtab"  id ="searchTjId">
				
					<table width="100%" border="0"  class="formlist">
						<tfoot>
							<tr>
								<td colspan="4">
									<input type="hidden" name="go" value="a" />
									<div class="btn">
										<button type="button"  class="btn_cx" id="search_go" onclick="searchRs();return false;">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button"  class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody id="tbody_search_query">
							<tr>
								<th>
									�û���
								</th>
								<td>
									<input type="text" name="yhm" id="yhm" style="width:200px" maxlength="20" 
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>								
								<th>
									����
								</th>
								<td>
									<input type="text" name="xm" id="xm" style="width:200px" maxlength="20" 
										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>
								</td>						
							</tr>
							<tr>
								<!-- 
								<th>
									������
								</th>
								<td>
									<html:select property="zdm" style="width:200px" styleId="zdm">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="yhzList" property="dm" labelProperty="mc" />
									</html:select>
								</td>
								 -->
								<th>
									����״̬
								</th>
								<td>
									<html:select property="qx" style="width:200px" styleId="qx">
										<html:option value="">--��ѡ��--</html:option>
										<html:option value="1">����</html:option>
										<html:option value="0">ͣ��</html:option>
									</html:select>
								</td>
								<th>
									��������
								</th>
								<td>							
									<html:select property="szbm" style="width:200px" styleId="szbm">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="yjbmList" property="bmdm" labelProperty="bmqc" />
									</html:select>
								</td>	
							</tr>
							<tr>
								<th>
									�Ƿ񸨵�Ա
								</th>
								<td>
									<html:select property="sffdy" style="width:200px" styleId="sffdy">
										<html:option value="">--��ѡ��--</html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
								<th>
									�Ƿ������
								</th>
								<td>
									<html:select property="sfbzr" style="width:200px" styleId="sfbzr">
										<html:option value="">--��ѡ��--</html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
		
			<!-- �๦�ܲ����� end-->

			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=yhglNewForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			<%@ include file="/xsgzgl/xtwh/general/qxgl/yhgl/yhglCz.jsp"%>

			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</div>
		</html:form>
	</body>
</html>
