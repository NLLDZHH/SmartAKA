package com.njust.SmartAKA.controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;  

import javax.management.MBeanServer;
import javax.servlet.http.HttpServletRequest;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



//导入JPBC相应的jar包
import it.unisa.dia.gas.jpbc.*;  
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;  
import java.lang.reflect.Proxy;  
import java.util.Calendar;  
import java.text.SimpleDateFormat;  
import java.util.Date;



@Controller
public class TestController{
	//static String userna;
	List<HashMap> listOfParaments;
	protected Element s, a,b, P, Ppub, SA, QA,SB,QB, TA,TB,KA,KB,hA,hB;   
    private Field G1, Zr;  
    private Pairing pairing;  
	
	
	@RequestMapping("users/keys/buildSystem.json")
	public void buildSystem(){
		System.out.println("开始buildSystem");
		init();
		s = Zr.newRandomElement().getImmutable();// //随机生成主私钥s  
        P = G1.newRandomElement().getImmutable();// 生成G1的生成元P  
        Ppub = P.mulZn(s);// 计算Ppub=sP,注意顺序  ，生成主公钥Ppub。
        //String ppp=P.toString();
        //System.out.println("G1群的生成元P=" + P); 
       // System.out.println("G1群的生成元P="+ppp);
      
        //System.out.println("s属于Zr群，主私钥s=" + s);  
       // System.out.println("Ppub属于G1群，主公钥Ppub=" + Ppub);  
	}

	
	/**
	@RequestMapping("users/keys/extractSecretKeyOfAlice.json")
	public @ResponseBody List<HashMap> extractSecretKeyOfAlice() 
	{   
	   //System.out.println("怎么回事");
	 //  String P="Lucky!!!"; 
	   System.out.println("-------------------Alice的公私钥对产生阶段----------------------");  
       QA = pairing.getG1().newElement().setFromHash("IDa".getBytes(), 0, 3)  
               .getImmutable();// //从长度为3的Hash值IDu确定用户U产生的公钥Qa，生成用户U的公钥。  
       SA = QA.mulZn(s).getImmutable();//生成用户U的私钥。  
       System.out.println("Alice的公钥：QA=" + QA);  
       System.out.println("Alice的私钥：SA=" + SA);  
	   List<HashMap> listone = new ArrayList();
	   HashMap<String, String> newsMap = new HashMap<String, String>();
		newsMap.put("P",SA.toString() );
		//newsMap.put("content", tmp.getTitle());
	   listone.add(newsMap);
	    return listone;
	 
	}
	 * @return 
	
	**/
	@RequestMapping(value = "users/keys/extractSecretKeyOf{username}" ,method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody List<HashMap>  getSecretKey(@PathVariable("username") String username) {  
		
		 System.out.println("-------------------Alice的公私钥对产生阶段----------------------");  
		 System.out.println(username);
		   QA = pairing.getG1().newElement().setFromHash(username.getBytes(), 0,username.length())  
	               .getImmutable();// //从长度为3的Hash值IDu确定用户U产生的公钥Qa，生成用户U的公钥。  
	       SA = QA.mulZn(s).getImmutable();//生成用户U的私钥。
	       if(listOfParaments.isEmpty()){
	    	  
			   
	    	   System.out.println("listOfParaments是空的");
	    	   HashMap<String,Element> paramentMap1=new HashMap<String,Element>();
		       paramentMap1.put("public key of"+username, QA);
		       HashMap<String,Element> paramentMap2=new HashMap<String,Element>();
		       paramentMap2.put("private key of"+username, SA);
		       listOfParaments.add(paramentMap1);
		       listOfParaments.add(paramentMap2);
	       }
	       else{
	    	   System.out.println("listOfParaments不空");
	    	   HashMap<String,Element> paramentMap3=new HashMap<String,Element>();
		       paramentMap3.put("public key of"+username, QA);
		       HashMap<String,Element> paramentMap4=new HashMap<String,Element>();
		       paramentMap4.put("private key of"+username, SA);
		       listOfParaments.add(paramentMap3);
		       listOfParaments.add(paramentMap4);
	       }
	      
		   List<HashMap> listone = new ArrayList();
		   HashMap<String, String> newsMap = new HashMap<String, String>();
			newsMap.put("P",SA.toString() );
			//newsMap.put("content", tmp.getTitle());
		   listone.add(newsMap);
		    return listone;

    }
	
	
	@RequestMapping(value = "users/keys/computeTof{username}" ,method = {RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody List<HashMap>  getT(@PathVariable("username") String username) {  
	        System.out.println("-------------------Alice随机选择一个Zr群中的元素a，然后计算TA=aP，再将TA发送给Bob阶段----------------------");  
	        a = Zr.newRandomElement().getImmutable();  //
	        TA = P.mulZn(a);  
	        if(listOfParaments.size()==4){
	        	 System.out.println("listOfParaments长度是4");
		    	   HashMap<String,Element> paramentMap1=new HashMap<String,Element>();
			       paramentMap1.put("a of"+username, a);
			       HashMap<String,Element> paramentMap2=new HashMap<String,Element>();
			       paramentMap2.put("T of"+username, TA);
			       listOfParaments.add(paramentMap1);
			       listOfParaments.add(paramentMap2);
	        }
	        else{
	               System.out.println("listOfParaments是6了");
	    	       HashMap<String,Element> paramentMap3=new HashMap<String,Element>();
		           paramentMap3.put("a of"+username, a);
		           HashMap<String,Element> paramentMap4=new HashMap<String,Element>();
		           paramentMap4.put("T of"+username, TA);
		           listOfParaments.add(paramentMap3);
		           listOfParaments.add(paramentMap4);
	        	
	        }      
	        List<HashMap> listone = new ArrayList();
	 	    HashMap<String, String> newsMap = new HashMap<String, String>();
	 		newsMap.put("P",TA.toString() );
	 	    listone.add(newsMap);
	 	    return listone;
	    } 
	
	
	
	
	@RequestMapping(value = "users/keys/createKOf{username}" ,method = {RequestMethod.GET, RequestMethod.POST})
    public  @ResponseBody List<HashMap> getK (@PathVariable("username") String username) {   		   
            System.out.println(username.split("#")[0]);
            System.out.println(username.split("#")[1]); 
            String stringKA;
         //   System.out.println("-------------------Alice计算协商密钥KA=e(aQB,Ppub)e(SA,TB)阶段----------------------");    
          //   String stringKA=SHA((KA.toString()+hA.toString()), "SHA-256");
         //   hA=TB.mulZn(a);
            if(listOfParaments.size()==8){      
                 for(int i=0;i<listOfParaments.size();i++){
                   HashMap<String,Element> map = listOfParaments.get(i);
                   if(map.keySet().contains("private key of"+username.split("#")[0])){
                 	  System.out.println((Element) listOfParaments.get(i).get("private key of"+username.split("#")[0]));
                 	  SA=(Element) listOfParaments.get(i).get("private key of"+username.split("#")[0]);
                   }
                 }
                 
                 for(int i=0;i<listOfParaments.size();i++){
                     HashMap<String,Element> map = listOfParaments.get(i);
                     if(map.keySet().contains("a of"+username.split("#")[0])){
                   	  System.out.println((Element) listOfParaments.get(i).get("a of"+username.split("#")[0]));
                   	  a=(Element) listOfParaments.get(i).get("a of"+username.split("#")[0]);
                     }
                   }
                 
                 for(int i=0;i<listOfParaments.size();i++){
                     HashMap<String,Element> map = listOfParaments.get(i);
                     if(map.keySet().contains("T of"+username.split("#")[1])){
                   	  System.out.println((Element) listOfParaments.get(i).get("T of"+username.split("#")[1]));
                   	  TB=(Element) listOfParaments.get(i).get("T of"+username.split("#")[1]);
                     }
                   }
                 for(int i=0;i<listOfParaments.size();i++){
                     HashMap<String,Element> map = listOfParaments.get(i);
                     if(map.keySet().contains("public key of"+username.split("#")[1])){
                   	  System.out.println((Element) listOfParaments.get(i).get("public key of"+username.split("#")[1]));
                   	  QB=(Element) listOfParaments.get(i).get("public key of"+username.split("#")[1]);
                     }
                   }
                 
                 
                 
                 KA=pairing.pairing((Ppub.mulZn(a)).mul(SA),TB.mul(QB));     
                 System.out.println("KA=e(aPpub+SA,TB+QB)=" + KA);                  
                 stringKA=SHA(KA.toString(), "SHA-256");
            }
            else{
            	for(int i=0;i<listOfParaments.size();i++){
                    HashMap<String,Element> map = listOfParaments.get(i);
                    if(map.keySet().contains("private key of"+username.split("#")[0])){
                  	  System.out.println((Element) listOfParaments.get(i).get("private key of"+username.split("#")[0]));
                  	  SA=(Element) listOfParaments.get(i).get("private key of"+username.split("#")[0]);
                    }
                  }
                  
                  for(int i=0;i<listOfParaments.size();i++){
                      HashMap<String,Element> map = listOfParaments.get(i);
                      if(map.keySet().contains("a of"+username.split("#")[0])){
                    	  System.out.println((Element) listOfParaments.get(i).get("a of"+username.split("#")[0]));
                    	  a=(Element) listOfParaments.get(i).get("a of"+username.split("#")[0]);
                      }
                    }
                  
                  for(int i=0;i<listOfParaments.size();i++){
                      HashMap<String,Element> map = listOfParaments.get(i);
                      if(map.keySet().contains("T of"+username.split("#")[1])){
                    	  System.out.println((Element) listOfParaments.get(i).get("T of"+username.split("#")[1]));
                    	  TB=(Element) listOfParaments.get(i).get("T of"+username.split("#")[1]);
                      }
                    }
                  for(int i=0;i<listOfParaments.size();i++){
                      HashMap<String,Element> map = listOfParaments.get(i);
                      if(map.keySet().contains("public key of"+username.split("#")[1])){
                    	  System.out.println((Element) listOfParaments.get(i).get("public key of"+username.split("#")[1]));
                    	  QB=(Element) listOfParaments.get(i).get("public key of"+username.split("#")[1]);
                      }
                    }
                  
                  
                  
                  KA=pairing.pairing((Ppub.mulZn(a)).mul(SA),TB.mul(QB));     
                  System.out.println("KA=e(aPpub+SA,TB+QB)=" + KA);                  
                  stringKA=SHA(KA.toString(), "SHA-256"); 
            	
            }
            
            List<HashMap> listone = new ArrayList();
	 	    HashMap<String, String> newsMap = new HashMap<String, String>();
	 		newsMap.put("P",stringKA);
	 	    listone.add(newsMap);
	 	    return listone;
  }
	
	
	
	
	
	
	/**
	
	@RequestMapping("users/keys/extractSecretKeyOfBob.json")
	public @ResponseBody List<HashMap> extractSecretKeyOfBob() {  
        System.out.println("-------------------Bob的公私钥对产生阶段----------------------");  
        QB = pairing.getG1().newElement().setFromHash("IDb".getBytes(), 0, 3)  
                .getImmutable();// //从长度为3的Hash值IDu确定用户U产生的公钥Qa，生成用户U的公钥。  
        SB = QB.mulZn(s).getImmutable();//生成用户U的私钥。  
        System.out.println("Bob的公钥：QB=" + QB);  
        System.out.println("Bob的私钥：SB=" + SB);  
        List<HashMap> listone = new ArrayList();
 	   HashMap<String, String> newsMap = new HashMap<String, String>();
 		newsMap.put("P",SB.toString() );
 		//newsMap.put("content", tmp.getTitle());
 	   listone.add(newsMap);
 	    return listone;
    }  
	
	
	
	
	@RequestMapping("users/keys/computeTA.json")
    public @ResponseBody List<HashMap> computeTA() {  
        System.out.println("-------------------Alice随机选择一个Zr群中的元素a，然后计算TA=aP，再将TA发送给Bob阶段----------------------");  
        a = Zr.newRandomElement().getImmutable();  //
        TA = P.mulZn(a);  
      //  T1 = pairing.pairing(Ppub, Qa).getImmutable();// 计算e（Ppub,Qa）  
       // T1 = T1.powZn(r).getImmutable();  
        System.out.println("Alice随机选择一个Zr群中的元素a=" + a);  
        System.out.println("Alice计算TA=aP,然后将TA发送给Bob，TA=" + TA);  
        //System.out.println("T1=e（Ppub,Qa）^r=" + T1);  
        List<HashMap> listone = new ArrayList();
 	   HashMap<String, String> newsMap = new HashMap<String, String>();
 		newsMap.put("P",TA.toString() );
 		//newsMap.put("content", tmp.getTitle());
 	   listone.add(newsMap);
 	    return listone;
    } 
	@RequestMapping("users/keys/computeTB.json")
	public @ResponseBody List<HashMap> computeTB() {  
        System.out.println("-------------------Bob随机选择一个Zr群中的元素b，然后计算TB=bP，再将TB发送给Alice阶段----------------------");  
        b = Zr.newRandomElement().getImmutable();  //
        TB = P.mulZn(b);  
      //  T1 = pairing.pairing(Ppub, Qa).getImmutable();// 计算e（Ppub,Qa）  
       // T1 = T1.powZn(r).getImmutable();  
        System.out.println("Bob随机选择一个Zr群中的元素b=" + b);  
        System.out.println("Bob计算TB=bP,然后将TB发送给Alice，TA=" + TB);  
        //System.out.println("T1=e（Ppub,Qa）^r=" + T1); 
        List<HashMap> listone = new ArrayList();
  	   HashMap<String, String> newsMap = new HashMap<String, String>();
  		newsMap.put("P",TB.toString() );
  		//newsMap.put("content", tmp.getTitle());
  	   listone.add(newsMap);
  	    return listone;
    }  
	
	
	
	
	
	
	
	
	
	@RequestMapping("users/keys/createKA.json")
	public @ResponseBody List<HashMap> createKA(){  
        System.out.println("-------------------Alice计算协商密钥KA=e(aQB,Ppub)e(SA,TB)阶段----------------------");    
       
        hA=TB.mulZn(a);
        KA=pairing.pairing((Ppub.mulZn(a)).mul(SA),TB.mul(QB));     
        System.out.println("KA=e(aPpub+SA,TB+QB)=" + KA); 
        String stringKA=SHA((KA.toString()+hA.toString()), "SHA-256");
       // int byt = V.getLengthInBytes();// 求V的字节长度，假设消息长度为128字节  
       // System.out.println("文本长度" + (byt + 128)); 
        List<HashMap> listone = new ArrayList();
  	   HashMap<String, String> newsMap = new HashMap<String, String>();
  		newsMap.put("P",stringKA);
  		//newsMap.put("content", tmp.getTitle());
  	   listone.add(newsMap);
  	    return listone;
    }  
	@RequestMapping("users/keys/createKB.json")
	 public  @ResponseBody List<HashMap> createKB() {  
        System.out.println("-------------------Bob计算协商密钥KB=e(bQA,Ppub)e(SB,TA)阶段----------------------");      
        
        hB=TA.mulZn(b);
        KB=pairing.pairing(TA.mul(QA),(Ppub.mulZn(b)).mul(SB)); 
        System.out.println("KB=e(TA+QA,bPpub+SB)=" + KB);  
        String stringKB=SHA((KB.toString()+hB.toString()), "SHA-256");
       // int byt = V.getLengthInBytes();// 求V的字节长度，假设消息长度为128字节  
       // System.out.println("文本长度" + (byt + 128));  
        List<HashMap> listone = new ArrayList();
   	   HashMap<String, String> newsMap = new HashMap<String, String>();
   		newsMap.put("P",stringKB );
   		//newsMap.put("content", tmp.getTitle());
   	   listone.add(newsMap);
   	    return listone;
    }  
	**/
	private void checkSymmetric(Pairing pairing) {  
        if (!pairing.isSymmetric()) {  
            throw new RuntimeException("密钥不对称!");  
        }  
    }  
	 private void init() {  
		    listOfParaments = new ArrayList();
	        pairing = PairingFactory.getPairing("conf/spring/a.properties");//  
	        PairingFactory.getInstance().setUsePBCWhenPossible(true);  
	        checkSymmetric(pairing);  
	        //将变量r初始化为Zr中的元素  
	        Zr = pairing.getZr();  
	        a = Zr.newElement(); 
	        b=Zr.newElement(); 
	        //将变量Ppub，Qa，Sa，V初始化为G1中的元素，G1是加法群  
	        G1 = pairing.getG1();  
	        Ppub = G1.newElement();  
	        QA = G1.newElement();  
	        SA = G1.newElement(); 	        
	        QB=G1.newElement(); 
	        SB=G1.newElement(); 
	        hA=G1.newElement();
	        hB=G1.newElement();
	        TA=G1.newElement();
	        TB=G1.newElement();     
	        //将变量T1，T2V初始化为GT中的元素，GT是乘法群  
	        Field GT = pairing.getGT();         
	        KA=GT.newElement();  
	        KB=GT.newElement();  
	    } 
	   private String SHA(final String strText, final String strType)  
	    {  
	      // 返回值  
	      String strResult = null;  
	    
	      // 是否是有效字符串  
	      if (strText != null && strText.length() > 0)  
	      {  
	        try  
	        {  
	          // SHA 加密开始  
	          // 创建加密对象 并傳入加密類型  
	          MessageDigest messageDigest = MessageDigest.getInstance(strType);  
	          // 传入要加密的字符串  
	          messageDigest.update(strText.getBytes());  
	          // 得到 byte 類型结果  
	          byte byteBuffer[] = messageDigest.digest();  
	    
	          // 將 byte 轉換爲 string  
	          StringBuffer strHexString = new StringBuffer();  
	          // 遍歷 byte buffer  
	          for (int i = 0; i < byteBuffer.length; i++)  
	          {  
	            String hex = Integer.toHexString(0xff & byteBuffer[i]);  
	            if (hex.length() == 1)  
	            {  
	              strHexString.append('0');  
	            }  
	            strHexString.append(hex);  
	          }  
	          // 得到返回結果  
	          strResult = strHexString.toString();  
	        }  
	        catch (NoSuchAlgorithmException e)  
	        {  
	          e.printStackTrace();  
	        }  
	      }  
	    
	      return strResult;  
	    }  
}
