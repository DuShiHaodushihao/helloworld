package com.how2java.tmall.interceptor;

import com.how2java.tmall.pojo.Category;
import com.how2java.tmall.pojo.OrderItem;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.CategoryService;
import com.how2java.tmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OtherInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
        return true;
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)throws Exception{
        List<Category> cs=categoryService.list();
        request.getSession().setAttribute("cs",cs);

        HttpSession session=request.getSession();
        String contextPath=session.getServletContext().getContextPath();
        request.getSession().setAttribute("contextPath",contextPath);

        User user=(User)session.getAttribute("user");
        int cartTotalItemNumber=0;
        if(null!=user){
            List<OrderItem> ois=orderItemService.listByUser(user.getId());
            for(OrderItem oi:ois){
                cartTotalItemNumber+=oi.getNumber();
            }
        }
        request.getSession().setAttribute("cartTotalItemNumber",cartTotalItemNumber);
    }
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex)throws Exception{

    }
}
