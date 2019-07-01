<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/jjgl/xqsh/script/xqsh.js"></script>
		<script type="text/javascript" src="xsgzgl/jjgl/xqwh/js/xqwh.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"�ҽ������б�",
					pager:"pager",
					multiselect:true,
					rowNum:20,
					url:"jjgl_xqwhgl.do?method=queryXqList&type=0",
					colList:[
					   {label:'�ҽ̱��',name:'xqid', index: 'xqid',key:true,formatter:xqidLink},
					   {label:'�ҽ̿�Ŀ',name:'jjxk', index: 'jjxk'},
					   {label:'�ҽ��꼶',name:'jjnj', index: 'jjnj'},
					   {label:'�ҽ̵ص�',name:'jjdd', index: 'jjdd'},
					   {label:'�Ǽ���',name:'djr', index: 'djr'},
					   {label:'�Ǽ�����',name:'sqsj', index: 'sqsj'},
					   {label:'״̬',name:'jjztmc', index: 'jjztmc'},
					   {label:'������Ϣ',name:'xssqid', index: 'xssqid', formatter:sqrLink}
					],
					sortname: "xqid",
					sortorder: "desc"
				};

			function tsLink(v,r){
				//��Ͷ��
				if(v === '0'){
						return "����";
				}else{
					var onclickfn = "onclick=\"" + "showDialog('�ҽ�Ͷ����Ϣ' , 720 , 450 , 'jjgl_xqwhgl.do?method=viewTsxx&xqid=" + r['xqid'] + "')" + "\"";
					
					var href = "href = 'javascript:void(0);'";

					var el = "<a " + href + " class='name' " + onclickfn + " >��Ͷ��(" + v + ")</a>";
					
					return el;
				}
			}

			function pjLink(v,r){
				//������
				if(v === '0'){
						return "δ����";
				}else{
					var onclickfn = "onclick=\"" + "showDialog('�ҽ�������Ϣ' , 600 , 280 , 'jjgl_xqwhgl.do?method=viewPjxx&xqid=" + r['xqid'] + "')" + "\"";
					
					var href = "href = 'javascript:void(0);'";

					var el = "<a " + href + " class='name' " + onclickfn + " >������</a>";
					
					return el;
				}
			}
			
			function sqrLink(v,r){
				
				if(v == null){
						return "��";
				}else{
					var onclickfn = "onclick=\"" + "showDialog('�ҽ�ѧ��������Ϣ' , 720 , 450 , 'jjgl_xqwhgl.do?method=assign&xqid=" + r['xqid'] + "')" + "\"";
					
					var href = "href = 'javascript:void(0);'";

					var el = "<a " + href + " class='name' " + onclickfn + " >������</a>";
					
					return el;
				}
			}

            function xqidLink(v,r){

                return "<a class='name' href='javascript:void(0);' onclick='ck("+v+")'>"+v+"</a>";
            }

			
			function fbrLink(v,r){
				var onclickfn = "onclick=\"" + "showDialog('�ҽ̷�������Ϣ' , 720 , 450 , 'jjgl_zcyhgl.do?method=viewZcyh&yhm=" + r['sqr'] + "')" + "\"";
				
				var href = "href = 'javascript:void(0);'";

				var el = "<a " + href + " class='name' " + onclickfn + " >" + v + "</a>";
				
				return el;
			}

			function jjlsxmLink(v,r) {
			    v = v||"";
                return "<a class='name' onclick='showDialog(\"�ҽ���ʦ��Ϣ\",700,500,\"jjgl_jjzg.do?method=jjzgView&xh="+r["jjlsxh"]+"\")'>"+v+"</a>";
            }
			
			function searchRs(){
				var map = {};
				map["xqid"] = jQuery("#xqid").val();
				map["jjlsxm"] = jQuery("#jjlsxm").val();
				map["jjxk"] = jQuery("#jjxk").val();
				map["jjnj"] = jQuery("#jjnj").val();
                map["sfypjj"] = jQuery("#sfypjj").val();
                map["sfyjxys"] = jQuery("#sfyjxys").val();
                jQuery("#dataTable").reloadGrid(map);
			}

			
			
			/**
			 * ҳǩ�л�
			 * @return
			 */
			function selectTab(obj,query_type){
				
				gridSetting['url'] =  "jjgl_xqwhgl.do?method=queryXqList&type=" + query_type;
				//�ҽ���Ƹ�б�
				if(query_type == "0"){

                    jQuery("#zjLinkLi").css("display","");
                    jQuery("#xgLinkLi").css("display","");
                    jQuery("#scLinkLi").css("display","");
					jQuery("#xqfplsLinkLi").css("display","");
					jQuery("#xqztxgLinkLi").css("display","");
                    jQuery("#sjycLinkLi").css("display","none");
                    jQuery("#xysxzLinkLi").css("display","none");
                    jQuery("#sf").css("display","none");
                    gridSetting['colList'] = [
					   					   {label:'�ҽ̱��',name:'xqid', index: 'xqid',key:true,formatter:xqidLink},
										   {label:'�ҽ̿�Ŀ',name:'jjxk', index: 'jjxk'},
										   {label:'�ҽ��꼶',name:'jjnj', index: 'jjnj'},
										   {label:'�ҽ̵ص�',name:'jjdd', index: 'jjdd'},
										   {label:'�Ǽ���',name:'djr', index: 'djr'},
										   {label:'�Ǽ�����',name:'sqsj', index: 'sqsj'},
										   {label:'״̬',name:'jjztmc', index: 'jjztmc'}
										   /*{label:'������Ϣ',name:'xssqid', index: 'xssqid', formatter:sqrLink}*/
										];
				//���ɼҽ��б�
				} else if(query_type == "1"){
                    jQuery("#zjLinkLi").css("display","none");
                    jQuery("#xgLinkLi").css("display","none");
                    jQuery("#scLinkLi").css("display","none");
					jQuery("#xqfplsLinkLi").css("display","none");
					jQuery("#xqztxgLinkLi").css("display","");
                    jQuery("#sjycLinkLi").css("display","none");
                    jQuery("#xysxzLinkLi").css("display","none");
                    jQuery("#sf").css("display","none");
                    gridSetting['colList'] = [
											{label:'�ҽ̱��',name:'xqid', index: 'xqid',key:true,formatter:xqidLink},
											{label:'�ҽ̿�Ŀ',name:'jjxk', index: 'jjxk'},
											{label:'�ҽ��꼶',name:'jjnj', index: 'jjnj'},
											{label:'�ҽ̵ص�',name:'jjdd', index: 'jjdd'},
											{label:'�Ǽ���',name:'djr', index: 'djr'},
											{label:'�Ǽ�����',name:'sqsj', index: 'sqsj'},
											{label:'�ҽ���ʦѧ��',name:'jjlsxh', index: 'jjlsxh',hidden:true},
											{label:'�ҽ���ʦ',name:'jjlsxm', index: 'jjlsxm',formatter:jjlsxmLink},
											{label:'�ɳ�ʱ��',name:'pcsj', index: 'pcsj'}
										];
				//���ڼҽ��б�
				}else if(query_type == "2"){
                    jQuery("#zjLinkLi").css("display","none");
                    jQuery("#xgLinkLi").css("display","none");
                    jQuery("#scLinkLi").css("display","none");
					jQuery("#xqfplsLinkLi").css("display","none");
					jQuery("#xqztxgLinkLi").css("display","");
                    jQuery("#sjycLinkLi").css("display","");
                    jQuery("#xysxzLinkLi").css("display","none");
                    jQuery("#sf").css("display","none");
                    gridSetting['colList'] = [
												{label:'�ҽ̱��',name:'xqid', index: 'xqid',key:true,formatter:xqidLink},
												{label:'�ҽ̿�Ŀ',name:'jjxk', index: 'jjxk'},
												{label:'�ҽ��꼶',name:'jjnj', index: 'jjnj'},
												{label:'�ҽ̵ص�',name:'jjdd', index: 'jjdd'},
												{label:'�Ǽ���',name:'djr', index: 'djr'},
												{label:'�Ǽ�����',name:'sqsj', index: 'sqsj'},
                        						{label:'�ҽ���ʦѧ��',name:'jjlsxh', index: 'jjlsxh',hidden:true},
												{label:'�ҽ���ʦ',name:'jjlsxm', index: 'jjlsxm',formatter:jjlsxmLink},
												{label:'�ɳ�ʱ��',name:'pcsj', index: 'pcsj'}
											];
				//�ѽ�Э�����б�
				}else if(query_type == "3"){
                    jQuery("#zjLinkLi").css("display","none");
                    jQuery("#xgLinkLi").css("display","none");
                    jQuery("#scLinkLi").css("display","none");
					jQuery("#xqfplsLinkLi").css("display","none");
					jQuery("#xqztxgLinkLi").css("display","");
                    jQuery("#sjycLinkLi").css("display","none");
                    jQuery("#xysxzLinkLi").css("display","");
                    jQuery("#sf").css("display","none");
                    gridSetting['colList'] = [
												{label:'�ҽ̱��',name:'xqid', index: 'xqid',key:true,formatter:xqidLink},
												{label:'�ҽ̿�Ŀ',name:'jjxk', index: 'jjxk'},
												{label:'�ҽ��꼶',name:'jjnj', index: 'jjnj'},
												{label:'�ҽ̵ص�',name:'jjdd', index: 'jjdd'},
												{label:'�Ǽ���',name:'djr', index: 'djr'},
												{label:'�Ǽ�����',name:'sqsj', index: 'sqsj'},
                        						{label:'�ҽ���ʦѧ��',name:'jjlsxh', index: 'jjlsxh',hidden:true},
												{label:'�ҽ���ʦ',name:'jjlsxm', index: 'jjlsxm',formatter:jjlsxmLink},
												{label:'��Э����ʱ��',name:'jxyssj', index: 'jxyssj'}
											];
				//�ѹرռҽ��б�
				}else if(query_type == "4"){
                    jQuery("#zjLinkLi").css("display","none");
                    jQuery("#xgLinkLi").css("display","none");
                    jQuery("#scLinkLi").css("display","none");
                    jQuery("#xqfplsLinkLi").css("display","none");
                    jQuery("#xqztxgLinkLi").css("display","");
                    jQuery("#sjycLinkLi").css("display","none");
                    jQuery("#xysxzLinkLi").css("display","");
                    jQuery("#sf").css("display","");
                    gridSetting['colList'] = [
                        {label:'�ҽ̱��',name:'xqid', index: 'xqid',key:true,formatter:xqidLink},
                        {label:'�ҽ̿�Ŀ',name:'jjxk', index: 'jjxk'},
                        {label:'�ҽ��꼶',name:'jjnj', index: 'jjnj'},
                        {label:'�ҽ̵ص�',name:'jjdd', index: 'jjdd'},
                        {label:'�Ǽ���',name:'djr', index: 'djr'},
                        {label:'�Ǽ�����',name:'sqsj', index: 'sqsj'},
                        {label:'�ҽ���ʦѧ��',name:'jjlsxh', index: 'jjlsxh',hidden:true},
                        {label:'�ҽ���ʦ',name:'jjlsxm', index: 'jjlsxm',formatter:jjlsxmLink},
                        {label:'�Ƿ����ɼҽ�',name:'sfypjj', index: 'sfypjj'},
                        {label:'�Ƿ��ѽ�Э����',name:'sfyjxys', index: 'sfyjxys'},
                        {label:'�ر�ʱ��',name:'gbsj', index: 'gbsj'}
                    ];
                }
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
			}

			/**
			*����ҽ���ʦ
			**/
			function xqfpls(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����¼��");
				} else {
					var url = "jjgl_xqwhgl.do?method=assignManual&xqid="+jQuery("#dataTable").getSeletIds()[0];
					var title = "����ҽ���ʦ";
					showDialog(title,850,570,url);
				}
			}

			/**
			*�鿴
			**/
			function ck(value){

				var url = "jjgl_xqwhgl.do?method=ck&xqid="+value;
				var title = "�鿴������Ϣ";
				showDialog(title,800,400,url);
			}
			
			/**
			*�޸�״̬
			**/
			function xqztxg(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					var url = "jjgl_xqwhgl.do?method=changeJJzt&xqid="+jQuery("#dataTable").getSeletIds()[0];
					var title = "�ҽ�״̬�޸�";
					showDialog(title,680,350,url);
				}
			}

			function xz() {
                var url = "jjgl_xqwhgl.do?method=downXys"
                window.open(url);
            }

			
			/**
			*��ʼ��
			*/
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			/**
			*���¼�������
			*/
			function reloadWindow(){
				jQuery("#dataTable").reloadGrid();
			}

            /**
			 * ���Ӽҽ���Ϣ
             */
            function zj() {
                var url = "jjgl_xqwhgl.do?method=xqwhAdd";
                var title = "�ҽ���Ϣ����";
                showDialog(title, 800, 550, url);
            }

            /**
			 * �޸ļҽ���Ϣ
             */
            function xg() {
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1) {
                    showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
                } else {
                    var url = 'jjgl_xqwhgl.do?method=xqwhEdit&xqid=' + rows[0]["xqid"];
                    var title = "�ҽ���Ϣ�޸�";
                    showDialog(title, 800, 550, url);
                }
            }

            /**
			 * ɾ���ҽ���Ϣ
             */
            function sc() {
                var ids = jQuery("#dataTable").getSeletIds();
                if (ids.length == 0) {
                    showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
                    return false;
                }

                var rows = jQuery("#dataTable").getSeletRow();
                for(var i=0;i<rows.length;i++){

                    /*if(rows[i]["jjzt"] != '0' || rows[i]["xssqid"] != '4'){
                        showAlertDivLayer("ֻ��ɾ��δ�ɳ����ѹرյļҽ���Ϣ��");
                        return false;
					}*/

                    if (rows[i]["xssqid"] != null) {
                        showAlertDivLayer("ֻ��ɾ��δ������ļҽ���Ϣ��");
                        return false;
                    }
                }

                showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
                    "okFun" : function() {
                        jQuery.post("jjgl_xqwhgl.do?method=xqwhDel", {
                                values : ids.toString()
                            },
                            function(data) {
                                showAlertDivLayer(data["message"]);
                                jQuery("#dataTable").reloadGrid();
                            }, 'json');
                    }
                });
            }
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
			<!-- �������� -->
			<div class="searchtab">
				<html:form action="/jjgl_xqwhgl" method="post" styleId="jjglXqwhForm">
					<!-- ��ť -->
					<div class="buttonbox">
						<ul>
							<li id="zjLinkLi"><a href="javascript:void(0);" onclick="zj();" class="btn_zj" id="zjLink">����</a></li>
							<li id="xgLinkLi"><a href="javascript:void(0);" onclick="xg();" class="btn_xg" id="xgLink">�޸�</a></li>
							<li id="scLinkLi"><a href="javascript:void(0);" onclick="sc();" class="btn_sc" id="scLink">ɾ��</a></li>
							<li id="xqfplsLinkLi"><a href="javascript:void(0);" onclick="xqfpls();" class="btn_csh" id="xqfplsLink">ָ����ʦ</a></li>
							<li id="xqztxgLinkLi"><a href="javascript:void(0);" onclick="xqztxg();" class="btn_xg" id="xqztxgLink">�ҽ̲���</a></li>
							<li id="sjycLinkLi" style="display:none"><a href="javascript:void(0);" onclick="sjyc();" class="btn_xg" id="sjycLink">�Խ��ӳ�</a></li>
							<li id="dcLinkLi"><a href="javascript:void(0);" onclick="dc();" class="btn_dc" id="dcLink">����</a></li>
							<li id="xysxzLinkLi" style="display:none"><a href="javascript:void(0);" onclick="xz();" class="btn_dy" id="xysxzLink">Э��������</a></li>
						</ul>
					</div>
					<div class="comp_title" id="comp_title">
				      <ul style="width:90%" id="tabUl">
				      	<li class="ha" >
				      		<a href="javascript:void(0);" onclick="selectTab(this,'0');"><span>�ҽ���Ƹ�б�</span></a>
				      	</li>
				      	<li>
				      		<a href="javascript:void(0);" onclick="selectTab(this,'1');"><span>���ɼҽ��б�</span></a>
				      	</li>
						<li ><a href="javascript:void(0);" onclick="selectTab(this,'2');"><span>���ڼҽ��б�</span></a></li>
						
						<li ><a href="javascript:void(0);" onclick="selectTab(this,'3');"><span>�ѽ�Э�����б�</span></a></li>
						<li ><a href="javascript:void(0);" onclick="selectTab(this,'4');"><span>�ѹرռҽ��б�</span></a></li>
				      </ul>
				    </div>
					
					<table width="100%" border="0">
						<tr>
							<th width="10%">�ҽ̱��</th>
							<td>
								<html:text property="xqid" styleId="xqid" ></html:text>
							</td>
							<th width="10%">�ҽ���ʦ</th>
							<td>
								<html:text property="jjlsxm" styleId="jjlsxm" ></html:text>
							</td>
						</tr>
						<tr>
						<th width="10%">�ҽ̿�Ŀ</th>
							<td>
								<html:select property="jjxk" styleId="jjxk" style="width:173px">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjxkList" property="jjxk" labelProperty="jjxk"/>
					    		</html:select>
							</td>
							<th width="10%">�꼶</th>
							<td>
								<html:select property="jjnj" styleId="jjnj" style="width:173px">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjnjList" property="jjnj" labelProperty="jjnj"/>
					    		</html:select>
							</td>
						</tr>
						<tr id="sf" style="display: none">
							<th width="10%">�Ƿ����ɼҽ�</th>
							<td>
								<html:select property="sfypjj" styleId="sfypjj">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
							<th width="10%">�Ƿ��ѽ�Э����</th>
							<td>
								<html:select property="sfyjxys" styleId="sfyjxys">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs()">
										�� ѯ
									</button>
								</div>
							</td>
						</tr>
					</table>
				</html:form>
			</div>
		</div>
		<div class="formbox">
			<div>
				<h3 class="datetitle_01">
					<span> 
						�����б�
					</span>
				</h3>
			</div>
			<table id="dataTable"></table>
		</div>
		<div id="pager"></div>
		
	</body>
</html>
