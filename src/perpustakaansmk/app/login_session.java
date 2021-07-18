/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpustakaansmk.app;

/**
 *
 * @author Ahmad
 */
public class login_session {
    private static String n,i,s;
    
    public static String getnama(){
        return n;
    }
    
    public static String getnik(){
        return i;
    }
    
    public static String getstatus(){
        return s;
    }
    
    public static void setnama(String nama){
        login_session.n = nama;
    }
    public static void setnik(String nik){
        login_session.i = nik;
    }
    public static void setstatus(String hak){
        login_session.s = hak;
    }
}
