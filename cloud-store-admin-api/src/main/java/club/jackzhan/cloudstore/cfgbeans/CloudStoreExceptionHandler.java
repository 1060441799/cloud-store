package club.jackzhan.cloudstore.cfgbeans;

import club.jackzhan.cloudstore.exception.BusinessException;
import club.jackzhan.cloudstore.util.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description: 全局异常统一处理
 * Date: 2019-05-22 15:27
 *
 * @Author: JackZhan
 */
@RestControllerAdvice
@Slf4j
public class CloudStoreExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResultResponse defaultErrorHandler(HttpServletRequest req, BusinessException e) throws BusinessException {
        log.error("请求接口：{}，异常：{}", req.getRequestURI(), e.toString());
        return ResultResponse.failure(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultResponse defaultErrorHandler(HttpServletRequest req, Exception e) {
//        log.error("请求接口：{}，异常：{}", req.getRequestURI(), e.getStackTrace());
        log.error("请求接口：{}", req.getRequestURI());
        log.error("异常：", e);

        return ResultResponse.failure();
    }
}
