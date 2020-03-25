package com.liyulong.blog.main.common.validator;

import com.liyulong.blog.main.common.exception.MyException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 验证工具类
 */
public class ValidatorUtils {

    //快速结束模式
    private static Validator validator = Validation.byProvider(HibernateValidator.class)
            .configure().failFast(false).buildValidatorFactory().getValidator();

    /**
     * 校验对象
     * @param object        待校验对象
     * @param groups        待校验的组
     * @throws MyException  校验不通过，则报MyException异常
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws MyException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            List<String> collect = constraintViolations.stream().map(constant -> constant.getMessage()).collect(Collectors.toList());
            String msg = StringUtils.join(collect, ",");
            throw new MyException(msg);
        }
    }

    /**
     * 校检bean中一个属性
     * @param obj bean
     * @param propertyName 属性名
     */
    public static void ValidateProperty(Object obj,String propertyName){
        Set<ConstraintViolation<Object>> violations = validator.validateProperty(obj,propertyName);
        if(!violations.isEmpty()){
            for(ConstraintViolation<Object> violation : violations){
                throw new MyException(violation.getMessage());
            }
        }
    }

}
