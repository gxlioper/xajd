<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript">
			function showXmzdszUpdate(){
				var xmdm = $('xmdm').value;
				var mkmc = $('mkmc').value;
				
				if (""==xmdm){
					alert("���������ѡ��һ����Ҫά������Ŀ");
					return false;
				} else {
					showTopWin('/xgxt/xmzdsz.do?method=xmzdszUpdate&mkmc='+mkmc+'&xmdm='+xmdm,800,600);
				}
			}
			
			
			function checkXmList(text){
				var liArr = $('xmList').getElementsByTagName('li');
				
				for (var i = 0 ; i < liArr.length ; i++){
					if (liArr[i].id.indexOf(text)>=0){
						liArr[i].style.display="";
					} else {
						liArr[i].style.display="none";
					}
				}
			
			}
			
			
		</script>
		<style type="css">
.text-overflow {
	display: block; /*�����������*/
	width: 16em;
	word-break: keep-all; /* ������ */
	white-space: nowrap; /* ������ */
	overflow: hidden; /* ���ݳ������ʱ���س������ֵ����� */
	text-overflow: ellipsis;
	/* ���������ı����ʱ��ʾʡ�Ա��(...) ������overflow:hidden;һ��ʹ�á�*/
}
		
		</style>
	</head>

	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a onmouseover="">${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/xmzdsz">
			<!-- ������ -->
			<input type="hidden" name="mkmc" value="${mkmc }" id="mkmc" />
			<html:hidden property="queryequals_xmdm" styleId="xmdm" />
			<!-- ������ end-->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="#" onclick="showXmzdszUpdate();" class="btn_sz">�ֶ�����</a>
							</li>
							<li>
								<a href="#"
									onclick="batchData('primarykey_cbv','/xgxt/xmzdsz.do?method=xmzdszManage&doType=del','del');return false;"
									class="btn_sc">�ֶ�ɾ��</a>
							</li>
						</logic:equal>
					</ul>
				</div>
			</div>
			<!-- ��ť end-->
			<!-- �������� -->
			<!-- �๦�ܲ����� -->
			<div class="leftframe04">
				<div class="menulist">
					<!--��start-->
					<div class="menutitle">
						<h3>
							<span class="title"> 
					      	������Ŀ
							</span>
						</h3>
						<!--CNLTreeMenu Start:-->
						<div class="CNLTreeMenu1" id="CNLTreeMenu1"
							style="height:420px; overflow-x:hidden;overflow-y:auto;">
							<p class="choose_select">
								<label>
									��Ŀ����
									<input type="text" style="width:80px" class="text_nor"
										onkeyup="checkXmList(this.value)" />
								</label>

							</p>
							

							<ul id="xmList">
								<logic:iterate id="x" name="xmList">
									<li id="${x.xmmc}"
										class='<logic:equal value="${xmdm}" name="x" property="xmdm">Current</logic:equal>'>
										<a href="#" class='Child' title="${x.xmmc}"
											onclick="$('xmdm').value='${x.xmdm }';$('search_go').click();return false;"
											style='float:left;width:110px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;'>${x.xmmc}</a>
									</li>
								</logic:iterate>
							</ul>
						</div>

					</div>

				</div>
				<!--��end-->
			</div>

			<div class="rightframe04" style="80%">
				<!--��ѯ����-->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="10">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/xmzdsz.do?method=xmzdszManage')">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									������Դ
								</th>
								<td>
									<html:text property="querylike_lybmc"></html:text>
								</td>
								<th>
									�ֶ�����
								</th>
								<td>
									<html:text property="querylike_zdmc"></html:text>
								</td>
								<th></th>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- �������� end-->
				<!-- ��ѯ���-->
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; </span>
				</h3>
				<div class="con_overlfow">
					<table width="100%" class="dateline tablenowrap">
						<thead>
							<tr>
								<td>
									<input type="checkbox" id="jiade" />
									<input type="checkbox" id="selall" name="selall"
										onclick="selAll()" />
								</td>
								<logic:iterate id="b" name="topTr" offset="1" scope="request">
									<td id="${b.en}" onclick="tableSort(this)">
										${b.cn}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)">
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
											<input type="hidden" value="${v }" />
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xmzdszForm"></jsp:include>
				<!--��ҳend-->
				<script type="text/javascript">
									$('choose').className="hide";
							</script>
			</div>
				<!-- ��ѯ��� end-->
		</html:form>
	</body>
</html>
