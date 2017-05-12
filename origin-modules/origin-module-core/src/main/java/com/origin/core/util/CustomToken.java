package com.origin.core.util;

import com.origin.common.util.BASE64Util;

/**
 * Created by lc on 2017/5/12.
 */
public class CustomToken {

    public static String generate(SimpleToken simpleToken) throws Exception {
        String str = simpleToken.getId()+"."+simpleToken.getAuthority();
        return BASE64Util.encryptBASE64(str);
    }

    public static SimpleToken parse(String str) throws Exception {
        SimpleToken simpleToken = new SimpleToken();
        if (StringUtil.isNullOrBlank(str)){
            simpleToken.setId(-1);
            simpleToken.setAuthority(-1);
            return simpleToken;
        }
        String[] strs = BASE64Util.decryptBASE64(str).split("\\.");
        simpleToken.setId(Integer.parseInt(strs[0]));
        simpleToken.setAuthority(Integer.parseInt(strs[1]));
        return simpleToken;
    }

    public static boolean check(int uAhority,int needAhority){
        if (uAhority>=needAhority){
            return true;
        }
        return false;
    }
}
