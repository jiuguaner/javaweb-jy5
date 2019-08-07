package com.itdr.controller;

import com.itdr.common.ResponseCode;
import com.itdr.pojo.Users;
import com.itdr.service.UserService;
import com.itdr.utils.PathUTil;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/manage/user/*")
public class UsersController extends HttpServlet {
    private UserService uc = new UserService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        String path = PathUTil.getPath(pathInfo);

        ResponseCode rs = null;
        switch (path){
            case "list":
                rs = listDo(request);
                break;
            case "login":
               rs = loginDo(request);
               break;
            case "disableuser":
                rs = disableuserDo(request);
                break;
        }


         response.getWriter().write(rs.toString());

    }

    private ResponseCode listDo(HttpServletRequest request){

        ResponseCode rs = new ResponseCode();

        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("User");

        if (user == null){
            rs.setStatus(3);
            rs.setMag("请登录后再操作！！");
            return rs;
        }
        if (user.getType() != 1){
            rs.setStatus(3);
            rs.setMag("没有操作权限！！");
            return rs;
        }

        String pageSize = request.getParameter("pageSize");
        String pageNum = request.getParameter("pageNum");

        rs = uc.selectAll(pageSize, pageNum);
        //调用业务层处理业务
        return rs;
    }


    //用户登录请求
    private ResponseCode loginDo(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ResponseCode rs = uc.selectOne(username, password);

        //获取session对象
        HttpSession session = request.getSession();
        session.setAttribute("User",rs.getData());

        return rs;
    }
    //禁用用户请求
    private ResponseCode disableuserDo(HttpServletRequest request){
        String username = request.getParameter("uid");

        ResponseCode rs = uc.selectOne("uid");


        return rs;
    }

}
