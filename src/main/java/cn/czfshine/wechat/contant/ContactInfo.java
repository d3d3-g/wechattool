package cn.czfshine.wechat.contant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author:czfshine
 * @date:2018/2/26 15:22
 */

public class ContactInfo {

    private static ContactInfo contactInfo;
    private  Map<String,Contact> contactMap=new HashMap<>();
    private static Logger logger=LoggerFactory.getLogger("coninfo");
    static {
        contactInfo=new ContactInfo();
    }
    public static ContactInfo getInstance() {
        return contactInfo;
    }
    private transient Set<String> loseuser=new HashSet<>();
    public void addContacts(Contact[] contacts){
        for(Contact c:contacts){
            contactMap.put(c.getUid(),c);
        }
    }
    public void addContacts(Map<String,Contact> stringContactMap){
        contactMap.putAll(stringContactMap);
    }

    public String getUsernameFromUid(String uid){
        Contact contact = contactMap.getOrDefault(uid,null);
        if(contact!=null){
            return contact.getNickname();
        }
        if(!loseuser.contains(uid)){
            loseuser.add(uid);
            logger.warn("找不到id为{}的用户信息",uid);
        }

        return uid;
    }
}
