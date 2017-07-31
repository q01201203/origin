package com.origin.core.util;

import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.common.util.BASE64Util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lc on 2017/5/12.
 */
public class CustomToken {

    public static String generate(SimpleToken simpleToken) throws Exception {
        String str = simpleToken.getId()+"."+simpleToken.getAuthority();
        return BASE64Util.encryptBASE64(str).replaceAll("\r|\n", "");
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

    public static boolean check(int uAhority,int needAthority){
        if (uAhority>=needAthority){
            return true;
        }
        return false;
    }

    public static Object tokenValidate(HttpServletRequest request,int needAthority){
        SimpleToken simpleToken = (SimpleToken) request.getAttribute("token");
        Integer uid = (Integer) simpleToken.getId();
        Integer authority = (Integer) simpleToken.getAuthority();

        if (uid!=null && authority!=null){
            if (!check(authority, needAthority)){
                return Result.create(ResultCode.SSO_PERMISSION_ERROR).setMessage("权限等级不够");
            }else {
                return simpleToken;
            }
        }else {
            return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
        }
    }

    public static Object tokenValidate(SimpleToken simpleToken, int needAthority){
        Integer uid = (Integer) simpleToken.getId();
        Integer authority = (Integer) simpleToken.getAuthority();
        System.out.println("renxinhua uid " +uid);
        if (uid!=null && authority!=null){
            if (!check(authority, needAthority)){
                return Result.create(ResultCode.SSO_PERMISSION_ERROR).setMessage("权限等级不够");
            }else {
                return simpleToken;
            }
        }else {
            return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
        }
    }
}
