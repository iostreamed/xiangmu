<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>

<div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>个人中心页面 >> 图片上传页面</span>
        </div>
       		 ${uploadWpError}
        <div class="providerAdd">
            <form id="userForm" name="userForm" method="post" action="${path}/sys/savepicture" enctype="multipart/form-data">
				<input type="hidden" name="method" value="add">
                <!--div的class 为error是验证错误，ok是验证成功-->
               <div>
                    <input type="hidden" id="errorinfo_wp" value=""/>
                    <label for="a_workPicPath">工作证照片：</label>
                    <input type="file" name="attachs" id="a_workPicPath"/>
                    <font color="red"></font>
                </div>                                
                <div class="providerAddBtn">
                    <input type="button" name="add" id="add" value="保存" >
					<input type="button" id="back" name="back" value="返回" >
                </div>
            </form>
        </div>
</div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/pictureadd.js"></script>