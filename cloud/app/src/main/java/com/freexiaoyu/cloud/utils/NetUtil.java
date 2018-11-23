package com.freexiaoyu.cloud.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetUtil {
	
    /**
     * 网络连接检测
     * @param ctx
     * @return
     */
    public static boolean isNetworkAvailable(Context ctx) {   
    	boolean netstatus = false;
        try {   
            ConnectivityManager cm = (ConnectivityManager) ctx   
                    .getSystemService(Context.CONNECTIVITY_SERVICE);   
            NetworkInfo info = cm.getActiveNetworkInfo();   
            if(info != null && info.isConnected()){
            	netstatus = true;
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
            netstatus = false;   
        } 
        return netstatus;
    } 
    

    
    /**
	* 获取当前的网络状态 -1：没有网络     1：WIFI网络    2：wap网络    3：net网络 
	* @param context 
	* @return 
	*/ 
	public static int getAPNType(Context context){ 
		int netType = -1; 
		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); 
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo(); 
		if(networkInfo==null){ 
			return netType; 
		} 
		int nType = networkInfo.getType(); 
		if(nType==ConnectivityManager.TYPE_MOBILE){ 
		
			if(networkInfo.getExtraInfo().toLowerCase().equals("cmnet")){ 
				netType = 3; 
			} 
			else{ 
				netType = 2; 
			} 
		} 
		else if(nType==ConnectivityManager.TYPE_WIFI){ 
			netType = 1; 
		} 
		return netType; 
	}
	
    
    
    /**
     * 通过GPRS获取本地IP
     * @return
     */
	public static String getLocalIpAddress(){
		String ip = "127.0.0.1";
        try
        {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
            {
               NetworkInterface intf = en.nextElement();
               for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
               {
                   InetAddress inetAddress = enumIpAddr.nextElement();
                   if (!inetAddress.isLoopbackAddress())
                   {
                	   ip = inetAddress.getHostAddress().toString();
                   }
               }
           }
        }
        catch (SocketException ex){
        	ex.printStackTrace();
        }
        return ip;
        
    }
}
