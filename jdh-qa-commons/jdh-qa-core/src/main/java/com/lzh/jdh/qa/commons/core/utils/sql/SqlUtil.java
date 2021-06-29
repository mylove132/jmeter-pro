package com.lzh.jdh.qa.commons.core.utils.sql;
import com.lzh.jdh.qa.commons.core.exception.CommonExceptionEnumInterface;
import com.lzh.jdh.qa.commons.core.exception.ValidateException;
import com.lzh.jdh.qa.commons.core.utils.StringUtils;

/**
 * sql操作工具类
 *
 */
public class SqlUtil
{
    /**
     * 仅支持字母、数字、下划线、空格、逗号、小数点（支持多个字段排序）
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,\\.]+";

    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String value)
    {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value))
        {
            throw new ValidateException(CommonExceptionEnumInterface.UTIL_SQL_VALIDATE_EXCEPTION);
        }
        return value;
    }

    /**
     * 验证 order by 语法是否符合规范
     */
    public static boolean isValidOrderBySql(String value)
    {
        return value.matches(SQL_PATTERN);
    }
}
