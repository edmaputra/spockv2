package id.co.bankaltimtara.spokc.controller;

import javax.servlet.http.HttpServletRequest;

public class Logs {

    public static String dapatkanSemua(HttpServletRequest request, String clazz){
        return "CLIENT:"+request.getRemoteAddr()+"; Class:"+clazz+"; OPERATION:GET ALL";
    }

    public static String dapatkan(HttpServletRequest request, String clazz, Object s){
        return "CLIENT:"+request.getRemoteAddr()+"; Class:"+clazz+"; OPERATION:FIND ONE; ENTITY:"+s.toString();
    }

    public static String tambah(HttpServletRequest request, String clazz, Object s){
        return "CLIENT:"+request.getRemoteAddr()+"; Class:"+clazz+"; OPERATION:ADD; ENTITY:"+s.toString();
    }

    public static String update(HttpServletRequest request, String clazz, Object s){
        return "CLIENT:"+request.getRemoteAddr()+"; Class:"+clazz+"; OPERATION:UPDATE; ENTITY:"+s.toString();
    }

    public static String hapus(HttpServletRequest request, String clazz, Object s){
        return "CLIENT:"+request.getRemoteAddr()+"; Class:"+clazz+"; OPERATION:DELETE; ENTITY:"+s.toString();
    }

    public static String error(HttpServletRequest request, String clazz, String operation, Object s){
        return "CLIENT:"+request.getRemoteAddr()+"; Class:"+clazz+"; ERROR:"+operation+"; ENTITY:"+s.toString();
    }
}
