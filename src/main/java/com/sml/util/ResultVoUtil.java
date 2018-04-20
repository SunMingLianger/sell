package com.sml.util;

import com.sml.vo.ResultVo;

/**
 * Created by 神迷的亮
 * 2018-04-18 14:52
 */
public class ResultVoUtil
{
    public static ResultVo success(Object object)
    {
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg("成功");
        resultVo.setCode(0);
        resultVo.setData(object);
        return resultVo;
    }

    public static ResultVo error(Integer code, String msg)
    {
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg(msg);
        resultVo.setCode(code);
        return resultVo;
    }

    public static ResultVo success()
    {

        return success(null);
    }

}
