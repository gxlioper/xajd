<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xyfd/jzppwh/fdyywh/js/wfcyy.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption : "�յ�ԤԼ�б�",
					pager : "pager",
					url : "xyfd_sdyy.do?method=sdyyList&type=query",
					colList : [
						{ label : 'yyid', name : 'yyid', index : 'yyid',key : true,hidden : true },
						{ label : 'ԤԼ��', name : 'yyh', index : 'yyh',width:'12%',formatter:yyhLink},
						{ label : 'ԤԼѧ��', name : 'xh', index : 'xh',hidden:true},
						{ label : 'ԤԼѧ��', name : 'xm', index : 'xm',width:'10%'},
						{ label : 'ԤԼ��', name : 'yyr', index : 'yyr',width:'10%'},
						{ label : '����ʱ��', name : 'fdsj', index : 'fdsj',width:'10%'},
                        { label : '�����γ�', name : 'fdkc', index : 'fdkc', hidden : true },
						{ label : '�γ�����', name : 'kcmc', index : 'kcmc', width : '15%' },
						{ label : '���״̬', name : 'shztmc', index : 'shztmc', width : '10%' },
						{ label : '���״̬', name : 'zt', index : 'zt', hidden : true}
					],
					params:{yyzt:"dsh"},
                    sortname : "yyh",
                    sortorder : "desc",
                    radioselect:false
				}
				var map = getSuperSearch();
                map["yyzt"]="dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);

                jQuery.post("xyfd_sdyy.do?method=yyTs", {}, function(data) {
                    if(data["num"]!='0'){
                        showAlertDivLayer("����<font class='red'>"+data["num"]+"</font>��ԤԼ������");
					}
                }, 'json');
            })
			//ȷ��ԤԼ
			function submitYy() {
                var ids = jQuery("#dataTable").getSeletIds();
                var rows = jQuery("#dataTable").getSeletRow();
                if (ids.length != 1) {
                    showAlertDivLayer("��ѡ��һ����Ҫȷ�ϵ�ԤԼ��");
                    return false;
                }
                if(rows[0]['zt']=='3'||rows[0]['zt']=='0'){
                    showAlertDivLayer("�ø���ԤԼ��ȡ����");
                    return false;
				}
                if(rows[0]['zt']=='1'){
                    showAlertDivLayer("�ø���ԤԼ��ȷ�ϣ�");
                    return false;
                }
                if(rows[0]['zt']=='4'||rows[0]['zt']=='6'){
                    showAlertDivLayer("�ø���ԤԼ����ɣ������ظ�ȷ�ϣ�");
                    return false;
				}
                showConfirmDivLayer("��ȷ��Ҫȷ��ѡ���ԤԼ��", {
                    "okFun" : function() {
                        jQuery.post("xyfd_sdyy.do?method=submitYy", {
                            values : ids.toString()
                        }, function(data) {
                            showAlertDivLayer(data["message"]);
                            jQuery("#dataTable").reloadGrid();
                        }, 'json');
                    }
                });
            }
            //ȡ��ԤԼ
            function cancelYy() {
                var ids = jQuery("#dataTable").getSeletIds();
                var rows = jQuery("#dataTable").getSeletRow();
                if (ids.length != 1) {
                    showAlertDivLayer("��ѡ��һ����Ҫȡ����ԤԼ��");
                    return false;
                }
                if (rows[0]['zt'] != '5'&&rows[0]['zt'] != '1') {
                    showAlertDivLayer("ֻ��ԤԼ�л���ԤԼ�ļ�¼���ܱ�ȡ����");
                    return false;
                }
                var height = jQuery(window).height();
                var url = 'xyfd_fqyy.do?method=qxYy&yyid=' + rows[0]["yyid"];
                showDialog('ȡ��ԤԼԭ��', 600, height-250, url);
            }

            function add(yysf) {
                var height = jQuery(window).height();
                var rows = jQuery("#dataTable").getSeletRow();
                if (rows.length != 1) {
                    showAlertDivLayer("��ѡ��һ����ҪԤԼ�Ŀγ̣�");
                }
                var url = "xyfd_fqyy.do?method=addYy&yysf="+yysf+"&fdkc="+rows[0]['fdkc']+"&xh="+rows[0]['xh'];
                showDialog("�����γ�����", 800, height-250, url);
            }
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xyfd_fqyy">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<logic:equal name="isJsOrPb" value="true">
								<li>
									<a href="javascript:void(0);" onclick="submitYy();return false;" class="btn_sr">ȷ��ԤԼ</a>
								</li>
								<%--<li>--%>
									<%--<a href="javascript:void(0);" onclick="cancelYy();return false;" class="btn_shuc">ȡ��ԤԼ</a>--%>
								<%--</li>--%>
								<li>
									<a href="javascript:void(0);" onclick="add('tea');return false;"  class="btn_zj" >ԤԼ�´θ���</a>
								</li>
							</logic:equal>
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���¼�����б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
