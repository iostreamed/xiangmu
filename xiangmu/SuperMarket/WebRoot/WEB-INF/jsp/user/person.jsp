<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/head.jsp"%>
 <div class="right">
        <div class="location">
            <strong>你现在所在的位置是:</strong>
            <span>个人中心</span>
        </div>
        <div class="providerView">
        	<a class="viewPerson" href="${path }/sys/pictureadd" userid=${user.id } username=${user.username }><img src="${path }/statics/uploadfiles/${user.image}" alt="上传照片" title="上传照片"/></a></span>
            <p><strong>用户编号：</strong><span>${user.usercode }</span></p>
            <p><strong>用户名称：</strong><span>${user.username }</span></p>
            <p><strong>用户性别：</strong>
            	<span>
            		<c:if test="${user.gender == 1 }">男</c:if>
					<c:if test="${user.gender == 2 }">女</c:if>
				</span>
			</p>
            <p><strong>出生日期：</strong><span><fmt:formatDate value="${user.birthday }" pattern="yyyy-MM-dd"/></span></p>
            <p><strong>用户电话：</strong><span>${user.phone }</span></p>
            <p><strong>用户地址：</strong><span>${user.address }</span></p>
            <p><strong>用户角色：</strong><span>${user.role.roleName}</span></p>
        </div>
    </div>
</section>
<%@include file="/WEB-INF/jsp/common/foot.jsp" %>